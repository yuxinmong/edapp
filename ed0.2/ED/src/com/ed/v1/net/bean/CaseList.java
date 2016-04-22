package com.ed.v1.net.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CaseList {
	@JsonProperty
	private String id;
	@JsonProperty
	private String imgUrl;
	@JsonProperty
	private String createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
