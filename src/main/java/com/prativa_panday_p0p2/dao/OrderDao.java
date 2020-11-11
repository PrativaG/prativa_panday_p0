package com.prativa_panday_p0p2.dao;

import java.util.List;

import com.prativa_panday_p0p2.pojos.Order;

public interface OrderDao {
	
	public boolean createOrder(Order order, String prodId, int supplierId);
	
	public List<Order> retreiveAllOrders();
	
	public Order retreiveOrder(int id);
	
	public boolean deleteOrder(int id);

}
