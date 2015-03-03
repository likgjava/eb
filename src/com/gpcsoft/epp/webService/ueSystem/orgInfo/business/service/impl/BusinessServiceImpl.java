package com.gpcsoft.epp.webService.ueSystem.orgInfo.business.service.impl;

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
import com.gpcsoft.epp.webService.ueSystem.orgInfo.business.dto.BusinessDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.business.dto.DownBusinessDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.business.service.BusinessService;
import com.gpcsoft.srplatform.auth.domain.Company;

public class BusinessServiceImpl implements BusinessService {
	
	@Autowired(required=true) @Qualifier("supplierManagerImpl")
	private SupplierManager supplierManager;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true)  @Qualifier("userApiDaoHibernate")
	private UserApiDao userApiDao;
	
	/**
	 * FuncName: updateBusinessMessage
	 * Description :  维护营业信息
	 * @param 
	 * @param orgCode
	 * @param xml
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-3-25  下午04:57:37
	 * @Modifier: liuke
	 * @Modified Date:2011-3-25  下午04:57:37
	 */
	public  LoginDTO updateBusinessMessage(String orgCode,String xml){
		Document document;
		LoginDTO  loginDTO = new LoginDTO();
		HeaderDTO headerDTO = new HeaderDTO();
		headerDTO.setOperTag("Y");
		headerDTO.setOperDesc("success");
		loginDTO.setHeader(headerDTO);
		try {
			document = DocumentHelper.parseText(xml);
			Element e =   document.getRootElement().element("body");
			BusinessDTO businessDTO = (BusinessDTO) XmlUtil.elementToObject(e, BusinessDTO.class);
			OrgInfo orgInfo = 	userApiService.getOrgInfoByOrgCode(orgCode);
			//保存ORG_INFO表里数据
			orgInfo.setMainProducts(businessDTO.getMainProduct());   //主营产品
			orgInfo.setBidForRange(businessDTO.getTenderType());     //投标范围及类别     
			orgInfo.setEntCapacity(businessDTO.getOcapacity());      //企业产能
			orgInfo.setDescCn(businessDTO.getIntroduction());        //企业简介
			
			//保存SPL_SUPPLIER表里的数据	
			if(orgInfo.getSupplierId()!=null&&!"".equals(orgInfo.getSupplierId())){   //标示该机构是供应商
				Supplier supplier = supplierManager.get(orgInfo.getSupplierId());
				supplier.setWebUrl(businessDTO.getWebUrl());    //企业网址
				supplierManager.save(supplier);
			}
			
			//保存AUTH_ORGNIZATION表里数据
			Company company = orgInfo.getCompany();
			company.setEmail(businessDTO.getOEmail());           //保存企业EMAIL
			userApiService.save(orgInfo);
		} catch (Exception e) {
			e.printStackTrace();
			loginDTO.getHeader().setOperTag("N");
			loginDTO.getHeader().setOperDesc("error");
		}
		return loginDTO;
	}

	/**
	 * FuncName: getBusiness
	 * Description : UE平台机构信息下载BUSINESS
	 * @param userCode
	 * @param password
	 * @param orgCode
	 * @param infoType
	 * @return String
	 * @throws Exception 
	 * @author: shenjz
	 * @Create Date:2011-3-25  下午04:38:30
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-25  下午04:38:30
	 */
	public String getBusiness(String userCode, String password, String orgCode,String infoType) throws Exception {
		StringBuffer sb = new StringBuffer();
		DownBusinessDTO d1 = new DownBusinessDTO();
		Agency agency = userApiDao.getAgency(orgCode);
		OrgInfo orgInfo = agency.getOrgInfo();
		d1.setWebUrl(orgInfo.getDescCn());
		d1.setOemail(orgInfo.getCompany().getEmail());
		d1.setMainProduct(orgInfo.getMainProducts());
		d1.setTenderType(orgInfo.getBidForRange());
		d1.setOcapacity(orgInfo.getEntCapacity());
		d1.setIntroduction(orgInfo.getDescCn());
		d1.setAchievement(agency.getAgencyBussCndt());
		d1.setLastInput("");
		d1.setLastTwoInput("");
		d1.setLastThreeInput("");
		d1.setIncomeTax("");
		d1.setProfit("");
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
		sb.append(XmlUtil.objectToXml(d1));
		sb.append("</body>");
		sb.append("</downorginfo>");
		return sb.toString();
	}

}
