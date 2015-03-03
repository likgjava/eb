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

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationDefine;
import com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationDefineService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
  * @gpcsoft.view value="qualificationDefineFormView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_define_form.jsp"
  * @gpcsoft.view value="qualificationDefineTreeFormView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_define_tree_form.jsp"
  * @gpcsoft.view value="qualificationDefineListView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_define_list.jsp"
  * @gpcsoft.view value="qualificationDefineDetailView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_define_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={QualificationDefine.class})
@RequestMapping("/QualificationDefineController.do")//页面请求路径,可修改
public class QualificationDefineController extends AnnotationMultiController<QualificationDefine> {

	@Autowired(required=true) @Qualifier("qualificationDefineServiceImpl")
	private QualificationDefineService qualificationDefineService;
	
	
	
	/** 
	 * Description :  取得资质定义 根据资质分类
	 * Create Date: 2010-7-30下午02:44:00 by yucy  Modified Date: 2010-7-30下午02:44:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getDefineByClass")   
	public ModelAndView getDefineByClass(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> param = new HashMap<String, Object>();
		String qualificationClassId = request.getParameter("qualificationClassId");
		String orgId = AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId();
		param.put("qualificationClassId", qualificationClassId);
		param.put("orgId", orgId);
		List<QualificationDefine>  QualificationDefineList = qualificationDefineService.getDefineByClass(param);
		model.put("defineList", QualificationDefineList);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	/** 
	 * Description : 保存资质定义 
	 * Create Date: 2010-7-29下午03:22:17 by guoyr  Modified Date: 2010-7-29下午03:22:17 by guoyr
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveQualificationDefine")
	public ModelAndView saveQualificationDefine(HttpServletRequest request,  SessionStatus status) throws Exception {
		Map <String, Object> model = new HashMap<String, Object> ();
		QualificationDefine qualificationDefine = JsonUtils.json2Bean(JsonUtils.getJSONString(request.getParameter("qualificationJson")),QualificationDefine.class);
		qualificationDefineService.saveQualificationDefine(qualificationDefine);
		
		model.put("objId", qualificationDefine.getObjId());
		model.put("parentId", qualificationDefine.getParent().getObjId());
		model.put("path", qualificationDefine.getPath());
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(null != request.getParameter("objId") && !"".equals(request.getParameter("objId"))) {
			QualificationDefine qualificationDefine  = qualificationDefineService.get(request.getParameter("objId"));
			model.put("qualificationDefine", qualificationDefine);
		}else {
			model.put("qualificationDefine", new QualificationDefine());
		}
		
		return new ModelAndView("qualificationDefineFormView", model);
	}
	
	/** 
	 * Description : 删除资质定义，如果该资质分类被机构资质信息所引用，则不充许删除 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeQualificationDefine")
	public ModelAndView removeQualificationDefine(HttpServletRequest request) {
		String objId = request.getParameter("objId");
		
		qualificationDefineService.removeQualificationDefine(objId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
