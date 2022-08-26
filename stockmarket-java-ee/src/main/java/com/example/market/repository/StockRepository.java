package com.example.market.repository;

import java.util.List;

import com.example.market.entity.Stock;

public interface StockRepository extends CrudRepository<Stock, String>{
	List<Stock> findAllByCompany(String company);
}
