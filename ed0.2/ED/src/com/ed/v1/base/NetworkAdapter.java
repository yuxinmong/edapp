package com.ed.v1.base;

/**
 * Created by Haijun.Wang on 15/6/28.
 */

import com.ed.v1.net.JSONDeserializable;
import com.ed.v1.net.api.APIDelegate;
import com.ed.v1.net.api.APIQueue;
import com.ed.v1.net.api.APIRequest;
import com.ed.v1.net.api.APIResponse;

import android.support.v4.app.FragmentActivity;

public class NetworkAdapter {
    private FragmentActivity activity;
    private BaseFragment fragment;

    public NetworkAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    public NetworkAdapter(BaseFragment fragment) {
        this.fragment = fragment;
        this.activity = fragment.getActivity();
    }

    public FragmentActivity getActivity() {
        return this.activity;
    }

    void cancel(Responsable tag) {
        APIQueue.getInstance().cancel(tag);
    }

    public <T extends JSONDeserializable> void addRequest(Object tag, APIRequest<T> request, APIDelegate<T> delegate) {
//        request.setDelegate(new DelegateProxy<T>(delegate));
    	request.setDelegate(delegate);
        APIQueue.getInstance().addRequest(tag, request);
    }

    public class DelegateProxy<T extends JSONDeserializable> implements APIDelegate<T> {

        private APIDelegate<T> delegate;

        public DelegateProxy(APIDelegate<T> delegate) {
            this.delegate = delegate;
        }

        @Override
        public void onResponse(APIResponse<T> response) {
//            try {
//                if (response == null || response.isFailed()) {
//                    if (fragment != null) {
//                        NetworkAdapter.this.fragment.requestFailed(response);
//                    } else {
//                        NetworkAdapter.this.activity.requestFailed(response);
//                    }
//                } else 
//            	if (delegate != null) {
//                    this.delegate.onResponse(response);
//                }
//            } catch (Exception e) {
//                MobclickAgent.reportError(getActivity(), e);
//                if (activity == null) {
//                    fragment.hideLoading();
//                }
//                getActivity().hideLoadingDialog(true);
//                if (BuildConfig.DEBUG && response.getRequest() != null) {
//                    ToastUtil.show(getActivity(), getActivity().getString(R.string.refreshlistview_error)  + response.getRequest().getURL());
//                    Log.d("API", "数据加载失败! url: " + response.getRequest().getURL() + "\n" + response.getJSONContent());
//                } else {
//                    ToastUtil.show(getActivity(), getActivity().getString(R.string.refreshlistview_error));
//                }
//            }
        }

    }

    /**
     * 添加一组Request，以并行或串行的方式执行
     *
     * @param tag
     * @param groupDelegate
     * @param serial          true : 串行
     * @param requestPartners
     */
    void addRequestGroup(String tag, GroupDelegate groupDelegate, boolean serial, RequestPartner<JSONDeserializable>... requestPartners) {
        RequestGroup requestGroup = new RequestGroup(tag, requestPartners, serial, groupDelegate);
        requestGroup.excute();
    }

    public class RequestGroup extends APIRequest<JSONDeserializable> {

        private Object tag;
        private int successAcount;
        private int index;
        private RequestPartner<JSONDeserializable>[] requests;
        private boolean serial;
        private GroupDelegate groupDelegate;

        public RequestGroup(Object tag, RequestPartner<JSONDeserializable>[] requests, boolean serial, GroupDelegate groupDelegate) {
            super(null, null);
            if (requests == null || requests.length == 0) {
                throw new IllegalArgumentException("requests is empty.");
            }
            this.tag = tag;
            this.requests = requests;
            this.serial = serial;
            this.groupDelegate = groupDelegate;
        }

        void excute() {
            if (serial) {
                addRequestPartner(requests[0]);
            } else {
                for (RequestPartner<JSONDeserializable> requestPartner : requests) {
                    addRequestPartner(requestPartner);
                }
            }
        }

        @Override
        public void onResponse(APIResponse<JSONDeserializable> response) {
            if (!response.isIntermediate()) {
                successAcount++;
                if (index < requests.length) {
                    if (serial) {
                        addRequestPartner(requests[index++]);
                    }
                } else {
                    groupDelegate.onResponse(this);
                }
            }
        }

        private void addRequestPartner(final RequestPartner<JSONDeserializable> requestPartner) {

            addRequest(tag, requestPartner.request, new DelegateProxy<JSONDeserializable>(requestPartner.delegate) {
                @Override
                public void onResponse(APIResponse<JSONDeserializable> response) {
                    super.onResponse(response);
                    requestPartner.setResponse(response);
                    RequestGroup.this.onResponse(response);
                }
            });
        }

        public boolean isSuccess() {
            return successAcount == requests.length;
        }

        public RequestPartner<JSONDeserializable>[] getRequests() {
            return requests;
        }

        /**
         * 使用url进行查找，如果请求的一组请求中有重复的url，将返回最靠前的一个
         *
         * @param api
         * @return
         */
        @SuppressWarnings("unchecked")
        public <T extends JSONDeserializable> APIResponse<T> getResponse(String api) {
            for (RequestPartner<? extends JSONDeserializable> requestPartner : requests) {
                if (requestPartner.request.getURL().equals(api)) {
                    try {
                        APIResponse<T> response = (APIResponse<T>) requestPartner.getResponse();
                        return response;
                    } catch (Exception e) {
                        // nothing
                    }
                }
            }
            return null;
        }
    }

    public static class RequestPartner<T extends JSONDeserializable> {

        private APIRequest<T> request;
        private APIDelegate<T> delegate;
        private APIResponse<T> response;

        public RequestPartner(APIRequest<T> request, APIDelegate<T> delegate) {
            this.request = request;
            this.delegate = delegate;
        }

        private void setResponse(APIResponse<T> response) {
            this.response = response;
        }

        public APIResponse<T> getResponse() {
            return response;
        }
    }

    public static interface GroupDelegate {
        void onResponse(RequestGroup requestGroup);
    }
}
