package com.peterchang.currency_api.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum CurrencyId {
	
	UNKNOWN("UNKNOWN", ""),
	USD("USD", "美元"),
    EUR("EUR", "歐元"),
    GBP("GBP", "英鎊"),
    JPY("JPY", "日圓"),
    CNY("CNY", "人民幣"),
    TWD("TWD", "新台幣"),
    HKD("HKD", "港幣"),
    KRW("KRW", "韓圓"),
    AUD("AUD", "澳幣"),
    CAD("CAD", "加幣"),
    CHF("CHF", "瑞士法郎"),
    SGD("SGD", "新加坡幣"),
    THB("THB", "泰銖"),
    NZD("NZD", "紐西蘭幣"),
    ZAR("ZAR", "南非幣"),
    SEK("SEK", "瑞典克朗"),
    NOK("NOK", "挪威克朗"),
    DKK("DKK", "丹麥克朗"),
    RUB("RUB", "俄羅斯盧布"),
    PHP("PHP", "菲律賓披索"),
    VND("VND", "越南盾"),
    INR("INR", "印度盧比"),
    IDR("IDR", "印尼盾"),
    MYR("MYR", "馬來幣"),
    MXN("MXN", "墨西哥披索"),
    BRL("BRL", "巴西雷亞爾"),
    TRY("TRY", "土耳其里拉");

    private String id;
    private String name;

    CurrencyId(String id, String name) {
        this.id = id;
        this.name = name;
    }

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	/**
	 * 根據「幣別代碼」取得「對應名稱」
	 * 
	 * @param String currencyId
	 * @return String
	 */
	public static String getNameById(String currencyId) {
		
		if (StringUtils.isBlank(currencyId)) {
			return UNKNOWN.name();
		}
		
		for (CurrencyId currency : values()) {
			if (StringUtils.equalsIgnoreCase(currency.id, currencyId)) {
				return currency.name;
			}
		}
		
		return UNKNOWN.name();	
	}
	
	/**
	 * 取得「幣別列表」，排除 UNKNOWN
	 * 
	 * @return List<CurrencyId>
	 */
	public static List<CurrencyId> getCurrencyList() {
		
		List<CurrencyId> currencyList = new ArrayList<CurrencyId>();
		
		for (CurrencyId currency : values()) {
			if (currency != UNKNOWN) {
				currencyList.add(currency);
			}
		}
		
		return currencyList;
	}
}
