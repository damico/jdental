package org.mdk.jdental.web;

import java.util.ArrayList;

public class RegFormImpl implements FormType {
	
	private String successMsg = "<label class='success'>Registro preenchido com sucesso!</label>";
	
	public ArrayList<FormField> getFormFieldList(){
		ArrayList<FormField> ffList = new ArrayList<FormField>();
		
		ffList.add(new FormField("Login", "text", null, "Preenchimento incorreto!", "evalLogin"));
		ffList.add(new FormField("Email", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+"));
		ffList.add(new FormField("Senha", "password", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+"));
		ffList.add(new FormField("Repita a Senha", "password", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+"));

		ffList.add(new FormField(null, "Submit", "Registrar", null, null));
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
		return "USER_T";
	}

	@Override
	public String getSQL() {
		// TODO Auto-generated method stub
		return null;
	}
}
