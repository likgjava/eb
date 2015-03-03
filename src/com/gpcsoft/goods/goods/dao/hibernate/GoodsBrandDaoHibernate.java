package com.gpcsoft.goods.goods.dao.hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.goods.goods.dao.GoodsBrandDao;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;

@Repository
public class GoodsBrandDaoHibernate extends BaseGenericDaoHibernate<GoodsBrand> implements GoodsBrandDao {

	/** 
	 * Description :  启卖禁卖
	 * Create Date: 2010-8-6上午03:06:03 by yucy  Modified Date: 2010-8-6上午03:06:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateSellStatus(final Map<String, Object> param) throws Exception {
		Boolean flag = false;
		Integer result = (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String objId = (String)param.get("objId");
					String sellStatus = (String)param.get("sellStatus");

					//先 禁卖商品
					String hqlGoods="update Goods g set g.sellStatus = :sellStatus where g.goodsBrand.objId in(:objId) ";
					Query queryGoods = session.createQuery(hqlGoods);
					if(null!=sellStatus&&"start".equals(sellStatus)){
						queryGoods.setParameter("sellStatus", GoodsEnum.SELL_START);
					}else{
						queryGoods.setParameter("sellStatus", GoodsEnum.SELL_STOP);
					}
					if(objId!=null&&!"".equals(objId)){
						queryGoods.setParameterList("objId", objId.split(","));
						queryGoods.executeUpdate();
					}
				
					String hql="update GoodsBrand gb set gb.sellStatus=:sellStatus where gb.objId in (:objId) ";
					Query query = session.createQuery(hql);
					if(null!=objId&&!"".equals(objId)){
						query.setParameterList("objId", objId.split(","));
					}
					if(null!=sellStatus&&"start".equals(sellStatus)){
						query.setParameter("sellStatus", GoodsEnum.SELL_START);
					}else{
						query.setParameter("sellStatus", GoodsEnum.SELL_STOP);
					}
					return  query.executeUpdate();
				}
		});
		if(result>0){
			flag = true;
		}
		return flag;
	}

	/** 
	 * Description :  删除品牌(新增/变更)
	 * Create Date: 2010-8-6上午03:06:03 by yucy  Modified Date: 2010-8-6上午03:06:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean deleteBrand(final Map<String, Object> param) throws Exception{
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String objId = (String)param.get("objId");
					String hql = "select gb from GoodsBrand gb where objId in (:objId)";
					Query query = session.createQuery(hql);
					if(objId!=null&&!"".equals(objId)){
						query.setParameterList("objId", objId.split(","));
					}
					for(Object object: query.list()){
						remove((GoodsBrand)object);
					}
					return true;
				}
		});
	}

	/** 
	 * Description :  报废品牌
	 * Create Date: 2010-8-6上午09:54:02 by yucy  Modified Date: 2010-8-6上午09:54:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateDestroy(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String objId = (String)param.get("objId");
					
					String hql="update GoodsBrand gb set gb.useStatus = :useStatus, gb.invalidTime=:invalidTime where gb.objId in (:objId) ";
					Query query = session.createQuery(hql);
					query.setParameter("useStatus", GoodsEnum.USE_INVALID);//设为失效
					query.setTimestamp("invalidTime",new Date());//设失效时间
					if(objId!=null&&!"".equals(objId)){
						query.setParameterList("objId", objId.split(","));
					}
					return query.executeUpdate()>0;
				}
		});
	}

	/** 
	 * Description :  获取指定维护供应商的商品品牌 
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getAssignedGoodsBrand(final String orgInfoId) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				
				sql.append("select distinct m.goods_brand_id, b.brand_name from goods_modifier m ");
				sql.append("inner join goods_brand b ");
				sql.append("on m.goods_brand_id = b.goods_brand_id ");
				if(null != orgInfoId && !"".equals(orgInfoId)) {
					sql.append("where m.supplier_id =:orgInfoId ");
				}
				//过滤禁卖和报废的品牌 02 02
				sql.append("and b.sell_status != '").append(GoodsEnum.SELL_STOP).append("' ");
				sql.append("and b.manager_status != '").append(GoodsEnum.USE_INVALID).append("' ");
				sql.append("order by m.goods_brand_id");
				
				Query query = session.createSQLQuery(sql.toString());
				if(null != orgInfoId && !"".equals(orgInfoId)) {
					query.setString("orgInfoId", orgInfoId);
				}
				
				List<String[]> goodsBrandList = query.list();
				
				return goodsBrandList;
			}});
	}
	/** 
	 * Description : 获得供应商可维护的所有品牌  
	 * Create Date: 2010-9-9下午03:52:29 by guoyr  Modified Date: 2010-9-9下午03:52:29 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsBrand> getAllGoodsBrandByOrgId(final String orgInfoId){
		return (List<GoodsBrand>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuilder hql = new StringBuilder();
				hql.append("from GoodsBrand b where b.useStatus =:useStatus and b.sellStatus =:sellStatus");
				
				// 如果是供商，则只取该供应商可维护的品牌，如果是管理员，则取全部
				if(null != orgInfoId && !"".equals(orgInfoId)){
					hql.append(" and b.belongsId =:belongsId");
				}
				Query query = session.createQuery(hql.toString());
				query.setString("useStatus", GoodsEnum.USE_VALID);
				query.setString("sellStatus", GoodsEnum.SELL_START);
				if(null != orgInfoId && !"".equals(orgInfoId)) {
					query.setString("belongsId", orgInfoId);
				}
				return query.list();
			}
		});
	}
	
	/** 
	 * Description :  根据参数获得品牌的展示信息列表
	 * Create Date: 2011-2-16下午05:50:15 by liangxj  Modified Date: 2011-2-16下午05:50:15 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：排序信息
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<GoodsBrand> getGoodsBrandListForShow(final Page<GoodsBrand> page,final Map<String, Object> paramsMap) throws Exception{
		return (Page<GoodsBrand>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String keyWord = (String)paramsMap.get("keyWord");  //关键字
				String validSort = (String) paramsMap.get("validSort"); //生效时间排序
				String goodsSort = (String) paramsMap.get("goodsSort"); //商品数量排序
				
				//查询品牌信息
				String preHql;
				preHql = "from GoodsBrand as gb " +
									"where gb.useStatus = '"+GoodsEnum.USE_VALID+"' and gb.auditStatus = '"+GoodsEnum.PASS_EXAM+"' and gb.sellStatus = '"+GoodsEnum.SELL_START+"'";
				StringBuilder hql = new StringBuilder();
				
				//关键字
				if(StringUtils.hasLength(keyWord)) {
					hql.append(" and ( gb.brandName like '%").append(keyWord).append("%' ");
					hql.append(" or gb.englishName like '%").append(keyWord).append("%' ");
					hql.append(" or gb.shortSpellName like '%").append(keyWord).append("%' ");
					hql.append(" or gb.brandDesc like '%").append(keyWord).append("%' ) ");
				}
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				if(StringUtils.hasLength(validSort) || StringUtils.hasLength(goodsSort)){
					orderHql.append("order by ");
					if(StringUtils.hasLength(validSort)) {
						orderHql.append("gb.validTime ").append(validSort).append(",");
					}
					if(StringUtils.hasLength(goodsSort)) {
						orderHql.append("gb.goodsTotal ").append(goodsSort);
					}
					
					if(orderHql.toString().endsWith(",")) {
						orderHql.delete(orderHql.length()-1, orderHql.length());
					}
				}
				
				Query query = session.createQuery(preHql + hql + orderHql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
		
				List<GoodsBrand> goodsBrandList=query.list();
				page.setData(goodsBrandList);
				
				//查询总数
				preHql = "select count(gb.objId) from GoodsBrand as gb where gb.useStatus = '"+GoodsEnum.USE_VALID+"' and gb.auditStatus = '"+GoodsEnum.PASS_EXAM+"' and gb.sellStatus = '"+GoodsEnum.SELL_START+"'";
				query = session.createQuery(preHql + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				return page;
		}});
	}

	/** 
	 * Description :  检查品牌名称
	 * Create Date: 2011-5-6上午09:51:53 by yucy  Modified Date: 2011-5-6上午09:51:53 by yucy
	 * @param   brandName		检查目标
	 * 			goodsClassId	过滤的范围
	 * 			notObjId		排除的id
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> checkBrandName(final String brandName,final String goodsClassId,final String notObjId) throws Exception {
		return (Map<String,Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String Hql = " select  b.brand_name||','||b.english_name from goods_brand b, goods_brand_to_class bc "+

					 " where b.goods_brand_id = bc.goods_brand_id and b.manager_status = '" + GoodsEnum.USE_VALID + "'"+
 
					 " and ( b.brand_name like :brandName or b.english_name like :brandName )  "+
 
					 " and bc.goods_class_id in (:goodsClassId)";
 
				if(StringUtils.hasLength(notObjId)){
					Hql += " and b.goods_brand_id != '"+notObjId+"' ";
				}
				
				Query query = session.createSQLQuery(Hql);
				
				query.setParameterList("goodsClassId", goodsClassId.split(","));
				
				query.setString("brandName","%"+brandName+"%");
				
				List<String> list =  query.list();
				
				Map<String,Object> result = new HashMap<String,Object>();
				
				boolean hasit = false;
				
				for (String string : list) {
					//已经存在名称
					if( string.split(",")[0].equals( brandName) || string.split(",")[1].equals( brandName) ){
						result.put("checkType", false);					
						hasit = true;
						break;
					}
				}
				
				//类似名称
				if(!hasit){
					result.put("checkType", true);					
					result.put("samelist", list);					
				}
				
				return result;
			}
		});
	}
}
