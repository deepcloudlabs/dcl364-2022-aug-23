package com.example.order.entity;

import com.example.crm.entity.Customer;
import com.example.product.entity.Product;

public record Order(String sku,Customer customer,Product product, 
		int quantity, double totalPrice) {

}
