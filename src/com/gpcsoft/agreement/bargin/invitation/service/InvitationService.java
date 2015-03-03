package com.gpcsoft.agreement.bargin.invitation.service;
import java.io.IOException;
import java.util.Map;

import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;
import com.gpcsoft.epp.project.domain.Project;

import freemarker.template.TemplateException;

/** 
  *  Comments: <strong>BulletinAgreementService</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-10-8 上午10:17:10 by guoyr    					                            
  *  <br/>Modified Date:  2010-10-8 上午10:17:10 by guoyr   
  *  @since 0.5
  *  @version: 0.5 
  */ 
public interface InvitationService  {

	/** 
	 * Description : 保存邀请函
	 * Create Date: 2010-10-8下午05:19:42 by guoyr  Modified Date: 2010-10-8下午05:19:42 by guoyr
	 * @param content 邀请函内容  supplierIds邀请供应商
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	public Inviterollrequ saveInvitation(Inviterollrequ inviterollrequ,String content,String supplierIds) throws Exception;
	
	/** 
	 * Description : 跳转到邀请函页面时获得邀请函内容
	 * Create Date: 2010-10-29下午03:31:44 by guoyr  Modified Date: 2010-10-29下午03:31:44 by guoyr
	 * @param   
	 * @return  
	 * @throws TemplateException 
	 * @throws IOException 
	 * @throws Exception 
	 * @Exception   
	 */
	public Map<String, Object> getInvitationInfo(String projectId) throws IOException, TemplateException, Exception;
	
	/** 
	 * Description :  得到新的邀请函内容
	 * Create Date: 2010-11-17下午06:10:31 by guoyr  Modified Date: 2010-11-17下午06:10:31 by guoyr
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	public String getInviterollrequContent(Project project) throws Exception;
}
