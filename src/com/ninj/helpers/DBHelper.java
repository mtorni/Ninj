package com.ninj.helpers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import com.ninj.bind.DBField;
import com.ninj.bind.DBInfo;
import com.ninj.util.DBUtil;

public abstract class DBHelper {





	public static void setPreparedStatement(PreparedStatement pstmt, int pos,
			Object f) throws Exception {

		if (f == null) {
			pstmt.setNull(pos, java.sql.Types.NULL);
		} else if (f instanceof java.lang.String) {
			pstmt.setString(pos, (String) f);
		} else if (f instanceof java.util.Date) {
			pstmt.setTimestamp(pos, new java.sql.Timestamp(((java.util.Date) f)
					.getTime()));
		} else if (f instanceof java.lang.Integer) {
			pstmt.setInt(pos, ((Integer) f).intValue());
		} else if (f instanceof java.lang.Long) {
			pstmt.setLong(pos, ((Long) f).longValue());
		} else if (f instanceof java.lang.Float) {
			pstmt.setFloat(pos, ((Float) f).floatValue());
		} else if (f instanceof java.lang.Double) {
			pstmt.setDouble(pos, ((Double) f).doubleValue());
		}

	}

	public static void setPreparedStatement(Object obj,
			PreparedStatement pstmt, int pos, DBField f) throws Exception {

		Object s = ObjectHelper.getObjectFieldValue(obj, f.getObjectName());

		setPreparedStatement(pstmt, pos, s);

	}



	public static long getAutoId(Object obj, DBInfo info, DBField df,
			Connection conn) throws Exception {
		long id = 0;
		try {

			if (conn == null) {
				throw new Exception(
						"Ninj Error >> Connection for method delete is null");
			}

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement("select max(" + df.getDBName()
						+ ") from " + info.getTable());

				rs = pstmt.executeQuery();

				if (rs.next()) {
					id = rs.getLong(1);

				}

			} finally {
				DBUtil.closePreparedStatement(pstmt, rs);

			}

		} catch (Exception e) {

			DBUtil.handleSQLException(conn);
			throw e;
		} finally {

		}
		return id + 1;
	}

	public static Connection getConnection(String dataSource) throws Exception {

		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup(dataSource);
		ctx.close();
		return ds.getConnection();
	}


	public static Connection getConnection(String url, String username, String password) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}


	public static Connection getConnection(String driverClassName, String url, String username, String password) throws Exception {
		Class.forName(driverClassName);
		Connection conn = null;
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

}
