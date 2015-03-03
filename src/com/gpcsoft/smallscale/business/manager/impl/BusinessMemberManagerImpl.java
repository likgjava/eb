package com.gpcsoft.smallscale.business.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.pubservice.application.service.dao.ServiceSubscribeDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceSubscribe;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.smallscale.business.dao.BusinessMemberDao;
import com.gpcsoft.smallscale.business.domain.BusinessMember;
import com.gpcsoft.smallscale.business.manager.BusinessMemberManager;
import com.gpcsoft.srplatform.auth.dao.OrgRoleDao;

@Repository
public class BusinessMemberManagerImpl extends BaseManagerImpl<BusinessMember> implements BusinessMemberManager {

	@Autowired(required=true)  @Qualifier("businessMemberDaoHibernate")
	private BusinessMemberDao businessMemberDaoHibernate;
	@Autowired(required=true)  @Qualifier("orgRoleDaoHibernate")
	private OrgRoleDao orgRoleDaoHibernate;
	@Autowired(required=true)  @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDaoHibernate;
	@Autowired(required=true)  @Qualifier("serviceSubscribeDaoHibernate")
	private ServiceSubscribeDao serviceSubscribeDao;

	/** 
	 * Description :  根据companyId，获得最新的orgInfo。
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BusinessMember> getBusinessMemberList(String orgInfoId) throws Exception {
		return businessMemberDaoHibernate.getBusinessMemberList(orgInfoId);
	}
	
	/** 
	 * Description :  商圈会员角色定时任务(如果商圈会员到期，自动删除角色)
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void update_businessmember_orgrole() throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("select b from ServiceSubscribe b left join fetch b.orgInfo where b.auditStatus=? ");
		hql.append("and b.orgInfo.objId||b.serviceBase.objId||b.endTime in (");
		hql.append("select b1.orgInfo.objId||b.serviceBase.objId||max(b1.endTime) from ServiceSubscribe b1 ");
		hql.append("group by b1.orgInfo.objId,b.serviceBase.objId )");
		
		List<ServiceSubscribe> memberList = serviceSubscribeDao.findbyHql(hql.toString(), ServiceEnum.PASS_EXAM);
		
		String orgId = "";
		for (ServiceSubscribe subscribe : memberList) {
			//会员过期
			if(subscribe.getEndTime().getTime() < new Date().getTime()) {
				OrgInfo orgInfo = (orgInfoDaoHibernate.findbyHql("from OrgInfo t left join fetch t.company where t.objId=?", subscribe.getOrgInfo().getObjId())).get(0);
				orgId = orgInfo.getCompany().getObjId();
				System.out.println("=========================="+orgId);
				
				//删除角色
				orgRoleDaoHibernate.deleteOrgRoleByOrgId(orgId);
			}
		}
	}
}
