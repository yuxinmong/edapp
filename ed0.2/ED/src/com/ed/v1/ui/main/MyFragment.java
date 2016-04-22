package com.ed.v1.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragment;
import com.ed.v1.common.SystemStatusManager;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.common.widget.CheckedImageView;
import com.ed.v1.common.widget.CircleImageView;
import com.ed.v1.model.UserInfo;
import com.ed.v1.ui.userinfo.AdressActivity;
import com.ed.v1.ui.userinfo.PictureShowActivity;
import com.ed.v1.ui.userinfo.HeaderBgActivity;
import com.ed.v1.ui.userinfo.OrderActivity;
import com.ed.v1.ui.userinfo.ThreeDActivity;
import com.ed.v1.ui.userinfo.UserInfoActivity;
import com.ed.v1.ui.userinfo.UserLikeActivity;
import com.ed.v1.ui.userinfo.UserSetActivity;
import com.example.zhy_horizontalscrollview03.BuyerShowGalleryActivity;

public class MyFragment extends BaseFragment implements OnClickListener {
	@Res(R.id.user_tokens)
	TextView user_tokens;
	@Res(R.id.user_picture)
	TextView user_picture;
	@Res(R.id.user_like)
	TextView user_like;
	@Res(R.id.user_adress)
	TextView user_adress;
	@Res(R.id.user_settinglv)
	LinearLayout user_settinglv;
	@Res(R.id.user_header)
	CircleImageView user_header;
	@Res(R.id.menu_my_order)
	LinearLayout menu_my_order;
	@Res(R.id.header_bg)
	ImageView header_bg;
	@Res(R.id.user_appearance)
	TextView user_appearance;
	
	UserInfo userInfo = new UserInfo();

	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_my;
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
		user_tokens.setOnClickListener(this);
		user_settinglv.setOnClickListener(this);
		user_picture.setOnClickListener(this);
		user_like.setOnClickListener(this);
		user_adress.setOnClickListener(this);
		menu_my_order.setOnClickListener(this);
		header_bg.setOnClickListener(this);
		user_adress.setOnClickListener(this);
		user_appearance.setOnClickListener(this);
		// userInfo = DataCenter.getInstance().getUserInfo();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.user_picture:

			Intent intent = new Intent(MyFragment.this.getActivity(),
					PictureShowActivity.class);
			startActivity(intent);
			break;
		case R.id.user_settinglv:
			Intent intent1 = new Intent(MyFragment.this.getActivity(),
					UserSetActivity.class);
			startActivity(intent1);
			break;
		case R.id.menu_my_order:
			Intent intent2 = new Intent(MyFragment.this.getActivity(),
					OrderActivity.class);
			startActivity(intent2);
			break;
		case R.id.header_bg:
			Intent intent3 = new Intent(MyFragment.this.getActivity(),
					HeaderBgActivity.class);
			startActivity(intent3);
			break;
		case R.id.user_like:
			Intent intent4 = new Intent(MyFragment.this.getActivity(),
					UserLikeActivity.class);
			startActivity(intent4);
			break;
		case R.id.user_adress:
			Intent intent5 = new Intent(MyFragment.this.getActivity(),
					AdressActivity.class);
			startActivity(intent5);
			break;
		case R.id.user_appearance:
			Intent intent6 = new Intent(MyFragment.this.getActivity(),
					ThreeDActivity.class);
			startActivity(intent6);
			break;
		default:
			break;
		}
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}

	
   @Override
public void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
}
}
