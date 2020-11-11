package com.prativa_panday_p0p2.driver;

import com.prativa_panday_p0p2.controller.OrderController;
import com.prativa_panday_p0p2.controller.ProductController;
import com.prativa_panday_p0p2.controller.SupplierController;

import io.javalin.Javalin;

public class ServiceDriver {
	
	private static ProductController productController = new ProductController();
	
	private static SupplierController supplierController = new SupplierController();
	
	private static OrderController orderController = new OrderController();
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(9090);
		app.get("/project0", ctx -> ctx.html("Welcome to project 0 - part 2!!"));
		
		
		//product
		app.post("/product", ctx -> productController.createProduct(ctx));
		app.get("/product/:id", ctx -> productController.getProduct(ctx));
		app.delete("/product/:id", ctx -> productController.deleteProduct(ctx));
		app.get("/product", ctx -> productController.getAllProducts(ctx));
		app.put("/product/:id", ctx -> productController.updateProduct(ctx));
		
		//working with supplier
		app.post("/supplier", ctx -> supplierController.createSupplier(ctx));
		app.get("/supplier/:id", ctx -> supplierController.retrieveSupplier(ctx));
		app.get("/supplier", ctx -> supplierController.retrieveAllSupplier(ctx));
		app.delete("/supplier/:id", ctx -> supplierController.deleteSupplier(ctx));
		
		//working with order
		app.get("/order/:id", ctx -> orderController.getOrder(ctx));
		app.get("/order", ctx -> orderController.getAllOrder(ctx));
		app.post("/order", ctx -> orderController.createOrder(ctx));
		app.delete("/order/:id", ctx -> orderController.deleteOrder(ctx));
	}
}
