package com.ed.v1.ui.userinfo.express;

import java.util.ArrayList;
import java.util.List;









import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.ui.userinfo.UserInfoActivity;
import com.ed.v1.util.CommonUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpressActivity extends BaseFragmentActivity implements OnClickListener{

	
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_express;
	}
	private void initTitile() {
		// TODO Auto-generated method stub
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("查看物流");
		mBtnBack.setOnClickListener(this);

	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        CommonUtil.setTranslucentStatus(this);

		initTitile();
		CustomNodeListView listview = (CustomNodeListView) this
				.findViewById(R.id.listview);
		View headView = getLayoutInflater().inflate(R.layout.logistics_head_layout, null);
		headView.setFocusable(true);
		headView.setFocusableInTouchMode(true);
		headView.requestFocus();
		listview.addHeaderView(headView);
		
		List<LogisticsInfo> datas = new ArrayList<LogisticsInfo>();
		for (int i = 0; i < 40; i++) {
			LogisticsInfo data = new LogisticsInfo();
			data.setDateTime("2015-10-9");
			if (i == 0 || i == 3 || i == 8) {
				data.setInfo("快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收快件已被 拍照 签收");
			} else if (i == -4) {
				data.setInfo("在广在XXXX公司XX县分部进行签收扫描，快件已被 拍照 签收在XXXX公司XX县分部进行签收扫描，快件已被 拍照 签收");

			} else {
				data.setInfo("在XXXX公司XX县分部进行签收扫描，快件已被 拍照 签收");
			}
			datas.add(data);
		}
		LogisticsAdapter adapter = new LogisticsAdapter(datas, this);
		listview.setAdapter(adapter);
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
