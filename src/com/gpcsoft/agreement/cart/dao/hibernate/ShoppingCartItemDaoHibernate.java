package com.gpcsoft.agreement.cart.dao.hibernate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.cart.dao.ShoppingCartItemDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.srplatform.auth.domain.Attachment;

/** 
  *  Comments: <strong>ShoppingCartItemDaoHibernate</strong>            		
  *	 <br/>	购物车item DAO	        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-4-12 下午03:18:22 by wangsw    					                            
  *  <br/>Modified Date:  2010-4-12 下午03:18:22 by wangsw                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Repository
public class ShoppingCartItemDaoHibernate extends BaseGenericDaoHibernate<ShoppingCartItem> implements ShoppingCartItemDao {

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.dao.ShoppingCartItemDao#updateChangeQtyAndMoney(com.gpcsoft.eps.agreement.cart.domain.ShoppingCartItem)
	 */
	@SuppressWarnings("unchecked")
	public void updateChangeQtyAndMoney(final ShoppingCartItem cartItem) {
		getHibernateTemplate().execute(new HibernateCallback(){
		public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String hql="update ShoppingCartItem item set item.goodsQty=:goodsQty,item.goodsPrice=:goodsPrice,item.goodsTotal=:goodsTotal where item.objId=:objId";
			Query query = session.createQuery(hql);
			query.setBigDecimal("goodsQty", cartItem.getGoodsQty());
			query.setBigDecimal("goodsPrice", cartItem.getGoodsPrice());
			query.setBigDecimal("goodsTotal", cartItem.getGoodsTotal());
			query.setString("objId", cartItem.getShoppingCart().getObjId());
			return query.executeUpdate();
		}});
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.dao.ShoppingCartItemDao#findSupplierByOrderMoney()
	 */
	@SuppressWarnings("unchecked")
	public List<OrgInfo> findSupplierByOrderMoney() {   
		return (List<OrgInfo>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {//DISTINCT
				StringBuilder hql=new StringBuilder();
				hql.append(" select o.supplier.objId, sum(o.goodsTotal) from Order o where 1=1 ");
				hql.append(" and o.supplier.supplierId is not null ");
				hql.append(" group by o.supplier.objId ");
				hql.append(" order by sum(o.goodsTotal) desc ");
				Query query = session.createQuery(hql.toString()); 
				query.setMaxResults(10);
				List list=query.list();
				List<OrgInfo> orgInfoList=new ArrayList<OrgInfo>();
				for(int i=0; i<list.size(); i++){
					Object[] objs=(Object[]) list.get(i);
					OrgInfo orgInfo=(OrgInfo) getHibernateTemplate().get(OrgInfo.class, (Serializable) objs[0]); 
					orgInfoList.add(orgInfo);
				}
				return orgInfoList;
			}});
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.dao.ShoppingCartItemDao#findGoodsClassForIndex()
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsClass> findGoodsClassForIndex(String nameFirstSpell) {   
				StringBuilder hql=new StringBuilder();
//				hql.append(" select gc3 from GoodsClass gc3 where gc3.parentClazz in ");
//				hql.append(" (select gc2.objId ");
//				hql.append(" from GoodsClass gc2 where gc2.parentClazz.objId in (select gc1 from GoodsClass gc1 where gc1.parentClazz is null) ");
//				hql.append(" group by gc2.objId) ");
//				if(nameFirstSpell!=null)
//					hql.append(" and gc3.shortSpellName like ").append("'").append(nameFirstSpell).append("%'");
				
				if(nameFirstSpell==null)
					hql.append(" select g from GoodsClass g where g.parentClazz is null order by g.goodsClassName ");
				else
					hql.append(" select g from GoodsClass g where g.isLeaf =true and g.shortSpellName like ").append("'").append(nameFirstSpell).append("%'").append(" order by g.goodsClassName");
				return getHibernateTemplate().find(hql.toString());
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.dao.ShoppingCartItemDao#findHotGoodsForIndex()
	 */
	@SuppressWarnings("unchecked")
	public List<Goods> findHotGoodsForIndex() {
		return (List<Goods>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {  
				String hql="select oi.goods.objId,oi.goods.productName,oi.goods.picture, sum(oi.goodsQty)" +
						" from OrderItem oi " +
						" group by oi.goods.objId,oi.goods.productName,oi.goods.picture " +
						" order by sum(oi.goodsQty) desc";// by yucy
				Query query = session.createQuery(hql); 
				query.setMaxResults(10);
				List list=query.list();
				List<Goods> goodsList=new ArrayList<Goods>();
				for(int i=0; i<list.size(); i++){
					Object[] objs=(Object[]) list.get(i);
					Goods goods=new Goods();
					Attachment picture=new Attachment();
					goods.setObjId((String) objs[0]);
					goods.setProductName((String) objs[1]);
					picture.setObjId((String) objs[2]);
					goods.setPicture(picture.getObjId());// by yucy
					goodsList.add(goods);
				}
				return goodsList;
			}});
	}

	/** 
	 * Description :  取得ShoppingCartItemBy Ids（结果可能多条）
	 * Create Date: 2010-10-28上午11:33:39 by yucy  Modified Date: 2010-10-28上午11:33:39 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ShoppingCartItem> getShoppingCartItemByIds(final Object[] ids) throws Exception {
		return (List<ShoppingCartItem>) getHibernateTemplate().execute(
			new HibernateCallback<List<ShoppingCartItem>>(){
			@SuppressWarnings("unchecked")
			public List<ShoppingCartItem> doInHibernate(Session session)throws HibernateException, SQLException { 
				String Hql = " from ShoppingCartItem where objId in ( :ids ) " ;
				Query query = session.createQuery(Hql); 
				query.setParameterList("ids", ids);
				return query.list();
			}});
	}
}
