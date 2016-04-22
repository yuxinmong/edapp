package com.ed.v1.model;

import java.io.Serializable;

import com.ed.v1.net.bean.Content;
import com.fasterxml.jackson.annotation.JsonProperty;

//@DatabaseTable(tableName = "t_takedrug")
public class TakeDrug extends Content implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1283717163458153905L;

	@JsonProperty
	private String drugName;
	@JsonProperty
	private String patientId;
	@JsonProperty
	private String patientDrugId;
	@JsonProperty
	private int timesPerDay;// 1天次数
	@JsonProperty
	private double pillsPerTime0;// 数量 上午
	@JsonProperty
	private double pillsPerTime1;// 数量 中午
	@JsonProperty
	private double pillsPerTime2;// 数量 下午
	@JsonProperty
	private double pillsPerTime3;
	@JsonProperty
	private double pillsPerTime4;
	@JsonProperty
	private String remindTime0;// 時間 上午
	@JsonProperty
	private String remindTime1;// 时间 中午
	@JsonProperty
	private String remindTime2;// 时间 下午
	@JsonProperty
	private String remindTime3;// 时间 下午
	@JsonProperty
	private String remindTime4;// 时间 下午
	@JsonProperty
	private String spec;// 规格
	@JsonProperty
	private String productPlace;// 产地
	@JsonProperty
	private int drugType;// 药品类型 0：用户输入 1：药品列表

	@JsonProperty
	private String createDate;// 创建时间
	@JsonProperty
	private String updateDate;// 更新时间
	@JsonProperty
	private int buyNum;// 购买数量
	@JsonProperty
	private String drugImgUrl;// 药物图片的URL
	@JsonProperty
	private int isRemind;
	@JsonProperty
	private String fromPatientId;
	@JsonProperty
	private int fromType;
	@JsonProperty
	private int totalCount;
	@JsonProperty
	private int leftCount;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getPatientDrugId() {
		return patientDrugId;
	}

	public void setPatientDrugId(String patientDrugId) {
		this.patientDrugId = patientDrugId;
	}

	public int getTimesPerDay() {
		return timesPerDay;
	}

	public void setTimesPerDay(int timesPerDay) {
		this.timesPerDay = timesPerDay;
	}

	public double getPillsPerTime0() {
		return pillsPerTime0;
	}

	public void setPillsPerTime0(double pillsPerTime0) {
		this.pillsPerTime0 = pillsPerTime0;
	}

	public double getPillsPerTime1() {
		return pillsPerTime1;
	}

	public void setPillsPerTime1(double pillsPerTime1) {
		this.pillsPerTime1 = pillsPerTime1;
	}

	public double getPillsPerTime2() {
		return pillsPerTime2;
	}

	public void setPillsPerTime2(double pillsPerTime2) {
		this.pillsPerTime2 = pillsPerTime2;
	}

	public double getPillsPerTime3() {
		return pillsPerTime3;
	}

	public void setPillsPerTime3(double pillsPerTime3) {
		this.pillsPerTime3 = pillsPerTime3;
	}

	public double getPillsPerTime4() {
		return pillsPerTime4;
	}

	public void setPillsPerTime4(double pillsPerTime4) {
		this.pillsPerTime4 = pillsPerTime4;
	}

	public String getRemindTime0() {
		return remindTime0;
	}

	public void setRemindTime0(String remindTime0) {
		this.remindTime0 = remindTime0;
	}

	public String getRemindTime1() {
		return remindTime1;
	}

	public void setRemindTime1(String remindTime1) {
		this.remindTime1 = remindTime1;
	}

	public String getRemindTime2() {
		return remindTime2;
	}

	public void setRemindTime2(String remindTime2) {
		this.remindTime2 = remindTime2;
	}

	public String getRemindTime3() {
		return remindTime3;
	}

	public void setRemindTime3(String remindTime3) {
		this.remindTime3 = remindTime3;
	}

	public String getRemindTime4() {
		return remindTime4;
	}

	public void setRemindTime4(String remindTime4) {
		this.remindTime4 = remindTime4;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getProductPlace() {
		return productPlace;
	}

	public void setProductPlace(String productPlace) {
		this.productPlace = productPlace;
	}

	public int getDrugType() {
		return drugType;
	}

	public void setDrugType(int drugType) {
		this.drugType = drugType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public String getDrugImgUrl() {
		return drugImgUrl;
	}

	public void setDrugImgUrl(String drugImgUrl) {
		this.drugImgUrl = drugImgUrl;
	}

	public int getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(int isRemind) {
		this.isRemind = isRemind;
	}

	public String getFromPatientId() {
		return fromPatientId;
	}

	public void setFromPatientId(String fromPatientId) {
		this.fromPatientId = fromPatientId;
	}

	public int getFromType() {
		return fromType;
	}

	public void setFromType(int fromType) {
		this.fromType = fromType;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getLeftCount() {
		return leftCount;
	}

	public void setLeftCount(int leftCount) {
		this.leftCount = leftCount;
	}

}
