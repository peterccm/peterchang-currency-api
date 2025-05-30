package com.peterchang.currency_api.ext.coindesk.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyDetail implements Serializable {

	private static final long serialVersionUID = -3089845915982892997L;

	/** 幣別 */
	private String code;

	/** 貨幣符號 */
	private String symbol;

	/** 匯率 */
	private String rate;

	/** 幣別說明 */
	private String description;

	/** 完整匯率 */
	@JsonProperty("rate_float")
	private String rateFloat;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRateFloat() {
		return rateFloat;
	}

	public void setRateFloat(String rateFloat) {
		this.rateFloat = rateFloat;
	}
}
