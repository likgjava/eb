package com.gpcsoft.goods.goodsclass.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassDao;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassParamTypeDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassCategory;
import com.gpcsoft.goods.goodsclass.manager.GoodsClassCategoryManager;
import com.gpcsoft.goods.goodsclass.manager.GoodsClassManager;
import com.gpcsoft.goods.goodsclass.service.GoodsClassService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class GoodsClassServiceImpl extends BaseGenericServiceImpl<GoodsClass> implements GoodsClassService {

	@Autowired(required=true) @Qualifier("goodsClassManagerImpl")
	private GoodsClassManager goodsClassManager;
	
	@Autowired(required=true) @Qualifier("goodsClassCategoryManagerImpl")
	private GoodsClassCategoryManager goodsClassCategoryManagerImpl;
	
	@Autowired(required=true)  @Qualifier("goodsClassDaoHibernate")
	private GoodsClassDao goodsClassDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("goodsClassParamTypeDaoHibernate")
	private GoodsClassParamTypeDao goodsClassParamTypeDao;
	
	
	
	/**
     * Description : 删除指定的商品分类。
     * Create Date: Jan 27, 2010 1:19:06 PM by liujf  Modified Date: Jan 27, 2010 1:19:06 PM by yucy
     * @param   
     * @return  
     * @Exception
     */
    public void removeGoodsClass(String goodsClassId) throws Exception {
	    Assert.hasText(goodsClassId);
	    GoodsClass goodsClass = goodsClassDaoHibernate.get(goodsClassId);
	    
	    /*判断是否是叶子结点，如果不是叶子结点，就不允许进行删除操作。*/
	    if (!isLeaf(goodsClassId)) {
	        throw new Exception("在删除该商品分类之前， 请先删除其下级分类！");
	    }
	    
	    if (goodsClass.getParentClazz() != null && goodsClass.getParentClazz().getObjId() != null) {
	    	/*判断这个分类的父类是否只有一个孩子结点，如果没有，就把这个分类的父类置为孩子结点。*/
		    boolean isMoreThanOnChildFlag = isMoreThanOneChild(goodsClass.getParentClazz().getObjId());
		    if (!isMoreThanOnChildFlag) {
	    		GoodsClass parentClass = this.get(goodsClass.getParentClazz().getObjId());
	    		parentClass.setIsLeaf(true);
		    }
	    }
	    this.remove(goodsClass.getObjId(),GoodsClass.class);
    }
    
	/**
	 * Description : 判断该结点是否为孩子结点。
	 * Create Date: Jan 27, 2010 1:29:48 PM by liujf  Modified Date: Jan 27, 2010 1:29:48 PM by liujf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isLeaf(String goodsClassId) throws Exception {
        Assert.hasText(goodsClassId);
        
	    List<GoodsClass> goodsClassesList = goodsClassDaoHibernate.findChildGoodsClassesListByGoodsClassId(goodsClassId);
	    if (goodsClassesList == null || goodsClassesList.isEmpty()) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	/**
	 * Description : 判断该结点是否拥有多于一个的孩子结点。
	 * Create Date: Jan 27, 2010 1:29:48 PM by liujf  Modified Date: Jan 27, 2010 1:29:48 PM by liujf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isMoreThanOneChild(String goodsClassId) throws Exception {
        Assert.hasText(goodsClassId);
        
	    List<GoodsClass> goodsClassesList = goodsClassDaoHibernate.findChildGoodsClassesListByGoodsClassId(goodsClassId);
	    if (goodsClassesList != null && goodsClassesList.size() > 1) {
	        return true;
	    } else {
	        return false;
	    }
	}
    
    /** 
     * Description :保存商品分类  
     * Create Date: 2010-7-28下午04:50:00 by yucy  Modified Date: 2010-7-28下午04:50:00 by yucy
     * @param   分类,名目id
     * @return  
     * @Exception   
     */
    public void saveGoodsClass(GoodsClass goodsClass,String purCategoryIds) throws Exception {
    	
    	Assert.notNull(goodsClass);
        Assert.notNull(goodsClass.getGoodsClassCode());
        Assert.notNull(goodsClass.getGoodsClassName());
        /*判断商品分类编号和名称是否已经存在*/
        String parentId = null;
    	parentId = goodsClass.getParentClazz() == null ? null:goodsClass.getParentClazz().getObjId();
    	boolean isExit = goodsClassManager.checkNameIsExit(goodsClass.getObjId(), parentId, goodsClass.getGoodsClassName(), goodsClass.getGoodsClassCode());
        if (isExit) {
            throw new UnsupportedOperationException("相同名称或编号的商品分类在这个分类下已经存在！");
        }
        if (goodsClass.getParentClazz() != null && "-1".equals(goodsClass.getParentClazz().getObjId())) {
        	/*首次创建结点的时候，树是默认为-1给根节点的，所以要置为空*/
        	goodsClass.setParentClazz(null);
        }

        Date currentDate = new Date();
        User currentUser = AuthenticationHelper.getCurrentUser(true);
        boolean isNew = false;
        
        //拼音简码
        WordToSpell spell = new WordToSpell();
        goodsClass.setShortSpellName(spell.String2Alpha(goodsClass.getGoodsClassName()));
        
        if (goodsClass.getObjId() == null) {
        	//拼装品目分类中间对象
        	Set<GoodsClassCategory> goodsClassCategorySet = new HashSet<GoodsClassCategory>();
        	if(null!=purCategoryIds&&!"".equals(purCategoryIds)){
        		String[] purCategoryIdArray = purCategoryIds.split(",");
        		for (String string : purCategoryIdArray) {
        			GoodsClassCategory goodsClassCategory = new GoodsClassCategory();
        			PurCategory purCategory = new PurCategory();
        			purCategory.setObjId(string);
        			goodsClassCategory.setPurCategory(purCategory);
        			goodsClassCategory.setGoodsClass(goodsClass);
        			goodsClassCategorySet.add(goodsClassCategory);
    			}
        	}
        	//拼装完毕回设
        	goodsClass.setGoodsClassCategorySet(goodsClassCategorySet);
        	
        	String objId = null;
        	isNew = true;
        	if ("-1".equals(parentId)) {
        		objId = goodsClassManager.getNextChildKeyCode("A", null, "GoodsClass", "parentClazz", "objId");
        	} else {
        		objId = goodsClassManager.getNextChildKeyCode("A", parentId, "GoodsClass", "parentClazz", "objId");
        	}
        	goodsClass.setObjId(objId);
        
            /*获得同一级分类的最大顺序号sortNo 只有当新增的时候才需要取这个值。*/
        	parentId = goodsClass.getParentClazz() == null ? null:goodsClass.getParentClazz().getObjId();
        	int curMaxSortNo = goodsClassManager.getMaxSortNoByParentClassId(parentId);
            goodsClass.setSort(new Long(curMaxSortNo + 1));
            goodsClass.setCreateTime(currentDate);
            goodsClass.setCreateUser(currentUser);
            goodsClass.setModifyTime(currentDate);
            goodsClass.setModifier(currentUser);
            goodsClass.setIsLeaf(true);
        } else {
        	//先删除原先的分类品目关系
        	goodsClassCategoryManagerImpl.deleteGoodsClassCategoryByClassId(goodsClass.getObjId());
        	//拼装品目分类中间对象
        	Set<GoodsClassCategory> goodsClassCategorySet = new HashSet<GoodsClassCategory>();
        	if(null!=purCategoryIds&&!"".equals(purCategoryIds)){
        		String[] purCategoryIdArray = purCategoryIds.split(",");
        		for (String string : purCategoryIdArray) {
        			GoodsClassCategory goodsClassCategory = new GoodsClassCategory();
        			PurCategory purCategory = new PurCategory();
        			purCategory.setObjId(string);
        			goodsClassCategory.setPurCategory(purCategory);
        			goodsClassCategory.setGoodsClass(goodsClass);
        			goodsClassCategorySet.add(goodsClassCategory);
    			}
        	}
        	//拼装完毕回设
        	goodsClass.setGoodsClassCategorySet(goodsClassCategorySet);
        	
            goodsClass.setModifyTime(currentDate);
            goodsClass.setModifier(currentUser);
        }
        
        if (goodsClass.getParentClazz() != null) {
    		/*根节点是没有父节点的，所以要判断空指针的异常*/
    		GoodsClass parentClass = this.get(goodsClass.getParentClazz().getObjId());
    		parentClass.setIsLeaf(false);
    	}
        if (isNew) {
        	this.goodsClassDaoHibernate.getHibernateTemplate().save(goodsClass);
    	} else {
    		this.save(goodsClass);
    	}
    }

    /**
     * Description : 查看商品分类的详细信息。
     * Create Date: Jan 27, 2010 7:59:14 PM by yucy  Modified Date: Jan 27, 2010 7:59:14 PM by yucy
     * @param   
     * @return  
     * @Exception
     */
    public Map<String,Object> getGoodsClassDetail(String goodsClassId) throws Exception {
    	Map<String , Object> goodsClassInfoMap = new HashMap<String, Object>();
    	GoodsClass goodsClass = goodsClassDaoHibernate.findGoodsClassByObjId(goodsClassId);
    	if (goodsClass == null) return null;
  
    	if (goodsClass.getGoodsClassCategorySet() != null && !goodsClass.getGoodsClassCategorySet().isEmpty()) {
    		StringBuilder purCategoryIds = new StringBuilder();
    		StringBuilder purCategoryNames = new StringBuilder();
    		
    		List<GoodsClassCategory> list = new ArrayList<GoodsClassCategory>();
    		list.addAll(goodsClass.getGoodsClassCategorySet());
    		
    		for (int i = 0; i < list.size(); i++) {
				if(i==0){
					purCategoryIds.append(list.get(i).getPurCategory().getObjId());
	    			purCategoryNames.append(list.get(i).getPurCategory().getCategoryName());
				}else {
					purCategoryIds.append(","+list.get(i).getPurCategory().getObjId());
	    			purCategoryNames.append(","+list.get(i).getPurCategory().getCategoryName());
				}
			}
    		
    		goodsClassInfoMap.put("purCategoryIds", purCategoryIds.toString());
    		goodsClassInfoMap.put("purCategoryNames", purCategoryNames.toString());
    	}
    	goodsClassInfoMap.put("goodsClass", goodsClass);
  		return goodsClassInfoMap;
    }

	/** 
	 * Description :  首页需要的3级商品分类
	 * Create Date: 2010-4-17下午06:59:27 by wangsw  Modified Date: 2010-4-17下午06:59:27 by wangsw
	 * @return  3级分类
	 * @Exception   
	 */
	public List<GoodsClass> findGoodsClassForIndex(String nameFirstSpell) {
		return goodsClassManager.findGoodsClassForIndex(nameFirstSpell);
	}

	/** 
	 * Description :  取得分配的分类编号
	 * Create Date: 2010-7-28下午05:21:27 by yucy  Modified Date: 2010-7-28下午05:21:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getGoodsClassCode(String parentClassId) throws Exception{
		return goodsClassManager.getNextChildKeyCode("A", parentClassId, "GoodsClass", "parentClazz", "objId");
	}
	
	/** 
	 * Description :  根据商品分类code获得商品分类
	 * Create Date: 2010-8-18上午11:13:27 by liangxj  Modified Date: 2010-8-18上午11:13:27 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public GoodsClass getGoodsClassByCode(String goodsClassCode) throws Exception {
		QueryObject<GoodsClass> query=new QueryObjectBase<GoodsClass>();
		query.setEntityClass(GoodsClass.class);
		query.setQueryParam(new QueryParam("goodsClassCode",QueryParam.OPERATOR_EQ,goodsClassCode));
    	List<GoodsClass> list = goodsClassDaoHibernate.findByQuery(query);
    	
    	if(list != null && list.size() > 0) {
    		return list.get(0);
    	}
    	else
    		return null;
	}

	@Override
	protected void onBeforeRemove(String classId){
		try {
			goodsClassParamTypeDao.deleteByGoodsClassId(classId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Description :  首页需要的商品分类,只查询有商品的
	 * Create Date: 2010-12-17下午02:51:55 by liangxj  Modified Date: 2010-12-17下午02:51:55 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsClass> findGoodsClassForHasGoods(String nameFirstSpell) {
		List<GoodsClass> list = goodsClassDaoHibernate.findGoodsClassForHasGoods(nameFirstSpell);
		
		if(nameFirstSpell != null){
			return list;
		}else {
			Set<String> firstIds = new HashSet<String>();   //存放一级分类id
			Set<String> secondIds = new HashSet<String>();   //存放二级分类id
			Set<String> thirdIds = new HashSet<String>();   //存放三级分类id
			for (GoodsClass gc : list) {
				String code = gc.getGoodsClassCode();
				firstIds.add(code.substring(0,3));
				if(code.length() > 4)
					secondIds.add(code.substring(0,5));
				if(code.length() > 6)
					thirdIds.add(code.substring(0,7));
			}
			
			//查找一级分类
			if(firstIds.size() > 0) {
				List<GoodsClass> firstList = goodsClassDaoHibernate.findGoodsClassByCodes(firstIds);
				
				//查找二级分类
				if(secondIds.size() > 0) {
					List<GoodsClass> secondList = goodsClassDaoHibernate.findGoodsClassByCodes(secondIds);
					
					//查找三级分类
					if(thirdIds.size() > 0) {
						List<GoodsClass> thirdList = goodsClassDaoHibernate.findGoodsClassByCodes(thirdIds);
						
						//将三级分类作为子分类放到二级分类里面
						for (GoodsClass tGoodsClass : thirdList) {
							for (GoodsClass sGoodsClass : secondList) {
								//当三级的上级id与二级的id相同时，将这个三级作为子分类放入二级的下级集合中
								if(sGoodsClass.getObjId().equals(tGoodsClass.getRemarks())){
									sGoodsClass.getChildren().add(tGoodsClass);
									break;
								}
							}
						}
					}
					
					//将二级分类作为子分类放到一级分类里面
					for (GoodsClass sGoodsClass : secondList) {
						for (GoodsClass fGoodsClass : firstList) {
							//当二级的上级id与一级的id相同时，将这个二级作为子分类放入一级的下级集合中
							if(fGoodsClass.getObjId().equals(sGoodsClass.getRemarks())){
								fGoodsClass.getChildren().add(sGoodsClass);
								break;
							}
						}
					}
				}
				
				list = firstList;
			}
			else return null;
		}
		return list;
	}
}
