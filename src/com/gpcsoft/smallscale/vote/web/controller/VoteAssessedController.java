package com.gpcsoft.smallscale.vote.web.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.smallscale.vote.domain.VoteAssessed;
import com.gpcsoft.smallscale.vote.service.VoteAssessedService;

/**
  * @gpcsoft.view value="voteAssessedFormView"
  *  url="view/smallscale/vote/vote_assessed_add.jsp"
  * @gpcsoft.view value="voteAssessedListView"
  *  url="view/smallscale/vote/vote_assessed_list.jsp"
  * @gpcsoft.view value="voteAssessedDetailView"
  *  url="view/smallscale/vote/vote_assessed_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={VoteAssessed.class})
@RequestMapping("/VoteAssessedController.do")//页面请求路径,可修改
public class VoteAssessedController extends AnnotationMultiController<VoteAssessed> {

	@Autowired(required=true) @Qualifier("voteAssessedServiceImpl")
	private VoteAssessedService voteAssessedService;
	
	/**
	 * Description :  查看投票评选
	 * Create Date: 2011-2-24上午10:29:34 by zhaojf  Modified Date: 2011-2-24上午10:29:34 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=viewVoteAssessed")
	public ModelAndView viewVoteAssessed(String objId,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		model = voteAssessedService.viewVoteAssessed(objId);
		status.setComplete();
		return new ModelAndView("voteAssessedDetailView",model);
	}

	/**
	 * Description :  删除
	 * Create Date: 2011-2-24上午10:34:23 by zhaojf  Modified Date: 2011-2-24上午10:34:23 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=removeVoteAssessed")
	public ModelAndView removeVoteAssessed(String objId) throws Exception{
		voteAssessedService.removeVoteAssessed(objId);
		return new ModelAndView(Constants.JSON_VIEW);
	}	

}
