package com.gpcsoft.smallscale.pointmall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.smallscale.pointmall.service.GiftSupplierService;
import com.gpcsoft.smallscale.pointmall.domain.GiftSupplier;

/**
  * @gpcsoft.view value="giftSupplierListView"
  *  url="view/smallscale/pointmall/gift_supplier_list.jsp"
  * @gpcsoft.view value="toGiftSupplierAddView"
  *  url="view/smallscale/pointmall/gift_supplier_add.jsp"
  * @gpcsoft.view value="toGiftSupplierModifyView"
  *  url="view/smallscale/pointmall/gift_supplier_modify.jsp"
  * @gpcsoft.view value="viewGiftSupplierView"
  *  url="view/smallscale/pointmall/gift_supplier_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GiftSupplier.class})
@RequestMapping("/GiftSupplierController.do")//页面请求路径,可修改
public class GiftSupplierController extends AnnotationMultiController<GiftSupplier> {

	@Autowired(required=true) @Qualifier("giftSupplierServiceImpl")
	private GiftSupplierService giftSupplierService;
	
	/**
	 * Description :  跳转至新增/修改页面
	 * Create Date: 2011-1-12上午11:11:13 by zhaojf  Modified Date: 2011-1-12上午11:11:13 by zhaojf
	 * @param   type=newAdd 新增, type=modify 修改
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toGiftSupplierAdd")
	public ModelAndView toGiftSupplierAdd(String objId,String type) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		String returnStrView = "toGiftSupplierAddView";
		
		//获取被禁用的礼品供货商的id(多个id用逗号分开)
		String filterVelue = giftSupplierService.getObjIdsOfNotUsed();
		model.put("filterVelue", filterVelue);
		
		//修改
		if(type.equals("modify")){
			GiftSupplier giftSupplier = giftSupplierService.get(objId);
			model.put("giftSupplier", giftSupplier);
			returnStrView = "toGiftSupplierModifyView";
		}
		
		model.put("saveType", type);
		return new ModelAndView(returnStrView,model);
	}
	
	/**
	 * Description :  保存礼品供货商
	 * Create Date: 2011-1-12上午11:38:24 by zhaojf  Modified Date: 2011-1-12上午11:38:24 by zhaojf
	 * @param   saveType=newAdd 新增, saveType=modify 修改
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveGiftSupplier")
	public ModelAndView saveGiftSupplier(GiftSupplier giftSupplier,String saveType,SessionStatus status) throws Exception{
		
		giftSupplierService.saveGiftSupplier(giftSupplier,saveType);
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * Description :  查看礼品供货商详情
	 * Create Date: 2011-1-12下午02:45:14 by zhaojf  Modified Date: 2011-1-12下午02:45:14 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=viewGiftSupplier")
	public ModelAndView viewGiftSupplier(String objId,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		GiftSupplier giftSupplier = giftSupplierService.get(objId);
		model.put("giftSupplier", giftSupplier);
		status.setComplete();
		return new ModelAndView("viewGiftSupplierView",model);
	}
	
	/**
	 * Description :  判断礼品供货商是否唯一
	 * Create Date: 2011-1-12下午04:08:39 by zhaojf  Modified Date: 2011-1-12下午04:08:39 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=isUnique")
	public ModelAndView isUnique(HttpServletRequest request, String giftSupplierName) throws Exception{
		Map <String, Object> model = new HashMap<String, Object> ();
		
		giftSupplierName = StringUtils.ascii2Native(giftSupplierName);//礼品供货商名称
		
		model.put("isUnique", giftSupplierService.isUnique(giftSupplierName));//判断是否唯一
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description :  删除
	 * Create Date: 2011-1-12下午04:10:03 by zhaojf  Modified Date: 2011-1-12下午04:10:03 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=removeGiftSupplier")
	public ModelAndView removeGiftSupplier(String objId,SessionStatus status) throws Exception{
		Map <String, Object> model = new HashMap<String, Object> ();
		
		model.put("isHasGift", giftSupplierService.removeGiftSupplier(objId));
		
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description :  禁用或启用
	 * Create Date: 2011-1-12下午05:08:01 by zhaojf  Modified Date: 2011-1-12下午05:08:01 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=modifyIsUsedStatus")
	public ModelAndView modifyIsUsedStatus(String objId,boolean isUsed,SessionStatus status) throws Exception{
		Map <String, Object> model = new HashMap<String, Object> ();
		
		giftSupplierService.modifyIsUsedStatus(objId, isUsed);
		
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
