package com.prativa_panday_p0p2.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.prativa_panday_p0p2.pojos.Product;
import com.prativa_panday_p0p2.pojos.Product.ProductCategory;

public class ProductServiceImpl implements ProductService {
	
    private static Logger Log = Logger.getLogger("productServiceLog");

	List<Product> allProducts = new ArrayList<Product>();	
	
	public List<Product> getProducts(){
		return this.allProducts;
	}
	
	public void setProducts(List<Product> products) {
		this.allProducts = products;
	}
	
	/** method to create new product*/
	@Override
	public boolean createProduct(ProductCategory productCat, String name, Date expiryDate, String quality) {
				
		//creating new product
		try {
			Product product = new Product(productCat, name, quality);
			allProducts.add(product);
			return true;
		}catch(NullPointerException e) {
			Log.info("Error" +e + " occured while creating the product.");

		}catch(Exception e) {
			Log.info("Error" +e + " occured while creating the product.");
		}
		return false;
		
	}
	
	/*method that returns product based on the basis of ProducCategory passed*/

	
	public List<Product> getProductByType(ProductCategory productCat) {
		// TODO Auto-generated method stub
		List<Product> sameTypeProduct = new ArrayList<>();
		for(Product p : allProducts) {
			if(p.getProductCategory() == productCat) {
				sameTypeProduct.add(p);
			}
		}
		
		return sameTypeProduct;
	}

	@Override
	public Product createProductFromDao(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProduct(String prodName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProduct(String prodName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProduct(String prodName) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
