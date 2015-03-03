package com.gpcsoft.epp.buyresult.service.impl;

import java.util.List;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.buyresult.dao.BuyWinnerDao;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.manager.BuyWinnerManager;
import com.gpcsoft.epp.buyresult.service.BuyWinnerService;

@Service 
public class BuyWinnerServiceImpl extends BaseGenericServiceImpl<BuyWinner> implements BuyWinnerService {

	@Autowired(required=true) @Qualifier("buyWinnerManagerImpl")
	private BuyWinnerManager buyWinnerManager;

	@Autowired(required=true)  @Qualifier("buyWinnerDaoHibernate")
	private BuyWinnerDao buyWinnerDaoHibernate;
	
	public List<BuyWinner> getListByResultId(String resultId) {
		logger.debug("\nes BuyWinnerServiceImpl||getListByResultId\n");
		return buyWinnerManager.getListByResultId(resultId);
	}

	/**
	 * 
	 * Description : 根据项目得到中标供应商列表
	 * Create Date: 2010-8-20下午01:23:28 by liuke  Modified Date: 2010-8-20下午01:23:28 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<BuyWinner> getBuyWinnerBySubProjectId(String subProjectId) {
		logger.debug("\nes BuyWinnerServiceImpl||getBuyWinnerBySubProjectId\n");
		return buyWinnerDaoHibernate.getBuyWinnerListByProjectId(subProjectId);
	}

	/**
	 * 
	 * Description : 根据项目获得中标供应商列表 
	 * Create Date: 2010-8-25下午05:44:15 by liuke  Modified Date: 2010-8-25下午05:44:15 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<BuyWinner> getBuyWinnerByProjectId(String projectId) {
		logger.debug("\nes BuyWinnerServiceImpl||getBuyWinnerByProjectId\n");
		return buyWinnerDaoHibernate.getBuyWinnerByProjectId(projectId);
	}
	/** 
	 * Description : 根据主键获取成交供应商清单
	 * Create Date: 2010-9-19下午04:42:24 by yangx  Modified Date: 2010-9-19下午04:42:24 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public BuyWinner getBuyWinnerByObjId(String objId){
		logger.debug("\nes BuyWinnerServiceImpl||getBuyWinnerByObjId\n");
		return buyWinnerManager.get(objId);
	}
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
	public List<BuyWinner> getBuyWinnerList(String projectId){
		return buyWinnerDaoHibernate.getBuyWinnerList(projectId);
	}

	/**
	 * FuncName: getWinnerQuotesum
	 * Description :  根据项目Id得到中标供应商对包组的报价
	 * @param 
	 * @param projectId
	 * @return List<BuyWinner>
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-11 10:09
	 */
	public List getWinnerQuotesum(String projectId) {
		List list = buyWinnerDaoHibernate.getWinnerQuotesum(projectId);
		
		JSONArray JSONArray = new JSONArray();
		for(Object obj :list){
			Object[] objArry = (Object[]) obj;
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("subProjId", objArry[0]);
			JSONObject.put("sellId", objArry[1]);
			JSONObject.put("sellName", objArry[2]);
			JSONObject.put("quoteSum", objArry[3]);
			
			JSONArray.add(JSONObject);
		}
		return JSONArray;
	}

	/**
	 * 
	 * Description : 根据项目Id获得中标供应商
	 * Create Date: 2011-8-31下午05:44:15 by caojz  Modified Date: 2011-8-31下午05:44:15 by caojz
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<BuyWinner> getBuyWinnerByProjId(String projectId) {
		
		return buyWinnerDaoHibernate.getBuyWinnerByProjId(projectId);
	}
	
	
}
