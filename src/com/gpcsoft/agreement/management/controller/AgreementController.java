package com.gpcsoft.agreement.management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.management.domain.Agreement;
import com.gpcsoft.agreement.management.domain.AgreementGoodsclass;
import com.gpcsoft.agreement.management.domain.Period;
import com.gpcsoft.agreement.management.service.AgreementGoodsService;
import com.gpcsoft.agreement.management.service.AgreementGoodsclassService;
import com.gpcsoft.agreement.management.service.AgreementService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
  * @gpcsoft.view value="agreementFormView"
  *  url="view/agreement/mamagement/purchase_agreement_form.jsp"
  * @gpcsoft.view value="agreementTreeFormView"
  *  url="view/agreement/management/agreementTreeForm.jsp"
  * @gpcsoft.view value="agreementListView"
  *  url="view/agreement/mamagement/purchase_agreement_list.jsp"
  * @gpcsoft.view value="agreementDetailView"
  *  url="view/agreement/management/agreementDetail.jsp"
  * @gpcsoft.view value="toChooseClassBrandView"
  *  url="view/agreement/mamagement/choose_category_brand.jsp"
  * @gpcsoft.view value="toChooseGoods"
  *  url="view/agreement/mamagement/choose_by_category_goods.jsp"
  * @gpcsoft.view value="toAuthorizedVenderView"
  *  url="view/agreement/mamagement/authorized_vender.jsp"
  * @gpcsoft.view value="toAuthorizedGoodsOrClassView"
  *  url="view/agreement/mamagement/authorized_category_goods.jsp"
  * @gpcsoft.view value="toVenderFormView"
  *  url="view/agreement/mamagement/vender_qualification_form.jsp"
  *  
  * @gpcsoft.view value="toVenderQualityListView"
  *  url="view/agreement/mamagement/vender_qualification_list.jsp"
  *  
  * @gpcsoft.view value="toAgreementGoodsPriceListView"
  *  url="view/agreement/mamagement/agreement_goods_list.jsp"
  *  
  * @gpcsoft.view value="toAgreementPreViewView"
  *  url="view/agreement/mamagement/purchase_agreement_preview.jsp"
  */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Agreement.class})
@RequestMapping("/AgreementController.do")//页面请求路径,可修改
public class AgreementController extends AnnotationMultiController<Agreement> {

	@Autowired(required=true) @Qualifier("agreementServiceImpl")
	private AgreementService agreementService;
	
/*	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;*/
	
	@Autowired(required=true) @Qualifier("agreementGoodsclassServiceImpl")
	private AgreementGoodsclassService agreementGoodsclassService;
	
	@Autowired(required=true) @Qualifier("agreementGoodsServiceImpl")
	private AgreementGoodsService agreementGoodsService;
	
	/** 
	 * Description :  保存采购协议
	 * Create Date: 2010-4-19下午01:22:29 by yucy  Modified Date: 2010-4-19下午01:22:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveAgreement")   
	public ModelAndView saveAgreement(HttpServletRequest request, Agreement agreement) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		agreementService.saveAgreement(agreement);
		model.put("objId",agreement.getObjId());
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description : 跳转协议分类品牌页面 或跳转单品挑选页面 或跳回列表（多种页面）
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toChooseClassBrand")   
	public ModelAndView toChooseClassBrand(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		String toView = request.getParameter("toView");//选择性跳转 toChooseGoods  toChooseClassBrandView
		String objId = request.getParameter("objId");
		Agreement agreement = agreementService.get(objId);
		model.put("agreement", agreement);
		return new ModelAndView(toView, model);
    }
	
	/** 
	 * Description :跳转供应商资格管理
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toVenderQualityList")   
	public ModelAndView toVenderQualityList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		return new ModelAndView("toVenderQualityListView", model);
    }
	
	/** 
	 * Description :跳转协议商品价格管理
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAgreementGoodsPriceList")   
	public ModelAndView toAgreementGoodsPriceList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		return new ModelAndView("toAgreementGoodsPriceListView", model);
    }
	
	/** 
	 * Description : 删除agreement
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeAgreement")   
	public ModelAndView removeAgreement(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		String[] agreementIds = request.getParameter("agreementIds").split(",");
		agreementService.remove(agreementIds, Agreement.class);
		model.put(Constants.JSON_RESULT, "删除成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description : 跳转新增协议
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toNewAgreement")   
	public ModelAndView toNewAgreement(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Agreement agreement = null;
		if(null!=request.getParameter("objId")&&!"".equals(request.getParameter("objId"))){
			agreement = agreementService.get(request.getParameter("objId"));
		}else {
			agreement = new Agreement();
			OrgInfo org = new OrgInfo();
			agreement.setOrg(org);
			OrgInfo supplier = new OrgInfo();
			agreement.setSupplier(supplier);
			Period period = new Period();
			agreement.setPeriod(period);
		}
		model.put("agreement", agreement);
		return new ModelAndView((new StringBuilder(String.valueOf(ClassUtils.getShortNameAsProperty(getPersistClass())))).append("FormView").toString(), model);
    }
	
	
	/** 
	 * Description :  选择代理机构
	 * Create Date: 2010-5-17下午03:14:10 by yucy  Modified Date: 2010-5-17下午03:14:10 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=chooseAgent")
	public ModelAndView chooseAgent(HttpServletRequest request) throws Exception {
		OrgInfoService orgInfoService = (OrgInfoService) FrameBeanFactory.getBean("orgInfoServiceImpl");
		String queryColumns = makeQueryColumns(request);//接收前台的指定列名
		QueryObject query = QueryWebUtils.getQuery(request, OrgInfo.class);
		initQueryColums(request, query);
		query.getQueryParam().and(new QueryParam("agencyId",QueryParam.OPERATOR_NE,null));//=等于的where条件    
		Page page = prePage(request);
		Page pageData = orgInfoService.findByQuery(query, true,page.getStart(), page.getPageSize());
		pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), getEnumColumns()));
		Map<String, Object> model = new HashMap<String, Object>();
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	
	/** 
	 * Description :  选择供应商
	 * Create Date: 2010-5-17下午03:15:28 by yucy  Modified Date: 2010-5-17下午03:15:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=chooseSupplier")
	public ModelAndView chooseSupplier(HttpServletRequest request) throws Exception {
		OrgInfoService orgInfoService = (OrgInfoService) FrameBeanFactory.getBean("orgInfoServiceImpl");
		String queryColumns = makeQueryColumns(request);//接收前台的指定列名
		QueryObject query = QueryWebUtils.getQuery(request, OrgInfo.class);
		initQueryColums(request, query);
		query.getQueryParam().and(new QueryParam("supplierId",QueryParam.OPERATOR_NE,null));
		
		Page page = prePage(request);
		Page pageData = orgInfoService.findByQuery(query, true,page.getStart(), page.getPageSize());
		pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), getEnumColumns()));
		Map<String, Object> model = new HashMap<String, Object>();
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :取得供应商可以录入行情的区间  
	 * Create Date: 2010-9-26下午05:03:11 by yucy  Modified Date: 2010-9-26下午05:03:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getDistrictByOrg")
	public ModelAndView getDistrictByOrg(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("goodsId", request.getParameter("goodsId"));
		param.put("orgId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		List<String[]> districtList  = agreementService.getDistrictByOrg(param);
		model.put("districtList", districtList);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
    @SuppressWarnings("unchecked")
    @RequestMapping(params = "method=getVenderlist")
	public ModelAndView getVenderlist(HttpServletRequest request)throws Exception{
	    Map model = new HashMap();
        String queryColumns = makeQueryColumns(request);
        QueryObject query = QueryWebUtils.getQuery(request, getPersistClass());
        initQueryColums(request, query);
        Page page = prePage(request);
        query.getQueryParam().and(new QueryParam("supplier.objId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()));
        Page pageData = baseGenericService.findByQuery(query, true, page.getStart(), page.getPageSize());
        String alias = request.getParameter("alias");
        pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), alias != null ? alias.split(",") : null, getPersistClass(), new Map[] {
            getEnumColumns()
        }));
	    endPage(model, pageData, request);
	    return new ModelAndView("jsonView", model);
    }
    
    /** 
     * Description :  跳转到协议预览
     * Create Date: 2011-12-6下午04:01:46 by yucy  Modified Date: 2011-12-6下午04:01:46 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toAgreementPreView")
	public ModelAndView toAgreementPreView(HttpServletRequest request)throws Exception{
	    Map<String,Object> model = new HashMap<String,Object>();
	    String agreementId = request.getParameter("objId");
	    //取协议信息
		model.put("agreement",agreementService.get(agreementId));
		
		//取协议分类信息
		List<Object[]> agreementClassGoodsList = new ArrayList<Object[]>();
		List<AgreementGoodsclass> AgreementGoodsclassList = agreementGoodsclassService.getGoodsClassWithTips(agreementId);
	    Map<String,Object> param = new HashMap<String,Object>();
		for(AgreementGoodsclass agreementGoodsclass: AgreementGoodsclassList){
			param.clear();
			param.put("agreementClassId", agreementGoodsclass.getObjId());
			agreementClassGoodsList.add( new Object[]{agreementGoodsclass , agreementGoodsService.getAgreementGoodsListByParam(param) } );
		}
		model.put("agreementClassGoodsList", agreementClassGoodsList );
		
		//取单品
		param.clear();
		param.put( "agreementId", agreementId );
		model.put("agreementGoodsList",agreementGoodsService.getAgreementGoodsListByParam(param));
	    return new ModelAndView("toAgreementPreViewView", model);
    }
}
