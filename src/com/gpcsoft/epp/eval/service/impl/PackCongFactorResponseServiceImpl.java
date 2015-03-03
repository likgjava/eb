package com.gpcsoft.epp.eval.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.eval.dao.PackCongFactorResponseDao;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.domain.PackCongFactorResponse;
import com.gpcsoft.epp.eval.service.PackCongFactorResponseService;

@Service 
public class PackCongFactorResponseServiceImpl extends BaseGenericServiceImpl<PackCongFactorResponse> implements PackCongFactorResponseService {

	@Autowired(required=true)  @Qualifier("packCongFactorResponseDaoHibernate")
	private PackCongFactorResponseDao packCongFactorResponseDaoHibernate;
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.epp.eval.service.PackCongFactorResponseService#getFactorResponseByBidIdAndPackIds(java.lang.String, java.lang.String[])
	 */
	public List<Map<String, String>> getFactorResponseByBidIdAndPackIds(String bidId, String[] packIds) throws Exception {
		logger.debug("\nPackCongFactorResponseServiceImpl||getFactorResponseByBidIdAndPackIds\n");
		return packCongFactorResponseDaoHibernate.getFactorResponseByBidIdAndPackIds(bidId, packIds);
	}
	
	/**
	 * @Description: 获取 供应商投标的指标分类(一级分类)
	 * @param supplierId 供应商ID
	 * @param packId 包件ID/项目Id
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-20 下午10:42:03 By wanghz
	 */
	public List<CongruousFactorType> getBidCongruousFactorType(String supplierId,String packId){
		logger.debug("\nes PackCongFactorResponseServiceImpl||getBidCongruousFactorType\n");
		return packCongFactorResponseDaoHibernate.getBidCongruousFactorType(supplierId, packId);
	}
	
	/**
	 * 
	 * Description :根据项目获得所有指标响应与包件中间表 
	 * Create Date: 2010-10-11上午09:27:01 by liuke  Modified Date: 2010-10-11上午09:27:01 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<PackCongFactorResponse> getAllPackCongFactorResponseListByProjectId(String projectId) {
		logger.debug("\n PackCongFactorResponseServiceImpl||getAllPackCongFactorResponseListByProjectId\n");
		return packCongFactorResponseDaoHibernate.getAllPackCongFactorResponseListByProjectId(projectId);
	}
}
