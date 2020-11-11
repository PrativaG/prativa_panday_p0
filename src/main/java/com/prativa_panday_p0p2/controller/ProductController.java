package com.prativa_panday_p0p2.controller;

import java.sql.SQLException;
import java.util.List;

import com.prativa_panday_p0p2.pojos.Product;
import com.prativa_panday_p0p2.pojos.Product.ProductCategory;
import com.prativa_panday_p0p2.service.ProductService;
import com.prativa_panday_p0p2.service.ProductServiceDaoImpl;

import io.javalin.http.Context;

public class ProductController {
	
	ProductService productService = new ProductServiceDaoImpl();
	
	public void createProduct(Context ctx) {
		
		try {
			//fetching data from user input(postman right now)
			double costPrice = Double.parseDouble(ctx.formParam("costPrice"));

			String name = ctx.formParam("name").toLowerCase();
			
			double sellPrice = Double.parseDouble(ctx.formParam("sellPrice"));
			
			int quantity = Integer.parseInt(ctx.formParam("quantity"));
			
			Product product = new Product(name, costPrice, sellPrice, quantity);
			productService.createProductFromDao(product);
			
			System.out.println(product.toString());
			
			ctx.html(product.getProductName() +" was created successfully");
		} catch (NullPointerException e) {

			e.printStackTrace();
		}
		
	}

	public void getProduct(Context ctx) {
		try {
			String prodName = ctx.pathParam("id").toLowerCase();
			Product product = productService.getProduct(prodName);
			
			System.out.println(product.toString());
			
			ctx.html(prodName +" was successfully fetched from database ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getAllProducts(Context ctx) {
		
		List<Product> allProducts = productService.getAllProducts();
		
		for(Product p : allProducts) {
			System.out.println(p.toString());
			ctx.html(p.getProductName());
			ctx.html(String.valueOf(p.getProductQuantity()));
		}
		
		ctx.html("All products retreived successfully");
		
	}
	
	public void deleteProduct(Context ctx) {
		String prodId = ctx.pathParam("id");
		
		boolean isDeleted = productService.deleteProduct(prodId);
		
		if(isDeleted) ctx.html("Product was deleted successsfully!");
		else ctx.html("Please enter valid product id");
	}
	
	public void updateProduct(Context ctx) {
		
	}

}
