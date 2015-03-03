package com.gpcsoft.smallscale.chart.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.manager.PurCategoryManager;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.manager.GoodsManager;
import com.gpcsoft.smallscale.chart.manager.ChartManager;
import com.gpcsoft.smallscale.chart.service.ChartService;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Service 
public class ChartServiceImpl extends BaseGenericServiceImpl<GpcBaseObject> implements ChartService {

	@Autowired(required=true) @Qualifier("chartManagerImpl")
	private ChartManager chartManager;
	
	@Autowired(required=true) @Qualifier("purCategoryManagerImpl")
	private PurCategoryManager purCategoryManager;
	
	@Autowired(required=true) @Qualifier("goodsManagerImpl")
	private GoodsManager goodsManager;
	
	/** 
	 * Description :  获取通用的FusionCharts XML文件中<chart></chart>标签的属性参数
	 * Create Date: 2011-7-15下午02:53:44 by likg  Modified Date: 2011-7-15下午02:53:44 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private StringBuilder getCommonChartParams(String caption, String subCaption, String showValues) {
		StringBuilder params = new StringBuilder();
		params.append(" baseFont='Arial' baseFontSize='12' decimals='0'");
		params.append(" exportEnabled='1' exportAtClient='0' exportAction='download' exportDialogMessage='正在导出，请稍候...' exportHandler='FCExporter' exportShowMenuItem='1' exportFormats='JPG=导出为 JPG 图片|PNG=导出为 PNG 图片|PDF=导出为 PDF 文件'");
		params.append(" formatNumberScale='"+SysInfo.getInstance().getChartFormatNumberScale()+"'");
		params.append(" showValues='"+showValues+"'");
		params.append(" caption='"+caption+"'");
		if(StringUtils.hasLength(subCaption)) {params.append(" subCaption='"+subCaption+"'");}
		params.append(" exportFileName='"+subCaption+caption+"' ");
		
		return params;
	}
	/** 
	 * Description :  获取‘月采购节省总额同比、环比图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSaveAmountCompareData(Map<String, Object> param) throws Exception {
		String year1 = (String) param.get("year1"); //年份1
		String year2 = (String) param.get("year2"); //年份1
		
		Map<String, Object> result = chartManager.getSaveAmountByMonth(param);
		List<BigDecimal> list1 = (List<BigDecimal>) result.get("list1");
		List<BigDecimal> list2 = (List<BigDecimal>) result.get("list2");
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<?xml version='1.0' encoding='UTF-8'?>");
		xml.append("<chart "+this.getCommonChartParams("月采购节省总额同比、环比图", year1+" VS "+year2, "0")+" numberPrefix='￥' >");
		xml.append(this.getChartCategoriesMonth());
		xml.append("<dataset seriesName='"+year1+"年' showValues='0'>");
		for(BigDecimal str : list1) { xml.append("<set value='"+str+"' />"); }
		xml.append("</dataset>");
		xml.append("<dataset seriesName='"+year2+"年' showValues='0'>");
		for(BigDecimal str : list2) { xml.append("<set value='"+str+"' />"); }
		xml.append("</dataset>");
		xml.append("</chart>");
		result.put("chartXml", xml);
		
		return result;
	}
	
	/** 
	 * Description :  获取‘月采购节省总额同比、环比图’的列表数据
	 * Create Date: 2011-7-11下午08:42:41 by likg  Modified Date: 2011-7-11下午08:42:41 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getSaveAmountCompareList(Map<String, Object> param) throws Exception {
		return chartManager.getSaveAmountByMonth(param);
	}
	
	/** 
	 * Description :  获取‘月采购预算、实际额及节省额对比图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getPlanActualSaveAmountData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String year = (String) param.get("year"); //年份
		
		List<BigDecimal[]> amountList = chartManager.getPlanActualSaveQtyByMonth(param);
		result.put("amountList", amountList);
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart "+this.getCommonChartParams("月采购预算、实际额及节省额对比图", year+"年", "0")+" numberPrefix='￥' >");
		xml.append(this.getChartCategoriesMonth());
		xml.append("<dataset seriesName='预算'>");
		for(BigDecimal[] amounts : amountList) { xml.append("<set value='"+amounts[0]+"' />"); }
		xml.append("</dataset>");
		xml.append("<dataset seriesName='实际' renderAs='Line'>");
		for(BigDecimal[] amounts : amountList) { xml.append("<set value='"+amounts[1]+"' />"); }
		xml.append("</dataset>");
		xml.append("<dataset seriesName='节省金额' renderAs='Area'>");
		for(BigDecimal[] amounts : amountList) { xml.append("<set value='"+amounts[2]+"' />"); }
		xml.append("</dataset>");
		xml.append("</chart>");
		result.put("chartXml", xml);
		
		return result;
	}
	
	/** 
	 * Description :  获取‘月采购预算、实际额及节省额对比图’的列表数据
	 * Create Date: 2011-7-11下午08:42:41 by likg  Modified Date: 2011-7-11下午08:42:41 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BigDecimal[]> getPlanActualSaveAmountList(Map<String, Object> param) throws Exception {
		return chartManager.getPlanActualSaveQtyByMonth(param);
	}
	
	/** 
	 * Description :  获取采购人或供应商产生过交易的采购品目
	 * Create Date: 2011-7-12下午03:08:10 by likg  Modified Date: 2011-7-12下午03:08:10 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getPurCategoryList(Map<String, Object> param) throws Exception {
		return chartManager.getPurCategoryList(param);
	}

	/** 
	 * Description :  获取‘品目月采购额、采购量、节省额趋势图’或‘品目月销售额、销售量趋势图’的图表和列表数据
	 * Create Date: 2011-7-12下午03:51:42 by likg  Modified Date: 2011-7-12下午03:51:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getPurcategoryAmountQtySaveData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String year = (String) param.get("year"); //年份
		String purCategoryName = (String) param.get("purCategoryName"); //采购品目名称
		String userType = (String) param.get("userType"); //用户类型
		
		//判断用户类型
		String amountName = null;
		String qtyName = null;
		String caption = null;
		if("buyer".equals(userType)) {
			amountName = "采购额";
			qtyName = "采购量";
			caption = purCategoryName+"月采购额、采购量、节省额趋势图";
		} else {
			amountName = "销售额";
			qtyName = "销售量";
			caption = purCategoryName+"月销售额、销售量趋势图";
		}
		
		List<BigDecimal[]> amountQtySaveList = chartManager.getPurcategoryAmountQtySaveByMonth(param);
		result.put("amountQtySaveList", amountQtySaveList);
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart palette='3' numberPrefix='￥' PYAxisName='Amount' SYAxisName='Quantity' "+this.getCommonChartParams(caption, year+"年", "0")+">");
		xml.append(this.getChartCategoriesMonth());
		xml.append("<dataset seriesName='"+amountName+"'>");
		for(BigDecimal[] amounts : amountQtySaveList) { xml.append("<set value='"+amounts[0]+"' />"); }
		xml.append("</dataset>");
		xml.append("<dataset seriesName='"+qtyName+"' lineThickness='3' parentYAxis='S'>");
		for(BigDecimal[] amounts : amountQtySaveList) { xml.append("<set value='"+amounts[1]+"' />"); }
		xml.append("</dataset>");
		if("buyer".equals(userType)) {
			xml.append("<dataset seriesName='节省额' renderAs='Area'>");
			for(BigDecimal[] amounts : amountQtySaveList) { xml.append("<set value='"+amounts[2]+"' />"); }
			xml.append("</dataset>");
		}
		xml.append("</chart>");
		result.put("chartXml", xml);
		
		return result;
	}

	/** 
	 * Description :  获取‘品目月采购额、采购量、节省额趋势图’列表数据
	 * Create Date: 2011-7-12下午03:51:42 by likg  Modified Date: 2011-7-12下午03:51:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BigDecimal[]> getPurcategoryAmountQtySaveList(Map<String, Object> param) throws Exception {
		return chartManager.getPurcategoryAmountQtySaveByMonth(param);
	}

	/** 
	 * Description :  获取‘多品目月采购额对比图’或‘多品目月销售额对比图’的图表和列表数据
	 * Create Date: 2011-7-13上午09:47:47 by likg  Modified Date: 2011-7-13上午09:47:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getPurcategoryAmountData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String year = (String) param.get("year"); //年份
		String userType = (String) param.get("userType"); //用户类型
		String purCategoryId = (String) param.get("purCategoryId"); //采购品目id
		String[] purCategoryIds = purCategoryId.split(",");
		
		List<BigDecimal[]> amountsList = new ArrayList<BigDecimal[]>();
		for(int i=0; i<12; i++) {
			amountsList.add(new BigDecimal[purCategoryIds.length]);
		}
		List<String> purCategoryNameList = new ArrayList<String>();
		List<BigDecimal> totalAmountList = new ArrayList<BigDecimal>();
		
		//判断用户类型
		String caption = null;
		if("buyer".equals(userType)) {
			caption = "多品目月采购额对比图";
		} else {
			caption = "多品目月销售额对比图";
		}
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart "+this.getCommonChartParams(caption, year+"年", "0")+" numberPrefix='￥' >");
		xml.append(this.getChartCategoriesMonth());
		for(int i=0; i<purCategoryIds.length; i++) {
			PurCategory purCategory = purCategoryManager.get(purCategoryIds[i]);
			param.put("purCategoryId", purCategoryIds[i]);
			List<BigDecimal[]> amountList = chartManager.getPurcategoryAmountQtySaveByMonth(param);
			purCategoryNameList.add(purCategory.getCategoryName());
			BigDecimal totalAmount = new BigDecimal(0);
			
			xml.append("<dataset seriesName='"+purCategory.getCategoryName()+"'>");
			for(int j=0; j<amountList.size(); j++) {
				BigDecimal[] amounts = amountList.get(j);
				amountsList.get(j)[i] = amounts[0];
				totalAmount = totalAmount.add(amounts[0]==null ? new BigDecimal(0) : amounts[0]);
				
				xml.append("<set value='"+amounts[0]+"' />");
			}
			xml.append("</dataset>");
			totalAmountList.add(totalAmount);
		}
		xml.append("</chart>");
		result.put("chartXml", xml);
		result.put("amountsList", amountsList);
		result.put("purCategoryNameList", purCategoryNameList);
		result.put("totalAmountList", totalAmountList);
		
		return result;
	}

	/** 
	 * Description :  获取‘多品目月采购量对比图’的图表和列表数据
	 * Create Date: 2011-7-13上午10:09:34 by likg  Modified Date: 2011-7-13上午10:09:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getPurcategoryQtyData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String year = (String) param.get("year"); //年份
		String purCategoryId = (String) param.get("purCategoryId"); //采购品目id
		String[] purCategoryIds = purCategoryId.split(",");
		
		List<BigDecimal[]> qtysList = new ArrayList<BigDecimal[]>();
		for(int i=0; i<12; i++) {
			qtysList.add(new BigDecimal[purCategoryIds.length]);
		}
		List<String> purCategoryNameList = new ArrayList<String>();
		List<BigDecimal> totalQtyList = new ArrayList<BigDecimal>();
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart "+this.getCommonChartParams("多品目月采购量对比图", year+"年", "0")+" palette='3' >");
		xml.append(this.getChartCategoriesMonth());
		for(int i=0; i<purCategoryIds.length; i++) {
			PurCategory purCategory = purCategoryManager.get(purCategoryIds[i]);
			param.put("purCategoryId", purCategoryIds[i]);
			List<BigDecimal[]> qtyList = chartManager.getPurcategoryAmountQtySaveByMonth(param);
			purCategoryNameList.add(purCategory.getCategoryName());
			BigDecimal totalQty = new BigDecimal(0);
			
			xml.append("<dataset seriesName='"+purCategory.getCategoryName()+"'>");
			for(int j=0; j<qtyList.size(); j++) {
				BigDecimal[] amounts = qtyList.get(j);
				qtysList.get(j)[i] = amounts[1];
				totalQty = totalQty.add(amounts[1]==null ? new BigDecimal(0) : amounts[1]);
				
				xml.append("<set value='"+amounts[1]+"' />");
			}
			xml.append("</dataset>");
			totalQtyList.add(totalQty);
		}
		xml.append("</chart>");
		result.put("chartXml", xml);
		result.put("qtysList", qtysList);
		result.put("purCategoryNameList", purCategoryNameList);
		result.put("totalQtyList", totalQtyList);
		
		return result;
	}

	/** 
	 * Description :  获取‘多品目月采购额对比图’的列表数据
	 * Create Date: 2011-7-13上午09:47:47 by likg  Modified Date: 2011-7-13上午09:47:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BigDecimal[]> getPurcategoryAmountList(Map<String, Object> param) throws Exception {
		String purCategoryId = (String) param.get("purCategoryId"); //采购品目id
		String[] purCategoryIds = purCategoryId.split(",");
		
		List<BigDecimal[]> amountsList = new ArrayList<BigDecimal[]>();
		for(int i=0; i<12; i++) {
			amountsList.add(new BigDecimal[purCategoryIds.length]);
		}
		
		//组装数据
		for(int i=0; i<purCategoryIds.length; i++) {
			param.put("purCategoryId", purCategoryIds[i]);
			List<BigDecimal[]> amountList = chartManager.getPurcategoryAmountQtySaveByMonth(param);
			
			for(int j=0; j<amountList.size(); j++) {
				BigDecimal[] amounts = amountList.get(j);
				amountsList.get(j)[i] = amounts[0];
			}
		}
		
		return amountsList;
	}

	/** 
	 * Description :  获取‘多品目月采购量对比图’的列表数据
	 * Create Date: 2011-7-13上午09:47:47 by likg  Modified Date: 2011-7-13上午09:47:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BigDecimal[]> getPurcategoryQtyList(Map<String, Object> param) throws Exception {
		String purCategoryId = (String) param.get("purCategoryId"); //采购品目id
		String[] purCategoryIds = purCategoryId.split(",");
		
		List<BigDecimal[]> qtysList = new ArrayList<BigDecimal[]>();
		for(int i=0; i<12; i++) {
			qtysList.add(new BigDecimal[purCategoryIds.length]);
		}
		
		//组装数据
		for(int i=0; i<purCategoryIds.length; i++) {
			param.put("purCategoryId", purCategoryIds[i]);
			List<BigDecimal[]> amountList = chartManager.getPurcategoryAmountQtySaveByMonth(param);
			
			for(int j=0; j<amountList.size(); j++) {
				BigDecimal[] amounts = amountList.get(j);
				qtysList.get(j)[i] = amounts[1];
			}
		}
		
		return qtysList;
	}

	/** 
	 * Description :  获取‘各品目年度采购总额分布图’或‘各品目年度销售总额分布图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getPurcategorysAmountPieData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String year = (String) param.get("year"); //年份
		String userType = (String) param.get("userType"); //用户类型
		
		List<Object[]> purcategorysAmountList = chartManager.getAllPurcategoryAmountQtyByYear(param);
		result.put("purcategorysAmountList", purcategorysAmountList);
		
		//判断用户类型
		String caption = null;
		if("buyer".equals(userType)) {
			caption = "各品目年度采购总额分布图";
		} else {
			caption = "各品目年度销售总额分布图";
		}
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart "+this.getCommonChartParams(caption, year+"年", "1")+" palette='4' numberPrefix='￥'>");
		xml.append(this.getChartCategoriesMonth());
		for(Object[] purcategorysAmount : purcategorysAmountList) { xml.append("<set label='"+purcategorysAmount[1]+"' value='"+purcategorysAmount[2]+"' />"); }
		xml.append("</chart>");
		result.put("chartXml", xml);
		
		return result;
	}

	/** 
	 * Description :  获取‘各品目年度采购总额分布图’的列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getPurcategorysAmountPieList(Map<String, Object> param) throws Exception {
		return chartManager.getAllPurcategoryAmountQtyByYear(param);
	}

	/** 
	 * Description :  获取‘各品目年度采购总量分布图或‘各品目年度销售总量分布图’’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getPurcategorysQtyPieData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String year = (String) param.get("year"); //年份
		String userType = (String) param.get("userType"); //用户类型
		
		List<Object[]> purcategorysQtyList = chartManager.getAllPurcategoryAmountQtyByYear(param);
		result.put("purcategorysQtyList", purcategorysQtyList);
		
		//判断用户类型
		String caption = null;
		if("buyer".equals(userType)) {
			caption = "各品目年度采购总量分布图";
		} else {
			caption = "各品目年度销售总量分布图";
		}
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart "+this.getCommonChartParams(caption, year+"年", "1")+" palette='4' >");
		xml.append(this.getChartCategoriesMonth());
		for(Object[] purcategorysQty : purcategorysQtyList) { xml.append("<set label='"+purcategorysQty[1]+"' value='"+purcategorysQty[3]+"' />"); }
		xml.append("</chart>");
		result.put("chartXml", xml);
		
		return result;
	}

	/** 
	 * Description :  获取‘各品目年度采购总量分布图’的列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getPurcategorysQtyPieList(Map<String, Object> param) throws Exception {
		return chartManager.getAllPurcategoryAmountQtyByYear(param);
	}

	/** 
	 * Description :  获取‘月采购额和采购量同比、环比图’和‘月销售额和销售量同比、环比图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getAmountQtyCompareData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String year1 = (String) param.get("year1"); //年份1
		String year2 = (String) param.get("year2"); //年份2
		String buyerId = (String) param.get("buyerId"); //采购人Id
		
		//获取数据
		param.put("year", year1);
		List<BigDecimal[]> amountQtyList1 = chartManager.getPlanActualSaveQtyByMonth(param);
		param.put("year", year2);
		List<BigDecimal[]> amountQtyList2 = chartManager.getPlanActualSaveQtyByMonth(param);
		result.put("amountQtyList1", amountQtyList1);
		result.put("amountQtyList2", amountQtyList2);
		
		//判断角色
		String amountName = null;
		String qtyName = null;
		String caption = null;
		if(StringUtils.hasLength(buyerId)) {
			amountName = "采购额";
			qtyName = "采购量";
			caption = "月采购额和采购量同比环比图";
		} else {
			amountName = "销售额";
			qtyName = "销售量";
			caption = "月销售额和销售量同比环比图";
		}
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart "+this.getCommonChartParams(caption, year1+" VS "+year2, "0")+" palette='3' PYAxisName='Amount' SYAxisName='Quantity' numberPrefix='￥'>");
		xml.append(this.getChartCategoriesMonth());
		xml.append("<dataset seriesName='"+year1+amountName+"'>");
		for(BigDecimal[] amountQty : amountQtyList1) { xml.append("<set value='"+amountQty[1]+"' />"); }
		xml.append("</dataset>");
		xml.append("<dataset seriesName='"+year2+amountName+"'>");
		for(BigDecimal[] amountQty : amountQtyList2) { xml.append("<set value='"+amountQty[1]+"' />"); }
		xml.append("</dataset>");
		xml.append("<dataset seriesName='"+year1+qtyName+"' lineThickness='3' parentYAxis='S'>");
		for(BigDecimal[] amountQty : amountQtyList1) { xml.append("<set value='"+amountQty[3]+"' />"); }
		xml.append("</dataset>");
		xml.append("<dataset seriesName='"+year2+qtyName+"' lineThickness='3' parentYAxis='S'>");
		for(BigDecimal[] amountQty : amountQtyList2) { xml.append("<set value='"+amountQty[3]+"' />"); }
		xml.append("</dataset>");
		xml.append("</chart>");
		result.put("chartXml", xml);
		
		return result;
	}

	/** 
	 * Description :  获取‘月采购额和采购量同比、环比图’的列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getAmountQtyCompareList(Map<String, Object> param) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String year1 = (String) param.get("year1"); //年份1
		String year2 = (String) param.get("year2"); //年份2
		
		param.put("year", year1);
		List<BigDecimal[]> amountQtyList1 = chartManager.getPlanActualSaveQtyByMonth(param);
		param.put("year", year2);
		List<BigDecimal[]> amountQtyList2 = chartManager.getPlanActualSaveQtyByMonth(param);
		result.put("amountQtyList1", amountQtyList1);
		result.put("amountQtyList2", amountQtyList2);
		
		return result;
	}

	/** 
	 * Description :  获取‘各采购方式月采购额分布比例图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getEbuymethodAmountData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String year = (String) param.get("year"); //年份
		
		param.put("ebuyMethod", "00");
		List<BigDecimal[]> amountList1 = chartManager.getEbuymethodAmountQtyByMonth(param);
		param.put("ebuyMethod", "01");
		List<BigDecimal[]> amountList2 = chartManager.getEbuymethodAmountQtyByMonth(param);
		param.put("ebuyMethod", "02");
		List<BigDecimal[]> amountList3 = chartManager.getEbuymethodAmountQtyByMonth(param);
		result.put("amountList1", amountList1);
		result.put("amountList2", amountList2);
		result.put("amountList3", amountList3);
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart "+this.getCommonChartParams("各采购方式月采购额分布比例图", year+"年", "0")+" numberPrefix='￥' showSum='0' >");
		xml.append(this.getChartCategoriesMonth());
		xml.append("<dataset seriesName='直接订购' showValues='0'>");
		for(BigDecimal[] amount : amountList1) { xml.append("<set value='"+amount[0]+"' />"); }
		xml.append("</dataset>");
		xml.append("<dataset seriesName='议价' showValues='0'>");
		for(BigDecimal[] amount : amountList2) { xml.append("<set value='"+amount[0]+"' />"); }
		xml.append("</dataset>");
		xml.append("<dataset seriesName='竞价' showValues='0'>");
		for(BigDecimal[] amount : amountList3) { xml.append("<set value='"+amount[0]+"' />"); }
		xml.append("</dataset>");
		xml.append("</chart>");
		result.put("chartXml", xml);
		
		return result;
	}

	/** 
	 * Description :  获取‘各采购方式月采购额分布比例图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getEbuymethodAmountList(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		
		param.put("ebuyMethod", "00");
		List<BigDecimal[]> amountList1 = chartManager.getEbuymethodAmountQtyByMonth(param);
		param.put("ebuyMethod", "01");
		List<BigDecimal[]> amountList2 = chartManager.getEbuymethodAmountQtyByMonth(param);
		param.put("ebuyMethod", "02");
		List<BigDecimal[]> amountList3 = chartManager.getEbuymethodAmountQtyByMonth(param);
		result.put("amountList1", amountList1);
		result.put("amountList2", amountList2);
		result.put("amountList3", amountList3);
		
		return result;
	}
	
	/** 
	 * Description :  获取‘各采购方式月采购量分布比例图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getEbuymethodQtyData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String year = (String) param.get("year"); //年份
		
		param.put("ebuyMethod", "00");
		List<BigDecimal[]> qtyList1 = chartManager.getEbuymethodAmountQtyByMonth(param);
		param.put("ebuyMethod", "01");
		List<BigDecimal[]> qtyList2 = chartManager.getEbuymethodAmountQtyByMonth(param);
		param.put("ebuyMethod", "02");
		List<BigDecimal[]> qtyList3 = chartManager.getEbuymethodAmountQtyByMonth(param);
		result.put("qtyList1", qtyList1);
		result.put("qtyList2", qtyList2);
		result.put("qtyList3", qtyList3);
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart "+this.getCommonChartParams("各采购方式月采购量分布比例图", year+"年", "0")+" showSum='0'>");
		xml.append(this.getChartCategoriesMonth());
		xml.append("<dataset seriesName='直接订购' showValues='0'>");
		for(BigDecimal[] qty : qtyList1) { xml.append("<set value='"+qty[1]+"' />"); }
		xml.append("</dataset>");
		xml.append("<dataset seriesName='议价' showValues='0'>");
		for(BigDecimal[] qty : qtyList2) { xml.append("<set value='"+qty[1]+"' />"); }
		xml.append("</dataset>");
		xml.append("<dataset seriesName='竞价' showValues='0'>");
		for(BigDecimal[] qty : qtyList3) { xml.append("<set value='"+qty[1]+"' />"); }
		xml.append("</dataset>");
		xml.append("</chart>");
		result.put("chartXml", xml);
		
		return result;
	}
	
	/** 
	 * Description :  获取‘各采购方式月采购量分布比例图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getEbuymethodQtyList(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		
		param.put("ebuyMethod", "00");
		List<BigDecimal[]> qtyList1 = chartManager.getEbuymethodAmountQtyByMonth(param);
		param.put("ebuyMethod", "01");
		List<BigDecimal[]> qtyList2 = chartManager.getEbuymethodAmountQtyByMonth(param);
		param.put("ebuyMethod", "02");
		List<BigDecimal[]> qtyList3 = chartManager.getEbuymethodAmountQtyByMonth(param);
		result.put("qtyList1", qtyList1);
		result.put("qtyList2", qtyList2);
		result.put("qtyList3", qtyList3);
		
		return result;
	}

	/** 
	 * Description :  获取‘各采购方式年度采购额分布图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getEbuymethodAmountPieData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String year = (String) param.get("year"); //年份
		
		List<Object[]> ebuymethodAmountList = chartManager.getAllEbuymethodAmountQtyByYear(param);
		result.put("ebuymethodAmountList", ebuymethodAmountList);
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart "+this.getCommonChartParams("各采购方式年度采购额分布图", year+"年", "1")+" palette='4' numberPrefix='￥' >");
		xml.append(this.getChartCategoriesMonth());
		String[] ebuymethodNames = new String[]{"直接订购", "议价", "竞价"};
		for(Object[] ebuymethodAmount : ebuymethodAmountList) {
			xml.append("<set label='"+ebuymethodNames[Integer.parseInt(ebuymethodAmount[0].toString())]+"' value='"+ebuymethodAmount[1]+"' />");
		}
		xml.append("</chart>");
		result.put("chartXml", xml);
		
		return result;
	}

	/** 
	 * Description :  获取‘各采购方式年度采购额分布图’的列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getEbuymethodAmountPieList(Map<String, Object> param) throws Exception {
		return chartManager.getAllEbuymethodAmountQtyByYear(param);
	}
	
	/** 
	 * Description :  获取‘各采购方式年度采购量分布图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getEbuymethodQtyPieData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String year = (String) param.get("year"); //年份
		
		List<Object[]> ebuymethodQtyList = chartManager.getAllEbuymethodAmountQtyByYear(param);
		result.put("ebuymethodQtyList", ebuymethodQtyList);
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart "+this.getCommonChartParams("各采购方式年度采购量分布图", year+"年", "1")+" palette='4' >");
		xml.append(this.getChartCategoriesMonth());
		String[] ebuymethodNames = new String[]{"直接订购", "议价", "竞价"};
		for(Object[] ebuymethodQty : ebuymethodQtyList) {
			xml.append("<set label='"+ebuymethodNames[Integer.parseInt(ebuymethodQty[0].toString())]+"' value='"+ebuymethodQty[2]+"' />");
		}
		xml.append("</chart>");
		result.put("chartXml", xml);
		
		return result;
	}
	
	/** 
	 * Description :  获取‘各采购方式年度采购量分布图’的列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getEbuymethodQtyPieList(Map<String, Object> param) throws Exception {
		return chartManager.getAllEbuymethodAmountQtyByYear(param);
	}
	
	/** 
	 * Description :  获取12个月份垂直表头
	 * Create Date: 2011-7-14下午01:07:47 by likg  Modified Date: 2011-7-14下午01:07:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private StringBuilder getChartCategoriesMonth() {
		StringBuilder result = new StringBuilder();
		
		result.append("<categories>");
		for(int i=1; i<=12; i++) {
			result.append("<category label='").append(i<10 ? "0"+i : i).append("' />");
		}
		result.append("</categories>");
		
		return result;
	}
	
	/**
	 * Description :获取‘时间段内各商品销售量分布图’的图表和列表数据  
	 * Create Date: 2011-7-28下午02:56:07 by zhaojf  Modified Date: 2011-7-28下午02:56:07 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String, Object> getDuringGoodsQtyPieData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String startDate = (String) param.get("startDate"); //开始时间
		String endDate = (String) param.get("endDate"); //截止时间
		String userType = (String) param.get("userType"); //用户类型
		String subCaption = "";
		if(!"".equals(startDate) && !"".equals(endDate)){
			subCaption = subCaption +startDate+ "至" + endDate;
		}
		if("".equals(startDate) && "".equals(endDate)){
			subCaption = "全部";
		}
		if(!"".equals(startDate) && "".equals(endDate)){
			subCaption = startDate + "-- 现在";
		}
		if("".equals(startDate) && !"".equals(endDate)){
			subCaption = endDate + "之前"; 
		}
		
		List<Object[]> durGoodsQtyList = chartManager.getAllDurGoodsAmountQtyByYear(param);
		result.put("durGoodsQtyList", durGoodsQtyList);
		
		//判断用户类型
		String caption = null;
		if("buyer".equals(userType)) {
			caption = "时间段内各商品采购量分布图";
		} else {
			caption = "时间段内各商品销售量分布图";
		}
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart "+this.getCommonChartParams(caption, subCaption, "1")+" palette='4' >");
		xml.append(this.getChartCategoriesMonth());
		for(Object[] durGoodsQtyPie : durGoodsQtyList) { xml.append("<set label='"+durGoodsQtyPie[0]+"' value='"+durGoodsQtyPie[2]+"' />"); }
		xml.append("</chart>");
		result.put("chartXml", xml);
		
		return result;
	}
	
	/**
	 * Description :  获取‘时间段内各商品销售额分布图’的图表和列表数据
	 * Create Date: 2011-7-29上午10:52:49 by zhaojf  Modified Date: 2011-7-29上午10:52:49 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String, Object> getDuringGoodsAmountPieData(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		String startDate = (String) param.get("startDate"); //开始时间
		String endDate = (String) param.get("endDate"); //截止时间
		String userType = (String) param.get("userType"); //用户类型
		String subCaption = "";
		if(!"".equals(startDate) && !"".equals(endDate)){
			subCaption = subCaption +startDate+ "至" + endDate;
		}
		if("".equals(startDate) && "".equals(endDate)){
			subCaption = "全部";
		}
		if(!"".equals(startDate) && "".equals(endDate)){
			subCaption = startDate + "-- 现在";
		}
		if("".equals(startDate) && !"".equals(endDate)){
			subCaption = endDate + "之前"; 
		}
		
		List<Object[]> durGoodsAmountList = chartManager.getAllDurGoodsAmountQtyByYear(param);
		result.put("durGoodsAmountList", durGoodsAmountList);
		
		//判断用户类型
		String caption = null;
		if("buyer".equals(userType)) {
			caption = "时间段内各商品采购额分布图";
		} else {
			caption = "时间段内各商品销售额分布图";
		}
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart "+this.getCommonChartParams(caption, subCaption, "1")+" palette='4' numberPrefix='￥'>");
		xml.append(this.getChartCategoriesMonth());
		for(Object[] durGoodsAmountPie : durGoodsAmountList) { xml.append("<set label='"+durGoodsAmountPie[0]+"' value='"+durGoodsAmountPie[3]+"' />"); }
		xml.append("</chart>");
		result.put("chartXml", xml);
		
		return result;
	}
	
	/**
	 * 获取‘时间段内各商品销售量分布图’、‘时间段内各商品销售额分布图’列表数据
	 */
	public List<Object[]> getDuringGoodsAmountAndQtyPieList(Map<String, Object> param) throws Exception {
		return chartManager.getAllDurGoodsAmountQtyByYear(param);
	}
	
	/**
	 *  获取‘商品在项目中的报价趋势图‘(供应商才报价)
	 */
	public Map<String, Object> getGoodsProjectBiddingLineData(Map<String, Object> param) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String goodsId = (String) param.get("goodsId");
		Goods goods = goodsManager.get(goodsId);
		
		//获取报价数据
		List<BiddingRecordDetail> goodsProjectBiddingList = chartManager.getAllGoodsBiddingByProjectData(param);
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart"+this.getCommonChartParams("商品"+goods.getProductName()+"在项目中", "我的报价趋势", "0")+" palette='4' numberPrefix='￥' numberSuffix='元' xAxisName='项目名称' yAxisName='商品报价(元)' >");
		xml.append("<categories>");
		for(int i=0;i<goodsProjectBiddingList.size();i++){
			xml.append("<category label='"+goodsProjectBiddingList.get(i).getProject().getProjName()+"' />");
		}
		xml.append("</categories>");
		xml.append("<dataset >");
		for(int j=0;j<goodsProjectBiddingList.size();j++){
			xml.append("<set value='"+goodsProjectBiddingList.get(j).getGoodsPrice()+"' />");
		}		
		xml.append("</dataset>");
		xml.append("</chart>");
		
		result.put("chartXml", xml);
		result.put("goodsProjectBiddingList", goodsProjectBiddingList);
		return result;
	}
	
	/**
	 * 获取商品在项目里的报价(根据参数商品Id、供货商Id、当前用户Id)
	 */
	public List<BiddingRecordDetail> getAllGoodsBiddingByProjectData(Map<String, Object> param) throws Exception {
		return chartManager.getAllGoodsBiddingByProjectData(param);
	}
	
	/**
	 * 各季度中标情况对比图(供应商)
	 */
	public Map<String, Object> gebBiddingWinnerRecordsByYearData(Map<String, Object> param) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String year = (String) param.get("year"); //年份
		String start_date = "";//季度开始时间
		String end_date = "";//季度结束时间
		//四个季度所有的中标情况
		List<Object []> all_biddingWinnerList = new ArrayList<Object[]>();
		String [] paramArr = new String[3];
		
		//第一季度
		start_date = year + "-01-01";
		end_date = year + "-03-31";
		param.put("start_date", start_date);
		param.put("end_date", end_date);		
		//获取中标情况数据
		List<BuyWinner> first_biddingWinnerList = chartManager.getAllBiddingWinnerRecordsByYearData(param);
		paramArr[0] = "第一季度(1月-3月)";
		paramArr[1]="0";
		paramArr[2]="0";
		for(BuyWinner objArr : first_biddingWinnerList){
			if(objArr.getResultType().equals("01")){paramArr[1] = String.valueOf(Integer.valueOf(paramArr[1])+1);}
			else{paramArr[2] = String.valueOf(Integer.valueOf(paramArr[2])+1);}
		}
		all_biddingWinnerList.add(paramArr);
		
		//第二季度
		paramArr = new String[3];
		paramArr[0] = "第二季度(4月-6月)";
		paramArr[1]="0";
		paramArr[2]="0";
		start_date = year + "-04-01";
		end_date = year + "-06-30";
		param.put("start_date", start_date);
		param.put("end_date", end_date);		
		//获取中标情况数据
		List<BuyWinner> second_biddingWinnerList = chartManager.getAllBiddingWinnerRecordsByYearData(param);
		for(BuyWinner objArr : second_biddingWinnerList){
			if(objArr.getResultType().equals("01")){paramArr[1] = String.valueOf(Integer.valueOf(paramArr[1])+1);}
			else{paramArr[2] = String.valueOf(Integer.valueOf(paramArr[2])+1);}
		}
		all_biddingWinnerList.add(paramArr);
		
		//第三季度
		paramArr = new String[3];		
		start_date = year + "-07-01";
		end_date = year + "-09-30";
		param.put("start_date", start_date);
		param.put("end_date", end_date);		
		//获取中标情况数据
		List<BuyWinner> third_biddingWinnerList = chartManager.getAllBiddingWinnerRecordsByYearData(param);
		paramArr[0] = "第三季度(7月-9月)";
		paramArr[1]="0";
		paramArr[2]="0";
		for(BuyWinner objArr : third_biddingWinnerList){
			if(objArr.getResultType().equals("01")){paramArr[1] = String.valueOf(Integer.valueOf(paramArr[1])+1);}
			else{paramArr[2] = String.valueOf(Integer.valueOf(paramArr[2])+1);}
		}
		all_biddingWinnerList.add(paramArr);
		
		//第四季度
		paramArr = new String[3];
		start_date = year + "-10-01";
		end_date = year + "-12-31";
		param.put("start_date", start_date);
		param.put("end_date", end_date);	
		//获取中标情况数据
		List<BuyWinner> four_biddingWinnerList = chartManager.getAllBiddingWinnerRecordsByYearData(param);
		paramArr[0] = "第四季度(10月-12月)";
		paramArr[1]="0";
		paramArr[2]="0";
		for(BuyWinner objArr : four_biddingWinnerList){
			if(objArr.getResultType().equals("01")){paramArr[1] = String.valueOf(Integer.valueOf(paramArr[1])+1);}
			else{paramArr[2] = String.valueOf(Integer.valueOf(paramArr[2])+1);}
		}
		all_biddingWinnerList.add(paramArr);
		
		//组装XML
		StringBuilder xml = new StringBuilder("");
		xml.append("<chart"+this.getCommonChartParams("各季度项目中标情况", year+"年", "0")+" palette='2'  showLabels='1' numberSuffix='项' showSum='1' decimals='0' useRoundEdges='1' legendBorderAlpha='0' >");
		xml.append("<categories>");
		xml.append("<category label='第一季度' /><category label='第二季度' /><category label='第三季度' /><category label='第四季度' />");
		xml.append("</categories>");
		xml.append("<dataset seriesName='未中标' color='F6BD0F' showValues='0'>");
		for(Object[] objArr : all_biddingWinnerList){xml.append("<set value='"+objArr[2]+"' />");}		
		xml.append("</dataset>");
		xml.append("<dataset seriesName='中标' color='AFD8F8' showValues='0'>");
		for(Object[] objArr : all_biddingWinnerList){xml.append("<set value='"+objArr[1]+"' />");}		
		xml.append("</dataset>");
		xml.append("</chart>");
		
		result.put("chartXml", xml);
		result.put("all_biddingWinnerList", all_biddingWinnerList);
		return result;
	}
}
