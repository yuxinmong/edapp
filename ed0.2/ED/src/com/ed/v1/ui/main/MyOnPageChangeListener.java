package com.ed.v1.ui.main;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;

public class MyOnPageChangeListener implements OnPageChangeListener {
	private int endPosition;
	private int beginPosition;
	private int currentFragmentIndex;
	private boolean isEnd;
	private int item_width;
	private HorizontalScrollView mHorizontalScrollView;
	private View mImageView;
	private ViewPager pager;
	public MyOnPageChangeListener(int item_width,HorizontalScrollView mHorizontalScrollView,View mImageView,ViewPager pager){
		super();
		this.item_width=item_width;
		this.mHorizontalScrollView=mHorizontalScrollView;
		this.mImageView=mImageView;
		this.pager=pager;
		
	}
	@Override
	public void onPageSelected(final int position) {
		Animation animation = new TranslateAnimation(endPosition, position
				* item_width, 0, 0);

		beginPosition = position * item_width;

		currentFragmentIndex = position;
		if (animation != null) {
			animation.setFillAfter(true);
			animation.setDuration(0);
			mImageView.startAnimation(animation);
			mHorizontalScrollView.smoothScrollTo((currentFragmentIndex - 1)
					* item_width, 0);
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		if (!isEnd) {
			if (currentFragmentIndex == position) {
				endPosition = item_width * currentFragmentIndex
						+ (int) (item_width * positionOffset);
			}
			if (currentFragmentIndex == position + 1) {
				endPosition = item_width * currentFragmentIndex
						- (int) (item_width * (1 - positionOffset));
			}

			Animation mAnimation = new TranslateAnimation(beginPosition,
					endPosition, 0, 0);
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
				animation = new TranslateAnimation(endPosition,
						currentFragmentIndex * item_width, 0, 0);
				animation.setFillAfter(true);
				animation.setDuration(1);
				mImageView.startAnimation(animation);
				mHorizontalScrollView.invalidate();
				endPosition = currentFragmentIndex * item_width;
			}
		}
	}
}
