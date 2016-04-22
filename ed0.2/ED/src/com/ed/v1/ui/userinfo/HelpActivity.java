package com.ed.v1.ui.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.util.CommonUtil;

public class HelpActivity extends BaseFragmentActivity implements
		OnClickListener,
		SearchView.OnQueryTextListener {
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
/*	@Res(R.id.listview)
	ListView listview;*/
	private String[] datas = { "关于宜定", "打赏个五分好评", "帮助", "服务协议", "关于宜定",
			"打赏个五分好评", "帮助", "服务协议" ,"关于宜定", "打赏个五分好评", "帮助", "服务协议", "关于宜定",
			"打赏个五分好评", "帮助", "服务协议"};
	private SearchView sv;
	private ListView lv;
	// 自动完成的列表
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_help;
	}

	private void initTitile() {
		// TODO Auto-generated method stub
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("帮助");
		mBtnBack.setOnClickListener(this);

	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.item_help_list, datas);*/
		//listview.setAdapter(adapter);
		CommonUtil.setTranslucentStatus(this);

		initTitile();
		initSerchView();
	}

	private void initSerchView() {
		// TODO Auto-generated method stub
		lv = (ListView) getRootView().findViewById(R.id.lv);
		lv.setAdapter(new ArrayAdapter<String>(this,R.layout.item_help_list, datas));
		lv.setTextFilterEnabled(true);//设置lv可以被过虑
		sv = (SearchView) getRootView().findViewById(R.id.sv);
		// 设置该SearchView默认是否自动缩小为图标
		sv.setIconifiedByDefault(false);
		// 为该SearchView组件设置事件监听器
		sv.setOnQueryTextListener(this);
		// 设置该SearchView显示搜索按钮
		sv.setSubmitButtonEnabled(true);
		// 设置该SearchView内默认显示的提示文本
		sv.setQueryHint("搜索");
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
	// 用户输入字符时激发该方法
		@Override
		public boolean onQueryTextChange(String newText) {
			if (TextUtils.isEmpty(newText)) {
				// 清除ListView的过滤
				lv.clearTextFilter();
			} else {
				// 使用用户输入的内容对ListView的列表项进行过滤
				lv.setFilterText(newText);
			}
			return true;
		}

		// 单击搜索按钮时激发该方法
		@Override
		public boolean onQueryTextSubmit(String query) {
			// 实际应用中应该在该方法内执行实际查询
			// 此处仅使用Toast显示用户输入的查询内容
			return false;
		}
}
