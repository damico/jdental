package org.mdk.jdental.components;

import java.util.ArrayList;
import java.util.Map;

import org.mdk.jdental.dataobjects.SelectList;
import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.transactions.DatabaseAdaptor;
import org.mdk.jdental.transactions.TransactionManager;
import org.mdk.jdental.utils.LoggerManager;
import org.mdk.jdental.web.FormField;
import org.mdk.jdental.web.FormType;
import org.mdk.jdental.web.SessionManager;

public class Controller {

	private DatabaseAdaptor adaptor = null;
	
	public Controller(){
		TransactionManager tm = new TransactionManager();
		this.adaptor = tm.getDbAdaptor();
	}

	
	public boolean openSessionForUser(String login, String passwd) {
		boolean ret = false;
		
		
		
		int type = adaptor.openSessionForUser(login, passwd);
		if(type > 0){
			SessionManager sm = new SessionManager();
			sm.createSession(login, type);
			ret = true;
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Session opened for: "+login+" Type: "+type);
		}
		return ret;
	}

	public void regUser(Map<String, String> map) {
		adaptor.regUser(map);	
	}
	
	public boolean genericInsert(Map<String, String> map, ArrayList<FormField> ffArray, String sql) {
		boolean ret = false;
		try {
			adaptor.genericInsert(map, ffArray, sql);
			ret = true;
		} catch (TopLevelException e) {
			e.printStackTrace();
		}
		return ret;
	}


	public SelectList genericSearch(Map<String, String> formData, ArrayList<FormField> ffList, String sql, FormType formType) {
		SelectList ret = null;
		try {
			ret = adaptor.genericSearch(formData, ffList, sql, formType);
		} catch (TopLevelException e) {
			e.printStackTrace();
		}
		return ret;
	}


	public Map<Integer, String> getClientIDs() {
		Map<Integer, String>  clientList = null;
		try {
			clientList = adaptor.getClientIDs();
		} catch (TopLevelException e) {
			e.printStackTrace();
		} 
		return clientList;
	}


	public boolean scheduleInsert(Map<String, String> formData, ArrayList<FormField> ffList, String sql) {
		boolean ret = false;
		try {
			ret = adaptor.scheduleInsert(formData, sql);
		} catch (TopLevelException e) {
			e.printStackTrace();
		} 
		return ret;
	}
}
