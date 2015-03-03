package com.gpcsoft.bizplatform.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.bizplatform.organization.domain.UserInfo;
import com.gpcsoft.bizplatform.organization.service.UserInfoService;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="userInfoFormView"
  *  url="view//organization/userInfoForm.jsp"
  * @gpcsoft.view value="userInfoTreeFormView"
  *  url="view//organization/userInfoTreeForm.jsp"
  * @gpcsoft.view value="userInfoListView"
  *  url="view//organization/userInfoList.jsp"
  * @gpcsoft.view value="userInfoDetailView"
  *  url="view//organization/userInfoDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={UserInfo.class})
@RequestMapping("/UserInfoController.do")//页面请求路径,可修改
public class UserInfoController extends AnnotationMultiController<UserInfo> {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("userInfoServiceImpl")
	private UserInfoService userInfoService;

}
