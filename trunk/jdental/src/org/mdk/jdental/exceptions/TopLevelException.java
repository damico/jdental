package org.mdk.jdental.exceptions;

import org.mdk.jdental.utils.LoggerManager;


public class TopLevelException extends Exception {

	private static final long serialVersionUID = -5433172273983353912L;
	
	private StackTraceElement[] stackTraceElements;
	
	private String message;
	
	public TopLevelException(String message){
		super(message);
		this.message = message;
	}
	
	public TopLevelException(String message, String className){
		super(message);
		this.message = message;
		LoggerManager.getInstance().logAtExceptionTime(className, message);

	}
	
	
	
	public TopLevelException(){
		super();
	}
	
	public TopLevelException(StackTraceElement[] stackTraceElements) {
		this.stackTraceElements = stackTraceElements;
	}
	
	public TopLevelException(StackTraceElement[] stackTraceElements, String rootMessage) {
		super(rootMessage);
		this.stackTraceElements = stackTraceElements;
		
	}

	public String getStackTraceElements(){
		StringBuffer sb = new StringBuffer();
		
		if(stackTraceElements == null){
			sb.append(message);
		}else{
			for(int i = 0; i < stackTraceElements.length; i++){
				sb.append(stackTraceElements[i].getFileName()+"("+stackTraceElements[i].getLineNumber()+")\n");
			}
		}
		
		
		return sb.toString();
	}
	

}
