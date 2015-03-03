package com.gpcsoft.epp.common.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @Description: Bean to Xml 注解标签,标记哪些属性属于转换XML节点 
 * @version V1.0
 * @Create Date 2011-4-8 下午04:00:47 By wanghz
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BeanToXml {
	
	public String elementName();
	
	public String propertyName();
	
	public TransientType transientType();
	
	public String className() default "";
	
	public enum TransientType{property,manyToOne,manyToMany};
}

