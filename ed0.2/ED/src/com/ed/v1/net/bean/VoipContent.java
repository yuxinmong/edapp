package com.ed.v1.net.bean;

import com.ed.v1.net.JSONDeserializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Haijun.Wang on 15/6/24.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoipContent implements JSONDeserializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private int error;
    @JsonProperty
    private String msg;
    @JsonProperty
    private boolean success;
	@JsonProperty
	private String voipAccount;
	@JsonProperty
	private String voipPwd;
    @Override
    public void deserialize(JSONObject o) throws JSONException {

    }

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	public void setVoipAccount(String voipAccount) {
		this.voipAccount = voipAccount;
	}
	public String getVoipAccount() {
		return voipAccount;
	}
	public void setVoipPwd(String voipPwd) {
		this.voipPwd = voipPwd;
	}
	public String getVoipPwd() {
		return voipPwd;
	}
}
