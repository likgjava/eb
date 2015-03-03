package com.gpcsoft.smallscale.expert.controller;

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

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.industry.domain.Industry;
import com.gpcsoft.bizplatform.base.industry.service.IndustryService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.smallscale.expert.service.ExpertInfoService;

/**
  * @gpcsoft.view value="expertShowListView"
  *  url="view/smallscale/expert/showexpert/show_expert_list.jsp"
  * @gpcsoft.view value="expertShowForListView"
  *  url="view/smallscale/expert/showexpert/expert_list_div_l.jsp"
  * @gpcsoft.view value="expertShowInfoView"
  *  url="view/smallscale/expert/showexpert/show_expert_detail.jsp"
  * @gpcsoft.view value="expertRecommendView"
  *  url="view/smallscale/expert/showexpert/expert_recommend_list.jsp"
  * @gpcsoft.view value="recommendExpertIndexView"
  *  url="view/srplatform/portal/include/recommend_expert_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/ExpertShowController.do")//页面请求路径,可修改
public class ExpertShowController extends AnnotationMultiController<GpcBaseObject> {

	@Autowired(required=true) @Qualifier("expertInfoServiceImpl")
	private ExpertInfoService expertInfoService;
	
	@Autowired(required=true) @Qualifier("industryServiceImpl")
	private IndustryService industryService;
	
	 /** 
     * Description :  跳转到专家展示的二级页面，也就是列表页面
     * Create Date: 2010-7-27上午10:02:32 by liangxj  Modified Date: 2010-7-27上午10:02:32 by liangxj
     * @param   categoryId 品目id categoryCode 品目编号
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toExpertList")
    public ModelAndView toExpertList(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
    	
    	/** categoryId和districtId可能来自与详情页面的参数；validSort和keyWord,districtLevel来自与首页的参数 */
    	String districtLevel = request.getParameter("districtLevel");
    	String validSort = request.getParameter("validSort");
    	String districtId = request.getParameter("districtId");
    	String categoryId = request.getParameter("categoryId");
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
    	
    	Short level = 0;
    	if(districtLevel != null && !districtLevel.equals("")) {
    		level = new Short(districtLevel);
    	}
    	
    	paramsMap.put("districtLevel", districtLevel);
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(keyWord));
    	paramsMap.put("categoryId", categoryId);
    	paramsMap.put("validSort", validSort);
    	paramsMap.put("districtId", districtId);
    	
    	/** 取下级品目展示信息*/
    	List<String[]> listCategory = expertInfoService.getCategoryListShowByCategory("", null, true);
    	model.put("categoryList", listCategory);
    	
    	/** 取区域信息*/
    	List<String[]> listDistrict = expertInfoService.getDistrictListForShow(null, categoryId,level);
    	model.put("districtList", listDistrict);
    	
    	/** 获取行业信息*/
    	List<Industry> listIndustry = industryService.getIndustryByLevel(new Short("1"));  //获得顶级行业
    	model.put("industryList", listIndustry);
    	
    	/** 取专家信息*/
    	Page<ExpertInfo> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<ExpertInfo> pageData = expertInfoService.getExpertListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
    	/** 回设高亮参数或首页参数 */
    	model.put("categoryId", categoryId);
    	model.put("districtId", districtId);
    	model.put("districtLevel", districtLevel);
    	model.put("keyWord",StringUtils.returnIngoreStr(keyWord));
    	model.put("validSort", validSort);
    	
        return new ModelAndView("expertShowListView",model);
    }
    
    
	/** 
	 * Description :  获取专家展示的列表数据
	 * Create Date: 2010-12-2下午02:31:16 by liangxj  Modified Date: 2010-12-2下午02:31:16 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getExpertForList")   
	public ModelAndView getExpertForList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		//品目和区域
    	paramsMap.put("categoryId", request.getParameter("categoryId"));
    	paramsMap.put("districtId", request.getParameter("districtId"));
    	
    	//排序
    	paramsMap.put("specifySort", request.getParameter("specifySort"));
    	paramsMap.put("validSort", request.getParameter("validSort"));
    	
    	//高级搜索条件
    	paramsMap.put("sspecifyYear", request.getParameter("sspecifyYear"));//从事年限
    	paramsMap.put("especifyYear", request.getParameter("especifyYear"));
    	
    	paramsMap.put("isRetiredC", request.getParameter("isRetiredC"));  //是否退休
    	paramsMap.put("isRetiredJ", request.getParameter("isRetiredJ"));  //
    	
    	paramsMap.put("isConsultant", request.getParameter("isConsultant"));  //是否咨询专家
    	paramsMap.put("isReviewers", request.getParameter("isReviewers"));  //是否评审专家
    	
    	paramsMap.put("belongIndustry", request.getParameter("belongIndustry"));  //所属行业
    	paramsMap.put("professionQualificationLevel", request.getParameter("professionQualificationLevel"));//专家资质
    	String keyWord = request.getParameter("keyWord");//关键字
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    
    	
    	/** 取专家信息 */
    	Page<ExpertInfo> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<ExpertInfo> pageData = expertInfoService.getExpertListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
		return new ModelAndView("expertShowForListView",model);
    }
	
	
	/** 
	 * Description :  根据品目获得区域的展示信息集合
	 * Create Date: 2010-12-2下午02:38:24 by liangxj  Modified Date: 2010-12-2下午02:38:24 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getDistrictListShowByCategory")
	public ModelAndView getDistrictListShowByCategory(String categoryId,String districtId,String districtLevel) throws Exception {
		Short level = 0;
    	if(districtLevel != null && !districtLevel.equals("")) {
    		level = new Short(districtLevel);
    	}
    	
    	List<String[]> district = expertInfoService.getDistrictListForShow(districtId, categoryId,level);
		
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("result", district);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获得供应商的详细信息，包含职称、教育、培训、任职
	 * Create Date: 2010-8-4下午04:28:52 by liangxj  Modified Date: 2010-8-4下午04:28:52 by liangxj
	 * @param
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getExpertInfo")
	public ModelAndView getExpertInfo(String objId ,HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		
		//查询供应商
		ExpertInfo expert = expertInfoService.getExpertAllInfo(objId);
		model.put("expert", expert);
		
		//导航参数
		model.put("categoryId", request.getParameter("categoryId"));
		model.put("districtId", request.getParameter("districtId"));
		
		if(request.getParameter("categoryName")!=null&&!"".equals(request.getParameter("categoryName"))){
			model.put("categoryName", StringUtils.ascii2Native(request.getParameter("categoryName")));
		}
		if(request.getParameter("districtName")!=null&&!"".equals(request.getParameter("districtName"))){
			model.put("districtName", StringUtils.ascii2Native(request.getParameter("districtName")));
		}
		
		//详细页面特定显示的tab
    	model.put("tabId", request.getParameter("tabId"));
		
		return new ModelAndView("expertShowInfoView", model);
	}
	
	/** 
	 * Description : 获得推荐专家--根据加入时间 
	 * Create Date: 2010-8-20上午11:58:07 by liangxj  Modified Date: 2010-8-20上午11:58:07 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getRecommendExpert")
	public ModelAndView getRecommendExpert(HttpServletRequest request, String viewName ) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		/** keyWord来自与首页的参数 */
		String keyWord = request.getParameter("keyWord");
    	
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	paramsMap.put("categoryId", request.getParameter("categoryId"));
		paramsMap.put("modifySort", "asc");
		
		/** 取专家信息*/
    	Page<ExpertInfo> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<ExpertInfo> pageData = expertInfoService.getExpertListForShow(page,paramsMap);
    	model.put("expertList",pageData.getData());

    	String viewStr = "expertRecommendView";
    	if(null != viewName) {
    		viewStr = viewName;
    	}
		return new ModelAndView(viewStr, model);
	}
}
