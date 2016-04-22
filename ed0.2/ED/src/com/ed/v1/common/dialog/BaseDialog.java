package com.ed.v1.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import com.ed.v1.R;

public class BaseDialog extends Dialog {

    protected int screenWidth;
    private float mDensity = 1f;
    private int paddingSize = 0;

    protected Context ctx;
    protected View view;

    protected ImageView dialog_titleicon;
    protected TextView dialog_titletext;
    protected TextView dialog_message;
    protected Button mPositiveButton; // 确定
    protected Button mNegativeButton; // 取消
    protected Button mNeutralButton; // 中间

    protected LinearLayout dialog_title;
    protected LinearLayout dialog_body;
    protected LinearLayout dialog_space_bar;
    protected LinearLayout dialog_botton;
    protected LinearLayout layoutContainer;
    protected ScrollView dialog_scroll_body;
    protected LinearLayout dialog_fix_body;

    private View.OnClickListener backListener = null;

    private boolean isCancelable = true;

    public BaseDialog(Context context) {
        super(context, R.style.dialog);
        this.ctx = context;
        // 初始化
        this.initView();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        // 添加布局
        LayoutParams viewLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        // viewLayoutParams.width = screenWidth;
        super.setContentView(this.view, viewLayoutParams);
    }

    protected void initView() {
        // 获取系统数据
        this.screenWidth = this.ctx.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        this.mDensity = this.ctx.getResources().getDisplayMetrics().density;
        this.paddingSize = (int) (6 * this.mDensity);

        // 初始化布局
        this.view = LayoutInflater.from(this.ctx).inflate(R.layout.base_dialog, null);
        this.layoutContainer = (LinearLayout) this.view.findViewById(R.id.layout_container);
        this.dialog_titleicon = (ImageView) this.view.findViewById(R.id.title_icon);
        this.dialog_titletext = (TextView) this.view.findViewById(R.id.title_text);
        this.dialog_title = (LinearLayout) this.view.findViewById(R.id.dialog_title);
        this.dialog_body = (LinearLayout) this.view.findViewById(R.id.dialog_body);
        this.dialog_botton = (LinearLayout) this.view.findViewById(R.id.dialog_botton);
        this.dialog_space_bar = (LinearLayout) this.view.findViewById(R.id.dialog_space_bar);
        this.dialog_scroll_body = (ScrollView) this.view.findViewById(R.id.dialog_scroll_body);
        this.dialog_fix_body = (LinearLayout) this.view.findViewById(R.id.dialog_fix_body);

        // 设置边距
        int padding = this.screenWidth * 5 / 100;
        this.view.setPadding(padding, padding, padding, padding);

        // setTitleIcon(R.drawable.popup_icon_warning);
        // 设置点击空白处不关闭
        setCanceledOnTouchOutside(false);
    }

    /**
     * 设置类型(类型WindowManager.LayoutParams.TYPE_SYSTEM_ALERT需要权限 <uses-permission
     * android:name="android.permission.SYSTEM_ALERT_WINDOW" />)
     * 
     * @param type
     *            类型（WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG用于Acitivty内，
     *            WindowManager.LayoutParams.TYPE_SYSTEM_ALERT用于Acitivty外，效果如浮层）
     */
    public void setType(int type) {
        this.getWindow().setType(type);
    }

    public void setOnlyContent() {
        this.dialog_title.setVisibility(View.GONE);
        this.dialog_botton.setVisibility(View.GONE);
    }

    public void setTitleIcon(int iconRes) {
        this.dialog_title.setVisibility(View.VISIBLE);
        this.dialog_titleicon.setImageResource(iconRes);
    }

    private void setButtonStyle(Button btn) {
        btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.ctx.getResources().getDimensionPixelSize(R.dimen.dialog_button_text_size));
        btn.setTextColor(this.ctx.getResources().getColor(R.color.selector_dialog_button_text_default));
    }

    public void setPositiveButton(String resStr, View.OnClickListener paramOnClickListener) {
        this.setPositiveButton(resStr, paramOnClickListener, LayoutParams.MATCH_PARENT);
    }

    /**
     * @param resStrId
     * @param paramOnClickListener
     * @param btnType
     * @param fillType
     *            填充类型（LayoutParams.MATCH_PARENT 或 LayoutParams.WRAP_CONTENT）
     */
    public void setPositiveButton(String resStr, View.OnClickListener paramOnClickListener, int fillType) {
        if (this.mPositiveButton != null) {
            this.dialog_botton.removeView(this.mPositiveButton);
        }
        this.mPositiveButton = new Button(this.ctx);
        this.setButtonStyle(this.mPositiveButton);

        this.dialog_space_bar.setVisibility(View.VISIBLE);
        this.dialog_botton.setVisibility(View.VISIBLE);
        this.mPositiveButton.setText(resStr);
        this.mPositiveButton.setOnClickListener(paramOnClickListener);
        this.mPositiveButton.setGravity(Gravity.CENTER);
    }

    public void setNegativeButton(String resStr, View.OnClickListener paramOnClickListener) {
        this.setNegativeButton(resStr, paramOnClickListener, LayoutParams.MATCH_PARENT);
    }

    /**
     * @param resStrId
     * @param paramOnClickListener
     * @param btnType
     * @param fillType
     *            填充类型（LayoutParams.MATCH_PARENT 或 LayoutParams.WRAP_CONTENT）
     */
    public void setNegativeButton(String resStr, View.OnClickListener paramOnClickListener, int fillType) {
        if (this.mNegativeButton != null) {
            this.dialog_botton.removeView(this.mNegativeButton);
        }
        this.mNegativeButton = new Button(this.ctx);
        this.setButtonStyle(this.mNegativeButton);

        this.dialog_space_bar.setVisibility(View.VISIBLE);
        this.dialog_botton.setVisibility(View.VISIBLE);
        this.mNegativeButton.setText(resStr);
        this.mNegativeButton.setOnClickListener(paramOnClickListener);
        this.mNegativeButton.setGravity(Gravity.CENTER);
    }

    public void setNeutralButton(String resStr, View.OnClickListener paramOnClickListener) {
        this.setNeutralButton(resStr, paramOnClickListener, LayoutParams.MATCH_PARENT);
    }

    public void setNeutralButton(String resStr, View.OnClickListener paramOnClickListener, int fillType) {
        if (this.mNeutralButton != null) {
            this.dialog_botton.removeView(this.mNeutralButton);
        }
        this.mNeutralButton = new Button(this.ctx);
        this.setButtonStyle(this.mNeutralButton);

        this.dialog_space_bar.setVisibility(View.VISIBLE);
        this.dialog_botton.setVisibility(View.VISIBLE);
        this.mNeutralButton.setText(resStr);
        this.mNeutralButton.setOnClickListener(paramOnClickListener);
        this.mNeutralButton.setGravity(Gravity.CENTER);
    }

    @Override
    public void setContentView(View paramView) {
        this.dialog_body.removeAllViews();
        LinearLayout.LayoutParams paras = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        paras.gravity = Gravity.CENTER;
        // int i1 =
        // this.ctx.getApplicationContext().getResources().getDisplayMetrics().widthPixels
        // * 95 / 100;
        // paramView.setMinimumWidth(i1);
        this.dialog_body.addView(paramView, paras);
        // this.dialog_body.addView(paramView);
        this.dialog_body.setVisibility(View.VISIBLE);
    }

    public void setContentViewBackground(int drawableId) {
        this.dialog_body.setBackgroundResource(drawableId);
    }

    public void setContentViewNoPadding(View paramView) {
        this.dialog_body.removeAllViews();
        LinearLayout.LayoutParams paras = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        paras.gravity = Gravity.CENTER;
        this.dialog_body.setPadding(0, 0, 0, 0);
        this.dialog_body.addView(paramView);
        this.dialog_body.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // back key down
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (this.backListener != null) {
                this.backListener.onClick(null);
            } else {
                if (this.isCancelable) {
                    this.dismiss();
                }
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_SEARCH) {
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void setOnBackListener(View.OnClickListener listener) {
        this.backListener = listener;
    }

    public void setMessage(int msg) {
        this.setMessage(this.ctx.getString(msg));
    }

    public void setMessage(String msg) {
        this.dialog_body.removeAllViews();
        this.dialog_message = new TextView(this.ctx);
        this.dialog_message.setTextColor(this.ctx.getResources().getColor(R.color.base_dialog_content_text));
        this.dialog_message.setTextSize(16f);
        this.dialog_message.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.weight = 1.0F;
        this.dialog_body.addView(this.dialog_message, params);
        this.dialog_message.setText(msg);
        this.dialog_body.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
        super.setCancelable(isCancelable);
    }

    @Override
    public void setTitle(int titleResId) {
        String title = this.ctx.getResources().getString(titleResId);
        this.setTitle(title);
    }

    @Override
    public void setTitle(CharSequence title) {
        this.dialog_title.setVisibility(View.VISIBLE);
        this.dialog_titletext.setText(title);
    }

    public void setFixContentView(View view, LayoutParams params) {
        this.dialog_scroll_body.setVisibility(View.GONE);
        this.dialog_fix_body.setVisibility(View.VISIBLE);
        this.dialog_fix_body.removeAllViews();
        this.dialog_fix_body.addView(view, params);
    }

    private void addButton(Button button) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        layoutParams.weight = 1.0F;
        button.setPadding(this.paddingSize, this.paddingSize, this.paddingSize, this.paddingSize);
        this.dialog_botton.addView(button, layoutParams);
    }

    private void addButtonLine() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) this.mDensity, LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        LinearLayout line = new LinearLayout(this.ctx);
        line.setBackgroundResource(R.color.base_dialog_button_line);
        this.dialog_botton.addView(line, layoutParams);
    }

    private void formatButtons() {
        this.dialog_botton.removeAllViewsInLayout();

        if (this.mNegativeButton != null) {
            this.addButton(this.mNegativeButton);

            if (this.mNeutralButton != null || this.mPositiveButton != null) {
                this.mNegativeButton.setBackgroundResource(R.drawable.selector_button_bg_dialog_default_left);
            } else {
                this.mNegativeButton.setBackgroundResource(R.drawable.selector_button_bg_dialog_default_mid);
            }
        }

        if (this.mNeutralButton != null) {
            if (this.mNegativeButton != null) {
                this.addButtonLine();
            }

            if (this.mNegativeButton != null || this.mPositiveButton != null) {
                if (this.mNegativeButton == null) {
                    this.mNeutralButton.setBackgroundResource(R.drawable.selector_button_bg_dialog_default_left);
                } else if (this.mPositiveButton == null) {
                    this.mNeutralButton.setBackgroundResource(R.drawable.selector_button_bg_dialog_default_right);
                } else {
                    this.mNeutralButton.setBackgroundResource(R.drawable.selector_button_bg_dialog_default);
                }
            } else {
                this.mNeutralButton.setBackgroundResource(R.drawable.selector_button_bg_dialog_default);
            }

            this.addButton(this.mNeutralButton);
        }

        if (this.mPositiveButton != null) {
            if (this.mNegativeButton != null || this.mNeutralButton != null) {
                this.addButtonLine();
                this.mPositiveButton.setBackgroundResource(R.drawable.selector_button_bg_dialog_default_right);
            } else {
                this.mPositiveButton.setBackgroundResource(R.drawable.selector_button_bg_dialog_default);
            }
            this.addButton(this.mPositiveButton);
        }
    }

    @Override
    public void show() {
        // TODO 格式化button
        this.formatButtons();
        super.show();
    }

}
