package com.gpcsoft.epp.common.service;
import java.util.List;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.Employee;
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
public interface UserApiService extends BaseGenericService{

	
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
	 * 当前用户是否包括指定的权限
	 * @param url
	 * @return
	 * Created at 2011-6-13 10:50 by zhouzhanghe
	 */
	public boolean currUserAuthContainsURL(String url);
	
	/**
	 * FuncName: getIsMemeberCurrOrg
	 * Description : 判断当前用户所属机构是否会员
	 * @return true:是会员,false:不是会员
	 * @author: zhouzhanghe
	 * @Create Date:2011-6-29  下午16:51:39
	 */
	public boolean getIsMemeberCurrOrg();

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
	 * Description :  根据用户ID获取对应的机构信息
	 * Create Date: 2010-6-21上午09:51:10 by Administrator  Modified Date: 2010-6-21上午09:51:10 by Administrator
	 * @param userId
	 * @return
	 */
	public OrgInfo getOrgInfoByUser(User user)throws Exception;

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
	 * Description :  根据员工Id获取员工的账号信息
	 * Create Date: 2010-11-8下午05:14:33 by yangx  Modified Date: 2010-11-8下午05:14:33 by yangx
	 * @param   empId：员工Id
	 * @return  User
	 * @Exception   
	 */
	public User getUserByEmpId(String empId);

	/** 
	 * Description :  根据查询条件查询相关部门信息
	 * Create Date: 2010-11-16上午10:23:04 by yangx  Modified Date: 2010-11-16上午10:23:04 by yangx
	 * @param   query：查询条件
	 * @return  List
	 */
	public List getDepartmentByQueryObject(QueryObject query);

	/** 
	 * Description :  获取设置的大宗代理机构
	 * Create Date: 2010-11-23上午10:22:59 by yangx  Modified Date: 2010-11-23上午10:22:59 by yangx
	 * @param   query:查询条件,page:分页对象
	 * @return  Page<OrgInfo>
	 * @Exception   
	 */
	public Page<OrgInfo> getAllAgentForBlockTrade(Page page);

	/** 
	 * Description :  保存代理机构
	 * Create Date: 2010-11-23下午01:07:21 by yangx  Modified Date: 2010-11-23下午01:07:21 by yangx
	 * @param   orgName：机构名称,orgInfoId：机构Id
	 * @return  OrgInfo
	 * @Exception   
	 */
	public OrgInfo saveAgentForBlockTrade(String orgName,String orgInfoId);

	/** 
	 * Description :  删除设置的大宗代理机构
	 * Create Date: 2010-11-23下午02:27:25 by yangx  Modified Date: 2010-11-23下午02:27:25 by yangx
	 * @param   orgInfoIds：机构Ids
	 * @return  
	 * @Exception   
	 */
	public void removeAgentForBlockTrade(String orgInfoIds);

	/** 
	 * Description :  根据机构Id过滤代理机构
	 * Create Date: 2010-11-23下午04:35:28 by yangx  Modified Date: 2010-11-23下午04:35:28 by yangx
	 * @param   orgInfoIds：要过滤的代理机构Id
	 * @return  List<OrgInfo>
	 * @Exception   
	 */
	public List<OrgInfo> getOrgInfoForAgent(String orgInfoIds);

	/** 
	 * Description :  从设置的大宗代理机构文件中查询代理机构
	 * Create Date: 2010-11-24上午11:47:30 by yangx  Modified Date: 2010-11-24上午11:47:30 by yangx
	 * @param   
	 * @return  List<OrgInfo>
	 * @Exception   
	 */
	public List<OrgInfo> getAgentForFile();
	
	
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
	public boolean findValidateUserByPssw(String userName, String password);
	
	/**
	 * 验证用户
	 * @param userName 用户名
	 * @param md5Password MD5密码
	 * @return
	 * Create Date: 2011-3-23 14:30 by zhouzh
	 */
	public boolean findValidateUserByMd5Pssw(String userName, String md5Password);
	
	
	/**
	 * Description :  根据机构Code获取此机构
	 * Create Date: 2011-3-16上午11:14:53 by zhouzhanghe  Modified Date: 2011-3-16上午11:14:53 by zhouzhanghe
	 * @param   
	 * @return  
	 * @Exception
	 */
	public OrgInfo getOrgInfoByOrgCode(String orgCode) throws Exception;
	
	
	/**
	 * FuncName: getOrgInfoByOrginfoId
	 * Description :  根据orginfoID获得orginfo对象
	 * @param 
	 * @param orginfoId
	 * @return OrgInfo
	 * @author: liuke
	 * @Create Date:2011-4-13  下午05:15:34
	 * @Modifier: liuke
	 * @Modified Date:2011-4-13  下午05:15:34
	 */
	public OrgInfo getOrgInfoById(String orgId);
	
	/**
	 * FuncName: getEmployeeById
	 * Description :  根据ID得到Employee对象
	 * @param 
	 * @param empId
	 * @return Employee
	 * @author: liuke
	 * @Create Date:2011-4-14  下午01:36:56
	 * @Modifier: liuke
	 * @Modified Date:2011-4-14  下午01:36:56
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
	 * Description :是否存在姓名的员工  
	 * Create Date: 2011-9-14上午09:43:42 by sunl  Modified Date: 2011-9-14上午09:43:42 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isExistEmployee(String name);
	
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
}
