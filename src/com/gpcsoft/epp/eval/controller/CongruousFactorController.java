package com.gpcsoft.epp.eval.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.bouncycastle.asn1.ocsp.Request;
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
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.utils.StringUtil;
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.service.CongruousFactorService;
import com.gpcsoft.epp.eval.service.CongruousFactorTypeService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;
import com.gpcsoft.epp.projreview.service.GenviewDefineService;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;

/**
  * @gpcsoft.view value="createCongruousFactorFormView"
  *  url="view/es/planform/eval/congruousFactorForm.jsp"
  * @gpcsoft.view value="updateCongruousFactorFormView"
  *  url="view/es/planform/eval/updateCongruousFactorForm.jsp"
  *  @gpcsoft.view value="updateCongruousFactorDetailView"
  *  url="view/es/planform/eval/updateCongruousFactorForm2.jsp"
  * @gpcsoft.view value="congruousFactorTreeFormView"
  *  url="view/es/planform/eval/congruousFactorTreeForm.jsp"
  * @gpcsoft.view value="congruousFactorListView"
  *  url="view/es/planform/eval/congruousFactorList.jsp"
  * @gpcsoft.view value="congruousFactorListView2"
  *  url="view/es/planform/eval/congruousFactorList2.jsp"
  * @gpcsoft.view value="congruousFactorDetailView"
  *  url="view/es/planform/eval/congruousFactorDetail.jsp"
  * @gpcsoft.view value="importSysFactorListView"
  * url="view/es/planform/eval/importSysFactorList.jsp"
  * @gpcsoft.view value="congruousFactorTypeTabListView"
  * url="view/es/planform/eval/congruousFactorTypeTabList.jsp"
  * @gpcsoft.view value="congruousFactorTypeTabListView2"
  * url="view/es/planform/eval/congruousFactorTypeTabList2.jsp"
  * @gpcsoft.view value="setOpenbidGeneralview"
  * url="view/es/planform/projreview/setOpenbidGeneralview.jsp"
  * @gpcsoft.view value="setGenviewDefine"
  * url="view/es/planform/projreview/setGenviewDefine.jsp"
  * @gpcsoft.view value="viewGenviewDefine"
  * url="view/es/planform/projreview/viewGenviewDefine.jsp"
  * @gpcsoft.view value="toGenviewDefineList"
  * url="view/es/planform/projreview/genviewDefineList.jsp"
  * @gpcsoft.view value="toGenviewDefineListView"
  * url="view/es/planform/projreview/genviewDefineListView.jsp"
  * @gpcsoft.view value="toDetail"
  * url="view/es/planform/eval/indexView.jsp"
  * @gpcsoft.view value="toFactorTabShow"
  * url="view/es/planform/eval/indexTabView.jsp"
  * 
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={CongruousFactor.class})
@RequestMapping("/CongruousFactorController.do")//页面请求路径,可修改
public class CongruousFactorController extends AnnotationMultiController<CongruousFactor> {

	@Autowired(required=true) @Qualifier("congruousFactorServiceImpl")
	private CongruousFactorService congruousFactorService;

	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("congruousFactorTypeServiceImpl")
	private CongruousFactorTypeService congruousFactorTypeService;
	
	@Autowired(required=true) @Qualifier("genviewDefineServiceImpl")
	private GenviewDefineService genviewDefineService;
	
	@Autowired(required = true) @Qualifier("purchaseDocServiceImpl")
	private PurchaseDocService purchaseDocService;
	
	@Autowired(required = true) @Qualifier("congruousFactorServiceImpl")
	private CongruousFactorService CongruousFactorService;

	/**
	 * @Description: 跳转到新增指标页面
	 * @param objId 
	 * @param factorTypeId
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-2 下午04:24:39 By wanghz
	 */
	@SuppressWarnings("unchecked")   
	@RequestMapping(params = "method=toCreateCongruousFactorFormView")
	public ModelAndView toCreateCongruousFactorFormView(String factorTypeId,String objId,String projectId,HttpServletRequest request)throws Exception {
		logger.debug("\nes=CongruousFactorController||toCreateCongruousFactorFormView\n");
		Map model = new HashMap();
		Project project = projectService.get(projectId);
		model.put("packList", project.getSubProject());
		model.put("project", project);
		CongruousFactor congruousFactor = new CongruousFactor();
		CongruousFactorType congruousFactorType = congruousFactorTypeService.get(factorTypeId);
		congruousFactor.setFactorType(congruousFactorType);
		congruousFactor.setAuditMethod(congruousFactorType.getAuditMethod());
		congruousFactor.setIsNeed("01");// 投标必填
		model.put("congruousFactor", congruousFactor);
		return new ModelAndView("createCongruousFactorFormView",model);
	}
	
	/**
	 * @Description: 跳转到更新指标页面
	 * @param objId 
	 * @param factorTypeId
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-2 下午04:24:39 By wanghz
	 */
	@SuppressWarnings("unchecked")   
	@RequestMapping(params = "method=toUpdateCongruousFactorFormView")
	public ModelAndView toUpdateCongruousFactorFormView(String factorTypeId,String objId,String projectId,HttpServletRequest request)throws Exception {
		logger.debug("\nes=CongruousFactorController||toUpdateCongruousFactorFormView\n");
		Map model = new HashMap();
		Project project = projectService.get(projectId);
		model.put("packList", project.getSubProject());
		model.put("project", project);
		CongruousFactor congruousFactor = congruousFactorService.get(objId);
		model.put("congruousFactor", congruousFactor);
		model.put("factypeMfactorList", congruousFactor.getFactypeMfactorSet());
		return new ModelAndView("updateCongruousFactorFormView",model);
	}
	
	/**
	 * @Description: 跳转到指标详情页面
	 * @param objId 
	 * @param factorTypeId
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-2 下午04:24:39 By wanghz
	 */
	@SuppressWarnings("unchecked")   
	@RequestMapping(params = "method=toUpdateCongruousFactorDetailView")
	public ModelAndView toUpdateCongruousFactorDetailView(String factorTypeId,String objId,String projectId,HttpServletRequest request)throws Exception {
		logger.debug("\nes=CongruousFactorController||toUpdateCongruousFactorFormView\n");
		Map model = new HashMap();
		Project project = projectService.get(projectId);
		model.put("packList", project.getSubProject());
		model.put("project", project);
		CongruousFactor congruousFactor = congruousFactorService.get(objId);
		model.put("congruousFactor", congruousFactor);
		model.put("factypeMfactorList", congruousFactor.getFactypeMfactorSet());
		return new ModelAndView("updateCongruousFactorDetailView",model);
	}
	
	/**
	 * @Description: 新增指标
	 * @param congruousFactor
	 * @param projId
	 * @param status
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-3 上午10:48:10 By wanghz
	 */
	@RequestMapping(params = "method=createCongruousFactor")
	public ModelAndView createCongruousFactor(SessionStatus status,CongruousFactor congruousFactor,String projId)throws Exception{
		logger.debug("\nes=CongruousFactorController||createCongruousFactor\n");
		if(null == projId || "".equals(projId) || "NULL".equals(projId.toUpperCase()) || null == congruousFactor){
			throw new EsException(messageSource.getMessage(EsExceptionEnum.UPDATE_CONGRUOUS_FACTOR_IS_ERROR));
		}
		congruousFactorService.saveCreateCongruousFactor(congruousFactor, projId);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * @Description: 更新指标
	 * @param congruousFactor
	 * @param projId
	 * @param status
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-3 上午10:48:10 By wanghz
	 */
	@RequestMapping(params = "method=updateCongruousFactor")
	public ModelAndView updateCongruousFactor(SessionStatus status,CongruousFactor congruousFactor,String projId)throws Exception{
		logger.debug("\nes=CongruousFactorController||updateCongruousFactor\n");
		if(null == projId || "".equals(projId) || "NULL".equals(projId.toUpperCase()) || null == congruousFactor){
			throw new EsException(messageSource.getMessage(EsExceptionEnum.UPDATE_CONGRUOUS_FACTOR_IS_ERROR));
		}
		congruousFactorService.updateCongruousFactor(congruousFactor, projId);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * @Description: 跳转到引入指标List
	 * @param projectId
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-11 上午09:34:17 By wanghz
	 */
	@SuppressWarnings("unchecked")   
	@RequestMapping(params = "method=toImportSysFactorListView")
	public ModelAndView toImportSysFactorListView(String projectId) {
		logger.debug("\nes=CongruousFactorController||toImportSysFactorListView\n");
		Map model = new HashMap();
		Project project = projectService.get(projectId);
		model.put("project", project);
		model.put("packList", project.getSubProject());
		return new ModelAndView("importSysFactorListView",model);
	}
	
	/**
	 * @Description: 保存引入系统指标
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-11 上午10:22:28 By wanghz
	 */
	@RequestMapping(params = "method=saveImportFactors")
	public ModelAndView saveImportFactors(SessionStatus status,String packIds,String isNeed,String congruousFactors){
		if(null == packIds || "".equals(packIds) || 
		   null == congruousFactors || "".equals(congruousFactors) ||
		   null == isNeed || "".equals(isNeed)){
		   throw new EsException(messageSource.getMessage(EsExceptionEnum.SAVE_IMPORT_FACTOR_ERROR));
		}
		JSONArray jsonArray = JSONArray.fromObject(congruousFactors);
		if(null != jsonArray && (!jsonArray.isEmpty()) ){
			List<CongruousFactor> congruousFactorList = new ArrayList<CongruousFactor>(1);
			for(int i=0;i<jsonArray.size();i++){
				if (null != jsonArray.get(i) && !"".equals(jsonArray.get(i).toString()) && !"NULL".equals(jsonArray.get(i).toString().toUpperCase())) {
					JSONObject jSONObject = (JSONObject)jsonArray.get(i);
					CongruousFactor congruousFactor = (CongruousFactor)JSONObject.toBean(jSONObject,CongruousFactor.class);
					congruousFactorList.add(congruousFactor);
				}
			}
			congruousFactorService.saveCreateCongruousFactor(packIds.split(","),isNeed,congruousFactorList);
		}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * @Description: 删除指标
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-9-7 下午11:59:31 By wanghz
	 */
	@RequestMapping(params = "method=removeCongruousFactor")
	public ModelAndView removeCongruousFactor(SessionStatus status,String objId){
		logger.debug("\nes=CongruousFactorController||removeCongruousFactor\n");
		congruousFactorService.removeCongruousFactor(objId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * @Description: 跳转到指标分类tab列表
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-9-19 上午10:51:01 By wanghz
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=toCongruousFactorListView")
	public ModelAndView toCongruousFactorListView(String projectId){
		logger.debug("\nes=CongruousFactorController||toCongruousFactorListView\n");
		List<CongruousFactorType> congruousFactorTypeList = congruousFactorTypeService.getCongruousFactorTypeByProjectId(projectId);
		Map model = new HashMap();
		model.put("projectId", projectId);
		model.put("congruousFactorTypeList", congruousFactorTypeList);
		return new ModelAndView("congruousFactorTypeTabListView",model);
	}
	/**
	 * @Description: 跳转到指标分类tab列表
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-9-19 上午10:51:01 By wanghz
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=toCongruousFactorListView2")
	public ModelAndView toCongruousFactorListView2(String projectId){
		logger.debug("\nes=CongruousFactorController||toCongruousFactorListView\n");
		List<CongruousFactorType> congruousFactorTypeList = congruousFactorTypeService.getCongruousFactorTypeByProjectId(projectId);
		String returnUrl = "congruousFactorTypeTabListView";
		PurchaseDoc doc = purchaseDocService.getPurchaseDocByProjectId(projectId);
		if(doc==null){ //如果招标文件不是采购文件
			doc = purchaseDocService.getInqpDocByProjectId(projectId);
		}
		Map model = new HashMap();
		model.put("doc", doc);
		model.put("projectId", projectId);
		model.put("congruousFactorTypeList", congruousFactorTypeList);
		return new ModelAndView(returnUrl,model);
	}
	
	/**
	 * @Description: 跳转到指标分类tab列表
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-9-19 上午10:51:01 By wanghz
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=toCongruousFactorList2")
	public ModelAndView toCongruousFactorList2(HttpServletRequest request){
		logger.debug("\nes=CongruousFactorController||toCongruousFactorListView\n");
		Map model = new HashMap();
		model.put("factorTypeId", request.getParameter("factorTypeId"));
		model.put("projectId", request.getParameter("projectId"));
		model.put("isShowScore", request.getParameter("isShowScore"));
		return new ModelAndView("congruousFactorListView2",model);
	}
	
	/**
	 * @Description: 获取指标分值总和
	 * @param factorTypeId 指标分类ID
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-9-19 下午02:28:31 By wanghz
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=getScoreSum")
	public ModelAndView getScoreSum(String factorTypeId){
		logger.debug("\nes=CongruousFactorController||getScoreSum\n");
		Map model = new HashMap();
		Double scoreSum = congruousFactorService.getScoreSum(factorTypeId);
		if (null == scoreSum) {
			scoreSum = 0D;
		}
		model.put("scoreSum", scoreSum);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
    /**
     * 
     * Description :跳转到设置开标一览表页面  
     * Create Date: 2010-10-8上午10:19:28 by liuke  Modified Date: 2010-10-8上午10:19:28 by liuke
     * @param   
     * @return  
     * @throws Exception 
     * @Exception
     */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=toSetOpenbidGeneralview")
	public ModelAndView toSetOpenbidGeneralview(String projectId) throws Exception{
		logger.debug("\nes CongruousFactorController||toSetOpenbidGeneralview\n");
		List<CongruousFactorType> congruousFactorTypeList = congruousFactorTypeService.getCongruousFactorTypeByProjectId(projectId);
		List<GenviewDefine> genviewDefineList = genviewDefineService.getGenviewDefineListByProjectId(projectId);
		String lastNum ="";
		if(genviewDefineList.size()>0){
			GenviewDefine lastGenviewDefine = genviewDefineList.get(genviewDefineList.size()-1);
			if(lastGenviewDefine.getShowNo()!=null){
				lastNum =  lastGenviewDefine.getShowNo().toString();
			}
		}else{
			lastNum = "0";
		}
		Map model = new HashMap();
		List<Project> subProjectList  = projectService.getSubProjectByProjectId(projectId);
		if(subProjectList!=null && subProjectList.isEmpty()){
			subProjectList = new ArrayList<Project>();
			subProjectList.add(projectService.getProjectByObjId(projectId));
		}
		model.put("projectId", projectId);
		model.put("genviewDefineList", genviewDefineList);
		model.put("subProjectList", subProjectList);
		model.put("lastNum",lastNum);
		model.put("congruousFactorTypeList", congruousFactorTypeList);
		return new ModelAndView("setOpenbidGeneralview",model);
	}
	
	
	
	/**
	 * FuncName :toSetGenviewDefine 
	 * Description :  跳转到设置开标一览表表头页面
	 * Create Date: 2011-10-31上午11:02:59 by liuke
	 * Modified Date: 2011-10-31上午11:02:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=toSetGenviewDefine")
	public ModelAndView toSetGenviewDefine(HttpServletRequest request) throws Exception{
		logger.debug("\nes CongruousFactorController||toSetGenviewDefine\n");
		String projectId = request.getParameter("projectId");
	
		
		List<Project> subProjectList = new ArrayList<Project>();
		Project project = projectService.getProjectByObjId(projectId);
		subProjectList = projectService.getSubProjectByProjectId(projectId);
		
		if(subProjectList.isEmpty()){
			subProjectList.add(project);	
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("subProjectList", subProjectList);
		return new ModelAndView("toGenviewDefineList",model);
	}
	
	
	/**
	 * FuncName : setGenviewDefine
	 * Description :  保存开标一览表表头页面
	 * Create Date: 2011-10-31下午03:29:25 by liuke
	 * Modified Date: 2011-10-31下午03:29:25 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=setGenviewDefine")
	public ModelAndView setGenviewDefine(HttpServletRequest request) throws Exception{
		logger.debug("\nes CongruousFactorController||setGenviewDefine\n");
		String subProjId = request.getParameter("subProjId");
		List<GenviewDefine> genviewDefineList = genviewDefineService.getGenviewDefineListByProjectId(subProjId);
		Boolean falg = true;
		for (Iterator iterator = genviewDefineList.iterator(); iterator.hasNext();) {
			GenviewDefine genviewDefine = (GenviewDefine) iterator.next();
			if("总报价".equals(genviewDefine.getFactorName())){ //判断该包组开标一览表表头是否存在总报价
				falg = false;
			}
		}
		if(falg){  //如果不存在总报价表头，则添加总报价
			GenviewDefine genviewDefine = new GenviewDefine();
			String objId =UUID.randomUUID().toString();
			genviewDefine.setObjId(objId);
			genviewDefine.setFactorId(objId);
			genviewDefine.setFactorName("总报价");
			genviewDefine.setShowNo(new BigDecimal("1"));
			Project p = new Project();
			p.setObjId(subProjId);
			genviewDefine.setProject(p);
			genviewDefineService.saveGenviewDefineEx(genviewDefine);
			genviewDefineList = genviewDefineService.getGenviewDefineListByProjectId(subProjId);
		}
		
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("genviewDefineList", genviewDefineList);
		return new ModelAndView("setGenviewDefine",model);
	}
	
	
	
	
	
	
	/**
	 * FuncName : saveGenviewDefine
	 * Description :  保存开标一览表头
	 * Create Date: 2011-10-31下午03:29:25 by liuke
	 * Modified Date: 2011-10-31下午03:29:25 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=saveGenviewDefine")
	public ModelAndView saveGenviewDefine(HttpServletRequest request) throws Exception{
		logger.debug("\nes CongruousFactorController||saveGenviewDefine\n");
		String objId = request.getParameter("objId");
		String projectId = request.getParameter("projectId");
		String factorId = request.getParameter("factorId");
		String factorName = request.getParameter("factorName");
		String showNo = request.getParameter("showNo");
		
		GenviewDefine genviewDefine = new GenviewDefine();
		if(objId==null||"".equals(objId)){ //如果是第一次录入
			String uId = UUID.randomUUID().toString();
			genviewDefine.setFactorId(uId);
			genviewDefine.setObjId(null);
		}else{
			genviewDefine.setFactorId(factorId);
			genviewDefine.setObjId(objId);
		}
		genviewDefine.setFactorName(factorName);
		if(showNo!=null){
			genviewDefine.setShowNo(new BigDecimal(showNo));
		}
		Project p = new Project();
		p.setObjId(projectId);
		genviewDefine.setProject(p);
		Map<String,Object> model = new HashMap<String,Object>();
		genviewDefineService.saveGenviewDefineInfo(genviewDefine);
		model.put("genviewDefine", genviewDefine);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
	/**
	 * FuncName : deleteGenviewDefine
	 * Description :  删除开标一览表头
	 * Create Date: 2011-10-31下午03:29:25 by liuke
	 * Modified Date: 2011-10-31下午03:29:25 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=deleteGenviewDefine")
	public ModelAndView deleteGenviewDefine(HttpServletRequest request) throws Exception{
		logger.debug("\nes CongruousFactorController||deleteGenviewDefine\n");
		String objId = request.getParameter("objId");
		genviewDefineService.deleteGenviewDefineAndItemByObjId(objId);
		Map<String,Object> model = new HashMap<String,Object>();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
	/**
	 * FuncName: toViewGenviewDefine
	 * Description :  查看开标一览表表头tab页
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: zhangsh
	 * @Create Date:2011-11-15  上午07:39:50
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-15  上午07:39:50
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=toViewGenviewDefine")
	public ModelAndView toViewGenviewDefine(HttpServletRequest request) throws Exception{
		logger.debug("\nes CongruousFactorController||toViewGenviewDefine\n");
		String projectId = request.getParameter("projectId");
	
		List<Project> subProjectList = new ArrayList<Project>();
		Project project = projectService.getProjectByObjId(projectId);
		subProjectList = projectService.getSubProjectByProjectId(projectId);
		
		if(subProjectList.isEmpty()){
			subProjectList.add(project);	
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("subProjectList", subProjectList);
		return new ModelAndView("toGenviewDefineListView",model);
	}
	
	
	/**
	 * FuncName: viewGenviewDefine
	 * Description :  查看开表一览表表头
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: zhangsh
	 * @Create Date:2011-11-15  上午08:00:35
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-15  上午08:00:35
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=viewGenviewDefine")
	public ModelAndView viewGenviewDefine(HttpServletRequest request) throws Exception{
		logger.debug("\nes CongruousFactorController||viewGenviewDefine\n");
		String subProjId = request.getParameter("subProjId");
		List<GenviewDefine> genviewDefineList = genviewDefineService.getGenviewDefineListByProjectId(subProjId);
		Boolean falg = true;
		for (Iterator iterator = genviewDefineList.iterator(); iterator.hasNext();) {
			GenviewDefine genviewDefine = (GenviewDefine) iterator.next();
			if("总报价".equals(genviewDefine.getFactorName())){ //判断该包组开标一览表表头是否存在总报价
				falg = false;
			}
		}
		if(falg){  //如果不存在总报价表头，则添加总报价
			GenviewDefine genviewDefine = new GenviewDefine();
			String objId =UUID.randomUUID().toString();
			genviewDefine.setObjId(objId);
			genviewDefine.setFactorId(objId);
			genviewDefine.setFactorName("总报价");
			genviewDefine.setShowNo(new BigDecimal("1"));
			Project p = new Project();
			p.setObjId(subProjId);
			genviewDefine.setProject(p);
			genviewDefineService.saveGenviewDefineEx(genviewDefine);
			genviewDefineList = genviewDefineService.getGenviewDefineListByProjectId(subProjId);
		}
		
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("genviewDefineList", genviewDefineList);
		return new ModelAndView("viewGenviewDefine",model);
	}
	
	/**
	 * FuncName : saveFinishGenviewDefine
	 * Description :完成开标一览表头设置
	 * Create Date: 2011-11-15  03:03:43 by liuke
	 * Modified Date: 2011-11-15  03:03:43 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=saveFinishGenviewDefine")
	public ModelAndView saveFinishGenviewDefine(HttpServletRequest request)throws Exception{
		logger.debug("\nes CongruousFactorController||saveFinishGenviewDefine\n");
		String projectId = request.getParameter("projectId");  //鑾峰緱椤圭洰ID
		genviewDefineService.saveFinishGenviewDefine(projectId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	
	/**
	 * FuncName : toDetail
	 * Description :跳到指标展示页面
	 * Create Date: 2011-11-29  15:01 by yangyc 
	 * Modified Date: 2011-11-29  15:01 by yangyc
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toDetail")
	public ModelAndView toDetail(HttpServletRequest request) throws Exception {
		String projectId = request.getParameter("projectId");
		String parentId = "";
		Project project = projectService.getProjectByObjId(projectId);
		if(project.getParentId()!=null) {
			parentId = project.getParentId();
		}else {
			parentId = projectId;
		}
		
		//根据项目Id查出指标所有分类.
		List<CongruousFactorType> factorTypeList = congruousFactorTypeService.getAllFactorTypeByProjectId(parentId);
		//根据项目Id查出所有指标
		List<CongruousFactor> factorList = congruousFactorService.getAllFactorByProjectId(projectId);
		//循环取得父分类以及子分类list
		List<CongruousFactorType> childTypeList = new ArrayList<CongruousFactorType>();//子分类集
		List<CongruousFactorType> fatherTypeList = new ArrayList<CongruousFactorType>();//父分类集
		for (Iterator iterator = factorTypeList.iterator(); iterator.hasNext();) {
			CongruousFactorType factorType = (CongruousFactorType) iterator.next();
			if(factorType.getParent()==null) {
				fatherTypeList.add(factorType);
			}else {
				childTypeList.add(factorType);
			}
		}
		//循环所有分类，如果该分类parent不为空，则把自己存入父分类之中
		for (Iterator iterator = factorTypeList.iterator(); iterator.hasNext();) {
			CongruousFactorType congruousFactorType = (CongruousFactorType) iterator.next();
			if(congruousFactorType.getParent()!=null) {
				congruousFactorType.getParent().getFactorTypeList().add(congruousFactorType);
			}
		}
		//循环所有分类以及所有指标，把指标存入与之相对应的分类之中
		for (Iterator iterator = factorTypeList.iterator(); iterator.hasNext();) {
			CongruousFactorType congruousFactorType = (CongruousFactorType) iterator.next();
			for (Iterator iterator2 = factorList.iterator(); iterator2.hasNext();) {
				CongruousFactor congruousFactor = (CongruousFactor) iterator2.next();
				if (congruousFactor.getFactorType().getObjId().equals(congruousFactorType.getObjId())) {
					congruousFactorType.getFactorList().add(congruousFactor);
				}
			}
		}
		//循环所有分类，查出每个分类的级数放入分类中。(级数从零开始)
		for (Iterator iterator = factorTypeList.iterator(); iterator.hasNext();) {
			CongruousFactorType congruousFactorType = (CongruousFactorType) iterator.next();
			CongruousFactorType factorType = congruousFactorType;
			int level = 0;
			while(factorType.getParent()!=null) {
				factorType = factorType.getParent();
				level++;
			}
			congruousFactorType.setFactorTypeLevel(level);
		}
		//循环所有分类，得到分类的最大级数
		int maxTypeLevel = 0;
		for (Iterator iterator = factorTypeList.iterator(); iterator.hasNext();) {
			CongruousFactorType congruousFactorType = (CongruousFactorType) iterator.next();
			if(congruousFactorType.getFactorTypeLevel()>maxTypeLevel) {
				maxTypeLevel = congruousFactorType.getFactorTypeLevel();
			}
		}
		//之前把指标存入分类，仅仅是把指标存入了最底层分类即没有子分类的分类
		//循环所有分类,每个分类如果目前指标集为空,则把它的所有子分类的指标集加起来。--从层级最大的开始逆向处理
		for(int i=maxTypeLevel;i>-1;i--){
			for (Iterator iterator = factorTypeList.iterator(); iterator.hasNext();) {
				CongruousFactorType congruousFactorType = (CongruousFactorType) iterator.next();
				if(congruousFactorType.getFactorTypeLevel()==i&&congruousFactorType.getFactorTypeList().size()!=0) {
					List<CongruousFactor> list = new ArrayList<CongruousFactor>();
					for (Iterator iterator2 = congruousFactorType.getFactorTypeList().iterator(); iterator2.hasNext();) {
						CongruousFactorType congruousFactorType1 = (CongruousFactorType) iterator2.next();
						list.addAll(congruousFactorType1.getFactorList());
					}
					congruousFactorType.setFactorList(list);
				}
			}
		}
		//对查出的分类list以及指标list进行重新排序
		List<CongruousFactorType> typeSortList = new ArrayList<CongruousFactorType>();
		List<CongruousFactor> factorSortList = new ArrayList<CongruousFactor>();
		factorTypeList = this.getTypeSort(fatherTypeList, typeSortList);//得到新排序后的指标分类集factorTypeList
		factorList = this.getFactorSort(typeSortList, factorSortList);//得到新排序后的指标集factorList
		//计算出第个分类所在行编号
		for(int i=0;i<=maxTypeLevel;i++){//循环每个级数,分别对每个级的factorType的所在行数进行赋值
			//每到一个级数，初始行编号为0
			int row = 0;
			for (Iterator iterator = factorTypeList.iterator(); iterator.hasNext();) {
				CongruousFactorType congruousFactorType = (CongruousFactorType) iterator.next();
				if(congruousFactorType.getFactorTypeLevel()==i){//如果此指标分类的级等于当前要用的级数,那么对其行数赋值
					congruousFactorType.setRowNumber(row);
					//每赋值一个指标分类,那么同级的下一个分类所在行编号则为当前行数加上当前分类所占的行数
					row += congruousFactorType.getFactorList().size();
				}
			}
		}
		//定义一个二维数组,把每个分类按顺序放到二维数组中相应的位置
		String[][] coordinate = new String[factorList.size()][maxTypeLevel+3];
		//定义一个二维数组,存放跨行数
		int[][] coorRow = new int[factorList.size()][maxTypeLevel+3];
		//定义一个二维数组,存放跨列数
		int[][] coorLie = new int[factorList.size()][maxTypeLevel+3];	
		//循环所有分类往二维数组中存放要在页面显示的分类的信息
		for (Iterator iterator = factorTypeList.iterator(); iterator.hasNext();) {
			CongruousFactorType congruousFactorType = (CongruousFactorType) iterator.next();
			coordinate[congruousFactorType.getRowNumber()][congruousFactorType.getFactorTypeLevel()]=congruousFactorType.getTypeName();
			//跨行数和跨列数也一并存入相应二维数组
			coorRow[congruousFactorType.getRowNumber()][congruousFactorType.getFactorTypeLevel()]=congruousFactorType.getFactorList().size();
			//跨列数：如果当前分类没有子分类,那么它后面应该有(最大级数-当前级数)个空位,要与后面的空位合并,空位数+1就是其应跨列数
			if(congruousFactorType.getFactorTypeList().size()==0){
				coorLie[congruousFactorType.getRowNumber()][congruousFactorType.getFactorTypeLevel()] = maxTypeLevel-congruousFactorType.getFactorTypeLevel()+1;
			}else{
				coorLie[congruousFactorType.getRowNumber()][congruousFactorType.getFactorTypeLevel()] = 1;
			}
		}
		//初始一个行数,循环所有指标,把指标以及其描述按行存入二维数组中
		//指标及其描述的跨行跨列数都为1
		int v = 0;//初始一个行数
		for (Iterator iterator = factorList.iterator(); iterator.hasNext();) {
			CongruousFactor congruousFactor = (CongruousFactor) iterator.next();
			coordinate[v][maxTypeLevel+1] = congruousFactor.getFactorName();
			if(!StringUtil.empty(congruousFactor.getRemark())) {
				coordinate[v][maxTypeLevel+2] = congruousFactor.getRemark();
			}else {
				coordinate[v][maxTypeLevel+2] = " ";
			}
			coorLie[v][maxTypeLevel+1] = 1;
			coorRow[v][maxTypeLevel+1] = 1;
			coorLie[v][maxTypeLevel+2] = 1;
			coorRow[v][maxTypeLevel+2] = 1;
			v++;
		}
		//定义一个StringBuffer对象在后台组装页面html再传到前台
		StringBuffer sb = new StringBuffer();
		//只有当指标有值时才显示指标列表
		if(factorTypeList.size()>0||factorList.size()>0) {
			//循环行,一行一行组装html
			//最外层
			sb.append("<table border ='1' width='100%'>");
			sb.append("<tr height='25px;'><td colspan='"+(maxTypeLevel+1)+"'><center><b>指标分类</b></center></td>");
			sb.append("<td><center><b>指标名称</b></center></td>");
			sb.append("<td><center><b>指标描述</b></center></td></tr>");
			for(int i=0;i<factorList.size();i++) {//循环行
				//每一行外有<tr>
				sb.append("<tr height='25px;'>");
				for(int j=0;j<maxTypeLevel+3;j++) {
					//如果二维数组coordinate中某个位置有值则表示在页面上有显示,没则表示在页面上此处被前面或上面的给跨行跨列跨掉了就不显示
					if(!StringUtil.empty(coordinate[i][j])){
						//每一列外有<td>
						if(j<maxTypeLevel+1) {
							sb.append("<td colspan='"+coorLie[i][j]+"'rowspan='"+coorRow[i][j]+"' width='"+(coorLie[i][j]*50/(maxTypeLevel+1))+"%'>");
							sb.append("  "+coordinate[i][j]);
							sb.append("</td>");
						}else{
							sb.append("<td colspan='"+coorLie[i][j]+"'rowspan='"+coorRow[i][j]+"' width='25%'>");
							sb.append("  "+coordinate[i][j]);
							sb.append("</td>");
						}
					}
				}
				sb.append("</tr>");
			}
			sb.append("</table>");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("StringBuffer",sb);
		return new ModelAndView("toDetail",model);
	}
	
	/**
	 * FuncName : getTypeSort
	 * Description :对查出的指标分类重新进行排序
	 * Create Date: 2011-11-30  19:01 by yangyc
	 * Modified Date: 2011-11-30  19:01 by yangyc
	 * @param   
	 * @return  
	 * @Exception
	 */
	private List<CongruousFactorType> getTypeSort(List<CongruousFactorType> list,List<CongruousFactorType> typeSortList) throws Exception {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			CongruousFactorType congruousFactorType = (CongruousFactorType) iterator.next();
			if(congruousFactorType.getFactorTypeList().size()!=0) {
				typeSortList.add(congruousFactorType);
				this.getTypeSort(congruousFactorType.getFactorTypeList(), typeSortList);
			}else{
				typeSortList.add(congruousFactorType);
			}
		}
		 return typeSortList;
	}
	/**
	 * FuncName : getFactorSort
	 * Description :对查出的指标重新进行排序
	 * Create Date: 2011-11-30  19:01 by yangyc
	 * Modified Date: 2011-11-30  19:01 by yangyc
	 * @param   
	 * @return  
	 * @Exception
	 */
	private List<CongruousFactor> getFactorSort(List<CongruousFactorType> list,List<CongruousFactor> factorSortList) throws Exception {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			CongruousFactorType congruousFactorType = (CongruousFactorType) iterator.next();
			if(congruousFactorType.getFactorTypeList().size()==0) {
				factorSortList.addAll(congruousFactorType.getFactorList());
			}
		}
		 return factorSortList;
	}
	
	/**
	 * FuncName : toFactorTabShow
	 * Description :跳到指标展示TAB页面
	 * Create Date: 2011-11-29  15:01 by yangyc 
	 * Modified Date: 2011-11-29  15:01 by yangyc
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toFactorTabShow")
	public ModelAndView toFactorTabShow(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);
		
		List<Project> projectList = projectService.getSubProjectByProjectId(projectId);//根据项目id查包组
		if(projectList.size()==0) {
			projectList.add(project);
		}
		model.put("subPrjList", projectList);
		return new ModelAndView("toFactorTabShow",model);
	}
	
	
	
}
