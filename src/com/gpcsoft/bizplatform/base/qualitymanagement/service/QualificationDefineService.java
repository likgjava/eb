package com.gpcsoft.bizplatform.base.qualitymanagement.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationDefine;
import com.gpcsoft.core.service.BaseGenericService;

public interface QualificationDefineService extends BaseGenericService<QualificationDefine> {

	/** 
	 * Description :  取得资质定义 根据资质分类
	 * Create Date: 2010-7-30下午02:47:57 by yucy  Modified Date: 2010-7-30下午02:47:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<QualificationDefine> getDefineByClass(Map<String, Object> param) throws Exception;

	/** 
	 * Description : 保存资质定义
	 * Create Date: 2010-7-29下午03:17:34 by guoyr  Modified Date: 2010-7-29下午03:17:34 by guoyr
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	public QualificationDefine saveQualificationDefine(QualificationDefine qualificationDefine) throws Exception ;
	
	/** 
	 * Description : 删除资质定义，如果该资质分类被机构资质信息所引用，则不充许删除 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeQualificationDefine(String objId);
}
