package com.gpcsoft.goods.goodsprice.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsprice.dao.GoodsPriceSupplierDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceSupplier;
import com.gpcsoft.goods.goodsprice.manager.GoodsPriceSupplierManager;
import com.gpcsoft.goods.goodsprice.service.GoodsPriceSupplierService;

@Service 
public class GoodsPriceSupplierServiceImpl extends BaseGenericServiceImpl<GoodsPriceSupplier> implements GoodsPriceSupplierService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("goodsPriceSupplierManagerImpl")
	private GoodsPriceSupplierManager goodsPriceSupplierManager;
	
	@Autowired(required=true) @Qualifier("goodsPriceSupplierDaoHibernate")
	private GoodsPriceSupplierDao goodsPriceSupplierDao;
	
	@Autowired(required=true)  @Qualifier("goodsClassDaoHibernate")
	private GoodsClassDao goodsClassDao;

	/** 
	 * Description :  是否有供应商行情
	 * Create Date: 2010-4-15下午03:02:54 by yucy  Modified Date: 2010-4-15下午03:02:54 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public GoodsPriceSupplier getgoodsPriceSupplierBySupplier(Map<String, Object> param)throws Exception {
		return goodsPriceSupplierDao.getgoodsPriceSupplierBySupplier(param);
	}

	/** 
	 * Description :  显示供应商行情/禁止显示供应商行情
	 * Create Date: 2010-4-17上午12:31:27 by yucy  Modified Date: 2010-4-17上午12:31:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateShowStatus(Map<String, Object> param) throws Exception {
		return goodsPriceSupplierDao.updateShowStatus(param);
	}

	/** 
	 * Description :  判断供应商是否具有商圈会员角色
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isMember(Map<String,Object> param) throws Exception {
		return goodsPriceSupplierDao.isMember(param);
	}
	
	/** 
	 * Description :  获取供应商报价的商品列表
	 * Create Date: 2011-2-23上午10:50:45 by likg  Modified Date: 2011-2-23上午10:50:45 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Goods> getGoodsList(Page<Goods> page, Map<String, Object> param) throws Exception {
		return goodsPriceSupplierDao.getGoodsList(page, param);
	}

	/** 
	 * Description :  获取供应商报价的商品分类列表
	 * Create Date: 2011-2-23上午10:50:45 by likg  Modified Date: 2011-2-23上午10:50:45 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsClass> getGoodsClassList(Map<String, Object> param) throws Exception {
		List<GoodsClass> gcList = goodsPriceSupplierDao.getGoodsClassList(param);
		
		Set<String> firstIds = new HashSet<String>();  //存放一级分类id
		Set<String> secondIds = new HashSet<String>(); //存放二级分类id
		Set<String> thirdIds = new HashSet<String>();  //存放三级分类id
		for (GoodsClass gc : gcList) {
			String code = gc.getGoodsClassCode();
			firstIds.add(code.substring(0,3));
			if(code.length() > 4)
				secondIds.add(code.substring(0,5));
			if(code.length() > 6)
				thirdIds.add(code.substring(0,7));
		}
		
		//查找一级分类
		if(firstIds.size() > 0) {
			List<GoodsClass> firstList = goodsClassDao.findGoodsClassByCodes(firstIds);
			
			//查找二级分类
			if(secondIds.size() > 0) {
				List<GoodsClass> secondList = goodsClassDao.findGoodsClassByCodes(secondIds);
				
				//查找三级分类
				if(thirdIds.size() > 0) {
					List<GoodsClass> thirdList = goodsClassDao.findGoodsClassByCodes(thirdIds);
					
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
			
			gcList = firstList;
		} else {
			return null;
		}
		
		return gcList;
	}
	
}
