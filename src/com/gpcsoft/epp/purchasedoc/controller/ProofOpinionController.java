package com.gpcsoft.epp.purchasedoc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import com.gpcsoft.core.Constants;
import com.gpcsoft.epp.purchasedoc.domain.ProofOpinion;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.service.ProofOpinionService;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

/**
  * @gpcsoft.view value="proofOpinionFormView"
  *  url="view/es/planform/purchasedoc/proofOpinionForm.jsp"
  * @gpcsoft.view value="proofOpinionTreeFormView"
  *  url="view/es/planform/purchasedoc/proofOpinionTreeForm.jsp"
  * @gpcsoft.view value="proofOpinionListView"
  *  url="view/es/planform/purchasedoc/proofOpinionList.jsp"
  * @gpcsoft.view value="proofOpinionDetailView"
  *  url="view/es/planform/purchasedoc/proofOpinionDetail.jsp"
  * @gpcsoft.view value="toUpdateProofOpinionForm"
  *  url="view/es/planform/purchasedoc/updateProofOpinionForm.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ProofOpinion.class})
@RequestMapping("/ProofOpinionController.do")//页面请求路径,可修改
public class ProofOpinionController extends AnnotationMultiController<ProofOpinion> {

	@Autowired(required=true) @Qualifier("proofOpinionServiceImpl")
	private ProofOpinionService proofOpinionService;

	@Autowired(required=true) @Qualifier("purchaseDocServiceImpl")
	private PurchaseDocService purchaseDocService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	/**
	 * 
	 * Description :  代理机构：录入招标文件论证页面
	 * Create Date: 2010-6-19下午02:38:07 by liuke  Modified Date: 2010-6-19下午02:38:07 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toProofOpinionForm")
	public ModelAndView toDosBuyRecordForm(HttpServletRequest request)throws Exception {
		logger.debug("\nProofOpinionController||toDosBuyRecordForm\n");
		String projectId = request.getParameter("projectId");
		PurchaseDoc purchaseDoc = purchaseDocService.getPurchaseDocByProjectId(projectId);
		if(null==purchaseDoc){
			purchaseDoc = purchaseDocService.getInqpDocByProjectId(projectId);
		}
		String fileId = purchaseDoc.getObjId();
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("projectId", projectId);
		model.put("fileId", fileId);
		return new ModelAndView("proofOpinionFormView", model);	
	}
	
	/** 
	 * Description :   代理机构：到修改招标文件论证页面
	 * Create Date: 2010-9-6下午03:35:26 by yangx  Modified Date: 2010-9-6下午03:35:26 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toUpdateProofOpinionForm")
	public ModelAndView toUpdateProofOpinionForm(HttpServletRequest request)throws Exception {
		logger.debug("\nProofOpinionController||toUpdateProofOpinionForm\n");
		String objId = request.getParameter("objId");
		ProofOpinion proofOpinion = proofOpinionService.getProofOpinionByObjId(objId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("proofOpinion", proofOpinion);
		return new ModelAndView("toUpdateProofOpinionForm", model);	
	}
	
	/**
	 * 
	 * Description : 跳转到查看文件论证结果页面
	 * Create Date: 2010-8-6下午01:16:58 by liuke  Modified Date: 2010-8-6下午01:16:58 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toLookProofOpinion")
	public ModelAndView toLookProofOpinion(HttpServletRequest request)throws Exception {
		logger.debug("\nProofOpinionController||toLookProofOpinion\n");
		String projectId = request.getParameter("projectId");
		PurchaseDoc purchaseDoc = purchaseDocService.getPurchaseDocByProjectId(projectId);
		if(null==purchaseDoc){
			purchaseDoc = purchaseDocService.getInqpDocByProjectId(projectId);
		}
		List<ProofOpinion> proofOpinionlist = proofOpinionService.getProofOpinionListbyProjectId(projectId);
		for(ProofOpinion opinion:proofOpinionlist){
			List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(opinion.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				opinion.setAttachmentlist(attachmentList);
			}else{
				opinion.setAttachmentlist(new ArrayList<Attachment>());
			}
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("purchaseDoc", purchaseDoc);
		model.put("proofOpinionlist", proofOpinionlist);
		return new ModelAndView("proofOpinionDetailView", model);	
	}
	
	/** 
	 * Description :  保存招标文件论证信息
	 * Create Date: 2010-9-6下午03:23:28 by yangx  Modified Date: 2010-9-6下午03:23:28 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=saveProofOpinion")
	public ModelAndView saveProofOpinion(HttpServletRequest request,ProofOpinion proofOpinion,SessionStatus stauts)throws Exception {
		logger.debug("\nes ProofOpinionController||saveProofOpinion\n");
		ProofOpinion proofOpinions = proofOpinionService.saveProofOpinion(proofOpinion);
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		Map model = new HashMap();
		model.put("proofOpinion",proofOpinions);
	    return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 修改招标文件论证信息
	 * Create Date: 2010-9-6下午03:23:28 by yangx  Modified Date: 2010-9-6下午03:23:28 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=updateProofOpinion")
	public ModelAndView updateProofOpinion(HttpServletRequest request,ProofOpinion proofOpinion,SessionStatus stauts)throws Exception {
		logger.debug("\nes ProofOpinionController||updateProofOpinion\n");
		ProofOpinion proofOpinions = proofOpinionService.updateProofOpinion(proofOpinion);
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		Map model = new HashMap();
		model.put("proofOpinion",proofOpinions);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * 
	 * Description : 跳转到论证询价文件列表页面
	 * Create Date: 2010-9-15下午05:52:50 by liuke  Modified Date: 2010-9-15下午05:52:50 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=toInqpDocProofOpinionList")
	public ModelAndView toInqpDocProofOpinionList(HttpServletRequest request,ProofOpinion proofOpinion,SessionStatus stauts)throws Exception {
		logger.debug("\nes ProofOpinionController||toInqpDocProofOpinionList\n");
		String projectId = request.getParameter("projectId");
		PurchaseDoc purchaseDoc = purchaseDocService.getInqpDocByProjectId(projectId);
		String isProof = "";
		if(null!=purchaseDoc&&null!=purchaseDoc.getProofOpinion()&&!purchaseDoc.getProofOpinion().equals("")){//表示询价文件被论证过
			isProof = "YES";
		}else{
			isProof = "NO";
		}
		Map model = new HashMap();
		model.put("isProof", isProof);
		return new ModelAndView("proofOpinionListView", model);
	}
	
	/**
	 * 
	 * Description : 跳转到论证招标文件列表页面
	 * Create Date: 2010-9-15下午05:52:50 by liuke  Modified Date: 2010-9-15下午05:52:50 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=toPurchaseDocProofOpinionList")
	public ModelAndView toPurchaseDocProofOpinionList(HttpServletRequest request,ProofOpinion proofOpinion,SessionStatus stauts)throws Exception {
		logger.debug("\nes ProofOpinionController||toPurchaseDocProofOpinionList\n");
		String projectId = request.getParameter("projectId");
		PurchaseDoc purchaseDoc = purchaseDocService.getPurchaseDocByProjectId(projectId);
		String isProof = "";
		if(null!=purchaseDoc&&null!=purchaseDoc.getProofOpinion()&&!purchaseDoc.getProofOpinion().equals("")){//表示询价文件被论证过
			isProof = "YES";
		}else{
			isProof = "NO";
		}
		Map model = new HashMap();
		model.put("isProof", isProof);
		return new ModelAndView("proofOpinionListView", model);
	}
}
