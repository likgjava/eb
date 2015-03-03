package com.gpcsoft.agreement.bargin.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.project.domain.RecommendProject;
import com.gpcsoft.agreement.bargin.project.service.RecommendProjectService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="recommendProjectManageListView"
  *  url="view/agreement/bargin/project/recommend/recommend_project_manage_list.jsp"
  *  
  * @gpcsoft.view value="recommendProjectListView"
  *  url="view/srplatform/portal/include/recommend_project_list.jsp"
  */
@Controller
@Scope("request")
@SessionAttributes(types={RecommendProject.class})
@RequestMapping("/RecommendProjectController.do")
public class RecommendProjectController extends AnnotationMultiController<RecommendProject> {
	
	@Autowired(required=true) @Qualifier("recommendProjectServiceImpl")
	private RecommendProjectService recommendProjectService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;

	/** 
	 * Description :  跳转到项目推荐管理列表页面
	 * Create Date: 2010-10-9 下午 16:30:16 by likg  Modified Date: 2010-10-9 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toRecommendProjectView")
	public ModelAndView toRecommendProjectView() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("recommendProjectManageListView", model);
	}
	
	/** 
	 * Description :  保存推荐项目
	 * Create Date: 2010-10-11 下午 16:30:16 by likg  Modified Date: 2010-10-11 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveRecommendProject")
	public ModelAndView saveRecommendProject(RecommendProject recommendProject, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String projectIds = request.getParameter("projectIds"); //项目IDs
		
		//处理图片上传
		CommonsMultipartFile file = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile");
		if(file!=null && file.getSize()!=0) {
			Object o = AttachmentUtil.uploadFile(request, "pictureFile");
			if(o instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o;
				attachmentService.saveAttachment(attachment);
				recommendProject.setPicture(attachment.getObjId());
			}
		}
		
		//保存推荐项目
		recommendProjectService.saveRecommendProject(recommendProject, projectIds);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获取所有的未推荐项目
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=listNoRecommendProject")
	public ModelAndView listNoRecommendProject(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("projCode", request.getParameter("projCode")); //项目编号
		param.put("projName", request.getParameter("projName")); //项目名称
		param.put("ebuyMethod", request.getParameter("ebuyMethod")); //采购方式
		param.put("amountRange", request.getParameter("amountRange")); //采购金额范围
		param.put("order", request.getParameter("order")); //排序字段
		param.put("order_flag", request.getParameter("order_flag")); //排序方式
		Page<Project> page = prePage(request);
	    Page<Project> pageData = recommendProjectService.listNoRecommendProject(param, page);
	    
		String alias=request.getParameter("alias");
		String queryColumns = makeQueryColumns(request);
		pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), alias==null?null:alias.split(","), Project.class, getEnumColumns()));
		endPage(model, pageData, request);
	    
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  修改推荐项目的排序序号
	 * Create Date: 2010-10-15上午10:40:08 by likg  Modified Date: 2010-10-15上午10:40:08 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=updateSort")
	public ModelAndView updateSort(HttpServletRequest request) throws Exception {
		String sourceObjId = request.getParameter("sourceObjId");
		String isToUp = request.getParameter("isToUp");
		
		recommendProjectService.updateSort(sourceObjId, ("true".equals(isToUp) ? true : false));
		
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  获得推荐项目列表
	 * Create Date: 2010-10-18下午05:07:08 by likg  Modified Date: 2010-10-18下午05:07:08 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=getRecommendProject")
	public ModelAndView getRecommendProject(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		Page<RecommendProject> page = prePage(request);
		Page<RecommendProject> pageData = recommendProjectService.getRecommendProject(page, null);
		model.put("recommendProjects", pageData.getData());
		
		return new ModelAndView("recommendProjectListView", model);
	}
}
