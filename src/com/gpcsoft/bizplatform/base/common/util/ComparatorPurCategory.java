package com.gpcsoft.bizplatform.base.common.util;

import java.util.Comparator;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
/**
 * 比较List排序
 * @author xiaogh
 *
 */
public class ComparatorPurCategory implements Comparator<Object>{

	 public int compare(Object arg0, Object arg1) {
		 PurCategory p1=(PurCategory)arg0;
		 PurCategory p2=(PurCategory)arg1;
	     return p1.getCategoryCode().compareTo(p2.getCategoryCode());	 
	 }	
}
