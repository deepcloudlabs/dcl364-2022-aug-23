package com.example.order.application;

import java.util.ServiceLoader;

import com.example.crm.service.CustomerService;
import com.example.order.service.business.StandardOrderService;
import com.example.product.service.StockService;

public class OrderApplication {

	public static void main(String[] args) {
		var orderService = new StandardOrderService();
		var customerService = ServiceLoader.load(CustomerService.class).findFirst().get();
		orderService.setCustomerService(customerService); // dependency injection
		var stockService = ServiceLoader.load(StockService.class).findFirst().get();
		orderService.setStockService(stockService); // dependency injection
		var order = orderService.makeOrder("aa123", "11111111110", 2);
		System.out.println(order);

	}

}
