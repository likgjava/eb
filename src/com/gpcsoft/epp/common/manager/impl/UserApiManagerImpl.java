package com.gpcsoft.epp.common.manager.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.dao.UserApiDao;
import com.gpcsoft.epp.common.domain.RoleTypeEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.manager.UserApiManager;
import com.gpcsoft.epp.common.utils.MD5Util;
import com.gpcsoft.epp.common.utils.StringUtil;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.UserInfoDTO;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Organization;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
@SuppressWarnings("unchecked")
public class UserApiManagerImpl extends BaseManagerImpl implements UserApiManager {

	
	
	@Autowired(required=true)  @Qualifier("userApiDaoHibernate")
	private UserApiDao userApiDaoHibernate;

	/** 
	 * Description :  根据部门ID获取对应的所有员工信息
	 * Create Date: 2010-6-20下午04:55:34 by Administrator  Modified Date: 2010-6-20下午04:55:34 by Administrator
	 * @param depId
	 * @return
	 */
	public List<Employee> getEmpListByCurUserDepId(String depId) {
		return userApiDaoHibernate.getEmpListByCurUserDepId(depId);
	}

	/** 
	 * Description :  根据机构ID获得该机构下面的所有员工信息
	 * Create Date: 2010-6-20下午05:14:13 by Administrator  Modified Date: 2010-6-20下午05:14:13 by Administrator
	 * @param objId
	 * @return
	 */
	public List<Employee> getEmpListByCurUserOrgId(String orgId) {
		
		return userApiDaoHibernate.getEmpListByCurUserOrgId(orgId);
	}

	/** 
	 * Description :  根据用户ID获取对应的部门信息
	 * Create Date: 2010-6-21上午09:51:10 by Administrator  Modified Date: 2010-6-21上午09:51:10 by Administrator
	 * @param userId
	 * @return
	 */
	public Department getDepartmentByUserId(String userId) {
		
		return userApiDaoHibernate.getDepartmentByUserId(userId);
	}

	/** 
	 * Description :  根据员工ID获取员工同部门的所有员工
	 * Create Date: 2010-6-25下午05:40:57 by Administrator  Modified Date: 2010-6-25下午05:40:57 by Administrator
	 * @param empId
	 * @return
	 */
	public List<Employee> getAllEmpListByCurUserEmpId(String empId) {
		
		return userApiDaoHibernate.getAllEmpListByCurUserEmpId(empId);
	}

	/** 
	 * Description :  根据当前用户判断角色类型
	 * Create Date: 2010-6-18下午05:27:46 by Administrator  Modified Date: 2010-6-18下午05:27:46 by Administrator
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String checkRole(OrgInfo orgInfo)throws Exception{
		String requestUrl = "";
		if(orgInfo.getAgencyId() != null && !"".equals(orgInfo.getAgencyId())){
			requestUrl = RoleTypeEnum.ECP_AGENCY;
		}
		if(orgInfo.getBuyerId() != null && !"".equals(orgInfo.getBuyerId())){
			requestUrl = RoleTypeEnum.ECP_BUYER;
		}
		if(orgInfo.getSupplierId() != null && !"".equals(orgInfo.getSupplierId())){
			requestUrl = RoleTypeEnum.ECP_SUPPLIER;
		}
		if(orgInfo.getSupervisionId() != null && !"".equals(orgInfo.getSupervisionId())){
			requestUrl = RoleTypeEnum.ECP_SUPERVISE;
		}
		return requestUrl;
	}

	/** 
	 * Description :  根据用户ID获取对应的机构信息
	 * Create Date: 2010-6-21上午09:51:10 by Administrator  Modified Date: 2010-6-21上午09:51:10 by Administrator
	 * @param userId
	 * @return
	 */
	public OrgInfo getOrgInfoByUserId(String userId) {
		return userApiDaoHibernate.getOrgInfoByUserId(userId);
	}

	
	/**
	 * 
	 * Description :获得所有代理机构  
	 * Create Date: 2010-7-9下午04:15:11 by liuke  Modified Date: 2010-7-9下午04:15:11 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<OrgInfo> getAllAgents(QueryObject queryObject) {
		return userApiDaoHibernate.getAllAgents(queryObject);
	}

	
	/**
	 * 
	 * Description : 获得所有供应商列表
	 * Create Date: 2010-8-17上午09:57:30 by liuke  Modified Date: 2010-8-17上午09:57:30 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<OrgInfo> getAllSuppliers(QueryObject queryObject) {
		return userApiDaoHibernate.getAllSuppliers(queryObject);
	}
	/**
	 * 
	 * Description :  根据机构Id查询员工列表
	 * Create Date: 2010-8-31下午03:09:10 by shenjianzhuang  Modified Date: 2010-8-31下午03:09:10 by shenjianzhuang
	 * @param queryObject
	 * @param page
	 * @return
	 * @return  Page<Employee>
	 * @Exception 
	 */	 
	public Page<Employee> searchEmployee(final QueryObject queryObject,final Page<Employee> page) {
		return userApiDaoHibernate.searchEmployee(queryObject, page);
	}
	
	/** 
	 * Description :  根据员工Id获取员工的账号信息
	 * Create Date: 2010-11-8下午05:14:33 by yangx  Modified Date: 2010-11-8下午05:14:33 by yangx
	 * @param   empId：员工Id
	 * @return  User
	 * @Exception   
	 */
	public User getUserByEmpId(String empId){
		QueryObject<User> queryObject = new QueryObjectBase<User>();
		queryObject.setEntityClass(User.class);
		queryObject.getQueryParam().and(new QueryParam("emp.objId",QueryParam.OPERATOR_EQ,empId));
		List<User> list = userApiDaoHibernate.findByQuery(queryObject);
		return (list!=null&&list.size()>0)?list.get(0):null;
	}
	/** 
	 * Description :  根据orgInfoId查询组织结构信息
	 * Create Date: 2010-11-18下午03:35:01 by yangx  Modified Date: 2010-11-18下午03:35:01 by yangx
	 * @param   orgInfoId：组织结构Id
	 * @return  List<Organization>
	 * @Exception   
	 */
	public Company getOrganizationByOrgInfoId(String orgInfoId){
		return userApiDaoHibernate.getOrganizationByOrgInfoId(orgInfoId);
	}
	
	/** 
	 * Description :  从设置的大宗代理机构文件中查询代理机构
	 * Create Date: 2010-11-24上午11:47:30 by yangx  Modified Date: 2010-11-24上午11:47:30 by yangx
	 * @param   
	 * @return  List<OrgInfo>
	 * @Exception   
	 */
	public List<OrgInfo> getAgentForFile(){
		List<OrgInfo> orgInfoList = new ArrayList<OrgInfo>();
		String filePath = messageSource.getMessage("takeAgentUrl");
		String fileName = SeparationEnum.AGENT_BLOCKTRADE;
		String path=filePath.replaceAll("\\$\\{rootPath}",Constants.ROOTPATH);
		if((path+fileName)!=null&&!"".equals(path+fileName)){
			File file = new File(path+fileName);
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				String tempString = null;
				while ((tempString=reader.readLine())!= null) {//一次读入一行，直到读入null为文件结束
					String[] contents = tempString.split(SeparationEnum.NUM);//将格式:objId#name数据分解
					OrgInfo orgInfo = new OrgInfo();
					orgInfo.setOrgName(contents[1]);
					orgInfo.setObjId(contents[0]);
					orgInfoList.add(orgInfo);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return orgInfoList;
	}

	
	/**
	 * FuncName: getUserByObjId
	 * Description :  根据ID获得用户
	 * @param 
	 * @param objId
	 * @return User
	 * @author: liuke
	 * @Create Date:2011-4-14  上午10:14:30
	 * @Modifier: liuke
	 * @Modified Date:2011-4-14  上午10:14:30
	 */
	public User getUserByObjId(String objId) {
		return (User) this.get(objId, User.class);
	}

	
	/**
	 * FuncName: getRolesByUserId
	 * Description :  根据用户ID获得角色ID
	 * @param 
	 * @param userId
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-4-18  下午05:51:04
	 * @Modifier: liuke
	 * @Modified Date:2011-4-18  下午05:51:04
	 */
	public String getRolesByUserId(String userId) {
	  User user = (User) userApiDaoHibernate.get(userId, User.class);
		Set<Role> roles = user.getRoles();
		StringBuffer sb = new StringBuffer();
		for(Role role:roles){
			sb.append(role.getObjId()+",");
		}
		String rolesStr = sb.toString();
		return rolesStr.substring(0, rolesStr.lastIndexOf(",")).replace(",", "','");
	}

	/**
	 * FuncName: getUserInfoDTOByUserCodeAndPassWord
	 * Description :  根据用户名和密码获得登录信息
	 * @param 
	 * @param userCode
	 * @param password
	 * @return UserInfoDTO
	 * @author: liuke
	 * @Create Date:2011-5-9  上午10:19:30
	 * @Modifier: liuke
	 * @Modified Date:2011-5-9  上午10:19:30
	 */
	public UserInfoDTO getUserInfoDTOByUserCodeAndPassWord(String userCode,
			String password) {
		String decodeStringPassword = StringUtil.decodeString(password); //用Base64算法对字符串进行解码.
		String md5PassWord =  MathUtil.encryptPassWordSHA(decodeStringPassword);//密码转换成加密字符串
		return userApiDaoHibernate.getUserInfoDTOByUserCodeAndPassWord(userCode, md5PassWord);
	}
	
	/** 
	 * FuncName : getOrganizationByUserId
	 * Description :  根据用户ID获取机构Id
	 * Create Date: 2011-10-18上午10:56:06 by yangx  
	 * Modified Date: 2011-10-18上午10:56:06 by yangx
	 * @param   userId：用户Id
	 * @return  Organization
	 * @Exception   
	 */
	public Organization getOrganizationByUserId(String userId) throws Exception{
		List<Organization> organizationList = userApiDaoHibernate.getOrganizationByUserId(userId);
		return (organizationList!=null&&organizationList.size()>0)?organizationList.get(0):null;
	}
}
