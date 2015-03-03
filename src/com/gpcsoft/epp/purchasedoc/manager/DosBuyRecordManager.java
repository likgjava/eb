package com.gpcsoft.epp.purchasedoc.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyRecord;
import com.gpcsoft.srplatform.auth.domain.User;

public interface DosBuyRecordManager extends BaseManager<DosBuyRecord> {

	
	/**
	 * 
	 * Description :通过供应商主键判断是否购买采购文件  
	 * Create Date: 2010-5-27下午04:52:06 by liuke  Modified Date: 2010-5-27下午04:52:06 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean IsBuyPurchaseDoc(String projectId, String subprojectId ,User user);
	
	
	/**
	 * 
	 * Description :  采购文件购买记录对供应商参与招标项目的影响
	 * Create Date: 2010-8-11上午09:30:37 by shenjianzhuang  Modified Date: 2010-8-11上午09:30:37 by shenjianzhuang
	 * @return
	 * @return  boolean
	 * @Exception 
	 */
	public boolean checkDocBuyStatusToProject(String projectId, String supplierId);
	
	/** 
	 * Description :  根据项目id获取招标文件购买记录
	 * Create Date: 2010-10-19下午05:00:25 by wangcl  Modified Date: 2010-10-19下午05:00:25 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<DosBuyRecord> getByProjectId(String projectId);
	
	/** 
	 * FuncName : getDosBuyRecordBySupplierId
	 * Description :  根据供应商Id获取标书费购买记录
	 * Create Date: 2011-9-22下午11:38:18 by yangx  
	 * Modified Date: 2011-9-22下午11:38:18 by yangx
	 * @param   supplierId：供应商Id
	 * @return  DosBuyRecord
	 * @Exception   
	 */
	public List<DosBuyRecord> getDosBuyRecordBySupplierId(String supplierId,String projectId);
}
