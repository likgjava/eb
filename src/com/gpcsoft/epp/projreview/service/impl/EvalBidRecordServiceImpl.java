package com.gpcsoft.epp.projreview.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.epp.eval.manager.CongruousFactorManager;
import com.gpcsoft.epp.projreview.dao.EvalBidRecordDao;
import com.gpcsoft.epp.projreview.domain.EvaSellerRecord;
import com.gpcsoft.epp.projreview.domain.EvalBidRecord;
import com.gpcsoft.epp.projreview.domain.EvalFactorResult;
import com.gpcsoft.epp.projreview.manager.EvaSellerRecordManager;
import com.gpcsoft.epp.projreview.manager.EvalBidRecordManager;
import com.gpcsoft.epp.projreview.service.EvalBidRecordService;

@Service 
public class EvalBidRecordServiceImpl extends BaseGenericServiceImpl<EvalBidRecord> implements EvalBidRecordService {

	@Autowired(required=true) @Qualifier("evalBidRecordManagerImpl")
	private EvalBidRecordManager evalBidRecordManager;

	@Autowired(required=true)  @Qualifier("evalBidRecordDaoHibernate")
	private EvalBidRecordDao evalBidRecordDaoHibernate;
	@Autowired(required=true) @Qualifier("evaSellerRecordManagerImpl")
	private EvaSellerRecordManager evaSellerRecordManager;
	
	@Autowired(required=true) @Qualifier("congruousFactorManagerImpl")
	private CongruousFactorManager congruousFactorManager;
	
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
	public EvalBidRecord getEvalBidRecordByObjId(String objId) {
		logger.debug("\nes EvalBidRecordServiceImpl||getEvalBidRecordByObjId\n");
		return evalBidRecordManager.get(objId);
	}

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
	public List<EvalBidRecord> getEvalBidRecordBySubProjectId(String subProjectId) {
		logger.debug("\nes EvalBidRecordServiceImpl||getEvalBidRecordBySubProjectId\n");
		return evalBidRecordDaoHibernate.getEvalBidRecordBySubProjectId(subProjectId);
	}

	/**
	 * FuncName:getAllBidSupplierByPackId
	 * Description:获取所有投标供应商
	 * @param packId:包件ID/项目ID
	 * @return List<Map<String,String>> 为了避免代码耦合返回 MAP 集合,map{supplierId:供应商ID,supplierName:供应商名称}
	 * @author wanghz
	 * @Create Date 2010-9-20 下午03:39:10 
	 */
	public List<Map<String,String>> getAllBidSupplierByPackId(String packId){
		logger.debug("\nes EvalBidRecordServiceImpl||getAllBidSupplierByPackId\n");
		return evalBidRecordDaoHibernate.getAllBidSupplierByPackId(packId);
	}
	
	/**
	 * FuncName:getAllEvalFactorResult
	 * Description: 获取所有评审打分
	 * @param supplierId:供应商ID,packId:项目/包组ID,factorTypeId:指标分类ID,userId:打分人
	 * @return List<EvalFactorResult>
	 * @author wanghz
	 * @Create Date 2010-10-9 下午03:20:16
	 */
	public List<EvalFactorResult> getAllEvalFactorResult(String supplierId,String packId,String factorTypeId,String userId){
		logger.debug("\nes EvalBidRecordServiceImpl||getAllEvalFactorResult\n");
		List<EvalFactorResult> evalFactorResultList = evalBidRecordDaoHibernate.getAllEvalFactorResult(supplierId, packId, factorTypeId, userId);// 获取评审记录
		List<EvalFactorResult> evalFactorResultList2 = new ArrayList<EvalFactorResult>();
		for (Iterator iterator = evalFactorResultList.iterator(); iterator
				.hasNext();) {
			EvalFactorResult evalFactorResult = (EvalFactorResult) iterator
					.next();
			CongruousFactor congruousFactor = congruousFactorManager.get(evalFactorResult.getFactorId());
			evalFactorResult.setMaxScore(congruousFactor.getScore().toString());
			evalFactorResultList2.add(evalFactorResult);
			
		}
		if (null == evalFactorResultList || evalFactorResultList.isEmpty()) {// 没有评审记录，则获取投标响应组装评审记录
			List<CongFactorResponse> congFactorResponseList = evalBidRecordDaoHibernate.getAllCongFactorResponse(supplierId, packId, factorTypeId);
			for (CongFactorResponse congFactorResponse:congFactorResponseList) {
				EvalFactorResult evalFactorResult = new EvalFactorResult();
				evalFactorResult.setFactorId(congFactorResponse.getFactorId());
				evalFactorResult.setFactorName(congFactorResponse.getFactorName());
				CongruousFactor congruousFactor = congruousFactorManager.get(congFactorResponse.getFactorId());
				evalFactorResult.setFactorTypeId(congruousFactor.getFactorType().getObjId());
				evalFactorResult.setFactorTypeName(congruousFactor.getFactorType().getTypeName());
				evalFactorResult.setRespValue(congFactorResponse.getRespValue());
				evalFactorResult.setMaxScore(congruousFactor.getScore().toString());
				String respAttrId = "";
				String respAttrName = "";
				if (null != congFactorResponse.getAttr() && null != congFactorResponse.getAttr().getObjId()) {
					respAttrId = congFactorResponse.getAttr().getObjId();
					respAttrName = congFactorResponse.getAttr().getViewName();
				}
				evalFactorResult.setRespAttrId(respAttrId);
				evalFactorResult.setRespAttrName(respAttrName);
				evalFactorResultList2.add(evalFactorResult);
			}
		}
		return evalFactorResultList2;
	}
	
	/**
	 * FuncName:getEvaSellerRecord
	 * Description: 获取评审记录
	 * @param supplierId 供应商ID,packId 项目/包组ID,userId 打分人
	 * @return EvaSellerRecord
	 * @author wanghz
	 * @Create Date 2010-10-9 下午04:34:20 
	 */
	public EvaSellerRecord getEvaSellerRecord(String supplierId,String packId,String userId){
		logger.debug("\nes EvalBidRecordServiceImpl||getEvaSellerRecord\n");
		return evaSellerRecordManager.getEvaSellerRecord(supplierId, packId, userId);
	}
	
	/**
	 * FuncName:getBidQuoteSum
	 * Description:获取投标总金额
	 * @param supplierId:供应商ID,packId:包件ID
	 * @return BigDecimal
	 * @author wanghz
	 * @Create Date 2010-10-9 下午05:30:38 
	 */
	public BigDecimal getBidQuoteSum(String supplierId,String packId){
		logger.debug("\nes EvalBidRecordServiceImpl||getBidQuoteSum\n");
		return evalBidRecordDaoHibernate.getBidQuoteSum(supplierId, packId);
	}

}
