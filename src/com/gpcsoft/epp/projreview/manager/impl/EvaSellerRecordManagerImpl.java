package com.gpcsoft.epp.projreview.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.projreview.dao.EvaSellerRecordDao;
import com.gpcsoft.epp.projreview.domain.EvaSellerRecord;
import com.gpcsoft.epp.projreview.manager.EvaSellerRecordManager;

@Repository
public class EvaSellerRecordManagerImpl extends BaseManagerImpl<EvaSellerRecord> implements EvaSellerRecordManager {

	@Autowired(required=true)  @Qualifier("evaSellerRecordDaoHibernate")
	private EvaSellerRecordDao evaSellerRecordDaoHibernate;

	/**
	 * FuncName:getEvaSellerRecordListByEvalBidRecordId
	 * Description :根据项目评审主表主键得到项目评审字表列表  
	 * @param   evalBidRecordId:项目评审主表主键
	 * @return  List<EvaSellerRecord>
	 * @author liuke
	 * @Create Date: 2010-6-4下午02:38:02
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-4下午02:38:02  
	 */
	public List<EvaSellerRecord> getEvaSellerRecordListByEvalBidRecordId(String evalBidRecordId) {
		QueryObject<EvaSellerRecord> queryObject = new QueryObjectBase<EvaSellerRecord>();
		queryObject.setEntityClass(EvaSellerRecord.class);
		queryObject.setQueryParam(new QueryParam("evalId",QueryParam.OPERATOR_EQ,evalBidRecordId));
		return evaSellerRecordDaoHibernate.findByQuery(queryObject);
	}
    
	/**
	 * FuncName:getEvaSellerRecordListOrderByScore
	 * Description : 根据项目主键得到按推荐次数排列并且过滤的评标结果 
	 * @param   subProjId:包组主键
	 * @return  List<EvaSellerRecord>
	 * @author liuke
	 * @Create Date: 2010-6-26下午01:30:15 
	 * @Modifier    liukes
	 * @Modified Date: 2010-6-26下午01:30:15  
	 */
	public List<EvaSellerRecord> getEvaSellerRecordListOrderByScore(String subProjId) {
		List list =  evaSellerRecordDaoHibernate.getEvaSellerRecordListOrderByScore(subProjId);
		return list;
	}

	/**
	 * FuncName:getEvaSellerRecordList
	 * Description : 根据项目主键评标结果 
	 * @param   subProjId:包组主键
	 * @return  List<EvaSellerRecord>
	 * @author liuke
	 * @Create Date: 2010-6-26下午01:30:15 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-26下午01:30:15  
	 */
	public List<EvaSellerRecord> getEvaSellerRecordList(String subProjId) {
		return evaSellerRecordDaoHibernate.getEvaSellerRecordList(subProjId);
	}
   
	/**
	 * FuncName:getEvaSellerRecordListBySubProjectAndSupplier
	 * Description : 根据包组主键与供应商得到评标结果 
	 * @param subProjId:包组主键,supplierId:供应商主键
	 * @return List<EvaSellerRecord>
	 * @author liuke
	 * @Create Date: 2010-8-12上午11:41:51 
	 * @Modifier liuke
	 * @Modified Date: 2010-8-12上午11:41:51  
	 */
	public List<EvaSellerRecord>  getEvaSellerRecordListBySubProjectAndSupplier(String subProjId,String supplierId){
		return evaSellerRecordDaoHibernate.getEvaSellerRecordListBySubProjectAndSupplier(subProjId, supplierId);
	}
	
	/**
	 * FuncName:getEvaSellerRecord
	 * Description: 获取评审记录
	 * @param supplierId:供应商主键,packId:项目/包组主键,userId:打分人
	 * @return EvaSellerRecord
	 * @author wanghz
	 * @Create Date 2010-10-9 下午04:34:20 
	 */
	public EvaSellerRecord getEvaSellerRecord(String supplierId,String packId,String userId){
		return evaSellerRecordDaoHibernate.getEvaSellerRecord(supplierId, packId, userId);
	}
}
