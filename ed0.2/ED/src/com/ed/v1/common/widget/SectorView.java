package com.ed.v1.common.widget;

import java.math.BigDecimal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ed.v1.common.Percent;

public class SectorView extends ImageView {

    private static final BigDecimal MAX = BigDecimal.valueOf(10000);
    private RectF mArcBounds = new RectF();
    private Bitmap mSrcBitmap = null;
    private int mStartX = 0;
    private Canvas mCanvas = null;
    private int mProgress = 0;

    public SectorView(Context context) {
        this(context, null);
    }

    public SectorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SectorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        Drawable coverDrawable = this.getDrawable();
        mSrcBitmap = drawableToBitmap(coverDrawable);
        // mSrcBitmap = BitmapFactory.decodeResource(getResources(),
        // R.drawable.testing_circle_y);

        int width = mSrcBitmap.getWidth();
        mStartX = (w + 1) / 2 - width / 2;
        mArcBounds = new RectF(0, 0, width, width);

        mCanvas = new Canvas();
    }

    public void setCurrentValue(Percent percent) {
        int level = percent.asBigDecimal().multiply(MAX).intValue();
        mProgress = level * 360 / 10000;
        invalidate();
    }

    public void setFill() {
        mProgress = 360;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = mSrcBitmap.getWidth();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap canvasBitmap = Bitmap.createBitmap(width, width, Config.ARGB_8888);
        mCanvas.setBitmap(canvasBitmap);

        mCanvas.drawARGB(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);
        mCanvas.drawArc(mArcBounds, -90, mProgress, true, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        mCanvas.drawBitmap(mSrcBitmap, 0, 0, paint);

        canvas.drawBitmap(canvasBitmap, mStartX, 0f, null);

        canvasBitmap.recycle();
        canvasBitmap = null;
    }

    public void onDestoryView() {

    }

}
