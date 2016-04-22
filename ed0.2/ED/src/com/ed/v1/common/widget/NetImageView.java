package com.ed.v1.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.ed.v1.R;
import com.ed.v1.net.api.APIQueue;

/**
 * 通过setImageUrl来加载网络图片
 *
 * @author yangcheng
 */
public class NetImageView extends ImageView {

    protected static final long ANIM_DURATION = 1000;

    protected String url;
    protected String lastUrl;

    protected boolean playAnimation = true;

    protected AlphaAnimation animation;

    private OnImageLoadListener onImageLoadListener;

    public NetImageView(Context context) {
        super(context);
        this.init();
    }

    public NetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public NetImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.init();
    }

    private void init() {
        this.animation = new AlphaAnimation(0, 1);
        this.animation.setDuration(ANIM_DURATION);
    }

    public void enableAnimation(boolean enable) {
        this.playAnimation = enable;
    }

    public void setAnimationDuration(long duration) {
        this.animation.setDuration(duration);
    }

    public void setOnImageLoadListener(OnImageLoadListener onImageLoadListener) {
        this.onImageLoadListener = onImageLoadListener;
    }

    public void setImageUrl(final String _url, final int defaultImage, final boolean shouldCache) {
        if (TextUtils.isEmpty(_url)) {
            if (defaultImage != 0) {
                super.setImageResource(defaultImage);
            }
            return;
        }
        final String url = _url.replace(' ', '+');
        if (url.equals(this.url) && url.equals(this.lastUrl) && shouldCache) {
            return;
        }
        this.url = url;
        if (!url.equals(this.lastUrl)) {
            super.setImageResource(defaultImage);
        }
        if (this.playAnimation) {
            clearAnimation();
        }

        APIQueue.getInstance().getImageLoader().get(url, new ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                NetImageView.super.setImageResource(defaultImage);
            }

            @Override
            public void onResponse(ImageContainer response, boolean isImmediate) {
                if (url.equals(NetImageView.this.url) && response != null) {
                    Bitmap bitmap = response.getBitmap();
                    if ((shouldCache && bitmap != null && !NetImageView.this.url.equals(lastUrl)) || (!shouldCache)) {
                        NetImageView.super.setImageBitmap(bitmap);
                        NetImageView.this.invalidate();
                        lastUrl = url;
                        if (playAnimation) {
                            startAnimation(animation);
                        }
                        if (onImageLoadListener != null) {
                            onImageLoadListener.onLoaded(bitmap, isImmediate);
                        }
                    }
                }
            }
        }, shouldCache);
    }

    public void setImageUrl(final String _url, final int defaultImage) {
        setImageUrl(_url, defaultImage, true);
    }

    public void setImageUrl(String url) {
        setImageUrl(url, R.drawable.group_default);
    }

    public void setImageUrl(String url, boolean shouldCache) {
        setImageUrl(url, R.drawable.group_default, shouldCache);
    }

    // ===start=== 设置非网络图片时清除状态，以免再次设置最近一次的网络图片时出现无法显示的问题。
    @Override
    public void setImageResource(int resId) {
        cleanUrl();
        super.setImageResource(resId);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        cleanUrl();
        super.setImageBitmap(bm);
    }

    public void setImageResourceWithoutClean(int resId) {
        super.setImageResource(resId);
    }

    @Override
    public void setImageURI(Uri uri) {
        cleanUrl();
        super.setImageURI(uri);
    }

    private void cleanUrl() {
        lastUrl = "";
        url = "";
    }
    // ====end==== 设置非网络图片时清除状态，以免再次设置最近一次的网络图片时出现无法显示的问题。

    public void setImageUrl(String url, boolean shouldCache, final int defaultImage) {
        setImageUrl(url, defaultImage, shouldCache);
    }

    public interface OnImageLoadListener {
        void onLoaded(Bitmap bitmap, boolean isImmediate);
    }
}
