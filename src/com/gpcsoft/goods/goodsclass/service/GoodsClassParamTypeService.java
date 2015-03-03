package com.gpcsoft.goods.goodsclass.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType;

public interface GoodsClassParamTypeService extends BaseGenericService<GoodsClassParamType> {

	/** 
	 * Description : 保存商品参数 
	 * Create Date: 2010-7-29下午03:17:34 by guoyr  Modified Date: 2010-7-29下午03:17:34 by guoyr
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	public GoodsClassParamType saveGoodsClassParamType(GoodsClassParamType goodsClassParamType) throws Exception ; 
	
	/** 
	 * Description : 上下移动商品参数时修改原行和目标行的排序 
	 * Create Date: 2010-8-2下午06:01:48 by guoyr  Modified Date: 2010-8-2下午06:01:48 by guoyr
	 * @param  sourceObjId 原行的排序
	 * @param  targetObjId 目标行的排序
	 * @return  
	 * @Exception   
	 */
	public void  updateSort (String sourceObjId, String targetObjId);
	
	/** 
	 * Description : 删除商品参数分类，并判断是否新修改父结点为叶子结点 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeGoodsClassParamType(String objId);
	
	/** 
     * Description : 根据商品分类获取分类参数信息  
     * Create Date: 2010-8-4下午05:07:29 by sunl  Modified Date: 2010-8-4下午05:07:29 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    public List<GoodsClassParamType> getGoodsClassParamByClass(String classId) throws Exception;
    
    /** 
	 * Description : 判断在同一商品分类下的商品参数在父节点下是不是唯一  
	 * Create Date: 2010-11-25上午10:23:13 by guoyr  Modified Date: 2010-11-25上午10:23:13 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isUnique(String goodsClassId, String paramName, String objId, String parentId );

	/** 
	 * Description :  批量导入商品分类参数
	 * Create Date: 2011-5-27下午04:55:07 by likg  Modified Date: 2011-5-27下午04:55:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveGoodsClassParamTypeBatch(Map<String, Object> param) throws Exception;
}
