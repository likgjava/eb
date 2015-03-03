package com.gpcsoft.pubservice.application.concern.web.controller;

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
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.concern.domain.Concern;
import com.gpcsoft.pubservice.application.concern.domain.ConcernGroup;
import com.gpcsoft.pubservice.application.concern.enumeration.ConcernEnum;
import com.gpcsoft.pubservice.application.concern.service.ConcernGroupService;
import com.gpcsoft.pubservice.application.concern.service.ConcernService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @gpcsoft.view value="concernSupplierList"
 *  url="view/pubservice/application/concern/concern_supplier_list.jsp"
 * @gpcsoft.view value="concernBuyerList"
 *  url="view/pubservice/application/concern/concern_buyer_list.jsp"
 * @gpcsoft.view value="addConcernFormView"
 *  url="view/pubservice/application/concern/add_concern_form.jsp"
 * @gpcsoft.view value="clientBargareeRecordView"
 *  url="view/pubservice/application/concern/client_bargaree_record_list.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Concern.class})
@RequestMapping("/ConcernController.do")//页面请求路径,可修改
public class ConcernController extends AnnotationMultiController<Concern> {

	@Autowired(required=true) @Qualifier("concernServiceImpl")
	private ConcernService concernService;
	
	@Autowired(required=true) @Qualifier("concernGroupServiceImpl")
	private ConcernGroupService concernGroupService;
	
	/** 
	 * Description :  跳转到关注的客户页面 
	 * Create Date: 2010-11-3下午05:36:32 by guoyr  Modified Date: 2010-11-3下午05:36:32 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toConcernList")
	public ModelAndView toConcernList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		// 获取采购人的分组列表
		List<ConcernGroup> concernGroupList = concernGroupService.getConcerngListByGroupType(ConcernEnum.BUYER_GROUP);
		model.put("concernGroupList", concernGroupList);
		
		User currentUser = AuthenticationHelper.getCurrentUser(true);
		
		User user = AuthenticationHelper.getCurrentUser(true);
		if(User.USER_TYPE_MANAGER.equals(user.getUsrIsAdmin())) {
			model.put("isOrgManager", true);
		}
			
		model.put("curUserId", currentUser.getObjId());
		model.put("curOrgInfoId", currentUser.getOrgInfo().getObjId());
		return new ModelAndView("concernBuyerList",model);	
	}
	
	/** 
	 * Description :  保存直接关注客户 
	 * Create Date: 2010-11-3下午05:43:06 by guoyr  Modified Date: 2010-11-3下午05:43:06 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveConcern")
	public ModelAndView saveConcern(HttpServletRequest request,Concern concern) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String result = "关注成功";
		
		if(null != concern.getObjId() && !"".equals(concern.getObjId())){
			result = "修改成功";
		}
		concernService.saveConcern(concern);
		model.put(Constants.JSON_RESULT, result);
		
		return new ModelAndView(Constants.JSON_VIEW, model);	
	}

	/**
	 * Description :  保存客户(间接保存,交易时的客户)
	 * Create Date: 2011-7-1下午12:01:43 by zhaojf  Modified Date: 2011-7-1下午12:01:43 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveClientConcern")
	public ModelAndView saveClientConcern(String orgInfoIds,String projectCreator) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();		
		concernService.saveClientConcern(orgInfoIds,projectCreator);
		model.put(Constants.JSON_RESULT, "true");
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  跳转到添加关注客户页面、修改分组页面
	 * Create Date: 2010-11-25下午05:07:10 by guoyr  Modified Date: 2010-11-25下午05:07:10 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAddConcernForm")
	public ModelAndView toAddConcernForm() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		User currentUser = AuthenticationHelper.getCurrentUser(true);
		model.put("curUserId", currentUser.getObjId());
		return new ModelAndView("addConcernFormView", model);	
	}
	
	/**
	 * Description :  移至黑名单、移出黑名单(移至我的客户)
	 * Create Date: 2011-6-28上午11:23:15 by zhaojf  Modified Date: 2011-6-28上午11:23:15 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=transferInOrOutHacker")
	public ModelAndView transferInOrOutHacker(String objId,String listType) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		//移至黑名单
		String listTypeValue=ConcernEnum.HACKER_LIST;
		if(ConcernEnum.CLIENT_LIST.equals(listType)){
			listTypeValue = ConcernEnum.CLIENT_LIST;
		}
		//移至我的客户
		if(ConcernEnum.HACKER_LIST.equals(listType)){
			listTypeValue = ConcernEnum.HACKER_LIST;
		}
		concernService.transferInOrOutHacker(objId, listTypeValue);
		model.put(Constants.JSON_RESULT, "true");
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/**
	 * Description :  交易记录
	 * Create Date: 2011-7-4上午11:16:47 by zhaojf  Modified Date: 2011-7-4上午11:16:47 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=clientBargareeRecord")
	public ModelAndView clientBargareeRecord(String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Concern concern = concernService.get(objId);
		model.put("concern", concern);
		//客户机构
		OrgInfo clienter = concern.getOrgInfo();
		model.put("clienter", clienter);
		
		User currentUser = AuthenticationHelper.getCurrentUser(true);
		model.put("currentUser", currentUser);
		
		OrgInfo currentOrg = (OrgInfo) currentUser.getOrgInfo();
		model.put("currentOrg", currentOrg);
		return new ModelAndView("clientBargareeRecordView",model);
	}

}
