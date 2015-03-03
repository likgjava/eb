package com.gpcsoft.epp.oppugnrequisition.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnRequisition;

public interface OppugnRequisitionService extends BaseGenericService<OppugnRequisition> {
	
	/** 
	 * Description :   保存质疑
	 * Create Date: 2010-12-1上午10:29:31 by yangx  Modified Date: 2010-12-1上午10:29:31 by yangx
	 * @param   projectId:项目Id,oppugnRequisition:质疑对象
	 * @return  
	 * @Exception   
	 */
	public void saveOppugnRequisition(String projectId,OppugnRequisition oppugnRequisition);
	
	/**
	 * 
	 * Description :  根据质疑Id获取质疑对象 
	 * Create Date: 2010-9-7上午09:08:16 by shenjianzhuang  Modified Date: 2010-9-7上午09:08:16 by shenjianzhuang
	 * @return
	 * @return  OppugnRequisition
	 * @Exception 
	 */	
	public OppugnRequisition getOppugnRequisition(String objId);
	
	/**
	 * 
	 * Description :  根据质疑Id删除质疑对象 
	 * Create Date: 2010-9-7上午09:08:16 by shenjianzhuang  Modified Date: 2010-9-7上午09:08:16 by shenjianzhuang
	 * @return
	 * @return  OppugnRequisition
	 * @Exception 
	 */	
	public OppugnRequisition deleteOppugnRequisition(String objId);
}
