package com.ed.v1.ui.userinfo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.jeesoft.widget.pickerview.CharacterPickerView;
import cn.jeesoft.widget.pickerview.CharacterPickerWindow;

import com.android.volley.Request.Method;
import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.Constants;
import com.ed.v1.common.ToastUtil;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.common.widget.CircleImageView;
import com.ed.v1.common.widget.wheel.ActionSheet;
import com.ed.v1.common.widget.wheel.NumericWheelAdapter;
import com.ed.v1.common.widget.wheel.OnWheelScrollListener;
import com.ed.v1.common.widget.wheel.WheelView;
import com.ed.v1.common.widget.wheel.ActionSheet.ActionSheetListener;
import com.ed.v1.datacenter.DataCenter;
import com.ed.v1.model.UserInfo;
import com.ed.v1.net.api.APIDelegate;
import com.ed.v1.net.api.APIRequest;
import com.ed.v1.net.api.APIResponse;
import com.ed.v1.net.api.ImageRequest;
import com.ed.v1.net.bean.AgeSexContent;
import com.ed.v1.net.bean.UploadTokenContent;
import com.ed.v1.util.BirthUtils;
import com.ed.v1.util.BitmapUtil;
import com.ed.v1.util.BottomDialogUtils;
import com.ed.v1.util.CommonUtil;
import com.ed.v1.util.FileUtil;
import com.king.photo.activity.AlbumActivity;
import com.king.photo.activity.PictureGalleryActivity;
import com.king.photo.util.Bimp;
import com.king.photo.util.FileUtils;
import com.king.photo.util.ImageItem;
import com.king.photo.util.PublicWay;
import com.king.photo.util.ResUtil;

public class UserInfoActivity extends BaseFragmentActivity implements
		OnWheelScrollListener, OnClickListener {

	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;

	@Res(R.id.btnBack)
	LinearLayout mBtnBack;

	@Res(R.id.input_user_age)
	TextView input_user_age;
	@Res(R.id.input_user_sex)
	TextView input_user_sex;
	@Res(R.id.input_user_name)
	TextView input_user_name;
	@Res(R.id.input_user_mail)
	TextView input_user_mail;
	@Res(R.id.user_header)
	CircleImageView user_header;
	@Res(R.id.user_info_layout)
	RelativeLayout user_info_layout;
	@Res(R.id.user_mail_lt)
	RelativeLayout user_mail_lt;
	@Res(R.id.user_sex_lt)
	RelativeLayout user_sex_lt;
	@Res(R.id.user_age_lt)
	RelativeLayout user_age_lt;
	@Res(R.id.user_name_lt)
	RelativeLayout user_name_lt;
	private int clickPosition;
	private View birthView;
	private WheelView yearWheel;
	private WheelView monthWheel;
	private WheelView dayWheel;
	private String[] sex = new String[] { "男", "女" };
	private String[] header = new String[] { "拍照", "从相册选择" };
	UserInfo userInfo;
	private int year = 60;
	private int month = 0;
	private int day = 0;
	private String birthdayData;
	private Handler handler = new Handler();
	private static final int TAKE_PICTURE = 0x000001;

	/**
	 * 跳转拍照标识
	 */
	private final int RESULT_CAPTURE_IMAGE = 1;
	static final int RESULT_UPDATE_NAME = 5;
	static final int RESULT_UPDATE_MAIL = 6;
	static final int RESULT_UPDATE_AGE = 7;
	static final int RESULT_UPDATE_HEAD = 8;
	public static final String action = "userImg.broadcast.action";
	/**
	 * 跳转浏览相册标识
	 */
	private int INTENT_LOAD_IMAGE = 2;

	/**
	 * 跳转图片剪辑标识
	 */
	private final int INTENT_CROP = 3;
	// 拍照后图片名
	private String takePicName = null;
	// 图片路径
	private String bigPicPath;

	private String token;
	private String imageUrl;
	private NumericWheelAdapter dayAdapter;
	private PopupWindow pop;
	private LinearLayout ll_popup;
	private ArrayList<ImageItem> headBmp;
	public static Bitmap bmp;
	public static String userImage = FileUtil.getSDPath() + "/ed/cache/image/";

	private void initTitile() {
		// TODO Auto-generated method stub

		mText_Titleinfo.setText(R.string.personal_info);
		mBtnBack.setOnClickListener(this);

	}

	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_userinfo;
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		CommonUtil.setTranslucentStatus(this);
		ResUtil.init(this);

		PublicWay.activityList.add(this);
		initTitile();
		userInfo = DataCenter.getInstance().getUserInfo();
		initPop();
		initUserInfo();
		//findView(R.id.user_info_layout).setOnClickListener(this);
		findView(R.id.user_sex_lt).setOnClickListener(this);
		findView(R.id.user_age_lt).setOnClickListener(this);
		user_mail_lt.setOnClickListener(this);
		user_name_lt.setOnClickListener(this);
	}

	private void initUserInfo() {
		// TODO Auto-generated method stub

		if (userInfo.getHeadImgUrl() != null) {

			addRequest(new ImageRequest(userInfo.getHeadImgUrl(), user_header,
					R.drawable.user_l), null);
		}

	}

	private void initPop() {
		pop = new PopupWindow(UserInfoActivity.this);

		View view = getLayoutInflater().inflate(R.layout.item_popupwindows,
				null);

		ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.user_info_layout:
			Intent intent1 = new Intent(UserInfoActivity.this,
					AlbumActivity.class);
			startActivity(intent1);
			overridePendingTransition(R.anim.activity_translate_in,
					R.anim.activity_translate_out);
			pop.dismiss();
			ll_popup.clearAnimation();
			break;
		case R.id.user_name_lt:
			Intent intent = new Intent(getApplicationContext(),
					UpdataUserinfoActivity.class);
			intent.putExtra("value", RESULT_UPDATE_NAME);
			startActivityForResult(intent, RESULT_UPDATE_NAME);
			break;
		case R.id.user_mail_lt:
			Intent intent2 = new Intent(getApplicationContext(),
					UpdataUserinfoActivity.class);
			intent2.putExtra("value", RESULT_UPDATE_MAIL);
			startActivityForResult(intent2, RESULT_UPDATE_MAIL);
			break;
		case R.id.user_sex_lt:

			break;
		case R.id.user_age_lt:
			Intent intent3 = new Intent(getApplicationContext(),
					UpdataUserinfoActivity.class);
			intent3.putExtra("value", RESULT_UPDATE_AGE);
			intent3.putExtra("birthday", input_user_age.getText());
			startActivityForResult(intent3, RESULT_UPDATE_AGE);
			break;
		case R.id.btnBack:
			this.finish();
			break;

		default:
			break;
		}
	}

	private void UpdateImage() {
		// TODO Auto-generated method stub
		if (takePicName != null) {

		} else {
			hideLoadingDialog();
		}
	}

	protected int getSexNum(String sex) {
		// TODO Auto-generated method stub
		int sexnum = 0;
		if (sex.endsWith("男"))
			sexnum = 1;
		if (sex.endsWith("女"))
			sexnum = 2;
		return sexnum;
	}

	protected String getSexString(String string) {
		// TODO Auto-generated method stub
		String sex1 = null;
		if (string.endsWith("1"))
			sex1 = "男";
		if (string.endsWith("2"))
			sex1 = "女";
		return sex1;
	}

	public void showSexSheet() {
		ActionSheet.createBuilder(this, getSupportFragmentManager())
				.setCancelButtonTitle(R.string.messagebox_cancel)
				.setOtherButtonTitles(sex[0], sex[1])
				.setCancelableOnTouchOutside(true)
				.setListener(new SexListener()).show();
	}

	public class SexListener implements ActionSheetListener {

		@Override
		public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onOtherButtonClick(ActionSheet actionSheet, int index) {
			// TODO Auto-generated method stub
			switch (index) {
			case 0:
				input_user_sex.setText(R.string.sex0);
				break;
			case 1:
				input_user_sex.setText(R.string.sex1);

				break;
			default:
				break;
			}
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode != RESULT_OK) {
			return;
		}
		String ss = data.getStringExtra("inputvalue");
		switch (requestCode) {
		case RESULT_UPDATE_NAME:
			input_user_name.setText(ss);
			break;
		case RESULT_UPDATE_MAIL:
			input_user_mail.setText(ss);
			break;
		case RESULT_UPDATE_AGE:
			input_user_age.setText(ss);
			break;

		default:
			break;
		}

	}
	


	@Override
	protected void onRestart() {
		if (Bimp.tempSelectBitmap.size() != 0) {
			bmp=Bimp.tempSelectBitmap.get(0).getBitmap();
			user_header
					.setImageBitmap(bmp);
		}
		super.onRestart();

	};

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		if (Bimp.tempSelectBitmap.size() != 0) {
			bmp=Bimp.tempSelectBitmap.get(0).getBitmap();
			user_header
					.setImageBitmap(bmp);
		}
		super.onResume();
	}

	@Override
	public void onScrollingStarted(WheelView wheel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollingFinished(WheelView wheel) {
		// TODO Auto-generated method stub
		switch (clickPosition) {
		case 1:
			day = dayWheel.getCurrentItem();
			month = monthWheel.getCurrentItem();
			year = yearWheel.getCurrentItem();

			dayAdapter = new NumericWheelAdapter(UserInfoActivity.this, 1,
					BirthUtils.getDay(year, month));
			dayAdapter.setLabel("日");
			dayWheel.setViewAdapter(dayAdapter);
			break;

		default:
			break;
		}
	}

}
