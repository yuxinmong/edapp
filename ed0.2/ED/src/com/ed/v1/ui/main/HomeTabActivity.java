package com.ed.v1.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.Constants;
import com.ed.v1.common.SystemStatusManager;
import com.ed.v1.common.ToastUtil;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.datacenter.DataCenter;
import com.ed.v1.model.UserInfo;
import com.ed.v1.net.api.APIRequest;
import com.ed.v1.net.bean.Content;
import com.ed.v1.util.CommonUtil;

public class HomeTabActivity extends BaseFragmentActivity implements
		OnClickListener {

	public static final int DELAY_INIT = 1;
	FragmentManager fm;
	// private BaseFragment homeFrament;
	@Res(R.id.tab_home)
	private TextView tabHomeBtn;
	@Res(R.id.tab_my)
	private TextView tabMyBtn;
	@Res(R.id.tab_show)
	private TextView tabShow;
	@Res(R.id.bottom_layout)
	private LinearLayout bottom_layout;
	// @Res(R.id.nav_title)
	// private TextView tvTitle;
	private UserInfo userinfo;
	private int index;
	private TextView currentView;
	// private LinearLayout title_layout;
	public static HomeTabActivity homeUI;
	private PopupWindow popupWindow;
	View items_popup_content;

	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_home;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		homeUI = this;
		super.onCreate(savedInstanceState);
       // setTranslucentStatus();
		//透明状态栏
		CommonUtil.setTranslucentStatus(homeUI);
       // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		items_popup_content = LayoutInflater.from(this).inflate(
				R.layout.items_popup_content, null);
		changeFragment(1);
		delayPopup();
	}
	
	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// title_layout = (LinearLayout) findViewById(R.id.title_layout);
		userinfo = DataCenter.getInstance().getUserInfo();
		tabHomeBtn.setSelected(true);
		tabHomeBtn.setOnClickListener(this);
		tabMyBtn.setOnClickListener(this);
		tabShow.setOnClickListener(this);

	}

	private void delayInit() {
		changeFragment(1);
	}

	private void delayPopup() {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				// execute the task
				popupWindow = new PopupWindow(items_popup_content,
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);
				popupWindow.setOutsideTouchable(true);
				popupWindow.setFocusable(true);
				popupWindow.setBackgroundDrawable(new ColorDrawable(0));
				popupWindow.showAtLocation(getRootView(), Gravity.CENTER, 0, 0);
			}
		}, 2000);
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		dismissPopupWindow();
		super.onStop();

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.tab_home:
			changeFragment(1);
			break;
		case R.id.tab_show:
			changeFragment(2);
			break;
		case R.id.tab_my:
			changeFragment(3);
			break;
		case R.id.btnRight:
			logout();
			break;
		default:
			break;
		}
	}

	public void changeFragment(int index) {
		if (this.index == index) {
			return;
		}
		this.index = index;
		String tag = "";
		if (currentView != null) {
			currentView.setSelected(false);
		}
		switch (index) {
		case 1:
			tag = "home";
			tabHomeBtn.setSelected(true);
			currentView = tabHomeBtn;
			bottom_layout.setVisibility(View.VISIBLE);
			break;
		case 2:
			tag = "show";
			bottom_layout.setVisibility(View.GONE);
			tabShow.setSelected(true);
			currentView = tabShow;
			break;
		case 3:
			tag = "my";
			tabMyBtn.setSelected(true);
			currentView = tabMyBtn;
			bottom_layout.setVisibility(View.VISIBLE);

			break;
		default:
			break;
		}
		FragmentController.getInstance().showFragmentByTag(
				getSupportFragmentManager(), tag);

	}

	long time;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (System.currentTimeMillis() - time > 3 * 1000) {
			ToastUtil.show(this, "再按一次退出ED");
			time = System.currentTimeMillis();
		} else {
			
			super.onBackPressed();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		clear();
		super.onDestroy();
	}

	public void logout() {
		APIRequest<Content> request = new APIRequest<Content>(Method.DELETE,
				Constants.CareLinker.URL_LOGIN, Content.class);
		request.addParameter("userId", DataCenter.getInstance().getUserInfo()
				.getUserId());
		addRequest(request, null);
		clear();
		SharedPreferences share = getSharedPreferences(
				Constants.CareLinker.SHARE_NAME, 0);
		share.edit().remove("userName").remove("password").commit();
		Intent intent = new Intent(HomeTabActivity.this,
				HomeTabActivity.class);
		startActivity(intent);
		finish();
	}

	private void clear() {
		DataCenter.getInstance().clear();
		FragmentController.getInstance().clear();
	}

	public void restartAPP() {
		Intent intent = new Intent(HomeTabActivity.this,
				HomeTabActivity.class);
		// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	private void dismissPopupWindow() {
		// TODO Auto-generated method stub
		if (popupWindow != null) {
			popupWindow.dismiss();
		}
	}

}
