package com.gpcsoft.epp.bid.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;

public interface BidPackageManager extends BaseManager<BidPackage> {

	/**
	 * Funcname:getBidPackageByPackIdAndBidId
	 * Description: 根据包件ID,投标ID获取投标与包件中间表
	 * @param packId:包组ID
	 * @param bidId:投标ID
	 * @return BidPackage
	 * @throws Exception
	 * @Create Date 2010-8-5 上午10:05:48 
	 * @author wanghz
	 */
	public BidPackage getBidPackageByPackIdAndBidId(String packId,String bidId);
	
	/**
	 * Funcname:removeBidPackageAndFactorResponseAndResponse
	 * Description: 根据投标ID,投标包件ID,删除投标包件中间表,指标响应与投标包件中间表,指标响应
	 * @param bidId
	 * @param packIds
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-8-5 下午12:41:37 
	 * @author  wanghz
	 */
	public void removeBidPackageAndFactorResponseAndResponse(String bidId,String[] packIds,List<CongFactorResponse> congFactorResponseList);
	
	/**
	 * Funcname:getBidPackage
	 * Description : 根据包组和投标记录得到投标包组对象 
	 * @Create Date: 2010-8-11上午11:53:10 
	 * @author liuke  
	 * @Modified Date: 2010-8-11上午11:53:10 
	 * @author liuke
	 * @param   subProjectId;包组主键,bidId:投标主键
	 * @return  BidPackage:包组与投标对象中间表
	 * @Exception
	 */
	public BidPackage getBidPackage(String subProjectId,String bidId);
	
	/**
	 * Funname:getAllByPackId
	 * Description: 根据包件ID获取所有投标包件中间表
	 * @param packId
	 * @return List<BidPackage>
	 * @throws Exception
	 * @Create Date 2010-8-16 下午01:32:50 
	 * @author wanghz
	 */
	public List<BidPackage> getAllByPackId(String packId)throws Exception;
	
	
	/**
	 * Funcname:removeAllBidPackageByProject
	 * Description :根据项目删除投标包件中间表
	 * @Create Date: 2010-12-15下午07:18:56 
	 * @author  liuke  
	 * @Modified Date: 2010-12-15下午07:18:56 
	 * @author liuke
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeAllBidPackageByProject(String projectId);
	
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
	public BidPackage getBidPackageByPackCodeAndBidId(String packCode, String bidId) ;
	
}
