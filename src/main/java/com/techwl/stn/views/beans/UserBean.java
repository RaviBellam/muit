package com.techwl.stn.views.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserBean {
	
	@JsonProperty("name")
	private String name;
	@JsonProperty("emailId")
	private String emailId;
	@JsonProperty("mobileNo")
	private String mobileNo;
	
	private String avatar;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	
}
