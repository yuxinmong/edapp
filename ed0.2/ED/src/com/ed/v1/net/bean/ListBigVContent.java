package com.ed.v1.net.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListBigVContent extends Content {
	@JsonProperty
	String[] bigVList;

	public String[] getBigVList() {
		return bigVList;
	}

	public void setBigVList(String[] bigVList) {
		this.bigVList = bigVList;
	}
}
