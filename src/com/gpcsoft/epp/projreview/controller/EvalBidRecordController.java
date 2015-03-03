package com.gpcsoft.epp.projreview.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.service.PackCongFactorResponseService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.EvaSellerRecord;
import com.gpcsoft.epp.projreview.domain.EvalBidRecord;
import com.gpcsoft.epp.projreview.domain.EvalFactorResult;
import com.gpcsoft.epp.projreview.service.EvalBidRecordService;
import com.gpcsoft.epp.projreview.service.OpenBidService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="evalBidRecordFormView"
  *  url="view/es/planform/projreview/evalBidRecordForm.jsp"
  * @gpcsoft.view value="evalBidRecordTreeFormView"
  *  url="view/es/planform/projreview/evalBidRecordTreeForm.jsp"
  * @gpcsoft.view value="evalBidRecordListView"
  *  url="view/es/planform/projreview/evalBidRecordList.jsp"
  * @gpcsoft.view value="evalBidRecordDetailView"
  *  url="view/es/planform/projreview/evalBidRecordDetail.jsp"
  * @gpcsoft.view value="syndicEvalBidRecordListView"
  *  url="view/es/planform/projreview/syndicEvalBidRecordList.jsp"
  * @gpcsoft.view value="createSyndicEvalBidRecordFormView"
  *  url="view/es/planform/projreview/createSyndicEvalBidRecordForm.jsp"
  * @gpcsoft.view value="updateSyndicEvalBidRecordFormView"
  *  url="view/es/planform/projreview/updateSyndicEvalBidRecordForm.jsp"
  * @gpcsoft.view value="syndicEvalTypeBidRecordListView"
  *  url="view/es/planform/projreview/syndicEvalTypeBidRecordList.jsp"
  * @gpcsoft.view value="noOpenBidView"
  *  url="view/es/common/noOpenBid.jsp"
  * 
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={EvalBidRecord.class})
@RequestMapping("/EvalBidRecordController.do")//页面请求路径,可修改
public class EvalBidRecordController extends AnnotationMultiController<EvalBidRecord> {

	@Autowired(required=true) @Qualifier("evalBidRecordServiceImpl")
	private EvalBidRecordService evalBidRecordService;
    
	@Autowired(required=true) @Qualifier("packCongFactorResponseServiceImpl")
	private PackCongFactorResponseService packCongFactorResponseService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("openBidServiceImpl")
	private OpenBidService openBidService;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	/**
	 * FuncName:toSyndicEvalBidRecordListView
	 * Description:跳转到评审集合页面
	 * @param packId:包ID/项目ID,factorTypeId:指标分类主键,如果没有指标分类,则根据所有指标对供应商打分
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-20 下午03:22:39  
	 */
	@RequestMapping(params = "method=toSyndicEvalBidRecordListView")
	public ModelAndView toSyndicEvalBidRecordListView(String packId,String factorTypeId){
		logger.debug("\nes EvalBidRecordController||toSyndicEvalBidRecordListView\n");
		List<Map<String,String>> suppliers = evalBidRecordService.getAllBidSupplierByPackId(packId);// 获取投标供应商
		String returnUrl = "";
		Boolean flag = openBidService.isOpenBided(packId);
		Map<String, Object> model = new HashMap<String, Object>();
		if(flag){
			returnUrl = "syndicEvalBidRecordListView";
		}else{
			returnUrl = "noOpenBidView";
			model.put("errorType", "noOpenBid");
		}
		model.put("packId", packId);
		model.put("suppliers", suppliers);
		model.put("factorTypeId", (null == factorTypeId || "UNDEFINED".equals(factorTypeId.toUpperCase())) || "NULL".equals(factorTypeId.toUpperCase()) ? "":factorTypeId);
		return new ModelAndView(returnUrl,model);
	}
	
	/**
	 * FuncName:toSyndicEvalBidRecordFormView
	 * Description:跳转到评审List页面
	 * @param supplierId:供应商ID,packId:包ID/项目ID,factorTypeId:指标分类ID,如果没有指标分类,则根据所有指标对供应商打分
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-20 下午03:22:39  
	 */
	@RequestMapping(params = "method=toSyndicEvalBidRecordFormView")
	public ModelAndView toSyndicEvalBidRecordFormView(String supplierId,String packId,String factorTypeId,String typeCode) throws Exception{
		logger.debug("\nes EvalBidRecordController||toSyndicEvalBidRecordFormView\n");
		String returnUrl = "updateSyndicEvalBidRecordFormView";// 更新
		Map<String, Object> model = new HashMap<String, Object>();
		User user = AuthenticationHelper.getCurrentUser();
		List<EvalFactorResult> evalFactorResultList = evalBidRecordService.getAllEvalFactorResult(supplierId,packId,factorTypeId,user.getObjId());// 获取评审打分
		EvaSellerRecord evaSellerRecord = evalBidRecordService.getEvaSellerRecord(supplierId,packId,user.getObjId());// 获取评审记录
		if (null == evaSellerRecord) {// 没有评审记录，组装评审记录
			evaSellerRecord = new EvaSellerRecord();
			OrgInfo supplierInfo = userApiService.getOrgInfoById(supplierId);
			evaSellerRecord.setSupplier(supplierInfo);
			evaSellerRecord.setSupplierName(supplierInfo.getOrgName());
			evaSellerRecord.setEvalId("");
			Project pack = (Project)projectService.get(packId, Project.class);
			evaSellerRecord.setSubProjId(pack.getObjId());
			evaSellerRecord.setSubProjName(pack.getProjName());
			evaSellerRecord.setSubProjCode(pack.getProjCode());
			// 获取投标总金额
			BigDecimal bidQuoteSum = evalBidRecordService.getBidQuoteSum(supplierId,packId);
			evaSellerRecord.setQuoteSum(bidQuoteSum);
			returnUrl = "createSyndicEvalBidRecordFormView";
		}
		model.put("evalFactorResultList", evalFactorResultList);
		model.put("evaSellerRecord", evaSellerRecord);
		model.put("supplierId", supplierId);
		model.put("packId", packId);
		model.put("factorTypeId", factorTypeId);
		model.put("typeCode", typeCode);
		return new ModelAndView(returnUrl,model);
	}
	
	/**
	 * FuncName:getSyndicEvalTypeBidRecord
	 * Description: 获取供应商投标的指标分类
	 * @param supplierId:供应商主键,packId:包组主键
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-21 上午11:35:43 
	 */
	@RequestMapping(params = "method=getSyndicEvalTypeBidRecord")
	public ModelAndView getSyndicEvalTypeBidRecord(String supplierId,String packId){
		logger.debug("\nes EvalBidRecordController||getSyndicEvalTypeBidRecord\n");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("packId", packId);
		model.put("supplierId", supplierId);
		List<CongruousFactorType> congruousFactorTypeList = packCongFactorResponseService.getBidCongruousFactorType(supplierId,packId);
		model.put("congruousFactorTypeList", congruousFactorTypeList);
		return new ModelAndView("syndicEvalTypeBidRecordListView",model);
	}
}
