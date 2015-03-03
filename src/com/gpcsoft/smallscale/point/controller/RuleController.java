package com.gpcsoft.smallscale.point.controller;

import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.smallscale.point.service.RuleService;
import com.gpcsoft.smallscale.point.domain.Rule;


/**
  * @gpcsoft.view value="ruleFormView"
  *  url="view/smallscale/point/rule_form.jsp"
  * @gpcsoft.view value="ruleTreeFormView"
  *  url="view/smallscale/point/ruleTreeForm.jsp"
  * @gpcsoft.view value="ruleListView"
  *  url="view/smallscale/point/rule_list.jsp"
  * @gpcsoft.view value="ruleDetailView"
  *  url="view/smallscale/point/ruleDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Rule.class})
@RequestMapping("/RuleController.do")//页面请求路径,可修改
public class RuleController extends AnnotationMultiController<Rule> {

	@Autowired(required=true) @Qualifier("ruleServiceImpl")
	private RuleService ruleService;
	

	
	@RequestMapping(params={"method=saveWithStatus"})
	  public ModelAndView saveWithStatus(String  objId,String currentStatus ,  HttpServletRequest request, SessionStatus status)
	    throws Exception{
	    
		Rule rule = ruleService.get(objId);
		rule.setCurrentStatus(currentStatus);
		return super.save(rule, request, status);
	    
	  }
	
	
	@Override
	 protected Map<String, String> getEnumColumns() {
	       Map<String, String> enumMap=new HashMap<String, String>();
	       enumMap.put("pointWay", "com.gpcsoft.smallscale.point.domain.Rule.pointWay");//第一个参数是属性，第二参数是枚举类中定义名
	       enumMap.put("pointSign", "com.gpcsoft.smallscale.point.domain.Rule.pointSign");
	       enumMap.put("sourceCode", "com.gpcsoft.smallscale.point.domain.Rule.sourceCode");
	       return enumMap;
	 }

}
