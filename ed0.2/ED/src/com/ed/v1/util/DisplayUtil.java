package com.ed.v1.util;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * 用于获取屏幕宽高
 * 
 * @author jiangxy
 * 
 */
public class DisplayUtil {

	private static Display display;
	private static Activity act;
	private static DisplayUtil displayUtil;

	private DisplayUtil() {
	}

	/**
	 * 获取DisplayUtil实例
	 * 
	 * @param activity
	 * @return
	 */
	public static DisplayUtil getInstance(Context activity) {
		act = (Activity) activity;
		display = act.getWindowManager().getDefaultDisplay();
		if (displayUtil == null) {
			displayUtil = new DisplayUtil();
		}
		return displayUtil;
	}

	/**
	 * 获取屏幕宽度，单位px
	 * 
	 * @return
	 */
	public int getScreenWidth() {
		return display.getWidth();
	}

	/**
	 * 获取屏幕高度，单位px
	 * 
	 * @return
	 */
	public int getScreenHeight() {
		return display.getHeight();
	}

	/**
	 * 获取屏幕宽度，单位dip
	 * 
	 * @return
	 */
	public int getScreenWidthDip() {
		return DisplayUtil.getInstance(act).px2dip(getScreenWidth());
	}

	/**
	 * 获取屏幕高度，单位dip
	 * 
	 * @return
	 */
	public int getScreenHeightDip() {
		return DisplayUtil.getInstance(act).px2dip(getScreenHeight());
	}

	/**
	 * 获取控件view宽度，单位px
	 * 
	 * @param viewId
	 * @return
	 */
	public int getViewWidthByResource(int viewId) {
		View view = this.act.findViewById(viewId);
		Integer width = view.getLayoutParams().width;
		return (width != null) ? width : 0;
	}

	/**
	 * 获取View控件高度，单位px
	 * 
	 * @param viewId
	 * @return
	 */
	public int getViewHeightByResource(int viewId) {
		View view = this.act.findViewById(viewId);
		Integer height = view.getLayoutParams().height;
		return (height != null) ? height : 0;
	}

	/**
	 * 获取控件view宽度，单位dip
	 * 
	 * @param viewId
	 * @return
	 */
	public int getViewWidthByResourceDip(int viewId) {
		return DisplayUtil.getInstance(act).px2dip(
				getViewWidthByResource(viewId));
	}

	/**
	 * 获取View控件高度，单位dip
	 * 
	 * @param viewId
	 * @return
	 */
	public int getViewHeightByResourceDip(int viewId) {
		return DisplayUtil.getInstance(act).px2dip(
				getViewHeightByResource(viewId));
	}

	/**
	 * 获取view控件百分比宽度，单位px
	 * 
	 * @param viewId
	 * @param percent
	 * @return
	 */
	public int getViewWidthPercent(int viewId, int percent) {
		return (int) (getViewWidthByResource(viewId) * percent * 0.01);
	}

	/**
	 * 获取view百分比高度，单位px
	 * 
	 * @param viewId
	 * @param percent
	 * @return
	 */
	public int getViewHeightPercent(int viewId, int percent) {
		return (int) (getViewHeightByResource(viewId) * percent * 0.01);
	}

	/**
	 * 获取view控件百分比宽度，单位dip
	 * 
	 * @param viewId
	 * @param percent
	 * @return
	 */
	public int getViewWidthPercentDip(int viewId, int percent) {
		return DisplayUtil.getInstance(act).px2dip(
				getViewWidthPercent(viewId, percent));
	}

	/**
	 * 获取view百分比高度，单位dip
	 * 
	 * @param viewId
	 * @param percent
	 * @return
	 */
	public int getViewHeightPercentDip(int viewId, int percent) {
		return DisplayUtil.getInstance(act).px2dip(
				getViewHeightPercent(viewId, percent));
	}

	/**
	 * 获取屏幕百分比宽度，单位px
	 * 
	 * @param percent
	 * @return
	 */
	public int getScreenWidthByPercent(int percent) {
		return (int) (getScreenWidth() * percent * 0.01);
	}

	/**
	 * 获取屏幕百分比高度，单位px
	 * 
	 * @param percent
	 * @return
	 */
	public int getScreenHeightByPercent(int percent) {
		return (int) (getScreenHeight() * percent * 0.01);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px
	 * 
	 * @param dpValue
	 * @return
	 */
	public int dip2px(int dpValue) {
		final float scale = act.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px
	 * 
	 * @param dpValue
	 * @return
	 */
	public int dip2px(float dpValue) {
		final float scale = act.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px 的单位 转成为 dp
	 * 
	 * @param pxValue
	 * @return
	 */
	public int px2dip(int pxValue) {
		final float scale = act.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px 的单位 转成为 dp
	 * 
	 * @param pxValue
	 * @return
	 */
	public int px2dip(float pxValue) {
		final float scale = act.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 设置全屏与取消全屏
	 * 
	 * @param isFullScreen
	 */
	public void fullScreen(Boolean isFullScreen) {
		if (isFullScreen) {
			act.getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN); // 设置全屏
		} else {
			WindowManager.LayoutParams attrs = act.getWindow().getAttributes();
			attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
			act.getWindow().setAttributes(attrs);
			act.getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		}
	}

	/**
	 * 状态栏高度
	 * 
	 * @return
	 */
	public int getStatusBarHeight() {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = act.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}

	/**
	 * 状态栏高度
	 * 
	 * @return
	 */
	public int getStatusBarHeightBottom() {
		Rect rect = new Rect();
		act.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
		int statusBarHeight = act.getWindow().getWindowManager()
				.getDefaultDisplay().getHeight()
				- rect.bottom;
		return statusBarHeight + getStatusBarHeight();
	}

}
