package com.gpcsoft.epp.worktask.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
 *  Comments: <strong>CompletedWorkTaskURLEnum</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-7-21 上午10:54:45 by yangx    					                            
 *  <br/>Modified Date:  2010-7-21 上午10:54:45 by yangx                                   
 *<p>@since 0.5
 *   @version: 0.5 
 */
public class CompletedWorkTaskURLEnum {

	public final static String WORKTASK_URL = "workTaskURL";

	/**
	 * 提交审核申报书URL(二级)
	 */
	public final static String WORKTASK_URL_TASKPLANUPDATE = "TaskPlanController.do?method=toSaveOrUpdate&objId=";
//	public final static String WORKTASK_URL_TASKPLANUPDATE = "TaskPlanController.do?method=toSaveOrUpdate";
	/**
	 *提交审核申报书URL(一级)
	 */
	public final static String WORKTASK_URL_TASKPLANAUDIT = "TaskPlanController.do?method=showDetail&type=sub&objId=";
//	public final static String WORKTASK_URL_TASKPLANAUDIT = "TaskPlanController.do?method=showDetail&type=sub";
	/**
	 *一级单位自己新建提交审核申报书URL(一级)
	 */
	public final static String WORKTASK_URL_TASKPLANAUDIT_S = "TaskPlanController.do?method=toSaveOrUpdateForSum&objId=";
//	public final static String WORKTASK_URL_TASKPLANAUDIT_S = "TaskPlanController.do?method=toSaveOrUpdateForSum";
	/**
	 *申报书--指定项目负责人URL(采购办)
	 */
//	public final static String WORKTASK_URL_TASKPLAN_MANAGER = "view/es/planform/project/agentassignList1.jsp";
	/**
	 *审核采购资金的申报书URL
	 */
	public final static String WORKTASK_URL_TASKPLANAUDITFUND = "view/es/planform/taskplan/taskPlanFormForAuditDe.jsp?objId=";
//	public final static String WORKTASK_URL_TASKPLANAUDITFUND = "view/es/planform/taskplan/taskPlanFormForAuditDe.jsp?";
	/**
	 *审核申报书URL
	 */
	public final static String WORKTASK_URL_TASKPLANAUDITS = "TaskPlanController.do?method=toAuditTaskPlanPage&objId=";
//	public final static String WORKTASK_URL_TASKPLANAUDITS = "TaskPlanController.do?method=toAuditTaskPlanPage";
	/**
	 *提交招标公告URL
	 */
	public final static String WORKTASK_URL_BULLETIN_SUBMIT = "BulletinController.do?method=toSubmitPurBulletin&parentId=";
//	public final static String WORKTASK_URL_BULLETIN_SUBMIT = "BulletinController.do?method=toDraftPurBulletin&projectId=";
//	public final static String WORKTASK_URL_BULLETIN_SUBMIT = "BulletinController.do?method=toDraftPurBulletin";
	/**
	 *审核招标公告URL
	 */
	public final static String WORKTASK_URL_BULLETIN_AUDIT = "BulletinController.do?method=getBulletinAuditByObjId&objId=";
//	public final static String WORKTASK_URL_BULLETIN_AUDIT = "BulletinController.do?method=getBulletinAuditByObjId";
	/**
	 *提交招标公示URL
	 */
	public final static String WORKTASK_URL_RESULT_PREVIEW_SUBMIT = "BulletinController.do?method=toResultPublicity&parentId=";
//	public final static String WORKTASK_URL_RESULT_PREVIEW_SUBMIT = "BulletinController.do?method=toResultPublicity&projectId=";
//	public final static String WORKTASK_URL_RESULT_PREVIEW_SUBMIT = "BulletinController.do?method=toResultPublicity";
	/**
	 *审核招标公示URL
	 */
	public final static String WORKTASK_URL_RESULT_PREVIEW_AUDIT = "BulletinController.do?method=toPublicityAudit&parentId=";
//	public final static String WORKTASK_URL_RESULT_PREVIEW_AUDIT = "BulletinController.do?method=toPublicityAudit&projectId=";
//	public final static String WORKTASK_URL_RESULT_PREVIEW_AUDIT = "BulletinController.do?method=toPublicityAudit";
	/**
	 *提交中标公告URL
	 */
	public final static String WORKTASK_URL_RESULT_BULLETIN_SUBMIT = "BulletinController.do?method=getResultBulletin&parentId=";
//	public final static String WORKTASK_URL_RESULT_BULLETIN_SUBMIT = "BulletinController.do?method=toResultPublicity&projectId=";
//	public final static String WORKTASK_URL_RESULT_BULLETIN_SUBMIT = "BulletinController.do?method=toResultPublicity";
	/**
	 *审核中标公告URL
	 */
	public final static String WORKTASK_URL_RESULT_BULLETIN_AUDIT = "BulletinController.do?method=toBulletinAudit&parentId=";
//	public final static String WORKTASK_URL_RESULT_BULLETIN_AUDIT = "BulletinController.do?method=toBulletinAudit&projectId=";
//	public final static String WORKTASK_URL_RESULT_BULLETIN_AUDIT = "BulletinController.do?method=toBulletinAudit";
	/**
	 *保存供应商报名URL
	 */
	public final static String WORKTASK_URL_SIGNUPRECORD_SAVE = "SignUprecordController.do?method=toPage&parentId=";
//	public final static String WORKTASK_URL_SIGNUPRECORD_SAVE = "SignUprecordController.do?method=toPage&projectId=";
//	public final static String WORKTASK_URL_SIGNUPRECORD_SAVE = "SignUprecordController.do?method=toPage";
	/**
	 *审核供应商报名URL
	 */
	public final static String WORKTASK_URL_SIGNUPRECORD_AUDIT = "SignUprecordController.do?method=getSupplierForAudit&objId=";
//	public final static String WORKTASK_URL_SIGNUPRECORD_AUDIT = "SignUprecordController.do?method=getSupplierForAudit";
	/**
	 *审核采购文件URL
	 */
	public final static String WORKTASK_URL_PURCHASEDOC_AUDIT = "PurchaseDocController.do?method=toConfigPurchaseDocPage&objId=";
	/**
	 *审核采购文件URL
	 */
	public final static String WORKTASK_URL_PURCHASEDOC_SUBMIT = "PurchaseDocController.do?method=toUpdatePurchaseDocPage&objId=";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CompletedWorkTaskURLEnum.WORKTASK_URL, str);
	} 

}
