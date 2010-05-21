package org.mdk.jdental.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class FormGenerator {
	private FormType formType = null;
	private StringBuffer elements = null;
	public FormGenerator(String formName, String postName, FormType formType) throws UnsupportedEncodingException{
		this.formType = formType;
		elements =  new StringBuffer();
		elements.append("<form method='post' name='"+getName(formName)+"' action='"+postName+"' class='box' >");
	}
	
	public String displayForm() throws UnsupportedEncodingException {
		
		ArrayList<FormField> ffList = formType.getFormFieldList();
		for(int i=0; i<ffList.size(); i++){
			
			String label = null2String(ffList.get(i).getLabel());
			String value = null2String(ffList.get(i).getValue());
			
			if(ffList.get(i).getInputType().equalsIgnoreCase("hidden")){
				elements.append("<input type='"+ffList.get(i).getInputType()+"' name='"+getName(label)+"' value='"+value+"'><br>\n");
			}else if(ffList.get(i).getInputType().equalsIgnoreCase("submit")) {
				elements.append("<input type='"+ffList.get(i).getInputType()+"' name='"+getName(label)+"' value='"+value+"' class='botao'><br>\n");
			}else{	
				elements.append("<label class='title'>"+label+":</label> <right><input type='"+ffList.get(i).getInputType()+"' name='"+getName(label)+"' value='"+value+"' class='campos'></right><br>\n");
			}
		}
		elements.append("</form>");
		return elements.toString();
	}
	
	private String getName(String sourceName) throws UnsupportedEncodingException{
		return URLEncoder.encode(sourceName, "UTF-8");
	}

	private String null2String(String value){
		if(value==null) value = "";
		return value;
	}
}
