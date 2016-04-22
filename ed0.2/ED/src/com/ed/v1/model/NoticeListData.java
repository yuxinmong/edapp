package com.ed.v1.model;

import java.util.List;

import com.ed.v1.net.bean.Content;

public class NoticeListData extends Content {

	private List<HomeNotice> pushMessageList;

	public List<HomeNotice> getPushMessageList() {
		return pushMessageList;
	}

	public void setPushMessageList(List<HomeNotice> pushMessageList) {
		this.pushMessageList = pushMessageList;
	}

	
}
