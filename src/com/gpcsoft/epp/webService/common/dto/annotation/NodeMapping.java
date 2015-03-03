package com.gpcsoft.epp.webService.common.dto.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
 * @version 1.0
 * @since 2011-3-14 15:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NodeMapping {
	
	/**
	 * XML文件中的每个Node的开始标记
	 * @return
	 */
	public String tag();
	
	/**
	 * 非空验证
	 * @return
	 */
	public boolean notNull() default false; 
}
