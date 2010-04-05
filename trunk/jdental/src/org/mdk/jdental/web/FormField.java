package org.mdk.jdental.web;

public class FormField {
	private String type;
	private String errMsg;
	private String value;
	private String validationPattern;
	private String label;
	
	
	
	public FormField(String label, String type,  String value,  String errMsg,
			String validationPattern) {
		super();
		this.type = type;
		this.errMsg = errMsg;
		this.value = value;
		this.validationPattern = validationPattern;
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return errMsg;
	}
	public void setName(String name) {
		this.errMsg = name;
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
	
	
}
