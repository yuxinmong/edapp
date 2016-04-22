package com.ed.v1.common.widget;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleLayout extends LinearLayout {

	public TextView[] textViews;// 标题栏数组，用于存储要显示的标题

	/**
	 * titles:即要显示的标题栏的数组
	 * 
	 * @param context
	 * @param titles
	 */
	public TitleLayout(Context context, String[] titles)
	{
		super(context);
		// 控件中的子空间整体横向排列，控件本身填充父控件
		this.setOrientation(HORIZONTAL);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		this.setLayoutParams(layoutParams);
		// 初始化，标题栏
		this.textViews = new TextView[titles.length];
		// 循环，根据标题栏动态生成TextView来显示标题，每个标题栏的宽度比例为1:1,其中的内容居中。
		for(int i = 0; i < titles.length; i++)
		{
			TextView textView = new TextView(context);
			textView.setText(titles[i]);
			textView.setGravity(Gravity.CENTER);
			textViews[i] = textView; 
			LayoutParams params = new LayoutParams(0,LayoutParams.WRAP_CONTENT);
			params.weight = 1;
			params.gravity = Gravity.CENTER;
			addView(textView, params);
		}
		
	}

	/**
	 * 获取标题栏的标题TextView控件，为了在MainActi中添加对标题栏点击事件的相应和切换。
	 * 
	 * @return
	 */
	public TextView[] getTextViews()
	{
		return textViews;
	}
}
