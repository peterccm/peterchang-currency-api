package com.peterchang.currency_api.common.abs;

import java.io.Serializable;

public abstract class AbstractResBody implements Serializable {

	private static final long serialVersionUID = 7451611897248897668L;
	
	/** 查詢時間 */
	private String queryTime;

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
}
