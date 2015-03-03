package com.gpcsoft.smallscale.expert.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.expert.dao.ExpertInfoApplyDao;
import com.gpcsoft.smallscale.expert.dao.ExpertInfoDao;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.smallscale.expert.domain.ExpertInfoApply;
import com.gpcsoft.smallscale.expert.enumeration.ExpertEnum;
import com.gpcsoft.smallscale.expert.service.ExpertInfoApplyService;

@Service 
public class ExpertInfoApplyServiceImpl extends BaseGenericServiceImpl<ExpertInfoApply> implements ExpertInfoApplyService {

	//@Autowired(required=true) @Qualifier("expertInfoApplyManagerImpl")
	//private ExpertInfoApplyManager expertInfoApplyManager;
	
	@Autowired(required=true)  @Qualifier("expertInfoApplyDaoHibernate")
	private ExpertInfoApplyDao expertInfoApplyDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("expertInfoDaoHibernate")
	private ExpertInfoDao expertInfoDaoHibernate;

	/** 
	 * Description :  获取专家申请列表
	 * Create Date: 2011-1-6上午11:06:15 by likg  Modified Date: 2011-1-6上午11:06:15 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ExpertInfoApply> getApplyExpertList(String expertObjId, String applyType) throws Exception {
		return expertInfoApplyDaoHibernate.getApplyExpertList(expertObjId, applyType);
	}

	/** 
	 * Description :  审核专家的申请
	 * Create Date: 2011-1-6下午02:28:46 by likg  Modified Date: 2011-1-6下午02:28:46 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditApplyExpert(Map<String, Object> params) throws Exception {
		//获取参数
		String objId = (String)params.get("objId");
		String expertId = (String)params.get("expertId");
		String auditStatus = (String)params.get("auditStatus");
		String applyType = (String)params.get("applyType");
		String opinion = (String)params.get("opinion");
		
		//同步expertInfo中的属性
		ExpertInfo expertInfo = expertInfoDaoHibernate.get(expertId);
		if(ExpertEnum.CONSULTANT.equals(applyType) && ExpertEnum.PASS_EXAM.equals(auditStatus)) {//咨询员审核通过
			expertInfo.setIsConsultant("1");
		}else if(ExpertEnum.REVIEWERS.equals(applyType) && ExpertEnum.PASS_EXAM.equals(auditStatus)) {//评审员审核通过
			expertInfo.setIsReviewers("1");
		}
		
		ExpertInfoApply expertInfoApply = expertInfoApplyDaoHibernate.get(objId);
		expertInfoApply.setAuditStatus(auditStatus);
		expertInfoApply.setVerifyTime(new Date());
		expertInfoApply.setVerifyUser(AuthenticationHelper.getCurrentUser(true));
		expertInfoApply.setOpinion(opinion);
	}

}
