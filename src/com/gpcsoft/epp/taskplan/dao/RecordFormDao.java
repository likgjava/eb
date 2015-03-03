package com.gpcsoft.epp.taskplan.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.taskplan.domain.RecordForm;

public interface RecordFormDao extends BaseGenericDao<RecordForm> {
	
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
}
