package com.gpcsoft.epp.inviterollrequ.dao;

import java.math.BigDecimal;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;

public interface InviterollrequDao extends BaseGenericDao<Inviterollrequ> {

	/**
	 * 
	 * Description :根据项目主键得到邀请函申请对象 
	 * Create Date: 2010-8-17下午03:22:03 by liuke  Modified Date: 2010-8-17下午03:22:03 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Inviterollrequ getInviterollrequByProjectId(String projectId,String auditStatus);

	
	/**
	 * 
	 * Description :根据QueryObject对象获得邀请函数量  
	 * Create Date: 2010-10-25下午02:03:23 by liuke  Modified Date: 2010-10-25下午02:03:23 by liuke
	 * @param   QueryObject queryObject
	 * @return  BigDecimal
	 * @Exception
	 */
	public BigDecimal getInviterollrequTotalByQueryObject(QueryObject queryObject);
	
	
	
	
	/**
	 * 
	 * Description :根据QueryObject对象获得邀请函列表
	 * Create Date: 2010-10-25下午02:03:23 by liuke  Modified Date: 2010-10-25下午02:03:23 by liuke
	 * @param   QueryObject queryObject
	 * @return  Page<Inviterollrequ>
	 * @Exception
	 */
	public Page<Inviterollrequ> getInviterollrequByQueryObject(
			QueryObject queryObject,Page page);
	
}
