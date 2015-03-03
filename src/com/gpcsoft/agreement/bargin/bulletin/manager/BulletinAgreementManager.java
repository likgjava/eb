package com.gpcsoft.agreement.bargin.bulletin.manager;
import java.io.IOException;
import java.util.Map;

import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.project.domain.Project;

/** 
  *  Comments: <strong>BulletinAgreementService</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-10-8 上午10:17:10 by sunl    					                            
  *  <br/>Modified Date:  2010-10-8 上午10:17:10 by sunl   
  *  @since 0.5
  *  @version: 0.5 
  */ 
public interface BulletinAgreementManager {
	/** 
	 * Description : 保存公告
	 * Create Date: 2010-10-8下午05:19:42 by guoyr  Modified Date: 2010-10-8下午05:19:42 by guoyr
	 * @param   
	 * @return  
	 * @throws IOException 
	 * @Exception   
	 */
	public Bulletin saveBulletinAgreement(Bulletin bulletin) throws IOException;
	
	/** 
	 * Description :   保存公告(接口)
	 * Create Date: 2011-5-16下午04:38:20 by sunl  Modified Date: 2011-5-16下午04:38:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Bulletin saveBulletinAgreementInterface(Project project)  throws Exception;
	
	/** 
	 * Description : 根据项目获得模板所需的数据 
	 * Create Date: 2010-12-13下午03:30:43 by guoyr  Modified Date: 2010-12-13下午03:30:43 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getBulletinTemplateMap(Project project) throws Exception;
}
