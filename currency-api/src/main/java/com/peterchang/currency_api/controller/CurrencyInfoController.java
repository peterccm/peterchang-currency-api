package com.peterchang.currency_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peterchang.currency_api.common.abs.AbstractController;
import com.peterchang.currency_api.common.exception.APIException;
import com.peterchang.currency_api.dto.currencyInfo.CurrencyInfoReqBody;
import com.peterchang.currency_api.dto.currencyInfo.CurrencyInfoResBody;
import com.peterchang.currency_api.service.CurrencyInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Currency Info", description = "幣別管理")
@RestController
@RequestMapping("/currencyInfo")
public class CurrencyInfoController extends AbstractController<CurrencyInfoReqBody, CurrencyInfoResBody> {

	@Autowired
	private CurrencyInfoService service;

	/**
	 * 畫面初始
	 */
	@Operation(description = "幣別管理-畫面初始頁")
	@GetMapping("/init")
	@Override
	protected ResponseEntity<CurrencyInfoResBody> doInitial() throws APIException {

		CurrencyInfoResBody resBody = this.service.doInitial();

		return super.doResponse(resBody);
	}

	/**
	 * 查詢結果頁
	 * 
	 * @param String currencyId
	 */
	@Operation(description = "幣別管理-查詢結果頁")
	@GetMapping("/query")
	@Override
	protected ResponseEntity<CurrencyInfoResBody> doQuery(
			@Parameter(example = "", name = "currencyId", required = true, description = "查詢方式 \"ALL\":全部 或 幣別:(例)\"TWD\"")
			@ModelAttribute CurrencyInfoReqBody reqBody) throws APIException {

		CurrencyInfoResBody resBody = this.service.doQuery(reqBody);

		return super.doResponse(resBody);
	}

	/**
	 * 單筆檢視頁
	 * 
	 * @param String currencyId
	 */
	@Operation(description = "幣別管理-單筆檢視頁")
	@GetMapping("/query/{currencyId}")
	@Override
	protected ResponseEntity<CurrencyInfoResBody> doQueryView(
			@Parameter(example = "TWD", name = "currencyId", required = true, description = "幣別:(例)TWD")
			@PathVariable String currencyId) throws APIException {

		CurrencyInfoResBody resBody = this.service.doQueryView(currencyId);

		return super.doResponse(resBody);
	}

	/**
	 * 單筆新增頁
	 * 
	 * @param CurrencyInfoReqBody reqBody
	 */
	@Operation(description = "幣別管理-單筆新增頁")
	@PostMapping("/create")
	@Override
	protected ResponseEntity<CurrencyInfoResBody> doCreate(
			@Parameter(example = "TWD", name = "currencyId", required = true, description = "幣別:(例)TWD")
			@RequestBody CurrencyInfoReqBody reqBody) throws APIException {

		CurrencyInfoResBody resBody = this.service.doCreate(reqBody);

		return super.doResponse(resBody);
	}

	/**
	 * 單筆修改頁
	 * 
	 * @param String currencyId
	 * @param CurrencyInfoReqBody reqBody
	 */
	@Operation(description = "幣別管理-單筆修改頁")
	@PutMapping("/query/{currencyId}/update")
	@Override
	protected ResponseEntity<CurrencyInfoResBody> doUpdate(
			@Parameter(example = "TWD", name = "currencyId", required = true, description = "幣別:(例)TWD")
			@PathVariable String currencyId, 
			@RequestBody CurrencyInfoReqBody reqBody) throws APIException {

		CurrencyInfoResBody resBody = this.service.doUpdate(currencyId, reqBody);

		return super.doResponse(resBody);
	}

	/**
	 * 單筆刪除
	 * 
	 * @param String currencyId
	 */
	@Operation(description = "幣別管理-單筆刪除")
	@DeleteMapping("/query/{currencyId}/delete")
	@Override
	protected ResponseEntity<CurrencyInfoResBody> doDelete(
			@Parameter(example = "TWD", name = "currencyId", required = true, description = "幣別:(例)TWD")
			@PathVariable String currencyId) throws APIException {

		CurrencyInfoResBody resBody = this.service.doDelete(currencyId);

		return super.doResponse(resBody);
	}

	/**
	 * 刷新 Currency 資料
	 * 呼叫 CoinDesk API 並更新 DB
	 * 
	 * @param String renewAction
	 */
	@Operation(description = "幣別管理-刷新幣別資料(呼叫 CoinDesk API 並更新 DB)")
	@GetMapping("/renewData")
	protected ResponseEntity<CurrencyInfoResBody> doRenewCurrencyData(
			@Parameter(example = "1", name = "renewAction", required = true, description = "資料刷新模式 1:更新(預設) 2:全部移除並刷新")
			@ModelAttribute CurrencyInfoReqBody reqBody) throws APIException {

		CurrencyInfoResBody resBody = this.service.doRenewCurrencyData(reqBody);

		return super.doResponse(resBody);
	}
}
