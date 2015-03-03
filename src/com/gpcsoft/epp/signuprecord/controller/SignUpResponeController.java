package com.gpcsoft.epp.signuprecord.controller;

import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import com.gpcsoft.core.Constants;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;
import com.gpcsoft.epp.signuprecord.service.SignUpResponeService;

/**
  * @gpcsoft.view value="signUpResponeFormView"
  *  url="view/es/planform/signuprecord/signUpResponeForm.jsp"
  * @gpcsoft.view value="signUpResponeTreeFormView"
  *  url="view/es/planform/signuprecord/signUpResponeTreeForm.jsp"
  * @gpcsoft.view value="signUpResponeListView"
  *  url="view/es/planform/signuprecord/signUpResponeList.jsp"
  * @gpcsoft.view value="signUpResponeDetailView"
  *  url="view/es/planform/signuprecord/signUpResponeDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={SignUpRespone.class})
@RequestMapping("/SignUpResponeController.do")//页面请求路径,可修改
public class SignUpResponeController extends AnnotationMultiController<SignUpRespone> {

	@Autowired(required=true) @Qualifier("signUpResponeServiceImpl")
	private SignUpResponeService signUpResponeService;

}
