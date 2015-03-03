package com.gpcsoft.epp.projreview.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;

public interface GenviewDefineManager extends BaseManager<GenviewDefine> {

/**
 * 
 * Description :根据项目删除开标一览表指标  
 * Create Date: 2010-10-9下午01:22:46 by liuke  Modified Date: 2010-10-9下午01:22:46 by liuke
 * @param   
 * @return  
 * @Exception
 */
public void reomveGenviewDefine(String projectId);

/**
 * 
 * Description :根据指标删除开标一览表指标  
 * Create Date: 2010-10-9下午01:22:46 by liuke  Modified Date: 2010-10-9下午01:22:46 by liuke
 * @param   
 * @return  
 * @Exception
 */
public void removeGenviewDefineByCongruousFactorId(String factorId);
}
