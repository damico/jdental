package org.mdk.jdental.transactions;

import org.mdk.jdental.utils.Constants;
import org.mdk.jdental.utils.ManageProperties;

public class TransactionManager {

	public DatabaseAdaptor getDbAdaptor() {
		DatabaseAdaptor ret = null;
		String classfn = ManageProperties.getInstance().read(Constants.DB_CLASSFN);
		if(classfn.equals("org.postgresql.Driver")){
			String dbhost = ManageProperties.getInstance().read(Constants.DB_HOST);
			String dbport = ManageProperties.getInstance().read(Constants.DB_PORT);
			String dbname = ManageProperties.getInstance().read(Constants.DB_NAME);
			String dbuser = ManageProperties.getInstance().read(Constants.DB_USER);
			String dbpasswd = ManageProperties.getInstance().read(Constants.DB_PASSWD);
			
			DatabaseConfig dConfig = new DatabaseConfig(classfn, dbhost, dbport, dbname, dbuser, dbpasswd);
			ret = new PostgresManager(dConfig);
		}
		return ret;
	}

}
