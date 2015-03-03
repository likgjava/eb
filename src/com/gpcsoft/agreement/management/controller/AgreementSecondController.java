package com.gpcsoft.agreement.management.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.agreement.management.service.AgreementSecondService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="agreementSecondFormView"
  *  url="view/agreement/management/agreementSecondForm.jsp"
  * @gpcsoft.view value="agreementSecondTreeFormView"
  *  url="view/agreement/management/agreementSecondTreeForm.jsp"
  * @gpcsoft.view value="agreementSecondListView"
  *  url="view/agreement/management/agreementSecondList.jsp"
  * @gpcsoft.view value="agreementSecondDetailView"
  *  url="view/agreement/management/agreementSecondDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={AgreementSecond.class})
@RequestMapping("/AgreementSecondController.do")//页面请求路径,可修改
public class AgreementSecondController extends AnnotationMultiController<AgreementSecond> {

	@Autowired(required=true) @Qualifier("agreementSecondServiceImpl")
	private AgreementSecondService agreementSecondService;
	
	
	/** 
	 * Description :  保存二级采购协议(同时保存分类 商品)
	 * Create Date: 2010-4-19下午01:22:29 by yucy  Modified Date: 2010-4-19下午01:22:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveAgreementSecond")   
	public ModelAndView saveAgreementSecond(HttpServletRequest request, String ObjectStr) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(!"".equals(ObjectStr)&&null!=ObjectStr){
			//获取类别ids  和单品ids
			String goodsClassId = request.getParameter("goodsClassId");
			String goodsIds = request.getParameter("goodsIds");
			AgreementSecond agreementSecond = JsonUtils.json2Bean(ObjectStr, AgreementSecond.class);
			
			Map<String, Object> params = new HashMap<String, Object>();   
			params.put("goodsClassId", goodsClassId);
			params.put("goodsIds", goodsIds);
			params.put("agreementSecond", agreementSecond);
			
			agreementSecondService.saveAgreementSecond(params);
			
			model.put(Constants.JSON_RESULT, "保存成功!");
		}else{
			model.put(Constants.JSON_RESULT, "保存失败!");
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	
	/** 
	 * Description :  保存二级采购协议
	 * Create Date: 2010-4-19下午01:22:29 by yucy  Modified Date: 2010-4-19下午01:22:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveAgreementSecondSimple")   
	public ModelAndView saveAgreementSecondSimple(HttpServletRequest request, String agreementSecondStr) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		
		AgreementSecond agreementSecond = JsonUtils.json2Bean(agreementSecondStr, AgreementSecond.class);
		
		AgreementSecond agreementSecondBefore = agreementSecondService.get(agreementSecond.getObjId());
		
		BeanUtils.copyPropertiesFilterEmptyNew(agreementSecondBefore,agreementSecond);

		agreementSecondService.save(agreementSecondBefore);
		
		model.put(Constants.JSON_RESULT, "保存成功!");
		
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	

}
