package com.example.market.application;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;

import com.example.market.entity.Stock;
import com.example.market.repository.StockRepository;

@Stateless
public class StockMarketApplication {

	@Inject
	private StockRepository stockRepository;
	
	public Stock findStock(String symbol) {
		return stockRepository.findOne(symbol)
				              .orElseThrow(() -> new IllegalArgumentException("Cannot find stock."));
	}

	public List<Stock> findStocks(int pageNo, int pageSize) {
		return stockRepository.findAll(pageNo, pageSize);	
	}

	public Stock persistStock(@Valid Stock stock) {
		Stock persistedStock= null;
		try {
			persistedStock = stockRepository.create(stock).get();
		} catch (InterruptedException | ExecutionException e) {
			throw new IllegalArgumentException("Cannot retrieve stock.");
		}
		return persistedStock;
	}

	public Stock updateStock(String symbol, @Valid Stock stock) {
		return stockRepository.update(stock);
	}

	public Stock deleteStock(String symbol) {
		return stockRepository.removeById(symbol)
				.orElseThrow(() -> new IllegalArgumentException("Cannot find stock"));
	}

}
