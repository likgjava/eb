package com.gpcsoft.epp.requirement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.requirement.dao.PreqEntryDao;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.epp.requirement.manager.PreqEntryManager;
import com.gpcsoft.epp.requirement.service.PreqEntryService;

@Service 
public class PreqEntryServiceImpl extends BaseGenericServiceImpl<PreqEntry> implements PreqEntryService {

	@Autowired(required=true) @Qualifier("preqEntryManagerImpl")
	private PreqEntryManager preqEntryManager;
	
	@Autowired(required=true)  @Qualifier("preqEntryDaoHibernate")
	private PreqEntryDao preqEntryDaoHibernate;
	
	/** 
	 * Description : 根据查询条件获取申报书信息
	 * Create Date: 2010-7-10下午03:30:46 by Administrator  Modified Date: 2010-7-10下午03:30:46 by Administrator
	 * @param queryObject
	 * @return
	 */
	public List<PreqEntry> getPreqEntryByQueryObject(QueryObject queryObject) {
		logger.debug("\nPreqEntryServiceImpl||getPreqEntryByQueryObject\n");
		return preqEntryManager.findByQuery(queryObject);
	}
	

	/**
	 * @Description: 根据申报书ID获取所有需求条目
	 * @param objId 申报书IDobjId
	 * @return List<PreqEntry>
	 * @throws Exception
	 * @Create Date 2010-7-26 下午01:39:07 By wanghz
	 */
	public List<PreqEntry> getAllByTaskPlanId(String objId) throws Exception {
		logger.debug("\nPreqEntryServiceImpl||getAllByTaskPlanId\n");
		return preqEntryManager.getAllByTaskPlanId(objId);
	}

	/**
	 * 
	 * Description :根据项目获得所有需求条目  
	 * Create Date: 2010-11-3下午04:31:45 by liuke  Modified Date: 2010-11-3下午04:31:45 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<PreqEntry> getPreqEntryByprojectId(String projectId) {
		logger.debug("\nPreqEntryServiceImpl||getPreqEntryByprojectId\n");
		return preqEntryDaoHibernate.getPreqEntryByprojectId(projectId);
	}

	/**
	 * 
	 * Description :根据包组获得所有需求条目  
	 * Create Date: 2010-11-3下午04:33:07 by liuke  Modified Date: 2010-11-3下午04:33:07 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<PreqEntry> getPreqEntryBySubProjectId(String subProjectId) {
		logger.debug("\nPreqEntryServiceImpl||getPreqEntryBySubProjectId\n");
		return preqEntryDaoHibernate.getPreqEntryBySubProjectId(subProjectId);
	}
}
