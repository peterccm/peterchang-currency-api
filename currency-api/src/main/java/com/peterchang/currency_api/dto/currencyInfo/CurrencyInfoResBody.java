package com.peterchang.currency_api.dto.currencyInfo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.peterchang.currency_api.common.abs.AbstractResBody;
import com.peterchang.currency_api.common.component.Selector;

@JsonInclude(Include.NON_NULL)
public class CurrencyInfoResBody extends AbstractResBody {

	private static final long serialVersionUID = -4713885955732559246L;
	
	/** 單筆明細 */
	private Detail detail;
	
	/** 明細清單 */
	private List<Detail> details;
	
	/** 幣別-下拉選單 */
	private List<Selector> currencyList;
	
	/** 資料刷新模式選項 */
	private List<Selector> renewActions;

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

	public List<Selector> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Selector> currencyList) {
		this.currencyList = currencyList;
	}

	public List<Selector> getRenewActions() {
		return renewActions;
	}

	public void setRenewActions(List<Selector> renewActions) {
		this.renewActions = renewActions;
	}
}
