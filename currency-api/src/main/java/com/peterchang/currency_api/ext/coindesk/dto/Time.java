package com.peterchang.currency_api.ext.coindesk.dto;

import java.io.Serializable;

public class Time implements Serializable {

	private static final long serialVersionUID = 3096757328880270394L;

	/** UTC */
	private String updated;

	/** ISO */
	private String updatedISO;

	/** BST */
	private String updateduk;

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getUpdatedISO() {
		return updatedISO;
	}

	public void setUpdatedISO(String updatedISO) {
		this.updatedISO = updatedISO;
	}

	public String getUpdateduk() {
		return updateduk;
	}

	public void setUpdateduk(String updateduk) {
		this.updateduk = updateduk;
	}
}
