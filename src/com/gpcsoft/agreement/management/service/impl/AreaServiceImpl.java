package com.gpcsoft.agreement.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.management.domain.Area;
import com.gpcsoft.agreement.management.manager.AreaManager;
import com.gpcsoft.agreement.management.service.AreaService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.tree.DHtmlTree;
import com.gpcsoft.core.utils.DHtmlTreeUtils;

@Service 
public class AreaServiceImpl extends BaseGenericServiceImpl<Area> implements AreaService {

	@Autowired(required=true) @Qualifier("areaManagerImpl")
	private AreaManager areaManager;

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AreaService#removeArea(java.lang.String)
	 */
	public Integer removeArea(String areaId) {
		return areaManager.removeArea(areaId);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AreaService#getAreaInfo(java.util.Map)
	 */
	public Map<String, Object> getAreaInfo(Map<String, Object> params) {
		return areaManager.getAreaInfo(params);
	}

	@SuppressWarnings("unchecked")
	public Object getOwnerAreaTree(DHtmlTree dhtmlTree,String id,Map<String, Object> params) throws Exception {
		List nodeList = new ArrayList();
		params.put("id", id);
		nodeList.add(areaManager.getAreaTreeById(params));
		nodeList.add(new ArrayList());
		if("listTop".equals(dhtmlTree.getAction())){
			dhtmlTree.setId("0");
		}
		return DHtmlTreeUtils.getTree(dhtmlTree, nodeList);
	}

	/** 
	 * Description :  根据是否有效取到区间和区域信息
	 * Create Date: 2011-12-1下午02:24:38 by yucy  Modified Date: 2011-12-1下午02:24:38 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getAreaList(String isValid) throws Exception {
		return areaManager.getAreaList(isValid);
	}

	/** 
	 * Description :  保存区间（批量）
	 * Create Date: 2011-12-2上午11:39:01 by yucy  Modified Date: 2011-12-2上午11:39:01 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveAreaBatch(Map<String, Object> param) throws Exception {
		return areaManager.saveAreaBatch(param);
	}
}
