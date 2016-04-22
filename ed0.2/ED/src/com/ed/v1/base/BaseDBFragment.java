package com.ed.v1.base;

import android.support.v4.app.Fragment;

import com.ed.v1.database.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public abstract class BaseDBFragment extends Fragment {

	private DatabaseHelper mydbHelper = null;

	public DatabaseHelper getHelper() {
		if (mydbHelper == null) {
			mydbHelper = OpenHelperManager.getHelper(this.getActivity(),
					DatabaseHelper.class);
		}
		return mydbHelper;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();

		if (mydbHelper != null) {
			OpenHelperManager.releaseHelper();
			mydbHelper = null;
		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}
