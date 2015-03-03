package com.gpcsoft.agreement.ftl.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.ftl.dao.FtlTemplateDao;
import com.gpcsoft.agreement.ftl.domain.FtlTemplate;
import com.gpcsoft.cms.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class FtlTemplateDaoHibernate extends BaseGenericDaoHibernate<FtlTemplate> implements FtlTemplateDao {

	/** 
	 * Description : 验证当前用户下的模板是不是唯一的
	 * Create Date: 2010-12-2下午03:49:37 by guoyr  Modified Date: 2010-12-2下午03:49:37 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public boolean isUnique(final String ftlName, final String objId, final String createUserId ){
		
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				StringBuffer sb= new StringBuffer("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(objId)","0")+" from FtlTemplate  where ftlName =:ftlName and createUser.objId =:createUserId");
				if(StringUtils.hasLength(objId)){
					sb.append(" and objId <>:objId");
				}
				Query query = session.createQuery(sb.toString());
				query.setString("ftlName", ftlName);
				query.setString("createUserId", createUserId);
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
}
