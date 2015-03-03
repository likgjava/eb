package com.gpcsoft.epp.common.controller;

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

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.service.UserCaService;
import com.gpcsoft.srplatform.auth.domain.User;

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={User.class})
@RequestMapping("/CaController.do")//页面请求路径,可修改
public class UserCaController extends AnnotationMultiController{

	@Autowired(required=true) @Qualifier("userCaServiceImpl")
	private UserCaService userCaService;
	
	/** 
	 * Description :  验证用户的证书信息
	 * Create Date: 2010-8-18上午10:27:14 by yangx  Modified Date: 2010-8-18上午10:27:14 by yangx
	 * @param   user 	用户对象
	 * @param   usrCaSn 证书信息
	 * @return  
	 * @Exception   
	 */
	public ModelAndView checkCA(HttpServletRequest request,User user,String usrCaSn) throws Exception {
		boolean caFlag = userCaService.checkCA(user, usrCaSn);
		Map<String,Object> model = new HashMap<String,Object>();
		if (caFlag) {//判断证书信息与密钥信息是否匹配
			return new ModelAndView("");
		}else {
			return new ModelAndView("");
		}
	}
}
