package com.gpcsoft.agreement.bargin.bidding.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecord;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordService;
import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.signuprecord.dao.SignUprecordDao;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;

@Service 
public class BiddingRecordServiceImpl extends BaseGenericServiceImpl<BiddingRecord> implements BiddingRecordService {
	
	@Autowired(required=true) @Qualifier("biddingRecordDaoHibernate")
	private BiddingRecordDao biddingRecordDaoHibernate;
	
	@Autowired(required=true) @Qualifier("signUprecordDaoHibernate")
	private SignUprecordDao signUprecordDao;
	
	/** 
	 * Description :  根据项目ID获取报价信息（多信息）
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecord> getBiddingRecordByProjectId_allInfo(String projId, String turnId) throws Exception {
		//供应商报名数据
		List<SignUprecord> signUprecordList = new ArrayList<SignUprecord>(); 
		//报价信息
		List<BiddingRecord> biddingRecordList  = null; 
		//取所有供应商的数据(包括fetch多信息)
		signUprecordList = signUprecordDao.getSignUprecordByprojectId(projId);
		biddingRecordList = biddingRecordDaoHibernate.getBiddingRecordByProjectId_allInfo(projId,turnId);
		//结果数据(包含未报价的供应商(臆造数据))
		List<BiddingRecord> biddingRecordResult  = new ArrayList<BiddingRecord>();
		for(SignUprecord signUprecord: signUprecordList){
			boolean hasRecord = false;
			for(BiddingRecord biddingRecord: biddingRecordList){
				if(signUprecord.getSupplier().getObjId().equals(biddingRecord.getSupplier().getObjId())){
					biddingRecordResult.add(biddingRecord);
					hasRecord = true;
				}
			}
			if(!hasRecord){//没有报价则造一条数据
				BiddingRecord biddingRecord = new BiddingRecord();
				biddingRecord.setProject(signUprecord.getProject());
				BargainTurn bargainTurn = new BargainTurn();
				bargainTurn.setObjId(turnId);
				biddingRecord.setBargainTurn(bargainTurn);
				biddingRecord.setSupplier(signUprecord.getSupplier());
				biddingRecordResult.add(biddingRecord);
			}
		}
		return biddingRecordResult;
	}
	
	/** 
	 * Description :  根据项目ID、供应商ID、轮次ID 获取供应商的报价历史
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecord> getSupplierBiddingHistory(String projId,String supplierId,String turnId) throws Exception {
		return biddingRecordDaoHibernate.getSupplierBiddingHistory(projId,supplierId,turnId);
	}
	
	/** 
	 * Description :  根据项目ID和供应商ID获取供应商的报价信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public BiddingRecord getSupplierBiddingLastRecords(String projId,String supplierId,String turnId) throws Exception {
		return biddingRecordDaoHibernate.getSupplierBiddingLastRecords(projId,supplierId,turnId);
	}
	
	/** 
	 * Description :  根据项目ID获取最高、最低、平均报价
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object> getMaxMinAvgPriceByProjId(String objId) throws Exception {
		return biddingRecordDaoHibernate.getMaxMinAvgPriceByProjId(objId);
	}
	
	/** 
	 * Description :  取得每个供应商最低报价 (对象)
	 * Create Date: 2010-10-21下午09:23:44 by yucy  Modified Date: 2010-10-21下午09:23:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecord> getMinBiddingRecordByProjectId(String objId,String turnId) throws Exception {
		return biddingRecordDaoHibernate.getMinBiddingRecordByProjectId(objId,turnId ,null);
	}
	
	/** 
	 * Description :  取得每个供应商最低议价报价 (对象)
	 * Create Date: 2010-10-21下午09:23:44 by yucy  Modified Date: 2010-10-21下午09:23:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecord> getMinBiddingRecordByTalkProjectId(String objId,String turnId) throws Exception {
		return biddingRecordDaoHibernate.getMinBiddingRecordByTalkProjectId(objId,turnId ,null);
	}
	
	/**
	 * Description :  取得单个供应商项目的最低报价
	 * Create Date: 2010-10-22上午11:05:46 by yucy  Modified Date: 2010-10-22上午11:05:46 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	public BiddingRecord getMinBiddingRecordByProjectAndSupplier( Map<String, Object> param) throws Exception {
		return biddingRecordDaoHibernate.getMinBiddingRecordByProjectAndSupplier(param);
	}
	
	
	/** 
	 * Description :  根据轮次ID和项目ID获得报价排名
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getSelfRanking(String projId,String turnId) throws Exception {
		return biddingRecordDaoHibernate.getSelfRanking(projId,turnId);
	}
	
	/** 
	 * Description :  获取轮次的最低报价
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getMinTurnBargainPrice(String projId,String turnId) throws Exception {
		return biddingRecordDaoHibernate.getMinTurnBargainPrice(projId, turnId);
	}

	/** 
	 * Description :    根据项目ID、供应商ID、轮次ID 获取供应商的最低报价(所有报名的供应商)
	 * Create Date: 2010-10-26下午10:09:25 by yucy  Modified Date: 2010-10-26下午10:09:25 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecord> getMinBiddingRecordBySupplyProjIdturnId( String projId, String turnId, String supplierId)throws Exception {
		
		//供应商报名数据
		List<SignUprecord> signUprecordList = new ArrayList<SignUprecord>(); 
		
		//报价信息
		List<BiddingRecord> biddingRecordList  = null; 
		
		//取单个供应商的数据
		if(StringUtils.hasLength(supplierId)&&StringUtils.hasLength(projId)){
			SignUprecord signUprecord = signUprecordDao.getSignUprecordByprojectIdAndSupplierId(projId, supplierId);
			signUprecordList.add(signUprecord);
			biddingRecordList = biddingRecordDaoHibernate.getMinBiddingRecordByProjectId(projId,turnId,supplierId);
		}
		//取所有供应商的数据
		else{
			signUprecordList = signUprecordDao.getSignUprecordByprojectId(projId);
			biddingRecordList = biddingRecordDaoHibernate.getMinBiddingRecordByProjectId(projId,turnId,null);
		}
		
		//结果数据(包含未报价的供应商(臆造数据))
		List<BiddingRecord> biddingRecordResult  = new ArrayList<BiddingRecord>();
		
		for(SignUprecord signUprecord: signUprecordList){
			boolean hasRecord = false;
			for(BiddingRecord biddingRecord: biddingRecordList){
				if(signUprecord.getSupplier().getObjId().equals(biddingRecord.getSupplier().getObjId())){
					biddingRecordResult.add(biddingRecord);
					hasRecord = true;
				}
			}
			if(!hasRecord){//没有报价则造一条数据
				BiddingRecord biddingRecord = new BiddingRecord();
				biddingRecord.setSupplier(signUprecord.getSupplier());
				biddingRecordResult.add(biddingRecord);
			}
		}
		return biddingRecordResult;
	}
}
