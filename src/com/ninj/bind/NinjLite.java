package com.ninj.bind;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ninj.helpers.DBHelper;
import com.ninj.helpers.NinjHelper;
import com.ninj.helpers.ObjectHelper;
import com.ninj.util.DBUtil;

public abstract class NinjLite {

	public static void save(Object obj, Connection conn) throws Exception {

		try {

			if (conn == null) {
				throw new Exception(
						"Binder Error >> Connection for method save is null");
			}

			conn.setAutoCommit(false);

			DBInfo info = ObjectHelper.getDBInfo(obj);
			NinjHelper.update(obj, info, conn);

			conn.commit();
			conn.setAutoCommit(true);

		} catch (Exception e) {

			DBUtil.handleSQLException(conn);
			throw e;
		} finally {

			DBUtil.closeConnection(conn);
		}

	}

	public static void delete(Object obj, Connection conn) throws Exception {
		try {

			if (conn == null) {
				throw new Exception(
						"Binder Error >> Connection for method delete is null");
			}
			DBInfo info = ObjectHelper.getDBInfo(obj);
			NinjHelper.delete(info, conn);
		} catch (Exception e) {

			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}

	}

	public static Object newObject(Object obj, Connection conn)
			throws Exception {

		try {
			DBInfo info = ObjectHelper.getDBInfo(obj);
			NinjHelper.getObject(obj, info, conn);

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}

		return obj;
	}

	public static Object newObject(Object obj, ResultSet rs) throws Exception {

		try {

			DBInfo info = ObjectHelper.getDBInfo(obj);

			for (DBField df : info.getAllFields()) {
				ObjectHelper.setObjectField(obj, df, rs);
			}

		} catch (Exception e) {
			throw e;
		}

		return obj;
	}

	public static Object insert(Object obj, Connection conn) throws Exception {
		try {

			if (conn == null) {
				throw new Exception(
						"Ninj Error >> Connection for method newInstance is null");
			}

			conn.setAutoCommit(false);

			DBInfo info = ObjectHelper.getDBInfo(obj);
			NinjHelper.insert(obj, info, conn);
		

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {

			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}

		return obj;

	}
	
	public static Object insertRet(Object obj, Connection conn) throws Exception {
		try {

			if (conn == null) {
				throw new Exception(
						"Ninj Error >> Connection for method newInstance is null");
			}

			conn.setAutoCommit(false);

			DBInfo info = ObjectHelper.getDBInfo(obj);
			NinjHelper.insert(obj, info, conn);
			NinjHelper.getObject(obj, info, conn);

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {

			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}

		return obj;

	}

	public static void setBinaryField(Object obj, String field_name,
			InputStream in, Connection conn) throws Exception {
		try {
			if (conn == null) {
				throw new Exception(
						"Ninj Error >> Connection for method setBinaryField is null");
			}
			conn.setAutoCommit(false);
			DBInfo info = ObjectHelper.getDBInfo(obj);
			NinjHelper.setBinaryField(info, field_name, in, conn);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}

	}

	public static void setByteField(Object obj, String field_name,
			ByteArrayInputStream bain, Connection conn) throws Exception {
		try {
			if (conn == null) {
				throw new Exception(
						"Ninj Error >> Connection for method setByteField is null");
			}
			conn.setAutoCommit(false);
			DBInfo info = ObjectHelper.getDBInfo(obj);
			NinjHelper.setByteField(info, field_name, bain, conn);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}

	}

	public static String setPasswordField(Object obj, String field_name,
			String value, Connection conn) throws Exception {
		String password;
		try {
			if (conn == null) {
				throw new Exception(
						"Ninj Error >> Connection for method setByteField is null");
			}
			conn.setAutoCommit(false);
			DBInfo info = ObjectHelper.getDBInfo(obj);

			NinjHelper.setPasswordField(info, field_name, value, conn);

			password = NinjHelper.getPasswordField(obj, info, field_name, conn);

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
		return password;

	}

	public static Object persist(Object obj, Connection conn) throws Exception {
		
		
		try {

			if (conn == null) {
				throw new Exception(
						"Ninj Error >> Connection for method newInstance is null");
			}

			conn.setAutoCommit(false);

			DBInfo info = ObjectHelper.getDBInfo(obj);
			try {
				NinjHelper.insert(obj, info, conn);
			} catch (Exception e) {
				NinjHelper.update(obj, info, conn);
			}
			NinjHelper.getObject(obj, info, conn);

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {

			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		
		

		return obj;
	}

	public static boolean exists(Object obj, Connection conn) throws Exception {
		try {

			if (conn == null) {
				throw new Exception(
						"Binder Error >> Connection for method exists is null");
			}
	
			
			DBInfo info = ObjectHelper.getDBInfo(obj);

			return NinjHelper.exists(obj, info, conn);
			
		} catch (Exception e) {

			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}

	}
}
