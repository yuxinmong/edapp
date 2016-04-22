package com.ed.v1.net.api;

import com.android.volley.*;
import com.ed.v1.net.JSONDeserializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class APIResponse<T extends JSONDeserializable> {

    public static enum ResultCode {
        // server returned result code
        Success,
        Login,
        Error,
        ServiceDisabled,
        SessionTimeout,

        // other result code
        Timeout,
        NoConnection,
        Exception,
        Unknown,

        // token is expired
        TokenIsExpired
    }

    private APIRequest<T> request;
    private String response;
    private ResultCode resultCode;
    private String[] errors;
    private String[] messages;
    private JSONObject content;
    private Throwable exception;
    private Object contentObject;
    private boolean intermediate;
    private String sid;

    public APIResponse(APIRequest<T> request) {
        this.resultCode = ResultCode.Unknown;
        this.request = request;
    }

    public void isIntermediate(boolean intermediate) {
        this.intermediate = intermediate;
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public APIRequest<T> getRequest() {
        return this.request;
    }

    public boolean isResponseOfAPI(String api) {
        return this.request.getURL().equalsIgnoreCase(api);
    }

    public String getRawResponse() {
        return this.response;
    }

    public boolean isSuccess() {
        return this.resultCode == ResultCode.Success;
    }

    public boolean isFailed() {
        return !this.isSuccess();
    }

    public ResultCode getResultCode() {
        return this.resultCode;
    }

    public String getError() {
        if (this.errors == null) {
            return "";
        }

        if (this.errors.length == 1) {
            return this.errors[0];
        }

        StringBuilder builder = new StringBuilder();
        boolean first = true;

        for (String error : this.errors) {
            if (!first) {
                builder.append("\n");
            }

            builder.append(error);
            first = false;
        }

        return builder.toString();
    }

    public String[] getMessages() {
        return this.messages;
    }

    public JSONObject getJSONContent() {
        return this.content;
    }

    @SuppressWarnings("unchecked")
    public <M extends JSONDeserializable> M getContentObjectAs(Class<M> u) {
        if (this.isSuccess()) {
            try {
                return (M) this.contentObject;
            } catch (ClassCastException e) {
                return null;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public T getcontentObject() {
        return (T) contentObject;
    }

    public Throwable getException() {
        return this.exception;
    }

    public void parseException(Throwable exception) {

        if (exception == null) {
            return;
        }

        this.exception = exception;

        if (exception instanceof TimeoutError) {
            this.resultCode = ResultCode.Timeout;
            return;
        }

        if (exception instanceof NetworkError || exception instanceof NoConnectionError) {
            this.resultCode = ResultCode.NoConnection;
            return;
        }

        if (exception instanceof VolleyError) {

            NetworkResponse response = ((VolleyError) exception).networkResponse;

            if (response != null) {
                if (response.statusCode == 401) {
                    this.resultCode = ResultCode.Login;
                    return;
                }
            }
        }

        this.resultCode = ResultCode.Exception;
    }

    public void parseResponse(String response) throws JSONException {

        if (response == null) {
            return;
        }

        this.response = response;

        JSONObject o = new JSONObject(this.response);

        sid = o.optString(sid);

        String result = o.optString("success");

        if (result == null) {
            this.resultCode = ResultCode.Unknown;
        } else if (result.equals("true")) {
            this.resultCode = ResultCode.Success;
        } else if (result.equals("error")) {
            this.resultCode = ResultCode.Error;
        } else if (result.equals("login")) {
            this.resultCode = ResultCode.Login;
        } else if (result.equals("service_disabled")) {
            this.resultCode = ResultCode.ServiceDisabled;
        } else if (result.equals("session_timeout")) {
            this.resultCode = ResultCode.SessionTimeout;
        }  else {
            this.resultCode = ResultCode.Unknown;
        }

        JSONArray errors = o.optJSONArray("errors");
        if (errors != null) {
            this.errors = new String[errors.length()];
            for (int i = 0; i < this.errors.length; ++i) {
                this.errors[i] = errors.getString(i);
            }
        } else {
            this.errors = new String[0];
        }

        this.contentObject = this.buildContent(o);
    }

    protected String getSid() {
        return sid;
    }

    protected abstract Object buildContent(JSONObject content) throws JSONException;
}
