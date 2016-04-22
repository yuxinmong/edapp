package com.ed.v1.ui.userinfo;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ed.v1.R.string;

/**
 * @version 0.1 king 2015-11
 */
public class ArrayDataDemo {

	private static final Map<String, Map<String, List<String>>> DATAs = new LinkedHashMap<>();
	private static int start = 1880;
	private static int end = 2016;

	public static final int YEAR=1;
	public static final int MONTH=2;
	public static final int DAY=3;
	public static  int tag;
	private static void init() {
		if (!DATAs.isEmpty()) {
			return;
		}

		for (int i = start; i < end; i++) {
			Map<String, List<String>> city = new HashMap<>();
			for (int j = 0; j < 12; j++) {
				List<String> data = new ArrayList<>();
				for (int k = 0; k < 30; k++) {
					data.add(getRandomText() + k);
				}
				city.put("" + j, data);
			}
			String data=null;
			switch (tag) {
			case YEAR:
				data=i+"年";
				break;
			case MONTH:
				data=i+"月";
				break;
			case DAY:
				data=i+"日";
				break;
			default:
				break;
			}
			
			DATAs.put(data, city);
		}
	}

	private static Random random = new Random();

	private static String getRandomText() {
		int num = 31;
		String str = "1";
		for (int i = 1; i < num; i++) {
			str = "" + i;
		}
		return str;
	}

	public static Map<String, Map<String, List<String>>> getAll() {
		init();
		return new HashMap<>(DATAs);
	}

}
