package com.gpcsoft.epp.oppugnrequisition.manager.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.oppugnrequisition.dao.OppugnRequisitionDao;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnRequisition;
import com.gpcsoft.epp.oppugnrequisition.manager.OppugnRequisitionManager;

@Repository
public class OppugnRequisitionManagerImpl extends BaseManagerImpl<OppugnRequisition> implements OppugnRequisitionManager {

	@Autowired(required=true)  @Qualifier("oppugnRequisitionDaoHibernate")
	private OppugnRequisitionDao oppugnRequisitionDaoHibernate;

	/**
	 * 保存质疑
	 */
	public void saveOppugnRequisition(OppugnRequisition oppugnRequisition) {
		oppugnRequisitionDaoHibernate.save(oppugnRequisition);
	}
	
	/**
	 * 根据查询条件获取质疑
	 */
	public OppugnRequisition getOppugnRequisitionByQueryObject(
			QueryObject queryObject) {
		List list = oppugnRequisitionDaoHibernate.findByQuery(queryObject);
		if(null != list && !list.isEmpty()){
			return (OppugnRequisition)list.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 保存质疑答复
	 */
	public void saveOppugnReply(OppugnReply oppugnReply) {
		oppugnRequisitionDaoHibernate.saveOppugnReply(oppugnReply);
	}

}
