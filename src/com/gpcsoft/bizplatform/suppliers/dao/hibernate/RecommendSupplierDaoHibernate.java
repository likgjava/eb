package com.gpcsoft.bizplatform.suppliers.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.industry.domain.Industry;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.suppliers.dao.RecommendSupplierDao;
import com.gpcsoft.bizplatform.suppliers.domain.RecommendSupplier;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class RecommendSupplierDaoHibernate extends BaseGenericDaoHibernate<RecommendSupplier> implements RecommendSupplierDao 
{
	/** 
	 * Description :  推荐指定的供应商
	 * Create Date: 2010-10-11 下午 16:30:16 by likg  Modified Date: 2010-10-11 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void recommendSupplier(final String[] supplierIds, final RecommendSupplier recommendSupplierPattern) throws Exception 
	{
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				// 获得已推荐供应商的最大排序序号
				Query queryMaxSort = session.createQuery("select max(rs.sort) from RecommendSupplier rs");
				Long maxSort = (Long) queryMaxSort.uniqueResult();
				if(maxSort == null)
				{
					maxSort = 0L;
				}
				
				StringBuilder sql = new StringBuilder();
				long mm = new Date().getTime();
				sql.append("insert into RECOMMEND_SUPPLIER (ID,RECOMMENDER,REASON,AUDIT_STATUS,USE_STATUS,CREATE_TIME,SUPPLIER,ORGINFO,SORT) ");
				sql.append("select o.ORG_INFO_ID || :mm, :recommender, :reason, :audit_status, :use_status, :create_time, o.SUPPLIER_ID, o.ORG_INFO_ID, :sort + rownum ");
				sql.append("from ORG_INFO o ");
				sql.append("where o.ORG_INFO_ID in (");
				for(String str : supplierIds)
				{
					sql.append("'" + str + "',");
				}
				sql.deleteCharAt(sql.length()-1);
				sql.append(")");
				
				Query query = session.createSQLQuery(sql.toString());
				
				query.setParameter("mm", mm);
				query.setParameter("recommender", AuthenticationHelper.getCurrentUser(true).getObjId());
				query.setParameter("reason", recommendSupplierPattern.getReason());
				query.setParameter("audit_status", recommendSupplierPattern.getAuditStatus());
				query.setParameter("use_status", (recommendSupplierPattern.getUseStatus()==true ? "1" : "0"));
				query.setParameter("create_time", recommendSupplierPattern.getCreateTime());
				query.setParameter("sort", maxSort);
				
				query.executeUpdate();
				return true;
			}
		});
	}

	/** 
	 * Description :  获取所有的未推荐供应商
	 * Create Date: 2010-10-14 下午 16:30:16 by likg  Modified Date: 2010-10-14 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page listNoRecommendSupplier(final Map<String,Object> param, final Page page) throws Exception 
	{
		return (Page) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				List noRecommendSupplier = null;
				StringBuilder sql = new StringBuilder();
				
				sql.append(" from ORG_INFO o join SPL_SUPPLIER s on o.SUPPLIER_ID = s.SUPPLIER_ID where o.ORG_INFO_ID not in (select rs.ORGINFO from RECOMMEND_SUPPLIER rs )");
				
				//过滤条件
				sql.append(" and o.SUPPLIER_ID is not null");
				sql.append(" and o.USE_STATUS = '").append(OrganizationEnum.USE_VALID).append("'");
				sql.append(" and o.AUDIT_STATUS = '").append(OrganizationEnum.PASS_EXAM).append("'");
				sql.append(" and o.IS_OFF = '").append(OrganizationEnum.ENABLE).append("'");
				sql.append(" and s.AUDIT_STATUS = '").append(OrganizationEnum.PASS_EXAM).append("'");
				
				//查询过滤条件
				String unitScape = param.get("unitScape").toString();
				String orgName = param.get("orgName").toString();
				String entPrpt = param.get("entPrpt").toString();
				if(StringUtils.hasLength(unitScape)){
					sql.append(" and o.UNIT_SCAPE like '%" + unitScape + "%'");
				}
				if(StringUtils.hasLength(entPrpt)){
					sql.append(" and o.ENT_PRPT like '%" + entPrpt + "%'");
				}
				if(StringUtils.hasLength(orgName)){
					sql.append(" and o.ORG_NAME like '%" + orgName + "%'");
				}
				
				//排序条件
				String order = " order by o.ORG_NAME desc ";
				if(StringUtils.hasLength(param.get("order").toString())){
					String orderStr = param.get("order").toString();
					StringBuilder multiOrder = new StringBuilder();
					if(orderStr.indexOf("orgName") != -1){
						multiOrder.append("o.ORG_NAME, ");
					}
					if(orderStr.indexOf("entPrpt") != -1){
						multiOrder.append("o.ENT_PRPT, ");
					}
					if(orderStr.indexOf("unitScape") != -1){
						multiOrder.append("o.UNIT_SCAPE, ");
					}
					sql.append(" order by ").append(multiOrder.toString().substring(0,multiOrder.toString().length()-2));//去掉空格
					sql.append(param.get("order_flag").toString().equals("true") ? " desc ":" asc ");
				}else{
					sql.append(order);
				}
				
				String queryColumn = "select o.ORG_INFO_ID,o.ORG_NAME,o.ENT_PRPT,o.UNIT_SCAPE";
				Query queryCount = session.createSQLQuery("select count(*) " + sql.toString());
				Query query = session.createSQLQuery(queryColumn + sql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				Integer totalCount = ((BigDecimal)queryCount.list().get(0)).intValue();
				noRecommendSupplier = query.list();
				
				return new Page(page.getStart(), totalCount, page.getPageSize(), noRecommendSupplier);
			}
		});
	}

	/** 
	 * Description :  批量删除推荐供应商
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean deleteRecommendSupplier(final String[] objIds) throws Exception 
	{
		Boolean flag = false;
		Integer result = (Integer) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String hql = "delete from RecommendSupplier rs where rs.objId in (:objIds)";
				Query query = session.createQuery(hql);
				
				if(objIds != null)
				{
					query.setParameterList("objIds", objIds);
				}
				
				return query.executeUpdate();
			}
		});
		
		if(result > 0)
		{
			flag = true;
		}
		
		return flag;
	}

	/** 
	 * Description :  获得推荐的供应商
	 * Create Date: 2010-10-19上午11:07:30 by likg  Modified Date: 2010-10-19上午11:07:30 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Supplier> getRecommendSupplierInfo(final Page<Supplier> page, final Map<String, Object> paramsMap) throws Exception 
	{
		return (Page<Supplier>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String categoryCode = (String) paramsMap.get("categoryCode"); //采购品目Code
				String districtId = (String) paramsMap.get("districtId"); //区域id
				String keyWord = (String) paramsMap.get("keyWord"); //关键字

				
//				StringBuilder hql = new StringBuilder();
//				hql.append("select rs.supplier from RecommendSupplier rs join rs.supplier s join fetch s.orgInfo where 1=1 ");
//				
//				//品目过滤
//				if(StringUtils.hasLength(categoryCode)){
//					hql.append(" and s.orgInfo.bidForRange like '%").append(categoryCode).append("%' ");
//				}
//				
//				//区域过滤
//				if(StringUtils.hasLength(districtId)){
//					hql.append(" and s.orgInfo.districtValue like '%").append(districtId).append("%' ");
//				}
//				
//				//关键字过滤
//				if(StringUtils.hasLength(keyWord)){
//					hql.append(" and ( instr( s.orgInfo.bidForRange,'").append(keyWord).append("') >0 ");
//					hql.append(" or instr( s.orgInfo.mainProducts ,'").append(keyWord).append("')>0 ");
//					hql.append(" or instr( s.orgInfo.descCn , '").append(keyWord).append("') >0");
//					hql.append(" or instr( s.orgInfo.orgName, '").append(keyWord).append("') >0 )");
//				}
//				
//				hql.append(" order by rs.sort");
//				
//				Query query = session.createQuery(hql.toString());
//				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
//				
//				Query queryCount = session.createQuery("select count(rs.objId) from RecommendSupplier rs");
//				Integer totalCount = ((Long)queryCount.list().get(0)).intValue();
//				
//				return new Page<Supplier>(page.getStart(), totalCount, page.getPageSize(), query.list());
				
				
				String column = " select o.supplier_id," +
							   " o.org_info_id,"+
						       " o.org_name,"+
						       " o.org_code," +
						       " o.org_logo," +
						       " o.desc_cn," +
						       " bi.industry_name";
				//连接表
				String table = " from recommend_supplier rs,spl_supplier s, org_info o " +
						" left join BASE_INDUSTRY bi on o.belongindustry = bi.industry_id" +
						" where rs.supplier = s.supplier_id and s.org_info_id = o.org_info_id ";
				
				//选择条件
				StringBuilder condition = new StringBuilder();
				
				//品目过滤
				if(StringUtils.hasLength(categoryCode)){
					condition.append(" and instr(o.bid_for_range,'").append(categoryCode).append("')>0 ");
				}
				
				//区域过滤
				if(StringUtils.hasLength(districtId)){
					condition.append(" and instr(o.district_value, '").append(districtId).append("')>0 ");
				}
				
				//关键字过滤
				if(StringUtils.hasLength(keyWord)){
					condition.append(" and ( instr( o.bid_for_range,'").append(keyWord).append("') >0 ");
					condition.append(" or instr( o.main_products ,'").append(keyWord).append("')>0 ");
					condition.append(" or instr( o.desc_cn , '").append(keyWord).append("') >0");
					condition.append(" or instr( o.org_name, '").append(keyWord).append("') >0 )");
				}
				
				//排序
				String orderSql = " order by rs.sort";
				
				Query query = session.createSQLQuery(column + table + condition.toString() + orderSql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<Supplier> dataList = new ArrayList<Supplier>();
				
				//拼装对象
				for(Object[] objects : (List<Object[]>)query.list()){
					Supplier supplier = new Supplier();
					supplier.setObjId((String)objects[0]);
					OrgInfo orgInfo = new OrgInfo();
					orgInfo.setObjId((String)objects[1]);
					orgInfo.setOrgName((String)objects[2]);
					orgInfo.setOrgCode((String)objects[3]);
					orgInfo.setLogo((String)objects[4]);
					orgInfo.setDescCn((String)objects[5]);
					supplier.setOrgInfo(orgInfo);
					
					Industry industry = new Industry();
					industry.setName((String)objects[6]);
					orgInfo.setBelongIndustry(industry);
					dataList.add(supplier);
				}
				page.setData(dataList);
				
				//count
				query = session.createSQLQuery("select count(s.supplier_id) "+table + condition.toString());
				page.setTotalRowNum(((BigDecimal)query.uniqueResult()).longValue());
				return page;
			}
		});
	}
	
	/** 
	 * Description :  修改推荐供应商的排序序号
	 * Create Date: 2010-12-22上午10:43:42 by likg  Modified Date: 2010-12-22上午10:43:42 by likg
	 * @param   
	 * @return 
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateSort(final Long sort, final boolean isToUp) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				StringBuilder hql = new StringBuilder();
				hql.append("from RecommendSupplier rs where 1=1 ");
				
				//过滤条件和排序条件
				if(isToUp){
					hql.append(" and rs.sort <= ").append(sort);
					hql.append(" order by rs.sort desc ");
				}else{
					hql.append(" and rs.sort >= ").append(sort);
					hql.append(" order by rs.sort asc ");
				}
				
				Query query = session.createQuery(hql.toString());
				query.setFirstResult(0).setMaxResults(2);
		
				List<RecommendSupplier> supplierList = query.list();
				if(supplierList!=null && supplierList.size()==2){
					RecommendSupplier sourceRecommendSupplier = supplierList.get(0);
					RecommendSupplier targetRecommendSupplier = supplierList.get(1);
					
					Long tempSort = sourceRecommendSupplier.getSort();
					sourceRecommendSupplier.setSort(targetRecommendSupplier.getSort());
					targetRecommendSupplier.setSort(tempSort);
				}
				
				return null;
			}	
		});
	}
	
}
