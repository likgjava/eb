package com.gpcsoft.epp.bid.manager.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.bid.dao.BidPackageDao;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.manager.BidPackageManager;
import com.gpcsoft.epp.eval.dao.CongFactorResponseDao;
import com.gpcsoft.epp.eval.dao.PackCongFactorResponseDao;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.eval.domain.PackCongFactorResponse;

@Repository
public class BidPackageManagerImpl extends BaseManagerImpl<BidPackage> implements BidPackageManager {

	@Autowired(required=true)  @Qualifier("bidPackageDaoHibernate")
	private BidPackageDao bidPackageDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("packCongFactorResponseDaoHibernate")
	private PackCongFactorResponseDao packCongFactorResponseDaoHibernate;
	
	
	public BidPackage getBidPackageByPackIdAndBidId(String packId, String bidId) {
		return bidPackageDaoHibernate.getBidPackageByPackIdAndBidId(packId, bidId);
	}
	
	@Autowired(required=true)  @Qualifier("congFactorResponseDaoHibernate")
	private CongFactorResponseDao congFactorResponseDaoHibernate;
	
	
	public void removeBidPackageAndFactorResponseAndResponse(String bidId,String[] packIds,List<CongFactorResponse> congFactorResponseList) {
		
		// 1.1删除指标响应
		List<CongFactorResponse> congFactorResponseRemoveList = congFactorResponseDaoHibernate.getCongFactorResponseIsMayRemove(congFactorResponseList,bidId);
		if(congFactorResponseRemoveList.size()>0){
			Set<String> responseIds = new HashSet<String>();
			for(CongFactorResponse congFactorResponse:congFactorResponseRemoveList){
				responseIds.add(congFactorResponse.getObjId());
			}
			for(String temp:responseIds){
				congFactorResponseDaoHibernate.remove(temp,CongFactorResponse.class);
			}
		}
		
		// 1.2删除指标响应与投标包件中间表
		List<PackCongFactorResponse> packCongFactorResponseList = packCongFactorResponseDaoHibernate.getPackCongFactorResponseIsMayRemove(bidId);
		if(packCongFactorResponseList.size()>0){
			StringBuffer packFactorResponseIds = new StringBuffer();
			for(PackCongFactorResponse PackCongFactorResponse:packCongFactorResponseList){
				packFactorResponseIds.append(PackCongFactorResponse.getObjId());
				packFactorResponseIds.append(",");
			}
			packFactorResponseIds = new StringBuffer(packFactorResponseIds.toString().substring(0,packFactorResponseIds.toString().length()-1));
			packCongFactorResponseDaoHibernate.remove(packFactorResponseIds.toString().split(","),PackCongFactorResponse.class);
		}
		
		// 1.3删除投标与包件中间表	
		List<BidPackage> bidPackageList = bidPackageDaoHibernate.getBidPackageIsMayRemove(bidId,packIds);
		if(bidPackageList.size()>0){
			StringBuffer bidPackIds = new StringBuffer();
			for(BidPackage bidPackage:bidPackageList){
				bidPackIds.append(bidPackage.getObjId());
				bidPackIds.append(",");
			}
			bidPackageDaoHibernate.remove(bidPackIds.toString().substring(0,bidPackIds.toString().length()-1).split(","),BidPackage.class);
		}
	}

	/**
	 * Funcname:getBidPackage
	 * Description : 根据包组得到投标包组对象 
	 * @Create Date: 2010-8-11上午11:53:10 
	 * @author  liuke  
	 * @Modified Date: 2010-8-11上午11:53:10  
	 * @author liuke
	 * @param   subProjectId:包组主键,bidId: 投标主键
	 * @return  BidPackage包组中间表对象
	 * @Exception
	 */
	public BidPackage getBidPackage(String subProjectId,String bidId) {
		return bidPackageDaoHibernate.getBidPackage(subProjectId,bidId);
	}
	
	/**
	 * Funcname:getAllByPackId
	 * Description: 根据包件ID获取所有投标包件中间表
	 * @param packId
	 * @return List<BidPackage>
	 * @throws Exception
	 * @Create Date 2010-8-16 下午01:32:50 
	 * @author  wanghz
	 */
	public List<BidPackage> getAllByPackId(String packId)throws Exception{
		return bidPackageDaoHibernate.getAllByPackId(packId);
	}

	/**
	 * Funcname:removeAllBidPackageByProject
	 * Description :根据项目删除投标包件中间表
	 * Create Date: 2010-12-15下午07:18:56 
     * @author liuke  
	 * Modified Date: 2010-12-15下午07:18:56 
	 * @author liuke  
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeAllBidPackageByProject(String projectId) {
		bidPackageDaoHibernate.removeAllBidPackageByProject(projectId);
	}


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
	public BidPackage getBidPackageByPackCodeAndBidId(String packCode,
			String bidId) {
		return bidPackageDaoHibernate.getBidPackageByPackCodeAndBidId(packCode, bidId);
	}
}
