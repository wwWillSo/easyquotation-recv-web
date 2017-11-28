package com.szw.easyquotation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szw.easyquotation.container.ChartContainer;
import com.szw.easyquotation.entity.MarketDataCandleChart;
import com.szw.easyquotation.entity.RealTimeMarketdata;
import com.szw.easyquotation.repository.MarketdataCandleChartRepository;
import com.szw.easyquotation.repository.RealTimeMarketdataRepository;
import com.szw.easyquotation.service.PublicService;


@Controller
public class MarketdataController {

	@Autowired
	private RealTimeMarketdataRepository realTimeMarketdataRepository;

	@Autowired
	private MarketdataCandleChartRepository marketdataCandleChartRepository;

	@Autowired
	private PublicService publicService;

	@RequestMapping("/getMarketdataByCode/{stockcode}")
	@ResponseBody
	public RealTimeMarketdata getMarketdataByCode(@PathVariable String stockcode) {
		return publicService.getMarketdataByCode(stockcode);
	}

	@RequestMapping("/retrieveMarketDataCandleChart/{stockcode}/{chartType}")
	@ResponseBody
	public List<MarketDataCandleChart> retrieveMarketDataCandleChart(@PathVariable String stockcode, @PathVariable int chartType) {
		return publicService.retrieveMarketDataCandleChart(stockcode, chartType);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/retrieveKChart/{stockcode}/{chartType}")
	@ResponseBody
	public List<Object[]> retrieveKChart(@PathVariable String stockcode, @PathVariable int chartType) {

		return publicService.retrieveKChart(stockcode, chartType);

	}

	@RequestMapping("/getDataFromPy/{stockcode}")
	@ResponseBody
	public List<RealTimeMarketdata> getChartFromPy(@PathVariable String stockcode) {
		return ChartContainer.getAllMarketdata("http://127.0.0.1:8081/getAllMarketData");
	}
}
