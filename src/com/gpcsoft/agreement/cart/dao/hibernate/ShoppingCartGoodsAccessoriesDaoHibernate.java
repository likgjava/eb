package com.gpcsoft.agreement.cart.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.cart.dao.ShoppingCartGoodsAccessoriesDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsAccessories;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.domain.GoodsAccessories;

@Repository
public class ShoppingCartGoodsAccessoriesDaoHibernate extends BaseGenericDaoHibernate<ShoppingCartGoodsAccessories> implements ShoppingCartGoodsAccessoriesDao {

	/** 
	 * Description :  获取商品零配件列表
	 * Create Date: 2011-1-18上午10:13:34 by likg  Modified Date: 2011-1-18上午10:13:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsAccessories> getGoodsAccessList(final Map<String, Object> param) throws Exception {
		return (List<GoodsAccessories>) getHibernateTemplate().execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				Object shoppingCartItemId = param.get("shoppingCartItemId");
				
				StringBuilder hql = new StringBuilder();
				hql.append("select gc.goodsAccess from ShoppingCartGoodsAccessories gc join gc.goodsAccess gg where 1=1 ");
				
				if(shoppingCartItemId!=null && StringUtils.hasLength(shoppingCartItemId.toString())) {
					hql.append(" and gc.shoppingCartItem.objId = '").append(shoppingCartItemId).append("' ");
				}
				
				Query query = session.createQuery(hql.toString());
				
				return query.list();
			}
		});
	}
}
