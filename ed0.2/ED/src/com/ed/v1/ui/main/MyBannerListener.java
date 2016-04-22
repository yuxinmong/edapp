package com.ed.v1.ui.main;

import java.util.List;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyBannerListener implements OnPageChangeListener {
	private List<ImageView> mImageList;
	private int previousPointEnale = 0;
	private LinearLayout llPointGroup;
	 public MyBannerListener(LinearLayout llPointGroup,List<ImageView> mImageList){
		 super();
		 this.mImageList=mImageList;
		 this.llPointGroup=llPointGroup;
	 }
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// 获取新的位置
		int newPosition;
		int s = arg0 % mImageList.size();
		if(s>3){
			newPosition=s/3;
		}else{
			newPosition=s;
		}
		// 设置广告标语
		// 消除上一次的状态点
		llPointGroup.getChildAt(previousPointEnale).setEnabled(false);
		// 设置当前的状态点“点”
		llPointGroup.getChildAt(newPosition).setEnabled(true);
		// 记录位置
		previousPointEnale = newPosition;
	}

}
