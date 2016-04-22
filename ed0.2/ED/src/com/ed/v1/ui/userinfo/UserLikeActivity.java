package com.ed.v1.ui.userinfo;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.SystemStatusManager;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.ui.home.fragment.ShowAdapter;
import com.ed.v1.ui.main.ClothContentFragment;
import com.ed.v1.ui.userinfo.HeaderBgActivity.BgAdapter;
import com.ed.v1.util.CommonUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class UserLikeActivity extends BaseFragmentActivity implements
		OnClickListener {
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	String goods[]={"3","2","1","3","2"};
	@Res(R.id.listview)
	PullToRefreshListView listview;
	private ShowAdapter bgAdapter;
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_user_like;
	}

	private void initTitile() {
		// TODO Auto-generated method stub
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("我喜欢的");
		mBtnBack.setOnClickListener(this);

	}

	private void initLisview() {
		// TODO Auto-generated method stub
		listview.setMode(Mode.BOTH);
		bgAdapter= new ShowAdapter(this,goods);
		listview.setAdapter(bgAdapter);
		
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		CommonUtil.setTranslucentStatus(this);
		initTitile();
		initLisview();
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
