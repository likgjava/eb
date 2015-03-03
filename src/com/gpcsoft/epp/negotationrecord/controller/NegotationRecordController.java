package com.gpcsoft.epp.negotationrecord.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecord;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecordItem;
import com.gpcsoft.epp.negotationrecord.service.NegotationRecordService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.service.OpenBidService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.epp.requirement.service.PreqEntryService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

/**
  * @gpcsoft.view value="negotationRecordFormView"
  *  url="view/es/planform/negotationrecord/negotationRecordForm.jsp"
  * @gpcsoft.view value="negotationRecordTreeFormView"
  *  url="view/es/planform/negotationrecord/negotationRecordTreeForm.jsp"
  * @gpcsoft.view value="negotationRecordListView"
  *  url="view/es/planform/negotationrecord/negotationRecordList.jsp"
  * @gpcsoft.view value="negotationRecordDetailView"
  *  url="view/es/planform/negotationrecord/negotationRecordDetail.jsp"
  * @gpcsoft.view value="negotationrecordInfo" 
  *  url="view/es/planform/negotationrecord/negotationrecordInfo.jsp"
  * @gpcsoft.view value="negotationRecordForm"  
  *  url="view/es/planform/negotationrecord/negotationRecordForm.jsp"
  * @gpcsoft.view value="negotationRecordDetail" 
  *  url="view/es/planform/negotationrecord/negotationRecordDetail.jsp"
  * @gpcsoft.view value="updateNegotationRecordForm"  
  *  url="view/es/planform/negotationrecord/updateNegotationRecordForm.jsp"
  * @gpcsoft.view value="noOpenBidView"
  *  url="view/es/common/noOpenBid.jsp" 
  * @gpcsoft.view value="negotationrecordList"
  *  url="view/es/planform/negotationrecord/negotationrecordList.jsp"
  * @gpcsoft.view value="negotationrecordViewInfo"
  *  url="view/es/planform/negotationrecord/negotationrecordViewInfo.jsp"
  * @gpcsoft.view value="negotationrecordViewList"
  *  url="view/es/planform/negotationrecord/negotationrecordViewList.jsp" 
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={NegotationRecord.class})
@RequestMapping("/NegotationRecordController.do")//页面请求路径,可修改
public class NegotationRecordController extends AnnotationMultiController<NegotationRecord> {

	@Autowired(required=true) @Qualifier("negotationRecordServiceImpl")
	private NegotationRecordService negotationRecordService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("preqEntryServiceImpl")
	private PreqEntryService preqEntryService;
	
	@Autowired(required=true) @Qualifier("openBidServiceImpl")
	private OpenBidService openBidService;
	
	/** 
	 * Description :  录入谈判记录：跳转到录入谈判记录页
	 * Create Date: 2010-10-22上午11:05:59 by liuke  Modified Date: 2010-10-22上午11:05:59 by liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception   
	 */
	@RequestMapping(params = "method=toNegotationRecordForProject")
	public ModelAndView toNegotationRecordForProject(HttpServletRequest request)throws Exception {
		logger.debug("\nes NegotationRecordController||toNegotationRecordForProject\n");
		String projectId = request.getParameter("projectId");
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("projProcessRule","FALSE");//判断是否分包，默认设置为不分包
		List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);
		if(!subProjectList.isEmpty()){
			model.put("subProjectList", subProjectList);
			model.put("projProcessRule","TRUE");//设置为分包
		}
		model.put("projectId", projectId);
		return new ModelAndView("negotationrecordInfo", model);
	}
	
	/**
	 * Description :  代理机构查询项目谈判记录列表
	 * Create Date: 2010-5-12上午15:27:35 by liuke  Modified Date: 2010-5-12上午15:27:35 by liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=searchNegotationRecordForAgent")
	public ModelAndView searchProjectForAgent(HttpServletRequest request)throws Exception{
		logger.debug("\nes NegotationRecordController||searchNegotationRecordForAgent\n");
		String subProjectId = request.getParameter("subProjectId");
		String tenderId = request.getParameter("projectId");
		String supplierName = request.getParameter("supplierName");
		Page page = prePage(request);
		QueryObject queryObject = QueryWebUtils.getQuery(request, NegotationRecord.class);
		User user = AuthenticationHelper.getCurrentUser();
		queryObject.getQueryParam().and(new QueryParam("subProjectId",QueryParam.OPERATOR_EQ,subProjectId));
		queryObject.getQueryParam().and(new QueryParam("tenderId",QueryParam.OPERATOR_EQ,tenderId));
		queryObject.getQueryParam().and(new QueryParam("supplierName",QueryParam.OPERATOR_EQ,supplierName));
		Page pages=negotationRecordService.searchNegotationRecordForAgent(queryObject, page);
		Map model = new HashMap();
		super.endPage(model, pages, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description : 跳转到新增谈判记录页面 
	 * Create Date: 2010-11-3下午02:36:06 by liuke  Modified Date: 2010-11-3下午02:36:06 by liuke
	 * @param    request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=toNewNegotationRecordForm")
	public ModelAndView toNewNegotationRecordForm(HttpServletRequest request)throws Exception{
		logger.debug("\nes NegotationRecordController||toNewNegotationRecordForm\n");
		String projectId = request.getParameter("projectId");
		String subProjectId = request.getParameter("subProjectId");
		Set<SignUprecord> singUpSet = new HashSet<SignUprecord>();
		List<SignUprecord> singUpList = signUprecordService.getSignUprecordByprojectId(projectId);
		for (Iterator iterator = singUpList.iterator(); iterator.hasNext();) {
			SignUprecord signUprecord = (SignUprecord) iterator.next();
			singUpSet.add(signUprecord);
		}
		List<PreqEntry> preqEntryList = new ArrayList<PreqEntry>();
		if(subProjectId==null||"".equals(subProjectId)){ //说明没有分包
			preqEntryList = preqEntryService.getPreqEntryByprojectId(projectId);
		}else{ //说明分包
			preqEntryList = preqEntryService.getPreqEntryBySubProjectId(subProjectId);
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("singUpList", singUpSet);
		model.put("preqEntryList", preqEntryList);
		model.put("recordTime",new Date());
		return new ModelAndView("negotationRecordForm", model);
	}
	
	/**
	 * Description :保存谈判记录  
	 * Create Date: 2010-11-3下午03:42:13 by liuke  Modified Date: 2010-11-3下午03:42:13 by liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=saveNegotationRecord")
	public ModelAndView saveNegotationRecord(HttpServletRequest request)throws Exception{
		logger.debug("\nes NegotationRecordController||saveNegotationRecord\n");
		String negotationRecordString = request.getParameter("negotationRecordString");
		String negotationRecordItemString = request.getParameter("negotationRecordItemString");
		List<NegotationRecordItem> negotationRecordItemsList = new ArrayList<NegotationRecordItem>();
		NegotationRecord negotationRecord = JsonUtils.json2Bean(negotationRecordString, NegotationRecord.class);
		
		if (null != negotationRecordItemString && !"".equals(negotationRecordItemString) && !"UNDEFINED".equals(negotationRecordItemString.toUpperCase())) {
			JSONArray jsonArray = JSONArray.fromObject(negotationRecordItemString);
			if (null != jsonArray && jsonArray.isArray()) {
				for(int i=1;i<jsonArray.size();i++){
					JSONObject jSONObject = (JSONObject)jsonArray.get(i);
					NegotationRecordItem negotationRecordItem = (NegotationRecordItem)JSONObject.toBean(jSONObject,NegotationRecordItem.class);
					negotationRecordItemsList.add(negotationRecordItem);
				}
			}
		}
		negotationRecordService.saveNegotationRecord(negotationRecord, negotationRecordItemsList);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * 
	 * Description :删除谈判记录  
	 * Create Date: 2010-11-3下午08:19:32 by liuke  Modified Date: 2010-11-3下午08:19:32 by liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=removeNegotationRecord")
	public ModelAndView removeNegotationRecord(HttpServletRequest request)throws Exception{
		logger.debug("\nes NegotationRecordController||removeNegotationRecord\n");
		String objId = request.getParameter("objId");
		negotationRecordService.removeNegotationRecord(objId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * Description :跳转到查看谈判记录页面  
	 * Create Date: 2010-11-4上午09:37:36 by liuke  Modified Date: 2010-11-4上午09:37:36 by liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=toNegotationRecordDetail")
	public ModelAndView toNegotationRecordDetail(HttpServletRequest request)throws Exception{
		logger.debug("\nes NegotationRecordController||toNegotationRecordDetail\n");
		String objId = request.getParameter("objId");
		NegotationRecord negotationRecord = negotationRecordService.getNegotationRecordByObjId(objId);
		List<NegotationRecordItem> NegotationRecordItemList = negotationRecordService.getNegotationRecordItemListByNegotationRecord(objId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("negotationRecord", negotationRecord);
		model.put("NegotationRecordItemList", NegotationRecordItemList);
		return new ModelAndView("negotationRecordDetail", model);
	}
	
	
	/**
	 * Description :跳转到修改谈判记录页面  
	 * Create Date: 2010-11-4上午09:37:36 by liuke  Modified Date: 2010-11-4上午09:37:36 by liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=toUpdateNegotationRecordDetail")
	public ModelAndView toUpdateNegotationRecordDetail(HttpServletRequest request)throws Exception{
		logger.debug("\nes NegotationRecordController||toUpdateNegotationRecordDetail\n");
		String objId = request.getParameter("objId");
		String projectId = request.getParameter("projectId");
		String subProjectId = request.getParameter("subProjectId");
		NegotationRecord negotationRecord = negotationRecordService.getNegotationRecordByObjId(objId);
		List<NegotationRecordItem> NegotationRecordItemList = negotationRecordService.getNegotationRecordItemListByNegotationRecord(objId);
		List<SignUprecord> singUpList = signUprecordService.getSignUprecordByprojectId(projectId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("negotationRecord", negotationRecord);
		model.put("NegotationRecordItemList", NegotationRecordItemList);
		model.put("singUpList", singUpList);
		return new ModelAndView("updateNegotationRecordForm", model);
	}
	
	
	/**
	 * Description :修改谈判记录  
	 * Create Date: 2010-11-3下午03:42:13 by liuke  Modified Date: 2010-11-3下午03:42:13 by liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=updateNegotationRecord")
	public ModelAndView updateNegotationRecord(HttpServletRequest request)throws Exception{
		logger.debug("\nes NegotationRecordController||updateNegotationRecord\n");
		String negotationRecordString = request.getParameter("negotationRecordString");
		String negotationRecordItemString = request.getParameter("negotationRecordItemString");
		List<NegotationRecordItem> negotationRecordItemsList = new ArrayList<NegotationRecordItem>();
		NegotationRecord negotationRecord = JsonUtils.json2Bean(negotationRecordString, NegotationRecord.class);
		if (null != negotationRecordItemString && !"".equals(negotationRecordItemString) && !"UNDEFINED".equals(negotationRecordItemString.toUpperCase())) {
			JSONArray jsonArray = JSONArray.fromObject(negotationRecordItemString);
			if (null != jsonArray && jsonArray.isArray()) {
				for(int i=1;i<jsonArray.size();i++){
					JSONObject jSONObject = (JSONObject)jsonArray.get(i);
					NegotationRecordItem negotationRecordItem = (NegotationRecordItem)JSONObject.toBean(jSONObject,NegotationRecordItem.class);
					negotationRecordItemsList.add(negotationRecordItem);
				}
			}
		}
		negotationRecordService.updateNegotationRecord(negotationRecord, negotationRecordItemsList);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	/**
	 * Description :跳转到谈判记录Tab页面  
	 * Create Date: 2010-11-4上午09:37:36 by liuke  Modified Date: 2010-11-4上午09:37:36 by liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=toNegotationrecordList")
	public ModelAndView toNegotationrecordList(HttpServletRequest request)throws Exception{
		logger.debug("\nes NegotationRecordController||toNegotationrecordList\n");
		String subProjectId = request.getParameter("subProjectId");
		String projectId = request.getParameter("projectId");
		Boolean flag = true;
		if(subProjectId!=null&&!"".equals(subProjectId)){//表示分包
			 flag = openBidService.isOpenBided(subProjectId);
		}else{      //没分包             
			 flag = openBidService.isOpenBided(projectId);
		}
		String returnUrl = "";
		Map<String, Object> model = new HashMap<String, Object>();
		returnUrl = "negotationrecordList";
		model.put("projectId", projectId);
		model.put("subProjectId", subProjectId);
		return new ModelAndView(returnUrl, model);
	}
	
	
	
	/**
	 * 
	 * FuncName : toViewNegotationRecordForProject
	 * Description :  跳转到谈判记录查看页面
	 * Create Date: 2011-12-5下午02:06:59 by liuke
	 * Modified Date: 2011-12-5下午02:06:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toViewNegotationRecordForProject")
	public ModelAndView toViewNegotationRecordForProject(HttpServletRequest request)throws Exception {
		logger.debug("\nes NegotationRecordController||toNegotationRecordForProject\n");
		String projectId = request.getParameter("projectId");
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("projProcessRule","FALSE");//判断是否分包，默认设置为不分包
		List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);
		if(!subProjectList.isEmpty()){
			model.put("subProjectList", subProjectList);
			model.put("projProcessRule","TRUE");//设置为分包
		}
		model.put("projectId", projectId);
		return new ModelAndView("negotationrecordViewInfo", model);
	}
	
	
	
	/**
	 * FuncName : toViewNegotationrecordList
	 * Description :  toViewNegotationrecordList
	 * Create Date: 2011-12-5下午02:44:14 by liuke
	 * Modified Date: 2011-12-5下午02:44:14 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toViewNegotationrecordList")
	public ModelAndView toViewNegotationrecordList(HttpServletRequest request)throws Exception{
		logger.debug("\nes NegotationRecordController||toViewNegotationrecordList \n");
		String subProjectId = request.getParameter("subProjectId");
		String projectId = request.getParameter("projectId");
		Boolean flag = true;
		if(subProjectId!=null&&!"".equals(subProjectId)){//表示分包
			 flag = openBidService.isOpenBided(subProjectId);
		}else{      //没分包             
			 flag = openBidService.isOpenBided(projectId);
		}
		String returnUrl = "";
		Map<String, Object> model = new HashMap<String, Object>();
		returnUrl = "negotationrecordViewList";
		model.put("projectId", projectId);
		model.put("subProjectId", subProjectId);
		return new ModelAndView(returnUrl, model);
	}
	
	
	/**
	 * FuncName : finishNegotationrecord
	 * Description :  完成谈判记录
	 * Create Date: 2011-12-5下午06:03:42 by liuke
	 * Modified Date: 2011-12-5下午06:03:42 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=finishNegotationrecord")
	public ModelAndView finishNegotationrecord(HttpServletRequest reqeust)throws Exception{
		String projectId= reqeust.getParameter("projectId");
		negotationRecordService.finishNegotationRecord(projectId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
}
