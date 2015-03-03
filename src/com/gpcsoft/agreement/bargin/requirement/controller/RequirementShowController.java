package com.gpcsoft.agreement.bargin.requirement.controller;

import java.util.Date;
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

import com.gpcsoft.agreement.bargin.requirement.domain.Requirement;
import com.gpcsoft.agreement.bargin.requirement.service.RequirementService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

/**
  * @gpcsoft.view value="requirementShowListView" 
  * url="view/agreement/purchaserequire/showrequirement/show_requirement_list.jsp"
  * 
  * @gpcsoft.view value="requirementShowForListView" 
  * url="view/agreement/purchaserequire/showrequirement/requirement_list_div.jsp"
  * 
  * @gpcsoft.view value="requirementShowInfoView" 
  * url="view/agreement/purchaserequire/showrequirement/show_requirement_detail.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/RequirementShowController.do")//页面请求路径,可修改
public class RequirementShowController extends AnnotationMultiController<GpcBaseObject> 
{
	@Autowired(required=true) @Qualifier("requirementServiceImpl")
	private RequirementService requirementService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true)  @Qualifier("serviceSubscribeServiceImpl")
	private ServiceSubscribeService serviceSubscribeService;
	
	@Autowired(required=true) @Qualifier("purCategoryServiceImpl")
	private PurCategoryService purCategoryService;
	
	/** 
	 * Description :  采购需求列表页面
	 * Create Date: 2011-3-30上午09:50:08 by yucy  Modified Date: 2011-3-30上午09:50:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toRequirementList")
    public ModelAndView toRequirementList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>(); // 查询条件
    	
    	String categoryCode = request.getParameter("categoryCode");
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
    	String districtLevel = request.getParameter("districtLevel");
    	String districtId = request.getParameter("districtId");
    	String evalSort = request.getParameter("evalSort");
    	
    	Short level = 0;
    	if(StringUtils.hasLength(districtLevel)) {
    		level = new Short(districtLevel);
    	}
    	paramsMap.put("categoryCode", categoryCode);
		paramsMap.put("order", "createTime");
		paramsMap.put("keyWord", keyWord);
    	
    	/** 取下级品目展示信息*/
    	List<String[]> listCategory = requirementService.getCategoryListShowByCategory("", categoryCode, true,keyWord);
    	model.put("categoryList", listCategory);
    	
    	/** 取区域信息*/
    	List<String[]> listDistrict = requirementService.getDistrictListForShow(null, categoryCode,level,keyWord);
    	model.put("districtList", listDistrict);
		
    	//获取采购需求信息
    	Page<Requirement> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Requirement> pageData = requirementService.getRequirementListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
    	/** 回设高亮参数或首页参数 */
    	model.put("districtId", districtId);
    	model.put("categoryCode", categoryCode);
    	model.put("districtLevel", districtLevel);
    	model.put("keyWord",StringUtils.returnIngoreStr(keyWord));
    	model.put("evalSort", evalSort);
    	if(StringUtils.hasLength(categoryCode)) {
    		PurCategory purCategory = purCategoryService.get(categoryCode);
    		model.put("purCategoryName", purCategory.getCategoryName()); //采购品目名称
    	}
    	
        return new ModelAndView("requirementShowListView",model);
    }
	
	/** 
	 * Description :  获取采购需求展示的列表数据
	 * Create Date: 2010-7-27上午11:46:10 by yucy  Modified Date: 2010-7-27上午11:46:10 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getRequirementForList")   
	public ModelAndView getRequirementForList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		//品目和区域
    	paramsMap.put("categoryCode", request.getParameter("categoryCode"));
    	paramsMap.put("districtId", request.getParameter("districtId"));
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(request.getParameter("keyWord"))));
    	
    	//排序
    	paramsMap.put("pubTimeSort", request.getParameter("pubTimeSort"));
    	
    	paramsMap.put("createTimeSort", request.getParameter("createTimeSort"));

    	paramsMap.put("price",request.getParameter("price"));

    	/** 取采购需求信息 */
    	Page<Requirement> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Requirement> pageData = requirementService.getRequirementListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
		return new ModelAndView("requirementShowForListView",model);
    }
	
	/** 
	 * Description :  获得需求的详情信息
	 * Create Date: 2011-3-31上午10:55:30 by yucy  Modified Date: 2011-3-31上午10:55:30 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getRequirementInfo")
	public ModelAndView getRequirementInfo(String objId, HttpServletRequest request, String viewName) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		
		//获取公告信息
		Requirement requirement  = requirementService.get(objId);
		model.put("requirement", requirement);
		
		//获得商品的附图
		if(requirement.getPicture() != null){
			List<Attachment> images = attachmentService.getAttachmentsByRealID(requirement.getPicture());
			model.put("images", images);
		}
		
		//获得同类的发布信息
		Page<Requirement> pageRequirement = new Page<Requirement>();
		pageRequirement.setStart(0);
		paramsMap.clear();
		pageRequirement.setPageSize(10);
		paramsMap.put("categoryCode",requirement.getCategory().getCategoryCode() );
		paramsMap.put("notin",requirement.getObjId());
		model.put("requirementList", requirementService.getRequirementListForShow(pageRequirement, paramsMap).getData());

		//剩余时间
		model.put("remainSignUpTime", requirement.getEndTime().getTime()-(new Date()).getTime());
		
		//是否显示联系方式
		if(AuthenticationHelper.getCurrentUser()!=null && AuthenticationHelper.getCurrentUser().getOrgInfo()!=null){
			model.put("isShowContact", serviceSubscribeService.isShangQuanHuiYuan((AuthenticationHelper.getCurrentUser(true).getOrgInfo()).getObjId()));
		}
		
    	if(viewName == null){ viewName = "requirementShowInfoView";}
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description :  根据投标类别获得区域的展示信息集合
	 * Create Date: 2011-3-30下午03:43:53 by yucy  Modified Date: 2011-3-30下午03:43:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getDistrictListShowByCategory")
	public ModelAndView getDistrictListShowByCategory(String categoryCode,String districtId,String districtLevel,HttpServletRequest request) throws Exception {
		String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
		Short level = 0;
    	if(districtLevel != null && !districtLevel.equals("")) {
    		level = new Short(districtLevel);
    	}
    	List<String[]> district = requirementService.getDistrictListForShow(districtId, categoryCode,level,keyWord);
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("result", district);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
