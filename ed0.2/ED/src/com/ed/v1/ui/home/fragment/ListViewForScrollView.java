package com.ed.v1.ui.home.fragment;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ListViewForScrollView extends ListView{
	 public ListViewForScrollView(Context context) {
		  super(context);
		  // TODO Auto-generated constructor stub
		 }
		 public ListViewForScrollView(Context context, AttributeSet attrs) {
		  super(context, attrs);
		 }
		
		 @Override
		 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		  int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
		    MeasureSpec.AT_MOST);
		  super.onMeasure(widthMeasureSpec, expandSpec);
		 }
}

