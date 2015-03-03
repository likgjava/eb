package com.gpcsoft.bizplatform.organization.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.srplatform.auth.domain.Company;

@Repository
public class OrgInfoDaoHibernate extends BaseGenericDaoHibernate<OrgInfo> implements OrgInfoDao {
	
		/** 
	 * Description :  获得代理机构下拉列表
	 * Create Date: 2010-9-26下午10:11:49 by sunl  Modified Date: 2010-9-26下午10:11:49 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getAgencies() throws Exception {
		Query query = this.getSession().createSQLQuery("select t.org_info_id,t.org_name from Org_Info t where t.agency_Id is not null");
		List<String[]> result = query.list();
		return result;
	}
	
	/** 
	 * Description :  查询待审核的机构信息列表
	 * 				  查询条件为：auditStatus=01(待审核)
	 * 				  或者 supplierStatus=01 or buyersTatus=01 or agencyTatus=01
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<OrgInfo> listOrgInfoForAudit(Page<OrgInfo> page,Map<String,Object> param) throws Exception {
		StringBuilder hql = new StringBuilder();
		String temp = "select count(o.objId) ";
		hql.append(" from OrgInfo o where 1 = 1 ");//by yucy and o.auditStatus = '01'
		//供应商审核
		if(StringUtils.hasLength((String)param.get("type")) && "SUPPLIER".equals((String)param.get("type"))) {
			hql.append(" and o.supplierId in (select g.objId from Supplier g where g.auditStatus = '01') and o.auditStatus in ('01','02')");
		}else if(StringUtils.hasLength((String)param.get("type")) && "BUYER".equals((String)param.get("type"))) {
			hql.append(" and o.buyerId in (select b.objId from Buyer b where b.auditStatus = '01') and o.auditStatus in ('01','02')");
		}else if(StringUtils.hasLength((String)param.get("type")) && "AGENCY".equals((String)param.get("type"))) {
			hql.append(" and o.agencyId in (select a.objId from Agency a where a.auditStatus = '01') and o.auditStatus in ('01','02')");
		}else {
			hql.append(" and o.auditStatus = '01' ");//by yucy and o.auditStatus = '01'放在这里
		}
		if(StringUtils.hasLength((String)param.get("orgName"))){
			if("_".equals((String)param.get("orgName").toString().replaceAll(" ", ""))
					|| "%".equals((String)param.get("orgName").toString().replaceAll(" ", ""))) {
				//处理输入“_””%“的情况
				hql.append("and o.orgName like '%$_%' escape '$'");
			} else {
				hql.append("and o.orgName like '%" + (String)param.get("orgName") + "%'");
			}
		}
		if(StringUtils.hasLength((String)param.get("auditType"))){
			hql.append("and o.auditType = '" + param.get("auditType") + "'");
		}
		hql.append(" and o.useStatus != '").append(OrganizationEnum.USE_INVALID).append("'");
		String order = " order by o.createTime desc ";
		if(StringUtils.hasLength((String)param.get("order"))){
			order = param.get("order").toString();
			hql.append(" order by ").append(order);
			if(StringUtils.hasLength((String)param.get("order_flag"))){
				hql.append(((String)param.get("order_flag")).equals("true") ? " desc ":" asc ");
			}
		}else{
			hql.append(order);
		}
		Query queryCount = this.getSession().createQuery(temp + hql.toString());
		Query query = this.getSession().createQuery(hql.toString());
		query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
		Long totalRow = (Long)queryCount.list().get(0);
		List list = query.list();
		Page<OrgInfo> pageData = new Page<OrgInfo>(page.getStart(),totalRow,page.getPageSize(),list );
		return pageData;	
	}
	
	/** 
	 * Description :  根据companyId，获得最新的orgInfo。
	 * 				  查询条件为根据companyId获取创建时间最新的orginfo信息 
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getLastedOrgInfo(String companyId,boolean isGetValid) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append(" from OrgInfo t left join fetch t.company where 1=1 ");
		
		List<OrgInfo> list1 = new ArrayList<OrgInfo>();
		List<OrgInfo> list2 = new ArrayList<OrgInfo>();
		
		if(isGetValid){//获取有效机构
			hql.append(" and t.company.objId = ? and t.useStatus = '").append(OrganizationEnum.USE_VALID).append("' ");
			list1 = this.findbyHql(hql.toString(),companyId);
		}else{
			hql.append(" and t.createTime = (select max(w.createTime) from OrgInfo w where w.company.objId = ?) ");
			list2 = this.findbyHql(hql.toString(),companyId);
		}
		
		OrgInfo orgInfo = null;
		
		//如果没有有效机构就获取最新机构
		if(isGetValid){
			if(list1.size() > 0){
				orgInfo = (OrgInfo)list1.get(0);
			}else{
				String sql = "from OrgInfo t left join fetch t.company where 1=1  and t.createTime = (select max(w.createTime) from OrgInfo w where w.company.objId = ?) ";
				list2 = this.findbyHql(sql,companyId);
				if (list2.size() > 0) {
					orgInfo = (OrgInfo)list2.get(0);
				}
			}
		}else{
			if(list2 != null && list2.size() > 0) {
				orgInfo = (OrgInfo)list2.get(0);
			}
		}
		
		return orgInfo;
	}
	
	/** 
	 * Description :  更新原来的orgInfo对象里的currentId为最新的orgInfoId
	 * 				  条件为所有companyId为传入参数值的orgInfo对象
	 * Create Date: 2010-7-23上午11:28:31 by sunl  Modified Date: 2010-7-23上午11:28:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateCurrentId(String orgInfoId, String newOrgInfoId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append(" update OrgInfo t set t.currentId =:newOrgInfoId ");
		hql.append(" where t.objId =:orgInfoId or t.currentId =:orgInfoId");	
		
		Query query = super.getSession().createQuery(hql.toString());
		query.setString("newOrgInfoId", newOrgInfoId);
		query.setString("orgInfoId", orgInfoId);
		return query.executeUpdate();
	}
	
	/** 
	 * Description :  开启或禁用机构下用户
	 * Create Date: 2010-8-2上午11:39:32 by sunl  Modified Date: 2010-8-2上午11:39:32 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void disableOrEnableUser(String companyId,String userIsLocked) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append(" update User t ");
		hql.append(" set t.usrIsLocked =:usrIsLocked ");
		hql.append(" where t.emp.objId in ");
		hql.append(" (select e.objId from Employee e, User u where e.objId = u.emp.objId and e.company.objId =:companyId)");
		
		Query query = this.getSession().createQuery(hql.toString());
		query.setString("usrIsLocked", userIsLocked);
		query.setString("companyId", companyId);
		query.executeUpdate();
	}
	
	/** 
	 * Description :  根据组织机构的Id来查询orgInfo的信息
	 * Create Date: 2010-7-29下午06:16:11 by yucy  Modified Date: 2010-7-29下午06:16:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public OrgInfo getOrgInfoByCompanyId(final String companyId) {
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="from OrgInfo t where t.company.objId=:companyId";
				Query query = session.createQuery(hql);
				query.setParameter("companyId", companyId);
				return query.list();
		}});
		OrgInfo orgInfo = null;
		if(list!=null&&list.size()>0){
			orgInfo = (OrgInfo)list.get(0);
		}
		return orgInfo;
	}
	
	/** 
	 * Description :  获取机构历史信息
	 * Create Date: 2010-7-28下午02:12:00 by sunl  Modified Date: 2010-7-28下午02:12:00 by sunl
	 * @param   
	 * @return	
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<OrgInfo> getOrgHistory(final Map<String,Object> param) throws Exception {
		List<OrgInfo> list = (List<OrgInfo>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				Query query = null;
				hql.append("from OrgInfo t where t.objId=:id or t.currentId=:id");
				query = session.createQuery(hql.toString());
				query.setParameter("id", param.get("id"));
				return query.list();
		}});
		return list;
	}
	
	/**
	 * 
	 * Description :  验证机构代码和机构名称的唯一
	 * Create Date: 2010-8-24下午08:44:39 by sunl  Modified Date: 2010-8-24下午08:44:39 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public Integer checkOrgUnique(final Map<String,Object> param) throws Exception {
		Integer result = (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				Query query = null;
				sql.append("select count(*) from org_info t where 1 = 1 ");
				if(StringUtils.hasLength((String)param.get("orgCode"))){
					sql.append(" and t.org_code = '").append(param.get("orgCode").toString()).append("' ");
				}
				if(StringUtils.hasLength((String)param.get("orgName"))){
					sql.append(" and t.org_name = '").append(param.get("orgName").toString()).append("' ");
				}
				
				if(StringUtils.hasLength((String)param.get("id"))) {
					sql.append(" and t.org_info_id <> :id ");
					sql.append(" and ( t.current_valid_id <> :id or t.current_valid_id is null)");
				}
				
				//验证类型(供应商还是采购人)
//				if(StringUtils.hasLength((String)param.get("type"))) {
//					if("supplier".equals((String)param.get("type"))) {
//						sql.append(" and t.supplier_id is not null ");
//					}else if("buyer".equals((String)param.get("type"))) {
//						sql.append(" and t.buyer_id is not null ");
//					}
//				}
				
				query = session.createSQLQuery(sql.toString());
				if(StringUtils.hasLength((String)param.get("id"))) {
					query.setParameter("id", param.get("id"));
				}
				return ((BigDecimal)query.list().get(0)).intValue();
		}});
		return result;
	}
	
	/**
	 * 
	 * Description :  模糊检验机构名称
	 * Create Date: 2010-8-24下午08:44:39 by sunl  Modified Date: 2010-8-24下午08:44:39 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> checkOrgName(final Map<String,Object> param) throws Exception {
		return (List<String>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					StringBuilder hql = new StringBuilder();
					hql.append("select t.orgName from OrgInfo t where 1=1 ");
					
					if(StringUtils.hasLength((String)param.get("orgName"))) {
						hql.append("and t.orgName like :orgName ");
					}
					Query query = session.createQuery(hql.toString());
					
					if(StringUtils.hasLength((String)param.get("orgName"))) {
						query.setParameter("orgName","%" + (String)param.get("orgName") + "%");
					}
					
					return query.list();
				}
		});
	}
	
	/** 
	 * Description :  根据主键，获得机构的详细信息，包括供应商信息，采购人信息，代理机构信息
	 * 				  机构资质，成功案例，评价信息等
	 * Create Date: 2010-8-26下午06:48:28 by sunl  Modified Date: 2010-8-26下午06:48:28 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgAllInfo(final String objId) throws Exception {
		List<OrgInfo> list = getOrgAllInfoList(objId);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	/** 
	 * Description :  根据主键，获得机构的详细信息，包括供应商信息，采购人信息，代理机构信息
	 * 				  机构资质，成功案例，评价信息等
	 * Create Date: 2010-8-26下午06:50:25 by sunl  Modified Date: 2010-8-26下午06:50:25 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<OrgInfo> getOrgAllInfoList(final String objIds) throws Exception {
		return (List<OrgInfo>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				//查询机构信息
				StringBuilder hql = new StringBuilder();
				
				hql.append("from OrgInfo as oi left join fetch oi.company ");
				hql.append("left join fetch oi.qualitys as qu ");
				hql.append("left join fetch oi.successCases as sc ");
				hql.append("left join fetch oi.evaluates ev ");
				hql.append("where oi.objId in (:objId) ");
				
				Query query = session.createQuery(hql.toString());
				query.setParameterList("objId", objIds.split(","));
				
				Set<OrgInfo> orgInfoSet = new HashSet<OrgInfo>(query.list());
				List<OrgInfo> orgInfoList = new ArrayList<OrgInfo>();
				orgInfoList.addAll(orgInfoSet);
				return orgInfoList;
		}});
	}
	
	/** 
	 * Description : 根据机构Id 获得机构的管理员  
	 * Create Date: 2010-10-29下午02:47:21 by guoyr  Modified Date: 2010-10-29下午02:47:21 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public String[] getAllManagersByOrgInfoIds(final String userType,final String objIds) throws Exception {
		return (String[]) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuilder hql = new StringBuilder();
				hql.append("select u.objId from User u where u.usrIsAdmin=:usrIsAdmin ");
				hql.append(" and u.emp.company.objId in (select o.company.objId from OrgInfo o where o.objId in (:objId) and o.useStatus =:useStatus)");
				Query query = session.createQuery(hql.toString());
				query.setParameter("usrIsAdmin", userType);
				query.setParameter("useStatus", OrganizationEnum.USE_VALID);
				query.setParameterList("objId", objIds.split(","));
				List<String> list = query.list();
				String[] users = new String[list.size()];
				int i = 0;
				for(String objId : list){
					users[i++] = objId;
				}
				return users;
			}});
	}

	/** 
	 * Description :  查询是否通过审核
	 * Create Date: 2010-11-10下午06:41:44 by yucy  Modified Date: 2010-11-10下午06:41:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean getIsAuditPass(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				String hql = " select o.useStatus,o.auditStatus " +
						" from OrgInfo o where o.objId = :orgId " +
						" and o.useStatus = :useStatus " +
						" and o.auditStatus = :auditStatus ";
				Query query = session.createQuery(hql);
				query.setParameter("orgId", param.get("orgId"));
				query.setParameter("useStatus", OrganizationEnum.USE_VALID);
				query.setParameter("auditStatus", OrganizationEnum.APPLY_AUDIT);
				return query.list().size()>0?true:false;
			}});
	}
	
	/** 
	 * Description :  根据供应商名称查询供应商列表
	 * Create Date: 2010-11-22下午03:40:51 by guoyr  Modified Date: 2010-11-22下午03:40:51 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<OrgInfo> getAllOrgInfoByOrgName(final String orgName){
		List<String[]> list = (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuilder hql = new StringBuilder();
				hql.append("select o.objId,o.orgName,o.supplierId from OrgInfo o where o.supplierId is not null and o.useStatus =:useStatus");
				
				// 根据机构名称查询
				if(null != orgName && !"".equals(orgName)){
					hql.append(" and o.orgName like :orgName");
				}
				
				// 只查询已启用的
				hql.append(" and o.isOff =:isOff");
				hql.append("  order by o.createTime desc");
				Query query = session.createQuery(hql.toString());
				query.setString("isOff", OrganizationEnum.ENABLE);
				query.setString("useStatus", OrganizationEnum.USE_VALID);
				if(null != orgName && !"".equals(orgName)){
					query.setParameter("orgName","%" + orgName + "%");
				}
				return query.list();
			}
		});
		List<OrgInfo> orgInfoList = new ArrayList<OrgInfo>();
		for(Object[] obj : list){
			OrgInfo orgInfo = new OrgInfo();
			orgInfo.setObjId((String)obj[0]);
			orgInfo.setOrgName((String)obj[1]);
			orgInfo.setSupplierId((String)obj[2]);
			orgInfoList.add(orgInfo);
		}
		return orgInfoList;
	}
	
	/** 
	 * Description :  插入/更新auth_orgnizaiton数据
	 * Create Date: 2011-10-25上午11:54:17 by sunl  Modified Date: 2011-10-25上午11:54:17 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer saveCompany(final Company company,final String saveType) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder insert_sql = new StringBuilder();
				StringBuilder update_sql = new StringBuilder();
				Integer result = 0;
				Query query = null;
				
				if("save".equals(saveType)) {//新增
					insert_sql.append("insert into auth_orgnization (org_id,org_name,org_type,org_status,org_audit_status,CREATE_TIME,org_tel) ");
					insert_sql.append("values ('");
					insert_sql.append(company.getObjId());
					insert_sql.append("','").append(company.getName());
					insert_sql.append("','").append(company.getType());
					insert_sql.append("','").append(company.getStatus());
					insert_sql.append("','").append(company.getAuditStatus());
					insert_sql.append("',sysdate,'").append(company.getTel()).append("')");
					query = session.createSQLQuery(insert_sql.toString());
				} 
				if("update".equals(saveType)) {
					update_sql.append("update auth_orgnization t set t.org_level=t.org_level ");
					if(StringUtils.hasLength(company.getName())) {
						update_sql.append(",t.org_name='").append(company.getName()).append("' ");
					}
					if(StringUtils.hasLength(company.getType())) {
						update_sql.append(",t.org_type='").append(company.getType()).append("' ");
					}
					if(StringUtils.hasLength(company.getStatus())) {
						update_sql.append(",t.org_status='").append(company.getStatus()).append("' ");
					}
					if(StringUtils.hasLength(company.getAuditStatus())) {
						update_sql.append(",t.org_audit_status='").append(company.getAuditStatus()).append("' ");
					}
					if(StringUtils.hasLength(company.getTel())) {
						update_sql.append(",t.org_tel='").append(company.getTel()).append("' ");
					}
					update_sql.append(" where t.org_id='").append(company.getObjId()).append("'");
					query = session.createSQLQuery(update_sql.toString());
				}
				
				if(query != null) {
					result=query.executeUpdate();
				}
				return result;
			}
		});
	}
	
	/** 
	 * Description :  插入/更新org_info数据
	 * Create Date: 2011-10-25上午11:54:17 by sunl  Modified Date: 2011-10-25上午11:54:17 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer saveOrgInfo(final OrgInfo orgInfo,final String saveType) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder insert_sql = new StringBuilder();
				StringBuilder update_sql = new StringBuilder();
				Integer result = 0;
				Query query = null;
				
				if("save".equals(saveType)) {//新增
					insert_sql.append("insert into org_info (org_info_id,org_code,org_name,company_id,audit_status,use_status,is_off,valid_date,ismanufacturer,main_products,CREATE_TIME) ");
					insert_sql.append("values ('").append(orgInfo.getObjId()).append("','").append(orgInfo.getOrgCode()).append("','").append(orgInfo.getOrgName()).append("','").append(orgInfo.getCompany().getObjId()).append("','").append(orgInfo.getAuditStatus()).append("','").append(orgInfo.getUseStatus()).append("','1',sysdate,'0','").append(orgInfo.getMainProducts()).append("',sysdate)");
					query = session.createSQLQuery(insert_sql.toString());
				} 
				if("update".equals(saveType)) {
					update_sql.append("update org_info t set t.creator=t.creator ");
					if(StringUtils.hasLength(orgInfo.getOrgCode())) {
						update_sql.append(",t.org_code='").append(orgInfo.getOrgCode()).append("'");
					}
					if(StringUtils.hasLength(orgInfo.getOrgName())) {
						update_sql.append(",t.org_name='").append(orgInfo.getOrgName()).append("'");
					}
					if(StringUtils.hasLength(orgInfo.getMainProducts())) {
						update_sql.append(",t.main_products='").append(orgInfo.getMainProducts()).append("'");
					}
					update_sql.append(" where t.org_id='").append(orgInfo.getObjId()).append("'");
					query = session.createSQLQuery(update_sql.toString());
				}
				
				if(query != null) {
					result=query.executeUpdate();
				}
				return result;
			}
		});
	}

	/** 
	 * Description :  根据当前组织机构code获得orgInfo
	 * Create Date: 2011-12-9下午02:30:10 by yucy  Modified Date: 2011-12-9下午02:30:10 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public OrgInfo getOrgInfoByOrgCode(final String orgCode) throws Exception {
		return (OrgInfo) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException {
				String hql = " from OrgInfo o where o.orgCode='"+orgCode+"'";
				Query query = session.createQuery( hql);		
				List result = query.list();
				return result!=null&& result.size()>0 ? result.get(0):null;
			}
		});
	}
}
