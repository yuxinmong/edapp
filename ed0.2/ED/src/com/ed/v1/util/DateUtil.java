package com.ed.v1.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.text.TextUtils;

public class DateUtil {

	public enum FormatPATTERN {
		UPLOAD_PATTERN("yyyyMMddhhmmss"), BIRTHDAY("yyyy-MM-dd");
		private String style;

		private FormatPATTERN(String style) {
			this.style = style;
		}

		@Override
		public String toString() {

			return String.valueOf(this.style);

		}
	}

	private static SimpleDateFormat format;

	public static String getUploadTimeByNow() {
		return getFormatDate(new Date(),
				FormatPATTERN.UPLOAD_PATTERN.toString());
	}

	public static String getFormatDate(Date date, String pattern) {
		format = new SimpleDateFormat(pattern);
		if (date != null) {
			return format.format(date);
		} else {
			return null;
		}
	}

	public static int getAge(String date) {
		return getAge(getDateByString(date));
	}

	public static Date getDateByString(String dateStr) {
		if (TextUtils.isEmpty(dateStr)) {
			return new Date();
		}
		Date date = null;
		;
		format = new SimpleDateFormat(FormatPATTERN.BIRTHDAY.style);
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			date = new Date();
		}
		return date;
	}

	/** 计算年龄 */
	public static int getAge(Date birthDay) {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			return 0;
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age;
	}

	public static String getBirthdayByAge(int age) {
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		String bir = (yearNow - age) + "-00-00";
		return bir;
	}

	public static String getFormatRemindTime(String time) {
		if (TextUtils.isEmpty(time)) {
			return "";
		} else {
			return time.replaceAll(":", "") + "00";
		}
	}
}
