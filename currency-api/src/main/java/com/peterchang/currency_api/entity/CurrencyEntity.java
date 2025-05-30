package com.peterchang.currency_api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY")
public class CurrencyEntity implements Serializable {

	private static final long serialVersionUID = 6315149916784352266L;

	/** 幣別代碼 */
	@Id
	@Column(name = "CURRENCYID")
	private String currencyId;

	/** 幣別名稱 */
	@Column(name = "CURRENCYNAME")
	private String currencyName;

	/** 幣別英文名稱 */
	@Column(name = "CURRENCYENAME")
	private String currencyEName;

	/** 幣別符號 */
	@Column(name = "SYMBOL")
	private String symbol;

	/** 匯率 */
	@Column(name = "RATE")
	private BigDecimal rate;

	/** 完整匯率 */
	@Column(name = "RATEFLOAT")
	private BigDecimal rateFloat;
	
	/** 建立時間 */
	@Column(name = "CREATETIME")
	private Date createTime;

	/** 更新時間 */
	@Column(name = "UPDATETIME")
	private Date updateTime;

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyEName() {
		return currencyEName;
	}

	public void setCurrencyEName(String currencyEName) {
		this.currencyEName = currencyEName;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getRateFloat() {
		return rateFloat;
	}

	public void setRateFloat(BigDecimal rateFloat) {
		this.rateFloat = rateFloat;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
