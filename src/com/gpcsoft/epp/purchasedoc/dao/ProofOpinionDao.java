package com.gpcsoft.epp.purchasedoc.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.purchasedoc.domain.ProofOpinion;

public interface ProofOpinionDao extends BaseGenericDao<ProofOpinion> {
    /**
     * 
     * Description :根据项目得到招标文件论证对象列表  
     * Create Date: 2010-8-6下午01:23:45 by lic  Modified Date: 2010-8-6下午01:23:45 by lic
     * @param   
     * @return  
     * @Exception
     */
	public List<ProofOpinion> getProofOpinionListbyProjectId(String projectId);
}
