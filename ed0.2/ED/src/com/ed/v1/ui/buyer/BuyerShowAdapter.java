package com.ed.v1.ui.buyer;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.ui.main.HomeTabActivity;
import com.ed.v1.ui.main.MyFragmentPagerAdapter;
import com.ed.v1.ui.userinfo.LoadImageUtil;

public class BuyerShowAdapter extends
		RecyclerView.Adapter<RecyclerView.ViewHolder> implements
		View.OnClickListener {

	private int[] datas;
	static Context context;

	private OnRecyclerViewItemClickListener mOnItemClickListener = null;
	private static Fragment f;

	public BuyerShowAdapter(int[] datas, Fragment fragment) {
		this.datas = datas;
		this.f = fragment;
	}

	// define interface
	public static interface OnRecyclerViewItemClickListener {
		void onItemClick(View view, String data);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

		View view = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.items_show_single_img, viewGroup, false);
		view.setOnClickListener(this);

		return new BuyerShowView(view);

	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder masonryView,
			int position) {
	
	
			BuyerShowView holder = (BuyerShowView) masonryView;
			LoadImageUtil.loadImage(datas[position], holder.imageView);
			holder.imageView.setTag(datas[position]);
			return;

	}

	@Override
	public int getItemCount() {
		return datas.length;
	}

	public static class BuyerShowView extends RecyclerView.ViewHolder {

		ImageView imageView;

		public BuyerShowView(View itemView) {
			super(itemView);

			imageView = (ImageView) itemView.findViewById(R.id.img);

		}
	}

	@Override
	public int getItemViewType(int position) {
		if (position == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (mOnItemClickListener != null) {
			// 注意这里使用getTag方法获取数据
			mOnItemClickListener.onItemClick(v, (String) v.getTag());
		}

	}

	public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
		this.mOnItemClickListener = listener;
	}
}
