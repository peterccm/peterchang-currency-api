package com.peterchang.currency_api.common.exception;

import com.peterchang.currency_api.common.interfaces.IMsgCode;

public class APIException extends Exception {
	
	private static final long serialVersionUID = -5890608192917020791L;

	private String errorCode;
	
	private String errorMsg;
	
	public APIException(IMsgCode msgCode) {
		super(msgCode.getMsg());
		this.errorCode = msgCode.getCode();
		this.errorMsg = msgCode.getMsg();
	}

	public APIException(IMsgCode msgCode, Throwable arg) {
		super(msgCode.getMsg(), arg);		
		this.errorCode = msgCode.getCode();
		this.errorMsg = msgCode.getMsg();
	}

	public APIException(Throwable arg) {
		super(arg);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
