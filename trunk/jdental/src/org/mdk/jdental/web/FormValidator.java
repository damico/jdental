package org.mdk.jdental.web;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.mdk.jdental.utils.GenericUtilities;

public class FormValidator {

	public FormValidator(HttpServletRequest request) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, UnsupportedEncodingException {
		String obj = request.getParameter("object");
		Class c = Class.forName(obj);
		Method m = c.getMethod("getFormFieldList", null);
		
		GenericUtilities gu = new GenericUtilities();
		
		ArrayList<FormField> ffList = (ArrayList<FormField>) m.invoke(c.newInstance(), null);
		for(int i=0; i<ffList.size(); i++){
			System.out.println(ffList.get(i).getValidationPattern());
			boolean rexp = gu.regex(request.getParameter(URLEncoder.encode(ffList.get(i).getName(),"UTF-8")), ffList.get(i).getValidationPattern());
			
		}
	}

}
