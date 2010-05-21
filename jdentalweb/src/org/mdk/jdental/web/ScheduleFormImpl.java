package org.mdk.jdental.web;

import java.util.ArrayList;

public class ScheduleFormImpl implements FormType {

	private String successMsg = "<label class='success'>Novo Compromisso salvo com sucesso!</label>";
	private String tableName = "SCHEDULE_T";
	
	@Override
	public ArrayList<FormField> getFormFieldList() {
		ArrayList<FormField> ffList = new ArrayList<FormField>();
		
		ffList.add(new FormField("event", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+", "String"));
		ffList.add(new FormField("i_d", "text", null, "Preenchimento incorreto!", "[0-9]{1,}+", "String"));
		ffList.add(new FormField("i_m", "text", null, "Preenchimento incorreto!", "[0-9]{1,}+", "String"));
		ffList.add(new FormField("i_y", "text", null, "Preenchimento incorreto!", "[0-9]{1,}+", "String"));
		
		ffList.add(new FormField("i_h", "text", null, "Preenchimento incorreto!", "[0-9]{1,}+", "String"));
		ffList.add(new FormField("i_min", "text", null, "Preenchimento incorreto!", "[0-9]{1,}+", "String"));
		
		
		ffList.add(new FormField("f_d", "text", null, "Preenchimento incorreto!", "[0-9]{1,}+", "String"));
		ffList.add(new FormField("f_m", "text", null, "Preenchimento incorreto!", "[0-9]{1,}+", "String"));
		ffList.add(new FormField("f_y", "text", null, "Preenchimento incorreto!", "[0-9]{2,}+", "String"));
		
		ffList.add(new FormField("f_h", "text", null, "Preenchimento incorreto!", "[0-9]{1,}+", "String"));
		ffList.add(new FormField("f_min", "text", null, "Preenchimento incorreto!", "[0-9]{1,}+", "String"));

		
		ffList.add(new FormField("client", "text", null, "Preenchimento incorreto!", "[0-9]{1,}+", "String"));
		ffList.add(new FormField("details", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+", "String"));
		ffList.add(new FormField(null, "Submit", "Cadastrar", null, null));
		ffList.add(new FormField("mode", "hidden", "insert", null, null));
		ffList.add(new FormField("object", "hidden", this.getClass().getName(), null, null));
		
		return ffList;
	}

	@Override
	public String getFormSuccessMsg() {
		return successMsg;
	}

	@Override
	public void setFormSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public String getSQL() {
		return "INSERT INTO \"JDENTAL\"."+getTableName()+" (SCHEDULE_EVENT,  SCHEDULE_USER_ID , SCHEDULE_CLIENT_ID , SCHEDULE_INITIAL_DATE , SCHEDULE_END_DATE , SCHEDULE_DETAILS)VALUES(?,?,?,?,?,?)";
	}

}
