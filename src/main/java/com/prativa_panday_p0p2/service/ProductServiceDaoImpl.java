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
		productDao.createProduct(product);
		return product;
	}

	@Override
	public List<Product> getProductByType(ProductCategory productCat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkIfDamaged(Product p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product getProduct(String prodName) {
		// TODO Auto-generated method stub
		productDao.retrieveProduct(prodName);
		return null;
	}
	
}
