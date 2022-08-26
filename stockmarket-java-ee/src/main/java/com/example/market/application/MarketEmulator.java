package com.example.market.application;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.market.repository.StockRepository;

@Stateless
public class MarketEmulator {
	@Inject
	public StockRepository stockRepository;
	
	@Schedule(second = "*/2", minute = "*", hour = "*")
	public void changeStockPrices() {
		stockRepository.findAll(0, 10)
		               .forEach( stock -> {
		            	   stock.setPrice((1.0 + ThreadLocalRandom.current().nextDouble(-0.1, 0.1)) * stock.getPrice());
		            	   stockRepository.update(stock);
		               });
	}
}
