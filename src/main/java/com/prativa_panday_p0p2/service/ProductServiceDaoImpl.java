package com.prativa_panday_p0p2.service;

import java.util.Date;
import java.util.List;

import com.prativa_panday_p0p2.dao.ProductDAO;
import com.prativa_panday_p0p2.dao.ProductDAOImpl;
import com.prativa_panday_p0p2.pojos.Product;
import com.prativa_panday_p0p2.pojos.Product.ProductCategory;

public class ProductServiceDaoImpl implements ProductService {
	
	ProductDAO productDao = new ProductDAOImpl();

	@Override
	public boolean createProduct(ProductCategory productCat, String name, Date expiryDate, String quality) {
		return false;
	}

	@Override
	public Product createProductFromDao(Product product) {
		boolean isProductCreated = productDao.createProduct(product);
		if(isProductCreated) return product;
		else return null;
	}

	

	@Override
	public Product getProduct(String prodName) {
		return productDao.retrieveProduct(prodName);
	}

	@Override
	public boolean deleteProduct(String prodId) {
		
		return productDao.deleteProduct(prodId);
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productDao.retrieveAllProduct();
	}

	@Override
	public Product updateProduct(String prodName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
