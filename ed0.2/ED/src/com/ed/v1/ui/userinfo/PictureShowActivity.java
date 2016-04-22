package com.ed.v1.ui.userinfo;

import java.util.ArrayList;
import java.util.List;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.SystemStatusManager;
import com.ed.v1.common.viewholder.Res;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

public class PictureShowActivity extends BaseFragmentActivity implements
		OnClickListener {
	@Res(R.id.viewpager)
	ViewPager mviewpager;
    @Res(R.id.lv1)
    LinearLayout lv1;
    @Res(R.id.lv2)
    TextView lv2;
	private Object datas;
	ArrayList<Fragment> fragments;
	private int CurrentPageIndex;
	private int maxNum = 9;
	private int pagerNum = 3;

	private int count;
	private boolean isUpdate;
	private int[] imageIds;
	private FixedPagerAdapter pagerAdapter;
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_picture_show;
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		setTranslucentStatus();
		initViewpager();
        initOnclickListener();
	}

	private void initOnclickListener() {
		// TODO Auto-generated method stub
		lv1.setOnClickListener(this);
		lv2.setOnClickListener(this);
	}

	private void initDatas() {
           fragments = new ArrayList<Fragment>();

		
		 imageIds = new int[] { R.drawable.top1, R.drawable.top2,
				R.drawable.top3 };
		for(int id:imageIds)
		{
			String data=Scheme.DRAWABLE.wrap(id + "");
			PictureShowFragment pictureShow=new PictureShowFragment(data);
			fragments.add(pictureShow);
		}
		 pagerAdapter = new FixedPagerAdapter(this.getSupportFragmentManager());
		mviewpager.setAdapter(pagerAdapter);
		mviewpager.setCurrentItem(1);// 初始化时设置显示第一页（ViewPager中索引为1）
	}
	private void initViewpager() {
		// TODO Auto-generated method stub
		// 1.设置幕后item的缓存数目
		mviewpager.setOffscreenPageLimit(3);
		mviewpager.setPageMargin(40);
		mviewpager.setOnPageChangeListener(new OnPagerChangeListener());

		initDatas();
		
	
	
		
	}
	private void loadMore() {
		// TODO Auto-generated method stub
		count = count + 1;
		isUpdate = true;
	
		for(int id:imageIds)
		{
			String data=Scheme.DRAWABLE.wrap(id + "");
			PictureShowFragment pictureShow=new PictureShowFragment(data);
			fragments.add(pictureShow);
		}
	
		/*adapter = new ViewPagerAdapter();
		viewPager.setAdapter(adapter);*/
		pagerAdapter.notifyDataSetChanged();
		CurrentPageIndex = count * 3 - 1;
		mviewpager.setCurrentItem(CurrentPageIndex,false);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lv1:
			finish();
		case R.id.lv2:
			finish();
		default:
			break;
		}
	}

	private void setTranslucentStatus() {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window win = this.getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			winParams.flags |= bits;
			win.setAttributes(winParams);
		}
		SystemStatusManager tintManager = new SystemStatusManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.transparent);// 状态栏无背景
	}
	
	public class FixedPagerAdapter extends FragmentStatePagerAdapter {


	


	    /**
	     * 这个是在继承FragmentStatePagerAdapter会强制写入的
	     *
	     * @param fm
	     */
	    public FixedPagerAdapter(FragmentManager fm) {
	        super(fm);
	    }


	    @Override
	    public Fragment getItem(int position) {
	        return fragments.get(position);
	    }

	    /**
	     * Return the number of views available.
	     * 返回一个可以用的view的个数
	     *
	     * @return
	     */
	    @Override
	    public int getCount() {
	        return fragments.size();
	    }

	    /**
	     * Create the page for the given position. The adapter is responsible for
	     * adding the view to the container given here,
	     * although it only must ensure this is done by the time it returns from finishUpdate(ViewGroup).
	     * 这个同destroyItem（）相反，是对于给定的位置创建视图，适配器往container中添加
	     *
	     * @param container
	     * @param position
	     * @return
	     */
	    @Override
	    public Object instantiateItem(ViewGroup container, int position) {
	        Fragment fragment = null;
	        fragment = (Fragment) super.instantiateItem(container,position);
	        return fragment;
	    }



	    /**
	     * Remove a page for the given position. The adapter is responsible for
	     * removing the view from its container,
	     * although it only must ensure this is done by the time it returns from finishUpdate(View).
	     * 移除给定位置的数据，适配器负责从container（容器）中取出，但是这个必须保证是在finishUpdate（view）
	     * 返回的时间内完成
	     *
	     * @param container
	     * @param position
	     * @param object
	     */
	    @Override
	    public void destroyItem(ViewGroup container, int position, Object object) {
	        super.destroyItem(container, position, object);
	    }

	    /**
	     * 得到滑动页面的Title
	     *
	     * @param position
	     * @return
	     */
	  
	}

	/**
	 * ViewPager滑动监听事件
	 */
	class OnPagerChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int state) {

		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int apositionOffsetPixelsE) {
			int CurrentPageIndex = mviewpager.getCurrentItem();

		}

		@Override
		public void onPageSelected(int position) {
			 CurrentPageIndex = mviewpager.getCurrentItem();
			
			
			
				if ((CurrentPageIndex) >= maxNum - 1) {
					// addView();
					return;

				}

				else if (CurrentPageIndex == fragments.size() - 1) {
					loadMore();
				}
			
					
				
			}

		}

	

}
