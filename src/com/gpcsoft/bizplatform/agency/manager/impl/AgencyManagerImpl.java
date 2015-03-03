package com.gpcsoft.bizplatform.agency.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.agency.dao.AgencyDao;
import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.agency.manager.AgencyManager;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.BeanUtils;


@Repository
public class AgencyManagerImpl extends BaseManagerImpl<Agency> implements AgencyManager {

	@Autowired(required=true)  @Qualifier("agencyDaoHibernate")
	private AgencyDao agencyDaoHibernate;

	/** 
	 * Description : 更新代理机构审核状态  
	 * Create Date: 2010-7-28下午02:50:44 by sunl  Modified Date: 2010-7-28下午02:50:44 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateAgencyStatus(String agencyId,OrgInfo orgInfo) throws Exception {
		return agencyDaoHibernate.updateAgencyStatus(agencyId, orgInfo);
	}
	
	/** 
	 * Description :  保存或更新代理机构扩展信息
	 * 				  传入参数为agencyId和表单对象agency
	 * Create Date: 2010-7-26下午09:03:47 by sunl  Modified Date: 2010-7-26下午09:03:47 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Agency saveAgency(boolean isSave,Agency agency) throws Exception {
		if(isSave){//保存
			Agency oldAgency = agencyDaoHibernate.get(agency.getObjId());
			Agency newAgency = new Agency();
			//将数据库旧值拷贝到新对象内
			BeanUtils.copyPropertiesFilterEmpty(newAgency,oldAgency);
			//将表单新值拷贝到新对象内
			BeanUtils.copyPropertiesFilterEmpty(newAgency,agency);
			newAgency.setObjId(null);
			newAgency.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//新代理机构更新为待审核状态
			newAgency = agencyDaoHibernate.save(newAgency);
			return newAgency;
		}else{//更新
			Agency oldAgency = agencyDaoHibernate.get(agency.getObjId());
			BeanUtils.copyPropertiesFilterEmpty(oldAgency,agency);
			return oldAgency;
		}
	}
}
