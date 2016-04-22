package com.ed.v1.base;

import android.app.Dialog;
import android.content.Context;
import com.ed.v1.R;

/**
 * Created by Haijun.Wang on 15/6/28.
 */
public class NormalLoadingDialog extends Dialog {

    public NormalLoadingDialog(Context context) {
        super(context, R.style.loading_dialog);
        setContentView(R.layout.normal_progress_dialog);
    }

}
