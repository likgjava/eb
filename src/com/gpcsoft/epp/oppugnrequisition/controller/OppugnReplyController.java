package com.gpcsoft.epp.oppugnrequisition.controller;

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
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnRequisition;
import com.gpcsoft.epp.oppugnrequisition.service.OppugnReplyService;
import com.gpcsoft.epp.oppugnrequisition.service.OppugnRequisitionService;

/**
  * @gpcsoft.view value="oppugnReplyFormView"
  *  url="view/es/planform/oppugnrequisition/oppugnReplyForm.jsp"
  * @gpcsoft.view value="oppugnReplyTreeFormView"
  *  url="view/es/planform/oppugnrequisition/oppugnReplyTreeForm.jsp"
  * @gpcsoft.view value="oppugnReplyListView"
  *  url="view/es/planform/oppugnrequisition/oppugnReqList.jsp"
  * @gpcsoft.view value="oppugnReplyDetailView"
  *  url="view/es/planform/oppugnrequisition/oppugnReplyDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OppugnReply.class})
@RequestMapping("/OppugnReplyController.do")//页面请求路径,可修改
public class OppugnReplyController extends AnnotationMultiController<OppugnReply> {

	@Autowired(required=true) @Qualifier("oppugnReplyServiceImpl")
	private OppugnReplyService oppugnReplyService;
	
	@Autowired(required=true) @Qualifier("oppugnRequisitionServiceImpl")
	private OppugnRequisitionService oppugnRequisitionService;
	
	/** 
	 * Description :  跳转新增质疑答复页面
	 * Create Date: 2010-6-24下午05:35:39 by sunl  Modified Date: 2010-6-24下午05:35:39 by sunl
	 * @param   质疑ID
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAddOppugnReply")
	public ModelAndView toAddOppugnReply(HttpServletRequest request,String oppugnId)throws Exception {
		logger.debug("\nes OppugnReplyController||toAddOppugnReply\n");
		String projectId = request.getParameter("projectId");
		OppugnReply reply = new OppugnReply();
		reply.setOppurReplyType("00");
		OppugnRequisition oppugnRequisition = oppugnRequisitionService.getOppugnRequisition(oppugnId);
		reply.setOppugnRequisition(oppugnRequisition);
		if(oppugnRequisition.getReplys()!=null&&oppugnRequisition.getReplys().size()>0){
			reply.setOppurReplyType("01");
		}
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("reply", reply);
		model.put("projectId", projectId);
		return new ModelAndView("oppugnReplyFormView",model);
	}
	
	/**
	 * Description :  保存回复对象
	 * Create Date: 2010-9-7上午09:41:39 by shenjianzhuang  Modified Date: 2010-9-7上午09:41:39 by shenjianzhuang
	 * @param oppugnReply
	 * @return
	 * @return  OppugnReply
	 * @Exception 
	 */	
	@RequestMapping(params = "method=saveOppugnReply")
	public ModelAndView saveOppugnReply(OppugnReply oppugnReply)throws Exception {
		logger.debug("\nes OppugnReplyController||saveOppugnReply\n");
		Map<String,Object> model = new HashMap<String,Object>();
		oppugnReplyService.saveOppugnReply(oppugnReply);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
