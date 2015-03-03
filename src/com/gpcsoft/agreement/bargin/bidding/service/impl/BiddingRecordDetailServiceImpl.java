package com.gpcsoft.agreement.bargin.bidding.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordDetailDao;
import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordHistoryDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordHistory;
import com.gpcsoft.agreement.bargin.bidding.manager.BiddingRecordDetailManager;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordDetailService;
import com.gpcsoft.agreement.bargin.project.dao.BargainTurnDao;
import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.manager.ProjProcessRuleManager;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

@Service 
public class BiddingRecordDetailServiceImpl extends BaseGenericServiceImpl<BiddingRecordDetail> implements BiddingRecordDetailService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("biddingRecordDetailManagerImpl")
	private BiddingRecordDetailManager biddingRecordDetailManager;
	
	@Autowired(required=true) @Qualifier("biddingRecordDetailDaoHibernate")
	private BiddingRecordDetailDao biddingRecordDetailDaoHibernate;
	
	@Autowired(required=true) @Qualifier("biddingRecordHistoryDaoHibernate")
	private BiddingRecordHistoryDao biddingRecordHistoryDao;
	
	@Autowired(required=true) @Qualifier("bargainTurnDaoHibernate")
	private BargainTurnDao bargainTurnDao;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("projProcessRuleManagerImpl")
	private ProjProcessRuleManager projProcessRuleManager;

	/** 
	 * Description : 获取最低报价  
	 * Create Date: 2011-5-17下午12:05:37 by yucy  Modified Date: 2011-5-17下午12:05:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getMinRecord(String objId) throws Exception {
		return biddingRecordDetailDaoHibernate.getMinRecord(objId);
	}

	/** 
	 * Description :  获取各供应商的报价,按需求
	 * Create Date: 2011-5-16下午06:39:08 by yucy  Modified Date: 2011-5-16下午06:39:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> loadRecordByRequire(String requireId ,Map<String, Object> param)throws Exception {
		return biddingRecordDetailDaoHibernate.loadRecordByRequire(requireId ,param);
	}
	
	/** 
	 * Description :  获取供应商对需求的所有报价
	 * Create Date: 2011-5-20下午02:36:03 by yucy  Modified Date: 2011-5-20下午02:36:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getDetailBySupplierAndRequire(String supplierId,String requireId) throws Exception {
		return biddingRecordDetailDaoHibernate.getDetailBySupplierAndRequire(supplierId,requireId);
	}

	/** 
	 * Description : 更新选中状态 
	 * Create Date: 2011-5-17下午06:43:12 by yucy  Modified Date: 2011-5-17下午06:43:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> updateRecordChooseStatus(String changedIdandValue ,String requireId) throws Exception {
		return biddingRecordDetailDaoHibernate.updateRecordChooseStatus(changedIdandValue , requireId);
	}

	/** 
	 * Description :  保存排序
	 * Create Date: 2011-5-24下午02:31:18 by yucy  Modified Date: 2011-5-24下午02:31:18 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveSort(String[] orderArray) throws Exception {
		return biddingRecordDetailDaoHibernate.saveSort(orderArray);
	}

	/**
	 * Description :  获取我报过的最低价
	 * Create Date: 2011-5-26上午10:39:06 by yucy  Modified Date: 2011-5-26上午10:39:06 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Object[]> getMyMinRecord(String projectId,String orgId) throws Exception {
		return biddingRecordDetailDaoHibernate.getMyMinRecord( projectId ,orgId );
	}
	
	/** 
	 * Description :  保存报价信息[保存报价和保存历史]
	 * Create Date: 2011-5-26下午12:49:57 by sunl  Modified Date: 2011-5-26下午12:49:57 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveBargain(BiddingRecordDetail bargainDetail,BargainTurn bargainTurn) throws Exception {
		//保存报价
		biddingRecordDetailDaoHibernate.save(bargainDetail);
		
		//保存报价历史
		BiddingRecordHistory bargainHistory = new BiddingRecordHistory();
		bargainHistory.setBiddingRecordDetail(bargainDetail);
		bargainHistory.setBarginTime(bargainDetail.getBarginTime());
		bargainHistory.setCreateTime(bargainDetail.getCreateTime());
		bargainHistory.setCreateUser(bargainDetail.getCreateUser());
		bargainHistory.setGoodsPrice(bargainDetail.getGoodsPrice());
		bargainHistory.setGoodsTotal(bargainDetail.getGoodsTotal());
		bargainHistory.setBargainTurn(bargainTurn);
		biddingRecordHistoryDao.save(bargainHistory);
	}
	
	/** 
	 * Description :  重新报价[更新报价和更新历史报价]
	 * Create Date: 2011-5-26下午12:49:57 by sunl  Modified Date: 2011-5-26下午12:49:57 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateBargain(Map<String,Object> params) throws Exception {
		String bargainDetailId = (String)params.get("bargainDetailId");
		String turnId = (String)params.get("turnId");
		Double newPrice = Double.valueOf((String)params.get("newPrice"));
		Double newTotal = Double.valueOf((String)params.get("newTotal"));
		
		//更新报价
		BiddingRecordDetail bargainDetail = biddingRecordDetailDaoHibernate.get(bargainDetailId);
		bargainDetail.setGoodsPrice(BigDecimal.valueOf(newPrice));
		bargainDetail.setGoodsTotal(BigDecimal.valueOf(newTotal));
		
		//保存历史报价
		BargainTurn turn = bargainTurnDao.get(turnId);
		BiddingRecordHistory history = new BiddingRecordHistory();
		history.setBarginTime(new Date());
		history.setCreateUser(bargainDetail.getCreateUser());
		history.setCreateTime(new Date());
		history.setBiddingRecordDetail(bargainDetail);
		history.setBargainTurn(turn);
		history.setGoodsPrice(BigDecimal.valueOf(newPrice));
		history.setGoodsTotal(BigDecimal.valueOf(newTotal));
		biddingRecordHistoryDao.save(history);
	}
	
	/** 
	 * Description :  根据项目id、需求条目id、供应商id查询报价
	 * Create Date: 2011-5-26下午03:08:08 by sunl  Modified Date: 2011-5-26下午03:08:08 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getBiddingDetailList(Map<String,Object> params) throws Exception {
		return biddingRecordDetailDaoHibernate.getBiddingDetailList(params);
	}

	/** 
	 * Description :  获取我的排名
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getMyRanking(String projId, String requireId,String supplierId) throws Exception {
		return biddingRecordDetailDaoHibernate.getMyRanking(projId, requireId, supplierId);
	}

	/** 
	 * Description :  需求条目最低报价[根据项目、供应商、需求条目]
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getRequireMinPrice(String projId,String requireId, String supplierId) throws Exception {
		return biddingRecordDetailDaoHibernate.getRequireMinPrice(projId, requireId, supplierId);
	}

	/** 
	 * Description :  获取最低报价(确认成交供应商)
	 * Create Date: 2011-6-1上午09:55:47 by yucy  Modified Date: 2011-6-1上午09:55:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getMinRecordAndDetail(String projectId) throws Exception {
		return biddingRecordDetailDaoHibernate.getMinRecordAndDetail(projectId);
	}

	/** 
	 * Description : 获取中标供应商的最低报价或选中的报价
	 * Create Date: 2011-6-13下午01:30:48 by yucy  Modified Date: 2011-6-13下午01:30:48 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getMinOrSelectRecordDetail(String projectId,String dealSupplier) throws Exception {
		return biddingRecordDetailDaoHibernate.getMinOrSelectRecordDetail(projectId,dealSupplier);
	}

	/**
	 * 
	 * Description :  更新为成交报价
	 * Create Date: 2011-6-13下午04:17:00 by yucy  Modified Date: 2011-6-13下午04:17:00 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<BiddingRecordDetail> updateRecordDetailForResult( String detailIds) throws Exception {
		return biddingRecordDetailDaoHibernate.updateRecordDetailForResult(detailIds);
	}

	/** 
	 * Description :  获取成交报价条目(按供应商)
	 * Create Date: 2011-6-14上午10:49:12 by yucy  Modified Date: 2011-6-14上午10:49:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecordDetail> getDealRecordDetail(Map<String, Object> param) throws Exception {
		return biddingRecordDetailDaoHibernate.getDealRecordDetail(param);
	}

	/** 
	 * Description :  获取供应商报价排名显示FusionChart的XML数据
	 * Create Date: 2011-7-1上午09:59:34 by likg  Modified Date: 2011-7-1上午09:59:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getBiddingRankChartXml(Map<String, Object> param) throws Exception {
		String supplierId = (String) param.get("supplierId"); //当前供应商机构Id
		String projId = (String) param.get("projId"); //项目Id
		Map<String, Object> templateMap = new HashMap<String, Object>();
		
		//获取项目是否显示最高最低报价的规则
		ProjProcessRule projRule1 = projProcessRuleManager.getProjProcessRuleByProjectIdAndCode(projId, "maxPrice");
		ProjProcessRule projRule2 = projProcessRuleManager.getProjProcessRuleByProjectIdAndCode(projId, "minPrice");
		templateMap.put("showMaxPrice", projRule1.getResValue());
		templateMap.put("showMinPrice", projRule2.getResValue());
		
		//获取供应商的最低报价的排名信息
		List<Object[]> rankList = biddingRecordDetailDaoHibernate.getBiddingRankChartDate(param);
		
		//获取模板需要的数据
		if(rankList!=null && rankList.size()>0) {
			if(rankList.size()==1) {
				templateMap.put("minPrice", "0");
			} else {
				templateMap.put("minPrice", rankList.get(0)[1]);
			}
			templateMap.put("maxPrice", rankList.get(rankList.size()-1)[1]);
		}
		templateMap.put("rankList", rankList);
		templateMap.put("size", rankList.size());
		templateMap.put("supplierId", supplierId);
		
		//根据模板生成显示FusionChart的XML
		String contentsString = "";
		try {
			contentsString = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.BIDDING_RANK_TEMPLATE), templateMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contentsString;
	}

	/** 
	 * Description :  获取供应商报价排名数据
	 * Create Date: 2011-7-1上午09:42:38 by likg  Modified Date: 2011-7-1上午09:42:38 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getBiddingRankData(Map<String, Object> param) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String supplierIds = ""; //供应商ids
		String minPrices = ""; //最低价
		
		//获取供应商的最低报价的排名信息
		List<Object[]> rankList = biddingRecordDetailDaoHibernate.getBiddingRankChartDate(param);
		for(Object[] objs : rankList) {
			supplierIds += objs[0] + ",";
			minPrices += objs[1] + ",";
		}
		if(supplierIds != "") {
			supplierIds = supplierIds.substring(0, supplierIds.length()-1);
			minPrices = minPrices.substring(0, minPrices.length()-1);
		}
		result.put("supplierIds", supplierIds);
		result.put("minPrices", minPrices);
		
		return result;
	}
}
