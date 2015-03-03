package com.gpcsoft.goods.goodsclass.service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassCategoryDao;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassCategory;
import com.gpcsoft.goods.goodsclass.manager.GoodsClassCategoryManager;
import com.gpcsoft.goods.goodsclass.service.GoodsClassCategoryService;

@Service 
public class GoodsClassCategoryServiceImpl extends BaseGenericServiceImpl<GoodsClassCategory> implements GoodsClassCategoryService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("goodsClassCategoryManagerImpl")
	private GoodsClassCategoryManager goodsClassCategoryManager;
	
	@Autowired(required=true) @Qualifier("goodsClassCategoryDaoHibernate")
	private GoodsClassCategoryDao goodsClassCategoryDaoHibernate;
	@Autowired(required=true) @Qualifier("goodsClassDaoHibernate")
	private GoodsClassDao goodsClassDaoHibernate;
	
	/** 
	 * Description :  根据品目获得商品分类的信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	public List<GoodsClass> getClassListByCategory(String categoryId,String categoryCode,Boolean isLeaf) throws Exception {
		return goodsClassCategoryDaoHibernate.getClassListByCategory(categoryId, categoryCode,isLeaf);
	}
	
	/** 
	 * Description :  根据品获取分类集合(jdbc查询方式)
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getClassByCategory(String categoryId,String categoryCode,Boolean isLeaf) throws Exception {
		return goodsClassCategoryDaoHibernate.getClassByCategory(categoryId, categoryCode,isLeaf);
	}
	
	/** 
	 * Description :  根据品目获得商品分类的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getBrandsListShowByClass(String categoryId,String categoryCode,Boolean isLeaf) throws Exception {
		return goodsClassCategoryDaoHibernate.getBrandsListShowByClass(categoryId, categoryCode, isLeaf);
	}
	

	/** 
	 * Description : 根据品牌获取还未指定维护供应商的商品分类 
	 * Create Date: 2010-8-6上午09:18:06 by guoyr  Modified Date: 2010-8-6上午09:18:06 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsClass> getUnSpecifiedGoodsClass(String objId,String goodsBrandId) {
		List<String[]> list = new ArrayList<String[]>();
		list = goodsClassCategoryDaoHibernate.getUnSpecifiedGoodsClassByBrandId(goodsBrandId);
		Set<String> goodsClassSet = new HashSet<String>();
		List<GoodsClass> goodsClassList = new ArrayList<GoodsClass>();
		for(Object[] obj : list){
			GoodsClass goodsClass = new GoodsClass();
			goodsClass.setObjId((String) obj[2]);
			goodsClass.setGoodsClassName((String) obj[3]);
			
			// 取掉重覆的分类
			if(goodsClassSet.contains((String) obj[2])) {
				continue;
			}
			goodsClassList.add(goodsClass);
			goodsClassSet.add((String) obj[2]);
		}
		
		// 如果是修改商品的指定供应商,因为列表中已经过滤了已指定的商品分类,所以当列表中没有该分类时手动把该分类添加到列表中
		if(null != objId && !"".equals(objId) && !goodsClassSet.contains(objId)) {
			goodsClassList.add(goodsClassDaoHibernate.get(objId));
		}
		return goodsClassList;
	}
	
	/** 
	 * Description :  根据分类获取品目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> getAssignedPurcategory(String classId) throws Exception {
		List<String[]> list = new ArrayList<String[]>();
		list = goodsClassCategoryDaoHibernate.getAssignedPurcategory(classId);
		List<PurCategory> purCategoryList = new ArrayList<PurCategory>();
		
		for(Object object : list){
			Object[] obj = (Object[])object;
			PurCategory purCategory = new PurCategory();
			purCategory.setObjId((String) obj[0]);
			purCategory.setCategoryName((String) obj[1]);
			purCategoryList.add(purCategory);
		}
		return purCategoryList;
	}
}
