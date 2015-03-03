
package com.gpcsoft.epp.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Element;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.utils.BeanToXml.TransientType;

/** @Description: Bean 工具类 
 *    
 * @version V1.0
 * @Create Date 2011-3-15 下午02:34:06 By wanghz   
 */
public class BeanUtil {

	private static EnumService enumService = null;
	public static EnumService getEnumService(){
		if (null == enumService) {
			enumService = (EnumService)FrameBeanFactory.getBean("enumServiceImpl");
		}
		return enumService;
	}
	
	/**
	 * @Description: 根据xml节点获取对象实例
	 * @param e xml节点
	 * @param c 创建实例类
	 * @return Object
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 * @Create Date 2011-3-15 下午03:26:20 By wanghz
	 */
	public static Object getObjectByElement(org.dom4j.Element e) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, ParseException{
		if (null != e && null != e.attributeValue("className") && !"".equals(e.attributeValue("className"))) {
			String className = e.attributeValue("className");
			Object object = Class.forName(className).newInstance();
			Map<String,String> data = new HashMap<String, String>();
			Map<String, String> classNames = new HashMap<String, String>();
			for (org.dom4j.Element element:(java.util.List<org.dom4j.Element>)e.elements()) {
				data.put(element.attributeValue("name"), element.getTextTrim());
				String chassName = element.attributeValue("className");
				if (null != chassName && !"".equals(chassName)) {
					classNames.put(element.attributeValue("name"), chassName);
				}
			}
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field:fields) {
				String value = data.get(field.getName());
				if (null != value && !"".equals(value)) {
					field.setAccessible(true);
					if (field.getType().equals(String.class)) {
						field.set(object, value);
					}
					if (field.getType().equals(BigDecimal.class)) {
						field.set(object, new BigDecimal(value));
					}
					if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
						field.set(object,Integer.parseInt(value));
					}
					if (field.getType().equals(boolean.class)||field.getType().equals(Boolean.class) || field.getClass().equals(Boolean.class)) {
						if ("1".equals(value) || "TRUE".equals(value.toLowerCase())||"true".equals(value.toLowerCase())) {
							field.set(object,true);
						} else {
							field.set(object,false);
						}
					}
					if (field.getType().equals(Date.class)) {
						field.set(object,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value));
					}
					field.setAccessible(false);
				}
			}
			for (Entry<String, String> entry:classNames.entrySet()) {
				String value = data.get(entry.getKey());
				String[] propertyNames = entry.getKey().split("\\.");
				if (null != propertyNames && propertyNames.length == 2) {
					Object property = Class.forName(entry.getValue()).newInstance();
					for (Field field:property.getClass().getDeclaredFields()) {
						if (field.getName().equals(propertyNames[1])) {
							field.setAccessible(true);
							field.set(property, value);
							field.setAccessible(false);
							break;
						}
					}
					for (Field field:fields) {
						if (field.getName().equals(propertyNames[0])) {
							field.setAccessible(true);
							field.set(object, property);
							field.setAccessible(false);
							break;
						}
					}
				}
			}
			return object;
		}
		return null;
	}
	
	/**
	 * @Description: 将string解析MAP
	 * @param str {key:value,key:value}
	 * @return Map<String,String>
	 * @throws Exception
	 * @Create Date 2011-4-1 上午09:25:42 By wanghz
	 */
	public static Map<String,String> parseMap(String str){
		Map<String, String> map = null;
		if (null != str && !"".equals(str)) {
			 String[] s = str.split(",");
			 if (null != s && s.length>0) {
				 map = new HashMap<String, String>(s.length);
				 for (String mapStr:s) {
					 String[] maps = mapStr.split(":");
					 if (maps.length == 2) {
						 map.put(maps[0],maps[1]);
					 }
				 }
			 }
		}
		return map;
	}
	
	/**
	 * @Description: 获取枚举集合
	 * @param enumKey 枚举KEY
	 * @return List<Map<String,String>> [{code:枚举值,name:枚举描述}]
	 * @throws Exception
	 * @Create Date 2011-3-17 上午11:32:05 By wanghz
	 */
	public static List<Map<String,String>> getEnumList(String enumKey){
		List<String> list = getEnumService().getEnum(enumKey);
		List<Map<String,String>> enumList = new ArrayList<Map<String,String>>();
		for (String str:list) {
			if (null != str && !"".equals(str) && str.contains(":")) {
				Map<String,String> enumMap = new HashMap<String, String>();
				String[] enums = str.split(":");
				enumMap.put("code", enums[0]);
				enumMap.put("name", enums[1]);
				enumList.add(enumMap);
			}
		}
		return enumList;
	}
	
	/**
	 * @Description: Bean to XML
	 * @return org.dom4j.Document
	 * @throws JgException
	 * @Create Date 2011-4-8 下午03:55:57 By wanghz
	 */
	public org.dom4j.Document beanToXmlNode(Object object) throws EsException{
		org.dom4j.Document document = org.dom4j.DocumentHelper.createDocument();
		Element root = document.addElement("root");
		String className = object.getClass().getName();
		String sortName = className.substring(className.lastIndexOf(".")+1);
		Element element = root.addElement(sortName.substring(0,1).toLowerCase()+sortName.substring(1));
//		element.addAttribute("className",className);
		this.beanToXmlNode(element,object);
		return document;
	}
	
	/**
	 * @Description: Bean to XML
	 * @param object Bean
	 * @param e XML元素节点
	 * @return org.dom4j.Element
	 * @throws JgException
	 * @Create Date 2011-4-8 下午03:55:57 By wanghz
	 */
	public org.dom4j.Element beanToXmlNode(org.dom4j.Element e,Object object) throws EsException{
		try {
			for (Method method:object.getClass().getMethods()) {
				if (method.isAnnotationPresent(BeanToXml.class)) {
					BeanToXml beanToXml = method.getAnnotation(BeanToXml.class);
					Object o = method.invoke(object);
					if (beanToXml.transientType().name().equals(TransientType.property.name())) {// 属性
						this.addElement(e,beanToXml.elementName(),o,beanToXml.propertyName(),beanToXml.className());
					}
					if (beanToXml.transientType().name().equals(TransientType.manyToOne.name())) {// 内置对象
						if (null != o) {
							String className = o.getClass().getName();
							if (className.contains("javassist")) {
								className = className.substring(0,className.indexOf("_$$_javassist"));
							}
							Object targetObject = Class.forName(className).newInstance();
							BeanUtils.copyProperties(targetObject, o);
							this.beanToXmlNode(this.addElement(e, beanToXml.elementName(), "", beanToXml.propertyName(),beanToXml.className()), targetObject);
						}
					}
					if (beanToXml.transientType().name().equals(TransientType.manyToMany.name())) {// 内置对象集合
						if (null != o && o instanceof Collection) {
							Collection collection = (Collection)o;
							if (!collection.isEmpty()) {
								Iterator iterator = collection.iterator();
								while (iterator.hasNext()) {
									Object collectionInstance = iterator.next();
									if (null != collectionInstance) {
										this.beanToXmlNode(this.addElement(e, beanToXml.elementName(), "", beanToXml.propertyName(),beanToXml.className()),collectionInstance);
									}
								}
							}
						}
					}
				}
			}
		} catch (SecurityException e1) {
			throw new EsException(e1.getMessage());
		} catch (IllegalArgumentException e1) {
			throw new EsException(e1.getMessage());
		} catch (IllegalAccessException e1) {
			throw new EsException(e1.getMessage());
		} catch (InvocationTargetException e1) {
			throw new EsException(e1.getMessage());
		} catch (InstantiationException e1) {
			throw new EsException(e1.getMessage());
		} catch (ClassNotFoundException e1) {
			throw new EsException(e1.getMessage());
		}
		return e;
	}
	private org.dom4j.Element addElement(org.dom4j.Element e,String elementName,Object object,String propertyName,String className){
		if (null == object) {
			object = "";
		}
		org.dom4j.Element element = e.addElement(elementName);
		element.addAttribute("name", propertyName).setText(object.toString());
		if (null != className && !"".equals(className)) {
			element.addAttribute("className", className);
		}
		return element;
	}
}
