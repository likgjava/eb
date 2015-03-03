package com.gpcsoft.smallscale.business.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.business.dao.BusinessMemberDao;
import com.gpcsoft.smallscale.business.domain.BusinessMember;

@Repository
public class BusinessMemberDaoHibernate extends BaseGenericDaoHibernate<BusinessMember> implements BusinessMemberDao {

	/** 
	 * Description :  根据companyId，获得最新的orgInfo。
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BusinessMember> getBusinessMemberList(String orgInfoId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BusinessMember m where m.orgInfo.objId = ? ");
		return this.findbyHql(hql.toString(), orgInfoId);
	}
	
	/** 
	 * Description :  获得商圈会员的展示信息
	 * Create Date: 2011-2-18上午09:06:24 by liangxj  Modified Date: 2011-2-18上午09:06:24 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含投标信息、社区信息、排序信息
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<BusinessMember> getBusinessMemberListForShow(final Page<BusinessMember> page,final Map<String, Object> paramsMap) throws Exception {
		return (Page<BusinessMember>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				String categoryCode = (String) paramsMap.get("categoryCode"); //采购品目Code
				String orgName = (String) paramsMap.get("orgName"); //机构名称
				String keyWord= (String) paramsMap.get("keyWord"); //关键字
				String createTimeSort = (String) paramsMap.get("createTimeSort"); //创建时间排序
				
				//查询商圈会员信息
				String preHql = "from BusinessMember as s left join fetch s.orgInfo as o left join fetch o.belongIndustry b " +
									"where o.auditStatus='"+OrganizationEnum.PASS_EXAM+"' and o.useStatus = '"+OrganizationEnum.USE_VALID+"' and o.isOff = '"+OrganizationEnum.ENABLE+"' " +
										" and s.useStatus = '" + OrganizationEnum.USE_VALID + "' and s.isOff = '"+OrganizationEnum.ENABLE+"' ";
				
				StringBuilder hql = new StringBuilder();
				//品目过滤
				if(StringUtils.hasLength(categoryCode)){
					String categoryCodeHql = "";
					for(String categoryCodeCell: categoryCode.split(",")){
						categoryCodeHql += StringUtils.hasLength(categoryCodeHql)?" or ":"";
						categoryCodeHql+= " o.bidForRange like '%"+categoryCodeCell+"%'";
					}
					hql.append(" and (").append(categoryCodeHql).append(")");
				}
				
				//高级搜索条件
				StringBuilder highparam = new StringBuilder();
				//机构名称
				if(StringUtils.hasLength(orgName)){
					highparam.append(" and o.orgName like '%").append(orgName).append("%')");
				}
				//关键字(主营产品,机构名称,简介 ,经营范围)
				if(StringUtils.hasLength(keyWord)){
					highparam.append(" and (o.bidForRange like '%").append(keyWord).append("%'");
					highparam.append(" or o.mainProducts like '%").append(keyWord).append("%'");
					highparam.append(" or o.descCn like '%").append(keyWord).append("%'");
					highparam.append(" or o.orgName like '%").append(keyWord).append("%')");
				}
				
				hql.append(highparam.toString());
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				if(paramsMap.get("order")!=null && StringUtils.hasLength(paramsMap.get("order").toString())){
					String orderStr = paramsMap.get("order").toString();
					StringBuilder multiOrder = new StringBuilder();
					if(orderStr.indexOf("orgInfo.orgName") != -1){
						multiOrder.append("o.orgName, ");
					}
					if(orderStr.indexOf("orgInfo.belongIndustry.name") != -1){
						multiOrder.append("b.name, ");
					}
					if(orderStr.indexOf("orgInfo.distinctName") != -1){
						multiOrder.append("o.districtValue, ");
					}
					if(orderStr.indexOf("createTime") != -1){
						multiOrder.append("s.createTime, ");
					}
					orderHql.append(" order by ").append(multiOrder.toString().substring(0,multiOrder.toString().length()-2));//去掉空格
					orderHql.append(paramsMap.get("order_flag").toString().equals("true") ? " desc ":" asc ");
				}else if(StringUtils.hasLength(createTimeSort)){
					orderHql.append(" order by s.createTime ").append(createTimeSort);
				}else{
					orderHql.append(" order by s.createTime desc ");
				}
				
				Query query = session.createQuery(preHql + hql + orderHql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
		
				List<Supplier> supplierList=query.list();
				page.setData(supplierList);
				
				//查询总数
				preHql = "select count(s.objId) from BusinessMember s left join s.orgInfo as o " +
							"where o.auditStatus='"+OrganizationEnum.PASS_EXAM+"' and o.useStatus = '"+OrganizationEnum.USE_VALID+"' and o.isOff = '"+OrganizationEnum.ENABLE+"' " +
								" and s.useStatus = '" + OrganizationEnum.USE_VALID + "' and s.isOff = '"+OrganizationEnum.ENABLE+"' ";
				query = session.createQuery(preHql + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				return page;
			}
		});
	}

	/** 
	 * Description :  根据条件获取商圈会员的数量
	 * Create Date: 2011-3-23上午11:46:26 by likg  Modified Date: 2011-3-23上午11:46:26 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Long getBusinessMemberNum(final Map<String, Object> paramsMap) throws Exception {
		return (Long) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String categoryCode = (String) paramsMap.get("categoryCode"); //采购品目Code
				
				StringBuilder hql = new StringBuilder("select count(s.objId) from BusinessMember s left join s.orgInfo as o ");
				hql.append(" where o.auditStatus='").append(OrganizationEnum.PASS_EXAM).append("' ")
				   .append(" and o.useStatus = '").append(OrganizationEnum.USE_VALID).append("' ")
				   .append(" and o.isOff = '").append(OrganizationEnum.ENABLE).append("' ")
				   .append(" and s.useStatus = '").append(OrganizationEnum.USE_VALID).append("' ")
				   .append(" and s.isOff = '").append(OrganizationEnum.ENABLE).append("' ");
				
				//品目过滤
				if(StringUtils.hasLength(categoryCode)){
					String categoryCodeHql = "";
					for(String categoryCodeCell: categoryCode.split(",")) {
						categoryCodeHql += StringUtils.hasLength(categoryCodeHql)?" or ":"";
						categoryCodeHql+= " o.bidForRange like '%"+categoryCodeCell+"%'";
					}
					hql.append(" and (").append(categoryCodeHql).append(")");
				}
				Query query = session.createQuery(hql.toString());
				
				return query.uniqueResult();
			}
		});
	}
}
