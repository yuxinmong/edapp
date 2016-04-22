package com.ed.v1.model;

import com.ed.v1.net.bean.AgeSexContent;

public class AgeSexInfo {
	private String sex;
	private String birthday;

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

	public AgeSexInfo() {
	}

	public AgeSexInfo(AgeSexContent content) {
		this.sex = content.getSex();
		this.birthday = content.getBirthday();

	}

}
