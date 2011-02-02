package com.ninj.exceptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataException extends Exception {
	private List errors;

	public DataException() {
		super();
		this.errors = new ArrayList();
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
	
	public boolean isPrombleField(String f) {
		boolean flag = false;
		for (Iterator it = errors.iterator(); it.hasNext();) {
			DataError data_error = (DataError) it.next();
			if (f.equals(data_error.getField())) {
				flag = true;
			}
		}
		return flag;
	}

	public List getProblemFields() {
		List lt = new ArrayList();
		for (Iterator it = errors.iterator(); it.hasNext();) {
			DataError data_error = (DataError) it.next();
			lt.add(data_error.getField());
		}
		return lt;
	}

	public String getProblemFieldsCommaSeperated() {
		String str = "";
		for (Iterator it = errors.iterator(); it.hasNext();) {
			DataError data_error = (DataError) it.next();
			str += data_error.getField();
			if (it.hasNext()) {
				str += ",";
			}
		}
		return str;
	}
	

	public String getErrorMessage() {
		String msg = "";
		for (Iterator it = errors.iterator(); it.hasNext();) {
			msg += "<li>";
			DataError data_error = (DataError) it.next();
			msg += data_error.getMessage();
			msg += "</li>";
		}
		return msg;
	}
}
