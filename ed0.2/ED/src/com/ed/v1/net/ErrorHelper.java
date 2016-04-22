package com.ed.v1.net;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.android.volley.*;
import com.ed.v1.R;
import com.ed.v1.common.Log;
import com.ed.v1.common.dialog.MessageBox;
import com.ed.v1.net.api.APIResponse;
import com.ed.v1.ui.main.HomeTabActivity;

import org.json.JSONObject;

public class ErrorHelper {

    public static void login(Activity activity) {
        Intent loginIntent = new Intent(activity, HomeTabActivity.class);
        activity.startActivity(loginIntent);
    }

    /**
     * 
     * @param activity
     * @param requestToken
     *            true：需要重新请求token
     */
    public static void login(Activity activity, boolean requestToken) {
        Intent loginIntent = new Intent(activity, HomeTabActivity.class);
        activity.startActivity(loginIntent);
    }

    public static void prompt(APIResponse<?> response, Context context) {
        if (response != null && response.isFailed()) {

            String error = null;

            if (response.getException() != null) {
                error = ErrorHelper.getMessage(response.getException(), context);
            } else {
                error = response.getError();
            }

            if (error != null) {
                MessageBox.show(new MessageBox.Error(context, error));
            }
        }
    }

    /**
     * Returns appropriate message which is to be displayed to the user against
     * the specified error object.
     * 
     * @param error
     * @param context
     * @return
     */
    public static String getMessage(Object error, Context context) {
        if (error instanceof TimeoutError) {
            return context.getResources().getString(R.string.error_no_response);
        } else if (ErrorHelper.isServerProblem(error)) {
            return ErrorHelper.handleServerError(error, context);
        } else if (ErrorHelper.isNetworkProblem(error)) {
            return context.getResources().getString(R.string.error_no_internet_connectivity);
        } else if (error instanceof ParseError) {
            return "数据解析错误";
        }
        return "网络错误";
    }

    /**
     * Determines whether the error is related to network
     * 
     * @param error
     * @return
     */
    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError) || (error instanceof NoConnectionError);
    }

    /**
     * Determines whether the error is related to server
     * 
     * @param error
     * @return
     */
    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    /**
     * Handles the server error, tries to determine whether to show a stock
     * message or to show a message retrieved from the server.
     * 
     * @param err
     * @param context
     * @return
     */
    private static String handleServerError(Object err, Context context) {
        VolleyError error = (VolleyError) err;

        NetworkResponse response = error.networkResponse;

        if (response != null) {
            switch (response.statusCode) {
                case 404:
                    return "未找到该资源或操作对象:404";
                case 422:
                case 401:
                    try {

                        JSONObject o = new JSONObject(new String(response.data).toString());

                        String result = o.getString("result");

                        return result;

                    } catch (Exception e) {
                        Log.logStackTrace(e);
                    }
                    // invalid request
                    return error.getMessage();

                default:
                    return "服务器未知错误:" + response.statusCode + "01";
            }
        }

        return "服务器未知错误";
    }
}
