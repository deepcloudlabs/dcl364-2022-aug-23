package com.example.order.service.business;

import com.example.crm.service.CustomerService;
import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import com.example.product.service.StockService;

public class StandardOrderService implements OrderService {

	private CustomerService customerService ;
	private StockService stockService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}

	@Override
	public Order makeOrder(String sku, String identity, int quantity) {
		System.err.println(stockService.getClass().getName());
		System.err.println(customerService.getClass().getName());
		var customer = customerService.findCustomerByIdentity(identity)
				   .orElseThrow(() -> new IllegalArgumentException("Cannot find customer!"));
		var product = stockService.findProductById(sku) 
				.orElseThrow(() -> new IllegalArgumentException("Cannot find product!"));
		if (product.quantity() == 0)
			throw new IllegalArgumentException("Product is not available in the stock!");
		return new Order(sku, customer, product, quantity, quantity * product.price());
	}

}
