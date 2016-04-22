package com.ed.v1.base;

import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.*;

import com.ed.v1.BuildConfig;
import com.ed.v1.R;
import com.ed.v1.common.BroadCastUtil;
import com.ed.v1.common.Log;
import com.ed.v1.common.ToastUtil;
import com.ed.v1.common.usagetracker.PageName;
import com.ed.v1.common.viewholder.AutomaticViewHolder;
import com.ed.v1.common.viewholder.AutomaticViewHolderUtil;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.net.ErrorHelper;
import com.ed.v1.net.JSONDeserializable;
import com.ed.v1.net.api.APIDelegate;
import com.ed.v1.net.api.APIRequest;
import com.ed.v1.net.api.APIResponse;


/**
 * Created by Haijun.Wang on 15/6/24.
 */
public abstract class BaseFragment extends Fragment implements Responsable {

    private final String PAGE_NAME;

    private String title;

    private View root;

    private NetworkAdapter networkAdapter;

    private LoadingViewHolder loadingViewHolder;

    private NetworkStateReceiver networkStateReceiver;

    private boolean enablePageEvent = false;
    /** Fragment当前状态是否可见 */  
    protected boolean isVisible;  
    private InternalReceiver internalReceiver;

    public BaseFragment() {
        Class<? extends BaseFragment> clazz = getClass();
        PageName pageName = clazz.getAnnotation(PageName.class);
        if (pageName != null) {
            PAGE_NAME = pageName.value();
        } else {
            PAGE_NAME = clazz.getSimpleName();
        }
    }

    protected View getRoot() {
        return root;
    }

    public void setEnablePageEvent(boolean enablePageEvent) {
        this.enablePageEvent = enablePageEvent;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        networkAdapter = new NetworkAdapter(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        resetActionBarOptions();
        if (getTitle() != null) {
            setActivityTitle(getTitle());
        }
    }

    public class InternalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent == null || intent.getAction() == null ) {
                return ;
            }
            handleReceiver(context, intent);
        }
    }
    /**
     * 重置Actionbar的设置，Fragment可在调用super.onCreateOpntionsMenu()之后变更设置，或是覆盖此方法来自定义Actionbar
     */
    protected void resetActionBarOptions() {
        getActionBar()
                .setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_HOME);
    }

    @Override
    public void onDetach() {
        networkAdapter.cancel(this);
        super.onDetach();
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.base_fragment, container, false);
        inflater.inflate(this.getContentViewId(), (ViewGroup) findView(root, R.id.rootContainer), true);
        return root;
    }

    @Override
    public final void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingViewHolder = new LoadingViewHolder(findView(R.id.loadingView));
        AutomaticViewHolderUtil.findAllViews(this, root);
        try {
            init(savedInstanceState);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.logStackTrace("CL", e);
            }
            hideLoading();
            hideActivityLoading(true);
            getRealActivity().showAlertDialog(this.getString(R.string.app_run_error));
        }
        registerNetworkReceiver();
    }

    @Override
    public final boolean requestFailed(APIResponse<? extends JSONDeserializable> response) {
        if (response.isIntermediate() || onRequestFailed(response)) {
            return true;
        }

        hideLoading();
        // 发生请求失败，同时关闭loading dialog
        hideActivityLoading(true);

        if (response.getResultCode() == APIResponse.ResultCode.Login || response.getResultCode() == APIResponse.ResultCode.TokenIsExpired) {
            ErrorHelper.login(this.getActivity(), response.getResultCode() == APIResponse.ResultCode.TokenIsExpired);
            return true;
        } else {// 避免从Fragment弹出过多网络状态的dialog，这里只使用Toast
            String errString = null;
            if (response.getException() != null) {
                errString = ErrorHelper.getMessage(response.getException(), getActivity());
            }
            if (errString == null) {
                errString = response.getError();
            }
            if (errString == null || errString.equals("")) {
                errString = this.getString(R.string.error_no_response) ;
            }
            if (isVisible()) {
                ToastUtil.show(getActivity(), errString);
            }
            Log.d("API", errString);
            return true;
        }
    }

    /**
     * 如果要自行处理请求失败，请返回true
     *
     * @param response
     * @return 返回true，BaseFragment将不再处理失败，返回false将把失败交由BaseFragment处理
     */
    public boolean onRequestFailed(APIResponse<? extends JSONDeserializable> response) {
        return false;
    }

    protected <T extends JSONDeserializable> void addRequest(APIRequest<T> request, APIDelegate<T> delegate) {
        networkAdapter.addRequest(this, request, delegate);
    }

    protected abstract int getContentViewId();

    protected abstract void init(Bundle savedInstanceState);

    protected int getTitleId() {
        return 0;
    }

    protected final String getTitle() {
        if (title != null) {
            return title;
        } else {
            return getTitleId() == 0 ? null : getString(getTitleId());
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitle(int title) {
        this.title = getString(title);
    }

    /**
     * 当网络恢复连接时，会通过此方法提醒当前界面重新加载，可通过判断当前界面是否已加载来判断是否需要重新请求数据。
     */
    protected void onReload() {
    }

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

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
        } else {
        }
        super.onHiddenChanged(hidden);
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
        if (reloadViewIsShowing()) {
            onReload();
            showLoading();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(networkStateReceiver!=null){
        unRegisterNetworkReceiver();}
    }

    private void registerNetworkReceiver() {
        if (networkStateReceiver == null) {
            networkStateReceiver = new NetworkStateReceiver();
            IntentFilter filter = new IntentFilter(BroadCastUtil.Action.NETWORK_STATE_CHANGED);
            this.getActivity().registerReceiver(networkStateReceiver, filter);
        }
    }

    private void unRegisterNetworkReceiver() {
        if (networkStateReceiver != null) {
        	 try {
            this.getActivity().unregisterReceiver(networkStateReceiver);
        	 } catch (Exception e) {
             }
        }
    }

    protected void showActivityLoading() {
        getRealActivity().showLoadingDialog();
    }

    protected void showActivityLoading(boolean cancelable) {
        getRealActivity().showLoadingDialog(cancelable);
    }

    protected void hideActivityLoading() {
        getRealActivity().hideLoadingDialog();
    }

    protected void hideActivityLoading(boolean forceClose) {
        getRealActivity().hideLoadingDialog(forceClose);
    }

    /**
     * 显示加载中的提示，会自动隐藏重新加载按钮
     */
    protected void showLoading() {
        loadingViewHolder.showProgress();
    }

    /**
     * 会将加载中和重新加载按钮一同隐藏
     */
    protected void hideLoading() {
        loadingViewHolder.hide();
    }

    /**
     * 显示重新加载按钮，会自动隐藏加载中的提示
     */
    protected void showReloadView() {
        loadingViewHolder.showReloadView();
    }

    /**
     * 会将加载中和重新加载按钮一同隐藏
     */
    protected void hideReloadView() {
        loadingViewHolder.hide();
    }

    protected boolean reloadViewIsShowing() {
        return loadingViewHolder.reloadViewIsShowing();
    }

    private class LoadingViewHolder extends AutomaticViewHolder implements View.OnClickListener {

        @Res(R.id.progressView)
        private View progressView;
        @Res(R.id.btnReload)
        private View btnReload;

        public LoadingViewHolder(View root) {
            super(root);
            btnReload.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onReload();
        }

        void showProgress() {
            show();
            progressView.setVisibility(View.VISIBLE);
            btnReload.setVisibility(View.GONE);
        }

        void showReloadView() {
            show();
            btnReload.setVisibility(View.VISIBLE);
            progressView.setVisibility(View.GONE);
        }

        boolean reloadViewIsShowing() {
            return root.getVisibility() == View.VISIBLE && btnReload.getVisibility() == View.VISIBLE;
        }

    }

    private class NetworkStateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent data) {
            if (data.getBooleanExtra(BroadCastUtil.Action.NETWORK_STATE_CHANGED_EXTRA_AVAILABLE, false)) {
                onNetworkActive(true);
            } else {
                onNetworkActive(false);
            }
        }
    }

    // ==========utils============

    protected ActionBar getActionBar() {
        return getActivity().getActionBar();
    }

    protected void setActivityTitle(int titleId) {
        getActivity().getActionBar().setTitle(titleId);
    }

    protected void setActivityTitle(CharSequence title) {
        getActivity().getActionBar().setTitle(title);
    }

    protected BaseFragmentActivity getRealActivity() {
        return (BaseFragmentActivity) getActivity();
    }

    @SuppressWarnings("unchecked")
    protected <F extends BaseFragment> F findChildFragment(int id) {
        return (F) getChildFragmentManager().findFragmentById(id);
    }

    @SuppressWarnings("unchecked")
    protected <F extends BaseFragment> F findChildFragment(String tag) {
        return (F) getChildFragmentManager().findFragmentByTag(tag);
    }

    @SuppressWarnings("unchecked")
    protected <V extends View> V findView(int id) {
        return (V) root.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    protected <V extends View> V findView(View parent, int id) {
        return (V) parent.findViewById(id);
    }

	public void setActionBarActivity(FragmentActivity activity) {
		// TODO Auto-generated method stub
		
	}

	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		
	}


	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if(getActivity().onKeyDown(keyCode, event)) {
	            return true;
	        }
	        return getActivity().onKeyDown(keyCode, event);
	    }

		public boolean isEnableRightSlideGesture() {
			// TODO Auto-generated method stub
			return true;
		}
		
		protected void handleReceiver(Context context, Intent intent) {
			// TODO Auto-generated method stub
			   if(intent == null ) {
		            return ;
		        }
		}

	      
	
	    /** 
	     * 在这里实现Fragment数据的缓加载. 
	     * @param isVisibleToUser 
	     */  
	    @Override  
	    public void setUserVisibleHint(boolean isVisibleToUser) {  
	        super.setUserVisibleHint(isVisibleToUser);  
	        if(getUserVisibleHint()) {  
	            isVisible = true;  
	            onVisible();  
	        } else {  
	            isVisible = false;  
	            onInvisible();  
	        }  
	    }  
	  
	    protected void onVisible(){  
	        lazyLoad();  
	    }  
	  
	    protected abstract void lazyLoad();  
	  
	    protected void onInvisible(){} 
	   

}
