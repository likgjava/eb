package com.gpcsoft.epp.common.dao.hibernate;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.HibernateUtils;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.dao.UserApiDao;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.UserInfoDTO;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Organization;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
@SuppressWarnings("unchecked")
public  class UserApiDaoHibernate extends BaseGenericDaoHibernate implements UserApiDao {

	/** 
	 * Description :  根据部门ID获取对应的所有员工信息
	 * Create Date: 2010-6-20下午04:55:34 by Administrator  Modified Date: 2010-6-20下午04:55:34 by Administrator
	 * @param depId
	 * @return
	 */
	public List<Employee> getEmpListByCurUserDepId(String depId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Employee t where t.department.objId=?");
		return this.getHibernateTemplate().find(hql.toString(), new Object[]{depId});
	}

	/** 
	 * Description :  根据机构ID获得该机构下面的所有员工信息
	 * Create Date: 2010-6-20下午05:14:13 by Administrator  Modified Date: 2010-6-20下午05:14:13 by Administrator
	 * @param objId
	 * @return
	 */
	public List<Employee> getEmpListByCurUserOrgId(String orgId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Employee t where t.company.objId=?");
		return this.findbyHql(hql.toString(), new Object[]{orgId});
	}

	/** 
	 * Description :  根据用户ID获取对应的部门信息
	 * Create Date: 2010-6-21上午09:51:10 by Administrator  Modified Date: 2010-6-21上午09:51:10 by Administrator
	 * @param userId
	 * @return
	 */
	public Department getDepartmentByUserId(String userId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t1 from User t0,Employee t,Department t1 where t0.emp.objId=t.objId and t.department.objId = t1.objId and t0.objId=?");
		List<Department> list = this.findbyHql(hql.toString(), new Object[]{userId});;
		if(list != null && !list.isEmpty()){
			return (Department)list.get(0);
		}else{
			return null;
		}
	}

	/** 
	 * Description :  根据员工ID获取员工同部门的所有员工
	 * Create Date: 2010-6-25下午05:40:57 by Administrator  Modified Date: 2010-6-25下午05:40:57 by Administrator
	 * @param empId
	 * @return
	 */
	public List<Employee> getAllEmpListByCurUserEmpId(String empId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Employee t where t.department.objId=(select t1.department.objId from Employee t1 where t1.objId=?)");
		return this.findbyHql(hql.toString(), new Object[]{empId});
	}

	
	/** 
	 * Description :  根据用户ID获取对应的机构信息
	 * Create Date: 2010-6-21上午09:51:10 by Administrator  Modified Date: 2010-6-21上午09:51:10 by Administrator
	 * @param userId
	 * @return
	 */
	public OrgInfo getOrgInfoByUserId(String userId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select ui.orgInfo from UserInfo ui where ui.objId=?");
		List<OrgInfo> list = this.findbyHql(hql.toString(), new String[]{userId});
		if(list != null && !list.isEmpty()){
			
			//由于未知的原因，查询总是代理，所以这里先临时解决 
			OrgInfo orgInfo = (OrgInfo)HibernateUtils.getObjectFromHibernateProxy(list.get(0));
			
			return orgInfo;
		}else{
			return null;
		}
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
		StringBuffer hql = new StringBuffer("select objId,orgName from OrgInfo where '-1'!="+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("agencyId")+" ");
		List list = this.findbyHql(hql.toString());
		list = HqlResultConvertUtils.hqlResultConvert(list, "objId,orgName".split(","), OrgInfo.class);
		return list;
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
		StringBuffer hql = new StringBuffer("select objId,orgName from OrgInfo where '-1'!="+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("supplierId")+" ");
		List list = this.findbyHql(hql.toString(), new String[]{});
		list = HqlResultConvertUtils.hqlResultConvert(list, "objId,orgName".split(","), OrgInfo.class);
		return list;
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
	public Page<Employee> searchEmployee(final QueryObject queryObject,
			final Page<Employee> page) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//查询分页记录
				StringBuffer preHql = new StringBuffer("from Employee t where 1=1 ");
				
				StringBuffer whereHql = new StringBuffer();
				
				List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
				if(queryList != null && !queryList.isEmpty()){
					for (int i = 0; i < queryList.size(); i++) {
						QueryParam queryParam = (QueryParam)queryList.get(i);
						if("name".equals(queryParam.getName())){
							whereHql.append(" and t.name like '"+(String)queryParam.getValue()+"' ");
						}
						if("number".equals(queryParam.getName())){
							whereHql.append(" and t.number like '"+(String)queryParam.getValue()+"' ");
						}
						if("company.objId".equals(queryParam.getName())){
							whereHql.append(" and company.objId = '"+(String)queryParam.getValue()+"' ");
						}
					}
				}
				whereHql.append(" order by t.createTime desc ");
				Query query = session.createQuery(preHql.toString() + whereHql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<Employee> employeeList=query.list();
				page.setData(employeeList);
				//查询总数
				String countHql = "select count(t.objId) from Employee t where 1=1 ";
				query = session.createQuery(countHql + whereHql.toString());
				page.setTotalRowNum((Long) query.list().get(0));
				return null;
		}});
		return page;
	}
	
	/** 
	 * Description :  根据公司Id获取员工列表
	 * Create Date: 2010-10-22下午04:16:09 by yangx  Modified Date: 2010-10-22下午04:16:09 by yangx
	 * @param   companyId：公司Id;empIds：员工Id
	 * @return  List<Employee>
	 * @Exception   
	 */
	public List<Employee> getEmpListByCompanyId(String companyId,String empIds){
		StringBuffer hql = new StringBuffer("From Employee t where t.company.objId='"+companyId+"'");
		if(companyId.equals("")){
			 hql = new StringBuffer("From Employee t where 1=1"); 
		}
		if (empIds!=null&&!"".equals(empIds)) {
			hql.append(" and t.objId not in('"+empIds+"')");
		}
		return this.findbyHql(hql.toString());
	}
	
	/** 
	 * Description :  根据orgInfoId查询组织结构信息
	 * Create Date: 2010-11-18下午03:35:01 by yangx  Modified Date: 2010-11-18下午03:35:01 by yangx
	 * @param   orgInfoId：组织结构Id
	 * @return  List<Organization>
	 * @Exception   
	 */
	public Company getOrganizationByOrgInfoId(String orgInfoId){
		String hql = "select t From Company t,OrgInfo o where t.objId=o.company.objId and o.objId='"+orgInfoId+"'";
		List<Company> list = this.findbyHql(hql);
		if (list!=null&&list.size()>0) {
			return (Company)list.get(0);
		}
		return null;
	}
	
	/** 
	 * Description :  根据机构Id过滤代理机构
	 * Create Date: 2010-11-23下午04:35:28 by yangx  Modified Date: 2010-11-23下午04:35:28 by yangx
	 * @param   orgInfoIds：要过滤的代理机构Id
	 * @return  List<OrgInfo>
	 * @Exception   
	 */
	public List<OrgInfo> getOrgInfoForAgent(String orgInfoIds){
		StringBuffer hql = new StringBuffer("select t From OrgInfo t where t.agencyId is not null");
		if (orgInfoIds!=null&&!"".equals(orgInfoIds)) {
			hql.append(" and t.objId not in ('"+orgInfoIds+"')");
		}
		List<OrgInfo> orgInfoList = this.findbyHql(hql.toString());
		return orgInfoList;
	}

	/** 
	 * Description : 根据代理机构ID查询代理机构信息
	 * Create Date: 2010-11-24上午11:46:14 by liuke  Modified Date: 2010-11-24上午11:46:14 by liuke
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Agency getAgentMessageByObjId(final String objId) {
		List agentList = (List) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("from Agency t where t.orgInfo.objId =:objId");
				Query query = session.createQuery(hql.toString());
				query.setString("objId", objId);
				return query.list();
			}});
		return (agentList.size()>0)?(Agency)agentList.get(0):null;
	}

	public boolean findValidateUser(String userName, String password) {
		List list = this.findbyHql("select u.objId from User u where u.usName = ? and u.password = ? ", new Object[]{userName, password});
		if(list != null && list.size() > 0 )
			return true;
		return false;
	}
	
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
	public Agency getAgency(String orgCode){
		StringBuffer hql = new StringBuffer("select t From Agency t where t.orgInfo.orgCode = '"+orgCode+"'");
		return (Agency) this.findbyHql(hql.toString()).get(0);
	}
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
	public Organization getOrganization(String orgCode){
		return null;
	}

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
	public OrgInfo getOrgInfoById(String orgId) {
		return (OrgInfo) this.get(orgId,OrgInfo.class);
		
	}

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
	public User getUserById(String userId) {
		return (User) this.get(userId, User.class);
	}

	
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
	public Employee getEmployeeById(String empId) {
		return (Employee) this.get(empId, Employee.class);
	}
	
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
	public void updateOrgInfo(final OrgInfo orgInfo) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
			 StringBuffer hql = new StringBuffer();
			 //hql.append("update OrgInfo o set o.memberClassId ='"+orgInfo.getMemberClassId()+"' where o.objId = '"+orgInfo.getObjId()+"'");
			 session.createQuery(hql.toString()).executeUpdate();	
			 return null;
			}
			
		});
		
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
	public UserInfoDTO getUserInfoDTOByUserCodeAndPassWord(final String userCode,
			final String password) {
	List list =	(List) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append("select us.USR_NAME,org.ORG_CODE,org.ORG_NAME,emp.EMP_NAME,us.USR_ID,sup.CORPORATION, sup.TRANSACTION_CERTNO from ORG_INFO org left join AUTH_ORG_EMPLOYEE emp on org.COMPANY_ID = emp.EMP_COMPANY_ID ");
				sql.append("left join AUTH_USER us on emp.EMP_ID = us.EMP_ID ");
				sql.append("left join SPL_SUPPLIER sup on org.SUPPLIER_ID = sup.SUPPLIER_ID  ");
				sql.append("where us.USR_NAME=:userCode and us.USR_PASSWORD=:password");
				Query query = session.createSQLQuery(sql.toString());
				return query.setString("userCode",userCode).setString("password", password).list();
			}});
			 UserInfoDTO userinfodto = new UserInfoDTO();
			 userinfodto.setUserCode("");
	    	 userinfodto.setOrgCode("");
	    	 userinfodto.setOrgName("");
	    	 userinfodto.setUserName("");
	    	 userinfodto.setUserId("");
	    	 userinfodto.setLegalDelegate("");
	    	 userinfodto.setTransactionCertNo("");
		     for(Iterator iterator = list.iterator(); iterator
				.hasNext();){
		    	 Object[] object = (Object[]) iterator.next();
		    	 if(object[0]!=null){
		    		 userinfodto.setUserCode(object[0].toString());
		    	 }
		    	 if(object[1]!=null){
		    		 userinfodto.setOrgCode(object[1].toString());
		    	 }
		    	 if(object[2]!=null){
		    		 userinfodto.setOrgName(object[2].toString());
		    	 }
		    	 if(object[3]!=null){
		    		 userinfodto.setUserName(object[3].toString());
		    	 }
		    	 if(object[4]!=null){
		    		 userinfodto.setUserId(object[4].toString());
		    	 }
		    	 if(object[5]!=null){
		    		 userinfodto.setLegalDelegate(object[5].toString());
		    	 }
		    	 if(object[6]!=null){
		    		 userinfodto.setTransactionCertNo(object[6].toString());
		    	 }
		     }
		return userinfodto;
	}
	
	
	
	
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
	public List<User> getUserByUserName(String userName,String password){
		List<User> list = this.findbyHql("from User u where u.usName = ? and u.password = ?", userName,password);
		return list;
	}
	
	/** 
	 * Description :根据姓名获取员工  
	 * Create Date: 2011-9-14上午09:43:42 by sunl  Modified Date: 2011-9-14上午09:43:42 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Employee> getEmployeeByName(final String name) {
		return (List<Employee>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("from Employee e where e.name =:name ");
				Query query = session.createQuery(hql.toString());
				query.setParameter("name", name);
				return query.list();
			}});
	}
	
	/** 
	 * Description :  根据机构ID获取组织机构
	 * Create Date: 2011-9-14上午10:29:34 by sunl  Modified Date: 2011-9-14上午10:29:34 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Company getOrgnizationByOrgInfoId(String orgInfoId){
		StringBuffer hql = new StringBuffer("select t.company From OrgInfo t where t.objId = '"+orgInfoId+"'");
		return (Company) this.findbyHql(hql.toString()).get(0);
	}
	
	/** 
	 * Description :  根据机构ID和员工名册获取员工
	 * Create Date: 2011-9-14上午10:29:34 by sunl  Modified Date: 2011-9-14上午10:29:34 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Employee getEmployeeByOrgAndName(String orgId,String name){
		StringBuffer hql = new StringBuffer();
		hql.append("from Employee e where e.company.objId='");
		hql.append(orgId).append("' and e.name='");
		hql.append(name).append("'");
		List list = this.findbyHql(hql.toString());
		if(list != null && list.size() !=0) {
			return (Employee) this.findbyHql(hql.toString()).get(0);
		} else {
			return null;
		}
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
	public List<Organization> getOrganizationByUserId(final String userId){
		return (List<Organization>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<Organization> doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer(" select * from auth_orgnization ao where ");
				hql.append(" ao.org_id in(");
				hql.append(" select e.emp_company_id from auth_org_employee e left join auth_user u on e.emp_id=u.emp_id");
				hql.append(" where u.usr_id = '"+userId+"')");
//				hql.append(" or");
//				hql.append(" ao.org_id in(");
//				hql.append(" select e.emp_department_id from auth_org_employee e left join auth_user u on e.emp_id=u.emp_id");
//				hql.append(" where u.usr_id = '"+userId+"')");
				Query query = session.createSQLQuery(hql.toString()).addEntity(Organization.class);
				return query.list();
		}});
	}
	
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
	public List<OrgInfo> getSuppliers2(String supplierIds,String orgName,String str) {
		StringBuffer hql1 = new StringBuffer();
		if(supplierIds!=null&&!"".equals(supplierIds)){
			hql1.append("select objId,orgName from OrgInfo where '-1'!="+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("supplierId")+" and objId in ("+supplierIds+") and orgName like '%"+orgName+"%'");
		}else{
			hql1.append("select objId,orgName from OrgInfo where '-1'!="+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("supplierId")+" and orgName like '%"+orgName+"%' and objId not in ("+str+")");
		}
		List list = this.findbyHql(hql1.toString(), new String[]{});
		list = HqlResultConvertUtils.hqlResultConvert(list, "objId,orgName".split(","), OrgInfo.class);
		return list;
	}

	/**
	 * FuncName: getEmployeeByResId
	 * Description :  查找拥有该资源的员工
	 * @param resId 资源ID
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-25 10:09
	 */
	public List<Employee> getEmployeeByResId(String resId){
		String sql = "select aoe.* from AUTH_ORG_EMPLOYEE aoe left join auth_user au on aoe.emp_id = au.emp_id left join auth_user_role aur on au.usr_id = aur.usr_id left join auth_role_resource arr on arr.rol_id = aur.rol_id where arr.res_id='"+resId+"'";
		return getSession().createSQLQuery(sql).addEntity(Employee.class).list();
	}

	/**
	 * 验证用户权限
	 * @param userId 用户ID
	 * @param resId 资源ID
	 * @return
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-25 10:59
	 */
	public boolean getValidateUserByUserIdAndResId(String userId, String resId) {
		String sql = "select arr.res_id from auth_role_resource  arr left join auth_user_role aur on arr.rol_id = aur.rol_id where aur.usr_id='"+userId+"' and arr.res_id='"+resId+"'";
		List result= getSession().createSQLQuery(sql).list();
		if(result != null && result.size() > 0)
			return true;
		return false;
	}

	/**
	 * FuncName: getEmployeeByOrgInfoId
	 * Description :  根据OrgInfoId查找员工
	 * @param orgInfoId 
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-26 08:40
	 */
	public List<Employee> getEmployeeByOrgInfoId(String orgInfoId) {
		String hql = "select e From Employee e,Company t,OrgInfo o where e.company.objId = o.company.objId and t.objId=o.company.objId and o.objId='"+orgInfoId+"'";
		List<Employee> list = this.findbyHql(hql);
		return list;
	}
	
}
