package com.gpcsoft.bizplatform.base.industry.web.controller;

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
import com.gpcsoft.core.Constants;
import com.gpcsoft.bizplatform.base.industry.service.IndustryService;
import com.gpcsoft.bizplatform.base.industry.domain.Industry;


/**
 * @gpcsoft.view value="industryListView"
 * url="view/bizplatform/base/industry/industry_list.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Industry.class})
@RequestMapping("/IndustryController.do")//页面请求路径,可修改
public class IndustryController extends AnnotationMultiController<Industry> {

	@Autowired(required=true) @Qualifier("industryServiceImpl")
	private IndustryService industryService;

	
	/** 
	 * Description :  保存行业参数，并生成拼音缩写 
	 * Create Date: 2010-11-16下午01:33:16 by liangxj  Modified Date: 2010-11-16下午01:33:16 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveIndustry")
    public ModelAndView saveIndustry(Industry industry, SessionStatus status) throws Exception {
    	Map <String, Object> model = new HashMap<String, Object> ();
    	industryService.saveIndustry(industry);
		
		model.put("industry", industry);
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	
	/** 
	 * Description :  删除行业，如果该行业被引用，则不充许删除
	 * Create Date: 2010-11-16下午01:33:30 by liangxj  Modified Date: 2010-11-16下午01:33:30 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeIndustry")
    public ModelAndView removeIndustry(HttpServletRequest request) throws Exception {
		String objId = request.getParameter("objId");
		industryService.removeIndustry(objId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
