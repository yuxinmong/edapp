package com.ed.v1.net.bean;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.ed.v1.net.JSONDeserializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TitleListContent implements JSONDeserializable{
	 @JsonProperty
	    private int error;
	    @JsonProperty
	    private String msg;
	    @JsonProperty
	    private boolean success;
	    @JsonProperty
	    private List<String> firstClassList;
		@Override
		public void deserialize(JSONObject o) throws JSONException {
			// TODO Auto-generated method stub
			
		}
		public int getError() {
			return error;
		}

		public void setError(int error) {
			this.error = error;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}
		public List<String> getListTitles(){
			return firstClassList;
		}
		public void setListTitles(List<String> titleList){
			this.firstClassList=titleList;
		}
}