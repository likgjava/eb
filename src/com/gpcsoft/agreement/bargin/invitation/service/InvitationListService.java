package com.gpcsoft.agreement.bargin.invitation.service;
import java.io.IOException;

import com.gpcsoft.epp.bulletin.domain.Bulletin;

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
public interface InvitationListService  {

	/** 
	 * Description : 保存公告
	 * Create Date: 2010-10-8下午05:19:42 by guoyr  Modified Date: 2010-10-8下午05:19:42 by guoyr
	 * @param   
	 * @return  
	 * @throws IOException 
	 * @Exception   
	 */
	public Bulletin saveBulletinAgreement(Bulletin bulletin) throws IOException;
}
