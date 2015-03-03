package com.gpcsoft.epp.webService.ueSystem.orgInfo.account.service.impl;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.manager.SupplierManager;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.HeaderDTO;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.account.dto.AccountDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.account.service.AccountService;

public class AccountServiceImpl implements AccountService {
	@Autowired(required=true) @Qualifier("supplierManagerImpl")
	private SupplierManager supplierManager;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
 
	
	/**
	 * FuncName: updateBasicMessage
	 * Description :维护机构开户信息
	 * @param 
	 * @param xml
	 * @return String
	 * @author: chench
	 * @Create Date:2011-3-25  下午02:04:45
	 * @Modifier: chench
	 * @Modified Date:2011-3-25  下午02:04:45
	 */
	public  LoginDTO updateAccountMessage(String orgCode,String xml) {
		Document document;
		LoginDTO loginDTO = new LoginDTO();
		HeaderDTO headerDTO = new HeaderDTO();
		try {
			document = DocumentHelper.parseText(xml);
			AccountDTO accountDTO = ( AccountDTO) XmlUtil.elementToObject(document.getRootElement(),  AccountDTO.class);
			OrgInfo orgInfo = userApiService.getOrgInfoByOrgCode(orgCode);

			if(orgInfo.getSupplierId()!=null&&!"".equals(orgInfo.getSupplierId())){   //标示该机构是供应商
				Supplier supplier = supplierManager.get(orgInfo.getSupplierId());
				supplier.setOpenBank(accountDTO.getOpenBank());
				supplier.setOpenAccountNmbr(accountDTO.getOpenName());
				supplier.setOpenAccount(accountDTO.getOpenAccount());
			    supplierManager.save(supplier);
			    headerDTO.setOperTag("Y");
			    headerDTO.setOperDesc("维护开户信息成功");
			    loginDTO.setHeader(headerDTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginDTO;
	}

}
