package com.gpcsoft.goods.goodsclass.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

public interface GoodsClassManager extends BaseManager<GoodsClass>{
    /**
     * Description : 获得同一级分类的最大顺序号sort.
     * Create Date: Jan 28, 2010 10:46:28 PM by liujf  Modified Date: Jan 28, 2010 10:46:28 PM by yucy
     * @param   
     * @return  
     * @Exception
     */
    public int getMaxSortNoByParentClassId(String parentClassId);
    
    /**
     * 判断该商品分类的分类名称是否已经被其他对象使用了。
     * @param currentGoodsClassId
     * @param parentGoodsClassId
     * @param goodsClassName
     * @param goodsClassCode
     * @return
     * @throws Exception
     */
    public boolean checkNameIsExit(String currentGoodsClassId, String parentGoodsClassId, String goodsClassName, String goodsClassCode);
    
    /**
     * Description : 获得该父类下面的子类的下一个最大的编号。
     * Create Date: May 6, 2010 10:11:03 AM by liujf  Modified Date: May 6, 2010 10:11:03 AM by yucy
     * @param   
     * @return  
     * @Exception
     */
	public String getNextChildKeyCode(String firstCode, String parentId, String domian, String parentPropertyName, String targetPropertyName) throws Exception;
	
	/** 
	 * Description :  首页需要的3级商品分类
	 * Create Date: 2010-4-17下午06:59:27 by wangsw  Modified Date: 2010-4-17下午06:59:27 by wangsw
	 * @return  3级分类
	 * @Exception   
	 */
	List<GoodsClass> findGoodsClassForIndex(String nameFirstSpell);
	
	/** 
	 * Description :  根据classCode取得商品分类
	 * Create Date: 2011-12-9下午02:47:55 by yucy  Modified Date: 2011-12-9下午02:47:55 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public GoodsClass getGoodsClassByClassCode(String classCode) throws Exception;
}
