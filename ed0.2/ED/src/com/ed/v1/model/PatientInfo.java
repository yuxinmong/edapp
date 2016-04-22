package com.ed.v1.model;

import com.ed.v1.net.bean.Content;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientInfo extends Content {

	@JsonProperty
	String patientName;
	@JsonProperty
	String headImgUrl;
	@JsonProperty
	String patientId;
	@JsonProperty
	String telephone;
	@JsonProperty
	String userId;

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
