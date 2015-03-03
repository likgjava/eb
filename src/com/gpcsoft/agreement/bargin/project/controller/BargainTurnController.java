package com.gpcsoft.agreement.bargin.project.controller;

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

import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.agreement.bargin.project.service.BargainTurnService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  *  @gpcsoft.view value="toModifyCutFormView"
  *  url="view/agreement/bargin/project/hall/modify_cut_form.jsp"
  */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BargainTurn.class})
@RequestMapping("/BargainTurnController.do")//页面请求路径,可修改
public class BargainTurnController extends AnnotationMultiController<BargainTurn> {
	
	@Autowired(required=true) @Qualifier("bargainTurnServiceImpl")
	private BargainTurnService bargainTurnServiceImpl;
	
	/** 跳转到修改降价幅度页面
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toModifyCutForm")
	public ModelAndView toModifyCutForm(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		String bargainTurnId = request.getParameter("turnId");
		BargainTurn bargainTurn = bargainTurnServiceImpl.get(bargainTurnId);
		model.put("bargainTurn", bargainTurn);
		
		return new ModelAndView("toModifyCutFormView",model);
	}
	
	/** 修改降价幅度
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveModifyCut")
	public ModelAndView saveModifyCut(BargainTurn turn,HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		bargainTurnServiceImpl.save(turn);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
