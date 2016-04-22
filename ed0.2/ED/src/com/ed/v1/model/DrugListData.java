package com.ed.v1.model;

import java.util.List;

import com.ed.v1.net.bean.Content;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DrugListData extends Content {

	@JsonProperty
	private List<DrugInfo> drugs;
	@JsonProperty
	private String currentVersion;
	public List<DrugInfo> getDrugs() {
		return drugs;
	}
	public void setDrugs(List<DrugInfo> drugs) {
		this.drugs = drugs;
	}
	public String getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	
}
