package com.gpcsoft.epp.bid.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.bid.dao.BidSubKeyDao;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.domain.BidSubKey;
import com.gpcsoft.epp.bid.service.BidSubKeyService;

@Service 
public class BidSubKeyServiceImpl extends BaseGenericServiceImpl<BidSubKey> implements BidSubKeyService{

	@Autowired(required=true) @Qualifier("bidSubKeyDaoHibernate")
	private BidSubKeyDao bidSubKeyDao;
	
	/**
	 * Funcname:getBidIdByProjCodeAndOrgId
	 * Description: 根据机构ID,项目编号获取项目ID
	 * @param orgId:机构Id
	 * @param projCode:项目编号
	 * @return Bid：投标记录对象
	 * @Create Date 2011-8-5 上午10:05:48 
	 * @author caojz
	 * @throws Exception
	 */
	public Bid getBidIdByProjCodeAndOrgId(String projCode, String orgId) {
		
		return bidSubKeyDao.getBidIdByProjCodeAndOrgId(projCode, orgId);
	}

	/**
	 * Funcname:getBidPackageByBidId
	 * Description: 根据包组和投标记录得到投标包组对象 
	 * @param bidId:投标Id
	 * @param subProjectId:包组主键
	 * @return BidPackage：投标与包件中间对象
	 * @Create Date 2011-8-5 上午10:05:48 
	 * @author caojz
	 * @throws Exception
	 */
	public BidPackage getBidPackageByBidId(String bidId, String subProjectId)
			throws Exception {
		return bidSubKeyDao.getBidPackageByBidId(bidId, subProjectId);
	}

	/**
	 * Funcname:getBidSubKeysById
	 * Description: 根据投标记录ID得到开标子密钥集合 
	 * @param bidPackId:投标记录ID
	 * @return BidSubKey：开标子密钥集合
	 * @Create Date 2011-8-5 上午10:05:48 
	 * @author caojz
	 * @throws Exception
	 */
	public List<BidSubKey> getBidSubKeysById(String bidPackId) throws Exception {
		
		return bidSubKeyDao.getBidSubKeysById(bidPackId);
	}

	/**
	 * Funcname:saveOrUpdateBidSubKey
	 * Description :  保存或更新开标子密钥
	 * @Create Date: 2011-8-5 上午10:05:48     
	 * @author caojz
	 * @param   BidSubKey bidSubKey
	 * @return  boolean
	 * @Exception 
	 */
	 public BidSubKey saveOrUpdate(BidSubKey subKey) {
		
		return bidSubKeyDao.save(subKey);
	}
 

	/**
	 * 根据投标bidId和supplierId，查询BidSubKey是否存在
	 * @param bidId
	 * @param supplierId
	 * @return
	 * @author zhouzhanghe
	 * @created at 2011-11-09 16:56
	 */
	public boolean getIsExistByBidIdAndSupplierId(String bidId, String supplierId) {
		return bidSubKeyDao.getIsExistByBidIdAndSupplierId(bidId, supplierId);
	}
}
