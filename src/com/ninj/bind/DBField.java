package com.ninj.bind;

public class DBField {

	private String db_name;

	private String object_name;

	private Object value;

	private boolean pkey = false;
	
	private boolean fkey = false;
	
	private boolean auto = false;
	


	public DBField(String db_name, String object_name, Object value) {
		this.db_name = db_name;
		this.object_name = object_name;
		this.value = value;		
	}
	
	public void setAuto(boolean auto){
		this.auto = auto;
	}
	
	public void setPKey(boolean pkey){
		this.pkey = pkey;
	}
	
	public void setFKey(boolean fkey){
		this.fkey = fkey;
	}
	
	public void setValue(Object obj){
		this.value = obj;
	}

	public String getDBName() {
		return this.db_name;
	}

	public String getObjectName() {
		return this.object_name;
	}

	public Object getValue() {
		return this.value;
	}

	public boolean isPKey() {
		return this.pkey;
	}
	
	public boolean isFKey(){
		return this.fkey;
	}
	
	public boolean isAuto(){
		return this.auto;
	}
	
	public boolean isReadOnly(){
		return this.isReadOnly();
	}
}
