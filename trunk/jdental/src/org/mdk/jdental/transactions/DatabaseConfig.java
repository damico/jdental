package org.mdk.jdental.transactions;

import org.mdk.jdental.utils.Constants;
import org.mdk.jdental.utils.ManageProperties;

public class DatabaseConfig {
	public String getClassfn(){
		return ManageProperties.getInstance().read(Constants.DB_CLASSFN);
	}
	
	public String getDBurl(){
		String dburl = "jdbc:derby:" + ManageProperties.getInstance().read(Constants.DB_PATH) + ";create=true;";
		return dburl;
	}
}
