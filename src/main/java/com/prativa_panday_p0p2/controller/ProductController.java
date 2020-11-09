package com.prativa_panday_p0p2.controller;

import java.sql.SQLException;

import com.prativa_panday_p0p2.pojos.Product;
import com.prativa_panday_p0p2.pojos.Product.ProductCategory;
import com.prativa_panday_p0p2.service.ProductService;
import com.prativa_panday_p0p2.service.ProductServiceDaoImpl;

import io.javalin.http.Context;

public class ProductController {
	
	ProductService productService = new ProductServiceDaoImpl();
	
	public void createProduct(Context ctx) {
		System.out.println("Creating new product");
		
		try {
			//fetching data from user input(postman right now)
			double costPrice = Double.parseDouble(ctx.formParam("costPrice"));
			System.out.println("cp"+costPrice);
			String name = ctx.formParam("name");
			System.out.println(name);
			ProductCategory productCat = ProductCategory.valueOf(ctx.formParam("productCat").toUpperCase());
			double sellPrice = Double.parseDouble(ctx.formParam("sellPrice"));
			int quantity = Integer.parseInt(ctx.formParam("quantity"));
			
			Product product = new Product(productCat, name, costPrice, sellPrice, quantity);
			productService.createProductFromDao(product);
			System.out.println("Product was created");
			//ctx.html(product.getProductName());
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("null found");
		}
		
	}

	public void getProduct(Context ctx) {
		try {
			String prodName = ctx.pathParam("name");
			Product product = productService.getProduct(prodName);
			ctx.html(product.toString());
			System.out.println(prodName);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
