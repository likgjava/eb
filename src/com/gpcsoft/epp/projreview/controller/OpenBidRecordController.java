package com.gpcsoft.epp.projreview.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.service.OpenBidRecordService;
import com.gpcsoft.epp.projreview.service.OpenBidService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

/**
  * @gpcsoft.view value="openBidRecordFormView"
  *  url="view/es/planform/projreview/openBidRecordForm.jsp"
  * @gpcsoft.view value="openBidRecordTreeFormView"
  *  url="view/es/planform/projreview/openBidRecordTreeForm.jsp"
  * @gpcsoft.view value="openBidRecordListView"
  *  url="view/es/planform/projreview/openBidRecordList.jsp"
  * @gpcsoft.view value="openBidRecordDetailView"
  *  url="view/es/planform/projreview/openBidRecordDetail.jsp"
  * @gpcsoft.view value="openBidRecordListPageView"
  *  url="view/es/planform/projreview/openBidRecordListPage.jsp"
  *  @gpcsoft.view value="newOpenBidRecordListView"
  *  url="view/es/planform/projreview/openBidRecordListView.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OpenBidRecord.class})
@RequestMapping("/OpenBidRecordController.do")//页面请求路径,可修改
@SuppressWarnings("unchecked")
public class OpenBidRecordController extends AnnotationMultiController<OpenBidRecord> {

	@Autowired(required=true) @Qualifier("openBidRecordServiceImpl")
	private OpenBidRecordService openBidRecordService;

	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("openBidServiceImpl")
	private OpenBidService openBidService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleService;
	
	@Autowired(required=true) @Qualifier("bidServiceImpl")
	private BidService bidService;
	
	/**
	 * FuncName:toOpenBidRecordResult
	 * Description:跳转到开标页面
	 * @param   request,stauts
	 * @return  ModelAndView
	 * @author 	  liuke
	 * @Create Date: 2010-6-4上午10:29:12 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-4上午10:29:12 
	 */
	@RequestMapping(params= "method=toOpenBidRecordResult")
	public ModelAndView toOpenBidRecordResult(HttpServletRequest request,SessionStatus stauts)throws Exception { 
		logger.debug("\nes OpenBidRecordController||toOpenBidRecordResult\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		Map model = new HashMap();
		List subProjectList = openBidRecordService.getSubProjectByProjectId(projectId);
		if(subProjectList.size()==0&&!projectRule.getIsDividePack()){//如果没有分包
			subProjectList.add(project);
		}
		model.put("subProjectList", subProjectList);
		return new ModelAndView("newOpenBidRecordListView",model);
	}
	
	/**
	 * FuncName:getOpenBidRecordList
	 * Description : 得到开标列表对象
	 * @param   request,stauts
	 * @return  ModelAndView
	 * @author 	   liuke
	 * @Create Date: 2010-6-4上午10:29:12 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-4上午10:29:12
	 */
	@RequestMapping(params= "method=getOpenBidRecordList")
	public ModelAndView getOpenBidRecordList(HttpServletRequest request,SessionStatus stauts)throws Exception { 
		logger.debug("\nes=OpenBidRecordController||getOpenBidRecordList\n");
		Map model = new HashMap();
		String subProjId = request.getParameter("subProjId");
		List openBidRecordList = openBidRecordService.getopenBidRecordListByPackId(subProjId);
		if(openBidRecordList.size()>0){
			model.put("isKinescopeOpenBid", false);
		}else{
			model.put("isKinescopeOpenBid", true);
		}
		Project project = projectService.getProjectBySubProjectId(subProjId);
		if(null==project){//表示该对象不是包组是项目
			    project =  projectService.getProjectByObjId(subProjId);
		}
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(project.getObjId());
		if((new Date()).after(projectRule.getSubmitETime())){//判断今天是否在投标结束之后
			model.put("openBidRecord", "yes");
		}
		model.put("openBidRecordList", openBidRecordList);
		return new ModelAndView("openBidRecordListPageView",model);
	} 
	
	/**
	 * FuncName:saveOpenBidRecord
	 * Description:新增开标供应商表 
	 * @param   request,status,projectId:项目主键
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-6-4下午05:06:24 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-4下午05:06:24
	 */
	@RequestMapping(params= "method=saveOpenBidRecord")
	public ModelAndView saveOpenBidRecord(HttpServletRequest request, SessionStatus status,String projectId)throws Exception {
		logger.debug("\nes=OpenBidRecordController||saveOpenBidRecord\n");
		String subProjectId = request.getParameter("subProjectId");
		Boolean checkValue = true;//用以判断每个包组所投供应商是否满足三家
		Boolean checkValue2 = true;//用以判断是否所有供应商都录入保证金缴纳情况
		Boolean checkValue3 = true;//用以判断包组是否具有开标小组成员
		checkValue = openBidService.isJudgeSupplierNum(projectId,subProjectId);
		List<SignUprecord> signUprecordList = signUprecordService.getSignUprecordByprojectId(projectId);
	    for (Iterator iterator = signUprecordList.iterator(); iterator.hasNext();) {
			SignUprecord signUprecord = (SignUprecord) iterator.next();
			if(bidService.getBidBySupplierid(signUprecord.getSupplier().getObjId(), subProjectId)!=null){
				if(!openBidService.isSupplyerInBailrecord(projectId, signUprecord.getObjId())){
					checkValue2 = false;
				}
			}
	    }
	    checkValue3 = openBidService.isPacHaveWorkMemember(projectId,subProjectId);
	    if(checkValue&&checkValue2&&checkValue3){//都检查通过后保存开标记录
	    	ProjProcessRule projProcessRule = projProcessRuleService.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.SUBPROJECTOPENBID);//查询规则是否分包开标
			if (projProcessRule!=null&&"true".equals(projProcessRule.getResValue())) {//按照包组开标
				openBidRecordService.saveOpenBidRecordBySubProjectId(projectId,subProjectId);
			}else{//按照项目开标
				List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);//根据项目查询包组
				if (subProjectList!=null&&subProjectList.size()>0) {//判断是否存在包组
					for (Project subProject:subProjectList) {//遍历包组判断是否所有包组投标供应商数量都符合条件
						openBidRecordService.saveOpenBidRecordBySubProjectId(projectId,subProject.getObjId());
					}
				}else{//不存在包组,检查项目的投标供应商数量是否符合条件
					openBidRecordService.saveOpenBidRecordBySubProjectId(projectId,subProjectId);
				}
			}
			status.setComplete();
		}	
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
