/**
 * <p>yis: Yayeah Infrastructure</p>
 * <p>Description: </p>
 * <p>Yayeah Infrastructure supply some base technology for all yayeah projects.</p>
 * <p>Such as custom exception, abstract dao, </p>
 * <p>common util class, UI style, java script, etc.</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: yayeah (www.yayeah.com)</p>
 */

package com.gpcsoft.goods.common;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.util.ComparableComparator;

/**
 * <p>Title: </p>
 * <p>Description: Algorithm Utils </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: yayeah (www.yayeah.com)</p>
 *
 * @author liujf(liujf@yayeah.com/talkfree@126.com)
 * @date 2008-7-28
 * @version 1.0
 */

public class AlgorithmUtils {
	class YISComparator implements Comparator<Object> {
		private String fieldName;
		private Comparator<Object> yisComparator;
		
		@SuppressWarnings("unchecked")
		public YISComparator(String fieldName) {
			yisComparator = new ComparableComparator();
			this.fieldName = fieldName;
		}

		public int compare(Object o1, Object o2) {
			if (o1 == null || o2 == null) {
				return 0;
			}
			
			Object obj1 = getFieldValue(o1, fieldName);
			Object obj2 = getFieldValue(o2, fieldName);

			return this.yisComparator.compare(obj1, obj2);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Object getFieldValue(Object object, String fieldName) {
		Class clazz = object.getClass();
		StringBuilder methodName = new StringBuilder();
		try {
			methodName.append("get" + fieldName);
			methodName.setCharAt(3, Character.toUpperCase(fieldName.charAt(0)));
			Method method = clazz.getDeclaredMethod(methodName.toString());
			if (method != null) {
				Object obj = method.invoke(object);
				return obj;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Sort the objectsList order by asc. After sorting, the min object is at the index 0.
	 * After sorting, the example result(fileName1,fileName10,fileName11,fileName50,fileName6,fileName9).
	 * After sorting, the example result(00001,00006,00009,00010,00011,00050).
	 * @param objectsList
	 * @param fieldName
	 */
	@SuppressWarnings("unchecked")
	public static void sort(List objectsList, String fieldName) {
		AlgorithmUtils utils = new AlgorithmUtils();
		YISComparator yisComparator = utils.new YISComparator(fieldName);
		Collections.sort(objectsList, yisComparator);
	}
}