package com.example.product.service;

import java.util.Optional;

import com.example.product.entity.Product;

public interface StockService {
	Optional<Product> findProductById(String sku);
}
