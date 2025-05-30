package com.peterchang.currency_api.common.abs;

import org.apache.commons.lang3.StringUtils;

import com.peterchang.currency_api.common.enums.MsgCode;
import com.peterchang.currency_api.common.exception.APIException;

public abstract class AbstractService {

	// ========= Validate Methods =========
	/**
	 * 檢核資料是否存在
	 * 
	 * @param boolean dataExist
	 * @throws APIException
	 */
	protected void validateDataFound(boolean dataExists) throws APIException {
		
		if (!dataExists) {
			throw new APIException(MsgCode.DATA_NOT_EXISTS);
		}
	}
	
	/**
	 * 檢核資料是否已存在
	 * 
	 * @param boolean dataExist
	 * @throws APIException
	 */
	protected void validateDataExists(boolean dataExists) throws APIException {
		
		if (dataExists) {
			throw new APIException(MsgCode.DATA_IS_EXISTS);
		}
		
	}
	
	/**
	 * 檢核欄位是否空白或 null
	 * 
	 * @param boolean dataExist
	 * @throws APIException
	 */
	protected void validateColumnBlank(String value) throws APIException {
		
		if (StringUtils.isBlank(value)) {
			throw new APIException(MsgCode.COLUMN_CANNOT_NULL);
		}
	}
}
