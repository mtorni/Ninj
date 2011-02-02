package com.ninj.ninjsupport;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ninj.bind.DBField;
import com.ninj.bind.DBInfo;
import com.ninj.bind.Ninj;
import com.ninj.bind.NinjLite;
import com.ninj.helpers.DBHelper;
import com.ninj.helpers.NinjHelper;
import com.ninj.helpers.ObjectHelper;
import com.ninj.util.DBUtil;

public class CrudSupport {

	private DBConfig dbConfig;

	public CrudSupport(DBConfig dbConfig) throws Exception {
		this.dbConfig = dbConfig;
	}

	public String getDBString(Object obj, String dbField) throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection();
			return this.getDBString(obj, dbField, conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public String getDBString(Object obj, String dbField, Connection conn)
			throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(this);
		return (String) NinjSupportHelper.getDBField(info, dbField, "string",
				conn);
	}

	public int getDBInt(Object obj, String dbField) throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection();
			return this.getDBInt(obj, dbField, conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public int getDBInt(Object obj, String dbField, Connection conn)
			throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		return (Integer) NinjSupportHelper.getDBField(info, dbField, "int",
				conn);
	}

	public Date getDBDate(Object obj, String dbField) throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection();
			return this.getDBDate(obj, dbField, conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public Date getDBDate(Object obj, String dbField, Connection conn)
			throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		return (Date) NinjSupportHelper.getDBField(info, dbField, "date", conn);
	}

	public float getDBFloat(Object obj, String dbField) throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection();
			return this.getDBFloat(obj, dbField, conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public float getDBFloat(Object obj, String dbField, Connection conn)
			throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		return (Float) NinjSupportHelper.getDBField(info, dbField, "float",
				conn);
	}

	public double getDBDouble(Object obj, String dbField) throws Exception {
		Connection conn = null;
		try {
			DBInfo info = ObjectHelper.getDBInfo(obj);
			conn = this.getConnection();
			return (Double) NinjSupportHelper.getDBField(info, dbField,
					"double", conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public double getDBDouble(Object obj, String dbField, Connection conn)
			throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		return (Double) NinjSupportHelper.getDBField(info, dbField, "double",
				conn);
	}

	public Blob getDBBlob(Object obj, String dbField) throws Exception {
		Connection conn = null;
		try {
			DBInfo info = ObjectHelper.getDBInfo(obj);
			conn = this.getConnection();
			return (Blob) NinjSupportHelper.getDBField(info, dbField, "blob",
					conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public Blob getDBBlob(Object obj, String dbField, Connection conn)
			throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		return (Blob) NinjSupportHelper.getDBField(info, dbField, "blob", conn);
	}

	public void setDBString(Object obj, String dbField, String value)
			throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection();
			this.setDBString(obj, dbField, value, conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public void setDBString(Object obj, String dbField, String value,
			Connection conn) throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		NinjSupportHelper.setDBField(info, dbField, value, conn);
	}

	public void setDBInt(Object obj, String dbField, int value)
			throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection();
			this.setDBInt(obj, dbField, value, conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public void setDBInt(Object obj, String dbField, int value, Connection conn)
			throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		NinjSupportHelper.setDBField(info, dbField, value, conn);
	}

	public void setDBDate(Object obj, String dbField, Date value)
			throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection();
			this.setDBDate(obj, dbField, value, conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public void setDBDate(Object obj, String dbField, Date value,
			Connection conn) throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		NinjSupportHelper.setDBField(info, dbField, value, conn);
	}

	public void setDBFloat(Object obj, String dbField, float value)
			throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection();
			this.setDBFloat(obj, dbField, value, conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public void setDBFloat(Object obj, String dbField, float value,
			Connection conn) throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		NinjSupportHelper.setDBField(info, dbField, value, conn);
	}

	public void setDBDouble(Object obj, String dbField, double value)
			throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection();
			this.setDBDouble(obj, dbField, value, conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public void setDBDouble(Object obj, String dbField, double value,
			Connection conn) throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		NinjSupportHelper.setDBField(info, dbField, value, conn);
	}

	public void setDBBinaryField(Object obj, String dbField, InputStream in)
			throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection();
			this.setDBBinaryField(obj, dbField, in, conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public void setDBBinaryField(Object obj, String dbField, InputStream in,
			Connection conn) throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		NinjSupportHelper.setDBField(info, dbField, in, conn);
	}

	public void setDBByteField(Object obj, String dbField,
			ByteArrayInputStream bain) throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection();
			this.setDBByteField(obj, dbField, bain, conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public void setDBByteField(Object obj, String dbField,
			ByteArrayInputStream bain, Connection conn) throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		NinjSupportHelper.setDBField(info, dbField, bain, conn);
	}

	public void setDBPassword(Object obj, String dbField, String value)
			throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection();
			this.setDBPassword(obj, dbField, value, conn);
		} catch (Exception e) {
			DBUtil.handleSQLException(conn);
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public void setDBPassword(Object obj, String dbField, String value,
			Connection conn) throws Exception {
		DBInfo info = ObjectHelper.getDBInfo(obj);
		NinjSupportHelper.setDBPasswordField(info, dbField, value, conn);
	}

	// make crud names

	public Object create(Object obj) throws Exception {
		NinjLite.insert(obj, this.getConnection());
		return obj;
	}

	public Object create(Object obj, Connection conn) throws Exception {
		Ninj.insert(obj, conn);
		return obj;
	}

	public Object createRet(Object obj) throws Exception {
		NinjLite.insertRet(obj, this.getConnection());
		return obj;
	}

	public Object createRet(Object obj, Connection conn) throws Exception {
		Ninj.insertRet(obj, conn);
		return obj;
	}

	public Object retrieve(Object obj) throws Exception {
		return (Object) NinjLite.newObject(obj, this.getConnection());
	}

	public Object retrieve(Object obj, Connection conn) throws Exception {
		return (Object) Ninj.newObject(obj, conn);
	}

	public Object retrieve(Object obj, ResultSet rs) throws Exception {
		return (Object) NinjLite.newObject(obj, rs);
	}

	public void update(Object obj) throws Exception {
		NinjLite.save(obj, this.getConnection());
	}

	public void update(Object obj, Connection conn) throws Exception {
		Ninj.save(obj, conn);
	}

	public void delete(Object obj) throws Exception {
		NinjLite.delete(obj, this.getConnection());
	}

	public void delete(Object obj, Connection conn) throws Exception {
		Ninj.delete(obj, conn);
	}

	public boolean exists(Object obj) throws Exception {
		return NinjLite.exists(obj, this.getConnection());
	}

	public boolean exists(Object obj, Connection conn) throws Exception {
		return Ninj.exists(obj, conn);
	}
	// end make crud name

	public void execute(String sql, Object[] params) throws Exception {

		Connection conn = null;

		try {

			conn = this.getConnection();

			execute(sql, params, conn);

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}

	}

	public void execute(String sql, Object[] params, Connection conn)
			throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = conn.prepareStatement(sql);

			if (params != null) {
				for (int x = 0; x < params.length; x++) {
					Object param = params[x];

					if (param == null) {
						pstmt.setNull(x + 1, java.sql.Types.NULL);
					} else if (param instanceof java.lang.String) {
						pstmt.setString(x + 1, (String) param);
					} else if (param instanceof java.util.Date) {
						pstmt.setTimestamp(x + 1, new java.sql.Timestamp(
								((java.util.Date) param).getTime()));
					} else if (param instanceof java.lang.Integer) {
						pstmt.setInt(x + 1, ((Integer) param).intValue());
					} else if (param instanceof java.lang.Long) {
						pstmt.setLong(x + 1, ((Long) param).longValue());
					} else if (param instanceof java.lang.Float) {
						pstmt.setFloat(x + 1, ((Float) param).floatValue());
					} else if (param instanceof java.lang.Double) {
						pstmt.setDouble(x + 1, ((Double) param).doubleValue());
					}
				}
			}

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt, rs);
		}

	}

	public Object get(Object obj, String sql, Object[] params) throws Exception {

		Object o = null;

		Connection conn = null;

		try {

			conn = this.getConnection();
			conn.setReadOnly(true);
			o = get(obj, sql, params, conn);
			conn.setReadOnly(false);
		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}

		return o;

	}

	public Object get(Object obj, String sql, Object[] params, Connection conn)
			throws Exception {
		Object o = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = conn.prepareStatement(sql);

			if (params != null) {
				for (int x = 0; x < params.length; x++) {
					Object param = params[x];

					if (param == null) {
						pstmt.setNull(x + 1, java.sql.Types.NULL);
					} else if (param instanceof java.lang.String) {
						pstmt.setString(x + 1, (String) param);
					} else if (param instanceof java.util.Date) {
						pstmt.setTimestamp(x + 1, new java.sql.Timestamp(
								((java.util.Date) param).getTime()));
					} else if (param instanceof java.lang.Integer) {
						pstmt.setInt(x + 1, ((Integer) param).intValue());
					} else if (param instanceof java.lang.Long) {
						pstmt.setLong(x + 1, ((Long) param).longValue());
					} else if (param instanceof java.lang.Float) {
						pstmt.setFloat(x + 1, ((Float) param).floatValue());
					} else if (param instanceof java.lang.Double) {
						pstmt.setDouble(x + 1, ((Double) param).doubleValue());
					}
				}
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				o = obj.getClass().newInstance();
				o = this.retrieve(o, rs);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt, rs);
		}

		return o;

	}

	public List list(Object obj, String sql, Object[] params) throws Exception {

		List lt = new ArrayList();

		Connection conn = null;

		try {

			conn = this.getConnection();
			conn.setReadOnly(true);
			lt = list(obj, sql, params, conn);
			conn.setReadOnly(false);
		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closeConnection(conn);
		}

		return lt;

	}

	public List list(Object obj, String sql, Object[] params, Connection conn)
			throws Exception {
		List lt = new ArrayList();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = conn.prepareStatement(sql);

			if (params != null) {
				for (int x = 0; x < params.length; x++) {
					Object param = params[x];

					if (param == null) {
						pstmt.setNull(x + 1, java.sql.Types.NULL);
					} else if (param instanceof java.lang.String) {
						pstmt.setString(x + 1, (String) param);
					} else if (param instanceof java.util.Date) {
						pstmt.setTimestamp(x + 1, new java.sql.Timestamp(
								((java.util.Date) param).getTime()));
					} else if (param instanceof java.lang.Integer) {
						pstmt.setInt(x + 1, ((Integer) param).intValue());
					} else if (param instanceof java.lang.Long) {
						pstmt.setLong(x + 1, ((Long) param).longValue());
					} else if (param instanceof java.lang.Float) {
						pstmt.setFloat(x + 1, ((Float) param).floatValue());
					} else if (param instanceof java.lang.Double) {
						pstmt.setDouble(x + 1, ((Double) param).doubleValue());
					}
				}
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Object o = obj.getClass().newInstance();
				lt.add(this.retrieve(o, rs));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.closePreparedStatement(pstmt, rs);
		}

		return lt;

	}

	// ////

	public Connection getConnection() throws Exception {
		return this.dbConfig.getConnection();
	}

}
