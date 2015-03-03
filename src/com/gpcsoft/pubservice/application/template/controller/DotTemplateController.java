package com.gpcsoft.pubservice.application.template.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.template.domain.DotTemplate;
import com.gpcsoft.pubservice.application.template.service.DotTemplateService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="dotTemplateListView"
  *  url="view/pubservice/application/template/template_list.jsp"
  *  
  * @gpcsoft.view value="dotTemplateFormView"
  *  url="view/pubservice/application/template/template_form.jsp"
  *  
  * @gpcsoft.view value="dotTemplateDetailView"
  *  url="view/pubservice/application/template/template_detail.jsp"
  */
@Controller
@Scope("request")
@SessionAttributes(types={DotTemplate.class})
@RequestMapping("/DotTemplateController.do")
public class DotTemplateController extends AnnotationMultiController<DotTemplate> {

	@Autowired(required=true) @Qualifier("dotTemplateServiceImpl")
	private DotTemplateService dotTemplateService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	/** 
	 * Description :  跳转到范本列表页面
	 * Create Date: 2011-8-1上午11:23:45 by likg  Modified Date: 2011-8-1上午11:23:45 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toDotTemplateListView")
	public ModelAndView toDotTemplateListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取当前用户信息
		model.put("userId", AuthenticationHelper.getCurrentUser(true).getObjId());
		
		return new ModelAndView("dotTemplateListView", model);
	}
	
	/** 
	 * Description :  跳转到新增或修改页面
	 * Create Date: 2011-8-1上午11:23:45 by likg  Modified Date: 2011-8-1上午11:23:45 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateView")
	public ModelAndView toCreateOrUpdateView(String objId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String userType = request.getParameter("userType"); //用户类型
		
		//获取机构信息
		if(!"manager".equals(userType)) {
			model.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		}
		
		//获取范本信息
		DotTemplate dotTemplate = null;
		if(StringUtils.hasLength(objId)) {
			dotTemplate = dotTemplateService.get(objId);
			
			//获取范本附件信息
			Attachment attachment = attachmentService.get(dotTemplate.getTemplateFile());
			model.put("attachmentViewName", attachment.getViewName());
		} else {
			dotTemplate = new DotTemplate();
		}
		model.put("dotTemplate", dotTemplate);
		
		return new ModelAndView("dotTemplateFormView", model);
	}
	
	/** 
	 * Description :  保存范本信息
	 * Create Date: 2011-8-1上午11:46:47 by likg  Modified Date: 2011-8-1上午11:46:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveDotTemplate")
	public ModelAndView saveDotTemplate(DotTemplate dotTemplate, HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//处理范本附件
		if(!StringUtils.hasLength(dotTemplate.getTemplateFile())) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("uploadFile");
			if(file!=null && file.getSize()!=0) {
				Object o = AttachmentUtil.uploadFile(request, "uploadFile");
				if(o instanceof GpcBaseObject) {
					Attachment attachment = (Attachment) o;
					attachmentService.saveAttachment(attachment);
					dotTemplate.setTemplateFile(attachment.getObjId());
				}
			}
		}
		
		//保存范本信息
		dotTemplateService.saveDotTemplate(dotTemplate);
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到范本详情页面
	 * Create Date: 2011-8-1下午02:09:28 by likg  Modified Date: 2011-8-1下午02:09:28 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toDotTemplateDetailView")
	public ModelAndView toDotTemplateDetailView(String objId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取范本信息
		DotTemplate dotTemplate = dotTemplateService.get(objId);
		model.put("dotTemplate", dotTemplate);
		
		//获取范本附件信息
		Attachment attachment = attachmentService.get(dotTemplate.getTemplateFile());
		model.put("attachmentViewName", attachment.getViewName());
		
		//判断当前用户的角色(管理员或范本创建者可以查看非共享的范本附件)
		if(!dotTemplate.getIsShare()) {
			User user = AuthenticationHelper.getCurrentUser(true);
			if(roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_MANAGER) || user.getObjId().equals(dotTemplate.getCreateUser().getObjId())){
				model.put("canView", true);
			}
		}
		
		return new ModelAndView("dotTemplateDetailView", model);
	}
	
    /** 
     * Description :  修改范本的使用状态
     * Create Date: 2011-10-11上午11:42:01 by likg  Modified Date: 2011-10-11上午11:42:01 by likg
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=updateUseStatus")
    public ModelAndView updateUseStatus(String objId, HttpServletRequest request, SessionStatus status) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	
    	//修改范本的使用状态
		DotTemplate dotTemplate = dotTemplateService.get(objId);
		dotTemplate.setUseStatus(request.getParameter("useStatus"));
		dotTemplateService.save(dotTemplate);
		status.setComplete();
    	
    	return new ModelAndView(Constants.JSON_VIEW, model);
    }

}
