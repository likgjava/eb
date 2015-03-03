package com.gpcsoft.goods.goodsprice.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goodsprice.dao.AttentionPriceDao;
import com.gpcsoft.goods.goodsprice.domain.AttentionPrice;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class AttentionPriceDaoHibernate extends BaseGenericDaoHibernate<AttentionPrice> implements AttentionPriceDao {

	/** 
	 * Description :  取得当前关注的城市
	 * Create Date: 2010-11-4上午11:35:05 by yucy  Modified Date: 2010-11-4上午11:35:05 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Object[] getCurrentAttentionCity(final Map<String, Object> param) throws Exception {
		return (Object[]) getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session) throws HibernateException, SQLException {
			String hql = " select a.district.objId, a.district.name from AttentionPrice a where a.attentionOrg.objId = :orgId  ";
			Query query = session.createQuery(hql);
			query.setParameter("orgId", param.get("orgId").toString());
			List result = query.list();
			return result.size()>0?result.get(0):null;
		}});
	}

	/** 
	 * Description : 改变关注区域
	 * Create Date: 2010-11-4下午01:49:20 by yucy  Modified Date: 2010-11-4下午01:49:20 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean changerAttentionCity(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session) throws HibernateException, SQLException {
			String hql = " update AttentionPrice a set a.district.objId = :districtId  where a.attentionOrg.objId = :orgId  ";
			Query query = session.createQuery(hql);
			query.setParameter("orgId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
			query.setParameter("districtId",param.get("districtId"));
			return query.executeUpdate()>0?true:false;
		}});
	}

	/** 
	 * Description :  是否关注过
	 * Create Date: 2010-11-17下午05:57:37 by yucy  Modified Date: 2010-11-17下午05:57:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean hasAttention(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session) throws HibernateException, SQLException {
			String hql = " select a.objId from AttentionPrice a where a.goods.objId = :goodsId and a.attentionOrg.objId = :orgId ";
			Query query = session.createQuery(hql);
			query.setParameter("orgId", param.get("orgId"));
			query.setParameter("goodsId",param.get("goodsId"));
			return query.list().size()>0?true:false;
		}});
	}

	/** 
	 * Description :  取得商品被关注的次数
	 * Create Date: 2011-2-28下午02:01:58 by yucy  Modified Date: 2011-2-28下午02:01:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer getAttentionNumberByGoodsId(final String goodsId) throws Exception {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session) throws HibernateException, SQLException {
			String sql = " select count(distinct ap.org_info) from GOODS_ATTENTION_PRICE ap where ap.goods_id = :goodsId ";
			Query query = session.createSQLQuery(sql);
			query.setParameter("goodsId",goodsId);
			List result = query.list();
			return result.size()>0?new Integer(result.get(0).toString()):0;
		}});
	}
}
