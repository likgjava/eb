package com.gpcsoft.epp.expertrule.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
  *  Comments: <strong>RuleTypeEnum</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 执行平台    		
  *  <br/>Create Date：2010-8-9 上午09:51:40 by yangx    					                            
  *  <br/>Modified Date:  2010-8-9 上午09:51:40 by yangx                                   
  *	  @since 0.5
  *   @version: 0.5 
  */ 
public class ExpertRuleTypeEnum {

	public final static String EXPERTRULE_TYPE = "expertRuleType";

	/**
	 * 论证规则
	 */
	public final static String PROOF_RULE = "00";
	
	/**
	 * 评审规则
	 */
	public final static String ACCREDITATION_RULE = "01";
	
	/**
	 *开标规则
	 */
	public final static String OPENBID_RULE = "02";
	
	/**
	 * 工作规则
	 */
	public final static String WORK_RULE = "03";

	/**
	 * 采购单位
	 */
	public final static String UNIT_BUYER = "00";
	
	/**
	 * 回避单位
	 */
	public final static String UNIT_OUT = "01";
	
	/**
	 * 正常抽取
	 */
	public final static String EXPERTLEVEL_NOMAL = "1";
	/**
	 * 只平抽取
	 */
	public final static String EXPERTLEVEL_ONLY = "2";
	
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ExpertRuleTypeEnum.EXPERTRULE_TYPE, str);
	}
}
