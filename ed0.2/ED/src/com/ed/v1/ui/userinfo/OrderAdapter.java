package com.ed.v1.ui.userinfo;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.ui.userinfo.express.ExpressActivity;

public class OrderAdapter extends BaseAdapter implements OnClickListener {
	private ArrayList<String> datas;
	private Context context;
	ViewHolder viewholder;

	OrderAdapter(Context context, ArrayList<String> datas) {
		this.context = context;
		this.datas = datas;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return datas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View conterview, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View contentview = conterview;
		if (contentview == null) {
			viewholder = new ViewHolder();
			contentview = LayoutInflater.from(context).inflate(
					R.layout.item_order, null);

			viewholder.check_ems = (TextView) contentview
					.findViewById(R.id.check_ems);
			viewholder.apply_back_cash = (TextView) contentview
					.findViewById(R.id.apply_back_cash);
			viewholder.price = (TextView) contentview.findViewById(R.id.price);
			viewholder.order_time = (TextView) contentview
					.findViewById(R.id.order_time);
			viewholder.order_code = (TextView) contentview
					.findViewById(R.id.order_code);
			viewholder.price1 = (TextView) contentview
					.findViewById(R.id.price1);
			viewholder.good_title = (TextView) contentview
					.findViewById(R.id.good_title);
			viewholder.goods_img = (ImageView) contentview
					.findViewById(R.id.goods_img);
			contentview.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) contentview.getTag();
		}
		viewholder.check_ems.setOnClickListener(this);
		viewholder.apply_back_cash.setOnClickListener(this);

		return contentview;
	}

	class ViewHolder {
		TextView check_ems;
		TextView apply_back_cash;
		TextView price;
		TextView order_time;
		TextView order_code;
		TextView price1;
		TextView good_title;
		ImageView goods_img;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.check_ems:
			Intent intent = new Intent(context, ExpressActivity.class);
			context.startActivity(intent);
			break;
		case R.id.apply_back_cash:
			Intent intent2 = new Intent(context, ApplyBackActivity.class);
			context.startActivity(intent2);
			break;
		default:
			break;
		}
	}
}
