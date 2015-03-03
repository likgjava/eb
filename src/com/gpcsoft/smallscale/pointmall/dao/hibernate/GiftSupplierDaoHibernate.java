package com.gpcsoft.smallscale.pointmall.dao.hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.smallscale.pointmall.dao.GiftSupplierDao;
import com.gpcsoft.smallscale.pointmall.domain.GiftSupplier;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class GiftSupplierDaoHibernate extends BaseGenericDaoHibernate<GiftSupplier> implements GiftSupplierDao {

	/**
	 * Description :   判断礼品供货商是否唯一
	 * Create Date: 2011-1-12下午03:54:07 by zhaojf  Modified Date: 2011-1-12下午03:54:07 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean isUnique(final String giftSupplierName) {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				StringBuffer sb= new StringBuffer("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(objId)","0")+" from GiftSupplier  where supplierName =:giftSupplierName");
				
				Query query = session.createQuery(sb.toString());
				query.setString("giftSupplierName", giftSupplierName);
				
				List<Long>  list =  query.list();
				boolean flag = true;
				if(list.get(0) > 0){
					flag = false;
				}
				return flag;
			}			
		});
	}

	/**
	 * Description :  判断此供货商是否还包含有礼品
	 * Create Date: 2011-1-12下午04:16:20 by zhaojf  Modified Date: 2011-1-12下午04:16:20 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean isHasGiftByGiftSupplier(final String objId) {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				StringBuffer sb= new StringBuffer("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(objId)","0")+" from Gift  where giftSupplier.objId =:objId");
				
				Query query = session.createQuery(sb.toString());
				
				if(StringUtils.hasLength(objId)){
					query.setString("objId", objId);
				}
				List<Long>  list =  query.list();
				boolean flag = false;
				if(list.get(0) > 0){
					flag = true;
				}
				return flag;
			}			
		});
	}

	/**
	 * Description :  禁用或启用
	 * Create Date: 2011-1-12下午05:25:19 by zhaojf  Modified Date: 2011-1-12下午05:25:19 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public void modifyIsUsedStatus(final String objId, final boolean isUsed) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="update GiftSupplier set isUsed =:isUsed where objId =:objId";
				Query query = session.createQuery(hql);
				query.setBoolean("isUsed", isUsed);
				query.setString("objId", objId);
				return query.executeUpdate();
			}
		});
	}

	/**
	 * Description :  修改礼品供货商
	 * Create Date: 2011-1-13上午10:12:05 by zhaojf  Modified Date: 2011-1-13上午10:12:05 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public void modifyGiftSupplier(final String objId,final Date startTime,
			final Date endTime) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="update GiftSupplier set startTime =:startTime,endTime =:endTime where objId =:objId";
				Query query = session.createQuery(hql);
				query.setDate("startTime", startTime);
				query.setDate("endTime", endTime);
				query.setString("objId", objId);
				return query.executeUpdate();
			}
		});
	}
}
