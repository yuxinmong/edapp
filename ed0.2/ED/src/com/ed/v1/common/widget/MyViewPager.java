package com.ed.v1.common.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager{

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
	}
  
 @Override
public boolean dispatchTouchEvent(MotionEvent ev) {
	// TODO Auto-generated method stub
	 boolean ret = super.dispatchTouchEvent(ev); 
	  if(ret) {
			requestDisallowInterceptTouchEvent(true);  
 
	  }

     return ret;  
}
	  
  
}
