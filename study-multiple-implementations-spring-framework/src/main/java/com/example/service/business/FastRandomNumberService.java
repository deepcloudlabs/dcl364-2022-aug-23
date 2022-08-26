package com.example.service.business;

import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Named;
import javax.inject.Singleton;

import com.example.service.RandomNumberService;
import com.example.service.Strategy;
import com.example.service.StrategyType;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Named
@Singleton
@Strategy(StrategyType.FAST)
public class FastRandomNumberService implements RandomNumberService {

	@Override
	public int next(int min, int max) {
		System.err.println(getClass().getSimpleName());
		return ThreadLocalRandom.current().nextInt(max - min + 1) + min;
	}

}
