package com.ed.v1.net;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONHelper {

    public static ObjectMapper jsonParser = new ObjectMapper();

    // JSON Helpers

    public static JSONObject optJSONObject(JSONObject o, String... names) throws JSONException {
        if (o == null) {
            return null;
        }

        for (String name : names) {
            if (o.has(name) && !o.isNull(name)) {
                return o.getJSONObject(name);
            }
        }

        return null;
    }

    public static JSONArray optJSONArray(JSONObject o, String... names) throws JSONException {
        if (o == null) {
            return null;
        }

        for (String name : names) {
            if (o.has(name) && !o.isNull(name)) {
                return o.getJSONArray(name);
            }
        }

        return null;
    }

    public static int optInt(JSONObject o, String... names) throws JSONException {
        if (o == null) {
            return 0;
        }

        for (String name : names) {
            if (o.has(name) && !o.isNull(name)) {
                return o.getInt(name);
            }
        }

        return 0;
    }

    public static long optLong(JSONObject o, String... names) throws JSONException {
        if (o == null) {
            return 0;
        }

        for (String name : names) {
            if (o.has(name) && !o.isNull(name)) {
                return o.getLong(name);
            }
        }

        return 0;
    }

    public static double optDouble(JSONObject o, String... names) throws JSONException {
        if (o == null) {
            return 0.0;
        }

        for (String name : names) {
            if (o.has(name) && !o.isNull(name)) {
                return o.getDouble(name);
            }
        }

        return 0.0;
    }

    public static String optString(JSONObject o, String... names) throws JSONException {
        if (o == null) {
            return "";
        }

        for (String name : names) {
            if (o.has(name) && !o.isNull(name)) {
                return o.getString(name);
            }
        }

        return "";
    }

    public static boolean optBoolean(JSONObject o, String... names) throws JSONException {
        if (o == null) {
            return false;
        }

        for (String name : names) {
            if (o.has(name) && !o.isNull(name)) {
                return o.getBoolean(name);
            }
        }

        return false;
    }

}
