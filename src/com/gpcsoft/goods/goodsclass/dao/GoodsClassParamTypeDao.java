package com.gpcsoft.goods.goodsclass.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType;

public interface GoodsClassParamTypeDao extends BaseGenericDao<GoodsClassParamType> {
    
	/** 
	 * Description :得到父节点的所有子级节点的个数  
	 * Create Date: 2010-8-4上午09:56:42 by guoyr  Modified Date: 2010-8-4上午09:56:42 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public int getgoodsClassParamTypeMaxSort(String parentId);
	
	/** 
	 * Description : 修改节点是否为叶子结点  
	 * Create Date: 2010-8-4上午09:57:21 by guoyr  Modified Date: 2010-8-4上午09:57:21 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateGoodsClassParamTypeIsLeaf(String objId, boolean isLeaf);
	
	/** 
	 * Description : 查询当前商品参数的父结点的子结点的个数 
	 * Create Date: 2010-8-3下午06:16:29 by guoyr  Modified Date: 2010-8-3下午06:16:29 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getSubGoodsClassParamTypeCount(String objId);
	
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
	 * Description :  删除分类参数(by分类id)
	 * Create Date: 2010-11-30下午01:55:04 by yucy  Modified Date: 2010-11-30下午01:55:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void deleteByGoodsClassId(String classId) throws Exception;

	/** 
	 * Description :  批量导入商品分类参数
	 * Create Date: 2011-5-27下午04:55:07 by likg  Modified Date: 2011-5-27下午04:55:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveGoodsClassParamTypeBatch(Map<String, Object> param) throws Exception;
}
