package com.ed.v1.datacenter;

import java.util.List;

import com.ed.v1.model.DrugInfo;
import com.ed.v1.model.PatientInfo;
import com.ed.v1.model.UserInfo;
import com.ed.v1.net.bean.FriendContent;

public class DataCenter {

	private static DataCenter instance;
	private UserInfo userInfo;
	private PatientInfo patientInfo;
	private List<DrugInfo> drugs;
	private List<FriendContent> friendList;
	private Boolean isDoService = false;

	private DataCenter() {
	}

	public static DataCenter getInstance() {
		if (instance == null) {
			instance = new DataCenter();
		}
		return instance;
	}

	public UserInfo getUserInfo() {
		if (userInfo == null) {
			userInfo = new UserInfo();
		}
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public void clear() {
		userInfo = null;
	}

	public PatientInfo getPatientInfo() {
		if (patientInfo == null) {
			patientInfo = new PatientInfo();
		}
		return patientInfo;
	}

	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
	}

	public List<DrugInfo> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<DrugInfo> drugs) {
		this.drugs = drugs;
	}

	public List<FriendContent> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<FriendContent> friendList) {
		this.friendList = friendList;
	}

	public void setIsDoService(Boolean isDoService) {
		this.isDoService = isDoService;
	}

	public Boolean getIsDoService() {
		return isDoService;
	}
}
