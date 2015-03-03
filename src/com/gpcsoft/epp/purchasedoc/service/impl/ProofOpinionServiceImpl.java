package com.gpcsoft.epp.purchasedoc.service.impl;

import java.util.List;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.purchasedoc.dao.ProofOpinionDao;
import com.gpcsoft.epp.purchasedoc.domain.ProofOpinion;
import com.gpcsoft.epp.purchasedoc.manager.ProofOpinionManager;
import com.gpcsoft.epp.purchasedoc.service.ProofOpinionService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;

@Service 
public class ProofOpinionServiceImpl extends BaseGenericServiceImpl<ProofOpinion> implements ProofOpinionService {

	@Autowired(required=true) @Qualifier("proofOpinionManagerImpl")
	private ProofOpinionManager proofOpinionManager;

	@Autowired(required=true)  @Qualifier("proofOpinionDaoHibernate")
	private ProofOpinionDao proofOpinionDaoHibernate;
	
	@Autowired(required=false)
	private AttachmentManager attachmentManagerImpl;
	
	/**
	 * 
	 * Description :  
	 * Create Date: 2010-8-6下午01:21:57 by liuke  Modified Date: 2010-8-6下午01:21:57 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<ProofOpinion> getProofOpinionListbyProjectId(String projectId) {
		logger.debug("\nProofOpinionServiceImpl||getProofOpinionListbyProjectId\n");
		return proofOpinionDaoHibernate.getProofOpinionListbyProjectId(projectId);
	}
	/** 
	 * Description :  保存招标文件论证信息
	 * Create Date: 2010-9-6下午03:24:17 by yangx  Modified Date: 2010-9-6下午03:24:17 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ProofOpinion saveProofOpinion(ProofOpinion proofOpinion){
		proofOpinionManager.save(proofOpinion);
		attachmentManagerImpl.setIsUsed(proofOpinion.getAttachRelaId(),true);//修改附件引用状态为true
		User user = AuthenticationHelper.getCurrentUser();
		proofOpinion.setParentBizId(proofOpinion.getTenderId());
		proofOpinion.setUser(user);
		return proofOpinion;
	}
	
	/** 
	 * Description :  修改招标文件论证信息
	 * Create Date: 2010-9-6下午03:24:17 by yangx  Modified Date: 2010-9-6下午03:24:17 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ProofOpinion updateProofOpinion(ProofOpinion proofOpinion){
		return proofOpinionManager.save(proofOpinion);
	}
	
	/** 
	 * Description :  根据主键获取招标文件论证对象
	 * Create Date: 2010-9-7下午05:13:18 by yangx  Modified Date: 2010-9-7下午05:13:18 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ProofOpinion getProofOpinionByObjId(String objId){
		return proofOpinionManager.get(objId);
	}
}
