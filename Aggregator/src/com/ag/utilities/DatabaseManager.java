package com.ag.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

/**
 * Database Connection Manager
 * @author blantaff@gmail.com
 *
 */
public class DatabaseManager {
	private static Logger logger = Logger.getLogger(DatabaseManager.class);
	static ResourceBundle rb = ResourceBundle.getBundle("DatabaseSettings"); 
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = rb.getString("DBURL"); 
	private static final String USER = rb.getString("DBUSER"); 
	private static final String PASS = rb.getString("DBPASS"); 

	private static DatabaseManager instance; 

	DatabaseManager() throws SQLException { 
		init(); 
	} 

	public static DatabaseManager getInstance() throws SQLException { 
		if (instance == null) 
			instance = new DatabaseManager(); 
		return instance; 
	} 

	public void init() throws SQLException { 
		try { 
			Class.forName(DRIVER).newInstance(); 
		}
		catch (Exception ex) { 
			logger.error("Problem connecting to database: " + ex);
		} 
	} 

	/**
	 * Get database connection
	 * @return DB Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException { 
		instance = DatabaseManager.getInstance(); 
		Connection conn = DriverManager.getConnection(URL, USER, PASS); 
		if (conn == null) 
			throw new SQLException("Pool connection failed"); 
		return conn; 
	} 

	public static void close(Connection conn) { 
		if (conn != null) { 
			try { 
				conn.close(); 
			} catch (Exception ignored) { 
			} 
		} 
	} 

	public static void close(Statement stmt) { 
		if (stmt != null) { 
			try { 
				stmt.close(); 
			} catch (Exception ignored) { 
			} 
		} 
	} 

	public static void close(ResultSet rset) { 
		if (rset != null) { 
			try { 
				rset.close(); 
			} catch (Exception ignored) { 
			} 
		} 
	} 
} 