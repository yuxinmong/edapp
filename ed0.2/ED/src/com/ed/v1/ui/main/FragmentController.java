package com.ed.v1.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.ui.buyer.BuyerShowFragment;
import com.ed.v1.ui.home.fragment.HomeFragment;


public class FragmentController {

//	private FragmentManager manager;
	private static FragmentController controller;
	private Fragment currentFragment;
	private FragmentTransaction transaction;

	private FragmentController() {
//		this.manager = manager;
	}

	public static FragmentController getInstance() {
		if (controller == null) {
			controller = new FragmentController();
		}
		return controller;
	}

	public void showFragmentByTag(FragmentManager manager, String tag) {
		if (TextUtils.isEmpty(tag)) {
			return;
		}
		if (tag.equals("home")) {
			Fragment f = manager.findFragmentByTag(tag);
			transaction = manager.beginTransaction();
			if (f == null) {
				f = new HomeFragment();
				transaction.add(R.id.content_layout, f, "home");
			}
			transaction.show(f);
			if (currentFragment != null) {
				transaction.hide(currentFragment);
			}
			transaction.commitAllowingStateLoss();
			currentFragment = f;
		} else if (tag.equals("show")) {
			Fragment f = manager.findFragmentByTag(tag);
			transaction = manager.beginTransaction();
			if (f == null) {
				f = new BuyerShowFragment();
				transaction.add(R.id.content_layout, f, "show");
			}
			transaction.show(f);
			if (currentFragment != null) {
				transaction.hide(currentFragment);
			}
			transaction.commitAllowingStateLoss();
			currentFragment = f;
		} else if (tag.equals("my")) {
			Fragment f = manager.findFragmentByTag(tag);
			transaction = manager.beginTransaction();
			if (f == null) {
				f = new MyFragment();
				transaction.add(R.id.content_layout, f, "my");
			}
			transaction.show(f);
			if (currentFragment != null) {
				transaction.hide(currentFragment);
			}
			transaction.commitAllowingStateLoss();
			currentFragment = f;
		} 
	}

	public void clear() {
//		manager = null;
		transaction = null;
		currentFragment = null;
		controller = null;
	}
}
