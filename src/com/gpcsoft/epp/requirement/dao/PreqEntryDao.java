package com.gpcsoft.epp.requirement.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.requirement.domain.PreqEntry;

public interface PreqEntryDao extends BaseGenericDao<PreqEntry>{
	/** 
	 * Description :  根据申报书条目ID删除对应的需求条目ID
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
	
	
	/**
	 * 
	 * Description :根据项目获得所有需求条目  
	 * Create Date: 2010-11-3下午04:31:45 by liuke  Modified Date: 2010-11-3下午04:31:45 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<PreqEntry> getPreqEntryByprojectId(String projectId);

	
	/**
	 * 
	 * Description :根据包组获得所有需求条目  
	 * Create Date: 2010-11-3下午04:33:07 by liuke  Modified Date: 2010-11-3下午04:33:07 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<PreqEntry> getPreqEntryBySubProjectId(String subProjectId);
}
