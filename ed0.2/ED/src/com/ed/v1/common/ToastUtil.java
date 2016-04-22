package com.ed.v1.common;

import android.content.Context;
import android.widget.Toast;
import com.ed.v1.BuildConfig;

/**
 * Created by Haijun.Wang on 15/6/28.
 */
public class ToastUtil {
    public static void show(Context context, String info) {
        Toast toast = Toast.makeText(context, info, Toast.LENGTH_SHORT);
        // LinearLayout toastView = (LinearLayout) toast.getView();
        // Drawable background =
        // context.getResources().getDrawable(R.drawable.yellow_background_with_radius);
        // toastView.setBackgroundDrawable(background);
        toast.show();
    }

    public static void show(Context context, int id, Object... arg) {
        ToastUtil.show(context, context.getString(id, arg));
    }

    public static void debug(Context context, String text) {
        if (BuildConfig.DEBUG) {
            Toast.makeText(context, "DEBUG: " + text, Toast.LENGTH_LONG).show();
        }
    }
}
