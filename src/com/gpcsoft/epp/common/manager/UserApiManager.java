package com.gpcsoft.epp.common.manager;
import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.UserInfoDTO;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Organization;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>UserApiService</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-6-20 下午04:55:26 by Administrator    					                            
  *  <br/>Modified Date:  2010-6-20 下午04:55:26 by Administrator  
  */ 
@SuppressWarnings("unchecked")
public interface UserApiManager extends BaseManager{

	
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
	 * Description :  根据用户ID获取对应的机构信息
	 * Create Date: 2010-6-21上午09:51:10 by Administrator  Modified Date: 2010-6-21上午09:51:10 by Administrator
	 * @param userId
	 * @return
	 */
	public OrgInfo getOrgInfoByUserId(String userId);


	/** 
	 * Description :  根据员工ID获取员工同部门的所有员工
	 * Create Date: 2010-6-25下午05:40:57 by Administrator  Modified Date: 2010-6-25下午05:40:57 by Administrator
	 * @param empId
	 * @return
	 */
	public List<Employee> getAllEmpListByCurUserEmpId(String empId);
	
	/** 
	 * Description :  根据当前用户判断角色类型
	 * Create Date: 2010-6-18下午05:27:46 by Administrator  Modified Date: 2010-6-18下午05:27:46 by Administrator
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String checkRole(OrgInfo orgInfo)throws Exception;
	
	
	
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
	 * Description :  根据员工Id获取员工的账号信息
	 * Create Date: 2010-11-8下午05:14:33 by yangx  Modified Date: 2010-11-8下午05:14:33 by yangx
	 * @param   empId：员工Id
	 * @return  User
	 * @Exception   
	 */
	public User getUserByEmpId(String empId);
	
	/** 
	 * Description :  根据orgInfoId查询组织结构信息
	 * Create Date: 2010-11-18下午03:35:01 by yangx  Modified Date: 2010-11-18下午03:35:01 by yangx
	 * @param   orgInfoId：组织结构Id
	 * @return  List<Organization>
	 * @Exception   
	 */
	public Company getOrganizationByOrgInfoId(String orgInfoId);
	
	/** 
	 * Description :  从设置的大宗代理机构文件中查询代理机构
	 * Create Date: 2010-11-24上午11:47:30 by yangx  Modified Date: 2010-11-24上午11:47:30 by yangx
	 * @param   
	 * @return  List<OrgInfo>
	 * @Exception   
	 */
	public List<OrgInfo> getAgentForFile();
	
	
	
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
	public User getUserByObjId(String objId);
	
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
	public String getRolesByUserId(String userId);
	
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
	 * FuncName : getOrganizationByUserId
	 * Description :  根据用户ID获取机构Id
	 * Create Date: 2011-10-18上午10:56:06 by yangx  
	 * Modified Date: 2011-10-18上午10:56:06 by yangx
	 * @param   userId：用户Id
	 * @return  Organization
	 * @Exception   
	 */
	public Organization getOrganizationByUserId(String userId) throws Exception;
}
