package com.peterchang.currency_api.ext.coindesk.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterchang.currency_api.common.exception.APIException;
import com.peterchang.currency_api.ext.coindesk.dto.CoinDeskRes;

@SpringBootTest
@ActiveProfiles("test")
public class CoinDeskClientTest {
	
	@Autowired
	private WebClient webClient;

	@Value("${ext-api.coindesk.url}")
	private String coinDeskUrl;
	
	/**
	 * CoinDesk 連線測試，取回資料
	 * 
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws APIException 
	 */
	@Test
    public void testCoinDeskToEntity() throws JsonMappingException, JsonProcessingException, APIException {
		
		System.out.println("=== CoinDesk API 取回資料並轉成 DTO 流程開始 ===");
		System.out.println("=== STEP 1. CoinDesk API 取回資料 === start");
	    String json = webClient.get()
	            .uri(coinDeskUrl)
	            .retrieve()
	            .bodyToMono(String.class)
	            .block();

	    // 驗證是否取得下行
	    assertThat(json).isNotNull();
	    System.out.println("取回資料：" + json);
	    System.out.println("=== STEP 1. CoinDesk API 取回資料 === end");
	    
	    ObjectMapper mapper = new ObjectMapper();

	    System.out.println("=== STEP 2. CoinDesk API 轉成 DTO === start");
	    CoinDeskRes res = mapper.readValue(json, CoinDeskRes.class);

	    // 驗證取得資料是否取有值
	    assertThat(res.getBpi()).isNotEmpty();
	    
	    // 使用 pretty print 格式化 Map 輸出
	    String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res.getBpi());
	    
	    System.out.println("轉換成功，內容：" + prettyJson);
	    System.out.println("=== STEP 2. CoinDesk API 轉成 DTO === end");
	    System.out.println("=== CoinDesk API 取回資料並轉成 DTO 流程結束 ===");
    }
}
