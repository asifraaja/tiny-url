package com.paas.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.paas.dao.UrlDao;
import com.paas.entity.Url;
import com.paas.service.KeyGeneratorService;
import com.paas.service.TinyUrlService;

public class TinyUrlServiceImpl implements TinyUrlService {
	static UrlDao urlDao = new UrlDao();
	KeyGeneratorService keygen = new KeyGeneratorServiceImpl();

	public String generateTinyUrl(String originalUrl, String username, String apiKey) {
		String tinyUrl = "";
		tinyUrl = keygen.getKey();

		if (!urlDao.isUrlInUse(tinyUrl)) {
			Url url = new Url(originalUrl, tinyUrl, "", "");
			urlDao.addUrl(url);
		} else {
			System.out.println("found a duplicate url:" + tinyUrl);
		}
		return tinyUrl;
	}

	public void removeUrl(String url) {
		boolean isDeleted = urlDao.deleteUrl(url);
		if (isDeleted)
			System.out.println("Deleted the url");
		else
			System.out.println("No such url");
	}

	public void viewUrl(String urlToView) {
		Url url = urlDao.getUrl(urlToView);
		if (url == null)
			System.out.println("No Such Url");
		else
			System.out.println(url);
	}

	public void viewAll() {
		List<Url> urls = urlDao.viewAll();
		for (Url url : urls)
			System.out.println(url);
	}

	// function to generate a random string of length n
	static String getAlphaNumericString(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		TinyUrlServiceImpl tusi = new TinyUrlServiceImpl();
		Random random = new Random();

		ExecutorService es = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 100; i++) {
			es.submit(() -> {
				int x = random.nextInt(30);
				tusi.generateTinyUrl(getAlphaNumericString(x), "", "");

			});
		}

		try {
			es.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			es.shutdown();
		}

		/*
		 * int option = 0; Scanner scanner = new Scanner(System.in); do {
		 * System.out.println("1.Add  2.Delete  3.View  4.Exit"); option =
		 * scanner.nextInt();
		 * 
		 * switch(option) { case 1: System.out.println("OriginalUrl:"); String
		 * originalUrl = scanner.next(); tusi.generateTinyUrl(originalUrl, "", "");
		 * break; case 2: System.out.println("UrlToDelete:"); String url =
		 * scanner.next(); tusi.removeUrl(url); break; case 3:
		 * System.out.println("All the urls are :-"); tusi.viewAll(); break; default: }
		 * }while(option != 4);
		 */

	}

}
