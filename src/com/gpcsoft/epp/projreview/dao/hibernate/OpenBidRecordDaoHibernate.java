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
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.dao.OpenBidRecordDao;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
@Repository
public class OpenBidRecordDaoHibernate extends BaseGenericDaoHibernate<OpenBidRecord> implements OpenBidRecordDao {
	
	/**
	 * FuncName:getOpenBidRecordByPackId
	 * Description : 根据包件ID得到开标记录对象
	 * @param   packId:包组主键
	 * @return   List<OpenBidRecord>
	 * @author 		liuke
	 * @Create Date: 2010-5-26上午10:28:46 
	 * @Modifier    liuke
	 * @Modified Date: 2010-5-26上午10:28:46 
	 */
	public List<OpenBidRecord> getOpenBidRecordByPackId(String packId) {
		return this.findbyHql("from OpenBidRecord as openBidRecord where openBidRecord.subProjId = ? ", packId);
	}
	
	/**
	 * FuncName:getOpenBidRecordByOPenBidId
	 * Description :  根据开标记录主表得到开标记录对象
	 * @param   openBidId:开标记录主键
	 * @return  List<OpenBidRecord>
	 * @author liuke
	 * @Create Date: 2010-5-26上午10:28:46 
	 * @Modifier  liuke
	 * @Modified Date: 2010-5-26上午10:28:46 
	 */
	public List<OpenBidRecord> getOpenBidRecordByOPenBidId(String openBidId) {
		return this.findbyHql("from OpenBidRecord openBidRecord where openBidRecord.openBId=?", openBidId);
	}
	
	/**
	  * FuncName:getOpenBidRecordbyBidId
	  * Description:根据投标主键得到开标子表对象
	  * @param   bidId:投标主键
	  * @return  OpenBidRecord
	  * @author liuke
	  * @Create Date: 2010-5-26下午04:24:32 
	  * @Modifier   liuke
	  * @Modified Date: 2010-5-26下午04:24:32 
	  */
	public OpenBidRecord getOpenBidRecordbyBidId(String bidId) {
		List list = this.findbyHql("from OpenBidRecord openBidRecord where openBidRecord.bidId=?", bidId);
		if(list.size()>0) {
			return (OpenBidRecord) list.get(0);
		}else {
			return null;
		}
	}
	
	 /**
	  * FuncName:updateOpenBidRecord
	  * Description : 更新开标子表对象 
	  * @param   openBidRecord:开标子表
	  * @return  void
	  * @author liuke
	  * @Create Date: 2010-5-26下午04:36:22 
	  * @Modifier   liuke
	  * @Modified Date: 2010-5-26下午04:36:22 
	  */
	public void updateOpenBidRecord(OpenBidRecord openBidRecord) {
		openBidRecord = this.getHibernateTemplate().merge(openBidRecord);  
		this.getHibernateTemplate().update(openBidRecord);
	}
	
	 /**
	  * FuncName:getAllByProjectId
	  * Description: 根据项目ID获取所有开标记录
	  * @param projectId:项目主键
	  * @return List<OpenBidRecord>
	  * @author wanghz
	  * @Create Date 2010-8-1 下午02:21:21  
	  */
	public List<OpenBidRecord> getAllByProjectId(String projectId) throws Exception{
		StringBuffer hql = new StringBuffer();
		hql.append("from OpenBidRecord as o where o.subProjId in (select p.objId from Project as p where p.parentId = ?) and ");
		hql.append("o.objId not in (select oo.openBidRecord.objId from OpenbidGeneralview as oo where oo.subProj.parentId = ? ) ");
		hql.append("order by o.createTime");
		List<OpenBidRecord> openBidRecordList = this.getSession().createQuery(hql.toString()).setString(0, projectId).setString(1, projectId).list();
		if(openBidRecordList.isEmpty()){
			return new ArrayList<OpenBidRecord>(0);
		}
		return openBidRecordList;
	}
	
	/**
	 * FuncName:getOpenBidRecordBySupplierIdAndSubProjectId
	 * Description :根据包组和供应商得到开标记录对象 
	 * @param   projectId:项目主键,supplierId供应商主键
	 * @return  OpenBidRecord
	 * @author liuke
	 * @Create Date: 2010-8-7下午06:25:01 
	 * @Modifier  liuke
	 * @Modified Date: 2010-8-7下午06:25:01 
	 */
	public OpenBidRecord getOpenBidRecordBySupplierIdAndSubProjectId(String projectId, String supplierId) {
		 List<OpenBidRecord> list = this.findbyHql("from OpenBidRecord obr where obr.subProjId=? and obr.supplier.objId = ?", projectId,supplierId);
		 if(list.size()>0){
			 return list.get(0);
		 }else{
			 return null; 
		 }
	}
	
	/**
	 * FuncName:getAllNoPackByProjectId
	 * Description : 得到所有未分包的项目的开标记录信息 
	 * @param   projectId:项目主键
	 * @return  List<OpenBidRecord>
	 * @author liuke
	 * @Create Date: 2010-9-2上午10:59:36 
	 * @Modifier    liuke
	 * @Modified Date: 2010-9-2上午10:59:36  
	 */
	public List<OpenBidRecord> getAllNoPackByProjectId(String projectId)
			throws Exception {
		List <OpenBidRecord> list = this.findbyHql("from OpenBidRecord t where t.subProjId = ? and t.objId not in ( select oo.openBidRecord.objId from OpenbidGeneralview as oo where oo.subProj.objId = ? )", projectId,projectId);
		if(list.isEmpty()){
			return new ArrayList<OpenBidRecord>();
		}
		return list;
	}
	
	 /**
	  * FuncName:getAllOpenBidRecordByProjectId
	  * Description :根据项目得到所有开标记录  
	  * @param   projectId:项目主键
	  * @return  List<OpenBidRecord>
	  * @author 	liuke
	  * @Create Date: 2010-10-11下午06:03:53
	  * @Modifier   liuke
	  * @Modified Date: 2010-10-11下午06:03:53  
	  */
	public List<OpenBidRecord> getAllOpenBidRecordByProjectId(String projectId)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from OpenBidRecord as o where o.subProjId = ? or (o.subProjId in (select p.objId from Project as p where p.parentId = ?))");
		hql.append("order by o.createTime");
		List<OpenBidRecord> openBidRecordList = this.getSession().createQuery(hql.toString()).setString(0, projectId).setString(1, projectId).list();
		if(openBidRecordList.isEmpty()){
			return new ArrayList<OpenBidRecord>(0);
		}
		return openBidRecordList;
	}
	
	 /**
	  * FuncName:removeAllOpenBidRecordByProject
	  * Description :根据项目删除开标记录
	  * @param projectId
	  * @return  void
	  * @author liuke
	  * @Create Date: 2010-12-15下午05:35:40
	  * @Modifier   liuke
	  * @Modified Date: 2010-12-15下午05:35:40  
	  */
	@SuppressWarnings("unchecked")
	public void removeAllOpenBidRecordByProject(final String projectId) {
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("delete from OpenBidRecord t where t.subProjId in (select p.objId from Project p where p.objId = ? or p.parentId = ?)");
				Query query  = session.createQuery(hql.toString());
				query.setString(0, projectId).setString(1, projectId).executeUpdate();
				return null;
			}});
	}

	
	 /**
	  * FuncName: removeAllOpenBidRecordBySubProjectIdAndBidId
	  * Description :  根据包组和投标ID删除开标记录表对象
	  * @param 
	  * @param subProjId
	  * @param bidId void
	  * @author: liuke
	  * @Create Date:2011-3-11  下午08:09:09
	  * @Modifier: liuke
	  * @Modified Date:2011-3-11  下午08:09:09
	  */
	@SuppressWarnings("unchecked")
	public void removeAllOpenBidRecordByBidId(
			final String bidId) {
		  this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append(" delete from OpenBidRecord t where ");
				hql.append(" t.bidId=:bidId  ");
				session.createQuery(hql.toString()).setString("bidId", bidId).executeUpdate();
				return null;
			}});
		
	}
	
	/**
	  * FuncName:getOpenBidRecordListByOpenBidIdAndSupplierId
	  * Description :根据开标Id和供应商ID获得开标记录对象
	  * @param   openBidId:开标ID
	  * @param   supplierId:供应商ID
	  * @return  OpenBidRecord
	  * @author  shengn
	  * @Create  Date:2011-10-12 15:59  
	  */
	@SuppressWarnings("unchecked")
	public OpenBidRecord getOpenBidRecordListByOpenBidIdAndSupplierId(String openBidId,String supplierId)throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from OpenBidRecord as o where o.openBId=? and o.supplier.objId=?");
		hql.append("order by o.createTime");
		List<OpenBidRecord> openBidRecordList = this.getHibernateTemplate().find(hql.toString(), new Object[]{openBidId,supplierId});
		return openBidRecordList.isEmpty()?null:(OpenBidRecord)openBidRecordList.get(0);
	}
}
