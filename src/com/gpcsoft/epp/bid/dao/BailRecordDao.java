package com.gpcsoft.epp.bid.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bid.domain.BailRecord;

public interface BailRecordDao extends BaseGenericDao<BailRecord> {
	
	/**
	 * Funcname:getSelectedBailRecord
	 * Description:根据时间范围查询相应的保证金记录表
	 * @Create Date: 2010-8-5下午02:58:38 
	 * @author shenjianzhuang  
	 * @Modified Date: 2010-8-5下午02:58:38 
	 * @author shenjianzhuang
	 * @param page
	 * @param renderTime
	 * @param renderTime2
	 * @param returnedTime
	 * @param returnedTime2
	 * @return  Page<BailRecord>
	 * @Exception 
	 */
	public Page<BailRecord> getSelectedBailRecord(Page page, String renderTime,String renderTime2, String returnedTime,String returnedTime2);
	
	/**
	 * FuncName:getBailRecordByProjectId
	 * Description : 根据项目查询相应的保证金记录
	 * @Create Date: 2010-9-1下午03:18:46    
	 * @author shenjianzhuang
	 * @Modified Date: 2010-9-1下午03:18:46 
	 * @author shenjianzhuang
	 * @param projectId:项目主键
	 * @return  List<BailRecord>:投标保证金集合
	 * @Exception 
	 */
	public List<BailRecord> getBailRecordByProjectId(String projectId);
	

	/**
	 * FuncName:getBailRecordBySignUprecord
	 * Description:创建或修改对象
	 * @param signUprecordId:供应商报名主键
	 * @return BailRecord：投标保证金对象
	 * @author: shenjz
	 * @Create Date:2011-4-7  下午05:40:47
	 * @Modifier: shenjz
	 * @Modified Date:2011-4-7  下午05:40:47
	 */			 
	public BailRecord getBailRecordBySignUprecord(String signUprecordId);
	
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
	
}
