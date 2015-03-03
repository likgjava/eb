package com.gpcsoft.epp.projreview.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.projreview.domain.EvaSellerRecord;
import com.gpcsoft.epp.projreview.domain.EvalBidRecord;
import com.gpcsoft.epp.projreview.domain.EvalFactorResult;

public interface EvalBidRecordService extends BaseGenericService<EvalBidRecord> {
	
	/**
	 * FuncName:getEvalBidRecordByObjId
	 * Description :根据主键得到评标结果对象  
	 * @param   objId:评标结果主键
	 * @return  EvalBidRecord
	 * @author 	  liuke
	 * @Create Date: 2010-9-7下午03:31:12 
	 * @Modifier   liuke
	 * @Modified Date: 2010-9-7下午03:31:12 
	 */
	public 	EvalBidRecord getEvalBidRecordByObjId(String objId);
	
	/**
	 * FuncName:getEvalBidRecordBySubProjectId
	 * Description:根据包组获得评标列表
	 * @param   subProjectId:包组主键
	 * @return  List<EvalBidRecord>
	 * @author 	   liuke
	 * @Create Date:2010-9-19下午03:32:54 
	 * @Modifier   liuke
	 * @Modified Date: 2010-9-19下午03:32:54 
	 */
	public List<EvalBidRecord> getEvalBidRecordBySubProjectId(String subProjectId);

	/**
	 * FuncName:getAllBidSupplierByPackId
	 * Description:获取所有投标供应商
	 * @param packId:包件ID/项目ID
	 * @return List<Map<String,String>> 为了避免代码耦合返回 MAP 集合,map{supplierId:供应商ID,supplierName:供应商名称}
	 * @author wanghz
	 * @Create Date 2010-9-20 下午03:39:10 
	 */
	public List<Map<String,String>> getAllBidSupplierByPackId(String packId);
	
	/**
	 * FuncName:getAllEvalFactorResult
	 * Description: 获取所有评审打分
	 * @param supplierId:供应商ID,packId:项目/包组ID,factorTypeId:指标分类ID,userId:打分人
	 * @return List<EvalFactorResult>
	 * @author wanghz
	 * @Create Date 2010-10-9 下午03:20:16
	 */
	public List<EvalFactorResult> getAllEvalFactorResult(String supplierId,String packId,String factorTypeId,String userId);
	
	/**
	 * FuncName:getEvaSellerRecord
	 * Description: 获取评审记录
	 * @param supplierId 供应商ID,packId 项目/包组ID,userId 打分人
	 * @return EvaSellerRecord
	 * @author wanghz
	 * @Create Date 2010-10-9 下午04:34:20 
	 */
	public EvaSellerRecord getEvaSellerRecord(String supplierId,String packId,String userId);
	
	/**
	 * FuncName:getBidQuoteSum
	 * Description:获取投标总金额
	 * @param supplierId:供应商ID,packId:包件ID
	 * @return BigDecimal
	 * @author wanghz
	 * @Create Date 2010-10-9 下午05:30:38 
	 */
	public BigDecimal getBidQuoteSum(String supplierId,String packId);
}
