package com.ed.v1.common.widget;

import com.ed.v1.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CheckedImageView extends ImageView {
	private Boolean Liked=false;
	
	public Boolean getLiked() {
		return Liked;
	}

	public void setLiked(Boolean liked) {
		this.Liked = liked;
	}

	public CheckedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	


	public boolean isLike() {
		// TODO Auto-generated method stub
		return Liked;
	}

}
