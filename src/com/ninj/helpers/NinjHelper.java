package com.ninj.helpers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import com.ninj.bind.DBField;
import com.ninj.bind.DBInfo;
import com.ninj.util.DBUtil;

public abstract class NinjHelper {

	public static void insertKeys(Object obj, DBInfo info, Connection conn)
			throws Exception {

		PreparedStatement pstmt = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("insert into " + info.getTable() + "(");
			int i = 0;
			for (DBField f : info.getAllKeys()) {
				sql.append(f.getDBName());
				if (i < (info.getAllKeys().size() - 1)) {
					sql.append(",");
				}
				i++;
			}

			sql.append(")" + " values(");
			i = 0;
			for (; i < info.getAllKeys().size(); i++) {
				sql.append("?");
				if (i < (info.getAllKeys().size() - 1)) {
					sql.append(",");
				}
			}

			sql.append(")");

			pstmt = conn.prepareStatement(sql.toString());

			i = 0;
			for (DBField f : info.getAllKeys()) {
				DBHelper.setPreparedStatement(obj, pstmt, (i + 1), f);
				i++;
			}

			pstmt.execute();

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt);
		}
	}

	public static void insert(Object obj, DBInfo info, Connection conn)
			throws Exception {

		for (DBField f : info.getAutoKeys()) {
			long id = DBHelper.getAutoId(obj, info, f, conn);
			ObjectHelper.setAutoObjectField(obj, f, id);
		}

		PreparedStatement pstmt = null;
		try {

			String sql = "insert into " + info.getTable();
			sql += "(";
			int i = 0;

			for (DBField f : info.getAllFields()) {
				sql += f.getDBName();
				if (i < (info.getAllFields().size() - 1)) {
					sql += ",";
				}
				i++;
			}

			sql += ")";
			sql += " values(";
			i = 0;
			for (; i < info.getAllFields().size(); i++) {
				sql += "?";
				if (i < (info.getAllFields().size() - 1)) {
					sql += ",";
				}
			}

			sql += ")";

			pstmt = conn.prepareStatement(sql);

			i = 0;
			for (DBField f : info.getAllFields()) {
				DBHelper.setPreparedStatement(obj, pstmt, (i + 1), f);
				i++;
			}

			pstmt.execute();

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt);
		}

	}

	public static void setBinaryField(DBInfo info, String field_name,
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

	public static void setByteField(DBInfo info, String field_name,
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
				sql.append(f.getDBName() + " = ?");
				if (i < (info.getPKeys().size() - 1)) {
					sql.append(" and ");
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

	public static void setPasswordField(DBInfo info, String field_name,
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

	public static String getPasswordField(Object obj, DBInfo info,
			String field_name, Connection conn) throws Exception {
		String s;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("select " + field_name + " from " + info.getTable()
					+ " where ");

			int i = 0;
			for (Iterator it = info.getPKeys().iterator(); it.hasNext(); i++) {
				DBField f = (DBField) it.next();
				sql.append(f.getDBName() + " = ?");
				if (i < (info.getPKeys().size() - 1)) {
					sql.append(" and ");
				}
			}

			pstmt = conn.prepareStatement(sql.toString());

			i = 0;
			for (Iterator it = info.getPKeys().iterator(); it.hasNext(); i++) {
				DBField f = (DBField) it.next();

				DBHelper.setPreparedStatement(obj, pstmt, (i + 1), f);
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				s = rs.getString(1);

			} else {
				throw new Exception(
						"Binder -> Error in retriving password field");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt, rs);
		}

		return s;

	}

	public static void getObject(Object obj, DBInfo info, Connection conn)
			throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("select ");
			int i = 0;
			for (DBField f : info.getAllFields()) {
				sql.append(f.getDBName());
				if (i < (info.getAllFields().size() - 1)) {
					sql.append(", ");
				}
				i++;
			}
			sql.append(" from " + info.getTable() + " where ");

			i = 0;
			for (DBField f : info.getPKeys()) {
				sql.append(f.getDBName() + " = ?");
				if (i < (info.getPKeys().size() - 1)) {
					sql.append(" and ");
				}
				i++;
			}

			pstmt = conn.prepareStatement(sql.toString());

			i = 0;
			for (DBField f : info.getPKeys()) {
				DBHelper.setPreparedStatement(obj, pstmt, (i + 1), f);
				i++;
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				for (DBField f : info.getAllFields()) {
					ObjectHelper.setObjectField(obj, f, rs);
				}

			} else {
				throw new Exception("Binder -> Sorry can not find object");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt, rs);
		}
	}

	public static void update(Object obj, DBInfo info, Connection conn)
			throws Exception {

		PreparedStatement pstmt = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("update " + info.getTable() + " set ");
			int i = 0;

			for (DBField f : info.getAllFields()) {
				sql.append(f.getDBName() + " = ?");

				if (i < (info.getAllFields().size() - 1)) {
					sql.append(", ");
				}
				i++;
			}

			sql.append(" where ");
			i = 0;

			for (DBField f : info.getPKeys()) {
				sql.append(f.getDBName() + " = ?");
				if (i < (info.getPKeys().size() - 1)) {
					sql.append(" and ");
				}
				i++;
			}

			pstmt = conn.prepareStatement(sql.toString());

			i = 0;
			for (DBField f : info.getAllFields()) {
				DBHelper.setPreparedStatement(obj, pstmt, (i + 1), f);
				i++;
			}

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

	public static void delete(DBInfo info, Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("delete from " + info.getTable() + " where ");
			int i = 0;
			for (DBField f : info.getPKeys()) {
				sql.append(f.getDBName() + " = ?");
				if (i < (info.getPKeys().size() - 1)) {
					sql.append(" and ");
				}
				i++;
			}

			pstmt = conn.prepareStatement(sql.toString());

			i = 0;
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

	public static boolean exists(Object obj, DBInfo info, Connection conn)
			throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("select ");
			int i = 0;
			for (DBField f : info.getAllFields()) {
				sql.append(f.getDBName());
				break;
			}
			sql.append(" from " + info.getTable() + " where ");

			i = 0;
			for (DBField f : info.getPKeys()) {
				sql.append(f.getDBName() + " = ?");
				if (i < (info.getPKeys().size() - 1)) {
					sql.append(" and ");
				}
				i++;
			}

			pstmt = conn.prepareStatement(sql.toString());

			i = 0;
			for (DBField f : info.getPKeys()) {
				DBHelper.setPreparedStatement(obj, pstmt, (i + 1), f);
				i++;
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				flag = true;

			}

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt, rs);
		}
		return flag;
	}

}
