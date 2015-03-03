package com.gpcsoft.agreement.bargin.bulletin.service;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.project.domain.Project;

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
public interface BulletinAgreementService {

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
	 * Description :  读取excel内容，批量保存采购公告
	 * Create Date: 2011-3-17下午03:38:55 by sunl  Modified Date: 2011-3-17下午03:38:55 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String saveBulletinBatch(HttpServletRequest request,MultipartFile file) throws IOException;
	
	
	/** 
	 * Description : 保存公告(接口)
	 * Create Date: 2010-10-8下午05:19:42 by guoyr  Modified Date: 2010-10-8下午05:19:42 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Bulletin saveBulletinAgreementInterface(Project project)  throws Exception;
	
	/** 
	 * Description : 根据项目ID和项目的类型获得项目的公告 
	 * Create Date: 2010-12-13下午02:41:35 by guoyr  Modified Date: 2010-12-13下午02:41:35 by guoyr
	 * @param   projectId 项目id
	 * @param   bullType 项目类型
	 * @return  
	 * @Exception   
	 */
	public Bulletin getProjectBulletin(String projectId,String bullType);
	
	/** 
	 * Description : 根据项目获得模板所需的数据 
	 * Create Date: 2010-12-13下午03:30:43 by guoyr  Modified Date: 2010-12-13下午03:30:43 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getBulletinTemplateMap(Project project) throws Exception;


	/** 
	 * Description :  批量更新状态
	 * Create Date: 2011-3-11下午10:51:30 by yucy  Modified Date: 2011-3-11下午10:51:30 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateStatus(String objIds, Map<String, Object> param) throws Exception;
}
