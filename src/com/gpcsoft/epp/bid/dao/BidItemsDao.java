package com.gpcsoft.epp.bid.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.bid.domain.BidItems;

public interface BidItemsDao extends BaseGenericDao<BidItems> {

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
	public List<BidItems> getBidItemsByPackIds(String[] packIds, String bidId);
	
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
	public void removeBidItemsByBidId(String bidId,List<BidItems> bidItemsList);
	
	
}
