package com.gpcsoft.epp.purchasedoc.service;
import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyRecord;
import com.gpcsoft.srplatform.auth.domain.User;

public interface DosBuyRecordService extends BaseGenericService<DosBuyRecord> {

	
	
	/**
	 * 
	 * Description :通过供应商主键判断是否允许下载采购文件  
	 * Create Date: 2010-5-27下午04:52:06 by liuke  Modified Date: 2010-5-27下午04:52:06 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean IsBuyPurchaseDoc(String projectId, String subprojectId ,User user);
	
	/** 
	 * FuncName : saveDosBuyRecord
	 * Description :  保存标书费购买记录
	 * Create Date: 2011-9-22下午01:35:21 by yangx  
	 * Modified Date: 2011-9-22下午01:35:21 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public DosBuyRecord saveDosBuyRecord(DosBuyRecord dosBuyRecord,String projectIds) throws Exception ;
	
	/** 
	 * Description :  根据供应商Id获取OrgInfo
	 * Create Date: 2010-9-7下午05:08:56 by yangx  Modified Date: 2010-9-7下午05:08:56 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgInfo(String supplierId);

	/** 
	 * FuncName :   删除标书费购买记录
	 * Description :  removeDosBuyRecordByIds
	 * Create Date: 2011-9-22下午04:23:24 by yangx  
	 * Modified Date: 2011-9-22下午04:23:24 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeDosBuyRecordByIds(String[] objIds)throws EsException;
	
	/**
	 * 
	 * Description :根据主键获得询价文件购买信息  
	 * Create Date: 2010-9-14下午05:36:55 by liuke  Modified Date: 2010-9-14下午05:36:55 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public DosBuyRecord getDosBuyRecordByObjId(String objId);
	
	/** 
	 * Description :  根据项目id获取招标文件购买记录
	 * Create Date: 2010-10-19下午05:00:25 by wangcl  Modified Date: 2010-10-19下午05:00:25 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<DosBuyRecord> getByProjectId(String projectId);
	
	
	/** 
	 * FuncName : getDosBuyRecordBySupplierId
	 * Description :  根据供应商Id获取标书费购买记录
	 * Create Date: 2011-9-22下午11:38:18 by yangx  
	 * Modified Date: 2011-9-22下午11:38:18 by yangx
	 * @param   supplierId：供应商Id
	 * @return  DosBuyRecord
	 * @Exception   
	 */
	public List<DosBuyRecord> getDosBuyRecordBySupplierId(String supplierId,String projectId);
	
	
	/** 
	 * FuncName : saveConfirmDosBuyRecord
	 * Description :  保存标书费购买记录审核状态
	 * Create Date: 2011-9-26下午05:57:15 by yangx  
	 * Modified Date: 2011-9-26下午05:57:15 by yangx
	 * @return  DosBuyRecord
	 * @Exception   
	 */
	public DosBuyRecord saveConfirmDosBuyRecord(DosBuyRecord dosBuyRecord);

	/** 
	 * FuncName : finshConfirmDosBuyRecord
	 * Description :  完成标书确认
	 * Create Date: 2011-11-16下午01:38:07 by yangx  
	 * Modified Date: 2011-11-16下午01:38:07 by yangx
	 * @param   dosBuyRecordId：标书费对象Id
	 * @return  DosBuyRecord
	 */
	public DosBuyRecord finishConfirmDosBuyRecord(String projectId);
	
	/** 
	 * Description :  网银支付成功之后，修改采购文件的支付状态
	 * Create Date: 2012-1-6下午03:03:16 by likg  Modified Date: 2012-1-6下午03:03:16 by likg
	 * @param   projId:项目id(如果项目分包，则为包件id)		empId:员工id
	 * @return  
	 * @Exception   
	 */
	public Boolean updatePayStatus(String projId, String empId) throws Exception;
	
}
