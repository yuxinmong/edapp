package com.ed.v1.ui.userinfo;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragment;
import com.ed.v1.common.viewholder.Res;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class OderListFragment extends BaseFragment {
	
	@Res(R.id.listview)
	PullToRefreshListView  oder_pull_refresh_list;
	
	private ArrayList<String> datas;
	private String text="";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Bundle args = getArguments();
		text = args != null ? args.getString("text") : "";
		super.onCreate(savedInstanceState);
	}
	

	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_order_list;
	}
	private void initList(){
		initPullToRefresh();
		oder_pull_refresh_list.setAdapter(new OrderAdapter(getActivity(),datas));
		oder_pull_refresh_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),OderInfoActivity.class);
				startActivity(intent);
			}
		});
		
	}
	private void initPullToRefresh() {
		
		oder_pull_refresh_list.setMode(Mode.BOTH);
		
	}
	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		datas=new ArrayList<String>();
		for(int i=0;i<=3;i++){
			datas.add("1");
		}
		initList();
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		
	}
}
