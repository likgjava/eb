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

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationClass;
import com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationClassService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="qualificationClassFormView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_class_form.jsp"
  * @gpcsoft.view value="qualificationClassTreeFormView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_class_tree_form.jsp"
  * @gpcsoft.view value="qualificationClassListView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_class_list.jsp"
  * @gpcsoft.view value="qualificationClassDetailView"
  *  url="view/bizplatform/base/qualitymanagement/qualification_class_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={QualificationClass.class})
@RequestMapping("/QualificationClassController.do")//页面请求路径,可修改
public class QualificationClassController extends AnnotationMultiController<QualificationClass> {

	@Autowired(required=true) @Qualifier("qualificationClassServiceImpl")
	private QualificationClassService qualificationClassService;

	/** 
	 * Description : 保存资质分类 
	 * Create Date: 2010-7-29下午03:20:50 by guoyr  Modified Date: 2010-7-29下午03:20:50 by guoyr
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveQualificationClass")
	public ModelAndView saveQualificationClass(HttpServletRequest request, SessionStatus status) throws Exception {
		Map <String, Object> model = new HashMap<String, Object> ();
		QualificationClass qualificationClass = JsonUtils.json2Bean(JsonUtils.getJSONString(request.getParameter("qualificationJson")),QualificationClass.class);
		qualificationClassService.saveQualificationClass(qualificationClass);
		
		model.put("objId", qualificationClass.getObjId());
		model.put("parentId", null != qualificationClass.getParent() ? qualificationClass.getParent().getObjId() : "");
		model.put("path", qualificationClass.getPath());
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(null != request.getParameter("objId") && !"".equals(request.getParameter("objId"))) {
			QualificationClass qualificationClass  = qualificationClassService.get(request.getParameter("objId"));
			model.put("qualificationClass", qualificationClass);
		}else {
			model.put("qualificationClass", new QualificationClass());
		}
		
		return new ModelAndView("qualificationClassFormView", model);
	}
	
	/** 
	 * Description : 删除资质分类，如果该资质分类被机构资质信息所引用，则不充许删除 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeQualificationClass")
	public ModelAndView removeQualificationClass(HttpServletRequest request) {
		String objId = request.getParameter("objId");
		
		qualificationClassService.removeQualificationClass(objId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description : 只取显示的分类 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
    @SuppressWarnings("unchecked")
	@Override
	protected void onBeforeGetTree(QueryObject query) {
    	query.getQueryParam().and(new QueryParam("isDisplay",true));
		super.onBeforeGetTree(query);
	}
}
