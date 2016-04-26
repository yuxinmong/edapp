package com.ed.v1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.TimeZone;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.support.v4.content.LocalBroadcastManager;




/**
 * Created by Haijun.Wang on 15/6/24.
 */
public class CLApplication extends Application {
	private static String TAG = "CLApplication";
	private static CLApplication instance;
	public static DisplayImageOptions options;
	@Override
	public void onCreate() {
		super.onCreate();
		CLApplication.instance = this;
		Share ();
		instance = this;
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration
				.createDefault(this);
		
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.group_default)
		.cacheInMemory(true).cacheOnDisk(true)
		.bitmapConfig(Bitmap.Config.RGB_565).build();

		
		//Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(configuration);
		
		
		
	
		
		
	}


public void  Share (){
	   PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
       //璞嗙摚RENREN骞冲彴鐩墠鍙兘鍦ㄦ湇鍔″櫒绔厤缃�
       //鏂版氮寰崥
       PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
       //鏄撲俊
     
}
	public boolean getLoggingSwitch() {
		try {
			ApplicationInfo appInfo = getPackageManager().getApplicationInfo(
					getPackageName(), PackageManager.GET_META_DATA);
			boolean b = appInfo.metaData.getBoolean("LOGGING");
			return b;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean getAlphaSwitch() {
		try {
			ApplicationInfo appInfo = getPackageManager().getApplicationInfo(
					getPackageName(), PackageManager.GET_META_DATA);
			boolean b = appInfo.metaData.getBoolean("ALPHA");
			return b;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}


	public synchronized static CLApplication getInstance() {
		return instance;
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	// 以下代码移植 乱
	public static int mType = 0;
	public static final int BUFFER_LEN = 43;
	private final static boolean mSecure = true;

	public static final int TYPE_BLOODPRESSURE_BEILIS = 1;
	public static final int TYPE_BLOODGLUCOSE_FUDAKANG = 2;
	public static final int TYPE_BLOODGLUCOSE_SINOCARE = 3;
	public static final int TYPE_BLOODGLUCOSE_BIOLAND = 4;

	// 改成匹配前三位，爱噢乐后面的数字不能用
	public static final String BLOODPRESSURE_BEILIS_DEVICE_NAME = "BOL";// "BOLUTEK";
	public static final String BLOODGLUCOSE_FUDAKANG_DEVICE_NAME = "Dua";// "Dual-SPP";
	public static final String BLOODGLUCOSE_SINOCAREWL1_DEVICE_NAME = "Sin";// "Sinocare";
	public static final String BLOODGLUCOSE_BIOLAND_DEVICE_NAME = "BGM";



	



	
	public int getCurrentDeviceType() {
		return mType;
	}

	
	


}
