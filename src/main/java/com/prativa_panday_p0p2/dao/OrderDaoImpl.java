package com.prativa_panday_p0p2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prativa_panday_p0p2.pojos.Order;
import com.prativa_panday_p0p2.pojos.Product;
import com.prativa_panday_p0p2.pojos.Supplier;
import com.prativa_panday_p0p2.util.ConnectionUtil;

public class OrderDaoImpl implements OrderDao {
	
	private ConnectionUtil conUtil = new ConnectionUtil();
	
	private PreparedStatement ps;
	
	private ProductDAOImpl productDao = new ProductDAOImpl();
	
	private SupplierDaoImpl supplierDao = new SupplierDaoImpl();
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.conUtil = connUtil;
	}

	@Override
	public boolean createOrder(Order order, String prodId, int supplierId) {
		try(Connection con = conUtil.createConnection()){
			String sql = "insert into all_order (order_quantity, total_cost, product_id, supplier_id) " +"values (?, ?, ?, ?);";
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, order.getOrderQty());
			ps.setDouble(2, order.getTotal());
			ps.setInt(3, Integer.parseInt(prodId));
			ps.setInt(4, supplierId);
			
			ps.executeUpdate();
			return true;
		}catch(SQLException e) {
//			 Log.warn("PlayerDaoPostgres.readPlayer threw SQLException: " + e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Order> retreiveAllOrders() {
		List <Order> allOrder = new ArrayList<>();
		
		try(Connection con = conUtil.createConnection()){
			String sql = "select * from all_order;";
			
			ps = con.prepareStatement(sql);
							
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt(1));
				order.setTotal(rs.getDouble(2));
				
//				Product p = productDao.retrieveProduct(String.valueOf(rs.getInt(3)));
//				order.setProduct(p);
//				
//				System.out.println("from dao " +p.getProductName());
//				
//				Supplier s =supplierDao.retrieveSupplier(rs.getInt(4));
//				order.setSupplier(s);
				
				order.setOrderQty(rs.getInt(5));
				
				allOrder.add(order);
			}	
			
		}catch(SQLException e) {
//			 Log.warn(" threw SQLException: " + e);
			e.printStackTrace();
		}
		return allOrder;
	}

	@Override
	public Order retreiveOrder(int id) {
		Order order = new Order();
		
		try(Connection con = conUtil.createConnection()){
			String sql = "select * from all_order " +"where order_id = ?;";
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				order.setOrderId(rs.getInt(1));
				order.setTotal(rs.getDouble(2));
				
				Product p = productDao.retrieveProduct(String.valueOf(rs.getInt(3)));
				order.setProduct(p);
				
				Supplier s =supplierDao.retrieveSupplier(rs.getInt(4));
				order.setSupplier(s);
				
				order.setOrderQty(rs.getInt(5));
				
			}
			
			
		}catch(SQLException e) {
//			 Log.warn(" threw SQLException: " + e);
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public boolean deleteOrder(int id) {
		try (Connection con = conUtil.createConnection()){
			String sql = "delete from all_order " + "where order_id = ?;";
			
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


