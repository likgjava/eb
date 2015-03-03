package com.gpcsoft.epp.buyresult.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;

public interface BuyWinnerService extends BaseGenericService<BuyWinner> {
	
	/** 
	 * Description :  获取某标段的中标结果集合
	 * Create Date: 2010-6-28下午12:58:08 by wangcl  Modified Date: 2010-6-28下午12:58:08 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BuyWinner> getListByResultId(String resultId);

	/**
	 * 
	 * Description : 根据项目得到中标供应商列表
	 * Create Date: 2010-8-20下午01:23:28 by liuke  Modified Date: 2010-8-20下午01:23:28 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<BuyWinner> getBuyWinnerBySubProjectId(String subProjectId);
	
	/**
	 * 
	 * Description : 根据项目获得中标供应商列表 
	 * Create Date: 2010-8-25下午05:44:15 by liuke  Modified Date: 2010-8-25下午05:44:15 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<BuyWinner> getBuyWinnerByProjectId(String projectId);
	
	/** 
	 * Description : 根据主键获取成交供应商清单
	 * Create Date: 2010-9-19下午04:42:24 by yangx  Modified Date: 2010-9-19下午04:42:24 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public BuyWinner getBuyWinnerByObjId(String objId);
	/**
	 * FuncName: getBuyWinnerList
	 * Description :  根据项目Id得到中标供应商
	 * @param 
	 * @param projectId
	 * @return List<BuyWinner>
	 * @author: shenjz
	 * @Create Date:2011-3-30  下午05:15:16
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-30  下午05:15:16
	 */
	public List<BuyWinner> getBuyWinnerList(String projectId);

	/**
	 * 
	 * Description : 根据项目Id获得中标供应商
	 * Create Date: 2011-8-31下午05:44:15 by caojz  Modified Date: 2011-8-31下午05:44:15 by caojz
	 * @param   
	 * @return  
	 * @Exception
	 */
	 public List<BuyWinner> getBuyWinnerByProjId(String projectId);

	/**
	 * FuncName: getWinnerQuotesum
	 * Description :  根据项目Id得到中标供应商对包组的报价
	 * @param 
	 * @param projectId
	 * @return List<BuyWinner>
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-11 10:09
	 */
	public List getWinnerQuotesum(String projectId);
	
}
