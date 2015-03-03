package com.gpcsoft.smallscale.vote.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.smallscale.vote.domain.VotePointerRecommendExpert;
import com.gpcsoft.smallscale.vote.service.VotePointerRecommendExpertService;

/**
  * @gpcsoft.view value="votePointerRecommendExpertFormView"
  *  url="view/smallscale/vote/votePointerRecommendExpertForm.jsp"
  * @gpcsoft.view value="votePointerRecommendExpertTreeFormView"
  *  url="view/smallscale/vote/votePointerRecommendExpertTreeForm.jsp"
  * @gpcsoft.view value="votePointerRecommendExpertListView"
  *  url="view/smallscale/vote/votePointerRecommendExpertList.jsp"
  * @gpcsoft.view value="votePointerRecommendExpertDetailView"
  *  url="view/smallscale/vote/votePointerRecommendExpertDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={VotePointerRecommendExpert.class})
@RequestMapping("/VotePointerRecommendExpertController.do")//页面请求路径,可修改
public class VotePointerRecommendExpertController extends AnnotationMultiController<VotePointerRecommendExpert> {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("votePointerRecommendExpertServiceImpl")
	private VotePointerRecommendExpertService votePointerRecommendExpertService;

}
