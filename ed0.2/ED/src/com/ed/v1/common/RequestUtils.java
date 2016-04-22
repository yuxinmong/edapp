package com.ed.v1.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.ed.v1.BuildConfig;
import com.ed.v1.CLApplication;

import java.util.regex.Pattern;

/**
 * Created by Haijun.Wang on 15/6/24.
 */
public final class RequestUtils {

    /**
     * Return whether or not the string is empty, where empty is either null, an
     * empty string, or a string containing only whitespace.
     *
     * @param s
     */
    public static final boolean isEmpty(String s) {
        return s == null || s.length() == 0 || s.trim().length() == 0;
    }

    /**
     * Checks whether the input string matches the given regular expression
     * pattern
     *
     * @param input
     *            String to test
     * @param pattern
     *            regular expression to match
     * @return true if the String matches the regular expression
     */
    public static boolean matches(String input, Pattern pattern) {
        return pattern.matcher(input).matches();
    }

    public static int compareValues(double value1, double value2) {
        return Double.compare(value1, value2);
    }

    public static void activityRedirect(Context context, Class<?> toActivity) {
        Intent intent = new Intent(context, toActivity);
        intent.putExtra("fromActivity", context.getClass().getSimpleName());

        context.startActivity(intent);
    }

    public static void leaveTimeCalc(Context context) {
        SharedPreferences sp = context.getSharedPreferences("gestureLockStore", 0);
        SharedPreferences.Editor Ed = sp.edit();
        Ed.putLong("appLeaveTime", System.currentTimeMillis());
        Ed.commit();
    }

    public static boolean isNeedGestureLogin(Context context) {
        SharedPreferences sp = context.getSharedPreferences("gestureLockStore", 0);
        long appLeaveTime = sp.getLong("appLeaveTime", -1);
        long appBackTime = System.currentTimeMillis();

        if (appLeaveTime != -1) {
            if (!BuildConfig.DEBUG) {
                if (!(10 * 60 * 1000 >= (appBackTime - appLeaveTime))) {
                    return true;
                } else {
                    return false;
                }

            } else {
                if (!(10 * 60 * 1000 >= (appBackTime - appLeaveTime))) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    public static void resetLeaveTime(Activity context) {
        SharedPreferences sp = context.getSharedPreferences("gestureLockStore", 0);
        SharedPreferences.Editor Ed = sp.edit();
        Ed.putLong("appLeaveTime", -1);
        Ed.commit();
    }

    public static String getSharedPreferenceValue(String nameSpace, String key) {
        SharedPreferences sp = CLApplication.getInstance().getSharedPreferences(nameSpace, 0);
        return sp.getString(key, "");
    }

    public static void setSharedPreferenceValue(String nameSpace, String key, String value) {
        SharedPreferences sp = CLApplication.getInstance().getSharedPreferences(nameSpace, 0);
        SharedPreferences.Editor Ed = sp.edit();
        Ed.putString(key, value);
        Ed.commit();
    }

    public static void logError(String tag, Exception e) {
        Log.e(tag, e == null ? "exception" : e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage());
    }

}
