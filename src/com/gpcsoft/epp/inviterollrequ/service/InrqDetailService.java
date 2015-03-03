package com.gpcsoft.epp.inviterollrequ.service;
import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.inviterollrequ.domain.InrqDetail;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;

public interface InrqDetailService extends BaseGenericService<InrqDetail> {

	/**
	 * 
	 * Description :得到供应商邀请函数量  
	 * Create Date: 2010-8-26下午03:44:48 by liuke  Modified Date: 2010-8-26下午03:44:48 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getInrqDetailNumByQueryObject(QueryObject queryObject); 
	
	/**
	 * 
	 * Description :得到单一来源邀请函列表 
	 * Create Date: 2010-8-26下午05:15:37 by liuke  Modified Date: 2010-8-26下午05:15:37 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public Page<Inviterollrequ> getInrqDetailByQueryObject(QueryObject queryObject,Page<InrqDetail> page);
	
	/** 
	 * Description :  根据项目Id获取邀请函内容 
	 * Create Date: 2010-8-28下午03:16:15 by yangx  Modified Date: 2010-8-28下午03:16:15 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public InrqDetail getInrqDetailContentByProjectId(String projectId);
	
	/** 
	 * Description : 根据项目Id获取录入的供应商 
	 * Create Date: 2010-8-27下午05:51:23 by yangx  Modified Date: 2010-8-27下午05:51:23 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<InrqDetail> getInrqDetailByProjectId(String projectId);
	
	/** 
	 * Description :  根据邀请函objId查询邀请函
	 * Create Date: 2010-8-28下午02:59:47 by yangx  Modified Date: 2010-8-28下午02:59:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public InrqDetail getInrqDetailByObjId(String objId);
	
	/**
	 * Description :  修改邀请函对象
	 * Create Date: 2010-10-15下午04:01:03 by shenjianzhuang  Modified Date: 2010-10-15下午04:01:03 by shenjianzhuang
	 * @param objId
	 * @return
	 * @return  InrqDetail
	 * @Exception		 
	 */	  
	public InrqDetail updateInrqDetail(String objId,String contents);
}
