package com.gpcsoft.epp.inviterollrequ.dao;

import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.inviterollrequ.domain.InrqDetail;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;

public interface InrqDetailDao extends BaseGenericDao<InrqDetail> {
	
	/**
	 * 
	 * Description :得到供应商邀请函数量  
	 * Create Date: 2010-8-26下午03:44:48 by liuke  Modified Date: 2010-8-26下午03:44:48 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public BigDecimal getInrqDetailNumByQueryObject(QueryObject queryObject);
	
	/**
	 * 
	 * Description : 删除邀请函 
	 * Create Date: 2010-8-27下午05:02:03 by liuke  Modified Date: 2010-8-27下午05:02:03 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeinrqDetailList(String InviterollrequId);
	
	
	/** 
	 * Description :  得到单一来源邀请函列表 
	 * Create Date: 2010-8-28下午02:08:52 by yangx  Modified Date: 2010-8-28下午02:08:52 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Inviterollrequ> getInrqDetailByQueryObject(QueryObject queryObject,Page<InrqDetail> page);
	
	/**
	 * 
	 * Description : 根据邀请名单申请单得到邀请函对象列表   
	 * Create Date: 2010-8-27下午01:25:38 by liuke  Modified Date: 2010-8-27下午01:25:38 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<InrqDetail> getInrqDetailByInviterollrequId(String inviterollrequId);
	/** 
	 * Description : 根据项目Id获取录入的供应商 
	 * Create Date: 2010-8-27下午05:51:23 by yangx  Modified Date: 2010-8-27下午05:51:23 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<InrqDetail> getInrqDetailByProjectId(String projectId);
	
	/** 
	 * Description :  根据项目Id与供应商Id获取邀请函明细
	 * Create Date: 2010-8-28上午11:55:16 by yangx  Modified Date: 2010-8-28上午11:55:16 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public InrqDetail getInrqDetailByProjectIdAndSupplierId(String projectId);
}
