package com.gpcsoft.goods.goodsclass.dao.hibernate;


import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassCategoryDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassCategory;

@Repository
public class GoodsClassCategoryDaoHibernate extends BaseGenericDaoHibernate<GoodsClassCategory> implements GoodsClassCategoryDao {
	/** 
	 * Description :  根据品目获得商品分类的信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsClass> getClassListByCategory(final String categoryId,final String categoryCode,final Boolean isLeaf) throws Exception {
		return (List<GoodsClass>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder("select distinct c.goodsClass from GoodsClassCategory c where 1=1 ");
				
				//拼装查询条件
				if(StringUtils.hasLength(categoryId)) {
					hql.append(" and c.purCategory.objId in (:categoryId) ");
				}
				if(StringUtils.hasLength(categoryCode)) {
					hql.append(" and c.purCategory.categoryCode like :categoryCode ");
				}
				if(isLeaf) {
					hql.append(" and c.goodsClass.isLeaf =:isLeaf ");
				}
				
				Query query = session.createQuery(hql.toString());
				
				//传入查询条件参数
				if(StringUtils.hasLength(categoryId)) {
					query.setParameterList("categoryId", categoryId.split(","));
				}
				if(StringUtils.hasLength(categoryCode)) {
					query.setString("categoryCode", categoryCode+"%");
				}
				if(isLeaf) {
					query.setBoolean("isLeaf", isLeaf);
				}
				
				List<GoodsClass> gcList=query.list();
				
				return gcList;
			}});
	}
	
	/** 
	 * Description :  根据品获取分类集合(jdbc查询方式)
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getClassByCategory(final String categoryId,final String categoryCode,final Boolean isLeaf) throws Exception {
		return (List<Object[]>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
     			StringBuilder sql = new StringBuilder();
     			sql.append("select t.goods_class_id, t.goods_class_name,count(b.goods_brand_id) from goods_class t left join goods_brand_to_class b ");
     			sql.append("on t.goods_class_id=b.goods_class_id where 1=1 and t.goods_class_id in ");
     			sql.append("(select c.goods_class_id from goods_class_to_category c where 1=1 ");
     			if(StringUtils.hasLength(categoryId)) {
     				sql.append(" and c.pur_category_id=:categoryId ");
     			}
     			sql.append(")");
     			sql.append("group by t.goods_class_id, t.goods_class_name");
     			
     			
     			Query query = session.createSQLQuery(sql.toString());
     			if(StringUtils.hasLength(categoryId)) {
     				query.setString("categoryId", categoryId);
     			}
     			List list = query.list();
				
				return list;
			}
		});
	}
	
	/** 
	 * Description :  根据品目获得商品分类的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getBrandsListShowByClass(final String categoryId,final String categoryCode,final Boolean isLeaf) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder("select b.goods_class_id, b.goods_class_name, count(goods_id) from " +
										"goods a, " +
										"goods_class b, " +
										"purcatalog_category c " +
										"where a.manger_status =:useStatus and a.sell_Status =:sellStatus " +
										"and a.goods_class_id = b.goods_class_id " +
										"and a.pur_category_id = c.id ");
				
				if(StringUtils.hasLength(categoryId)) {
					sql.append(" and c.id in (:categoryId) ");
				}
				if(StringUtils.hasLength(categoryCode)) {
					sql.append(" and c.category_code like :categoryCode ");
				}
				if(isLeaf) {
					sql.append(" and c.purcategory_is_leaf = :isLeaf ");
				}
				
				sql.append(" group by b.goods_class_id, b.goods_class_name order by b.goods_class_name");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("useStatus", GoodsEnum.USE_VALID);
				query.setString("sellStatus", GoodsEnum.SELL_START);
				
				if(StringUtils.hasLength(categoryId)) {
					query.setParameterList("categoryId", categoryId.split(","));
				}
				if(StringUtils.hasLength(categoryCode)) {
					query.setString("categoryCode", categoryCode+"%");
				}
				if(isLeaf) {
					query.setBoolean("isLeaf", isLeaf);
				}
				
				List<String[]> gcList=query.list();
				
				return gcList;
			}});
	}

	/** 
	 * Description :根据品牌 获取还未指定维护供应商的产品分类  
	 * Create Date: 2010-8-6上午09:18:06 by guoyr  Modified Date: 2010-8-6上午09:18:06 by guoyr
	 * @param goodsClassId产品分类Id，
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getUnSpecifiedGoodsClassByBrandId(final String goodsBrandId) {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder("select * from (select a.*,e.supplier_id from " +
						"(select c.goods_brand_id ,c.brand_name, d.goods_class_id, d.goods_class_name  " +
						"from goods_brand_to_class b,  goods_brand c ,  goods_class d " +
						"where b.goods_brand_id = c.goods_brand_id and b.goods_class_id = d.goods_class_id " +
						"and c.manager_status = :useStatus and c.sell_status = :sellStatus ) a " +
						"left join goods_modifier e on a.goods_class_id = e.goods_class_id " +
						"and a.goods_brand_id = e.goods_brand_id ) " +
						"where supplier_id is null ");
				
				if(null != goodsBrandId && !"".equals(goodsBrandId)) {
					sql.append(" and goods_brand_id = :goodsBrandId");
					sql.append(" order by goods_class_id");
				}
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("useStatus", GoodsEnum.USE_VALID);
				query.setString("sellStatus", GoodsEnum.SELL_START);
				
				if(null != goodsBrandId && !"".equals(goodsBrandId)) {
					query.setString("goodsBrandId", goodsBrandId);
				}
				List<String[]> unSpecifiedGoods = query.list();
				
				return unSpecifiedGoods;
			}});
	}
	
	/** 
	 * Description :  删除中间对象
	 * Create Date: 2010-12-24下午03:20:52 by yucy  Modified Date: 2010-12-24下午03:20:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean deleteGoodsClassCategoryByClassId(final String classIds)throws Exception {
		Boolean result = false;
		Integer flag =  (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException,SQLException {
				String hql = "delete from GoodsClassCategory gcc where gcc.goodsClass.objId in (:classIds)";
				Query query = session.createQuery(hql);
				if(null!=classIds&&!"".equals(classIds)){
					query.setParameter("classIds", classIds);
				}
				return query.executeUpdate();
			}
		});
		if(flag>0){
			result =true;
		}
		return result;
	}
	
	/** 
	 * Description :  根据分类获取品目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getAssignedPurcategory(final String classId) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				
				sql.append("select g.pur_category_id,p.category_name from GOODS_CLASS_TO_CATEGORY g ");
				sql.append("join PURCATALOG_CATEGORY p on g.pur_category_id = p.id ");
				sql.append("where g.goods_class_id = :classId ");
				sql.append("and p.purcategory_is_leaf = :isLeaf ");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("classId", classId);
				query.setBoolean("isLeaf", true);
				
				List<String[]> purCategoryList = query.list();
				
				return purCategoryList;
			}});
	}
}
