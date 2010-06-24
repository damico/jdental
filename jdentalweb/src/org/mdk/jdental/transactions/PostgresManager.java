package org.mdk.jdental.transactions;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.mdk.jdental.dataobjects.SelectList;
import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.utils.LoggerManager;
import org.mdk.jdental.web.FormField;
import org.mdk.jdental.web.FormType;

public class PostgresManager implements DatabaseAdaptor {

	private DatabaseConfig databaseConfig = null;
	public PostgresManager(DatabaseConfig databaseConfig) {
		this.databaseConfig = databaseConfig;
	}

	

	public int openSessionForUser(String login, String passwd) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		int ret = -1;
		try {
			Class.forName(databaseConfig.getClassfn());
			con = DriverManager.getConnection(databaseConfig.getDbUrl());
			String sql = "SELECT USER_TYPE FROM \"JDENTAL\".USER_T WHERE USER_LOGIN = ? AND USER_PASSWD = ? AND USER_APPVD = 1";
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
			String sql = "INSERT INTO \"JDENTAL\".USER_T (USER_LOGIN,USER_EMAIL,USER_PASSWD,USER_APPVD,USER_TYPE)VALUES(?,?,?,?,?)";
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
	public void genericInsert(Map<String, String> map, ArrayList<FormField> ffArray, String sql) throws TopLevelException {
		PreparedStatement ps = null;
		Connection con = null;

		try {
			Class.forName(databaseConfig.getClassfn());

			con = DriverManager.getConnection(databaseConfig.getDbUrl());
			ps = con.prepareStatement(sql);

			for(int i=0; i<ffArray.size(); i++){
				String dataType = ffArray.get(i).getDataType();

				if(dataType!=null){
					String element = map.get(URLEncoder.encode(ffArray.get(i).getLabel(), "UTF-8"));
					//LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Element "+element);
					if(dataType.equalsIgnoreCase("String")){
						ps.setString(i+1, element);
					}else if(dataType.equalsIgnoreCase("Integer")){
						ps.setInt(i+1, Integer.valueOf(element));
					}else if(dataType.equalsIgnoreCase("Double")){
						ps.setDouble(i+1, Double.valueOf(element));
					}else{
						LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Data Type not defined!");
					}
				}


			}
			ps.executeUpdate();
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Inserting client...");
		} catch (SQLException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			throw new TopLevelException(e.getStackTrace());
		} catch (ClassNotFoundException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			throw new TopLevelException(e.getStackTrace());
		} catch (UnsupportedEncodingException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			throw new TopLevelException(e.getStackTrace());
		}

	}

	@Override
	public SelectList genericSearch(Map<String, String> formData, ArrayList<FormField> ffList, String sql, FormType formType) throws TopLevelException {

		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		SelectList ret = null;

		try {
			Class.forName(databaseConfig.getClassfn());

			con = DriverManager.getConnection(databaseConfig.getDbUrl());
			ps = con.prepareStatement(sql);

			for(int i=0; i<ffList.size(); i++){
				String dataType = ffList.get(i).getDataType();

				if(dataType!=null){
					String element = formData.get(URLEncoder.encode(ffList.get(i).getLabel(), "UTF-8"));
					if(dataType.equalsIgnoreCase("String")){
						ps.setString(i+1, "%"+element+"%");
					}else if(dataType.equalsIgnoreCase("Integer")){
						ps.setInt(i+1, Integer.valueOf(element));
					}else if(dataType.equalsIgnoreCase("Double")){
						ps.setDouble(i+1, Double.valueOf(element));
					}else{
						LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Data Type not defined!");
					}
				}


			}
			rs = ps.executeQuery();

			ArrayList<FormField> ff = formType.getFormFieldList();
			ArrayList<String> labels = new ArrayList<String>();
			ArrayList<String[]> rows = new ArrayList<String[]>();
			
			for(int i=0; i<ff.size(); i++){
				String dataType = ff.get(i).getDataType();
				if(dataType!=null) labels.add(URLEncoder.encode(ff.get(i).getLabel(), "UTF-8"));
			}
			

			while(rs.next()){
				String[] row = new String[labels.size()];
				for(int i=0; i<row.length; i++){
					try{
						row[i] = rs.getString(i+1);
					}catch(Exception e){
						row[i] = String.valueOf(rs.getString(i+1));
					}
				}
				rows.add(row);
			}
			ret = new SelectList(rows, labels);

		} catch (SQLException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), "["+sql+"] "+e.getMessage());
			throw new TopLevelException(e.getStackTrace());
		} catch (ClassNotFoundException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			throw new TopLevelException(e.getStackTrace());
		} catch (UnsupportedEncodingException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			throw new TopLevelException(e.getStackTrace());
		}
		return ret;
	}



	@Override
	public Map<Integer, String> getClientIDs() throws TopLevelException {
		
		Map<Integer, String> ret = null;
		
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = "SELECT CLIENT_ID, CLIENT_NAME FROM \"JDENTAL\".CLIENT_T ORDER BY CLIENT_NAME";
		
		try {
			Class.forName(databaseConfig.getClassfn());
			
			con = DriverManager.getConnection(databaseConfig.getDbUrl());
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			
			ret = new HashMap<Integer, String>();

			while(rs.next()){
				ret.put(rs.getInt(1), rs.getString(2));
			}

		} catch (SQLException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), "["+sql+"] "+e.getMessage());
			throw new TopLevelException(e.getStackTrace());
		} catch (ClassNotFoundException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			throw new TopLevelException(e.getStackTrace());
		} 
		return ret;
	}


	public int getUserIdByLogin(String login) throws TopLevelException {
		
		int ret = -1;
		
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = "SELECT USER_ID FROM \"JDENTAL\".USER_T WHERE USER_LOGIN = ?";
		
		try {
			Class.forName(databaseConfig.getClassfn());
			
			con = DriverManager.getConnection(databaseConfig.getDbUrl());
			ps = con.prepareStatement(sql);
			ps.setString(1, login);
			rs = ps.executeQuery();
			while(rs.next()){
				ret = rs.getInt(1);
			}

		} catch (SQLException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), "["+sql+"] "+e.getMessage());
			throw new TopLevelException(e.getStackTrace());
		} catch (ClassNotFoundException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			throw new TopLevelException(e.getStackTrace());
		} 
		return ret;
	}

	@Override
	public boolean scheduleInsert(Map<String, String> formData, String sql,	Timestamp datei, Timestamp datee) throws TopLevelException {
		
		PreparedStatement ps = null;
		Connection con = null;
		boolean ret = false;

		try {
			Class.forName(databaseConfig.getClassfn());
			con = DriverManager.getConnection(databaseConfig.getDbUrl());
			ps = con.prepareStatement(sql);
			ps.setString(1, formData.get("event"));
			ps.setInt(2, getUserIdByLogin(formData.get("login")));
			ps.setInt(3, Integer.parseInt(formData.get("client")));
			ps.setTimestamp(4, datei);
			ps.setTimestamp(5, datee);
			ps.setString(6, formData.get("details"));
			ps.executeUpdate();
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Inserting schedule event...");
			ret = true;
		} catch (SQLException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), "["+sql+"] "+e.getMessage());
			e.printStackTrace();
			throw new TopLevelException(e.getStackTrace());
		} catch (ClassNotFoundException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			throw new TopLevelException(e.getStackTrace());
		} 
		
		
		
		return ret;
	}



	


}
