package com.gpcsoft.pubservice.application.favorites.dao.hibernate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.favorites.dao.FavoritesDao;
import com.gpcsoft.pubservice.application.favorites.domain.Favorites;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class FavoritesDaoHibernate extends BaseGenericDaoHibernate<Favorites> implements FavoritesDao {

	/** 
	 * Description :  取得类似的收藏
	 * Create Date: 2010-8-19下午02:31:47 by yucy  Modified Date: 2010-8-19下午02:31:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSimilarFavorites(final Map<String, Object> param) throws Exception {
		return (Map<String, Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Map<String, Object> model = new HashMap<String, Object>(); 
				String favoriteId = (String)param.get("favoriteId");
				model.put("inFavorites", false);
				StringBuilder Sql = new StringBuilder();
				String inStr = UnifyDBSqlFactory.getIUnifyDBSqlImpl().inStr("favorites_object_key", ",", 1);
				String subStr = UnifyDBSqlFactory.getIUnifyDBSqlImpl().subString("favorites_object_key", 0,100);
				subStr = subStr.replace("100", inStr+"-1");
				Sql.append("select * from ( select count(favorites_id) keyCount ,keyName from ((select t.favorites_object_key keyName, t.favorites_id from ecp_pub_favorites t where "+inStr+" = 0 and t.favorites_object_id = :favoriteId) union (select "+subStr+" keyName, t.favorites_id from  ecp_pub_favorites t where "+inStr+" > 1 and t.favorites_object_id = :favoriteId ) ) group by keyName order by keyCount desc ) ");
				Query query = session.createSQLQuery(Sql.toString());
				query.setParameter("favoriteId", favoriteId);
				query.setMaxResults(5);
				model.put("FavoritesList",query.list());
				return model;
		}});
	}

	/** 
	 * Description :  是否收藏过
	 * Create Date: 2010-9-3上午10:48:56 by yucy  Modified Date: 2010-9-3上午10:48:56 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public boolean hasThisFavorite(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String favoriteId = (String)param.get("favoriteId");
				//是否收藏过
				String hql1 = "from Favorites f where f.favoriteId = :favoriteId and f.createUser.objId= :createUserId";
				Query query1 = session.createQuery(hql1);
				query1.setParameter("favoriteId", favoriteId);
				query1.setParameter("createUserId",  AuthenticationHelper.getCurrentUser(true).getObjId());
				return query1.list().size()>0?true:false;
				
		}});
	}
}
