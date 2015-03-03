package com.gpcsoft.agreement.cart.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.cart.dao.ShoppingCartDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCart;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

@Repository
public class ShoppingCartDaoHibernate extends BaseGenericDaoHibernate<ShoppingCart> implements ShoppingCartDao {

	/** 
	 * Description : 我的购物车 
	 * Create Date: 2010-4-9下午04:53:30 by wangsw  Modified Date: 2010-4-9下午04:53:30 by yucy
	 * @param paramsMap 
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<ShoppingCartItem> findMyShoppingCar(Map<String, Object> paramsMap) {
		StringBuilder hql=new StringBuilder();
		
		hql.append(" select c from ShoppingCartItem c "); 
		
		hql.append(" where ");
		hql.append(" c.shoppingCart.buyer.objId= ? and c.shoppingCart.createUser.objId = ? order by c.supplier ");
		List<ShoppingCartItem> cartItemList = this.getHibernateCacheTemplate().find(
				hql.toString(), new Object[]{(String)paramsMap.get("orgInfoId"),(String)paramsMap.get("userId")});
		return cartItemList;
	}
	
	/** 
	 * Description :  根据购物车ITEM的id和orginfoid获得购物车条目
	 * Create Date: 2010-10-9上午09:50:30 by sunl  Modified Date: 2010-10-9上午09:50:30 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<ShoppingCartItem> getShoppingCarItem(Map<String, Object> paramsMap) {
		StringBuilder hql=new StringBuilder();
		
		hql.append("from ShoppingCartItem c ");
		hql.append(" where c.objId in (:objId)");
		Query query = this.getSession().createQuery(hql.toString());
		query.setParameterList("objId", paramsMap.get("cartItem").toString().split(","));
		
		List<ShoppingCartItem> cartItemList = query.list();
		return cartItemList;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.dao.ShoppingCartDao#updateChangeQtyAndMoney(com.gpcsoft.eps.agreement.cart.domain.ShoppingCartItem)
	 */
	@SuppressWarnings("unchecked")
	public void updateChangeQtyAndMoney(final ShoppingCartItem cartItem) {
		getHibernateTemplate().execute(new HibernateCallback(){
		public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String hql="update ShoppingCart set goodsQty=:goodsQty,goodsTotal=:goodsTotal where objId=:objId";
			Query query = session.createQuery(hql);
			query.setBigDecimal("goodsQty", cartItem.getShoppingCart().getGoodsQty());
			query.setBigDecimal("goodsTotal", cartItem.getShoppingCart().getGoodsTotal());
			query.setString("objId", cartItem.getShoppingCart().getObjId());
			return query.executeUpdate();
		}});
	}

	/** 
	 * Description :  根据购物车ID调整购物车、购物车Item的数量和金额
	 * Create Date: 2010-4-14下午05:58:31 by wangsw  Modified Date: 2010-4-14下午05:58:31 by yucy
	 * @param  购物车ID 
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateSortChatAndItemQytAndMoney(final String objId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//计算每个ShoppingItem的数量和金额
				BigDecimal cartQty=new BigDecimal(0);
				BigDecimal cartTotal=new BigDecimal(0);
				String hql="from ShoppingCartItem si where si.shoppingCart.objId=:objId";
				Query query = session.createQuery(hql);
				query.setString("objId", objId);
				List<ShoppingCartItem> itemList=query.list();
				for(ShoppingCartItem item:itemList){
					cartQty=cartQty.add(item.getGoodsQty());
					cartTotal=cartTotal.add(item.getGoodsTotal());
				}
				ShoppingCartItem shoppingCartItem=new ShoppingCartItem();
				ShoppingCart shoppingCart=new ShoppingCart();
				shoppingCart.setObjId(objId);
				shoppingCart.setGoodsQty(cartQty);
				shoppingCart.setGoodsTotal(cartTotal);
				shoppingCartItem.setShoppingCart(shoppingCart);
				updateChangeQtyAndMoney(shoppingCartItem);
				return null;
			}});
	}

	/** 
	 * Description : 根据user查找购物车 
	 * Create Date: 2010-4-26下午12:14:42 by wangsw  Modified Date: 2010-4-26下午12:14:42 by yucy
	 * @param   orgInfo
	 * @return  购物车
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public ShoppingCart findShoppingCartByOrgInfoId(final Map<String, Object> paramsMap) {
		List<ShoppingCart> shoppingCartList=(List<ShoppingCart>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//计算每个ShoppingItem的数量和金额
				String hql="from ShoppingCart c where c.buyer.objId = :orgId and c.createUser.objId = :userId ";
				Query query = session.createQuery(hql);
				query.setParameter("orgId",paramsMap.get("orgId"));
				query.setParameter("userId",paramsMap.get("userId"));
				return query.list();
			}});
		return shoppingCartList.size()!=0?shoppingCartList.get(0):null;
	}

	/** 
	 * Description : 清空购物车
	 * Create Date: 2010-5-5上午09:54:34 by wangsw  Modified Date: 2010-5-5上午09:54:34 by yucy
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void removeAllByOrgInfo(final Map<String, Object> paramsMap) {
		List<ShoppingCart> list=getHibernateTemplate().find("from ShoppingCart c where c.buyer.objId = ? and createUser.objId = ? ",new Object[]{paramsMap.get("orgId"),paramsMap.get("userId")});
		if(list.size()>0){
			getHibernateTemplate().delete(list.get(0));
		}
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.dao.ShoppingCartDao#getGoodsClassAndChild(java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsClass> getGoodsClassAndChild(final String id) {
		return (List<GoodsClass>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql=" select GOODS_CLASS_ID,GOODS_CLASS_NAME from GOODS_CLASS start with GOODS_CLASS_ID=:id connect by prior GOODS_CLASS_ID=PARENT_CLASS_ID";
				Query query = session.createSQLQuery(sql);
				query.setString("id", id);
				List idsList=query.list();
				List<GoodsClass> gcList=new ArrayList<GoodsClass>();
				if(idsList.size()!=0){
					Object[] obj=(Object[]) idsList.get(0);
					GoodsClass goodsClass=new GoodsClass();
					goodsClass.setObjId((String) obj[0]);
					goodsClass.setGoodsClassName((String) obj[1]);
					gcList.add(goodsClass);
				}
				return gcList;
			}});
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.dao.ShoppingCartDao#findGoodsClassForShowGoods(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsClass> findGoodsClassForShowGoods(final Map<String, Object> paramsMap) {
		String goodsClassId=(String) paramsMap.get("goodsClassId");	
		StringBuffer hql = new StringBuffer();
		hql.append(" select g from GoodsClass g  ");
		hql.append(" where g.parentClazz.objId = ? ");
		List list = this.getHibernateTemplate().find(hql.toString(), goodsClassId);
		return list;
	}

	public List<GoodsBrand> findBrandForShowGoods(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
