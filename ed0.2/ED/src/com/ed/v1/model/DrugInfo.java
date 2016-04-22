package com.ed.v1.model;

import com.ed.v1.net.bean.Content;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "t_druginfo")
public class DrugInfo extends Content {
	public DrugInfo() {
	}

	private String header;

	@JsonProperty
	@DatabaseField(id = true)
	public String id;
	@JsonProperty
	@DatabaseField()
	private String drugName;
	@JsonProperty
	@DatabaseField()
	private String spec;
	@JsonProperty
	@DatabaseField()
	private String productPlace;
	@JsonProperty
	@DatabaseField()
	private String drugImgUrl;
	@JsonProperty
	@DatabaseField()
	private String drugNo;
	@DatabaseField
	private int drugType;

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BuyMedicineInfo [id=" + id + ", drugName=" + drugName
				+ ", spec=" + spec + "]";
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getProductPlace() {
		return productPlace;
	}

	public void setProductPlace(String productPlace) {
		this.productPlace = productPlace;
	}

	public String getDrugImgUrl() {
		return drugImgUrl;
	}

	public void setDrugImgUrl(String drugImgUrl) {
		this.drugImgUrl = drugImgUrl;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getDrugNo() {
		return drugNo;
	}

	public void setDrugNo(String drugNo) {
		this.drugNo = drugNo;
	}

	public int getDrugType() {
		return drugType;
	}

	public void setDrugType(int drugType) {
		this.drugType = drugType;
	}

}
