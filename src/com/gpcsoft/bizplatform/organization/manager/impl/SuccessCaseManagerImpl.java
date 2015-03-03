package com.gpcsoft.bizplatform.organization.manager.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.dao.SuccessCaseDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.domain.SuccessCase;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.manager.SuccessCaseManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

import freemarker.template.TemplateException;

@Repository
public class SuccessCaseManagerImpl extends BaseManagerImpl<SuccessCase> implements SuccessCaseManager {

	@Autowired(required=true)  @Qualifier("successCaseDaoHibernate")
	private SuccessCaseDao successCaseDaoHibernate;
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;

	/** 
	 * Description : 确认成交供应商后，为供应商生成成功案例 
	 * Create Date: 2010-12-13上午11:35:05 by guoyr  Modified Date: 2010-12-13上午11:35:05 by guoyr
	 * @param  project：项目 supplier：供应商 buyer：采购人
	 * @return  
	 * @throws TemplateException 
	 * @throws IOException 
	 * @Exception   
	 */
	public SuccessCase createSupplierSuccessCase(String projectName,Date beginTime,String purCategoryIds, String purCategoryNames,String ebuyMethodCN,BigDecimal budgetTotalMoney,OrgInfo supplier,OrgInfo buyer) throws IOException, TemplateException{
		
		SuccessCase successCase = getInitSuccessCase(projectName,beginTime,purCategoryIds,purCategoryNames,supplier);
		
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap.put("ebuyMethodCN", ebuyMethodCN);
		templateMap.put("budgetTotalMoney", budgetTotalMoney);
		templateMap.put("buyer", buyer);
		templateMap.put("successCase", successCase);
		
		// 成功案例模板路径
		String templatePath = CustomerMessage.SUCCESSCASE_SUPPLIER_TEMPLATE;
		
		String contentsString = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(templatePath), templateMap);
		successCase.setDescription(contentsString);
		return successCaseDaoHibernate.save(successCase);
	}
	
	/** 
	 * Description : 确认成交供应商后，为采购人生成成功案例 
	 * Create Date: 2010-12-13上午11:35:05 by guoyr  Modified Date: 2010-12-13上午11:35:05 by guoyr
	 * @param  project：项目 buyer：采购人 supplierList：供应商
	 * @return  
	 * @throws TemplateException 
	 * @throws IOException 
	 * @Exception   
	 */
	public SuccessCase createBuyerSuccessCase(String projectName,Date beginTime,String purCategoryIds, String purCategoryNames,String ebuyMethodCN,BigDecimal budgetTotalMoney,OrgInfo buyer ,List<OrgInfo>supplierList) throws IOException, TemplateException{
		
		SuccessCase successCase = getInitSuccessCase(projectName,beginTime,purCategoryIds,purCategoryNames,buyer);
		
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap.put("ebuyMethodCN", ebuyMethodCN);
		templateMap.put("budgetTotalMoney", budgetTotalMoney);
		templateMap.put("successCase", successCase);
		templateMap.put("supplierList", supplierList);
		templateMap.put("nowDate", DateUtil.format(new Date(), "yyyy年MM月dd日"));
		
		// 成功案例模板路径
		String templatePath = CustomerMessage.SUCCESSCASE_BUYER_TEMPLATE;
		
		String contentsString = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(templatePath), templateMap);
		successCase.setDescription(contentsString);
		return successCaseDaoHibernate.save(successCase);
	}
	
	/** 
	 * Description : 初始化成功案例
	 * Create Date: 2010-12-13下午02:10:43 by guoyr  Modified Date: 2010-12-13下午02:10:43 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private SuccessCase getInitSuccessCase(String projectName,Date beginTime,String purCategoryIds, String purCategoryNames,OrgInfo orgInfo) {
		SuccessCase successCase = new SuccessCase();
		successCase.setOrgInfo(orgInfo);
		successCase.setProjectName(projectName);
		successCase.setStartTime(beginTime);// 开始时间
		successCase.setEndTime(new Date());//结束时间
		successCase.setAuditStatus(OrganizationEnum.PASS_EXAM);
		successCase.setUseStatus(OrganizationEnum.USE_VALID);
		successCase.setCategoryIds(purCategoryIds);//采购品目
		successCase.setCategoryNames(purCategoryNames);
		return successCase;
	}
}
