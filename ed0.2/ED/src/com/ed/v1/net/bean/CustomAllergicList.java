package com.ed.v1.net.bean;

import com.ed.v1.net.bean.Content;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * 自定义过敏史
 */
public class CustomAllergicList extends Content {

	@JsonProperty
	private String id;
	@JsonProperty
	private String value;
	@JsonProperty
	private String createDate;
	@JsonProperty
	private String updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}
