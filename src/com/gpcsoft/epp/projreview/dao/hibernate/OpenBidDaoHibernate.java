package com.gpcsoft.epp.projreview.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.projreview.dao.OpenBidDao;
import com.gpcsoft.epp.projreview.domain.OpenBid;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class OpenBidDaoHibernate extends BaseGenericDaoHibernate<OpenBid> implements OpenBidDao {
	
	/**
	 * FuncName:saveOpenBid
	 * Description:保存开标主表对象  
	 * @param   openBid:开标主键
	 * @return  	void
	 * @author 	   liuke
	 * @Create Date: 2010-5-26下午05:17:56 
	 * @Modifier   liuke
	 * @Modified Date: 2010-5-26下午05:17:56 
	 */
	public void saveOpenBid(OpenBid openBid) {
		this.save(openBid);
	}
	
	 /**
	  * FuncName:getOpenBidByProjectIdAndPackId
	  * Description :通过项目和包组得到开标记录  
	  * @param   projectId:项目主键,subProjectId:包租主键
	  * @return  List<OpenBid> 
	  * @author     liuke
	  * @Create Date: 2010-9-19下午01:49:44 
	  * @Modifier   liuke
	  * @Modified Date: 2010-9-19下午01:49:44 
	  */
	public List<OpenBid> getOpenBidByProjectIdAndPackId(String projectId,String subProjectId) throws Exception {
		List<OpenBid> list = this.findbyHql("from OpenBid t where t.projId = ? and t.subProjId = ?", projectId,subProjectId);
		return list;
	}

	/**
	  * FuncName:getOpenBidBySubProjectId
	  * Description :根据包组ID得到开标记录列表  
	  * @param   String subProjId  包组ID
	  * @return  List<OpenBid> 开标对象列表
	  * @author liuke
	  * @Create Date: 2010-10-27上午11:24:18 
	  * @Modifier   liuke
	  * @Modified Date: 2010-10-27上午11:24:18  
	  */
	public List<OpenBid> getOpenBidBySubProjectId(String subProjId) {
		List<OpenBid> list = this.findbyHql("from OpenBid t where t.subProjId = ?",subProjId);
		return list;
	}
	
	/**
	 * FuncName: getOpenBidByProjectId
	 * Description :根据项目ID得到开标记录列表  
	 * @param   String projectId  项目ID
	 * @return  List<OpenBid> 开标对象列表
	 * @author zhouzhanghe
	 * @Create Date: 2010-11-08 20:01
	 */
	public List<OpenBid> getOpenBidByProjectId(String projectId) {
		List<OpenBid> list = this.findbyHql("from OpenBid t where t.projId = ?",projectId);
		return list;
	}
	
	 /**
	  * FuncName:removeAllOpenBidByProject
	  * Description :根据项目删除开标信息
	  * @param projectId:项目主键
	  * @return  void
	  * @author liuke
	  * @Create Date: 2010-12-15下午05:48:38 
	  * @Modifier   liuke
	  * @Modified Date: 2010-12-15下午05:48:38 
	  */
	@SuppressWarnings("unchecked")
	public void removeAllOpenBidByProject(final String projectId) {
		   this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql = "delete from OpenBid t where t.projId = ? or t.subProjId = ? ";
					Query query  = session.createQuery(hql);
					query.setString(0, projectId).setString(1, projectId).executeUpdate();
					return null;
			}});
	}

	
	 /**
	  * FuncName: removeAllOpenBidNotInBidPackage
	  * Description :  删除项目里不存在的开标记录
	  * @param 
	  * @param projectId void
	  * @author: liuke
	  * @Create Date:2011-3-12  上午11:36:22
	  * @Modifier: liuke
	  * @Modified Date:2011-3-12  上午11:36:22
	  */
	@SuppressWarnings("unchecked")
	public void removeAllOpenBidNotInBidPackage(final String projectId) {
		   this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					StringBuffer  hql = new StringBuffer(); 
					hql.append(" delete from OpenBid t where t.projId=:projectId and  t.subProjId not in ( ");
					hql.append(" select distinct bp.pack.objId from BidPackage bp where  bp.bid.objId in  (  ");
					hql.append(" select b.objId from Bid b where b.project.objId=:projectId ) )");
					Query query  = session.createQuery(hql.toString());
					query.setString("projectId", projectId).executeUpdate();
					return null;
			}});
	}

	
	
	 /**
	  * FuncName: removeAllOpenBidByPack
	  * Description :  根据包组删除开标对象
	  * @param 
	  * @param packId void
	  * @author: liuke
	  * @Create Date:2011-5-28  下午12:57:23
	  * @Modifier: liuke
	  * @Modified Date:2011-5-28  下午12:57:23
	  */
	@SuppressWarnings("unchecked")
	public void removeAllOpenBidByPack(final String packId) {
		 this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					StringBuffer  hql = new StringBuffer(); 
					hql.append(" delete from OpenBid t where t.subProjId=:packId ");
					Query query  = session.createQuery(hql.toString());
					query.setString("packId", packId).executeUpdate();
					return null;
			}});
		
	}
	
	
}
