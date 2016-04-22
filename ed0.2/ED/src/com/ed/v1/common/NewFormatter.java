package com.ed.v1.common;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yangcheng on 15/4/15.
 */
public class NewFormatter {
    public static final long SECOND = 1000;
    public static final long MINUTE = SECOND * 60;
    public static final long HOUR = MINUTE * 60;
    public static final long DAY = HOUR * 24;
    public static final long MONTH = DAY * 30;
    public static final long YEAR = MONTH * 12;


    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_CN = "yyyy年MM月dd日";
    public static final String FORMAT_DATE_TIME = "yyy-MM-dd HH:mm:ss";
    public static final String FORMAT_TIME = "HH:mm:ss";
    public static final String FORMAT_DATE_HM = "yyyy-MM-dd HH:mm";


    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(FORMAT_DATE);
    private static final SimpleDateFormat dateFormatter_zhcn = new SimpleDateFormat(FORMAT_DATE_CN);
    private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(FORMAT_DATE_TIME);
    private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(FORMAT_TIME);
    private static final SimpleDateFormat dateHMFormat = new SimpleDateFormat(FORMAT_DATE_HM);


    public static String formatDate(long t) {
        return dateFormatter.format(new Date(t));
    }

    public static String formatDate_zhcn(long t) {
        return dateFormatter_zhcn.format(new Date(t));
    }

    public static String formatDateTime(long t) {
        return dateTimeFormatter.format(new Date(t));
    }

    public static String formatTime(long t) {
        return timeFormatter.format(new Date(t));
    }

    public static String toString(long l) {
        return Long.toString(l);
    }

    public static String toString(double d) {
        return Double.toString(d);
    }

    public static String toString(boolean b) {
        return Boolean.toString(b);
    }

    public static String formatDateHM(long t) {
        return dateHMFormat.format(new Date(t));
    }

    public static String formatDaysLeft(long ms) {
        if (ms < 0) {
            return "0 天";
        }
        long minutes = ms / 1000 / 60;
        long hours = minutes / 60;
        minutes = minutes % 60;
        long days = hours / 24;
        hours = hours % 24;

        return days > 0 ? String.format(Locale.getDefault(), "%d 天", days) : String.format(Locale.getDefault(), "%d 小时, %d 分钟", hours, minutes);
    }

    public static String formatShortTimeLeft(long time) {
        if (time >= YEAR) {
            return time / YEAR + "年";
        }
        if (time >= MONTH) {
            return time / MONTH + "个月";
        }
        if (time >= DAY) {
            return time / DAY + "天";
        }
        if (time >= HOUR) {
            return time / HOUR + "小时";
        }
        if (time >= MINUTE) {
            return time / MINUTE + "分钟";
        }
        if (time > SECOND) {
            return time / SECOND + "秒";
        }
        return "1秒";
    }

    public static long String2Date(String dateString) {
        if (TextUtils.isEmpty(dateString)) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateString);
            return date.getTime();
        } catch (ParseException e) {
            Log.logStackTrace(e);
        }
        return new Date().getTime();
    }

    public static String formatStringDate(String d) {
        if (TextUtils.isEmpty(d)) {
            return "N/A";
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (d.indexOf("T") != -1) {
            d = d.substring(0, d.indexOf("T"));
        }
        if (d != null) {
            try {
                return formatDate(format.parse(d).getTime());
            } catch (ParseException e) {
                Log.logStackTrace(e);
            }
        }
        return "N/A";
    }

    /**
     * 格式化这坨东西：Mon, 18 May 2015 04:05:29 GMT
     * EEE, dd MMM yyyy HH:mm:ss z
     *
     * @param s
     * @return
     */
    public static String formatLongStringDate(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        try {
            Date date = simpleDateFormat.parse(s);
            return dateFormatter_zhcn.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "--";
    }

    /**
     * 格式化这坨东西：2015-06-02T03:11:00.000Z
     * yyyy-MM-dd'T'HH:mm:ss.SSS
     *
     * @param s
     * @return
     */
    public static long stringDate2Long(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        try {
            Date date = simpleDateFormat.parse(s);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getDays(long time) {
        return time / DAY;
    }

    public static long getHours(long time) {
        return (time % DAY) / HOUR;
    }

    public static long getMins(long time) {
        return (time % HOUR) / MINUTE;
    }

    public static long getSeconds(long time) {
        return (time % MINUTE) / SECOND;
    }
}
