package com.ed.v1.ui.userinfo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.net.bean.OrderDetailBean;

public class OrderDetailAdapter extends BaseAdapter{
	
	private ArrayList<OrderDetailBean> dataArr = new ArrayList<OrderDetailBean>();
	private LayoutInflater inflater;
	
	public OrderDetailAdapter(Context c) {
		inflater = LayoutInflater.from(c);
	}
	
	public void addItem(OrderDetailBean bean){
		dataArr.add(bean);
		this.notifyDataSetChanged();
	}
	
	public void addItems(ArrayList<OrderDetailBean> arr){
		dataArr.addAll(arr);
		this.notifyDataSetChanged();
	}
	
	public void clear(){
		dataArr.clear();
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		
		return dataArr.size();
	}

	@Override
	public Object getItem(int position) {
		
		return dataArr.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		
		if(convertView == null){
			
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.grid_order_detail, null);
			holder.imgPhoto = (ImageView) convertView.findViewById(R.id.img_list_photo);
			holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_list_price);
			holder.tvLabel = (TextView) convertView.findViewById(R.id.tv_list_label);
			convertView.setTag(holder);
			
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		OrderDetailBean bean = dataArr.get(position);
		holder.imgPhoto.setImageResource(bean.getPhoto());
		holder.tvPrice.setText(bean.getPrice());
		holder.tvLabel.setText(bean.getLabel());
		
		return convertView;
	}
	
	class ViewHolder{
		
		private ImageView imgPhoto;
		private TextView tvPrice;
		private TextView tvLabel;
	}
	

}
