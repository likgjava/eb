package com.gpcsoft.epp.projrule.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.projrule.domain.Facilities;

public interface FacilitiesDao extends BaseGenericDao<Facilities>  {
	
	/**
	 * FuncName: getFacilitiesList
	 * Description :  获取会议室设备
	 * @param 
	 * @param meetRoomId
	 * @return List<Facilities>
	 * @author: shenjz
	 * @Create Date:2011-11-3  下午07:27:16
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-3  下午07:27:16
	 */
	public List<Facilities> getFacilitiesList(String meetRoomId);
	
	/**
	 * FuncName: getFacilitiesList
	 * Description :  删除一个评标室所有设备
	 * @param 
	 * @param meetRoomId
	 * @return List<Facilities>
	 * @author: shenjz
	 * @Create Date:2011-11-3  下午07:27:16
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-3  下午07:27:16
	 */
	@SuppressWarnings("unchecked")
	public void deleteFacilitiesList(final String meetRoomId);
}
