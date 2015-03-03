package com.gpcsoft.epp.negotiate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidSubmitTypeEnum;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.service.PackCongFactorResponseService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
 * @gpcsoft.view value="NegotiateResultForm"
 *  url="view/es/competitive/NegotiateResultForm.jsp"
 *  @gpcsoft.view value="NosignUprecordPage"
 *  url="view/es/common/NosignUprecordPage.jsp"
 *  @gpcsoft.view value="loadFactorInfoForm"
 *  url="view/es/competitive/bidFactorInfoForm.jsp"
 */


@Controller//标识为控制器 
@Scope("request")
@SessionAttributes(types={Bid.class})
@RequestMapping("/NegotiateResultController.do")//页面请求路径,可修改
public class NegotiateResultController extends AnnotationMultiController<Bid>{
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
	
    /**
     * Description :  录入谈判结果
     * Create Date: 2010-8-17下午04:06 by shenjianzhuang  
     * @param   request,bid:谈判结果,status
     * @return  ModelAndView
     * @Exception
     */
	@RequestMapping(params = "method=saveOrUpdateBid")
	public ModelAndView saveOrUpdateBid(HttpServletRequest request,Bid bid, SessionStatus status)throws Exception {
		logger.debug("\nes NegotiateResultController||saveOrUpdateBid\n");
		Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));
		String packIds = request.getParameter("PACK_IDS");
		if(null == packIds || "".equals(packIds)){
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_FACTOR_INFO_IS_ERROR));
		}
		String strNum = request.getParameter("listNum");
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
				isUploadFile = new Boolean(request.getParameter("isUploadFile"+i));
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
			bid.setBidSubmitType(BidSubmitTypeEnum.NOT_ONLINE_SUBMITTYPE);
			bidService.saveOrUpdateResult(bid,congFactorResponseList,packIds.split(","));
		}else{
			bid.setBidSubmitType(BidSubmitTypeEnum.NOT_ONLINE_SUBMITTYPE);
			bidService.saveOrUpdateResult(bid,new ArrayList<CongFactorResponse>(),packIds.split(","));
		}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/**
	 * Description : 跳转到录入谈判结果页面
	 * Create Date: 2010-8-17下午02:19:56 by shenjianzhuang  Modified Date: 2010-8-17下午02:19:56 by shenjianzhuang
	 * @param request
	 * @return  ModelAndView
	 * @Exception 
	 */
	@RequestMapping(params="method=toBidSupplier")
	public ModelAndView toBidSupplier(HttpServletRequest request)throws Exception {
		logger.debug("\nes NegotiateResultController||toBidSupplier\n");
		User user = AuthenticationHelper.getCurrentUser();
		String projectId = request.getParameter("projectId");
		Project project = projectService.get(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		SignUprecord signUprecord = null;
		signUprecord = signUprecordService.getSignUprecordBySupplierId(projectId, user);
		Map<String, Object> model = new HashMap<String, Object>();
		Boolean isShowFactor = new Boolean(messageSource.getMessage("supplierBidIsShowFactor"));
		model.put("isShowFactor", isShowFactor);
		String returnUrl = "";
		if(signUprecord!=null){
		if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(signUprecord.getAuditStatus())){// 已经报名的供应商才可以投标
		List bidList = bidService.getBidListByProjectIdAndUserId(projectId, user);
		model.put("user", user);
		model.put("projectRule",projectRule);
		request.setAttribute("project", project);
		if(null != bidList && bidList.size()>0){
			Bid bid = (Bid)bidList.get(0);
			model.put("bid", bid);
			List<Project> bidPackList = bidService.getBidPackByBidId(bid.getObjId());// 获取已经投标的包件
			model.put("bidPackList", bidPackList);
		}else{
			Bid bid = new Bid();
			bid.setProjCode(project.getProjCode());
			bid.setProjName(project.getProjName());
			bid.setProject(project);
			bid.setBidLinkerIdCard(user.getEmp().getIdCard());
			bid.setSupplier((OrgInfo)user.getOrgInfo());
			bid.setBidLinker(user.getEmp().getName());
			model.put("bid", bid);
		}
			returnUrl = "NegotiateResultForm";
		}else{
			model.put("judgeSignUprecord","01");//供应商报名不是审核通过的标志
			returnUrl = "NosignUprecordPage";
		}
		}else{
			model.put("judgeSignUprecord","02");//供应商没报名标志
			returnUrl = "NosignUprecordPage";
		}
		return new ModelAndView(returnUrl,model);
	}
	
	/**
	 * Description :加载指标信息
	 * Create Date: 2010-8-17下午02:19:56 by shenjianzhuang  Modified Date: 2010-8-17下午02:19:56 by shenjianzhuang
	 * @param bidId,packIds:包组Id
	 * @return  void
	 * @Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params= "method=loadFactorInfo")
	public ModelAndView loadFactorInfo(String bidId,String packIds)throws Exception {
		logger.debug("\nes NegotiateResultController||loadFactorInfo\n");
		String[] packId;// 根据投标ID、包件Id获取指标响应
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
		for(String temp:factorTypeIdSet){
			CongruousFactorType congruousFactorType = new CongruousFactorType();
			congruousFactorType.setObjId(temp);
			congruousFactorType.setTypeName(datdaMap.get(temp));
			congruousFactorTypeList.add(congruousFactorType);
		}
		Map model = new HashMap();
		model.put("congruousFactorList", dataList);
		model.put("factorTypeList", congruousFactorTypeList);
		return new ModelAndView("loadFactorInfoForm",model);
	}
}
