package com.peterchang.currency_api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peterchang.currency_api.common.abs.AbstractService;
import com.peterchang.currency_api.common.component.Selector;
import com.peterchang.currency_api.common.enums.CurrencyId;
import com.peterchang.currency_api.common.enums.MsgCode;
import com.peterchang.currency_api.common.exception.APIException;
import com.peterchang.currency_api.common.utils.DateUtils;
import com.peterchang.currency_api.common.utils.NumberUtils;
import com.peterchang.currency_api.dao.CurrencyDao;
import com.peterchang.currency_api.dto.currencyInfo.CurrencyInfoReqBody;
import com.peterchang.currency_api.dto.currencyInfo.CurrencyInfoResBody;
import com.peterchang.currency_api.dto.currencyInfo.Detail;
import com.peterchang.currency_api.entity.CurrencyEntity;
import com.peterchang.currency_api.ext.coindesk.client.CoinDeskClient;
import com.peterchang.currency_api.ext.coindesk.dto.CoinDeskRes;
import com.peterchang.currency_api.ext.coindesk.dto.CurrencyDetail;

@Service
public class CurrencyInfoService extends AbstractService {

	@Autowired
	private CurrencyDao dao;

	@Autowired
	private CoinDeskClient coinDeskClient;

	/**
	 * 畫面初始
	 */
	public CurrencyInfoResBody doInitial() throws APIException {

		CurrencyInfoResBody resBody = new CurrencyInfoResBody();

		// 幣別-下拉選單
		List<Selector> currencyList = this.getCurrencyList();

		// 資料刷新模式選項
		List<Selector> renewActions = this.getRenewActions();

		resBody.setCurrencyList(currencyList);
		resBody.setRenewActions(renewActions);

		return resBody;
	}

	/**
	 * 查詢結果頁
	 * 
	 * @param String currencyId
	 */
	public CurrencyInfoResBody doQuery(CurrencyInfoReqBody reqBody) throws APIException {

		String reqCurrencyId = reqBody.getCurrencyId();

		CurrencyInfoResBody resBody = new CurrencyInfoResBody();

		// 查詢 DB
		List<CurrencyEntity> entities = this.dao.findByCurrenyIdOrAll(reqCurrencyId);

		// 組合查詢結果
		List<Detail> details = this.fetchDataList(entities);

		resBody.setQueryTime(DateUtils.getISODateTimeStr(new Date()));
		resBody.setDetails(details);

		return resBody;
	}

	/**
	 * 單筆檢視頁
	 * 
	 * @param String currencyId
	 */
	public CurrencyInfoResBody doQueryView(String currencyId) throws APIException {

		CurrencyInfoResBody resBody = new CurrencyInfoResBody();

		// 查詢 DB
		CurrencyEntity entity = this.dao.findById(currencyId).orElse(null);

		// 驗證
		super.validateDataFound(entity != null);

		// 組合查詢結果
		Detail detail = this.fetchData(entity);

		resBody.setDetail(detail);

		return resBody;
	}

	/**
	 * 單筆新增頁
	 * 
	 * @param CurrencyInfoReqBody reqBody
	 */
	public CurrencyInfoResBody doCreate(CurrencyInfoReqBody reqBody) throws APIException {

		String reqCurrencyId = reqBody.getCurrencyId();
		String reqCurrencyName = reqBody.getCurrencyName();
		String reqCurrencyEName = reqBody.getCurrencyEName();
		String reqSymbol = reqBody.getSymbol();
		String reqRate = reqBody.getRate();
		String reqRateFloat = reqBody.getRateFloat();

		// 驗證
		super.validateColumnBlank(reqCurrencyId);
		super.validateDataExists(dao.existsById(reqCurrencyId));

		CurrencyInfoResBody resBody = new CurrencyInfoResBody();

		CurrencyEntity entity = new CurrencyEntity();
		entity.setCurrencyId(reqCurrencyId.toUpperCase());
		entity.setCurrencyName(StringUtils.trimToEmpty(reqCurrencyName));
		entity.setCurrencyEName(StringUtils.trimToEmpty(reqCurrencyEName));
		entity.setSymbol(StringUtils.trimToEmpty(StringEscapeUtils.unescapeHtml4(reqSymbol)));
		entity.setRate(NumberUtils.parseAmount(reqRate));
		entity.setRateFloat(NumberUtils.parseAmount(reqRateFloat));
		entity.setCreateTime(DateUtils.now());
		entity.setUpdateTime(DateUtils.now());

		// 新增資料並組合結果
		try {

			entity = this.dao.save(entity);
		} catch (Exception e) {

			throw new APIException(MsgCode.DATABASE_EXCEPTION, e);
		}

		Detail detail = this.fetchData(entity);

		resBody.setDetail(detail);

		return resBody;
	}

	/**
	 * 單筆修改頁
	 * 
	 * @param String currencyId
	 * @param CurrencyInfoReqBody reqBody
	 */
	public CurrencyInfoResBody doUpdate(String currencyId, CurrencyInfoReqBody reqBody) throws APIException {

		String reqCurrencyName = reqBody.getCurrencyName();
		String reqCurrencyEName = reqBody.getCurrencyEName();
		String reqSymbol = reqBody.getSymbol();
		String reqRate = reqBody.getRate();
		String reqRateFloat = reqBody.getRateFloat();

		CurrencyInfoResBody resBody = new CurrencyInfoResBody();

		// 查詢 DB
		CurrencyEntity entity = this.dao.findById(currencyId).orElse(null);

		// 驗證
		super.validateDataFound(entity != null);

		entity.setCurrencyName(StringUtils.trimToEmpty(reqCurrencyName));
		entity.setCurrencyEName(StringUtils.trimToEmpty(reqCurrencyEName));
		entity.setSymbol(StringUtils.trimToEmpty(StringEscapeUtils.unescapeHtml4(reqSymbol)));
		entity.setRate(NumberUtils.parseAmount(reqRate));
		entity.setRateFloat(NumberUtils.parseAmount(reqRateFloat));
		entity.setUpdateTime(DateUtils.now());

		try {

			entity = this.dao.save(entity);	
		} catch (Exception e) {

			throw new APIException(MsgCode.DATABASE_EXCEPTION, e);
		}

		// 組合結果
		Detail detail = this.fetchData(entity);

		resBody.setDetail(detail);

		return resBody;
	}

	/**
	 * 單筆刪除
	 * 
	 * @param String currencyId
	 */
	public CurrencyInfoResBody doDelete(String currencyId) throws APIException {

		CurrencyInfoResBody resBody = new CurrencyInfoResBody();

		// 驗證
		super.validateDataFound(this.dao.existsById(currencyId));

		// 移除資料並組合結果
		try {
			
			this.dao.deleteById(currencyId);
		} catch (Exception e) {

			throw new APIException(MsgCode.DATABASE_EXCEPTION, e);
		}

		// 查詢 DB
		List<CurrencyEntity> entities = this.dao.findAll();

		// 組合查詢結果
		List<Detail> details = this.fetchDataList(entities);

		resBody.setQueryTime(DateUtils.getISODateTimeStr(new Date()));
		resBody.setDetails(details);

		return resBody;
	}

	/**
	 * 刷新 Currency 資料
	 * 呼叫 CoinDesk API 並更新 DB
	 * 
	 * @param String renewAction
	 */
	public CurrencyInfoResBody doRenewCurrencyData(CurrencyInfoReqBody reqBody) throws APIException {

		String reqRenewAction = reqBody.getRenewAction();

		CurrencyInfoResBody resBody = new CurrencyInfoResBody();

		// 移除 DB 所有資料
		if (StringUtils.equals("2", reqRenewAction)) {
			this.dao.deleteAll();
		}

		// 取得 CoinDesk 資料並轉換為可控物件
		CoinDeskRes coinDeskRes = null;
		try {

			coinDeskRes = this.coinDeskClient.getData();
		} catch (Exception e) {

			throw new APIException(MsgCode.QUERY_FAIL, e);
		}

		// API 回應結果批次新增至DB
		this.saveCoinDeskResToDB(coinDeskRes);

		// 查詢 DB
		List<CurrencyEntity> entities = this.dao.findAll();

		// 組合查詢結果
		List<Detail> details = this.fetchDataList(entities);

		resBody.setQueryTime(DateUtils.getISODateTimeStr(new Date()));
		resBody.setDetails(details);

		return resBody;
	}

	// ========= Private Methods =========
	/**
	 * 取得「幣別」下拉選單
	 * 
	 * @return List<Selector>
	 */
	private List<Selector> getCurrencyList() {

		List<Selector> currencyList = new ArrayList<Selector>();

		for (CurrencyId currency : CurrencyId.getCurrencyList()) {

			currencyList.add(new Selector(currency.getName(), currency.getId()));
		}

		currencyList.add(0, new Selector("全部", "ALL"));

		return currencyList;
	}

	/**
	 * 取得「幣別更新模式」選項
	 * 
	 * @return List<Selector>
	 */
	private List<Selector> getRenewActions() {

		List<Selector> currencyList = new ArrayList<Selector>();

		currencyList.add(new Selector("更新", "1"));
		currencyList.add(new Selector("全部移除並刷新", "2"));

		return currencyList;
	}

	/**
	 * 組合查詢結果明細資訊
	 * 
	 * @param CurrencyEntity entity
	 */
	private Detail fetchData(CurrencyEntity entity) {

		Detail detail = new Detail();

		detail.setCurrencyId(entity.getCurrencyId());
		detail.setCurrencyName(entity.getCurrencyName());
		detail.setCurrencyEName(entity.getCurrencyEName());
		detail.setSymbol(entity.getSymbol());
		detail.setRate(String.valueOf(entity.getRate()));
		detail.setRateFloat(String.valueOf(entity.getRateFloat()));
		detail.setUpdateTime(DateUtils.getISODateTimeStr(entity.getUpdateTime()));
		detail.setCreateTime(DateUtils.getISODateTimeStr(entity.getCreateTime()));

		return detail;
	}

	/**
	 * 組合查詢結果明細列表
	 * 
	 * @param CurrencyEntity entity
	 */
	private List<Detail> fetchDataList(List<CurrencyEntity> entities) {

		List<Detail> details = new ArrayList<Detail>();

		if (entities != null && entities.size() > 0) {

			for (CurrencyEntity entity : entities) {

				Detail detail = this.fetchData(entity);

				details.add(detail);
			}
		}

		return details;
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
			entity.setCurrencyId(entry.getKey().toUpperCase());
			entity.setCurrencyName(CurrencyId.getNameById(entry.getKey()));
			entity.setCurrencyEName(StringUtils.trimToEmpty(currencyDetail.getDescription()));
			entity.setSymbol(StringUtils.trimToEmpty(StringEscapeUtils.unescapeHtml4(currencyDetail.getSymbol())));
			entity.setRate(NumberUtils.parseAmount(currencyDetail.getRate()));
			entity.setRateFloat(NumberUtils.parseAmount(currencyDetail.getRateFloat()));
			entity.setUpdateTime(new Date());
			entity.setCreateTime(DateUtils.getDateFromISO(coinDeskRes.getTime().getUpdatedISO()));

			entities.add(entity);
		}

		return entities;
	}

	/**
	 * CoinDesk API 回應結果批次新增至DB
	 * 
	 * @param CoinDeskRes coinDeskRes
	 * @throws Exception
	 */
	@Transactional
	private void saveCoinDeskResToDB(CoinDeskRes coinDeskRes) throws APIException {

		List<CurrencyEntity> entities;

		entities = this.convertToCurrencyEntities(coinDeskRes);

		try {

			this.dao.saveAll(entities);
		} catch (Exception e) {

			throw new APIException(MsgCode.DATABASE_EXCEPTION, e);
		}
	}
}
