package com.gpcsoft.epp.inviterollrequ.service;
import java.math.BigDecimal;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;

public interface InviterollrequService extends BaseGenericService<Inviterollrequ> {
	/**
	 * 
	 * Description :保存邀请函 
	 * Create Date: 2010-8-17下午03:16:44 by liuke  Modified Date: 2010-8-17下午03:16:44 by liuke
	 * @param   String[] supplier 供应商数组  String projectId 项目主键
	 * @return  
	 * @Exception
	 */
	public Inviterollrequ saveInviterollrequ(String[] supplier,String projectId) throws Exception;
	
	/**
	 * 
	 * Description :删除邀请函  
	 * Create Date: 2010-8-27下午04:56:59 by liuke  Modified Date: 2010-8-27下午04:56:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeInviterollrequ(String projectId);
	
	/**
	 * 
	 * Description :审核邀请函  
	 * Create Date: 2010-8-27下午04:56:59 by liuke  Modified Date: 2010-8-27下午04:56:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Inviterollrequ audit(String objId,String status,String opinion);
		
	/**
	 * Description :  提交邀请函
	 * Create Date: 2010-10-17上午10:10:45 by shenjianzhuang  Modified Date: 2010-10-17上午10:10:45 by shenjianzhuang
	 * @param objId
	 * @return
	 * @return  Inviterollrequ
	 * @Exception	
	 */
	public Inviterollrequ updateInviterollrequ(String objId) ;
	
	/**
	 * 
	 * Description :根据QueryObject对象获得邀请函数量  
	 * Create Date: 2010-10-25下午02:03:23 by liuke  Modified Date: 2010-10-25下午02:03:23 by liuke
	 * @param   QueryObject queryObject
	 * @return  BigDecimal
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getInviterollrequTotalByQueryObject(QueryObject queryObject);
	
	/**
	 * 
	 * Description :根据QueryObject对象获得邀请函列表
	 * Create Date: 2010-10-25下午02:03:23 by liuke  Modified Date: 2010-10-25下午02:03:23 by liuke
	 * @param   QueryObject queryObject
	 * @return  Page<Inviterollrequ>
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public Page<Inviterollrequ> getInviterollrequByQueryObject(QueryObject queryObject,Page page);
}
