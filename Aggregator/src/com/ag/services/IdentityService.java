package com.ag.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.ag.objects.Identity;
import com.ag.utilities.DatabaseManager;
import com.ag.utilities.Encryption;

public class IdentityService {

	private static Logger logger = Logger.getLogger(IdentityService.class);
	private static PreparedStatement ps;
	private static Connection conn;

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

}
