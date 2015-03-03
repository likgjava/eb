package com.gpcsoft.smallscale.business.service;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.business.domain.BusinessMember;

public interface BusinessMemberService extends BaseGenericService<BusinessMember> {

	/** 
	 * Description :  根据companyId，获得最新的orgInfo。
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getBusinessMemberList(String orgInfoId) throws Exception;
	
	
	/** 
	 * Description :  商圈会员角色定时任务(如果商圈会员到期，自动删除角色)
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void update_businessmember_orgrole() throws Exception;
	
	/** 
	 * Description :  获得商圈会员的展示信息
	 * Create Date: 2011-2-18上午09:06:24 by liangxj  Modified Date: 2011-2-18上午09:06:24 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含投标信息、社区信息、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<BusinessMember> getBusinessMemberListForShow(Page<BusinessMember> page,Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  根据条件获取商圈会员的数量
	 * Create Date: 2011-3-23上午11:46:26 by likg  Modified Date: 2011-3-23上午11:46:26 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Long getBusinessMemberNum(Map<String, Object> paramsMap) throws Exception;
}
