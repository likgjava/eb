package com.gpcsoft.bizplatform.organization.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.domain.SuccessCase;
import com.gpcsoft.core.manager.BaseManager;

import freemarker.template.TemplateException;

public interface SuccessCaseManager extends BaseManager<SuccessCase> {

	/** 
	 * Description : 确认成交供应商后，为供应商生成成功案例 
	 * Create Date: 2010-12-13上午11:35:05 by guoyr  Modified Date: 2010-12-13上午11:35:05 by guoyr
	 * @param  project：项目 supplier：供应商 buyer：采购人
	 * @return  
	 * @throws TemplateException 
	 * @throws IOException 
	 * @Exception   
	 */
	public SuccessCase createSupplierSuccessCase(String projectName,Date beginTime,String purCategoryIds, String purCategoryNames,String ebuyMethodCN,BigDecimal budgetTotalMoney,OrgInfo supplier,OrgInfo buyer) throws IOException, TemplateException;
	
	/** 
	 * Description : 确认成交供应商后，为采购人生成成功案例 
	 * Create Date: 2010-12-13上午11:35:05 by guoyr  Modified Date: 2010-12-13上午11:35:05 by guoyr
	 * @param  project：项目 buyer：采购人 supplierList：供应商
	 * @return  
	 * @throws TemplateException 
	 * @throws IOException 
	 * @Exception   
	 */
	public SuccessCase createBuyerSuccessCase(String projectName,Date beginTime,String purCategoryIds, String purCategoryNames,String ebuyMethodCN,BigDecimal budgetTotalMoney,OrgInfo buyer ,List<OrgInfo>supplierList) throws IOException, TemplateException;
	
	
}
