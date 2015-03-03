package com.gpcsoft.epp.expertrule.manager;

/** 
  *  Comments: <strong>向专家库申请评审专家</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-9-8 上午09:50:25 by liuy    					                            
  *  <br/>Modified Date:  2010-9-8 上午09:50:25 by liuy  
  */ 
public interface ExpertRuleSendManager {
	/** 
	 * Description :  发送抽取评审专家申请
	 * Create Date: 2010-9-8上午09:52:29 by liuy  Modified Date: 2010-9-8上午09:52:29 by liuy
	 * @param takeExpertRuleId	专家规则ID
	 * @return					{"成功/失败","原因"}
	 * @throws Exception
	 */
	public String[] sendExpertRule(String takeExpertRuleId) ;

}
