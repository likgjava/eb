package com.gpcsoft.bizplatform.organization.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.dao.IllegalRecDao;
import com.gpcsoft.bizplatform.organization.domain.IllegalRec;
import com.gpcsoft.bizplatform.organization.manager.IllegalRecManager;
import com.gpcsoft.bizplatform.organization.service.IllegalRecService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class IllegalRecServiceImpl extends BaseGenericServiceImpl<IllegalRec> implements IllegalRecService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("illegalRecManagerImpl")
	private IllegalRecManager illegalRecManager;
	
	@Autowired(required=true) @Qualifier("illegalRecDaoHibernate")
	private IllegalRecDao illegalRecDao;

	
	/** 
	 * Description :  获取违法信息
	 * Create Date: 2011-7-13上午12:35:44 by yucy  Modified Date: 2011-7-13上午12:35:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<IllegalRec> getIllegalRec(Map<String, Object> param) throws Exception {
		return illegalRecDao.getIllegalRec(param);
	}

}
