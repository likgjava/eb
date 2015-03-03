package com.gpcsoft.agreement.bargin.invitation.controller;

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

import com.gpcsoft.agreement.bargin.invitation.service.InvitationService;
import com.gpcsoft.agreement.bargin.signup.service.SignUprecordServiceXygh;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;

/**
 * @gpcsoft.view value="invitationForm"
 *  url="view/agreement/bargin/invitation/invitation_form.jsp"
 *  
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Bulletin.class})
@RequestMapping("/InvitationController.do")//页面请求路径,可修改
public class InvitationController extends AnnotationMultiController<Inviterollrequ> {
	
	@Autowired(required=true) @Qualifier("invitationServiceImpl")
	private InvitationService invitationService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceXyghImpl")
	private SignUprecordServiceXygh signUprecordServiceXygh;
	
	/** 
	 * Description : 跳转到邀请函页面 
	 * Create Date: 2010-10-8下午04:55:02 by guoyr  Modified Date: 2010-10-8下午04:55:02 by guoyr
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	@RequestMapping(params = "method=toSendInvitationForm")
	public ModelAndView toSendInvitationForm(String projectId) throws Exception{
			Map<String, Object> model = new HashMap<String, Object>();
			model = invitationService.getInvitationInfo(projectId);
			return new ModelAndView("invitationForm", model);
	}
	
	/** 
	 * Description : 发送邀请函 
	 * Create Date: 2010-10-8下午05:56:48 by guoyr  Modified Date: 2010-10-8下午05:56:48 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveInvitation")
	public ModelAndView saveInvitation(HttpServletRequest request, Inviterollrequ inviterollrequ, String supplierIds, SessionStatus status) throws Exception{
		Map<String, Object> model=new HashMap<String, Object>();
		String content = request.getParameter("content"); 
		invitationService.saveInvitation(inviterollrequ,content,supplierIds);
		if("true".equals(request.getParameter("isJoin"))){
			//保存供应商的报名信息
			signUprecordServiceXygh.saveSignUprecordBatch(inviterollrequ.getProject().getObjId(), supplierIds);
		}
		status.setComplete();
		return new ModelAndView(com.gpcsoft.core.Constants.JSON_VIEW, model);
	}
}
