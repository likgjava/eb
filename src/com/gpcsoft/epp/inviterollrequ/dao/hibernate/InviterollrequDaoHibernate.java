package com.gpcsoft.epp.inviterollrequ.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.inviterollrequ.dao.InviterollrequDao;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class InviterollrequDaoHibernate extends BaseGenericDaoHibernate<Inviterollrequ> implements InviterollrequDao {

	/**
	 * 
	 * Description :根据项目主键得到邀请函申请对象 
	 * Create Date: 2010-8-17下午03:22:03 by liuke  Modified Date: 2010-8-17下午03:22:03 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Inviterollrequ getInviterollrequByProjectId(String projectId,String auditStatus) {
		 List<Inviterollrequ> list= new ArrayList<Inviterollrequ>();
		if(null==auditStatus||"".equals(auditStatus)){
			 list = this.findbyHql("from Inviterollrequ il where il.project.objId =?", projectId);
		}else{
			 list = this.findbyHql("from Inviterollrequ il where il.project.objId =? and il.auditStatus = ?", projectId,auditStatus);
		}
	    if(list.size()>0){
	    	return list.get(0);
	    }
		return null;
	}
	
	/**
	 * 
	 * 
	 * Description :  修改邀请函的状态为待审核
	 * Create Date: 2010-10-18上午09:24:20 by shenjianzhuang  Modified Date: 2010-10-18上午09:24:20 by shenjianzhuang
	 * @param objId
	 * @return
	 * @return  Inviterollrequ
	 * @Exception
 	 */	
	@SuppressWarnings("unchecked")
	public void updateInviterollrequ(final String objId){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql = "update Inviterollrequ g set g.auditStatus =? where g.objId =?";
				Query query = session.createQuery(hql);
				return query.executeUpdate();
			}
		});
	
	}

	
	/**
	 * 
	 * Description :根据QueryObject对象获得邀请函数量  
	 * Create Date: 2010-10-25下午02:03:23 by liuke  Modified Date: 2010-10-25下午02:03:23 by liuke
	 * @param   QueryObject queryObject
	 * @return  BigDecimal
	 * @Exception
	 */
	public BigDecimal getInviterollrequTotalByQueryObject(
			QueryObject queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(t) from Inviterollrequ t where 1=1  ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("monitorId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.project.monitor.objId='"+(String)queryParam.getValue()+"' ");
				}
				if("useStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.useStatus='"+(String)queryParam.getValue()+"' ");
				};
				if("auditStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.auditStatus='"+(String)queryParam.getValue()+"' ");
				}
				if("projName".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.projName like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("projCode".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.projCode like '%"+(String)queryParam.getValue()+"%' ");
				}
			}
		}
		
		List list=this.findbyHql(hql.toString());
		Object object = list.get(0);
		return new BigDecimal(object.toString());
	}
	
	
	
	/**
	 * 
	 * Description :根据QueryObject对象获得邀请函列表
	 * Create Date: 2010-10-25下午02:03:23 by liuke  Modified Date: 2010-10-25下午02:03:23 by liuke
	 * @param   QueryObject queryObject
	 * @return  Page<Inviterollrequ>
	 * @Exception
	 */
	public Page<Inviterollrequ> getInviterollrequByQueryObject(
			QueryObject queryObject,Page page) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Inviterollrequ t where 1=1  ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("monitorId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.project.monitor.objId='"+(String)queryParam.getValue()+"' ");
				}
				if("useStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.useStatus='"+(String)queryParam.getValue()+"' ");
				};
				if("auditStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.auditStatus='"+(String)queryParam.getValue()+"' ");
				}
				if("projName".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.projName like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("projCode".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.projCode like '%"+(String)queryParam.getValue()+"%' ");
				}
			}
		}
		 Page<Inviterollrequ>  pages = this.findbyHql(hql.toString(), page.getStart(), page.getPageSize(), Inviterollrequ.class);
		return pages;
	}
}
