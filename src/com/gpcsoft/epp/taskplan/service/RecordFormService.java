package com.gpcsoft.epp.taskplan.service;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.taskplan.domain.RecordForm;

public interface RecordFormService extends BaseGenericService<RecordForm> {
	/**
	 * FuncName: saveRecordForm
	 * Description :  创建备案书对象
	 * @param 
	 * @param recordForm
	 * @return RecordForm
	 * @author: shenjz
	 * @Create Date:2011-5-23  上午10:55:59
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  上午10:55:59
	 */
	public RecordForm saveRecordForm(RecordForm recordForm);
	
	/** 
	 * FuncName : saveRecordFormAndProject
	 * Description :  保存招标投标登记表
	 * Create Date: 2011-12-21下午02:32:30 by yangx  
	 * Modified Date: 2011-12-21下午02:32:30 by yangx
	 * @param   recordForm：招标投标登记表
	 * @return  RecordForm
	 * @Exception   
	 */
	public RecordForm saveRecordFormAndProject(RecordForm recordForm);
	
	/**
	 * FuncName: getRecordFormByPrjId
	 * Description :  根据项目主键获取备案书对象
	 * @param 
	 * @param projectId
	 * @return RecordForm
	 * @author: shenjz
	 * @Create Date:2011-5-23  下午01:50:29
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  下午01:50:29
	 */
	public RecordForm getRecordFormByPrjId(String projectId);
	/**
	 * FuncName: saveRecordForm
	 * Description :  备案书确定底价
	 * @param 
	 * @param recordForm
	 * @return RecordForm
	 * @author: shenjz
	 * @Create Date:2011-5-23  上午10:55:59
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  上午10:55:59
	 */
	public RecordForm updateRecordForm(RecordForm recordForm);
	
	/**
	 * FuncName:  toTrackRecordFormList
	 * Description :  根据查询条件获得相关的备案书信息
	 * @param queryObject:组装的查询条件对象,page:分页对象
	 * @return Page<RecordForm>
	 * @author caojz
	 * @Create Date: 2011-8-30上午10:30:42   
	 * @Modifier caojz
	 * @Modified Date: 2011-8-30上午10:30:42 
	 */
	public Page<RecordForm> toTrackRecordFormList(QueryObject queryObject,
			Page<RecordForm> page);
	
	
	/** 
	 * FuncName : saveRecordFormForAudit
	 * Description :  审核招标投标登记表
	 * Create Date: 2011-12-22下午06:16:39 by yangx  
	 * Modified Date: 2011-12-22下午06:16:39 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public RecordForm saveRecordFormForAudit(RecordForm recordForm,String opinion);
}
