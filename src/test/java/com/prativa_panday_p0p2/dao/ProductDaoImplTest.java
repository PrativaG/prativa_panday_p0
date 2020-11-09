package com.prativa_panday_p0p2.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	private PreparedStatement testStmt;
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
		
		//creating a real statement from a connection
		stmt = realConn.prepareStatement(sql);

		//spying on that real statement
		spy = Mockito.spy(stmt);
		
		//mock our connection and util, so we will only use the statement we are spying on
			when(connection.createStatement()).thenReturn(spy);
			when(connUtil.createConnection()).thenReturn(connection);

		//Initialize product test object
		product = new Product(ProductCategory.FOOD, "pizza", 23.9, 45.6, 20);
	}

	@After
	public void tearDown() throws Exception {
		
		realConn.close();
	}

	@Test
	public void createProductTest() {
		
		sql = "INSERT INTO product VALUES " 
				+"(?, ?, ?, ?, ?);";
		
		try {
			productDao.createProduct(product);

			//Verify statement was prepared properly
			verify(spy).setString(1, product.getProductCategory().toString());
			verify(spy).setString(2, product.getProductName());
			verify(spy).setDouble(3, product.getCostPrice());
			verify(spy).setDouble(3, product.getSellPrice());
			verify(spy).setInt(4, product.getProductQuantity());
			
			
			verify(spy).executeUpdate();

		} catch (SQLException e) {
			fail("SQLException thrown in creation process:  " + e);
		}
	}

}
