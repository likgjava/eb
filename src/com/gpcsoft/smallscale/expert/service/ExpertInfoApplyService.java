package com.gpcsoft.smallscale.expert.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.expert.domain.ExpertInfoApply;

public interface ExpertInfoApplyService extends BaseGenericService<ExpertInfoApply> {

	/** 
	 * Description :  获取专家申请列表
	 * Create Date: 2011-1-6上午11:06:15 by likg  Modified Date: 2011-1-6上午11:06:15 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<ExpertInfoApply> getApplyExpertList(String expertObjId, String applyType) throws Exception;

	/** 
	 * Description :  审核专家的申请
	 * Create Date: 2011-1-6下午02:28:46 by likg  Modified Date: 2011-1-6下午02:28:46 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void auditApplyExpert(Map<String, Object> params) throws Exception;

}
