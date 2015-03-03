package com.gpcsoft.epp.requirement.manager;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.requirement.domain.PreqEntry;

public interface PreqEntryManager extends BaseManager<PreqEntry> {
	/** 
	 * Description :  根据申报书id获取需求条目
	 * Create Date: 2010-6-24下午01:10:46 by yangx  Modified Date: 2010-6-24下午01:10:46 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<PreqEntry> getPreqEntryByTaskPlan(QueryObject queryObject);
	
	/** 
	 * Description :  根据需求条目id查询中间表
	 * Create Date: 2010-6-24下午01:54:15 by yangx  Modified Date: 2010-6-24下午01:54:15 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List getProjectMPreqByPreqEntryId(QueryObject queryObject);

	/** 
	 * Description :  保存需求条目
	 * Create Date: 2010-7-10下午02:00:59 by Administrator  Modified Date: 2010-7-10下午02:00:59 by Administrator
	 * @param preqEntry
	 */
	public void savePreqEntry(PreqEntry preqEntry);

	/** 
	 * Description :  根据申报书条目ID删除对应的需求条目
	 * Create Date: 2010-7-10下午04:55:41 by Administrator  Modified Date: 2010-7-10下午04:55:41 by Administrator
	 * @param taskPlanSubId
	 */
	public void removeByTaskPlanSubId(String taskPlanSubId);
	
	/**
	 * @Description: 根据申报书ID获取所有申报书条目
	 * @param objId 申报书IDobjId
	 * @return List<PreqEntry>
	 * @throws Exception
	 * @Create Date 2010-7-26 下午01:39:07 By wanghz
	 */
	public List<PreqEntry> getAllByTaskPlanId(String objId)throws Exception;
}
