package com.gpcsoft.epp.projreview.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.projreview.dao.EvaSellerRecordDao;
import com.gpcsoft.epp.projreview.domain.EvaSellerRecord;

@Repository
public class EvaSellerRecordDaoHibernate extends BaseGenericDaoHibernate<EvaSellerRecord> implements EvaSellerRecordDao {
	
	/**
	 * FuncName:getEvaSellerRecordListOrderByScore
	 * Description : 根据项目主键得到按推荐次数排列并且过滤的评标结果 
	 * @param   subProjId:包组主键
	 * @return  List<EvaSellerRecord>
	 * @author liuke
	 * @Create Date: 2010-6-26下午01:30:15    
	 * @Modifier liuke
	 * @Modified Date: 2010-6-26下午01:30:15 
	 */
	public List<EvaSellerRecord> getEvaSellerRecordListOrderByScore(String subProjId) {
		List list = this.findbyHql("select t.supplier.objId,count(t.recommend) as num  from EvaSellerRecord t where t.subProjId = ? and t.recommend = '01' group by t.supplier.objId order by  t.supplier.objId ", subProjId);
		List<EvaSellerRecord> evaSellerRecordList = new ArrayList<EvaSellerRecord>();
		for(int i=0;i<list.size();i++){
			String supplierId = (String)list.get(0);
			String num = (String)list.get(1);
			EvaSellerRecord evaSellerRecord = new EvaSellerRecord();
			OrgInfo orgInfo = new OrgInfo();
			orgInfo.setObjId(supplierId);
			evaSellerRecord.setSupplier(orgInfo);
			evaSellerRecord.setNum(num);
			evaSellerRecordList.add(evaSellerRecord);
		}
		return evaSellerRecordList;
	}
    
	/**
	 * FuncName:getEvaSellerRecordBySupplierId
	 * Description : 根据供应商主键得到评标结果信息 
	 * @param   supplierId:供应商主键
	 * @return  EvaSellerRecord
	 * @author 		liuke
	 * @Create Date: 2010-6-26下午02:56:52 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-26下午02:56:52  
	 */
	public EvaSellerRecord getEvaSellerRecordBySupplierId(String supplierId) {
		List<EvaSellerRecord> list = this.findbyHql("from EvaSellerRecord e where e.supplier.objId=?", supplierId);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/**
	 * FuncName:getEvaSellerRecordList
	 * Description : 根据项目主键评标结果 
	 * @param   subProjId:包组主键
	 * @return  List<EvaSellerRecord>
	 * @author  liuke
	 * @Create Date: 2010-6-26下午01:30:15 
	 * @Modifier liuke
	 * @Modified Date: 2010-6-26下午01:30:15  
	 */
	public List<EvaSellerRecord> getEvaSellerRecordList(String subProjId) {
		List<EvaSellerRecord> list =this.findbyHql("from EvaSellerRecord e where e.subProjId=? order by quoteSum asc ", subProjId);
		return list;
	}
     
	/**
	 * FuncName:getEvaSellerRecordListByproject
	 * Description:根据项目主键评标结果 
	 * @param   projectId:项目主键
	 * @return  List<EvaSellerRecord>
	 * @author    liuke
	 * @Create Date: 2010-6-26下午01:30:15 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-26下午01:30:15  
	 */
	public List<EvaSellerRecord> getEvaSellerRecordListByproject(String projectId) {
		StringBuffer sb = new StringBuffer();
		sb.append("from EvaSellerRecord esr left join EvalBidRecord ebr on esr.evalId = ebr.objId where ebr.projId =?");
		List<EvaSellerRecord> list =  this.findbyHql(sb.toString(), projectId);
		if(list.isEmpty()) {
		    	return new ArrayList<EvaSellerRecord>(0);
		 }
		return list;
	}

	/**
     * FuncName:getEvaSellerRecordListBySubProjectAndSupplier
     * Description : 根据项目主键与供应商获得评标结果 
     * @param   subProjId:包组主键,supplierId:供应商主键
     * @return   List<EvaSellerRecord> 
     * @author	 liuke
     * @Create Date: 2010-6-26下午01:30:15 
     * @Modifier   liuke
     * @Modified Date: 2010-6-26下午01:30:15 
     */
	public List<EvaSellerRecord> getEvaSellerRecordListBySubProjectAndSupplier(String subProjId, String supplierId) {
		List<EvaSellerRecord> list = this.findbyHql("from EvaSellerRecord er where er.supplier.objId =? and er.subProjId =?", supplierId,subProjId);
		return list;
	}
	
	/**
	 * FuncName:getEvaSellerRecord
	 * Description: 获取评审记录
	 * @param supplierId 供应商主键,packId 项目/包组主键,userId:打分人
	 * @return EvaSellerRecord
	 * @author wanghz
	 * @Create Date 2010-10-9 下午04:34:20  
	 */
	@SuppressWarnings("unchecked")
	public EvaSellerRecord getEvaSellerRecord(final String supplierId,final String packId,final String userId){
		return (EvaSellerRecord) getHibernateTemplate().execute(new HibernateCallback(){
			public EvaSellerRecord doInHibernate(Session session)throws HibernateException, SQLException {	
				StringBuffer hql = new StringBuffer();
				hql.append(" select evaSellerRecord from EvaSellerRecord as evaSellerRecord,EvalBidRecord as evalBidRecord ");
				hql.append(" where evaSellerRecord.supplier.objId=:supplierId ");
				hql.append(" and evaSellerRecord.evalId = evalBidRecord.objId ");
				hql.append(" and ( evalBidRecord.subProjId=:packId or evalBidRecord.projId=:packId ) ");
				hql.append(" and  evalBidRecord.evalLinker=:userId ");
				List<EvaSellerRecord> evaSellerRecordList = session.createQuery(hql.toString())
				.setString("supplierId", supplierId)
				.setString("packId", packId)
				.setString("userId", userId)
				.list();
				if (null != evaSellerRecordList && evaSellerRecordList.size()>0){
					return evaSellerRecordList.get(0);
				}
				return null;
			}
		});
	}

	
	/**
	 * FuncName: removeEvaSellerRecordByPack
	 * Description :  创建或修改对象
	 * @param 
	 * @param packId void
	 * @author: liuke
	 * @Create Date:2011-5-28  下午04:27:07
	 * @Modifier: liuke
	 * @Modified Date:2011-5-28  下午04:27:07
	 */
	@SuppressWarnings("unchecked")
	public void removeEvaSellerRecordByPack(final String packId) {
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("delete from EvaSellerRecord t where t.subProjId=:packId");
				Query query =  session.createQuery(hql.toString());
				query.setString("packId", packId).executeUpdate();
				return null;
			}});
		
	}
}
