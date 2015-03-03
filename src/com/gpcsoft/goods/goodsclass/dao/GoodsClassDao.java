package com.gpcsoft.goods.goodsclass.dao;

import java.util.List;
import java.util.Set;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

public interface GoodsClassDao extends BaseGenericDao<GoodsClass> {
    /**
     * Description : 根据商品分类ID查找其孩子结点。
     * Create Date: Jan 27, 2010 1:36:41 PM by liujf  Modified Date: Jan 27, 2010 1:36:41 PM by yucy
     * @param   
     * @return  
     * @Exception
     */
    public List<GoodsClass> findChildGoodsClassesListByGoodsClassId(String goodsClassId) ;

    /**
     * Description : 查找商品分类信息。
     * Create Date: Jan 28, 2010 10:09:28 AM by liujf  Modified Date: Jan 28, 2010 10:09:28 AM by yucy
     * @param   
     * @return  
     * @Exception
     */
    public GoodsClass findGoodsClassByObjId(String goodsClassId) throws Exception;
    
    /**
     * Description : 获得该父类下面最大的编号。
     * Create Date: May 6, 2010 10:11:03 AM by liujf  Modified Date: May 6, 2010 10:11:03 AM by liujf
     * @param   
     * @return  
     * @Exception
     */
	public String getMaxChildKeyCode(String parentId, String tableName, String parentFieldName, String targetFieldName);
	
	
	/** 
	 * Description :  获得该父类下面最大的编号。(by hql)
	 * Create Date: 2010-7-28下午05:50:26 by yucy  Modified Date: 2010-7-28下午05:50:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getMaxChildKeyCodeByHQl(final String parentId, final String domain, final String parentPropertyName, final String targetPropertyName);

	
	/** 
	 * Description :  首页需要的商品分类
	 * Create Date: 2010-4-17下午07:00:31 by wangsw  Modified Date: 2010-4-17下午07:00:31 by wangsw
	 * @param  分类名称首字母
	 * @return  3级商品分类
	 * @Exception   
	 */
	public List<GoodsClass> findGoodsClassForIndex(String nameFirstSpell);
	
	/** 
	 * Description :  首页需要的商品分类,只查询有商品的
	 * Create Date: 2010-12-17下午02:51:55 by liangxj  Modified Date: 2010-12-17下午02:51:55 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsClass> findGoodsClassForHasGoods(String nameFirstSpell);
	
	/** 
	 * Description :  根据code获得分类
	 * Create Date: 2010-12-20下午04:33:21 by liangxj  Modified Date: 2010-12-20下午04:33:21 by liangxj
	 * @param   code数组
	 * @return  
	 * @Exception   
	 */
	public List<GoodsClass> findGoodsClassByCodes(final Set<String> codes);
	
	
	/** 
	 * Description :  根据ids获得分类
	 * Create Date: 2011-5-6下午03:57:52 by yucy  Modified Date: 2011-5-6下午03:57:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsClass> findGoodsClassByIds(String[] objIds) throws Exception;

	/** 
	 * Description :  根据classCode取得商品分类
	 * Create Date: 2011-12-9下午02:47:55 by yucy  Modified Date: 2011-12-9下午02:47:55 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public GoodsClass getGoodsClassByClassCode(String classCode) throws Exception;
}
