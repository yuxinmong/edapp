package com.ed.v1.net.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.ed.v1.net.JSONDeserializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FriendContent implements JSONDeserializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty
	private String userId;
	@JsonProperty
	private String voipAccount;
	@JsonProperty
	private String telephone;
	@JsonProperty
	private String patientName;
	@JsonProperty
	private String userName;
	@JsonProperty
	private String headImgUrl;
	@JsonProperty
	private String tags;
	private String header;

	@Override
	public void deserialize(JSONObject o) throws JSONException {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setVoipAccount(String voipAccount) {
		this.voipAccount = voipAccount;
	}

	public String getVoipAccount() {
		return voipAccount;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTags() {

		return tags;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
}
