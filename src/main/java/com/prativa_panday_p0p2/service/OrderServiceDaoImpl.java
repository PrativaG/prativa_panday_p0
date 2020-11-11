package com.prativa_panday_p0p2.service;

import java.util.List;

import com.prativa_panday_p0p2.dao.OrderDao;
import com.prativa_panday_p0p2.dao.OrderDaoImpl;
import com.prativa_panday_p0p2.dao.ProductDAO;
import com.prativa_panday_p0p2.dao.ProductDAOImpl;
import com.prativa_panday_p0p2.dao.SupplierDao;
import com.prativa_panday_p0p2.dao.SupplierDaoImpl;
import com.prativa_panday_p0p2.pojos.Order;
import com.prativa_panday_p0p2.pojos.Product;
import com.prativa_panday_p0p2.pojos.Supplier;

public class OrderServiceDaoImpl implements OrderService {
	
	ProductDAO prodDao = new ProductDAOImpl();
	SupplierDao supplierDao = new SupplierDaoImpl();
	OrderDao orderDao = new OrderDaoImpl();
	
	@Override
	public List<Order> getAllOrders() {
		return orderDao.retreiveAllOrders();
	}

	@Override
	public Order getOrder(int id) {
		
		return orderDao.retreiveOrder(id);
	}

	@Override
	public Order createOrder(String productId, int supplierId, int quantity) {
		
		Product p = prodDao.retrieveProduct(productId);
		Supplier s = supplierDao.retrieveSupplier(supplierId);
		double totalCost = (double)quantity * p.getCostPrice();
		
		Order order = new Order(p, s, quantity, totalCost);
		
		boolean b = orderDao.createOrder(order, productId, supplierId);
		
		if(b) return order;
		else return null;
	}

	@Override
	public boolean deleteOrder(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean makeOrder(int id, Product product, Supplier supplier, int quantity) {
		// TODO Auto-generated method stub
		return false;
	}

}
