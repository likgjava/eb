package com.gpcsoft.bizplatform.base.qualitymanagement.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.Qualification;
import com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="qualificationFormView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_form.jsp"
  * @gpcsoft.view value="qualificationTreeFormView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_tree_form.jsp"
  * @gpcsoft.view value="qualificationListView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_list.jsp"
  * @gpcsoft.view value="qualificationDetailView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Qualification.class})
@RequestMapping("/QualificationController.do")//页面请求路径,可修改
public class QualificationController extends AnnotationMultiController<Qualification> {

	@Autowired(required=true) @Qualifier("qualificationServiceImpl")
	private QualificationService qualificationService;
	
	/** 
	 * Description :保存资质  
	 * Create Date: 2010-7-29下午02:44:39 by guoyr  Modified Date: 2010-7-29下午02:44:39 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveQualification")
	public ModelAndView saveQualification(HttpServletRequest request, Qualification qualification, SessionStatus status) {
		Map <String, Object> model = new HashMap<String, Object> ();
		qualificationService.saveQualification(qualification);
		model.put("objId", qualification.getObjId());
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	/** 
	 * Description : 上下移动时修改原行和目标行的排序
	 * Create Date: 2010-8-2下午06:00:04 by guoyr  Modified Date: 2010-8-2下午06:00:04 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateSort")
	public ModelAndView updateSort(HttpServletRequest request) {
		String sourceObjId = request.getParameter("sourceObjId");
		String targetObjId = request.getParameter("targetObjId");
		qualificationService.updateSort(sourceObjId, targetObjId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description : 判断资质名称在父节点下是不是唯一  
	 * Create Date: 2010-11-25上午10:59:50 by guoyr  Modified Date: 2010-11-25上午10:59:50 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=isUnique")
	public ModelAndView isUnique(HttpServletRequest request, String qualityName, String objId, String parentId ) {
		Map <String, Object> model = new HashMap<String, Object> ();
		qualityName = StringUtils.ascii2Native(qualityName);
		model.put("isUnique", qualificationService.isUnique(qualityName, objId, parentId));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	
}
