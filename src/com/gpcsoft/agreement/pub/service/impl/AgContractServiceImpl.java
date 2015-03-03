package com.gpcsoft.agreement.pub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.order.dao.OrderDao;
import com.gpcsoft.agreement.pub.domain.AgContract;
import com.gpcsoft.agreement.pub.manager.AgContractManager;
import com.gpcsoft.agreement.pub.service.AgContractService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class AgContractServiceImpl extends BaseGenericServiceImpl<AgContract> implements AgContractService {

	@Autowired(required=true) @Qualifier("agContractManagerImpl")
	private AgContractManager agContractManager;

	@Autowired(required=true)  @Qualifier("orderDaoHibernate")
	private OrderDao orderDaoHibernate;
	
	//保存合同
	public AgContract saveContract(AgContract baseobject)throws Exception {
		return agContractManager.saveContract(baseobject);
	}

	//确认合同
	public AgContract auditContract(AgContract baseobject, String sureRole) throws Exception {
		return agContractManager.auditContract(baseobject, sureRole);
	}
	
	//作废合同
	public AgContract releaseContract(AgContract baseobject, String sureRole)
			throws Exception {
		return agContractManager.releaseContract(baseobject, sureRole);
	}
	
	@Override
	protected void onBeforeRemove(String id) {
		//将订单中的合同id清空
		orderDaoHibernate.saveOrderContract("",id);
	}
	
}
