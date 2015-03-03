package com.gpcsoft.epp.inviterollrequ.controller;

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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.inviterollrequ.domain.InrqAuditStatusEnum;
import com.gpcsoft.epp.inviterollrequ.domain.InrqDetail;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;
import com.gpcsoft.epp.inviterollrequ.service.InrqDetailService;
import com.gpcsoft.epp.inviterollrequ.service.InviterollrequService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="inviterollrequFormView"
  *  url="view/es/planform/inviterollrequ/inviterollrequForm.jsp"
  * @gpcsoft.view value="inviterollrequTreeFormView"
  *  url="view/es/planform/inviterollrequ/inviterollrequTreeForm.jsp"
  * @gpcsoft.view value="inviterollrequListView"
  *  url="view/es/planform/inviterollrequ/inviterollrequList.jsp"
  * @gpcsoft.view value="inviterollrequDetailView"
  *  url="view/es/planform/inviterollrequ/inviterollrequDetail.jsp"
  *  @gpcsoft.view value="inviterollrequAuditListView"
  *  url="view/es/singlesource/singlesourcedoc/inviterollrequAuditList.jsp"
  *  @gpcsoft.view value="blankView"
  *  url="view/es/common/blank.jsp"
  *  @gpcsoft.view value="inviterollrequListView"
  *  url="view/es/singlesource/singlesourcedoc/inviterollrequListView.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Inviterollrequ.class})
@RequestMapping("/InviterollrequController.do")//页面请求路径,可修改
public class InviterollrequController extends AnnotationMultiController<Inviterollrequ> {

	@Autowired(required=true) @Qualifier("inviterollrequServiceImpl")
	private InviterollrequService inviterollrequService;

	@Autowired(required=true) @Qualifier("inrqDetailServiceImpl")
	private InrqDetailService inrqDetailService;
	
	/**
	 * 
	 * Description :保存邀请名单申请单
	 * Create Date: 2010-8-17上午09:37:32 by liuke  Modified Date: 2010-8-17上午09:37:32 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveInviterollrequ")
	public ModelAndView saveInviterollrequ(HttpServletRequest request)throws Exception {
		logger.debug("\nes InviterollrequController||saveInviterollrequ\n");
		String projectId = request.getParameter("projectId") ;
		String supplierId = request.getParameter("supplierIds");
		String typeflag = request.getParameter("typeflag");
		String[] suppliers = supplierId.split(",");
		if("update".equals(typeflag)){//更新之前删除原来的邀请函
			inviterollrequService.removeInviterollrequ(projectId);
		}
		inviterollrequService.saveInviterollrequ(suppliers, projectId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * 
	 * Description :跳转到审核邀请函页面  
	 * Create Date: 2010-8-27下午01:07:33 by liuke  Modified Date: 2010-8-27下午01:07:33 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toInviterollrequAudit")
	public ModelAndView toInviterollrequAudit(HttpServletRequest request)throws Exception {
		logger.debug("\nes InviterollrequController||toInviterollrequAudit\n");
		String projectId = request.getParameter("projectId") ;
		String returnName = "blankView";
		Map<String ,Object> model = new HashMap<String ,Object>();
			model.put("projectId", projectId);
		List<InrqDetail> listInrqDetail = inrqDetailService.getInrqDetailByProjectId(projectId);
		if (listInrqDetail!=null&&listInrqDetail.size()>0) {//判断是否有录入的供应商
			Inviterollrequ inviterollrequ = listInrqDetail.get(0).getInviterollrequ();
			String auditStatus = inviterollrequ.getAuditStatus();//获取状态
			if (auditStatus!=null&&InrqAuditStatusEnum.AUDITING.equals(auditStatus)) {//判断是否为待审核的状态
				returnName = "inviterollrequAuditListView";//跳转到审核页面
				model.put("inviterollrequ", inviterollrequ);
			}else {
				returnName = "inviterollrequListView";//跳转到查看页面
			}
			model.put("inrqDetailList", listInrqDetail);
		}
		return new ModelAndView(returnName, model);
	}
	
	/**
	 * 
	 * Description :采购办：审核邀请函  
	 * Create Date: 2010-8-27下午03:37:13 by liuke  Modified Date: 2010-8-27下午03:37:13 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=inviterollrequAudit")
	public ModelAndView inviterollrequAudit(HttpServletRequest request)throws Exception {
		logger.debug("\nes InviterollrequController||inviterollrequAudit\n");
		String inviterollrequId = request.getParameter("inviterollrequId") ;
		String status = request.getParameter("status");
		String opinion = request.getParameter("opinion");
		inviterollrequService.audit(inviterollrequId,status,opinion);
		Map<String ,Object> model = new HashMap<String ,Object>();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	/**
	 * Description :  代理机构：提交邀请函
	 * Create Date: 2010-10-15下午05:25:30 by shenjianzhuang  Modified Date: 2010-10-15下午05:25:30 by shenjianzhuang
	 * @param request
	 * @return
	 * @throws Exception
	 * @return  ModelAndView
	 * @Exception		
	 */	
	@RequestMapping(params="method=toInviterollrequSub")
	public ModelAndView toInviterollrequSub(HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nes InviterollrequController||toInviterollrequSub\n");
		String objId = request.getParameter("objId") ;
		inviterollrequService.updateInviterollrequ(objId);
		status.setComplete();
		Map<String ,Object> model = new HashMap<String ,Object>();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	/**
	 * 
	 * Description :获得邀请名单申请单列表  
	 * Create Date: 2010-8-26下午05:10:21 by liuke  Modified Date: 2010-8-26下午05:10:21 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=searchInviterollrequByQueryObject")
	public ModelAndView searchInviterollrequByQueryObject(HttpServletRequest request,SessionStatus status)throws Exception {	
		logger.debug("\nes InviterollrequController||searchInviterollrequByQueryObject\n");
		User user = AuthenticationHelper.getCurrentUser();
		String projName = request.getParameter("projName");
		String projCode = request.getParameter("projCode");
		String useStatus = request.getParameter("useStatus");
		String auditStatus = request.getParameter("auditStatus");
		Page page = prePage(request);
		QueryObject<Inviterollrequ> queryObject = new QueryObjectBase<Inviterollrequ>();
			queryObject.getQueryParam().and(new QueryParam("monitorId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
			queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,useStatus));
			queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,auditStatus));
			queryObject.getQueryParam().and(new QueryParam("projName",QueryParam.OPERATOR_EQ,projName));
			queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		Page<Inviterollrequ> pageData = inviterollrequService.getInviterollrequByQueryObject(queryObject, page);
		Map model = new HashMap();
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
}
