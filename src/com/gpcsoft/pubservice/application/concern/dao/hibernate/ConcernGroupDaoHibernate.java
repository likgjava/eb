package com.gpcsoft.pubservice.application.concern.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.concern.dao.ConcernGroupDao;
import com.gpcsoft.pubservice.application.concern.domain.ConcernGroup;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class ConcernGroupDaoHibernate extends BaseGenericDaoHibernate<ConcernGroup> implements ConcernGroupDao {

	/** 
	 * Description : 获得默认分组  
	 * Create Date: 2010-11-4下午03:29:28 by guoyr  Modified Date: 2010-11-4下午03:29:28 by guoyr
	 * @param  groupType 默认分组的类型[01:供应商 02:采购人] 
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public ConcernGroup getDefaultConcernGroup(final String belongsUser){
		return (ConcernGroup) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="from ConcernGroup c where c.groupName =:groupName and c.belongsUser.objId =:belongsUser";
				Query query = session.createQuery(hql);
				query.setString("groupName", "默认分组");
				query.setString("belongsUser", belongsUser);
				List<ConcernGroup> list = query.list();
				if(null != list && list.size() > 0){
					return list.get(0);
				}else {
					return null;
				}
			}
		});
	}
	
	/** 
	 * Description :获得分组的最大排序号  
	 * Create Date: 2010-11-5下午08:23:38 by guoyr  Modified Date: 2010-11-5下午08:23:38 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer getMaxConcernGroupSort(final String groupType){
		List<Object> list = null;
		User currentUser = AuthenticationHelper.getCurrentUser(true);
		list =  getHibernateTemplate().find("select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("max(sort)","0") + " from ConcernGroup c where c.groupType = ? and c.belongsUser.objId = ?", groupType,currentUser.getObjId());
		return (Integer) list.get(0);
	}
	
	/** 
	 * Description : 修改关注分组 
	 * Create Date: 2010-11-3下午05:56:54 by guoyr  Modified Date: 2010-11-3下午05:56:54 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateConcernGroup (final ConcernGroup concernGroup){
		Boolean flag = false;
		Integer result = (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {	
				String hql="update ConcernGroup c set c.groupName =:groupName ,c.sort =:sort where c.objId =:objId";
				Query query = session.createQuery(hql);
				query.setString("groupName", concernGroup.getGroupName());
				query.setLong("sort", concernGroup.getSort());
				query.setString("objId", concernGroup.getObjId());
				return query.executeUpdate();
			}
			
		});
		if(result>0){
			flag = true;
		}
		return flag;
	}
}
