package com.gpcsoft.epp.webService.common.dto.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ColumnMapping {
	
	/**
	 * 非空验证
	 * @return
	 */
	public boolean nullable() default true; 
}
