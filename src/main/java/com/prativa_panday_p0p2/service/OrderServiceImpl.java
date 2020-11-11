package com.prativa_panday_p0p2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.prativa_panday_p0p2.pojos.Order;
import com.prativa_panday_p0p2.pojos.Product;
import com.prativa_panday_p0p2.pojos.Supplier;


public class OrderServiceImpl implements OrderService {
	
    private static Logger Log = Logger.getLogger("orderServiceLog");

	List<Order> allOrder = new ArrayList<Order>();
	
	public List<Order> getOrders() {
		return this.allOrder;
	}

	public void setOrders(List<Order> orders) {
		this.allOrder = orders;
	}	
	
	/*method to make order of the provided quantity of the product to the supplier*/
	@Override
	public boolean makeOrder(int id, Product product, Supplier supplier, int quantity) {
	
		Order order = new Order(id, product, supplier, quantity);
		allOrder.add(order);
		System.out.println("New order created!! And added to cache..");
		return true;
	}
	
	//method to retrieve order on the basis of order id passed
	@Override
	public Order getOrder(int id) {
		if(allOrder.size() == 0) {
			System.out.println("No orders created yet");
			Log.info("Order was not found that has order id " +id);
		}else {
			for(Order o : allOrder ) {
				if(o.getOrderId() == id) {
					return o;
				}
			}
		}
		return null;

	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order createOrder(String productName, int supplierId, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteOrder(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
