package com.gpcsoft.bizplatform.base.purcatalog.dao.hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.purcatalog.dao.PurCatalogDao;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalog;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class PurCatalogDaoHibernate extends BaseGenericDaoHibernate<PurCatalog> implements PurCatalogDao {

	/** 
	 * Description :  保存采购目录(写入dao供扩展)
	 * Create Date: 2010-8-10上午10:32:48 by yucy  Modified Date: 2010-8-10上午10:32:48 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurCatalog savePurCatalog(PurCatalog purCatalog) {
		return save(purCatalog);
	}

	/** 
	 * Description :  保存采购目录明细(写入dao供扩展)
	 * Create Date: 2010-8-10下午04:25:32 by yucy  Modified Date: 2010-8-10下午04:25:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveCatalogDetailOrProcType(Map<String, Object> param) {
		return null;
	}

	/** 
	 * Description :  获得展开节点的信息
	 * Create Date: 2010-8-11上午12:33:36 by yucy  Modified Date: 2010-8-11上午12:33:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getOpenItemInfo(final Map<String, Object> param) throws Exception {
		List result = (List)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer Sql = new StringBuffer();
				
				String categoryParam;
				String categoryId = (String)param.get("categoryId");
				if(null==categoryId||"".equals(categoryId)||"-1".equals(categoryId)){
					categoryParam = "is null";
				}else {
					categoryParam = "= '"+categoryId+"'";
				}
				Sql.append("select temp.*, " +
						"(case when temp.id in " +
						"(select cd.category_id from ecp_base_catalog_detail cd, ecp_base_catalog cl  where cd.catalog_id = cl.catalog_id   and cd.catalog_id = ?   union select cp.category_id   from ecp_base_catalog_proctype cp, ecp_base_catalog cl " +
						" where cl.catalog_id = cp.catalog_id and cl.catalog_id = ?) then  'yes' else 'no' end) as flag   from (select c.id, c.parent_id, c.category_name  from purcatalog_category c  start with c.id in " +
						"(select cd.category_id  from ecp_base_catalog_detail cd, ecp_base_catalog cl where cd.catalog_id = cl.catalog_id and cd.catalog_id = ? union select cp.category_id from ecp_base_catalog_proctype cp, ecp_base_catalog cl " +
						"where cl.catalog_id = cp.catalog_id   and cl.catalog_id =  ?)  connect by prior c.parent_id = c.id   group by id, c.parent_id, c.category_name  ) temp  where temp.parent_id "+ categoryParam);
				
				String catalogId = (String)param.get("catalogId");
				
				Query query = session.createSQLQuery(Sql.toString());
				query.setString(0,catalogId);
				query.setString(1,catalogId);
				query.setString(2,catalogId);
				query.setString(3,catalogId);
				
				return query.list();
		}});
		return result;
	}
	
	/** 
	 * Description :  发布
	 * Create Date: 2010-8-25上午11:21:23 by yucy  Modified Date: 2010-8-25上午11:21:23 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updatePubStatus(final String catalogId) throws Exception{
		Integer result = (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "update PurCatalog pc set pc.publishStatus = :publishStatus, pc.pubDate = :pubDate where pc.objId =:catalogId ";
				Query query = session.createQuery(hql);
				if(catalogId!=null&&!"".equals(catalogId)){
					query.setParameter("publishStatus",OrganizationEnum.PUBLISHED );
					query.setDate("pubDate",new Date());
					query.setParameter("catalogId",catalogId );
				}
				return query.executeUpdate();
			}});
		return result>0?true:false;
	}

	/** 
	 * Description :  根据品目取得目录
	 * Create Date: 2010-9-3下午04:21:32 by yucy  Modified Date: 2010-9-3下午04:21:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getCatalogByCategory(final Map<String, Object> param)throws Exception {
		return  (List<String[]>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String categoryId = (String)param.get("categoryId");
				String sql = "select pc.catalog_id ,pc.title ,case when temp1.catalog_id is not null then 'true' else 'false' end" +
						" from ECP_BASE_CATALOG pc left join (select pc.catalog_id, pc.title from PURCATALOG_CATEGORY t, ECP_BASE_CATALOG_PROCTYPE ecp, ECP_BASE_CATALOG pc where t.id = :categoryId and ecp.category_id = t.id and pc.catalog_id = ecp.catalog_id " +
						" union select pc.catalog_id, pc.title from PURCATALOG_CATEGORY t,  ECP_BASE_CATALOG_DETAIL ecd,  ECP_BASE_CATALOG pc where t.id = :categoryId and ecd.category_id = t.id and pc.catalog_id = ecd.catalog_id ) temp1 on temp1.catalog_id = pc.catalog_id";
				Query query = session.createSQLQuery(sql);
				query.setParameter("categoryId", categoryId);
				return query.executeUpdate();
			}});
	}

	/** 
	 * Description :  取得年度
	 * Create Date: 2009-4-9下午04:13:26 by yucy  Modified Date: 2009-4-9下午04:13:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAnual() throws Exception {
		return  (List<Integer>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "select distinct year from PurCatalog";
				Query query = session.createQuery(sql);
				return query.list();
			}});
	}
}
