package com.prativa_panday_p0p2.dao;

import java.util.List;

import com.prativa_panday_p0p2.pojos.Product;

public interface ProductDAO {
	
	public boolean createProduct(Product product);
	
	public Product retrieveProduct(String prodName);
	
	public List<Product> retrieveAllProduct();
	
	public boolean deleteProduct(String prodName);
}
