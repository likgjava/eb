package com.gpcsoft.serve.hotel.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.serve.hotel.domain.Hotel;

public interface HotelDao extends BaseGenericDao<Hotel> {

	/** 
	 * Description :  验证同一机构下酒店名称的唯一性
	 * Create Date: 2010-12-8下午04:13:18 by guoyr  Modified Date: 2010-12-8下午04:13:18 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isUnique(String objId,String orgInfoId,String hotelName)throws Exception;
	
	/** 
	 * Description : 审核酒店信息
	 * Create Date: 2010-12-9上午10:50:08 by guoyr  Modified Date: 2010-12-9上午10:50:08 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateHotelAuditStatus(String objId,String auditStatus,String useStatus);
	
	
	/** 
	 * Description :  获得酒店星级展示数据，包含酒店数量
	 * Create Date: 2010-12-8下午04:36:35 by liangxj  Modified Date: 2010-12-8下午04:36:35 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getStarLevelListShow() throws Exception;
	
	/** 
	 * Description :  获得酒店的展示数据
	 * Create Date: 2010-12-8下午04:37:40 by liangxj  Modified Date: 2010-12-8下午04:37:40 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含酒店级别、区域、客房信息、会议室信息、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<Hotel> getHotelListForShow(Page<Hotel> page,Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  根据酒店的星级获得酒店的区域信息
	 * Create Date: 2010-12-8下午04:39:48 by liangxj  Modified Date: 2010-12-8下午04:39:48 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getDistrictListShowByStarLevel(String districtId,String starLevel,Short districtLevel) throws Exception;
	
	/** 
	 * Description :  根据主键，获得酒店的详细信息，包括基本信息、客房信息、会议室信息等
	 * Create Date: 2010-12-10下午04:31:14 by liangxj  Modified Date: 2010-12-10下午04:31:14 by liangxj
	 * @param   objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	public List<Hotel> getHotelAllInfoList(final String objIds) throws Exception ;
	
	/** 
	 * Description :  根据主键，获得酒店的详细信息，包括基本信息、客房信息、会议室信息等
	 * Create Date: 2010-12-10下午04:31:14 by liangxj  Modified Date: 2010-12-10下午04:31:14 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Hotel getHotelAllInfo(final String objId) throws Exception ;
}
