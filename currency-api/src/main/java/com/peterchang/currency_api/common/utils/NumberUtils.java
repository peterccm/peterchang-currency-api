package com.peterchang.currency_api.common.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import com.peterchang.currency_api.common.enums.MsgCode;
import com.peterchang.currency_api.common.exception.APIException;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils{

	/**
	 * 金額字串轉為 java.math.BigDecimal
	 * 
	 * @param String amountStr
	 * @return BigDecimal
	 * @throws Exception 
	 */
	public static BigDecimal parseAmount(String amountStr) throws APIException {

		BigDecimal amount = null;

		try {

			NumberFormat format = NumberFormat.getNumberInstance(Locale.US);
			Number number = format.parse(amountStr);

			amount = new BigDecimal(number.toString());

		} catch (Exception e) {
			
			throw new APIException(MsgCode.CURRENCY_FORMAT_ERROR, e);
		}

		return amount;
	}
}
