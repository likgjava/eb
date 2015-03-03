package com.gpcsoft.agreement.order.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.OrderItemDao;
import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class OrderItemDaoHibernate extends BaseGenericDaoHibernate<OrderItem> implements OrderItemDao {

	@SuppressWarnings("unchecked")
	public void updateChangeQtyAndMoney(final OrderItem orderItem) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer hql=new StringBuffer("update OrderItem item set item.goodsTotal=:goodsTotal ");
				
				//更改数量
				boolean isQty = false;
				if(orderItem.getGoodsQty() != null && !"".equals(orderItem.getGoodsQty())){
					hql.append(" , item.goodsQty=:goodsQty ");
					isQty = true;
				}
				
				//更改单价
				boolean isPrice = false;
				if(orderItem.getGoodsPrice() != null && !"".equals(orderItem.getGoodsPrice())){
					hql.append(" , item.goodsPrice=:goodsPrice ");
					isPrice = true;
				}
				hql.append(" where item.objId=:objId");
				
				Query query = session.createQuery(hql.toString());
				query.setBigDecimal("goodsTotal", orderItem.getGoodsTotal());
				query.setString("objId", orderItem.getObjId());
				
				if(isQty)
					query.setBigDecimal("goodsQty", orderItem.getGoodsQty());
				if(isPrice)
 					query.setBigDecimal("goodsPrice", orderItem.getGoodsPrice());
				
				int result =  query.executeUpdate();
				
				//同步一下品目集合
				String categoryNameHql = " select distinct item.goods.purCategory.categoryName from OrderItem item where item.objId = :objId  ";
				query = session.createQuery(categoryNameHql);
				query.setString("objId", orderItem.getObjId());
				String categoryName = "";
				for (Object obj:query.list()) {
					categoryName += (StringUtils.hasLength(categoryName)?","+obj:obj);
				}
				
				String updateOrder = "  update Order o set o.categoryNames = :categoryNames where o.objId = ( select oi.order.objId from OrderItem oi where oi.objId = :objId ) ";
				query = session.createQuery(updateOrder);
				query.setParameter("categoryNames", categoryName);
				query.setParameter("objId", orderItem.getObjId());
				return result + query.executeUpdate();
			}});
	}

	/**
	 * 查询订单商品配件
	 * Description :  
	 * Create Date: 2010-4-13下午04:13:15 by sunl  Modified Date: 2010-4-13下午04:13:15 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrderItem> getOrderItemGoodsOption(Map<String, Object> paramsMap) {
		StringBuilder hql=new StringBuilder();
		
		hql.append("from OrderItem as op " +
				"left join fetch op.goods og" +
				"left join fetch op.orderGoodsOptions as oo left join fetch oo.option " +
				"left join fetch op.order " +
				"inner join op.orderProtasks as o where  o.protaskItem.objId=?"); 
		List<OrderItem> orderItemList=this.getHibernateTemplate().find(hql.toString(), new Object[]{paramsMap.get("protaskId")});

		return orderItemList;
	}
	
	/** 
	 * Description :  根据合同id获得订单的明细，并获得商品的选配
	 * Create Date: 2010-12-14下午01:39:56 by liangxj  Modified Date: 2010-12-14下午01:39:56 by liangxj
	 * @param   contractId 合同Id
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<OrderItem> getOrderItemByContract(String contractId) {
		StringBuilder hql=new StringBuilder();
		hql.append("from OrderItem as op " +
				"left join fetch op.goods og " +
				//"left join fetch op.orderGoodsOptions as oo left join fetch oo.option " +
				"where op.order.contract.objId=?"); 
		List<OrderItem> orderItemList=this.getHibernateTemplate().find(hql.toString(), new Object[]{contractId});

		return orderItemList;
	}

	/** 
	 * Description :  删除时同步订单中的品目名称
	 * Create Date: 2011-3-1下午09:32:12 by yucy  Modified Date: 2011-3-1下午09:32:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer updateCategoryNames(final String itemsId) throws Exception {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback(){public Object doInHibernate(Session session) throws HibernateException, SQLException {
				//同步一下品目集合
				String categoryNamesHql = " select distinct pc.category_name "+
					" from eps_agreement_order_item oi2 "+
					" left join goods g on oi2.goods_id = g.goods_id "+
					" left join purcatalog_category pc on g.pur_category_id = pc.id "+
					" where oi2.order_id = "+
					" (select oi.order_id from eps_agreement_order_item oi "+
					" where oi.order_dtl_id = :itemsId) "+
					"  and oi2.order_dtl_id != :itemsId ";
				Query query = session.createSQLQuery(categoryNamesHql);
				query.setParameter("itemsId", itemsId);
				String categoryName = "";
				for (Object obj:query.list()) {
					categoryName += (StringUtils.hasLength(categoryName)?","+obj:obj);
				}
				String updateSql = " update eps_agreement_order oo set oo.category_names = :categoryName where oo.order_id = (select o.order_id from eps_agreement_order_item oi join eps_agreement_order o on o.order_id = oi.order_id where oi.order_dtl_id = :itemsId ) ";
				Query query2 = session.createSQLQuery(updateSql);
				query2.setParameter("categoryName", categoryName);
				query2.setParameter("itemsId", itemsId);
				return query2.executeUpdate();
			}});
	}
}
