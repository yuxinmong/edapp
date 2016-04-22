package com.ed.v1.net.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterContent extends Content {

	@JsonProperty
	private String userId;
	@JsonProperty
	private String password;
	@JsonProperty
	private String patientId1;
	@JsonProperty
	private String patientId2;
	@JsonProperty
	private int roleId;
	@JsonProperty
	private String headImgUrl;
	@JsonProperty
	private String token;
	@JsonProperty
	private String patientName;
	@JsonProperty
	private int measureTimes;
	@JsonProperty
	private int points;
	@JsonProperty
	private int auditStatus;
	@JsonProperty
	private String branchStoreId;
	@JsonProperty
	private String mainStoreName;
	@JsonProperty
	private String branchStoreName;
	@JsonProperty
	private String voipAccount;
	@JsonProperty
	private String voipPwd;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPatientId1() {
		return patientId1;
	}
	public void setPatientId1(String patientId1) {
		this.patientId1 = patientId1;
	}
	public String getPatientId2() {
		return patientId2;
	}
	public void setPatientId2(String patientId2) {
		this.patientId2 = patientId2;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getMeasureTimes() {
		return measureTimes;
	}
	public void setMeasureTimes(int measureTimes) {
		this.measureTimes = measureTimes;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getBranchStoreId() {
		return branchStoreId;
	}
	public void setBranchStoreId(String branchStoreId) {
		this.branchStoreId = branchStoreId;
	}
	public String getMainStoreName() {
		return mainStoreName;
	}
	public void setMainStoreName(String mainStoreName) {
		this.mainStoreName = mainStoreName;
	}
	public String getBranchStoreName() {
		return branchStoreName;
	}
	public void setBranchStoreName(String branchStoreName) {
		this.branchStoreName = branchStoreName;
	}
	public void setVoipAccount(String voipAccount) {
		this.voipAccount = voipAccount;
	}
	public String getVoipAccount() {
		return voipAccount;
	}
	public void setVoipPwd(String voipPwd) {
		this.voipPwd = voipPwd;
	}
	public String getVoipPwd() {
		return voipPwd;
	}
	
}
