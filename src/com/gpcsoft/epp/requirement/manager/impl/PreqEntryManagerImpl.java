package com.gpcsoft.epp.requirement.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.requirement.dao.PreqEntryDao;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.epp.requirement.manager.PreqEntryManager;

@Repository
public class PreqEntryManagerImpl extends BaseManagerImpl<PreqEntry> implements PreqEntryManager {

	@Autowired(required=true)  @Qualifier("preqEntryDaoHibernate")
	private PreqEntryDao preqEntryDaoHibernate;
	

	/** 
	 * Description :  根据申报书id获取需求条目
	 * Create Date: 2010-6-24下午01:10:46 by yangx  Modified Date: 2010-6-24下午01:10:46 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<PreqEntry> getPreqEntryByTaskPlan(QueryObject queryObject){
		return preqEntryDaoHibernate.findByQuery(queryObject);
	}
	/** 
	 * Description :  根据需求条目id查询中间表
	 * Create Date: 2010-6-24下午01:54:15 by yangx  Modified Date: 2010-6-24下午01:54:15 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List getProjectMPreqByPreqEntryId(QueryObject queryObject){
		return preqEntryDaoHibernate.findByQuery(queryObject);
	}
	
	/** 
	 * Description :  保存需求条目
	 * Create Date: 2010-7-10下午02:00:59 by Administrator  Modified Date: 2010-7-10下午02:00:59 by Administrator
	 * @param preqEntry
	 */
	public void savePreqEntry(PreqEntry preqEntry) {
		this.save(preqEntry);
	}
	
	/** 
	 * Description :  根据申报书条目ID删除对应的需求条目ID
	 * Create Date: 2010-7-10下午04:55:41 by Administrator  Modified Date: 2010-7-10下午04:55:41 by Administrator
	 * @param taskPlanSubId
	 */
	public void removeByTaskPlanSubId(String taskPlanSubId) {
		preqEntryDaoHibernate.removeByTaskPlanSubId(taskPlanSubId);
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.epp.requirement.manager.PreqEntryManager#getAllByTaskPlanId(java.lang.String)
	 */
	public List<PreqEntry> getAllByTaskPlanId(String objId) throws Exception {
		return preqEntryDaoHibernate.getAllByTaskPlanId(objId);
	}
}
