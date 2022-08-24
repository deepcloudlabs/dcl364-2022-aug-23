package com.example.lottery.service.business;

import java.security.SecureRandom;

import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import javax.inject.Singleton;

import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.Secure;

@Named
@Singleton
//@Alternative
@Secure
public class SecureRandomNumberService implements RandomNumberService {

	private SecureRandom random = new SecureRandom();

	@Override
	public int generate(int min, int max) {
		System.err.println("SecureRandomNumberService::generate");
		return random.nextInt(max - min + 1) + min;
	}

}
