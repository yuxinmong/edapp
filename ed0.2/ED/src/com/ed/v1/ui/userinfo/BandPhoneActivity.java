package com.ed.v1.ui.userinfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.util.CommonUtil;

public class BandPhoneActivity extends BaseFragmentActivity implements
		OnClickListener {
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	private int TIME = 60;
	@Res(R.id.edtPhoneNumber)
	private EditText edtPhoneNum;
	@Res(R.id.edtCheckCode)
	private EditText edtCode;
	@Res(R.id.send_code)
	private FrameLayout sendCode;

	@Res(R.id.login_name)
	private LinearLayout loginNameLayout;
	@Res(R.id.login_pass)
	private LinearLayout loginPassLayout;
	@Res(R.id.check_code)
	private RelativeLayout checkCodeLayout;
	@Res(R.id.wait_time)
	private TextView waitTime;
	@Res(R.id.code_text)
	private TextView codeText;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 2:
				sendCode.setClickable(false);
				codeText.setVisibility(View.INVISIBLE);
				waitTime.setVisibility(View.VISIBLE);
				handler.sendEmptyMessage(1);
				break;
			case 1:
				if (TIME > 0) {
					TIME = TIME - 1;
					waitTime.setText(TIME + "秒后重新发送");
					handler.sendEmptyMessageDelayed(1, 1000);
				} else {
					TIME = 60;
					waitTime.setVisibility(View.GONE);
					codeText.setVisibility(View.VISIBLE);
					sendCode.setClickable(true);
				}
				break;

			default:
				break;
			}
		}
	};
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_band_phone;
	}

	private void initTitile() {
		// TODO Auto-generated method stub
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("手机绑定");
		mBtnBack.setOnClickListener(this);
		sendCode.setOnClickListener(this);

	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
CommonUtil.setTranslucentStatus(this);
		initTitile();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int viewId = v.getId();
		switch (viewId) {
		case R.id.btnBack:
			onBackPressed();
			break;
		
		case R.id.send_code:
			sendCheckCode();
			break;
	
		default:
			break;
		}
	}

	private void sendCheckCode() {
		
		handler.sendEmptyMessage(2);
	}

}
