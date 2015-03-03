package com.gpcsoft.epp.requirement.controller;

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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.epp.requirement.service.PreqEntryService;

/**
  * @gpcsoft.view value="preqEntryFormView"
  *  url="view/es/planform/requirement/preqEntryForm.jsp"
  * @gpcsoft.view value="preqEntryTreeFormView"
  *  url="view/es/planform/requirement/preqEntryTreeForm.jsp"
  * @gpcsoft.view value="preqEntryListView"
  *  url="view/es/planform/requirement/preqEntryList.jsp"
  * @gpcsoft.view value="preqEntryDetailView"
  *  url="view/es/planform/requirement/preqEntryDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={PreqEntry.class})
@RequestMapping("/PreqEntryController.do")//页面请求路径,可修改
public class PreqEntryController extends AnnotationMultiController<PreqEntry> {

	@Autowired(required=true) @Qualifier("preqEntryServiceImpl")
	private PreqEntryService preqEntryService;
	
	/** 
	 * Description :  根据申报书条目ID获取对应的需求条目信息
	 * Create Date: 2010-7-10下午03:46:37 by Administrator  Modified Date: 2010-7-10下午03:46:37 by Administrator
	 * @param request
	 * @param taskPlanSubId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=getPreqEntryByTaskPlanSubId")
	public ModelAndView getPreqEntryByTaskPlanSubId(HttpServletRequest request,String taskPlanSubId)throws Exception {
		logger.debug("\nPreqEntryController||getPreqEntryByTaskPlanSubId\n");
		QueryObject queryObject = new QueryObjectBase<PreqEntry>();
		queryObject.setEntityClass(PreqEntry.class);
		queryObject.getQueryParam().and(new QueryParam("taskPlanSub.objId",QueryParam.OPERATOR_EQ,taskPlanSubId));
		Map model=new HashMap();
		List<PreqEntry> list = preqEntryService.getPreqEntryByQueryObject(queryObject);
		if(list != null && !list.isEmpty()){
			PreqEntry preqEntry = (PreqEntry)list.get(0);
			model.put("preqEntry", preqEntry);
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * @Description: 保存采购需求条目
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-7-22 下午11:26:48 By wanghz
	 */
	@RequestMapping(params="method=savePreqEntry")
	public ModelAndView savePreqEntry(HttpServletRequest request,SessionStatus status,PreqEntry preqEntry)throws Exception {
		preqEntryService.save(preqEntry);
		status.setComplete();
		Map model=new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
