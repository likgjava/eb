package com.gpcsoft.epp.bid.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;

public interface BailRecordService extends BaseGenericService<BailRecord> {
	 
	/** 
	 * Description :  支付完保证金后，保存一条供应商保证金支付记录
	 * 				  在阳光易购支付页面支付完成后调用此业务方法
	 * Create Date: 2011-7-21下午02:38:25 by sunl  Modified Date: 2011-7-21下午02:38:25 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveBailRecordAfterPay(String projId,String empId,String payStatus) throws Exception ;
		
	  /**
		 * 
		 * Description :  根据时间范围查询保证金记录表
		 * Create Date: 2010-8-5下午02:58:38 by shenjianzhuang  Modified Date: 2010-8-5下午02:58:38 by shenjianzhuang
		 * @param page
		 * @param renderTime
		 * @param renderTime2
		 * @param returnedTime
		 * @param returnedTime2
		 * @return
		 * @return  Page<BailRecord>
		 * @Exception 
		 */
	public Page<BailRecord> getSelectedBailRecord(Page page, String renderTime,String renderTime2, String returnedTime,String returnedTime2);
	
	
	/**
	 * 
	 * Description : 根据项目查询相应的保证金记录
	 * Create Date: 2010-9-1下午03:18:46 by shenjianzhuang  Modified Date: 2010-9-1下午03:18:46 by shenjianzhuang
	 * @param signUprecordId
	 * @return
	 * @return  BailRecord
	 * @Exception 
	 */
	public List<BailRecord> getBailRecordByProjectId(String projectId);

	
	/** 
	 * FuncName : getBailRecordByProjectAndSupplierId
	 * Description :  根据项目Id和供应商Id获取保证金记录
	 * Create Date: 2011-9-27下午06:18:42 by yangx  
	 * Modified Date: 2011-9-27下午06:18:42 by yangx
	 * @param   projectId：项目Id,supplierId：项目Id
	 * @return  List<BailRecord>
	 * @Exception   
	 */
	public List<BailRecord> getBailRecordByProjectAndSupplierId(String projectId,String supplierId);
	
	/**
	 * 
	 * Description :  保存保证金对象
	 * Create Date: 2010-9-7上午09:57:16 by shenjianzhuang  Modified Date: 2010-9-7上午09:57:16 by shenjianzhuang
	 * @param bailRecord
	 * @return
	 * @return  BailRecord
	 * @Exception 
	 */	 
	public BailRecord saveBailRecord(BailRecord bailRecord);
	
	
	/**
  	 * Description :  根据主键获取保证金对象
	 * Create Date: 2010-9-7下午05:11:55 by shenjianzhuang  Modified Date: 2010-9-7下午05:11:55 by shenjianzhuang
	 * @param objId
	 * @return
	 * @return  BailRecord
	 * @Exception 
	 */			  
	public BailRecord getBailRecord(String objId);

	 /**
	 * FuncName: getBailRecordBySignUprecord
	 * Description :  创建或修改对象
	 * @param 
	 * @param signUprecordId
	 * @return BailRecord
	 * @author: shenjz
	 * @Create Date:2011-4-7  下午05:40:47
	 * @Modifier: shenjz
	 * @Modified Date:2011-4-7  下午05:40:47
	*/		 
	public BailRecord getBailRecordBySignUprecord(String signUprecordId);
	/**
	 * FuncName: idCanEntryBailRecord
	 * Description :  是否可以录入保证金
	 * @param 
	 * @param obj
	 * @return boolean
	 * @author: liuke
	 * @Create Date:2011-8-10  上午09:55:18
	 * @Modifier: liuke
	 * @Modified Date:2011-8-10  上午09:55:18
	 */
	public boolean isCanEntryBailRecord(Object[] obj);
	
	
	/** 
	 * FuncName : removeBailRecordByIds
	 * Description :  根据保证金Ids删除保证金记录
	 * Create Date: 2011-9-27下午04:36:18 by yangx  
	 * Modified Date: 2011-9-27下午04:36:18 by yangx
	 * @param   bailRecordIds：保证金Ids
	 * @return  
	 * @Exception   
	 */
	public void removeBailRecordByIds(String bailRecordIds);
	
	
	/** 
	 * FuncName : saveConfirmBailRecord
	 * Description :  保存确认保证金记录
	 * Create Date: 2011-9-27下午05:09:51 by yangx  
	 * Modified Date: 2011-9-27下午05:09:51 by yangx
	 * @param   bailRecord：保证金记录
	 * @return  BailRecord
	 * @Exception   
	 */
	public BailRecord saveConfirmBailRecord(BailRecord bailRecord);

	
	/** 
	 * FuncName : finshConfirmBailRecord
	 * Description :  完成补录保证金功能
	 * Create Date: 2011-11-18上午11:58:37 by yangx  
	 * Modified Date: 2011-11-18上午11:58:37 by yangx
	 * @param   projectId：项目ID
	 * @return  BailRecord
	 * @Exception   
	 */
	public BailRecord finishConfirmBailRecord(String projectId);

}
