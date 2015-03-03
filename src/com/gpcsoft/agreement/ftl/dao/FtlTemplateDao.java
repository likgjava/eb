package com.gpcsoft.agreement.ftl.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.agreement.ftl.domain.FtlTemplate;

public interface FtlTemplateDao extends BaseGenericDao<FtlTemplate> {

	/** 
	 * Description : 验证当前用户下的模板是不是唯一的
	 * Create Date: 2010-12-2下午03:49:37 by guoyr  Modified Date: 2010-12-2下午03:49:37 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isUnique(String ftlName, String objId, String createUserId );
}
