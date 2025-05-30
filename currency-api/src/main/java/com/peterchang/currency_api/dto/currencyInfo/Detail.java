package com.peterchang.currency_api.dto.currencyInfo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Detail implements Serializable {

	private static final long serialVersionUID = -6689711814751703180L;

	/** 幣別代碼 */
	private String currencyId;

	/** 幣別名稱 */
	private String currencyName;
	
	/** 幣別英文名稱 */
	private String currencyEName;

	/** 幣別符號 */
	private String symbol;
	
	/** 匯率 */
	private String rate;

	/** 完整匯率 */
	private String rateFloat;
	
	/** 更新時間 */
	private String updateTime;
	
	/** 建立時間 */
	private String createTime;

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getRateFloat() {
		return rateFloat;
	}

	public void setRateFloat(String rateFloat) {
		this.rateFloat = rateFloat;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
