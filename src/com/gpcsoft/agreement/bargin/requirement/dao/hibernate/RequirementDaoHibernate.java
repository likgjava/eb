package com.gpcsoft.agreement.bargin.requirement.dao.hibernate;

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
import org.springframework.util.StringUtils;

import com.gpcsoft.agreement.bargin.requirement.dao.RequirementDao;
import com.gpcsoft.agreement.bargin.requirement.domain.Requirement;
import com.gpcsoft.agreement.bargin.requirement.enumeration.RequirementEnum;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class RequirementDaoHibernate extends BaseGenericDaoHibernate<Requirement> implements RequirementDao {

	/** 
	 * Description :  更新状态(可能批量)
	 * Create Date: 2011-3-25上午10:59:51 by yucy  Modified Date: 2011-3-25上午10:59:51 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateStatus(final String objIds, final Map<String, Object> param)throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {

				String updateHql = " update Requirement r ";
				String columHql = "";
				if(StringUtils.hasLength((String)param.get("auditStatus"))){
					columHql += " set r.auditStatus = '"+(String)param.get("auditStatus")+"'";
				}
				
				if(StringUtils.hasLength((String)param.get("pubStatus"))){
					columHql += (StringUtils.hasLength(columHql)?",":"set")+ " r.pubStatus = '"+(String)param.get("pubStatus")+"'";
					columHql += " ,r.pubTime = "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
				}
				
				if(StringUtils.hasLength((String)param.get("projectId"))){
					columHql += (StringUtils.hasLength(columHql)?",":"set")+ " r.projectId = '"+(String)param.get("projectId")+"'";
				}
				
				updateHql += columHql+ " where r.objId in (:objIds)";
				Query query = session.createQuery(updateHql);
				query.setParameterList("objIds", objIds.split(","));
				return query.executeUpdate()>0;
			}
		});
	}

	/** 
	 * Description :  取得需求信息
	 * Create Date: 2011-3-30上午10:04:03 by yucy  Modified Date: 2011-3-30上午10:04:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Requirement> getRequirementListForShow(final Page<Requirement> page,final Map<String, Object> paramsMap) throws Exception {
		return (Page<Requirement>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				
				String keyWord = (String)paramsMap.get("keyWord");  //关键字
				String categoryCode = (String) paramsMap.get("categoryCode"); //采购品目Code
				String districtId = (String) paramsMap.get("districtId"); //区域id
				String price = (String)paramsMap.get("price");  //价格
				
				if(StringUtils.hasLength(districtId)){
					districtId = districtId.substring(0, 2);
				}
				
//				//查询需求信息
//				String preHql = " from Requirement r, District d where r.districtId = d.objId and r.auditStatus != '"+ RequirementEnum.NO_PASS_EXAM +"' ";//r.auditStatus = '"+ RequirementEnum.PASS_EXAM +"' and r.pubStatus = '"+RequirementEnum.PUBLISHED+"' 
//				
//				//查询条件
//				StringBuilder hql = new StringBuilder();
//				
//				//关键字
//				if(StringUtils.hasLength(keyWord)) {
//					hql.append(" and ( r.title like '%").append(keyWord).append("%'");
//					hql.append(" or r.districtNames like '%").append(keyWord).append("%'");
//					hql.append(" or r.discription like '%").append(keyWord).append("%'");
//					hql.append(" or r.linkMen like '%").append(keyWord).append("%')");
//				}
//				
//				//价格
//				if(StringUtils.hasLength(price)) {
//					String[] temp = price.split(",");
//					if(temp.length > 0 && StringUtils.hasLength(temp[0])) {
//						hql.append(" and r.purchaseBudget >= ").append(temp[0]);
//					}
//					if(temp.length > 1 && StringUtils.hasLength(temp[1])) {
//						hql.append(" and r.purchaseBudget <= ").append(temp[1]);
//					}
//				}
//				
//				
//				//品目过滤
//				if(StringUtils.hasLength(categoryCode)){
//					String categoryCodeHql = "";
//					for(String categoryCodeCell: categoryCode.split(",")){
//						categoryCodeHql += StringUtils.hasLength(categoryCodeHql)?" or ":"";
//						categoryCodeHql+= " r.category.categoryCode like '%"+categoryCodeCell+"%'";
//					}
//					hql.append(" and (").append(categoryCodeHql).append(")");
//				}
//				
//				//区域过滤
//				if(StringUtils.hasLength(districtId)){
//					hql.append(" and d.code like '").append(districtId).append("%' ");
//				}
//				
//				//排除
//				if(StringUtils.hasLength((String)paramsMap.get("notin"))){
//					hql.append(" and r.objId not in (");
//					String ssString = "";
//					for(String s:  ((String)paramsMap.get("notin")).split(",")){
//						ssString +=(StringUtils.hasLength(ssString)?",'":"'")+s+"'";
//					}
//					hql.append(ssString);
//					hql.append(") ");
//				}
//				
//				//排序条件
//				String pubTimeSort = (String) paramsMap.get("pubTimeSort"); //生效时间排序
//				String createTimeSort = (String) paramsMap.get("createTimeSort"); //生效时间排序
//
//				String order = (String) paramsMap.get("order");
//				StringBuilder orderHql = new StringBuilder();
//				orderHql.append(" order by ");
//				if(StringUtils.hasLength(pubTimeSort)|| StringUtils.hasLength(createTimeSort)|| StringUtils.hasLength(order)){
//					if(StringUtils.hasLength(order)){
//						for (String string : order.split(",")) {
//							orderHql.append(" r.").append(string).append(" desc ,");
//						}
//					}
//					if(StringUtils.hasLength(pubTimeSort)) {
//						orderHql.append("r.pubTime ").append(pubTimeSort);
//					}
//					if(StringUtils.hasLength(createTimeSort)) {
//						orderHql.append("r.createTime ").append(createTimeSort);
//					}
//					if(orderHql.toString().endsWith(",")) {
//						orderHql.delete(orderHql.length()-1, orderHql.length());
//					}
//				}else{
//					orderHql.append( " r.createTime desc ");
//				}
//				Query query = session.createQuery("select r "+preHql + hql + orderHql);
//				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
//		
//				List<Requirement> requirementList=query.list();
//				page.setData(requirementList);
//				
//				//查询总数
//				preHql = "  select count(r.objId) " + preHql;
//				query = session.createQuery(preHql + hql);
//				page.setTotalRowNum((Long) query.list().get(0));
//				return page;
				
				String column = " select r.purchase_require_id, " +
						" r.purchase_title," +
						" r.purchase_number," +
						" r.purchase_budget," +
						" r.district_names ," +
						" r.picture," +
						" r.create_time ," +
						" p.category_name " ;

				//连接表
				String table = " from eps_purchase_requirement r , purcatalog_category p where r.category_id = p.id";
		
				//选择条件
				StringBuilder condition = new StringBuilder();
				
				//关键字
				if(StringUtils.hasLength(keyWord)) {
					condition.append(" and ( instr(r.purchase_title, '").append(keyWord).append("')>0 ");
					condition.append(" or instr(r.district_names, '").append(keyWord).append("')>0");
					condition.append(" or instr(r.description ,'").append(keyWord).append("')>0");
					condition.append(" or instr(r.linkmen ,'").append(keyWord).append("')>0 )");
				}
				
				//价格
				if(StringUtils.hasLength(price)) {
					String[] temp = price.split(",");
					if(temp.length > 0 && StringUtils.hasLength(temp[0])) {
						condition.append(" and r.purchase_budget >= ").append(temp[0]);
					}
					if(temp.length > 1 && StringUtils.hasLength(temp[1])) {
						condition.append(" and r.purchase_budget <= ").append(temp[1]);
					}
				}
				
				//品目过滤
				if(StringUtils.hasLength(categoryCode)){
					String categoryCodeHql = "";
					for(String categoryCodeCell: categoryCode.split(",")){
						categoryCodeHql += StringUtils.hasLength(categoryCodeHql)?" or ":"";
						categoryCodeHql+= " instr(p.category_code,'"+categoryCodeCell+"')>0";
					}
					condition.append(" and (").append(categoryCodeHql).append(")");
				}
				
				//区域过滤
				if(StringUtils.hasLength(districtId)){
					condition.append(" and r.district_id like '").append(districtId).append("%' ");
				}
				
				//排除
				if(StringUtils.hasLength((String)paramsMap.get("notin"))){
					condition.append(" and r.purchase_require_id not in (");
					String ssString = "";
					for(String s:  ((String)paramsMap.get("notin")).split(",")){
						ssString +=(StringUtils.hasLength(ssString)?",'":"'")+s+"'";
					}
					condition.append(ssString);
					condition.append(") ");
				}
				
				//排序条件
				String pubTimeSort = (String) paramsMap.get("pubTimeSort"); //生效时间排序
				String createTimeSort = (String) paramsMap.get("createTimeSort"); //生效时间排序

				String order = (String) paramsMap.get("order");
				StringBuilder orderHql = new StringBuilder();
				String orderHqlStr = null;
				orderHql.append(" order by ");
				if(StringUtils.hasLength(pubTimeSort)|| StringUtils.hasLength(createTimeSort)|| StringUtils.hasLength(order)){
					if(StringUtils.hasLength(order)){
						for (String string : order.split(",")) {
							orderHql.append(" r.").append(string).append(" desc ,");
						}
					}
					if(StringUtils.hasLength(pubTimeSort)) {
						orderHql.append("r.publish_time ").append(pubTimeSort);
					}
					if(StringUtils.hasLength(createTimeSort)) {
						orderHql.append("r.create_time ").append(createTimeSort);
					}
					if(orderHql.toString().endsWith(",")) {
						orderHql.delete(orderHql.length()-1, orderHql.length());
					}
				}else{
					orderHql.append( " r.create_time desc ");
				}
				//特殊处理
				orderHqlStr = orderHql.toString().replace("createTime", "create_time");
				
				Query query = session.createSQLQuery(column + table + condition.toString() + orderHqlStr);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<Requirement> dataList = new ArrayList<Requirement>();
				//拼装对象
				for(Object[] objects : (List<Object[]>)query.list()){
					Requirement requirement = new Requirement();
					requirement.setObjId((String)objects[0]);
					requirement.setTitle((String)objects[1]);
					requirement.setPurchaseQty((BigDecimal)objects[2]);
					requirement.setPurchaseBudget((BigDecimal)objects[3]);
					requirement.setDistrictNames((String)objects[4]);
					requirement.setPicture((String)objects[5]);
					requirement.setCreateTime((Date)objects[6]);
					PurCategory category = new PurCategory();
					category.setCategoryName((String)objects[7]);
					requirement.setCategory(category);
					dataList.add(requirement);
				}
				page.setData(dataList);
				
				//count
				query = session.createSQLQuery("select count(r.purchase_require_id) "+table + condition.toString());
				page.setTotalRowNum(((BigDecimal)query.uniqueResult()).longValue());
				return page;
		}});
	}

	/** 
	 * Description :  根据品目获得品目的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by yucy  Modified Date: 2010-7-27下午06:12:58 by yucy
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getCategoryListShowByCategory(final String categoryId,final String categoryCode, final Boolean isLeaf,final String keyWord)throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder(
						"select p.id, p.category_code, p.category_name, b.requirementcount from purcatalog_category p,"+
						      " (select count(r.purchase_require_id) as requirementcount, r.category_id"+
						        " from EPS_PURCHASE_REQUIREMENT r"+
						        " where 1=1 "+
						        //" r.audit_status = :auditStatus "+ " and r.publish_status = :pubStatus"+
						        " and r.audit_status != :auditStatus "+
						        ( StringUtils.hasLength(keyWord)?" and (r.purchase_title like :keyWord or r.district_names like :keyWord  or r.description like :keyWord )":"")+
						        " group by r.category_id ) b"+
						" where p.id = b.category_id" );
				if(StringUtils.hasLength(categoryId)) {
					sql.append(" and p.id in (:categoryId) ");
				}
				if(StringUtils.hasLength(categoryCode)) {
					sql.append(" and p.category_code like :categoryCode ");
				}
				if(isLeaf) {
					sql.append(" and p.purcategory_is_leaf = :isLeaf ");
				}
				
				sql.append(" order by p.category_code ");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("auditStatus", RequirementEnum.NO_PASS_EXAM);
//				query.setString("pubStatus", RequirementEnum.PUBLISHED);
				
				if(StringUtils.hasLength(categoryId)) {
					query.setParameterList("categoryId", categoryId.split(","));
				}
				if(StringUtils.hasLength(categoryCode)) {
					query.setString("categoryCode", categoryCode+"%");
				}
				if(isLeaf) {
					query.setBoolean("isLeaf", isLeaf);
				}
				
				if(StringUtils.hasLength(keyWord)){
					query.setString("keyWord", "%"+keyWord+"%");
				}
				List<String[]> gcList=query.list();
				
				return gcList;
				
			}});	
	}

	/** 
	 * Description :  根据品目获得区域信息展示信息集合
	 * Create Date: 2010-8-9下午03:25:57 by yucy  Modified Date: 2010-8-9下午03:25:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getDistrictListForShow(final String districtId,final String categoryCode,final Short level,final String keyWord) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder(
						" select distinct a.district_id,a.district_name ,b.requirement_count "+
						" from base_district a ,"+
							" ( select count (r.purchase_require_id) as requirement_count ,substr(d.district_code ,0,2) did from "+
							"  EPS_PURCHASE_REQUIREMENT r ,purcatalog_category p ,base_district d"+
							" where 1=1"+
							//" r.audit_status = :auditStatus and r.publish_status = :pubStatus "+
							" and r.audit_status != :auditStatus "+
							" and r.category_id = p.id and d.district_id = r.district_id "+
							( StringUtils.hasLength(categoryCode)?" and r.category_id = p.id and p.category_code like :categoryCode ":"")+
					        ( StringUtils.hasLength(keyWord)?" and (r.purchase_title like :keyWord or r.district_names like :keyWord  or r.description like :keyWord )":"")+
							" group by substr(d.district_code ,0,2)) b"+
						"  where "+
						"   a.district_id like  b.did||'%' "+
						"   "
				);
				
				if(StringUtils.hasLength(districtId)) {
					sql.append(" and a.district_parent_id in (:districtId) ");
				}
				if(level != 0) {
					sql.append(" and a.dis_level = :districtLeval ");
				}
				sql.append(" order by a.district_name");
				
				Query query = session.createSQLQuery(sql.toString());
				
				if(StringUtils.hasLength(categoryCode)) {
					query.setString("categoryCode", "%"+categoryCode+"%");
				}
				
				query.setString("auditStatus", RequirementEnum.NO_PASS_EXAM);
//				query.setString("pubStatus", RequirementEnum.PUBLISHED );
				
				if(StringUtils.hasLength(districtId)) {
					query.setParameterList("districtId", districtId.split(","));
				}
				if(level != 0) {
					query.setShort("districtLeval", level);
				}
				if(StringUtils.hasLength(keyWord)){
					query.setString("keyWord", "%"+keyWord+"%");
				}
				
				List<String[]> gcList=query.list();
				return gcList;
			}});
	}

	/** 
	 * Description :  根据id 获得需求的集合
	 * Create Date: 2011-4-6下午02:27:52 by yucy  Modified Date: 2011-4-6下午02:27:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Requirement> getRequirementList(final String objIds) throws Exception {
		return (List<Requirement>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("from Requirement r left join fetch r.category where r.objId in (:objIds)");
				Query query = session.createQuery(hql.toString());
				query.setParameterList("objIds", objIds.split(","));
				return query.list();
			}
		});
	}

}
