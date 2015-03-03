package com.gpcsoft.smallscale.pointmall.controller;

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
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.smallscale.pointmall.domain.Gift;
import com.gpcsoft.smallscale.pointmall.domain.GiftSeries;
import com.gpcsoft.smallscale.pointmall.domain.GiftSupplier;
import com.gpcsoft.smallscale.pointmall.service.GiftService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="giftListManagerView"
  *  url="view/smallscale/pointmall/gift_manage.jsp"
  *  
  * @gpcsoft.view value="giftListSupplierView"
  *  url="view/smallscale/pointmall/gift_manage_supplier.jsp"
  *  
  * @gpcsoft.view value="giftCreateOrUpdateView"
  *  url="view/smallscale/pointmall/gift_add.jsp"
  *  
  * @gpcsoft.view value="giftUpdateBySupplierView"
  *  url="view/smallscale/pointmall/gift_update_supplier.jsp"
  *  
  * @gpcsoft.view value="giftDetailView"
  *  url="view/smallscale/pointmall/gift_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Gift.class})
@RequestMapping("/GiftController.do")//页面请求路径,可修改
public class GiftController extends AnnotationMultiController<Gift> {

	@Autowired(required=true) @Qualifier("giftServiceImpl")
	private GiftService giftService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentServiceImpl;
	
	/** 
	 * Description :  跳转到礼物管理页面(管理员)
	 * Create Date: 2011-1-7上午10:26:52 by yucy  Modified Date: 2011-1-7上午10:26:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toGiftListbyManager")
	public ModelAndView toGiftListbyManager (HttpServletRequest request){ 
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("giftListManagerView",model);
	}
	
	/** 
	 * Description :  跳转到礼物管理页面(供应商)
	 * Create Date: 2011-1-7上午10:26:52 by yucy  Modified Date: 2011-1-7上午10:26:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toGiftListbySupplier")
	public ModelAndView toGiftListbySupplier (HttpServletRequest request){ 
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("giftListSupplierView",model);
	}
	
	/** 
	 * Description :  跳转到礼物新增页面(管理员)
	 * Create Date: 2011-1-7上午11:24:59 by yucy  Modified Date: 2011-1-7上午11:24:59 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toCreateOrUpdateGift")
	public ModelAndView toCreateOrUpdateGift (HttpServletRequest request){ 
		Map<String, Object> model = new HashMap<String, Object>();
		String objId = request.getParameter("objId");
		if(com.gpcsoft.core.utils.StringUtils.hasLength(objId)){
			Gift gift = giftService.get(objId);
			if (gift.getGoods()==null) {
				gift.setGoods(new Goods());
			}if (gift.getGiftSupplier()==null) {
				gift.setGiftSupplier(new GiftSupplier());
			}if (gift.getGiftSeries()==null) {
				gift.setGiftSeries(new GiftSeries());
			}
			
			model.put("gift",gift);
		}else{
			Gift gift = new Gift();
			gift.setGoods(new Goods());
			gift.setGiftSeries(new GiftSeries());
			gift.setGiftSupplier(new GiftSupplier());
			gift.setGiftType(SmallscaleEnum.VIRTUAL_OBJECTS);//虚拟
			model.put("gift", gift);
		}
		return new ModelAndView("giftCreateOrUpdateView",model);
	}
	
	/** 
	 * Description :  跳转到礼物修改页面(供应商)
	 * Create Date: 2011-1-7上午11:24:59 by yucy  Modified Date: 2011-1-7上午11:24:59 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toUpdateGiftBySupplier")
	public ModelAndView toUpdateGiftBySupplier (HttpServletRequest request){ 
		Map<String, Object> model = new HashMap<String, Object>();
		String objId = request.getParameter("objId");
		Gift gift = giftService.get(objId);
		if (gift.getGoods()==null) {
			gift.setGoods(new Goods());
		}if (gift.getGiftSupplier()==null) {
			gift.setGiftSupplier(new GiftSupplier());
		}
		model.put("gift",gift);
		return new ModelAndView("giftUpdateBySupplierView",model);
	}
	
	/** 
	 * Description :  跳转到礼物查看页面
	 * Create Date: 2011-1-7上午10:26:52 by yucy  Modified Date: 2011-1-7上午10:26:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toGiftDetail")
	public ModelAndView toGiftDetail(HttpServletRequest request){ 
		Map<String, Object> model = new HashMap<String, Object>();
		String objId = request.getParameter("objId");
		model.put("gift", giftService.get(objId));
		return new ModelAndView("giftDetailView",model);
	}
	
	/** 
     * Description :  保存礼品
     * Create Date: 2010-8-3下午08:47:03 by yucy  Modified Date: 2010-8-3下午08:47:03 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=saveGift")
    public ModelAndView saveGift(HttpServletRequest request,Gift gift, SessionStatus status) throws Exception {
    	String returnMessage = "success";
        Map<String, Object> model = new HashMap<String, Object>();
     	Map<String, Object> param = new HashMap<String, Object>();
     	
        String saveType = request.getParameter("saveType");
        
        String ruleStr = request.getParameter("ruleStr");//兑换规则数据字符串
        
        //附件处理
        if(request instanceof MultipartHttpServletRequest){
        	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        	CommonsMultipartFile file = null;
        	file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile") ;
        	if(file.getSize()!=0){
        		try {
        			//商品图片
        			Object o = AttachmentUtil.uploadFile(request,"pictureFile");
        			if(o instanceof GpcBaseObject){
        				Attachment attachment = (Attachment)o;
        				attachmentServiceImpl.save(attachment);
        				gift.setPicture(attachment.getObjId());
        			}
        		}catch(Exception de) {
        			returnMessage = StringUtils.string2ASCII(de.getMessage());
        		}
        	}
        }
        
        param.put("ruleStr", ruleStr);//兑换规则数据字符串
        param.put("gift", gift);
        param.put("saveType", saveType);
        
        gift.setGiftSeries(gift.getGiftSeries()==null||gift.getGiftSeries().getObjId()==null?null:gift.getGiftSeries());
        gift.setGoods(gift.getGoods()==null||gift.getGoods().getObjId()==null?null:gift.getGoods());
        gift.setGiftSupplier( gift.getGiftSupplier()==null||gift.getGiftSupplier().getObjId()==null?null:gift.getGiftSupplier());
        
        gift = giftService.saveGift(param);
        
        status.setComplete();

        model.put(Constants.JSON_RESULT,returnMessage);
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  设置礼品为推荐礼品
     * Create Date: 2011-1-10下午11:15:46 by yucy  Modified Date: 2011-1-10下午11:15:46 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=updateGiftReCommond")
    public ModelAndView updateGiftReCommond(String objIds[], HttpServletRequest request) throws Exception {
     	Map<String, Object> param = new HashMap<String, Object>();
     	param.put("objIds", objIds);
     	param.put("isRecommended", request.getParameter("isRecommended"));
    	giftService.updateGiftReCommond(param);
    	return new ModelAndView(Constants.JSON_VIEW,new HashMap<String, Object>() );
    }

	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request)throws Exception {
		if("supplier".equals(request.getParameter("operateOrg"))){
			query.getQueryParam().and(new QueryParam("giftSupplier.supplierId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()));
		}
		return query;
	}
    
	
}