package com.example.lottery.service.business;

import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Named;
import javax.inject.Singleton;

import com.example.lottery.interceptors.Audit;
import com.example.lottery.interceptors.Profile;
import com.example.lottery.service.Fast;
import com.example.lottery.service.RandomNumberService;

@Named
@Singleton
//@Default
@Fast
public class FastRandomNumberService implements RandomNumberService {

	@Override
	@Profile
	@Audit
	public int generate(int min, int max) {
		System.err.println("FastRandomNumberService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
