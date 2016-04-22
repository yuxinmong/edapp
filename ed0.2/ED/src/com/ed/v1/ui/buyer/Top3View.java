package com.ed.v1.ui.buyer;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ed.v1.R;
import com.ed.v1.ui.main.HomeTabActivity;
import com.ed.v1.ui.main.MyFragmentPagerAdapter;

public class Top3View extends LinearLayout implements OnPageChangeListener,OnClickListener{
    private ViewPager viewpager;
	private ImageView left;
	private ImageView right;
	private int imageSize;
	private Context context;
	private Fragment fragment;
	private boolean bn_left;
	private boolean bn_right;

	public Top3View(Context context, AttributeSet attrs,String data,Fragment fragment) {
        super(context, attrs);
        this.context=context;
        this.fragment=fragment;
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.top3_view, this, true);
        viewpager = (ViewPager) v.findViewById(R.id.viewpager);
        left = (ImageView) v.findViewById(R.id.left);
        left.setOnClickListener(this);

        right = (ImageView) v.findViewById(R.id.right);
        right.setOnClickListener(this);
        initViewpager( );
        
    }

	private void initViewpager( ) {
		// TODO Auto-generated method stub
		// 1.设置幕后item的缓存数目
		viewpager.setOffscreenPageLimit(3);
		ArrayList<View> views = new ArrayList<View>();
		String []imageurl={"https://img.alicdn.com/imgextra/i3/356060330/TB2f31mkpXXXXcaXXXXXXXXXXXX-356060330.jpg",
				"https://img.alicdn.com/imgextra/i3/356060330/TB2J8nokVXXXXakXpXXXXXXXXXX-356060330.jpg",
				"https://img.alicdn.com/imgextra/i4/94008339/TB2dOuvlFXXXXXNXpXXXXXXXXXX_!!94008339.jpg"};
	
		Top3Fragment mageView4=new Top3Fragment(context,imageurl[2],3);
		views.add(mageView4);
		int number_top=1;
		for (String id : imageurl) {
			Top3Fragment mImageView = new Top3Fragment(context,id,number_top);
			// 显示图片的配置
			views.add(mImageView);
			number_top++;
		}
		Top3Fragment mageView0=new Top3Fragment(context,imageurl[0],1);
		views.add(mageView0);
	
		imageSize=views.size();
		viewpager.setAdapter(new CommomPagerAdatper(views));
		viewpager.setOnPageChangeListener(this);
		viewpager.setCurrentItem(1);// 初始化时设置显示第一页（ViewPager中索引为1）
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		changepage();
	}
	private void changepage() {
		// TODO Auto-generated method stub
		int current=viewpager.getCurrentItem();
		if(current==0)
		{
			viewpager.setCurrentItem(imageSize - 2, false);
		}
		else if(current==imageSize-1){
			viewpager.setCurrentItem(1, false); 

		}
		
	}
	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
	private void changepageleftOrright() {
		// TODO Auto-generated method stub
		int current = viewpager.getCurrentItem();
		if (current == 0) {
			viewpager.setCurrentItem(imageSize - 2, false);
		} else if (current == imageSize - 1) {
			viewpager.setCurrentItem(1, false);

		}

		else if (bn_left) {
			viewpager.setCurrentItem(current - 1, false);
			bn_left = false;
		} else if (bn_right) {
			viewpager.setCurrentItem(current + 1, false);
			bn_right = false;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.left:
			bn_left = true;
			changepageleftOrright();
			break;
		case R.id.right:
			bn_right = true;
			changepageleftOrright();
			break;

		default:
			break;
		}
	}
}
