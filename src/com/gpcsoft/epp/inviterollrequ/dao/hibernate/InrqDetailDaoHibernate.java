package com.gpcsoft.epp.inviterollrequ.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.inviterollrequ.dao.InrqDetailDao;
import com.gpcsoft.epp.inviterollrequ.domain.InrqDetail;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;

@Repository
public class InrqDetailDaoHibernate extends BaseGenericDaoHibernate<InrqDetail> implements InrqDetailDao {

	/**
	 * 
	 * Description :得到供应商邀请函数量  
	 * Create Date: 2010-8-26下午03:44:48 by liuke  Modified Date: 2010-8-26下午03:44:48 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getInrqDetailNumByQueryObject(QueryObject queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(t) From Inviterollrequ t,InrqDetail ti where t.objId=ti.inviterollrequ.objId");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if ("ebuyMethodType".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//采购方式
					hql.append(" and t.project.ebuyMethod = '"+(String)queryParam.getValue()+"'");
				}
				if ("auditStatus".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//审核状态
					hql.append(" and t.auditStatus = '"+(String)queryParam.getValue()+"'");
				}
				if ("supplierId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//供应商
					hql.append(" and ti.supplier.objId ='"+(String)queryParam.getValue()+"'");
				}
				if ("useStatus".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//使用状态
					hql.append(" and ti.useStatus = '"+(String)queryParam.getValue()+"'");
				}
				if ("inrqDKind".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//邀请函种类
					hql.append(" and ti.inrqDKind = '"+(String)queryParam.getValue()+"'");
				}
				if ("projName".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					hql.append(" and t.projName like '%"+(String)queryParam.getValue()+"%'");
				}
				if ("projCode".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					hql.append(" and t.projCode like '%"+(String)queryParam.getValue()+"%'");
				}
			}
		}
		List list=this.findbyHql(hql.toString());
		Object object = list.get(0);
		return new BigDecimal(object.toString());
	}

	/** 
	 * Description :  得到单一来源邀请函列表 
	 * Create Date: 2010-8-28下午02:08:52 by yangx  Modified Date: 2010-8-28下午02:08:52 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Inviterollrequ> getInrqDetailByQueryObject(QueryObject queryObject,Page<InrqDetail> page) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t From Inviterollrequ t,InrqDetail ti where t.objId=ti.inviterollrequ.objId");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if ("ebuyMethodType".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//采购方式
					hql.append(" and t.project.ebuyMethod = '"+(String)queryParam.getValue()+"'");
				}
				if ("auditStatus".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//审核状态
					hql.append(" and t.auditStatus = '"+(String)queryParam.getValue()+"'");
				}
				if ("supplierId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//供应商
					hql.append(" and ti.supplier.objId ='"+(String)queryParam.getValue()+"'");
				}
				if ("useStatus".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//使用状态
					hql.append(" and ti.useStatus = '"+(String)queryParam.getValue()+"'");
				}
				if ("inrqDKind".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {//邀请函种类
					hql.append(" and ti.inrqDKind = '"+(String)queryParam.getValue()+"'");
				}
				if ("projName".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					hql.append(" and t.projName like '%"+(String)queryParam.getValue()+"%'");
				}
				if ("projCode".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					hql.append(" and t.projCode like '%"+(String)queryParam.getValue()+"%'");
				}
			}
		}
		hql.append(" order by t.createTime desc");
		Page pages = this.findbyHql(hql.toString(), page.getStart(), page.getPageSize());
		return pages;
	}

	
	/**
	 * 
	 * Description : 根据邀请名单申请单得到邀请函对象列表   
	 * Create Date: 2010-8-27下午01:25:38 by liuke  Modified Date: 2010-8-27下午01:25:38 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<InrqDetail> getInrqDetailByInviterollrequId(
			String inviterollrequId) {
		List<InrqDetail> list = this.findbyHql("from InrqDetail t where t.inviterollrequ.objId = ?", inviterollrequId);
		return list;
	}

	/**
	 * 
	 * Description : 删除邀请函 
	 * Create Date: 2010-8-27下午05:02:03 by liuke  Modified Date: 2010-8-27下午05:02:03 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public void removeinrqDetailList(final String InviterollrequId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="delete from InrqDetail t where t.inviterollrequ.objId ='"+InviterollrequId+"'";
				Query query = session.createQuery(hql);
				return query.executeUpdate();
			}
		});
		
	}
	/** 
	 * Description : 根据项目Id获取录入的供应商 
	 * Create Date: 2010-8-27下午05:51:23 by yangx  Modified Date: 2010-8-27下午05:51:23 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<InrqDetail> getInrqDetailByProjectId(String projectId){
		String hql = "From InrqDetail t where t.inviterollrequ.project.objId='"+projectId+"'";
		List<InrqDetail> list = this.findbyHql(hql);
		return list;
	}
	
	/** 
	 * Description :  根据项目Id与供应商Id获取邀请函明细
	 * Create Date: 2010-8-28上午11:55:16 by yangx  Modified Date: 2010-8-28上午11:55:16 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public InrqDetail getInrqDetailByProjectIdAndSupplierId(String projectId){
		StringBuffer hql = new StringBuffer();
		hql.append("From InrqDetail t where t.inviterollrequ.project.objId='"+projectId+"'");
		List<InrqDetail> list = this.findbyHql(hql.toString());
		return list!=null&&list.size()>0?list.get(0):null;
	}

}
