package com.ed.v1.ui.main;

import java.util.ArrayList;

import com.ed.v1.CLApplication;
import com.ed.v1.R;
import com.ed.v1.base.BaseFragment;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.ui.home.fragment.ListViewForScrollView;
import com.ed.v1.ui.home.fragment.ShowAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.tianshicoffeeom.app.imgscroll.MyImgScroll;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class ClothContentFragment extends ListFragment {

	private String text="";
	String goods[]={"3","2","1","3","2"};
	private boolean isPrepared;
	private boolean islike=false;
	private PullToRefreshListView list;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Bundle args = getArguments();
		text = args != null ? args.getString("text") : "";

		setListAdapter(new ShowAdapter(getActivity(),goods));
		 isPrepared = true; 
			//lazyLoad() ;
			//initListview();
	 if(text.endsWith("like"))
		{
			islike=true;
			//isVisible=true;
		}else if(text.endsWith("1")){

             goods[1]="3";
             goods[2]="3";
             goods[3]="3";
             goods[4]="3";
		}else if(text.endsWith("2")){

            goods[1]="2";
            goods[2]="2";
            goods[3]="2";
            goods[4]="2";
		}
		else if(text.endsWith("3")){

            goods[1]="1";
            goods[2]="3";
            goods[3]="1";
            goods[4]="3";
		}
		else if(text.endsWith("4")){

            goods[1]="3";
            goods[2]="1";
            goods[3]="1";
            goods[4]="1";
		}
		super.onCreate(savedInstanceState);

	}




	
	 @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
         // TODO Auto-generated method stub
         View root = LayoutInflater.from(getActivity()).inflate(
					R.layout.fragment_show, null);
        
          return root;

          
}

	
}
