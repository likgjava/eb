package com.gpcsoft.bizplatform.buyer.dao;


import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;


public interface BuyerDao extends BaseGenericDao<Buyer> {
	
	/** 
	 * Description :  根据orgInfoId获取Buyer
	 * Create Date: 2011-10-24下午06:23:23 by sunl  Modified Date: 2011-10-24下午06:23:23 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Buyer getBuyerByOrgInfoId(String orgInfoId)throws Exception;
	
	/** 
	 * Description : 更新采购人审核状态  
	 * Create Date: 2010-7-28下午02:50:44 by sunl  Modified Date: 2010-7-28下午02:50:44 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateBuyerStatus(String buyerId,OrgInfo orgInfo) throws Exception;
	
		
	/** 
	 * Description :   根据参数获得采购人机构的展示信息列表，及时加载OrgInfor信息
	 * Create Date: 2010-10-19上午11:46:16 by liangxj  Modified Date: 2010-10-19上午11:46:16 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含所属区域、企业类型、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<Buyer> getBuyerListForShow(Page<Buyer> page,Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  根据主键，获得采购人的详细信息，包括基本信息、扩展信息、评价等
	 * Create Date: 2010-10-19下午05:17:50 by liangxj  Modified Date: 2010-10-19下午05:17:50 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Buyer getBuyerAllInfo(String objId) throws Exception;
	
	/** 
	 * Description :  获得采购人企业类型的展示数据 
	 * Create Date: 2010-10-19下午08:17:07 by liangxj  Modified Date: 2010-10-19下午08:17:07 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getUnitTypeListShow(String keyWord) throws Exception;
	
	/** 
	 * Description :  根据企业性质获得包含采购人数量的区域信息
	 * Create Date: 2010-10-19下午08:19:10 by liangxj  Modified Date: 2010-10-19下午08:19:10 by liangxj
	 * @param   districtId 区域id unitType 企业类型 districtLevel 区域级别
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getDistrictListForShow(String districtId,String unitType,Short districtLevel,String keyWord) throws Exception;
}
