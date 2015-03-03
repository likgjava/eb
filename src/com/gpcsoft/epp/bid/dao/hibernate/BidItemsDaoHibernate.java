package com.gpcsoft.epp.bid.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.bid.dao.BidItemsDao;
import com.gpcsoft.epp.bid.domain.BidItems;

@Repository
public class BidItemsDaoHibernate extends BaseGenericDaoHibernate<BidItems> implements BidItemsDao {

	/**
	 * Funcname:getBidItemsByPackIds
	 * Description: 根据包件ID获取投标条目
	 * @param packIds 包件Id
	 * @param bidId 投标Id
	 * @return List<BidItems>
	 * @throws Exception
	 * @Create Date 2010-10-8 上午10:18:53 
     * @author wanghz
	 */
	@SuppressWarnings("unchecked")
	public List<BidItems> getBidItemsByPackIds(final String[] packIds,final String bidId){
		return (List<BidItems>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<BidItems> doInHibernate(Session session)throws HibernateException, SQLException {	
				StringBuffer hql = new StringBuffer();
				hql.append(" select bidItems,bidPack.pack.objId from BidItems as bidItems,BidPackage as bidPack ");
				hql.append(" where bidItems.bidPackId = bidPack.objId ");
				hql.append(" and bidPack.bid.objId=:bidId ");
				hql.append(" and bidPack.pack.objId in ( :packIds ) ");
				List<Object[]> objects = session.createQuery(hql.toString())
				.setString("bidId", bidId)
				.setParameterList("packIds", packIds)
				.list();
				List<BidItems> bidItemsList = new ArrayList<BidItems>(0);
				if (null != objects && !objects.isEmpty()) {
					for (Object[] object:objects) {
						BidItems bidItems = (BidItems)object[0];
						bidItems.setPackId(object[1]+"");
						bidItemsList.add(bidItems);
					}
				}
				return bidItemsList;
			}
		});
	}
	
	/**
	 * Funcname:removeBidItemsByBidId
	 * Description: 删除投标品目
	 * @param bidItemsList 保留的投标品目
	 * @param bidId 投标主键
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-10-8 下午02:41:44 
   	 * @author wanghz
	 */
	@SuppressWarnings("unchecked")  
	public void removeBidItemsByBidId(final String bidId,final List<BidItems> bidItemsList){
		List<String> removeIds = (List<String>)getHibernateTemplate().execute(new HibernateCallback(){
			public List<String> doInHibernate(Session session)throws HibernateException, SQLException {	
						// 获取需要删除的投标品目
			StringBuffer hql = new StringBuffer();
			hql.append(" select bidItems.objId  from BidItems as bidItems,BidPackage as bidPackage ");
			hql.append(" where bidItems.bidPackId = bidPackage.objId ");
			hql.append(" and bidPackage.bid.objId=:bidId ");
			return session.createQuery(hql.toString()).setString("bidId", bidId).list();
			}
		});
		if (null != removeIds) {
			for (String ids:removeIds) {
				this.remove(ids, BidItems.class);
			}
		}
	}

}
