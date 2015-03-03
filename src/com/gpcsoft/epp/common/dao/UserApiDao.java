package com.gpcsoft.epp.common.dao;

import java.util.List;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.UserInfoDTO;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Organization;
import com.gpcsoft.srplatform.auth.domain.User;
@SuppressWarnings("unchecked")
public interface UserApiDao extends BaseGenericDao {

	/** 
	 * Description :  根据部门ID获取对应的所有员工信息
	 * Create Date: 2010-6-20下午04:55:34 by Administrator  Modified Date: 2010-6-20下午04:55:34 by Administrator
	 * @param depId
	 * @return
	 */
	public List<Employee> getEmpListByCurUserDepId(String depId);

	/** 
	 * Description :  根据机构ID获得该机构下面的所有员工信息
	 * Create Date: 2010-6-20下午05:14:13 by Administrator  Modified Date: 2010-6-20下午05:14:13 by Administrator
	 * @param objId
	 * @return
	 */
	public List<Employee> getEmpListByCurUserOrgId(String orgId);

	/** 
	 * Description :  根据用户ID获取对应的部门信息
	 * Create Date: 2010-6-21上午09:51:10 by Administrator  Modified Date: 2010-6-21上午09:51:10 by Administrator
	 * @param userId
	 * @return
	 */
	public Department getDepartmentByUserId(String userId);

	/** 
	 * Description :  根据员工ID获取员工同部门的所有员工
	 * Create Date: 2010-6-25下午05:40:57 by Administrator  Modified Date: 2010-6-25下午05:40:57 by Administrator
	 * @param empId
	 * @return
	 */
	public List<Employee> getAllEmpListByCurUserEmpId(String empId);

	/** 
	 * Description :  根据用户ID获取对应的机构信息
	 * Create Date: 2010-6-21上午09:51:10 by Administrator  Modified Date: 2010-6-21上午09:51:10 by Administrator
	 * @param userId
	 * @return
	 */
	public OrgInfo getOrgInfoByUserId(String userId);

	/**
	 * 
	 * Description :获得所有代理机构  
	 * Create Date: 2010-7-9下午04:15:11 by liuke  Modified Date: 2010-7-9下午04:15:11 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<OrgInfo> getAllAgents(QueryObject queryObject);
	
	
	/**
	 * 
	 * Description : 获得所有供应商列表
	 * Create Date: 2010-8-17上午09:57:30 by liuke  Modified Date: 2010-8-17上午09:57:30 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<OrgInfo> getAllSuppliers(QueryObject queryObject);
	  	
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
	public Page<Employee> searchEmployee(final QueryObject queryObject,final Page<Employee> page); 
	
	/** 
	 * Description :  根据公司Id获取员工列表
	 * Create Date: 2010-10-22下午04:16:09 by yangx  Modified Date: 2010-10-22下午04:16:09 by yangx
	 * @param   companyId：公司Id;empIds：员工Id
	 * @return  List<Employee>
	 * @Exception   
	 */
	public List<Employee> getEmpListByCompanyId(String companyId,String empIds);
	
	/** 
	 * Description :  根据orgInfoId查询组织结构信息
	 * Create Date: 2010-11-18下午03:35:01 by yangx  Modified Date: 2010-11-18下午03:35:01 by yangx
	 * @param   orgInfoId：组织结构Id
	 * @return  List<Organization>
	 * @Exception   
	 */
	public Company getOrganizationByOrgInfoId(String orgInfoId);
	
	/** 
	 * Description :  根据机构Id过滤代理机构
	 * Create Date: 2010-11-23下午04:35:28 by yangx  Modified Date: 2010-11-23下午04:35:28 by yangx
	 * @param   orgInfoIds：要过滤的代理机构Id
	 * @return  List<OrgInfo>
	 * @Exception   
	 */
	public List<OrgInfo> getOrgInfoForAgent(String orgInfoIds);
	
	
	
	/** 
	 * Description : 根据代理机构ID查询代理机构信息
	 * Create Date: 2010-11-24上午11:46:14 by liuke  Modified Date: 2010-11-24上午11:46:14 by liuke
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Agency getAgentMessageByObjId(String objId);
	
	/**
	 * 验证用户
	 * @param userName 用户名
	 * @param password 密码
	 * @return
	 * Create Date: 2011-3-23 14:30 by zhouzh
	 */
	public boolean findValidateUser(String userName, String password);
	
	/**
	 * FuncName: getOrg
	 * Description:根据orgCode获取orginfo对象 
	 * @param orgCode
	 * @return OrgInfo
	 * @author: shenjz
	 * @Create Date:2011-3-25  上午10:55:57
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-25  上午10:55:57
	 */
	public Agency getAgency(String orgCode);
	/**
	 * FuncName: getOrganization
	 * Description :  创建或修改对象
	 * @param orgCode
	 * @return Organization
	 * @author: shenjz
	 * @Create Date:2011-3-25  上午11:37:16
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-25  上午11:37:16
	 */
	public Organization getOrganization(String orgCode);
	
	
	/**
	 * FuncName: getOrgInfoById
	 * Description :  创建或修改对象
	 * @param 
	 * @param orgId
	 * @return OrgInfo
	 * @author: liuke
	 * @Create Date:2011-4-13  下午05:35:14
	 * @Modifier: liuke
	 * @Modified Date:2011-4-13  下午05:35:14
	 */
	public OrgInfo getOrgInfoById(String orgId);
	
	
	/**
	 * FuncName: getUserById
	 * Description :  根据ID获得
	 * @param 
	 * @param userId
	 * @return User
	 * @author: liuke
	 * @Create Date:2011-4-14  下午01:29:15
	 * @Modifier: liuke
	 * @Modified Date:2011-4-14  下午01:29:15
	 */
	public User getUserById(String userId);
	
	
	/**
	 * FuncName: getEmployeeById
	 * Description :  根据ID获得Employee对象
	 * @param 
	 * @param empId
	 * @return Employee
	 * @author: liuke
	 * @Create Date:2011-4-14  下午01:44:19
	 * @Modifier: liuke
	 * @Modified Date:2011-4-14  下午01:44:19
	 */
	public Employee getEmployeeById(String empId);
	
	/**
	 * FuncName: saveOrgInfo
	 * Description : 保存orginfo对象
	 * @param 
	 * @param orgInfo void
	 * @author: liuke
	 * @Create Date:2011-4-19  下午05:51:39
	 * @Modifier: liuke
	 * @Modified Date:2011-4-19  下午05:51:39
	 */
	public void updateOrgInfo(OrgInfo orgInfo);
	
	
	
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
	public UserInfoDTO getUserInfoDTOByUserCodeAndPassWord(String userCode,String password);
	
	
	/**
	 * FuncName: getUserByUserName
	 * Description :  根据用户名获得用户对象
	 * @param 
	 * @param userName void
	 * @author: liuke
	 * @Create Date:2011-5-20  上午09:12:16
	 * @Modifier: liuke
	 * @Modified Date:2011-5-20  上午09:12:16
	 */
	public List<User> getUserByUserName(String userName,String password);

	/** 
	 * Description :根据姓名获取员工  
	 * Create Date: 2011-9-14上午09:43:42 by sunl  Modified Date: 2011-9-14上午09:43:42 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Employee> getEmployeeByName(final String name);
	
	/** 
	 * Description :  根据机构ID获取组织机构
	 * Create Date: 2011-9-14上午10:29:34 by sunl  Modified Date: 2011-9-14上午10:29:34 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Company getOrgnizationByOrgInfoId(String orgInfoId);
	
	/** 
	 * Description :  根据机构ID和员工名册获取员工
	 * Create Date: 2011-9-14上午10:29:34 by sunl  Modified Date: 2011-9-14上午10:29:34 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Employee getEmployeeByOrgAndName(String orgId,String name);
	
	/** 
	 * FuncName : getOrganizationByUserId
	 * Description :  根据用户ID获取机构Id
	 * Create Date: 2011-10-18上午10:56:06 by yangx  
	 * Modified Date: 2011-10-18上午10:56:06 by yangx
	 * @param   userId：用户Id
	 * @return  Organization
	 * @Exception   
	 */
	public List<Organization> getOrganizationByUserId(String userId);
	
	/**
	 * FuncName: getSuppliers2
	 * Description :  获取供应商
	 * @param queryObject
	 * @return List<OrgInfo>
	 * @author: shenjz
	 * @Create Date:2011-11-8  下午04:05:00
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-8  下午04:05:00
	 */
	public List<OrgInfo> getSuppliers2(String supplierIds,String orgName,String str);

	/**
	 * FuncName: getEmployeeByResId
	 * Description :  查找拥有该资源的员工
	 * @param resId 资源ID
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-25 10:09
	 */
	public List<Employee> getEmployeeByResId(String resId);

	/**
	 * 验证用户权限
	 * @param userId 用户ID
	 * @param resId 资源ID
	 * @return
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-25 10:59
	 */
	public boolean getValidateUserByUserIdAndResId(String userId, String resId);

	/**
	 * FuncName: getEmployeeByOrgInfoId
	 * Description :  根据OrgInfoId查找员工
	 * @param orgInfoId 
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-26 08:40
	 */
	public List<Employee> getEmployeeByOrgInfoId(String orgInfoId);
	
	
}
