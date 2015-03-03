package com.gpcsoft.epp.common.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class RoleTypeEnum {
	
	
	public final static String ROLE_TYPE = "es_roleType";
	/**
	 * 采购人
	 */
	public final static String ECP_BUYER = "00";
	
	/**
	 * 代理机构
	 */
	public final static String ECP_AGENCY = "01";
	
	/**
	 * 供应商
	 */
	public final static String ECP_SUPPLIER = "02";
	
	/**
	 * 监管机构
	 */
	public final static String ECP_SUPERVISE = "03";
	
	/**
	 * 专家
	 */
	public final static String ECP_EXPERT = "04";
	
	/**
	 * 代理机构管理员
	 */
	public final static String ECP_AGENCY_MANAGER = "05";
	
	/**
	 * 监管机构管理员
	 */
	public final static String ECP_SUPERVISE_MANAGER = "06";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(RoleTypeEnum.ROLE_TYPE, str);
	}
	
}
