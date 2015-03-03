package com.gpcsoft.smallscale.groupbuying.controller;

import java.util.Date;
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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuying;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyingClass;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyingClassService;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyingService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="groupBuyingFormView"
  * url="view/smallscale/groupbuying/group_buying_form.jsp"
  * 
  * @gpcsoft.view value="groupBuyingDetailView"
  * url="view/smallscale/groupbuying/group_buying_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GroupBuying.class})
@RequestMapping("/GroupBuyingController.do")
public class GroupBuyingController extends AnnotationMultiController<GroupBuying> {

	@Autowired(required=true) @Qualifier("groupBuyingServiceImpl")
	private GroupBuyingService groupBuyingService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("groupBuyingClassServiceImpl")
	private GroupBuyingClassService groupBuyingClassService;
	
	/** 
	 * Description :  跳转到新增或修改团购信息页面
	 * Create Date: 2011-6-21上午11:16:04 by likg  Modified Date: 2011-6-21上午11:16:04 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateView")
	public ModelAndView toCreateOrUpdateView(String objId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取团购信息
		GroupBuying groupBuying = null;
		if(StringUtils.hasLength(objId)) {
			groupBuying = groupBuyingService.get(objId);
			
			//获取团购分类信息
			QueryObject<GroupBuyingClass> query = new QueryObjectBase<GroupBuyingClass>();
			query.setEntityClass(GroupBuyingClass.class);
			query.getQueryParam().and(new QueryParam("goodsClass.objId",QueryParam.OPERATOR_EQ,groupBuying.getGoodsClass().getObjId()));
			List<GroupBuyingClass> ggcList = groupBuyingClassService.findByQuery(query);
			if(ggcList!=null && ggcList.size()>0) {
				model.put("groupBuyingClassName", ggcList.get(0).getName());
			}
		} else {
			groupBuying = new GroupBuying();
		}
		model.put("groupBuying", groupBuying);
		
		return new ModelAndView("groupBuyingFormView", model);
	}
	
	/** 
	 * Description :  保存团购信息
	 * Create Date: 2011-6-21下午04:41:07 by likg  Modified Date: 2011-6-21下午04:41:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveGroupBuying")
	public ModelAndView saveGroupBuying(GroupBuying groupBuying, HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//处理团购图片
		CommonsMultipartFile file = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile");
		if(file!=null && file.getSize()!=0) {
			Object o = AttachmentUtil.uploadFile(request, "pictureFile");
			if(o instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o;
				attachmentService.saveAttachment(attachment);
				groupBuying.setPicture(attachment.getObjId());
			}
		}
		
		//保存团购信息
		groupBuyingService.saveGroupBuying(groupBuying);
		model.put("objId", groupBuying.getObjId());
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  修改团购信息的使用状态
	 * Create Date: 2011-6-22下午02:13:54 by likg  Modified Date: 2011-6-22下午02:13:54 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateStatus")
	public ModelAndView updateStatus(String objId, String useStatus, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//修改团购信息使用状态
		groupBuyingService.updateStatus(objId, useStatus);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到团购详情页面
	 * Create Date: 2011-6-22下午02:13:54 by likg  Modified Date: 2011-6-22下午02:13:54 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGroupBuyingDetailView")
	public ModelAndView toGroupBuyingDetailView(String objId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取团购信息
		GroupBuying groupBuying = groupBuyingService.get(objId);
		model.put("groupBuying", groupBuying);
		
		return new ModelAndView("groupBuyingDetailView", model);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request) throws Exception {
		String status = request.getParameter("status"); //团购信息状态
		
		//团购进行中(startTime<nowDate<endTime && useStatus=01)
		if("buying".equals(status)) {
			query.getQueryParam().and(new QueryParam("startTime",QueryParam.OPERATOR_LT,new Date()));
			query.getQueryParam().and(new QueryParam("endTime",QueryParam.OPERATOR_GT,new Date()));
			query.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
			
			//按团购结束时间倒序排列
			query.getQueryProjections().setOrderProperty("endTime");
			query.getQueryProjections().setDescFlag(true);
		}
		//团购未开始(startTime>nowDate || useStatus=00)
		else if("noStart".equals(status)) {
			QueryParam or = new QueryParam();
			//startTime>nowDate
			QueryParam and1 = new QueryParam("startTime",QueryParam.OPERATOR_GT,new Date());
			or.or(and1);
			//useStatus=00
			QueryParam and2 = new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_TEMP);
			or.or(and2);
			query.getQueryParam().and(or);
			
			//按团购开始时间倒序排列
			query.getQueryProjections().setOrderProperty("startTime");
			query.getQueryProjections().setDescFlag(true);
		}
		//团购已结束(endTime<nowDate)
		else if("over".equals(status)) {
			query.getQueryParam().and(new QueryParam("endTime",QueryParam.OPERATOR_LT,new Date()));
			
			//按团购创建时间倒序排列
			query.getQueryProjections().setOrderProperty("createTime");
			query.getQueryProjections().setDescFlag(true);
		}
		
		//过滤掉无效状态的记录
		query.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_NE,CommonEnum.USER_STATUS_CANCEL));
		
		return query;
	}

}
