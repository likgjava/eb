package com.gpcsoft.pubservice.application.template.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.pubservice.application.template.dao.DotTemplateFavoriteDao;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateFavorite;

@Repository
public class DotTemplateFavoriteDaoHibernate extends BaseGenericDaoHibernate<DotTemplateFavorite> implements DotTemplateFavoriteDao {

	/** 
	 * Description :  根据参数获取范本收藏列表集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id  orgInfoId:收藏机构的Id  userId:收藏人的Id
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<DotTemplateFavorite> getTemplateFavoriteList(final Map<String, Object> param) throws Exception {
		return (List<DotTemplateFavorite>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String templateId = (String) param.get("templateId"); //范本Id
				String orgInfoId = (String) param.get("orgInfoId"); //收藏机构的Id
				String userId = (String) param.get("userId"); //收藏人的Id
				
				StringBuilder hql = new StringBuilder();
				hql.append(" from DotTemplateFavorite t where 1=1");
				
				//范本过滤
				if(StringUtils.hasLength(templateId)) {
					hql.append(" and t.dotTemplate.objId = '").append(templateId).append("' ");
				}
				//收藏机构过滤
				if(StringUtils.hasLength(orgInfoId)) {
					hql.append(" and t.org.objId = '").append(orgInfoId).append("' ");
				}
				//收藏人过滤
				if(StringUtils.hasLength(userId)) {
					hql.append(" and t.createUser.objId = '").append(userId).append("' ");
				}
				
				Query query = session.createQuery(hql.toString());
				return query.list();
			}
		});
	}
}
