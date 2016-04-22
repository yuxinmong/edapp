package com.ed.v1.net.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncomesContent extends Content {
	@JsonProperty
	private Double incomeTotal;
	@JsonProperty
	private Double incomePending;
	@JsonProperty
	private int type;
	@JsonProperty
	private String account;

	public Double getIncomeTotal() {
		return incomeTotal;
	}

	public void setIncomeTotal(Double incomeTotal) {
		this.incomeTotal = incomeTotal;
	}

	public Double getIncomePending() {
		return incomePending;
	}

	public void setIncomePending(Double incomePending) {
		this.incomePending = incomePending;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
