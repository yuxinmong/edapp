package com.ed.v1.ui.userinfo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.common.widget.RoundImageView;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

public class ChoiceListItemView extends LinearLayout implements Checkable {
	 
    private TextView nameTxt;
    private CheckBox selectBtn;
    private RoundImageView bg_item_header;
    private TextView count;
    private String data;
    public ChoiceListItemView(Context context, AttributeSet attrs,String data) {
        super(context, attrs);
          this.data=data;
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_header_bg, this, true);
        selectBtn = (CheckBox) v.findViewById(R.id.checkBox);
        bg_item_header = (RoundImageView) v.findViewById(R.id.bg_item_header);
        String Url = Scheme.DRAWABLE.wrap(data);
        LoadImageUtil.loadImage(Url, bg_item_header);
        count = (TextView) v.findViewById(R.id.count);
    }
 
    public void setName(String text) {
        nameTxt.setText(text);
    }
 
    @Override
    public boolean isChecked() {
        return selectBtn.isChecked();
    }
 
    @Override
    public void setChecked(boolean checked) {
        selectBtn.setChecked(checked);
        //根据是否选中来选择不同的背景图片
        if (checked) {
            selectBtn.setSelected(true);;
        } else {
            selectBtn.setSelected(false);;
        }
    }
 
    @Override
    public void toggle() {
        selectBtn.toggle();
    }
 
}