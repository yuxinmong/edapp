package com.ed.v1.net;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ed.v1.BuildConfig;
import com.ed.v1.CLApplication;
import com.ed.v1.common.Constants;

public class URLChooser {

    public static enum BaseURLType {
        Product(Constants.CareLinker.PRODUCT_SITE_URL),
        Development(Constants.CareLinker.DEVELOP_SITE_URL);

        private String baseUrl;

        private BaseURLType(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getBaseUrl() {
            return baseUrl;
        }
    }

    private static final String PERF_SERVER_URL = "base_url_type";

    private static BaseURLType urlType;

    public static String getBaseURL() {

        return getBaseURLType().getBaseUrl();
    }

    public static BaseURLType getBaseURLType() {

        if (urlType == null) {
            if (!BuildConfig.DEBUG) {
                urlType = BaseURLType.Product;
                return urlType;
            }
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(CLApplication.getInstance().getApplicationContext());
            urlType = BaseURLType.valueOf(pref.getString(PERF_SERVER_URL, BaseURLType.Development.name()));
        }
        return urlType;
    }

    public static void setBaseURLType(BaseURLType urlType) {

        URLChooser.urlType = urlType;

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(CLApplication.getInstance().getApplicationContext());
        pref.edit().putString(PERF_SERVER_URL, urlType.name()).commit();
    }

    public static String buildURL(String url) {
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return url;
        }
//        if (url.charAt(0) == '/') {
//            url = url.substring(1);
//        }
        return URLChooser.getBaseURL() + url;
    }

}
