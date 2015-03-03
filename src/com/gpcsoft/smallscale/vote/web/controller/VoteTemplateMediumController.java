package com.gpcsoft.smallscale.vote.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.smallscale.vote.service.VoteTemplateMediumService;
import com.gpcsoft.smallscale.vote.domain.VoteTemplateMedium;

/**
  * @gpcsoft.view value="voteTemplateMediumDetailView"
  *  url="view/smallscale/vote/vote_template_medium_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={VoteTemplateMedium.class})
@RequestMapping("/VoteTemplateMediumController.do")//页面请求路径,可修改
public class VoteTemplateMediumController extends AnnotationMultiController<VoteTemplateMedium> {

	@Autowired(required=true) @Qualifier("voteTemplateMediumServiceImpl")
	private VoteTemplateMediumService voteTemplateMediumService;
	
	/**
	 * Description :  从机构库里赛选媒体
	 * Create Date: 2011-5-13上午11:49:33 by zhaojf  Modified Date: 2011-5-13上午11:49:33 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=getMediumOrgList")
	public ModelAndView getMediumOrgList(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		String orgName = request.getParameter("orgName");//媒体名称
		paramsMap.put("mediumName", orgName);
		
		Page<OrgInfo> page = prePage(request);//预分页,算出当前页和大小等
		Page<OrgInfo> pageMediumOrg = voteTemplateMediumService.getMediumOrgList(page,paramsMap);
    	endPage(model, pageMediumOrg, request);
		model.put("pageMediumOrg", pageMediumOrg);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/**
	 * Description :  查看详情
	 * Create Date: 2011-5-13下午04:13:11 by zhaojf  Modified Date: 2011-5-13下午04:13:11 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=viewMediumOrgInfo")
	public ModelAndView viewMediumOrgInfo(String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		VoteTemplateMedium voteTemplateMedium = voteTemplateMediumService.get(objId);
		model.put("voteTemplateMedium", voteTemplateMedium);
		return new ModelAndView("voteTemplateMediumDetailView",model);
	}
	
	/**
	 * Description :  判断合作媒体是否已包含此媒体
	 * Create Date: 2011-5-16下午04:19:33 by zhaojf  Modified Date: 2011-5-16下午04:19:33 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=isHasMediumOfTemplate")
	public ModelAndView isHasMediumOfTemplate(String templateId,String mediumId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("operationType", "isHasMedium");//操作类型
		params.put("templateId", templateId);
		params.put("mediumId", mediumId);
		boolean flag = voteTemplateMediumService.operateMediumParams(params);
		model.put(Constants.JSON_RESULT, flag);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  设置媒体排序号
	 * Create Date: 2011-5-16下午04:19:33 by zhaojf  Modified Date: 2011-5-16下午04:19:33 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=setMediumSort")
	public ModelAndView setMediumSort(String templateMediumId,String mediumSort) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("operationType", "setMediumSort");//操作类型
		params.put("templateMediumId", templateMediumId);
		params.put("mediumSort", Integer.valueOf(mediumSort));
		boolean flag = voteTemplateMediumService.operateMediumParams(params);
		model.put(Constants.JSON_RESULT, flag);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  保存合作媒体
	 * Create Date: 2011-5-16下午05:09:45 by zhaojf  Modified Date: 2011-5-16下午05:09:45 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveTemplateMedium")
	public ModelAndView saveTemplateMedium(VoteTemplateMedium voteTemplateMedium,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		//设置排序号
		params.put("templateId", voteTemplateMedium.getVoteTemplate().getObjId());
		Integer mediumSort = voteTemplateMediumService.generateMediumSort(params);
		voteTemplateMedium.setMediumSort(mediumSort);
		
		voteTemplateMediumService.save(voteTemplateMedium);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
