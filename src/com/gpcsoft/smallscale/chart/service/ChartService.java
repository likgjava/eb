package com.gpcsoft.smallscale.chart.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.BaseGenericService;

public interface ChartService extends BaseGenericService<GpcBaseObject> {

	/** 
	 * Description :  获取‘月采购节省总额同比、环比图’的XML数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getSaveAmountCompareData(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取‘月采购节省总额同比、环比图’的列表数据
	 * Create Date: 2011-7-11下午08:42:41 by likg  Modified Date: 2011-7-11下午08:42:41 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getSaveAmountCompareList(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取‘月采购预算、实际额及节省额对比图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getPlanActualSaveAmountData(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取‘月采购预算、实际额及节省额对比图’的列表数据
	 * Create Date: 2011-7-11下午08:42:41 by likg  Modified Date: 2011-7-11下午08:42:41 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<BigDecimal[]> getPlanActualSaveAmountList(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取采购人或供应商产生过交易的采购品目
	 * Create Date: 2011-7-12下午03:08:10 by likg  Modified Date: 2011-7-12下午03:08:10 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getPurCategoryList(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘品目月采购额、采购量、节省额趋势图’或‘品目月销售额、销售量趋势图’的图表和列表数据
	 * Create Date: 2011-7-12下午03:51:42 by likg  Modified Date: 2011-7-12下午03:51:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getPurcategoryAmountQtySaveData(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘品目月采购额、采购量、节省额趋势图’列表数据
	 * Create Date: 2011-7-12下午03:51:42 by likg  Modified Date: 2011-7-12下午03:51:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<BigDecimal[]> getPurcategoryAmountQtySaveList(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘多品目月采购额对比图’或‘多品目月销售额对比图’的图表和列表数据
	 * Create Date: 2011-7-13上午09:47:47 by likg  Modified Date: 2011-7-13上午09:47:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getPurcategoryAmountData(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘多品目月采购量对比图’的图表和列表数据
	 * Create Date: 2011-7-13上午10:09:34 by likg  Modified Date: 2011-7-13上午10:09:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getPurcategoryQtyData(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘多品目月采购额对比图’的列表数据
	 * Create Date: 2011-7-13上午09:47:47 by likg  Modified Date: 2011-7-13上午09:47:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<BigDecimal[]> getPurcategoryAmountList(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘多品目月采购量对比图’的列表数据
	 * Create Date: 2011-7-13上午10:09:34 by likg  Modified Date: 2011-7-13上午10:09:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<BigDecimal[]> getPurcategoryQtyList(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘各品目年度采购总额分布图’或‘各品目年度销售总额分布图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getPurcategorysAmountPieData(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘各品目年度采购总额分布图’的列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getPurcategorysAmountPieList(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘各品目年度采购总量分布图或‘各品目年度销售总量分布图’’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getPurcategorysQtyPieData(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘各品目年度采购总量分布图’的列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getPurcategorysQtyPieList(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘月采购额和采购量同比、环比图’和‘月销售额和销售量同比、环比图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getAmountQtyCompareData(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘月采购额和采购量同比、环比图’的列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getAmountQtyCompareList(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘各采购方式月采购额分布比例图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getEbuymethodAmountData(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘各采购方式月采购额分布比例图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getEbuymethodAmountList(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取‘各采购方式月采购量分布比例图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getEbuymethodQtyData(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取‘各采购方式月采购量分布比例图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getEbuymethodQtyList(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘各采购方式年度采购额分布图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getEbuymethodAmountPieData(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取‘各采购方式年度采购额分布图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getEbuymethodAmountPieList(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取‘各采购方式年度采购量分布图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getEbuymethodQtyPieData(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取‘各采购方式年度采购量分布图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getEbuymethodQtyPieList(Map<String, Object> param) throws Exception;

	/**
	 * Description :获取‘时间段内各商品销售量分布图’的图表和列表数据  
	 * Create Date: 2011-7-28下午02:56:07 by zhaojf  Modified Date: 2011-7-28下午02:56:07 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	Map<String, Object> getDuringGoodsQtyPieData(Map<String, Object> param) throws Exception;

	/**
	 * Description :  获取‘时间段内各商品销售额分布图’的图表和列表数据
	 * Create Date: 2011-7-29上午10:52:49 by zhaojf  Modified Date: 2011-7-29上午10:52:49 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	Map<String, Object> getDuringGoodsAmountPieData(Map<String, Object> param) throws Exception;
	
	/**
	 * Description :  获取‘时间段内各商品销售量分布图’、‘时间段内各商品销售额分布图’列表数据
	 * Create Date: 2011-7-29上午11:32:33 by zhaojf  Modified Date: 2011-7-29上午11:32:33 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	List<Object[]> getDuringGoodsAmountAndQtyPieList(Map<String, Object> param) throws Exception;
	
	/**
	 * Description :  获取‘时间段内各商品销售额分布图’的图表和列表数据
	 * Create Date: 2011-7-29上午10:52:49 by zhaojf  Modified Date: 2011-7-29上午10:52:49 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	Map<String, Object> getGoodsProjectBiddingLineData(Map<String, Object> param) throws Exception;

	/**
	 * Description :  获取商品在项目里的报价(根据参数商品Id、供货商Id、当前用户Id)
	 * Create Date: 2011-8-5上午10:25:19 by zhaojf  Modified Date: 2011-8-5上午10:25:19 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	List<BiddingRecordDetail> getAllGoodsBiddingByProjectData(Map<String, Object> param) throws Exception;
	
	/**
	 * Description :  各季度中标情况对比图(供应商)
	 * Create Date: 2011-8-8上午10:13:36 by zhaojf  Modified Date: 2011-8-8上午10:13:36 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	Map<String, Object> gebBiddingWinnerRecordsByYearData(Map<String, Object> param) throws Exception;
}
