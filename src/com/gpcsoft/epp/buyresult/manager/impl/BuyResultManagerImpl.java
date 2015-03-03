package com.gpcsoft.epp.buyresult.manager.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.buyresult.dao.BuyResultDao;
import com.gpcsoft.epp.buyresult.domain.BuyResult;
import com.gpcsoft.epp.buyresult.manager.BuyResultManager;

@Repository
public  class BuyResultManagerImpl extends BaseManagerImpl<BuyResult> implements BuyResultManager {

	@Autowired(required=true)  @Qualifier("buyResultDaoHibernate")
	private BuyResultDao buyResultDaoHibernate;
    	
	/**
	 * 
	 * Description :通过子项目主键得到成交结果对象  
	 * Create Date: 2010-6-26下午04:55:33 by liuke  Modified Date: 2010-6-26下午04:55:33 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public BuyResult getBuyResultBySubProjId(String subProjId) {
		QueryObject<BuyResult> queryObject = new QueryObjectBase<BuyResult>();
		queryObject.setEntityClass(BuyResult.class);
		queryObject.setQueryParam(new QueryParam("subProjId",QueryParam.OPERATOR_EQ,subProjId));
		List<BuyResult>   list = this.findByQuery(queryObject);
		return (list.size()>0)?list.get(0):null;
	}


	public List<BuyResult> getListByProjectId(String projectId) {
		return buyResultDaoHibernate.getListByProjectId(projectId);
	}



}
