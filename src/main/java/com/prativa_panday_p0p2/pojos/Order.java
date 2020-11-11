package com.prativa_panday_p0p2.pojos;

import java.util.Date;

import com.prativa_panday_p0p2.pojos.Product;

/*This is a model that holds information about the product 
 * that has to be ordered, the supplier that satisfies the order, payment and order date. */

public class Order {
	
	private int orderId;
	
	private Product product;
	
	private Supplier supplier;
	
	private int orderQty;
	
	private double total;
	
	public Order() {
		super();
	}

	public Order(int orderId, Product products, Supplier supplier, int qt) {
		super();
		this.orderId = orderId;
		this.product = products;
		this.supplier = supplier;
		this.orderQty =qt;
	}
	
	public Order(Product products, Supplier supplier, int qt, double total) {
		super();
		this.product = products;
		this.supplier = supplier;
		this.orderQty =qt;
		this.total = total;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	
	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public Product getProducts() {
		return product;
	}

	public void setProducts(Product product) {
		this.product = product;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Order [ product=" + product.getProductName() + ", supplier=" + supplier.getSupplierName() + ", orderQty="
				+ orderQty + ", total=" + total + "]";
	}
	
	
	

}
