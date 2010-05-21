package org.mdk.jdental.web;

import java.util.ArrayList;

public class SearchClientFormImpl implements FormType {

	@Override
	public ArrayList<FormField> getFormFieldList() {
ArrayList<FormField> ffList = new ArrayList<FormField>();
		
		ffList.add(new FormField("Paciente", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+", "String"));
		ffList.add(new FormField(null, "Submit", "Buscar", null, null));
		ffList.add(new FormField("mode", "hidden", "select", null, null));
		ffList.add(new FormField("object", "hidden", this.getClass().getName(), null, null));
		
		return ffList;
	}

	@Override
	public String getFormSuccessMsg() {
		return null;
	}

	@Override
	public String getSQL() {
		return "SELECT  CLIENT_NAME,CLIENT_ADDR,CLIENT_PHONE,CLIENT_MOBILE,CLIENT_EMAIL,CLIENT_CITY,CLIENT_STATE FROM \"JDENTAL\"."+getTableName()+" WHERE CLIENT_NAME LIKE (?)";
	}

	@Override
	public String getTableName() {
		return "CLIENT_T";
	}

	@Override
	public void setFormSuccessMsg(String successMsg) {
		// TODO Auto-generated method stub

	}

}
