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
import com.gpcsoft.pubservice.application.template.dao.DotTemplateDownDao;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateDown;

@Repository
public class DotTemplateDownDaoHibernate extends BaseGenericDaoHibernate<DotTemplateDown> implements DotTemplateDownDao {

	/** 
	 * Description :  根据参数获取范本下载记录的集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id  orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<DotTemplateDown> getTemplateDownListByParam(final Map<String, Object> param) throws Exception {
		return (List<DotTemplateDown>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String templateId = (String) param.get("templateId"); //范本Id
				String orgInfoId = (String) param.get("orgInfoId"); //机构Id
				
				StringBuilder hql = new StringBuilder();
				hql.append("from DotTemplateDown td join fetch td.dotTemplate t where 1=1");
				
				//范本过滤
				if(StringUtils.hasLength(templateId)) {
					hql.append(" and t.objId = '").append(templateId).append("' ");
				}
				//机构过滤
				if(StringUtils.hasLength(orgInfoId)) {
					hql.append(" and td.org.objId = '").append(orgInfoId).append("' ");
				}
				
				Query query = session.createQuery(hql.toString());
				return query.list();
			}
		});
	}
	
	/** 
	 * Description :  获取指定范本的下载次数
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Long getDownNum(final String templateId) throws Exception {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				
				String sql = "select count(td.TEMPLATE_DOWN_ID) from EPS_TEMPLATE_DOWN td where td.TEMPLATE_ID=:templateId";
				Query query = session.createSQLQuery(sql);
				query.setString("templateId", templateId);
				
				return query.uniqueResult();
			}
		});
	}

}
