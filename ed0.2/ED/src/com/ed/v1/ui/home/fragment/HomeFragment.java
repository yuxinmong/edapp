package com.ed.v1.ui.home.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import com.android.volley.Request.Method;
import com.ed.v1.CLApplication;
import com.ed.v1.R;
import com.ed.v1.base.BaseFragment;
import com.ed.v1.base.BaseLazyLoadFragment;
import com.ed.v1.common.Constants;
import com.ed.v1.common.ToastUtil;
import com.ed.v1.common.dialog.CommonDialog;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.common.wheel.RecyclePageChangeListener;
import com.ed.v1.common.widget.CheckedImageView;
import com.ed.v1.common.widget.CircleImageView;
import com.ed.v1.common.widget.MyViewPager;
import com.ed.v1.datacenter.DataCenter;
import com.ed.v1.model.HomeNotice;
import com.ed.v1.model.NoticeListData;
import com.ed.v1.model.UserInfo;
import com.ed.v1.net.api.APIDelegate;
import com.ed.v1.net.api.APIRequest;
import com.ed.v1.net.api.APIResponse;
import com.ed.v1.net.api.ImageRequest;
import com.ed.v1.net.bean.RegisterContent;
import com.ed.v1.ui.main.ClothContentFragment;
import com.ed.v1.ui.main.HomeTabActivity;
import com.ed.v1.ui.main.MyBannerAdapter;
import com.ed.v1.ui.main.MyBannerListener;
import com.ed.v1.ui.main.MyFragmentPagerAdapter;
import com.ed.v1.ui.main.MyOnPageChangeListener;
import com.ed.v1.util.StringUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.tianshicoffeeom.app.imgscroll.MyImgScroll;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;

public class HomeFragment extends BaseFragment implements OnClickListener {

	private String[] menus = { "明星装", "田野装", "派对装", "亲子装", "运动装" };
	private HorizontalScrollView mHorizontalScrollView;
	private LinearLayout mLinearLayout;
	private WrapContentHeightViewPager pager;
	private ImageView mImageView;
	private int mScreenWidth;
	private int item_width;
	private ArrayList<Fragment> fragments;

	private List<ImageView> mImageList;

	/** 记录是否停止循环播放 */
	private boolean isStop = false;
	private PullToRefreshScrollView mPullRefreshScrollView;
	private CheckedImageView lover;
	private MyImgScroll myPager;
	private LinearLayout ovalLayout;
	private ArrayList<View> bannerlistViews;
	private int mCurIndex = -1;
	/** 标志位，标志已经初始化完成 */
	private boolean isPrepared;
	/** 是否已被加载过一次，第二次就不再去请求数据了 */
	private boolean mHasLoadedOnce;

	private Object currentNav;
	private TextView currentTitle;

	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_home_new;
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		initGuidMenu();
		initBanner();
		initPullToRefresh();
		// 初始化viewPager
		initViewPager();
		
	}


	private void initPullToRefresh() {
		mPullRefreshScrollView = (PullToRefreshScrollView) findView(R.id.pull_refresh_list);
		mPullRefreshScrollView.getLoadingLayoutProxy()
				.setPullLabel("更新完毕");
		
		mPullRefreshScrollView.setMode(Mode.BOTH);
		mPullRefreshScrollView.setFocusable(true);
		mPullRefreshScrollView.setFocusableInTouchMode(true);
		
		//enableChildAutoScrollToBottom();
	
        
	}
private void enableChildAutoScrollToBottom() {
	  mPullRefreshScrollView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
	  mPullRefreshScrollView.setFocusable(false);
	  mPullRefreshScrollView.setFocusableInTouchMode(false);
	  mPullRefreshScrollView.setOnTouchListener(null);
  }

	private void initGuidMenu() {
		// TODO Auto-generated method stub
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		mHorizontalScrollView = findView(R.id.hsv_view);
		mImageView = findView(R.id.img1);
		mLinearLayout = findView(R.id.menu_content);

		item_width = (int) ((mScreenWidth / 4.0 + 0.5f));
		mImageView.getLayoutParams().width = item_width;

		pager = findView(R.id.pager);
		// 初始化导航
		initNav();

	}

	private void initBanner() {

		myPager = (MyImgScroll) findView(R.id.myvp);
		ovalLayout = (LinearLayout) findView(R.id.vb);
		 initBannerDatas() ;
		 myPager.setFocusable(true);
		 myPager.setFocusableInTouchMode(true);
		 myPager.requestFocus(); 
	}

	private void initViewPager() {
		
		fragments = new ArrayList<Fragment>();
		for (int i = 0; i < menus.length; i++) {
			Bundle data = new Bundle();
			data.putString("text", (i + 1) + "");
			ClothContentFragment fragment = new ClothContentFragment();
			fragment.setArguments(data);
			fragments.add(fragment);
		}
		initViewpagerDatas();
	}

	private void initNav() {
		for (int i = 0; i < menus.length; i++) {
			RelativeLayout layout = new RelativeLayout(getActivity());
			TextView view = new TextView(getActivity());
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
		// mLinearLayout.getChildAt(0).setSelected(true);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// currentNav=(RelativeLayout) v;
		RelativeLayout layout=(RelativeLayout) v;
		View ss=layout.getChildAt(0);
		if(currentTitle!=null){
			currentTitle.setSelected(false);
		}
		currentTitle=(TextView) ss;
		currentTitle.setSelected(true);
		pager.setCurrentItem((Integer) v.getTag(),false);
	}

	@Override
	public void onResume() {
		myPager.startTimer();
		super.onResume();
	}

	@Override
	public void onStop() {
		myPager.stopTimer();
		super.onStop();
	}
	

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

		//initBannerDatas();
		//initViewpagerDatas();
	}

	private void initViewpagerDatas() {
		// TODO Auto-generated method stub
		MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(
				getChildFragmentManager(), fragments);
		fragmentPagerAdapter.setFragments(fragments);
		float mDensity = getResources().getDisplayMetrics().density;
		pager.setAdapter(fragmentPagerAdapter);
		pager.setOnPageChangeListener(new MyOnPageChangeListener(item_width,
				mHorizontalScrollView, mImageView, pager));
		pager.setCurrentItem(0,true);

	}

	private void initBannerDatas() {
		// TODO Auto-generated method stub
		bannerlistViews = new ArrayList<View>();
		int[] imageResId = new int[] { R.drawable.banner, R.drawable.banner,
				R.drawable.item1 };
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(getActivity());
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {// 设置图片点击事件

				}
			});

			String drawableUrl = Scheme.DRAWABLE.wrap(imageResId[i] + "");

			ImageLoader.getInstance().displayImage(drawableUrl, imageView,
					CLApplication.getInstance().options);
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			bannerlistViews.add(imageView);
		}
		// 开始滚动
		myPager.start(getActivity(), bannerlistViews, 4000, ovalLayout,
				R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.dot_disable, R.drawable.dot_able);

	}

}
