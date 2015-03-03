package com.gpcsoft.pubservice.application.member.controller;

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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.pubservice.application.member.domain.MemberClass;
import com.gpcsoft.pubservice.application.member.service.MemberClassService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
 * @gpcsoft.view value="memberClassFormView"
 *  url="view/pubservice/application/member/member_class_form.jsp"
 *  
 * @gpcsoft.view value="memberClassDetailView"
 *  url="view/pubservice/application/member/member_class_detail.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={MemberClass.class})
@RequestMapping("/MemberClassController.do")//页面请求路径,可修改
public class MemberClassController extends AnnotationMultiController<MemberClass> {

	@Autowired(required=true) @Qualifier("memberClassServiceImpl")
	private MemberClassService memberClassService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	/** 
	 * Description :   新增/修改会员级别
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveMemberClass")
    public ModelAndView saveMemberClass(MemberClass memberClass, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//处理会员级别图片
		String returnMessage = "success";
		CommonsMultipartFile file = null;
		 try {
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		     file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile");
			 if(file != null && file.getSize() != 0){
				 Object o=AttachmentUtil.uploadFile(request,"pictureFile");
				 if(o instanceof GpcBaseObject){
						Attachment attachment = (Attachment)o;
						attachmentService.saveAttachment((Attachment)o);
						memberClass.setMemberClassPic(attachment.getObjId());
				 }
			 }
		 } catch (Exception de) {
			returnMessage = de.getMessage();
		 }
		 
		 //图片上传成功
		 if("success".equals(returnMessage)) {
			 memberClassService.saveMemberClass(memberClass);
		 }

		 model.put("returnMessage", returnMessage);
		
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :   跳转到新增或修改会员级别页面
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		MemberClass memberClass = null;
		String objId = request.getParameter("objId"); //会员级别id
		
		if(StringUtils.hasLength(objId)) {
			memberClass = memberClassService.get(objId);
		} else {
			memberClass = new MemberClass();
		}
		model.put("memberClass", memberClass);
		
		return new ModelAndView("memberClassFormView", model);
	}
	
	/** 
	 * Description :   查看会员级别详情
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toShowView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		MemberClass memberClass = null;
		String objId = request.getParameter("objId"); //会员级别id
		
		if(StringUtils.hasLength(objId)) {
			memberClass = memberClassService.get(objId);
		} else {
			memberClass = new MemberClass();
		}
		model.put("memberClass", memberClass);
		
		return new ModelAndView("memberClassDetailView", model);
	}

}
