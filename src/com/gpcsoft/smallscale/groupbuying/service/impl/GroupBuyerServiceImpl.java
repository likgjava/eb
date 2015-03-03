package com.gpcsoft.smallscale.groupbuying.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyer;
import com.gpcsoft.smallscale.groupbuying.enumeration.GroupBuyingEnum;
import com.gpcsoft.smallscale.groupbuying.manager.GroupBuyerManager;
import com.gpcsoft.smallscale.groupbuying.manager.GroupBuyingManager;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyerService;

@Service 
public class GroupBuyerServiceImpl extends BaseGenericServiceImpl<GroupBuyer> implements GroupBuyerService {

	@Autowired(required=true) @Qualifier("groupBuyerManagerImpl")
	private GroupBuyerManager groupBuyerManager;
	
	@Autowired(required=true) @Qualifier("groupBuyingManagerImpl")
	private GroupBuyingManager groupBuyingManager;

	/** 
	 * Description :  保存团购采购人信息，并生产订单信息
	 * Create Date: 2011-6-22下午07:39:35 by likg  Modified Date: 2011-6-22下午07:39:35 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveGroupBuyer(GroupBuyer groupBuyer) throws Exception {
		//新增
		if(!StringUtils.hasLength(groupBuyer.getObjId())) {
			groupBuyer.setPayStatus(GroupBuyingEnum.NO_PAY); //设置支付状态
		}
		groupBuyerManager.save(groupBuyer);
	}

	/** 
	 * Description :  修改支付状态，支付成功后修改团购的当前团购数
	 * Create Date: 2011-6-24上午11:27:13 by likg  Modified Date: 2011-6-24上午11:27:13 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updatePayStatus(String objId, String payStatus) throws Exception {
		Boolean paySuccess = false;
		
		//修改支付状态
		paySuccess = groupBuyerManager.updatePayStatus(objId, payStatus);
		
		//更新团购信息
		if(paySuccess) {
			GroupBuyer groupBuyer = groupBuyerManager.get(objId);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("objId", groupBuyer.getGroupBuying().getObjId());
			param.put("addNumber", groupBuyer.getAmount());
			groupBuyingManager.updateGroupBuying(param);
		}
		
		return paySuccess;
	}

}
