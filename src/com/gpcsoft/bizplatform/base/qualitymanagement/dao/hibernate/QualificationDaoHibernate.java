package com.gpcsoft.bizplatform.base.qualitymanagement.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.Qualification;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class QualificationDaoHibernate extends BaseGenericDaoHibernate<Qualification> implements QualificationDao {

	/**  
	 * Description : 修改资质是否为叶子结点 
	 * Create Date: 2010-7-30上午10:44:19 by guoyr  Modified Date: 2010-7-30上午10:44:19 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateQualificationIsLeaf(final String objId, final boolean isleaf) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="update Qualification set isLeaf =:isLeaf where objId =:objId";
				Query query = session.createQuery(hql);
				query.setString("objId", objId);
				query.setBoolean("isLeaf", isleaf);
				return query.executeUpdate();
			}
		});
	}

	/** 
	 * Description : 查询当前资质的父结点的子结点的个数 
	 * Create Date: 2010-8-3下午06:16:29 by guoyr  Modified Date: 2010-8-3下午06:16:29 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Long getSubQualificationCount(final String objId) {
		return (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "select count(objId) from Qualification  where parent.objId = ( " +
				"select parent.objId from Qualification  where objId =:objId)";
				Query query = session.createQuery(hql);
				query.setString("objId", objId);
				List<Object> list = query.list();
				if(list != null && list.size() > 0) {
					return (Long)list.get(0);
				} else {
					return 0;
				}
		
			}
		});

	}
	/** 
	 * Description :删除资质
	 * Create Date: 2010-8-24下午08:24:35 by guoyr  Modified Date: 2010-8-24下午08:24:35 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void removeQualification(final String objId){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="delete Qualification where objId =:objId";
				Query query = session.createQuery(hql);
				query.setString("objId", objId);
				return query.executeUpdate();
			}
		});
	}

	/** 
	 * Description : 修改资质的名称 
	 * Create Date: 2010-11-16上午11:10:25 by guoyr  Modified Date: 2010-11-16上午11:10:25 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateQualificationName(final String objId, final String qualityName){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="update Qualification set qualityName =:qualityName where objId =:objId";
				Query query = session.createQuery(hql);
				query.setString("qualityName", qualityName);
				query.setString("objId", objId);
				return query.executeUpdate();
			}
		});
	}
	
	/** 
	 * Description :  获取下级资质的列表
	 * Create Date: 2010-11-18上午10:37:51 by guoyr  Modified Date: 2010-11-18上午10:37:51 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Qualification> getSubQualificationList(final String parentId){
		return (List<Qualification>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="";
				if(StringUtils.hasLength(parentId)){
					hql="from Qualification  where parent.objId =:parentId order by sort";
				}else {
					hql="from Qualification  where parent.objId is null order by sort";
				}
				Query query = session.createQuery(hql);
				if(StringUtils.hasLength(parentId)){
					query.setString("parentId", parentId);
				}
				return query.list();
			}
		});
	}
	/** 
	 * Description : 根据资质的编号获得该资质
	 * Create Date: 2010-11-18上午10:28:16 by guoyr  Modified Date: 2010-11-18上午10:28:16 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Qualification getQualificationByCode(final String qualityCode){
		return (Qualification)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="from Qualification  where qualityCode =:qualityCode";
				Query query = session.createQuery(hql);
				query.setString("qualityCode", qualityCode);
				List<Qualification>  list =  query.list();
				Qualification qualification = null;
				if(null != list && list.size() > 0){
					qualification = list.get(0);
				}
				return qualification;
			}
		});
	}
	/** 
	 * Description : 判断资质名称在父节点下是不是唯一 
	 * Create Date: 2010-11-25上午10:23:13 by guoyr  Modified Date: 2010-11-25上午10:23:13 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public boolean isUnique(final String qualityName, final String objId, final String parentId ){
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				StringBuffer sb= new StringBuffer("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(objId)","0")+" from Qualification  where qualityName =:qualityName");
				if(StringUtils.hasLength(parentId)){
					sb.append(" and parent.objId =:parentId");
				}else {
					sb.append(" and parent.objId  is null");
				}
				if(StringUtils.hasLength(objId)){
					sb.append(" and objId <>:objId");
				}
				Query query = session.createQuery(sb.toString());
				query.setString("qualityName", qualityName);
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
}
