package com.gpcsoft.epp.project.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class ProjPlanStatusEnum {
	public final static String PROJ_PLAN_STATUS = "projPlanStatus";

	/**
	 * 未开始
	 */
	public final static String NOT_START = "00";
	
	/**
	 * 进行中
	 */
	public final static String ONGOING = "01";
	
	/**
	 * 已完成
	 */
	public final static String FINISH = "02";
	
	/**
	 * 有效
	 */
	public final static String IN_EFFECT = "01";
	
	/**
	 * 无效
	 */
	public final static String INEFFICACY = "00";
	
	/**
	 * 有效
	 */
	public final static String IN_TRUE = "true";
	/**
	 * 无效
	 */
	public final static String IN_FALSE = "false";

	/**
	 * 工作计划节点
	 */
	public final static String PLAN_NODE = "01";

	/**
	 * 工作计划节点自动完成
	 */
	public final static Character PLAN_AUTO = '1';

	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ProjPlanStatusEnum.PROJ_PLAN_STATUS, str);
	} 
	

}
