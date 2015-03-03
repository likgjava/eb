package com.gpcsoft.epp.purchasedoc.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.purchasedoc.dao.ProofOpinionDao;
import com.gpcsoft.epp.purchasedoc.domain.ProofOpinion;

import org.springframework.stereotype.Repository;

@Repository
public class ProofOpinionDaoHibernate extends BaseGenericDaoHibernate<ProofOpinion> implements ProofOpinionDao {

	  /**
     * 
     * Description :根据项目得到招标文件论证对象列表  
     * Create Date: 2010-8-6下午01:23:45 by liuke  Modified Date: 2010-8-6下午01:23:45 by liuke
     * @param   
     * @return  
     * @Exception
     */
	public List<ProofOpinion> getProofOpinionListbyProjectId(String projectId) {
		List<ProofOpinion> list = this.findbyHql("from ProofOpinion po where po.tenderId =?", projectId);
		return list;
	}

}
