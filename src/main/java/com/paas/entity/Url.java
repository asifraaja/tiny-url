package com.paas.entity;

public class Url {
	String originalUrl;
	String tinyUrl;
	String username;
	String createdAt;
	
	public Url(String originalUrl, String tinyUrl, String username, String createdAt) {
		this.originalUrl = originalUrl;
		this.tinyUrl = tinyUrl;
		this.username = username;
		this.createdAt = createdAt;
	}
	
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getTinyUrl() {
		return tinyUrl;
	}
	public void setTinyUrl(String tinyUrl) {
		this.tinyUrl = tinyUrl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Url [originalUrl=" + originalUrl + ", tinyUrl=" + tinyUrl + ", username=" + username + ", createdAt="
				+ createdAt + "]";
	}
}
