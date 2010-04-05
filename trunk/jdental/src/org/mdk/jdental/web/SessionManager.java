package org.mdk.jdental.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.mdk.jdental.utils.Constants;
import org.mdk.jdental.utils.GenericUtilities;
import org.mdk.jdental.utils.ManageProperties;

public class SessionManager {
	
	public void createSession(String sessionUser){
		GenericUtilities utility = new GenericUtilities();
		//create session name
		String sessionName = getSessionName(sessionUser);
		//delete if exist
		utility.deleteIfExist(sessionName);
		//create
		Properties prop = ManageProperties.getInstance().setPropFile(sessionName, utility.getCurrentDateTimeFormated("dd/MMM/yyyy HH:mm:ss"));

		try {
			prop.load(new FileInputStream(sessionName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String getLastLogin(String sessionUser) {
		return ManageProperties.getInstance().read(getSessionName(sessionUser),"date");
	}
	
	private String getSessionName(String sessionUser){
		return "/tmp/session-id-"+Constants.APP_NAME+"-"+sessionUser;
	}

}
