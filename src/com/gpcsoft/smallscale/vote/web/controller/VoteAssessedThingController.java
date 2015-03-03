package com.gpcsoft.smallscale.vote.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.service.GoodsBrandService;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedThing;
import com.gpcsoft.smallscale.vote.domain.VoteTemplate;
import com.gpcsoft.smallscale.vote.domain.VoteUnitGroup;
import com.gpcsoft.smallscale.vote.service.VoteAssessedThingService;
import com.gpcsoft.smallscale.vote.service.VoteUnitGroupService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="voteAssessedThingFormView"
  *  url="view/smallscale/vote/vote_assessedThing_add.jsp"
  * @gpcsoft.view value="voteAssessedThingDetailView"
  *  url="view/smallscale/vote/vote_assessedThing_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={VoteAssessedThing.class})
@RequestMapping("/VoteAssessedThingController.do")//页面请求路径,可修改
public class VoteAssessedThingController extends AnnotationMultiController<VoteAssessedThing> {

	@Autowired(required=true) @Qualifier("voteAssessedThingServiceImpl")
	private VoteAssessedThingService voteAssessedThingService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentServiceImpl;

	@Autowired(required=true) @Qualifier("voteUnitGroupServiceImpl")
	private VoteUnitGroupService voteUnitGroupService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	@Autowired(required=true) @Qualifier("goodsBrandServiceImpl")
	private GoodsBrandService goodsBrandService;
	
	/**
	 * Description :  查看评选单位详情
	 * Create Date: 2011-2-17下午01:33:42 by zhaojf  Modified Date: 2011-2-17下午01:33:42 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=viewVoteAssessedThing")
	public ModelAndView viewVoteAssessedThing(String objId,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		VoteAssessedThing voteAssessedThing = voteAssessedThingService.get(objId);
		model.put("voteAssessedThing", voteAssessedThing);
		status.setComplete();
		return new ModelAndView("voteAssessedThingDetailView",model);
	}
	
	/**
	 * Description :  跳转到新增、修改页面
	 * Create Date: 2011-2-17下午04:18:22 by zhaojf  Modified Date: 2011-2-17下午04:18:22 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toCreateOrUpdateVoteAssessedThing")
	public ModelAndView toCreateOrUpdateVoteAssessedThing(String objId,String voteTemplateId, HttpServletRequest request){
		Map<String, Object> model = new HashMap<String, Object>();
		VoteAssessedThing voteAssessedThing = new VoteAssessedThing();
		if(com.gpcsoft.core.utils.StringUtils.hasLength(objId)){//修改
			voteAssessedThing = voteAssessedThingService.get(objId);
			if(voteAssessedThing.getVoteTemplate()==null){
				voteAssessedThing.setVoteTemplate(new VoteTemplate());
			}
		}else{//新增
			VoteTemplate voteTemplate = new VoteTemplate();
			voteTemplate.setObjId(voteTemplateId);
			voteAssessedThing.setVoteTemplate(voteTemplate);
			voteAssessedThing.setVoteUnitGroup(new VoteUnitGroup());
			voteAssessedThing.setIsRecommended(false);
			voteAssessedThing.setUseStatus("01");
		}
		model.put("voteAssessedThing", voteAssessedThing);
		
		//评选组列表
		List<VoteUnitGroup> voteUnitGroupList = voteUnitGroupService.getVoteUnitGroupListByTemplateId(voteAssessedThing.getVoteTemplate().getObjId());
		model.put("voteUnitGroupList", voteUnitGroupList);
		
		return new ModelAndView("voteAssessedThingFormView",model);
	}
	
	/**
	 * Description :  保存评选单位
	 * Create Date: 2011-2-17下午05:08:56 by zhaojf  Modified Date: 2011-2-17下午05:08:56 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveVoteAssessedThing")
	public ModelAndView saveVoteAssessedThing(VoteAssessedThing voteAssessedThing,HttpServletRequest request,SessionStatus status) throws Exception{
		String returnMessage = "success";
        Map<String, Object> model = new HashMap<String, Object>();
        
        //附件处理
        if(request instanceof MultipartHttpServletRequest){
        	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        	CommonsMultipartFile file = null;
        	file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile") ;
        	if(file.getSize()!=0){
        		try {
        			Object o = AttachmentUtil.uploadFile(request,"pictureFile");
        			if(o instanceof GpcBaseObject){
        				Attachment attachment = (Attachment)o;
        				attachmentServiceImpl.save(attachment);
        				voteAssessedThing.setPicture(attachment.getObjId());
        			}
        		}catch(Exception de) {
        			returnMessage = StringUtils.string2ASCII(de.getMessage());
        		}
        	}
        }
        
        voteAssessedThingService.save(voteAssessedThing);
        
        status.setComplete();

        model.put(Constants.JSON_RESULT,returnMessage);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description :  在线报名参选对象
	 * Create Date: 2011-5-18下午04:47:33 by zhaojf  Modified Date: 2011-5-18下午04:47:33 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=signOnLineAssessedThing")
	public ModelAndView signOnLineAssessedThing(VoteAssessedThing voteAssessedThing,HttpServletRequest request,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		String attenderOrgInfoId = request.getParameter("attenderOrgInfoId");
		GoodsBrand goodsBrand = voteAssessedThingService.getGoodsBrandByNameAndBelongs(attenderOrgInfoId, voteAssessedThing.getAttenderName());
		voteAssessedThing.setAttender(goodsBrand.getObjId());//参选对象Id
		voteAssessedThing.setPicture(goodsBrand.getMainLogo());//参选对象图片
		voteAssessedThing.setThingComment(goodsBrand.getBrandDesc());//参选对象说明
		voteAssessedThingService.save(voteAssessedThing);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  是否推荐(isRecommended)
	 * Create Date: 2011-4-26下午06:35:42 by zhaojf  Modified Date: 2011-4-26下午06:35:42 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=updateIsRecommendedStatus")
	public ModelAndView updateIsRecommendedStatus(String objId,String isStatusVale) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("voteAssessedThingId", objId);//主题Id
		param.put("isStatusVale", isStatusVale);//当前状态值
		
		Boolean relsut = voteAssessedThingService.updateIsRecommendedStatus(param);
		if(relsut){
			model.put(Constants.JSON_RESULT, "success");
		}
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * Description :  审核此参选对象是否有效(包过参选对象所属机构有效、此参选对象有效)
	 * Create Date: 2011-5-5下午02:32:10 by zhaojf  Modified Date: 2011-5-5下午02:32:10 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=auditAttenderUseStatus")
	public ModelAndView auditAttenderUseStatus(String objId,String status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("voteAssessedThingId", objId);//主题Id
		param.put("status", "useStatus");//当前状态值
		
		VoteAssessedThing voteAssessedThing = voteAssessedThingService.get(objId);//参选对象
		GoodsBrand goodsBrand = goodsBrandService.get(voteAssessedThing.getAttender());//品牌
		OrgInfo orgInfo = orgInfoService.get(goodsBrand.getBelongsId());
		if(!"01".equals(goodsBrand.getUseStatus())){//品牌有效
			model.put("brandInValid", "true");
		}
		if(!"01".equals(orgInfo.getUseStatus())){//机构有效
			model.put("orgInfoInValid", "true");
		}
		if("01".equals(goodsBrand.getUseStatus()) && "01".equals(orgInfo.getUseStatus())){
			Boolean relsut = voteAssessedThingService.updateIsRecommendedStatus(param);//当品牌有效、所属机构有效,此参选对象修改为有效
			if(relsut){
				model.put(Constants.JSON_RESULT, "true");
			}
		}
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  设置排序号
	 * Create Date: 2011-5-20上午11:58:23 by zhaojf  Modified Date: 2011-5-20上午11:58:23 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=setNumSort")
	public ModelAndView setNumSort(String stringId,String numSort,String operClass) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("operClass", operClass);//操作domain
		params.put("stringId", stringId);//对象Id
		params.put("numSort", Integer.valueOf(numSort));//排序号
		boolean flag = voteAssessedThingService.setNumSort(params);
		model.put(Constants.JSON_RESULT, flag);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
