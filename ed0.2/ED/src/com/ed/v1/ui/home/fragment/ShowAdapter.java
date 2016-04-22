package com.ed.v1.ui.home.fragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.toolbox.ImageRequest;
import com.ed.v1.CLApplication;
import com.ed.v1.R;
import com.ed.v1.common.wheel.RecyclePageChangeListener;
import com.ed.v1.common.widget.MyToast;
import com.ed.v1.common.widget.MyViewPager;
import com.ed.v1.ui.buyer.Top3Fragment;
import com.ed.v1.ui.main.HomeTabActivity;
import com.ed.v1.ui.main.MyBannerAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.tianshicoffeeom.app.imgscroll.MyImgScroll;

public class ShowAdapter extends BaseAdapter {

	private Context context;
	private String[] items;
	private Drawable lover_able;
	private Drawable lover_disable;
	private int ableColor;
	private int disableColor;

	public ShowAdapter(Context ctx, String[] items) {
		context = ctx;
		this.items = items;
		initLove();

	}

	public void initLove() {
		// TODO Auto-generated method stub
		lover_able = context.getResources().getDrawable(R.drawable.lover_able);
		lover_able.setBounds(0, 0, lover_able.getMinimumWidth(),
				lover_able.getMinimumHeight());
		lover_disable = context.getResources().getDrawable(
				R.drawable.lover_disable);
		lover_disable.setBounds(0, 0, lover_disable.getMinimumWidth(),
				lover_disable.getMinimumHeight());
		disableColor = context.getResources().getColor(R.color.disable);
		ableColor = context.getResources().getColor(R.color.able);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated metho ImageView img = (ImageViewd stub
		View contentview = convertView;
		ViewHolder holder;
	
		int[] imageIds;
		if (contentview == null) {
			 holder = new ViewHolder();
			contentview = LayoutInflater.from(context).inflate(
					R.layout.item_show, null);
			holder.image_show_lv= (RelativeLayout) contentview
					.findViewById(R.id.image_show_lv);
			
			holder.info = (TextView) contentview
					.findViewById(R.id.good_information);
			holder.item_image_show = (ViewPager) contentview
					.findViewById(R.id.item_image_show);
			holder.lovertest = (TextView) contentview
					.findViewById(R.id.lover_text);
			holder.video_view_lv=(FrameLayout) contentview.findViewById(R.id.video_view_lv);
			holder.videoView = (VideoView) contentview.findViewById(R.id.videoView);
			contentview.setTag(holder);
	
		

		}else{
			holder = (ViewHolder) convertView.getTag();

		}
		//区分是图片还是视频item
		if (!items[position].endsWith("3")) {
		
				showImageOrVideo("image",holder);
		
			ArrayList<ImageView> mImageList;
			

				mImageList = new ArrayList<ImageView>();
				 imageIds = new int[] { R.drawable.item5, R.drawable.item4};
			
			
			ImageView mImageView4 = new ImageView(context);
			String drawableUrl4 = Scheme.DRAWABLE.wrap(imageIds[1] + "");
			mImageView4.setScaleType(ImageView.ScaleType.CENTER_CROP);
			ImageLoader.getInstance().displayImage(drawableUrl4, mImageView4,
					CLApplication.getInstance().options);
			mImageList.add(mImageView4);

			// 初始化广告条资源
			for (int id : imageIds) {
				ImageView mImageView = new ImageView(context);
				String drawableUrl = Scheme.DRAWABLE.wrap(id + "");
				// 显示图片的配置
				mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				ImageLoader.getInstance().displayImage(drawableUrl, mImageView,
						CLApplication.getInstance().options);
				mImageList.add(mImageView);
			}
			ImageView mImageView1 = new ImageView(context);
			String drawableUrl1 = Scheme.DRAWABLE.wrap(imageIds[0] + "");
			mImageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
			ImageLoader.getInstance().displayImage(drawableUrl1, mImageView1,
					CLApplication.getInstance().options);
			mImageList.add(mImageView1);
			
			/*holder.item_image_show.setOffscreenPageLimit(2);// 缓存两页，此处必须设置
			holder.item_image_show.setPageMargin(0);*/
			holder.item_image_show.setCurrentItem(0,false);
			RecyclePageChangeListener listener=new RecyclePageChangeListener(holder.item_image_show,mImageList.size());
			listener.setFirst(true);
			holder.item_image_show.setOnPageChangeListener(listener);
			holder.item_image_show.setPageTransformer(true,
					new FlipPagerTransformer());// 设置3D翻转 效果
			holder.item_image_show.setAdapter(new MyBannerAdapter(mImageList));
			holder.lovertest.setTextColor(disableColor);
			holder.lovertest.setCompoundDrawables(null, null, null,
					lover_disable);
			holder.lovertest.setTag("false");

			holder.lovertest.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg) {
					// TODO Auto-generated method stub
					TextView v = (TextView) arg;
					if (v.getTag().equals("false")) {

						v.setCompoundDrawables(null, null, null, lover_able);
						v.setTextColor(ableColor);
						v.setTag("true");
						MyToast.showCustomerToastShot(HomeTabActivity.homeUI,
								"已收藏");
					} else {
						v.setCompoundDrawables(null, null, null, lover_disable);
						v.setTextColor(disableColor);

						v.setTag("false");

					}

				}
			});

		} else {
			
			showImageOrVideo("video",holder);
			//holder.videoView.pause();
		}
		return contentview;
	}

	private void showImageOrVideo(String tag, ViewHolder holder) {
		// TODO Auto-generated method stub
		if (tag.endsWith("image")) {
			holder.video_view_lv.setVisibility(View.GONE);
			holder.image_show_lv.setVisibility(View.VISIBLE);

		} else {
			holder.image_show_lv.setVisibility(View.GONE);
			holder.video_view_lv.setVisibility(View.VISIBLE);

		}
	}

	class ViewHolder {
		FrameLayout video_view_lv;
		RelativeLayout image_show_lv;
		TextView info;
		ViewPager item_image_show;
		TextView lovertest;
		VideoView videoView;
	}

	
}
