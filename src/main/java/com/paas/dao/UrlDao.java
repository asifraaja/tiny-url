package com.paas.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import com.paas.entity.Url;

public class UrlDao {
	static List<Url> urls = new ArrayList<>();

	public void addUrl(Url url) {
		urls.add(url);
	}

	public boolean deleteUrl(String originalUrl) {
		BiPredicate<Url, String> biPredicate = (url, urlToDelete) -> url.getOriginalUrl().equalsIgnoreCase(urlToDelete)
				|| url.getTinyUrl().equals(urlToDelete);
		for(Url url : urls) {
			if(biPredicate.test(url, originalUrl))
				return urls.remove(url);
		}
		return false;
	}
	
	public Url getUrl(String urlToView) {
		BiPredicate<Url, String> biPredicate = (url, urlToCheck) -> url.getOriginalUrl().equalsIgnoreCase(urlToCheck)
				|| url.getTinyUrl().equals(urlToCheck);
		for(Url url : urls) {
			if(biPredicate.test(url, urlToView))
				return url;
		}
		return null;
	}
	
	public List<Url> viewAll() {
		return urls;
	}
	
	public boolean isUrlInUse(String tinyUrl) {
		for(Url url : urls) {
			if(url.getTinyUrl().equals(tinyUrl))
				return true;
		}
		return false;
	}

}
