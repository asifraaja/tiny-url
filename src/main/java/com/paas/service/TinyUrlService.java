package com.paas.service;

public interface TinyUrlService {
	public String generateTinyUrl(String originalUrl, String username, String apiKey);
}
