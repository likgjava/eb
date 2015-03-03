package com.gpcsoft.epp.bid.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.bid.domain.BidPackage;

public interface BidPackageDao extends BaseGenericDao<BidPackage> {
	
	/**
	 * Funcname:getBidPackageByPackIdAndBidId
	 * Description: 根据包件ID,投标ID获取投标与包件中间表
	 * @param packId:包件Id
	 * @param bidId:投标Id
	 * @return BidPackage：投保与包件中间对象
	 * @Create Date 2010-8-5 上午10:05:48 
	 * @author wanghz
	 * @throws Exception
	 */
	public BidPackage getBidPackageByPackIdAndBidId(String packId,String bidId);
	
	/**
	 * Funcname:getBidPackageIsMayRemove
	 * Description: 根据投标ID,投标包件ID,获取可以删除的 投标与包件中间表,只返回ID
	 * @param bidId:投标主键
	 * @param packIds:投标包件ID
	 * @return List<BidPackage>投保与包件中间对象集合
	 * @Create Date 2010-8-5 下午12:59:39 
	 * @author wanghz
	 */
	public List<BidPackage> getBidPackageIsMayRemove(String bidId,String[] packIds);
	
	/**
	 * FuncName: getBidPackageInfoByProjectIds
	 * Description :  根据项目ID获得投标信息
	 * @param projectIds:项目主键
	 * @return List<BidPackage>:投保与包件中间对象集合
	 * @author: liuke
	 * @Create Date:2011-6-24  上午11:23:04
	 * @Modifier: liuke
	 * @Modified Date:2011-6-24  上午11:23:04
	 */
	@SuppressWarnings("unchecked")
	public List getBidPackageInfoByProjectIds(String projectIds);
	
	/**
	 * FuncName:getBidPackage
	 * Description : 根据包组和投标记录得到投标包组对象 
	 * @Create Date: 2010-8-11上午11:53:10   .
	 * @author liuke
	 * @Modified Date: 2010-8-11上午11:53:10  
	 * @author liuke
	 * @param   subProjectId;包组主键,bidId:投标主键
	 * @return  BidPackage投保与包件中间对象
	 * @Exception
	 */
	public BidPackage getBidPackage(String subProjectId,String bidId);
	
	/**
	 * FuncName:getAllByPackId
	 * Description: 根据包件ID获取所有投标包件中间表
	 * @param packId
	 * @return List<BidPackage> 投保与包件中间对象集合
	 * @throws Exception
	 * @Create Date 2010-8-16 下午01:32:50 
	 * @author wanghz
	 */
	public List<BidPackage> getAllByPackId(String packId)throws Exception;
	
	/**
	 * Funcname:getAllBidPackageByProjectId
	 * Description:根据项目获得所有投标包件中间表  
	 * @Create Date: 2010-10-11上午09:16:52 
	 * @author  liuke  
	 * @Modified Date: 2010-10-11上午09:16:52 
	 * @author  liuke 
	 * @param   projectId
	 * @return  List<BidPackage>:投保与包件中间对象集合
	 * @Exception
	 */
	public List<BidPackage> getAllBidPackageByProjectId(String projectId) throws Exception;
	
	/**
	 * Funcname:getAllBidPackageByProjId
	 * Description :根据项目或包组ID获得所有投标包件中间表  
	 * @Create Date: 2010-10-11上午09:16:52 
	 * @author sunl  
	 * @Modified Date: 2010-10-11上午09:16:52 
	 * @author sunl  
	 * @param   projId:项目或包组ID
	 * @return  List<BidPackage>:投保与包件中间对象集合
	 * @Exception
	 */
	public List<BidPackage> getAllBidPackageByProjId(String projId) throws Exception;
	
	/**
	 * Funcname:removeAllBidPackageByProject
	 * Description :根据项目删除投标包件中间表
	 * @Create Date: 2010-12-15下午07:18:56 
	 * @author  liuke  
	 * @Modified Date: 2010-12-15下午07:18:56 
	 * @author liuke
	 * @param projectId:项目主键
	 * @return  void
	 * @Exception
	 */
	public void removeAllBidPackageByProject(String projectId);
	
	/**
	 * Funcname:removeAllBidPackageByPackId
	 * Description:根据项目删除投标包件中间表
	 * @Create Date: 2010-12-15下午07:18:56 
	 * @author liuke  
	 * @Modified Date: 2010-12-15下午07:18:56 
	 * @author  liuke
	 * @param packId:项目或包组Id
	 * @return  void
	 * @Exception
	 */
	public void removeAllBidPackageByPackId(String packId);
	
	/**
	 * FuncName: getAllBidPackagesByBid
	 * Description :  根据投标Id获得投标包件中间表对象
	 * @param bidId:投标主键,
	 * @return List<BidPackage>
	 * @author: liuke
	 * @Create Date:2011-5-30  下午06:50:02
	 * @Modifier: liuke
	 * @Modified Date:2011-5-30  下午06:50:02
	 */
	public List<BidPackage> getAllBidPackagesByBidAndNotInPack(String bidId,String subProjId);
	
	/**
	 * FuncName: getBidPackageByPackCodeAndBidId
	 * Description :  根据投标ID和包组编号获取到投标与包件中间表
	 * @param packCode:包组编号
	 * @param bidId:投标主键
	 * @return BidPackage:包件中间表
	 * @author: shenjz
	 * @Create Date:2011-6-23  上午09:32:38
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-23  上午09:32:38
	 */
	public BidPackage getBidPackageByPackCodeAndBidId(String packCode, String bidId); 
	
	
	
	/**
	 * @Description: 根据包件ID,与供应商ID查找投标与包件中间表
	 * @param packId
	 * @param supplierId
	 * @return
	 */
	public BidPackage getBidPackageByPackIdAndSupplierId(String packId,String supplierId);
}
