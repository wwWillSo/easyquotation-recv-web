package com.szw.easyquotation.socket;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SocketServer;
import org.springframework.stereotype.Component;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import com.szw.easyquotation.config.SystemConfig;


@ServerEndpoint(value = "/optionalDeepSocketServer")
@Component
public class OptionalDeepSocketServer {

	private Logger logger = Logger.getLogger(SocketServer.class);

	private static Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();

	private static Map<String, String> topicMap = new ConcurrentHashMap<String, String>();

	private static ExecutorService exec = Executors.newCachedThreadPool();

	@OnOpen
	public void onOpen(Session session) {
		logger.info("onOpen sessionId:" + session.getId());
		sessionMap.put(session.getId(), session);
		topicMap.put(session.getId(), "init");
		if (((ThreadPoolExecutor) exec).getActiveCount() <= 0) {
			getContentAndSetToStatic(SystemConfig.ZMQ_HOST, "marketdata");
		}
	}

	@OnMessage
	public void onMessage(String topic, Session session) {
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		if (StringUtils.isEmpty(topic)) {
			topic = "init";
		}
		topicMap.put(session.getId(), topic);
		logger.info("onMessage sessionId:" + session.getId() + " topic:" + topic);
	}

	public static void getContentAndSetToStatic(String host, String topic) {

		exec.execute(new Runnable() {
			public void run() {
				Context context = ZMQ.context(1);
				Socket subscriber = context.socket(ZMQ.SUB);
				subscriber.connect(host);
				subscriber.subscribe(topic.getBytes());
				while (!Thread.currentThread().isInterrupted()) {
					Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
					String data = subscriber.recvStr();
					String topics = data.substring(0, data.lastIndexOf("{"));
					String contents = data.substring(data.lastIndexOf("{"));
					synchronized (OptionalDeepSocketServer.class) {
						actionMethod(topics, contents);
					}
				}
				subscriber.close();
				context.term();
			}
		});

	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info("onClose sessionId:" + session.getId());
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		synchronized (SocketServer.class) {
			delMethod(session);
		}
	}

	@OnError
	public void error(Session session, java.lang.Throwable throwable) {
		logger.info("OnError sessionId:" + session.getId());
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		synchronized (SocketServer.class) {
			delMethod(session);
		}
	}

	public synchronized static void actionMethod(String topic, String content) {
		if (StringUtils.isEmpty(topic) || StringUtils.isEmpty(content))
			return;

		Set<Map.Entry<String, Session>> set = sessionMap.entrySet();
		for (Map.Entry<String, Session> i : set) {
			try {
				if (topic.startsWith(topicMap.get(i.getValue().getId()))) {
					i.getValue().getBasicRemote().sendText("{type:'" + "" + "',text:'" + content + "'}");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public synchronized static void delMethod(Session session) {
		if (session != null) {
			if (sessionMap.containsKey(session.getId()))
				sessionMap.remove(session.getId());
			if (topicMap.containsKey(session.getId()))
				topicMap.remove(session.getId());
		}
	}
}
