package org.mdk.jdental.utils;

public interface Constants {

	
	/* App data */
	

	public static final int WEB_SERVER_PORT = 8989;

	public static final String RENDER_SERVLET_FILE_PARAM = "file";

	public static final String DEFAULT_CONTENTPKG_PATH = "/opt/jdental/";

	public static final String APP_VERSION = "0.0.1";

	public static final String APP_NAME = "jDental";

	/* conf file */
	public static final String PROPERTIES_PATH = "/etc/jdental/jdental.conf";
	
	
	public static final String DB_CLASSFN = "classfn";
	public static final String DB_PATH = "dbpath";
	public static final String DB_HOST = "dbhost";
	public static final String DB_NAME = "dbname";
	public static final String DB_USER = "dbuser";
	public static final String DB_PASSWD = "dbpasswd";
	public static final String DB_PORT = "dbport";
	
	public static final String APP_FOLDER = "/opt/jdental/";

	public static final String LOG_NAME = "jdental.log";
	
	public static final String SEVERE_LOGLEVEL = " S ";
	public static final String NORMAL_LOGLEVEL = " N ";
	public static final int FIXED_LOGLIMIT = 5000000;

	public static final String SQL_SAVEEMOTION_DERBY = "INSERT INTO RSSF.URI (URI,EMOTION)VALUES(?,?)";

	public static final String SQL_GETFEELING_DERBY = "SELECT COUNT(EMOTION) FROM RSSF.URI WHERE TIMESTAMP >= ? AND EMOTION = ?";

	public static final String LOG_FOLDER = "/var/log/jdental/";

}