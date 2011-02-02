package com.ninj.exceptions;

public class DataError {

	private String field;
	private String msg;
	
	public DataError(String field, String msg){
		this.field = field;
		this.msg = msg;		
	}

	public String getField(){
		return this.field;
	}
	public String getMessage(){
		return this.msg;
	}
}
