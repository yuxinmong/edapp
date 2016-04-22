package com.ed.v1.net.api;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.ed.v1.common.Constants;
import com.ed.v1.net.JSONDeserializable;
import com.ed.v1.net.JSONHelper;
import com.ed.v1.net.URLChooser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class APIRequest<T extends JSONDeserializable> {

	public static final String PARAM_PAGE_INDEX = "pageIndex";
	public static final String PARAM_PAGE_SIZE = "pageSize";

	private String url;
	private Map<String, String> parameters = new HashMap<String, String>();
	private Map<String, String> restParams = new HashMap<String, String>();
	private APIDelegate<T> delegate;
	private Class<T> cls;
	private int method;

	private Object tag;

	private boolean isMultipart = false;

	private boolean alwaysInformCache;

	private boolean shouldCache = true;

	private boolean isCollectionRequest;

	// 防止反复token login死循环
	private int reLoginCount;

	public void setAlwaysInformCache(boolean alwaysInformCache) {
		this.alwaysInformCache = alwaysInformCache;
	}

	public boolean alwaysInformCache() {
		return alwaysInformCache;
	}

	public void setShouldCache(boolean shouldCache) {
		this.shouldCache = shouldCache;
	}

	public boolean shouldCache() {
		return shouldCache;
	}

	public APIRequest(String url, Class<T> cls) {
		this.method = Method.POST;
		this.url = url;
		this.cls = cls;
		addParameter("platform", "android");
		addParameter("versionType", Constants.CareLinker.APP_TYPE);
		addParameter("versionCode", Constants.CareLinker.VERSION);
	}

	public APIRequest(int method, String url, Class<T> cls) {
		this.method = method;
		this.url = url;
		this.cls = cls;
		addParameter("platform", "android");
		addParameter("versionType", Constants.CareLinker.APP_TYPE);
		addParameter("versionCode", Constants.CareLinker.VERSION);
	}

	public String getURL() {
		return this.url;
	}

	public String getAbsoluteURL() {
		String url = getURL();
		for (Map.Entry<String, String> entry : restParams.entrySet()) {
			url = url.replace(entry.getKey(), entry.getValue());
		}
		if (url.endsWith(".json")) {
			url += "?1";
		}
		if (getMethod() == Method.GET || getMethod() == Method.DELETE) {
			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				if (url.contains("?")) {
					url += "&" + entry.getKey() + "=" + entry.getValue();
				} else {
					url += "?" + entry.getKey() + "=" + entry.getValue();
				}
			}
		}
		return URLChooser.buildURL(url);
	}

	public void setDelegate(APIDelegate<T> delegate) {
		this.delegate = delegate;
	}

	public APIDelegate<T> getDelegate() {
		return this.delegate;
	}

	public Map<String, String> getParameters() {
		return this.parameters;
	}

	public APIRequest<T> addRestParameter(String key, String value) {
		restParams.put(key, value);
		return this;
	}

	public APIRequest<T> addParameters(Map<String, String> parameters) {
		for (String key : parameters.keySet()) {
			String value = parameters.get(key);
			this.parameters.put(key, value);
		}
		return this;
	}

	public APIRequest<T> addParameter(String key, String value) {
		this.parameters.put(key, value);
		return this;
	}

	public APIRequest<T> addParameter(String key, boolean value) {
		this.addParameter(key, Boolean.toString(value));
		return this;
	}

	public APIRequest<T> addParameter(String key, int value) {
		this.addParameter(key, Integer.toString(value));
		return this;
	}

	public APIRequest<T> addParameter(String key, long value) {
		this.addParameter(key, Long.toString(value));
		return this;
	}

	public APIRequest<T> addParameter(String key, double value) {
		this.addParameter(key, Double.toString(value));
		return this;
	}

	public APIResponse<T> createResponse() {
		return new APIResponse<T>(this) {
			@Override
			protected Object buildContent(JSONObject content)
					throws JSONException {
				try {
					T contentObject = JSONHelper.jsonParser.readValue(
							content.toString(), cls);
					return contentObject;
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}

	public HashMap<String, String> getHeaders() {
		return null;
	}

	public void onResponse(APIResponse<T> response) {
	}

	public int getMethod() {
		return method;
	}

	/**
	 * 用于标记某Request 是使用在列表中的，以帮助BaseListFragment正确显示。
	 *
	 * @param isCollectionRequest
	 */
	public void isCollectionRequest(boolean isCollectionRequest) {
		this.isCollectionRequest = isCollectionRequest;
	}

	public boolean isCollectionRequest() {
		return isCollectionRequest;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}

	public Object getTag() {
		return tag;
	}

	public void setReLoginCount(int reLoginCount) {
		this.reLoginCount = reLoginCount;
	}

	public int getReLoginCount() {
		return reLoginCount;
	}
}
