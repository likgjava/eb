package com.gpcsoft.pubservice.application.service.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.dao.ServiceGroupDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceGroup;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.pubservice.application.service.service.ServiceGroupService;

@Service 
public class ServiceGroupServiceImpl extends BaseGenericServiceImpl<ServiceGroup> implements ServiceGroupService {

	@Autowired(required=true) @Qualifier("serviceGroupDaoHibernate")
	private ServiceGroupDao serviceGroupDao;

	/** 
	 * Description :  获取搭配服务
	 * 根据会员级别进行过滤（会员级别为空的也应该被查询出来）
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   主服务ID
	 * @return  
	 * @Exception   
	 */
	public List<ServiceGroup> getServiceGroupList(Map<String,Object> params) throws Exception {
		return serviceGroupDao.getServiceGroupList(params);
	}
	
	/** 
	 * Description :   新增/修改服务组合
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveServiceGroup(ServiceGroup serviceGroup) throws Exception {
		//新增
		if(!StringUtils.hasLength(serviceGroup.getObjId())) {
			serviceGroup.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			serviceGroup.setCreateTime(new Date());
			serviceGroup.setUseStatus(ServiceEnum.USE_VALID);
		}
		
		serviceGroupDao.save(serviceGroup);
	}

	/** 
     * Description :  修改服务组合的使用状态
     * Create Date: 2011-3-23下午05:09:24 by likg  Modified Date: 2011-3-23下午05:09:24 by likg
     * @param   
     * @return  
     * @Exception   
     */
	public void updateUseStatus(String objId, String useStatus) throws Exception {
		ServiceGroup serviceGroup = serviceGroupDao.get(objId);
		
		if(ServiceEnum.USE_TEMP.equals(useStatus)) { //设置为临时状态，可以再启用
			serviceGroup.setUseStatus(ServiceEnum.USE_TEMP);
		} else if(ServiceEnum.USE_VALID.equals(useStatus)) { //设置为有效状态（启用状态）
			serviceGroup.setUseStatus(ServiceEnum.USE_VALID);
		} else if(ServiceEnum.USE_INVALID.equals(useStatus)) { //设置为无效状态（不可再启用，相当于删除）
			serviceGroup.setUseStatus(ServiceEnum.USE_INVALID);
		}
	}
}
