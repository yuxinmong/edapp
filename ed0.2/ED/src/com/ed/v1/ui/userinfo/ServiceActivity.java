package com.ed.v1.ui.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.util.CommonUtil;

public class ServiceActivity extends BaseFragmentActivity implements
		OnClickListener {
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;

	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_service;
	}

	private void initTitile() {
		// TODO Auto-generated method stub
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("服务条款");
		mBtnBack.setOnClickListener(this);

	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
CommonUtil.setTranslucentStatus(this);
		initTitile();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btnBack:
			finish();
			break;

		default:
			break;
		}
	}

}
