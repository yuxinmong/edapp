package com.ed.v1.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.ed.v1.R;

public class BottomDialogUtils {
	private static Dialog dialog;

	public static void showBottomDialog(Context context, View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context,
				R.style.BottomDialogTheme);

		dialog = builder.create();

		dialog.show();

		Activity activity = (Activity) context;
		WindowManager windowManager = activity.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.gravity = Gravity.BOTTOM;
		lp.width = (display.getWidth());
		dialog.getWindow().setAttributes(lp);

		dialog.setContentView(view);
	}

	public static void dismissBottomDialog() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

}
