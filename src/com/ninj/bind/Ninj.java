package com.ninj.bind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.ninj.util.DBUtil;
import com.ninj.helpers.*;

public class Ninj {

	private Object obj;
	private DBInfo info;
	private Connection conn;

	public Ninj() {

	}

	public Ninj(Connection conn) {
		this.conn = conn;
	}

	public Object newInstance(Object obj) throws Exception {

		if (this.conn == null) {
			throw new Exception(
					"Binder Error >> Connection for method newInstance is null");
		}

		try {

			this.obj = obj;
			this.info = ObjectHelper.getDBInfo(obj);

			NinjHelper.insert(obj, info, conn);
			NinjHelper.getObject(obj, info, conn);
		} catch (Exception e) {
			throw e;
		}

		return newObject(obj);
	}

	public static String setPasswordField(Object obj, String field_name,
			String value, Connection conn) throws Exception {

		String password;

		DBInfo info = ObjectHelper.getDBInfo(obj);
		NinjHelper.setPasswordField(info, field_name, value, conn);
		password = NinjHelper.getPasswordField(obj, info, field_name, conn);

		return password;
	}

	public Object newObject(Object obj) throws Exception {
		try {

			this.obj = obj;
			this.info = ObjectHelper.getDBInfo(obj);

			NinjHelper.getObject(obj, info, conn);
		} catch (Exception e) {
			throw e;
		}

		return obj;
	}

	public Object newObject(Object obj, ResultSet rs) throws Exception {
		try {

			this.obj = obj;
			this.info = ObjectHelper.getDBInfo(obj);

			for (DBField df : info.getAllFields()) {
				ObjectHelper.setObjectField(obj, df, rs);
			}

		} catch (Exception e) {
			throw e;
		}

		return obj;
	}

	public void save() throws Exception {
		if (this.conn == null) {
			throw new Exception(
					"Binder Error >> Connection for method save is null");
		}

		try {

			NinjHelper.update(obj, info, conn);

			this.info = ObjectHelper.getDBInfo(obj);

		} catch (Exception e) {
			throw e;
		}

	}

	public void delete() throws Exception {
		if (this.conn == null) {
			throw new Exception(
					"Binder Error >> Connection for method delete is null");
		}

		NinjHelper.delete(info, conn);

	}

	public static void save(Object obj, Connection conn) throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);

		NinjHelper.update(obj, info, conn);

	}

	public static void delete(Object obj, Connection conn) throws Exception {

		DBInfo info = ObjectHelper.getDBInfo(obj);

		NinjHelper.delete(info, conn);
	}

	public static Object newObject(Object obj, Connection conn)
			throws Exception {

		DBInfo info = ObjectHelper.getDBInfo(obj);

		NinjHelper.getObject(obj, info, conn);
		return obj;
	}

	public static Object insert(Object obj, Connection conn) throws Exception {
		try {

			if (conn == null) {
				throw new Exception(
						"Ninj Error >> Connection for method newInstance is null");
			}

			DBInfo info = ObjectHelper.getDBInfo(obj);

			NinjHelper.insert(obj, info, conn);

		} catch (Exception e) {

			DBUtil.handleSQLException(conn);
			throw e;
		}

		return obj;

	}

	public static Object insertRet(Object obj, Connection conn)
			throws Exception {
		try {

			if (conn == null) {
				throw new Exception(
						"Ninj Error >> Connection for method newInstance is null");
			}

			DBInfo info = ObjectHelper.getDBInfo(obj);
			NinjHelper.insert(obj, info, conn);
			NinjHelper.getObject(obj, info, conn);

		} catch (Exception e) {

			DBUtil.handleSQLException(conn);
			throw e;
		}

		return obj;

	}

	public static Object persist(Object obj, Connection conn) throws Exception {
		try {

			if (conn == null) {
				throw new Exception(
						"Ninj Error >> Connection for method newInstance is null");
			}

			DBInfo info = ObjectHelper.getDBInfo(obj);

			try {
				NinjHelper.update(obj, info, conn);
			} catch (Exception e) {
				NinjHelper.insert(obj, info, conn);
			}

			NinjHelper.getObject(obj, info, conn);

		} catch (Exception e) {

			DBUtil.handleSQLException(conn);
			throw e;
		}

		return obj;
	}


	public static boolean exists(Object obj, Connection conn) throws Exception {

		DBInfo info = ObjectHelper.getDBInfo(obj);

		return NinjHelper.exists(obj, info, conn);

	}
}
