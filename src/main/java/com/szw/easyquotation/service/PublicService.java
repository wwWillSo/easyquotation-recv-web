package com.szw.easyquotation.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.szw.easyquotation.entity.MarketDataCandleChart;
import com.szw.easyquotation.entity.RealTimeMarketdata;
import com.szw.easyquotation.repository.MarketdataCandleChartRepository;
import com.szw.easyquotation.util.DateUtil;


@Service
public class PublicService {

	@Autowired
	private MarketdataCandleChartRepository marketdataCandleChartRepository;

	@Autowired
	private RedisTemplate redisTemplate;

	public List<MarketDataCandleChart> retrieveMarketDataCandleChart(String stockcode, int chartType) {
		return marketdataCandleChartRepository.findByStockcodeAndChartType(stockcode, chartType);
	}

	public List<Object[]> retrieveKChart(String stockcode, int chartType) {

		List<Object[]> charts = new ArrayList<Object[]>();

		List<MarketDataCandleChart> list = marketdataCandleChartRepository.findByStockcodeAndChartType(stockcode, chartType);

		for (MarketDataCandleChart o : list) {
			Object[] chart = new Object[6];
			String date = DateUtil.format_yyyyMMddHHmmss(o.getCreateTime());
			BigDecimal open = o.getOpen();
			BigDecimal close = o.getClose();
			BigDecimal low = o.getLow();
			BigDecimal high = o.getHigh();
			BigDecimal vol = o.getVolume();

			chart[0] = date;
			chart[1] = open;
			chart[2] = close;
			chart[3] = low;
			chart[4] = high;
			chart[5] = vol;
			charts.add(chart);
		}

		return charts;
	}

	public RealTimeMarketdata getMarketdataByCode(String stockcode) {
		return (RealTimeMarketdata) redisTemplate.opsForValue().get(stockcode);
	}

}
