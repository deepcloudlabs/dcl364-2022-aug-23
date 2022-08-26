package com.example.service.business;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.example.lottery.interceptors.AuditInterceptor;
import com.example.lottery.interceptors.ProfileInterceptor;
import com.example.service.LotteryService;
import com.example.service.RandomNumberService;

@Stateless
@Interceptors({
	ProfileInterceptor.class, AuditInterceptor.class
})
public class StandardLotteryService implements LotteryService {
	@EJB
	private RandomNumberService randomNumberService;
	
	public StandardLotteryService() {
		System.err.println("StandardLotteryService::StandardLotteryService()");
	}

	@Override
	public List<Integer> draw(int max, int size) {
		System.err.println(randomNumberService.getClass().getName());
		return IntStream.generate( () -> randomNumberService.generate(max))
				        .distinct()
				        .limit(size)
				        .sorted()
				        .boxed()
				        .collect(Collectors.toList());
	}

	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		return IntStream.range(0, column)
				        .mapToObj( i -> draw(max,size))
				        .collect(Collectors.toList());
	}

}
