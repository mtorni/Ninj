package com.ninj.helpers;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.ninj.annotations.DBOAutoKey;
import com.ninj.annotations.DBOFKey;
import com.ninj.annotations.DBOField;
import com.ninj.annotations.DBOPKey;
import com.ninj.annotations.DBOTable;
import com.ninj.bind.DBField;
import com.ninj.bind.DBInfo;

public abstract class ObjectHelper {

	public static DBInfo getDBInfo(Object obj) throws Exception {

		DBInfo info = null;
		Document doc = null;

	
			try {

				Annotation[] ant = obj.getClass().getDeclaredAnnotations();

				// get table name
				for (Annotation annotation : ant) {
					if (annotation instanceof DBOTable) {
						DBOTable x = (DBOTable) annotation;
						info = new DBInfo(obj, x.name());
						break;
					}
				}

				Field[] fields = obj.getClass().getDeclaredFields();
				List lt = new ArrayList();
				for (Field field : fields) {
					Annotation[] fant = field.getDeclaredAnnotations();
					for (Annotation annotation : fant) {	
						Object value = getObjectFieldValue(obj, field.getName());

						if (annotation instanceof DBOAutoKey) {
							DBOAutoKey x = (DBOAutoKey) annotation;
							DBField f = new DBField(x.name(), field.getName(),
									value);
							f.setAuto(true);
							f.setPKey(true);
							lt.add(f);
						}
						
						
						if (annotation instanceof DBOPKey) {
							DBOPKey x = (DBOPKey) annotation;
							DBField f = new DBField(x.name(), field.getName(), value);
							f.setPKey(true);
							lt.add(f);
						}
						
						if (annotation instanceof DBOFKey) {
							DBOFKey x = (DBOFKey) annotation;
							DBField f = new DBField(x.name(), field.getName(),
									value);
							f.setFKey(true);
							lt.add(f);
						}

						if (annotation instanceof DBOField) {
							DBOField x = (DBOField) annotation;
							DBField f = new DBField(x.name(), field.getName(),
									value);
							lt.add(f);
						}

					}

				}

				info.setFieldList(lt);
			} catch (Exception e) {
				System.out.println("can't find antno for ninj object");
				throw e;
			}

	return info;

	}

	public static Object getObjectFieldValue(Object obj, String name)
			throws Exception {

		Object s = null;
		try {
			Class c = obj.getClass();

			Field f = c.getDeclaredField(name);

			f.setAccessible(true);

			s = f.get(obj);

		} catch (Exception e) {
			throw e;
		}

		return s;

	}

	public static void setObjectField(Object obj, DBField df, ResultSet rs)
			throws Exception {

		Class c = obj.getClass();

		Field f = c.getDeclaredField(df.getObjectName());

		f.setAccessible(true);

		Class cc = f.getType();

		if (cc == String.class) {
			f.set(obj, rs.getString(df.getDBName()));
		} else if (cc == Date.class) {
			try {
				f.set(obj, rs.getTimestamp(df.getDBName()));
			} catch (Exception e) {
				f.set(obj, null);
			}
		} else if (cc == int.class) {
			f.set(obj, new Integer(rs.getInt(df.getDBName())));
		} else if (cc == long.class) {
			f.set(obj, new Long(rs.getLong(df.getDBName())));
		} else if (cc == float.class) {
			f.set(obj, new Float(rs.getFloat(df.getDBName())));
		} else if (cc == double.class) {
			f.set(obj, new Double(rs.getDouble(df.getDBName())));
		}

	}

	public static void setAutoObjectField(Object obj, DBField df, long autoId)
			throws Exception {

		Class c = obj.getClass();

		Field f = c.getDeclaredField(df.getObjectName());

		f.setAccessible(true);

		Class cc = f.getType();

		if (cc == int.class) {			
			f.set(obj, new Integer((int) autoId));			
		} else if (cc == long.class) {			
			f.set(obj, new Long(autoId));			
		}
		df.setValue(autoId);

	}

}
