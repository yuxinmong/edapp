package com.ed.v1.ui.buyer;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CirculatePagerAdapter extends PagerAdapter {  
    
  private ArrayList<ImageView> imageViews;  
  private int size;  
  private Context context;  
    
    
  public CirculatePagerAdapter(Context context, ArrayList<ImageView> mImageList) {  
      this.context = context;  
      this.imageViews = mImageList;  
      size = mImageList.size();  
  }  

  @Override  
  public int getCount() {  
      return size; 
  }  

  @Override  
  public boolean isViewFromObject(View arg0, Object arg1) {  
      return arg0 == arg1;  
  }  
    
  @Override  
  public void destroyItem(ViewGroup container, int position, Object object) {  
      ((ViewPager) container).removeView((View) object);// 完全溢出view,避免数据多时出现重复现象  
  }  
    
  @Override  
  public Object instantiateItem(ViewGroup container, int position) {  
      container.addView((ImageView)imageViews.get(position), 0);  
      return (ImageView)imageViews.get(position);  
  }  
  /* 
   * 这个方法可用来异步获取网路图片时，更新viewpager中的图片（此处没用，使用时需格式化一下代码） public void refresh() 
   * { if(imageViews[size - 2].getDrawable() != null && 
   * imageViews[0].getDrawable() == null) { ImageView imageView = new 
   * ImageView(context); imageView.setBackgroundDrawable(imageViews[size - 
   * 2].getDrawable()); imageViews[0] = imageView; } 
   * if(imageViews[1].getDrawable() != null && imageViews[size - 
   * 1].getDrawable() == null) { ImageView imageView = new ImageView(context); 
   * imageView.setBackgroundDrawable(imageViews[1].getDrawable()); 
   * imageViews[size - 1] = imageView; } notifyDataSetChanged(); } 
   */  

}  


