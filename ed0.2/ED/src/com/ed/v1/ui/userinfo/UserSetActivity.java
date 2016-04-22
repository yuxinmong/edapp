package com.ed.v1.ui.userinfo;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.common.widget.MyToast;
import com.ed.v1.ui.main.HomeTabActivity;
import com.ed.v1.util.CommonUtil;

public class UserSetActivity extends BaseFragmentActivity implements
		OnClickListener {
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	@Res(R.id.personal_information)
	TextView personal_information;
	
	@Res(R.id.band_phone)
	RelativeLayout band_phone;
	@Res(R.id.clear)
	RelativeLayout clear;
	@Res(R.id.about_ed)
	TextView about_ed;
	@Res(R.id.mark)
	TextView mark;
	@Res(R.id.help)
	TextView help;
	@Res(R.id.service)
	TextView service;
	@Res(R.id.clear_text)
	TextView clear_text;
	@Res(R.id.log_out)
	Button log_out;
	private PopupWindow popupWindow;
	private View progress_clear_popup_content;

	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_setting;
	}

	private void initTitile() {
		// TODO Auto-generated method stub
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("设置");
		mBtnBack.setOnClickListener(this);

	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		CommonUtil.setTranslucentStatus(this);
		findView(R.id.log_out).setOnClickListener(this);
		personal_information.setOnClickListener(this);
		about_ed.setOnClickListener(this);
		band_phone.setOnClickListener(this);
		help.setOnClickListener(this);
		mark.setOnClickListener(this);
		clear.setOnClickListener(this);
		service.setOnClickListener(this);
		initTitile();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btnBack:
			finish();
			break;

		case R.id.log_out:
			logout();
			finish();
			break;
		case R.id.personal_information:
			Intent intent = new Intent(getApplicationContext(),
					UserInfoActivity.class);
			startActivity(intent);
			break;
		case R.id.band_phone:
			Intent intent1 = new Intent(getApplicationContext(),
					BandPhoneActivity.class);
			startActivity(intent1);
			break;
		case R.id.clear:
			clearPopup();
			endPopup();
			break;
		case R.id.mark:

			break;
		case R.id.help:
			Intent intent2 = new Intent(getApplicationContext(),
					HelpActivity.class);
			startActivity(intent2);
			break;
		case R.id.service:
			Intent intent3 = new Intent(getApplicationContext(),
					ServiceActivity.class);
			startActivity(intent3);
			break;
		case R.id.about_ed:
			Intent intent4 = new Intent(getApplicationContext(),
					AboutActivity.class);
			startActivity(intent4);
			break;
		default:
			break;
		}
	}
	void clearPopup()
	{
		 progress_clear_popup_content = LayoutInflater.from(this).inflate(
				R.layout.progress_clear_popup_content, null);
		popupWindow = new PopupWindow(progress_clear_popup_content,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0));
		popupWindow.showAtLocation(getRootView(), Gravity.CENTER, 0, 0);
	}

	private void logout() {
		HomeTabActivity.homeUI.logout();
	}
	private void endPopup() {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				// execute the task
				dismissPopupWindow();
				MyToast.showCustomerToastShot(HomeTabActivity.homeUI,
						R.string.clear_finish,R.layout.userinfo_toast);
				clear_text.setText(R.string.clear_count);
				clear_text.invalidate();
			}
		}, 1000);
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		dismissPopupWindow();
		super.onStop();

	}
	private void dismissPopupWindow() {
		// TODO Auto-generated method stub
		if (popupWindow != null) {
			popupWindow.dismiss();
		}
	}
}
