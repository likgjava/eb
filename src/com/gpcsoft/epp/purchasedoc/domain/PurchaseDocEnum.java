package com.gpcsoft.epp.purchasedoc.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.epp.project.domain.ProjPlanStatusEnum;
/**
 * 
  *  Comments: <strong>BulletinConfirmStatusEnum</strong>公告确认状态        		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-6-19 上午05:43:28 by liuke    					                            
  *  <br/>Modified Date:  2010-6-19 上午05:43:28 by liuke                            
  *      
  *  @since 0.4
  *  @version: 0.5
 */
public class PurchaseDocEnum {
   
	public final static String PROJ_DOC_STATUS = "projDocStatus";
	
	/**
	 * 采购文件状态为临时
	 */
	public final static String  TEMP ="11";
	
	/**
	 * 待采购人确认
	 */
	public final static String FORAUDIT = "00";
	
	/**
	 * 采购人确认通过
	 */
	public final static String BUYERPASS = "01";
	
	/**
	 * 监察局审核通过
	 */                        
	public final static String SUPERVISALOFFICEPASS = "02";
	
	/**
	 * 采购办审核通过
	 */                        
	public final static String PURCHASEOFFICEPASS    = "03";
	
	/**
	 * 被退回
	 */                        
	public final static String RETURNBACK    = "04";
	
	/**
	 * 待监察局审核
	 */                        
	public final static String SUPERVISALOFFICE_WAIT = "05";

	/**
	 * 待采购办审核
	 */                        
	public final static String PURCHASEOFFICE_WAIT = "06";
	
	
	/**
	 * 预算总金额
	 */                        
	public final static Double SUMPRICE = Double.parseDouble(((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getValueByConstant(PurchaseDocEnum.PROJ_DOC_STATUS,"SUMPRICE"));
	/**
	 * 文件类型为采购文件
	 */
	public final static String  PURCHASEDOC = "07";  
	/**
	 * 文件类型为澄清文件
	 */
	public final static String  CLARIFICATIONDOC ="08";
	/**
	 * 文件类型为询价文件
	 */
	public final static String  INQPDOC ="09";
	/**
	 * 文件类型为单一来源文件
	 */
	public final static String  SINGLESOURCEDOC ="10";
	
	public final static String SAVETYPE_ADD = "ADD";//文件保存类型：新增

	public final static String SAVETYPE_UPDATE = "UPDATE";//文件保存类型：修改
	
	public static String getCN(String str){
		//return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue("com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc.auditStatus", str);
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(PurchaseDocEnum.PROJ_DOC_STATUS, str);
	} 	
}
