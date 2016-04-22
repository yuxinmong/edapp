package com.ed.v1.net.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PointsContent extends Content {
	@JsonProperty
	private String userId;
	@JsonProperty
	private String patientId;
	@JsonProperty
	private String content;
	@JsonProperty
	private int type;
	@JsonProperty
	private int point;
	@JsonProperty
	private int status;
	@JsonProperty
	private String createDate;
	@JsonProperty
	private int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
