package com.gpcsoft.epp.oppugnrequisition.controller;

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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnRequisition;
import com.gpcsoft.epp.oppugnrequisition.service.OppugnReplyService;
import com.gpcsoft.epp.oppugnrequisition.service.OppugnRequisitionService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="toAddOppugnRequisitionFormView"
  *  url="view/es/planform/oppugnrequisition/addOppugnRequisitionForm.jsp"
  *  
  *  @gpcsoft.view value="toUpdateOppugnRequisitionFormView"
  *  url="view/es/planform/oppugnrequisition/updateOppugnRequisitionForm.jsp"
  *  
  * @gpcsoft.view value="oppugnRequisitionTreeFormView"
  *  url="view/es/planform/oppugnrequisition/oppugnRequisitionTreeForm.jsp"
  *  
  * @gpcsoft.view value="oppugnRequisitionDetailView"
  *  url="view/es/planform/oppugnrequisition/oppugnRequisitionDetail.jsp"
  *  
  * @gpcsoft.view value="supplierOppugnReqDetail"
  *  url="view/es/planform/oppugnrequisition/supplierOppugnReqDetail.jsp"
  *  
  * @gpcsoft.view value="oppugnSupplierListView"
  *  url="view/es/planform/oppugnrequisition/oppugnReqSupplierList.jsp"
  *  
  * @gpcsoft.view value="getReplyOppugnRequisition"
  *  url="view/es/planform/oppugnrequisition/supplierOppugnReqDetail.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OppugnRequisition.class})
@RequestMapping("/OppugnRequisitionController.do")//页面请求路径,可修改
@SuppressWarnings(value="unchecked")
public class OppugnRequisitionController extends AnnotationMultiController<OppugnRequisition> {

	@Autowired(required=true) @Qualifier("oppugnRequisitionServiceImpl")
	private OppugnRequisitionService oppugnRequisitionService;
	
	@Autowired(required=true) @Qualifier("oppugnReplyServiceImpl")
	private OppugnReplyService oppugnReplyService;

	/** 
	 * Description :  保存质疑
	 * Create Date: 2010-12-1上午10:29:10 by yangx  Modified Date: 2010-12-1上午10:29:10 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveOppugnRequisition")
	public ModelAndView saveOppugnRequisition(HttpServletRequest request,OppugnRequisition oppugnRequisition)throws Exception {
		logger.debug("\nes OppugnRequisitionController||saveOppugnRequisition\n");
		String projectId = request.getParameter("projectId");//项目ID
		oppugnRequisition.setUseStatus("00");//保存质疑为可更改
		oppugnRequisitionService.saveOppugnRequisition(projectId, oppugnRequisition);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  提交质疑
	 * Create Date: 2010-12-1上午10:29:19 by yangx  Modified Date: 2010-12-1上午10:29:19 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=submitOppugnRequisition")
	public ModelAndView submitOppugnRequisition(HttpServletRequest request,OppugnRequisition oppugnRequisition)throws Exception {
		logger.debug("\nes OppugnRequisitionController||submitOppugnRequisition\n");
		String projectId = request.getParameter("projectId");//项目ID
		oppugnRequisition.setUseStatus("01");//保存质疑为不可更改
		oppugnRequisitionService.saveOppugnRequisition(projectId, oppugnRequisition);
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/** 
	 * Description :  跳转到质疑新增页面
	 * Create Date: 2010-6-24上午11:18:55 by sunl  Modified Date: 2010-9-6下午11:18:55 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAddOppugnRequisition")
	public ModelAndView toAddOppugnRequisition(HttpServletRequest request,String objId,String projectId)throws Exception {
		logger.debug("\nes OppugnRequisitionController||toAddOppugnRequisition\n");
		OppugnRequisition oppugn;
		User user = AuthenticationHelper.getCurrentUser();
		oppugn = new OppugnRequisition();
		oppugn.setOppuLinker(user.getEmp().getName());
		oppugn.setOppuLinkerAddress(user.getEmp().getAddress());
		oppugn.setOppuLinkerEmail(user.getEmp().getEmail());
		oppugn.setOppuLinkerFax(user.getEmp().getFax());
		oppugn.setOppuLinkerTel(user.getEmp().getTelOffice());	
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("oppugn", oppugn);
		return new ModelAndView("toAddOppugnRequisitionFormView", model);
	}
	
	/** 
	 * Description :  跳转到质疑修改页面
	 * Create Date: 2010-9-6下午03:18:55 by shenjianzhuang  Modified Date: 2010-9-6下午03:18:55  by shenjianzhuang
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toUpdateOppugnRequisition")
	public ModelAndView toUpdateOppugnRequisition(HttpServletRequest request,String objId,String projectId)throws Exception {
		logger.debug("\nes OppugnRequisitionController||toUpdateOppugnRequisition\n");
		OppugnRequisition oppugn = oppugnRequisitionService.getOppugnRequisition(objId);
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("oppugn", oppugn);
		return new ModelAndView("toUpdateOppugnRequisitionFormView", model);
	}
	
	/** 
	 * Description :  根据质疑Id查看质疑回复
	 * Create Date: 2010-8-3上午10:45:03 by yangx  Modified Date: 2010-8-3上午10:45:03 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getReplyOppugnRequisition")
	public ModelAndView getReplyOppugnRequisition(HttpServletRequest request)throws Exception { 
		logger.debug("\nes OppugnRequisitionController||getReplyOppugnRequisition\n");
		String objId = request.getParameter("objId");
		OppugnRequisition oppugnRequisition = oppugnRequisitionService.getOppugnRequisition(objId);
		List<OppugnReply> listOppugnReply = oppugnReplyService.getReplyByOppId(objId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("oppugnRequisition",oppugnRequisition);
		if (listOppugnReply!=null&&listOppugnReply.size()>0) {
			model.put("listOppugnReply", listOppugnReply);
			model.put("size", listOppugnReply.size());
		}else {
			model.put("listOppugnReply", "no");
		}
		return new ModelAndView("getReplyOppugnRequisition", model);
	}
	
	/**
	 * Description :  根据质疑Id删除质疑对象 
	 * Create Date: 2010-9-7上午09:08:16 by shenjianzhuang  Modified Date: 2010-9-7上午09:08:16 by shenjianzhuang
	 * @param 	request
	 * @return  ModelAndView
	 * @Exception 
	 */	
	@RequestMapping(params = "method=deleteOppugnRequisition")
	public ModelAndView deleteOppugnRequisition(HttpServletRequest request)throws Exception { 
		logger.debug("\nes OppugnRequisitionController||deleteOppugnRequisition\n");
		Map model =  new  HashMap();
		oppugnRequisitionService.deleteOppugnRequisition(request.getParameter("objId"));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
