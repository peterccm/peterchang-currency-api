package com.peterchang.currency_api.common.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Selector {
	
	public Selector() {
		super();
	}
	
	public Selector(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	/** 選項名稱 */
	private String name;

	/** 選項值 */
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
