package com.prativa_panday_p0p2.service;

import java.util.Date;
import java.util.List;

import com.prativa_panday_p0p2.pojos.Product;
import com.prativa_panday_p0p2.pojos.Product.ProductCategory;



public interface ProductService {
	
	public boolean createProduct(ProductCategory productCat, String name, Date expiryDate, String quality);
	
	public Product createProductFromDao(Product product);
	
	public Product getProduct(String prodName);
	
	public List<Product> getAllProducts();
	
	public Product updateProduct(String prodName);
	
	public boolean deleteProduct(String prodName);
}
