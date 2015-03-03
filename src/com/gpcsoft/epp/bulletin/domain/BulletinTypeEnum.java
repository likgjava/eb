package com.gpcsoft.epp.bulletin.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/**
 * 
  *  Comments: <strong>BulletinTypeEnum</strong>公告类型            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-5-13 上午05:39:15 by yemx    					                            
  *  <br/>Modified Date:  2010-5-13 上午05:39:15 by yemx                                 
  *      
  *  @since 0.4
  *  @version: 0.5
 */
public class BulletinTypeEnum {
	
	//发布状态
	public final static String BULLETIN_RELSTATUS = "relStatus";
	
	/**
	 * 未发布
	 */
	public final static String BULLETIN_NOT_PUBLISH = "00";
	
	/**
	 * 已发布
	 */
	public final static String BULLETIN_PUBLISH = "01";
	
	/**
	 * 撤回
	 */
	public final static String BULLETIN_BACK = "02";
	
	//公告类型
	public final static String BULLETIN_TYPE = "bulletinType";
	
	
	/**
	 * 采购公示/预告
	 */
	public final static String PURCHASE_PREVIEW = "00";
	
	/**
	 * 采购公告
	 */
	public final static String PURCHASE_BULLETIN = "01";
	
	/**
	 * 更正公告
	 */
	public final static String VARIATION_BULLETIN = "02";
	
	/**
	 * 资格预审公告
	 */
	public final static String QUALIFICATION_BULLETIN = "03";
	
	/**
	 * 资格预审结果公示
	 */
	public final static String QUALIFICATION_RESULT_PREVIEW = "04";

	/**
	 * 结果公示
	 */
	public final static String RESULT_PREVIEW = "05";
	
	/**
	 * 结果公告
	 */
	public final static String RESULT_BULLETIN = "06";
	
	/**
	 * 暂停公告
	 */
	public final static String SUSPEND_BULLETIN = "07";
	
	/**
	 * 失败公告
	 */
	public final static String FAIL_BULLETIN = "08";
	
	
	/**
	 * 标评报告
	 */
	public final static String BID_EVALUATION_REPORT = "09";
	
	/**
	 * 询价公告
	 */
	public final static String INQPBULLETIN_BULLETIN = "10";
	
	/**
	 * 变更公告
	 */
	public final static String CHANGEBUKKETIN_BULLETIN = "11";
	
	/**
	 * 竞价公告
	 */
	public final static String BARGIN_BULLETIN = "12";
	/**
	 * 竞价结果公告
	 */	
	public final static String BARGIN_RESULT_BULLETIN = "13";
	/**
	 * 澄清公告 
	 */
	public final static String CLARIFY_BULLETIN="14";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(BulletinTypeEnum.BULLETIN_TYPE, str);
	} 

	/**
	 * Description :  获取公告发布状态枚举值
	 * Create Date: 2011-12-22下午03:15:42 by sunl  Modified Date: 2011-12-22下午03:15:42 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public static String getRelStatusCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(BulletinTypeEnum.BULLETIN_RELSTATUS, str);
	} 
}
