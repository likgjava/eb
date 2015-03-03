package com.gpcsoft.agreement.management.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.gpcsoft.agreement.management.domain.AgreementGoods;
import com.gpcsoft.agreement.management.domain.AgreementGoodsclass;
import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.agreement.management.service.AgreementGoodsclassService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.extra.json.FrameJsonView;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

/**
  * @gpcsoft.view value="agreementGoodsclassFormView"
  *  url="view/agreement/management/agreementGoodsclassForm.jsp"
  * @gpcsoft.view value="agreementGoodsclassTreeFormView"
  *  url="view/agreement/management/agreementGoodsclassTreeForm.jsp"
  * @gpcsoft.view value="agreementGoodsclassListView"
  *  url="view/agreement/management/agreementGoodsclassList.jsp"
  * @gpcsoft.view value="agreementGoodsclassDetailView"
  *  url="view/agreement/management/agreementGoodsclassDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={AgreementGoodsclass.class,AgreementGoods.class})
@RequestMapping("/AgreementGoodsclassController.do")//页面请求路径,可修改
public class AgreementGoodsclassController extends AnnotationMultiController<AgreementGoodsclass> {

	@Autowired(required=true) @Qualifier("agreementGoodsclassServiceImpl")
	private AgreementGoodsclassService agreementGoodsclassService;
	
	/** 
	 * Description : 删除协议分类 (同时删除相关联协议商品)
	 * Create Date: 2010-4-20上午02:58:44 by yucy  Modified Date: 2010-4-20上午02:58:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeAgreementGoodsclassAndGoods")
	public ModelAndView removeAgreementGoodsclassAndGoods(HttpServletRequest request) throws Exception {
		Map<String, String> model = new HashMap<String, String>();   
		Map<String, Object> params = new HashMap<String, Object>();   
		//初始result
		int result = 0;
		
		//一级删除分类
		if(null!=request.getParameter("agreementGoodsclassIds")&&!"".equals(request.getParameter("agreementGoodsclassIds"))
				){
			String[] agreementGoodsclassIds = request.getParameter("agreementGoodsclassIds").split(",");
			params.put("agreementGoodsclassIds", agreementGoodsclassIds);
			result = agreementGoodsclassService.removeAgreementGoodsclassAndGoods(params);
		}
		//删除二级分类
		else if(null!=request.getParameter("agreementGoodsclassIds")&&!"".equals(request.getParameter("agreementGoodsclassIds"))){
			String[] agreementGoodsclassIds = request.getParameter("agreementGoodsclassIds").split(",");
			agreementGoodsclassService.remove(agreementGoodsclassIds, AgreementGoodsclass.class);
			result  = 1;
		}
		
		if(result>0){
			model.put(Constants.JSON_RESULT, "删除成功!");
		}else{
			if(request.getParameter("agreementGoodsclassIds").equals("newClassAndBrandTr")){
				model.put(Constants.JSON_RESULT, "删除成功!");
			}else{
				model.put(Constants.JSON_RESULT, "删除失败!操作分类已授权二级协议!");
			}
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
	/**
	 * 
	 * Description :  授权未授权的分类
	 * Create Date: 2010-4-26上午10:39:18 by yucy  Modified Date: 2010-4-26上午10:39:18 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=listClass")
    public ModelAndView listClass(HttpServletRequest request)throws Exception{
	    Map model = new HashMap();
	    
		String queryColumns = makeQueryColumns(request);
		QueryObject query = QueryWebUtils.getQuery(request, this.getPersistClass());
		initQueryColums(request, query);
		
		Page page = prePage(request);
		Page pageData = agreementGoodsclassService.findByQuery(query, true,page.getStart(), page.getPageSize());
		
		List newRowData = new ArrayList();
		for (int i= 0;i<pageData.getData().size();i++) {
			List newColData = new ArrayList();
			Object[] objects =  (Object[])pageData.getData().get(i);
			
			
			String  brandId = (String)objects[3];
			String  goodsClassId = (String)objects[4];

			//参数
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("brandId", brandId);
			params.put("goodsClassId", goodsClassId);
			params.put("agreementId", request.getParameter("agreement.objId"));
			
			List<AgreementSecond> agreementSecondList = (List)agreementGoodsclassService.getAuthorizedClass(params);
			
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
	 * Description :  获得协议分类（带有无新商品属性）
	 * Create Date: 2010-4-27上午11:05:32 by yucy  Modified Date: 2010-4-27上午11:05:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGoodsClassWithTips")
    public ModelAndView getGoodsClassWithTips(HttpServletRequest request)throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();   
		
		String agreementId = request.getParameter("agreementId");
		
		List<AgreementGoodsclass> agreementGoodsclassList = (List<AgreementGoodsclass>)agreementGoodsclassService.getGoodsClassWithTips(agreementId);
		
		model.put("agreementClassList", agreementGoodsclassList);

		model.put(FrameJsonView.INCLUDED_PROPERTIES, new String[]{
				"brand",
				"goodsClass"
		});
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  授权新类别
	 * Create Date: 2010-5-6上午11:10:06 by yucy  Modified Date: 2010-5-6上午11:10:06 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=authorizGoodsClass")   
	public ModelAndView authorizGoodsClass(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		
		Map<String, Object> params = new HashMap<String, Object>();   
		
		if(null!=request.getParameter("goodsClassIds")&&!"".equals(request.getParameter("goodsClassIds"))&&
			null!=request.getParameter("agreeSecondId")&&!"".equals(request.getParameter("agreeSecondId"))){
			
			params.put("goodsClassIds", request.getParameter("goodsClassIds"));
			params.put("agreeSecondId", request.getParameter("agreeSecondId"));
			
			agreementGoodsclassService.authorizGoodsClass(params);
			
			model.put(Constants.JSON_RESULT,"添加成功!");
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  解除授权
	 * Create Date: 2010-5-6下午03:28:34 by yucy  Modified Date: 2010-5-6下午03:28:34 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=delAuthorizedClass")   
	public ModelAndView delAuthorizedClass(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		
		Map<String, Object> params = new HashMap<String, Object>();   
		
		if(null!=request.getParameter("agreementSecondId")&&!"".equals(request.getParameter("agreementSecondId"))
				&&null!=request.getParameter("agreementGoodsClassId")&&!"".equals(request.getParameter("agreementGoodsClassId"))){
			
			params.put("agreementSecondId", request.getParameter("agreementSecondId"));
			params.put("agreementGoodsClassId", request.getParameter("agreementGoodsClassId"));
			
			agreementGoodsclassService.removeAuthorizedClass(params);
			
		}
		
		model.put(Constants.JSON_RESULT,"删除成功!");
		
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	
	
	/** 
	 * Description :  保存或新增每行数据
	 * Create Date: 2010-5-12下午04:04:56 by yucy  Modified Date: 2010-5-12下午04:04:56 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveOrUpDateRow")   
	public ModelAndView saveOrUpDateRow(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> params = new HashMap<String, Object>();   //参数

		String objId = request.getParameter("objId");
		String goodsClassId = request.getParameter("goodsClassId");
		String brandId = request.getParameter("brandId");
		String discountRatio = request.getParameter("discountRatio");
		String agreementId = request.getParameter("agreementId");
		String agreementType = request.getParameter("agreementType");
		
		if(null!=goodsClassId&&!"".equals(goodsClassId)&&
		   null!=brandId&&!"".equals(brandId)&&
		   null!=discountRatio&&!"".equals(discountRatio)&&
		   null!=agreementId&&!"".equals(agreementId)&&
		   null!=agreementType&&!"".equals(agreementType)){
			if(null!=objId&&!"".equals(objId)&&!"null".equals(objId)){//修改
				AgreementGoodsclass agreementGoodsPersist= agreementGoodsclassService.get(objId);
				
				//是否修改了类别和品牌
				if(!goodsClassId.equals(agreementGoodsPersist.getGoodsClass().getObjId())
						||!brandId.equals(agreementGoodsPersist.getBrand().getObjId())){
					GoodsClass goodsClass = new GoodsClass();
					GoodsBrand goodsBrand = new GoodsBrand();
					goodsClass.setObjId(goodsClassId);
					goodsBrand.setObjId(brandId);
					agreementGoodsPersist.setGoodsClass(goodsClass);
					agreementGoodsPersist.setBrand(goodsBrand);
					agreementGoodsPersist.setDiscountRatio(new BigDecimal(discountRatio));
					Integer secondClass = agreementGoodsclassService.removeGoodsByClassReAddGoods(agreementGoodsPersist);
					if(secondClass>0){
						model.put(Constants.JSON_RESULT,"该分类已经授权了二级协议，不能再更改分类和品牌!");
						return new ModelAndView(Constants.JSON_VIEW,model);
					}
				}
				//只修改了discountRatio
				else if(goodsClassId.equals(agreementGoodsPersist.getGoodsClass().getObjId())
						&&brandId.equals(agreementGoodsPersist.getBrand().getObjId())
						&&!discountRatio.equals(agreementGoodsPersist.getDiscountRatio().toString())){
					
					BigDecimal discountRatiod = new BigDecimal(discountRatio); 
					agreementGoodsPersist.setDiscountRatio(discountRatiod);
					agreementGoodsclassService.updateClassAndGoodsDiscountRatio(agreementGoodsPersist);
				}
			}else {//新增
				params.put("goodsClassId", goodsClassId);
				params.put("brandId", brandId);
				params.put("discountRatio", discountRatio);
				params.put("agreementId",agreementId);
				params.put("agreementType", agreementType);
				
				agreementGoodsclassService.saveOrUpDateRow(params);
				model.put(Constants.JSON_RESULT,"保存成功!");
			}
		}else{
			//参数不全
		}
		model.put(Constants.JSON_RESULT,"保存成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
}
