package com.gpcsoft.epp.purchasedoc.dao;

import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.srplatform.auth.domain.User;

public interface PurchaseDocDao extends BaseGenericDao<PurchaseDoc> {

	
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
	 * Description :  根据项目Id、招标文件Id获取未录入购买记录的且报名的供应商
	 * Create Date: 2010-10-28下午02:17:47 by yangx  Modified Date: 2010-10-28下午02:17:47 by yangx
	 * @param   projectId：项目Id,purchaseDocId：招标文件Id
	 * @return  List<SignUprecord>
	 * @Exception   
	 */
	public List<SignUprecord> getSupplierByProjectIdAndPurchaseDocId(String projectId,String purchaseDocId);
	
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
	 * Description :新增采购文件  
	 * Create Date: 2010-6-19下午12:55:07 by liuke  Modified Date: 2010-6-19下午12:55:07 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void savePurchaseDoc(PurchaseDoc purchaseDoc);
	
	
	/**
	 * 
	 * Description :  修改采购文件
	 * Create Date: 2010-6-19下午12:56:04 by liuke  Modified Date: 2010-6-19下午12:56:04 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void updatePurchaseDoc(PurchaseDoc purchaseDoc);

	
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
	 * 
	 * Description : [采购人]根据申报书明细，得到采购人的待确认采购文件列表 []
	 * Create Date: 2010-7-6下午05:34:00 by liuke  Modified Date: 2010-7-6下午05:34:00 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page getPurchaseDocByBuyer(Page<PurchaseDoc> page, User user,
			String fileType);
	
	/**
	 * FuncName: getPurchaseDocByProjCode
	 * Description :  创建或修改对象
	 * @param 
	 * @param projCode
	 * @return PurchaseDoc
	 * @author: liuke
	 * @Create Date:2011-5-9  下午03:54:24
	 * @Modifier: liuke
	 * @Modified Date:2011-5-9  下午03:54:24
	 */
	public PurchaseDoc getPurchaseDocByProjCode(String projCode);
}
