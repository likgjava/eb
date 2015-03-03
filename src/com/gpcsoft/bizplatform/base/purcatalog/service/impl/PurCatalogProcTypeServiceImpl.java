package com.gpcsoft.bizplatform.base.purcatalog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.purcatalog.dao.PurCatalogProcTypeDao;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogProcType;
import com.gpcsoft.bizplatform.base.purcatalog.service.PurCatalogProcTypeService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class PurCatalogProcTypeServiceImpl extends BaseGenericServiceImpl<PurCatalogProcType> implements PurCatalogProcTypeService {

	@Autowired(required=true) @Qualifier("purCatalogProcTypeDaoHibernate")
	private PurCatalogProcTypeDao purCatalogProcTypeDaoHibernate;

	/**
	 * Description : 删除采购方式明细对象 Create Date: 2010-8-10下午04:09:34 by yucy Modified
	 * Date: 2010-8-10下午04:09:34 by yucy
	 * @param
	 * @return
	 * @Exception
	 */
	public Boolean deletePurCatalogProcType(String objId) throws Exception {
		 purCatalogProcTypeDaoHibernate.remove(objId, PurCatalogProcType.class);//写入service以待验证是否能删除
		 return true;
	}

}
