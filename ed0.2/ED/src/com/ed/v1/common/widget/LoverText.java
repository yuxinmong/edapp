package com.ed.v1.common.widget;

import com.ed.v1.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class LoverText extends TextView {
	private Drawable lover_able;
	private Drawable lover_disable;
	private int ableColor;
	private int disableColor;
	private Context context;

//	@SuppressLint("NewApi")
//	public LoverText(Context context, AttributeSet attrs, int defStyleAttr,
//			int defStyleRes) {
//		super(context, attrs, defStyleAttr, defStyleRes);
//		initLove();
//		// TODO Auto-generated constructor stub
//	}

	public LoverText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initLove();
	}

	public LoverText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initLove();
	}

	public LoverText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		initLove();
	}

	public void initLove() {
		// TODO Auto-generated method stub
		lover_able = context.getResources().getDrawable(R.drawable.lover_able);
		lover_able.setBounds(0, 0, 80, 80);
		lover_disable = context.getResources().getDrawable(
				R.drawable.lover_disable);
		lover_disable.setBounds(0, 0, 80, 80);
		disableColor = context.getResources().getColor(R.color.disable);
		ableColor = context.getResources().getColor(R.color.able);
		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				TextView v = (TextView) arg0;
				if (v.getTag().equals("false")) {

					v.setCompoundDrawables(null, null, null, lover_able);
					v.setTextColor(ableColor);
					v.setTag("true");
				} else {
					v.setCompoundDrawables(null, null, null, lover_disable);
					v.setTextColor(disableColor);

					v.setTag("false");

				}

			}
		});
	}
}
