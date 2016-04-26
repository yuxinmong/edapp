package com.ed.v1.ui.buyer;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragment;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.ui.buyer.BuyerShowAdapter.OnRecyclerViewItemClickListener;
import com.ed.v1.ui.main.HomeTabActivity;
import com.ed.v1.util.CommonUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.WanRecycleView;

public class BuyerShowFragment extends BaseFragment implements OnClickListener,
		 SwipeRefreshLayout.OnRefreshListener {
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.jion)
	TextView jion;
	@Res(R.id.cv_refreshListRecyclerView)
	WanRecycleView cv_refreshListRecyclerView;

	private ArrayList<String> datas;
	private int imageSize;
	private boolean bn_left = false;
	private boolean bn_right = false;
	private RecyclerView list;
	private int mScreenWidth;
	private int[] imageIds;
	private SwipeRefreshLayout mSwipeRefreshWidget;
	private SwipeRefreshLayout swipeLayout;

	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_buyer_show;
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		CommonUtil.setTranslucentStatus(getActivity());
		initTitile();
		datas = new ArrayList();
		for (int i = 0; i <= 15; i++) {
			datas.add("1");
		}
		initGridList();
		jion.setOnClickListener(this);

	}

	private void initGridList() {
		// TODO Auto-generated method stub

		StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(
				2, StaggeredGridLayoutManager.VERTICAL);
		cv_refreshListRecyclerView.setLayoutManager(layoutManager1);
		cv_refreshListRecyclerView.addHeaderView(new Top3View(getActivity(), null, null, this));
		cv_refreshListRecyclerView.setItemViewBothSidesMargin(36);
		initListData();
		BuyerShowAdapter adapter1 = new BuyerShowAdapter(imageIds,this);
		adapter1.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
			@Override
			public void onItemClick(View view, String data) {
				Intent intent = new Intent(getActivity(),
						ShowGalleryActivity.class);
				startActivity(intent);
			}
		});
		cv_refreshListRecyclerView.setAdapter(adapter1);
		cv_refreshListRecyclerView.setScrollingWhileRefreshingEnabled(true);  
		cv_refreshListRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);  
		cv_refreshListRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {  
	           @Override  
	           public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {  
	               new Handler().postDelayed(new Runnable() {  
	                   @Override  
	                   public void run() {  
	  
	                	   cv_refreshListRecyclerView.onRefreshComplete();  
	                   }  
	               }, 4000);  
	           }  
	  
	           @Override  
	           public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {  
	               new Handler().postDelayed(new Runnable() {  
	                   @Override  
	                   public void run() {  
	  
	                	   cv_refreshListRecyclerView.onRefreshComplete();  
	                   }  
	               }, 4000);  
	           }  
	       });  
		
	}

	private void initLeftRight() {
		// TODO Auto-generated method stub

		jion.setOnClickListener(this);
	}


	private void initTitile() {
		// TODO Auto-generated method stub
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("买家秀");
		mBtnBack.setOnClickListener(this);

	}

	private void initListData() {
		/**
		 * 初始化模拟数据
		 */
		imageIds = new int[] { R.drawable.top1, R.drawable.top3,
				R.drawable.top1, R.drawable.top2,R.drawable.item1,

		};

		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btnBack:
			HomeTabActivity.homeUI.changeFragment(1);
			break;
	
		case R.id.jion:

			Intent intent = new Intent(getActivity(), UPToTopActivity.class);
			startActivity(intent);
		default:
			break;
		}
	}

	
	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable() {
			public void run() {
				swipeLayout.setRefreshing(false);

				// adapter.notifyDataSetChanged();
			}
		}, 500);
	}
}
