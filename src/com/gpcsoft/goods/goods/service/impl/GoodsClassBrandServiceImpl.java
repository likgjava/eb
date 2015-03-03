package com.gpcsoft.goods.goods.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goods.dao.GoodsClassBrandDao;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsClassBrand;
import com.gpcsoft.goods.goods.service.GoodsClassBrandService;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

@Service 
public class GoodsClassBrandServiceImpl extends BaseGenericServiceImpl<GoodsClassBrand> implements GoodsClassBrandService {
	
	@Autowired(required=true) @Qualifier("goodsClassBrandDaoHibernate")
	private GoodsClassBrandDao goodsClassBrandDaoHibernate;
	
	/** 
	 * Description :  根据品目获得商品品牌的信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号
	 * @return  
	 * @Exception   
	 */
	public List<GoodsBrand> getBrandListByCategory(String categoryId,String categoryCode) throws Exception {
		return goodsClassBrandDaoHibernate.getBrandListByCategory(categoryId, categoryCode);
	}
	
	/** 
	 * Description :  根据商品分类获得商品品牌的信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   classId 分类id，classCode 分类编号
	 * @return  
	 * @Exception   
	 */
	public List<GoodsBrand> getBrandsListByClass(String classId,String classCode) throws Exception {
		return goodsClassBrandDaoHibernate.getBrandsListByClass(classId, classCode);
	}
	
	/** 
	 * Description :  获取指定维护供应商的产品分类 
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsClass> getAssignedGoodsClass(String brandId,String orgInfoId) throws Exception {
		List<String[]> list = new ArrayList<String[]>();
		list = goodsClassBrandDaoHibernate.getAssignedGoodsClass(brandId,orgInfoId);
		List<GoodsClass> goodsClassList = new ArrayList<GoodsClass>();
		
		for(Object[] obj : list){
			GoodsClass goodsClass = new GoodsClass();
			goodsClass.setObjId((String) obj[0]);
			goodsClass.setGoodsClassName((String) obj[1]);
			goodsClassList.add(goodsClass);
		}
		return goodsClassList;
	}

	/** 
	 * Description :  获得分类信息(根据品牌)
	 * Create Date: 2011-1-18下午04:40:21 by yucy  Modified Date: 2011-1-18下午04:40:21 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsClass> getClassListByBrand(String brandId)	throws Exception {
		return goodsClassBrandDaoHibernate.getClassListByBrand(brandId);
	}
}
