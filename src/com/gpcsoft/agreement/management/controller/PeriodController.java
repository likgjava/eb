package com.gpcsoft.agreement.management.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.management.domain.Period;
import com.gpcsoft.agreement.management.service.PeriodService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="periodFormView"
  *  url="view/agreement/mamagement/agreement_period_form.jsp"
  * @gpcsoft.view value="periodTreeFormView"
  *  url="view/agreement/management/periodTreeForm.jsp"
  * @gpcsoft.view value="periodListView"
  *  url="view/agreement/mamagement/agreement_period_list.jsp"
  * @gpcsoft.view value="periodDetailView"
  *  url="view/agreement/management/periodDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Period.class})
@RequestMapping("/PeriodController.do")//页面请求路径,可修改
public class PeriodController extends AnnotationMultiController<Period> {

	@Autowired(required=true) @Qualifier("periodServiceImpl")
	private PeriodService periodService;
	
	/** 
	 * Description :  获取按年度搜索框内容
	 * Create Date: 2010-4-19上午12:10:00 by yucy  Modified Date: 2010-4-19上午12:10:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getAnnualSearchContent")
	public ModelAndView getAnnualSearchContent(HttpServletRequest request){
		Map<String,Object> model=new HashMap<String,Object>();
		List list =  periodService.getAnnualSearchContent();
		model.put("list", list);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  删除期间(安全删除)
	 * Create Date: 2010-4-17下午02:30:03 by yucy  Modified Date: 2010-4-17下午02:30:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=delPeriod")
	public ModelAndView delPeriod(HttpServletRequest request) throws Exception {
		Map<String, String> model = new HashMap<String, String>();   
		String periods = request.getParameter("periods");
		int result =  periodService.delPeriod(periods);
		if(result>=0){
			model.put(Constants.JSON_RESULT, "删除成功!");
		}else{
			model.put(Constants.JSON_RESULT, "删除失败,所选中期间使用中!");
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description : 跳转form
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toNewPeriod")   
	public ModelAndView toNewPeriod(HttpServletRequest request,String objId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Period period = null;
		if(null!=objId&&!"".equals(objId)){
			period = periodService.get(objId);
		}else {
			period = new Period();
		}
		model.put("period", period);
		return new ModelAndView("periodFormView", model);
    }
	
	/** 
	 * Description :  跳转list
	 * Create Date: 2010-5-20下午03:31:27 by yucy  Modified Date: 2010-5-20下午03:31:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPeriodList")   
	public ModelAndView toPeriodList(HttpServletRequest request,String objId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		model.put("annual",periodService.getAnnualSearchContent());
		return new ModelAndView("periodListView", model);
    }
}
