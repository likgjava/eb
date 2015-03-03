package com.gpcsoft.goods.goods.controller;

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

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsGift;
import com.gpcsoft.goods.goods.service.GoodsGiftService;
import com.gpcsoft.goods.goods.service.GoodsService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
 * @gpcsoft.view value="goodsGiftListView"
 *  url="view/goods/goods/goodsmng/goods_gift_list.jsp"
 *  
  * @gpcsoft.view value="goodsGiftFormView"
  *  url="view/goods/goods/goodsmng/goods_gift_form.jsp"
  *  
  * @gpcsoft.view value="goodsGiftDetailView"
  *  url="view/goods/goods/goodsmng/goods_gift_detail.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsGift.class})
@RequestMapping("/GoodsGiftController.do")//页面请求路径,可修改
public class GoodsGiftController extends AnnotationMultiController<GoodsGift> {

	@Autowired(required=true) @Qualifier("goodsGiftServiceImpl")
	private GoodsGiftService goodsGiftService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("goodsServiceImpl")
	private GoodsService goodsService;
	
	/** 
	 * Description :  跳转到商品礼包列表页面
	 * Create Date: 2011-1-7下午01:47:34 by likg  Modified Date: 2011-1-7下午01:47:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsGiftListView")
	public ModelAndView toGoodsGiftListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		String goodsId = request.getParameter("goodsId");
		Goods goods = goodsService.get(goodsId);
		
		model.put("goods", goods);
		model.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()); //当前机构
		
		return new ModelAndView("goodsGiftListView", model);
	}
	
	/** 
	 * Description :  跳转到新增或修改礼包页面
	 * Create Date: 2011-1-7下午01:47:34 by likg  Modified Date: 2011-1-7下午01:47:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateGiftView")
	public ModelAndView toCreateOrUpdateGiftView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		GoodsGift goodsGift = new GoodsGift();
		
		String goodsGiftId = request.getParameter("goodsGiftId");
		if(StringUtils.hasLength(goodsGiftId)){
			goodsGift = goodsGiftService.get(goodsGiftId);
		}
		
		model.put("goodsGift", goodsGift);
		
		return new ModelAndView("goodsGiftFormView", model);
	}
	
	/** 
	 * Description :  保存新增或修改的礼包信息
	 * Create Date: 2011-1-7下午02:28:48 by likg  Modified Date: 2011-1-7下午02:28:48 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveGoodsGift")
	public ModelAndView saveGoodsGift(GoodsGift goodsGift, HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//处理礼包图片
		CommonsMultipartFile file = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
	    file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile");
		if(file != null && file.getSize() != 0){
			Object o=AttachmentUtil.uploadFile(request,"pictureFile");
			if(o instanceof GpcBaseObject){
				Attachment attachment = (Attachment)o;
				attachmentService.saveAttachment((Attachment)o);
				goodsGift.setGiftPicture(attachment.getObjId());
			}
		}
		
		//保存礼包信息
		goodsGiftService.saveGoodsGift(goodsGift);
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到商品礼包详情页面
	 * Create Date: 2011-1-7下午04:01:37 by likg  Modified Date: 2011-1-7下午04:01:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsGiftDetailView")
	public ModelAndView toGoodsGiftDetailView(String goodsGiftId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		GoodsGift goodsGift = goodsGiftService.get(goodsGiftId);
		model.put("goodsGift", goodsGift);
		
		return new ModelAndView("goodsGiftDetailView", model);
	}
	
}
