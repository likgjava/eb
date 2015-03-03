package com.gpcsoft.epp.purchasedoc.manager.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.purchasedoc.dao.PurchaseDocDao;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.manager.PurchaseDocManager;
import com.gpcsoft.epp.webService.common.util.ZipUtils;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.PurchaseFileDTO;
import com.gpcsoft.srplatform.auth.dao.AttachmentDao;
import com.gpcsoft.srplatform.auth.domain.Attachment;

@Repository
public class PurchaseDocManagerImpl extends BaseManagerImpl<PurchaseDoc> implements PurchaseDocManager {

	@Autowired(required=true)  @Qualifier("purchaseDocDaoHibernate")
	private PurchaseDocDao purchaseDocDaoHibernate;

	@Autowired(required=false)@Qualifier("attachmentDaoHibernate")
	private AttachmentDao attachmentDaoHibernate;
		
	/**
	 * 
	 * Description : 根据项目主键得到采购文件 
	 * Create Date: 2010-5-19下午03:48:57 by liuke  Modified Date: 2010-5-19下午03:48:57 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc getPurchaseDocByProjectId(String projectId) {
		return purchaseDocDaoHibernate.getPurchaseDocByProjectId(projectId);
	}
     
	
	/**
	 * 
	 * Description :采购文件评审  
	 * Create Date: 2010-5-29上午11:45:07 by liuke  Modified Date: 2010-5-29上午11:45:07 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void PurchaseDocReview(PurchaseDoc purchaseDoc) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * Description : 采购人确认采购文件 
	 * Create Date: 2010-5-29下午12:05:52 by liuke  Modified Date: 2010-5-29下午12:05:52 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void purchaseDocConfig(PurchaseDoc purchaseDoc) {
		purchaseDocDaoHibernate.updatePurchaseDoc(purchaseDoc);
		
	}

	/**
	 * 
	 * Description : 采购文件发布 
	 * Create Date: 2010-5-29下午12:05:52 by liuke  Modified Date: 2010-5-29下午12:05:52 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void purchaseDocRelease(PurchaseDoc purchaseDoc) {
		// TODO Auto-generated method stub
		
	}

     
	public PurchaseDoc getPurchaseDocbyProjectIdAndStatus(String projectId,
			String status) {
		return purchaseDocDaoHibernate.getPurchaseDocbyProjectIdAndStatus(projectId, status);
	}

	/**
	 * 
	 * Description :保存或修改采购文件  
	 * Create Date: 2010-6-19下午12:46:49 by liuke  Modified Date: 2010-6-19下午12:46:49 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveOrUpdatePurchaseDoc(PurchaseDoc purchaseDoc) {
		purchaseDocDaoHibernate.save(purchaseDoc);
	}


	/** 
	 * Description :  根据查询条件统计对应的采购文件数量
	 * Create Date: 2010-7-7下午03:38:09 by Administrator  Modified Date: 2010-7-7下午03:38:09 by Administrator
	 * @param queryObject[buyerId:采购人ID;auditStatus:审核状态]
	 * @return
	 */
	public BigDecimal getPurDocTotalByQueryObject(QueryObject queryObject) {
		return purchaseDocDaoHibernate.getPurDocTotalByQueryObject(queryObject);
	}

    
	/**
	 * 
	 * Description :得到更多采购文件信息  
	 * Create Date: 2010-7-13上午10:42:20 by liuke  Modified Date: 2010-7-13上午10:42:20 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<PurchaseDoc> searchPurchaseDocsByQueryObject(
			Page<PurchaseDoc> page, QueryObject queryObject) {
		return purchaseDocDaoHibernate.searchPurchaseDocsByQueryObject(page, queryObject);
	}
	
	
	
	/**
	 * 
	 * Description :  采购文件论证信息对项目的影响
	 * Create Date: 2010-8-13下午01:56:24 by shenjianzhuang  Modified Date: 2010-8-13下午01:56:24 by shenjianzhuang
	 * @return  void
	 * @Exception 
	 */ 
	public boolean checkProofOpinion(String objId) {
		StringBuffer exception = new StringBuffer();
		Boolean checkValue = true;//判断是否有不满足条件的数据
		PurchaseDoc puechaseDoc = purchaseDocDaoHibernate.get(objId);//获取采购文件
		String proofOpinion = puechaseDoc.getProofOpinion();//获取采购文件论证结论
		if(objId==null||"".equals(objId)){
			exception.append(messageSource.getMessage(EsExceptionEnum.DOC_IS_NONE));//文件不存在
		}else {
			
		}
		if(exception.length()>0)checkValue=false;//若有异常信息，表示有异常，需要把判断置为false;
		if (!checkValue) {
			 throw new EsException(exception.toString());//若有影响项目操作的数据，则抛出异常
		}
		return checkValue;
	}


	
	/**
	 * FuncName: getPurchaseFileDTOByProjCode
	 * Description :根据项目Code获得PurchaseFileDTO对象
	 * @param 
	 * @param projCode
	 * @return PurchaseFileDTO
	 * @author: liuke
	 * @throws Exception 
	 * @Create Date:2011-5-9  下午03:37:43
	 * @Modifier: liuke
	 * @Modified Date:2011-5-9  下午03:37:43
	 */
	public PurchaseFileDTO saveAndGetPurchaseFileDTOByProjCode(String projCode) throws Exception {
		PurchaseFileDTO purchaseDTO = new PurchaseFileDTO();
		try {
			PurchaseDoc doc =  purchaseDocDaoHibernate.getPurchaseDocByProjCode(projCode);
			if(doc==null){
				return purchaseDTO;
			}
			QueryObject<Attachment> query = new QueryObjectBase<Attachment>();
			query.setEntityClass(Attachment.class);
			query.getQueryParam().and(new QueryParam("relationId",QueryParam.OPERATOR_EQ,doc.getAttachRelaId()));
			List<Attachment> attachmentList = attachmentDaoHibernate.findByQuery(query);
			Attachment attachment = attachmentList.get(0);
			purchaseDTO.setFileName(attachment.getFileName()); //获得招标文件关联ID
			purchaseDTO.setViewName(attachment.getViewName());  //获得招标文件名称
			purchaseDTO.setFilePath(attachment.getPath());//获得招标文件路径
			purchaseDTO.setDownUrl("/DownloadFileHttpServlet?attachmentObjId="+attachment.getRelationId()+"&amp;fileType="+CommonEnum.FILE_TYPE_PURCHASE);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return purchaseDTO;
	}
	
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
	public String createNewPurchaseFile(String tempPath,String filePath,String fileName, String viewName)throws Exception{
		    boolean flag = FileUtil.fileExist(tempPath); //判断文件路径是否存在
		    if(!flag){
		    	FileUtil.mkdirs(tempPath);
			  }
			FileUtil.copy(filePath, tempPath+File.separator+fileName+viewName.substring(viewName.lastIndexOf(".")));   //复制招标文件到指定路径
		return tempPath;
	}
	

	
	/**
	 * FuncName: createZipFile
	 * Description : 创建压缩下载附件方法
	 * @param 
	 * @return String
	 * @author: liuke
	 * @throws Exception 
	 * @Create Date:2011-5-10  上午10:52:22
	 * @Modifier: liuke
	 * @Modified Date:2011-5-10  上午10:52:22
	 */
	public String  createZipFile(String purDocFilePath,String inputFile,String fileName, String viewName) throws Exception{
		String zipFileName = getMessageSource().getMessage("epp.projectAttaPath")+purDocFilePath;//压缩后的文件夹路径
		boolean flag = FileUtil.fileExist(zipFileName); //判断文件路径是否存在
	    if(!flag){
	    	FileUtil.mkdirs(zipFileName);  //不存在则创建文件
		  }  
	    ZipUtils.zip(new File(zipFileName+File.separator+fileName), inputFile.replace("/", File.separator));
		return purDocFilePath;
	}
	
	

}
