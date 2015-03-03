package com.gpcsoft.epp.projreview.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.projreview.domain.EvalBidRecord;
import com.gpcsoft.epp.projreview.domain.EvalFactorResult;

public interface EvalBidRecordDao extends BaseGenericDao<EvalBidRecord> {

	/**
	 * FuncName:getEvalBidRecordBySubProjectId
	 * Description: 根据包组获得评标列表 
	 * @return  List<EvalBidRecord>
	 * @author liuke
	 * @Create Date: 2010-9-19下午03:37:08 
	 * @Modifier liuke   
	 * @Modified Date: 2010-9-19下午03:37:08  
	 */
	public List<EvalBidRecord> getEvalBidRecordBySubProjectId(String subProjectId);
	
	/**
	 * FuncName:getAllBidSupplierByPackId
	 * Description: 获取所有投标供应商
	 * @param packId:包件ID/项目ID
	 * @return List<Map<String,String>>:为了避免代码耦合返回 MAP 集合,map{supplierId:供应商ID,supplierName:供应商名称}
	 * @author wanghz
	 * @Create Date 2010-9-20 下午03:39:10 
	 */
	public List<Map<String,String>> getAllBidSupplierByPackId(String packId);
	
	/**
	 * FuncName:getAllEvalFactorResult
	 * Description: 获取所有评审打分
	 * @param supplierId:供应商ID,packId:项目/包组ID,factorTypeId:指标分类主键,userId:打分人
	 * @return List<EvalFactorResult>
	 * @author wanghz
	 * @Create Date 2010-10-9 下午03:20:16 
	 */
	public List<EvalFactorResult> getAllEvalFactorResult(String supplierId,String packId,String factorTypeId,String userId);
	
	/**
	 * FuncName:getAllCongFactorResponse
	 * Description: 获取所有指标响应
	 * @param supplierId:供应商ID,packId:包件ID/项目Id,factorTypeId:指标分类ID
	 * @return List<CongFactorResponse>
	 * @throws wanghz
	 * @Create Date 2010-9-20 下午10:16:06
	 */
	public List<CongFactorResponse> getAllCongFactorResponse(final String supplierId,final String packId,final String factorTypeId);
	
	/**
	 * FuncName:getBidQuoteSum
	 * Description:获取投标总金额
	 * @param supplierId 供应商ID,packId:包件ID
	 * @return BigDecimal
	 * @author wanghz
	 * @Create Date 2010-10-9 下午05:30:38  
	 */
	public BigDecimal getBidQuoteSum(String supplierId,String packId);
	
	/**
	 * FuncName: removeEvalBidRecordByPack
	 * Description :  删除评标记录
	 * @param 
	 * @param packId void
	 * @author: liuke
	 * @Create Date:2011-5-28  下午04:19:49
	 * @Modifier: liuke
	 * @Modified Date:2011-5-28  下午04:19:49
	 */
	public void removeEvalBidRecordByPack(String packId);

}