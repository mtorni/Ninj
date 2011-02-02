package com.ninj.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Date;
import com.ninj.util.DateUtil;

public class RunTimeUtil {

	public static String runWindosXPCmd(String cmd) throws Exception {
		StringBuffer output = new StringBuffer();
		try {
			Runtime runtime = Runtime.getRuntime();

			String cmds[] = { "cmd", "/c", cmd };
			Process proc = runtime.exec(cmds);
			InputStream inputstream = proc.getInputStream();
			InputStream errorstream = proc.getErrorStream();

			InputStreamReader inputstreamreader = new InputStreamReader(
					inputstream);
			BufferedReader bufferedreader = new BufferedReader(
					inputstreamreader);

			InputStreamReader errorstreamreader = new InputStreamReader(
					errorstream);
			BufferedReader errorreader = new BufferedReader(errorstreamreader);

			String line;
			while ((line = bufferedreader.readLine()) != null) {
				if (!"".equals(line)) {
					output.append("cmd output: " + line + "\n");
				}
			}

			line = null;
			while ((line = errorreader.readLine()) != null) {
				if (!"".equals(line)) {
					output.append("cmd error: " + line + "\n");
				}
			}

		} catch (Exception e) {
			throw e;
		}

		return output.toString();

	}

	public static String runUnixCmd(String cmd) throws Exception {
		StringBuffer output = new StringBuffer();
		try {
			Runtime runtime = Runtime.getRuntime();

			// String cmds[] = { "bash", "-c", cmd };
			// String cmds[] = { cmd };
			String cmds[] = { "sh", "-c", cmd };

			Process proc = runtime.exec(cmds);
			InputStream inputstream = proc.getInputStream();
			InputStream errorstream = proc.getErrorStream();

			InputStreamReader inputstreamreader = new InputStreamReader(
					inputstream);
			BufferedReader bufferedreader = new BufferedReader(
					inputstreamreader);

			InputStreamReader errorstreamreader = new InputStreamReader(
					errorstream);
			BufferedReader errorreader = new BufferedReader(errorstreamreader);

			String line;
			while ((line = bufferedreader.readLine()) != null) {
				if (!"".equals(line)) {
					output.append("cmd output: " + line + "\n");
				}
			}

			line = null;
			while ((line = errorreader.readLine()) != null) {
				if (!"".equals(line)) {
					output.append("cmd error: " + line + "\n");
				}
			}

		} catch (Exception e) {
			throw e;
		}

		return output.toString();

	}

	// execute java method
	public void executeMethod(String classStr, String methodStr)
			throws Exception {
		// super("com.ninj.mapsupport.Init","getConnection");

		Class toRun = Class.forName(classStr);

		Method method = toRun.getMethod(methodStr, null);

		method.invoke(null, null);

	}

	private Connection getConnection(String classStr, String methodStr)
			throws Exception {
		// super("com.ninj.mapsupport.Init","getConnection");

		Class toRun = Class.forName(classStr);

		Method method = toRun.getMethod(methodStr, null);

		Connection conn = (Connection) method.invoke(null, null);

		return conn;

	}

	public void executeConstructor() {
		// Constructor con =
		// obj.getClass().getConstructor(java.sql.ResultSet.class);
		// Object o = con.newInstance(new Object[] { rs });
		// Class cfoo = Class.forName("Foo");
		// Constructor con = cfoo.getConstructor(Integer.TYPE, String.class);
		// Object o = con.newInstance(new Object[]{new Integer(27), "Hi !"});
		// Foo foo = (Foo)o;
	}

}
