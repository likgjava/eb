package com.gpcsoft.epp.webService.common.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

 

public  class XmlUtil {	
	/**
	 * 将XML文件内容转换为Document
	 * @param xmlPath xml文件路径
	 * @return
	 * @throws Exception
	 * @author zhouzhanghe at 2010.12.24 16:51
	 */
	public static Document parseXMLToDocument(String xmlPath) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(xmlPath));
		String tempStr;
		StringBuffer ewXml = new StringBuffer();
		while ((tempStr = reader.readLine()) != null) {
			ewXml.append(tempStr);
		}
		reader.close();
		return DocumentHelper.parseText(ewXml.toString());
	}
	
	/**
	 * 将XML文件中结点的内容映射到对象
	 * @param e  xml文件中的结点
	 * @param o xml填充的对象
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @author zhouzhanghe at 2010.12.24 10:48
	 */
	public static Object elementToObject(Element e, Class c) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Object o = c.newInstance();
		
		Method[] methods = c.getDeclaredMethods();
		for(Method method : methods){
			if(method.isAnnotationPresent(NodeMapping.class)){
				NodeMapping recordMapping = method.getAnnotation(NodeMapping.class);
				String nodeTag = recordMapping.tag();
				Class returnType = method.getReturnType();//方法返回类型
				Method setMethod = o.getClass().getMethod("s" + method.getName().substring(1), method.getReturnType());
				//处理返回类型为基本类型和String类型
				if(returnType.isPrimitive() || returnType == String.class){
					String value = null;
					Element subElemnet = e.element(nodeTag);
					if(subElemnet == null)
						throw new RuntimeException(e.getName() + "结点里没有" + nodeTag + "子结点。");
					value = subElemnet.getTextTrim();
					
					//非空验证
					if(recordMapping.notNull() && (value == null || StringUtils.empty(value))) 
						throw new RuntimeException("方法" + method.getName() + "返回值不能为空.");

						
					setMethod.invoke(o, value);
				//处理返回类型为List类型
				}else if(returnType == List.class){
					List list = new ArrayList();
					Type genericReturnType =method.getGenericReturnType();
					if(genericReturnType instanceof ParameterizedType){
						List<Element> elements = e.element(nodeTag).elements();
						for(Element subElement : elements){
							Type[] types = ((ParameterizedType)genericReturnType).getActualTypeArguments();
							list.add(elementToObject(subElement,(Class)types[0]));
						}
					}
					setMethod.invoke(o, list);
				//处理其它返回类型(这里的其它指返回类型为自定义类)
				}else{
					Element subElemnet = e.element(nodeTag);
					setMethod.invoke(o, elementToObject(subElemnet, returnType));
				}
			}
		}
		return o;
	}
	/**
	 * 将XML文件中结点的内容映射到对象(通过将NodeMapping放在属性上)
	 * @param e  xml文件中的结点
	 * @param o xml填充的对象
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @author zhouzhanghe at 2010.12.24 10:48
	 */
	public static Object elementToObjectViaNodeMappingInColumn(Element e, Class c) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Object o = c.newInstance();
		
		Field[] fields = o.getClass().getDeclaredFields();
		for(Field field : fields){
			if(field.isAnnotationPresent(NodeMapping.class)){
				NodeMapping recordMapping = field.getAnnotation(NodeMapping.class);
				String nodeTag = recordMapping.tag();
				Class columnType = field.getType();//属性类型
				Method setMethod = o.getClass().getMethod("set"+String.valueOf(field.getName().charAt(0)).toUpperCase().concat(field.getName().substring(1, field.getName().length())), field.getType());//创建调用方法
				//处理属性类型为基本类型和String类型
				if(columnType.isPrimitive() || columnType == String.class){
					String value = null;
					Element subElemnet = e.element(nodeTag);
					if(subElemnet == null)
						throw new RuntimeException(e.getName() + "结点里没有" + nodeTag + "子结点。");
					value = subElemnet.getTextTrim();
					
					setMethod.invoke(o, value);
					//处理属性类型为List类型
				}else if(columnType == List.class){
					List list = new ArrayList();
					Type genericReturnType =field.getGenericType();
					if(genericReturnType instanceof ParameterizedType){
						List<Element> elements = e.element(nodeTag).elements();
						for(Element subElement : elements){
							Type[] types = ((ParameterizedType)genericReturnType).getActualTypeArguments();
							list.add(elementToObject(subElement,(Class)types[0]));
						}
					}
					setMethod.invoke(o, list);
					//处理其它返回类型(这里的其它指返回类型为自定义类)
				}else{
					Element subElemnet = e.element(nodeTag);
					setMethod.invoke(o, elementToObject(subElemnet, columnType));
				}
			}
		}
		return o;
	}

	/**
	 * 将对象的属性封装为Element对象,
	 * @return 封装后的Element
	 * @author zhouzhanghe(2010.12.20 12:30)
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static Element objectToElement(Object o) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if( ! o.getClass().isAnnotationPresent(NodeMapping.class))
			throw new RuntimeException(o.getClass() + "传入对象的类没有"+NodeMapping.class.toString()+"注解");
		NodeMapping recordMappingClass = o.getClass().getAnnotation(NodeMapping.class);//得到类的标注
		Element record = DocumentFactory.getInstance().createElement(recordMappingClass.tag());//类的element
		Method[] methods = o.getClass().getDeclaredMethods();
		for(Method method : methods){
			if(method.isAnnotationPresent(NodeMapping.class)){
				NodeMapping annotation = method.getAnnotation(NodeMapping.class);
				
				method = o.getClass().getMethod(method.getName(), null);//创建调用方法
				Object value = method.invoke(o, null);//调用方法
				
				//非空验证
				if(annotation.notNull() && value == null) 
					throw new RuntimeException(o.getClass().toString() + "类中方法" + method.getName() + "返回值不能为空.");
				
				//创建子结点
				Element ele = DocumentFactory.getInstance().createElement(annotation.tag());//属性的element
				
				//处理如果返回值为空，则加入一个空值结点
				if(value == null){
					record.add(ele);
				}
				//处理返回类型为String或基本类型
				else if(method.getReturnType().isPrimitive() || method.getReturnType() == String.class){
					ele.setText(value.toString());
					record.add(ele);
 				}//处理返回类型为List
				else if(method.getReturnType() == List.class){
 					List list = (List) value;
 					for(Object subObj : list){
 						ele.add(objectToElement(subObj));
 					}
 					record.add(ele);
 				}//如果返回的是自定义对象类型,则直接放入类结点里
				else if(value != null){
 					record.add(objectToElement(value));
 				}else
 					throw new RuntimeException("无法处理" + o.getClass().toString() + "类中方法" + method.getReturnType() + "的返回类型.");
			}	
		}		
		return record;
	}
	
	
	/**
	 * 将对象的属性封装为Element对象(通过将NodeMapping放在属性上)
	 * @return 封装后的Element
	 * @author zhouzhanghe(2010.12.20 12:30)
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static Element objectToElementViaNodeMappingInColumn(Object o) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if( ! o.getClass().isAnnotationPresent(NodeMapping.class))
			throw new RuntimeException(o.getClass() + "传入对象的类没有"+NodeMapping.class.toString()+"注解");
		NodeMapping recordMappingClass = o.getClass().getAnnotation(NodeMapping.class);//得到类的标注
		Element record = DocumentFactory.getInstance().createElement(recordMappingClass.tag());//类的element
		Field[] fields = o.getClass().getDeclaredFields();
		for(Field field : fields){
			if(field.isAnnotationPresent(NodeMapping.class)){
				NodeMapping annotation = field.getAnnotation(NodeMapping.class);
				
				Method method = o.getClass().getMethod("get"+String.valueOf(field.getName().charAt(0)).toUpperCase().concat(field.getName().substring(1, field.getName().length())), null);//创建调用方法
				Object value = method.invoke(o, null);//调用方法
				
				//非空验证
				if(annotation.notNull() && value == null) 
					throw new RuntimeException(o.getClass().toString() + "类中方法" + method.getName() + "返回值不能为空.");
				
				//创建子结点
				Element ele = DocumentFactory.getInstance().createElement(annotation.tag());//属性的element
				
				//处理如果返回值为空，则加入一个空值结点
				if(value == null){
					record.add(ele);
				}
				//处理返回类型为String或基本类型
				else if(method.getReturnType().isPrimitive() || method.getReturnType() == String.class){
					ele.setText(value.toString());
					record.add(ele);
				}//处理返回类型为List
				else if(method.getReturnType() == List.class){
					List list = (List) value;
					for(Object subObj : list){
						ele.add(objectToElement(subObj));
					}
					record.add(ele);
				}//如果返回的是自定义对象类型,则直接放入类结点里
				else if(value != null){
					record.add(objectToElement(value));
				}else
					throw new RuntimeException("无法处理" + o.getClass().toString() + "类中方法" + method.getReturnType() + "的返回类型.");
			}	
		}		
		return record;
	}
	
	/**
	 * 将对象转换为xml
	 * @param o 被转换的对象
	 * @return
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @author zhouzhanghe
	 * @since 2011.3.25 14:44
	 */
	public static String objectToXml(Object o) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		 return objectToElement(o).asXML();
	}
	
    /**
     * Desc: 查询第一个结点名为tagName的子结点
     * @param elem 被查询的结点
     * @param tagName 子结点名
     * @return
     * @author zhouzhanghe
     * @since 2011.3.25 12:56
     */
    public static Element getFirstChildElement (Element elem, String tagName) {
    	if(elem == null || tagName == null)
    		throw new RuntimeException("传入参数不能为空.");
		if(tagName.equalsIgnoreCase(elem.getName()))
			return elem;
		else if(elem.elements().size() > 0){
	    	List<Element> elements = elem.elements();
	    	
	    	for(Element e : elements){
	    		Element findElement = getFirstChildElement(e, tagName);
	    		if(findElement != null)
	    			return findElement;
	    		else
	    			getFirstChildElement(e, tagName);
	    	}
    	}
    	return null;
    }
}
