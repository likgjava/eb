package com.gpcsoft.epp.project.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.domain.ProjectExceptionApply;

public interface ProjectExceptionApplyDao extends BaseGenericDao<ProjectExceptionApply> {

	/** 
	 * FuncName:getProjectExceptionList
	 * Description:获取异常申请列表
	 * @param   page:分页对象,queryObject:组装对象
	 * @return  Page<ProjectExceptionApply>
	 * @author yangx
	 * @Create Date: 2010-10-12上午10:55:07 
	 * @Modifier yangx   
	 * @Modified Date: 2010-10-12上午10:55:07     
	 */
	public Page<ProjectExceptionApply> getProjectExceptionList(Page page,QueryObject queryObject);
}
