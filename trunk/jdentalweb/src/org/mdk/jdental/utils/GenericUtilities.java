package org.mdk.jdental.utils;

import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericUtilities {
	
	public String getCurrentDateTimeFormated(String format){
		Date date = new Date();
		Format formatter = new SimpleDateFormat(format);
		String stime = formatter.format(date);
		return stime;
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
