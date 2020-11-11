package com.prativa_panday_p0p2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.prativa_panday_p0p2.pojos.Product;
import com.prativa_panday_p0p2.pojos.Product.ProductCategory;
import com.prativa_panday_p0p2.util.ConnectionUtil;

public class ProductDAOImpl implements ProductDAO {
	
	private PreparedStatement ps;
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}
	
//    private static Logger Log = Logger.getLogger("daoLog");

	
	@Override
	public boolean createProduct(Product product)  {
		
		try(Connection con = connUtil.createConnection()){
			String sql = "insert into product (product_name, cost_price, sell_price, product_quantity)" +"values (?, ?, ?, ?);";

			ps = con.prepareStatement(sql);
			
			ps.setString(1, product.getProductName());
			ps.setDouble(2, product.getCostPrice());
			ps.setDouble(3, product.getSellPrice());
			ps.setInt(4, product.getProductQuantity());
			
			ps.executeUpdate();
			return true;
		}catch(SQLException e) {
//			 Log.warn("PlayerDaoPostgres.readPlayer threw SQLException: " + e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Product retrieveProduct(String productId) {
		Product product = null;
		try(Connection con = new ConnectionUtil().createConnection()){
			String sql = "select * from product" +" where product_id = ?;";
			ps = con.prepareStatement(sql) ;
			
			ps.setInt(1, Integer.parseInt(productId));
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				product = new Product(rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5));
			}
		
		}catch(SQLException e) {
			//log error
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<Product> retrieveAllProduct() {
		
		List<Product> allProducts = new ArrayList<>();
		
		try(Connection con = new ConnectionUtil().createConnection()){
			String sql = "select * from product;";
			
			ps = con.prepareStatement(sql) ;
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Product product = new Product(rs.getString(2), 
												rs.getDouble(3),
												rs.getDouble(4), 
												rs.getInt(5));
				allProducts.add(product);
			}
			
			
		}catch(SQLException e) {
			//log error
			e.printStackTrace();
		}
		return allProducts;		
	}

	@Override
	public boolean deleteProduct(String prodid) {
		
		try(Connection con = new ConnectionUtil().createConnection()){
			String sql = "delete from product " +"where product_id = ?;";
			
			ps = con.prepareStatement(sql) ;
			
			ps.setInt(1, Integer.parseInt(prodid));
			
			ps.executeUpdate();
			return true;
		}catch(SQLException e) {
			//log error
			e.printStackTrace();
		}
		return false;
	}

}
