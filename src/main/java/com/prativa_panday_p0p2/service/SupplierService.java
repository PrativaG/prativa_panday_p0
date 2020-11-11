package com.prativa_panday_p0p2.service;

import java.util.List;

import com.prativa_panday_p0p2.pojos.Supplier;

public interface SupplierService {
	
	public Supplier createSupplier(int id, String name, String contact, String email);
	
	public Supplier createSupplierFromDao(Supplier s);
	
	public Supplier getSupplierInfo(int id);
	
	public List<Supplier> getAllSuppliers();
	
	public boolean deleteSupplier(int id);
}
