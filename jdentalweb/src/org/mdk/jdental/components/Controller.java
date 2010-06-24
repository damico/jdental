package org.mdk.jdental.components;

import java.util.ArrayList;
import java.util.Map;

import org.mdk.jdental.dataobjects.SelectList;
import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.transactions.DatabaseAdaptor;
import org.mdk.jdental.transactions.TransactionManager;
import org.mdk.jdental.utils.GenericUtilities;
import org.mdk.jdental.utils.LoggerManager;
import org.mdk.jdental.web.FormField;
import org.mdk.jdental.web.FormType;
import org.mdk.jdental.web.SessionManager;

import com.sun.jndi.toolkit.url.GenericURLContext;

public class Controller {

	private DatabaseAdaptor adaptor = null;
	
	public Controller(){
		TransactionManager tm = new TransactionManager();
		this.adaptor = tm.getDbAdaptor();
	}

	
	public boolean openSessionForUser(String login, String passwd) {
		boolean ret = false;
		
		int type = -1;
		
		try {
			type = adaptor.openSessionForUser(login, passwd);
		} catch (Exception e){
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), "Problems with Database Connection [0002]");
		}
		
		
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
		GenericUtilities gu = new GenericUtilities();
		String sdatei = formData.get("i_d")+"/"+formData.get("i_m")+"/"+formData.get("i_y")+" "+formData.get("i_h")+":"+formData.get("i_min")+":00";
		String sdatee = formData.get("f_d")+"/"+formData.get("f_m")+"/"+formData.get("f_y")+" "+formData.get("f_h")+":"+formData.get("f_min")+":00";
		
		try {
			java.sql.Timestamp datei = gu.getString2SqlDate(sdatei);
			java.sql.Timestamp datee = gu.getString2SqlDate(sdatee);
			ret = adaptor.scheduleInsert(formData, sql, datei, datee);
		} catch (TopLevelException e) {
			e.printStackTrace();
		} 
		return ret;
	}
}
