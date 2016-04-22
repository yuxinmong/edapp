package com.ed.v1.common;

import com.ed.v1.R;
import com.ed.v1.model.HomeNotice;
import com.ed.v1.ui.main.HomeTabActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

public class NotificationUtil {

    public static final int EMERGENCY_NOTIFICATION_REQUEST_CODE = 1;
    public static final int EMERGENCY_NOTIFICATION_ID = 1;

    /**
     * 在状态栏显示紧急事件的通知
     * @param context
     * @param info
     */
    public static void showMessageNotification(Context context, HomeNotice notice) {
        NotificationCompat.Builder builder = new Builder(context);
        // 点击该通知后要跳转的Activity
        Intent notificationIntent = new Intent(context, HomeTabActivity.class);
        notificationIntent.putExtra("url", notice.getLinkUrl());
        notificationIntent.putExtra("title", notice.getTitle());
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        // 定义Notification的各种属性
        builder.setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle(notice.getTitle())
                .setContentIntent(
                        PendingIntent.getActivity(context, EMERGENCY_NOTIFICATION_REQUEST_CODE, notificationIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT)).setContentText(notice.getSummary())
                .setTicker(notice.getTitle()).setDefaults(Notification.DEFAULT_SOUND);
        // 创建一个NotificationManager的引用
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        // 把Notification传递给NotificationManager
        notificationManager.notify(EMERGENCY_NOTIFICATION_ID, notification);
    }
    
}
