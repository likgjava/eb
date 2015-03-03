package com.gpcsoft.epp.contract.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class ContractConfirmEnum {
	
	public final static String CONTRACT_CONFIRM = "contractConfirm";
	
	/**
	 * 待提交
	 */
	public final static String WAIT_AUDIT = "00";
	
	/**
	 * 待审核
	 */
	public final static String AUDITING = "01";
	
	/**
	 * 被退回
	 */
	public final static String AUDIT_RETURN = "02";
	
	/**
	 * 已通过
	 */
	public final static String AUDIT_FOR_TPC = "03";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ContractConfirmEnum.CONTRACT_CONFIRM, str);
	}
}
