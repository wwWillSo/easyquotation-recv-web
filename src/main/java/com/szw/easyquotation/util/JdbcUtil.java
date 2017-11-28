package com.szw.easyquotation.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szw.easyquotation.entity.MarketDataCandleChart;


@Component
public class JdbcUtil {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void insertBatchFlush(List list) {
		try {
			for (int i = 0; i < list.size(); i++) {
				em.persist(list.get(i));
			}
			em.flush();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 整个list有可能除到最后还有余数，这时候的i是最后能被整除的i了 <p>TODO</p>
	 * 
	 * @param list
	 * @param count
	 * @author 苏镇威 2017年11月13日 上午11:03:14
	 */
	@Transactional
	public void insertBatch(List list, int count) {
		try {
			int temp = 0;
			for (int i = 0; i < list.size(); i++) {
				em.persist(list.get(i));
				if (i != 0 && i % count == 0) {
					em.flush();
					em.clear();
					System.out.println(i + "条持久化完毕...");
					continue;
				}
				temp += 1;
			}
			if (list.size() - temp != 0) {
				insertBatch(list.subList(temp, list.size()), count / 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public static void batchInsert(List list, JpaRepository repository) {
		for (int i = 0; i < list.size(); i++) {
			if (i != 0 && i % 200 == 0) {
				int temp = 0;
				for (int j = temp; j < i; j++) {
					repository.save(list.get(j));
					System.out.println("持久化" + j);
				}
				repository.flush();
				temp = i;
				System.out.println(temp + "条持久化完毕...");
			}
		}
	}

	public static void main(String args[]) {

		List<MarketDataCandleChart> list = new ArrayList<MarketDataCandleChart>();
		MarketDataCandleChart chart1 = new MarketDataCandleChart();
		chart1.setId(1l);
		MarketDataCandleChart chart2 = new MarketDataCandleChart();
		chart1.setId(2l);
		MarketDataCandleChart chart3 = new MarketDataCandleChart();
		chart1.setId(3l);

		list.add(chart1);
		list.add(chart2);
		list.add(chart3);

		// batchInsert(list);

	}
}
