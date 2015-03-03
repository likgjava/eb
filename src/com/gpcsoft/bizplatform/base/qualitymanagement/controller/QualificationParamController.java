package com.gpcsoft.bizplatform.base.qualitymanagement.controller;

import java.util.HashMap;
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

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationParam;
import com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationParamService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="qualificationParamFormView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_param_form.jsp"
  * @gpcsoft.view value="qualificationParamTreeFormView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_param_tree_form.jsp"
  * @gpcsoft.view value="qualificationParamListView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_param_list.jsp"
  * @gpcsoft.view value="qualificationParamDetailView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_param_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={QualificationParam.class})
@RequestMapping("/QualificationParamController.do")//页面请求路径,可修改
public class QualificationParamController extends AnnotationMultiController<QualificationParam> {

	@Autowired(required=true) @Qualifier("qualificationParamServiceImpl")
	private QualificationParamService qualificationParamService;
	
	
	
	/** 
	 * Description : 取得资质参数  根据资质定义
	 * Create Date: 2010-7-30下午02:44:00 by yucy  Modified Date: 2010-7-30下午02:44:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getParamByDefine")   
	public ModelAndView getParamByDefine(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> param = new HashMap<String, Object>();
		String qualificationDefineId = request.getParameter("qualificationDefineId");
		param.put("qualificationDefineId", qualificationDefineId);
		List<QualificationParam>  QualificationParamList = qualificationParamService.getParamByDefine(param);
		model.put("paramList", QualificationParamList);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
	/** 
	 * Description : 保存资质定义
	 * Create Date: 2010-7-30下午02:44:00 by yucy  Modified Date: 2010-7-30下午02:44:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveQualificationParam")
	public ModelAndView saveQualificationParam(HttpServletRequest request, SessionStatus status) throws Exception {
		Map <String, Object> model = new HashMap<String, Object> ();
		QualificationParam qualificationParam = JsonUtils.json2Bean(JsonUtils.getJSONString(request.getParameter("qualificationJson")),QualificationParam.class);
		qualificationParamService.saveQualificationParam(qualificationParam);
		model.put("objId", qualificationParam.getObjId());
		model.put("parentId", qualificationParam.getParent().getObjId());
		model.put("path", qualificationParam.getPath());
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(null != request.getParameter("objId") && !"".equals(request.getParameter("objId"))) {
			QualificationParam qualificationParam  = qualificationParamService.get(request.getParameter("objId"));
			model.put("qualificationParam", qualificationParam);
		}else {
			model.put("qualificationParam", new QualificationParam());
		}
		
		return new ModelAndView("qualificationParamFormView", model);
	}
	
	/** 
	 * Description : 删除资质定义，如果该资质分类被机构资质信息所引用，则不充许删除 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeQualificationParam")
	public ModelAndView removeQualificationDefine(HttpServletRequest request) {
		String objId = request.getParameter("objId");
		
		qualificationParamService.removeQualificationParam(objId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
