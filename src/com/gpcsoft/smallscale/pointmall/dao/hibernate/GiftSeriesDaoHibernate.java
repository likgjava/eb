package com.gpcsoft.smallscale.pointmall.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.smallscale.pointmall.dao.GiftSeriesDao;
import com.gpcsoft.smallscale.pointmall.domain.GiftSeries;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class GiftSeriesDaoHibernate extends BaseGenericDaoHibernate<GiftSeries> implements GiftSeriesDao {

	/** 
	 * Description :  取得礼品系列
	 * Create Date: 2011-1-10上午10:05:48 by yucy  Modified Date: 2011-1-10上午10:05:48 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GiftSeries> getSeriesList(Map<String, Object> paramsMap) throws Exception {
		return (List<GiftSeries>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append(" select gs from GiftSeries gs where gs.parent is null ");
				Query query = session.createQuery(hql.toString());
				return query.list();
			}
		});
	}



	/**
	 * Description :  判断礼品系列名称在同一父节点下是否唯一
	 * Create Date: 2011-1-10上午10:40:44 by zhaojf  Modified Date: 2011-1-10上午10:40:44 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean isUnique(final String giftSeriesName,final String objId,final String parentId) {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				StringBuffer sb= new StringBuffer("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(objId)","0")+" from GiftSeries  where name =:giftSeriesName");
				if(StringUtils.hasLength(parentId)){
					sb.append(" and parent.objId =:parentId");
				}else {
					sb.append(" and parent.objId  is null");
				}
				if(StringUtils.hasLength(objId)){
					sb.append(" and objId <>:objId");
				}
				Query query = session.createQuery(sb.toString());
				query.setString("giftSeriesName", giftSeriesName);
				if(StringUtils.hasLength(parentId)){
					query.setString("parentId", parentId);
				}
				if(StringUtils.hasLength(objId)){
					query.setString("objId", objId);
				}
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
	 * Description :  修改礼品系列的名称
	 * Create Date: 2011-1-10下午05:28:18 by zhaojf  Modified Date: 2011-1-10下午05:28:18 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public void updateGigtSeriesName(final String objId,final String giftSeriesName) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="update GiftSeries set name =:giftSeriesName where objId =:objId";
				Query query = session.createQuery(hql);
				query.setString("giftSeriesName", giftSeriesName);
				query.setString("objId", objId);
				return query.executeUpdate();
			}
		});
	}

	/**
	 * Description :  判断此系列是否还包含有礼品
	 * Create Date: 2011-1-11下午01:41:15 by zhaojf  Modified Date: 2011-1-11下午01:41:15 by zhaojf
	 * @param   
	 * @return  是：返回true 否:返回false
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean isHasGiftByGiftSeries(final String objId) {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				StringBuffer sb= new StringBuffer("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(objId)","0")+" from Gift  where SERIES_ID =:objId");
				
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
	 * Description :  获取当前父节点下所拥有的最大子节点编码
	 * Create Date: 2011-1-11下午02:48:56 by zhaojf  Modified Date: 2011-1-11下午02:48:56 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public String getGiftSeriesCount(String parentId) {
		List<Object> list = null;
		
		// 如果父ID为空
		if(null != parentId && !"".equals(parentId)) {
			list = getHibernateTemplate().find("select max(seriesCode) from GiftSeries where parent.objId = ? ", parentId);
		}else {
			list = getHibernateTemplate().find( "select max(seriesCode) from GiftSeries where parent.objId is null ");
		}
		
		return (String)list.get(0);		
	}

}
