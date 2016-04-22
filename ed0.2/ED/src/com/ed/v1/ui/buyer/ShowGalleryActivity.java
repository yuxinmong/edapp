package com.ed.v1.ui.buyer;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.BaseBundle;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.SystemStatusManager;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.common.widget.MyToast;
import com.ed.v1.ui.main.HomeTabActivity;
import com.ed.v1.ui.userinfo.LoadImageUtil;
import com.ed.v1.ui.userinfo.UserInfoActivity;
import com.ed.v1.util.CommonUtil;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

public class ShowGalleryActivity extends BaseFragmentActivity implements
		OnClickListener {
	private List<View> lstView;
	@Res(R.id.id_content)
	ViewPager viewPager;
	private ViewPagerAdapter adapter;
	private int CurrentPageIndex;
	private int maxNum = 10;
	private View view;
	private ArrayList<String> ids;
	private Drawable lover_able;
	private Drawable lover_disable;
	private int ableColor;
	private int disableColor;
	private TextView lover_text;

	@Res(R.id.slightly_thumbnail1)
	ImageView slightly_thumbnail1;
	@Res(R.id.slightly_thumbnail2)
	ImageView slightly_thumbnail2;
	@Res(R.id.slightly_thumbnail3)
	ImageView slightly_thumbnail3;
	@Res(R.id.slightly_thumbnail4)
	ImageView slightly_thumbnail4;
	@Res(R.id.slightly_thumbnail5)
	ImageView slightly_thumbnail5;
	// 记录加载第几次loadmore
	private int count;
	
	private View currentSlightly_thumbnail;
	// 是否刚刚加载了新数据
	private boolean isUpdate = false;
	//判断viewpager是否被触摸,默认为touch,只有当点击第5个略缩图时才为false;
	private boolean isTouch=true;
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.item_buyer_show;
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		CommonUtil.setTranslucentStatus(this);
		initSlightly_thumbnai();
		initViewPager();
	}

	private void initViewPager() {
		// TODO Auto-generated method stub
		adapter = new ViewPagerAdapter();
		initDatas();
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new OnPagerChangeListener());
		viewPager.setCurrentItem(0);
		viewPager.setOnTouchListener(new OnTouchListener() {
			
		

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				isTouch=true;
				return false;
			}
		});
	}

	private void setCurrentItem(int i) {
		// TODO Auto-generated method stub
		int ss = (i + 1) % 5;
		if(ss==0){
			ss=5;
		}
		changeLittleImg(ss);
		viewPager.setCurrentItem(i, false);

	}

	private void changeLittleImg(int ss) {
		// TODO Auto-generated method stub
		currentSlightly_thumbnail.setSelected(false);
		switch (ss) {
		case 1:
			slightly_thumbnail1.setSelected(true);
			currentSlightly_thumbnail = slightly_thumbnail1;
			break;
		case 2:
			slightly_thumbnail2.setSelected(true);
			currentSlightly_thumbnail = slightly_thumbnail2;
			break;
		case 3:
			slightly_thumbnail3.setSelected(true);
			currentSlightly_thumbnail = slightly_thumbnail3;
			break;
		case 4:
			slightly_thumbnail4.setSelected(true);
			currentSlightly_thumbnail = slightly_thumbnail4;
			break;
		case 5:
			slightly_thumbnail5.setSelected(true);
			currentSlightly_thumbnail = slightly_thumbnail5;
			break;
		default:
			break;
		}
	}

	private void initDatas() {
		// TODO Auto-generated method stub
		lstView = new ArrayList<View>();
		ids = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			String ss = Scheme.DRAWABLE.wrap(R.drawable.top1 + "");
			ids.add(ss);
		}
		/*
		 * ids. { R.drawable.top1, R.drawable.top2, R.drawable.top3,
		 * R.drawable.top1, R.drawable.top2 };
		 */
		// initSlightly_thumbnai();
		initSlightly_thumbnaiDatas();
		for (String id : ids) {
			view = LayoutInflater.from(this).inflate(R.layout.item_show_large,
					null);
			ImageView imagView = (ImageView) view.findViewById(R.id.img);
            imagView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			LoadImageUtil.loadImage(id, imagView);
			initLove(view.findViewById(R.id.lover_text));
			lstView.add(view);

		}
	}

	private void initSlightly_thumbnai() {
		// TODO Auto-generated method stub

		slightly_thumbnail1.setOnClickListener(this);
		slightly_thumbnail2.setOnClickListener(this);
		slightly_thumbnail3.setOnClickListener(this);
		slightly_thumbnail4.setOnClickListener(this);
		slightly_thumbnail5.setOnClickListener(this);
	}

	private void initSlightly_thumbnaiDatas() {
		currentSlightly_thumbnail = slightly_thumbnail1;
		currentSlightly_thumbnail.setSelected(true);
		CurrentPageIndex = viewPager.getCurrentItem();
		for (int i = CurrentPageIndex; i <= 5; i++) {

			LoadImageUtil.loadImage(ids.get(CurrentPageIndex),
					slightly_thumbnail1);
			LoadImageUtil.loadImage(ids.get(CurrentPageIndex + 1),
					slightly_thumbnail2);
			LoadImageUtil.loadImage(ids.get(CurrentPageIndex + 2),
					slightly_thumbnail3);
			LoadImageUtil.loadImage(ids.get(CurrentPageIndex + 3),
					slightly_thumbnail4);
			LoadImageUtil.loadImage(ids.get(CurrentPageIndex + 4),
					slightly_thumbnail5);
		}

	}

	private void chageSlightly_thumbnaiDatas() {
		if(isTouch){
			changeLittleImg(viewPager.getCurrentItem()%5+1);
			slightly_thumbnail4.setSelected(false);
			slightly_thumbnail3.setSelected(false);
			slightly_thumbnail2.setSelected(false);

		}
	

		int start = (viewPager.getCurrentItem() / 5) * 5;

		for (int i = 0; i <= 5; i++) {

			LoadImageUtil.loadImage(ids.get(start), slightly_thumbnail1);
			LoadImageUtil.loadImage(ids.get(start + 1), slightly_thumbnail2);
			LoadImageUtil.loadImage(ids.get(start + 2), slightly_thumbnail3);
			LoadImageUtil.loadImage(ids.get(start + 3), slightly_thumbnail4);
			LoadImageUtil.loadImage(ids.get(start + 4), slightly_thumbnail5);
		}

	}

	public void initLove(View view2) {
		// TODO Auto-generated method stub
		lover_text = (TextView) view2;
		lover_able = this.getResources().getDrawable(R.drawable.lover_able);
		lover_able.setBounds(0, 0, lover_able.getMinimumWidth(), lover_able.getMinimumHeight());
		lover_disable = this.getResources().getDrawable(
				R.drawable.lover_disable);
		lover_disable.setBounds(0, 0, lover_disable.getMinimumWidth(), lover_disable.getMinimumHeight());
		disableColor = this.getResources().getColor(R.color.disable);
		ableColor = this.getResources().getColor(R.color.able);
		lover_text.setTextColor(disableColor);
		lover_text.setCompoundDrawables(null, null, null, lover_disable);
		lover_text.setTag("false");
		lover_text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				TextView v = (TextView) arg0;
				if (v.getTag().equals("false")) {

					v.setCompoundDrawables(null, null, null, lover_able);
					v.setTextColor(ableColor);
					v.setTag("true");
					MyToast.showCustomerToastShot(ShowGalleryActivity.this,
							"已收藏");
				} else {
					v.setCompoundDrawables(null, null, null, lover_disable);
					v.setTextColor(disableColor);

					v.setTag("false");

				}

			}
		});
	}

	private void loadMore() {
		// TODO Auto-generated method stub
		count = count + 1;
		isUpdate = true;
		ArrayList<String> data = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			String ss = Scheme.DRAWABLE.wrap(R.drawable.top2 + "");
			data.add(ss);
		}
		for (String id : data) {

			view = LayoutInflater.from(this).inflate(R.layout.item_show_large,
					null);
			ImageView imagView = (ImageView) view.findViewById(R.id.img);
			imagView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			LoadImageUtil.loadImage(id, imagView);
			initLove(view.findViewById(R.id.lover_text));
			lstView.add(view);
		}

		ids.addAll(data);
		//adapter = new ViewPagerAdapter();
		//viewPager.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		CurrentPageIndex = count * 5 - 1;
		setCurrentItem(CurrentPageIndex);

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
			int CurrentPageIndex = viewPager.getCurrentItem();

		}

		@Override
		public void onPageSelected(int position) {
			 CurrentPageIndex = viewPager.getCurrentItem();
			 changeLittleImg(viewPager.getCurrentItem()%5+1);
			
			if(isTouch){
				if ((CurrentPageIndex) >= maxNum - 1) {
					// addView();
					return;

				}

				else if (CurrentPageIndex == lstView.size() - 1) {
					loadMore();
				}
				else if (CurrentPageIndex != 0) {

					if (CurrentPageIndex % 5 == 0
							|| (CurrentPageIndex + 1) % 5 == 0) {
						
						CurrentPageIndex = viewPager.getCurrentItem();
						chageSlightly_thumbnaiDatas();
						return;
					}
					
				}
					
				
			}

		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.slightly_thumbnail1:
			changeSelect(slightly_thumbnail1);
			gotoViewPager(0, slightly_thumbnail1);

			break;
		case R.id.slightly_thumbnail2:
			changeSelect(slightly_thumbnail2);

			gotoViewPager(1, slightly_thumbnail2);

			break;
		case R.id.slightly_thumbnail3:
			changeSelect(slightly_thumbnail3);

			gotoViewPager(2, slightly_thumbnail3);

			break;
		case R.id.slightly_thumbnail4:
			changeSelect(slightly_thumbnail4);

			gotoViewPager(3, slightly_thumbnail4);

			break;
		case R.id.slightly_thumbnail5:
			changeSelect(slightly_thumbnail5);
            isTouch=false;
			gotoViewPager(4, slightly_thumbnail5);

			break;

		default:
			break;
		}
	}

	private void gotoViewPager(int i, View v) {
		// TODO Auto-generated method stub
		// 计算第几次加载loadmore
		// changeSelect(v);
		count = viewPager.getCurrentItem() / 5;
		int page = i + count * 5;
		viewPager.setCurrentItem(page);
	}

	private void changeSelect(View v) {
		currentSlightly_thumbnail.setSelected(false);
		currentSlightly_thumbnail = v;
		currentSlightly_thumbnail.setSelected(true);
	}

	class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return lstView.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(lstView.get(position));
			return lstView.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(lstView.get(position));
		}
	}

	
}
