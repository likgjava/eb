package com.gpcsoft.bizplatform.base.qualitymanagement.dao;

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationClass;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface QualificationClassDao extends BaseGenericDao<QualificationClass> {

	/** 
	 * Description : 获取父节点为null的最大的排序编号  
	 * Create Date: 2010-7-30下午04:07:44 by guoyr  Modified Date: 2010-7-30下午04:07:44 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public int getQualificationClassMaxSort();
	
	/** 
	 * Description : 查找机构资质信息中引用资质分类的个数
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getQualificationClassCountFromOrgQuality(String objId);
	
	
}
