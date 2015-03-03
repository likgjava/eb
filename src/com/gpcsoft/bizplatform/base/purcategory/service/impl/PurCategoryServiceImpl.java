package com.gpcsoft.bizplatform.base.purcategory.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.ComparatorPurCategory;
import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.base.purcategory.dao.PurCategoryDao;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.manager.PurCategoryManager;
import com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

import edu.emory.mathcs.backport.java.util.Collections;

@Service 
public class PurCategoryServiceImpl extends BaseGenericServiceImpl<PurCategory> implements PurCategoryService {

	@Autowired(required=true) @Qualifier("purCategoryManagerImpl")
	private PurCategoryManager purCategoryManager;
	@Autowired(required=true)  @Qualifier("purCategoryDaoHibernate")
	private PurCategoryDao purCategoryDaoHibernate;
	
	/**
    * 
    * Description :  获取采购品目
    * Create Date: 2010-1-18上午09:43:38 by xiaogh  Modified Date: 2010-1-18上午09:43:38 by xiaogh
    * @param   categoryCode
    * @return  List<PurCategory>
    * @Exception
    */
	public List<PurCategory>  getListPurCategory(){
		List<PurCategory> pList = purCategoryManager.getPurCategory(null, true);
		ComparatorPurCategory comparatorPurCategory=new ComparatorPurCategory();
		Collections.sort(pList,comparatorPurCategory);
		return pList;
	}
	 
	 /** 
	 * Description :  首页需要的采购品目3级
	 * Create Date: 2010-7-15下午02:59:17 by liangxj  Modified Date: 2010-7-15下午02:59:17 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> findPurCategoryForIndex(String nameFirstSpell){
		return purCategoryManager.findPurCategoryForIndex(nameFirstSpell);
	}
	
	/** 
	 * Description :  首页需要的品目分类
	 * Create Date: 2011-2-18下午05:42:06 by sunl  Modified Date: 2011-2-18下午05:42:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> findGoodsCategoryForHasGoods(String nameFirstSpell) {

		List<PurCategory> list = purCategoryDaoHibernate.findGoodsCategoryForHasGoods(nameFirstSpell);
		
		if(nameFirstSpell != null){
			return list;
		}else {
			Set<String> firstIds = new HashSet<String>();   //存放一级分类id
			Set<String> secondIds = new HashSet<String>();   //存放二级分类id
			Set<String> thirdIds = new HashSet<String>();   //存放三级分类id
			for (PurCategory gc : list) {
				String code = gc.getCategoryCode();
				firstIds.add(code.substring(0,1));
				if(code.length() > 1)
					secondIds.add(code.substring(0,3));
				if(code.length() > 4)
					thirdIds.add(code.substring(0,5));
			}
			
			//查找一级品目
			if(firstIds.size() > 0) {
				List<PurCategory> firstList = purCategoryDaoHibernate.findGoodsCategoryByCodes(firstIds);
				
				//查找二级品目
				if(secondIds.size() > 0) {
					List<PurCategory> secondList = purCategoryDaoHibernate.findGoodsCategoryByCodes(secondIds);
					
					//查找三级品目
					if(thirdIds.size() > 0) {
						List<PurCategory> thirdList = purCategoryDaoHibernate.findGoodsCategoryByCodes(thirdIds);
						
						//将三级品目作为子品目放到二级品目里面
						for (PurCategory tGoodsCategory : thirdList) {
							for (PurCategory sGoodsCategory : secondList) {
								//当三级的上级id与二级的id相同时，将这个三级作为子品目放入二级的下级集合中
								if(sGoodsCategory.getObjId().equals(tGoodsCategory.getRemarks())){
									sGoodsCategory.getChildren().add(tGoodsCategory);
									break;
								}
							}
						}
					}
					
					//将二级品目作为子品目放到一级品目里面
					for (PurCategory sGoodsCategory : secondList) {
						for (PurCategory fGoodsCategory : firstList) {
							//当二级的上级id与一级的id相同时，将这个二级作为子品目放入一级的下级集合中
							if(fGoodsCategory.getObjId().equals(sGoodsCategory.getRemarks())){
								fGoodsCategory.getChildren().add(sGoodsCategory);
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

	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService#savePurCategory(com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory)
	 */
	public PurCategory savePurCategory(PurCategory purCategory) throws Exception{
		
		boolean isNew = (purCategory.getObjId()==null);
		
		// 设置拼音简写
		WordToSpell spell = new WordToSpell();
		purCategory.setShortSpellName(spell.String2Alpha(purCategory.getCategoryName())); 
		
		if( null == purCategory.getObjId() || "".equals(purCategory.getObjId())) {
			purCategory.setIsLeaf(true);
			
			//指定主键和 code by yucy
			String objId = purCategoryManager.getNextChildKeyCode("A", (purCategory.getParent() == null ? null:purCategory.getParent().getObjId()), "PurCategory", "parent", "objId");
			purCategory.setObjId(objId);
			purCategory.setCategoryCode(objId);
		}
		
		//保存
		if(isNew) {
        	purCategoryDaoHibernate.getHibernateTemplate().save(purCategory);
    	} else {
    		purCategoryDaoHibernate.save(purCategory);
    	}
		
		// 保存时将父节点改为非叶子结点
		if(null != purCategory.getParent()) {
			purCategoryDaoHibernate.updatePurCategoryIsLeaf(purCategory.getParent().getObjId(), false);
		}
		return purCategory;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService#removePurCategory(java.lang.String)
	 */
	public void removePurCategory(String objId) throws Exception {
		// 解决缓存问题 
		PurCategory purCategory = purCategoryDaoHibernate.getHibernateTemplate().get(PurCategory.class, objId);
		// 删除时如果该结果的父结点下已经只有1个子结点，则将父资质改为叶子结点
		if(purCategoryDaoHibernate.getSubPurCategoryCount(objId) <= 1 && null != purCategory.getParent()) {
			purCategoryDaoHibernate.updatePurCategoryIsLeaf(purCategory.getParent().getObjId(), true);
		}
		
		try {
			purCategoryDaoHibernate.remove(purCategory);
		} catch (Exception e) {
			throw new Exception("该品目被其它数据引用，不能删除！");
		}
	}

	/** 
	 * Description :  分级获得品目
	 * Create Date: 2011-3-18下午05:39:26 by yucy  Modified Date: 2011-3-18下午05:39:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> getCategorysByLevel(Map<String, Object> param) throws Exception {
		return purCategoryDaoHibernate.getCategorysByLevel(param);
	}

	/** 
	 * Description :  按关键字获得品目
	 * Create Date: 2011-3-22下午02:31:46 by yucy  Modified Date: 2011-3-22下午02:31:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> getCategorysByKeyWords(Map<String, Object> param) throws Exception {
		return purCategoryDaoHibernate.getCategorysByKeyWords(param);
	}
	
	/** 
	 * Description :  根据经营范围code获取品目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getCategoryByMainProducts(String bidForRangeCodes) throws Exception {
		return purCategoryDaoHibernate.getCategoryByMainProducts(bidForRangeCodes);
	}
}

