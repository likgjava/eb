package com.gpcsoft.epp.webService.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

import com.gpcsoft.epp.webService.common.dto.annotation.ColumnMapping;

/**
 * 对象工具类
 * @author zhouzhanghe
 * @created at 2011-11-08 14:17
 */
public class ObjectUtil {
	
	/**
	 * 验证ColumnMapping的nullable配置
	 * @param o
	 * @return true:如果对象的值符合nullable的配置,false:如果对象的值符合nullable的配置
	 * @author zhouzhanghe
	 * @created date 2011-11-08 15:29
	 */
	public boolean validNullable(Object o){
		Field[] fields = o.getClass().getDeclaredFields();
		for(Field field : fields){
			if(field.isAnnotationPresent(ColumnMapping.class)){
				ColumnMapping columnMapping = field.getAnnotation(ColumnMapping.class);
				/*如果非空*/
				if(columnMapping.nullable() == false){
					//创建调用方法及拼装调用的方法方法名
					try {
						Object value = o.getClass().getMethod("get"+String.valueOf(field.getName().charAt(0)).toUpperCase().concat(field.getName().substring(1, field.getName().length())), null).invoke(o, null);
						if(value == null)
							return false;
					} catch (SecurityException e) {
						e.printStackTrace();
						return false;
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
						return false;
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						return false;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						return false;
					} catch (InvocationTargetException e) {
						e.printStackTrace();
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * Description :  获取代理对象Id 
	 * Create Date: 2011-12-12下午03:36:38 by chenhongjun  
	 * Modified Date: 2011-12-12下午03:36:38 by chenhongjun
	 *@param obj
	 *@return
	 *下午03:36:38 
	 *String
	 */
	public static String getLazyInitializerObjId(Object obj){
		String result=null;
		if(obj instanceof HibernateProxy) {
			  LazyInitializer lazyInitializer = ((HibernateProxy)obj).getHibernateLazyInitializer();
			   result = (String)lazyInitializer.getIdentifier();
		  }
		return result;
	}
	
}
