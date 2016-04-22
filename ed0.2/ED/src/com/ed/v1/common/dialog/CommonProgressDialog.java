package com.ed.v1.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.ed.v1.R;

//progress dialog
public class CommonProgressDialog extends Dialog {

    private Context mContext = null;
    private static CommonProgressDialog mInstance = null;

    private CommonProgressDialog(Context context) {
        super(context, R.style.loading_dialog);

        this.mContext = context;

        this.setContentView(R.layout.common_progress);

        this.getWindow().getAttributes().gravity = Gravity.CENTER;

        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }

    public void setCanCancel(boolean aCancel) {
        this.setCanceledOnTouchOutside(aCancel);
        this.setCancelable(aCancel);
    }

    public static synchronized CommonProgressDialog getInstance(Context ctx) {
        CommonProgressDialog.mInstance = new CommonProgressDialog(ctx);

        return CommonProgressDialog.mInstance;
    }

    @Override
    public void show() {
        if (!this.isShowing()) {
            try {
                super.show();
            } catch (Exception e) {
                // TODO 暂时解决Request未能cancel造成的崩溃问题。
            }
        }

    }

    @Override
    public void dismiss() {
        if (this.isShowing()) {
            try {
                super.dismiss();
            } catch (Exception e) {
                // TODO 暂时解决Request未能cancel造成的崩溃问题。
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // loading animation
        Animation rotate_anim = AnimationUtils.loadAnimation(this.mContext, R.anim.rotate_anim);

        ImageView imageView = (ImageView) this.findViewById(R.id.loading_image_view);
        imageView.setAnimation(rotate_anim);
    }

    // modify display text
    public void setMessage(String strMessage) {

        TextView tvMsg = (TextView) this.findViewById(R.id.tip_text_view);

        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }
    }

}
