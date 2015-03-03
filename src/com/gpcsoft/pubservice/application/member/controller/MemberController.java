package com.gpcsoft.pubservice.application.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.pubservice.application.member.domain.Member;
import com.gpcsoft.pubservice.application.member.service.MemberService;

/**
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Member.class})
@RequestMapping("/MemberController.do")//页面请求路径,可修改
public class MemberController extends AnnotationMultiController<Member> {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("memberServiceImpl")
	private MemberService memberService;

}
