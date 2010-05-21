package org.mdk.jdental.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mdk.jdental.exceptions.TopLevelException;

public class GenericUtilities {

	public String getCurrentDateTimeFormated(String format){
		Date date = new Date();
		Format formatter = new SimpleDateFormat(format);
		String stime = formatter.format(date);
		return stime;
	}


	public java.sql.Timestamp getString2SqlDate(String sdate) throws TopLevelException{
		java.sql.Timestamp ret = null;
		DateFormat formatter = new SimpleDateFormat(Constants.DEFAULT_DATETIME_FORMAT);  
		try {
			//java.util.T date = (java.util.Date) formatter.parse(sdate);
			ret = new java.sql.Timestamp(formatter.parse(sdate).getTime());
			
		} catch (ParseException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			throw new TopLevelException(e.getStackTrace());
		}
		
		return ret;
	}

	public void deleteIfExist(String sessionName) {
		File file = new File(sessionName);
		if(file.exists()) file.delete();
	}

	public boolean regex(String source, String exp){
		/*
		 * The metacharacters supported by this API are: ([{\^-$|]})?*+.
		 */

		boolean ret = true;
		if(source!=null && exp!=null){
			Pattern pattern = Pattern.compile(exp);
			Matcher matcher = pattern.matcher(source);
			ret = matcher.find();
		}

		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Source: "+source+" Exp: "+exp+" Return: "+ret);


		return ret;
	}

	public boolean evalLogin(String source, Map<String, String> postedMap){
		boolean ret = false;
		return ret;
	}

}
