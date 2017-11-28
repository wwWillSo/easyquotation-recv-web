package com.szw.easyquotation.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * The persistent class for the real_time_marketdata database table.
 * 
 */
@Entity
@Table(name="real_time_marketdata")
@NamedQuery(name="RealTimeMarketdata.findAll", query="SELECT r FROM RealTimeMarketdata r")
public class RealTimeMarketdata implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String stockcode;

	private BigDecimal ask1;

	@Column(name="ask1_volume")
	private BigDecimal ask1Volume;

	private BigDecimal ask2;

	@Column(name="ask2_volume")
	private BigDecimal ask2Volume;

	private BigDecimal ask3;

	@Column(name="ask3_volume")
	private BigDecimal ask3Volume;

	private BigDecimal ask4;

	@Column(name="ask4_volume")
	private BigDecimal ask4Volume;

	private BigDecimal ask5;

	@Column(name="ask5_volume")
	private BigDecimal ask5Volume;

	private BigDecimal bid1;

	@Column(name="bid1_volume")
	private BigDecimal bid1Volume;

	private BigDecimal bid2;

	@Column(name="bid2_volume")
	private BigDecimal bid2Volume;

	private BigDecimal bid3;

	@Column(name="bid3_volume")
	private BigDecimal bid3Volume;

	private BigDecimal bid4;

	@Column(name="bid4_volume")
	private BigDecimal bid4Volume;

	private BigDecimal bid5;

	@Column(name="bid5_volume")
	private BigDecimal bid5Volume;

	private BigDecimal buy;

	private BigDecimal close;

	@Temporal(TemporalType.DATE)
	@JSONField(format="yyyy-MM-dd")
	private Date date;

	private BigDecimal high;

	private BigDecimal low;

	private String name;

	private BigDecimal now;

	private BigDecimal open;

	private BigDecimal sell;

	@JSONField(format="HH:mm:ss", serializeUsing=Date.class)
	private Date time;

	private BigDecimal turnover;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	private BigDecimal volume;

	public RealTimeMarketdata() {
	}

	public BigDecimal getAsk1() {
		return this.ask1;
	}

	public void setAsk1(BigDecimal ask1) {
		this.ask1 = ask1;
	}

	public BigDecimal getAsk1Volume() {
		return this.ask1Volume;
	}

	public void setAsk1Volume(BigDecimal ask1Volume) {
		this.ask1Volume = ask1Volume;
	}

	public BigDecimal getAsk2() {
		return this.ask2;
	}

	public void setAsk2(BigDecimal ask2) {
		this.ask2 = ask2;
	}

	public BigDecimal getAsk2Volume() {
		return this.ask2Volume;
	}

	public void setAsk2Volume(BigDecimal ask2Volume) {
		this.ask2Volume = ask2Volume;
	}

	public BigDecimal getAsk3() {
		return this.ask3;
	}

	public void setAsk3(BigDecimal ask3) {
		this.ask3 = ask3;
	}

	public BigDecimal getAsk3Volume() {
		return this.ask3Volume;
	}

	public void setAsk3Volume(BigDecimal ask3Volume) {
		this.ask3Volume = ask3Volume;
	}

	public BigDecimal getAsk4() {
		return this.ask4;
	}

	public void setAsk4(BigDecimal ask4) {
		this.ask4 = ask4;
	}

	public BigDecimal getAsk4Volume() {
		return this.ask4Volume;
	}

	public void setAsk4Volume(BigDecimal ask4Volume) {
		this.ask4Volume = ask4Volume;
	}

	public BigDecimal getAsk5() {
		return this.ask5;
	}

	public void setAsk5(BigDecimal ask5) {
		this.ask5 = ask5;
	}

	public BigDecimal getAsk5Volume() {
		return this.ask5Volume;
	}

	public void setAsk5Volume(BigDecimal ask5Volume) {
		this.ask5Volume = ask5Volume;
	}

	public BigDecimal getBid1() {
		return this.bid1;
	}

	public void setBid1(BigDecimal bid1) {
		this.bid1 = bid1;
	}

	public BigDecimal getBid1Volume() {
		return this.bid1Volume;
	}

	public void setBid1Volume(BigDecimal bid1Volume) {
		this.bid1Volume = bid1Volume;
	}

	public BigDecimal getBid2() {
		return this.bid2;
	}

	public void setBid2(BigDecimal bid2) {
		this.bid2 = bid2;
	}

	public BigDecimal getBid2Volume() {
		return this.bid2Volume;
	}

	public void setBid2Volume(BigDecimal bid2Volume) {
		this.bid2Volume = bid2Volume;
	}

	public BigDecimal getBid3() {
		return this.bid3;
	}

	public void setBid3(BigDecimal bid3) {
		this.bid3 = bid3;
	}

	public BigDecimal getBid3Volume() {
		return this.bid3Volume;
	}

	public void setBid3Volume(BigDecimal bid3Volume) {
		this.bid3Volume = bid3Volume;
	}

	public BigDecimal getBid4() {
		return this.bid4;
	}

	public void setBid4(BigDecimal bid4) {
		this.bid4 = bid4;
	}

	public BigDecimal getBid4Volume() {
		return this.bid4Volume;
	}

	public void setBid4Volume(BigDecimal bid4Volume) {
		this.bid4Volume = bid4Volume;
	}

	public BigDecimal getBid5() {
		return this.bid5;
	}

	public void setBid5(BigDecimal bid5) {
		this.bid5 = bid5;
	}

	public BigDecimal getBid5Volume() {
		return this.bid5Volume;
	}

	public void setBid5Volume(BigDecimal bid5Volume) {
		this.bid5Volume = bid5Volume;
	}

	public BigDecimal getBuy() {
		return this.buy;
	}

	public void setBuy(BigDecimal buy) {
		this.buy = buy;
	}

	public BigDecimal getClose() {
		return this.close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public BigDecimal getSell() {
		return this.sell;
	}

	public void setSell(BigDecimal sell) {
		this.sell = sell;
	}

	public String getStockcode() {
		return this.stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public BigDecimal getTurnover() {
		return this.turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getVolume() {
		return this.volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

}