package com.gpcsoft.epp.webService.ueSystem.orgInfo.legal.service.impl;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.manager.SupplierManager;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.HeaderDTO;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.legal.dto.LegalDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.legal.service.LegalService;

public class LegalServiceImpl implements LegalService {
	
	@Autowired(required=true) @Qualifier("supplierManagerImpl")
	private SupplierManager supplierManager;
	
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	/**
	 * FuncName: updateBasicMessage
	 * Description :维护机构法务信息
	 * @param 
	 * @param xml
	 * @return String
	 * @author: chench
	 * @Create Date:2011-3-25  下午02:04:45
	 * @Modifier: chench
	 * @Modified Date:2011-3-25  下午02:04:45
	 */
	public  LoginDTO updateLegalMessage(String orgCode,String xml) {
		Document document;
		LoginDTO loginDTO = new LoginDTO();
		HeaderDTO headerDTO = new HeaderDTO();
		try {
			document = DocumentHelper.parseText(xml);
			LegalDTO legalDTO = (LegalDTO) XmlUtil.elementToObject(document.getRootElement(),  LegalDTO.class);
			OrgInfo orgInfo = userApiService.getOrgInfoByOrgCode(orgCode);

			if(orgInfo.getSupplierId()!=null&&!"".equals(orgInfo.getSupplierId())){   //标示该机构是供应商
				Supplier supplier = supplierManager.get(orgInfo.getSupplierId());
				supplier.setOrgUnitRegNmbr(legalDTO.getOrgCodeFile());
				supplier.setOrgUnitAwardUnit(legalDTO.getOrgCodeAuthority());
				//银行信誉的
				//银行证明文件
				supplier.setTradeEndDate(DateUtil.parse(legalDTO.getBusinessValDate()));
				supplier.setRegAuthOrg(legalDTO.getBusinessFile());
			    supplierManager.save(supplier);
			    headerDTO.setOperTag("Y");
			    headerDTO.setOperDesc("维护法务信息成功");
			    loginDTO.setHeader(headerDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginDTO;
	}

}
