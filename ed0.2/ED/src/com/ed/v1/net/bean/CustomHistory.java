package com.ed.v1.net.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomHistory extends Content {

	@JsonProperty
	private List<CustomAllergicList> history;

	public List<CustomAllergicList> getHistory() {
		return history;
	}

	public void setHistory(List<CustomAllergicList> history) {
		this.history = history;
	}

}
