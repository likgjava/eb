package com.gpcsoft.epp.buyresult.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;

public interface BuyWinnerDao extends BaseGenericDao<BuyWinner> {
	/**
	 * 
	 * Description :根据项目得到中标供应商列表  
	 * Create Date: 2010-8-20下午01:25:46 by liuke  Modified Date: 2010-8-20下午01:25:46 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<BuyWinner> getBuyWinnerListByProjectId(String subProjectId);
	
	
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
