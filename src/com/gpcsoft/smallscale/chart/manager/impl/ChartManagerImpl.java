package com.gpcsoft.smallscale.chart.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.smallscale.chart.dao.ChartDao;
import com.gpcsoft.smallscale.chart.manager.ChartManager;

@Repository
public class ChartManagerImpl extends BaseManagerImpl<GpcBaseObject> implements ChartManager {

	@Autowired(required=true)  @Qualifier("chartDaoHibernate")
	private ChartDao chartDaoHibernate;

	/** 
	 * Description :  获取每月的节省金额
	 * Create Date: 2011-7-11上午10:01:30 by likg  Modified Date: 2011-7-11上午10:01:30 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getSaveAmountByMonth(Map<String, Object> param) throws Exception {
		String year1 = (String) param.get("year1"); //年份1
		String year2 = (String) param.get("year2"); //年份1
		param.put("year", year1);
		List<Object[]> saveAmountList1 = chartDaoHibernate.getSaveAmountByMonth(param);
		param.put("year", year2);
		List<Object[]> saveAmountList2 = chartDaoHibernate.getSaveAmountByMonth(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<BigDecimal> list1 = new ArrayList<BigDecimal>();
		List<BigDecimal> list2 = new ArrayList<BigDecimal>();
		for(int i=0; i<12; i++) {
			boolean hasData = false;
			for(Object[] objs : saveAmountList1) {
				if(Integer.parseInt(objs[0].toString()) == i+1) {
					list1.add((BigDecimal) objs[1]);
					hasData = true;
					break ;
				}
			}
			if(!hasData) { list1.add(null); }
			
			hasData = false;
			for(Object[] objs : saveAmountList2) {
				if(Integer.parseInt(objs[0].toString()) == i+1) {
					list2.add((BigDecimal) objs[1]);
					hasData = true;
					break ;
				}
			}
			if(!hasData) { list2.add(null); }
		}
		result.put("list1", list1);
		result.put("list2", list2);
		
		return result;
	}

	/** 
	 * Description :  获取每月的预算额、实际额、节省额、采购量
	 * Create Date: 2011-7-12上午11:22:50 by likg  Modified Date: 2011-7-12上午11:22:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BigDecimal[]> getPlanActualSaveQtyByMonth(Map<String, Object> param) throws Exception {
		List<Object[]> planActualSaveAmountList = chartDaoHibernate.getPlanActualSaveQtyByMonth(param);
		
		List<BigDecimal[]> amountList = new ArrayList<BigDecimal[]>();
		for(int i=0; i<12; i++) {
			boolean hasData = false;
			BigDecimal[] amounts = new BigDecimal[4];
			for(Object[] objs : planActualSaveAmountList) {
				if(Integer.parseInt(objs[0].toString()) == i+1) {
					amounts[0] = (BigDecimal) objs[1];
					amounts[1] = (BigDecimal) objs[2];
					amounts[2] = (BigDecimal) objs[3];
					amounts[3] = (BigDecimal) objs[4];
					hasData = true;
					break ;
				}
			}
			if(!hasData) {
				amounts[0] = null;
				amounts[1] = null;
				amounts[2] = null;
				amounts[3] = null;
			}
			amountList.add(amounts);
		}
		
		return amountList;
	}

	/** 
	 * Description :  获取采购人或供应商产生过交易的采购品目
	 * Create Date: 2011-7-12下午03:08:10 by likg  Modified Date: 2011-7-12下午03:08:10 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getPurCategoryList(Map<String, Object> param) throws Exception {
		return chartDaoHibernate.getPurCategoryList(param);
	}

	/** 
	 * Description :  获取指定品目的每月的（采购人：采购额、采购量、节省额）（供应商：销售额、销售量）
	 * Create Date: 2011-7-12下午03:55:51 by likg  Modified Date: 2011-7-12下午03:55:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BigDecimal[]> getPurcategoryAmountQtySaveByMonth(Map<String, Object> param) throws Exception {
		List<Object[]> purcategoryAmountQtySaveList = chartDaoHibernate.getPurcategoryAmountQtySaveByMonth(param);
		
		List<BigDecimal[]> aqsList = new ArrayList<BigDecimal[]>();
		for(int i=0; i<12; i++) {
			boolean hasData = false;
			BigDecimal[] aqs = new BigDecimal[3];
			for(Object[] objs : purcategoryAmountQtySaveList) {
				if(Integer.parseInt(objs[0].toString()) == i+1) {
					aqs[0] = (BigDecimal) objs[1];
					aqs[1] = (BigDecimal) objs[2];
					aqs[2] = (BigDecimal) objs[3];
					hasData = true;
					break ;
				}
			}
			if(!hasData) {
				aqs[0] = null;
				aqs[1] = null;
				aqs[2] = null;
			}
			aqsList.add(aqs);
		}
		
		return aqsList;
	}

	/** 
	 * Description :  获取所有品目的年度（采购人：采购额、采购量）（供应商：销售额、销售量）
	 * Create Date: 2011-7-12下午03:55:51 by likg  Modified Date: 2011-7-12下午03:55:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getAllPurcategoryAmountQtyByYear(Map<String, Object> param) throws Exception {
		return chartDaoHibernate.getAllPurcategoryAmountQtyByYear(param);
	}

	/** 
	 * Description :  获取指定采购方式每月的采购额、采购量
	 * Create Date: 2011-7-12下午03:55:51 by likg  Modified Date: 2011-7-12下午03:55:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BigDecimal[]> getEbuymethodAmountQtyByMonth(Map<String, Object> param) throws Exception {
		List<Object[]> ebuymethodAmountQtyList = chartDaoHibernate.getEbuymethodAmountQtyByMonth(param);
		
		List<BigDecimal[]> eaqList = new ArrayList<BigDecimal[]>();
		for(int i=0; i<12; i++) {
			boolean hasData = false;
			BigDecimal[] eaq = new BigDecimal[3];
			for(Object[] objs : ebuymethodAmountQtyList) {
				if(Integer.parseInt(objs[0].toString()) == i+1) {
					eaq[0] = (BigDecimal) objs[1];
					eaq[1] = (BigDecimal) objs[2];
					hasData = true;
					break ;
				}
			}
			if(!hasData) {
				eaq[0] = null;
				eaq[1] = null;
			}
			eaqList.add(eaq);
		}
		
		return eaqList;
	}

	/** 
	 * Description :  获取各采购方式的年度采购额、采购量
	 * Create Date: 2011-7-12下午03:55:51 by likg  Modified Date: 2011-7-12下午03:55:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getAllEbuymethodAmountQtyByYear(Map<String, Object> param) throws Exception {
		List<Object[]> ebuymethodAmountQtyList = chartDaoHibernate.getAllEbuymethodAmountQtyByYear(param);
		
		List<Object[]> eaqList = new ArrayList<Object[]>();
		for(int i=0; i<3; i++) {
			boolean hasData = false;
			Object[] eaq = new Object[3];
			for(Object[] objs : ebuymethodAmountQtyList) {
				if(Integer.parseInt(objs[0].toString()) == i) {
					eaq[0] = objs[0];
					eaq[1] = objs[1];
					eaq[2] = objs[2];
					hasData = true;
					break ;
				}
			}
			if(!hasData) {
				eaq[0] = i;
				eaq[1] = new BigDecimal(0);
				eaq[2] = new BigDecimal(0);
			}
			eaqList.add(eaq);
		}
		
		return eaqList;
	}

	/**
	 * Description :  获取所有商品时间段内（供应商：销售额、销售量）
	 * Create Date: 2011-7-28下午03:07:11 by zhaojf  Modified Date: 2011-7-28下午03:07:11 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Object[]> getAllDurGoodsAmountQtyByYear(Map<String, Object> param) throws Exception {
		return chartDaoHibernate.getAllDurGoodsAmountQtyByYear(param);
	}

	/**
	 * 获取商品在项目里的报价(根据参数商品Id、供货商Id、当前用户Id)
	 */
	public List<BiddingRecordDetail> getAllGoodsBiddingByProjectData(Map<String, Object> param) throws Exception {
		return chartDaoHibernate.getAllGoodsBiddingByProjectData(param);
	}

	/**
	 * 各季度中标情况对比图(供应商)
	 */
	public List<BuyWinner> getAllBiddingWinnerRecordsByYearData(Map<String, Object> param) throws Exception {
		return chartDaoHibernate.getAllBiddingWinnerRecordsByYearData(param);
	}

}
