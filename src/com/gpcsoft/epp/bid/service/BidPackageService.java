package com.gpcsoft.epp.bid.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.bid.domain.BidPackage;

public interface BidPackageService extends BaseGenericService<BidPackage> {

	/**
	 * 
	 * Description :根据项目获得所有投标与包件中间表列表  
	 * Create Date: 2010-10-11上午09:09:59 by liuke  Modified Date: 2010-10-11上午09:09:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<BidPackage>	getAllBidPackageListByProjectId(String projectId)throws Exception;
	
	/**
	 * 
	 * Description :根据项目或包组ID获得所有投标包件中间表  
	 * Create Date: 2010-10-11上午09:16:52 by sunl  Modified Date: 2010-10-11上午09:16:52 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<BidPackage> getAllBidPackageByProjId(String projId) throws Exception;
		
	/**
	 * FuncName: getBidPackageByPackCodeAndBidId
	 * Description :  根据投标ID和包组编号获取到投标与包件中间表
	 * @param packCode
	 * @param bidId
	 * @return BidPackage
	 * @author: shenjz
	 * @Create Date:2011-6-23  上午09:32:38
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-23  上午09:32:38
	 */
	public BidPackage getBidPackageByPackCodeAndBidId(String packCode, String bidId);
	
	
	/**
	 * @Description: 根据包件ID,投标ID获取投标与包件中间表
	 * @param packId
	 * @param bidId
	 * @return BidPackage
	 * @throws Exception
	 * @Create Date 2010-8-5 上午10:05:48 By wanghz
	 */
	public BidPackage getBidPackageByPackIdAndBidId(String packId,String bidId);
	
	
	
	/**
	 * @Description: 根据包件ID,与供应商ID查找投标与包件中间表
	 * @param packId
	 * @param supplierId
	 * @return
	 */
	public BidPackage getBidPackageByPackIdAndSupplierId(String packId,String supplierId);
}
