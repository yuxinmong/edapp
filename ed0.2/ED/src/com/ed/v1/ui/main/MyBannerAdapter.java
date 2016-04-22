package com.ed.v1.ui.main;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyBannerAdapter  extends PagerAdapter {

	private List<ImageView> mImageList;
	public MyBannerAdapter(List<ImageView> mImageList){
		super();
		this.mImageList=mImageList;
	}
	@Override
	public int getCount() {
		// 将viewpager页数设置成Integer.MAX_VALUE，可以模拟无限循环
		return mImageList.size();
	}

	/**
	 * 复用对象 true 复用view false 复用的是Object
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	/**
	 * 销毁对象
	 * 
	 * @param position
	 *            被销毁对象的索引位置
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mImageList.get(position % mImageList.size()));
	}

	/**
	 * 初始化一个对象
	 * 
	 * @param position
	 *            初始化对象的索引位置
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
			container.addView(mImageList.get(position % mImageList.size()));
		
		return mImageList.get(position % mImageList.size());
	}

}

