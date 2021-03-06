package com.prativa_panday_p0p2.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.prativa_panday_p0p2.pojos.Product;
import com.prativa_panday_p0p2.pojos.Supplier;
import com.prativa_panday_p0p2.pojos.Product.ProductCategory;
import com.prativa_panday_p0p2.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class ProductDaoImplTest {
	
	@Mock
	private ConnectionUtil connUtil;
	@Mock
	private Connection connection;
	
	private Connection realConn;
	private PreparedStatement stmt;
	private PreparedStatement trueStmt;
	private PreparedStatement spy;
	
	private ProductDAOImpl productDao;
	private Product product;
	private String sql;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		//Get new connection
		realConn = new ConnectionUtil().createConnection();

		//Set Dao with mocked ConnectionUtil
		productDao = new ProductDAOImpl();
		productDao.setConnUtil(connUtil);

		//Initialize product test object
		product = new Product("pizza", 23.9, 45.6, 20);
	}

	@After
	public void tearDown() throws Exception {
		
		realConn.close();
	}

	@Test
	public void createProductTest() {
		
		sql = "insert into product (product_name, cost_price, sell_price, product_quantity)" +"values (?, ?, ?, ?);";

		try {
			
			createSpyConnection(sql);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		try {
			boolean result = productDao.createProduct(product);

			//Verify statement was prepared properly
			verify(spy).setString(1, product.getProductName());
			verify(spy).setDouble(2, product.getCostPrice());
			verify(spy).setDouble(3, product.getSellPrice());
			verify(spy).setInt(4, product.getProductQuantity());
			
			
			verify(spy).executeUpdate();
			
			assertTrue("Product not created successfully", result == true);

		} catch (SQLException e) {
			fail("SQLException thrown in creation process:  " + e);
		}
	}

	@Test
	public void retreiveProductTest() {
		
		sql = "insert into product (product_name, cost_price, sell_price, product_quantity)" +"values (?, ?, ?, ?);";

		try {
			trueStmt = realConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			trueStmt.setString(1, product.getProductName());
			trueStmt.setDouble(2, product.getCostPrice());
			trueStmt.setDouble(3, product.getSellPrice());
			trueStmt.setInt(4, product.getProductQuantity());
			
			trueStmt.executeUpdate();
			
			//getting primary key id from database for later use
			ResultSet key_rs = trueStmt.getGeneratedKeys();
			key_rs.next();
			product.setProductId(String.valueOf(key_rs.getInt(1)));
			
			assertTrue(" inserting test product not successful", 1 == trueStmt.executeUpdate());
		} catch (SQLException e) {
			fail("SQLException thrown in retreiving method  " + e);
		}
		
		sql = "select * from product" +" where product_id = ?;";
		

		try {
			
			createSpyConnection(sql);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		try {
			Product testProduct = productDao.retrieveProduct(product.getProductId());
			
			verify(spy).setInt(1, Integer.parseInt(product.getProductId()));
			
			verify(spy).executeQuery();
			
			assertTrue("Not able to read the product", product.getProductId() == testProduct.getProductId());
			
		} catch (SQLException e) {
			// TODO: handle exception
			fail("SQLException thrown from read supplier method:  " + e);
		}
	}
	
	@Test
	public void retrieveAllProductTest() {
		
		sql = "select count(*) from product;";	
		
		int countProduct = -1;
		
		try {
			trueStmt = realConn.prepareStatement(sql);
			
			ResultSet rs = trueStmt.executeQuery();
			
			rs.next();
			
			countProduct = rs.getInt(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			fail("SQLException thrown from count " +e1);
		}
		
		sql = "select * from product;";
		

		try {
			
			createSpyConnection(sql);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		//testing retrieving products
		try {
			List<Product> allProduct = productDao.retrieveAllProduct();
			
			verify(spy).executeQuery();
			
			assertTrue("Total list size not matching with database ", countProduct == allProduct.size());
		} catch (SQLException e) {
			fail("SQLException thrown from retrieveAllSuppliertest " +e);
		}
	}
	
	@Test
	public void deleteProductTest() {
		sql = "insert into product (product_name, cost_price, sell_price, product_quantity)" +"values (?, ?, ?, ?);";
		
		try {
			trueStmt = realConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			trueStmt.setString(1, product.getProductName());
			trueStmt.setDouble(2, product.getCostPrice());
			trueStmt.setDouble(3, product.getSellPrice());
			trueStmt.setInt(4, product.getProductQuantity());
			
			int i = trueStmt.executeUpdate();
			
			//getting primary key id from database for later use
			ResultSet key_rs = trueStmt.getGeneratedKeys();
			key_rs.next();
			product.setProductId(String.valueOf(key_rs.getInt(1)));
			
			assertTrue(" inserting test product not successful", 1 == i);
		} catch (SQLException e) {
			fail("SQLException thrown  " + e);
		}
		
		//to test deleteSupplier
		sql = "delete from product " +"where product_id = ?;";
		
		try {
			
			createSpyConnection(sql);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		try {
			boolean result = productDao.deleteProduct(product.getProductId());
			
			verify(spy).setInt(1, Integer.parseInt(product.getProductId()));
			verify(spy).executeUpdate();
			
			assertTrue("Object was not deleted properly", result);

		} catch (SQLException e) {
			fail("SQLException thrown  " + e);
		}
	}
	
	private void createSpyConnection(String sql) throws SQLException{
		
		
		//creating a real statement from a connection
		stmt = realConn.prepareStatement(sql);

		//spying on that real statement
		spy = Mockito.spy(stmt);
		
		//mock our connection and util, so we will only use the statement we are spying on
		when(connUtil.createConnection()).thenReturn(connection);
		when(connection.prepareStatement(sql)).thenReturn(spy);
	
}

}
