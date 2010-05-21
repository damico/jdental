package org.mdk.jdental.transactions;

import java.util.ArrayList;
import java.util.Map;

import org.mdk.jdental.dataobjects.SelectList;
import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.web.FormField;
import org.mdk.jdental.web.FormType;


public interface DatabaseAdaptor {
	
	public int openSessionForUser(String login, String passwd);
	public void regUser(Map<String, String> map);
	public void genericInsert(Map<String, String> map, ArrayList<FormField> ffArray, String sql) throws TopLevelException;
	public SelectList genericSearch(Map<String, String> formData, ArrayList<FormField> ffList, String sql, FormType formType) throws TopLevelException;
	public Map<Integer, String> getClientIDs() throws TopLevelException;	
}
