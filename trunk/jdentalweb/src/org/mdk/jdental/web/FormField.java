package org.mdk.jdental.web;

public class FormField {
	private String inputType;
	private String dataType;
	private String errMsg;
	private String value;
	private String validationPattern;
	private String label;
	
	
	
	public FormField(String label, String inputType,  String value,  String errMsg,
			String validationPattern) {
		super();
		this.inputType = inputType;
		this.errMsg = errMsg;
		this.value = value;
		this.validationPattern = validationPattern;
		this.label = label;
	}
	
	public FormField(String label, String inputType,  String value,  String errMsg,
			String validationPattern, String dataType) {
		super();
		this.inputType = inputType;
		this.errMsg = errMsg;
		this.value = value;
		this.validationPattern = validationPattern;
		this.label = label;
		this.dataType = dataType;
	}
	
	
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValidationPattern() {
		return validationPattern;
	}
	public void setValidationPattern(String validationPattern) {
		this.validationPattern = validationPattern;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}


	public String getDataType() {
		return dataType;
	}


	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	
	
}
