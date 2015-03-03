package com.gpcsoft.epp.inqpdoc.controller;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyRecord;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.service.DosBuyRecordService;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;

/**
  * @gpcsoft.view value="inqpBuyRecordListView"
  *  url="view/es/inquiryprice/inqpdoc/inqpBuyRecordList.jsp"
  *  @gpcsoft.view value="InqpBuyRecordFormView"
  *  url="view/es/inquiryprice/inqpdoc/inqpBuyRecordForm.jsp"
  *  @gpcsoft.view value="docDownLoadView"
  *  url="view/es/inquiryprice/inqpdoc/docDownLoad.jsp"
  *  @gpcsoft.view value="inqpBuyRecordDetailView"
  *  url=" view/es/inquiryprice/inqpdoc/inqpBuyRecordDetail.jsp"
  * 
 */



@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={DosBuyRecord.class})
@RequestMapping("/InqpDocBuyRecordController.do")//页面请求路径,可修改
@SuppressWarnings("unchecked")
public class InqpDocBuyRecordController extends AnnotationMultiController<DosBuyRecord>{
	@Autowired(required=true) @Qualifier("dosBuyRecordServiceImpl")
	private DosBuyRecordService dosBuyRecordService;
	
	@Autowired(required=true) @Qualifier("purchaseDocServiceImpl")
	private PurchaseDocService purchaseDocService;

	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	/**
	 * 
	 * Description :  跳转到录入询价文件购买信息列表页面
	 * Create Date: 2010-6-19下午02:38:07 by liuke  Modified Date: 2010-6-19下午02:38:07 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toDosBuyRecordList")
	public ModelAndView toDosBuyRecordList(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocBuyRecordController||toDosBuyRecordList\n");
		String projectId = request.getParameter("projectId");
		Map model = new HashMap();
		PurchaseDoc purchaseDoc = purchaseDocService.getInqpDocByProjectId(projectId);
		String fileId = "none";
		if(null!=purchaseDoc){
			fileId = purchaseDoc.getObjId();
		}
		model.put("fileId", fileId);
		model.put("purchaseDoc", purchaseDoc);
		model.put("projectId", projectId);
	return new ModelAndView("inqpBuyRecordListView", model);	
	}	

	
	/**
	 * 
	 * Description :  跳转到录入采购文件购买信息页面
	 * Create Date: 2010-6-19下午02:38:07 by liuke  Modified Date: 2010-6-19下午02:38:07 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toDosBuyRecordForm")
	public ModelAndView toDosBuyRecordForm(HttpServletRequest request)throws Exception {
		logger.debug("\nes=InqpDocBuyRecordController||toDosBuyRecordForm\n");
		String fileId = request.getParameter("fileId");
		String projectId = request.getParameter("projectId");
		PurchaseDoc purchaseDoc = purchaseDocService.getInqpDocByObjId(fileId);
		List<SignUprecord> singUpList = purchaseDocService.getSupplierByProjectIdAndPurchaseDocId(projectId,fileId);
		Map model = new HashMap();
		model.put("singUpList", singUpList);
		model.put("purchaseDoc", purchaseDoc);
		return new ModelAndView("InqpBuyRecordFormView", model);	
	}
	
	/**
	 * 
	 * Description :  跳转到修改采购文件购买信息页面
	 * Create Date: 2010-6-19下午02:38:07 by liuke  Modified Date: 2010-6-19下午02:38:07 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toUpdateDosBuyRecordForm")
	public ModelAndView toUpdateDosBuyRecordForm(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocBuyRecordController||toUpdateDosBuyRecordForm\n");
		String objId = request.getParameter("objId");
		String fileId = request.getParameter("fileId");
		String projectId = request.getParameter("projectId");
		PurchaseDoc purchaseDoc = purchaseDocService.getInqpDocByObjId(fileId);
		DosBuyRecord dosBuyRecord = null;
		if(null!=objId){
			 dosBuyRecord = dosBuyRecordService.getDosBuyRecordByObjId(objId);
		}else{
			 dosBuyRecord = new DosBuyRecord();
		}
		List<SignUprecord> singUpList = signUprecordService.getSignupRecordList(projectId);
		Map model = new HashMap();
		model.put("singUpList", singUpList);
		model.put("purchaseDoc", purchaseDoc);
		model.put("dosBuyRecord", dosBuyRecord);
	return new ModelAndView("InqpBuyRecordFormView", model);	
	}
	
	
	/**
	 * 
	 * Description :  跳转到查看采购文件购买信息页面
	 * Create Date: 2010-6-19下午02:38:07 by liuke  Modified Date: 2010-6-19下午02:38:07 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toUpdateDosBuyRecordDetail")
	public ModelAndView toUpdateDosBuyRecordDetail(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocBuyRecordController||toUpdateDosBuyRecordDetail\n");
		String objId = request.getParameter("objId");
		String fileId = request.getParameter("fileId");
		PurchaseDoc purchaseDoc = purchaseDocService.getInqpDocByObjId(fileId);
		DosBuyRecord dosBuyRecord = null;
		if(null!=objId){
			 dosBuyRecord = dosBuyRecordService.getDosBuyRecordByObjId(objId);
		}else{
			 dosBuyRecord = new DosBuyRecord();
		}
		Map model = new HashMap();
		model.put("purchaseDoc", purchaseDoc);
		model.put("dosBuyRecord", dosBuyRecord);
		return new ModelAndView("inqpBuyRecordDetailView", model);	
	}
	
	/**
	 * 
	 * Description :  保存采购文件购买信息
	 * Create Date: 2010-6-19下午02:38:07 by liuke  Modified Date: 2010-6-19下午02:38:07 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveDosBuyRecord")
	public ModelAndView saveDosBuyRecord(HttpServletRequest request,DosBuyRecord dosBuyRecord,SessionStatus stauts)throws Exception {
//		logger.debug("\nes InqpDocBuyRecordController||saveDosBuyRecord\n");
//		String supplierId = dosBuyRecord.getSupplierId();
//		OrgInfo supplier = dosBuyRecordService.getOrgInfo(supplierId);
//		if(null!=supplier) {
//			dosBuyRecord.setSupplierName(supplier.getOrgName());
//		}
//		dosBuyRecordService.saveDosBuyRecord(dosBuyRecord);
//		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
	    return new ModelAndView(Constants.JSON_VIEW);	
	}
	
	
}
