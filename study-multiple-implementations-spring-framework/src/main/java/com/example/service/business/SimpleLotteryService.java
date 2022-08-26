package com.example.service.business;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.service.LotteryService;
import com.example.service.RandomNumberService;
import com.example.service.Strategy;
import com.example.service.StrategyType;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class SimpleLotteryService implements LotteryService {
	@Inject
	@Strategy(StrategyType.FAST)
	private RandomNumberService randomNumSrv;

	@Inject
	@Strategy(StrategyType.SECURE)
	@Strategy(StrategyType.FAST)
	private List<RandomNumberService> services;
	private AtomicInteger counter = new AtomicInteger(0);
	private int numOfImpls;

	@PostConstruct
	public void init() {
		numOfImpls = services.size();
		services.stream().map(Object::getClass).map(Class::getSimpleName).forEach(System.err::println);
	}

	@Override
	public List<Integer> draw(int max, int size, boolean isSorted) {
		return IntStream.generate(() -> services.get(counter.getAndIncrement() % numOfImpls).next(1, max)).distinct()
				.limit(size).sorted().boxed().collect(Collectors.toList());
	}

	@Override
	public List<List<Integer>> draw(int max, int size, boolean isSorted, int length) {
		return IntStream.range(0, length).mapToObj(i -> this.draw(max, size, isSorted)).collect(Collectors.toList());
	}

}
