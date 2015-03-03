package com.gpcsoft.pubservice.application.concern.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.pubservice.application.concern.dao.ConcernGroupDao;
import com.gpcsoft.pubservice.application.concern.domain.ConcernGroup;
import com.gpcsoft.pubservice.application.concern.manager.ConcernGroupManager;
import com.gpcsoft.pubservice.application.concern.service.ConcernGroupService;

@Service 
public class ConcernGroupServiceImpl extends BaseGenericServiceImpl<ConcernGroup> implements ConcernGroupService {

	@Autowired(required=true) @Qualifier("concernGroupManagerImpl")
	private ConcernGroupManager concernGroupManager;
	@Autowired(required=true)  @Qualifier("concernGroupDaoHibernate")
	private ConcernGroupDao concernGroupDaoHibernate;
	
	/** 
	 * Description : 保存关注分组 
	 * Create Date: 2010-11-3下午05:56:54 by guoyr  Modified Date: 2010-11-3下午05:56:54 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ConcernGroup saveConcernGroup (ConcernGroup concernGroup){
		concernGroup.setCreateTime(new Date());
		return concernGroupManager.saveConcernGroup(concernGroup);
	}
	
	/** 
	 * Description : 修改关注分组 
	 * Create Date: 2010-11-3下午05:56:54 by guoyr  Modified Date: 2010-11-3下午05:56:54 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateConcernGroup (ConcernGroup concernGroup){
		return concernGroupDaoHibernate.updateConcernGroup(concernGroup);
	}
	
	/** 
	 * Description :获得供应商名采购人的分组列表 
	 * Create Date: 2010-11-5下午02:39:31 by guoyr  Modified Date: 2010-11-5下午02:39:31 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ConcernGroup> getConcerngListByGroupType(String groupType){
		return concernGroupManager.getConcerngListByGroupType(groupType);
	}
	
	/** 
	 * Description :获得分组的最大排序号  
	 * Create Date: 2010-11-5下午08:23:38 by guoyr  Modified Date: 2010-11-5下午08:23:38 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer getMaxConcernGroupSort(String groupType){
		return concernGroupManager.getMaxConcernGroupSort(groupType);
	}
}
