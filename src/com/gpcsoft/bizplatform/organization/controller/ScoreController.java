package com.gpcsoft.bizplatform.organization.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.bizplatform.organization.domain.Score;
import com.gpcsoft.bizplatform.organization.service.ScoreService;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="scoreFormView"
  *  url="view///scoreForm.jsp"
  * @gpcsoft.view value="scoreTreeFormView"
  *  url="view///scoreTreeForm.jsp"
  * @gpcsoft.view value="scoreListView"
  *  url="view///scoreList.jsp"
  * @gpcsoft.view value="scoreDetailView"
  *  url="view///scoreDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Score.class})
@RequestMapping("/ScoreController.do")//页面请求路径,可修改
public class ScoreController extends AnnotationMultiController<Score> {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("scoreServiceImpl")
	private ScoreService scoreService;

}
