package com.peterchang.currency_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.peterchang.currency_api.utils.MockMvcUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyInfoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	/**
	 * 刷新 Currency 資料
	 * 呼叫 CoinDesk API 並更新 DB
	 * 
	 * @param String renewAction
	 */
	@Test
	public void testRenewData() throws Exception {
		
		System.out.println("=== 呼叫 CoinDesk API 並更新 DB 整合測試流程開始 ===");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/currencyInfo/renewData").param("renewAction", "1");

		ResultActions actions = mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());

        MockMvcUtils.printWithFormattedJson(actions);
        
        System.out.println("=== 呼叫 CoinDesk API 並更新 DB 整合測試流程結束 ===");
	}
}
