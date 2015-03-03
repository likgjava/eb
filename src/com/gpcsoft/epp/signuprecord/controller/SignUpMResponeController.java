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
import com.gpcsoft.epp.signuprecord.domain.SignUpMRespone;
import com.gpcsoft.epp.signuprecord.service.SignUpMResponeService;

/**
  * @gpcsoft.view value="signUpMResponeFormView"
  *  url="view/es/planform/signuprecord/signUpMResponeForm.jsp"
  * @gpcsoft.view value="signUpMResponeTreeFormView"
  *  url="view/es/planform/signuprecord/signUpMResponeTreeForm.jsp"
  * @gpcsoft.view value="signUpMResponeListView"
  *  url="view/es/planform/signuprecord/signUpMResponeList.jsp"
  * @gpcsoft.view value="signUpMResponeDetailView"
  *  url="view/es/planform/signuprecord/signUpMResponeDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={SignUpMRespone.class})
@RequestMapping("/SignUpMResponeController.do")//页面请求路径,可修改
public class SignUpMResponeController extends AnnotationMultiController<SignUpMRespone> {

	@Autowired(required=true) @Qualifier("signUpMResponeServiceImpl")
	private SignUpMResponeService signUpMResponeService;

}
