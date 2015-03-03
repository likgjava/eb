package com.gpcsoft.bizplatform.agency.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.agency.service.AgencyService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.EvaluateService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="agencyShowListView"
  *  url="view/bizplatform/agency/showagency/show_agency_list.jsp"
  * @gpcsoft.view value="agencyShowForListView"
  *  url="view/bizplatform/agency/showagency/agency_list_div_l.jsp"
  * @gpcsoft.view value="agencyShowInfoView"
  *  url="view/bizplatform/agency/showagency/show_agency_detail.jsp"
  * @gpcsoft.view value="agencyRecommendView"
  *  url="view/bizplatform/agency/showagency/agency_recommend_list.jsp"
  * @gpcsoft.view value="agencyRecommendIndexView"
  *  url="view/srplatform/portal/include/agency_recommend_index_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/AgencyShowController.do")//页面请求路径,可修改
public class AgencyShowController extends AnnotationMultiController<GpcBaseObject> {

	@Autowired(required=true) @Qualifier("agencyServiceImpl")
	private AgencyService agencyService;
    
	@Autowired(required=true) @Qualifier("evaluateServiceImpl")
	private EvaluateService evaluateService;
	
    /** 
     * Description :  跳转到代理机构展示的二级页面，也就是列表页面
     * Create Date: 2010-7-27上午10:02:32 by liangxj  Modified Date: 2010-7-27上午10:02:32 by liangxj
     * @param   categoryId 品目id categoryCode 品目编号
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toAgencyList")
    public ModelAndView toAgencyList(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
    	
    	/** adminGrd和agentType 可能来自与详情页面的参数；evalSort和keyWord来自与首页的参数 */
    	String adminGrd = request.getParameter("adminGrd");
    	String agentType = request.getParameter("agentType");
    	String evalSort = request.getParameter("evalSort");
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
    	
    	paramsMap.put("adminGrd",adminGrd);
    	paramsMap.put("agentType",agentType);
    	paramsMap.put("evalSort", evalSort);
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(keyWord));
    	
    	
    	/** 获得代理机构类型*/
    	List<String[]> agentTypeList = agencyService.getAgentTypeListShow();
    	
    	for (Object[] aType : agentTypeList) {
			for (String type : enumService.getEnum(OrganizationEnum.AGENT_AGENTTYPE)) {
				String[] temp = type.split(":");
				if(((String)aType[0]).equals(temp[0])) {
					aType[1] = temp[1];
				}
			}
		}
    	model.put("agentTypeList", agentTypeList);
    	
    	/** 获得行政级别*/
    	List<String[]> adminGrdList = agencyService.getAdminGrdListShow(request.getParameter("agentType"));
    	for (Object[] aGrd : adminGrdList) {
			for (String grd : enumService.getEnum(OrganizationEnum.AGENT_ADMINGRD)) {
				String[] temp = grd.split(":");
				if(aGrd[0].equals(temp[0])) {
					aGrd[1] = temp[1];
				}
			}
		}
    	model.put("adminGrdList", adminGrdList);
    	
    	/** 取代理机构信息*/
    	Page<Agency> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Agency> pageData = agencyService.getAgencyListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
    	/** 回设高亮参数或首页参数 */
    	model.put("keyWord",StringUtils.returnIngoreStr(keyWord));
    	model.put("evalSort", evalSort);
    	model.put("adminGrd", adminGrd);
    	model.put("agentType", agentType);

        return new ModelAndView("agencyShowListView",model);
    }
    
    
	/** 
	 * Description :  获取代理机构展示的列表数据
	 * Create Date: 2010-7-27上午11:46:10 by liangxj  Modified Date: 2010-7-27上午11:46:10 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getAgencyForList")   
	public ModelAndView getAgencyForList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
    	
    	paramsMap.put("validSort", request.getParameter("validSort"));
    	paramsMap.put("evalSort", request.getParameter("evalSort"));
    	paramsMap.put("adminGrd", request.getParameter("adminGrd"));
    	paramsMap.put("agentType", request.getParameter("agentType"));
    	
    	//高级搜索条件
    	paramsMap.put("regToDateStart", request.getParameter("regToDateStart"));
    	paramsMap.put("regToDateEnd", request.getParameter("regToDateEnd"));
    	paramsMap.put("unitType", request.getParameter("unitType"));
    	paramsMap.put("regIso", request.getParameter("regIso"));
    	
    	paramsMap.put("prctTotalNmbrLeft", request.getParameter("prctTotalNmbrLeft"));
    	paramsMap.put("prctTotalNmbrRight", request.getParameter("prctTotalNmbrRight"));
    	paramsMap.put("regPrctsNmbrLeft", request.getParameter("regPrctsNmbrLeft"));
    	paramsMap.put("regPrctsNmbrRight", request.getParameter("regPrctsNmbrRight"));
    	paramsMap.put("highTitleTchstNmbrLeft", request.getParameter("highTitleTchstNmbrLeft"));
    	paramsMap.put("highTitleTchstNmbrRight", request.getParameter("highTitleTchstNmbrRight"));
    	paramsMap.put("middleTitleTchstNmbrLeft", request.getParameter("middleTitleTchstNmbrLeft"));
    	paramsMap.put("middleTitleTchstNmbrRight", request.getParameter("middleTitleTchstNmbrRight"));
    	
    	String keyWord = request.getParameter("keyWord");
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	paramsMap.put("totalAssetsLeft", request.getParameter("totalAssetsLeft"));
    	paramsMap.put("totalAssetsRight", request.getParameter("totalAssetsRight"));
    	
    	
    	/** 取代理机构信息 */
    	Page<Agency> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Agency> pageData = agencyService.getAgencyListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
    	
    	
		return new ModelAndView("agencyShowForListView",model);
    }
	
	/** 
	 * Description :  根据投标类别获得区域的展示信息集合
	 * Create Date: 2010-8-3上午11:44:58 by sunl  Modified Date: 2010-8-3上午11:44:58 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getAdminGrdShowByAgentType")
	public ModelAndView getAdminGrdShowByAgentType(String agentType) throws Exception {
		List<String[]> adminGrdList = agencyService.getAdminGrdListShow(agentType);
    	for (Object[] aGrd : adminGrdList) {
			for (String grd : enumService.getEnum(OrganizationEnum.AGENT_ADMINGRD)) {
				String[] temp = grd.split(":");
				if(aGrd[0].equals(temp[0])) {
					aGrd[1] = temp[1];
				}
			}
		}
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("result", adminGrdList);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 获得推荐代理机构 
	 * Create Date: 2010-8-20上午11:58:07 by liangxj  Modified Date: 2010-8-20上午11:58:07 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getRecommendAgency")
	public ModelAndView getRecommendAgency(HttpServletRequest request, String viewName) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		/** keyWord来自与首页的参数 */
		String keyWord = request.getParameter("keyWord");
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	paramsMap.put("categoryCode", request.getParameter("agentType"));
		paramsMap.put("evalSort", "desc");
		
		/** 取代理机构信息*/
    	Page<Agency> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Agency> pageData = agencyService.getAgencyListForShow(page,paramsMap);
    	model.put("agencyList",pageData.getData());
    	
    	String viewStr = "agencyRecommendView";
    	if(null != viewName && "agencyRecommendIndexView".equals(viewName)) {
    		viewStr = "agencyRecommendIndexView";
    	}
		return new ModelAndView(viewStr, model);
	}
	
	/** 
	 * Description :  获得代理机构的详细信息，包含资质、成功案例、评价
	 * Create Date: 2010-8-4下午04:28:52 by liangxj  Modified Date: 2010-8-4下午04:28:52 by liangxj
	 * @param
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getAgencyInfo")
	public ModelAndView getAgencyInfo(String objId,HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		
		//查询代理机构
		Agency agency = agencyService.getAgencyAllInfo(objId);
		model.put("agency", agency);
		
		
		//查询评价详细信息 
		List quotaDetailList = (List)evaluateService.getQuataScoreDetail(agency.getOrgInfo(),"03");
		model.put("quotaDetailList", quotaDetailList);
		
		//详细页面特定显示的tab
    	model.put("tabId", request.getParameter("tabId"));

		return new ModelAndView("agencyShowInfoView", model);
	}
}
