package com.gpcsoft.goods.goods.service;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.GoodsBrand;

public interface GoodsBrandService extends BaseGenericService<GoodsBrand> {
    
	/** 
	 * Description :  检查品牌名称
	 * Create Date: 2011-5-6上午09:51:53 by yucy  Modified Date: 2011-5-6上午09:51:53 by yucy
	 * @param   brandName		检查目标
	 * 			goodsClassId	过滤的范围
	 * 			notObjId		排除的id
	 * @return  
	 * @Exception   
	 */
	public Map<String,Object> checkBrandName( String brandName, String goodsClassId, String notObjId ) throws Exception;

	/** 
	 * Description :  品牌新增
	 * Create Date: 2010-8-4下午02:44:59 by yucy  Modified Date: 2010-8-4下午02:44:59 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean createGoodsBrand(GoodsBrand goodsBrand,Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  品牌修改
	 * Create Date: 2010-8-4下午02:44:59 by yucy  Modified Date: 2010-8-4下午02:44:59 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateGoodsBrand(GoodsBrand goodsBrand,Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  品牌的变更
	 * Create Date: 2011-5-9上午09:20:26 by yucy  Modified Date: 2011-5-9上午09:20:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveChangeGoodsBrand(GoodsBrand goodsBrand,Map<String, Object> param) throws Exception ;
	
	/** 
	 * Description :  品牌审核
	 * Create Date: 2010-8-4下午04:22:12 by yucy  Modified Date: 2010-8-4下午04:22:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateBrandAuditStatus(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  启卖禁卖
	 * Create Date: 2010-8-6上午03:04:29 by yucy  Modified Date: 2010-8-6上午03:04:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateSellStatus(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  删除品牌
	 * Create Date: 2010-8-6上午09:26:32 by yucy  Modified Date: 2010-8-6上午09:26:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean deleteBrand(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  报废品牌(有效品牌)
	 * Create Date: 2010-8-6上午09:54:02 by yucy  Modified Date: 2010-8-6上午09:54:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateDestroy(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取指定维护供应商的商品品牌 
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsBrand> getAssignedGoodsBrand() throws Exception;
	
	/** 
	 * Description : 获得供应商可维护的所有品牌  
	 * Create Date: 2010-9-9下午03:52:29 by guoyr  Modified Date: 2010-9-9下午03:52:29 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsBrand> getAllGoodsBrandByOrgId();

	/** 
	 * Description :  根据参数获得品牌的展示信息列表
	 * Create Date: 2011-2-16下午05:50:15 by liangxj  Modified Date: 2011-2-16下午05:50:15 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<GoodsBrand> getGoodsBrandListForShow(Page<GoodsBrand> page,Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  审核变更信息
	 * Create Date: 2011-5-10上午09:37:02 by yucy  Modified Date: 2011-5-10上午09:37:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean auditChange(String brandId, String auditStatus) throws Exception;
}
