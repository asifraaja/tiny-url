package com.paas.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import com.paas.service.KeyGeneratorService;

public class KeyGeneratorServiceImpl implements KeyGeneratorService {

	static List<String> usedKeys = new ArrayList<>();
	static List<String> unusedKeys = new ArrayList<>();
	static Random random = new Random();
	static byte[] r = new byte[8];
	
	@Override
	public String getKey() {
		long t1=System.nanoTime();
		if(unusedKeys.isEmpty())
			generateKeys();
		String key = getUnusedKey();
		updateKeyTable(key);
		long t2=System.nanoTime() - t1;
		System.out.println("[" +Thread.currentThread().getId() + "] total time to get a key : " + t2/1000);
		System.out.println("key:" + key);
		return key;
	}
	
	public synchronized String getUnusedKey() {
		
		return unusedKeys.get(0);
	}
	
	public void updateKeyTable(String key) {
		unusedKeys.remove(key);
		usedKeys.add(key);
	}
	
	public void generateKeys() {
		random.nextBytes(r);
		unusedKeys.add(Base64.getEncoder().withoutPadding().encodeToString(r));
		Thread thread = new Thread(
				() -> {
					random.nextBytes(r);
					unusedKeys.add(Base64.getEncoder().withoutPadding().encodeToString(r));
				}
		);
		for(int i=0; i<5; i++)
			thread.run();
	}
}
