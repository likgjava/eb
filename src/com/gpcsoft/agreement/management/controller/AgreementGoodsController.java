package com.gpcsoft.agreement.management.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.management.domain.Agreement;
import com.gpcsoft.agreement.management.domain.AgreementGoods;
import com.gpcsoft.agreement.management.domain.AgreementGoodsclass;
import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.agreement.management.service.AgreementGoodsService;
import com.gpcsoft.agreement.management.service.AgreementGoodsclassService;
import com.gpcsoft.agreement.management.service.AgreementService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

/**
  * @gpcsoft.view value="agreementGoodsFormView"
  *  url="view/agreement/management/agreementGoodsForm.jsp"
  * @gpcsoft.view value="agreementGoodsTreeFormView"
  *  url="view/agreement/management/agreementGoodsTreeForm.jsp"
  * @gpcsoft.view value="agreementGoodsListView"
  *  url="view/agreement/management/agreementGoodsList.jsp"
  * @gpcsoft.view value="agreementGoodsDetailView"
  *  url="view/agreement/management/agreementGoodsDetail.jsp"
  *  
  * @gpcsoft.view value="toPriceGoodsListView"
  *  url="view/goods/goodsprice/price_goods_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={AgreementGoods.class})
@RequestMapping("/AgreementGoodsController.do")//页面请求路径,可修改
public class AgreementGoodsController extends AnnotationMultiController<AgreementGoods> {

	@Autowired(required=true) @Qualifier("agreementGoodsServiceImpl")
	private AgreementGoodsService agreementGoodsService;
	
	/** 
	 * Description : 保存协议商品(不涉及协议商品的归属:一级协议，二级协议，协议分类等)
	 * Create Date: 2010-4-20上午01:15:36 by yucy  Modified Date: 2010-4-20上午01:15:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveAgreementGoods")   
	public ModelAndView saveAgreementGoods(HttpServletRequest request, AgreementGoods agreeGoods) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(null!=agreeGoods.getObjId()){
			AgreementGoods prisistAgreeGoods = agreementGoodsService.get(agreeGoods.getObjId());
			BeanUtils.copyPropertiesFilterEmpty(prisistAgreeGoods, agreeGoods);
			agreementGoodsService.save(prisistAgreeGoods);
			model.put(Constants.JSON_RESULT,"添加成功!");
		}else {
			agreementGoodsService.save(agreeGoods);
			model.put(Constants.JSON_RESULT,"添加成功!");
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description : 保存协议商品(不涉及协议商品的归属:一级协议，二级协议，协议分类等)
	 * Create Date: 2010-4-20上午01:15:36 by yucy  Modified Date: 2010-4-20上午01:15:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveAgreementGoodsByCopy")   
	public ModelAndView saveAgreementGoodsByCopy(HttpServletRequest request, String agreeGoods) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		AgreementGoods agreementGoodsCurrent = JsonUtils.json2Bean(agreeGoods, AgreementGoods.class);
		AgreementGoods agreementGoodsBefore = agreementGoodsService.get(agreementGoodsCurrent.getObjId());
		BeanUtils.copyPropertiesFilterEmptyNew(agreementGoodsBefore, agreementGoodsCurrent);
		agreementGoodsService.save(agreementGoodsBefore);
		model.put(Constants.JSON_RESULT,"添加成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	 /** 
     * Description :  授权未授权的商品
     * Create Date: 2010-4-26下午08:07:51 by yucy  Modified Date: 2010-4-26下午08:07:51 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(params = "method=listGoods")   
	public ModelAndView listGoods(HttpServletRequest request)throws Exception{
	    Map model = new HashMap();
	    
		String queryColumns = makeQueryColumns(request);
		QueryObject query = QueryWebUtils.getQuery(request, this.getPersistClass());
		initQueryColums(request, query);
		
		Page page = prePage(request);
		Page pageData = agreementGoodsService.findByQuery(query, true,page.getStart(), page.getPageSize());
		
		List newRowData = new ArrayList();
		for (int i= 0;i<pageData.getData().size();i++) {
			
			List newColData = new ArrayList();
			Object[] objects =  (Object[])pageData.getData().get(i);
			
			String  goodsId = (String)objects[5];

			//参数
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("goodsId", goodsId);
			params.put("agreementId", request.getParameter("agreement.objId"));

			List<AgreementSecond> agreementSecondList = (List)agreementGoodsService.getAuthorizedGoods(params);
			
			//填数据
			newColData.add(pageData.getData().get(i));
			newColData.add(agreementSecondList);
			
			newRowData.add(newColData);
		}
		
		pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), getEnumColumns()));
		model.put("appendData", newRowData);
	    endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
    
    /** 
     * Description :  添加商品库中的新的商品
     * Create Date: 2010-4-28上午12:55:18 by yucy  Modified Date: 2010-4-28上午12:55:18 by yucy
     * @param   
     * @return  
     * @Exception   
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=addAgreementNewGoods")   
    public ModelAndView addAgreementNewGoods(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> params = request.getParameterMap();
		
		AgreementGoodsclassService  agreementGoodsclassService =  (AgreementGoodsclassService) FrameBeanFactory.getBean("agreementGoodsclassServiceImpl");
		
		AgreementService  agreementService =  (AgreementService) FrameBeanFactory.getBean("agreementServiceImpl");

		
		//商品库商品ids
		String[] goodsIds =  ((String[])params.get("goodsIds"))[0].split(",");
		String brandId = ((String[])params.get("brandId"))[0];
		String classId = ((String[])params.get("classId"))[0];
		String agreementType = ((String[])params.get("agreementType"))[0];
		String agreementClassId = ((String[])params.get("agreementClassId"))[0];
		
		AgreementGoodsclass agreementGoodsclass =  agreementGoodsclassService.get(agreementClassId);
		
		for (String goodId : goodsIds) {
			
			//循环保存
			AgreementGoods agreementGoods = new  AgreementGoods();
			
			agreementGoods.setAgreementGoodsclass(agreementGoodsclass);//设置agreementGoodsclass
			
			agreementGoods.setAgreementType(agreementType);//设agreementType
			
			Goods goods = new Goods();
			goods.setObjId(goodId);
			agreementGoods.setGoods(goods);//设goods
			
			GoodsClass goodsClass = new GoodsClass();
			goodsClass.setObjId(classId);
			agreementGoods.setGoodsClass(goodsClass);//设goodsClass
			
			GoodsBrand goodsBrand = new GoodsBrand();
			goodsBrand.setObjId(brandId);
			agreementGoods.setBrand(goodsBrand);//设goodsBrand
			
			agreementGoods.setDiscountRatio(agreementGoods.getDiscountRatio());//设置DiscountRatio
			
			agreementGoodsService.save(agreementGoods);
		}
		
		//更新Agreement  :modifyTime
		Agreement agreement = agreementGoodsclass.getAgreement();
		agreement.setModifyTime(new Date());
		agreementService.save(agreement);
		
		model.put(Constants.JSON_RESULT,"添加成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  删除商品
	 * Create Date: 2010-5-6上午10:25:01 by yucy  Modified Date: 2010-5-6上午10:25:01 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=deleteGoods")   
	public ModelAndView deleteGoods(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> params = new HashMap<String, Object>();   

		if(null!=request.getParameter("goodsIds")&&!"".equals(request.getParameter("goodsIds"))){
			String[] goodsIds =  request.getParameter("goodsIds").split(",");
			//agreementGoodsService.remove(goodsIds, AgreementGoods.class);
			params.put("goodsIds", goodsIds);
			int result = agreementGoodsService.removeGoods(params);
			
			if(result<=0){
				model.put(Constants.JSON_RESULT,"删除成功!");
			}else{
				model.put(Constants.JSON_RESULT,"删除失败，该商品已经授权供货商!");
			}
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  授权新商品
	 * Create Date: 2010-5-6上午11:05:47 by yucy  Modified Date: 2010-5-6上午11:05:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=authorizGoods")   
	public ModelAndView authorizGoods(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		
		Map<String, Object> params = new HashMap<String, Object>();   

		
		if(null!=request.getParameter("goodsIds")&&!"".equals(request.getParameter("goodsIds"))&&
				null!=request.getParameter("agreeSecondId")&&!"".equals(request.getParameter("agreeSecondId"))){
				
				params.put("goodsIds", request.getParameter("goodsIds"));
				params.put("agreeSecondId", request.getParameter("agreeSecondId"));
				
				agreementGoodsService.authorizGoods(params);
				
				model.put(Constants.JSON_RESULT,"添加成功!");
			}
		
		model.put(Constants.JSON_RESULT,"添加成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  解除授权
	 * Create Date: 2010-5-6下午05:41:08 by yucy  Modified Date: 2010-5-6下午05:41:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=delAuthorizedGoods")   
	public ModelAndView delAuthorizedGoods(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		
		Map<String, Object> params = new HashMap<String, Object>();   
		
		if(null!=request.getParameter("agreementSecondId")&&!"".equals(request.getParameter("agreementSecondId"))
				&&null!=request.getParameter("agreementGoodsId")&&!"".equals(request.getParameter("agreementGoodsId"))){
			
			params.put("agreementSecondId", request.getParameter("agreementSecondId"));
			params.put("agreementGoodsId", request.getParameter("agreementGoodsId"));
			
			agreementGoodsService.removeAuthorizedGoods(params);
		}
		model.put(Constants.JSON_RESULT,"删除成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	
	/** 
	 * Description : 获得选择参数 
	 * Create Date: 2010-5-19上午11:37:16 by yucy  Modified Date: 2010-5-19上午11:37:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getBrandSelectParam")   
	public  ModelAndView getBrandSelectParam(HttpServletRequest request,String goodsClassId,String goodsBrandId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> params = new HashMap<String, Object>();   
		if(null!=goodsClassId&&null==goodsBrandId){
			params.put("goodsClassId", goodsClassId);
		}else{
			params.put("goodsBrandId", goodsBrandId);
		}
		List list = agreementGoodsService.getBrandSelectParam(params);
		model.put("list", list);
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	
	/***********************************************行情商品部分************************************************/
	
	/** 
	 * Description :跳转行情商品列表
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPriceGoodsList")   
	public ModelAndView toPriceGoodsList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		return new ModelAndView("toPriceGoodsListView", model);
    }
	
	/** 
	 * Description :  获取行情商品展示的列表数据
	 * Create Date: 2009-4-13上午10:53:40 by yucy  Modified Date: 2009-4-13上午10:53:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getPriceGoodsList")   
	public ModelAndView getPriceGoodsList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Page<Goods> page = prePage(request); //预分页,算出当前页和大小等		
		
		//参数封装
		Map<String, Object> param = new HashMap<String, Object>();
		
		//查询条件
		param.put("productName", StringUtils.spaceToPercent(request.getParameter("productName")));
		param.put("brandName", StringUtils.spaceToPercent(request.getParameter("brandName")));
		param.put("className", StringUtils.spaceToPercent(request.getParameter("className")));
		
		//排序
		param.put("order", request.getParameter("order"));
		param.put("order_flag", request.getParameter("order_flag"));
		
		Page<Goods> pageData = (Page<Goods>) agreementGoodsService.getPriceGoodsListByAgree(page,param);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
}
