package com.gpcsoft.epp.taskplan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.consign.service.ConsignTaskPlanService;
import com.gpcsoft.epp.taskplan.domain.BlockTrade;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.service.BlockTradeService;

/** 
 *  Comments: <strong>抽取代理机构</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 权限平台     		
 *  <br/>Create Date：2010-10-22 上午10:34:58 by liuy    					                            
 *  <br/>Modified Date:  2010-10-22 上午10:34:58 by liuy  
 */
/**
  * @gpcsoft.view value="blockTaskPlanDetailView"
  *  url="view/es/planform/taskplan/blockTaskPlanDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BlockTrade.class})
@RequestMapping("/BlockTradeController.do")//页面请求路径,可修改
public class BlockTradeController extends AnnotationMultiController<BlockTrade> {

	@Autowired(required=true) @Qualifier("blockTradeServiceImpl")
	private BlockTradeService blockTradeService;
	
	@Autowired(required=true) @Qualifier("consignTaskPlanServiceImpl")
	private ConsignTaskPlanService consignTaskPlanService;
	
	/** 
	 * FunName:getAgentForRandom
	 * Description: 随机抽取代理机构
	 * @param: request
	 * @return ModelAndView
	 * @author: shenjianzhuang
	 * @Create Date: 2010-12-14下午03:23:10 
	 * @Modifier: shenjianzhuang
	 * @Modified Date: 2010-12-14下午03:23:10  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getAgentForRandom")
	public ModelAndView getAgentForRandom(HttpServletRequest request) throws Exception {
		logger.debug("\nes=BlockTradeController||getAgentForRandom\n");
		OrgInfo orginfo = blockTradeService.getAgentForRandom();
		Map model=new HashMap();
		model.put("company",orginfo);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName: getWinAgent
	 * Description:判断获胜的代理机构
	 * @param: request
	 * @return  ModelAndView
	 * @author: shenjianzhuang
	 * @Create Date: 2010-12-14下午03:23:10 
	 * @Modifier: shenjianzhuang
	 * @Modified Date: 2010-12-14下午03:23:10  
	 */	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getWinAgent")
	public ModelAndView getWinAgent(HttpServletRequest request) throws Exception {
		logger.debug("\nes=BlockTradeController||getAgentForRandom\n");
		String org_id = request.getParameter("org_id");
		OrgInfo orginfo =blockTradeService.getWinAgent(org_id);
		Map model=new HashMap();
		if(orginfo!=null){
			model.put("company",orginfo);
		}else{
			model.put("company","false");
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName: getBlockTradeList
	 * Description :  获取待处理大宗采购申报书
	 * @param: request
	 * @return  ModelAndView
	 * @author: shenjianzhuang
	 * @Create Date: 2010-12-14下午03:23:10 
	 * @Modifier: shenjianzhuang
	 * @Modified Date: 2010-12-14下午03:23:10  
	 */	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getBlockTradeList")
	public ModelAndView getBlockTradeList(HttpServletRequest request) throws Exception {
		logger.debug("\nes=BlockTradeController||getBlockTradeList\n");
		String taskCode = request.getParameter("taskCode");
		String applyDate = request.getParameter("applyDate");
		Page page = prePage(request);//预分页,算出当前页和大小等
		Page<TaskPlan> pageDate = blockTradeService.getTaskPlanPage(taskCode,applyDate, page);
		Map<String, Object> model = new HashMap<String, Object>();
		endPage(model, pageDate, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName: showDetail
	 * Description :  显示大宗的详细信息
	 * @param: objId:申报书主键,request
	 * @return  ModelAndView
	 * @author: shenjianzhuang
	 * @Create Date: 2010-12-14下午03:23:10 
	 * @Modifier: shenjianzhuang
	 * @Modified Date: 2010-12-14下午03:23:10  
	 */	
	@RequestMapping(params = "method=showDetail")
	public ModelAndView showDetail(String objId,HttpServletRequest request)throws Exception {
		logger.debug("\nes=BlockTradeController||showDetail\n");
		String type = request.getParameter("type");
		String isAudit = request.getParameter("isAudit");
		TaskPlan taskPlan = new TaskPlan();
		taskPlan.setObjId(objId);
		List<TaskPlan> taskPlanList = null;//获得申报书//获得申报书
		if(null!=isAudit && isAudit.equals("isAudit")){
			taskPlanList = blockTradeService.getTaskPlanListByObjId(objId);
		}else{
			 taskPlanList = blockTradeService.getTaskPlanList(taskPlan);
		}
	  	List<Consign> ctList = consignTaskPlanService.getConsignByTaskPlan(objId);	//获得委托协议
		Map<String,Object> model = new HashMap<String,Object>();
		if("forSum".equals(type)) {//表示从汇总列表查看详情
			model.put("type", type);
		}
		if(taskPlanList!=null&&taskPlanList.size()>0){
			model.put("taskPlan", taskPlanList.get(0));
		}else{
			model.put("taskPlan",null);
		}
		model.put("ctList", ctList);
		return new ModelAndView("blockTaskPlanDetailView", model);
	}	
	
}
