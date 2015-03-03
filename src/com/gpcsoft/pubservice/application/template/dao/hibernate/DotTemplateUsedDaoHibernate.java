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
import com.gpcsoft.pubservice.application.template.dao.DotTemplateUsedDao;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateUsed;

@Repository
public class DotTemplateUsedDaoHibernate extends BaseGenericDaoHibernate<DotTemplateUsed> implements DotTemplateUsedDao {

	/** 
	 * Description :  根据参数获取范本使用记录的集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id  projectCode:项目编号  orgInfoId:机构Id
	 * @return  List<DotTemplateUsed>
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<DotTemplateUsed> getTemplateUsedListByParam(final Map<String, Object> param) throws Exception {
		return (List<DotTemplateUsed>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String templateId = (String) param.get("templateId"); //范本Id
				String projectCode = (String) param.get("projectCode"); //项目编号
				String orgInfoId = (String) param.get("orgInfoId"); //机构Id
				
				StringBuilder hql = new StringBuilder();
				hql.append("from DotTemplateUsed tu join fetch tu.dotTemplate t where 1=1");
				
				//范本过滤
				if(StringUtils.hasLength(templateId)) {
					hql.append(" and t.objId = '").append(templateId).append("' ");
				}
				//项目过滤
				if(StringUtils.hasLength(projectCode)) {
					hql.append(" and tu.projectCode = '").append(projectCode).append("' ");
				}
				//机构过滤
				if(StringUtils.hasLength(orgInfoId)) {
					hql.append(" and tu.org.objId = '").append(orgInfoId).append("' ");
				}
				
				Query query = session.createQuery(hql.toString());
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取指定范本的使用次数
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Long getUsedNum(final String templateId) throws Exception {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				
				String sql = "select count(tu.TEMPLATE_USED_ID) from EPS_TEMPLATE_USED tu where tu.TEMPLATE_ID=:templateId";
				Query query = session.createSQLQuery(sql);
				query.setString("templateId", templateId);
				
				return query.uniqueResult();
			}
		});
	}

}
