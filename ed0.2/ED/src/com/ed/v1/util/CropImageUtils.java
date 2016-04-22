package com.ed.v1.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class CropImageUtils {

	public static void crop(Uri uri, Activity activity, final int ABC) {
		// 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// 裁剪框的比例，2：3
		intent.putExtra("aspectX", 2);
		intent.putExtra("aspectY", 3);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", 200);
		intent.putExtra("outputY", 300);
		// 图片格式
		intent.putExtra("outputFormat", "JPEG");
		intent.putExtra("noFaceDetection", false);// 人脸识别
		intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
		activity.startActivityForResult(intent, ABC);
	}

}
