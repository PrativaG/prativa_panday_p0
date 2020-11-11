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

import com.prativa_panday_p0p2.pojos.Supplier;
import com.prativa_panday_p0p2.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class SupplierDaoImplTest {
	
	@Mock
	private ConnectionUtil connUtil;
	@Mock
	private Connection connection;
	
	private Connection realConn;
	private PreparedStatement stmt;
	private PreparedStatement trueStmt;
	private PreparedStatement spy;
	
	private SupplierDaoImpl supplierDao;
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
		supplierDao = new SupplierDaoImpl();
		supplierDao.setConnUtil(connUtil);
		
		//Initialize supplier test object
		supplier = new Supplier("Testing", "1234567890");
	}

	@After
	public void tearDown() throws Exception {
		realConn.close();
	}
	

	@Test
	public void createSupplierTest() {
		sql = "insert into supplier (supplier_name, supplier_contact) " +"values (?, ?);";
		
		try {
			createSpyConnection(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			supplierDao.createSupplier(supplier);

			//Verify statement was prepared properly
			verify(spy).setString(1, supplier.getSupplierName());
			verify(spy).setInt(2, Integer.parseInt(supplier.getSupplierContactNum()));
			
			verify(spy).executeUpdate();
			
		} catch (SQLException e) {
			fail("SQLException thrown from creation method:  " + e);
		}
	}
	
	@Test
	public void retreiveSupplierTest() {
		
		sql = "Insert into supplier (supplier_name, supplier_contact) values " + "(?, ?);";
		
		try {
			trueStmt = realConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			trueStmt.setString(1, supplier.getSupplierName());
			trueStmt.setInt(2,  Integer.parseInt(supplier.getSupplierContactNum()));
			
			trueStmt.executeUpdate();
			
			//getting primary key id from database for later use
			ResultSet key_rs = trueStmt.getGeneratedKeys();
			key_rs.next();
			supplier.setSupplierId(key_rs.getInt(1));
			
			assertTrue(" inserting test supplier no successful", 1 == trueStmt.executeUpdate());
		} catch (SQLException e) {
			fail("SQLException thrown  " + e);
		}
		
		sql = "select * from supplier " +"where supplier_id = ?;";
		
		try {
			
			createSpyConnection(sql);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		try {
			Supplier testSupplier = supplierDao.retrieveSupplier(supplier.getSupplierId());
			
			verify(spy).setInt(1, supplier.getSupplierId());
			
			verify(spy).executeQuery();
			
			assertEquals("Not able to read the supplier", testSupplier.getSupplierId(), supplier.getSupplierId() );
			
		} catch (SQLException e) {
			
			fail("SQLException thrown from read supplier method:  " + e);
		}
	}
	
	@Test
	public void retrieveAllSuppliersTest() {
		
		sql = "select count(*) from supplier";
		
		int countSupplier = -1;
		
		try {
			trueStmt = realConn.prepareStatement(sql);
			ResultSet rs = trueStmt.executeQuery();
			rs.next();
			countSupplier = rs.getInt(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			fail("SQLException thrown " +e1);
		}
		
		//testing retrieving supplier
		sql = "select * from supplier;";
		
		try {
			
			createSpyConnection(sql);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		try {
			List<Supplier> allSupplier = supplierDao.retrieveAllSupplier();
			
			verify(spy).executeQuery();
			
			assertTrue("Total list size not matching with database ", countSupplier == allSupplier.size());
		} catch (SQLException e) {
			fail("SQLException thrown from retrieveAllSuppliertest " +e);
		}
	}
	
	@Test
	public void deleteSupplierTest() {
		sql = "Insert into supplier (supplier_name, supplier_contact) values " + "(?, ?);";
		
		try {
			trueStmt = realConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			trueStmt.setString(1, supplier.getSupplierName());
			trueStmt.setInt(2,  Integer.parseInt(supplier.getSupplierContactNum()));
			trueStmt.executeUpdate();
			
			//getting primary key id from database for later use
			ResultSet key_rs = trueStmt.getGeneratedKeys();
			key_rs.next();
			supplier.setSupplierId(key_rs.getInt(1));
			
			assertTrue(" inserting test supplier no successful", 1 == trueStmt.executeUpdate());
		} catch (SQLException e) {
			fail("SQLException thrown  " + e);
		}
		
		//to test deleteSupplier
		sql = "delete from supplier " + "where supplier_id = ?;";
		
		try {
			
			createSpyConnection(sql);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		try {
			
			boolean result = supplierDao.deleteSupplier(supplier.getSupplierId());
			
			verify(spy).setInt(1, supplier.getSupplierId());
			
			int i = verify(spy).executeUpdate();
			
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
