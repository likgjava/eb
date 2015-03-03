package com.gpcsoft.goods.goodsprice.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goodsprice.dao.GoodsPriceDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceSupplier;
import com.gpcsoft.goods.goodsprice.manager.GoodsOptFitPriceManager;
import com.gpcsoft.goods.goodsprice.manager.GoodsPriceManager;
import com.gpcsoft.goods.goodsprice.manager.GoodsPriceProcessManager;
import com.gpcsoft.goods.goodsprice.manager.GoodsPriceSupplierManager;
import com.gpcsoft.goods.goodsprice.service.GoodsPriceService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Service 
public class GoodsPriceServiceImpl extends BaseGenericServiceImpl<GoodsPrice> implements GoodsPriceService {

	@Autowired(required=true) @Qualifier("goodsPriceManagerImpl")
	private GoodsPriceManager goodsPriceManager;
	
	@Autowired(required=true) @Qualifier("goodsPriceDaoHibernate")
	private GoodsPriceDao goodsPriceDaoHibernate;
	
	@Autowired(required=true) @Qualifier("goodsOptFitPriceManagerImpl")
	private GoodsOptFitPriceManager goodsOptFitPriceManager;
	
	@Autowired(required=true) @Qualifier("goodsPriceSupplierManagerImpl")
	private GoodsPriceSupplierManager goodsPriceSupplierManager;
	
	@Autowired(required=true) @Qualifier("goodsPriceProcessManagerImpl")
	private GoodsPriceProcessManager goodsPriceProcessManager;

	/** 
	 * Description : 保存行情 
	 * Create Date: 2010-4-15下午01:34:22 by yucy  Modified Date: 2010-4-15下午01:34:22 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean savePrice(Map<String, Object> param) throws Exception {
		
		//先判断该供应是否有所属行情
		if(null==((GoodsPrice)param.get("goodsPrice")).getGoodsPriceSupplier()){
			GoodsPriceSupplier goodsPriceSupplier  = goodsPriceSupplierManager.saveSupplierPrice(param);
			((GoodsPrice)param.get("goodsPrice")).setGoodsPriceSupplier(goodsPriceSupplier);
		}
		//保存商品行情
		GoodsPrice goodsPrice = goodsPriceManager.savePrice(param);
		param.put("goodsPrice", goodsPrice);
		
		//保存选配行情
		goodsOptFitPriceManager.saveOptFitPrice(param);
		
		return true;
	}
	
	/** 
	 * Description :  审核行情
	 * Create Date: 2010-4-16下午02:29:44 by yucy  Modified Date: 2010-4-16下午02:29:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateAuditStatus(Map<String, Object> param) throws Exception {
		
		GoodsPrice goodsPrice = (GoodsPrice)param.get("goodsPrice");
		String auditType =(String)param.get("auditType");
		//通过
		if("pass".equals(auditType)){
			goodsPrice.setAuditStatus(GoodsEnum.PASS_EXAM);
			goodsPrice.setUseStatus(GoodsEnum.USE_VALID);
			
			//调用行情统计单元接口
			goodsPriceProcessManager.saveProcessByPrice(goodsPrice);
		}
		//不通过
		else if("nopass".equals(auditType)){
			goodsPrice.setAuditStatus(GoodsEnum.NO_PASS_EXAM);
		}
		goodsPrice.setVerifyTime(new Date());
		goodsPrice.setVerifyUser(AuthenticationHelper.getCurrentUser(true));
		
		GoodsPrice result = goodsPriceDaoHibernate.save(goodsPrice);
		return null!=result?true:false;
	}

	/** 
	 * Description :  禁用行情
	 * Create Date: 2010-4-16下午05:43:12 by yucy  Modified Date: 2010-4-16下午05:43:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateStopStatus(Map<String, Object> param) throws Exception {
		return goodsPriceDaoHibernate.updateStopStatus(param);
	}

	/** 
	 * Description :  删除行情
	 * Create Date: 2010-4-17上午09:55:50 by yucy  Modified Date: 2010-4-17上午09:55:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean deletePrice(Map<String, Object> param) throws Exception {
		return goodsPriceDaoHibernate.deletePrice(param);
	}
	/** 
	 * Description : 获得商品的行情 
	 * Create Date: 2010-9-16下午04:17:23 by guoyr  Modified Date: 2010-9-16下午04:17:23 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsPrice> getGoodsPriceList(Map<String, Object> param) throws Exception{

//		List<GoodsPrice> list = goodsPriceDaoHibernate.getGoodsPriceList(param);
//		List<GoodsPrice> goodsPriceList = new ArrayList<GoodsPrice>();
//		if(null != list && list.size() > 0) {
//			goodsPriceList.add(list.get(0));
//			for(int i = 1; i < list.size(); i++){
//				GoodsPrice g = list.get(i);
//				for(GoodsPrice o : goodsPriceList){
//					// 如果有相同的供应商报价
//					if(o.getGoodsPriceSupplier().getSupplier().getObjId().equals(g.getGoodsPriceSupplier().getSupplier().getObjId())){
//						
//					}else {
//						goodsPriceList.add(g);
//					}
//				}
//			}
//		}
		return goodsPriceDaoHibernate.getGoodsPriceList(param);
	}

	/** 
	 * Description :  取得行情的热门区域和相应的值
	 * Create Date: 2010-10-8下午02:58:53 by yucy  Modified Date: 2010-10-8下午02:58:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getHotTagAndVelue(Map<String, Object> params) throws Exception{
		return goodsPriceDaoHibernate.getHotTagAndVelue(params);
	}

	/** 
	 * Description :  取得销售额和销售量的图形
	 * Create Date: 2010-10-9下午05:01:34 by yucy  Modified Date: 2010-10-9下午05:01:34 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ListOrderedMap> getGoodsSalesChart(Map<String, Object> param) throws Exception{
		return goodsPriceDaoHibernate.getGoodsSalesChartData(param);
	}
	
	
    public List<OrgInfo> getProvideSupplierByGoods(String goodsIds)throws Exception{
    	return goodsPriceManager.getProvideSupplierByGoods(goodsIds);
    }

	/** 
	 * Description :  取的相应区域和相应的值
	 * Create Date: 2010-11-3下午02:24:40 by yucy  Modified Date: 2010-11-3下午02:24:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getHotTagAndVelueByDistrict(Map<String, Object> param) throws Exception {
		return goodsPriceDaoHibernate.getHotTagAndVelueByDistrict(param);
	}

	/** 
	 * Description :  取得所有的行情区域和值
	 * Create Date: 2010-11-4上午10:16:23 by yucy  Modified Date: 2010-11-4上午10:16:23 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getAllPriceAndValue(Map<String, Object> param) throws Exception {
		return goodsPriceDaoHibernate.getAllPriceAndValue(param);
	}
}
