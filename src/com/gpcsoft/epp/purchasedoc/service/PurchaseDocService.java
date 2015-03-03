package com.gpcsoft.epp.purchasedoc.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.DataItem;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.PurchaseFileDTO;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;

public interface PurchaseDocService extends BaseGenericService<PurchaseDoc> {

	/** 
	 * Description :  根据文件Id获取文件
	 * Create Date: 2010-9-7下午04:58:24 by yangx  Modified Date: 2010-9-7下午04:58:24 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurchaseDoc getPurchaseDocByObjId(String objId);

	/** 
	 * Description :  根据项目Id、招标文件Id获取未录入购买记录的且报名的供应商
	 * Create Date: 2010-10-28下午02:17:47 by yangx  Modified Date: 2010-10-28下午02:17:47 by yangx
	 * @param   projectId：项目Id,purchaseDocId：招标文件Id
	 * @return  List<SignUprecord>
	 * @Exception   
	 */
	public List<SignUprecord> getSupplierByProjectIdAndPurchaseDocId(String projectId,String purchaseDocId);
	
	/**
	 * 
	 * Description : 根据项目主键得到采购文件 
	 * Create Date: 2010-5-19下午03:48:57 by liuke  Modified Date: 2010-5-19下午03:48:57 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc getPurchaseDocByProjectId(String projectId);		
	
	
    /**
     * 
     * Description : 根据项目获得询价文件 
     * Create Date: 2010-9-8上午10:51:39 by liuke  Modified Date: 2010-9-8上午10:51:39 by liuke
     * @param   
     * @return  
     * @Exception
     */
	public PurchaseDoc getInqpDocByProjectId(String projectId);		
	
	
	/**
	 * 
	 * Description :根据主键获得询价文件  
	 * Create Date: 2010-9-8上午11:10:35 by liuke  Modified Date: 2010-9-8上午11:10:35 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc getInqpDocByObjId(String objId);
	
	
	/** 
	 * Description :  保存采购文件
	 * Create Date: 2010-9-6下午02:33:40 by yangx  Modified Date: 2010-9-6下午02:33:40 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurchaseDoc savePurchaseDoc(PurchaseDoc purchaseDoc);
	
	/** 
	 * Description :  保存澄清文件
	 * Create Date: 2010-9-6下午02:33:40 by yangx  Modified Date: 2010-9-6下午02:33:40 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurchaseDoc saveClarificationDoc(PurchaseDoc purchaseDoc);
	
	/** 
	 * Description :  修改：保存澄清文件
	 * Create Date: 2010-9-6下午02:33:40 by yangx  Modified Date: 2010-9-6下午02:33:40 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurchaseDoc updateSaveClarificationDoc(PurchaseDoc purchaseDoc);
	
	/** 
	 * Description :  提交澄清文件
	 * Create Date: 2010-9-6下午02:33:40 by yangx  Modified Date: 2010-9-6下午02:33:40 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurchaseDoc saveSubmitClarificationDoc(PurchaseDoc purchaseDoc);
	
	/** 
	 * Description :  修改：提交澄清文件
	 * Create Date: 2010-9-6下午02:33:40 by yangx  Modified Date: 2010-9-6下午02:33:40 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurchaseDoc updateSubmitClarificationDoc(PurchaseDoc purchaseDoc);
	/** 
	 * Description :  修改采购文件
	 * Create Date: 2010-9-6下午02:33:40 by yangx  Modified Date: 2010-9-6下午02:33:40 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
//	public PurchaseDoc updatePurchaseDoc(PurchaseDoc purchaseDoc);
	
	/**
	 * 
	 * Description :[采购办]得到待审核采购文件列表 
	 * Create Date: 2010-6-21上午11:32:33 by liuke  Modified Date: 2010-6-21上午11:32:33 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<PurchaseDoc> getPurchaseDocByUser(Page<PurchaseDoc> page,
			QueryObject queryObject,User user,String fileType);
	
	/**
	 * 
	 * Description : [采购人]得到待确认采购文件列表 
	 * Create Date: 2010-7-6下午05:34:00 by liuke  Modified Date: 2010-7-6下午05:34:00 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page getPurchaseDocByBuyer(Page<PurchaseDoc> page,User user,String fileType);


	/** 
	 * Description :  根据查询条件统计对应的采购文件数量
	 * Create Date: 2010-7-7下午03:38:09 by Administrator  Modified Date: 2010-7-7下午03:38:09 by Administrator
	 * @param queryObject[buyerId:采购人ID;auditStatus:审核状态]
	 * @return
	 */
	public BigDecimal getPurDocTotalByQueryObject(QueryObject queryObject);
	
	
	/**
	 * 
	 * Description : 通过项目查找项目总金额
	 * Create Date: 2010-7-10下午12:31:09 by liuke  Modified Date: 2010-7-10下午12:31:09 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Double getTotalPriceByProject(String projectId);
	
	/**
	 * 
	 * Description :得到更多采购文件信息  
	 * Create Date: 2010-7-13上午10:42:20 by liuke  Modified Date: 2010-7-13上午10:42:20 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<PurchaseDoc> searchPurchaseDocsByQueryObject(Page<PurchaseDoc> page,QueryObject queryObject);
	
	
	
	/** 
	 * Description :  审核采购文件
	 * Create Date: 2010-9-6下午04:09:37 by yangx  Modified Date: 2010-9-6下午04:09:37 by yangx
	 * @param   auditor:审核人账号ID，客户端审核时session无法获取user--add by sunl,
	 * @return  
	 * @Exception   
	 */
	public PurchaseDoc auditPurchaseDoc(String ids,User auditor,String opinion,String auditStatus,String workFlowTaskId);
	
	/**
	 * 
	 * Description :采购办审核询价文件  支持批量
	 * Create Date: 2010-9-8下午04:16:55 by liuke  Modified Date: 2010-9-8下午04:16:55 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc auditInqpDoc(String ids,String opinion,String auditStatus,String workFlowTaskId);
	
	
	/**
	 * 
	 * Description :监察局审核询价文件 支持批量 
	 * Create Date: 2010-9-8下午05:56:34 by liuke  Modified Date: 2010-9-8下午05:56:34 by liuke
	 * @param   String ids 审核文件主键,String opinion 审核意见,String auditStatus 审核状态,String workFlowTaskId 工作流ID
	 * @return  PurchaseDoc 文件对象
	 * @Exception
	 */
	public PurchaseDoc auditForJcjInqpDoc(String ids,String opinion,String auditStatus,String workFlowTaskId);
	
	
	/** 
	 * Description :  审核澄清文件 支持批量
	 * Create Date: 2010-9-6下午04:09:37 by yangx  Modified Date: 2010-9-6下午04:09:37 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurchaseDoc auditClarificationDoc(String ids,String opinion,String auditStatus,String workFlowTaskId);

	/** 
	 * Description :  确认采购文件 支持批量确认
	 * Create Date: 2010-9-6下午04:09:37 by yangx  Modified Date: 2010-9-6下午04:09:37 by yangx
	 * @param   ids 采购文件id串，多个以逗号隔开
	 * @param   auditStatus：‘Y’通过；‘N’退回。
	 * @return  
	 * @Exception   
	 */
	public PurchaseDoc saveConfirmPurchaseDoc(String ids,String opinion,String auditStatus,String workFlowTaskId);
	
	/**
	 * 
	 * Description : 确认询价文件 支持批量
	 * Create Date: 2010-9-8下午03:37:12 by liuke  Modified Date: 2010-9-8下午03:37:12 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc saveConfirmInqpDoc(String ids,String opinion,String auditStatus,String workFlowTaskId);
	
	/** 
	 * Description :  确认澄清文件 支持批量
	 * Create Date: 2010-9-6下午04:09:37 by yangx  Modified Date: 2010-9-6下午04:09:37 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurchaseDoc saveConfirmClarification(String ids,String opinion,String auditStatus,String workFlowTaskId);
	
	/**
	 * 
	 * Description :根据项目Id和文件类型获取文件
	 * Create Date: 2010-9-2下午04:21:49 by shenjianzhuang  Modified Date: 2010-9-2下午04:21:49 by shenjianzhuang
	 * @param projectId
	 * @param fileType
	 * @return
	 * @return  PurchaseDoc
	 * @Exception 
	 */	 
	public PurchaseDoc getPurchaseDocByProjectIdAndFileType(String projectId,String fileType);


	/** 
	 * Description :  监察局审核 支持批量
	 * Create Date: 2010-9-3下午02:01:28 by liuy  Modified Date: 2010-9-3下午02:01:28 by liuy
	 * @param purchaseDocAudit
	 * @return
	 * @throws Exception
	 */
	public PurchaseDoc saveForJcjAudit(String ids,String opinion,String auditStatus,String workFlowTaskId) throws Exception;
	
	/** 
	 * Description :  监察局审核澄清文件 支持批量
	 * Create Date: 2010-9-7上午10:40:23 by yangx  Modified Date: 2010-9-7上午10:40:23 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurchaseDoc saveClarificationForJcjAudit(String ids,String opinion,String auditStatus,String workFlowTaskId) throws Exception;


	/** 
	 * Description :  招标文件论证结论
	 * Create Date: 2010-9-3下午08:09:58 by liuy  Modified Date: 2010-9-3下午08:09:58 by liuy
	 * @param purchaseDocs
	 * @return
	 */
	public PurchaseDoc saveProofOpinion(PurchaseDoc purchaseDocs);
	
	
	/**
	 * 
	 * Description :保存询价文件  
	 * Create Date: 2010-9-8上午11:23:31 by liuke  Modified Date: 2010-9-8上午11:23:31 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc saveInqpDoc(PurchaseDoc purchaseDoc);
	
	
	
	
	/**
	 * 
	 * Description :修改询价文件  
	 * Create Date: 2010-9-8上午11:23:31 by liuke  Modified Date: 2010-9-8上午11:23:31 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc updateInqpDoc(PurchaseDoc purchaseDoc);
	
	/**
	 * FuncName: getPurchaseFileDTOByProjCode
	 * Description :根据项目Code获得PurchaseFileDTO对象
	 * @param 
	 * @param projCode
	 * @return PurchaseFileDTO
	 * @author: liuke
	 * @Create Date:2011-5-9  下午03:37:43
	 * @Modifier: liuke
	 * @Modified Date:2011-5-9  下午03:37:43
	 */
	public PurchaseFileDTO saveAndGetPurchaseFileDTOByProjCode(String projCode)throws Exception; 
	
	/**
	 * FuncName: saveUpdatePurchaseFile
	 * Description :用于UE接口中远程保存采购文件
	 * @param projId 项目主键
	 * @param purchaseFileObjId 采购文件附件主键
	 * @param projectDataItemList 待更新的项目数据
	 * @param congruousFactorList 待更新的指标数据
	 * @author: zhouzhanghe
	 * @Create Date:2011-7-5 9:27
	 */
	public void saveUpdatePurchaseFile(String projId, String purchaseFileObjId, List<DataItem> projectDataItemList, List<Map> congruousFactorList) throws Exception;
	/**
	 * FuncName: getAttachment
	 * Description :  根据项目编号获取招标文件附件对象
	 * @param 
	 * @return Attachment
	 * @author: shenjz
	 * @Create Date:2011-8-18  下午02:55:44
	 * @Modifier: shenjz
	 * @Modified Date:2011-8-18  下午02:55:44
	 */
	public Attachment getPurchaseDocAttachment(String projCode)throws Exception;

	
	/** 
	 * FuncName : removeFile
	 * Description :  删除招标文件
	 * Create Date: 2011-9-21上午11:29:07 by yangx  Modified Date: 2011-9-21上午11:29:07 by yangx
	 * @param   fileId：文件Id
	 * @return  
	 * @Exception   
	 */
	public void removePurchaseDocFile(String fileId,String attaPat)throws Exception;
	
	/**
	 * 根据采购文件id返回文件物理地址和文件名
	 * @param returnFilePath 文件物理地址
	 * @param returnFileViewName 用于显示的文件名
	 * @param paramPurchaseDocId 采购文件主键
	 * @author zhouzhanghe
	 * @created date 2011-11-18 14:21
	 */
	public void getPurchaseDocFilePathAndNameByPurchaseDocId(StringBuilder returnFilePath, StringBuilder returnFileViewName, String paramPurchaseDocId);
}
