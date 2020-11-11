package com.prativa_panday_p0p2.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import com.prativa_panday_p0p2.pojos.Order;
import com.prativa_panday_p0p2.pojos.Product;
import com.prativa_panday_p0p2.pojos.Supplier;
import com.prativa_panday_p0p2.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class OrderDaoImplTest {
	
	@Mock
	private ConnectionUtil connUtil;
	@Mock
	private Connection connection;
	
	private Connection realConn;
	private PreparedStatement stmt;
	private PreparedStatement trueStmt;
	private PreparedStatement spy;
	
	private OrderDaoImpl orderDao;
	private Order order;
	
	
	@Mock
	private Product product;

	@Mock
	private Supplier supplier;
	
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
		orderDao = new OrderDaoImpl();
		orderDao.setConnUtil(connUtil);
		
		//initializing product and supplier to pass it while creating orders
//		product = productDao.retrieveProduct("1");
//		supplier = supplierDao.retrieveSupplier(1);
		
		when(product.getProductId()).thenReturn("1");
		when(supplier.getSupplierId()).thenReturn(1);
				
		//Initialize order test object
		order = new Order(product, supplier, 12, 287.76);
	}

	@After
	public void tearDown() throws Exception {
		realConn.close();

	}

	@Test
	public void createOrderTest() {
		sql = "insert into all_order (order_quantity, total_cost, product_id, supplier_id) " +"values (?, ?, ?, ?);";
		
		try {
			createSpyConnection(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			System.out.println("From setup" +product.getProductId());

			boolean result = orderDao.createOrder(order, product.getProductId(), supplier.getSupplierId());

			//Verify statement was prepared properly
			verify(spy).setInt(1, order.getOrderQty());
			verify(spy).setDouble(2, order.getTotal());
			verify(spy).setInt(3, Integer.parseInt(product.getProductId()));
			verify(spy).setInt(4, supplier.getSupplierId());
			
			verify(spy).executeUpdate();
			
			assertEquals("Not able to create supplier ", true, result);
			
		} catch (SQLException e) {
			fail("SQLException thrown from creation method:  " + e);
		}
	}
	
	@Test
	public void retreiveOrderTest() {
		
		sql = "insert into all_order (order_quantity, total_cost, product_id, supplier_id) " +"values (?, ?, ?, ?);";
		
		try {
			trueStmt = realConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			trueStmt.setInt(1, order.getOrderQty());
			trueStmt.setDouble(2, order.getTotal());
			trueStmt.setInt(3, Integer.parseInt(product.getProductId()));
			trueStmt.setInt(4, supplier.getSupplierId());
			
			trueStmt.executeUpdate();
			
			//getting primary key id from database for later use
			ResultSet key_rs = trueStmt.getGeneratedKeys();
			key_rs.next();
			order.setOrderId(key_rs.getInt(1));
			
			assertTrue(" inserting test supplier no successful", 1 == trueStmt.executeUpdate());
		} catch (SQLException e) {
			fail("SQLException thrown  " + e);
		}
		
		sql = "select * from all_order " +"where order_id = ?;";
		
		try {
			
			createSpyConnection(sql);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		try {
			Order testOrder = orderDao.retreiveOrder(order.getOrderId());
			
			verify(spy).setInt(1, order.getOrderId());
			
			verify(spy).executeQuery();
			
			assertEquals("Not able to read the supplier", testOrder.getOrderId(), order.getOrderId() );
			
		} catch (SQLException e) {
			
			fail("SQLException thrown from read supplier method:  " + e);
		}
	}
	
	@Test
	public void retrieveAllOrderTest() {
		
		sql = "select count(*) from all_order;";
		
		int count = -1;
		
		try {
			trueStmt = realConn.prepareStatement(sql);
			
			ResultSet rs = trueStmt.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
		} catch (SQLException e1) {

			fail("SQLException thrown " +e1);
		}
		
		//testing retrieving order
		sql = "select * from all_order;";
		
		try {
			
			createSpyConnection(sql);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		try {
			List<Order> allOrder = orderDao.retreiveAllOrders();
			
			verify(spy).executeQuery();
			
			assertTrue("Total list size not matching with database ", count == allOrder.size());
		} catch (SQLException e) {
			fail("SQLException thrown from retrieveAllSuppliertest " +e);
		}
	}
	
	@Test
	public void deleteOrderTest() {
		sql = "insert into all_order (order_quantity, total_cost, product_id, supplier_id) " +"values (?, ?, ?, ?);";
		
		try {
			trueStmt = realConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			trueStmt.setInt(1, order.getOrderQty());
			trueStmt.setDouble(2, order.getTotal());
			trueStmt.setInt(3, Integer.parseInt(product.getProductId()));
			trueStmt.setInt(4, supplier.getSupplierId());
			
			trueStmt.executeUpdate();
			
			//getting primary key id from database for later use
			ResultSet key_rs = trueStmt.getGeneratedKeys();
			key_rs.next();
			order.setOrderId(key_rs.getInt(1));
			
			assertTrue(" inserting test supplier no successful", 1 == trueStmt.executeUpdate());
		} catch (SQLException e) {
			fail("SQLException thrown  " + e);
		}
		
		//to test deleteSupplier
		sql = "delete from all_order " + "where order_id = ?;";
		
		try {
			
			createSpyConnection(sql);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		try {
			
			boolean result = orderDao.deleteOrder(order.getOrderId());
			
			verify(spy).setInt(1, order.getOrderId());
			
			verify(spy).executeUpdate();
			
			assertEquals("Object was not deleted properly", true, result);

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
