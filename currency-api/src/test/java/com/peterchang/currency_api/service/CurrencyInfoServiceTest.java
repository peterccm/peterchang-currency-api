package com.peterchang.currency_api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterchang.currency_api.common.enums.CurrencyId;
import com.peterchang.currency_api.common.exception.APIException;
import com.peterchang.currency_api.common.utils.DateUtils;
import com.peterchang.currency_api.common.utils.NumberUtils;
import com.peterchang.currency_api.dao.CurrencyDao;
import com.peterchang.currency_api.entity.CurrencyEntity;
import com.peterchang.currency_api.ext.coindesk.dto.CoinDeskRes;
import com.peterchang.currency_api.ext.coindesk.dto.CurrencyDetail;
import com.peterchang.currency_api.ext.coindesk.dto.Time;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CurrencyInfoServiceTest {
	
	@Autowired
	private CurrencyDao dao;
	
	@Test
	@Order(0)
	/**
	 * CoinDesk API 資料轉換成 CurrencyInfo Table
	 */
    public void testConvertToCurrencyEntities() throws Exception {
		
		System.out.println("=== CoinDesk API 資料轉換成 CurrencyInfo Table 欄位流程開始 ===");
		
		System.out.println("=== STEP 1. 準備 CoinDeskRes 測試資料 === start");
		CoinDeskRes coinDeskRes = this.createTestData();
        
        ObjectMapper mapper = new ObjectMapper();
        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(coinDeskRes);
        System.out.println("轉換完成，結果：" + prettyJson);
        System.out.println("=== STEP 1. 準備 CoinDeskRes 測試資料 === end");
        
        System.out.println("=== STEP 2. CoinDeskRes 測試資料轉換成 CurrencyEntity 並符合欄位型態 === start");
        List<CurrencyEntity> results = this.convertToCurrencyEntities(coinDeskRes);

        // Assert
        assertThat(results).hasSize(1);
        System.out.println("轉換成功");
        
        CurrencyEntity usd = results.get(0);
        System.out.println("檢查CURRENCYID");
        assertThat(usd.getCurrencyId()).isEqualTo(CurrencyId.USD.getId());
        System.out.println("檢查CURRENCYNAME");
        assertThat(usd.getCurrencyName()).isEqualTo(CurrencyId.USD.getName());
        System.out.println("檢查CURRENCYENAME");
        assertThat(usd.getCurrencyEName()).isEqualTo("US Dollar");
        System.out.println("檢查幣別符號是否轉換正確");
        assertThat(usd.getSymbol()).isEqualTo("$");
        System.out.println("檢查匯率是否轉換正確");
        assertThat(usd.getRate()).isEqualByComparingTo("1030000.011");
        System.out.println("檢查完整匯率是否轉換正確");
        assertThat(usd.getRateFloat()).isEqualByComparingTo("1030000.01111");
        System.out.println("檢查是否記錄更新時間");
        assertThat(usd.getUpdateTime()).isNotNull();
        System.out.println("檢查UpdatedISO是否轉換正確");
        assertThat(DateUtils.getISODateTimeStr(usd.getCreateTime())).isEqualTo("2023/05/29 20:00:00");
        
        System.out.println("=== STEP 2. CoinDeskRes 測試資料轉換成 CurrencyEntity 並符合欄位型態 === end");
        System.out.println("=== CoinDesk API 取回資料轉換成 CurrencyInfo Table 欄位流程結束 ===");
    }
	
	@Test
	@Order(1)
	/**
	 * CoinDesk API 資料寫入 CurrencyInfo Table
	 */
    public void testWriteToDB() throws Exception {
		
		System.out.println("=== CoinDesk API 資料寫入 CurrencyInfo Table 測試流程開始 ===");
		
		System.out.println("=== STEP 1. 準備 CoinDeskRes 測試資料 === start");
		CoinDeskRes coinDeskRes = this.createTestData();
        
        ObjectMapper mapper = new ObjectMapper();
        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(coinDeskRes);
        System.out.println("轉換完成，結果：" + prettyJson);
        System.out.println("=== STEP 1. 準備 CoinDeskRes 測試資料 === end");
        
        System.out.println("=== STEP 2. CoinDeskRes 測試資料轉換成 CurrencyEntity 並符合欄位型態 === start");
        List<CurrencyEntity> results = this.convertToCurrencyEntities(coinDeskRes);
        System.out.println("=== STEP 2. CoinDeskRes 測試資料轉換成 CurrencyEntity 並符合欄位型態 === end");
        
        System.out.println("=== STEP 3. 資料寫入 DB === start");
        this.dao.saveAll(results);
        System.out.println("=== STEP 3. 資料寫入 DB === end");
		
        System.out.println("=== STEP 4. 查詢 DB 並驗證 === start");
        CurrencyEntity entity = this.dao.findById("USD").orElse(null);

        // 驗證資料不為 null
        assertThat(entity).isNotNull();
        System.out.println("查詢成功");
        
        System.out.println("檢查KEY值是否轉換正確");
        assertThat(entity.getCurrencyId()).isEqualTo(CurrencyId.USD.getId());
        System.out.println("檢查CURRENCYNAME");
        assertThat(entity.getCurrencyName()).isEqualTo(CurrencyId.USD.getName());
        System.out.println("檢查匯率是否轉換正確");
        assertThat(entity.getRate()).isEqualByComparingTo("1030000.011");
        System.out.println("檢查是否記錄更新時間");
        assertThat(entity.getUpdateTime()).isNotNull();
        System.out.println("檢查UpdatedISO是否轉換正確");
        assertThat(DateUtils.getISODateTimeStr(entity.getCreateTime())).isEqualTo("2023/05/29 20:00:00");
        
        prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
        System.out.println("查詢結果：" + prettyJson);
        System.out.println("=== STEP 4. 查詢 DB 並驗證 === end");
        System.out.println("=== CoinDesk API 資料寫入 CurrencyInfo Table 測試流程結束 ===");
	}
	
	/**
	 * 將 CoinDesk 內容轉換成 CurrencyEntity 列表
	 * 
	 * @param CoinDeskRes coinDeskRes
	 * @return List<CurrencyEntity>
	 * @throws Exception 
	 */
	private List<CurrencyEntity> convertToCurrencyEntities(CoinDeskRes coinDeskRes) throws APIException {

		List<CurrencyEntity> entities = new ArrayList<CurrencyEntity>();

		for (Entry<String, CurrencyDetail> entry : coinDeskRes.getBpi().entrySet()) {

			CurrencyDetail currencyDetail = entry.getValue();

			CurrencyEntity entity = new CurrencyEntity();
			entity.setCurrencyId(entry.getKey());
			entity.setCurrencyName(CurrencyId.getNameById(entry.getKey()));
			entity.setCurrencyEName(currencyDetail.getDescription());
			entity.setSymbol(StringEscapeUtils.unescapeHtml4(currencyDetail.getSymbol()));
			entity.setRate(NumberUtils.parseAmount(currencyDetail.getRate()));
			entity.setRateFloat(NumberUtils.parseAmount(currencyDetail.getRateFloat()));
			entity.setUpdateTime(new Date());
			entity.setCreateTime(DateUtils.getDateFromISO(coinDeskRes.getTime().getUpdatedISO()));

			entities.add(entity);
		}

		return entities;
	}
	
	/**
	 * 建立 CoinDesk 測試資料
	 */
	private CoinDeskRes createTestData() {
		
		CoinDeskRes coinDeskRes = new CoinDeskRes();
		
        Map<String, CurrencyDetail> bpi = new HashMap<>();

        CurrencyDetail usdDetail = new CurrencyDetail();
        usdDetail.setDescription("US Dollar");
        usdDetail.setRate("1,030,000.011");
        usdDetail.setRateFloat("1030000.01111");
        usdDetail.setSymbol("&#36;");

        bpi.put("USD", usdDetail);
        
        coinDeskRes.setBpi(bpi);

        Time time = new Time();
        time.setUpdated("May 29, 2023 12:00:00 UTC");
        time.setUpdatedISO("2023-05-29T12:00:00+00:00");
        time.setUpdateduk("May 29, 2023 at 13:00 BST");
        
        coinDeskRes.setTime(time);
        
        return coinDeskRes;
	}
}
