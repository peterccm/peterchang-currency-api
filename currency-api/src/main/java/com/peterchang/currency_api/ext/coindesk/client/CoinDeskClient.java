package com.peterchang.currency_api.ext.coindesk.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.peterchang.currency_api.ext.coindesk.dto.CoinDeskRes;

@Component
public class CoinDeskClient {
	
	@Autowired
	private WebClient webClient;

	@Value("${ext-api.coindesk.url}")
	private String coinDeskUrl;
	
	/**
	 * 呼叫 CoinDesk API	取得資料並轉換為可控物件
	 * 
	 * @return CoinDeskRes
	 */
	public CoinDeskRes getData() {
		
		// 取得 CoinDesk 資料並轉換為可控物件
		CoinDeskRes res = this.webClient.get().uri(coinDeskUrl).retrieve().bodyToMono(CoinDeskRes.class).block();
		
		return res;
	}
}
