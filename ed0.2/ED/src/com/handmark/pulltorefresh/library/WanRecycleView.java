package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

import com.ed.v1.ui.buyer.BuyerShowAdapter;
import com.ed.v1.ui.buyer.Top3View;

public class WanRecycleView extends PullToRefreshBase<RecyclerView> {  
  
    private FamiliarRecyclerView view;

	public WanRecycleView(Context context) {  
        super(context);  
    }  
  
    public WanRecycleView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public WanRecycleView(Context context, Mode mode) {  
        super(context, mode);  
    }  
  
    public WanRecycleView(Context context, Mode mode, AnimationStyle animStyle) {  
        super(context, mode, animStyle);  
    }  
  
    //重写4个方法  
    //1 滑动方向  
    @Override  
    public Orientation getPullToRefreshScrollDirection() {  
        return Orientation.VERTICAL;  
    }  
  
    //重写4个方法  
    //2  滑动的View  
    @Override  
    protected FamiliarRecyclerView createRefreshableView(Context context, AttributeSet attrs) {  
    	 view = new FamiliarRecyclerView(context, attrs);  
        return view;  
    }  
  
    //重写4个方法  
    //3 是否滑动到底部  
    @Override  
    protected boolean isReadyForPullEnd() {  
        View view = getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1);  
        if (null != view) {  
            return getRefreshableView().getBottom() >= view.getBottom();  
        }  
        return false;  
    }  
  
    //重写4个方法  
    //4 是否滑动到顶部  
    @Override  
    protected boolean isReadyForPullStart() {  
        View view = getRefreshableView().getChildAt(0);  
  
        if (view != null) {  
            return view.getTop() >= getRefreshableView().getTop();  
        }  
        return false;  
    }

	public void setLayoutManager(StaggeredGridLayoutManager layoutManager1) {
		// TODO Auto-generated method stub
		view.setLayoutManager(layoutManager1);
	}

	public void addHeaderView(Top3View top3View) {
		// TODO Auto-generated method stub
		view.addHeaderView(top3View);

	}

	public void setItemViewBothSidesMargin(int i) {
		// TODO Auto-generated method stub
		view.setItemViewBothSidesMargin(36);

	}

	public void setAdapter(BuyerShowAdapter adapter1) {
		// TODO Auto-generated method stub
		view.setAdapter(adapter1);
	}  
  
}  
