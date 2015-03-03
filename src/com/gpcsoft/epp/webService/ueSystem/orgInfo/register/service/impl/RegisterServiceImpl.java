package com.gpcsoft.epp.webService.ueSystem.orgInfo.register.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.manager.SupplierManager;
import com.gpcsoft.epp.common.dao.UserApiDao;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.HeaderDTO;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.register.dto.DownRegisterDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.register.dto.RegisterDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.register.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
 
	
	@Autowired(required=true) @Qualifier("supplierManagerImpl")
	private SupplierManager supplierManager;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true)  @Qualifier("userApiDaoHibernate")
	private UserApiDao userApiDao;
	
	/**
	 * FuncName: updateBasicMessage
	 * Description :维护机构工商注册信息
	 * @param 
	 * @param xml
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-3-24  下午02:04:45
	 * @Modifier: liuke
	 * @Modified Date:2011-3-24  下午02:04:45
	 */
	public  LoginDTO updateRegisterMessage(String orgCode,String xml){
		Document document;
		LoginDTO  loginDTO = new LoginDTO();
		HeaderDTO headerDTO = new HeaderDTO();
		headerDTO.setOperTag("Y");
		headerDTO.setOperDesc("success");
		loginDTO.setHeader(headerDTO);
		try {
			document = DocumentHelper.parseText(xml);
			Element e =   document.getRootElement().element("body");
			RegisterDTO registerDTO = (RegisterDTO) XmlUtil.elementToObject(e, RegisterDTO.class);
			OrgInfo orgInfo = userApiService.getOrgInfoByOrgCode(orgCode);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//保存ORG_INFO表里数据
			Date begainDate = sf.parse(registerDTO.getOpenDate());    //开业日期
			orgInfo.setBegainDate(begainDate);
			
			//保存SPL_SUPPLIER表里的数据	
			if(orgInfo.getSupplierId()!=null&&!"".equals(orgInfo.getSupplierId())){   //标示该机构是供应商
				Supplier supplier = supplierManager.get(orgInfo.getSupplierId());
				supplier.setRegCode(registerDTO.getRegisterCode());    //工商注册号 
				supplier.setNatTaxNmbr(registerDTO.getTaxCode());      //国税登记编号               
				supplier.setLandTaxNmbr(registerDTO.getRentCode());    //地税登记证号码
				supplier.setRegBusScope(registerDTO.getRegisterScale());//工商登记营业范围
				Date regDate = sf.parse(registerDTO.getRegisterDate());    //工商注册日期
				supplier.setRegDate(regDate);                      
				supplier.setRegAuthOrg(registerDTO.getRegisterAuthority());//工商注册发证机关
				supplierManager.save(supplier);
			}
			userApiService.save(orgInfo);
		} catch (Exception e) {
			e.printStackTrace();
			loginDTO.getHeader().setOperTag("N");
			loginDTO.getHeader().setOperDesc("error");
		}
		
		return loginDTO;
	}
	
	/**
	 * FuncName: getResgister
	 * Description :  UE平台机构信息下载REGISTER
	 * @param 
	 * @return String
	 * @author: shenjz
	 * @Create Date:2011-3-25  下午03:34:45
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-25  下午03:34:45
	 */
	public String getResgister(String userCode,String password,String orgCode,String infoType)throws Exception{
		StringBuffer sb = new StringBuffer();
		Agency agency = userApiDao.getAgency(orgCode);
		OrgInfo orgInfo = agency.getOrgInfo();
		DownRegisterDTO drd = new DownRegisterDTO();
		drd.setRegisterCode(agency.getRegCode());
		drd.setTaxCode(agency.getRegCode());
		drd.setRentCode(agency.getRegCode());
		drd.setRegisterScale(agency.getRegBusScope());
		if(agency.getRegDate()!=null){
			drd.setRegisterDate(agency.getRegDate().toString());
		}
		if(orgInfo.getBegainDate()!=null){
			drd.setOpenDate(orgInfo.getBegainDate().toString());
		}
		drd.setRegisterAuthority(agency.getRegAuthOrg());
		sb.append("<?xml version='1.0' encoding='gb2312' ?>");
		sb.append("<downorginfo xmlns='http://www.gpcsoft.com'>");
		sb.append("<header>");
		sb.append("<operTag>");
		sb.append("y/n");
		sb.append("</operTag>");
		sb.append("<operDesc>");
		sb.append("操作结果描述");
		sb.append("</operDesc>");
		sb.append("</header>");
		sb.append("<body>");
		sb.append(XmlUtil.objectToXml(drd));
		sb.append("</body>");
		sb.append("</downorginfo>");
		return sb.toString();
	}

}
