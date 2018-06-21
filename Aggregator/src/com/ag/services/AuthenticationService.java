package com.ag.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.ag.objects.Identity;
import com.ag.utilities.DatabaseManager;
import com.ag.utilities.Encryption;

public class AuthenticationService {

	private static Logger logger = Logger.getLogger(AuthenticationService.class);
	private static PreparedStatement ps;
	private static Connection conn;
	private static ResultSet rs;

	/**
	 * Get user data and build profile.
	 * @param username
	 * @param password
	 * @return
	 */
	public static  Identity getUserProfile(String loginId, String password) {

		Identity user = new Identity();

		try {
			conn = DatabaseManager.getConnection();
		} catch (SQLException e) {
			logger.error("Error getting DB Connection", e);
		}

		try{
			String getUserData = "select id, fname, lname, email from ag_identities where loginId = ? and password = ?";
			ps = conn.prepareStatement(getUserData);
			ps.setString(1, loginId);
			ps.setString(2, Encryption.encrypt(password));
			rs = ps.executeQuery();

			while (rs.next())
			{
				user.setId(rs.getInt(1));
				user.setFname(rs.getString(2));
				user.setLname(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setLoginId(loginId);
			}

		}catch (SQLException e) {
			logger.error("Error getting user profile", e);
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

		return user;
	}

	/**
	 * Validates a user based on login creds.
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean isValidUser(String loginId, String password) {

		boolean isValid = false;

		try {
			conn = DatabaseManager.getConnection();
		} catch (SQLException e) {
			logger.error("Error getting DB Connection", e);
		}

		try{
			String verifyUser = "Select id from ag_identities where loginId = ? and password = ?";
			ps = conn.prepareStatement(verifyUser);
			ps.setString(1, loginId);
			ps.setString(2, Encryption.encrypt(password));
			rs = ps.executeQuery();

			while (rs.next())
			{
				isValid = true;
			}

		}catch (SQLException e) {
			logger.error("Error validating user", e);
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

		return isValid;
	}

}
