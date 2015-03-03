package com.gpcsoft.bizplatform.base.qualitymanagement.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationParam;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface QualificationParamDao extends BaseGenericDao<QualificationParam> {

	/** 
	 * Description : 取得资质参数  根据资质定义
	 * Create Date: 2010-7-30下午02:44:00 by yucy  Modified Date: 2010-7-30下午02:44:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<QualificationParam> getParamByDefine(Map<String, Object> param) throws Exception;

	/** 
	 * Description : 查询资质信息详细中引用资质参数的个数 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getQualificationParamCountFromQualificationDetail(String objId);

	/** 
	 * Description : 取得资质参数  根据资质分类
	 * Create Date: 2010-7-30下午02:44:00 by yucy  Modified Date: 2010-7-30下午02:44:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<QualificationParam> getParamByClass(Map<String, Object> param) throws Exception;
}
