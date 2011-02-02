package com.ninj.ninjsupport;

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
import com.ninj.helpers.DBHelper;
import com.ninj.util.DBUtil;

public abstract class NinjSupportHelper {

	public static Object getDBField(DBInfo info, String fieldName, String type,
			Connection conn) throws Exception {
		Object ret = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("select " + fieldName + " from " + info.getTable());
			sql.append(" where ");

			int i = 0;
			for (DBField f : info.getPKeys()) {
				sql.append(f.getDBName());
				sql.append(" = ?");

				if (i < (info.getPKeys().size() - 1)) {
					sql.append(" and ");
				}

				i++;
			}

			pstmt = conn.prepareStatement(sql.toString());

			i = 1;
			for (DBField f : info.getPKeys()) {
				DBHelper.setPreparedStatement(pstmt, i, f.getValue());
				i++;
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if ("string".equalsIgnoreCase(type)) {
					ret = rs.getString(1);
				} else if ("int".equalsIgnoreCase(type)) {
					ret = rs.getInt(1);
				} else if ("date".equalsIgnoreCase(type)) {
					ret = rs.getDate(1);
				} else if ("long".equalsIgnoreCase(type)) {
					ret = rs.getLong(1);
				} else if ("float".equalsIgnoreCase(type)) {
					ret = rs.getFloat(1);
				} else if ("double".equalsIgnoreCase(type)) {
					ret = rs.getDouble(1);
				} else if ("blob".equalsIgnoreCase(type)) {
					ret = rs.getBlob(1);
				}

			}

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt);
		}

		return ret;
	}

	public static void setDBField(DBInfo info, String field_name, Object value,
			Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("update " + info.getTable());
			sql.append(" set ");
			sql.append(field_name);
			sql.append(" = ?");
			sql.append(" where ");

			int i = 0;
			for (DBField f : info.getPKeys()) {
				sql.append(f.getDBName());
				sql.append(" = ?");
				if (i < (info.getPKeys().size() - 1)) {
					sql.append(" and ");
				}

				i++;
			}

			pstmt = conn.prepareStatement(sql.toString());

			DBHelper.setPreparedStatement(pstmt, 1, value);

			i = 2;
			for (DBField f : info.getPKeys()) {
				DBHelper.setPreparedStatement(pstmt, i, f.getValue());
				i++;
			}
			pstmt.execute();

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt);
		}
	}

	public static void setDBField(DBInfo info, String field_name,
			InputStream in, Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("update " + info.getTable());
			sql.append(" set ");
			sql.append(field_name);
			sql.append(" = ?");
			sql.append(" where ");

			int i = 0;
			for (DBField f : info.getPKeys()) {
				sql.append(f.getDBName());
				sql.append(" = ?");
				if (i < (info.getPKeys().size() - 1)) {
					sql.append(" and ");
				}

				if (!info.getPKeys().isEmpty()) {

				}

				i++;
			}

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setBinaryStream(1, in, in.available());

			i = 1;
			for (DBField f : info.getPKeys()) {
				DBHelper.setPreparedStatement(pstmt, (i + 1), f.getValue());
				i++;
			}
			pstmt.execute();

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt);
		}
	}
	
	
	public static void setDBField(DBInfo info, String field_name,
			ByteArrayInputStream bain, Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("update " + info.getTable());
			sql.append(" set ");
			sql.append(field_name);
			sql.append(" = ?");
			sql.append(" where ");

			int i = 0;
			for (DBField f : info.getPKeys()) {
				sql.append(f.getDBName());
				sql.append(" = ?");
				if (i < (info.getPKeys().size() - 1)) {
					sql.append(" and ");
				}

				if (!info.getPKeys().isEmpty()) {

				}

				i++;
			}

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setBinaryStream(1, bain, bain.available());

			i = 1;
			for (DBField f : info.getPKeys()) {
				DBHelper.setPreparedStatement(pstmt, (i + 1), f.getValue());
				i++;
			}
			pstmt.execute();

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt);
		}
	}
	
	
	public static void setDBPasswordField(DBInfo info, String field_name,
			String value, Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("update " + info.getTable());
			sql.append(" set ");
			sql.append(field_name);
			sql.append(" = password(?)");
			sql.append(" where ");

			int i = 0;
			for (DBField f : info.getPKeys()) {
				sql.append(f.getDBName() + " = ?");
				if (i < (info.getPKeys().size() - 1)) {
					sql.append(" and ");
				}
				i++;
			}

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, (String) value);

			i = 1;
			for (DBField f : info.getPKeys()) {
				DBHelper.setPreparedStatement(pstmt, (i + 1), f.getValue());
				i++;
			}
			pstmt.execute();

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt);
		}

	}
	

}
