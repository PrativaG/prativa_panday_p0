package com.prativa_panday_p0p2.driver;

import com.prativa_panday_p0p2.controller.ProductController;

import io.javalin.Javalin;

public class ServiceDriver {
	
	private static ProductController productController = new ProductController();
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(9090);
		app.get("/project0", ctx -> ctx.html("Welcome to project 0 - part 2!!"));
		app.post("/createProduct", ctx -> productController.createProduct(ctx));
		app.get("/displayProduct/:name", ctx -> productController.getProduct(ctx));
	}
}
