package com.gpcsoft.bizplatform.base.qualitymanagement.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationDefine;

public interface QualificationDefineDao extends BaseGenericDao<QualificationDefine> {

	/** 
	 * Description :  取得资质定义 根据资质分类
	 * Create Date: 2010-7-30下午02:47:57 by yucy  Modified Date: 2010-7-30下午02:47:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<QualificationDefine> getDefineByClass(Map<String, Object> param);

	/** 
	 * Description : 查找机构资质信息中引秀用质定义的个数 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getQualificationDefineCountFromOrgQuality(String objId);
}
