/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ed.v1.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.ed.v1.R;

public class CommonUtils {

	/**
	 * 检测网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetWorkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}

		return false;
	}

	/**
	 * 检测Sdcard是否存在
	 * 
	 * @return
	 */
	public static boolean isExitsSdcard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}

	public static String getTopActivity(Context context) {
		ActivityManager manager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

		if (runningTaskInfos != null)
			return runningTaskInfos.get(0).topActivity.getClassName();
		else
			return "";
	}

	public static boolean isSameDay(Date date, Date date2) {
		if (date2.getYear() == date.getYear()
				&& date2.getMonth() == date.getMonth()
				&& date2.getDate() == date.getDate()) {
			return true;
		}
		return false;
	}

	// 需要移动utils里面
	public static String getTimeString(Date date) {
		String destTime = null;
		Date nowdate;
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		c.setTime(new Date());
		nowdate = c.getTime();
		if (nowdate.getTime() - (60 * 1000) < date.getTime()) {
			// 显示刚刚
			return "刚刚";
		}

		if (isSameDay(nowdate, date)) {
			// 显示今天
			return "今天";
		}
		c.add(Calendar.DAY_OF_YEAR, -1);
		nowdate = c.getTime();
		if (isSameDay(nowdate, date)) {
			// 显示昨天
			return "昨天";
		} else {
			// 月日 : 时分
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
			destTime = sdf.format(new Date(date.getTime()));
		}

		return destTime;
	}

	public static String getTimeNumber(Date date) {
		String dateTime = null;
		SimpleDateFormat dt = new SimpleDateFormat("HH:mm");
		dateTime = dt.format(date);
		return dateTime;
	}

	// 血压显示
	/*
	 * public static int getBeilisState(final int systolic, final int diastolic)
	 * { // 140--90mmHg ，舒张压90--60mmHg int s0 = 120, s1 = 140, s2 = 159, s3 =
	 * 160, s4 = 179; int d0 = 80, d1 = 90, d2 = 99, d3 = 100, d4 = 109; int
	 * stateId = R.string.normal;
	 * 
	 * if (systolic > s1 && systolic < s2 || diastolic > d1 && diastolic < d2) {
	 * stateId = R.string.high_1; } else if (systolic > s3 && systolic < s4 ||
	 * diastolic > d3 && diastolic < d4) { stateId = R.string.high_2; } else if
	 * (systolic > s4 || diastolic > d4) { stateId = R.string.high_3; }
	 * 
	 * return stateId; }
	 */

	public static String getTimeStr(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss");
		return sdf.format(new Date(time));
	}

	public static String getTimeYMDStr(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(time));
	}

	public static String getTimeYMDHMStr(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(new Date(time));
	}

	public static Date parseData(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(str);
	}

	public static String parseDataNoSpace(long time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date(time));
	}

	public static Date parseDataNoSec(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.parse(str);
	}

	public static Date parseDataByYMDStr(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (str.equals("0000-00-00")) {
			str = "1970-01-01";
		}
		return sdf.parse(str);
	}

	public static int calStatus(int systolic, int diastolic) {
		/*
		 * int status; if ((systolic >= 150 && systolic <= 159) || (diastolic >=
		 * 95 && diastolic <= 99)) { status = 2; } else if (systolic >= 160 ||
		 * diastolic >= 100) { status = 3; } else { status = 1; } return status;
		 */
		int NORMAL = 1;
		int HIGH = 2;
		int LEVEL_TWO_ABOVE = 3;
		int systolic_status, diastolic_status, status;

		if (systolic <= 139) {
			systolic_status = NORMAL;
		} else if (systolic >= 140 && systolic <= 159) {
			systolic_status = HIGH;
		} else {
			systolic_status = LEVEL_TWO_ABOVE;
		}

		if (diastolic <= 89) {
			diastolic_status = NORMAL;
		} else if (diastolic >= 90 && diastolic <= 99) {
			diastolic_status = HIGH;
		} else {
			diastolic_status = LEVEL_TWO_ABOVE;
		}

		if (systolic_status >= diastolic_status) {
			status = systolic_status;
		} else {
			status = diastolic_status;
		}

		return status;
	}

}
