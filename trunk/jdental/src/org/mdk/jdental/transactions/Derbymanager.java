package org.mdk.jdental.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.utils.Constants;



public class Derbymanager extends DatabaseConfig implements DatabaseAdaptor {

	private static final Derbymanager INSTANCE = new Derbymanager();
	public static Derbymanager getInstance(){
		return INSTANCE;
	}
	
	
	@Override
	public void saveEmotion(int emotion, String uri) throws TopLevelException {
		PreparedStatement ps = null;
		Connection con = null;
	
		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			ps = con.prepareStatement(Constants.SQL_SAVEEMOTION_DERBY);
			ps.setInt(1, emotion);
			ps.setString(2, uri);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new TopLevelException(e.getStackTrace(), e.getMessage());
		} catch (ClassNotFoundException e) {
			
			throw new TopLevelException(e.getStackTrace());
		} catch (Exception e) {
			
			throw new TopLevelException(e.getStackTrace());
		} finally {
			try {
				if(ps!=null) ps.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				throw new TopLevelException(e.getStackTrace());
			}
		}
		
	}


	public int getTodayFeeling(int i) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		int ret = 0;
		try {
			Class.forName(getClassfn());
			con = DriverManager.getConnection(getDBurl());
			String sql = Constants.SQL_GETFEELING_DERBY;
			ps = con.prepareStatement(sql);
			ps.setString(1, "");
			ps.setInt(2, i);
			rs = ps.executeQuery();
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

	
	

}
