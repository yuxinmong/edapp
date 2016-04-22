package com.ed.v1.net.bean;

import java.util.List;

import com.ed.v1.net.bean.Content;
import com.ed.v1.net.bean.HistoryListContent;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileData extends Content {

	@JsonProperty
	private List<HistoryListContent> history;

	public List<HistoryListContent> getHistory() {
		return history;
	}

	public void setHistory(List<HistoryListContent> history) {
		this.history = history;
	}

}
