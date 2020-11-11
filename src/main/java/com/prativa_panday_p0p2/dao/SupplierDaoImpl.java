package com.prativa_panday_p0p2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.prativa_panday_p0p2.pojos.Supplier;
import com.prativa_panday_p0p2.util.ConnectionUtil;

public class SupplierDaoImpl implements SupplierDao {
	
	private ConnectionUtil conUtil = new ConnectionUtil();
	
	private PreparedStatement ps;
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.conUtil = connUtil;
	}
	
	@Override
	public Supplier createSupplier(Supplier supplier) {
		
		try(Connection con = conUtil.createConnection()){
			String sql = "insert into supplier (supplier_name, supplier_contact) " +"values (?, ?);";
			ps = con.prepareStatement(sql);
			ps.setString(1, supplier.getSupplierName());
			ps.setInt(2, Integer.parseInt(supplier.getSupplierContactNum())); 
			
			ps.executeUpdate();
			
			return supplier;
		}catch(SQLException e) {
//			 Log.warn(" threw SQLException: " + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Supplier retrieveSupplier(int id) {
		Supplier supplier = new Supplier();
		
		try(Connection con = conUtil.createConnection()){
			String sql = "select * from supplier " +"where supplier_id = ?;";
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				supplier.setSupplierId(id);
				supplier.setSupplierName(rs.getString(2));
				supplier.setSupplierContactNum(String.valueOf(rs.getInt(3)));	
			}
			
			
		}catch(SQLException e) {
//			 Log.warn(" threw SQLException: " + e);
			e.printStackTrace();
		}
		return supplier;
	}

	@Override
	public List<Supplier> retrieveAllSupplier() {
		List<Supplier> allSuppliers= new ArrayList<>();
		
		try(Connection con = conUtil.createConnection()) {
			String sql = "select * from supplier;";
			
			ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Supplier supplier = new Supplier();
				
				supplier.setSupplierId(rs.getInt(1));
				supplier.setSupplierName(rs.getString(2));
				supplier.setSupplierContactNum(String.valueOf(rs.getInt(3)));
				
				allSuppliers.add(supplier);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allSuppliers;
	}

	@Override
	public boolean deleteSupplier(int id) {
		
		try (Connection con = conUtil.createConnection()){
			String sql = "delete from supplier " + "where supplier_id = ?;";
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			int rslt = ps.executeUpdate();
			
			if(rslt == 1) return true;
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
