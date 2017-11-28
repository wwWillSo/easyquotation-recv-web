package com.szw.easyquotation.container;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szw.easyquotation.entity.MarketDataCandleChart;
import com.szw.easyquotation.entity.RealTimeMarketdata;
import com.szw.easyquotation.repository.MarketdataCandleChartRepository;
import com.szw.easyquotation.util.DateUtil;
import com.szw.easyquotation.util.HttpClientUtils;
import com.szw.easyquotation.util.JdbcUtil;
import com.szw.easyquotation.util.SpringUtil;


public class ChartContainer {

	public final static int[] chartTypeArr = { 1, 3, 5, 10, 30, 60, 1440 };

	public final static ConcurrentMap<String, Map<String, MarketDataCandleChart>> chartMap = new ConcurrentHashMap<String, Map<String, MarketDataCandleChart>>();

	public static List<RealTimeMarketdata> dataList = null;

	public static boolean hasbeenInit = false;

	public static void genDataMap(MarketDataCandleChart chart) {

		Map<String, MarketDataCandleChart> map = ChartContainer.chartMap.get(chart.getStockcode());
		if (map == null) {
			map = new HashMap<String, MarketDataCandleChart>();
			map.put(chart.getChartType() + "", chart);
			chartMap.put(chart.getStockcode(), map);
		}

		map.put(chart.getChartType() + "", chart);
		chartMap.put(chart.getStockcode(), map);
	}

	public static void genDataMap(List<MarketDataCandleChart> list) {

		List<MarketDataCandleChart> newList = new ArrayList<MarketDataCandleChart>();

		for (MarketDataCandleChart chart : list) {
			// 第一种情况：此code未曾初始化过
			if (null == chartMap.get(chart.getStockcode())) {
				Map<String, MarketDataCandleChart> timeMap = new HashMap<String, MarketDataCandleChart>();
				timeMap.put(chart.getChartType() + "", chart);
				chartMap.put(chart.getStockcode(), timeMap);

				// System.out.println("调用1");

			} else if (null == chartMap.get(chart.getStockcode()).get(chart.getChartType() + "")) {

				// 更新chartMap
				Map<String, MarketDataCandleChart> timeMap = chartMap.get(chart.getStockcode());
				timeMap.put(chart.getChartType() + "", chart);
				chartMap.put(chart.getStockcode(), timeMap);

				// if (chart.getStockcode().equals("000001")) {
				// System.out.println(chartMap.get("000001").size());
				// }

				// System.out.println("调用2");
			} else {

				MarketDataCandleChart oldChart = chartMap.get(chart.getStockcode()).get(chart.getChartType() + "");

				// 第二种情况：此code已经初始化过，下面操作更新此code下的data
				// 如果此code已经初始化过，说明已经不是第一次怼进来的了
				// 这时候需要判断一下旧记录的时间，如果超过了chartType，代表需要更新到数据库
				if (null != oldChart && DateUtil.countMinutes(chart.getCreateTime(), oldChart.getCreateTime()) >= chart.getChartType()) {
					newList.add(chart);

					// 更新chartMap
					Map<String, MarketDataCandleChart> map = chartMap.get(chart.getStockcode());
					map.put(chart.getChartType() + "", chart);
					chartMap.put(chart.getStockcode(), map);
				}
				// System.out.println("调用3");
			}
		}

		// if (chartMap.get("000001") != null && chartMap.get("000001").get(1) != null) {
		// MarketDataCandleChart fir = chartMap.get("000001").get(1);
		// String createTime = null == fir ? "无" : fir.getCreateTime().toString();
		// System.out
		// .println("list.size:" + list.size() + ", newList.size:" + newList.size() + ",
		// chartMap.size:" + chartMap.size() + ", 000001:" + createTime);
		// }

		if (newList.size() != 0) {
			// new JdbcUtil().insertBatchFlush(newList);
			SpringUtil.getBean("jdbcUtil", JdbcUtil.class).insertBatchFlush(newList);
			System.out.println(newList.size() + "条持久化完毕...");
		}
	}

	public static boolean initDataMap(MarketdataCandleChartRepository marketDataCandleChartRepository, List<RealTimeMarketdata> dataList) {
		try {
			// long start = new Date().getTime();
			Date now = DateUtil.resetZeroSeconds(new Date());
			List<MarketDataCandleChart> list = new ArrayList<MarketDataCandleChart>();
			for (RealTimeMarketdata marketdata : dataList) {

				for (int min : ChartContainer.chartTypeArr) {
					MarketDataCandleChart newChart = new MarketDataCandleChart();
					BeanUtils.copyProperties(marketdata, newChart);
					newChart.setChartType(min);
					newChart.setCreateTime(now);
					newChart.setUpdateTime(newChart.getCreateTime());
					list.add(newChart);
				}

			}

			// System.out.println("提交给genDataMap方法的list.size:" + list.size());

			ChartContainer.genDataMap(list);
			// long end = new Date().getTime();
			// System.out.println((end - start));
			// if (ChartContainer.chartMap.size() == ChartContainer.dataList.size())
			ChartContainer.hasbeenInit = true;

			// System.out.println("chartMap初始化完成，data数量为：" + chartMap.size());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static List<RealTimeMarketdata> getAllMarketdata(String marketdataUrl) {

		String entity = HttpClientUtils.doGet(marketdataUrl);
		JSONObject jsonObj = JSON.parseObject(entity);
		JSONArray result = jsonObj.getJSONArray("marketdata");
		ChartContainer.dataList = JSON.parseArray(result.toJSONString(), RealTimeMarketdata.class);

		return ChartContainer.dataList;
	}

}
