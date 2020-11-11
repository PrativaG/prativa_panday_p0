package com.prativa_panday_p0p2.service;

import java.util.List;

import com.prativa_panday_p0p2.pojos.Order;
import com.prativa_panday_p0p2.pojos.Product;
import com.prativa_panday_p0p2.pojos.Supplier;

public interface OrderService {
	
	public List<Order> getAllOrders();
	
	public Order getOrder(int id);
	
	public Order createOrder(String productName, int supplierId, int quantity);
	
	public boolean deleteOrder(int id);
	
	public boolean makeOrder(int id, Product product, Supplier supplier, int quantity);
	
	
}
