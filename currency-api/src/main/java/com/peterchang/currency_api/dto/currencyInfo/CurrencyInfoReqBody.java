package com.peterchang.currency_api.dto.currencyInfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.peterchang.currency_api.common.abs.AbstractReqBody;

@JsonInclude(Include.NON_NULL)
public class CurrencyInfoReqBody extends AbstractReqBody {

	private static final long serialVersionUID = -1622360634839256918L;

	/** 幣別代碼 */
	private String currencyId;
	
	/** 幣別名稱 */
	private String currencyName;

	/** 幣別英文名稱 */
	private String currencyEName;

	/** 幣別符號 */
	private String symbol;

	/** 匯率 */
	private String rate;

	/** 完整匯率 */
	private String rateFloat;
	
	/** 資料刷新方式 1:更新(預設) 2：全部刷新 */
	private String renewAction;

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getRenewAction() {
		return renewAction;
	}

	public void setRenewAction(String renewAction) {
		this.renewAction = renewAction;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyEName() {
		return currencyEName;
	}

	public void setCurrencyEName(String currencyEName) {
		this.currencyEName = currencyEName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getRateFloat() {
		return rateFloat;
	}

	public void setRateFloat(String rateFloat) {
		this.rateFloat = rateFloat;
	}
}
