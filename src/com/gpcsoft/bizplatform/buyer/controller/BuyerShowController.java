package com.gpcsoft.bizplatform.buyer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.industry.domain.Industry;
import com.gpcsoft.bizplatform.base.industry.service.IndustryService;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.buyer.service.BuyerService;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.EvaluateService;
import com.gpcsoft.bizplatform.organization.service.IllegalRecService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.service.ShangquanhuiyuanPrivilegeService;

/**
  * @gpcsoft.view value="buyerShowListView"
  *  url="view/bizplatform/buyers/showbuyer/show_buyer_list.jsp"
  * @gpcsoft.view value="buyerShowForListView"
  *  url="view/bizplatform/buyers/showbuyer/buyer_list_div_l.jsp"
  * @gpcsoft.view value="buyerShowInfoView"
  *  url="view/bizplatform/buyers/showbuyer/show_buyer_detail.jsp"
  * @gpcsoft.view value="buyerRecommendView"
  *  url="view/bizplatform/buyers/showbuyer/buyer_recommend_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/BuyerShowController.do")//页面请求路径,可修改
public class BuyerShowController extends AnnotationMultiController<GpcBaseObject> {

	@Autowired(required=true) @Qualifier("buyerServiceImpl")
	private BuyerService buyerService;
	
	@Autowired(required=true) @Qualifier("evaluateServiceImpl")
	private EvaluateService evaluateService;
	
	@Autowired(required=true) @Qualifier("industryServiceImpl")
	private IndustryService industryService;
	
	@Autowired(required=true) @Qualifier("shangquanhuiyuanPrivilegeServiceImpl")
	private ShangquanhuiyuanPrivilegeService shangquanhuiyuanPrivilegeService;
	
	@Autowired(required=true) @Qualifier("illegalRecServiceImpl")
	private IllegalRecService illegalRecService;
	
    /** 
     * Description :  跳转到采购人展示的二级页面，也就是列表页面
     * Create Date: 2010-7-27上午10:02:32 by liangxj  Modified Date: 2010-7-27上午10:02:32 by liangxj
     * @param   categoryId 品目id categoryCode 品目编号
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toBuyerList")
    public ModelAndView toBuyerList(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
    	
    	/** districtId和unitType可能来自与详情页面的参数；evalSort和keyWord来自与首页的参数 */
    	String districtLevel = request.getParameter("districtLevel");
    	String districtId = request.getParameter("districtId");
    	String evalSort = request.getParameter("evalSort");
    	String unitType = request.getParameter("unitType");
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
    	String validSort = request.getParameter("validSort");
    	
    	Short level = 0;
    	if(districtLevel != null && !districtLevel.equals("")) {
    		level = new Short(districtLevel);
    	}
    	
    	paramsMap.put("districtId", districtId);
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(keyWord));
    	paramsMap.put("evalSort", evalSort);
    	paramsMap.put("unitType", unitType);
    	paramsMap.put("validSort", validSort);
    	
    	
    	/** 获得采购人机构类型*/
    	List<String[]> unitTypeList = buyerService.getUnitTypeListShow(keyWord);
    	
    	for (Object obj : unitTypeList) {
    		Object[] aType = (Object[])obj;
			for (String type : enumService.getEnum(OrganizationEnum.ORGINFO_ENTPRPT)) {
				String[] temp = type.split(":");
				if(((String)aType[0]).equals(temp[0])) {
					aType[1] = temp[1];
				}
			}
		}
    	model.put("unitTypeList", unitTypeList);
    	
    	/** 取区域信息*/
    	List<String[]> listDistrict = buyerService.getDistrictListForShow(null, unitType,level,keyWord);
    	model.put("districtList", listDistrict);
    	
    	/** 获取行业信息*/
    	List<Industry> listIndustry = industryService.getIndustryByLevel(new Short("1"));  //获得顶级行业
    	model.put("industryList", listIndustry);
    	
    	/** 取采购人信息*/
    	Page<Buyer> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Buyer> pageData = buyerService.getBuyerListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
    	/** 回设高亮参数或首页参数 */
    	model.put("districtId", districtId);
    	model.put("districtLevel", districtLevel);
    	model.put("unitType", unitType);
    	model.put("keyWord",StringUtils.returnIngoreStr(keyWord));
    	model.put("evalSort", evalSort);
    	model.put("validSort", validSort);
    	
        return new ModelAndView("buyerShowListView",model);
    }
    
    
	/** 
	 * Description :  获取采购人展示的列表数据
	 * Create Date: 2010-7-27上午11:46:10 by liangxj  Modified Date: 2010-7-27上午11:46:10 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getBuyerForList")   
	public ModelAndView getBuyerForList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		//企业性质和区域
    	paramsMap.put("districtId", request.getParameter("districtId"));
    	paramsMap.put("unitType", request.getParameter("unitType"));
    	
    	//排序
    	paramsMap.put("evalSort", request.getParameter("evalSort"));
    	paramsMap.put("validSort", request.getParameter("validSort"));
    	paramsMap.put("dealMoneySort", request.getParameter("dealMoneySort")); //交易金额
    	
    	//高级搜索条件
    	paramsMap.put("belongIndustry", request.getParameter("belongIndustry"));
    	paramsMap.put("dealTime", request.getParameter("dealTime"));
    	paramsMap.put("moneyTotleLeft", request.getParameter("moneyTotleLeft"));
    	paramsMap.put("moneyTotleRight", request.getParameter("moneyTotleRight"));
    	
    	String keyWord = request.getParameter("keyWord");
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    
    	
    	/** 取采购人信息 */
    	Page<Buyer> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Buyer> pageData = buyerService.getBuyerListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
		return new ModelAndView("buyerShowForListView",model);
    }
	
	/** 
	 * Description :  根据企业性质获得区域的展示信息集合
	 * Create Date: 2010-8-3上午11:44:58 by liangxj  Modified Date: 2010-8-3上午11:44:58 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getDistrictListShowByUnitType")
	public ModelAndView getDistrictListShowByUnitType(String unitType,String districtId,String districtLevel,HttpServletRequest request) throws Exception {
		String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
		
		Short level = 0;
    	if(districtLevel != null && !districtLevel.equals("")) {
    		level = new Short(districtLevel);
    	}
    	
    	Map<String,Object> model = new HashMap<String,Object>();
    	List<String[]> district = buyerService.getDistrictListForShow(districtId, unitType,level,keyWord);
		
    	model.put("result", district);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 获得推荐采购人 
	 * Create Date: 2010-8-20上午11:58:07 by liangxj  Modified Date: 2010-8-20上午11:58:07 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getRecommendBuyer")
	public ModelAndView getRecommendBuyer(HttpServletRequest request, String viewName ) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		/** keyWord来自与首页的参数 */
		String keyWord = request.getParameter("keyWord");
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	paramsMap.put("unitType", request.getParameter("unitType"));
		paramsMap.put("evalSort", "desc");
		
		/** 取采购人信息*/
    	Page<Buyer> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Buyer> pageData = buyerService.getBuyerListForShow(page,paramsMap);
    	model.put("buyerList",pageData.getData());

		return new ModelAndView("buyerRecommendView", model);
	}
	
	/** 
	 * Description :  获得采购人的详细信息，包含资质、成功案例、评价
	 * Create Date: 2010-8-4下午04:28:52 by liangxj  Modified Date: 2010-8-4下午04:28:52 by liangxj
	 * @param
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getBuyerInfo")
	public ModelAndView getBuyerInfo(String objId ,HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		
		//查询采购人
		Buyer buyer = buyerService.getBuyerAllInfo(objId);
		model.put("buyer", buyer);
		
		//导航参数
		model.put("unitType", request.getParameter("unitType"));
		model.put("districtId", request.getParameter("districtId"));
		
		if(request.getParameter("districtName")!=null&&!"".equals(request.getParameter("districtName"))){
			model.put("districtName", StringUtils.ascii2Native(request.getParameter("districtName")));
		}

		//查询评价详细信息 
		List<String[]> quotaDetailList = (List<String[]>)evaluateService.getQuataScoreDetail(buyer.getOrgInfo(),"02");
		model.put("quotaDetailList", quotaDetailList);
		
		//获取违法纪录
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("orgInfoId", buyer.getOrgInfo().getObjId());
    	paramsMap.put("isShow", true);
    	model.put("illegalRecList", illegalRecService.getIllegalRec(paramsMap));
		
		//详细页面特定显示的tab
    	model.put("tabId", request.getParameter("tabId"));
    	
    	//是否显示联系方式
    	if(AuthenticationHelper.getCurrentUser() != null  && AuthenticationHelper.getCurrentUser().getOrgInfo()!=null) {
    		model.put("isShowContact", shangquanhuiyuanPrivilegeService.getIsShowContact((AuthenticationHelper.getCurrentUser().getOrgInfo()).getObjId(), buyer.getOrgInfo().getObjId()));
    	}
    	
		return new ModelAndView("buyerShowInfoView", model);
	}
}
