package com.prativa_panday_p0p2.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.prativa_panday_p0p2.pojos.Order;
import com.prativa_panday_p0p2.service.OrderService;
import com.prativa_panday_p0p2.service.OrderServiceDaoImpl;

import io.javalin.http.Context;

public class OrderController {
	
	private static Logger log;
	
	OrderService orderService = new OrderServiceDaoImpl();
	
	public void createOrder(Context ctx) {
		
		String prodId = ctx.formParam("prodId");
		int supplierId = Integer.parseInt(ctx.formParam("supplierId"));
		int quantity = Integer.parseInt(ctx.formParam("quantity"));
		
		Order order = orderService.createOrder(prodId, supplierId, quantity);
		System.out.println(order.toString());
		
		ctx.html("Order was placed successfully! ");
		
	}
	
	public void getOrder(Context ctx) {
		
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		Order order = orderService.getOrder(id);
		
		System.out.println(order.toString());
		
		ctx.html("Order was retreived successfully! ");
	}
	
	public void getAllOrder(Context ctx) {
		
		List<Order> allorder = orderService.getAllOrders();
		
		for(Order o: allorder) {
			System.out.println("Order id: " +o.getOrderId()+ " Total Cost: " +o.getTotal() + " Quantity: " +o.getOrderQty());
			System.out.println();
		}
		
		ctx.html("All orders are retreived successfully!");
	}
	
	public void deleteOrder(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		boolean isDeleted = orderService.deleteOrder(id);
		
		if(isDeleted) ctx.html("Supplier with id " +id +" Deleted Successfully!");
		else ctx.html("please enter valid id");
	}
}
