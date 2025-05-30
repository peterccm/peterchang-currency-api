package com.peterchang.currency_api.common.enums;

import com.peterchang.currency_api.common.interfaces.IMsgCode;

public enum MsgCode implements IMsgCode{
	
	SUCCESS("0000", "執行成功"),
	DATABASE_EXCEPTION("1001", "資料庫發生異常"),
	DATA_NOT_EXISTS("9001", "資料不存在"),
	DATA_IS_EXISTS("9002", "資料已存在"),
	COLUMN_CANNOT_NULL("9100", "必填欄位不可為空"),
	CURRENCY_FORMAT_ERROR("9101", "金額格式錯誤"),
	DATE_FORMAT_ERROR("9102", "日期格式錯誤"),
	QUERY_FAIL("9500", "請求失敗"),
	FAIL("9999", "執行失敗");
	
	private String code;
	private String msg;
	
	MsgCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	@Override
	public String getName() {
		return super.name();
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMsg() {
		return this.msg;
	}
}
