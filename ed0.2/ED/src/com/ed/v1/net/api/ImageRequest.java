package com.ed.v1.net.api;

import android.widget.ImageView;

public class ImageRequest extends APISimpleRequest {

    private ImageView imageView;
    private int defaultImageId;
    private int errorImageId;

    public ImageRequest(String url, ImageView imageView, int defaultImageId) {
        this(url, imageView, defaultImageId, defaultImageId);
    }

    public ImageRequest(String url, ImageView imageView, int defaultImageId, int errorImageId) {
        super(url);

        this.imageView = imageView;
        this.defaultImageId = defaultImageId;
        this.errorImageId = errorImageId;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public int getDefaultImageId() {
        return this.defaultImageId;
    }

    public int getErrorImageId() {
        return this.errorImageId;
    }
}
