package com.gpcsoft.goods.goodsprice.controller;

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

import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsprice.domain.AttentionPrice;
import com.gpcsoft.goods.goodsprice.service.AttentionPriceService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.baseData.domain.District;

/**
 *  @gpcsoft.view value="myAttentionListView"
 *  url="view/goods/goodsprice/my_attention_list.jsp"
 *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={AttentionPrice.class})
@RequestMapping("/AttentionPriceController.do")//页面请求路径,可修改
public class AttentionPriceController extends AnnotationMultiController<AttentionPrice> {
	@Autowired(required=true) @Qualifier("attentionPriceServiceImpl")
	private AttentionPriceService attentionPriceService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;

	/** 
	 * Description :  加入我的关注
	 * Create Date: 2010-11-2下午02:34:57 by yucy  Modified Date: 2010-11-2下午02:34:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveAttention")
	public ModelAndView saveAttention(HttpServletRequest request ,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String,Object>();
		
		Map<String, Object> param = new HashMap<String,Object>();

		//查看是否添加过关注
		param.put("goodsId", request.getParameter("goodsId"));
		param.put("orgId", orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true).getObjId());
		if(!attentionPriceService.hasAttention(param)){
			
			Object[] cuttentCity = attentionPriceService.getCurrentAttentionCity(param);
			
			AttentionPrice attentionPrice  = new AttentionPrice();
			Goods goods = new Goods();
			goods.setObjId(request.getParameter("goodsId"));
			attentionPrice.setGoods(goods);
			attentionPrice.setAttentionOrg(orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true));
			
			District district =  new District();
			district.setObjId(cuttentCity==null?messageSource.getMessage("eps.goods.goodsprice.attentionprice.defultdistrictid"):(String)cuttentCity[0]);//设置默认地区Id
			
			attentionPrice.setDistrict(district);
			attentionPriceService.save(attentionPrice);
			status.setComplete();
		}else{
			model.put(Constants.JSON_RESULT, "hasIt");
		}
		
		return new  ModelAndView(Constants.JSON_VIEW ,model );
	}
	
	/** 
	 * Description :  跳转到我关注的列表
	 * Create Date: 2010-11-2下午04:04:10 by yucy  Modified Date: 2010-11-2下午04:04:10 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toMyAttentionList")
	public ModelAndView toMyAttentionList(HttpServletRequest request ,SessionStatus status) throws Exception  {
		Map<String, Object> model = new HashMap<String,Object>();
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("orgId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		Object[] cuttentCity = attentionPriceService.getCurrentAttentionCity(param);
		//当前城市的值
		if(cuttentCity==null){
			param.put("districtId",request.getParameter("defultDistrict"));
			//attentionPriceService.changerAttentionCity(param);
		}
		model.put("cuttentCity", cuttentCity);
		model.put("orgId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		model.put("currentUserId", AuthenticationHelper.getCurrentUser(true).getObjId());
		return new  ModelAndView("myAttentionListView" ,model );
	}
	
	/** 
	 * Description :  改变关注区域 
	 * Create Date: 2010-11-4下午01:47:21 by yucy  Modified Date: 2010-11-4下午01:47:21 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=changerAttentionCity")
	public ModelAndView changerAttentionCity(HttpServletRequest request ,SessionStatus status) throws Exception  {
		Map<String, Object> model = new HashMap<String,Object>();
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("districtId", request.getParameter("districtId"));
		attentionPriceService.changerAttentionCity(param);
		return new  ModelAndView(Constants.JSON_VIEW ,model );
	}
}
