package com.ed.v1.ui.userinfo;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ed.v1.CLApplication;
import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.common.widget.RoundImageView;
import com.ed.v1.ui.home.fragment.ShowAdapter;
import com.ed.v1.ui.main.HomeTabActivity;
import com.ed.v1.util.CommonUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

public class AdressActivity extends BaseFragmentActivity implements
		OnClickListener {
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	@Res(R.id.add_adresslv)
	TextView add_adresslv;
	@Res(R.id.listview)
	PullToRefreshListView listview;

	ArrayList<String> datas;
	public static HashMap<Integer, Boolean> isSelected;
	private int currentPosition;
	private AdressAdapter adapter;

	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_adress;
	}

	private void initTitile() {
		// TODO Auto-generated method stub
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("收货地址");
		mBtnBack.setOnClickListener(this);

	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		CommonUtil.setTranslucentStatus(this);
		initTitile();
		initdatas();
		initListview();
		add_adresslv.setOnClickListener(this);
	}



	private void initdatas() {
		// TODO Auto-generated method stub
		datas = new ArrayList<String>();
		datas.add("sss");
		datas.add("sss");
	}

	private void initListview() {
		// TODO Auto-generated method stub

		//listview.getLoadingLayoutProxy().setLastUpdatedLabel("更新中");
		listview.getLoadingLayoutProxy().setPullLabel("加载。。。");
		listview.getLoadingLayoutProxy().setRefreshingLabel("更新完毕");
		listview.getLoadingLayoutProxy().setReleaseLabel("更新中。。。");
		listview.setMode(Mode.DISABLED);
		adapter = new AdressAdapter(this);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {
				// TODO Auto-generated method stub

				isSelected.put(currentPosition - 1, false);
				currentPosition = position;
				ViewHolder holder = (ViewHolder) v.getTag();
				holder.isSelectUpdate = true;
				isSelected.put(position - 1, true);
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btnBack:
			finish();
			break;
		case R.id.add_adresslv:
			Intent intent=new Intent(this,AddAdressActivity.class);
			intent.putExtra("updateOradd", "add");
			startActivity(intent);
		default:
			break;
		}
	}

	class AdressAdapter extends BaseAdapter {
		private Context context;

		public AdressAdapter(Context ctx) {
			context = ctx;
			init();
		}
		private void init() {
			// TODO Auto-generated method stub
			isSelected = new HashMap<Integer, Boolean>();  
            for (int i = 0; i < datas.size(); i++) {  
                isSelected.put(i, false);  
            } 
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
			View contentview = convertView;
			final ViewHolder holder;
			if (contentview == null) {
				holder = new ViewHolder();
				contentview = LayoutInflater.from(context).inflate(
						R.layout.item_adress, null);
				holder.selector_add_adress = (ImageView) contentview
						.findViewById(R.id.selector_add_adress);
				holder.update_adress = (ImageView) contentview
						.findViewById(R.id.update_adress);
			
				  holder.update_adress.setOnClickListener(new OnClickListener()
				 {
				  
				  @Override public void onClick(View arg0) { // TODO
				  Intent intent1 = new
				  Intent(context,AddAdressActivity.class);
				  intent1.putExtra("updateOradd", "update");

				  startActivity(intent1); } });
				

				contentview.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();

			}
			holder.selector_add_adress.setSelected(isSelected.get(position));

			return contentview;
		}

	}

	private class ViewHolder {

		private ImageView update_adress;
		private ImageView selector_add_adress;
		// 用来记录是否正在选择某一张图片，避免adapter数据更新时重复加载图片
		private boolean isSelectUpdate;
	}
}
