package com.peterchang.currency_api.common.utils;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.peterchang.currency_api.common.enums.MsgCode;
import com.peterchang.currency_api.common.exception.APIException;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils{
	
	public static final String SIMPLE_ISO_DATE_STR = "yyyyMMdd";
	
	public static final String SIMPLE_ISO_DATE_TIME_STR = "yyyyMMddHHmmss";

	public static final String ISO_DATE_STR = "yyyy/MM/dd";

	public static final String ISO_DATE_TIME_STR = "yyyy/MM/dd HH:mm:ss";

	public static final String UTC_DATE_TIME_STR = "MMM d, yyyy HH:mm:ss z";

	public static final String BST_DATE_TIME_STR = "MMM d, yyyy 'at' HH:mm z";

	/**
	 * 取得當下系統時間
	 * 
	 * @return Date
	 */
	public static Date now() {

		return new Date();
	}
	
	/**
	 * 將 java.util.Date 轉換成無符號日期字串
	 * 
	 * @param dt
	 * @return String
	 */
	public static String getSimpleISODateStr(Date dt) {

		if (dt == null) {
			return "";
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SIMPLE_ISO_DATE_STR).withZone(ZoneId.systemDefault());

		return formatter.format(dt.toInstant());
	}
	
	/**
	 * 將 java.util.Date 轉換成無符號日期時間字串
	 * 
	 * @param dt
	 * @return String
	 */
	public static String getSimpleISODateTimeStr(Date dt) {

		if (dt == null) {
			return "";
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SIMPLE_ISO_DATE_TIME_STR).withZone(ZoneId.systemDefault());

		return formatter.format(dt.toInstant());
	}

	/**
	 * 將 java.util.Date 轉換成日期字串
	 * 
	 * @param dt
	 * @return String
	 */
	public static String getISODateStr(Date dt) {

		if (dt == null) {
			return "";
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ISO_DATE_STR).withZone(ZoneId.systemDefault());

		return formatter.format(dt.toInstant());
	}

	/**
	 * 將 java.util.Date 轉換成日期時間字串
	 * 
	 * @param dt
	 * @return String
	 */
	public static String getISODateTimeStr(Date dt) {

		if (dt == null) {
			return "";
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ISO_DATE_TIME_STR).withZone(ZoneId.systemDefault());

		return formatter.format(dt.toInstant());
	}

	/**
	 * 將 ISO 格式字串轉換成 java.util.Date
	 * 
	 * @param String dtStr
	 * @return String
	 * @throws Exception 
	 */
	public static Date getDateFromISO(String dtStr) throws APIException {

		Date dt = null;

		if (StringUtils.isBlank(dtStr)) {
			return null;
		}

		try {

			OffsetDateTime odt = OffsetDateTime.parse(dtStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

			dt = Date.from(odt.toInstant());
		} catch (Exception e) {

			throw new APIException(MsgCode.DATE_FORMAT_ERROR, e);
		}

		return dt;
	}

	/**
	 * 將 UTC 格式字串轉換成 java.util.Date
	 * 
	 * @param String dtStr
	 * @return String
	 * @throws Exception 
	 */
	public static Date getDateFromUTC(String dtStr) throws APIException {

		Date dt = null;

		if (StringUtils.isBlank(dtStr)) {
			return null;
		}

		try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(UTC_DATE_TIME_STR, Locale.ENGLISH);

			ZonedDateTime zonedDateTime = ZonedDateTime.parse(dtStr, formatter);

			dt =  Date.from(zonedDateTime.toInstant());
		} catch (Exception e) {

			throw new APIException(MsgCode.DATE_FORMAT_ERROR, e);
		}

		return dt;
	}

	/**
	 * 將 BST 格式字串轉換成 java.util.Date
	 * 
	 * @param String dtStr
	 * @return String
	 * @throws Exception 
	 */
	public static Date getDateFromBST(String dtStr) throws APIException {

		Date dt = null;

		if (StringUtils.isBlank(dtStr)) {
			return null;
		}

		try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BST_DATE_TIME_STR, Locale.ENGLISH);

			ZonedDateTime zonedDateTime = ZonedDateTime.parse(dtStr, formatter);
			
			dt = Date.from(zonedDateTime.toInstant());
		} catch (Exception e) {

			throw new APIException(MsgCode.DATE_FORMAT_ERROR, e);
		}

		return dt;
	}
}
