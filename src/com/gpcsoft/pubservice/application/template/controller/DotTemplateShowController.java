package com.gpcsoft.pubservice.application.template.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.template.domain.DotTemplate;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateDown;
import com.gpcsoft.pubservice.application.template.service.DotTemplateDownService;
import com.gpcsoft.pubservice.application.template.service.DotTemplateService;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.UserService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="showTemplateListView"
  *  url="view/pubservice/application/template/show_template_list.jsp"
  *  
  * @gpcsoft.view value="templateShowForListView"
  *  url="view/pubservice/application/template/template_list_div_l.jsp"
  *  
  * @gpcsoft.view value="recommendTemplateListView"
  *  url="view/pubservice/application/template/recommend_template_list.jsp"
  */
@Controller
@Scope("request")
@SessionAttributes(types={DotTemplate.class})
@RequestMapping("/DotTemplateShowController.do")
public class DotTemplateShowController extends AnnotationMultiController<DotTemplate> {

	@Autowired(required=true) @Qualifier("dotTemplateServiceImpl")
	private DotTemplateService dotTemplateService;
	
	@Autowired(required=true) @Qualifier("dotTemplateDownServiceImpl")
	private DotTemplateDownService dotTemplateDownService;
	
	@Autowired(required = true) @Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	/** 
	 * Description :  跳转到范本展示列表页面
	 * Create Date: 2011-10-9上午09:46:52 by likg  Modified Date: 2011-10-9上午09:46:52 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toShowTemplateListView")
	public ModelAndView toShowTemplateListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
    	
    	String categoryCode = request.getParameter("categoryCode"); //品目Code
    	String districtCode = request.getParameter("districtCode"); //区域Code
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord")); //关键字
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(keyWord));
    	paramsMap.put("categoryCode", categoryCode);
    	paramsMap.put("districtCode", districtCode);
    	
    	//获取各品目的范本数
    	List<Object[]> categoryList = dotTemplateService.getTemplateNumByCategory(paramsMap);
    	model.put("categoryList", categoryList);
    	
    	//获取各区域的范本数（省级区域）
    	List<Object[]> districtList = dotTemplateService.getTemplateNumByDistrict(paramsMap);
    	model.put("districtList", districtList);
    	
    	//取供应商信息
    	Page<DotTemplate> page = prePage(request);
    	Page<DotTemplate> pageData = dotTemplateService.getTemplateListForShow(page, paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT", pageData);
    	
    	//回设高亮参数或首页参数
    	model.put("districtCode", districtCode);
    	model.put("categoryCode", categoryCode);
    	model.put("keyWord", StringUtils.returnIngoreStr(keyWord));
    	
		return new ModelAndView("showTemplateListView", model);
	}
	
	/** 
	 * Description :  获取范本展示的列表数据
	 * Create Date: 2011-10-9上午10:53:14 by likg  Modified Date: 2011-10-9上午10:53:14 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getTemplateForList")   
	public ModelAndView getTemplateForList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		//搜索条件
    	paramsMap.put("categoryCode", request.getParameter("categoryCode")); //品目Code
    	paramsMap.put("districtCode", request.getParameter("districtCode")); //区域Code
    	paramsMap.put("templateType", request.getParameter("templateType")); //范本类型
    	paramsMap.put("isShare", request.getParameter("isShare")); //是否共享
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(request.getParameter("keyWord")))); //关键字
    	
    	//排序
    	paramsMap.put("createTimeSort", request.getParameter("createTimeSort")); //创建时间
    	paramsMap.put("favoriteNumSort", request.getParameter("favoriteNumSort")); //收藏次数
    	paramsMap.put("downNumSort", request.getParameter("downNumSort")); //下载次数
    	
    	//获取范本信息
    	Page<DotTemplate> page = prePage(request);
    	Page<DotTemplate> pageData = dotTemplateService.getTemplateListForShow(page, paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT", pageData);
    	
		return new ModelAndView("templateShowForListView", model);
    }
	
	/** 
	 * Description :  获得区域列表展示信息集合
	 * Create Date: 2011-8-16上午09:27:11 by likg  Modified Date: 2011-8-16上午09:27:11 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getDistrictListForShow")
	public ModelAndView getDistrictListForShow(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		//获取参数信息
		paramsMap.put("categoryCode", request.getParameter("categoryCode")); //品目Code
		paramsMap.put("districtCode", request.getParameter("districtCode")); //区域Code
    	
    	//获取各区域的范本数（省级区域）
    	List<Object[]> districtList = dotTemplateService.getTemplateNumByDistrict(paramsMap);
    	model.put("result", districtList);
    	
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  下载范本附件
	 * Create Date: 2011-10-10下午12:55:55 by likg  Modified Date: 2011-10-10下午12:55:55 by likg
	 * @param   templateFileId:范本附件Id
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=downloadTemplateFile")
	public void downloadTemplateFile(String templateFileId, HttpServletResponse response, HttpServletRequest request) throws Exception {
		//下载范本文件
		AttachmentUtil.downAttachmentFile(templateFileId, response, request);
		
		//保存范本下载信息
		String userId = request.getParameter("userId"); //下载用户Id
		DotTemplateDown dotTemplateDown = new DotTemplateDown();
		//指定下载用户
		if(StringUtils.hasLength(userId)) {
			User user = userService.get(userId);
			if(user!=null && user.getEmp()!=null && user.getEmp().getCompany()!=null) {
				OrgInfo orgInfo = orgInfoService.getOrgInfoByCompany(user.getEmp().getCompany().getObjId());
				dotTemplateDown.setOrg(orgInfo);
				dotTemplateDown.setCreateUser(user);
			}
		}
		//设置当前用户为下载用户
		else if(AuthenticationHelper.getCurrentUser()!=null && AuthenticationHelper.getCurrentUser().getOrgInfo()!=null) {
			dotTemplateDown.setOrg((OrgInfo)AuthenticationHelper.getCurrentUser().getOrgInfo());
		}
		DotTemplate dotTemplate = new DotTemplate();
		dotTemplate.setObjId(request.getParameter("templateId"));
		dotTemplateDown.setDotTemplate(dotTemplate);
		dotTemplateDownService.save(dotTemplateDown);
	}
	
	/** 
	 * Description :  获取推荐范本列表视图
	 * Create Date: 2011-10-11下午02:38:39 by likg  Modified Date: 2011-10-11下午02:38:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getRecommendTemplateListView")
	public ModelAndView getRecommendTemplateListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("favoriteNumSort", "desc"); //收藏次数倒序排列
    	params.put("downNumSort", "desc"); //下载次数倒序排列
    	params.put("usedNumSort", "desc"); //使用次数倒序排列
    	
		//获取推荐范本信息
    	Page<DotTemplate> page = prePage(request);
    	Page<DotTemplate> pageData = dotTemplateService.getTemplateListForShow(page, params);
    	model.put("recommendTemplateList", pageData.getData());
    	
		return new ModelAndView("recommendTemplateListView", model);
	}
	
}
