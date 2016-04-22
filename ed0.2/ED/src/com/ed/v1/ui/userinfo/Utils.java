package com.ed.v1.ui.userinfo;

public class Utils {
	static String chageMethodToString(int type) {
		String method = null;
		if (type == 1) {
			method = "话费充值";
		} else if (type == 2) {
			method = "银行卡";
		} else if (type == 3) {
			method = "支付宝";
		}
		return method;

	}

	public static String getForthNum(String name) {
		String username = null;
		if (name.length() > 4) {
			username = name.substring(name.length() - 4, name.length());

		}
		return username;

	}

	public static String tranToString(String[] authenList) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < authenList.length; i++) {
			stringBuffer.append(authenList[i]);
			if (i != authenList.length - 1) {
				stringBuffer.append("/");

			}
			System.out.println("authenList[i]" + authenList[i]);
		}
		System.out.println("stringBuffer.toString()" + stringBuffer.toString());
		return stringBuffer.toString();
	}

	public static String[] tranToStringList(String listString) {
		String[] liStrings = listString.split("/");
		return liStrings;
	}

}
