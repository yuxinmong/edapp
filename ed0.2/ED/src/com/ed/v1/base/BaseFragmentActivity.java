package com.ed.v1.base;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.*;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.ed.v1.BuildConfig;
import com.ed.v1.CLApplication;
import com.ed.v1.R;
import com.ed.v1.common.BroadCastUtil;
import com.ed.v1.common.Log;
import com.ed.v1.common.RequestUtils;
import com.ed.v1.common.StatusBarTintUtil;
import com.ed.v1.common.SystemBarTintManager;
import com.ed.v1.common.ToastUtil;
import com.ed.v1.common.dialog.CommonDialog;
import com.ed.v1.common.viewholder.AutomaticViewHolderUtil;
import com.ed.v1.net.ErrorHelper;
import com.ed.v1.net.JSONDeserializable;
import com.ed.v1.net.api.APIDelegate;
import com.ed.v1.net.api.APIRequest;
import com.ed.v1.net.api.APIResponse;

/**
 * Created by Haijun.Wang on 15/6/24.
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements
		Responsable {

	private NetworkAdapter networkAdapter;

	private int loadingSemaphore;

	private Dialog progressDialog;

	private NetworkStateReceiver networkStateReceiver;

	private boolean enablePageEvent = true;
	private InternalReceiver internalReceiver;
	protected String pageName;
	private View root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pageName = getClass().getSimpleName();
		loadingSemaphore = 0;
		root = getLayoutInflater().inflate(getContentViewId(), null);
		setContentView(root);
		networkAdapter = new NetworkAdapter(this);
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			// getActionBar().setHomeButtonEnabled(true);
			getActionBar().setDisplayHomeAsUpEnabled(true);

		}
		AutomaticViewHolderUtil.findAllViews(this, root);
		try {
			init(savedInstanceState);
		} catch (Exception e) {
			if (BuildConfig.DEBUG) {
				Log.logStackTrace("CL", e);
			}
			hideLoadingDialog(true);
			showAlertDialog(this.getString(R.string.app_run_error));
		}
		registerNetworkReceiver();

	}

	@Override
	protected void onDestroy() {
		hideLoadingDialog(true);
		// networkAdapter.cancel(this);
		try {
			unregisterReceiver(networkStateReceiver);
			unregisterReceiver(internalReceiver);
		} catch (Exception e) {
		}
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		RequestUtils.resetLeaveTime(BaseFragmentActivity.this);
		this.finish();
		super.onBackPressed();
	}

	@Override
	protected void onStop() {
		RequestUtils.leaveTimeCalc(this);
		super.onStop();
	}

	@Override
	protected void onRestart() {
		super.onRestart();

		// if (RequestUtils.isNeedGestureLogin(BaseFragmentActivity.this)) {
		// RequestUtils.activityRedirect(BaseFragmentActivity.this,
		// AccountLoginActivity.class);
		// finish();
		// }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.global, menu);
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		}
		return super.onCreateOptionsMenu(menu);
	}

	protected final void registerReceiver(String[] actionArray) {
		if (actionArray == null) {
			return;
		}
		IntentFilter intentfilter = new IntentFilter();
		for (String action : actionArray) {
			intentfilter.addAction(action);
		}
		if (internalReceiver == null) {
			internalReceiver = new InternalReceiver();
		}
		registerReceiver(internalReceiver, intentfilter);
	}

	private class InternalReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent == null || intent.getAction() == null) {
				return;
			}
			handleReceiver(context, intent);
		}
	}

	protected void handleReceiver(Context context, Intent intent) {
		// 广播处理
		if (intent == null) {
			return;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public final boolean requestFailed(
			APIResponse<? extends JSONDeserializable> response) {

		if (response.isIntermediate() || onRequestFailed(response)) {
			return true;
		}

		// 请求失败，强制关闭loading dialog，避免出现无法关闭的情况
		hideLoadingDialog(true);

		if (response.getResultCode() == APIResponse.ResultCode.Login
				|| response.getResultCode() == APIResponse.ResultCode.TokenIsExpired) {
			ErrorHelper
					.login(this,
							response.getResultCode() == APIResponse.ResultCode.TokenIsExpired);
			return true;
		} else {
			ErrorHelper.prompt(response, this);
			return true;
		}
	}

	protected boolean onRequestFailed(
			APIResponse<? extends JSONDeserializable> response) {
		return false;
	}

	public <T extends JSONDeserializable> void addRequest(
			APIRequest<T> request, APIDelegate<T> delegate) {
		networkAdapter.addRequest(this, request, delegate);
	}

	/**
	 * 显示loading dialog，计数器+1
	 * <p>
	 * 如果出现未处理的请求失败，将强制关闭loading dialog
	 *
	 * @param cancelable
	 */
	public void showLoadingDialog(boolean cancelable) {
		Dialog dialog = getProgressDialog();
		dialog.setCancelable(cancelable);
		if (!this.isFinishing() && !dialog.isShowing()) {
			dialog.show();
		}
		loadingSemaphore++;
	}

	/**
	 * 显示loading dialog，计数器+1
	 * <p>
	 * 如果出现未处理的请求失败，将强制关闭loading dialog
	 */
	public void showLoadingDialog() {
		showLoadingDialog(true);
	}

	/**
	 * 隐藏loading dialog，计数器-1
	 * <p>
	 * 计数器归零则关闭loading dialog
	 */
	public void hideLoadingDialog() {
		hideLoadingDialog(false);
	}

	/**
	 * 强制关闭loading dialog
	 * <p>
	 * 并将计数器归零
	 *
	 * @param forceClose
	 */
	public void hideLoadingDialog(boolean forceClose) {
		loadingSemaphore--;
		if (forceClose || loadingSemaphore < 0) {
			loadingSemaphore = 0;
		}
		if (loadingSemaphore == 0 && progressDialog != null
				&& progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}

	private Dialog getProgressDialog() {
		if (progressDialog == null || !progressDialog.isShowing()) {
			progressDialog = new NormalLoadingDialog(this);
			return progressDialog;
		} else {
			return progressDialog;
		}
	}

	protected abstract int getContentViewId();

	protected abstract void init(Bundle savedInstanceState);

	/**
	 * 需要监视网络状态时可覆盖此方法
	 *
	 * @param active
	 *            true:已重新连接网络
	 */
	protected void onNetworkActive(boolean active) {
		if (active) {
			onReload();
		}
	}

	/**
	 * 当网络恢复连接时，会通过此方法提醒当前界面重新加载，可通过判断当前界面是否已加载来判断是否需要重新请求数据。
	 */
	protected void onReload() {
	}

	@Override
	public void onPause() {
		if (enablePageEvent) {
		}
		super.onPause();

	};

	@Override
	public void onResume() {
		if (enablePageEvent) {
		}
		super.onResume();
	}

	/**
	 * 开启或关闭Activity级别的页面统计（只在onCreate时调用一次）
	 *
	 * @param enable
	 */
	protected void setEnablePageEvent(boolean enable) {
		enablePageEvent = enable;
	}

	private void registerNetworkReceiver() {
		if (networkStateReceiver == null) {
			networkStateReceiver = new NetworkStateReceiver();
			IntentFilter filter = new IntentFilter(
					BroadCastUtil.Action.NETWORK_STATE_CHANGED);
			this.registerReceiver(networkStateReceiver, filter);
		}
	}

	protected void unregisterReceiver() {
		if (networkStateReceiver != null) {
			this.unregisterReceiver(networkStateReceiver);
		}
	}

	public void showAlertDialog(CharSequence msg) {
		showAlertDialog(msg, this.getString(R.string.messagebox_ok),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
	}

	public void showAlertDialog(CharSequence msg, CharSequence btnLabel,
			DialogInterface.OnClickListener listener) {
		CommonDialog dialog = new CommonDialog(this);
		dialog.setTitle(this.getString(R.string.messagebox_warning));
		dialog.setContent(msg);
		dialog.setVisibilityButtons(DialogInterface.BUTTON_POSITIVE);
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, btnLabel);
		dialog.setOnClickListener(listener);
		dialog.show();
	}

	private class NetworkStateReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent data) {
			if (data.getBooleanExtra(
					BroadCastUtil.Action.NETWORK_STATE_CHANGED_EXTRA_AVAILABLE,
					false)) {
				onNetworkActive(true);
			} else {
				onNetworkActive(false);
				ToastUtil.show(BaseFragmentActivity.this,
						R.string.error_no_internet_connectivity);
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected <V extends View> V findView(int id) {
		return (V) findViewById(id);
	}

	@SuppressWarnings("unchecked")
	protected <V extends View> V findView(View parent, int id) {
		return (V) parent.findViewById(id);
	}

	public View getRootView() {
		return root;
	}

	/**
	 * 在这里实现Fragment数据的缓加载.
	 * 
	 * @param isVisibleToUser
	 */

}
