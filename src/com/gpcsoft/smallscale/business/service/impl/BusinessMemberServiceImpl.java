package com.gpcsoft.smallscale.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.business.dao.BusinessMemberDao;
import com.gpcsoft.smallscale.business.domain.BusinessMember;
import com.gpcsoft.smallscale.business.manager.BusinessMemberManager;
import com.gpcsoft.smallscale.business.service.BusinessMemberService;

@Service 
public class BusinessMemberServiceImpl extends BaseGenericServiceImpl<BusinessMember> implements BusinessMemberService {

	@Autowired(required=true) @Qualifier("businessMemberManagerImpl")
	private BusinessMemberManager businessMemberManager;
	
	@Autowired(required=true) @Qualifier("businessMemberDaoHibernate")
	private BusinessMemberDao businessMemberDaoHibernate;

	/** 
	 * Description :  根据companyId，获得最新的orgInfo。
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getBusinessMemberList(String orgInfoId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Integer validMemberCount = 0;//有效会员个数
		Integer historyMemberCount = 0;//历史会员个数
		Integer tempMemberCount = 0;//临时会员个数
		Integer noPassMemberCount = 0;//临时会员个数
		BusinessMember validMember = null;//有效会员
		BusinessMember noPassMember = null;//审核未通过会员
		
		//获得会员（包括历史会员）
		List<BusinessMember> businessMemberList = businessMemberManager.getBusinessMemberList(orgInfoId); 
		
		for (BusinessMember businessMember : businessMemberList) {
			//审核通过、有效、有效期
			if(OrganizationEnum.USE_VALID.equals(businessMember.getUseStatus())) {
				if(businessMember.getBegainDate().getTime()<=new Date().getTime() && businessMember.getEndDate().getTime()>=new Date().getTime()) {
					validMemberCount ++;
					validMember = businessMember;
				}else {//历史到期会员
					historyMemberCount ++;
				}
			}else if(OrganizationEnum.USE_TEMP.equals(businessMember.getUseStatus()) && OrganizationEnum.AWAIT_EXAM.equals(businessMember.getAuditStatus())){
				tempMemberCount ++;
			}else if(OrganizationEnum.USE_TEMP.equals(businessMember.getUseStatus()) && OrganizationEnum.NO_PASS_EXAM.equals(businessMember.getAuditStatus())){
				noPassMemberCount ++;
				noPassMember = businessMember;
			}
		}
		
		model.put("validMemberCount", validMemberCount);
		model.put("historyMemberCount", historyMemberCount);
		model.put("tempMemberCount", tempMemberCount);
		model.put("noPassMemberCount", noPassMemberCount);
		model.put("validMember", validMember);
		model.put("noPassMember", noPassMember);
		
		return model;
	}
	
	/** 
	 * Description :  商圈会员角色定时任务(如果商圈会员到期，自动删除角色)
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void update_businessmember_orgrole() throws Exception {
		businessMemberManager.update_businessmember_orgrole();
	}
	
	/** 
	 * Description :  获得商圈会员的展示信息
	 * Create Date: 2011-2-18上午09:06:24 by liangxj  Modified Date: 2011-2-18上午09:06:24 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含投标信息、社区信息、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<BusinessMember> getBusinessMemberListForShow(Page<BusinessMember> page,Map<String, Object> paramsMap) throws Exception {
		return businessMemberDaoHibernate.getBusinessMemberListForShow(page, paramsMap);
	}

	/** 
	 * Description :  根据条件获取商圈会员的数量
	 * Create Date: 2011-3-23上午11:46:26 by likg  Modified Date: 2011-3-23上午11:46:26 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getBusinessMemberNum(Map<String, Object> paramsMap) throws Exception {
		return businessMemberDaoHibernate.getBusinessMemberNum(paramsMap);
	}
}
