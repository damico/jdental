package org.mdk.jdental.web;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.utils.GenericUtilities;

public class FormValidator {

	private StringBuffer errorMsg = new StringBuffer();
	private String successMsg = null;
	private boolean error = false;
	
	public FormValidator(HttpServletRequest request) throws TopLevelException {
		String obj = request.getParameter("object");
		Class c = null;
		Method m = null;
		Object nInst = null;
		ArrayList<FormField> ffList = null;
		try {
			c = Class.forName(obj);
			m = c.getMethod("getFormFieldList", null);
			nInst = c.newInstance();
			ffList = (ArrayList<FormField>) m.invoke(nInst, null);
		} catch (ClassNotFoundException e) {
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
		
		
		 
		
		
		
		
		for(int i=0; i<ffList.size(); i++){
			System.out.println(ffList.get(i).getValidationPattern());
			boolean rexp = false;
			String label = ffList.get(i).getLabel();
			String pattern = ffList.get(i).getValidationPattern();
			String errMsg = ffList.get(i).getErrMsg();
			
			if(label!=null && pattern!=null &&errMsg!=null){
				String element = null;
				try {
					element = URLEncoder.encode(label,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				rexp = gu.regex(request.getParameter(element), pattern);
			}
						
			if(!rexp && (errMsg!=null)){
				setError(true);
				errorMsg.append("<br><label class='title'>"+label+": </label><label class='error'>"+errMsg+"</label>");
			}
		}
		
		if(!getError()){
			try {
				m = c.getMethod("getFormSuccessMsg", null);
				successMsg = (String) m.invoke(nInst, null);
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

}
