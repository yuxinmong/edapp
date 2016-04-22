package com.ed.v1.model;

import com.ed.v1.net.bean.RegisterContent;

public class UserInfo {

	private String userId;
	private String userName;
	private String realName;
	private String password;
	private String patientId1;
	private String patientId2;
	private int roleId;
	private String headImgUrl;
	private String token;
	private int measureTimes;
	private int points;
	private int auditStatus;
	private String branchStoreId;
	private String mainStoreName;
	private String branchStoreName;
	private String voipAccount;
	private String voipPwd;
	public UserInfo() {}
	public UserInfo(RegisterContent content) {
		this.userId = content.getUserId();
		this.password = content.getPassword();
		this.patientId1 = content.getPatientId1();
		this.patientId2 = content.getPatientId2();
		this.roleId = content.getRoleId();
		this.headImgUrl = content.getHeadImgUrl();
		this.token = content.getToken();
		this.realName = content.getPatientName();
		this.measureTimes = content.getMeasureTimes();
		this.points = content.getPoints();
		this.auditStatus = content.getAuditStatus();
		this.mainStoreName = content.getMainStoreName();
		this.branchStoreId = content.getBranchStoreId();
		this.branchStoreName = content.getBranchStoreName();
		this.voipAccount=content.getVoipAccount();
		this.voipPwd=content.getVoipPwd();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
