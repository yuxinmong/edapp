package com.ed.v1.net.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgeSexContent extends Content {
	@JsonProperty
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@JsonProperty
	private String birthday;
}
