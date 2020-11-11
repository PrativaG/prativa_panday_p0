package com.prativa_panday_p0p2.controller;

import java.util.List;

import com.prativa_panday_p0p2.pojos.Supplier;
import com.prativa_panday_p0p2.service.SupplierServiceDaoImpl;

import io.javalin.http.Context;

public class SupplierController {
	
	SupplierServiceDaoImpl supplierService = new SupplierServiceDaoImpl();
	
	public void createSupplier(Context ctx) {
		
		
			String name = ctx.formParam("name");
			String contactNum = ctx.formParam("contact");
			
			Supplier supplier = new Supplier(name, contactNum);
			
			supplierService.createSupplierFromDao(supplier);
			ctx.html(supplier.toString());
			ctx.html("Supplier was created successfully!");
		
	}
	
	public void retrieveSupplier(Context ctx) {
		
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		Supplier s = supplierService.getSupplierInfo(id);
		
		System.out.println(s.toString());
		
		ctx.html(s.getSupplierId() + "[" + s.getSupplierName() + "] [" + s.getSupplierContactNum() + "]");
		ctx.html("Supplier was retreived successfully");
		
	}
	
	public void deleteSupplier(Context ctx) {
		
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		boolean isDeleted = supplierService.deleteSupplier(id);
		
		if(isDeleted) ctx.html("Supplier with id " +id +" Deleted Successfully!");
		else ctx.html("please enter valid id");
	}
	
	public void retrieveAllSupplier(Context ctx) {
		List<Supplier> allSupplier = supplierService.getAllSuppliers();
		
		for(Supplier s : allSupplier) {
			System.out.println(s.toString());
		}
		
		ctx.html("All supplier was retreived successfully");
	}
}
