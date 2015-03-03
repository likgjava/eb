package com.gpcsoft.bizplatform.agency.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.bizplatform.agency.dao.AgencyDao;
import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class AgencyDaoHibernate extends BaseGenericDaoHibernate<Agency> implements AgencyDao {
	
	/** 
	 * Description :  根据orgInfoId获取Buyer
	 * Create Date: 2011-10-24下午06:23:23 by sunl  Modified Date: 2011-10-24下午06:23:23 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Agency getAgentByOrgInfoId(final String orgInfoId) throws Exception {
		return (Agency) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "from Agency a where a.orgInfo.objId=:orgInfoId ";
				Query query = session.createQuery(hql);
				query.setString("orgInfoId", orgInfoId);
				List list = query.list();
				if(list != null && list.size() > 0)
					return list.get(0);
				return null;
		}});
	}
	
	/** 
	 * Description :  更新代理机构审核状态
	 * Create Date: 2010-7-28下午02:50:03 by sunl  Modified Date: 2010-7-28下午02:50:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer updateAgencyStatus(final String agencyId,final OrgInfo orgInfo) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append(" update Agency au set au.auditStatus=:auditStatus ,au.verifyUser.objId=:verifyUserId,au.verifyTime=:verifyTime,au.opinion=:opinion where au.objId=:objId");
				Query query = session.createQuery(hql.toString());
				query.setString("auditStatus", orgInfo.getAuditStatus());
				query.setString("objId", agencyId);
				
				query.setString("verifyUserId", AuthenticationHelper.getCurrentUser(true).getObjId());
				query.setDate("verifyTime", new Date());
				query.setString("opinion", orgInfo.getOpinion());
				
				return query.executeUpdate();
			}
		});
	}
	
	/** 
	 * Description :  根据参数获得代理机构的展示信息列表，及时加载OrgInfor信息
	 * Create Date: 2010-8-9下午01:44:06 by liangxj  Modified Date: 2010-8-9下午01:44:06 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含行政级别、代理机构类型、排序信息
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Agency> getAgencyListForShow(final Page<Agency> page,final Map<String, Object> paramsMap) throws Exception  {
		return (Page<Agency>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String validSort = (String) paramsMap.get("validSort"); //生效时间排序
				String evalSort = (String) paramsMap.get("evalSort"); //评价排序
				String adminGrd = (String) paramsMap.get("adminGrd"); //行政级别
				String agentType = (String) paramsMap.get("agentType"); //代理机构类型
				String keyWord = (String) paramsMap.get("keyWord"); //关键字
				
				//查询代理机构信息
				String preHql = "from Agency as s left join fetch s.orgInfo as o " +
									"where s.auditStatus='"+OrganizationEnum.PASS_EXAM+"' and o.useStatus = '"+OrganizationEnum.USE_VALID+"' and o.isOff = '"+OrganizationEnum.ENABLE+"' ";
				
				StringBuilder hql = new StringBuilder();
				
				//行政级别过滤
				if(StringUtils.hasLength(adminGrd)){
					hql.append(" and s.adminGrd = '").append(adminGrd).append("' ");
				}
				
				//代理机构类型
				if(StringUtils.hasLength(agentType)){
					hql.append(" and s.agentType = '").append(agentType).append("' ");
				}
				
				
				//高级搜索条件
				StringBuilder highparam = new StringBuilder();
				if(StringUtils.hasLength((String)paramsMap.get("regToDateStart"))){
					highparam.append(" and s.regToDate >= ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date((String)paramsMap.get("regToDateStart")));
				}
				if(StringUtils.hasLength((String)paramsMap.get("regToDateEnd"))){
					highparam.append(" and s.regToDate <= ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date((String)paramsMap.get("regToDateEnd")));
				}
				if(StringUtils.hasLength((String)paramsMap.get("unitType"))){
					highparam.append(" and s.unitType = '").append((String)paramsMap.get("unitType")).append("' ");
				}
				if(StringUtils.hasLength((String)paramsMap.get("regIso"))){
					highparam.append(" and s.regIso = '").append("on".equals((String)paramsMap.get("regIso"))?"1":"0").append("' ");
				}
				if(StringUtils.hasLength((String)paramsMap.get("prctTotalNmbrLeft"))){
					highparam.append(" and s.prctTotalNmbr > ").append((String)paramsMap.get("prctTotalNmbrLeft"));
				}
				if(StringUtils.hasLength((String)paramsMap.get("prctTotalNmbrRight"))){
					highparam.append(" and s.prctTotalNmbr < ").append((String)paramsMap.get("prctTotalNmbrRight"));
				}
				if(StringUtils.hasLength((String)paramsMap.get("regPrctsNmbrLeft"))){
					highparam.append(" and s.regPrctsNmbr > ").append((String)paramsMap.get("regPrctsNmbrLeft"));
				}
				if(StringUtils.hasLength((String)paramsMap.get("regPrctsNmbrRight"))){
					highparam.append(" and s.regPrctsNmbr < ").append((String)paramsMap.get("regPrctsNmbrRight"));
				}
				if(StringUtils.hasLength((String)paramsMap.get("highTitleTchstNmbrLeft"))){
					highparam.append(" and s.highTitleTchstNmbr > ").append((String)paramsMap.get("highTitleTchstNmbrLeft"));
				}
				if(StringUtils.hasLength((String)paramsMap.get("highTitleTchstNmbrRight"))){
					highparam.append(" and s.highTitleTchstNmbr < ").append((String)paramsMap.get("highTitleTchstNmbrRight"));
				}
				if(StringUtils.hasLength((String)paramsMap.get("middleTitleTchstNmbrLeft"))){
					highparam.append(" and s.middleTitleTchstNmbr > ").append((String)paramsMap.get("middleTitleTchstNmbrLeft"));
				}
				if(StringUtils.hasLength((String)paramsMap.get("middleTitleTchstNmbrRight"))){
					highparam.append(" and s.middleTitleTchstNmbr < ").append((String)paramsMap.get("middleTitleTchstNmbrRight"));
				}
				//关键字(主营,机构名称,简介 ,评标地址)
				if(StringUtils.hasLength(keyWord)){
					highparam.append(" and (s.mianBussScp like '%").append(keyWord).append("%'");
					highparam.append(" or s.orgInfo.orgName like '%").append(keyWord).append("%'");
					highparam.append(" or s.unitDesc like '%").append(keyWord).append("%'");
					highparam.append(" or s.bidPrpsEvltAddr like '%").append(keyWord).append("%')");
				}
				if(StringUtils.hasLength((String)paramsMap.get("totalAssetsLeft"))){
					highparam.append(" and s.totalAssets < ").append((String)paramsMap.get("totalAssetsLeft"));
				}
				if(StringUtils.hasLength((String)paramsMap.get("totalAssetsRight"))){
					highparam.append(" and s.totalAssets = ").append((String)paramsMap.get("totalAssetsRight"));
				}
				
				
				hql.append(highparam.toString());
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				if(StringUtils.hasLength(validSort) || StringUtils.hasLength(evalSort)){
					orderHql.append(" order by ");
					if(StringUtils.hasLength(evalSort)) {
						orderHql.append("s.evalSum ").append(evalSort).append(",");
					}
					if(StringUtils.hasLength(validSort)) {
						orderHql.append("s.orgInfo.validTime ").append(validSort);
					}
					
					if(orderHql.toString().endsWith(",")) {
						orderHql.delete(orderHql.length()-1, orderHql.length());
					}
				}
				
				Query query = session.createQuery(preHql + hql + orderHql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
		
				List<Agency> agencyList=query.list();
				page.setData(agencyList);
				
				//查询总数
				preHql = "select count(s.objId) from Agency s where s.auditStatus='"+OrganizationEnum.PASS_EXAM+"' and s.orgInfo.useStatus = '"+OrganizationEnum.USE_VALID+"' and s.orgInfo.isOff = '"+OrganizationEnum.ENABLE+"' ";
				query = session.createQuery(preHql + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				return page;
		}});
	}
	
	/** 
	 * Description :  根据主键，获得代理机构的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param 
	 * @return  
	 * @Exception   
	 */
	public Agency getAgencyAllInfo(final String objId) throws Exception {
		List<Agency> list = getAgencyAllInfoList(objId);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	
	/** 
	 * Description :  根据主键，获得代理机构的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Agency> getAgencyAllInfoList(final String objIds) throws Exception {
		return (List<Agency>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				//查询代理机构信息
				String hql = "from Agency as s " +
								"left join fetch s.orgInfo as o left join fetch o.company " +
								"left join fetch o.qualitys as q " +
									"left join fetch q.qualificationClass as qc " +
									"left join fetch q.qualificationDefine as qd " +
									"left join fetch q.qualificationDetailSet as qds left join fetch qds.qualityParam " +
								"left join fetch o.successCases as c " +
								"left join fetch o.evaluates as e " +
								"where s.objId in (:objId) ";
				
				Query query = session.createQuery(hql);
				query.setParameterList("objId", objIds.split(","));
				
				Set<Agency> agencySet = new HashSet<Agency>(query.list());
				List<Agency> agencyList = new ArrayList<Agency>();
				agencyList.addAll(agencySet);
				return agencyList;
		}});
	}
	
	/** 
	 * Description :  获得代理机构类型的展示数据
	 * Create Date: 2010-8-12下午02:54:53 by liangxj  Modified Date: 2010-8-12下午02:54:53 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getAgentTypeListShow() throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String hql = "select a.agentType ,0, count(a.objId) from Agency a " +
						"left join a.orgInfo o " +
						"where o.useStatus =:useStatus and o.isOff =:isOff " +
						"group by a.agentType order by a.agentType";
				
				Query query = session.createQuery(hql);
				
				query.setString("useStatus", OrganizationEnum.USE_VALID);
				query.setString("isOff", OrganizationEnum.ENABLE);
				
				return query.list();
		}});
	}
	
	/** 
	 * Description :  获得代理机构类型的展示数据
	 * Create Date: 2010-8-12下午02:54:53 by liangxj  Modified Date: 2010-8-12下午02:54:53 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getAdminGrdListShow(final String agentType) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				StringBuffer hql = new StringBuffer("select a.adminGrd ,0, count(a.objId) from Agency a " +
						"left join a.orgInfo o " +
						"where o.useStatus =:useStatus and o.isOff =:isOff ");
				
				if(StringUtils.hasLength(agentType)) {
					hql.append("and a.agentType =:agentType ");
				}
					
				hql.append("group by a.adminGrd order by a.adminGrd ");
				
				Query query = session.createQuery(hql.toString());
				query.setString("useStatus", OrganizationEnum.USE_VALID);
				query.setString("isOff", OrganizationEnum.ENABLE);
				
				if(StringUtils.hasLength(agentType)) {
					query.setString("agentType", agentType);
				}
				
				return query.list();
		}});
	}
}
