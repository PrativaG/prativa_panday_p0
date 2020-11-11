package com.prativa_panday_p0p2.service;

import java.util.List;

import com.prativa_panday_p0p2.dao.SupplierDao;
import com.prativa_panday_p0p2.dao.SupplierDaoImpl;
import com.prativa_panday_p0p2.pojos.Supplier;

public class SupplierServiceDaoImpl implements SupplierService{
	
	SupplierDao supplierDao = new SupplierDaoImpl();
	
	


	@Override
	public Supplier getSupplierInfo(int id) {
		//returning supplier retreived from dao
		return supplierDao.retrieveSupplier(id);
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return supplierDao.retrieveAllSupplier();
	}

	@Override
	public boolean deleteSupplier(int id) {
		
		return supplierDao.deleteSupplier(id);
	}

	@Override
	public Supplier createSupplier(int id, String name, String contact, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier createSupplierFromDao(Supplier s) {
		Supplier supplier = supplierDao.createSupplier(s);
		return supplier;
	}

}
