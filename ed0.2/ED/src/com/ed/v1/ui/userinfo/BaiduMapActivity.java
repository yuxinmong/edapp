package com.ed.v1.ui.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.util.CommonUtil;
/**
 * @author djxiao
 * @create 2016-4-23下午7:43:08
 * @DESC:  通过百度地图选择地点，初始定位、点击百度地图获取地址
 */
public class BaiduMapActivity extends BaseFragmentActivity implements OnClickListener,OnGetGeoCoderResultListener{
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	
	@Res(R.id.map_baidu)
	MapView mapView;
	
	private LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	private BaiduMap mBaiduMap;
	boolean isFirstLoc = true; // 是否首次定位
	private GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
	

	@Override
	protected int getContentViewId() {
		
		return R.layout.activity_baidu_map;
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		
		CommonUtil.setTranslucentStatus(this);
		
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("百度地图");
		mText_TitleFinish.setVisibility(View.VISIBLE);
		mBtnBack.setOnClickListener(this);
		
		onMapAction();
		
	}
	
	/**
	 * @author djxiao
	 * @create 2016-4-23下午7:35:56
	 * @DECS:  地图的初始，包括定位，点击地图事件
	 */
	private void onMapAction(){
		// 地图初始化
		mBaiduMap = mapView.getMap();
		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
		
		
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
		
        mBaiduMap.setOnMapClickListener(new OnMapClickListener() {//地图点击事件
			
			@Override
			public boolean onMapPoiClick(MapPoi poi) {//点击地图上的图标回调
				
				LatLng ptCenter = new LatLng((Double.valueOf(poi.getPosition().latitude)), (Double.valueOf(poi.getPosition().longitude)));
				// 反Geo搜索
				mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(ptCenter));
				
				return false;
			}
			
			public void onMapClick(LatLng latLng) {//点击地图空白处回调
				LatLng ptCenter = new LatLng((Double.valueOf(latLng.latitude)), (Double.valueOf(latLng.longitude)));
				// 反Geo搜索
				mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(ptCenter));
			};
		});
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnBack:
			finish();
			break;
		}
		
	}
	
	 /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                     // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
    
    @Override
    public void onPause() {
    	mapView.onPause();
    	super.onPause();
    }
    
    @Override
    public void onResume() {
    	mapView.onResume();
    	super.onResume();
    }
    
    @Override
    protected void onDestroy() {
    	// 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
    	super.onDestroy();
    }

	@Override
	public void onGetGeoCodeResult(GeoCodeResult arg0) {//编码回调，由地区到经纬度
		
		
	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {//反编码回调，由经纬度到地区
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(BaiduMapActivity.this, "抱歉，未能找到结果", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		
		Intent intent = new Intent();
		intent.putExtra("address", result.getAddress());
		this.setResult(123,intent);
		finish();
		
	}
	

}
