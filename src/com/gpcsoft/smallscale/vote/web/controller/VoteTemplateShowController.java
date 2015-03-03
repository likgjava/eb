package com.gpcsoft.smallscale.vote.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.service.GoodsBrandService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.vote.domain.VoteAssesseExpert;
import com.gpcsoft.smallscale.vote.domain.VoteAssessed;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedThing;
import com.gpcsoft.smallscale.vote.domain.VoteTemplate;
import com.gpcsoft.smallscale.vote.domain.VoteTemplateMedium;
import com.gpcsoft.smallscale.vote.domain.VoteUnitGroup;
import com.gpcsoft.smallscale.vote.enumeration.VoteEnum;
import com.gpcsoft.smallscale.vote.service.VoteAssessedService;
import com.gpcsoft.smallscale.vote.service.VoteAssessedThingService;
import com.gpcsoft.smallscale.vote.service.VoteTemplateMediumService;
import com.gpcsoft.smallscale.vote.service.VoteTemplateService;
import com.gpcsoft.smallscale.vote.service.VoteUnitGroupService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="showVoteIndexView"
  *  url="view/smallscale/vote/show_vote_index.jsp"
  * @gpcsoft.view value="showUnitObjectStatisticView"
  *  url="view/smallscale/vote/show_unit_object_statistic.jsp"
  * @gpcsoft.view value="showToVoteView"
  *  url="view/smallscale/vote/show_to_vote.jsp"
  * @gpcsoft.view value="showVoteUnitCommentView"
  *  url="view/smallscale/vote/show_vote_unit_comment.jsp"
  * @gpcsoft.view value="showVoteResultListView"
  *  url="view/smallscale/vote/show_vote_result_list.jsp"
  * @gpcsoft.view value="showMoreUnitView"
  *  url="view/smallscale/vote/show_more_unit_object.jsp"
  * @gpcsoft.view value="showMoreExpertView"
  *  url="view/smallscale/vote/show_more_expert.jsp"
  * @gpcsoft.view value="showMoreOrgView"
  *  url="view/smallscale/vote/show_more_belongs_org.jsp"
  * @gpcsoft.view value="showMoreTemplateMediumView"
  *  url="view/smallscale/vote/show_more_template_medium.jsp"
  * @gpcsoft.view value="signOnlineNewBrandView"
  *  url="view/smallscale/vote/sign_online_new_brand.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/VoteTemplateShowController.do")//页面请求路径,可修改
public class VoteTemplateShowController extends AnnotationMultiController<GpcBaseObject> {

	@Autowired(required=true) @Qualifier("voteTemplateServiceImpl")
	private VoteTemplateService voteTemplateService;
	
	@Autowired(required=true) @Qualifier("voteAssessedThingServiceImpl")
	private VoteAssessedThingService voteAssessedThingService;
	
	@Autowired(required=true) @Qualifier("voteAssessedServiceImpl")
	private VoteAssessedService voteAssessedService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	@Autowired(required=true) @Qualifier("goodsBrandServiceImpl")
	private GoodsBrandService goodsBrandService;
	
	@Autowired(required=true) @Qualifier("voteUnitGroupServiceImpl")
	private VoteUnitGroupService voteUnitGroupService;
	
	@Autowired(required=true) @Qualifier("voteTemplateMediumServiceImpl")
	private VoteTemplateMediumService voteTemplateMediumService;
	
	/**
	 * Description : 投票活动首页
	 * Create Date: 2011-2-22下午04:16:15 by zhaojf  Modified Date: 2011-2-22下午04:16:15 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=showVoteIndex")
	public ModelAndView showVoteIndex(String templateCode,SessionStatus status,HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		//主题信息
		VoteTemplate voteTemplate = voteTemplateService.getVoteTemplateByCode(templateCode);
		model.put("voteTemplate", voteTemplate);
		
		//合作媒体
		params.put("voteTemplateId", voteTemplate.getObjId());//主题Id
		List<VoteTemplateMedium> voteTemplateMediumList = voteTemplateMediumService.getVoteTemplateMediumList(params);
		model.put("voteTemplateMediumList", voteTemplateMediumList.subList(0, 4));
		
		//评审专家
		params.put("voteTemplateId", voteTemplate.getObjId());//主题Id
		params.put("rownum", 6);//首页展示6条记录	
		Page<VoteAssesseExpert> pageExpert = new Page<VoteAssesseExpert>(1L, 10L, 6, new ArrayList<VoteAssesseExpert>());
    	Page<VoteAssesseExpert> pageDataExpert = voteTemplateService.getVoteAssesseExpertList(pageExpert, params);
    	endPage(model, pageDataExpert, request);
    	List<VoteAssesseExpert> voteAssesseExpertList = pageDataExpert.getData();
		model.put("voteAssesseExpertList", voteAssesseExpertList);
		model.put("expertCount", pageDataExpert.getTotalRowNum());
		
		//参选对象信息
		params.clear();
		int rownum = 8;//设置首页展示是显示几条记录
		params.put("voteTemplateId", voteTemplate.getObjId());//主题Id
		params.put("rownum", rownum);//首页展示8条记录		
		
		Page<VoteAssessedThing> page = new Page<VoteAssessedThing>(1L, 10L, rownum, new ArrayList<VoteAssessedThing>());
    	Page<VoteAssessedThing> pageData = voteTemplateService.getVoteAssessedObjectList(page, params);
    	endPage(model, pageData, request);
		
    	List<VoteAssessedThing> voteAssessThingList = pageData.getData();
		model.put("voteAssessThingList", voteAssessThingList);
		model.put("trNumCount", pageData.getTotalRowNum());
		
		//参选对象的所属机构
		String groupType = "01";//评选组类型  品牌
		params.clear();
		params.put("groupType", groupType);
		params.put("voteTemplateId", voteTemplate.getObjId());//主题Id
		params.put("rownum", rownum);//首页展示8条记录
		Page<OrgInfo> pageObject = new Page<OrgInfo>(1L, 10L, rownum, new ArrayList<OrgInfo>());
    	Page<OrgInfo> pageDataObject = voteTemplateService.getVoteBelongsOrgInfoList(pageObject, params);
    	endPage(model, pageDataObject, request);
		List<OrgInfo> orgInfoList = pageDataObject.getData();

		model.put("orgInfoList", orgInfoList);
		model.put("orgInfoListCount", orgInfoList.size());
		
		//当前用户
		User user = AuthenticationHelper.getCurrentUser();
		if(user != null){
			model.put("user", user);
		}
		
		status.setComplete();
		return new ModelAndView("showVoteIndexView",model);
	}

	/**
	 * Description : 更多的参选对象、专家、所属机构、合作媒体
	 * Create Date: 2011-5-6下午03:04:15 by zhaojf  Modified Date: 2011-5-6下午03:04:15 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=showMoreUnitOrExpertOrOrg")
	public ModelAndView showMoreUnitOrExpertOrOrg(HttpServletRequest request) throws Exception{
    	Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		String returnView = "showMoreUnitView";//更多参选对象
		
		String showMoreType = request.getParameter("showMoreType");//参数
		
		//主题Id
		String voteTemplateId = request.getParameter("voteTemplateId");
		paramsMap.put("voteTemplateId",voteTemplateId);
		//主题信息
		VoteTemplate voteTemplate = voteTemplateService.get(voteTemplateId);
		model.put("voteTemplate", voteTemplate);
		
		//合作媒体
		if("moreMediums".equals(showMoreType)){
			returnView = "showMoreTemplateMediumView";
			paramsMap.put("voteTemplateId", voteTemplate.getObjId());//主题Id
			List<VoteTemplateMedium> voteTemplateMediumList = voteTemplateMediumService.getVoteTemplateMediumList(paramsMap);
			model.put("voteTemplateMediumList", voteTemplateMediumList);
		}
		
		if("moreObjects".equals(showMoreType) || "moreExperts".equals(showMoreType)){
			//评选组列表
			List<VoteUnitGroup> voteUnitGroupList = voteUnitGroupService.getVoteUnitGroupListByTemplateId(voteTemplateId);
			model.put("voteUnitGroupList", voteUnitGroupList);
			
			String voteUnitGroupId = request.getParameter("voteUnitGroupId");
			if(voteUnitGroupId != null && voteUnitGroupId != ""){
				paramsMap.put("voteUnitGroupId", voteUnitGroupId);
				model.put("voteUnitGroupId", voteUnitGroupId);
			}else{
				paramsMap.put("voteUnitGroupId", voteUnitGroupList.get(0).getObjId());
				model.put("voteUnitGroupId", voteUnitGroupList.get(0).getObjId());
			}
		}
		
		//更多的参选对象
		if("moreObjects".equals(showMoreType)){
			returnView = "showMoreUnitView";//更多参选对象
			
	    	Page<VoteAssessedThing> page = prePage(request);//预分页,算出当前页和大小等	
	    	Page<VoteAssessedThing> pageData = voteTemplateService.getVoteAssessedObjectList(page, paramsMap);
	    	endPage(model, pageData, request);
	    	model.put("PAGERESULT",pageData);
		}
		
		//更多评审专家
    	if("moreExperts".equals(showMoreType)){
			returnView = "showMoreExpertView";
			Page<VoteAssesseExpert> pageExpert = prePage(request);//预分页,算出当前页和大小等	
	    	Page<VoteAssesseExpert> pageDataExpert = voteTemplateService.getVoteAssesseExpertList(pageExpert, paramsMap);
	    	endPage(model, pageDataExpert, request);
	    	model.put("PAGERESULT",pageDataExpert);
		}
    	
    	//更多参与机构
		if("moreOrgs".equals(showMoreType)){
			returnView = "showMoreOrgView";
			String groupType = "01";//评选组类型  品牌
			paramsMap.put("groupType", groupType);
			paramsMap.put("voteTemplateId", voteTemplate.getObjId());//主题Id
			Page<OrgInfo> pageObject = prePage(request);//预分页,算出当前页和大小等	
	    	Page<OrgInfo> pageDataObject = voteTemplateService.getVoteBelongsOrgInfoList(pageObject, paramsMap);
	    	endPage(model, pageDataObject, request);
	    	model.put("PAGERESULT",pageDataObject);

		}
    	
		return new ModelAndView(returnView,model);
	}
	
	/**
	 * Description : 投票页面
	 * Create Date: 2011-2-24上午10:58:34 by zhaojf  Modified Date: 2011-2-24上午10:58:34 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toVoteJsp")
	public ModelAndView toVoteJsp(String templateId,String voteAssessedThingId,String isSingleVote,String expertVoteFactor) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		//指标信息		
		model = voteAssessedService.findUnfurlPointerOfTemplate(templateId,voteAssessedThingId);
		
		//专家投票占分系数
		model.put("expertVoteFactor", expertVoteFactor);
		
		//主题信息
		model.put("voteTemplate", voteTemplateService.get(templateId));
		
		//参选对象
		VoteAssessedThing voteAssessedThing = voteAssessedThingService.get(voteAssessedThingId);
		model.put("voteAssessedThing", voteAssessedThing);
		
		//参选对象所属评选组
		VoteUnitGroup voteUnitGroup = voteAssessedThing.getVoteUnitGroup();
		model.put("voteUnitGroup", voteUnitGroup);
		
		//参选对象所属机构
		String groupType = voteUnitGroup.getGroupType();//评选组类型
		String attender = voteAssessedThing.getAttender();//参选对象id
		OrgInfo orgInfo = new OrgInfo();
		//企业
		if(attender!=null && attender!="" && groupType.equals(VoteEnum.GROUP_TYPE_ORG)){
			orgInfo = orgInfoService.get(attender);
		}
		
		//品牌
		if(attender!=null && attender!="" && groupType.equals(VoteEnum.GROUP_TYPE_BRAND)){
			GoodsBrand goodsBrand = goodsBrandService.get(attender);
			orgInfo = orgInfoService.get(goodsBrand.getBelongsId());
		}
		
		//当前用户信息(用以判断是否登录)
		User user = AuthenticationHelper.getCurrentUser();
		model.put("user", user);
		
		model.put("orgInfo", orgInfo);
		model.put("isSingleVote", isSingleVote);
		return new ModelAndView("showToVoteView",model);
	}

	/**
	 * Description : 保存投票评分结果
	 * Create Date: 2011-2-24下午02:17:50 by zhaojf  Modified Date: 2011-2-24下午02:17:50 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=voteGrade")
	public ModelAndView voteGrade(SessionStatus status,VoteAssessed voteAssessed,String result,String isSingleVote) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		
		//保存到投票评选表
		voteAssessed = voteAssessedService.saveVoteAssessed(voteAssessed);
		
		if(isSingleVote.equals("false")){
			String[] idsResult = result.split(";;");
			if(idsResult != null && idsResult.length>0){
				//保存到评选分值表
				voteAssessedService.saveVoteAssessedGrade(voteAssessed.getObjId(), idsResult);
			}
		}
		
		//设置投票数、评论数、成绩
		VoteAssessedThing voteAssessedThing = voteAssessedThingService.get(voteAssessed.getVoteAssessedThing().getObjId());
		voteAssessedThing.setVoteCountNum(voteAssessedThing.getVoteCountNum()+1);//设置投票数
		if(voteAssessed.getMemo() != null && voteAssessed.getMemo() != ""){
			voteAssessedThing.setVoteCommentNum(voteAssessedThing.getVoteCommentNum()+1);//设置评论数
		}
		params = voteAssessedService.voteStatisticUnfurl(voteAssessedThing.getVoteTemplate().getObjId(), voteAssessedThing.getObjId());
		voteAssessedThing.setVoteFinalCountGrade((BigDecimal) params.get("assessedFinalGrade"));//设置成绩
		voteAssessedThingService.save(voteAssessedThing);
		
		model.put(Constants.JSON_RESULT, "success");
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description : 投票评论列表(单个参选对象的评论列表)
	 * Create Date: 2011-4-28下午02:04:07 by zhaojf  Modified Date: 2011-4-28下午02:04:07 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=showVoteUnitComment")
	public ModelAndView showVoteUnitComment(HttpServletRequest request) throws Exception{
    	Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		//参选对象Id
		String voteAssessedThingId = request.getParameter("voteAssessedThingId");
		paramsMap.put("voteAssessedThingId", voteAssessedThingId);
		
		//取回帖列表
    	Page<VoteAssessed> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<VoteAssessed> pageData = voteAssessedService.getVoteAssessedCommentShow(page, paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
    	model.put("voteAssessedThing", voteAssessedThingService.get(voteAssessedThingId));
		
		return new ModelAndView("showVoteUnitCommentView",model);
	}

	/**
	 * Description : 投票结果列表(参选对象、投票数、成绩、评论数)
	 * Create Date: 2011-4-28下午04:53:15 by zhaojf  Modified Date: 2011-4-28下午04:53:15 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=showVoteResult")
	public ModelAndView showVoteResult(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String returnView = "showVoteResultListView";
		
		String templateId = request.getParameter("templateId");//参数
		String operation = request.getParameter("operation");//参数
		
		//主题Id
		paramMap.put("voteTemplateId",templateId);
		//主题信息
		VoteTemplate voteTemplate = voteTemplateService.get(templateId);
		model.put("voteTemplate", voteTemplate);
		
		if(!"topNum".equals(operation)){
			//评选组列表
			List<VoteUnitGroup> voteUnitGroupList = voteUnitGroupService.getVoteUnitGroupListByTemplateId(templateId);
			model.put("voteUnitGroupList", voteUnitGroupList);
			
			String voteUnitGroupId = request.getParameter("voteUnitGroupId");
			if(voteUnitGroupId != null && voteUnitGroupId != ""){
				paramMap.put("voteUnitGroupId", voteUnitGroupId);
				model.put("voteUnitGroup", voteUnitGroupService.get(voteUnitGroupId));
			}else{
				paramMap.put("voteUnitGroupId", voteUnitGroupList.get(0).getObjId());
				model.put("voteUnitGroup", voteUnitGroupList.get(0));
			}
		}
		
		paramMap.put("voteTemplateId", voteTemplate.getObjId());//主题Id
		int rownum = 6;//设置首页展示是显示几条记录
		if("topNum".equals(operation)){
			paramMap.put("rownum", rownum);//首页展示6条记录
			returnView = Constants.JSON_VIEW;
		}
		
		Page<VoteAssessedThing> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<VoteAssessedThing> pageData = voteTemplateService.getVoteAssessedObjectList(page, paramMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	if("topNum".equals(operation)){
    		model.put("voteResultTopNumLilst",pageData.getData());
		}
		
		return new ModelAndView(returnView,model);
	}
		
	/**
	 * Description : 在线报名(跳转到发布品牌)
	 * Create Date: 2011-5-3上午10:38:13 by zhaojf  Modified Date: 2011-5-3上午10:38:13 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=signOnLine")
	public ModelAndView signOnLine(HttpServletRequest request,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		GoodsBrand goodsBrand =new GoodsBrand();
		model.put("isManager", false);
		model.put("blongOrg", (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo());
		model.put("goodsBrand", goodsBrand);
		
		//评选组列表
		String templateId = request.getParameter("templateId");
		model.put("templateId", templateId);
		List<VoteUnitGroup> voteUnitGroupList = voteUnitGroupService.getVoteUnitGroupListByTemplateId(templateId);
		model.put("voteUnitGroupList", voteUnitGroupList);
		return new ModelAndView("signOnlineNewBrandView",model);
	}
	
	/**
	 * Description : 页面初始化加载项
	 * Create Date: 2011-5-5上午11:35:45 by zhaojf  Modified Date: 2011-5-5上午11:35:45 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=pageFirstInitOnLoad")
	public ModelAndView pageFirstInitOnLoad(String templateId,String operation) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		VoteTemplate voteTemplate = null;
		List<VoteUnitGroup> voteUnitGroupList = null;
		List<GoodsBrand> showGoodsBrandList = null;
		
		if("showTemplate".equals(operation) || "showBrand".equals(operation)){
			//主题信息
			voteTemplate = voteTemplateService.get(templateId);
			//评选组列表
			voteUnitGroupList = voteUnitGroupService.getVoteUnitGroupListByTemplateId(templateId);
		}
		
		//品牌列表
		if("showBrand".equals(operation)){
			showGoodsBrandList = goodsBrandService.getAllGoodsBrandByOrgId();
		}
		
		model.put("voteTemplate", voteTemplate);
		model.put("voteUnitGroupList", voteUnitGroupList);
		model.put("showGoodsBrandList", showGoodsBrandList);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description : 参选对象指标评分值统计
	 * Create Date: 2011-3-1上午11:10:24 by zhaojf  Modified Date: 2011-3-1上午11:10:24 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toVoteStatisticUnfurl")
	public ModelAndView toVoteStatisticUnfurl(String templateId,String voteAssessedThingId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		//指标信息、投票人数、平均成绩、最终成绩		
		model = voteAssessedService.voteStatisticUnfurl(templateId, voteAssessedThingId);
		
		return new ModelAndView("showUnitObjectStatisticView",model);
	}
	
}
