package com.gpcsoft.pubservice.application.message.dao.hibernate;

import java.sql.SQLException;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.pubservice.application.message.dao.ComplainDao;
import com.gpcsoft.pubservice.application.message.domain.Complain;

@Repository
public class ComplainDaoHibernate extends BaseGenericDaoHibernate<Complain> implements ComplainDao {
	
	/** 
	 * Description :  获取投诉举报列表
	 * Create Date: 2011-7-11下午07:46:19 by yucy  Modified Date: 2011-7-11下午07:46:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Complain> getComplainList(final Page<Complain> page,final Map<String, Object> param) throws Exception {
		return (Page<Complain>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Object orgInfoId = param.get("orgInfoId"); //被评价机构的id
				
				
				Object complainType = param.get("complainType"); //被评价机构的id

				
				String preHql = "from Complain c where 1=1 ";
				StringBuilder hql = new StringBuilder();
				
				if(orgInfoId!=null && StringUtils.hasLength(orgInfoId.toString())){
					hql.append(" and c.beCompanyId = '").append(orgInfoId.toString()).append("'");
				}
				
				if(complainType!=null && StringUtils.hasLength(complainType.toString())){
					hql.append(" and c.type = '").append(complainType.toString()).append("'");
				}
				
				Query query = session.createQuery(preHql + hql);
				
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				page.setData(query.list());
				
				//查询总数
				preHql = "select count(c.objId) from Complain c where 1=1 ";
				query = session.createQuery(preHql + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				return page;
			}
		});
	}

}
