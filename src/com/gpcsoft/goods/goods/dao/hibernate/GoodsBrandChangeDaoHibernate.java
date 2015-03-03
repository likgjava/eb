package com.gpcsoft.goods.goods.dao.hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.GoodsBrandChangeDao;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsBrandChange;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class GoodsBrandChangeDaoHibernate extends BaseGenericDaoHibernate<GoodsBrandChange> implements GoodsBrandChangeDao {
	
	/** 
	 * Description :  根据品牌取得变更数据
	 * Create Date: 2011-5-9上午11:37:29 by yucy  Modified Date: 2011-5-9上午11:37:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsBrandChange> getGoodsBrandChange(final String brandId,final String type, final String auditStatus) throws Exception {
		return (List<GoodsBrandChange>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql = "select * from goods_brand_change gbc where 1 =1  ";
				
				if(StringUtils.hasLength(brandId)){
					sql += " and gbc.goods_brand_id = '"+brandId+"'";
				}
				
				if(StringUtils.hasLength(type)){
					sql += " and gbc.modify_type = '"+type+"'";
				}
				
				if(StringUtils.hasLength(auditStatus)){
					sql += " and gbc.audit_status in (:auditStatus )";
				}
				
				sql += " order by gbc.create_time desc ";
				
				
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				if(StringUtils.hasLength(auditStatus)){
					sqlQuery.setParameterList("auditStatus", auditStatus.split(","));
				}
				
				sqlQuery.addEntity(GoodsBrandChange.class);
				return sqlQuery.list();
		}});
	}

	/** 
	 * Description :  更新品牌变更表的审核状态
	 * Create Date: 2011-5-9上午11:37:29 by yucy  Modified Date: 2011-5-9上午11:37:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateAuditStatus(final String brandId, final String type, final String auditStatus ,final String dealStatus) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql = "update goods_brand_change gbc set gbc.audit_status = '"+dealStatus+"'";
				
				if(GoodsEnum.PASS_EXAM.equals(dealStatus)){
					String today = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.formatDateTime(new Date()));
					sql += " ,gbc.verify_time = "+ today ;//加审核时间
				}
				
				sql += " where 1=1 ";
				if(StringUtils.hasLength(brandId)){
					sql += " and gbc.goods_brand_id = '"+brandId+"'";
				}
				
				if(StringUtils.hasLength(type)){
					sql += " and gbc.modify_type = '"+type+"'";
				}
				
				if(StringUtils.hasLength(auditStatus)){
					sql += " and gbc.audit_status in (:auditStatus )";
				}
				
				Query query = session.createSQLQuery(sql);
				if(StringUtils.hasLength(auditStatus)){
					query.setParameterList("auditStatus", auditStatus.split(","));
				}
				
				return query.executeUpdate()>0;
		}});
	}

	/** 
	 * Description :  获取品牌的变更审核列表
	 * Create Date: 2011-5-9下午04:20:19 by yucy  Modified Date: 2011-5-9下午04:20:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<GoodsBrand> getBrandListByBrandChange(final Page<GoodsBrand> page,final Map<String, Object> param) throws Exception {
		return (Page<GoodsBrand>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					
					String reslutHql = " from GoodsBrandChange gbc";

					String whereHqls = " where gbc.auditStatus = '"+GoodsEnum.AWAIT_EXAM+"'";
					
					if(StringUtils.hasLength((String)param.get("brandName"))){
						whereHqls += " and gbc.goodsBrand.brandName like '%"+(String)param.get("brandName")+"%'";
					}
					if(StringUtils.hasLength((String)param.get("brandCode"))){
						whereHqls += " and gbc.goodsBrand.brandCode like '%"+(String)param.get("brandCode")+"%'";
					}
					if(StringUtils.hasLength((String)param.get("englishName"))){
						whereHqls += " and gbc.goodsBrand.englishName like '%"+(String)param.get("englishName")+"%'";
					}
					
					if(StringUtils.hasLength((String)param.get("goodsClassNames"))){
						whereHqls += " and gbc.goodsBrand.goodsClassNames like '%"+(String)param.get("goodsClassNames")+"%'";
					}
					
					//排序条件()
					String orderHql = "";
					
					//查询结果
					Query query = session.createQuery("select distinct gbc.goodsBrand "+reslutHql+whereHqls+orderHql );
					query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
					page.setData(query.list());
					
					//查询记录数
					query = session.createQuery("select distinct gbc.goodsBrand.objId "+reslutHql+whereHqls+orderHql);
					page.setTotalRowNum(query.list().size());
					
					return page;
				}
		});
	}
}
