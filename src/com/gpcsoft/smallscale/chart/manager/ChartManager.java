package com.gpcsoft.smallscale.chart.manager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;

public interface ChartManager extends BaseManager<GpcBaseObject> {

	/** 
	 * Description :  获取每月的节省金额
	 * Create Date: 2011-7-11上午10:01:30 by likg  Modified Date: 2011-7-11上午10:01:30 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getSaveAmountByMonth(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取每月的预算额、实际额、节省额、采购量
	 * Create Date: 2011-7-12上午11:22:50 by likg  Modified Date: 2011-7-12上午11:22:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<BigDecimal[]> getPlanActualSaveQtyByMonth(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取采购人或供应商产生过交易的采购品目
	 * Create Date: 2011-7-12下午03:08:10 by likg  Modified Date: 2011-7-12下午03:08:10 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getPurCategoryList(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取指定品目的每月的采购额、采购量、节省额
	 * Create Date: 2011-7-12下午03:55:51 by likg  Modified Date: 2011-7-12下午03:55:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<BigDecimal[]> getPurcategoryAmountQtySaveByMonth(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取所有品目的年度（采购人：采购额、采购量）（供应商：销售额、销售量）
	 * Create Date: 2011-7-12下午03:55:51 by likg  Modified Date: 2011-7-12下午03:55:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getAllPurcategoryAmountQtyByYear(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取指定采购方式每月的采购额、采购量
	 * Create Date: 2011-7-12下午03:55:51 by likg  Modified Date: 2011-7-12下午03:55:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<BigDecimal[]> getEbuymethodAmountQtyByMonth(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取各采购方式的年度采购额、采购量
	 * Create Date: 2011-7-12下午03:55:51 by likg  Modified Date: 2011-7-12下午03:55:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getAllEbuymethodAmountQtyByYear(Map<String, Object> param) throws Exception;

	/**
	 * Description :  获取所有商品时间段内（供应商：销售额、销售量）
	 * Create Date: 2011-7-28下午03:07:11 by zhaojf  Modified Date: 2011-7-28下午03:07:11 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	List<Object[]> getAllDurGoodsAmountQtyByYear(Map<String, Object> param) throws Exception;
	
	/**
	 * Description :  获取商品在项目里的报价(根据参数商品Id、供货商Id、当前用户Id)
	 * Create Date: 2011-8-4上午11:30:54 by zhaojf  Modified Date: 2011-8-4上午11:30:54 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	List<BiddingRecordDetail> getAllGoodsBiddingByProjectData(Map<String, Object> param) throws Exception;
	
	/**
	 * Description :  获取一段时间段内的中标情况数据(供应商)
	 * Create Date: 2011-8-8上午10:13:36 by zhaojf  Modified Date: 2011-8-8上午10:13:36 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	List<BuyWinner> getAllBiddingWinnerRecordsByYearData(Map<String, Object> param) throws Exception;
}
