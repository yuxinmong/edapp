package com.ed.v1.ui.buyer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragment;
import com.ed.v1.common.CommonUtils;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.common.widget.MyToast;
import com.ed.v1.ui.home.fragment.ListViewForScrollView;
import com.ed.v1.ui.home.fragment.ShowAdapter;
import com.ed.v1.ui.main.HomeTabActivity;
import com.ed.v1.util.CommonUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

public class Top3Fragment extends LinearLayout implements OnClickListener {

	private int top;
	ImageView image_show;
	ImageView text_top1;
	ImageView text_top2;
	ImageView text_top3;
	TextView lover_text;
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.user_l).cacheInMemory(true)
			.cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

	private Drawable lover_able;
	private Drawable lover_disable;
	private int ableColor;
	private int disableColor;
	private int number_top;
	private String src;
	private Context context;
	public Top3Fragment(Context context, String src, int number_top) {
		super(context);
		this.context = context;
		this.src = src;
		this.number_top = number_top;
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.fragment_top3, this, true);
		lover_text = (TextView) v.findViewById(R.id.lover_text);
		image_show = (ImageView) v.findViewById(R.id.image_show);
		text_top1 = (ImageView) v.findViewById(R.id.text_top1);
		text_top2 = (ImageView) v.findViewById(R.id.text_top2);
		text_top3 = (ImageView) v.findViewById(R.id.text_top3);
		initLove();
		initTopNumber();
		ImageLoader.getInstance().displayImage(src, image_show, options);
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
					MyToast.showCustomerToastShot(HomeTabActivity.homeUI, "已收藏");

				} else {
					v.setCompoundDrawables(null, null, null, lover_disable);
					v.setTextColor(disableColor);
					v.setTag("false");

				}

			}
		});

	}


	public void initLove() {
		// TODO Auto-generated method stub
		lover_able = context.getResources().getDrawable(R.drawable.lover_able);
		lover_able.setBounds(0, 0, lover_able.getMinimumWidth(),
				lover_able.getMinimumHeight());
		lover_disable = context.getResources().getDrawable(
				R.drawable.lover_disable);
		lover_disable.setBounds(0, 0, lover_able.getMinimumWidth(),
				lover_able.getMinimumHeight());
		disableColor = context.getResources().getColor(R.color.disable);
		ableColor = context.getResources().getColor(R.color.able);
	}

	private void initTopNumber() {
		// TODO Auto-generated method stub
		switch (number_top) {
		case 1:
			text_top1.setVisibility(View.VISIBLE);
			break;
		case 2:
			text_top2.setVisibility(View.VISIBLE);
			break;
		case 3:
			text_top3.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
