package com.ed.v1.net.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PharmacyAuditCotent extends Content {
	@JsonProperty
	private String certificateImgUrl;

	public String getCertificateImgUrl() {
		return certificateImgUrl;
	}

	public void setCertificateImgUrl(String certificateImgUrl) {
		this.certificateImgUrl = certificateImgUrl;
	}
}
