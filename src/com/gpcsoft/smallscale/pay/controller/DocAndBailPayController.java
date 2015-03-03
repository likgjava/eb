package com.gpcsoft.smallscale.pay.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.service.BailRecordService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyRecord;
import com.gpcsoft.epp.purchasedoc.service.DosBuyRecordService;
import com.gpcsoft.epp.signuprecord.dao.SignUprecordDao;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
  *  @gpcsoft.view value="toDocPayView"
  *  url="view/srplatform/chinabank/doc_pay.jsp"
  *  
  *  @gpcsoft.view value="toBailPayView"
  *  url="view/srplatform/chinabank/bail_pay.jsp"
  *  
  *  @gpcsoft.view value="packDocPayView"
  *  url="view/srplatform/chinabank/pack_doc_pay.jsp"
  *  
  *  @gpcsoft.view value="packBailPayView"
  *  url="view/srplatform/chinabank/pack_bail_pay.jsp"
  */
@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/DocAndBailPayController.do")//页面请求路径,可修改
public class DocAndBailPayController extends AnnotationMultiController {

	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("dosBuyRecordServiceImpl")
	private DosBuyRecordService dosBuyRecordService;
	
	@Autowired(required=true) @Qualifier("bailRecordServiceImpl")
	private BailRecordService bailRecordService;
	
	@Autowired(required=true)  @Qualifier("signUprecordDaoHibernate")
	private SignUprecordDao signUprecordDaoHibernate;
	
	/** 
	 * Description :  跳转到采购文件支付页面
	 * Create Date: 2011-7-21上午11:37:08 by sunl  Modified Date: 2011-7-21上午11:37:08 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toDocPayView")
    public ModelAndView toDocPayView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//项目ID（如果项目分包，projId为包件id）
		String projId = request.getParameter("projId");
		
		//获取项目信息
		Project proj = projectService.get(projId);
		if(StringUtils.hasLength(proj.getParentId())) {
			proj = projectService.get(proj.getParentId());
		}
		
		//支付页面带过来项目信息
		model.put("proj", proj);
		
		//根据项目ID获取采购文件ID
		//String docId = purchaseDocService.getPurchaseDocByProjectId(projId).getObjId();
		
		//支付业务ID
		model.put("v_business_id", projId);
		
		//文件金额
		model.put("v_amount", request.getParameter("v_amount"));
		
		//商户返回调用业务方法
		model.put("v_back_req_method", "docPayResult");
		
		return new ModelAndView("toDocPayView",model);
    }
	
	/** 
	 * Description :  跳转到保证金支付页面
	 * Create Date: 2011-7-21上午11:37:08 by sunl  Modified Date: 2011-7-21上午11:37:08 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toBailPayView")
    public ModelAndView toBailPayView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//项目ID（如果项目分包，projId为包件id）
		String projId = request.getParameter("projId");
		
		//获取项目信息
		Project proj = projectService.get(projId);
		if(StringUtils.hasLength(proj.getParentId())) {
			proj = projectService.get(proj.getParentId());
		}
		
		//支付页面带过来项目信息
		model.put("proj", proj);
		
		//支付业务ID
		model.put("v_business_id", projId);
		
		//保证金金额
		model.put("v_amount", request.getParameter("v_amount"));
		
		//商户返回调用业务方法
		model.put("v_back_req_method", "bailPayResult");
		
		return new ModelAndView("toBailPayView",model);
    }
	
	/** 
	 * Description :  跳转到查看每个包件的标书费的支付情况页面
	 * Create Date: 2012-1-6下午06:39:37 by likg  Modified Date: 2012-1-6下午06:39:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPackDocPayView")
	public ModelAndView toPackDocPayView(String projectId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取机构信息
		OrgInfo orgInfo = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		
		//获取包件、采购文件购买记录、报名信息
		List<Project> subProjList = projectService.getSubProjectByProjectId(projectId);
		List<DosBuyRecord> dosBuyRecordList = new ArrayList<DosBuyRecord>();
		for(Project subProj : subProjList) {
			List<DosBuyRecord> dosBuyRecords = dosBuyRecordService.getDosBuyRecordBySupplierId(orgInfo.getObjId(), subProj.getObjId());
			dosBuyRecordList.addAll(dosBuyRecords);
		}
		List<SignUprecord> signUprecordList = signUprecordDaoHibernate.getSignUprecordListByprojectIdAndSupplierId(projectId, orgInfo.getObjId());
		String signUprecordIds = "";
		for(SignUprecord signUprecord : signUprecordList) {
			signUprecordIds += signUprecord.getProject().getObjId() + ",";
		}
		model.put("signUprecordIds", signUprecordIds);
		model.put("subProjList", subProjList);
		model.put("dosBuyRecordList", dosBuyRecordList);
		
		return new ModelAndView("packDocPayView", model);
	}
	
	/** 
	 * Description :  跳转到查看每个包件的保证金的支付情况页面
	 * Create Date: 2012-1-6下午06:39:37 by likg  Modified Date: 2012-1-6下午06:39:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPackBailPayView")
	public ModelAndView toPackBailPayView(String projectId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取机构信息
		OrgInfo orgInfo = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		
		//获取包件、采购文件购买记录、报名信息
		List<Project> subProjList = projectService.getSubProjectByProjectId(projectId);
		List<BailRecord> bailRecordList = new ArrayList<BailRecord>();
		for(Project subProj : subProjList) {
			List<BailRecord> bailRecords = bailRecordService.getBailRecordByProjectAndSupplierId(subProj.getObjId(), orgInfo.getObjId());
			bailRecordList.addAll(bailRecords);
		}
		List<SignUprecord> signUprecordList = signUprecordDaoHibernate.getSignUprecordListByprojectIdAndSupplierId(projectId, orgInfo.getObjId());
		String signUprecordIds = "";
		for(SignUprecord signUprecord : signUprecordList) {
			signUprecordIds += signUprecord.getProject().getObjId() + ",";
		}
		model.put("signUprecordIds", signUprecordIds);
		model.put("subProjList", subProjList);
		model.put("bailRecordList", bailRecordList);
		
		return new ModelAndView("packBailPayView", model);
	}
}
