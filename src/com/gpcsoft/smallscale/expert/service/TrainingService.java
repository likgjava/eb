package com.gpcsoft.smallscale.expert.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.expert.domain.Training;

public interface TrainingService extends BaseGenericService<Training> {

	/** 
	 * Description : 保存培训信息
	 * Create Date: 2010-11-26下午03:29:48 by guoyr  Modified Date: 2010-11-26下午03:29:48 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Training saveTraining(Training training);
	
	/** 
	 * Description : 审核培训信息
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateTrainingAuditStatus(String objIds, String auditStatus);
}
