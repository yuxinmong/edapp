package com.ed.v1.net.bean;

import java.util.ArrayList;

public class OrderGoodsBean {
	
	private int photo;
	private String desc;
	private String account;
	private String place;
	private String name;
	private String type;
	private String dimens;
	private String extraMoney;
	private String newMoney;
	private String oldMoney;
	private ArrayList<OrderDetailBean> detailBean;
	
	public OrderGoodsBean(int photo, String desc, String account, String place,
			String name, String type, String dimens, String extraMoney,
			String newMoney, String oldMoney,ArrayList<OrderDetailBean> detailBean) {
		super();
		this.photo = photo;
		this.desc = desc;
		this.account = account;
		this.place = place;
		this.name = name;
		this.type = type;
		this.dimens = dimens;
		this.extraMoney = extraMoney;
		this.newMoney = newMoney;
		this.oldMoney = oldMoney;
		this.detailBean = detailBean;
	}



	public int getPhoto() {
		return photo;
	}
	public void setPhoto(int photo) {
		this.photo = photo;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDimens() {
		return dimens;
	}
	public void setDimens(String dimens) {
		this.dimens = dimens;
	}
	public String getExtraMoney() {
		return extraMoney;
	}
	public void setExtraMoney(String extraMoney) {
		this.extraMoney = extraMoney;
	}
	public String getNewMoney() {
		return newMoney;
	}
	public void setNewMoney(String newMoney) {
		this.newMoney = newMoney;
	}
	public String getOldMoney() {
		return oldMoney;
	}
	public void setOldMoney(String oldMoney) {
		this.oldMoney = oldMoney;
	}

	public ArrayList<OrderDetailBean> getDetailBean() {
		return detailBean;
	}

	public void setDetailBean(ArrayList<OrderDetailBean> detailBean) {
		this.detailBean = detailBean;
	}
	

}
