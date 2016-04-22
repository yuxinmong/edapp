package com.ed.v1.common.wheel;

import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class RecyclePageChangeListener implements OnPageChangeListener {

	private ViewPager mviewpager;
	private int imageSize;
	private boolean isFirst;

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public RecyclePageChangeListener(ViewPager mviewpager, int imageSize) {
		this.mviewpager = mviewpager;
		this.imageSize = imageSize;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		if (this.isFirst()) {
			changepage();
			this.setFirst(false);
		} else {
			delaychangepage();
		}

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

	private void changepage() {
		// TODO Auto-generated method stub
		int current = mviewpager.getCurrentItem();
		if (current == 0) {
			mviewpager.setCurrentItem(imageSize - 2);
		} else if (current == imageSize - 1) {
			mviewpager.setCurrentItem(1);

		}

	}

	private void delaychangepage() {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				// execute the task
				int current = mviewpager.getCurrentItem();
				if (current == 0) {
					mviewpager.setCurrentItem(imageSize - 2,false);
				}

				else if (current == imageSize - 1) {
					mviewpager.setCurrentItem(1,false);

				}
			}
		}, 1500);
	}
}
