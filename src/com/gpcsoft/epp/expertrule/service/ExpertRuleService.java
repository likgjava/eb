package com.gpcsoft.epp.expertrule.service;

import java.util.List;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRuleItem;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.eprocurement.taker.provider.CodePO;

public interface ExpertRuleService extends BaseGenericService<TakeExpertRule>{

	
	/** 
	 * Description : 保存专家规则 
	 * Create Date: 2010-8-2下午03:16:36 by yangx  Modified Date: 2010-8-2下午03:16:36 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRule saveExpertRule(TakeExpertRule takeExpertRule);
	
	/** 
	 * Description : 提交专家规则 
	 * Create Date: 2010-8-2下午03:16:36 by yangx  Modified Date: 2010-8-2下午03:16:36 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRule saveSubmitExpertRule(TakeExpertRule takeExpertRule,String takeExpertRuleId);
	
	/** 
	 * Description :  保存专家规则条目
	 * Create Date: 2010-8-30下午08:31:57 by yangx  Modified Date: 2010-8-30下午08:31:57 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRuleItem saveExpertRuleCondition(TakeExpertRuleItem takeExpertRuleItem);
	
	
	/** 
	 * Description :  根据包组Id查询专家抽取规则
	 * Create Date: 2010-8-17下午06:49:55 by yangx  Modified Date: 2010-8-17下午06:49:55 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRule getExpertRuleBySubProjectId(String subProjectId);
	
	/** 
	 * Description :  根据包组Id查询专家抽取规则
	 * Create Date: 2010-8-17下午06:49:55 by yangx  Modified Date: 2010-8-17下午06:49:55 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRule toCreateOrUpdateTakeExpertRule(String subProjectId);

	/** 
	 * Description :  通过WebService读取信息写到指定位置
	 * Create Date: 2010-8-30下午03:45:16 by yangx  Modified Date: 2010-8-30下午03:45:16 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveInfoForWebService(String infoType);
	
	/** 
	 * Description :  根据单位名称进行模糊查询单位信息
	 * Create Date: 2010-8-23下午03:38:47 by yangx  Modified Date: 2010-8-23下午03:38:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<CodePO> getUnitByName(String unitName);
	
	/** 
	 * Description :  获取信息[来源：本地]
	 * Create Date: 2010-8-30下午04:31:01 by yangx  Modified Date: 2010-8-30下午04:31:01 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<CodePO> getInfoForFile(String infoType);
	/** 
	 * Description :  获取品目信息[来源：本地]
	 * Create Date: 2010-8-30下午04:43:33 by yangx  Modified Date: 2010-8-30下午04:43:33 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> getPurCategory();
	
	
	/** 
	 * Description :  根据项目规则Id查询规则条目
	 * Create Date: 2010-8-31下午02:22:46 by yangx  Modified Date: 2010-8-31下午02:22:46 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<TakeExpertRuleItem> getTakeExpertRuleItemBytakeExpertRuleId(String takeExpertRuleId);
	
	/** 
	 * Description :  根据项目规则明细Id获取规则明细
	 * Create Date: 2010-8-31下午04:34:31 by yangx  Modified Date: 2010-8-31下午04:34:31 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRuleItem getTakeExpertRuleItemByObjId(String takeExpertRuleItemId);
	
	/** 
	 * Description : 根据项目Id获取评标室 
	 * Create Date: 2010-9-20下午02:08:03 by yangx  Modified Date: 2010-9-20下午02:08:03 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getMeetRoomByProjectId(String projectId);
	/** 
	 * Description :  根据项目id获取抽取规则
	 * Create Date: 2010-10-15下午01:49:48 by wangcl  Modified Date: 2010-10-15下午01:49:48 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRule getByProjectId(String projectId);

	/** 
	 * Description :  按指定组别获取项目下所有标段的某组成员
	 * Create Date: 2010-8-5上午10:44:36 by yangx  Modified Date: 2010-8-5上午10:44:36 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project saveOrgetAllGroupMenber(String projectId, String groupType);

	/** 
	 * Description :  根据当前用户和包组获得成员信息  
	 * Create Date: 2010-8-5上午11:35:42 by yangx  Modified Date: 2010-8-5上午11:35:42 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<WorkgMember> getWorkMemberListByUserIdAndProjectId(String workgType, String projectId);
	
}
