package com.gpcsoft.bizplatform.base.qualitymanagement.service;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationClass;
import com.gpcsoft.core.service.BaseGenericService;

public interface QualificationClassService extends BaseGenericService<QualificationClass> {

	/** 
	 * Description : 保存资质分类 
	 * Create Date: 2010-7-29下午03:17:34 by guoyr  Modified Date: 2010-7-29下午03:17:34 by guoyr
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	public QualificationClass saveQualificationClass(QualificationClass qualificationClass) throws Exception ;
	
	/** 
	 * Description : 删除资质分类，如果该资质分类被机构资质信息所引用，则不充许删除 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeQualificationClass(String objId);
	
}
