package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.ejb.Singleton;
import javax.inject.Inject;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.Secure;

// CDI -> Spring Bean
// CDI Bean -> Component
//@Named // CDI Bean
//@Singleton // Spring's Default Scope is Singleton -> @Scope("singleton")
//@RequestScoped
//@SessionScoped
//@ApplicationScoped
//@ConversationScoped
@Singleton // EJB Lite
public class StandardLotteryService implements LotteryService {
//	@Inject // Field Injection
//	@Fast
	private RandomNumberService randomNumberService;
	
	
	@Inject // Constructor Injection
	public StandardLotteryService(@Secure RandomNumberService randomNumberService) {
		this.randomNumberService = randomNumberService;
		System.err.println("StandardLotteryService::StandardLotteryService(randomNumberService)");
	}

	
//	@Inject // Setter Injection
//	@Fast
	public void setRandomNumberService(RandomNumberService randomNumberService) {
		this.randomNumberService = randomNumberService;
	}


	public StandardLotteryService() {
		System.err.println("StandardLotteryService::StandardLotteryService()");
	}

	@Override
	public List<Integer> draw(int max, int size) {
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
