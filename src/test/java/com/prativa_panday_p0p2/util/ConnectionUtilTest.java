package com.prativa_panday_p0p2.util;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionUtilTest {

	private ConnectionUtil connectionUtil = new ConnectionUtil();

	@Test
	public void test() throws SQLException {
		Connection conn = connectionUtil.createConnection();
		conn.close();
		
		try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
