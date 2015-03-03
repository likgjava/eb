package com.gpcsoft.bizplatform.base.industry.service;
import java.util.List;

import com.gpcsoft.bizplatform.base.industry.domain.Industry;
import com.gpcsoft.core.service.BaseGenericService;

public interface IndustryService extends BaseGenericService<Industry> {
	
	/** 
	 * Description :  保存行业参数，并生成拼音缩写 
	 * Create Date: 2010-11-16下午01:34:01 by liangxj  Modified Date: 2010-11-16下午01:34:01 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Industry saveIndustry(Industry industry);
	
	
	/** 
	 * Description :  删除行业，如果该行业被引用，则不充许删除
	 * Create Date: 2010-11-16下午01:34:10 by liangxj  Modified Date: 2010-11-16下午01:34:10 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeIndustry(String objId) throws Exception;
	
	/** 
	 * Description :  根据级别，获得行业
	 * Create Date: 2010-11-16下午04:04:21 by liangxj  Modified Date: 2010-11-16下午04:04:21 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Industry> getIndustryByLevel(Short level) throws Exception;
}
