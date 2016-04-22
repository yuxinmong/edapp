package com.ed.v1.model;

import java.util.List;

import com.ed.v1.net.bean.Content;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientTakeDrugData extends Content {

	@JsonProperty
	private List<TakeDrug> patientDrugList;

	public List<TakeDrug> getPatientDrugList() {
		return patientDrugList;
	}

	public void setPatientDrugList(List<TakeDrug> patientDrugList) {
		this.patientDrugList = patientDrugList;
	}
	
}
