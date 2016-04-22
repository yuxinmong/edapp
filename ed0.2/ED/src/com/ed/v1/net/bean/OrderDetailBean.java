package com.ed.v1.net.bean;

public class OrderDetailBean {
	
	private int Photo;
	private String price;
	private String label;
	
	public OrderDetailBean(int photo, String price, String label) {
		super();
		Photo = photo;
		this.price = price;
		this.label = label;
	}
	public int getPhoto() {
		return Photo;
	}
	public void setPhoto(int photo) {
		Photo = photo;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	

}
