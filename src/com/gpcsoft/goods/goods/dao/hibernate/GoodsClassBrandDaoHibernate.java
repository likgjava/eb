package com.gpcsoft.goods.goods.dao.hibernate;


import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.dao.GoodsClassBrandDao;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsClassBrand;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

@Repository
public class GoodsClassBrandDaoHibernate extends BaseGenericDaoHibernate<GoodsClassBrand> implements GoodsClassBrandDao {
	/** 
	 * Description :  根据品目获得商品品牌的信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsBrand> getBrandListByCategory(final String categoryId,final String categoryCode) throws Exception {
		return (List<GoodsBrand>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder("select distinct c.goodsBrand from GoodsClassBrand c " +
									"left join c.goodsClass as gc " +
									"left join gc.goodsClassCategorySet as a " +
									"where c.goodsBrand.useStatus =:useStatus and c.goodsBrand.sellStatus =:sellStatus ");
				
				if(StringUtils.hasLength(categoryId)) {
					hql.append(" and a.purCategory.objId in (:categoryId) ");
				}
				if(StringUtils.hasLength(categoryCode)) {
					hql.append(" and a.purCategory.categoryCode like :categoryCode ");
				}
				
				Query query = session.createQuery(hql.toString());
				query.setString("useStatus", GoodsEnum.USE_VALID);
				query.setString("sellStatus", GoodsEnum.SELL_START);
				
				if(StringUtils.hasLength(categoryId)) {
					query.setParameterList("categoryId", categoryId.split(","));
				}
				if(StringUtils.hasLength(categoryCode)) {
					query.setString("categoryCode", categoryCode+"%");
				}
				
				List<GoodsClass> gcList=query.list();
				
				return gcList;
			}});
	}
	
	/** 
	 * Description :  根据商品分类获得商品品牌的信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   classId 分类id，classCode 分类编号
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsBrand> getBrandsListByClass(final String classId,final String classCode) throws Exception {
		return (List<GoodsBrand>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder("select distinct c.goodsBrand from GoodsClassBrand c " +
								"where c.goodsBrand.useStatus =:useStatus and c.goodsBrand.sellStatus =:sellStatus ");
				
				if(StringUtils.hasLength(classId)) {
					hql.append(" and c.goodsClass.objId in (:classId) ");
				}
				if(StringUtils.hasLength(classCode)) {
					hql.append(" and c.goodsClass.goodsClassCode like :classCode ");
				}
				
				Query query = session.createQuery(hql.toString());
				query.setString("useStatus", GoodsEnum.USE_VALID);
				query.setString("sellStatus", GoodsEnum.SELL_START);
				
				
				if(StringUtils.hasLength(classId)) {
					query.setParameterList("classId", classId.split(","));
				}
				if(StringUtils.hasLength(classCode)) {
					query.setString("classCode", classCode+"%");
				}
				
				List<GoodsClass> gcList=query.list();
				
				return gcList;
			}});
	}
	
	/** 
	 * Description :  根据商品品牌删除中间表
	 * Create Date: 2010-8-5上午11:08:36 by yucy  Modified Date: 2010-8-5上午11:08:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean deleteGoodsClassBrandByBrandId(final String brandIds) throws Exception {
		Boolean result = false;
		Integer flag =  (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException,SQLException {
				String hql = "delete from GoodsClassBrand gcb where gcb.goodsBrand.objId in (:brandIds)";
				Query query = session.createQuery(hql);
				if(null!=brandIds&&!"".equals(brandIds)){
					query.setParameter("brandIds", brandIds);
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
	 * Description :  获取指定维护供应商的产品分类 
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getAssignedGoodsClass(final String brandId, final String orgInfoId) {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				
				sql.append("select m.goods_class_id, c.goods_class_name from goods_modifier m ");
				sql.append("join goods_class c ");
				sql.append("on m.goods_class_id = c.goods_class_id ");
				sql.append("where 1=1 ");
				if(StringUtils.hasLength(orgInfoId)) {
					sql.append(" and m.supplier_id = :orgInfoId ");
				}
				sql.append("and m.goods_brand_id = :brandId ");
				sql.append("order by m.goods_class_id");
				
				Query query = session.createSQLQuery(sql.toString());
				if(StringUtils.hasLength(orgInfoId)) {
					query.setString("orgInfoId", orgInfoId);
				}
				query.setString("brandId", brandId);
				
				List<String[]> goodsClassList = query.list();
				
				return goodsClassList;
			}});
	}
	
	/** 
	 * Description :  获得分类信息(根据品牌)
	 * Create Date: 2011-1-18下午04:40:21 by yucy  Modified Date: 2011-1-18下午04:40:21 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsClass> getClassListByBrand(final String brandId) throws Exception {
		return (List<GoodsClass>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder("select distinct c.goodsClass from GoodsClassBrand c where 1=1 ");
				if(StringUtils.hasLength(brandId)) {
					hql.append("  and c.goodsBrand.objId = :brandId ");
				}
				Query query = session.createQuery(hql.toString());
				if(StringUtils.hasLength(brandId)) {
					query.setString("brandId", brandId);
				}
				List<GoodsClass> gcList=query.list();
				return gcList;
				
//				StringBuilder sql = new StringBuilder("select distinct bc.goods_class_id, c.goods_class_code, c.goods_class_name")
//				.append(" from goods_brand_to_class bc,")
//				.append(" goods_brand b,")
//				.append(" goods_class c")
//				.append(" where bc.goods_class_id = c.goods_class_id")
//				.append(" and bc.goods_brand_id = b.goods_brand_id");
//				if(StringUtils.hasLength(brandId)) {
//					sql.append(" and b.goods_brand_id = '").append(brandId).append("' ");
//				}
//				String[] queryColum = new String[]{"objId","goodsClassCode","goodsClassName"};;
//				Query query = session.createSQLQuery(sql.toString());
//				return HqlResultConvertUtils.hqlResultConvert(query.list(),queryColum,GoodsClass.class);//这个改不过来 show_goods_list_by_class.jsp
			}});
	}
}
