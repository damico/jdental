package org.mdk.jdental.web;

import java.util.ArrayList;

public class ClientFormImpl implements FormType {
	public ArrayList<FormField> getFormFieldList(){
		ArrayList<FormField> ffList = new ArrayList<FormField>();
		
		ffList.add(new FormField("Nome", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+"));
		ffList.add(new FormField("Enderećo", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+"));
		ffList.add(new FormField("Telefone", "text", null, "Preenchimento incorreto!", "[0-9]{3,}+"));
		ffList.add(new FormField("Celular", "text", null, "Preenchimento incorreto!", "[0-9]{3,}+"));
		ffList.add(new FormField("Email", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+"));
		ffList.add(new FormField("Cidade", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+"));
		ffList.add(new FormField("Estado", "text", null, "Preenchimento incorreto!", "[a-zA-Z]{3,}+"));
		ffList.add(new FormField(null, "Submit", "Cadastrar", null, null));
		ffList.add(new FormField("mode", "hidden", "insert", null, null));
		ffList.add(new FormField("object", "hidden", this.getClass().getName(), null, null));
		
		return ffList;
	}
}
