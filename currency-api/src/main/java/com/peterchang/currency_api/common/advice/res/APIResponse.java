package com.peterchang.currency_api.common.advice.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.peterchang.currency_api.common.enums.MsgCode;

@JsonInclude(Include.NON_NULL)
public class APIResponse<T> {
	
	private Date accessTime;
	private String rtnCode;
    private String rtnMsg;
    private T resBody;
    
    public APIResponse() {}

    public APIResponse(String rtnCode, String rtnMsg, T resBody) {
    	
    	this.accessTime = new Date();
        this.rtnCode = rtnCode;
        this.rtnMsg = rtnMsg;
        this.resBody = resBody;
    }
    
    public static <T> APIResponse<T> success(T resBody) {
        return new APIResponse<>(MsgCode.SUCCESS.getCode(), MsgCode.SUCCESS.getMsg(), resBody);
    }
    
    public static <T> APIResponse<T> fail(String code, String msg) {
        return new APIResponse<>(code, msg, null);
    }

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}

	public String getRtnMsg() {
		return rtnMsg;
	}

	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}

	public T getResBody() {
		return resBody;
	}

	public void setResBody(T resBody) {
		this.resBody = resBody;
	}
}
