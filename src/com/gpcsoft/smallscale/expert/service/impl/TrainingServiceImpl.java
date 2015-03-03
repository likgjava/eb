package com.gpcsoft.smallscale.expert.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.smallscale.expert.dao.TrainingDao;
import com.gpcsoft.smallscale.expert.domain.Training;
import com.gpcsoft.smallscale.expert.manager.TrainingManager;
import com.gpcsoft.smallscale.expert.service.TrainingService;

@Service 
public class TrainingServiceImpl extends BaseGenericServiceImpl<Training> implements TrainingService {

	@Autowired(required=true) @Qualifier("trainingManagerImpl")
	private TrainingManager trainingManager;
	
	@Autowired(required=true)  @Qualifier("trainingDaoHibernate")
	private TrainingDao trainingDaoHibernate;

	/** 
	 * Description : 保存培训信息
	 * Create Date: 2010-11-26下午03:29:48 by guoyr  Modified Date: 2010-11-26下午03:29:48 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Training saveTraining(Training training){
		
		return trainingManager.save(training);
	}
	
	/** 
	 * Description : 审核培训信息
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateTrainingAuditStatus(String objIds, String auditStatus){
		return trainingDaoHibernate.updateTrainingAuditStatus(objIds, auditStatus);
	}
}
