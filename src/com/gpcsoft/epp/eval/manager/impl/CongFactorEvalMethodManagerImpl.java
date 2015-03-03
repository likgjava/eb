package com.gpcsoft.epp.eval.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.eval.dao.CongFactorEvalMethodDao;
import com.gpcsoft.epp.eval.domain.CongFactorEvalMethod;
import com.gpcsoft.epp.eval.manager.CongFactorEvalMethodManager;

@Repository
public class CongFactorEvalMethodManagerImpl extends BaseManagerImpl<CongFactorEvalMethod> implements CongFactorEvalMethodManager {

	@Autowired(required=true)  @Qualifier("congFactorEvalMethodDaoHibernate")
	private CongFactorEvalMethodDao congFactorEvalMethodDaoHibernate;

}
