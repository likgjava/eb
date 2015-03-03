package com.gpcsoft.epp.projreview.service;
import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.domain.EvaSellerRecord;
import com.gpcsoft.epp.projreview.domain.EvalFactorResult;

public interface EvaSellerRecordService extends BaseGenericService<EvaSellerRecord> {

	 /**
	  * FuncName:getEvaSellerRecordListByProjectId
	  * Description :根据项目主键得到当前人录入的评审记录  
	  * @param   subProjectId:包组主键
	  * @return  List<EvaSellerRecord>
	  * @author 	liuke
	  * @Create Date: 2010-6-4下午01:32:35 
	  * @Modifier    liuke
	  * @Modified Date: 2010-6-4下午01:32:35 
	  */
	 public List<EvaSellerRecord> getEvaSellerRecordListByProjectId(String projectId);
	 
	 /**
	  * FuncName:saveAllEvaSellerRecord
	  * Description :   修改或更新评标供应商表 
	  * @param   evaSellerRecord:开标记录,projectId:项目主键
	  * @return  EvaSellerRecord
	  * @author 	liuke
	  * @Create Date: 2010-6-4下午05:15:46 
	  * @Modifier   liuke
	  * @Modified Date: 2010-6-4下午05:15:46 
	  */
	 public EvaSellerRecord saveAllEvaSellerRecord(EvaSellerRecord evaSellerRecord,String projectId);
	 
	 /**
	  * FuncName:updateAllEvaSellerRecord
	  * Description :修改或更新评标供应商表 
	  * @param   evaSellerRecord:开标记录,projectId:项目主键
	  * @return EvaSellerRecord
	  * @author liuke  
	  * @Create Date: 2010-6-4下午05:15:46 
	  * @Modifier liuke 
	  * @Modified Date: 2010-6-4下午05:15:46
	  */
	 public EvaSellerRecord updateAllEvaSellerRecord(EvaSellerRecord evaSellerRecord,String projectId);
	 
	 /**
	  * FuncName:getOrgInfobyObjId
	  * Description :根据主键得到机构信息  
	  * @param   objId:机构主键
	  * @return  OrgInfo
	  * @author lic
	  * @Create Date: 2010-6-20下午02:20:41
	  * @Modifier  lic 
	  * @Modified Date: 2010-6-20下午02:20:41 
	  */
	 public OrgInfo getOrgInfobyObjId(String objId);
	 
	 /** 
	  * FuncName:getSubProjectByProjectId
	  * Description :根据项目主键获取对应的所有包组信息
	  * @param projectId:项目主键
	  * @return  List<Project>
	  * @author Administrator
	  * @Create Date: 2010-5-21下午02:25:13 
	  * @Modifier   Administrator
	  * @Modified Date: 2010-5-21下午02:25:13  
	  */
	public List<Project> getSubProjectByProjectId(String projectId)throws Exception;
	 
	/**
	  * FuncName:getAllAuditMessage
	  * Description : 根据包组Id和供应商Id得到评标结果列表
	  * @param   supplierId:供应商主键,subProjId:包组主键
	  * @return  List<EvaSellerRecord>
	  * @author liuke
	  * @Create Date: 2010-6-23下午04:14:42 
	  * @Modifier   liuke
	  * @Modified Date: 2010-6-23下午04:14:42  
	  */
	public List<EvaSellerRecord> getAllAuditMessage(String supplierId,String subProjId)throws Exception;
	
	/** 
	 * FuncName:saveBidEvaluationReport
	 * Description :  保存评审报告
	 * @param bulletin:评审报告
	 * @return Bulletin:评审报告
	 * @author Administrator
	 * @Create Date: 2010-6-5上午11:30:42 
	 * @Modifier Administrator
     * @Modified Date: 2010-6-5上午11:30:42
	 */
	public Bulletin saveBidEvaluationReport(Bulletin bulletin);
	
	/**
	 * FuncName:getbidEvaluationReportByProjectId
	 * Description:根据项目主键得到评标报告信息
	 * @param   subProjId:包组主键
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-6-25下午02:58:31 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-25下午02:58:31 
	 */
	public Bulletin getbidEvaluationReportByProjectId(String subProjId);
	
	/**
	 * FuncName:getEvaSellerRecordListForEval
	 * Description :得到评审对象  
	 * @param   subProjId:包组主键,supplierId:供应商主键
	 * @return  List<EvaSellerRecord>
	 * @author liuke
	 * @Create Date: 2010-8-30下午07:36:34
	 * @Modifier  liuke
	 * @Modified Date: 2010-8-30下午07:36:34  
	 */
	public List<EvaSellerRecord> getEvaSellerRecordListForEval(String subProjId,String supplierId);
	
	/** 
	 * FuncName:saveSubmitBidEvaluationReport
	 * Description :提交评审报告
	 * @param bulletin
	 * @return Bulletin
	 * @author Administrator
	 * @Create Date: 2010-6-5上午11:30:42   
	 * @Modifier Administrator
	 * @Modified Date: 2010-6-5上午11:30:42 
	 */
	public Bulletin saveSubmitBidEvaluationReport(Bulletin bulletin);

	/**
	 * FuncName:getEvaSellerRecordList
	 * Description : 根据项目主键评标结果
	 * @param subProjId:包组主键
	 * @return List<EvaSellerRecord>
	 * @author liuke
	 * @Create Date: 2010-6-26下午01:30:15 
	 * @Modifier liuke
	 * @Modified Date: 2010-6-26下午01:30:15  
	 */
	public List<EvaSellerRecord> getEvaSellerRecordList(String subProjId);
	
	/**
	 * FuncName:saveCreateEvaSellerRecord
	 * Description: 创建评审记录与批量保存评审打分
	 * @param evaSellerRecord:评审记录,evalFactorResultList:评审打分,packId:包组主键
	 * @return EvaSellerRecord
	 * @author wanghz
	 * @Create Date 2010-10-9 下午06:00:42 
	 */
	public EvaSellerRecord saveCreateEvaSellerRecord(EvaSellerRecord evaSellerRecord,List<EvalFactorResult> evalFactorResultList,String packId);
	
	/**
	 * FuncName:updateEvaSellerRecord
	 * Description: 更新评审记录与批量保存评审打分
	 * @param evaSellerRecord 评审记录,evalFactorResultList:评审打分,packId:包件ID
	 * @return EvaSellerRecord
	 * @author wanghz
	 * @Create Date 2010-10-9 下午06:00:42  
	 */
	public EvaSellerRecord updateEvaSellerRecord(EvaSellerRecord evaSellerRecord,List<EvalFactorResult> evalFactorResultList,String packId);
	
	/** 
	 * FuncName : finishEvaSellerRecord
	 * Description :  完成评审打分
	 * Create Date: 2011-12-20 上午10:55:33 by caojz  
	 * Modified Date: 2011-12-20 上午10:55:33 by caojz
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public EvaSellerRecord finishEvaSellerRecord(String projectId);
}
