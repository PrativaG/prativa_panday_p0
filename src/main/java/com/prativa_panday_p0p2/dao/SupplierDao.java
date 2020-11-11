package com.prativa_panday_p0p2.dao;

import java.util.List;

import com.prativa_panday_p0p2.pojos.Supplier;

public interface SupplierDao {
	
	public Supplier createSupplier(Supplier supplier);
	
	public Supplier retrieveSupplier(int id);
	
	public List<Supplier> retrieveAllSupplier();
	
	public boolean deleteSupplier(int id);
}
