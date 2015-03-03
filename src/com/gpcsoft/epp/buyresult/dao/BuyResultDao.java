package com.gpcsoft.epp.buyresult.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.buyresult.domain.BuyResult;

public interface BuyResultDao extends BaseGenericDao<BuyResult> {

	
	/**
	 * 
	 * Description :保存成交结果对象  
	 * Create Date: 2010-6-2下午04:33:30 by liuke  Modified Date: 2010-6-2下午04:33:30 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveBuyResult(BuyResult buyResult);
	
	/**
	 * 
	 * FuncName: getListByProjectId
	 * Description :根据项目查询定标结果对象  
	 * @param 
	 * @param projectId
	 * @return List<BuyResult>
	 * @author: liuke
	 * @Create Date:2010-12-31  下午01:42:26
	 * @Modifier: liuke
	 * @Modified Date:2010-12-31  下午01:42:26
	 */
	public List<BuyResult> getListByProjectId(String projectId);
}
