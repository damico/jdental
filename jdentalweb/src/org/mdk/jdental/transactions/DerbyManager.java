package org.mdk.jdental.transactions;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.mdk.jdental.dataobjects.SelectList;
import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.utils.LoggerManager;
import org.mdk.jdental.web.FormField;
import org.mdk.jdental.web.FormType;



public class DerbyManager  implements DatabaseAdaptor {

	private DatabaseConfig databaseConfig = null;
	public DerbyManager(DatabaseConfig databaseConfig) {
		this.databaseConfig = databaseConfig;
	}
	

	
	


	public int openSessionForUser(String login, String passwd) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		int ret = 0;
		try {
			Class.forName(databaseConfig.getClassfn());
			con = DriverManager.getConnection(databaseConfig.getDbUrl());
			String sql = "SELECT USER_TYPE FROM JDENTAL.USER_T WHERE USER_LOGIN = ? AND USER_PASSWD = ? AND USER_APPVD = 1";
			ps = con.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, passwd);
			rs = ps.executeQuery();
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Verifying user: "+login);
			
			while(rs.next()){
				ret = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ret;
	}


	public void regUser(Map<String, String> map) {
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			Class.forName(databaseConfig.getClassfn());
			con = DriverManager.getConnection(databaseConfig.getDbUrl());
			String sql = "INSERT INTO JDENTAL.USER_T (USER_LOGIN,USER_EMAIL,USER_PASSWD,USER_APPVD,USER_TYPE)VALUES(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, map.get(URLEncoder.encode("Login", "UTF-8")));
			ps.setString(2, map.get(URLEncoder.encode("Email", "UTF-8")));
			ps.setString(3, map.get(URLEncoder.encode("Senha", "UTF-8")));
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			ps.executeUpdate();
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Inserting user...");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public void genericInsert(Map<String, String> map,ArrayList<FormField> ffArray, String sql) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public SelectList genericSearch(Map<String, String> formData,
			ArrayList<FormField> ffList, String sql, FormType formType)
			throws TopLevelException {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public Map<Integer, String> getClientIDs() throws TopLevelException {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public boolean scheduleInsert(Map<String, String> formData, String sql)
			throws TopLevelException {
		// TODO Auto-generated method stub
		return false;
	}

}
