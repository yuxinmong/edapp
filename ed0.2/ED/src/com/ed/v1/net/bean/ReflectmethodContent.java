package com.ed.v1.net.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReflectmethodContent extends Content {
	@JsonProperty
	private String id;
	@JsonProperty
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsInUse() {
		return isInUse;
	}

	public void setIsInUse(int isInUse) {
		this.isInUse = isInUse;
	}

	@JsonProperty
	private String account;
	@JsonProperty
	private int type;
	@JsonProperty
	private int isInUse;

}
