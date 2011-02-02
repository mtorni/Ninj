package com.ninj.exceptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FormException extends Exception {

	private List errors;
	
	private String type;

	public FormException() {
		super();
		this.errors = new ArrayList();
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}

	public void addError(String field, String msg) {
		this.errors.add(new DataError(field, msg));
	}

	public boolean hasError() {
		if (errors.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public List getErrors() {
		return this.errors;
	}

	
	public String getErrorMessage() {
		String msg = "<ul>";
		for (Iterator it = errors.iterator(); it.hasNext();) {
			msg += "<li>";
			DataError data_error = (DataError) it.next();
			msg += data_error.getMessage();
			msg += "</li>";
		}
		msg += "</ul>";
		return msg;
	}
	
	public String getResultAsXML() {
		StringBuffer msg = new StringBuffer();
		msg.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		msg.append("<RESULT>");
		if (getErrors().isEmpty()) {
			msg.append("<HAS_ERRORS>false</HAS_ERRORS>");
		} else {
			msg.append("<HAS_ERRORS>true</HAS_ERRORS>");
			msg.append("<ERROR_TYPE>form</ERROR_TYPE>");
			for (Iterator it = errors.iterator(); it.hasNext();) {
				DataError dataError = (DataError) it.next();
				msg.append("<ERROR>");
				msg.append("<FIELD>" + dataError.getField() + "</FIELD>");
				msg.append("<MESSAGE>" + dataError.getMessage() + "</MESSAGE>");
				msg.append("</ERROR>");
			}
		}
		msg.append("</RESULT>");
		return msg.toString();
	}

	public String getResultAsJSON() {

		StringBuffer msg = new StringBuffer();
		msg.append("{\"result\" : {");
		if (getErrors().isEmpty()) {
			msg.append("\"hasErros\" : \"false\"");
		} else {
			msg.append("\"hasErrors\" : \"true\",");
			msg.append("\"errorType\" : \"form\",");
			msg.append("\"error\" : [");
			for (Iterator it = errors.iterator(); it.hasNext();) {
				DataError dataError = (DataError) it.next();
				msg.append("{");
				msg.append("\"field\" : \"" + dataError.getField() + "\",");
				msg.append("\"message\" : \"" + dataError.getMessage() + "\"");
				msg.append("}");
				if (it.hasNext()) {
					msg.append(",");
				}
			}
			msg.append("]");
			
		}

		msg.append("}}");
		return msg.toString();
	}

	public static String getSystemFaultAsXML(String msg) {
		StringBuffer mesg = new StringBuffer();
		mesg.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		mesg.append("<RESULT>");
		mesg.append("<HAS_ERRORS>true</HAS_ERRORS>");
		mesg.append("<ERROR_TYPE>system</ERROR_TYPE>");
		mesg.append("<MESSAGE>" + msg + "</MESSAGE>");
		mesg.append("</RESULT>");
		return mesg.toString();
	}
	
	public static String getSystemFaultAsJSON(String msg) {
		StringBuffer mesg = new StringBuffer();
		mesg.append("{\"result\": {");
		mesg.append("\"hasErrors\": \"true\",");
		mesg.append("\"errorType\": \"system\",");
		mesg.append("\"message\": \"" + msg + "\"");
		mesg.append("}}");
		return mesg.toString();
	}
	
	
	public static String getSuccessXML(String msg) {
		StringBuffer mesg = new StringBuffer();
		mesg.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		mesg.append("<RESULT>");
		mesg.append("<HAS_ERRORS>false</HAS_ERRORS>");
		mesg.append("<MESSAGE>" + msg + "</MESSAGE>");
		mesg.append("</RESULT>");
		return mesg.toString();
	}
	
	public static String getSuccessJSON(String msg) {
		StringBuffer mesg = new StringBuffer();
		mesg.append("{\"result\": {");
		mesg.append("\"hasErrors\": \"false\",");
		mesg.append("\"message\": \"" + msg + "\"");
		mesg.append("}}");
		return mesg.toString();
	}
	
	

}
