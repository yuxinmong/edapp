package com.ed.v1.thirdTP;
//package com.carelinker.pharmacy.v2.thirdTP;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.widget.Toast;
//
//import com.android.volley.Request.Method;
//import com.carelinker.pharmacy.v2.CLApplication;
//import com.carelinker.pharmacy.v2.common.Constants;
//import com.carelinker.pharmacy.v2.common.ToastUtil;
//import com.carelinker.pharmacy.v2.datacenter.DataCenter;
//import com.carelinker.pharmacy.v2.net.api.APIDelegate;
//import com.carelinker.pharmacy.v2.net.api.APIQueue;
//import com.carelinker.pharmacy.v2.net.api.APIRequest;
//import com.carelinker.pharmacy.v2.net.api.APIResponse;
//import com.carelinker.pharmacy.v2.net.bean.ListBigVContent;
//import com.carelinker.pharmacy.v2.net.bean.RegisterContent;
//import com.carelinker.pharmacy.v2.ui.main.HomeTabActivity;
//import com.carelinker.pharmacy.v2.util.StringUtil;
//import com.carelinker.pharmacy.v2.util.UmengUtil;
//import com.tencent.connect.UserInfo;
//import com.tencent.tauth.IUiListener;
//import com.tencent.tauth.Tencent;
//import com.tencent.tauth.UiError;
//import com.umeng.comm.core.beans.CommUser;
//import com.umeng.comm.core.beans.CommUser.Gender;
//import com.umeng.comm.core.login.LoginListener;
//import com.umeng.community.example.custom.MyLoginImpl;
//import com.umeng.community.example.custom.MyPresenter;
//
//public class QQEntryActivity extends Activity implements IUiListener {
//
//	private ProgressDialog pd;
//	private Tencent mTencent;
//	private String openid, nickname, username, password;
//	protected String[] authenList;
//	public static final String APPID = "1104319271";
//	private Handler mHandler = new Handler() {
//		public void handleMessage(android.os.Message msg) {
//			switch (msg.what) {
//			case -1:
//				pd.dismiss();
//				ToastUtil.show(QQEntryActivity.this, "授权登录失败");
//				finish();
//			case -2:
//				pd.dismiss();
//				ToastUtil.show(QQEntryActivity.this, "获取用户信息失败");
//				finish();
//			case -3:
//				pd.dismiss();
//				ToastUtil.show(QQEntryActivity.this, "取消授权");
//				finish();
//			default:
//				break;
//			}
//		};
//	};
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		login1();
//	}
//
//	private void login1() {
//		// TODO Auto-generated method stub
//		pd = new ProgressDialog(this);
//		pd.setCanceledOnTouchOutside(false);
//		pd.setMessage("正在获取用户信息...");
//		pd.show();
//
//		mTencent = Tencent.createInstance(APPID, this.getApplicationContext());
//		// if (!mTencent.isSessionValid())
//		// {
//		mTencent.login(this, "get_simple_userinfo", this);
//		// }
//
//	}
//
//	@Override
//	public void onCancel() {
//		// TODO Auto-generated method stub
//		mHandler.sendEmptyMessage(-3);
//	}
//
//	@Override
//	public void onComplete(Object result) {
//		// TODO Auto-generated method stub
//		System.out.println("授权结果：" + result.toString());
//		if (((JSONObject) result).has("errcode")) {
//			mHandler.sendEmptyMessage(-1);
//		} else if (((JSONObject) result).has("access_token")) {
//			try {
//				openid = ((JSONObject) result).getString("openid");
//				getUserInfo();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				mHandler.sendEmptyMessage(-1);
//			}
//		}
//	}
//
//	@Override
//	public void onError(UiError arg0) {
//		// TODO Auto-generated method stub
//		mHandler.sendEmptyMessage(-1);
//	}
//
//	public void getUserInfo() {
//		UserInfo info = new UserInfo(this, mTencent.getQQToken());
//		info.getUserInfo(new IUiListener() {
//
//			@Override
//			public void onError(UiError arg0) {
//				// TODO Auto-generated method stub
//				mHandler.sendEmptyMessage(-2);
//			}
//
//			@Override
//			public void onComplete(Object arg0) {
//				// TODO Auto-generated method stub
//				System.out.println("QQ信息：" + arg0.toString());
//				try {
//					nickname = ((JSONObject) arg0).getString("nickname");
//					login();
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					mHandler.sendEmptyMessage(-2);
//				}
//			}
//
//			@Override
//			public void onCancel() {
//				// TODO Auto-generated method stub
//				mHandler.sendEmptyMessage(-3);
//			}
//		});
//	}
//	
//	private void login() {
//		pd.setMessage("正在登录...");
//		APIDelegate<RegisterContent> delegate = new APIDelegate<RegisterContent>() {
//
//			@Override
//			public void onResponse(APIResponse<RegisterContent> response) {
//				pd.dismiss();
//				RegisterContent result = response.getcontentObject();
//				if (result == null) {
//					ToastUtil.show(QQEntryActivity.this, "获取用户信息失败");
//					finish();
//					return;
//				}
//				if(result.getError() == 200) {
//					getSharedPreferences(Constants.CareLinker.SHARE_NAME, 0)
//					.edit()
//					.putString("userName", username)
//					.putString("password",
//							StringUtil.getFormatPassword(password))
//					.commit();
//					com.carelinker.pharmacy.v2.model.UserInfo userinfo = new com.carelinker.pharmacy.v2.model.UserInfo(result);
//					userinfo.setUserName(username);
//					DataCenter.getInstance().setUserInfo(userinfo);
//		
//					/* 登录组件在友盟的注册和 登录 */
//					// 如果用户状态为未认证，则不登录友盟社区
//		
//					/*
//					 * if (userinfo.getAuditStatus() == 3 ||
//					 * userinfo.getAuditStatus() == 4) { umengLogin(userinfo); }
//					 */
//					umengLogin(userinfo);
//					Intent intent = new Intent(QQEntryActivity.this,
//							HomeTabActivity.class);
//					startActivityForResult(intent, 0);
//					finish();
//				} else {
//					ToastUtil.show(QQEntryActivity.this, "登录失败");
//					finish();
//				}
//			}
//		};
//		APIRequest<RegisterContent> request = new APIRequest<RegisterContent>(
//				Method.POST, Constants.CareLinker.URL_LOGIN_BY_TP,
//				RegisterContent.class);
//		request.addParameter("tpId", openid);
//		request.addParameter("tpPlatform", 2);
//		request.addParameter("nickName", nickname);
//		request.setDelegate(delegate);
//		APIQueue.getInstance().addRequest(request);
//
//	}
//	
//	public void umengLogin(com.carelinker.pharmacy.v2.model.UserInfo userinfo) {
//
//		// 2015.7.22 by chensn
//
//		// myUserInfo.("https://img.alicdn.com/imgextra/i2/479171363/TB21M8qcpXXXXcBXpXXXXXXXXXX-479171363.jpg");
//
//		com.carelinker.pharmacy.v2.model.UserInfo user = DataCenter.getInstance().getUserInfo();
//		CommUser loginUser = new CommUser();
//
//		loginUser.id = user.getUserId();
//		loginUser.gender = Gender.FEMALE;
//		loginUser.name = UmengUtil.CombineUmengUserName(user);
//		loginUser.iconUrl = user.getHeadImgUrl();
//		System.out
//				.println("login:user.getHeadImgUrl():" + user.getHeadImgUrl());
//
//		// 通过分店ID得到总店ID
//		loginUser.customField = UmengUtil.getMainStoreId(user
//				.getBranchStoreId());
//		MyLoginImpl.setLoginedUser(loginUser);
//		/* 得到用户信息后自动登录 */
//		CLApplication.mCommSDK.login(QQEntryActivity.this,
//				new LoginListener() {
//
//					@Override
//					public void onComplete(int arg0, CommUser commUser) {
//						// TODO Auto-generated method stub
//						System.out.println("得到用户信息后自动登录 " + commUser.iconUrl);
//						/* 判断是否有大V需要自动关注 */
//
//						// 寻找需要默认自动关注的大V
//						fechAuthenticated_user(commUser);
//						// 每次登录前都自动关注一次
//
//					}
//
//					@Override
//					public void onStart() {
//						// TODO Auto-generated method stub
//
//					}
//
//				});
//
//	}
//
//	public String[] fechAuthenticated_user(CommUser loginUser) {
//		// TODO Auto-generated method stub
//
//		APIDelegate<ListBigVContent> bigVDelegate = new APIDelegate<ListBigVContent>() {
//
//			@Override
//			public void onResponse(APIResponse<ListBigVContent> response) {
//				// TODO Auto-generated method stub
//
//				ListBigVContent content = response.getcontentObject();
//
//				if (content != null && content.getError() == 200) {
//					authenList = content.getBigVList();
//					if (authenList != null) {
//						MyPresenter mypresenter = new MyPresenter();
//						mypresenter.attach(QQEntryActivity.this);
//						for (int i = 0; i < authenList.length; i++) {
//							mypresenter.followUser(authenList[i]);
//						}
//					}
//
//				}
//			}
//		};
//
//		APIRequest<ListBigVContent> request = new APIRequest<ListBigVContent>(
//				Method.GET, Constants.CareLinker.BIG_V, ListBigVContent.class);
//		/*
//		 * request.addParameter("userId", DataCenter.getInstance().getUserInfo()
//		 * .getUserId());
//		 */
//
//		request.addParameter("branchStoreId", "2");
//		request.setDelegate(bigVDelegate);
//		APIQueue.getInstance().addRequest(request);
//		// 蓝信康大V
//		return authenList;
//	}
//
// }
