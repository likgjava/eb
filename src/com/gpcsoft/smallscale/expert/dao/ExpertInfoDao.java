package com.gpcsoft.smallscale.expert.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;

public interface ExpertInfoDao extends BaseGenericDao<ExpertInfo> {
	/** 
	 * Description :  根据主键，获得专家的详细信息【基本信息，教育经历信息，培训经历信息，任职经历信息，职称管理信息】
	 * Create Date: 2010-11-26上午09:20:39 by likg  Modified Date: 2010-11-26上午09:20:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	ExpertInfo getExpertAllInfo(String objId) throws Exception;
	
	/** 
	 * Description :  根据多个主键（已逗号分隔），获得专家的详细列表信息【基本信息，教育经历信息，培训经历信息，任职经历信息，职称管理信息】
	 * Create Date: 2010-11-26上午09:29:07 by likg  Modified Date: 2010-11-26上午09:29:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<ExpertInfo> getExpertAllInfoList(String objIds) throws Exception;
	
	/** 
	 * Description : 根据当前用户获得专家信息 
	 * Create Date: 2010-11-30下午02:26:41 by guoyr  Modified Date: 2010-11-30下午02:26:41 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ExpertInfo getUserExpertInfo(String userId)throws Exception;
	
	/** 
	 * Description :  根据参数获得专家的展示信息列表，及时加载User和employee信息
	 * Create Date: 2010-12-1下午12:09:08 by liangxj  Modified Date: 2010-12-1下午12:09:08 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含行业信息、区域信息、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<ExpertInfo> getExpertListForShow(Page<ExpertInfo> page,Map<String, Object> paramsMap) throws Exception ;
	
	/** 
	 * Description :  根据品目获得下级品目的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getCategoryListShowByCategory(String categoryId,String categoryCode,Boolean isLeaf) throws Exception;
	
	/** 
	 * Description :  根据品目获得包含专家数量的区域信息
	 * Create Date: 2010-12-1下午05:06:35 by liangxj  Modified Date: 2010-12-1下午05:06:35 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getDistrictListForShow(String districtId,String categoryId,Short districtLevel) throws Exception;
}
