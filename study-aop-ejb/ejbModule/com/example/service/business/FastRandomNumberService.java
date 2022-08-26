package com.example.service.business;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.example.lottery.interceptors.AuditInterceptor;
import com.example.service.RandomNumberService;

@Stateless
@Interceptors({
	AuditInterceptor.class
})
public class FastRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
		System.err.println("FastRandomNumberService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
