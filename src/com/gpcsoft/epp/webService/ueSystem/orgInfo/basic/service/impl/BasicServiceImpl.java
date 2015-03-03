package com.gpcsoft.epp.webService.ueSystem.orgInfo.basic.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.base.industry.domain.Industry;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.manager.SupplierManager;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.common.dao.UserApiDao;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.HeaderDTO;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.basic.dto.BasicDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.basic.dto.DownBasicDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.basic.service.BasicService;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.baseData.domain.District;
import com.gpcsoft.srplatform.baseData.manager.DistrictManager;

@Service
public class BasicServiceImpl extends BaseGenericServiceImpl implements BasicService {
	@Autowired(required=true) @Qualifier("supplierManagerImpl")
	private SupplierManager supplierManager;

	@Autowired(required=true) @Qualifier("districtManagerImpl")
	private DistrictManager districtManager;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true)  @Qualifier("userApiDaoHibernate")
	private UserApiDao userApiDao;
	
	/**
	 * FuncName: updateBasicMessage
	 * Description :维护机构基本信息
	 * @param 
	 * @param xml
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-3-24  下午02:04:45
	 * @Modifier: liuke
	 * @Modified Date:2011-3-24  下午02:04:45
	 */
	public  LoginDTO updateBasicMessage(String orgCode,String xml) {
		Document document;
		LoginDTO  loginDTO = new LoginDTO();
		HeaderDTO headerDTO = new HeaderDTO();
		headerDTO.setOperTag("Y");
		headerDTO.setOperDesc("success");
		loginDTO.setHeader(headerDTO);
		try {
			document = DocumentHelper.parseText(xml);
			Element e =   document.getRootElement().element("body");
			BasicDTO basicDTO = (BasicDTO) XmlUtil.elementToObject(e, BasicDTO.class);
 
			OrgInfo orgInfo = userApiService.getOrgInfoByOrgCode(orgCode);
			//保存ORG_INFO表里数据
			orgInfo.setOrgName(basicDTO.getOrgName());   //机构名称
			
			String provinceStr = basicDTO.getProvince();  //省编码
			String cityStr = basicDTO.getCity();  //城市编码
			String townStr = basicDTO.getCounty();  //区县编码
			District province = districtManager.get(provinceStr);
			District city = districtManager.get(cityStr);
			District town = districtManager.get(townStr);
			String districtValue = provinceStr+","+cityStr+","+townStr+"##||##"+province.getName()+","+city.getName()+","+town.getName();
			orgInfo.setDistrictValue(districtValue);
			orgInfo.setEntPrpt(basicDTO.getProperty());  //企业类型/性质
			orgInfo.setUnitScape(basicDTO.getScale());   //企业规模
			Industry industry = new Industry();
			industry.setObjId(basicDTO.getBusiness());
			orgInfo.setBelongIndustry(industry);   //所属行业
			
			
			//保存SPL_SUPPLIER表里的数据
			if(orgInfo.getSupplierId()!=null&&!"".equals(orgInfo.getSupplierId())){   //标示该机构是供应商
				Supplier supplier = supplierManager.get(orgInfo.getSupplierId());
				supplier.setEntPrpt(basicDTO.getProperty()); //企业类型/性质
				supplier.setUnitScape(basicDTO.getScale());  //企业规模
				supplier.setCorporation(basicDTO.getDelegate());  //法定代表人
				supplierManager.save(supplier);
			}
			
			//保存AUTH_ORGNIZATION表里数据
			Company company = orgInfo.getCompany();
			company.setName(basicDTO.getOrgName());  //机构名称
			company.setShortName(basicDTO.getShortName());  //机构简称
			company.setAddress(basicDTO.getAddress());     //机构地址
			company.setPostCode(basicDTO.getPostcode());    //邮政编码
			
			District districtTown = new  District();
			districtTown.setObjId(basicDTO.getCounty());
			company.setTown(districtTown);//行政区域（区县）
			
			userApiService.save(orgInfo);
		} catch (Exception e) {
			e.printStackTrace();
			loginDTO.getHeader().setOperTag("N");
			loginDTO.getHeader().setOperDesc("error");
		}
		return loginDTO;
	}
	
	/**
	 * FuncName: getBasicXml
	 * Description :  UE平台机构信息下载BASIC
	 * @param 
	 * @return String
	 * @author: shenjz
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @Create Date:2011-3-25  上午11:21:48
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-25  上午11:21:48
	 */
	public String getOrginfoXml(String userCode,String password,String orgCode,String infoType) throws Exception{ //base
		StringBuffer sb = new StringBuffer();
		Agency agency = userApiDao.getAgency(orgCode);
		OrgInfo orgInfo = agency.getOrgInfo();
		DownBasicDTO dbd = new DownBasicDTO();
		dbd.setOrgName(orgInfo.getOrgName());
		dbd.setShortName(orgInfo.getCompany().getShortName());
		String[]str = orgInfo.getDistrictValue().replaceAll("#",",").replace('|', ',').split(",");
		dbd.setProvince(str[8]);
		dbd.setCity(str[9]);
		dbd.setCounty(str[10]);
		dbd.setAddress(orgInfo.getCompany().getAddress());
		dbd.setPostcode(orgInfo.getCompany().getPostCode());
		dbd.setOlinker(orgInfo.getCompany().getContact());
		dbd.setOlPhone(orgInfo.getCompany().getTel());
		dbd.setProperty(orgInfo.getCompany().getType());
		dbd.setBusiness(orgInfo.getBidForRange());
		dbd.setOperation(orgInfo.getMainProducts());
		dbd.setCooperate("");
		dbd.setScale(orgInfo.getUnitScape());
		dbd.setDelegate(agency.getRegCoporate());
		dbd.setDphone(orgInfo.getCompany().getTel());
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
		sb.append(XmlUtil.objectToXml(dbd));
		sb.append("</body>");
		sb.append("</downorginfo>");
		return sb.toString();
	}


}
