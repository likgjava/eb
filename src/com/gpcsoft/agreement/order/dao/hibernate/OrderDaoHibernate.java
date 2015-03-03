package com.gpcsoft.agreement.order.dao.hibernate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.OrderDao;
import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;

@Repository
public class OrderDaoHibernate extends BaseGenericDaoHibernate<Order> implements OrderDao {
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.order.dao.OrderDao#listOrderByTask(com.gpcsoft.core.utils.Page, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Page<Order> listOrderByTask(Page<Order> page,final String protaskId) {
		Map<String,Object> result = (Map<String,Object>)this.getHibernateTemplate().execute(
				new HibernateCallback(){ 
					public Object doInHibernate(Session session)throws HibernateException, SQLException {
						String hql = "select distinct o from Order o where o in(select op.orderItem.order from OrderProtask op where op.protaskItem.procurementtask.objId=:objId)";
						Query query = session.createQuery(hql);
						query.setString("objId", protaskId);
						Map<String,Object> result = new HashMap<String,Object>();
						result.put("totalRow", ((Query) query).list().size());
						result.put("list", ((Query) query).list());
						return result;
					}
				});
		Integer totalRow = (Integer) result.get("totalRow");
		List<Order> orderList = (List<Order>) result.get("list");
		Page<Order> pageData = new Page<Order>(page.getStart(),totalRow , page.getPageSize(),orderList );
		return pageData;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.order.dao.OrderDao#listOrderByTaskItem(com.gpcsoft.core.utils.Page, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Page listOrderByTaskItem(final Page<Order> page, final String protaskItemId) {
		Map<String,Object> result = (Map<String,Object>)this.getHibernateTemplate().execute(
				new HibernateCallback(){ 
					public Object doInHibernate(Session session)throws HibernateException, SQLException {
						String hql = "select distinct o from Order o where o in(select op.orderItem.order from OrderProtask op where op.protaskItem.objId=:protaskItemId)";
						Query query = session.createQuery(hql);
						query.setString("protaskItemId", protaskItemId);
						Map<String,Object> result = new HashMap<String,Object>();
						
						result.put("totalRow", ((Query) query).list().size());
						result.put("list", ((Query) query).list());
						return result;
					}
				});
		Integer totalRow = (Integer) result.get("totalRow");
		List<Order> orderList = (List<Order>) result.get("list");
		Page<Order> pageData = new Page<Order>(page.getStart(),totalRow , page.getPageSize(),orderList );
		return pageData;	
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
	public List<OrderItem> getOrderGoodsOption(Map<String, Object> paramsMap) {
		StringBuilder hql=new StringBuilder();
		hql.append("from OrderItem as op " +
				"where op.order.objId=?"); 
		List<OrderItem> orderItemList=this.getHibernateTemplate().find(hql.toString(), new Object[]{paramsMap.get("orderId")});
		for(OrderItem orderItem:orderItemList){
			orderItem.setOrderProtaskCount(orderItem.getOrderProtasks().size());
		}
		return orderItemList;
	}

	/**
	 * Description : 将合同的id更新到订单里面 
	 * Create Date: 2010-4-13下午07:14:02 by liangxj  Modified Date: 2010-4-13下午07:14:02 by liangxj
	 * @param  orderIds 订单id，多个id以逗号分隔  contractId 合同id
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public void saveOrderContract(final String orderIds, final String contractId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql;
				Query query;	
				if(!"".equals(orderIds)){
					hql="update Order set contract.objId=:contractId where objId in (:objIds)";
					query = session.createQuery(hql);
					query.setParameterList("objIds", orderIds.split(","));
				}else{
					hql="update Order set contract.objId=null where contract.objId=:contractId";
					query = session.createQuery(hql);
				}
				query.setString("contractId", contractId);
				return query.executeUpdate();
			}
		});
	}

	/**
	 * 作废订单，同步删除订单与任务书的关系
	 */
	@SuppressWarnings("unchecked")
	public void invalidOrder(final String contractObjId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String invalid = getEnumService().getValueByConstant("com.gpcsoft.eps.agreement.order.domain.Order.useStatus","INVALID");
				
				String hql="update Order o set o.useStatus='"+invalid+"' where o.contract.objId=:contractId";
				
				Query query = session.createQuery(hql);
				query.setString("contractId", contractObjId);

				return query.executeUpdate();
			}
		});
	}

	/** 
	 * Description :  更改订单支付状态
	 * Create Date: 2011-6-20下午02:34:39 by yucy  Modified Date: 2011-6-20下午02:34:39 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updatePayStatus(final String id, final String status) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String hql="update Order o set o.payStatus = :status where o.objId = :id or o.orderNo = :id ";
				Query query = session.createQuery(hql);
				query.setString("status", status);
				query.setString("id", id);
				return query.executeUpdate()>0;
			}
		});
	}
}
