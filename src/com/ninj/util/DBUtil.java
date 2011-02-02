package com.ninj.util;

import java.io.*;
import java.net.InetAddress;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public abstract class DBUtil {

	public static void handleSQLException(Connection conn) {

		try {
			conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.setReadOnly(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closePreparedStatement(PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {

		}

	}

	public static void closePreparedStatement(PreparedStatement pstmt,
			ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {

		}

		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {

		}

	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {

		}

	}

	public static void closeConnection(Connection conn, PreparedStatement pstmt) {

		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {

		}

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {

		}

	}

	public static void closeConnection(Connection conn, Statement stmt) {

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {

		}

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {

		}

	}

	public static void closeConnection(Connection conn, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {

		}

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {

		}

	}

	public static void closeConnection(Connection conn,
			PreparedStatement pstmt, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {

		}

		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {

		}

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {

		}

	}

	public static void closeConnection(Connection conn, Statement stmt,
			ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {

		}

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {

		}

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {

		}
	}

	public static String getGUID() throws Exception {
		String guid = null;

		SecureRandom seeder = new SecureRandom();
		guid = Integer.toHexString(InetAddress.getLocalHost().getHostAddress().hashCode());
		guid += Integer.toHexString((int) System.currentTimeMillis());
		guid += Integer.toHexString(seeder.nextInt());

		return guid;

	}

	public static String getShortGUID() throws Exception {
		String guid = null;
		SecureRandom seeder = new SecureRandom();
		guid = Integer.toHexString((int) System.currentTimeMillis());
		guid += Integer.toHexString(seeder.nextInt());
		return guid;

	}

}
