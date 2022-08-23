package com.example.order.service;

import com.example.order.entity.Order;

public interface OrderService {
	Order makeOrder(String sku,String identity,int quantity);
}
