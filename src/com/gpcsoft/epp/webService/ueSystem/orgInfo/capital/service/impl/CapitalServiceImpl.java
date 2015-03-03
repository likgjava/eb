package com.gpcsoft.epp.webService.ueSystem.orgInfo.capital.service.impl;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.manager.SupplierManager;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.HeaderDTO;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.capital.dto.CapitalDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.capital.service.CapitalService;

public class CapitalServiceImpl implements CapitalService {
	@Autowired(required=true) @Qualifier("supplierManagerImpl")
	private SupplierManager supplierManager;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	/**
	 * FuncName: updateCapitalMessage
	 * Description : 维护资本信息
	 * @param 
	 * @param orgCode
	 * @param xml
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-3-25  下午04:57:19
	 * @Modifier: liuke
	 * @Modified Date:2011-3-25  下午04:57:19
	 */
	public  LoginDTO updateCapitalMessage(String orgCode,String xml){
		Document document;
		LoginDTO  loginDTO = new LoginDTO();
		HeaderDTO headerDTO = new HeaderDTO();
		headerDTO.setOperTag("Y");
		headerDTO.setOperDesc("success");
		loginDTO.setHeader(headerDTO);
		try {
			document = DocumentHelper.parseText(xml);
			Element e =   document.getRootElement().element("body");
			CapitalDTO capitalDTO = (CapitalDTO) XmlUtil.elementToObject(e, CapitalDTO.class);
			OrgInfo orgInfo = userApiService.getOrgInfoByOrgCode(orgCode);
			//保存SPL_SUPPLIER表里的数据	
			if(orgInfo.getSupplierId()!=null&&!"".equals(orgInfo.getSupplierId())){   //标示该机构是供应商
				Supplier supplier = supplierManager.get(orgInfo.getSupplierId());
				if(capitalDTO.getRegisterfee()!=null&&!"".equals(capitalDTO.getRegisterfee())){
					supplier.setRegCapital(Double.valueOf(capitalDTO.getRegisterfee()));    //注册资金
				}
				if(capitalDTO.getTotalAsset()!=null&&"".equals(capitalDTO.getTotalAsset()))
				supplier.setPaidUpCapital(Double.valueOf(capitalDTO.getTotalAsset()));
				supplierManager.save(supplier);
			}
		} catch (Exception e) {
			e.printStackTrace();
			loginDTO.getHeader().setOperTag("N");
			loginDTO.getHeader().setOperDesc("error");
		}
		return loginDTO;
	}

}
