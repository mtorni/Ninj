package com.ninj.bind;

import java.util.*;

public class DBInfo {

	private Object obj;

	private String table;

	private List<DBField> field_lt;

	public DBInfo(Object obj, String table) {
		this.table = table;
		this.obj = obj;
	}

	public Object getObject() {
		return this.obj;
	}

	public String getTable() {
		return this.table;
	}

	public List<DBField> getAllFields() {
		return this.field_lt;
	}

	public List<DBField> getFields() {
		List<DBField> lt = new ArrayList<DBField>();
		for (DBField f : this.getAllFields()) {
			if (!f.isPKey() && !f.isFKey()) {
				lt.add(f);
			}
		}
		return lt;
	}

	public List<DBField> getAllKeys() {
		List<DBField> lt = new ArrayList<DBField>();
		for (DBField f : this.getAllFields()) {
			if (f.isPKey() || f.isFKey()) {
				lt.add(f);
			}
		}
		return lt;
	}

	public List<DBField> getPKeys() {
		List<DBField> lt = new ArrayList<DBField>();
		for (DBField f : this.getAllFields()) {
			if (f.isPKey()) {
				lt.add(f);
			}
		}
		return lt;
	}

	public List<DBField> getFKeys() {
		List<DBField> lt = new ArrayList<DBField>();
		for (DBField f : this.getAllFields()) {
			if (f.isFKey()) {
				lt.add(f);
			}
		}
		return lt;
	}

	public List<DBField> getAutoKeys() {
		List<DBField> lt = new ArrayList<DBField>();
		for (DBField f : this.getAllFields()) {
			if (f.isAuto()) {
				lt.add(f);
			}
		}
		return lt;
	}

	public void setFieldList(List<DBField> lt) {
		this.field_lt = lt;
	}
}
