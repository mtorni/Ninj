package com.ninj.bind;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.ninj.helpers.NinjHelper;
import com.ninj.helpers.ObjectHelper;
import com.ninj.util.DBUtil;

public abstract class NinjFormat {

	public static void toXML(Object obj) throws Exception {

		Element Root = new Element(obj.getClass().getSimpleName());

		Field[] fields = obj.getClass().getDeclaredFields();

		List lt = new ArrayList();
		for (Field field : fields) {
			Object value = ObjectHelper.getObjectFieldValue(obj, field
					.getName());
			System.out.println("name: " + field.getName() + " value:" + value);
			Element Child = new Element(field.getName());
			Child.addContent(String.valueOf(value));
			Root.addContent(Child);
		}

		XMLOutputter xml_out = new XMLOutputter(Format.getPrettyFormat());
		xml_out.output(Root, System.out);
	}

	public static String getChildText(Element parent, String child) {
		String txt = null;
		try {
			txt = parent.getChild(child).getText();
		} catch (Exception e) {}
		return txt;
	}

}
