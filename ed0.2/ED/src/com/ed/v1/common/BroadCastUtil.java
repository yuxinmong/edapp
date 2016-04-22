package com.ed.v1.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Haijun.Wang on 15/6/24.
 */
public class BroadCastUtil {

    // broadcast
    public static final class Action {
        public static final String NETWORK_STATE_CHANGED = "com.carelinker.pharmacy.v2.NETWORK_STATE_CHANGED";
        public static final String NETWORK_STATE_CHANGED_EXTRA_AVAILABLE = "available";
        public static final String LOGIN = "com.carelinker.pharmacy.v2.action.LOGIN";
        public static final String LOGOUT = "com.carelinker.pharmacy.v2.action.LOGOUT";
    }


    public static void broadcast(Context context, String action, Uri uri) {
        Intent intent = new Intent(action);
        if (uri != null) {
            intent.setData(uri);
        }
        context.sendBroadcast(intent);
    }

    public static void broadcastLogin(Context context) {
        broadcast(context, Action.LOGIN, null);
    }

    public static void broadcastLogout(Context context) {
        broadcast(context, Action.LOGOUT, null);
    }

}
