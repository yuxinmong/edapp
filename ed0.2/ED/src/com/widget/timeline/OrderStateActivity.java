package com.widget.timeline;

import java.util.ArrayList;
import java.util.List;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.ListView;


public class OrderStateActivity extends BaseFragmentActivity {
	
	private ListView listView;
	private List<TimeLineModel> list;
	private TimeLineAdapter adapter;



	private void initView() {
		listView=(ListView) findViewById(R.id.listview);
		adapter=new TimeLineAdapter(this, list);
		
		listView.setAdapter(adapter);
	}

	private void initData() {
		list=new ArrayList<TimeLineModel>();
		
		list.add(new TimeLineModel(R.drawable.ic_home, "gsss"));
		list.add(new TimeLineModel(R.drawable.ic_home, "ssssf"));
		list.add(new TimeLineModel(R.drawable.ic_home, "sdfsd"));
		list.add(new TimeLineModel(R.drawable.ic_home, "sadfasd"));
		list.add(new TimeLineModel(R.drawable.ic_home, "asdfsada"));
		list.add(new TimeLineModel(R.drawable.ic_home, "asdfasdf"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_order_state;
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		initData();
		initView();
	}

}
