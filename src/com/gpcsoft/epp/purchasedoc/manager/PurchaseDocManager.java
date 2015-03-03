package com.gpcsoft.epp.purchasedoc.manager;

import java.math.BigDecimal;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.PurchaseFileDTO;

public interface PurchaseDocManager extends BaseManager<PurchaseDoc> {

	
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
	 * Description :采购文件评审  
	 * Create Date: 2010-5-29上午11:45:07 by liuke  Modified Date: 2010-5-29上午11:45:07 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void PurchaseDocReview(PurchaseDoc purchaseDoc);
	
	
	/**
	 * 
	 * Description : 采购人确认采购文件 
	 * Create Date: 2010-5-29下午12:05:52 by liuke  Modified Date: 2010-5-29下午12:05:52 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void purchaseDocConfig(PurchaseDoc purchaseDoc);
	
	
	
	
	/**
	 * 
	 * Description : 采购文件发布
	 * Create Date: 2010-5-29下午12:15:11 by liuke  Modified Date: 2010-5-29下午12:15:11 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void purchaseDocRelease(PurchaseDoc purchaseDoc);
	
	
	/**
	 * 
	 * Description :  根据项目主键和文件状态取得采购文件
	 * Create Date: 2010-6-19下午12:01:01 by liuke  Modified Date: 2010-6-19下午12:01:01 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public  PurchaseDoc getPurchaseDocbyProjectIdAndStatus(String projectId,String status);
	
	/**
	 * 
	 * Description :保存或修改采购文件  
	 * Create Date: 2010-6-19下午12:46:49 by liuke  Modified Date: 2010-6-19下午12:46:49 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveOrUpdatePurchaseDoc(PurchaseDoc purchaseDoc);


	/** 
	 * Description :  根据查询条件统计对应的采购文件数量
	 * Create Date: 2010-7-7下午03:38:09 by Administrator  Modified Date: 2010-7-7下午03:38:09 by Administrator
	 * @param queryObject[buyerId:采购人ID;auditStatus:审核状态]
	 * @return
	 */
	public BigDecimal getPurDocTotalByQueryObject(QueryObject queryObject);
	
	
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
	 * 
	 * Description :  采购文件论证信息对项目的影响
	 * Create Date: 2010-8-13下午01:56:24 by shenjianzhuang  Modified Date: 2010-8-13下午01:56:24 by shenjianzhuang
	 * @return  void
	 * @Exception 
	 */ 
	public boolean checkProofOpinion(String objId);
	
	
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
	 * FuncName: createNewPurchaseFile
	 * Description :  将word文档拷到临时文件夹
	 * @param tempPath 临时文件夹路径
	 * @param filePath 采购文件（DOC）所在路径
	 * @param  fileName 采购文件（DOC）保存名称
	 * @param viewName  采购文件文件名称
	 * @author: liuke
	 * @Create Date:2011-5-9  下午04:20:59
	 * @Modifier: liuke
	 * @Modified Date:2011-5-9  下午04:20:59
	 */
	public String createNewPurchaseFile(String tempPath,String filePath,String fileName, String viewName)throws Exception;
	
	/**
	 * FuncName: createZipFile
	 * Description : 创建压缩下载附件方法
	 * @param purDocFilePath 采购文件ZIP位置
	 * @param inputFile 采购文件临时位置
	 * @param fileName 采购文件存放名称
	 * @param viewName 采购文件显示名称 
	 * @return String
	 * @author: liuke
	 * @throws Exception 
	 * @Create Date:2011-5-10  上午10:52:22
	 * @Modifier: liuke
	 * @Modified Date:2011-5-10  上午10:52:22
	 */
	public String  createZipFile(String purDocFilePath,String inputFile,String fileName, String viewName)throws Exception;
	
}
