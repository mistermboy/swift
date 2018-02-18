package Foundation;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class XMLWriterImprove {

	public static <T> void _write(ArrayList<T> objects, String root, String documentName) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		ArrayList<String> values = null;
		try {
			fichero = new FileWriter(documentName + ".xml");
			pw = new PrintWriter(fichero);

			pw.write(getHeadborard());
			pw.write(lineBreak());
			pw.write(openElement(root));

			pw.write(lineBreak());
			pw.write(tab());

			for (Object o : objects) {
				Class<?> clazz = o.getClass();
				String objectName = o.getClass().getSimpleName();
				pw.write(openElement(objectName));
				pw.write(lineBreak());

				for (Field field : clazz.getDeclaredFields()) {
					String fieldName = field.getName();

					pw.write(tab());
					pw.write(tab());
					pw.write(openElement(fieldName));

					values = getValues(field, clazz, o);

					if (values != null) {
						if (values.size() > 1) {
							pw.write(tab());
							pw.write(openElement(subElement(fieldName)));
							for (String s : values) {
								pw.write(s);
							}
							pw.write(closeElement(subElement(fieldName)));
						} else {
							pw.write(values.get(0));
						}
					}

					pw.write(closeElement(fieldName));
					pw.write(lineBreak());
				}
				pw.write(tab());
				pw.write(closeElement(objectName));
				pw.write(lineBreak());
				pw.write(tab());
			}
			pw.write(lineBreak());
			pw.write(closeElement(root));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private static ArrayList<String> getValues(Field field, Class<?> clazz, Object o) {
		String fieldName = field.getName();
		Object[] collection = null;
		ArrayList<String> values = new ArrayList<String>();
		for (Method method : clazz.getMethods()) {
			if (method.getName().toLowerCase().endsWith("get" + fieldName.toLowerCase())) {
				try {
					if (isClassCollection(field.getType())) {
						collection = (Object[]) method.invoke(o);
						for (Object singleObject : collection) {
							values.add(singleObject.toString());
						}
					} else {
						values.add(String.valueOf(method.invoke(o)));
					}
					return values;
				} catch (IllegalAccessException e) {
					System.out.println("Could not determine method: " + method.getName());
				} catch (InvocationTargetException e) {
					System.out.println("Could not determine method: " + method.getName());
				}
			}
		}
		return null;
	}

	public static boolean isClassCollection(Class<?> c) {
		return Collection.class.isAssignableFrom(c) || Map.class.isAssignableFrom(c) || c.isArray();
	}

	private static String subElement(String element) {
		return element.substring(0, element.length());
	}

	private static String openElement(String element) {
		return "<" + element + ">";
	}

	private static String closeElement(String element) {
		return "</" + element + ">";
	}

	private static String getHeadborard() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	}

	private static String lineBreak() {
		return "\n";
	}

	private static String tab() {
		return "\t";
	}

}
