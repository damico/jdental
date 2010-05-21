package org.mdk.jdental.transactions;



public class DatabaseConfig {
	
	private String dbUrl;
	private String classfn;
	
	public DatabaseConfig(String classfn, String dbpath) {
		setClassfn(classfn);
		setDbUrl("jdbc:derby:" + dbpath + ";create=true;");
	}

	public DatabaseConfig(String classfn, String dbhost, String dbport, String dbname,String dbuser,String dbpasswd) {
		setClassfn(classfn);
		setDbUrl("jdbc:postgresql://"+dbhost+":"+dbport+"/"+dbname+"?user="+dbuser+"&password="+dbpasswd);
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getClassfn() {
		return classfn;
	}

	public void setClassfn(String classfn) {
		this.classfn = classfn;
	}

	
}
