package com.gpcsoft.epp.webService.ueSystem.orgInfo.user.service.impl;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.HeaderDTO;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.user.dto.UserDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.user.service.UserService;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.EmployeeService;

public class UserServiceImpl implements UserService {
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	/**
	 * FuncName: updateBasicMessage
	 * Description :维护机构用户注册信息
	 * @param 
	 * @param xml
	 * @return String
	 * @author: chench
	 * @Create Date:2011-3-25  下午02:04:45
	 * @Modifier: chench
	 * @Modified Date:2011-3-25  下午02:04:45
	 */
	public  LoginDTO updateUserMessage(String orgCode,String xml) {
		Document document;
		LoginDTO loginDTO = new LoginDTO();
		HeaderDTO headerDTO = new HeaderDTO();
		try {
			document = DocumentHelper.parseText(xml);
			UserDTO userDTO = (UserDTO) XmlUtil.elementToObject(document.getRootElement(), UserDTO.class);
			OrgInfo orgInfo = userApiService.getOrgInfoByOrgCode(orgCode);
			User user =orgInfo.getUser();
			Employee employee = null;
            if(user!=null&&user.getObjId()!=null){
            	employee = userApiService.getEmployeeById(user.getEmp().getObjId());
            }
            if(employee!=null){
            	employee.setName(userDTO.getLName());
            	employee.setSex_value(userDTO.getLSex());
            	employee.setIdCard(userDTO.getLId());
            	employee.setEmail(userDTO.getLEmail());
            	employee.setMobile(userDTO.getLMPhone());
            	employee.setTelHome(userDTO.getLPhone());
            	employee.setFax(userDTO.getLFax());
            	employee.setAddress(userDTO.getLaddress());
            	employee.setZipCode(userDTO.getLPostcode());
            	userApiService.save(employee, Employee.class);
			    headerDTO.setOperTag("Y");
			    headerDTO.setOperDesc("维护注册信息成功");
			    loginDTO.setHeader(headerDTO);
            }			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginDTO;
	}

}
