package com.gpcsoft.epp.taskplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.taskplan.domain.ProjMember;
import com.gpcsoft.epp.taskplan.service.ProjMemberService;

/**
  * @gpcsoft.view value="projMemberFormView"
  *  url="view/epp/taskplan/Record/projMemberForm.jsp"
  * @gpcsoft.view value="projMemberTreeFormView"
  *  url="view/epp/taskplan/Record/projMemberTreeForm.jsp"
  * @gpcsoft.view value="projMemberListView"
  *  url="view/epp/taskplan/Record/projMemberList.jsp"
  * @gpcsoft.view value="projMemberDetailView"
  *  url="view/epp/taskplan/Record/projMemberDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ProjMember.class})
@RequestMapping("/ProjMemberController.do")//页面请求路径,可修改
public class ProjMemberController extends AnnotationMultiController<ProjMember> {

	@Autowired(required=true) @Qualifier("projMemberServiceImpl")
	private ProjMemberService projMemberService;

}
