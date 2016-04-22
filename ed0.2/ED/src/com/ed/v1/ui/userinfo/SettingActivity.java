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
import com.ed.v1.ui.main.HomeTabActivity;
import com.ed.v1.ui.main.MyFragment;

public class SettingActivity extends BaseFragmentActivity implements
		OnClickListener {
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	@Res(R.id.about_ed)
	TextView about_ed;
	@Res(R.id.band_phone)
	TextView band_phone;
	@Res(R.id.personal_information)
	TextView personal_information;
	@Res(R.id.help)
	TextView help;

	@Res(R.id.mark)
	TextView mark;
	@Res(R.id.clear)
	TextView clear;
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
		about_ed.setOnClickListener(this);
		band_phone.setOnClickListener(this);
		personal_information.setOnClickListener(this);
		help.setOnClickListener(this);
		mark.setOnClickListener(this);
		clear.setOnClickListener(this);
		findView(R.id.log_out).setOnClickListener(this);
		initTitile();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {	
	

		case R.id.btnBack:
			finish();
			break;
		case R.id.personal_information:
			Intent intent1 = new Intent(this,
					UserInfoActivity.class);
			startActivity(intent1);
			break;
		case R.id.about_ed:
			Intent intent2 = new Intent(this,
					AboutActivity.class);
			startActivity(intent2);
			break;
		case R.id.help:
			Intent intent3 = new Intent(this,
					HelpActivity.class);
			startActivity(intent3);
			break;
		case R.id.clear:
			break;
		case R.id.mark:
			break;
		case R.id.band_phone:
			Intent intent4 = new Intent(this,
					BandPhoneActivity.class);
			startActivity(intent4);
			break;
		case R.id.log_out:
			logout();
			finish();
			break;
		
		default:
			break;
		}
	}

	private void logout() {
		HomeTabActivity.homeUI.logout();
	}

}
