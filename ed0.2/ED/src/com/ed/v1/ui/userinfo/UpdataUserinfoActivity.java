package com.ed.v1.ui.userinfo;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import cn.jeesoft.widget.pickerview.CharacterPickerView;
import cn.jeesoft.widget.pickerview.CharacterPickerWindow;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.ui.userinfo.OptionsWindowHelper.OnOptionsSelectListener;
import com.ed.v1.util.CommonUtil;

public class UpdataUserinfoActivity extends BaseFragmentActivity implements
		OnClickListener {
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	@Res(R.id.edit_mail)
	EditText edit_mail;
	@Res(R.id.edit_name)
	EditText edit_name;
	@Res(R.id.delete_input_mail)
	ImageView delete_input_mail;
	@Res(R.id.delete_input_name)
	ImageView delete_input_name;
	@Res(R.id.edit_mail_lv)
	RelativeLayout edit_mail_lv;
	@Res(R.id.edit_name_lv)
	RelativeLayout edit_name_lv;
	@Res(R.id.edit_age_lv)
	RelativeLayout edit_age_lv;
	private int tag;
	private String year;
	private String month;
	private String day;
	private String formatBirthday;
	private String birthday;
private int option1;
private int option2;
private int option3;
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_update_info;
	}

	private void initTitile() {
		// TODO Auto-generated method stub'
		CommonUtil.setTranslucentStatus(this);
		mText_TitleFinish.setVisibility(View.VISIBLE);
		mText_TitleFinish.setOnClickListener(this);
		delete_input_mail.setOnClickListener(this);
		delete_input_name.setOnClickListener(this);
		// mText_Titleinfo.setText("关于宜定");
		mBtnBack.setOnClickListener(this);

	}

	@Override
	protected void init(Bundle tagnum) {
		// TODO Auto-generated method stub
		Intent data = getIntent();
		tag = data.getExtras().getInt("value");
		birthday = data.getExtras().getString("birthday");
		if (tag == UserInfoActivity.RESULT_UPDATE_MAIL) {

			mText_Titleinfo.setText("我的邮箱");
			edit_mail_lv.setVisibility(View.VISIBLE);
			edit_name_lv.setVisibility(View.GONE);
		} else if (tag == UserInfoActivity.RESULT_UPDATE_NAME) {
			mText_Titleinfo.setText("修改姓名");
			edit_mail_lv.setVisibility(View.GONE);
			edit_name_lv.setVisibility(View.VISIBLE);
		} else if (tag == UserInfoActivity.RESULT_UPDATE_AGE) {
			mText_Titleinfo.setText("修改生日");
			edit_mail_lv.setVisibility(View.GONE);
			edit_name_lv.setVisibility(View.GONE);
			initAge();

		}
		initTitile();
	}

	private void initAge() {
		edit_age_lv.setVisibility(View.VISIBLE);
		LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

		CharacterPickerView pickerView = new CharacterPickerView(this);
	
		edit_age_lv.addView(pickerView, layoutParams);

		// 初始化选项数据
		OptionsWindowHelper.setPickerData(pickerView,
				new OnOptionsSelectListener() {

					@Override
					public void onOptionsSelect(String years, String months,
							String days) {
						// TODO Auto-generated method stub
						 FormatBirthday(years,months,days);

					}

				});
		if(!birthday.equals("")){
			String[] ss = birthday.split("\\.");
			year=ss[0];
			month=ss[1];
			day=ss[2];
			int x=Integer.parseInt(year)-1880;
		
			int y=Integer.parseInt(month)-1;
			int z=Integer.parseInt(day)-1;
			if(y<0){
				y=0;
			}
			if(x<0){
				x=0;
			}
			if(z<0){
				z=0;
			}
			pickerView.setCurrentItems(x, y, z);
			pickerView.setCyclic(true);
		}

	}
	protected static int option(int option, int size) {
		// TODO Auto-generated method stub
		int y=size-option;
	

		if(y<=3){
			switch (y) {
			case 1:
				y=0;
				break;
			case 2:
				y=1;
				break;
			case 3:
				y=2;
				break;
			default:
				break;
			}
		}
		return y;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.info_finish:
			save();
			break;
		case R.id.delete_input_mail:
			edit_mail.setText("");
			break;
		case R.id.delete_input_name:
			edit_name.setText("");
			break;
		case R.id.btnBack:
			finish();
			break;
		default:
			break;
		}
	}

	private void save() {
		// TODO Auto-generated method stub
		switch (tag) {
		case UserInfoActivity.RESULT_UPDATE_HEAD:

			break;
		case UserInfoActivity.RESULT_UPDATE_MAIL:
			Intent data = new Intent();
			data.putExtra("inputvalue", edit_mail.getText().toString());
			setResult(RESULT_OK, data);
			finish();
			break;
		case UserInfoActivity.RESULT_UPDATE_NAME:
			Intent data2 = new Intent();
			data2.putExtra("inputvalue", edit_name.getText().toString());
			setResult(RESULT_OK, data2);
			finish();
			break;
		case UserInfoActivity.RESULT_UPDATE_AGE:
			Intent data3 = new Intent();
			if(!formatBirthday.equals("")){
				data3.putExtra("inputvalue", formatBirthday);
			}
			setResult(RESULT_OK, data3);
			finish();
			break;
		default:
			break;
		}

	}

	private  void FormatBirthday(String years, String months,String days) {
		year = years.split("年")[0];
		month = months.split("月")[0];
	day = days.split("日")[0];
	StringBuffer ss = new StringBuffer();	
	formatBirthday=ss.append(year).append(".").append(month).append(".").append(day).toString();
	
	}
}
