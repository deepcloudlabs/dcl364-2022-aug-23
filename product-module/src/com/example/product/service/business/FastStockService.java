package com.example.product.service.business;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import com.example.product.entity.Product;
import com.example.product.service.StockService;

public class FastStockService implements StockService {

	@Override
	public Optional<Product> findProductById(String sku) {
		return Optional.of(
		     new Product(sku, "ssd", 
		    		 ThreadLocalRandom.current().nextInt(700, 800), 
		    		 ThreadLocalRandom.current().nextInt(10, 50))
		);
	}

}
