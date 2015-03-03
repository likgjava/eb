package com.gpcsoft.agreement.cart.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.cart.dao.ShoppingCartGoodsGiftDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsGift;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.domain.GoodsGift;

@Repository
public class ShoppingCartGoodsGiftDaoHibernate extends BaseGenericDaoHibernate<ShoppingCartGoodsGift> implements ShoppingCartGoodsGiftDao {

	/** 
	 * Description :  获取商品礼包列表
	 * Create Date: 2011-1-18上午10:13:34 by likg  Modified Date: 2011-1-18上午10:13:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsGift> getGoodsGiftList(final Map<String, Object> param) throws Exception {
		return (List<GoodsGift>) getHibernateTemplate().execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				Object shoppingCartItemId = param.get("shoppingCartItemId");
				
				StringBuilder hql = new StringBuilder();
				hql.append("select gc.goodsGift from ShoppingCartGoodsGift gc join gc.goodsGift gg where 1=1 ");
				
				if(shoppingCartItemId!=null && StringUtils.hasLength(shoppingCartItemId.toString())) {
					hql.append(" and gc.shoppingCartItem.objId = '").append(shoppingCartItemId).append("' ");
				}
				
				Query query = session.createQuery(hql.toString());
				
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取商品礼包-购物车条目列表集合
	 * Create Date: 2011-1-18下午04:45:26 by likg  Modified Date: 2011-1-18下午04:45:26 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<ShoppingCartGoodsGift> getShoppingCartGoodsGiftList(final Map<String, Object> param) throws Exception {
		return (List<ShoppingCartGoodsGift>) getHibernateTemplate().execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				Object shoppingCartItemId = param.get("shoppingCartItemId");
				Object orgInfoId = param.get("orgInfoId");
				Object userId = param.get("userId");
				
				StringBuilder hql = new StringBuilder();
				hql.append("from ShoppingCartGoodsGift gc join fetch gc.goodsGift gg join fetch gc.shoppingCartItem sci where 1=1 ");
				
				if(orgInfoId!=null && StringUtils.hasLength(orgInfoId.toString()) && userId!=null && StringUtils.hasLength(userId.toString())) {
					hql.append(" and sci.shoppingCart.buyer.objId= '").append(orgInfoId).append("'");
					hql.append(" and sci.shoppingCart.createUser.objId = '").append(userId).append("'");
				}
				
				if(shoppingCartItemId!=null && StringUtils.hasLength(shoppingCartItemId.toString())) {
					hql.append(" and gc.shoppingCartItem.objId = '").append(shoppingCartItemId).append("' ");
				}
				
				Query query = session.createQuery(hql.toString());
				
				return query.list();
			}
		});
	}

}
