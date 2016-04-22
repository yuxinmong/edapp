package com.ed.v1.ui.userinfo;

import android.widget.ImageView;

import com.ed.v1.CLApplication;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

public class LoadImageUtil {
  public static void loadImage(int drawableUrl,ImageView mImageView){
		String Url = Scheme.DRAWABLE.wrap(drawableUrl + "");
		// 显示图片的配置		
		ImageLoader.getInstance().displayImage(Url, mImageView,
				CLApplication.getInstance().options);

  }
  public static void loadImage(String drawableUrl,ImageView mImageView){
		// 显示图片的配置		
		ImageLoader.getInstance().displayImage(drawableUrl, mImageView,
				CLApplication.getInstance().options);

  }
}
