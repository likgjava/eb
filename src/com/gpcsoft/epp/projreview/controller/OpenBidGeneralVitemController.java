package com.gpcsoft.epp.projreview.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;
import com.gpcsoft.epp.projreview.domain.OpenBidGeneralVitem;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;
import com.gpcsoft.epp.projreview.service.GenviewDefineService;
import com.gpcsoft.epp.projreview.service.OpenBidGeneralVitemService;
import com.gpcsoft.epp.projreview.service.OpenbidGeneralviewService;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
  * @gpcsoft.view value="openBidGeneralVitemFormView"
  *  url="view/es/planform/projreview/openBidGeneralVitemForm.jsp"
  * @gpcsoft.view value="openBidGeneralVitemTreeFormView"
  *  url="view/es/planform/projreview/openBidGeneralVitemTreeForm.jsp"
  * @gpcsoft.view value="openBidGeneralVitemListView"
  *  url="view/es/planform/projreview/openBidGeneralVitemList.jsp"
  * @gpcsoft.view value="openBidGeneralVitemDetailView"
  *  url="view/es/planform/projreview/openBidGeneralVitemDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OpenBidGeneralVitem.class})
@RequestMapping("/OpenBidGeneralVitemController.do")//页面请求路径,可修改
public class OpenBidGeneralVitemController extends AnnotationMultiController<OpenBidGeneralVitem> {

	@Autowired(required=true) @Qualifier("openBidGeneralVitemServiceImpl")
	private OpenBidGeneralVitemService openBidGeneralVitemService;

	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("genviewDefineServiceImpl")
	private GenviewDefineService genviewDefineService;
	
	@Autowired(required=true) @Qualifier("openbidGeneralviewServiceImpl")
	private OpenbidGeneralviewService openbidGeneralviewService;
	/**
	 * FuncName:saveAllOpenBidGeneralVitem
	 * Description :保存开标一览表明细  
	 * @param   request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-10-11下午01:05:46  
	 * @Modifier liuke
	 * @Modified Date: 2010-10-11下午01:05:46  
	 */
	@RequestMapping(params = "method=saveAllOpenBidGeneralVitem")
	public ModelAndView saveAllOpenBidGeneralVitem(HttpServletRequest request)throws Exception {
		logger.debug("\nes OpenBidGeneralVitemController||saveAllOpenBidGeneralVitem\n");
		String projectId = request.getParameter("projectId");
	    String allValues = request.getParameter("allValues");
	    openBidGeneralVitemService.saveAllOpenBidGeneralVitem(projectId, allValues);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	
	/**
	 * FuncName: saveAllOpenBidGeneralVitemSec
	 * Description :  保存修改开标一览表明细代码
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-9-26  下午01:11:53
	 * @Modifier: liuke
	 * @Modified Date:2011-9-26  下午01:11:53
	 */
	@RequestMapping(params = "method=saveAllOpenBidGeneralVitemSec")
	public ModelAndView saveAllOpenBidGeneralVitemSec(HttpServletRequest request)throws Exception {
		logger.debug("\nes OpenBidGeneralVitemController||saveAllOpenBidGeneralVitem\n");
		String projectId = request.getParameter("projectId");
	    String allValues = request.getParameter("allValues");
	    openBidGeneralVitemService.saveAllOpenBidGeneralVitemSec(projectId, allValues);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	/**
	 * FuncName: toOpenBidGeneralVitemPrintPage
	 * Description :  跳转到打印开标一览表页面
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-9-27  下午01:56:03
	 * @Modifier: liuke
	 * @Modified Date:2011-9-27  下午01:56:03
	 */
	@RequestMapping(params = "method=toOpenBidGeneralVitemPrintPage")
	public ModelAndView toOpenBidGeneralVitemPrintPage(HttpServletRequest request)throws Exception {
		logger.debug("\nes=OpenBidGeneralVitemController||toOpenBidGeneralVitemPrintPage\n");
		String projectId = request.getParameter("projectId");  //当前项目ID
        String subProjectId = request.getParameter("subProjectId"); //当前标段ID
		
        Project project= projectService.getProjectByObjId(projectId);    //项目对象
       // Project subProject = projectService.getProjectByObjId(subProjectId);   //标段对象
        List<Project> projectList = new ArrayList<Project>();
        projectList = projectService.getSubProjectByProjectId(projectId);
        if(projectList.isEmpty()){
        	projectList.add(project);
        }
        
        List<OpenBidGeneralVitem> openBidGeneralVitemList = openBidGeneralVitemService.getOpenBidGeneralVitemListByProjectId(projectId);
        Map<String,Object> model = new HashMap<String, Object>();
        if(openBidGeneralVitemList.size()>0){ //如果已经保存了开标一览表对象  
            ProjectRule rule = projectService.getProjectRuleByProjectId(projectId);    //项目规则对象
            
            List<GenviewDefine> genviewDefineList = genviewDefineService.getGenviewDefineListByProjectId(projectId);
            
            List<OpenbidGeneralview> openbidGeneralviewList = openbidGeneralviewService.getOpenbidGeneralviewList(projectId);
            
            Map<String ,Object> InfoMap = new HashMap<String, Object>();
            List<Object> infoList = new ArrayList<Object>(); 
            for (Iterator iterator = openbidGeneralviewList.iterator(); iterator.hasNext();) { 
            	  OpenbidGeneralview openbidGeneralview = (OpenbidGeneralview) iterator.next(); //开标一览表对象
            	  List<String> info = new ArrayList<String>();  //最小信息单元
            	  info.add(openbidGeneralview.getSubProj().getObjId());
            	  info.add(openbidGeneralview.getSupplierName());
            	  for (Iterator iterator2 = genviewDefineList.iterator(); iterator2.hasNext();) {
            		  GenviewDefine genviewDefine = (GenviewDefine) iterator2.next();
            		  for (Iterator iterator3 = openBidGeneralVitemList.iterator(); iterator3.hasNext();) {
    					OpenBidGeneralVitem openBidGeneralVitem = (OpenBidGeneralVitem) iterator3.next();
    					   if(genviewDefine!=null&&genviewDefine.getFactorId()!=null&&genviewDefine.getFactorId().equals(openBidGeneralVitem.getFactorId())&&openbidGeneralview!=null&&openbidGeneralview.getObjId()!=null&&openBidGeneralVitem.getOpenbidGeneralview()!=null&&openbidGeneralview.getObjId().equals(openBidGeneralVitem.getOpenbidGeneralview().getObjId())){
    						   info.add(openBidGeneralVitem.getRespValue());
    					   }  
    				}
    				
    			}
            	  infoList.add(info);
    			}
    		Map<String,Object> freeMarkermodel = new HashMap<String,Object>();
    		freeMarkermodel.put("project", project);
    		freeMarkermodel.put("projectList", projectList);
    		freeMarkermodel.put("rule", rule);
    		freeMarkermodel.put("genviewDefineList", genviewDefineList);
    		freeMarkermodel.put("infoList", infoList);
    		String content = freeMarkerService.getFreeMarkerContent("openBidGeneralVitem.ftl", freeMarkermodel);//设置开标一览表打印模板 
    		this.shallContentPlacedSession(request,content);
        }else{
        	model.put("result", "还未保存，不能打印！");
        }
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * FuncName:shallContentPlacedSession
	 * Description: 将预览内容放置session中
	 * @param reqeust,content:预置的内容
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-1-19 下午04:41:56 
	 */
	@RequestMapping(params = "method=shallContentPlacedSession")
	public ModelAndView shallContentPlacedSession(HttpServletRequest reqeust, String content)throws Exception {
		logger.debug("\nes=TaskPlanController||shallContentPlacedSession\n");
		if(null == content || "".equals(content) || "NULL".equals(content.toUpperCase())){
			content = reqeust.getParameter("content");
		}
		reqeust.getSession().setAttribute("content", content);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	/** 
	 * FuncName : finishOpenBidGeneralVitem
	 * Description :  
	 * Create Date: 2011-11-18下午04:19:12 by yangx  
	 * Modified Date: 2011-11-18下午04:19:12 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=finishOpenBidGeneralVitem")
	public ModelAndView finishOpenBidGeneralVitem(HttpServletRequest reqeust)throws Exception{
		String projectId= reqeust.getParameter("projectId");
		openBidGeneralVitemService.finishOpenBidGeneralVitem(projectId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
