package com.gpcsoft.pubservice.application.service.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.dao.ServiceBaseDao;
import com.gpcsoft.pubservice.application.service.dao.ServiceGroupDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceBase;
import com.gpcsoft.pubservice.application.service.domain.ServiceCharging;
import com.gpcsoft.pubservice.application.service.domain.ServiceGroup;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.pubservice.application.service.manager.ServiceBaseManager;
import com.gpcsoft.pubservice.application.service.manager.ServiceChargingManager;
import com.gpcsoft.pubservice.application.service.service.ServiceBaseService;

@Service 
public class ServiceBaseServiceImpl extends BaseGenericServiceImpl<ServiceBase> implements ServiceBaseService {

	@Autowired(required=true) @Qualifier("serviceBaseManagerImpl")
	private ServiceBaseManager serviceBaseManager;
	
	@Autowired(required=true) @Qualifier("serviceBaseDaoHibernate")
	private ServiceBaseDao serviceBaseDao;
	
	@Autowired(required=true) @Qualifier("serviceChargingManagerImpl")
	private ServiceChargingManager serviceChargingManager;
	
	@Autowired(required=true) @Qualifier("serviceGroupDaoHibernate")
	private ServiceGroupDao serviceGroupDao;

	/** 
	 * Description :  新增/修改服务
	 * Create Date: 2011-3-22下午01:21:16 by likg  Modified Date: 2011-3-22下午01:21:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveService(ServiceBase service) throws Exception {
		//新增服务
		if(!StringUtils.hasLength(service.getObjId())) {
			service.setUseStatus(ServiceEnum.USE_VALID);
			service.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			service.setCreateTime(new Date());
		}
		
		serviceBaseManager.save(service);
	}

	/** 
	 * Description :  查询要订阅的服务列表
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ServiceBase> getServiceSubscribeList() throws Exception {
		return serviceBaseDao.getServiceSubscribeList();
	}

	 /** 
     * Description :  修改服务的状态
     * Create Date: 2011-3-23下午05:09:24 by likg  Modified Date: 2011-3-23下午05:09:24 by likg
     * @param   
     * @return  
     * @Exception   
     */
	public void updateStatus(Map<String, Object> param) throws Exception {
		serviceBaseDao.updateStatus(param);
	}

	/** 
	 * Description :  获得服务的详细信息（包括服务计费信息和服务组合信息）
	 * Create Date: 2011-4-6下午08:06:07 by likg  Modified Date: 2011-4-6下午08:06:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void getServiceAllInfo(Map<String, Object> param, Map<String, Object> model) throws Exception {
		//获取服务信息
		String serviceName = (String) param.get("serviceName");
		String hql = "from ServiceBase sb where sb.useStatus != ?";
		if(StringUtils.hasLength(serviceName)) {
			hql += " and sb.serviceName like '%" + serviceName + "%' ";
		}
		List<ServiceBase> serviceList = serviceBaseManager.findByHql(hql, new Object[]{ServiceEnum.USE_INVALID});
		
		//获取服务计费信息和组合信息的数量
		List<Long> serviceChargingNumList = new ArrayList<Long>();
		List<Long> serviceGroupNumList = new ArrayList<Long>();
		String hql2 = "from ServiceCharging sc where sc.serviceBase.objId = ?";
		String hql3 = "from ServiceGroup sg where sg.mainService.objId = ?";
		for(ServiceBase sb : serviceList) {
			List<ServiceCharging> serviceChargingList = serviceChargingManager.findByHql(hql2, new Object[]{sb.getObjId()});
			serviceChargingNumList.add(new Long(serviceChargingList.size()));
			
			List<ServiceGroup> serviceGroupList = serviceGroupDao.findbyHql(hql3, sb.getObjId());
			serviceGroupNumList.add(new Long(serviceGroupList.size()));
		}
		
		model.put("serviceList", serviceList);
		model.put("serviceChargingNumList", serviceChargingNumList);
		model.put("serviceGroupNumList", serviceGroupNumList);
	}
}
