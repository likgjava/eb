package com.gpcsoft.epp.Statistics.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.Statistics.service.StatisticsService;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.xml.SingleSeriesXmlBuilder;
import com.gpcsoft.srplatform.logs.domain.LoginLogs;

/**
 * @gpcsoft.controller
 * @gpcsoft.view value="loginAnalysisView"
 *  url="view/srplatform/auth/Analysis.jsp"
 */
@Controller//标识为控制器
@Scope("request")//必须
@RequestMapping("/StatisticsController.do")//页面请求路径,可修改
public class StatisticsController extends AnnotationMultiController<LoginLogs> {
	@Autowired(required=true) @Qualifier("statisticsServiceImpl")
	private StatisticsService statisticsService;
	
		
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	/** 
	 * Description :  代理机构项目负责人
	 * Create Date: 2010-10-15下午04:57:04 by yangx  Modified Date: 2010-10-15下午04:57:04 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=cgfsShowForAgent")//采购方式
	public void cgfsShowForAgent(HttpServletResponse response) throws Exception {
		User user=AuthenticationHelper.getCurrentUser();
		List li=statisticsService.statisticsEbuyMethodForAgent(user.getEmp().getObjId());
		List<ListOrderedMap> rData = this.getListOrderedMap(li);		
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("采购方式分析");//设置图形标题
		builder.setXAxisName("采购方式分析");//设置图形x轴显示名称
		builder.setYAxisName(messageSource.getMessage("loginAnalysis.yAxisName"));//设置图形y轴显示名称
		builder.setNamePosition(0);//设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);//设置y轴显示的值在结果集中的序号
		builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setBorderColor("ffffff");  //图表边框色
		builder.setBgColor("ffffff");      //图表背景色
		String str = builder.buildXml(rData);
		response.getWriter().write(str);
		response.getWriter().close();
	}
	
	/** 
	 * Description :  代理机构管理员
	 * Create Date: 2010-10-15下午04:57:04 by yangx  Modified Date: 2010-10-15下午04:57:04 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=cgfsShowForAgentManager")//采购方式
	public void cgfsShowForAgentManager(HttpServletResponse response) throws Exception {
		User user=AuthenticationHelper.getCurrentUser();
		List li=statisticsService.statisticsEbuyMethodForAgentManager((user.getOrgInfo()).getObjId());
		List<ListOrderedMap> rData = this.getListOrderedMap(li);
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("采购方式分析");//设置图形标题
		builder.setXAxisName("采购方式分析");//设置图形x轴显示名称
		builder.setYAxisName(messageSource.getMessage("loginAnalysis.yAxisName"));//设置图形y轴显示名称
		builder.setNamePosition(0);//设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);//设置y轴显示的值在结果集中的序号
		builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setBorderColor("ffffff");  //图表边框色
		builder.setBgColor("ffffff");      //图表背景色
		String str = builder.buildXml(rData);
		response.getWriter().write(str);
		response.getWriter().close();
	}
	
	/** 
	 * Description :  监管单位经办人
	 * Create Date: 2010-10-15下午04:57:04 by yangx  Modified Date: 2010-10-15下午04:57:04 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=cgfsShowForSupervise")//采购方式
	public void cgfsShowForSupervise(HttpServletResponse response) throws Exception {
		User user=AuthenticationHelper.getCurrentUser();
		List li=statisticsService.statisticsEbuyMethodForSupervise(user.getEmp().getObjId());
		List<ListOrderedMap> rData = this.getListOrderedMap(li);
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("采购方式分析");//设置图形标题
		builder.setXAxisName("采购方式分析");//设置图形x轴显示名称
		builder.setYAxisName(messageSource.getMessage("loginAnalysis.yAxisName"));//设置图形y轴显示名称
		builder.setNamePosition(0);//设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);//设置y轴显示的值在结果集中的序号
		builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setBorderColor("ffffff");  //图表边框色
		builder.setBgColor("ffffff");      //图表背景色
		String str = builder.buildXml(rData);
		response.getWriter().write(str);
		response.getWriter().close();
	}
	
	/** 
	 * Description :  监管单位管理员
	 * Create Date: 2010-10-15下午04:57:04 by yangx  Modified Date: 2010-10-15下午04:57:04 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=cgfsShowForSuperviseManager")//采购方式
	public void cgfsShowForSuperviseManager(HttpServletResponse response) throws Exception {
		List li=statisticsService.statisticsEbuyMethodForSuperviseManager();
		List<ListOrderedMap> rData = this.getListOrderedMap(li);
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("采购方式分析");//设置图形标题
		builder.setXAxisName("采购方式分析");//设置图形x轴显示名称
		builder.setYAxisName(messageSource.getMessage("loginAnalysis.yAxisName"));//设置图形y轴显示名称
		builder.setNamePosition(0);//设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);//设置y轴显示的值在结果集中的序号
		builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setBorderColor("ffffff");  //图表边框色
		builder.setBgColor("ffffff");      //图表背景色
		String str = builder.buildXml(rData);
		response.getWriter().write(str);
		response.getWriter().close();
	}
	
	/** 
	 * Description :  采购人
	 * Create Date: 2010-10-15下午04:57:04 by yangx  Modified Date: 2010-10-15下午04:57:04 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=cgfsShowForBuyer")//采购方式
	public void cgfsShowForBuyer(HttpServletResponse response) throws Exception {
		User user=AuthenticationHelper.getCurrentUser();
		List li=statisticsService.statisticsEbuyMethodForBuyer((user.getOrgInfo()).getObjId());
		List<ListOrderedMap> rData = this.getListOrderedMap(li);
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("采购方式分析");//设置图形标题
		builder.setXAxisName("采购方式分析");//设置图形x轴显示名称
		builder.setYAxisName(messageSource.getMessage("loginAnalysis.yAxisName"));//设置图形y轴显示名称
		builder.setNamePosition(0);//设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);//设置y轴显示的值在结果集中的序号
		builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setBorderColor("ffffff");  //图表边框色
		builder.setBgColor("ffffff");      //图表背景色
		String str = builder.buildXml(rData);
		response.getWriter().write(str);
		response.getWriter().close();
	}
	
	/** 
	 * Description :  业务处室
	 * Create Date: 2010-10-15下午04:57:04 by yangx  Modified Date: 2010-10-15下午04:57:04 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=cgfsShowForGovernment")//采购方式
	public void cgfsShowForGovernment(HttpServletResponse response) throws Exception {
		User user=AuthenticationHelper.getCurrentUser();
		Department department = userApiService.getDepartmentByUserId(user.getObjId());
		List li=statisticsService.statisticsEbuyMethodForGovernment(department.getObjId());
		List<ListOrderedMap> rData = this.getListOrderedMap(li);
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("采购方式分析");//设置图形标题
		builder.setXAxisName("采购方式分析");//设置图形x轴显示名称
		builder.setYAxisName(messageSource.getMessage("loginAnalysis.yAxisName"));//设置图形y轴显示名称
		builder.setNamePosition(0);//设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);//设置y轴显示的值在结果集中的序号
		builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setBorderColor("ffffff");  //图表边框色
		builder.setBgColor("ffffff");      //图表背景色
		String str = builder.buildXml(rData);
		response.getWriter().write(str);
		response.getWriter().close();
	}
	
	
	/** 
	 * Description :  供应商
	 * Create Date: 2010-10-15下午04:57:04 by yangx  Modified Date: 2010-10-15下午04:57:04 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=cgfsShowForSupply")//采购方式
	public void cgfsShowForSupply(HttpServletResponse response) throws Exception {
		User user=AuthenticationHelper.getCurrentUser();
		List li=statisticsService.statisticsEbuyMethodForSupply((user.getOrgInfo()).getObjId());
		List<ListOrderedMap> rData = this.getListOrderedMap(li);
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("采购方式分析");//设置图形标题
		builder.setXAxisName("采购方式分析");//设置图形x轴显示名称
		builder.setYAxisName(messageSource.getMessage("loginAnalysis.yAxisName"));//设置图形y轴显示名称
		builder.setNamePosition(0);//设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);//设置y轴显示的值在结果集中的序号
		builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setBorderColor("ffffff");  //图表边框色
		builder.setBgColor("ffffff");      //图表背景色
		String str = builder.buildXml(rData);
		response.getWriter().write(str);
		response.getWriter().close();
	}
	
	@RequestMapping(params = "method=cgflShow")//采购分类
	public void cgflShow(HttpServletResponse response) throws Exception {
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
			ListOrderedMap map = new ListOrderedMap();
			map.put("date", "工程类");
			map.put("count", "5");
			list.add(map);
			ListOrderedMap map1 = new ListOrderedMap();
			map1.put("date", "服务类");
			map1.put("count", "3");
			list.add(map1);
			ListOrderedMap map2 = new ListOrderedMap();
			map2.put("date", "维护类");
			map2.put("count", "1");
			list.add(map2);
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("采购分类");//设置图形标题
		builder.setXAxisName("采购分类");//设置图形x轴显示名称
		builder.setYAxisName(messageSource.getMessage("loginAnalysis.yAxisName"));//设置图形y轴显示名称
		builder.setNamePosition(0);//设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);//设置y轴显示的值在结果集中的序号
		builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setBorderColor("ffffff");  //图表边框色
		builder.setBgColor("ffffff");      //图表背景色
		String str = builder.buildXml(list);
		response.getWriter().write(str);
		response.getWriter().close();
    }
	
	@RequestMapping(params = "method=pmpmShow")//品目排名
	public void pmpmShow(HttpServletResponse response) throws Exception {
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
			ListOrderedMap map = new ListOrderedMap();
			map.put("date", "计算机");
			map.put("count", "50");
			list.add(map);
			ListOrderedMap map1 = new ListOrderedMap();
			map1.put("date", "防火墙");
			map1.put("count", "88");
			list.add(map1);
			ListOrderedMap map2 = new ListOrderedMap();
			map2.put("date", "电视机");
			map2.put("count", "69");
			list.add(map2);
			ListOrderedMap map3 = new ListOrderedMap();
			map3.put("date", "空气调节设备");
			map3.put("count", "50");
			list.add(map3);
			ListOrderedMap map4 = new ListOrderedMap();
			map4.put("date", "复印机");
			map4.put("count", "76");
			list.add(map4);
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("品目分析");//设置图形标题
		builder.setXAxisName("品目");//设置图形x轴显示名称
		builder.setYAxisName(messageSource.getMessage("loginAnalysis.yAxisName"));//设置图形y轴显示名称
		builder.setNamePosition(0);//设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);//设置y轴显示的值在结果集中的序号
		builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setLabelDisplay("");
		builder.setSlantLabels("");
		builder.setBorderColor("ffffff");  //图表边框色
		builder.setBgColor("ffffff");      //图表背景色
		String str = builder.buildXml(list);
		response.getWriter().write(str);
		response.getWriter().close();
    }
	
	@RequestMapping(params = "method=jefxShow")//金额分析
	public void jefxShow(HttpServletResponse response) throws Exception {
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
			ListOrderedMap map = new ListOrderedMap();
			map.put("date", "1月");
			map.put("count", "46");
			list.add(map);
			ListOrderedMap map1 = new ListOrderedMap();
			map1.put("date", "2月");
			map1.put("count", "85");
			list.add(map1);
			ListOrderedMap map2 = new ListOrderedMap();
			map2.put("date", "3月");
			map2.put("count", "67");
			list.add(map2);
			ListOrderedMap map3 = new ListOrderedMap();
			map3.put("date", "4月");
			map3.put("count", "49");
			list.add(map3);
			ListOrderedMap map4 = new ListOrderedMap();
			map4.put("date", "5月");
			map4.put("count", "76");
			list.add(map4);
			ListOrderedMap map5 = new ListOrderedMap();
			map5.put("date", "6月");
			map5.put("count", "96");
			list.add(map5);
			ListOrderedMap map6 = new ListOrderedMap();
			map6.put("date", "7月");
			map6.put("count", "62");
			list.add(map6);
			ListOrderedMap map7 = new ListOrderedMap();
			map7.put("date", "8月");
			map7.put("count", "62");
			list.add(map7);
			ListOrderedMap map8 = new ListOrderedMap();
			map8.put("date", "9月");
			map8.put("count", "37");
			list.add(map8);
			ListOrderedMap map9 = new ListOrderedMap();
			map9.put("date", "10月");
			map9.put("count", "49");
			list.add(map9);
			ListOrderedMap map10 = new ListOrderedMap();
			map10.put("date", "11月");
			map10.put("count", "76");
			list.add(map10);
			ListOrderedMap map11 = new ListOrderedMap();
			map11.put("date", "12月");
			map11.put("count", "96");
			list.add(map11);
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("资金分析");//设置图形标题
		builder.setXAxisName("月份");//设置图形x轴显示名称
		builder.setYAxisName("资金");//设置图形y轴显示名称
		builder.setNamePosition(0);//设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);//设置y轴显示的值在结果集中的序号
		//builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setLabelDisplay("");
		builder.setSlantLabels("");
		builder.setBorderColor("ffffff");  //图表边框色
		builder.setBgColor("ffffff");      //图表背景色
		String str = builder.buildXml(list);
		
		response.getWriter().write(str);
		response.getWriter().close();
    }
	
	/** 
	 * Description :  组装查询出来的数据
	 * Create Date: 2010-12-29下午01:46:59 by yangx  Modified Date: 2010-12-29下午01:46:59 by yangx
	 * @param   li:要组装的数据集合
	 * @return  List<ListOrderedMap>
	 * @Exception   
	 */
	private List<ListOrderedMap> getListOrderedMap(List li){
		List<ListOrderedMap> rData = new ArrayList<ListOrderedMap>();
		for (int i = 0; i < li.size(); ++i) {
		      Object[] ob = (Object[])li.get(i);
		      ListOrderedMap map = new ListOrderedMap();
		      map.put("date", EbuyMethodEnum.getEBuyMethodCN(ob[1].toString()));
		      map.put("count", Integer.valueOf(Integer.parseInt(ob[0].toString())));
		      rData.add(map);
		}
		return rData;
	}
}
