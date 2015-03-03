package com.gpcsoft.smallscale.vote.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.gpcsoft.smallscale.vote.domain.VotePointer;
import com.gpcsoft.smallscale.vote.domain.VoteTemplate;
import com.gpcsoft.smallscale.vote.service.VotePointerService;

/**
  * @gpcsoft.view value="votePointerFormView"
  *  url="view/smallscale/vote/vote_pointer_add.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={VotePointer.class})
@RequestMapping("/VotePointerController.do")//页面请求路径,可修改
public class VotePointerController extends AnnotationMultiController<VotePointer> {

	@Autowired(required=true) @Qualifier("votePointerServiceImpl")
	private VotePointerService votePointerService;
	
	/**
	 * Description :  跳转到新增或修改页面
	 * Create Date: 2011-2-18下午03:27:15 by zhaojf  Modified Date: 2011-2-18下午03:27:15 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toCreateOrUpdateVotePointer")
	public ModelAndView toCreateOrUpdateVotePointer(String objId,String voteTemplateId,HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		if(com.gpcsoft.core.utils.StringUtils.hasLength(objId)){//修改
			VotePointer votePointer = votePointerService.get(objId);
			if(votePointer.getVoteTemplate() == null){
				votePointer.setVoteTemplate(new VoteTemplate());
			}
			model.put("votePointer", votePointer);
		}else{//新增
			VotePointer votePointer = new VotePointer();
			VoteTemplate voteTemplate = new VoteTemplate();
			voteTemplate.setObjId(voteTemplateId);
			votePointer.setVoteTemplate(voteTemplate);
			model.put("votePointer", votePointer);
		}
		return new ModelAndView("votePointerFormView",model);
	}
	
	/**
	 * Description :  保存指标
	 * Create Date: 2011-2-18下午03:38:42 by zhaojf  Modified Date: 2011-2-18下午03:38:42 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=savePointer")
	public ModelAndView saveVotePointer(VotePointer votePointer,SessionStatus status,HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		votePointerService.saveVotePointer(votePointer);
		status.setComplete();
		model.put(Constants.JSON_RESULT,"success");
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
