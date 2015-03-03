package com.gpcsoft.smallscale.pointmall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.smallscale.pointmall.service.GiftSeriesService;
import com.gpcsoft.smallscale.pointmall.domain.GiftSeries;

/**
  * @gpcsoft.view value="giftSeriesFormView"
  *  url="view/smallscale/pointmall/gift_series_form.jsp"
  * @gpcsoft.view value="giftSeriesListView"
  *  url="view/smallscale/pointmall/gift_series_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GiftSeries.class})
@RequestMapping("/GiftSeriesController.do")//页面请求路径,可修改
public class GiftSeriesController extends AnnotationMultiController<GiftSeries> {

	@Autowired(required=true) @Qualifier("giftSeriesServiceImpl")
	private GiftSeriesService giftSeriesService;
	
	/**
	 * Description :  保存礼品系列
	 * Create Date: 2011-1-7下午02:08:05 by zhaojf  Modified Date: 2011-1-7下午02:08:05 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveGiftSeries")
	public ModelAndView saveGiftSeries(HttpServletRequest request,SessionStatus status) throws Exception{
		Map <String, Object> model = new HashMap<String, Object> ();
		
		GiftSeries giftSeries = JsonUtils.json2Bean(JsonUtils.getJSONString(request.getParameter("giftSeriesJson")), GiftSeries.class);
		
		giftSeries = giftSeriesService.saveGiftSeries(giftSeries);//保存
		
		model.put("objId", giftSeries.getObjId());
		model.put("parentId", null != giftSeries.getParent() ? giftSeries.getParent().getObjId() : "");		
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  判断礼品系列名称在同一父节点下是否唯一
	 * Create Date: 2011-1-10上午10:24:47 by zhaojf  Modified Date: 2011-1-10上午10:24:47 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=isUnique")
	public ModelAndView isUnique(HttpServletRequest request, String giftSeriesName, String objId, String parentId ) {
		Map <String, Object> model = new HashMap<String, Object> ();
		
		giftSeriesName = StringUtils.ascii2Native(giftSeriesName);//礼品系列名称
		
		model.put("isUnique", giftSeriesService.isUnique(giftSeriesName, objId, parentId));//判断是否唯一
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
    @Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(null != request.getParameter("objId") && !"".equals(request.getParameter("objId"))) {
			GiftSeries giftSeries  = giftSeriesService.get(request.getParameter("objId"));
			model.put("giftSeries", giftSeries);
		}else {
			model.put("giftSeries", new GiftSeries());
		}
		
		return new ModelAndView("giftSeriesFormView", model);
	}
    
    /**
     * Description :  删除
     * Create Date: 2011-1-10下午05:08:26 by zhaojf  Modified Date: 2011-1-10下午05:08:26 by zhaojf
     * @param   
     * @return  
     * @Exception
     */
	@RequestMapping(params = "method=removeGiftSeries")
	public ModelAndView removeGiftSeries(String objId) {
		Map<String, Object> model = new HashMap<String, Object>();				
		
		model.put("isHasGift", giftSeriesService.removeGiftSeries(objId));
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
	/**
	 * Description :  上下移动商品参数时修改原行和目标行的排序
	 * Create Date: 2011-1-24下午04:24:29 by zhaojf  Modified Date: 2011-1-24下午04:24:29 by zhaojf
	 * @param   sourceObjId 原行的排序   targetObjId 目标行的排序
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=updateSort")
	public ModelAndView updateSort(HttpServletRequest request) {
		String sourceObjId = request.getParameter("sourceObjId");
		String targetObjId = request.getParameter("targetObjId");
		giftSeriesService.updateSort(sourceObjId, targetObjId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
}
