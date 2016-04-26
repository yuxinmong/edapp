package com.ed.v1.ui.home.fragment;

import com.activeandroid.Configuration;

import android.view.View;
import android.support.v4.view.ViewPager;  
public class FlipPagerTransformer implements ViewPager.PageTransformer  
{  
	private float mDensity;
	FlipPagerTransformer(){
		
	}
	@Override
	public void transformPage(View page, float position) {
        if(position <= 0 && position >= -1){
            page.setPivotX(page.getMeasuredWidth());
        }else if(position <= 1 && position >= -1){
            page.setPivotX(0);
        }
        page.setPivotY(page.getMeasuredHeight() * 0.5f);
        if(mDensity <= 1.5f){
            page.setRotationY(position * 90f);

        }else if(1.5f < mDensity && mDensity <= 2.0f){
            page.setRotationY(position * 75f);
        }else if(2.0f < mDensity){
            page.setRotationY(position * 60f);
        }
    }
	
	
	
}  