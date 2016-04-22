package com.ed.v1.util;

import android.text.TextUtils;

public class StringUtil {

	public static String getFormatPassword(String password) {
		if (TextUtils.isEmpty(password)) {
			return "";
		}
		return MD5.encode(password).toUpperCase();
	}

	public static boolean isEmptyOrNull(String str) {
		if (null == str || "".equals(str) || "null".equals(str)) {
			return true;
		}
		return false;
	}

	/*
	 * 补0凑成strLength位数字
	 */
	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// 左补0
				// sb.append(str).append("0");//右补0
				str = sb.toString();
				strLen = str.length();
			}
		}

		return str;
	}
}
