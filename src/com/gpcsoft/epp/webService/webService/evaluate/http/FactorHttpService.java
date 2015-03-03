package com.gpcsoft.epp.webService.webService.evaluate.http;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.service.ElectronicReviewService;

/**
 * 为编评系统提供远程调用服务。
 * <br>
 * <strong>尚未进行安全认证</strong>.
 * @author <a href="mailto:shenjz@gpcsoft.com">zhouzhanghe</a>
 * @version 1.0
 * @since 2011-3-17 11:20
 */
@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={CongruousFactor.class})
@RequestMapping("/FactorHttpService.do")//页面请求路径,可修改
public class FactorHttpService extends AnnotationMultiController{
	
	
	@Autowired(required=true) @Qualifier("electronicReviewServiceImpl")
	private ElectronicReviewService electronicReviewService;
	
	/**
	 * FuncName: getCongruousFactorList
	 * Description :  获取指标集合
	 * @param 
	 * @param projectCode
	 * @param packCode
	 * @return
	 * @throws Exception String
	 * @author: shenjz
	 * @Create Date:2011-9-8  上午09:47:21
	 * @Modifier: shenjz
	 * @Modified Date:2011-9-8  上午09:47:21
	 */
	@RequestMapping(params="method=getCongruousFactorList")
	public void  getCongruousFactorList(String projectCode,String packCode,HttpServletResponse response)throws Exception {
		if(projectCode==null){
			response.getWriter().write("");
		}
		response.getWriter().write(electronicReviewService.getCongruousFactor(projectCode,packCode));
	}
	
}
