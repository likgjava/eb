package com.gpcsoft.smallscale.pointmall.dao.hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.smallscale.pointmall.dao.GiftDao;
import com.gpcsoft.smallscale.pointmall.domain.Gift;
import com.gpcsoft.smallscale.pointmall.domain.GiftSeries;

@Repository
public class GiftDaoHibernate extends BaseGenericDaoHibernate<Gift> implements GiftDao {

	/** 
	 * Description :查询礼品
	 * Create Date: 2011-1-10上午09:09:49 by yucy  Modified Date: 2011-1-10上午09:09:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Gift> getGiftList(final Map<String, Object> paramsMap) throws Exception {
		return (List<Gift>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				
				GiftSeries giftSeries = (GiftSeries)paramsMap.get("giftSeries");
				
				hql.append(" select g from Gift g where 1=1 ");
				
				hql.append( " and :today between g.startTime and g.endTime ");//设置时间
				
				if((giftSeries)!=null){
					hql.append(" and g.giftSeries.parent.objId = :parentId ");
				}
				
				if(paramsMap.get("giftType")!=null){
					hql.append(" and g.giftType = :giftType ");
				}
				
				if(paramsMap.get("isRecommended")!=null){
					hql.append(" and g.isRecommended = :isRecommended ");
				}
				
				Query query = session.createQuery(hql.toString());
				query.setTimestamp("today", new Date());//设置时间
				
				if((giftSeries)!=null){
					query.setParameter("parentId", giftSeries.getObjId());
				}
				
				if(paramsMap.get("giftType")!=null){
					query.setParameter("giftType", paramsMap.get("giftType"));
				}
				
				if(paramsMap.get("isRecommended")!=null){
					query.setBoolean("isRecommended", (Boolean)paramsMap.get("isRecommended"));
				}
				
				
				if((Integer)paramsMap.get("resultNumber")!=null){
					query.setMaxResults((Integer)paramsMap.get("resultNumber"));
				}
				return query.list();
			}
		});
	}

	/** 
	 * Description : 更新是否推荐 
	 * Create Date: 2011-1-10下午11:20:01 by yucy  Modified Date: 2011-1-10下午11:20:01 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateGiftReCommond(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append(" update Gift g set g.isRecommended = :isRecommended where g.objId in (:objIds) ");
				Query query = session.createQuery(hql.toString());
				query.setBoolean("isRecommended",SmallscaleEnum.YES.equals( param.get("isRecommended")));
				query.setParameterList("objIds", (String [])param.get("objIds"));
				return query.executeUpdate()>0;
			}
		});
	}

	/** 
	 * Description :  取得礼品列表
	 * Create Date: 2011-1-11下午01:51:07 by yucy  Modified Date: 2011-1-11下午01:51:07 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Gift> getGiftListForShow(final Page<Gift> page,final Map<String, Object> paramsMap) throws Exception {
		return (Page<Gift>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String seriesId = (String) paramsMap.get("seriesId"); //系列ID
				String keyWord = (String)paramsMap.get("keyWord");  //关键字
				String giftType = (String) paramsMap.get("giftType"); //礼品类型

				
				//查询商品信息
				String preHql;
				preHql = "from Gift g where g.isUsed= :isUsed ";
				

				StringBuilder hql = new StringBuilder();
				hql.append( " and :today between g.startTime and g.endTime ");//设置时间
				
				//关键字
				if(StringUtils.hasLength(keyWord)) {
					hql.append(" and ( g.giftName like '%").append(keyWord).append("%' ");
					hql.append(" or g.giftComment like '%").append(keyWord).append("%' )");
					//hql.append(" or g.giftSeries.name like '%").append(keyWord).append("%'  ");
				}
				
				//系列
				if(StringUtils.hasLength(seriesId)){
					hql.append(" and ( g.giftSeries.objId = '"+seriesId+"' or g.giftSeries.parent.objId = '"+seriesId+"' )");
				}
				
				//礼品类型
				if(StringUtils.hasLength(giftType)){
					hql.append(" and g.giftType= '"+giftType+"'");
				}
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				if(paramsMap.get("order")!=null) {
					orderHql.append(" order by ");
					String[] order = ((String)paramsMap.get("order")).split(",");
					for (String string : order) {
						orderHql.append(" g.").append(string).append(",");
					}
					
					if(orderHql.toString().endsWith(",")) {
						orderHql.delete(orderHql.length()-1, orderHql.length());
					}
				}
				
				Query query = session.createQuery(preHql + hql + orderHql);
				query.setBoolean("isUsed", true);//启用的
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				query.setTimestamp("today", new Date());//设置时间
				
				List<Gift> giftList=query.list();
				page.setData(giftList);
				
				//查询总数
				preHql = "select count(g.objId) from Gift g where g.isUsed= :isUsed ";
				query = session.createQuery(preHql + hql);
				query.setBoolean("isUsed", true);
				query.setDate("today", new Date());//设置时间
				page.setTotalRowNum((Long) query.list().get(0));
				return page;
		}});}
}
