package com.ed.v1.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class InerListView extends ListView {

	public InerListView(Context context) {
        super(context);
	}

	public InerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
	}

	public InerListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
	}

	/**  
	 * 0x900>>2
	 * Integer.MAX_VALUE >> 2,如果不设置，系统默认设置是显示两条
	 */
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

	}
}
