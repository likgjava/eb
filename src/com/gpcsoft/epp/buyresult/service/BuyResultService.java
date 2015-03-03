package com.gpcsoft.epp.buyresult.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.buyresult.domain.BuyResult;
import com.gpcsoft.epp.project.domain.Project;

public interface BuyResultService extends BaseGenericService<BuyResult> {

	/** 
	  * Description :  根据项目ID获取对应的所有包组信息
	  * Create Date: 2010-5-21下午02:25:13 by Administrator  Modified Date: 2010-5-21下午02:25:13 by Administrator
	  * @param projectId:项目Id
	  * @return
	  * @throws Exception
	  */
	public List<Project> getSubProjectByProjectId(String projectId)throws Exception;
	
	/**
	 * 
	 * Description : 根据项目主键得到公告信息
	 * Create Date: 2010-6-25下午02:58:31 by liuke  Modified Date: 2010-6-25下午02:58:31 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Bulletin> getbidEvaluationReportByProjectId(String subProjId);
	
	/**
	 * 
	 * Description :保存成交结果与成交供应商  
	 * Create Date: 2010-6-26下午04:48:59 by lic  Modified Date: 2010-6-26下午04:48:59 by lic
	 * @param   
	 * @return  
	 * @Exception
	 */
	public BuyResult saveBuyResultAndBuyWinner(BuyResult buyResult,String suppliers,String allSupplierIds);
	
	/**
	 * 
	 * Description :根据项目主键得到成交结果 
	 * Create Date: 2010-6-26下午04:48:59 by liuke  Modified Date: 2010-6-26下午04:48:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public BuyResult getBuyResultBySubProjId(String subProjId);
	
	/** 
	 * Description :  根据项目id获取中标结果记录
	 * Create Date: 2010-6-28上午10:26:23 by wangcl  Modified Date: 2010-6-28上午10:26:23 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BuyResult> getListByProjectId(String projectId);

	
	/** 
	 * FuncName : finishBuyResult
	 * Description :  完成定标
	 * Create Date: 2011-11-16下午02:52:34 by yangx  
	 * Modified Date: 2011-11-16下午02:52:34 by yangx
	 * @param   projectId：项目Id
	 * @return  BuyResult
	 */
	public BuyResult finishBuyResult(String projectId);
	
}
