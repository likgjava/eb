package com.gpcsoft.epp.bid.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidItems;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.service.BailRecordService;
import com.gpcsoft.epp.bid.service.BidPackageService;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.service.PackCongFactorResponseService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;
import com.gpcsoft.srplatform.sysconfig.service.SysConfigService;

/**
  * @gpcsoft.view value="bidFormView"
  *  url="view/es/planform/bid/bidForm.jsp"
  * @gpcsoft.view value="bidTreeFormView"
  *  url="view/es/planform/bid/bidTreeForm.jsp"
  * @gpcsoft.view value="bidListView"
  *  url="view/es/planform/bid/bidList.jsp"
  * @gpcsoft.view value="bidDetailView"
  *  url="view/es/planform/bid/bidDetail.jsp"
  * @gpcsoft.view value="NosignUprecordPageView"
  *  url="view/es/common/NosignUprecordPage.jsp"
  * @gpcsoft.view value="bidResultPageView"
  *  url="view/es/planform/bid/bidResultPageView.jsp"
  * @gpcsoft.view value="bidResultPageListView"
  *  url="view/es/planform/bid/bidResultPageList.jsp"
  * @gpcsoft.view value="loadFactorInfoFormView"
  *  url="view/es/planform/bid/bidFactorInfoForm.jsp"
  * @gpcsoft.view value="updateBidFormView"
  *  url="view/es/planform/bid/updateBidForm.jsp" 
  * @gpcsoft.view value="bidItemsView"
  *  url="view/es/planform/bid/bidItemsList.jsp" 
  * @gpcsoft.view value="bidItemsDetail"
  *  url="view/es/planform/bid/bidItemsDetailList.jsp" 
  * @gpcsoft.view value="bidFactorResponseDetail"
  *  url="view/es/planform/bid/bidFactorResponseDetail.jsp" 
  * @gpcsoft.view value="syndicEvalBidRecordList" 
  *  url="view/es/planform/bid/syndicEvalBidRecordList.jsp"
  * @gpcsoft.view value="underLinebidForm" 
  *  url="view/es/planform/bid/underLinebidForm.jsp"
  * @gpcsoft.view value="underLinebidDetail"  
  *  url="view/es/planform/bid/underLinebidDetail.jsp"
  * @gpcsoft.view value="underLineUpdateBidForm"  
  *  url="view/es/planform/bid/underLineUpdateBidForm.jsp"
  *   @gpcsoft.view value="toSupplierBidTabView"
  *  url="view/es/planform/signuprecord/supplierBidTab.jsp"
  *   @gpcsoft.view value="toSupplierBidDetailView"
  *  url="view/es/planform/signuprecord/supplierBidDetail.jsp"
  *  @gpcsoft.view value="confirmBailRecordPage"
  *  url="view/es/planform/bid/confirmBailRecordPage.jsp"
  *  @gpcsoft.view value="toViewConfirmBailRecordPage"
  *  url="view/es/planform/bid/confirmBailRecordViewList.jsp"
  *   @gpcsoft.view value="toOpenBidWord"
  *  url="view/es/planform/bid/openBidWord.jsp"
  *  @gpcsoft.view value="toHostSpeakWord"
  *  url="view/es/planform/bid/hostSpeakWord.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Bid.class})
@RequestMapping("/BidController.do")//页面请求路径,可修改
public class BidController extends AnnotationMultiController<Bid> {

	@Autowired(required=true) @Qualifier("bidServiceImpl")
	private BidService bidService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;

	@Autowired(required=true) @Qualifier("packCongFactorResponseServiceImpl")
	private PackCongFactorResponseService packCongFactorResponseService;
	
	@Autowired(required = true) @Qualifier("sysConfigServiceImpl")
	private SysConfigService sysConfigService;
	
	@Autowired(required = true) @Qualifier("bailRecordServiceImpl")
	private BailRecordService bailRecordService;
	
	@Autowired(required=true) @Qualifier("bidPackageServiceImpl")
	private BidPackageService bidPackageService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;  //freeMarkerService;
	
	
	/** 
	 * Description :  跳转到供应商投标详情页
	 * 供应商投标文件、文件大小、文件名称
	 * Create Date: 2011-7-8上午10:53:23 by sunl  Modified Date: 2011-7-8上午10:53:23 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=toSupplierBidTab")
	public ModelAndView toSupplierBidTab(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||toSupplierBidDetail\n");
		Map model = new HashMap();
		String projectId = request.getParameter("projectId");//项目ID
		if(StringUtils.hasLength(projectId)) {//获取分包信息
			Project project = projectService.get(projectId);
			ProjectRule pule = projectService.getProjectRuleByProjectId(projectId);
			List<Project> subPrjList = projectService.getSubProjectByProjectId(projectId);
			if(pule.getIsDividePack()==false){
				subPrjList = new ArrayList<Project>();
				subPrjList.add(project);
			}
			model.put("subPrjList", subPrjList);
		} 
		return new ModelAndView("toSupplierBidTabView", model);
	}
	
	/** 
	 * Description :  跳转到供应商投标详情页
	 * 供应商投标文件、文件大小、文件名称
	 * Create Date: 2011-7-8上午10:53:23 by sunl  Modified Date: 2011-7-8上午10:53:23 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=toSupplierBidDetail")
	public ModelAndView toSupplierBidDetail(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||toSupplierBidDetail\n");
		Map model = new HashMap();
		
		String supplierName = request.getParameter("supplierName");//供应商
		String projectId = request.getParameter("projectId");//项目ID
		String packId = request.getParameter("packId");//包件ID
		String supplierId = request.getParameter("supplierId");//供应商id 
		
		if(!StringUtils.hasLength(supplierId)) {
			supplierId = AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId();
		}
		
		//获取供应商投标文件、文件大小、投标状态等信息
		List<String[]> supplierBidInfoList = bidService.getBidInfoList(projectId,packId,supplierId);
		model.put("supplierBidInfoList", supplierBidInfoList);
//		model.put("supplierName", supplierName==null?((OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo()).getOrgName():com.gpcsoft.core.utils.StringUtils.ascii2Native(supplierName));//投标单位名称
		
		return new ModelAndView("toSupplierBidDetailView", model);
	}
	
    /**
     * Description:新增投标对象
     * Create Date: 2010-5-17下午04:50:34 by liuke  
     * Modified Date: 2010-5-17下午04:50:34 by liuke
     * @param   request:请求,bid:投标对象,bidItem:投标品目JSON数组字符串
     * @return  ModelAndView
     * @Exception
     */
	@RequestMapping(params = "method=saveBid")
	public ModelAndView saveBid(HttpServletRequest request,Bid bid, SessionStatus status,String bidItem)throws Exception {
		logger.debug("\nes BidController||saveBid\n");
		if(bidService.isCanBid(bid.getProject().getObjId(), bid.getSupplier().getObjId(),null)){//判断是否可以投标
			Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 附件上传控制
			String packIds = request.getParameter("PACK_IDS");
			if(null == packIds || "".equals(packIds)){
				throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_FACTOR_INFO_IS_ERROR));
			}
			List<BidItems> bidItemsList = new ArrayList<BidItems>();
			if (null != bidItem && !"".equals(bidItem) && !"UNDEFINED".equals(bidItem.toUpperCase())) {// 组装投标品目数据
				JSONArray jsonArray = JSONArray.fromObject(bidItem);
				if (null != jsonArray && jsonArray.isArray()) {
					for(int i=0;i<jsonArray.size();i++){
						JSONObject jSONObject = (JSONObject)jsonArray.get(i);
						BidItems bidItems = (BidItems)JSONObject.toBean(jSONObject,BidItems.class);
						bidItemsList.add(bidItems);
					}
				}
			}
			String strNum = request.getParameter("listNum");// 1.组装指标响应数据
			if(null != strNum && !"".equals(strNum)){
				Integer listNum = Integer.parseInt(strNum);
				List<CongFactorResponse> congFactorResponseList = new ArrayList<CongFactorResponse>();
				for(int i=0;i<listNum;i++){
					CongFactorResponse congFactorResponse = new CongFactorResponse();
					congFactorResponse.setObjId(request.getParameter("objId"+i));
					congFactorResponse.setFactorId(request.getParameter("factorId"+i));
					congFactorResponse.setFactorName(request.getParameter("factorName"+i));
					congFactorResponse.setRespValue(request.getParameter("respValue"+i));
					congFactorResponse.setPackIds(request.getParameter("packId"+i));
					if(null == congFactorResponse.getObjId() || "".equals(congFactorResponse.getObjId())){
						congFactorResponse.setCreateTime(new java.util.Date());
						congFactorResponse.setObjId(null);
					}
					isUploadFile = new Boolean(request.getParameter("isUploadFile"+i));// 1.1附件上传控制
					if(isUploadFile){
						Object o=AttachmentUtil.uploadFile(request,("attrFile"+i));
						if(o instanceof GpcBaseObject){
							Attachment attachment = (Attachment)o;
							attachmentService.saveAttachment((Attachment)o);
							congFactorResponse.setAttr(attachment);
						}
					}else{
						String attId = request.getParameter("attrId"+i);
						if(null != attId && !"".equals(attId)){
							Attachment attachment = new Attachment();
							attachment.setObjId(attId);
							congFactorResponse.setAttr(attachment);
						}else{
							congFactorResponse.setAttr(null);
						}
					}
					congFactorResponseList.add(congFactorResponse);
				}
				bidService.saveBid(bid,congFactorResponseList,packIds.split(","),bidItemsList);
			}else{
				bidService.saveBid(bid,new ArrayList<CongFactorResponse>(),packIds.split(","),bidItemsList);
			}
		}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
     * Description :  修改投标对象
     * Create Date: 2010-5-17下午04:50:34 by liuke  Modified Date: 2010-5-17下午04:50:34 by liuke
     * @param   HttpServletRequest request,Bid bid
     * @param bidItem 投标品目JSON 数组字符串
     * @return  ModelAndView
     * @Exception
     */
	@RequestMapping(params = "method=updateBid")
	public ModelAndView updateBid(HttpServletRequest request,Bid bid, SessionStatus status,String bidItem)throws Exception {
		logger.debug("\nes BidController||updateBid\n");
		if(bidService.isCanBid(bid.getProject().getObjId(), bid.getSupplier().getObjId(),null)){//判断是否可以投标
			Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 附件上传控制
			String packIds = request.getParameter("PACK_IDS");
			if(null == packIds || "".equals(packIds)){
				throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_FACTOR_INFO_IS_ERROR));
			}
			List<BidItems> bidItemsList = new ArrayList<BidItems>();
			if (null != bidItem && !"".equals(bidItem) && !"UNDEFINED".equals(bidItem.toUpperCase())) {// 组装投标品目数据
				JSONArray jsonArray = JSONArray.fromObject(bidItem);
				if (null != jsonArray && jsonArray.isArray()) {
					for(int i=0;i<jsonArray.size();i++){
						JSONObject jSONObject = (JSONObject)jsonArray.get(i);
						BidItems bidItems = (BidItems)JSONObject.toBean(jSONObject,BidItems.class);
						bidItemsList.add(bidItems);
					}
				}
			}
			String strNum = request.getParameter("listNum");// 1.组装指标响应数据
			if(null != strNum && !"".equals(strNum)){
				Integer listNum = Integer.parseInt(strNum);
				List<CongFactorResponse> congFactorResponseList = new ArrayList<CongFactorResponse>();
				for(int i=0;i<listNum;i++){
					CongFactorResponse congFactorResponse = new CongFactorResponse();
					congFactorResponse.setObjId(request.getParameter("objId"+i));
					congFactorResponse.setFactorId(request.getParameter("factorId"+i));
					congFactorResponse.setFactorName(request.getParameter("factorName"+i));
					congFactorResponse.setRespValue(request.getParameter("respValue"+i));
					congFactorResponse.setPackIds(request.getParameter("packId"+i));
					if(null == congFactorResponse.getObjId() || "".equals(congFactorResponse.getObjId())){
						congFactorResponse.setCreateTime(new java.util.Date());
						congFactorResponse.setObjId(null);
					}
					isUploadFile = new Boolean(request.getParameter("isUploadFile"+i));// 1.1附件上传控制
					if(isUploadFile){
						Object o=AttachmentUtil.uploadFile(request,("attrFile"+i));
						if(o instanceof GpcBaseObject){
							Attachment attachment = (Attachment)o;
							attachmentService.saveAttachment((Attachment)o);
							congFactorResponse.setAttr(attachment);
						}
					}else{
						String attId = request.getParameter("attrId"+i);
						if(null != attId && !"".equals(attId)){
							Attachment attachment = new Attachment();
							attachment.setObjId(attId);
							congFactorResponse.setAttr(attachment);
						}else{
							congFactorResponse.setAttr(null);
						}
					}
					congFactorResponseList.add(congFactorResponse);
				}
				bidService.updateBid(bid,congFactorResponseList,packIds.split(","),bidItemsList);
			}else{
				bidService.updateBid(bid,new ArrayList<CongFactorResponse>(),packIds.split(","),bidItemsList);
			}
		}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * Description : 跳转到投标页面
	 * Create Date: 2010-5-19下午06:45:47 by liuke  
	 * Modified Date: 2010-5-19下午06:45:47 by liuke
	 * @param   request：前台页面传过来的请求
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=toBidSupplier")
	public ModelAndView toBidSupplier(HttpServletRequest request)throws Exception {
		logger.debug("\nes BidController||toBidSupplier\n");
		User user = AuthenticationHelper.getCurrentUser();
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		SignUprecord signUprecord = null;
		signUprecord = signUprecordService.getSignUprecordBySupplierId(projectId, user);
		Map<String, Object> model = new HashMap<String, Object>();
		Boolean isShowFactor = new Boolean(messageSource.getMessage("supplierBidIsShowFactor"));
		model.put("isShowFactor", isShowFactor);
		model.put("projectRule", projectRule);
		String returnName="";
		if(signUprecord!=null){// 已经报名的供应商才可以投标
			if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(signUprecord.getAuditStatus())){//判断是否为审核通过的供应商报名
				List bidList = bidService.getBidListByProjectIdAndUserId(projectId, user);
				model.put("user", user);
				request.setAttribute("project", project);
				if(null != bidList && bidList.size()>0){
					Bid bid = (Bid)bidList.get(0);
					model.put("bid", bid);
					List<Project> bidPackList = bidService.getBidPackByBidId(bid.getObjId());// 获取已经投标的包件
					if(null == projectRule.getIsDividePack() || false == projectRule.getIsDividePack()){//项目没有分包
						Project projectForBid = bidPackList.get(0); //如果没分包只有一个对象
						model.put("projectForBid", projectForBid);
					}
					model.put("bidPackList", bidPackList);
					returnName ="updateBidFormView";
					// 根据系统规则控制是否多次投标
					if (!(Boolean)sysConfigService.getSysConfigItemValueByItemCode("PubleOpenBid__supplierBid_suplierBid")) {
						returnName = "bidDetailView";// 跳转到查看页面
					}
				}else{
					Bid bid = new Bid();
					bid.setProjCode(project.getProjCode());
					bid.setProjName(project.getProjName());
					bid.setProject(project);
					bid.setBidLinkerIdCard(user.getEmp().getIdCard());
					bid.setSupplier((OrgInfo)user.getOrgInfo());
					bid.setBidLinker(user.getEmp().getName());
					model.put("bid", bid);
					returnName ="bidFormView";
				}
				// 未到投标时间 或投标时间已过，则跳转到查看投标信息页面
				java.util.Date nowDate = new java.util.Date();
				if (nowDate.before(projectRule.getSubmitStTime()) || nowDate.after(projectRule.getSubmitETime())) {
					returnName = "bidDetailView";
				}
			}else{
				model.put("judgeSignUprecord","01");//供应商报名不是审核通过的标志
				returnName = "NosignUprecordPageView";
			}
		}else{
			model.put("judgeSignUprecord","02");//供应商没报名标志
			returnName = "NosignUprecordPageView";
		}
		return new ModelAndView(returnName,model);
	}
	
	/**
	 * Description : 跳转到投标结果页面
	 * Create Date: 2010-6-4上午10:29:12 by liuke  Modified Date: 2010-6-4上午10:29:12 by liuke
	 * @param   request:请求,stauts:会话实例状态
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params= "method=toBidResultPage")
	public ModelAndView toBidResultPage(HttpServletRequest request,SessionStatus stauts)throws Exception { 
		String projectId = request.getParameter("projectId");
		Map<String,Object> model = new HashMap<String,Object>();
		List subProjectList = projectService.getSubProjectByProjectId(projectId);
		model.put("subProjectList", subProjectList);
		return new ModelAndView("bidResultPageView",model);
	}
	
	/**
	 * Description: 加载指标信息
	 * @param packId
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-4 上午11:47:06 By wanghz
	 */
	@RequestMapping(params= "method=loadFactorInfo")
	public ModelAndView loadFactorInfo(String bidId,String packIds,String projectId)throws Exception {
		logger.debug("\nes BidController||loadFactorInfo\n");
		List<Project> checkPackList = new ArrayList<Project>();// 选择的包件或项目
		Project project = projectService.getProjectByObjId(projectId);// 根据投标ID、包件Ids获取指标响应
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		if (projectRule!=null&&projectRule.getIsDividePack()!=null &&!projectRule.getIsDividePack()) {//项目没分包
			packIds = projectId;
			checkPackList.add(project);
		}else{//分包
			for(Project pack:project.getSubProject()){
				if(null != packIds && packIds.contains(pack.getObjId())){
					checkPackList.add(pack);
				}
			}
		}
		if (null == bidId || "".equals(bidId) || "NULL".equals(bidId.toUpperCase()) || "undefined".equals(bidId)) {
			bidId = null;
		}
		String[] packId;
		if(null != packIds && !"".equals(packIds)){
			packId = packIds.split(",");
		}else{
			packId = new String[]{};
		}
		List<Map<String, String>> dataList = packCongFactorResponseService.getFactorResponseByBidIdAndPackIds(bidId,packId);
		Set<String> factorTypeIdSet = new HashSet<String>(0);// 过滤重复指标分类
		Map<String, String> datdaMap = new HashMap<String, String>();
		for(Map<String, String> temp:dataList){
			datdaMap.put(temp.get("factorTypeId"), temp.get("factorTypeName"));
			factorTypeIdSet.add(temp.get("factorTypeId"));
		}
		List<CongruousFactorType> congruousFactorTypeList = new ArrayList<CongruousFactorType>(0);
		for(String temp:factorTypeIdSet){// 获取指标分类
			CongruousFactorType congruousFactorType = new CongruousFactorType();
			congruousFactorType.setObjId(temp);
			congruousFactorType.setTypeName(datdaMap.get(temp));
			congruousFactorTypeList.add(congruousFactorType);
		}
		if (null == packIds) {// 获取投标包件
			for(Project pack:project.getSubProject()){
				for(Map<String, String> temp:dataList){
					if (null != temp.get("packId") && temp.get("packId").toString().equals(pack.getObjId())) {
						checkPackList.add(pack);
						break;
					}
				}
			}
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("checkPackList", checkPackList);
		model.put("congruousFactorList", dataList);
		model.put("factorTypeList", congruousFactorTypeList);
		return new ModelAndView("loadFactorInfoFormView",model);
	}
	
	/**
	 * Description:跳转到投标详细页面
	 * Create Date:2010-8-7下午03:27:46 by liuke  
	 * Modified Date: 2010-8-7下午03:27:46 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params= "method=toBidDetail")
	public ModelAndView toBidDetail(HttpServletRequest request,SessionStatus stauts)throws Exception { 
		String objId = request.getParameter("objId");
		Bid bid = bidService.get(objId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("bid", bid);
		return new ModelAndView("bidDetailView",model);
	}
	
	/**
	 * @Description: 跳转到投标条目列表
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-10-8 上午10:08:00 By wanghz
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params= "method=toBidItemsView")
	public ModelAndView toBidItemsView(HttpServletRequest request,String packIds,String bidId)throws Exception{
		logger.debug("\nes=BidController||toBidItemsView\n");
		Map model = new HashMap();
		List<BidItems> bidItems = bidService.getBidItemsByPackIds(packIds.split(","), bidId);
		model.put("bidItems", bidItems);
		return new ModelAndView("bidItemsView",model);
	}
	
	/**
	 * @Description: 跳转到查看投标条目列表
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-10-11 上午10:40:23 By wanghz
	 */
	@RequestMapping(params= "method=toBidItemsDetail")
	public ModelAndView toBidItemsDetail(HttpServletRequest request,String packIds,String bidId)throws Exception{
		logger.debug("\nes=BidController||toBidItemsDetail\n");
		Map<String,Object> model = new HashMap<String,Object>();
		if (null != packIds) {
			List<BidItems> bidItems = bidService.getBidItemsByPackIds(packIds.split(","), bidId);
			model.put("bidItems", bidItems);
		}
		return new ModelAndView("bidItemsDetail",model);
	}
	
	/**
	 * @Description: 查看投标响应信息
	 * @param packId
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-4 上午11:47:06 By wanghz
	 */
	@RequestMapping(params= "method=toBidFactorResponseDetail")
	public ModelAndView toBidFactorResponseDetail(String bidId,String packIds,String projectId)throws Exception {
		logger.debug("\nes BidController||toBidFactorResponseDetail\n");
		List<Project> checkPackList = new ArrayList<Project>();// 选择的包件或项目
		Project project = projectService.getProjectByObjId(projectId);// 根据投标ID、包件Ids获取指标响应
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		if (projectRule!=null&&projectRule.getIsDividePack()!=null &&!projectRule.getIsDividePack()) {//项目没分包
			packIds = projectId;
		}else{//项目分包
			for(Project pack:project.getSubProject()){
				if(null != packIds && packIds.contains(pack.getObjId())){
					checkPackList.add(pack);
				}
			}
		}
		if (null == bidId || "".equals(bidId) || "NULL".equals(bidId.toUpperCase()) || "undefined".equals(bidId)) {
			bidId = null;
		}
		String[] packId;
		if(null != packIds && !"".equals(packIds)){
			packId = packIds.split(",");
		}else{
			packId = new String[]{};
		}
		List<Map<String, String>> dataList = packCongFactorResponseService.getFactorResponseByBidIdAndPackIds(bidId,packId);
		Set<String> factorTypeIdSet = new HashSet<String>(0);// 过滤重复指标分类
		Map<String, String> datdaMap = new HashMap<String, String>();
		for(Map<String, String> temp:dataList){
			datdaMap.put(temp.get("factorTypeId"), temp.get("factorTypeName"));
			factorTypeIdSet.add(temp.get("factorTypeId"));
		}
		List<CongruousFactorType> congruousFactorTypeList = new ArrayList<CongruousFactorType>(0);
		for(String temp:factorTypeIdSet){// 获取指标分类
			CongruousFactorType congruousFactorType = new CongruousFactorType();
			congruousFactorType.setObjId(temp);
			congruousFactorType.setTypeName(datdaMap.get(temp));
			congruousFactorTypeList.add(congruousFactorType);
		}
		if (null == packIds) {// 获取投标包件
			for(Project pack:project.getSubProject()){
				for(Map<String, String> temp:dataList){
					if (null != temp.get("packId") && temp.get("packId").toString().equals(pack.getObjId())) {
						checkPackList.add(pack);
						break;
					}
				}
			}
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("checkPackList", checkPackList);
		model.put("congruousFactorList", dataList);
		model.put("factorTypeList", congruousFactorTypeList);
		return new ModelAndView("bidFactorResponseDetail",model);
	}
	
	/**
	 * 
	 * Description :跳转到线下投标页面  
	 * Create Date: 2010-11-16上午09:58:21 by liuke  Modified Date: 2010-11-16上午09:58:21 by liuke
	 * Modified Date: 2011-11-15下午16:45:47 by yangyc 从供应商中挑出审核通过的供应商返回页面
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toUnderLineBidPage")
	public ModelAndView toUnderLineBidPage(HttpServletRequest request){
		logger.debug("\nes BidController||toUnderLineBidPage\n");
		Set<SignUprecord> signUprecordSet = new HashSet<SignUprecord>();
		String projectId = request.getParameter("projectId");
		List<SignUprecord> signUprecordList = signUprecordService.getSignUprecordByprojectId(projectId);
		for (Iterator iterator = signUprecordList.iterator(); iterator
				.hasNext();) {
			SignUprecord signUprecord = (SignUprecord) iterator.next();
			if(AuditStatusEnum.AUDIT_PASS.equals(signUprecord.getAuditStatus())) {
				signUprecordSet.add(signUprecord);
			}
		}
		String returnUrl = "syndicEvalBidRecordList";
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("signUprecordList", signUprecordSet);
		return new ModelAndView(returnUrl,model);
	}
	
	
	/**
	 * Description : 跳转到线下投标页面
	 * Create Date: 2010-5-19下午06:45:47 by liuke  
	 * Modified Date: 2010-5-19下午06:45:47 by liuke
	 * @param   request:前台页面传过来的请求
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=toUnderLineBidForm")
	public ModelAndView toUnderLineBidForm(HttpServletRequest request)throws Exception {
		logger.debug("\nes BidController||toUnderLineBidForm\n");
		Map<String, Object> model = new HashMap<String, Object>();
		String signUprecordId = request.getParameter("signUprecordId");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);
		if(project.getTenderType().equals(ProjectEnum.ZFCG)){
			model.put("tenderType","ZFCG");
			if(project.getEbuyMethod().equals(EbuyMethodEnum.OPEN_BIDDING)){
				model.put("ebuyMehod","OPENBIDDING");
			}else if(project.getEbuyMethod().equals(EbuyMethodEnum.INVITE_BIDDING)){
				model.put("ebuyMehod","INVITEBIDDING");
			}else if(project.getEbuyMethod().equals(EbuyMethodEnum.NEGOTIATE)){
				model.put("ebuyMehod","NEGOTIATE");
			}else if(project.getEbuyMethod().equals(EbuyMethodEnum.INQUIRY)){
				model.put("ebuyMehod","INQUIRY");
			}else if(project.getEbuyMethod().equals(EbuyMethodEnum.SINGLE_SOURCE)){
				model.put("ebuyMehod","SINGLESOURCE");
			}
		}else if(project.getTenderType().equals(ProjectEnum.TDJY)){
			model.put("tenderType","TDJY");
			if(project.getEbuyMethod().equals(EbuyMethodEnum.LIST)){
				model.put("ebuyMehod","LIST");
			}else if(project.getEbuyMethod().equals(EbuyMethodEnum.AUCTION)){
				model.put("ebuyMehod","AUCTION");
			}else if(project.getEbuyMethod().equals(EbuyMethodEnum.OPEN_BIDDING)){
				model.put("ebuyMehod","OPENBIDDING");
			}
		}else if(project.getTenderType().equals(ProjectEnum.CQJY)){
			model.put("tenderType", "CQJY");
			if(project.getEbuyMethod().equals(EbuyMethodEnum.LIST)){
				model.put("ebuyMehod","LIST");
			}else if(project.getEbuyMethod().equals(EbuyMethodEnum.AUCTION)){
				model.put("ebuyMehod","AUCTION");
			}else if(project.getEbuyMethod().equals(EbuyMethodEnum.OPEN_BIDDING)){
				model.put("ebuyMehod","OPENBIDDING");
			}
		}else if(project.getTenderType().equals(ProjectEnum.JZGC)){
			model.put("tenderType", "JZGC");
			if(project.getEbuyMethod().equals(EbuyMethodEnum.INVITE_BIDDING)){
				model.put("ebuyMehod","INVITEBIDDING");
			}else if(project.getEbuyMethod().equals(EbuyMethodEnum.OPEN_BIDDING)){
				model.put("ebuyMehod","OPENBIDDING");
			}
		}
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		SignUprecord signUprecord = null;
		signUprecord = signUprecordService.getSignUprecordByobjId(signUprecordId);
		List<SignUprecord> signUprecordList = signUprecordService.getSignUprecordByprojectId(projectId);
		
		//过滤掉审核没有通过的报名信息
		for (Iterator iterator = signUprecordList.iterator(); iterator.hasNext();) {
			SignUprecord signUprecord2 = (SignUprecord) iterator.next();
			if(AuditStatusEnum.AUDIT_NO_PASS.equals(signUprecord2.getAuditStatus())){
				iterator.remove();
			}
		}
		for (Iterator iterator = signUprecordList.iterator(); iterator.hasNext();) {
			SignUprecord signUprecord2 = (SignUprecord) iterator.next();
			if(!signUprecord2.getSupplier().getObjId().equals(signUprecord.getSupplier().getObjId())){
				iterator.remove();
			}
		}
		String packIds = "";
		for (Iterator iterator = signUprecordList.iterator(); iterator
				.hasNext();) {
			SignUprecord signUprecord2 = (SignUprecord) iterator.next();
			packIds += signUprecord2.getProject().getObjId()+",";
		}
		model.put("packIds", packIds);
		model.put("signUprecordList", signUprecordList);
		Boolean isShowFactor = new Boolean(messageSource.getMessage("supplierBidIsShowFactor"));
		model.put("isShowFactor", isShowFactor);
		model.put("projectRule", projectRule);
		String returnName="";
		Date nowDate = new Date();
		if (nowDate.before(projectRule.getOpenBidSDate())) {//未到开标开始时间
			model.put("openBidSTime", "false");
		}
		if(signUprecord!=null){// 已经报名的供应商才可以投标
			model.put("signUprecord", signUprecord);
			if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(signUprecord.getAuditStatus())){//判断是否为审核通过的供应商报名
				Bid bid = bidService.getBidBySupplierid(signUprecord.getSupplier().getObjId(), projectId);
				request.setAttribute("project", project);
				if(null != bid){
					bid.setBidLinkerIdCard(signUprecord.getIdCard());  //把联系人ID放入bid对象中
					model.put("bid", bid);
					List<Project> bidPackList = bidService.getBidPackByBidId(bid.getObjId());// 获取已经投标的包件
					if(null == projectRule.getIsDividePack() || !projectRule.getIsDividePack()){//项目没有分包
						Project projectForBid = bidPackList.get(0); //如果没分包只有一个对象
						model.put("projectForBid", projectForBid);
					}
					model.put("bidPackList", bidPackList);
					returnName ="underLineUpdateBidForm";
					// 根据系统规则控制是否多次投标
					if (!(Boolean)sysConfigService.getSysConfigItemValueByItemCode("PubleOpenBid__supplierBid_suplierBid")) {
						returnName = "underLinebidDetail";// 跳转到查看页面
					}
				}else{
				    bid = new Bid();
					bid.setProjCode(project.getProjCode());
					bid.setProjName(project.getProjName());
					bid.setProject(project);
					bid.setBidLinkerIdCard(signUprecord.getIdCard());
					bid.setSupplier(signUprecord.getSupplier());
					bid.setBidLinker(signUprecord.getLinker());
					model.put("bid", bid);
					returnName ="underLinebidForm";
				}
				if (nowDate.before(projectRule.getOpenBidSDate())) {// 未到开标开始时间 ，则跳转到查看投标信息页面
					returnName = "underLinebidDetail";
				}
			}else{
				model.put("judgeSignUprecord","01");//供应商报名不是审核通过的标志
				returnName = "NosignUprecordPageView";
			}
		}else{
			model.put("judgeSignUprecord","02");//供应商没报名标志
			returnName = "NosignUprecordPageView";
		}
		return new ModelAndView(returnName,model);
	}
		
	
	/** 
	 * FuncName : toViewConfirmBailRecordPage
	 * Description :  跳转到查看投标结果页面
	 * Create Date: 2011-11-15下午03:30:29 by yangx  
	 * Modified Date: 2011-11-15下午03:30:29 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toViewConfirmBailRecordPage")
	public ModelAndView toViewConfirmBailRecordPage(HttpServletRequest request)throws Exception{
		logger.debug("\nes BidController||toConfirmBailRecordPage\n");
		String projectId = request.getParameter("projectId");
		String isNotFirst = "NO";  //判断是否已经确认过投标
		Project project = projectService.getProjectByObjId(projectId);
		List<Project> packList = new ArrayList<Project>();
		List<BailRecord> bailRecordListFromTable = bailRecordService.getBailRecordByProjectId(projectId);
		List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);
	    if(subProjectList.isEmpty()){ //如果项目没分包，则把项目放入packList列表中
	    	packList.add(project);
	    }else{
	    	for (Iterator iterator = subProjectList.iterator(); iterator.hasNext();) { //如果项目分包，把包件放入packList列表中
				Project subProj = (Project) iterator.next();
				packList.add(subProj);
			}
	    }
		List<SignUprecord> signUprecordList = signUprecordService.getSignUprecordByprojectId(projectId);
	   
		List<BailRecord> bailRecordList = new ArrayList<BailRecord>(); //设置页面要展示的保证金记录列表
		
		List<BidPackage>  bidPackageList = bidPackageService.getAllBidPackageListByProjectId(projectId);
		for (Iterator iterator = signUprecordList.iterator(); iterator.hasNext();) {
			SignUprecord signUprecord = (SignUprecord) iterator.next();
			String supplierName = signUprecord.getSupplier().getOrgName();
			if(supplierName.length()>10){
				signUprecord.setSupplierName(supplierName.substring(0, 10)+"...");
			}else{
				signUprecord.setSupplierName(supplierName);
			}
			 for (Iterator iterator2 = bidPackageList.iterator(); iterator2.hasNext();) {  //遍历投标子表
				BidPackage bidPackage = (BidPackage) iterator2.next();
				if(bidPackage.getPack().getObjId().equals(signUprecord.getProject().getObjId())&&bidPackage.getBid().getSupplier().getObjId().equals(signUprecord.getSupplier().getObjId())){ //判断是否确定过投标
					signUprecord.setIsBid("isBid");
					isNotFirst = "YES";
				}
			}
			 BailRecord bailRecord = new BailRecord();
			 bailRecord.setSignUprecord(signUprecord);
			 bailRecord.setBallMoney(new BigDecimal("0"));
			 bailRecordList.add(bailRecord); 
		}
		
		for (Iterator iterator = bailRecordList.iterator(); iterator.hasNext();) {
			BailRecord bailRecord = (BailRecord) iterator.next();
			 for (Iterator iterator2 = bailRecordListFromTable.iterator(); iterator2.hasNext();) {
				 BailRecord bailRecordTable = (BailRecord) iterator2.next();
				 if(bailRecordTable.getSignUprecord()!=null&&bailRecord.getSignUprecord().getObjId().equals(bailRecordTable.getSignUprecord().getObjId())){
					 bailRecord.setBallMoney(bailRecordTable.getBallMoney());   //把数据库中的保证金金额放到保证金列表中
				 }
			}
		}
		 	
		String returnUrl = "toViewConfirmBailRecordPage";
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("projId", projectId);
		model.put("isNotFirst",isNotFirst);
		model.put("signUprecordList", signUprecordList);
		model.put("packList", packList);
		model.put("bailRecordList", bailRecordList);
		return new ModelAndView(returnUrl,model);
	}
	
	
	/**
	 * FuncName: toConfirmBailRecordPage
	 * Description :  跳转到确认录入保证金页面
	 * @param 
	 * @param request
	 * @return ModelAndView
	 * @author: liuke
	 * @Create Date:2011-9-30  上午10:57:36
	 * @Modifier: liuke
	 * @Modified Date:2011-9-30  上午10:57:36
	 */
	@RequestMapping(params = "method=toConfirmBailRecordPage")
	public ModelAndView toConfirmBailRecordPage(HttpServletRequest request)throws Exception{
		logger.debug("\nes BidController||toConfirmBailRecordPage\n");
		String projectId = request.getParameter("projectId");
		String isNotFirst = "NO";  //判断是否已经确认过投标
		Project project = projectService.getProjectByObjId(projectId);
		List<Project> packList = new ArrayList<Project>();
		List<BailRecord> bailRecordListFromTable = bailRecordService.getBailRecordByProjectId(projectId);
		List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);
	    if(subProjectList.isEmpty()){ //如果项目没分包，则把项目放入packList列表中
	    	packList.add(project);
	    }else{
	    	for (Iterator iterator = subProjectList.iterator(); iterator.hasNext();) { //如果项目分包，把包件放入packList列表中
				Project subProj = (Project) iterator.next();
				packList.add(subProj);
			}
	    }
		List<SignUprecord> signUprecordList = signUprecordService.getSignUprecordByprojectId(projectId);
	   
		List<BailRecord> bailRecordList = new ArrayList<BailRecord>(); //设置页面要展示的保证金记录列表
		
		List<BidPackage>  bidPackageList = bidPackageService.getAllBidPackageListByProjectId(projectId);
		for (Iterator iterator = signUprecordList.iterator(); iterator.hasNext();) {
			SignUprecord signUprecord = (SignUprecord) iterator.next();
			String supplierName = signUprecord.getSupplier().getOrgName();
			if(supplierName.length()>10){
				signUprecord.setSupplierName(supplierName.substring(0, 10)+"...");
			}else{
				signUprecord.setSupplierName(supplierName);
			}
			 for (Iterator iterator2 = bidPackageList.iterator(); iterator2.hasNext();) {  //遍历投标子表
				BidPackage bidPackage = (BidPackage) iterator2.next();
				if(bidPackage.getBid().getSupplier()!=null){
					if(bidPackage.getPack().getObjId().equals(signUprecord.getProject().getObjId())&&bidPackage.getBid().getSupplier().getObjId().equals(signUprecord.getSupplier().getObjId())){ //判断是否确定过投标
						signUprecord.setIsBid("isBid");
						isNotFirst = "YES";
					}
				}
			}
			 BailRecord bailRecord = new BailRecord();
			 bailRecord.setSignUprecord(signUprecord);
			 bailRecord.setBallMoney(new BigDecimal("0"));
			 bailRecordList.add(bailRecord); 
		}
		
		for (Iterator iterator = bailRecordList.iterator(); iterator.hasNext();) {
			BailRecord bailRecord = (BailRecord) iterator.next();
			 for (Iterator iterator2 = bailRecordListFromTable.iterator(); iterator2.hasNext();) {
				 BailRecord bailRecordTable = (BailRecord) iterator2.next();
				 if(bailRecordTable.getSignUprecord()!=null&&bailRecord.getSignUprecord().getObjId().equals(bailRecordTable.getSignUprecord().getObjId())){
					 bailRecord.setBallMoney(bailRecordTable.getBallMoney());   //把数据库中的保证金金额放到保证金列表中
				 }
			}
		}
		 	
		String returnUrl = "confirmBailRecordPage";
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("projId", projectId);
		model.put("isNotFirst",isNotFirst);
		model.put("signUprecordList", signUprecordList);
		model.put("packList", packList);
		model.put("bailRecordList", bailRecordList);
		return new ModelAndView(returnUrl,model);
	}
	
	
	/**
	 * FuncName: saveConfirmBailRecord
	 * Description :  保存确认投标记录
	 * @param 
	 * @param request
	 * @return ModelAndView
	 * @author: liuke
	 * @Create Date:2011-9-30  下午03:02:28
	 * @Modifier: liuke
	 * @Modified Date:2011-9-30  下午03:02:28
	 */
	@RequestMapping(params = "method=saveConfirmBailRecord")
	public ModelAndView saveConfirmBailRecord(HttpServletRequest request){
	  logger.debug("\nes BidController||saveConfirmBailRecord\n");
		String bailRecordValue = request.getParameter("bailRecordValue");
		bidService.saveBidAndBidPackage(bailRecordValue);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	
	/**
	 * FuncName: toInqpDocDetailView
	 * Description :  获取监标词
	 * @param request
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-7-5  下午02:27:20
	 * @Modifier: liuke
	 * @Modified Date:2011-7-5  下午02:27:20
	 */
	@RequestMapping(params="method=getOpenBidWord")
	public ModelAndView getOpenBidWord(HttpServletRequest request)throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		String projId = request.getParameter("projId");
		Project project = projectService.get(projId);
		//获取监标词模板内容
		model.put("projName", project.getProjName());
		model.put("buyerName", project.getBuyersName());
		model.put("agencyName", project.getAgencies().getOrgName());
		model.put("projContent", project.getContent());
		
		//投标截止时间
		ProjectRule rule = projectService.getProjectRuleByProjectId(projId);
		model.put("endSubmitTime", rule.getSubmitETime());
		
		//获取报名供应商,
		List<SignUprecord> signupRecordList = signUprecordService.getSignUprecordByprojectId(projId);
		
		HashSet<SignUprecord> signupRecordSet = new HashSet<SignUprecord>();
		for (int i=0;i<signupRecordList.size();i++ ) {
			signupRecordSet.add(signupRecordList.get(i));
		}
	
		model.put("signupRecordList", signupRecordSet);
		model.put("currentDate", new Date());
		model.put("supplyCount", signupRecordSet.size());
		
		String content = freeMarkerService.getFreeMarkerContent("openBidWord.ftl", model);
		
		model.put("content", content);
		
		return new ModelAndView("toOpenBidWord",model);
	}
	/**
	 * 
	 * Description : 获取主持人讲话
	 * Create Date: 2011-10-14下午05:41:32 by chenhongjun  
	 * Modified Date: 2011-10-14下午05:41:32 by chenhongjun
	 * @param   
	 * @return  
	 * @Exception
	 * 
	 */
	@RequestMapping(params="method=getHostSpeak")
	public ModelAndView getHostSpeak(HttpServletRequest request)throws Exception{
		/*${twoBuyerName!}  二级专公司
		${buyerName!}  业主单位/采购人
		${bidProjectName}  招标项目名称
		${bidProjectCode} 招标编号
		${bidNum} 投标人数
		*/
		Map<String,Object> model = new HashMap<String,Object>();
		String projId = request.getParameter("projId");
		Project project = projectService.get(projId);
		String twoBuyerName="";//
		OrgInfo orgInfo=orgInfoService.get(project.getBuyersId());//采购人
		if(orgInfo.getCompany()!=null){
			if(orgInfo.getCompany().getParent()!=null){
				twoBuyerName=orgInfo.getCompany().getParent().getName();
			}
		}
		model.put("twoBuyerName", twoBuyerName);
		model.put("buyerName", project.getBuyersName());
		model.put("bidProjectName", project.getProjName());
		model.put("bidProjectCode", project.getProjCode());
		//String bidNum="";//参与投标数量
		//model.put("bidNum", );
		String content = freeMarkerService.getFreeMarkerContent("hostSpeak.ftl", model);
		
		
		
		model.put("content", content);
		
		return new ModelAndView("toHostSpeakWord",model);
	} 
	
}
