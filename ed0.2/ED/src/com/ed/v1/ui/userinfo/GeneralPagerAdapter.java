package com.ed.v1.ui.userinfo;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class GeneralPagerAdapter extends PagerAdapter{
	
	private List<View> mListViews; 
	
	
	public GeneralPagerAdapter(){
		
	}
	
	
	public void setDataSource(List<View> list){
		this.mListViews = list;
		this.notifyDataSetChanged();
	}
	
	
	
	public void addView(View v){
		
		if(mListViews == null){
			mListViews = new ArrayList<View>();
		}
		mListViews.add(v);
		
		this.notifyDataSetChanged();
	}
	
	
	public View getView(int index){
		
		return mListViews.get(index);
	}
	
	public void clear(){
		
		if(mListViews != null){
			mListViews.clear();
		}
		
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mListViews ==null?0:mListViews.size();
	}
	
	
	@Override  
	public void destroyItem(ViewGroup container, int position, Object object){   
		
	    container.removeView(mListViews.get(position));  
	}  

	
	 @Override  
	 public Object instantiateItem(ViewGroup container, int position) {  
		 
	     container.addView(mListViews.get(position), 0);  
	     return mListViews.get(position);  
	 }  

	 

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

}
