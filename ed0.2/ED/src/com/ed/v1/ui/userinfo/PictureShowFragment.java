package com.ed.v1.ui.userinfo;

import android.os.Bundle;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragment;
import com.ed.v1.common.viewholder.Res;

public class PictureShowFragment extends BaseFragment{
         String data;
         
	public PictureShowFragment(String data) {
		// TODO Auto-generated constructor stub
		this.data=data;
	}
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.item_picture_show;
	}
	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		
	}

}
