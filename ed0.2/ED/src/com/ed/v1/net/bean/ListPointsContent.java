package com.ed.v1.net.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListPointsContent extends Content {
	@JsonProperty
	private List<PointsContent> employeePointList;

	public List<PointsContent> getListPointsContent() {
		return employeePointList;
	}

	public void setListPointsContent(List<PointsContent> employeePointList) {
		this.employeePointList = employeePointList;
	}

}
