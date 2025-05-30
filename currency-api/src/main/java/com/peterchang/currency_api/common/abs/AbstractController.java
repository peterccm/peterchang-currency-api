package com.peterchang.currency_api.common.abs;

import org.springframework.http.ResponseEntity;

public abstract class AbstractController<S extends AbstractReqBody, R extends AbstractResBody> {
	
	/**
	 * 頁面初始
	 */
	protected ResponseEntity<R> doInitial() throws Exception {
		return null;
	}
	
	/**
	 * 查詢
	 */
	protected ResponseEntity<R> doQuery(S reqBody) throws Exception {
		return null;
	}
	
	/**
	 * 檢視
	 */
	protected ResponseEntity<R> doQueryView(String id) throws Exception {
		return null;
	}
	
	/**
	 * 新增
	 */
	protected ResponseEntity<R> doCreate(S reqBody) throws Exception {
		return null;
	}
	
	/**
	 * 修改
	 */
	protected ResponseEntity<R> doUpdate(String id, S reqBody) throws Exception {
		return null;
	}
	
	/**
	 * 新增
	 */
	protected ResponseEntity<R> doDelete(String id) throws Exception {
		return null;
	}
	
	/**
	 * 取得 ResponseEntity 物件
	 */
	protected ResponseEntity<R> doResponse(R resBody) {	
		return ResponseEntity.ok().body(resBody);
	}
}