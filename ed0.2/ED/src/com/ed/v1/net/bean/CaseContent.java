package com.ed.v1.net.bean;

import java.util.List;

import com.ed.v1.net.bean.Content;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CaseContent extends Content {
	@JsonProperty
	private List<CaseList> patientImgList;

	public List<CaseList> getPatientImgList() {
		return patientImgList;
	}

	public void setPatientImgList(List<CaseList> patientImgList) {
		this.patientImgList = patientImgList;
	}

}
