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
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.smallscale.business.domain.OrgCommunity;
import com.gpcsoft.smallscale.business.service.OrgCommunityService;

/**
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OrgCommunity.class})
@RequestMapping("/OrgCommunityController.do")//页面请求路径,可修改
public class OrgCommunityController extends AnnotationMultiController<OrgCommunity> {

	@Autowired(required=true) @Qualifier("orgCommunityServiceImpl")
	private OrgCommunityService orgCommunityService;
	
	/** 
	 * Description :  加入社区
	 * Create Date: 2011-10-21上午11:03:28 by yucy  Modified Date: 2011-10-21上午11:03:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	
	@RequestMapping(params="method=joinToCommunity")
	public ModelAndView joinToCommunity( HttpServletRequest request , String[] joinIds ,String[] minusIds ,String orgId ) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgId",orgId);
		param.put("joinIds",joinIds);
		
		orgCommunityService.createOrgCommunity(param);
		
		return new  ModelAndView (Constants.JSON_VIEW,model);
		
	}
	
	/** 
	 * Description :  脱离社区
	 * Create Date: 2011-10-21上午11:03:28 by yucy  Modified Date: 2011-10-21上午11:03:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	
	@RequestMapping(params="method=minusOrgCommunity")
	public ModelAndView minusOrgCommunity( HttpServletRequest request ,String[] minusIds ,String orgId ) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgId",orgId);
		param.put("minusIds",minusIds);
		
		if(minusIds!=null  &&  minusIds.length >0){
			orgCommunityService.deleteOrgCommunity(param);
		}
		return new  ModelAndView (Constants.JSON_VIEW,model);
	}
	
}
