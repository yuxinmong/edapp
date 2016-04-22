package com.ed.v1.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.common.viewholder.AutomaticViewHolderUtil;
import com.ed.v1.common.viewholder.Res;

/**
 * Created by Haijun.Wang on 15/6/24.
 */
public class CommonDialog extends Dialog implements View.OnClickListener {

    @Res(R.id.imgIcon)
    private ImageView imgIcon;
    @Res(R.id.txtTitle)
    private TextView txtTitle;
    @Res(R.id.txtContent)
    private TextView txtContent;
    @Res(R.id.btnPositive)
    private Button btnPositive;
    @Res(R.id.btnNegative)
    private Button btnNegative;
    @Res(R.id.btnNeutral)
    private Button btnNeutral;

    private Button[] buttons;

    private OnClickListener onClickListener;

    public CommonDialog(Context context) {
        super(context, R.style.Dialog);
        setContentView(R.layout.common_dialog);
        AutomaticViewHolderUtil.findAllViews(this, findViewById(R.id.layoutRoot));
        txtContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        btnPositive.setOnClickListener(this);
        btnNegative.setOnClickListener(this);
        btnNeutral.setOnClickListener(this);

        buttons = new Button[] { btnPositive, btnNegative, btnNeutral };
    }

    public CommonDialog setIcon(int id) {
        imgIcon.setImageResource(id);
        return this;
    }

    @Override
	public void setTitle(int titleID) {
        txtTitle.setText(titleID);
    }

    @Override
	public void setTitle(CharSequence title) {
        txtTitle.setText(title);
    }

    public CommonDialog setContent(int contentId) {
        txtContent.setText(contentId);
        return this;
    }

    public CommonDialog setContent(CharSequence content) {
        txtContent.setText(content);
        return this;
    }

    public CommonDialog setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    /**
     * @param which  {@link #BUTTON_POSITIVE} , {@link #BUTTON_NEGATIVE} , {@link #BUTTON_NEUTRAL}
     * @param textId
     */
    public CommonDialog setButton(int which, int textId) {
        getButton(which).setText(textId);
        return this;
    }

    /**
     * @param which {@link #BUTTON_POSITIVE} , {@link #BUTTON_NEGATIVE} , {@link #BUTTON_NEUTRAL}
     * @param text
     */
    public CommonDialog setButton(int which, CharSequence text) {
        getButton(which).setText(text);
        return this;
    }

    /**
     * @param which {@link #BUTTON_POSITIVE} , {@link #BUTTON_NEGATIVE} , {@link #BUTTON_NEUTRAL}
     * @return
     */
    public Button getButton(int which) {
        return buttons[-1 - which];
    }

    /**
     * 参数指定的按钮将被设置为可见，其余按钮被设置为不可见
     *
     * @param whichButtons {@link #BUTTON_POSITIVE} , {@link #BUTTON_NEGATIVE} , {@link #BUTTON_NEUTRAL}
     */
    public CommonDialog setVisibilityButtons(int... whichButtons) {
        for (Button button : buttons) {
            button.setVisibility(View.GONE);
        }
        for (int which : whichButtons) {
            getButton(which).setVisibility(View.VISIBLE);
        }
        return this;
    }

    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            if (v == btnPositive) {
                onClickListener.onClick(this, BUTTON_POSITIVE);
            } else if (v == btnNegative) {
                onClickListener.onClick(this, BUTTON_NEGATIVE);
            } else if (v == btnNeutral) {
                onClickListener.onClick(this, BUTTON_NEUTRAL);
            }
        }
    }

}

