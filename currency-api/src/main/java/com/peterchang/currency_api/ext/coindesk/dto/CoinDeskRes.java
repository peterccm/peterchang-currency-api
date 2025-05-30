package com.peterchang.currency_api.ext.coindesk.dto;

import java.io.Serializable;
import java.util.Map;

public class CoinDeskRes implements Serializable {
	
	private static final long serialVersionUID = -5507002745415580392L;

	/** 更新時間 */
	private Time time;
	
	/** 聲明 */
	private String disclaimer;
	
	/** 類別 */
	private String chartName;
	
	/** 幣別列表 */
	Map<String, CurrencyDetail> bpi;

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public Map<String, CurrencyDetail> getBpi() {
		return bpi;
	}

	public void setBpi(Map<String, CurrencyDetail> bpi) {
		this.bpi = bpi;
	}
}
