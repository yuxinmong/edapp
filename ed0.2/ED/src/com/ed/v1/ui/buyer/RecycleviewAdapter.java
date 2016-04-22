package com.ed.v1.ui.buyer;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.ui.buyer.BuyerShowAdapter.BuyerShowView;
import com.ed.v1.ui.userinfo.LoadImageUtil;

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.Recycleview> implements View.OnClickListener{
	 
		private int[] datas;
		Context context;
		private int root;
		public RecycleviewAdapter(Context  context,int[] datas,int root) {
	        this.datas=datas;
	        this.root=root;
	        this.context=context;
	    }
		private OnRecyclerViewItemClickListener mOnItemClickListener = null;
	     
	    //define interface
	    public static interface OnRecyclerViewItemClickListener {
	        void onItemClick(View view , String data);
	    }
	    @Override
	    public Recycleview onCreateViewHolder(ViewGroup viewGroup, int i) {
	        View view= LayoutInflater.from(viewGroup.getContext()).inflate(root, viewGroup, false);
	        view.setOnClickListener(this);
	        return new Recycleview(view);
	        
	        
	        
	      
	        
	    }

	    @Override
	    public void onBindViewHolder(Recycleview masonryView, int position) {
	    	if(position+1==getItemCount()){
	    		
	    	}
	    	else{ 
	    		LoadImageUtil.loadImage(datas[position], masonryView.imageView);
	    		 //将数据保存在itemView的Tag中，以便点击时进行获取
	    		masonryView.imageView.setTag(datas[position]);
	    	}
	    }

	    @Override
	    public int getItemCount() {
	        return datas.length;
	    }

	    
	    public static class Recycleview extends  RecyclerView.ViewHolder{

	        ImageView imageView;

	       public Recycleview(View itemView){
	           super(itemView);
	           imageView= (ImageView) itemView.findViewById(R.id.img );
	           
	       }

	    }
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (mOnItemClickListener != null) {
	            //注意这里使用getTag方法获取数据
	            mOnItemClickListener.onItemClick(v,(String)v.getTag());
	        }
		}
		 public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
		        this.mOnItemClickListener = listener;
		    }
	}