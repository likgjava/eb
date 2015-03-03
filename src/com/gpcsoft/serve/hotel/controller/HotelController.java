package com.gpcsoft.serve.hotel.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.service.HotelService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="hotelFormView"
  *  url="view/serve/hotel/hotel_form.jsp"
  * @gpcsoft.view value="hotelListView"
  *  url="view/serve/hotel/hotel_list.jsp"
  * @gpcsoft.view value="hotelAuditListView"
  *  url="view/serve/hotel/hotel_audit_list.jsp"
  * @gpcsoft.view value="hotelDetailView"
  *  url="view/serve/hotel/hotel_detail.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Hotel.class})
@RequestMapping("/HotelController.do")//页面请求路径,可修改
public class HotelController extends AnnotationMultiController<Hotel> {

	@Autowired(required=true) @Qualifier("hotelServiceImpl")
	private HotelService hotelService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentServiceImpl;


	/** 
	 * Description :  验证同一机构下酒店名称的唯一性
	 * Create Date: 2010-12-8下午04:13:18 by guoyr  Modified Date: 2010-12-8下午04:13:18 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=isUnique")
	public ModelAndView isUnique(String objId, String hotelName)throws Exception {
		Map<String, Boolean> model = new HashMap<String, Boolean>();
		
		hotelName = StringUtils.ascii2Native(hotelName);
		model.put("isUnique", hotelService.isUnique(objId, hotelName));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到酒店列表页面
	 * Create Date: 2010-12-7 上午09:18:16 by guoyr  Modified Date: 2010-12-7上午09:18:16 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = hotelService.getDistrictInfo();
		
		return new ModelAndView("hotelListView", model);
	}
	
	/** 
	 * Description :  跳转到酒店form页面
	 * Create Date: 2010-12-7 上午09:18:16 by guoyr  Modified Date: 2010-12-7上午09:18:16 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request)
			throws Exception {
		Map<String, Object> model = hotelService.getHotelInfo(request.getParameter("objId")); 
		
		return new ModelAndView("hotelFormView", model);
	}

	/** 
	 * Description :  保存酒店信息
	 * Create Date: 2010-12-7上午10:28:21 by guoyr  Modified Date: 2010-12-7上午10:28:21 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveHotel")
	public ModelAndView saveHotel(HttpServletRequest request, Hotel hotel, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String returnMessage = "success";
		
		//处理商品图片
		try {
			CommonsMultipartFile file = null;
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile");
			if(file != null && file.getSize() != 0){
				Object o=AttachmentUtil.uploadFile(request,"pictureFile");
				if(o instanceof GpcBaseObject){
					Attachment attachment = (Attachment)o;
					attachmentServiceImpl.saveAttachment((Attachment)o);
					hotel.setPicture(attachment.getObjId());
				}
			}
		} catch (Exception e) {
			returnMessage = e.getMessage();
		}
		
		if("success".equals(returnMessage)){
			hotel.setIsOff(true);
			hotelService.saveHotel(hotel);
		 }
		
		model.put(Constants.JSON_RESULT, StringUtils.string2ASCII(returnMessage));
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 跳转到酒店审核列表页面 
	 * Create Date: 2010-12-9上午10:45:02 by guoyr  Modified Date: 2010-12-9上午10:45:02 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditHotelList")
	public ModelAndView toAuditHotelList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = hotelService.getDistrictInfo();
		
		return new ModelAndView("hotelAuditListView", model);
	}

	/** 
	 * Description : 跳转到酒店信息页面 
	 * Create Date: 2010-12-9上午10:46:17 by guoyr  Modified Date: 2010-12-9上午10:46:17 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toShowView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = hotelService.getHotelInfo(request.getParameter("objId")); 

		return new ModelAndView("hotelDetailView", model);
	}
	
	/** 
	 * Description : 审核酒店信息
	 * Create Date: 2010-12-9上午10:50:08 by guoyr  Modified Date: 2010-12-9上午10:50:08 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateHotelAuditStatus")
	public ModelAndView updateHotelAuditStatus(HttpServletRequest request,String objId,String auditStatus) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		hotelService.updateHotelAuditStatus(objId, auditStatus);
		return new ModelAndView(Constants.JSON_VIEW, model);	
    }
}
