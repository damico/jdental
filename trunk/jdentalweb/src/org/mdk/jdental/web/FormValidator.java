package org.mdk.jdental.web;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.utils.GenericUtilities;
import org.mdk.jdental.utils.LoggerManager;

public class FormValidator {

	private StringBuffer errorMsg = new StringBuffer();
	private String successMsg = null;
	private String sql = null;
	private boolean error = false;
	private ArrayList<FormField> ffList = null;
	private Map<String, String> formData = new HashMap<String, String>();
	
	public FormValidator(HttpServletRequest request) throws TopLevelException {
		String obj = request.getParameter("object");
		Class c = null;
		Class eval = null;
		Method mc = null;
		Method me = null;
		Method ms = null;
		Object nInst = null;
		
		try {
			eval = Class.forName("org.mdk.jdental.utils.GenericUtilities");
			
			c = Class.forName(obj);
			mc = c.getMethod("getFormFieldList", null);
			ms = c.getMethod("getSQL", null);
			nInst = c.newInstance();
			ffList = (ArrayList<FormField>) mc.invoke(nInst, null);
			sql = (String) ms.invoke(nInst, null);
			setSql(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new TopLevelException(e.getStackTrace());
			
		} catch (SecurityException e) {
			throw new TopLevelException(e.getStackTrace());
		} catch (NoSuchMethodException e) {
			throw new TopLevelException(e.getStackTrace());
		} catch (InstantiationException e) {
			throw new TopLevelException(e.getStackTrace());
		} catch (IllegalAccessException e) {
			throw new TopLevelException(e.getStackTrace());
		} catch (IllegalArgumentException e) {
			throw new TopLevelException(e.getStackTrace());
		} catch (InvocationTargetException e) {
			throw new TopLevelException(e.getStackTrace());
		}
		
		
		GenericUtilities gu = new GenericUtilities();
		
		
		 setFfList(ffList);
		
		
		
		
		for(int i=0; i<ffList.size(); i++){

			boolean rexp = false;
			String label = ffList.get(i).getLabel();
			String pattern = ffList.get(i).getValidationPattern();
			String errMsg = ffList.get(i).getErrMsg();
			String value = null;
			if(label!=null && pattern!=null &&errMsg!=null){
				String element = null;
				try {
					element = URLEncoder.encode(label,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				value = request.getParameter(element);
				
				
				if(pattern.substring(0, 4).equals("eval")){
					try {
						Object[] args_value = { new String (value), new HashMap<String, String>(formData) };

			            Class[] args_class = { Class.forName ("java.lang.String"),  Class.forName ("java.util.Map") };

						me = eval.getMethod(pattern, args_class);
						nInst = c.newInstance();
						me.invoke(nInst, args_value);
					} catch (Exception e) {
						throw new TopLevelException(e.getStackTrace(), e.getMessage());
					}
					
				}else{
					rexp = gu.regex(value, pattern);
				}
				
				if(!rexp && (errMsg!=null)){
					setError(true);
					errorMsg.append("<br><label class='title'>"+label+": </label><label class='error'>"+errMsg+"</label>");
				}else{
					formData.put(label, value);
				}
			}
			setFormData(formData);
		}
		
		if(!getError()){
			try {
				mc = c.getMethod("getFormSuccessMsg", null);
				successMsg = (String) mc.invoke(nInst, null);
			} catch (SecurityException e) {
				throw new TopLevelException(e.getStackTrace());
			} catch (NoSuchMethodException e) {
				throw new TopLevelException(e.getStackTrace());
			} catch (IllegalArgumentException e) {
				throw new TopLevelException(e.getStackTrace());
			} catch (IllegalAccessException e) {
				throw new TopLevelException(e.getStackTrace());
			} catch (InvocationTargetException e) {
				throw new TopLevelException(e.getStackTrace());
			}
			
		}
		
		
	}
	
	public void setError(boolean error){
		this.error = error;
	}
	
	public boolean getError(){
		return error;
	}
	
	public String getSuccessMsg(){
		return successMsg;
	}
	
	public String getErrorMsg(){
		return errorMsg.toString();
	}

	public ArrayList<FormField> getFfList() {
		return ffList;
	}

	public void setFfList(ArrayList<FormField> ffList) {
		this.ffList = ffList;
	}

	public Map<String, String> getFormData() {
		return formData;
	}

	public void setFormData(Map<String, String> formData) {
		this.formData = formData;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	

	

	
}
