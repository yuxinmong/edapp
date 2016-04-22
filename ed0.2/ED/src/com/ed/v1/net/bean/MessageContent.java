package com.ed.v1.net.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.ed.v1.net.JSONDeserializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageContent implements JSONDeserializable{
	 private static final long serialVersionUID = 1L;
	    @JsonProperty
		private String id;
	    @JsonProperty
		private String content;
		@JsonProperty
		private String type;
		@JsonProperty
		private String createDate;
		@JsonProperty
		private String sendTimes;
		@JsonProperty
		private String storeId;

	    @Override
	    public void deserialize(JSONObject o) throws JSONException {

	    }

	    public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public void setContent(String content) {
			this.content= content;
		}
		public String getContent() {
			return content;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getType() {
			return type;
		}
		public String getSendTimes() {
			return sendTimes;
		}
		public void setSendTimes(String sendTimes) {
			this.sendTimes = sendTimes;
		}
		public String getCreateDate() {
			return createDate;
		}
		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}
		public void setStoreId(String storeId){
			this.storeId=storeId;
		}
		public String getStoreId(){			      
			return storeId;
		}
}
