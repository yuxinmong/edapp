package com.ed.v1.net.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListReflectMethodContent extends Content {
	@JsonProperty
	private List<ReflectmethodContent> methodList;

	public List<ReflectmethodContent> getMethodList() {
		return methodList;
	}

	public void setMethodList(List<ReflectmethodContent> methodList) {
		this.methodList = methodList;
	}

}
