package com.ed.v1.ui.userinfo;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.util.CommonUtil;


public class OrderActivity extends BaseFragmentActivity implements
		OnClickListener {
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	private String[] menus = { "全部订单", "待付款", "已付款", "已过期" };
	private Drawable order_finished;
	private Drawable order_unfinished;
	private HorizontalScrollView mHorizontalScrollView ;
	private LinearLayout mLinearLayout;
	private ViewPager pager;
	private ImageView mImageView;
	private int mScreenWidth;
	private int item_width;
	
	private int endPosition;
	private int beginPosition;
	private int currentFragmentIndex;
	private boolean isEnd;
	
	private ArrayList<Fragment> fragments;
	
	
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_my_order;
	}

	private void initTitile() {
		// TODO Auto-generated method stub
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("我的订单");
		mBtnBack.setOnClickListener(this);

	}
	
	
	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		CommonUtil.setTranslucentStatus(this);
		initTitile();
		initDom() ;
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv_view);
		mLinearLayout = (LinearLayout) findViewById(R.id.menu_content);
		mImageView = (ImageView) findViewById(R.id.img1);
		item_width = (int) ((mScreenWidth / 4.0 + 0.5f));
		mImageView.getLayoutParams().width = item_width;
		
		pager = (ViewPager) findViewById(R.id.pager);
		// 初始化导航
		initNav();
		// 初始化viewPager
		initViewPager();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btnBack:
			finish();
			break;
		
		default:
			pager.setCurrentItem((Integer) v.getTag(),false);
			break;
		}
	}

	private void initViewPager() {
		fragments = new ArrayList<Fragment>();
    	for (int i = 0; i < menus.length; i++) {
    		Bundle data = new Bundle();
    		data.putString("text", (i+1)+"");
    		OderListFragment fragment = new OderListFragment();
    		fragment.setArguments(data);
    		fragments.add(fragment);
		}
    	MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
		pager.setAdapter(fragmentPagerAdapter);
		fragmentPagerAdapter.setFragments(fragments);
		pager.setOnPageChangeListener(new MyOnPageChangeListener());
		pager.setCurrentItem(0);
	}
	
	private void initNav() {
		for (int i = 0; i < menus.length; i++) {
			RelativeLayout layout = new RelativeLayout(this);
			TextView view = new TextView(this);
			view.setText(menus[i]);
			view.setTextColor(R.drawable.bg_title);
		
			// view.setBackgroundResource(R.drawable.bg_nav);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			layout.addView(view, params);
			mLinearLayout.addView(layout, (int) (mScreenWidth / 4 + 0.5f), 50);
			layout.setOnClickListener(this);
			layout.setTag(i);
		}
	}
	public void initDom() {
		// TODO Auto-generated method stub
		order_finished = getResources().getDrawable(R.drawable.order_finished);
		order_finished.setBounds(0, 0, order_finished.getMinimumWidth(),
				order_finished.getMinimumHeight());
		order_unfinished = getResources().getDrawable(
				R.drawable.order_unfinished);
		order_unfinished.setBounds(0, 0, order_unfinished.getMinimumWidth(),
				order_unfinished.getMinimumHeight());

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> fragments; 
		private FragmentManager fm;

	    public MyFragmentPagerAdapter(FragmentManager fm) {
	        super(fm);
	    	this.fm = fm;
	    }

	    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
	        super(fm);
			this.fm = fm;
	        this.fragments = fragments;
	    }

	    @Override
	    public int getCount() {
	        return fragments.size();
	    }

	    @Override
	    public Fragment getItem(int position) {
	        return fragments.get(position);
	    }

	    @Override
	    public int getItemPosition(Object object) {
	        return POSITION_NONE;
	    }
	    
	    
		public void setFragments(ArrayList<Fragment> fragments) {
			if(this.fragments != null){
				FragmentTransaction ft = fm.beginTransaction();
				for(Fragment f:this.fragments){
					ft.remove(f);
				}
				ft.commit();
				ft=null;
				fm.executePendingTransactions();
			}
			this.fragments = fragments;
			notifyDataSetChanged();
		}
		
		
		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
		    Object obj = super.instantiateItem(container, position);
		    return obj;
		}
			
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener {

		
		@Override
		public void onPageSelected(final int position) {
			Animation animation = new TranslateAnimation(endPosition, position* item_width, 0, 0);
			
			beginPosition = position * item_width;
			
			currentFragmentIndex = position;
			if (animation != null) {
				animation.setFillAfter(true);
				animation.setDuration(0);
				mImageView.startAnimation(animation);
				mHorizontalScrollView.smoothScrollTo((currentFragmentIndex - 1) * item_width , 0);
			}
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			if(!isEnd){
				if(currentFragmentIndex == position){
					endPosition = item_width * currentFragmentIndex + 
							(int)(item_width * positionOffset);
				}
				if(currentFragmentIndex == position+1){
					endPosition = item_width * currentFragmentIndex - 
							(int)(item_width * (1-positionOffset));
				}
				
				Animation mAnimation = new TranslateAnimation(beginPosition, endPosition, 0, 0);
				mAnimation.setFillAfter(true);
				mAnimation.setDuration(0);
				mImageView.startAnimation(mAnimation);
				mHorizontalScrollView.invalidate();
				beginPosition = endPosition;
			}
		}
	
		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_DRAGGING) {
				isEnd = false;
			} else if (state == ViewPager.SCROLL_STATE_SETTLING) {
				isEnd = true;
				beginPosition = currentFragmentIndex * item_width;
				if (pager.getCurrentItem() == currentFragmentIndex) {
					// 未跳入下一个页面
					mImageView.clearAnimation();
					Animation animation = null;
					// 恢复位置
					animation = new TranslateAnimation(endPosition, currentFragmentIndex * item_width, 0, 0);
					animation.setFillAfter(true);
					animation.setDuration(1);
					mImageView.startAnimation(animation);
					mHorizontalScrollView.invalidate();
					endPosition = currentFragmentIndex * item_width;
				}
			}
		}

	}


}
