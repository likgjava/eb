package com.gpcsoft.epp.project.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
 *  Comments: <strong>ProjectProcessEnum</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-7-15 上午09:27:38 by yangx    					                            
 *  <br/>Modified Date:  2010-7-15 上午09:27:38 by yangx                                   
 *<p>@since 0.5
 *   @version: 0.5 
 */
public class ProjProcessStatusEnum {
	public final static String PROJ_PROCESS_STATUS = "projProcessStatus";
	
	/**
	 * 指定项目经办人
	 */
	public final static String DESIGNATED_PROJECT_MANAGER = "10";
	
	/**
	 * 设定项目规则
	 */
	public final static String DESIGNATED_PROJECT_RULE = "20";
	
	/**
	 * 项目分包
	 */
	public final static String SUB_PROJECT = "30";
	
	/**
	 * 评标小组
	 */
	public final static String EVALUATION_GROUP = "40";
	
	/**
	 * 开标小组
	 */
	public final static String BID_GROUP = "41";
	
	/**
	 * 预定评标室
	 */
	public final static String BOOK_EVALUATION_ROOM = "50";
	
	/**
	 * 上传采购文件
	 */
	public final static String UPLOAD_PURCHASE_FILE = "60";
	
	/**
	 * 采购人确认采购文件
	 */
	public final static String PURCHASER_CONFIRM = "70";
	
	/**
	 * 监察局审核采购文件
	 */
	public final static String SUPERVISION_AUDIT_FILE = "80";
	
	/**
	 * 采购办审核采购文件
	 */
	public final static String PROCUREMENTOFFICE_AUDIT_FILE = "90";
	
	/**
	 * 起草采购公告
	 */
	public final static String DRAFT_PURCHASE_BULLETIN = "100";
	
	/**
	 * 提交采购公告
	 */
	public final static String SUBMIT_PURCHASE_BULLETIN = "110";
	
	/**
	 * 采购办审核采购公告
	 */
	public final static String PROCUREMENTOFFICE_AUDIT_PURCHASE_BULLETIN = "120";
	
	/**
	 * 供应商报名
	 */
	public final static String SUPPLIERS_APPLY = "130";
	
	/**
	 * 代理机构确认供应商报名
	 */
	public final static String SUPPLIERS_APPLY_CONFIRM = "140";
	
	/**
	 * 下载采购文件
	 */
	public final static String DOWNLOAD_PURCHASE_FILE = "150";
	
	/**
	 * 供应商投标
	 */
	public final static String SUPPLIERS_BID = "160";
	
	/**
	 * 开标
	 */
	public final static String OPEN_BID = "170";
	
	/**
	 * 专家评审
	 */
	public final static String EXPERT_ACCREDITATION = "180";
	
	/**
	 * 经办人生成评标报告
	 */
	public final static String GENERATE_EVALUATION_REPORT = "190";
	
	/**
	 * 定标
	 */
	public final static String CALIBRATION_BID = "200";
	
	/**
	 *  起草中标公告
	 */
	public final static String DRAFT_RESULT_BULLETIN = "210";
	
	/**
	 *  提交中标公告
	 */
	public final static String SUBMIT_RESULT_BULLETIN = "220";
	
	/**
	 *  采购办审核中标公告
	 */
	public final static String PROCUREMENTOFFICE_AUDIT_RESULT_BULLETIN = "230";
	
	/**
	 *  起草中标通知书
	 */
	public final static String DRAFT_RESULT_NOTICES = "240";
	
	/**
	 *  供应商起草合同
	 */
	public final static String SUPPLIERS_DRAFT_CONTRACT = "250";
	
	/**
	 *  采购人确认合同
	 */
	public final static String PURCHASER_CONFIRM_CONTRACT = "260";

	/**
	 *  项目归档
	 */
	public final static String PUT_ON_RECORDS = "280";
	
	
	/**
	 * 预定开标室
	 */
	public final static String BOOK_OPEN_BID_EVALUATION_ROOM = "290";
	/**
	 * 起草资格预审公告
	 */
	public final static String  DRAFT_QUALIFICATION="300";
	
	/**
	 * 提交资格预审公告
	 */
	public final static String  SUBMIT_QUALIFICATION="310";
	
	/**
	 * 审核资格预审公告
	 */
	public final static String  AUDIT_QUALIFICATION="320";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ProjProcessStatusEnum.PROJ_PROCESS_STATUS,str);
	}
}
