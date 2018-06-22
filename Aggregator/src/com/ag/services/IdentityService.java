package com.ag.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.ag.objects.Identity;
import com.ag.utilities.DatabaseManager;
import com.ag.utilities.Encryption;

public class IdentityService {

	private static Logger logger = Logger.getLogger(IdentityService.class);
	private static PreparedStatement ps;
	private static Connection conn;
	private static ResultSet rs;

	public static boolean createIdentity(Identity i) {

		boolean isCreated = false;

		try {
			conn = DatabaseManager.getConnection();
		} catch (SQLException e) {
			logger.error("Error getting DB Connection", e);
		}

		try{

			String create = "insert into ag_identities(fname, lname, email, loginId, password) values (?,?,?,?,?)";
			ps = conn.prepareStatement(create);
			ps.setString(1, i.getFname());
			ps.setString(2, i.getLname());
			ps.setString(3, i.getEmail());
			ps.setString(4, i.getLoginId());
			ps.setString(5, Encryption.encrypt("changeme"));

			ps.executeUpdate();

			isCreated = true;

		}catch (SQLException e) {
			logger.error("Error creating identity", e);
		}
		catch (Exception e) {
			logger.error("Non db error", e);
		}

		finally{
			try{
				conn.close();
				ps.close();
			}catch(SQLException e) {
				logger.error("Error closing connection.", e);
			}
		}

		return isCreated;		
	}

	public static List<Identity> getIdentities() {

		List<Identity> newTasks = new ArrayList<Identity>();

		try {
			conn = DatabaseManager.getConnection();
		} catch (SQLException e) {
			logger.error("Error getting DB Connection", e);
		}

		try{

			String getIdentities = "select id, fname, lname, email, loginId from ag_identities;";
			ps = conn.prepareStatement(getIdentities);
			rs = ps.executeQuery();

			while (rs.next())
			{
				Identity i = new Identity();

				i.setId(rs.getInt(1));
				i.setFname(rs.getString(2));
				i.setLname(rs.getString(3));
				i.setEmail(rs.getString(4));
				i.setLoginId(rs.getString(5));

				newTasks.add(i);
			}	

		}catch (SQLException e) {
			logger.error("Error getting identities", e);
		}
		catch (Exception e) {
			logger.error("Non db error", e);
		}

		try{
			conn.close();
			ps.close();
			rs.close();
		}catch(SQLException e) {
			logger.error("Error closing connection.", e);
		}

		return newTasks;


	}

}
