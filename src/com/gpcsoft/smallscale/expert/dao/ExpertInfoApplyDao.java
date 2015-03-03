package com.gpcsoft.smallscale.expert.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.expert.domain.ExpertInfoApply;

public interface ExpertInfoApplyDao extends BaseGenericDao<ExpertInfoApply> {

	/** 
	 * Description :  获取专家申请列表
	 * Create Date: 2011-1-6上午11:06:15 by likg  Modified Date: 2011-1-6上午11:06:15 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<ExpertInfoApply> getApplyExpertList(String expertObjId, String applyType) throws Exception;

}
