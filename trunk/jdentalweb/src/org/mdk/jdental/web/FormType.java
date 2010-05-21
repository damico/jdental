package org.mdk.jdental.web;

import java.util.ArrayList;

public interface FormType {
	public ArrayList<FormField> getFormFieldList();
	public String getFormSuccessMsg();
	public void setFormSuccessMsg(String successMsg);
	public String getSQL();
	public String getTableName();
}
