package com.gpcsoft.bizplatform.base.qualitymanagement.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationParam;
import com.gpcsoft.core.service.BaseGenericService;

public interface QualificationParamService extends BaseGenericService<QualificationParam> {

	/** 
	 * Description : 取得资质参数  根据资质定义
	 * Create Date: 2010-7-30下午02:44:00 by yucy  Modified Date: 2010-7-30下午02:44:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<QualificationParam> getParamByDefine(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description : 取得资质参数  根据资质分类
	 * Create Date: 2010-7-30下午02:44:00 by yucy  Modified Date: 2010-7-30下午02:44:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<QualificationParam> getParamByClass(Map<String, Object> param) throws Exception;
	

	/** 
	 * Description : 保存资质参数
	 * Create Date: 2010-7-29下午03:17:34 by guoyr  Modified Date: 2010-7-29下午03:17:34 by guoyr
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	public QualificationParam saveQualificationParam(QualificationParam qualificationParam) throws Exception ;
	
	/** 
	 * Description : 删除资质参数，如果该资质参数被资质信息详细所引用，则不充许删除 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeQualificationParam(String objId);

}
