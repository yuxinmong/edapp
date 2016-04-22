package com.ed.v1.net.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.ed.v1.net.JSONDeserializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleContent extends Content implements JSONDeserializable{
	
	@JsonProperty
	private String id;
    @JsonProperty
	private String firstClass;
	@JsonProperty
	private String secondClass;
	@JsonProperty
	private String title;
	@JsonProperty
	private String summary;
	@JsonProperty
	private String content;
	@JsonProperty
	private String imgUrl;
	@JsonProperty
	private String linkUrl;
	@JsonProperty
	private String deliverDate;
	@JsonProperty
	private String readerNum;
	 @Override
	    public void deserialize(JSONObject o) throws JSONException {

	    }
	    public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public void setFirstClass(String firstClass) {
			this.firstClass = firstClass;
		}
		public String getFirstClass() {
			return firstClass;
		}
		public void setSecondClass(String secondClass) {
			this.secondClass= secondClass;
		}
		public String getSecondClass() {
			return secondClass;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public void setContent(String content){
			this.content=content;
		}
		public String getContent(){		      
			return content;
		}
		public void setImgUrl(String imgUrl) {
			this.imgUrl= imgUrl;
		}
		public String getImgUrl() {
			return imgUrl;
		}
		public String getLinkUrl() {
			return linkUrl;
		}
		public void setLinkUrl(String linkUrl) {
			this.linkUrl = linkUrl;
		}
		public String getDeliverDate() {
			return deliverDate;
		}
		public void setDeliverDate(String deliverDate) {
			this.deliverDate = deliverDate;
		}
		public void setReaderNum(String readerNum){
			this.readerNum=readerNum;
		}
		public String getReaderNum(){		      
			return readerNum;
		}
}
