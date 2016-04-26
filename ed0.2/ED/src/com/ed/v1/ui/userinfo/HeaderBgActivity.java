package com.ed.v1.ui.userinfo;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.ed.v1.CLApplication;
import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.common.widget.RoundImageView;
import com.ed.v1.ui.home.fragment.ShowAdapter;
import com.ed.v1.util.CommonUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

public class HeaderBgActivity extends BaseFragmentActivity implements
		OnClickListener {
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	@Res(R.id.bg_listview)
	PullToRefreshListView bg_listview;
	ArrayList<String> datas;
	private BgAdapter bgAdapter;
	public static  HashMap<Integer, Boolean> isSelected;
	private int currentPosition;
	protected boolean isSelectUpdate;
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_choose_background;
	}

	private void initTitile() {
		// TODO Auto-generated method stub
		CommonUtil.setTranslucentStatus(this);
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("选择头像背景");
		mBtnBack.setOnClickListener(this);

	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
CommonUtil.setTranslucentStatus(this);
		initTitile();
		initDatas();
		initPullToRefresh();
		
	
	}

	private void initDatas() {
		// TODO Auto-generated method stub
		datas=new ArrayList<String> ();
		for(int i=0;i<6;i++){
			String data=R.drawable.bg_header+"";
			datas.add(data);
		}
		
	}

	private void initPullToRefresh() {
		bg_listview.getLoadingLayoutProxy().setLastUpdatedLabel(
				"lastUpdateLabel");
		bg_listview.getLoadingLayoutProxy().setLastUpdatedLabel(
				"lastUpdateLabel");
		bg_listview.getLoadingLayoutProxy().setPullLabel("PULLLABLE");
		bg_listview.getLoadingLayoutProxy().setRefreshingLabel(
				"refreshingLabel");
		bg_listview.getLoadingLayoutProxy().setReleaseLabel("releaseLabel");
		bg_listview.setMode(Mode.PULL_UP_TO_REFRESH);
		bgAdapter=new BgAdapter(this);
		bg_listview.setAdapter(bgAdapter);
		
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

	class BgAdapter extends BaseAdapter {

		private Context context;
		public BgAdapter(Context ctx) {
			context = ctx;

		}
	
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return datas.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return datas.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			   ChoiceListItemView choiceListItemView = new ChoiceListItemView(context, null,datas.get(position));
			return choiceListItemView;
		}

	}

	private class ViewHolder {

		private RoundImageView bg_item_header;
		private CheckBox choose_img;
		private TextView count;
		//用来记录是否正在选择某一张图片，避免adapter数据更新时重复加载图片
		private boolean isSelectUpdate;
	}

}
