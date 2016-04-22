package com.ed.v1.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BirthAgeUtils {

	// 通过年龄得到出生年份
	public static Date getBirthYearFromAge(int age) {
		Calendar mycalendar = Calendar.getInstance();
		int year = mycalendar.get(Calendar.YEAR);
		int birthYear = year - age;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(birthYear - 1900, 00, 01);
		return date;
	}

	// 通过出生年份得到年龄
	public static Integer getAgeFromBirthday(Date birthday) {
		if (birthday == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int age = new Date().getYear() + 1900
				- Integer.parseInt(sdf.format(birthday));
		return age;
	}

}
