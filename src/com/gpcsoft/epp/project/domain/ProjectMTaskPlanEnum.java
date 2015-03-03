package com.gpcsoft.epp.project.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/**
* CopyRright (c)2008-xxxx: <珠海政采 >
* Project: <执行平台>
* Module ID: <立项>
*Comments: <ProjectMTaskPlan类中属性取值定义>
* JDK version used: <JDK5.0>
* Author： <zhouzhanghe>
* Create Date： <2011-06-22>
* Version: 
*/
public class ProjectMTaskPlanEnum {

	public final static String PROJECTENUM = "epp.ProjectMTaskPlan.fromType";

	/**
	 * 采购申报书
	 */
	public final static String CGSBS = "00";
	/**
	 * 建筑类登记表
	 */
	public final static String JZLDJB = "01";
	/**
	 * 产权交易登记表
	 */
	public final static String CQJYDJB = "02";
	/**
	 * 土地交易登记表
	 */
	public final static String TDJYDJB = "03";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ProjectMTaskPlanEnum.PROJECTENUM, str);
	} 
}
