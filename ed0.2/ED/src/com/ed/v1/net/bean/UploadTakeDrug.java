package com.ed.v1.net.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadTakeDrug extends Content {

	@JsonProperty
	private String patientDrugId;

	public String getPatientDrugId() {
		return patientDrugId;
	}

	public void setPatientDrugId(String patientDrugId) {
		this.patientDrugId = patientDrugId;
	}
	
}
