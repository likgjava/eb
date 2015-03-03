package com.gpcsoft.agreement.cart.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.cart.dao.ShoppingCartDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCart;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.core.dao.jdbc.BaseDaoJDBC;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

/** 
  *  Comments: <strong>GoodsDaoJDBC</strong>		
  *	 <br/>	商品的DAO JDBC实现																								
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司		
  *  <br/>Project:   srplatform					  
  *  <br/>Module ID: 权限平台 		
  *  <br/>Create Date：2010-3-14 上午03:45:33 by wangsw					
  *  <br/>Modified Date:  2010-3-14 上午03:45:33 by wangsw   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Repository
public class ShoppingCartDaoJdbc extends BaseDaoJDBC<ShoppingCart> implements ShoppingCartDao {

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.dao.ShoppingCartDao#findGoodsClassForShowGoods(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsClass> findGoodsClassForShowGoods(
			Map<String, Object> paramsMap) {
		String goodsClassId=(String) paramsMap.get("goodsClassId");//分类
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from GOODS_CLASS t ");
		sql.append(" where t.PARENT_CLASS_ID = ?");
		final List<GoodsClass> gcList=new ArrayList<GoodsClass>();
		this.getJdbcTemplate().query(
				sql.toString(),
                new Object[]{goodsClassId},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                    	GoodsClass gc=new GoodsClass();
                    	gc.setObjId(rs.getString("GOODS_CLASS_ID"));
                    	gc.setGoodsClassName(rs.getString("GOODS_CLASS_NAME"));
                        gcList.add(gc);
                    }
                });
		for (Iterator iterator = gcList.iterator(); iterator.hasNext();) {
			GoodsClass goodsClass = (GoodsClass) iterator.next();
			StringBuffer dsql = new StringBuffer();
			dsql.append("select count(1) from goods t ");
			dsql.append(" where t.goods_id in ( select GOODS_CODE from GOODS_PRICE ) ");//必须有行情
			dsql.append(" and t.goods_class_id in (select t.goods_class_id from goods_class t connect by prior t.goods_class_id = t.parent_class_id start with t.parent_class_id ='"+goodsClass.getObjId()+"' union select '"+goodsClass.getObjId()+"' from dual)");
			goodsClass.setSort(this.getJdbcTemplate().queryForLong(dsql.toString()));
		}
		return gcList;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.dao.ShoppingCartDao#findBrandForShowGoods(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsBrand> findBrandForShowGoods(Map<String, Object> paramsMap) {
		String categoryId=(String) paramsMap.get("categoryId");
		String goodsClassId=(String) paramsMap.get("goodsClassId");
		final List<GoodsBrand> brandList=new ArrayList<GoodsBrand>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct(t.goods_brand_id),t.brand_name from goods_brand t ,GOODS_BRAND_TO_CLASS t2,GOODS_CLASS_TO_CATEGORY t3 where t.goods_brand_id=t2.goods_brand_id and t2.goods_class_id=t3.goods_class_id ");
		if(categoryId!=null){
			sql.append(" and t3.pur_category_id ='").append(categoryId).append("'");
		}
		//递归大类下的所有分类
		if(goodsClassId!=null){
			sql.append(" and t2.goods_class_id in (select t.goods_class_id from goods_class t connect by prior t.goods_class_id = t.parent_class_id start with t.parent_class_id ='"+goodsClassId+"' union select '"+goodsClassId+"' from dual) ");
		}
		this.getJdbcTemplate().query(
				sql.toString(),
                new Object[]{},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                    	GoodsBrand gb=new GoodsBrand();
                    	gb.setObjId(rs.getString("goods_brand_id"));
                    	gb.setBrandName(rs.getString("brand_name"));
                    	brandList.add(gb);
                    }
                });
		for (Iterator iterator = brandList.iterator(); iterator.hasNext();) {
			GoodsBrand goodsBrand = (GoodsBrand) iterator.next();
			StringBuffer dsql = new StringBuffer();
			dsql.append("select count(1) from goods t ");
			dsql.append(" where t.goods_id in ( select GOODS_CODE from GOODS_PRICE ) ");//必须有行情
			dsql.append(" and t.goods_brand_id='").append(goodsBrand.getObjId()).append("'");
			if(goodsClassId!=null){
				dsql.append(" and t.goods_class_id in (select t.goods_class_id from goods_class t connect by prior t.goods_class_id = t.parent_class_id start with t.parent_class_id ='"+goodsClassId+"' union select '"+goodsClassId+"' from dual)");
			}
			if(categoryId!=null){
				sql.append(" and  t.goods_class_id in (select y.goods_class_id from goods_class_to_category y where y.pur_category_id='").append(categoryId).append("')");
			}
			//goodsBrand.setGoodsTotal(this.getJdbcTemplate().queryForLong(dsql.toString())); // by yucy
			
		}
		return brandList;
	}
	public List<OrderItem> findHotSellGoods() {
		return null;
	}

	public List<ShoppingCartItem> findMyShoppingCar(
			Map<String, Object> paramsMap) {
		return null;
	}

	public ShoppingCart findShoppingCartByOrgInfoId(Map<String, Object> paramsMap) {
		return null;
	}

	public List<GoodsBrand> getBrandByGoodsClass(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<GoodsClass> getGoodsClassAndChild(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeAllByOrgInfo(Map<String, Object> paramsMap)throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void updateChangeQtyAndMoney(ShoppingCartItem cartItem) {
		// TODO Auto-generated method stub
		
	}

	public void updateSortChatAndItemQytAndMoney(String objId) {
		// TODO Auto-generated method stub
		
	}

	public List<ShoppingCartItem> getShoppingCarItem(
			Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
