package com.gpcsoft.epp.expertrule.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;

public interface ExpertRuleManager extends BaseManager<TakeExpertRule>{

	/** 
	 * Description :  通过WebService读取信息写到指定位置
	 * Create Date: 2010-8-30下午03:45:16 by yangx  Modified Date: 2010-8-30下午03:45:16 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveInfoForWebService(String infoType);
	/** 
	 * Description :  根据项目id获取抽取规则
	 * Create Date: 2010-10-15下午01:49:48 by wangcl  Modified Date: 2010-10-15下午01:49:48 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TakeExpertRule getByProjectId(String projectId);
	/** 
	 * Description :  通过包组Id和小组类型得到工作小组对象
	 * Create Date: 2010-8-5上午10:48:06 by yangx  Modified Date: 2010-8-5上午10:48:06 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public WorkGroup getWorkGroupByProjectIdAndType(String subProjectId,String workType);
	/** 
	 * Description :  根据工作组类别和包组获得成员信息  
	 * Create Date: 2010-8-5上午11:35:42 by yangx  Modified Date: 2010-8-5上午11:35:42 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<WorkgMember> getWorkMemberListByUserIdAndProjectId(
			String workgType, String subProjectId);
	
}
