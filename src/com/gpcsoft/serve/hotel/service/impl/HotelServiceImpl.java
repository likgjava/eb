package com.gpcsoft.serve.hotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.serve.hotel.dao.HotelDao;
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.enumeration.HotelEnum;
import com.gpcsoft.serve.hotel.service.HotelService;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.baseData.domain.District;
import com.gpcsoft.srplatform.baseData.manager.DistrictManager;

@Service 
public class HotelServiceImpl extends BaseGenericServiceImpl<Hotel> implements HotelService {

	@Autowired(required=true) @Qualifier("districtManagerImpl")
	private DistrictManager districtManager;
	@Autowired(required=true)  @Qualifier("hotelDaoHibernate")
	private HotelDao hotelDaoHibernate;

	/** 
	 * Description :  保存酒店信息
	 * Create Date: 2010-12-7上午10:28:21 by guoyr  Modified Date: 2010-12-7上午10:28:21 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Hotel saveHotel(Hotel hotel)throws Exception{
		User user = AuthenticationHelper.getCurrentUser(true);
		OrgInfo orgInfo= (OrgInfo)user.getOrgInfo();
		hotel.setOrgInfo(orgInfo);
		
		// 酒店的编号设为酒店的名称首字母+机构的编号
		WordToSpell spell = new WordToSpell();
		hotel.setHotelCode(spell.String2Alpha(hotel.getHotelName())+orgInfo.getOrgCode()); 
		
		// 初始为有效状态
		if(null == hotel.getObjId()){
			hotel.setUseStatus(HotelEnum.USE_TEMP);
		}
		return hotelDaoHibernate.save(hotel);
	}
	
	/** 
	 * Description :  验证同一机构下酒店名称的唯一性
	 * Create Date: 2010-12-8下午04:13:18 by guoyr  Modified Date: 2010-12-8下午04:13:18 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isUnique(String objId, String hotelName)throws Exception{
		OrgInfo orgInfo = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		String orgInfoId = orgInfo.getObjId();
		return hotelDaoHibernate.isUnique(objId, orgInfoId, hotelName);
	}
	
	/** 
	 * Description : 得到初始化区域信息 
	 * Create Date: 2010-12-9上午10:36:24 by guoyr  Modified Date: 2010-12-9上午10:36:24 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getDistrictInfo()throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		// 为列表添加区域(便于form表单的绑定)
		Hotel hotel = new Hotel();
		District provinceObj = new District();
		District cityObj = new District();
		District townObj = new District();
		townObj.setParent(cityObj);
		cityObj.setParent(provinceObj);
		hotel.setDistrict(townObj);
		
		OrgInfo orgInfo = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		String orgInfoId = orgInfo.getObjId();
		
		//获取所有【province-->city-->town】数据
		List<District> province = districtManager.findTopDistricts();
		List<District> city = new ArrayList<District>();
		List<District> town = new ArrayList<District>();
		model.put("province", province);
		model.put("city", city);
		model.put("town", town);
		model.put("hotel",hotel);
		model.put("orgInfoId", orgInfoId);
		return model;
	}
	
	/** 
	 * Description :  得到酒店的信息
	 * Create Date: 2010-12-9上午10:36:43 by guoyr  Modified Date: 2010-12-9上午10:36:43 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getHotelInfo(String objId)throws Exception{
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		Hotel hotel = null;
		if(StringUtils.hasLength(objId)){
			hotel = hotelDaoHibernate.get(objId);
		}else {
			hotel = new Hotel();
		}
		//获取所有【province-->city-->town】数据
		List<District> province = districtManager.findTopDistricts();
		List<District> city = new ArrayList<District>();
		List<District> town = new ArrayList<District>();
		if(null != hotel && hotel.getDistrict() != null) {
			city = districtManager.getSubDistricts(hotel.getDistrict().getParent().getParent().getObjId());
			town = districtManager.getSubDistricts(hotel.getDistrict().getParent().getObjId());
		}else {
			hotel.setDistrict(null);
		}
		model.put("hotel", hotel);
		model.put("province", province);
		model.put("city", city);
		model.put("town", town);
		return model;
	}
	
	/** 
	 * Description : 审核酒店信息
	 * Create Date: 2010-12-9上午10:50:08 by guoyr  Modified Date: 2010-12-9上午10:50:08 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateHotelAuditStatus(String objId,String auditStatus)throws Exception{
		
		// 默认为临时
		String useStatus = HotelEnum.USE_TEMP;
		
		// 如果是审核通过，则将使用状态置为有效，否则为临时
		if(StringUtils.hasLength(auditStatus) && auditStatus.equals(HotelEnum.PASS_EXAM)){
			useStatus = HotelEnum.USE_VALID;
		}
		return hotelDaoHibernate.updateHotelAuditStatus(objId, auditStatus, useStatus);
	}
	
	
	/** 
	 * Description :  获得酒店星级展示数据，包含酒店数量
	 * Create Date: 2010-12-8下午04:36:35 by liangxj  Modified Date: 2010-12-8下午04:36:35 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getStarLevelListShow() throws Exception {
		return hotelDaoHibernate.getStarLevelListShow();
	}
	
	/** 
	 * Description :  获得酒店的展示数据
	 * Create Date: 2010-12-8下午04:37:40 by liangxj  Modified Date: 2010-12-8下午04:37:40 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含酒店级别、区域、客房信息、会议室信息、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<Hotel> getHotelListForShow(Page<Hotel> page,Map<String, Object> paramsMap) throws Exception {
		return hotelDaoHibernate.getHotelListForShow(page, paramsMap);
	}
	
	/** 
	 * Description :  根据酒店的星级获得酒店的区域信息
	 * Create Date: 2010-12-8下午04:39:48 by liangxj  Modified Date: 2010-12-8下午04:39:48 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getDistrictListShowByStarLevel(String districtId,String starLevel,Short districtLevel) throws Exception {
		return hotelDaoHibernate.getDistrictListShowByStarLevel(districtId, starLevel, districtLevel);
	}
	
	/** 
	 * Description :  根据主键，获得酒店的详细信息，包括基本信息、客房信息、会议室信息等
	 * Create Date: 2010-12-10下午04:31:14 by liangxj  Modified Date: 2010-12-10下午04:31:14 by liangxj
	 * @param   objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	public List<Hotel> getHotelAllInfoList(final String objIds) throws Exception {
		return hotelDaoHibernate.getHotelAllInfoList(objIds);
	}
	
	/** 
	 * Description :  根据主键，获得酒店的详细信息，包括基本信息、客房信息、会议室信息等
	 * Create Date: 2010-12-10下午04:31:14 by liangxj  Modified Date: 2010-12-10下午04:31:14 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Hotel getHotelAllInfo(final String objId) throws Exception {
		return hotelDaoHibernate.getHotelAllInfo(objId);
	}
}
