package com.ed.v1.ui.userinfo;

import android.content.Intent;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragment;
import com.ed.v1.common.viewholder.Res;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

public class PictureShowFragment extends BaseFragment implements OnClickListener{
         String data;
        
         @Res(R.id.weixin)
         TextView weixin;
         @Res(R.id.friend)
         TextView friend;
         @Res(R.id.weibo)
         TextView weibo;
	public PictureShowFragment(String data) {
		// TODO Auto-generated constructor stub
		this.data=data;
	}
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.item_picture_show;
	}
	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		weixin.setOnClickListener(this);
		friend.setOnClickListener(this);
		weibo.setOnClickListener(this);
	  
	}
	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.weixin:
			  new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
             //.withMedia(new UMEmoji(ShareActivity.this,"http://img.newyx.net/news_img/201306/20/1371714170_1812223777.gif"))
              .withText("hello umeng")
//              .withTargetUrl("http://dev.umeng.com")
             .share();
    
			break;
	      case R.id.friend:
              new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                      .share();
              break;
	      case R.id.weibo:
              /** shareaction need setplatform , callbacklistener,and content(text,image).then share it **/
              new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.SINA).setCallback(umShareListener)
                      .withText("umeng")
//                      .withExtra(new UMImage(ShareActivity.this,R.drawable.ic_launcher))
                      .withTargetUrl("http://dev.umeng.com")
                       .share();
              break;
		default:
			break;
		}
	}
	  private UMShareListener umShareListener = new UMShareListener() {
	        @Override
	        public void onResult(SHARE_MEDIA platform) {
	            Log.d("plat","platform"+platform);
	            Toast.makeText(getActivity(), platform + "分享成功啦", Toast.LENGTH_SHORT).show();
	        }

	        @Override
	        public void onError(SHARE_MEDIA platform, Throwable t) {
	            Toast.makeText(getActivity(),platform + "分享成功啦", Toast.LENGTH_SHORT).show();
	        }

	        @Override
	        public void onCancel(SHARE_MEDIA platform) {
	            Toast.makeText(getActivity(),platform + "分享成功啦", Toast.LENGTH_SHORT).show();
	        }
	    };
	  @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        /** attention to this below ,must add this**/
	        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
	        //Log.d("result","onActivityResult");
	    }
}
