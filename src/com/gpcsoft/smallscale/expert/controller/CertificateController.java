package com.gpcsoft.smallscale.expert.controller;

import java.util.HashMap;
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
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.expert.domain.Certificate;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.smallscale.expert.enumeration.ExpertEnum;
import com.gpcsoft.smallscale.expert.service.CertificateService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="certificateListView"
  *  url="view/smallscale/expert/certificate_list.jsp"
  * @gpcsoft.view value="certificateFormView"
  *  url="view/smallscale/expert/certificate_form.jsp"
  * 
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Certificate.class})
@RequestMapping("/CertificateController.do")//页面请求路径,可修改
public class CertificateController extends AnnotationMultiController<Certificate> {

	@Autowired(required=true) @Qualifier("certificateServiceImpl")
	private CertificateService certificateService;

	/** 
	 * Description : 跳转到职称管理列表页面 
	 * Create Date: 2010-11-26上午10:15:24 by guoyr  Modified Date: 2010-11-26上午10:15:24 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCertificateList")
	public ModelAndView toCertificateList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		User user = AuthenticationHelper.getCurrentUser(true);
		
		// 用户的专家信息
		if(null != user.getExtInfo()){
			model.put("expertInfoId", user.getExtInfo().getObjId());
		}
		return new ModelAndView("certificateListView", model);	
    }
	
	/** 
	 * Description : 保存职称信息
	 * Create Date: 2010-11-26下午03:28:52 by guoyr  Modified Date: 2010-11-26下午03:28:52 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveCertificate")
	public ModelAndView saveCertificate(HttpServletRequest request, Certificate certificate) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		// 如果是提交，设为待审核状态
		if(request.getParameter("saveType").equals("submit")){
			certificate.setAuditStatus(ExpertEnum.AWAIT_EXAM);
		}else{
			// 如果是保存，设为草稿状态
			certificate.setAuditStatus(ExpertEnum.DRAFT_EXAM);
		}
		certificateService.saveCertificate(certificate);
		return new ModelAndView(Constants.JSON_VIEW, model);	
    }
	
	/** 
	 * Description : 跳转到修改或新增职称管理表单页面 
	 * Create Date: 2010-11-26上午10:15:24 by guoyr  Modified Date: 2010-11-26上午10:15:24 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		String objId = request.getParameter("objId");
		Certificate certificate = null;
		if(StringUtils.hasLength(objId)) {
			certificate  = certificateService.get(objId);
		}else {
			certificate = new Certificate();
			User user = AuthenticationHelper.getCurrentUser(true);
			
			// 专家信息
			certificate.setExpertInfo((ExpertInfo) user.getExtInfo());
		}
		model.put("certificate", certificate);
		
		return new ModelAndView("certificateFormView", model);
	}
	
	/** 
	 * Description : 审核专家职称 
	 * Create Date: 2010-11-29上午10:22:40 by guoyr  Modified Date: 2010-11-29上午10:22:40 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditCertificate")
	public ModelAndView auditCertificate(HttpServletRequest request,String objIds,String auditStatus) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		certificateService.updateCertificateAuditStatus(objIds, auditStatus);
		return new ModelAndView(Constants.JSON_VIEW, model);	
    }
}
