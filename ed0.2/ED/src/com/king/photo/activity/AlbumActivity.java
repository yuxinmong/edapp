package com.king.photo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ed.v1.R;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.ui.userinfo.UserInfoActivity;
import com.ed.v1.util.CommonUtil;
import com.king.photo.adapter.AlbumGridViewAdapter;
import com.king.photo.util.AlbumHelper;
import com.king.photo.util.Bimp;
import com.king.photo.util.FileUtils;
import com.king.photo.util.ImageBucket;
import com.king.photo.util.ImageItem;
import com.king.photo.util.PublicWay;
import com.king.photo.util.ResUtil;

/**
 * 这个是进入相册显示所有图片的界面
 * 
 * @author king
 * @QQ:595163260
 * @version 2014年10月18日  下午11:47:15
 */
public class AlbumActivity extends Activity implements OnClickListener{
	//显示手机里的所有图片的列表控件
	private GridView gridView;
	//当手机里没有图片时，提示用户没有图片的控件
	private TextView tv;
	//gridView的adapter
	private AlbumGridViewAdapter gridImageAdapter;

	private Intent intent;

	private Context mContext;
	private ArrayList<ImageItem> dataList;
	private AlbumHelper helper;
	public static List<ImageBucket> contentList;
	public static Bitmap bitmap;
	TextView mText_Titleinfo;
	LinearLayout mBtnBack;
	private TextView camara;  

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(ResUtil.getLayoutID("plugin_camera_album"));
		PublicWay.activityList.add(this);
		CommonUtil.setTranslucentStatus(this);

		 initTitile();
		mContext = this;
		//注册一个广播，这个广播主要是用于在GalleryActivity进行预览时，防止当所有图片都删除完后，再回到该页面时被取消选中的图片仍处于选中状态
		IntentFilter filter = new IntentFilter("data.broadcast.action");  
		registerReceiver(broadcastReceiver, filter);  
        bitmap = BitmapFactory.decodeResource(getResources(),ResUtil.getDrawableID("plugin_camera_no_pictures"));
        init();
		initListener();

	}
	
	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {  
		  
        @Override  
        public void onReceive(Context context, Intent intent) {  
        	//mContext.unregisterReceiver(this);
            // TODO Auto-generated method stub  
        	gridImageAdapter.notifyDataSetChanged();
        }  
    };
    private void initTitile() {
		// TODO Auto-generated method stub
    	mText_Titleinfo=(TextView) findViewById(R.id.nav_title);
    	mBtnBack=(LinearLayout) findViewById(R.id.btnBack);
    			mText_Titleinfo.setText(R.string.all_picture);
		mBtnBack.setOnClickListener(new AlbumSendListener());
		camara=(TextView) findViewById(R.id.camara);

	}


	
	// 完成按钮的监听
	private class AlbumSendListener implements OnClickListener {
		public void onClick(View v) {
			overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
			intent.setClass(mContext, UserInfoActivity.class);
			startActivity(intent);
			finish();
		}

	}

	

	// 初始化，给一些对象赋值
	private void init() {
		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());
		
		contentList = helper.getImagesBucketList(false);
		dataList = new ArrayList<ImageItem>();
		for(int i = 0; i<contentList.size(); i++){
			dataList.addAll( contentList.get(i).imageList );
		}
		
		intent = getIntent();
		Bundle bundle = intent.getExtras();
		gridView = (GridView) findViewById(ResUtil.getWidgetID("myGrid"));
		gridImageAdapter = new AlbumGridViewAdapter(this,dataList,
				Bimp.tempSelectBitmap);
		gridView.setAdapter(gridImageAdapter);
		tv = (TextView) findViewById(ResUtil.getWidgetID("myText"));
		gridView.setEmptyView(tv);
	
	}

	private void initListener() {

		gridImageAdapter
				.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(final ToggleButton toggleButton,
							int position, boolean isChecked,Button chooseBt) {
						if (Bimp.tempSelectBitmap.size() >= PublicWay.num) {
							toggleButton.setChecked(false);
							chooseBt.setVisibility(View.GONE);
							if (!removeOneData(dataList.get(position))) {
								Toast.makeText(AlbumActivity.this, ResUtil.getString("only_choose_num"),
										200).show();
							}
							return;
						}
						if (isChecked) {
							//chooseBt.setVisibility(View.VISIBLE);
							Bimp.tempSelectBitmap.clear();
							Bimp.tempSelectBitmap.add(dataList.get(position));
							//okButton.setText(ResUtil.getString("finish"));
						if (Bimp.tempSelectBitmap.size() > 0) {
								intent.putExtra("position", "1");
								intent.setClass(AlbumActivity.this, PictureGalleryActivity.class);
								startActivity(intent);
							}
						} else {
							Bimp.tempSelectBitmap.remove(dataList.get(position));
							chooseBt.setVisibility(View.GONE);
							//okButton.setText(ResUtil.getString("finish")+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
						}
					}
				});

		camara.setOnClickListener(this);

	}

	private boolean removeOneData(ImageItem imageItem) {
			if (Bimp.tempSelectBitmap.contains(imageItem)) {
				Bimp.tempSelectBitmap.remove(imageItem);
				return true;
			}
		return false;
	}


	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
		  finish();
		}
		return false;

	}
@Override
protected void onRestart() {
	super.onRestart();
}
public void photo() {
	Intent intent1 = new Intent(AlbumActivity.this,
			TakeActivity.class);
	startActivity(intent1);

}
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.camara:
		finish();
		photo();
          
		break;

	default:
		break;
	}
}
}
