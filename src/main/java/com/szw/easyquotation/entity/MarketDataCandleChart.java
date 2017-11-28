package com.szw.easyquotation.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the market_data_candle_chart database table.
 * 
 */
@Entity
@Table(name = "market_data_candle_chart")
@NamedQuery(name = "MarketDataCandleChart.findAll", query = "SELECT m FROM MarketDataCandleChart m")
public class MarketDataCandleChart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "chart_type")
	private int chartType;			// 图表类型

	private BigDecimal close;		// 收盘价

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;		// 生成时间

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Date updateTime;		// 更新时间

	private BigDecimal high;		// 最高价

	private BigDecimal low;			// 最低价

	private String name;			// 股票名称

	private BigDecimal now;			// 当前价

	private BigDecimal open;		// 开盘价

	private String stockcode;		// 股票代码

	private BigDecimal turnover;	// 成交额

	private BigDecimal volume;		// 成交量

	public MarketDataCandleChart() {
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getChartType() {
		return this.chartType;
	}

	public void setChartType(int chartType) {
		this.chartType = chartType;
	}

	public BigDecimal getClose() {
		return this.close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getHigh() {
		return this.high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return this.low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getNow() {
		return this.now;
	}

	public void setNow(BigDecimal now) {
		this.now = now;
	}

	public BigDecimal getOpen() {
		return this.open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public String getStockcode() {
		return this.stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}

	public BigDecimal getTurnover() {
		return this.turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

	public BigDecimal getVolume() {
		return this.volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

}