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
	public void createProduct(Product product)  {
		// TODO Auto-generated method stub
		System.out.println("Inside dao");
		try(Connection con = connUtil.createConnection()){
			String sql = "insert into product (product_name, cost_price, sell_price, product_quantity, product_cat)" +"values (?, ?, ?, ?, ?, ?);";
			ps = con.prepareStatement(sql);
			ps.setString(1, product.getProductName());
			ps.setDouble(2, product.getCostPrice());
			ps.setDouble(3, product.getSellPrice());
			ps.setInt(4, product.getProductQuantity());
//			ps.setDate(5, null);
			ps.setString(6, product.getProductCategory().toString()); //changing enum productcategory to string
			int i = ps.executeUpdate();
			System.out.println("updated "+i);
		}catch(SQLException e) {
//			 Log.warn("PlayerDaoPostgres.readPlayer threw SQLException: " + e);
		}

	}

	@Override
	public Product retrieveProduct(String prodName) {
		
		try(Connection con = new ConnectionUtil().createConnection()){
			String sql = "select * from product" +" where product_name = ?;";
			ps = con.prepareStatement(sql) ;
			ps.setString(1, prodName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Product product = new Product(ProductCategory.valueOf(rs.getString(6)), 
						rs.getString(1), rs.getDouble(2), rs.getDouble(3), rs.getInt(4));
			
		return product;
		}catch(SQLException e) {
			//log error
		}
		return null;
	}

	@Override
	public List<Product> retrieveAllProduct() {
		
		List<Product> allProducts = new ArrayList<>();
		
		try(Connection con = new ConnectionUtil().createConnection()){
			String sql = "select * from product;";
			ps = con.prepareStatement(sql) ;
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			
			while(rs.next()) {
				Product product = new Product(ProductCategory.valueOf(rs.getString(6)), 
												rs.getString(1), 
												rs.getDouble(2),
												rs.getDouble(3), 
												rs.getInt(4));
				allProducts.add(product);
			}
			
			
		}catch(SQLException e) {
			//log error
		}
		return allProducts;		
	}

	@Override
	public void deleteProduct(String prodName) {
		
		try(Connection con = new ConnectionUtil().createConnection()){
			String sql = "delete from product" +"where prodName = ?;";
			ps = con.prepareStatement(sql) ;
			ps.setString(1, prodName);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			//log error
		}
	}

}
