package com.gpcsoft.pubservice.application.message.controller;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.message.service.AdviseService;
import com.gpcsoft.pubservice.application.message.domain.Advise;

import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="adviseFormView"
  *  url="view/pubservice/application/message/advise_form.jsp"
  * @gpcsoft.view value="adviseReplyFormView"
  *  url="view/pubservice/application/message/advise_reply_form.jsp"
  * @gpcsoft.view value="adviseListView" 
  *  url="view/pubservice/application/message/advise_list.jsp"
  * @gpcsoft.view value="adviseDetailView"
  *  url="view/pubservice/application/message/advise_view.jsp"
  *  @gpcsoft.view value="adviseCreateView"
  *  url="view/pubservice/application/message/advise_form.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Advise.class})
@RequestMapping("/AdviseController.do")//页面请求路径,可修改
public class AdviseController extends AnnotationMultiController<Advise> {

	@Autowired(required=true) @Qualifier("adviseServiceImpl")
	private AdviseService adviseService;
	
	/**
	 * 查询列表
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=myList")
	public ModelAndView myList(HttpServletRequest request) throws Exception{

		Map<String, Object> model = new HashMap<String, Object>();		
		String isAdmin = request.getParameter("isAdmin");
		model.put("isAdmin", isAdmin);
		
		return new ModelAndView("adviseListView",model);		
	}
	
	/**
	 * 添加建议意见页面
	 * @param objId
	 * @return
	 */
	@RequestMapping(params="method=toCreateAdvise")
	public ModelAndView toCreateAdvise(String objId){		
		Map<String, Object> model = new HashMap<String, Object>();
		User user = AuthenticationHelper.getCurrentUser(true);		
		model.put("email", user.getEmp().getEmail());
		model.put("mobile", user.getEmp().getMobile());
		return new ModelAndView("adviseCreateView",model);
		
	}
	
	/**
	 * 回复页面
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=toReply")
	public ModelAndView toReply(HttpServletRequest request,String objId,SessionStatus status) throws Exception{

		Map<String, Object> model = new HashMap<String, Object>();

		Advise ad = adviseService.get(objId);
		model.put("advise", ad);
		
	
		return new ModelAndView("adviseReplyFormView",model);		
	}
	
	/** 
	 * Description : 回复保存
	 * Create Date: 2010-10-26下午03:03:18 by dongcl  Modified Date: 2010-10-26下午03:03:18 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveReply")
	public ModelAndView saveReply(Advise advise,HttpServletRequest request,SessionStatus status) throws Exception{
        User user = AuthenticationHelper.getCurrentUser(true);
        Map<String, Object> model = new HashMap<String, Object>();
		advise.setReplyTime(new Date());
		advise.setRepplier(user);
		adviseService.saveReply(advise);
		
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW,model);
		
		//return this.save(advise, request, status);	
		
	}
	
	
	/**
	 * 查看页面
	 * @param objId
	 * @return
	 */
	@RequestMapping(params="method=toView")
	public ModelAndView toView(String objId){		
		Map<String, Object> model = new HashMap<String, Object>();
		Advise ad = adviseService.get(objId);
		model.put("advise", ad);
		return new ModelAndView("adviseDetailView",model);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public QueryObject onFind(QueryObject query,HttpServletRequest request) throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
			QueryParam qp = query.getQueryParam()==null?new QueryParam():query.getQueryParam();
			
			String isAdmin = request.getParameter("isAdmin");
			if(null != isAdmin && isAdmin.equals("true")){//管理员
				request.setAttribute("isAdmin", isAdmin);
			}
			else{
				qp.and(new QueryParam("createUser.objId","=",user.getObjId()));
			}
			
			query.setQueryParam(qp);
		return query;
	}


	/**
	 * Description :  采购人桌面保存建议
	 * Create Date: 2011-4-7下午05:19:28 by zhaojf  Modified Date: 2011-4-7下午05:19:28 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveDeskTopAdvese")
	public ModelAndView saveDeskTopAdvese(HttpServletRequest request,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Advise advise = JsonUtils.json2Bean(JsonUtils.getJSONString(request.getParameter("adviseJson")), Advise.class);
		User user = AuthenticationHelper.getCurrentUser(true);
		advise.setContactEmail(user.getEmp().getEmail());
		advise.setContactTel(user.getEmp().getMobile());
		adviseService.save(advise);
		status.setComplete();
		model.put(Constants.JSON_RESULT, "true");
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
