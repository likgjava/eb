package com.gpcsoft.goods.goodsclass.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.manager.GoodsClassManager;

@Repository
public class GoodsClassManagerImpl extends BaseManagerImpl<GoodsClass> implements GoodsClassManager {

	@Autowired(required=true)  @Qualifier("goodsClassDaoHibernate")
	private GoodsClassDao goodsClassDaoHibernate;
	
	/**
	 * Description : 获得同一级分类的最大顺序号sortNo.
	 * Create Date: Jan 28, 2010 10:46:28 PM by liujf  Modified Date: Jan 28, 2010 10:46:28 PM by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	public int getMaxSortNoByParentClassId(String parentClassId) {
	    List<GoodsClass> goodsClassesList = goodsClassDaoHibernate.findChildGoodsClassesListByGoodsClassId(parentClassId);
	    if (goodsClassesList == null || goodsClassesList.isEmpty()) return -1;
	    GoodsClass goodsClassMax = goodsClassesList.get(goodsClassesList.size() -1);
	    if (goodsClassMax.getSort() == null) return -1; /*如果sortNo的值为空，那么返回-1的话，下一个就可以从0开始了。*/
	    else return goodsClassMax.getSort().intValue(); 
	}
    
    /**
     * 判断该商品分类的分类名称是否已经被其他对象使用了。
     * @param currentGoodsClassId
     * @param parentGoodsClassId
     * @param goodsClassName
     * @param goodsClassCode
     * @return
     * @throws Exception
     */
    public boolean checkNameIsExit(String currentGoodsClassId, String parentGoodsClassId, String goodsClassName, String goodsClassCode) {
    	List<GoodsClass> goodsClasssList = null;
    	if (currentGoodsClassId == null) {
    		/*在新增参数的时候做的查询*/
    		if (parentGoodsClassId == null || "-1".equals(parentGoodsClassId)) {
    			/*判断顶级结点是否重复*/
    			goodsClasssList = goodsClassDaoHibernate.findbyHql("from GoodsClass c where c.parentClazz is null and (c.goodsClassName=? or c.goodsClassCode=?)", goodsClassName, goodsClassCode);
    		} else {
    			goodsClasssList = goodsClassDaoHibernate.findbyHql("from GoodsClass c where c.parentClazz.objId=? and (c.goodsClassName=? or c.goodsClassCode=?)", parentGoodsClassId, goodsClassName, goodsClassCode);    			
    		}
    	} else {
    		/*在修改参数的时候做的查询*/
    		if (parentGoodsClassId == null) {
    			/*判断顶级结点是否重复*/
    			goodsClasssList = goodsClassDaoHibernate.findbyHql("from GoodsClass c where c.parentClazz is null and (c.goodsClassName=? or c.goodsClassCode=?) and c.objId!=?", goodsClassName, goodsClassCode, currentGoodsClassId);
    		} else {
    			goodsClasssList = goodsClassDaoHibernate.findbyHql("from GoodsClass c where c.parentClazz.objId=? and (c.goodsClassName=? or c.goodsClassCode=?) and c.objId!=?", parentGoodsClassId, goodsClassName, goodsClassCode, currentGoodsClassId);    			
    		}
    	}
    	
    	if (goodsClasssList != null && !goodsClasssList.isEmpty()) {
    		return true;
    	} else {
    		return false;
    	}
    }
	
    /**
     * Description : 获得该分类在列表中的位置(-1表示在列表中不存在这个对象)。
     * Create Date: Jan 28, 2010 4:24:01 PM by liujf  Modified Date: Jan 28, 2010 4:24:01 PM by yucy
     * @param   
     * @return  
     * @Exception
     */
    private int getGoodsClassPos(String goodsClassId, List<GoodsClass> goodsClassesList) throws Exception {
        Assert.notNull(goodsClassesList);
        
        int curGoodsClassPos = 0;
        for (GoodsClass goodsClass : goodsClassesList) {
            if (goodsClassId.equals(goodsClass.getObjId())) {
                break;
            }
            curGoodsClassPos++;
        }
        
        if (curGoodsClassPos == goodsClassesList.size()) {
            /*在列表中不存在这个对象的ID*/
            curGoodsClassPos = -1;
        }
        
        return curGoodsClassPos;
    }
	
	/**
	 * Description : 获得排在当前商品分类前面的商品分类(根据sortNo来排序)。
	 * Create Date: Jan 28, 2010 8:46:56 AM by liujf  Modified Date: Jan 28, 2010 8:46:56 AM by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	public GoodsClass getFrontGoodsClass(GoodsClass goodsClass) throws Exception {
	    Assert.notNull(goodsClass);
        Assert.hasText(goodsClass.getObjId());
        String parentId = (null!=goodsClass.getParentClazz()?goodsClass.getParentClazz().getObjId():null); // by yucy
	    List<GoodsClass> goodsClassesList = goodsClassDaoHibernate.findChildGoodsClassesListByGoodsClassId(parentId);
	    GoodsClass goodsClassFront = null;
	    int curGoodsClassPos = getGoodsClassPos(goodsClass.getObjId(), goodsClassesList);
	    if (curGoodsClassPos == -1) throw new Exception("在列表中不存在这个对象!");
	    
	    if (curGoodsClassPos == 0) {
	        /*当前的商品分类已经是第一个*/
	        throw new Exception("当前商品分类已经同级分类的首位了！");
	        //goodsClassFront = goodsClass;
	    } else {
	        goodsClassFront = goodsClassesList.get(curGoodsClassPos - 1);
	    }
	    
	    return goodsClassFront;
	}
	
    /**
     * Description : 获得排在当前商品分类后面的商品分类(根据sortNo来排序)。
     * Create Date: Jan 28, 2010 8:46:56 AM by liujf  Modified Date: Jan 28, 2010 8:46:56 AM by yucy
     * @param   
     * @return  
     * @Exception
     */
    public GoodsClass getRearGoodsClass(GoodsClass goodsClass) throws Exception {
        Assert.notNull(goodsClass);
        Assert.hasText(goodsClass.getObjId());
        String parentId = (null!=goodsClass.getParentClazz()?goodsClass.getParentClazz().getObjId():null); // by yucy
        List<GoodsClass> goodsClassesList = goodsClassDaoHibernate.findChildGoodsClassesListByGoodsClassId(parentId);
        GoodsClass goodsClassRear = null;
        int curGoodsClassPos = getGoodsClassPos(goodsClass.getObjId(), goodsClassesList);
        if (curGoodsClassPos == -1) throw new Exception("在列表中不存在这个对象!");
        
        if (curGoodsClassPos == goodsClassesList.size() - 1) {
            /*当前的商品分类已经是第一个*/
            throw new Exception("当前商品分类已经同级分类的末位了！");
            //goodsClassFront = goodsClass;
        } else {
            goodsClassRear = goodsClassesList.get(curGoodsClassPos + 1);
        }
        
        return goodsClassRear;
    }
	
	/**
	 * Description : 把当前的商品分类向上移动一位。
	 * Create Date: Jan 28, 2010 8:41:59 AM by liujf  Modified Date: Jan 28, 2010 8:41:59 AM by liujf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void moveUp(String goodsClassId) throws Exception {
        Assert.hasText(goodsClassId);
        
	    GoodsClass goodsClassCur = this.goodsClassDaoHibernate.get(goodsClassId);
	    GoodsClass goodsClassFront = this.getFrontGoodsClass(goodsClassCur);
	    
	    /*挪动位置*/
	    Long currentPos = goodsClassCur.getSort();
	    goodsClassCur.setSort(goodsClassFront.getSort());
	    goodsClassFront.setSort(currentPos);
	    
	    goodsClassDaoHibernate.save(goodsClassCur);
        goodsClassDaoHibernate.save(goodsClassFront);
	}
    
    /**
     * Description : 把当前的商品分类向下移动一位。
     * Create Date: Jan 28, 2010 8:41:59 AM by liujf  Modified Date: Jan 28, 2010 8:41:59 AM by yucy
     * @param   
     * @return  
     * @Exception
     */
    public void moveDown(String goodsClassId) throws Exception {
        Assert.hasText(goodsClassId);
        
        GoodsClass goodsClassCur = this.goodsClassDaoHibernate.get(goodsClassId);
        GoodsClass goodsClassRear = this.getRearGoodsClass(goodsClassCur);
        
        /*挪动位置*/
        Long currentPos = goodsClassCur.getSort();
        goodsClassCur.setSort(goodsClassRear.getSort());
        goodsClassRear.setSort(currentPos);
        
        goodsClassDaoHibernate.save(goodsClassCur);
        goodsClassDaoHibernate.save(goodsClassRear);
    }

    /**
     * Description : 获得该父类下面的子类的下一个最大的编号。
     * Create Date: May 6, 2010 10:11:03 AM by liujf  Modified Date: May 6, 2010 10:11:03 AM by yucy
     * @param   
     * @return  
     * @Exception
     */
	public String getNextChildKeyCode(String firstCode, String parentId, String domian, String parentPropertyName, String targetPropertyName) throws Exception {
		String maxKeyCode = goodsClassDaoHibernate.getMaxChildKeyCodeByHQl(parentId, domian, parentPropertyName, targetPropertyName);
		if (parentId == null||"".equals(parentId)) parentId = firstCode;
		if (maxKeyCode == null||"".equals(maxKeyCode)) maxKeyCode = parentId + "00";
		
		String nextKeyCode = null;
		if(maxKeyCode.length()==1){
			int offset=(int)'B'-(int)'A';
			int v=maxKeyCode.charAt(0); 
			char lowercase=(char)(v+offset);
			nextKeyCode = ""+lowercase;
		}else{
			nextKeyCode = maxKeyCode.substring(maxKeyCode.length() - 2, maxKeyCode.length());
			Integer code = new Integer(nextKeyCode);
			code = code + 1;  
			nextKeyCode = parentId + StringUtils.constructCode(code, 2);
		}
		return nextKeyCode;
	}

	/** 
	 * Description :  首页需要的3级商品分类
	 * Create Date: 2010-4-17下午06:59:27 by wangsw  Modified Date: 2010-4-17下午06:59:27 by wangsw
	 * @return  3级分类
	 * @Exception   
	 */
	public List<GoodsClass> findGoodsClassForIndex(String nameFirstSpell) {
		return goodsClassDaoHibernate.findGoodsClassForIndex(nameFirstSpell);
	}

	/** 
	 * Description :  根据classCode取得商品分类
	 * Create Date: 2011-12-9下午02:47:55 by yucy  Modified Date: 2011-12-9下午02:47:55 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public GoodsClass getGoodsClassByClassCode(String classCode) throws Exception {
		return goodsClassDaoHibernate.getGoodsClassByClassCode( classCode );
	}
}
