package com.gpcsoft.epp.consign.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.consign.domain.Consign;

public interface ConsignDao extends BaseGenericDao<Consign> {
	
	/** 
	 * FuncName:submitConsign
	 * Description:提交委托书
	 * @param   objIds:以逗号分割的申报书主键,useStatus:用户使用状态,confirmStatus:确认状态
	 * @return  void
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:52:43 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-6下午05:52:43 
	 */
	public void submitConsign(String objIds,String useStatus,String confirmStatus);
	
	/** 
	 * FuncName:getConsignListByObject
	 * Description :  根据对象获取委托协议列表
	 * @param   page:分页对象,queryObject:数据组装对象 
	 * @return  Page<Consign>
	 * @author yangx
	 * @Create Date: 2010-7-13下午04:13:25 
	 * @Modifier  yangx
	 * @Modified Date: 2010-7-13下午04:13:25 
	 */
	@SuppressWarnings("unchecked")
	public Page<Consign> getConsignListByObject(Page<Consign> page,QueryObject queryObject);
	
	/**
	 * FuncName:getConsignListOrCountByQuery
	 * Description: 获取委托协议List 或 记录条数
	 * @param queryObject{count:记录条数,type:查询类型{data:数据,count记录条数},createUser:创建人ID,confirmStatus:确认状态,consBuyer:采购人}
	 * @return Object
	 * @author wanghz
	 * @Create Date 2010-9-2 下午02:36:16 
	 */
	public Object getConsignListOrCountByQuery(QueryObject queryObject)throws EsException;
}
