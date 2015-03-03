package com.gpcsoft.epp.projreview.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.projreview.domain.EvaSellerRecord;

public interface EvaSellerRecordManager extends BaseManager<EvaSellerRecord> {

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
	public List<EvaSellerRecord> getEvaSellerRecordListByEvalBidRecordId(String evalBidRecordId);

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
	public List<EvaSellerRecord> getEvaSellerRecordListOrderByScore(String subProjId);

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
	public List<EvaSellerRecord> getEvaSellerRecordList(String subProjId);

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
	public List<EvaSellerRecord> getEvaSellerRecordListBySubProjectAndSupplier(String subProjId, String supplierId);

	/**
	 * FuncName:getEvaSellerRecord
	 * Description: 获取评审记录
	 * @param supplierId:供应商主键,packId:项目/包组主键,userId:打分人
	 * @return EvaSellerRecord
	 * @author wanghz
	 * @Create Date 2010-10-9 下午04:34:20 
	 */
	public EvaSellerRecord getEvaSellerRecord(String supplierId,String packId,String userId);
}
