package com.gpcsoft.epp.projreview.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.projreview.dao.OpenbidGeneralviewDao;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;

@Repository
public class OpenbidGeneralviewDaoHibernate extends BaseGenericDaoHibernate<OpenbidGeneralview> implements OpenbidGeneralviewDao {
	
	/**
	 * FuncName:getAllByProjectId
	 * Description: 根据项目ID获取所有开标一览表
	 * @param projectId:项目主键
	 * @return List<OpenbidGeneralview>
	 * @author wanghz
	 * @Create Date 2010-8-1 下午02:30:11 
	 */
	public List<OpenbidGeneralview> getAllByProjectId(String projectId)throws Exception {
		String hql = "from OpenbidGeneralview as o where (o.subProj.parentId = ? or o.subProj.objId = ? ) order by o.objId ";
		List<OpenbidGeneralview> openbidGeneralviewList = this.getSession().createQuery(hql).setString(0, projectId).setString(1, projectId).list();
		if(openbidGeneralviewList.isEmpty()){
			return new ArrayList<OpenbidGeneralview>(0);
		}
		return openbidGeneralviewList;
	}
	
	/**
	 * FuncName:validateIsEndProjectPlan
	 * Description: 验证项目计划是否完成
	 * @param packId 包件ID
	 * @return Boolean
	 * @author wanghz
	 * @Create Date 2010-8-1 下午11:10:46  
	 */
	public Boolean validateIsEndProjectPlan(String packId) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(o.objId) from OpenBidRecord as o where o.subProjId in ( ");
		hql.append("select pack.objId from Project as pack where pack.parentId in ( ");
		hql.append("select project.parentId from Project as project where project.objId = ? ) ) ");
		hql.append("and o.objId not in ( select oo.openBidRecord.objId from OpenbidGeneralview as oo ");
		hql.append("where oo.subProj.parentId in ( select project1.parentId from Project as project1 ");
		hql.append("where project1.objId = ? ) ) ");
		Long num = (Long)this.getSession().createQuery(hql.toString()).setString(0,packId).setString(1,packId).list().get(0);
		if(num == 0){
			return true;
		}
		return false;
	}

	/**
	 * FuncName:getAllNoPackByProjectId
	 * Description :得到所有未分包的项目开标一览表 
	 * @param   projectId:项目主键
	 * @return  List<OpenbidGeneralview>
	 * @author liuke
	 * @Create Date: 2010-9-2上午10:46:43 
	 * @Modifier   liuke
	 * @Modified Date: 2010-9-2上午10:46:43  
	 */
	public List<OpenbidGeneralview> getAllNoPackByProjectId(String projectId)
			throws Exception {
		List<OpenbidGeneralview> list = this.findbyHql("from OpenbidGeneralview t where t.subProj.objId = ?", projectId);
		if(list.isEmpty()){
			return new ArrayList<OpenbidGeneralview>(0);
		}
		return list;
	}
	
	/**
	 * FuncName:getOpenbidGeneralviewByOpenBidRecordId
	 * Description :根据评审结果主键获得开标一览表对象  
	 * @param   bidRecordId:评审结果主键
	 * @return  OpenbidGeneralview
	 * @author 	  liuke
	 * @Create Date: 2010-9-16下午04:49:11 
	 * @Modifier  liuke
	 * @Modified Date: 2010-9-16下午04:49:11 
	 */
	public OpenbidGeneralview getOpenbidGeneralviewByOpenBidRecordId(String bidRecordId) {
		List<OpenbidGeneralview> list = this.findbyHql("from OpenbidGeneralview t where t.openBidRecord.objId = ?", bidRecordId);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * FuncName:removeAllOpenbidGeneralviewByProject
	 * Description :根据项目主键删除所有开标一览表
	 * @param projectId:项目主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-12-15下午05:04:07 
	 * @Modifier   liuke
	 * @Modified Date: 2010-12-15下午05:04:07 
	 */
	@SuppressWarnings("unchecked")
	public void removeAllOpenbidGeneralviewByProject(final String projectId) {
	     this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "delete from ecp_openbid_generalview t where t.sub_proj_id in (select p.tenderid from ecp_tender_project p where p.parent_id = ? or p.tenderid = ?)";
				Query query  = session.createSQLQuery(sql);
				query.setString(0, projectId).setString(1, projectId).executeUpdate();
				return null;
			}});
	}

	
	
	/**
	 * FuncName: getOpenbidGeneralviewBySupplierIdAndPackId
	 * Description :  根据包件与供应商ID获得开标一览表数据
	 * @param 
	 * @param supplierId
	 * @param packId void
	 * @author: liuke
	 * @Create Date:2011-9-29  下午07:13:21
	 * @Modifier: liuke
	 * @Modified Date:2011-9-29  下午07:13:21
	 */
	public OpenbidGeneralview getOpenbidGeneralviewBySupplierIdAndPackId(String supplierId,String packId) {
		List<OpenbidGeneralview> list = this.findbyHql("from OpenbidGeneralview t where t.supplierId = '"+supplierId+"' and t.subProj.objId = '"+packId+"' ");
		return (!list.isEmpty())?list.get(0):null  ;
	}

}
