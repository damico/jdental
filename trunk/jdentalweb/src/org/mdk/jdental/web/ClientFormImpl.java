package org.mdk.jdental.web;

import java.util.ArrayList;

public class ClientFormImpl implements FormType {
	
	private String successMsg = "<label class='success'>Cadastro de Pacientes preenchido com sucesso!</label>";
	private String tableName = "CLIENT_T";
	
	public ArrayList<FormField> getFormFieldList(){
		ArrayList<FormField> ffList = new ArrayList<FormField>();
		
		ffList.add(new FormField("Nome", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+", "String"));
		ffList.add(new FormField("End", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+", "String"));
		ffList.add(new FormField("Telefone", "text", null, "Preenchimento incorreto!", "[0-9]{3,}+", "String"));
		ffList.add(new FormField("Celular", "text", null, "Preenchimento incorreto!", "[0-9]{3,}+", "String"));
		ffList.add(new FormField("Email", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+", "String"));
		ffList.add(new FormField("Cidade", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+", "String"));
		ffList.add(new FormField("Estado", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{2,}+", "String"));
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
		return "INSERT INTO \"JDENTAL\"."+getTableName()+" (CLIENT_NAME,CLIENT_ADDR,CLIENT_PHONE,CLIENT_MOBILE,CLIENT_EMAIL,CLIENT_CITY,CLIENT_STATE)VALUES(?,?,?,?,?,?,?)";
	}
}
