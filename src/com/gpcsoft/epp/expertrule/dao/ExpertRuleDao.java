package com.gpcsoft.epp.expertrule.dao;

import java.util.List;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRuleItem;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;

public interface ExpertRuleDao extends BaseGenericDao<TakeExpertRule>{

	/** 
	 * Description :  根据工作组类别和包组获得成员信息  
	 * Create Date: 2010-8-5上午11:35:42 by yangx  Modified Date: 2010-8-5上午11:35:42 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<WorkgMember> getWorkMemberListByUserIdAndProjectId(String workgType,String projectId);
	
	/** 
	 * Description :  根据objId更新小组中的组长
	 * Create Date: 2010-8-5下午02:18:04 by yangx  Modified Date: 2010-8-5下午02:18:04 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateWorkgMemberForIsLeader(String objId,String workGroupId);

	/** 
	 * Description :  根据小组成员Id删除小组成员与对应的用户
	 * Create Date: 2010-8-6下午03:05:20 by yangx  Modified Date: 2010-8-6下午03:05:20 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeWorkgMemberAndUser(WorkgMember workgMember);
	
	/** 
	 * Description :  根据小组成员Id跟新正选专家、备选专家
	 * Create Date: 2010-8-9上午11:09:31 by yangx  Modified Date: 2010-8-9上午11:09:31 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateWorkgMemberForAmount(String workgMemberId,String isAmount);
	
	/** 
	 * Description :  通过项目主键和小组类型得到工作小组对象
	 * Create Date: 2010-8-5上午10:48:06 by yangx  Modified Date: 2010-8-5上午10:48:06 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List getWorkGroupByProjectIdAndType(String projectId,String workType);
	
	/** 
	 * Description :  根据账号查询User
	 * Create Date: 2010-8-9上午11:40:24 by yangx  Modified Date: 2010-8-9上午11:40:24 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List getUserByAccount(String account);
	
	/** 
	 * Description :  根据专家Id获取小组成员
	 * Create Date: 2010-8-10下午04:13:05 by yangx  Modified Date: 2010-8-10下午04:13:05 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public WorkgMember getWorkMemberByExpertId(String expertId);
	
	/** 
	 * Description :  获取品目[来源：本地]
	 * Create Date: 2010-8-19上午09:36:29 by yangx  Modified Date: 2010-8-19上午09:36:29 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> getPurCategory();
	
	/** 
	 * Description :  根据专家规则Id获取条目
	 * Create Date: 2010-8-30下午09:34:13 by yangx  Modified Date: 2010-8-30下午09:34:13 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<TakeExpertRuleItem> getTakeExpertRuleItemBytakeExpertRuleId(String takeExpertRuleId);
	
	/** 
	 * Description : 根据项目Id获取评标室 
	 * Create Date: 2010-9-20下午02:08:03 by yangx  Modified Date: 2010-9-20下午02:08:03 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List getMeetRoomByProjectId(String projectId);
	
	/** 
	 * Description :  根据项目id获取抽取规则
	 * Create Date: 2010-10-15下午01:49:48 by wangcl  Modified Date: 2010-10-15下午01:49:48 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRule getByProjectId(String projectId);
}
