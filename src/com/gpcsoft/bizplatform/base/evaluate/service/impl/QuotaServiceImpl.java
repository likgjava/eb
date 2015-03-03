package com.gpcsoft.bizplatform.base.evaluate.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.evaluate.dao.QuotaDao;
import com.gpcsoft.bizplatform.base.evaluate.domain.Quota;
import com.gpcsoft.bizplatform.base.evaluate.service.QuotaService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class QuotaServiceImpl extends BaseGenericServiceImpl<Quota> implements QuotaService {

	@Autowired(required=true)  @Qualifier("quotaDaoHibernate")
	private QuotaDao quotaDaoHibernate;

	/** 
	 * Description :   根据组织机构信息(OrgInfo)取得评价指标集合
	 * Create Date: 2010-7-21下午04:08:00 by yucy  Modified Date: 2010-7-21下午04:08:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Quota> getQuotaLisstByOrgInfo(Map<String, Object> params) throws Exception{
		return quotaDaoHibernate.getQuotaLisstByOrgInfo(params);
	}

	
	/** 
	 * Description :  删除指标项(检查可删性)
	 * Create Date: 2010-7-26下午03:43:19 by yucy  Modified Date: 2010-7-26下午03:43:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public int delQuota(String[] quotaIdArray) throws Exception{
		Integer flag = 0;
		//检查可删性
		if(quotaDaoHibernate.checkDelQuota(quotaIdArray)){
			//删除
			flag = quotaDaoHibernate.delQuota(quotaIdArray);
		}
		return flag;
	}

}
