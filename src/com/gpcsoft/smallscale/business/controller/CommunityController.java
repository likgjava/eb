package com.gpcsoft.smallscale.business.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;
import com.gpcsoft.smallscale.business.domain.Community;
import com.gpcsoft.smallscale.business.service.CommunityService;

/**
  * @gpcsoft.view value="communityFormView"
  *  url="view/smallscale/business/community_form.jsp"
  * @gpcsoft.view value="communityListView"
  *  url="view/smallscale/business/community_list.jsp"
  *  
  * @gpcsoft.view value="toOrgComunityManageView"
  *  url="view/smallscale/business/community_org_manage.jsp"
  *  
  * @gpcsoft.view value="toOrgJoinCommunityListView"
  *  url="view/smallscale/business/community_join_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Community.class})
@RequestMapping("/CommunityController.do")//页面请求路径,可修改
public class CommunityController extends AnnotationMultiController<Community> {

	@Autowired(required=true) @Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	@Autowired(required=true) @Qualifier("serviceSubscribeServiceImpl")
	private ServiceSubscribeService serviceSubscribeService;
	
	/** 
	 * Description :  跳转到新增或保存社区
	 * Create Date: 2010-7-21上午10:19:52 by yucy  Modified Date: 2010-7-21上午10:19:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateCommunity")   
	public ModelAndView toCreateOrUpdateCommunity(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Community community = null;
		if(null!=request.getParameter("objId")&&!"".equals(request.getParameter("objId"))){
			community = communityService.get(request.getParameter("objId"));
		}else {
			community = new Community();
		}
		
		//如果是机构新增社区
		if(StringUtils.hasLength(request.getParameter("orgInfo.objId"))){
			model.put("orgInfo", baseGenericService.get(request.getParameter("orgInfo.objId"), OrgInfo.class));//推送机构值
		}
		
		model.put("community", community);
    	return new ModelAndView("communityFormView", model);
	}
	
    /** 
     * Description :  保存社区
     * Create Date: 2011-2-17下午05:00:27 by yucy  Modified Date: 2011-2-17下午05:00:27 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=saveCommunity")
    public ModelAndView saveCommunity(HttpServletRequest request,Community community, SessionStatus status) throws Exception {
    	String returnMessage = "success";
        Map<String, Object> model = new HashMap<String, Object>();
     	
        model.put("community", communityService.save(community));
        status.setComplete();

        model.put(Constants.JSON_RESULT,returnMessage);
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/** 
	 * Description :  删除社区
	 * Create Date: 2010-7-26下午03:26:13 by yucy  Modified Date: 2010-7-26下午03:26:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=delCommunity")   
	public ModelAndView delCommunity(HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		String communityIds = request.getParameter("communityIds");
		if(null!=communityIds&&!"".equals(communityIds)){
			String[] communityIdArray = communityIds.split(",");
			int result = communityService.delCommunity(communityIdArray);		//删除社区(检查可删性)
			if(result>0){
				model.put(Constants.JSON_RESULT, "删除成功!");
			}else{
				model.put(Constants.JSON_RESULT, "删除失败!社区已投入使用");
			}
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  跳转至机构管理社区或者加入社区
	 * Create Date: 2011-10-20下午04:16:13 by yucy  Modified Date: 2011-10-20下午04:16:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOrgComunityList")   
	public ModelAndView toOrgComunityList(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();   
		
		
		Map<String, Object> param = new HashMap<String, Object>();   
		
		param.put("orgInfoId", AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId());
		param.put("serviceId", ServiceEnum.SERVICE_D);
		
		//是否具有创建社区服务
		model.put("canCreate", serviceSubscribeService.isOrgInfoHasService(param));

		param.remove("serviceId");
		param.put("serviceId", ServiceEnum.SERVICE_E);
		
		model.put("canJoin", serviceSubscribeService.isOrgInfoHasService(param));
		
		return new ModelAndView("toOrgComunityManageView", model );
	}
	
	/** 
	 * Description :  跳转至机构加入的社区
	 * Create Date: 2011-10-20下午10:00:34 by yucy  Modified Date: 2011-10-20下午10:00:34 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toOrgJoinCommunityList")
	public ModelAndView toOrgJoinCommunityList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   

		return new ModelAndView("toOrgJoinCommunityListView", model );
	}
	
	/** 
	 * Description :  取得供应商选择社区列表
	 * Create Date: 2011-10-21上午10:21:55 by yucy  Modified Date: 2011-10-21上午10:21:55 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getCommunityListForChoose")   
	public ModelAndView getCommunityListForChoose(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Page<Community> page = prePage(request); //预分页,算出当前页和大小等		
		
		//参数封装
		Map<String, Object> param = new HashMap<String, Object>();
		
		//查询条件
		//投标品目查询
		param.put("bidForRangeCode", request.getParameter("bidForRangeCode"));
		param.put("communityName", request.getParameter("communityName"));

		param.put("orgNotIn", request.getParameter("orgNotIn"));
		param.put("orgNotIn_Id", request.getParameter("orgNotIn_Id"));
		
		//不过滤机构
		param.put("company_path", request.getParameter("company_path"));

		
		
		param.put("isPublic", request.getParameter("isPublic"));
		
		//排序
		param.put("order", request.getParameter("order"));
		param.put("order_flag", request.getParameter("order_flag"));
		
		Page<Community> pageData = (Page<Community>) communityService.getCommunityListForShow(page,param);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
}
