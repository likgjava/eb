package com.gpcsoft.smallscale.chart.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.service.GoodsService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.chart.service.ChartService;
import com.gpcsoft.srplatform.common.utils.excel.ExcelNoTemplate;

/**
 * @gpcsoft.view value="saveAmountCompareView"
 *  url="view/smallscale/chart/save_amount_compare_div.jsp"
 *  
 * @gpcsoft.view value="planActualSaveAmountView"
 *  url="view/smallscale/chart/plan_actual_save_amount_div.jsp"
 *  
 * @gpcsoft.view value="purcategoryAmountQtySaveView"
 *  url="view/smallscale/chart/purcategory_amount_qty_save.jsp"
 *  
 * @gpcsoft.view value="purcategoryAmountQtySaveDivView"
 *  url="view/smallscale/chart/purcategory_amount_qty_save_div.jsp"
 *  
 * @gpcsoft.view value="purcategoryAmountView"
 *  url="view/smallscale/chart/purcategory_amount.jsp"
 *  
 * @gpcsoft.view value="purcategoryAmountDivView"
 *  url="view/smallscale/chart/purcategory_amount_div.jsp"
 *  
 * @gpcsoft.view value="purcategoryQtyView"
 *  url="view/smallscale/chart/purcategory_qty.jsp"
 *  
 * @gpcsoft.view value="purcategoryQtyDivView"
 *  url="view/smallscale/chart/purcategory_qty_div.jsp"
 *  
 * @gpcsoft.view value="purcategorysAmountPieDivView"
 *  url="view/smallscale/chart/purcategorys_amount_pie_div.jsp"
 *  
 * @gpcsoft.view value="purcategorysQtyPieDivView"
 *  url="view/smallscale/chart/purcategorys_qty_pie_div.jsp"
 *  
 * @gpcsoft.view value="amountQtyCompareDivView"
 *  url="view/smallscale/chart/amount_qty_compare_div.jsp"
 *  
 * @gpcsoft.view value="ebuymethodAmountDivView"
 *  url="view/smallscale/chart/ebuymethod_amount_div.jsp"
 *  
 * @gpcsoft.view value="ebuymethodQtyDivView"
 *  url="view/smallscale/chart/ebuymethod_qty_div.jsp"
 *  
 * @gpcsoft.view value="ebuymethodAmountPieDivView"
 *  url="view/smallscale/chart/ebuymethod_amount_pie_div.jsp"
 *  
 * @gpcsoft.view value="ebuymethodQtyPieDivView"
 *  url="view/smallscale/chart/ebuymethod_qty_pie_div.jsp"
 *  
 * @gpcsoft.view value="duringGoodsQtyPieDivView"
 *  url="view/smallscale/chart/during_goods_qty_pie_div.jsp"
 *  
 * @gpcsoft.view value="duringGoodsAmountPieDataDivView"
 *  url="view/smallscale/chart/during_goods_amount_pie_div.jsp"
 *  
 * @gpcsoft.view value="goodsProjectBiddingLineDataDivView"
 *  url="view/smallscale/chart/s_goods_project_bidding_line_div.jsp"
 *  
 * @gpcsoft.view value="biddingWinnerRecordsByYearDataView"
 *  url="view/smallscale/chart/bidding_winner_records_column_div.jsp"
 *  
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GpcBaseObject.class})
@RequestMapping("/ChartController.do")
public class ChartController extends AnnotationMultiController<GpcBaseObject> {

	@Autowired(required=true) @Qualifier("chartServiceImpl")
	private ChartService chartService;
	
	@Autowired(required = true) @Qualifier("purCategoryServiceImpl")
	private PurCategoryService purCategoryService;
	
	@Autowired(required=true) @Qualifier("goodsServiceImpl")
	private GoodsService goodsService;
	
	/** 
	 * Description :  获取‘月采购节省总额同比、环比图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toSaveAmountCompareView")
	public ModelAndView toSaveAmountCompareView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("year1", request.getParameter("year1")); //年份1
		param.put("year2", request.getParameter("year2")); //年份2
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		
		Map<String, Object> result = chartService.getSaveAmountCompareData(param);
		
		return new ModelAndView("saveAmountCompareView", result);
	}
	
	/** 
	 * Description :  导出‘月采购节省总额同比、环比图’为Excel文件
	 * Create Date: 2011-7-11下午08:38:22 by likg  Modified Date: 2011-7-11下午08:38:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=exportSaveAmountCompareToExcel")
	public void exportSaveAmountCompareToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String year1 = request.getParameter("year1"); //年份1
		String year2 = request.getParameter("year2"); //年份2
		param.put("year1", year1);
		param.put("year2", year2);
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		
		//第二行表头,定义列宽
		String[] title = new String[]{"", year1+"年", year2+"年", "差额"};
		String[][] columnNames = new String[][]{title};
		int[] columnWidths = new int[]{100, 120, 120};
		
		//文件名
		String fileName = year1 + "和" + year2 + "每月采购节省总额同比、环比";
		
		//取得数据
		Map<String, Object> result = chartService.getSaveAmountCompareList(param);
		List<BigDecimal> list1 = (List<BigDecimal>) result.get("list1");
		List<BigDecimal> list2 = (List<BigDecimal>) result.get("list2");
		
		//组装数据
		BigDecimal totalSaveAmount1 = new BigDecimal(0);
		BigDecimal totalSaveAmount2 = new BigDecimal(0);
		BigDecimal totalGapAmount = new BigDecimal(0);
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(int i=0; i<12; i++) {
			int j = 0;
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("0"+j++, (i+1)+"月");
			rowData.put("0"+j++, (list1.get(i)==null ? "" : list1.get(i)));
			rowData.put("0"+j++, (list2.get(i)==null ? "" : list2.get(i)));
			rowData.put("0"+j++, (list2.get(i)==null ? 0 : list2.get(i).doubleValue()) - (list1.get(i)==null ? 0 : list1.get(i).doubleValue()));
			list.add(rowData);
			totalSaveAmount1 = totalSaveAmount1.add(list1.get(i)!=null ? list1.get(i) : new BigDecimal(0));
			totalSaveAmount2 = totalSaveAmount2.add(list2.get(i)!=null ? list2.get(i) : new BigDecimal(0));
			totalGapAmount = totalGapAmount.add((list2.get(i)==null?new BigDecimal(0):list2.get(i)).subtract(list1.get(i)==null?new BigDecimal(0):list1.get(i)));
		}
		ListOrderedMap totalRowData = new ListOrderedMap();
		totalRowData.put("13", "总计");
		totalRowData.put("14", totalSaveAmount1);
		totalRowData.put("15", totalSaveAmount2);
		totalRowData.put("16", totalGapAmount);
		list.add(totalRowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/** 
	 * Description :  获取‘月采购预算、实际额及节省额对比图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPlanActualSaveAmountView")
	public ModelAndView toPlanActualSaveAmountView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("year", request.getParameter("year")); //年份
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getPlanActualSaveAmountData(param);
		
		return new ModelAndView("planActualSaveAmountView", result);
	}
	
	/** 
	 * Description :  导出‘月采购预算、实际额及节省额对比图’为Excel文件
	 * Create Date: 2011-7-11下午08:38:22 by likg  Modified Date: 2011-7-11下午08:38:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=exportPlanActualSaveAmountToExcel")
	public void exportPlanActualSaveAmountToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String year = request.getParameter("year"); //年份
		param.put("year", year);
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		
		//第二行表头,定义列宽
		String[] title = new String[]{"", "采购预算（元）", "实际（元）", "节省（元）"};
		String[][] columnNames = new String[][]{title};
		int[] columnWidths = new int[]{100, 120, 120, 120};
		
		//文件名
		String fileName = year + "年每月采购预算、实际额及节省额对比";
		
		//取得数据
		List<BigDecimal[]> amountList = chartService.getPlanActualSaveAmountList(param);
		
		//组装数据
		BigDecimal totalPlanAmount = new BigDecimal(0);
		BigDecimal totalActualAmount = new BigDecimal(0);
		BigDecimal totalSaveAmount = new BigDecimal(0);
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(int i=0; i<12; i++) {
			int j = 1;
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("0"+j++, (i+1)+"月");
			rowData.put("0"+j++, (amountList.get(i)[0]==null ? "" : amountList.get(i)[0]));
			rowData.put("0"+j++, (amountList.get(i)[1]==null ? "" : amountList.get(i)[1]));
			rowData.put("0"+j++, (amountList.get(i)[2]==null ? "" : amountList.get(i)[2]));
			list.add(rowData);
			totalPlanAmount = totalPlanAmount.add(amountList.get(i)[0]!=null ? amountList.get(i)[0] : new BigDecimal(0));
			totalActualAmount = totalActualAmount.add(amountList.get(i)[1]!=null ? amountList.get(i)[1] : new BigDecimal(0));
			totalSaveAmount = totalSaveAmount.add(amountList.get(i)[2]!=null ? amountList.get(i)[2] : new BigDecimal(0));
		}
		ListOrderedMap totalRowData = new ListOrderedMap();
		totalRowData.put("01", "总计");
		totalRowData.put("02", totalPlanAmount);
		totalRowData.put("03", totalActualAmount);
		totalRowData.put("04", totalSaveAmount);
		list.add(totalRowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/** 
	 * Description :  跳转到‘品目月采购额、采购量、节省额趋势图’或‘品目月销售额、销售量趋势图’的页面
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPurcategoryAmountQtySaveView")
	public ModelAndView toPurcategoryAmountQtySaveView(HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String, Object>();
		Map<String,Object> param = new HashMap<String, Object>();
		String userType = request.getParameter("userType"); //用户类型
		param.put("userType", userType);
		if("supplier".equals(userType)) {
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		} else {
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		}
		
		//获取采购人或供应商产生过交易的采购品目
		List<Object[]> purCategoryList = chartService.getPurCategoryList(param);
		model.put("purCategoryList", purCategoryList);
		model.put("userType", userType);
		
		return new ModelAndView("purcategoryAmountQtySaveView", model);
	}
	
	/** 
	 * Description :  获取‘品目月采购额、采购量、节省额趋势图’或‘品目月销售额、销售量趋势图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getPurcategoryAmountQtySaveData")
	public ModelAndView getPurcategoryAmountQtySaveData(HttpServletRequest request) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("year", request.getParameter("year")); //年份
		param.put("purCategoryId", request.getParameter("purCategoryId")); //采购品目id
		String userType = request.getParameter("userType"); //用户类型
		param.put("userType", userType);
		if("supplier".equals(userType)) {
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		} else {
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		}
		
		//获取采购品目信息
		PurCategory purCategory = purCategoryService.get(request.getParameter("purCategoryId"));
		param.put("purCategoryName", purCategory.getCategoryName()); //采购品目名称
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getPurcategoryAmountQtySaveData(param);
		result.put("userType", userType);
		
		return new ModelAndView("purcategoryAmountQtySaveDivView", result);
	}
	
	/** 
	 * Description :  导出‘品目月采购额、采购量、节省额趋势图’或‘品目月销售额、销售量趋势图’为Excel文件
	 * Create Date: 2011-7-11下午08:38:22 by likg  Modified Date: 2011-7-11下午08:38:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=exportPurcategoryAmountQtySaveToExcel")
	public void exportPurcategoryAmountQtySaveToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String purCategoryId = request.getParameter("purCategoryId"); //品目id
		String year = request.getParameter("year"); //年份
		param.put("year", year);
		param.put("purCategoryId", purCategoryId);
		String userType = request.getParameter("userType"); //用户类型
		
		//获取采购品目信息
		PurCategory purCategory = purCategoryService.get(request.getParameter("purCategoryId"));
		
		//第二行表头,定义列宽,文件名
		String[] title = null;
		String fileName = null;
		int[] columnWidths = new int[]{100, 120, 120, 120};
		if("buyer".equals(userType)) {
			title = new String[]{"", "采购额（元）", "采购量（订单数）", "节省额（元）"};
			fileName = year + "年"+purCategory.getCategoryName()+"每月采购额、采购量及节省额对比";
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			title = new String[]{"", "销售额（元）", "销售量（订单数）"};
			fileName = year + "年"+purCategory.getCategoryName()+"每月销售额、销售量对比";
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		String[][] columnNames = new String[][]{title};
		
		//取得数据
		List<BigDecimal[]> amountList = chartService.getPurcategoryAmountQtySaveList(param);
		
		//组装数据
		BigDecimal totalActualAmount = new BigDecimal(0);
		BigDecimal totalQty = new BigDecimal(0);
		BigDecimal totalSaveAmount = new BigDecimal(0);
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(int i=0; i<12; i++) {
			int j = 1;
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("0"+j++, (i+1)+"月");
			rowData.put("0"+j++, (amountList.get(i)[0]==null ? "" : amountList.get(i)[0]));
			rowData.put("0"+j++, (amountList.get(i)[1]==null ? "" : amountList.get(i)[1].intValue()));
			if("buyer".equals(userType)) {
				rowData.put("0"+j++, (amountList.get(i)[2]==null ? "" : amountList.get(i)[2]));
				totalSaveAmount = totalSaveAmount.add(amountList.get(i)[2]!=null ? amountList.get(i)[2] : new BigDecimal(0));
			}
			list.add(rowData);
			totalActualAmount = totalActualAmount.add(amountList.get(i)[0]!=null ? amountList.get(i)[0] : new BigDecimal(0));
			totalQty = totalQty.add(amountList.get(i)[1]!=null ? amountList.get(i)[1] : new BigDecimal(0));
		}
		ListOrderedMap totalRowData = new ListOrderedMap();
		totalRowData.put("01", "总计");
		totalRowData.put("02", totalActualAmount);
		totalRowData.put("03", totalQty.intValue());
		if("buyer".equals(userType)) {
			totalRowData.put("04", totalSaveAmount);
		}
		list.add(totalRowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/** 
	 * Description :  跳转到‘多品目月采购额对比图’或‘多品目月销售额对比图’的页面
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPurcategoryAmountView")
	public ModelAndView toPurcategoryAmountView(HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String, Object>();
		Map<String,Object> param = new HashMap<String, Object>();
		String userType = request.getParameter("userType"); //用户类型
		param.put("userType", userType);
		if("buyer".equals(userType)) {
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		
		//获取采购人产生过交易的采购品目
		List<Object[]> purCategoryList = chartService.getPurCategoryList(param);
		model.put("purCategoryList", purCategoryList);
		model.put("userType", userType);
		
		return new ModelAndView("purcategoryAmountView", model);
	}
	
	/** 
	 * Description :  获取‘多品目月采购额对比图’或‘多品目月销售额对比图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getPurcategoryAmountData")
	public ModelAndView getPurcategoryAmountData(HttpServletRequest request) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("year", request.getParameter("year")); //年份
		param.put("purCategoryId", request.getParameter("purCategoryId")); //采购品目id，以逗号分割
		String userType = request.getParameter("userType"); //用户类型
		param.put("userType", userType);
		if("buyer".equals(userType)) {
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getPurcategoryAmountData(param);
		result.put("userType", userType);
		
		return new ModelAndView("purcategoryAmountDivView", result);
	}
	
	/** 
	 * Description :  跳转到‘多品目月采购量对比图’或‘多品目月销售量对比图’的页面
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPurcategoryQtyView")
	public ModelAndView toPurcategoryQtyView(HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String, Object>();
		Map<String,Object> param = new HashMap<String, Object>();
		String userType = request.getParameter("userType"); //用户类型
		param.put("userType", userType);
		if("buyer".equals(userType)) {
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		
		//获取采购人或供应商产生过交易的采购品目
		List<Object[]> purCategoryList = chartService.getPurCategoryList(param);
		model.put("purCategoryList", purCategoryList);
		model.put("userType", userType);
		
		return new ModelAndView("purcategoryQtyView", model);
	}
	
	/** 
	 * Description :  获取‘多品目月采购量对比图’或‘多品目月销售量对比图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getPurcategoryQtyData")
	public ModelAndView getPurcategoryQtyData(HttpServletRequest request) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("year", request.getParameter("year")); //年份
		param.put("purCategoryId", request.getParameter("purCategoryId")); //采购品目id
		String userType = request.getParameter("userType"); //用户类型
		param.put("userType", userType);
		if("buyer".equals(userType)) {
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getPurcategoryQtyData(param);
		result.put("userType", userType);		
		return new ModelAndView("purcategoryQtyDivView", result);
	}
	
	/** 
	 * Description :  导出‘多品目月采购额对比图’或‘多品目月销售量对比图’为Excel文件
	 * Create Date: 2011-7-11下午08:38:22 by likg  Modified Date: 2011-7-11下午08:38:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=exportPurcategoryAmountToExcel")
	public void exportPurcategoryAmountToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String purCategoryId = request.getParameter("purCategoryId"); //品目id
		String year = request.getParameter("year"); //年份
		param.put("year", year);
		param.put("purCategoryId", purCategoryId);
		String userType = request.getParameter("userType"); //用户类型
		
		//第二、三行表头，定义列宽，文件名
		String[] purCategoryIds = purCategoryId.split(",");
		String[] title1 = new String[purCategoryIds.length+1];
		String[] title2 = new String[purCategoryIds.length+1];
		String fileName = null;
		title1[0] = "";
		for(int i=0; i<purCategoryIds.length; i++) {
			PurCategory purCategory = purCategoryService.get(purCategoryIds[i]);
			title2[i+1] = purCategory.getCategoryName();
			title1[i+1] = "#";
		}
		if("buyer".equals(userType)) {
			title1[1] = "采购额（元）";
			fileName = "多品目月采购额对比";
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			title1[1] = "销售额（元）";
			fileName = "多品目月销售额对比";
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		String[][] columnNames = new String[][]{title1, title2};
		int[] columnWidths = new int[]{100, 120, 120, 120};
		
		//取得数据
		List<BigDecimal[]> amountsList = chartService.getPurcategoryAmountList(param);
		
		//组装数据
		BigDecimal[] totalAmountList = new BigDecimal[purCategoryIds.length];
		for(int j=0; j<purCategoryIds.length; j++) {
			totalAmountList[j] = new BigDecimal(0);
		}
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(int i=0; i<12; i++) {
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("00", (i+1)+"月");
			BigDecimal[] amounts = amountsList.get(i);
			for(int j=0; j<amounts.length; j++) {
				rowData.put("0"+(j+1), (amounts[j]==null ? "" : amounts[j]));
				totalAmountList[j] = totalAmountList[j].add(amounts[j]==null ? new BigDecimal(0) : amounts[j]);
			}
			list.add(rowData);
		}
		ListOrderedMap totalRowData = new ListOrderedMap();
		totalRowData.put("00", "总计");
		int t = 1;
		for(BigDecimal totalAmount : totalAmountList) {
			totalRowData.put("0"+t++, totalAmount);
		}
		list.add(totalRowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/** 
	 * Description :  导出‘多品目月采购量对比图’为Excel文件
	 * Create Date: 2011-7-11下午08:38:22 by likg  Modified Date: 2011-7-11下午08:38:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=exportPurcategoryQtyToExcel")
	public void exportPurcategoryQtyToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String purCategoryId = request.getParameter("purCategoryId"); //品目id
		String year = request.getParameter("year"); //年份
		param.put("year", year);
		param.put("purCategoryId", purCategoryId);
		String userType = request.getParameter("userType"); //用户类型
		
		//第二、三行表头,定义列宽,文件名
		String[] purCategoryIds = purCategoryId.split(",");
		String[] title1 = new String[purCategoryIds.length+1];
		String[] title2 = new String[purCategoryIds.length+1];
		int[] columnWidths = new int[purCategoryIds.length+1];
		String fileName = null;
		columnWidths[0] = 100;
		for(int i=0; i<purCategoryIds.length; i++) {
			PurCategory purCategory = purCategoryService.get(purCategoryIds[i]);
			title2[i+1] = purCategory.getCategoryName();
			title1[i+1] = "#";
			columnWidths[i+1] = 120;
		}
		if("buyer".equals(userType)) {
			title1[1] = "采购量（订单数）";
			fileName = "多品目月采购量对比";
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			title1[1] = "销售量（订单数）";
			fileName = "多品目月销售量对比";
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		String[][] columnNames = new String[][]{title1, title2};
		
		//取得数据
		List<BigDecimal[]> qtysList = chartService.getPurcategoryQtyList(param);
		
		//组装数据
		BigDecimal[] totalQtyList = new BigDecimal[purCategoryIds.length];
		for(int j=0; j<purCategoryIds.length; j++) {
			totalQtyList[j] = new BigDecimal(0);
		}
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(int i=0; i<12; i++) {
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("00", (i+1)+"月");
			BigDecimal[] qtys = qtysList.get(i);
			for(int j=0; j<qtys.length; j++) {
				rowData.put("0"+(j+1), (qtys[j]==null ? "" : qtys[j].intValue()));
				totalQtyList[j] = totalQtyList[j].add(qtys[j]==null ? new BigDecimal(0) : qtys[j]);
			}
			list.add(rowData);
		}
		ListOrderedMap totalRowData = new ListOrderedMap();
		totalRowData.put("00", "总计");
		int t = 1;
		for(BigDecimal totalQty : totalQtyList) {
			totalRowData.put("0"+t++, totalQty.intValue());
		}
		list.add(totalRowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/** 
	 * Description :  获取‘各品目年度采购总额分布图’或‘各品目年度销售总额分布图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getPurcategorysAmountPieData")
	public ModelAndView getPurcategorysAmountPieData(HttpServletRequest request) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("year", request.getParameter("year")); //年份
		String userType = request.getParameter("userType"); //用户类型
		param.put("userType", userType);
		if("buyer".equals(userType)) {
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getPurcategorysAmountPieData(param);
		result.put("userType", userType);
		
		return new ModelAndView("purcategorysAmountPieDivView", result);
	}
	
	/** 
	 * Description :  导出‘各品目年度采购总额分布图’或‘各品目年度销售总额分布图’为Excel文件
	 * Create Date: 2011-7-11下午08:38:22 by likg  Modified Date: 2011-7-11下午08:38:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=exportPurcategorysAmountPieToExcel")
	public void exportPurcategorysAmountPieToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String year = request.getParameter("year"); //年份
		param.put("year", year);
		String userType = request.getParameter("userType"); //用户类型
		
		//第二行表头,定义列宽,文件名
		String[] title = null;
		String fileName = null;
		int[] columnWidths = new int[]{100, 200};
		if("buyer".equals(userType)) {
			fileName = year + "年各品目年度采购总额";
			title = new String[]{"", "采购额（元）"};
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			fileName = year + "年各品目年度销售总额";
			title = new String[]{"", "销售额（元）"};
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		String[][] columnNames = new String[][]{title};
		
		//取得数据
		List<Object[]> purcategorysAmountList = chartService.getPurcategorysAmountPieList(param);
		
		//组装数据
		BigDecimal totalAmount = new BigDecimal(0);
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(Object[] obj : purcategorysAmountList) {
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("00", obj[1]);
			rowData.put("01", obj[2]);
			list.add(rowData);
			totalAmount = totalAmount.add(obj[2]==null ? new BigDecimal(0) : (BigDecimal)obj[2]);
		}
		ListOrderedMap totalRowData = new ListOrderedMap();
		totalRowData.put("00", "总计");
		totalRowData.put("01", totalAmount);
		list.add(totalRowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/** 
	 * Description :  获取‘各品目年度采购总量分布图或‘各品目年度销售总量分布图’’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getPurcategorysQtyPieData")
	public ModelAndView getPurcategorysQtyPieData(HttpServletRequest request) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("year", request.getParameter("year")); //年份
		String userType = request.getParameter("userType"); //用户类型
		param.put("userType", userType);
		if("buyer".equals(userType)) {
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getPurcategorysQtyPieData(param);
		result.put("userType", userType);
		
		return new ModelAndView("purcategorysQtyPieDivView", result);
	}
	
	/** 
	 * Description :  导出‘各品目年度采购总量分布图’或‘各品目年度销售总量分布图’为Excel文件
	 * Create Date: 2011-7-11下午08:38:22 by likg  Modified Date: 2011-7-11下午08:38:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=exportPurcategorysQtyPieToExcel")
	public void exportPurcategorysQtyPieToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String year = request.getParameter("year"); //年份
		param.put("year", year);
		String userType = request.getParameter("userType"); //用户类型
		
		//第二行表头,定义列宽,文件名
		String[] title = null;
		String fileName = null;
		int[] columnWidths = new int[]{100, 200};
		if("buyer".equals(userType)) {
			fileName = year + "年各品目年度采购总量";
			title = new String[]{"", "采购量（订单数）"};
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			fileName = year + "年各品目年度销售总量";
			title = new String[]{"", "销售量（订单数）"};
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		String[][] columnNames = new String[][]{title};
		
		//取得数据
		List<Object[]> purcategorysQtyList = chartService.getPurcategorysQtyPieList(param);
		
		//组装数据
		BigDecimal totalQty = new BigDecimal(0);
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(Object[] obj : purcategorysQtyList) {
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("00", obj[1]);
			rowData.put("01", ((BigDecimal)obj[3]).intValue());
			list.add(rowData);
			totalQty = totalQty.add(obj[3]==null ? new BigDecimal(0) : (BigDecimal)obj[3]);
		}
		ListOrderedMap totalRowData = new ListOrderedMap();
		totalRowData.put("00", "总计");
		totalRowData.put("01", totalQty.intValue());
		list.add(totalRowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/** 
	 * Description :  获取‘月采购额和采购量同比、环比图’和‘月销售额和销售量同比、环比图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getAmountQtyCompareData")
	public ModelAndView getAmountQtyCompareData(HttpServletRequest request) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("year1", request.getParameter("year1")); //年份1
		param.put("year2", request.getParameter("year2")); //年份2
		String userType = request.getParameter("userType"); //用户类型
		param.put("userType", userType);
		if("supplier".equals(userType)) {
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		} else {
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		}
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getAmountQtyCompareData(param);
		result.put("userType", userType);
		
		return new ModelAndView("amountQtyCompareDivView", result);
	}
	
	/** 
	 * Description :  导出‘月采购额和采购量同比、环比图’为Excel文件
	 * Create Date: 2011-7-11下午08:38:22 by likg  Modified Date: 2011-7-11下午08:38:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=exportAmountQtyCompareToExcel")
	public void exportAmountQtyCompareToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String year1 = request.getParameter("year1"); //年份1
		String year2 = request.getParameter("year2"); //年份2
		param.put("year1", year1);
		param.put("year2", year2);
		String userType = request.getParameter("userType"); //用户类型
		
		//第二、三行表头,定义列宽,文件名
		String[] title1 = null;
		String[] title2 = new String[]{"", year1+"年", year2+"年", year1+"年", year2+"年"};
		int[] columnWidths = new int[]{100, 120, 120, 120, 120};
		String fileName = null;
		if("buyer".equals(userType)) {
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
			title1 = new String[]{"", "采购额（元）", "#", "采购量（订单数）", "#"};
			fileName = year1+"VS"+year2+"年月采购额和采购量同比、环比";
		} else {
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
			title1 = new String[]{"", "销售额（元）", "#", "销售量（订单数）", "#"};
			fileName = year1+"VS"+year2+"年月销售额和销售量同比、环比";
		}
		String[][] columnNames = new String[][]{title1, title2};
		
		//取得数据
		Map<String,Object> result = chartService.getAmountQtyCompareList(param);
		List<BigDecimal[]> amountQtyList1 = (List<BigDecimal[]>) result.get("amountQtyList1");
		List<BigDecimal[]> amountQtyList2 = (List<BigDecimal[]>) result.get("amountQtyList2");
		
		//组装数据
		BigDecimal totalAmount1 = new BigDecimal(0);
		BigDecimal totalAmount2 = new BigDecimal(0);
		BigDecimal totalQty1 = new BigDecimal(0);
		BigDecimal totalQty2 = new BigDecimal(0);
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(int i=0; i<12; i++) {
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("00", (i+1)+"月");
			rowData.put("01", (amountQtyList1.get(i)[1]==null ? "" : amountQtyList1.get(i)[1]));
			rowData.put("02", (amountQtyList2.get(i)[1]==null ? "" : amountQtyList2.get(i)[1]));
			rowData.put("03", (amountQtyList1.get(i)[3]==null ? "" : amountQtyList1.get(i)[3].intValue()));
			rowData.put("04", (amountQtyList2.get(i)[3]==null ? "" : amountQtyList2.get(i)[3].intValue()));
			list.add(rowData);
			totalAmount1 = totalAmount1.add(amountQtyList1.get(i)[1]==null ? new BigDecimal(0) : amountQtyList1.get(i)[1]);
			totalAmount2 = totalAmount2.add(amountQtyList2.get(i)[1]==null ? new BigDecimal(0) : amountQtyList2.get(i)[1]);
			totalQty1 = totalQty1.add(amountQtyList1.get(i)[3]==null ? new BigDecimal(0) : amountQtyList1.get(i)[3]);
			totalQty2 = totalQty2.add(amountQtyList2.get(i)[3]==null ? new BigDecimal(0) : amountQtyList2.get(i)[3]);
		}
		ListOrderedMap totalRowData = new ListOrderedMap();
		totalRowData.put("00", "总计");
		totalRowData.put("01", totalAmount1);
		totalRowData.put("02", totalAmount2);
		totalRowData.put("03", totalQty1.intValue());
		totalRowData.put("04", totalQty2.intValue());
		list.add(totalRowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/** 
	 * Description :  获取‘各采购方式月采购额分布比例图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getEbuymethodAmountData")
	public ModelAndView getEbuymethodAmountData(HttpServletRequest request) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("year", request.getParameter("year")); //年份
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getEbuymethodAmountData(param);
		
		return new ModelAndView("ebuymethodAmountDivView", result);
	}
	
	/** 
	 * Description :  导出‘各采购方式月采购额分布比例图’为Excel文件
	 * Create Date: 2011-7-11下午08:38:22 by likg  Modified Date: 2011-7-11下午08:38:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=exportEbuymethodAmountToExcel")
	public void exportEbuymethodAmountToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String year = request.getParameter("year"); //年份
		param.put("year", year);
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		
		//第二行表头,定义列宽
		String[] title = new String[]{"", "直接订购（元）", "议价（元）", "竞价（元）", "总额（元）"};
		int[] columnWidths = new int[]{80, 120, 120, 120, 120};
		String[][] columnNames = new String[][]{title};
		
		//文件名
		String fileName = year+"年各采购方式月采购额分布比例";
		
		//取得数据
		Map<String,Object> result = chartService.getEbuymethodAmountList(param);
		List<BigDecimal[]> amountList1 = (List<BigDecimal[]>) result.get("amountList1");
		List<BigDecimal[]> amountList2 = (List<BigDecimal[]>) result.get("amountList2");
		List<BigDecimal[]> amountList3 = (List<BigDecimal[]>) result.get("amountList3");
		
		//组装数据
		BigDecimal totalAmount1 = new BigDecimal(0);
		BigDecimal totalAmount2 = new BigDecimal(0);
		BigDecimal totalAmount3 = new BigDecimal(0);
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(int i=0; i<12; i++) {
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("00", (i+1)+"月");
			rowData.put("01", (amountList1.get(i)[0]==null ? "" : amountList1.get(i)[0]));
			rowData.put("02", (amountList2.get(i)[0]==null ? "" : amountList2.get(i)[0]));
			rowData.put("03", (amountList3.get(i)[0]==null ? "" : amountList3.get(i)[0]));
			rowData.put("04", (amountList1.get(i)[0]==null ? new BigDecimal(0) : amountList1.get(i)[0]).add(amountList2.get(i)[0]==null ? new BigDecimal(0) : amountList2.get(i)[0]).add(amountList3.get(i)[0]==null ? new BigDecimal(0) : amountList3.get(i)[0]));
			list.add(rowData);
			totalAmount1 = totalAmount1.add(amountList1.get(i)[0]==null ? new BigDecimal(0) : amountList1.get(i)[0]);
			totalAmount2 = totalAmount2.add(amountList2.get(i)[0]==null ? new BigDecimal(0) : amountList2.get(i)[0]);
			totalAmount3 = totalAmount3.add(amountList3.get(i)[0]==null ? new BigDecimal(0) : amountList3.get(i)[0]);
		}
		ListOrderedMap totalRowData = new ListOrderedMap();
		totalRowData.put("00", "总计");
		totalRowData.put("01", totalAmount1);
		totalRowData.put("02", totalAmount2);
		totalRowData.put("03", totalAmount3);
		totalRowData.put("04", totalAmount1.add(totalAmount2).add(totalAmount3));
		list.add(totalRowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/** 
	 * Description :  获取‘各采购方式月采购量分布比例图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getEbuymethodQtyData")
	public ModelAndView getEbuymethodQtyData(HttpServletRequest request) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("year", request.getParameter("year")); //年份
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getEbuymethodQtyData(param);
		
		return new ModelAndView("ebuymethodQtyDivView", result);
	}
	
	/** 
	 * Description :  导出‘各采购方式月采购量分布比例图’为Excel文件
	 * Create Date: 2011-7-11下午08:38:22 by likg  Modified Date: 2011-7-11下午08:38:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=exportEbuymethodQtyToExcel")
	public void exportEbuymethodQtyToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String year = request.getParameter("year"); //年份
		param.put("year", year);
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		
		//第二行表头,定义列宽
		String[] title = new String[]{"", "直接订购（订单数）", "议价（订单数）", "竞价（订单数）", "总量（订单数）"};
		int[] columnWidths = new int[]{80, 120, 120, 120, 120};
		String[][] columnNames = new String[][]{title};
		
		//文件名
		String fileName = year+"年各采购方式月采购量分布比例";
		
		//取得数据
		Map<String,Object> result = chartService.getEbuymethodQtyList(param);
		List<BigDecimal[]> qtyList1 = (List<BigDecimal[]>) result.get("qtyList1");
		List<BigDecimal[]> qtyList2 = (List<BigDecimal[]>) result.get("qtyList2");
		List<BigDecimal[]> qtyList3 = (List<BigDecimal[]>) result.get("qtyList3");
		
		//组装数据
		BigDecimal totalQty1 = new BigDecimal(0);
		BigDecimal totalQty2 = new BigDecimal(0);
		BigDecimal totalQty3 = new BigDecimal(0);
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(int i=0; i<12; i++) {
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("00", (i+1)+"月");
			rowData.put("01", (qtyList1.get(i)[1]==null ? "" : qtyList1.get(i)[1].intValue()));
			rowData.put("02", (qtyList2.get(i)[1]==null ? "" : qtyList2.get(i)[1].intValue()));
			rowData.put("03", (qtyList3.get(i)[1]==null ? "" : qtyList3.get(i)[1].intValue()));
			rowData.put("04", (qtyList1.get(i)[1]==null ? new BigDecimal(0) : qtyList1.get(i)[1]).add(qtyList2.get(i)[1]==null ? new BigDecimal(0) : qtyList2.get(i)[1]).add(qtyList3.get(i)[1]==null ? new BigDecimal(0) : qtyList3.get(i)[1]).intValue());
			list.add(rowData);
			totalQty1 = totalQty1.add(qtyList1.get(i)[1]==null ? new BigDecimal(0) : qtyList1.get(i)[1]);
			totalQty2 = totalQty2.add(qtyList2.get(i)[1]==null ? new BigDecimal(0) : qtyList2.get(i)[1]);
			totalQty3 = totalQty3.add(qtyList3.get(i)[1]==null ? new BigDecimal(0) : qtyList3.get(i)[1]);
		}
		ListOrderedMap totalRowData = new ListOrderedMap();
		totalRowData.put("00", "总计");
		totalRowData.put("01", totalQty1.intValue());
		totalRowData.put("02", totalQty2.intValue());
		totalRowData.put("03", totalQty3.intValue());
		totalRowData.put("04", totalQty1.add(totalQty2).add(totalQty3).intValue());
		list.add(totalRowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/** 
	 * Description :  获取‘各采购方式年度采购额分布图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getEbuymethodAmountPieData")
	public ModelAndView getEbuymethodAmountPieData(HttpServletRequest request) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("year", request.getParameter("year")); //年份
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getEbuymethodAmountPieData(param);
		
		return new ModelAndView("ebuymethodAmountPieDivView", result);
	}
	
	/** 
	 * Description :  导出‘各采购方式年度采购额分布图’为Excel文件
	 * Create Date: 2011-7-11下午08:38:22 by likg  Modified Date: 2011-7-11下午08:38:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=exportEbuymethodAmountPieToExcel")
	public void exportEbuymethodAmountPieToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String year = request.getParameter("year"); //年份
		param.put("year", year);
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		
		//第二行表头,定义列宽
		String[] title = new String[]{"", "采购额（元）"};
		int[] columnWidths = new int[]{100, 150};
		String[][] columnNames = new String[][]{title};
		
		//文件名
		String fileName = year+"年各采购方式年度采购额";
		
		//取得数据
		List<Object[]> ebuymethodAmountList = chartService.getEbuymethodAmountPieList(param);
		
		//组装数据
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		ListOrderedMap rowData = new ListOrderedMap();
		BigDecimal amount1 = (ebuymethodAmountList.get(0)[1]==null ? new BigDecimal(0) : (BigDecimal)ebuymethodAmountList.get(0)[1]);
		rowData.put("00", "直接订购");
		rowData.put("01", amount1);
		list.add(rowData);
		
		rowData = new ListOrderedMap();
		BigDecimal amount2 = (ebuymethodAmountList.get(1)[1]==null ? new BigDecimal(0) : (BigDecimal)ebuymethodAmountList.get(1)[1]);
		rowData.put("00", "议价");
		rowData.put("01", amount2);
		list.add(rowData);
		
		rowData = new ListOrderedMap();
		BigDecimal amount3 = (ebuymethodAmountList.get(2)[1]==null ? new BigDecimal(0) : (BigDecimal)ebuymethodAmountList.get(2)[1]);
		rowData.put("00", "竞价");
		rowData.put("01", amount3);
		list.add(rowData);
		
		rowData = new ListOrderedMap();
		rowData.put("00", "总计");
		rowData.put("01", amount1.add(amount2).add(amount3));
		list.add(rowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/** 
	 * Description :  获取‘各采购方式年度采购量分布图’的图表和列表数据
	 * Create Date: 2011-7-11上午09:17:18 by likg  Modified Date: 2011-7-11上午09:17:18 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getEbuymethodQtyPieData")
	public ModelAndView getEbuymethodQtyPieData(HttpServletRequest request) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("year", request.getParameter("year")); //年份
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getEbuymethodQtyPieData(param);
		
		return new ModelAndView("ebuymethodQtyPieDivView", result);
	}
	
	/** 
	 * Description :  导出‘各采购方式年度采购量分布图’为Excel文件
	 * Create Date: 2011-7-11下午08:38:22 by likg  Modified Date: 2011-7-11下午08:38:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=exportEbuymethodQtyPieToExcel")
	public void exportEbuymethodQtyPieToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String year = request.getParameter("year"); //年份
		param.put("year", year);
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		
		//第二行表头,定义列宽
		String[] title = new String[]{"", "采购量（订单数）"};
		int[] columnWidths = new int[]{100, 150};
		String[][] columnNames = new String[][]{title};
		
		//文件名
		String fileName = year+"年各采购方式年度采购量";
		
		//取得数据
		List<Object[]> ebuymethodQtyList = chartService.getEbuymethodQtyPieList(param);
		
		//组装数据
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		ListOrderedMap rowData = new ListOrderedMap();
		BigDecimal qty1 = (ebuymethodQtyList.get(0)[2]==null ? new BigDecimal(0) : (BigDecimal)ebuymethodQtyList.get(0)[2]);
		rowData.put("00", "直接订购");
		rowData.put("01", qty1.intValue());
		list.add(rowData);
		
		rowData = new ListOrderedMap();
		BigDecimal qty2 = (ebuymethodQtyList.get(1)[2]==null ? new BigDecimal(0) : (BigDecimal)ebuymethodQtyList.get(1)[2]);
		rowData.put("00", "议价");
		rowData.put("01", qty2.intValue());
		list.add(rowData);
		
		rowData = new ListOrderedMap();
		BigDecimal qty3 = (ebuymethodQtyList.get(2)[2]==null ? new BigDecimal(0) : (BigDecimal)ebuymethodQtyList.get(2)[2]);
		rowData.put("00", "竞价");
		rowData.put("01", qty3.intValue());
		list.add(rowData);
		
		rowData = new ListOrderedMap();
		rowData.put("00", "总计");
		rowData.put("01", qty1.add(qty2).add(qty3).intValue());
		list.add(rowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/** 
	 * Description :  导出Excel文件
	 * Create Date: 2011-7-14下午12:07:21 by likg  Modified Date: 2011-7-14下午12:07:21 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private void exportToExcel(HttpServletResponse response, String title, String[][] colNames, int[] colWidths, List<ListOrderedMap> datas) throws Exception {
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "Attachment; filename="+ new String(title.getBytes("gbk"), "ISO8859-1" )+".xls");
		OutputStream outputStream = response.getOutputStream();
		
		ExcelNoTemplate excel = new ExcelNoTemplate(title, colNames, colWidths, datas, null, null);
		excel.setPaged(false);
		excel.setFirstDataColumnDisplayed(false);
		excel.prepare();
		excel.out(outputStream);
		
		outputStream.flush();
		outputStream.close();
		outputStream = null;
	}

	/**
	 * Description :  获取‘时间段内各商品销售量分布图’的图表和列表数据
	 * Create Date: 2011-7-28下午02:39:37 by zhaojf  Modified Date: 2011-7-28下午02:39:37 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=getDuringGoodsQtyPieData")
	public ModelAndView getDuringGoodsQtyPieData(HttpServletRequest request) throws Exception{
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("startDate", request.getParameter("startDate")); //开始时间
		param.put("endDate", request.getParameter("endDate")); //截止时间
		String userType = request.getParameter("userType"); //用户类型
		param.put("userType", userType);
		if("buyer".equals(userType)) {
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getDuringGoodsQtyPieData(param);
		result.put("userType", userType);
		
		return new ModelAndView("duringGoodsQtyPieDivView", result);
	}
	
	/**
	 * Description :  导出‘时间段内各商品销售量分布图’为Excel文件
	 * Create Date: 2011-7-29上午11:15:15 by zhaojf  Modified Date: 2011-7-29上午11:15:15 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=exportDuringGoodsQtyPieToExcel")
	public void exportDuringGoodsQtyPieToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("startDate", request.getParameter("startDate")); //开始时间
		param.put("endDate", request.getParameter("endDate")); //截止时间
		String startDate = request.getParameter("startDate"); 
		String endDate = request.getParameter("endDate"); 
		
		String subCaption = "";
		if(!"".equals(startDate) && !"".equals(endDate)){
			subCaption = subCaption +startDate+ "--" + endDate;
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
		
		//第二行表头,定义列宽,文件名
		String[] title = null;
		String fileName = null;
		int[] columnWidths = new int[]{100, 200};
		
		fileName = subCaption + "时间段内各商品销售量";
		title = new String[]{"商品名称", "销售量"};
		param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		
		String[][] columnNames = new String[][]{title};
		
		//取得数据
		List<Object[]> duringGoodsAmountList = chartService.getDuringGoodsAmountAndQtyPieList(param);
		
		//组装数据
		BigDecimal totalAmount = new BigDecimal(0);
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(Object[] obj : duringGoodsAmountList) {
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("00", obj[0]);
			rowData.put("01", obj[2]);
			list.add(rowData);
			totalAmount = totalAmount.add(obj[2]==null ? new BigDecimal(0) : (BigDecimal)obj[2]);
		}
		ListOrderedMap totalRowData = new ListOrderedMap();
		totalRowData.put("00", "总计");
		totalRowData.put("01", totalAmount);
		list.add(totalRowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/**
	 * Description :   获取‘时间段内各商品销售额分布图’的图表和列表数据
	 * Create Date: 2011-7-29上午11:01:19 by zhaojf  Modified Date: 2011-7-29上午11:01:19 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=getDuringGoodsAmountPieData")
	public ModelAndView getDuringGoodsAmountPieData(HttpServletRequest request) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("startDate", request.getParameter("startDate")); //开始时间
		param.put("endDate", request.getParameter("endDate")); //截止时间
		String userType = request.getParameter("userType"); //用户类型
		param.put("userType", userType);
		if("buyer".equals(userType)) {
			param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //采购人id
		} else {
			param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		}
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.getDuringGoodsAmountPieData(param);
		result.put("userType", userType);
		
		return new ModelAndView("duringGoodsAmountPieDataDivView", result);
	}
	
	/**
	 * Description :  导出‘时间段内各商品销售额分布图’为Excel文件
	 * Create Date: 2011-7-29上午11:15:15 by zhaojf  Modified Date: 2011-7-29上午11:15:15 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=exportDuringGoodsAmountPieToExcel")
	public void exportDuringGoodsAmountPieToExcel(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("startDate", request.getParameter("startDate")); //开始时间
		param.put("endDate", request.getParameter("endDate")); //截止时间
		String startDate = request.getParameter("startDate"); 
		String endDate = request.getParameter("endDate"); 
		
		String subCaption = "";
		if(!"".equals(startDate) && !"".equals(endDate)){
			subCaption = subCaption +startDate+ "--" + endDate;
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
		
		//第二行表头,定义列宽,文件名
		String[] title = null;
		String fileName = null;
		int[] columnWidths = new int[]{100, 200};
		
		fileName = subCaption + "时间段内各商品销售额";
		title = new String[]{"商品名称", "销售额（元）"};
		param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		
		String[][] columnNames = new String[][]{title};
		
		//取得数据
		List<Object[]> duringGoodsAmountList = chartService.getDuringGoodsAmountAndQtyPieList(param);
		
		//组装数据
		BigDecimal totalAmount = new BigDecimal(0);
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(Object[] obj : duringGoodsAmountList) {
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("00", obj[0]);
			rowData.put("01", obj[3]);
			list.add(rowData);
			totalAmount = totalAmount.add(obj[3]==null ? new BigDecimal(0) : (BigDecimal)obj[3]);
		}
		ListOrderedMap totalRowData = new ListOrderedMap();
		totalRowData.put("00", "总计");
		totalRowData.put("01", totalAmount);
		list.add(totalRowData);
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}

	/**
	 * Description :  获取‘商品在项目中的报价趋势图‘(供应商才报价)
	 * Create Date: 2011-8-4上午10:44:07 by zhaojf  Modified Date: 2011-8-4上午10:44:07 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=getGoodsProjectBiddingLineData")
	public ModelAndView getGoodsProjectBiddingLineData(HttpServletRequest request) throws Exception{
		Map<String,Object> param = new HashMap<String, Object>();
		Map<String,Object> result = new HashMap<String, Object>();
		String goodsId = request.getParameter("goodsId");
		param.put("goodsId", goodsId);//商品Id
		param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //当前供应商id
		param.put("currentUserId", AuthenticationHelper.getCurrentUser(true).getObjId());//当前用户Id
		
		//获取图表和列表数据
		result = chartService.getGoodsProjectBiddingLineData(param);
		
		return new ModelAndView("goodsProjectBiddingLineDataDivView",result);
	}
	
	/** 
	 * Description :  导出‘商品在项目中的报价趋势图’数据  
	 * Create Date: 2011-8-5上午10:13:54 by zhaojf  Modified Date: 2011-8-5上午10:13:54 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=exportGoodsProjectBiddingToExcel")
	public void exportGoodsProjectBiddingToExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> param = new HashMap<String, Object>();
		String goodsId = request.getParameter("goodsId");
		param.put("goodsId", goodsId);//商品Id
		param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //当前供应商id
		param.put("currentUserId", AuthenticationHelper.getCurrentUser(true).getObjId());//当前用户Id
		
		Goods goods = goodsService.get(goodsId);
		String goodsName = goods.getProductName().replace(" ", "");
		String subCaption = "商品'"+goodsName+"'在项目中我的报价趋势";
		
		//第二行表头,定义列宽,文件名
		String[] title = null;
		String fileName = null;
		int[] columnWidths = new int[]{100, 200};
		
		fileName = subCaption;
		title = new String[]{"项目名称", "报价（元）"};
		
		String[][] columnNames = new String[][]{title};
		
		//获取报价数据
		List<BiddingRecordDetail> goodsProjectBiddingList = chartService.getAllGoodsBiddingByProjectData(param);
		
		//组装数据
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(BiddingRecordDetail obj : goodsProjectBiddingList) {
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("00", obj.getProject().getProjName());
			rowData.put("01", obj.getGoodsPrice());
			list.add(rowData);
		}
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
	
	/**
	 * Description : 各季度中标情况对比图(供应商)
	 * Create Date: 2011-8-5下午02:35:09 by zhaojf  Modified Date: 2011-8-5下午02:35:09 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=gebBiddingWinnerRecordsByYearData")
	public ModelAndView gebBiddingWinnerRecordsByYearData(HttpServletRequest request) throws Exception{
		Map<String,Object> param = new HashMap<String, Object>();
		String year = request.getParameter("year"); //年份
		param.put("year", year);
		
		param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //供应商id
		
		//获取图表和列表数据
		Map<String, Object> result = chartService.gebBiddingWinnerRecordsByYearData(param);
		return new ModelAndView("biddingWinnerRecordsByYearDataView",result);
	}
	
	/**
	 * Description :  到出‘各季度中标情况对比数据(供应商)’
	 * Create Date: 2011-8-8下午04:45:46 by zhaojf  Modified Date: 2011-8-8下午04:45:46 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=exportBiddingWinnerRecordsColumnToExcel")
	public void exportBiddingWinnerRecordsColumnToExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> param = new HashMap<String, Object>();
		Map<String,Object> result = new HashMap<String, Object>();
		String year = request.getParameter("year");
		param.put("year", year);//商品Id
		param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //当前供应商id
		
		String subCaption = year + "年各季度项目中标情况";
		
		//第二行表头,定义列宽,文件名
		String[] title = null;
		String fileName = null;
		int[] columnWidths = new int[]{150, 100 ,100};
		
		fileName = subCaption;
		title = new String[]{"", "中标数","未中标数"};
		
		String[][] columnNames = new String[][]{title};
		
		//获取报价数据
		result = chartService.gebBiddingWinnerRecordsByYearData(param);
		List<Object []> all_biddingWinnerList = (List<Object[]>) result.get("all_biddingWinnerList");
		
		//组装数据
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		for(Object[] obj : all_biddingWinnerList) {
			ListOrderedMap rowData = new ListOrderedMap();
			rowData.put("00", obj[0]);
			rowData.put("01", obj[1]);
			rowData.put("02", obj[2]);
			list.add(rowData);
		}
		
		//执行导出
		this.exportToExcel(response, fileName, columnNames, columnWidths, list);
	}
}
