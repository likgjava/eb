package com.gpcsoft.smallscale.pointmall.service.impl;

import java.util.Date;
import java.util.List;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.point.dao.ConsumeDao;
import com.gpcsoft.smallscale.point.domain.Consume;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.smallscale.pointmall.manager.RealGiftRecordManager;
import com.gpcsoft.smallscale.pointmall.service.RealGiftRecordService;
import com.gpcsoft.smallscale.pointmall.dao.GiftDao;
import com.gpcsoft.smallscale.pointmall.dao.GiftExchangeRuleDao;
import com.gpcsoft.smallscale.pointmall.dao.RealGiftRecordDao;
import com.gpcsoft.smallscale.pointmall.domain.Gift;
import com.gpcsoft.smallscale.pointmall.domain.GiftExchangeRule;
import com.gpcsoft.smallscale.pointmall.domain.RealGiftRecord;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class RealGiftRecordServiceImpl extends BaseGenericServiceImpl<RealGiftRecord> implements RealGiftRecordService {

	@Autowired(required=true) @Qualifier("realGiftRecordManagerImpl")
	private RealGiftRecordManager realGiftRecordManager;
	
	@Autowired(required=true)  @Qualifier("giftDaoHibernate")
	private GiftDao giftDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("giftExchangeRuleDaoHibernate")
	private GiftExchangeRuleDao giftExchangeRuleDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("consumeDaoHibernate")
	private ConsumeDao consumeDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("realGiftRecordDaoHibernate")
	private RealGiftRecordDao realGiftRecordDao;
	
	/**
	 * 保存实物兑换记录
	 * @param realGiftRecord
	 * @param giftId
	 * @param eruleId
	 */
	public void saveRecord(RealGiftRecord realGiftRecord,String giftId,String eruleId){
		
		Gift gift = giftDaoHibernate.get(giftId);
		
		//减去库存数量
		gift.setExchangeCount(gift.getExchangeCount() - realGiftRecord.getGiftCount());
		GiftExchangeRule erule = giftExchangeRuleDaoHibernate.get(eruleId);
		realGiftRecord.setGift(gift);
		realGiftRecord.setErule(erule);
		realGiftRecord.setDealStatus(SmallscaleEnum.DEAL_STATUS_NO);
		realGiftRecordManager.save(realGiftRecord);		
		
		
		//生成消费记录
		long consumeNumber = erule.getScore().longValue() * realGiftRecord.getGiftCount().longValue();
		Consume consume = new Consume();
		consume.setConsumeType(SmallscaleEnum.CURRENT_TYPE_GIFT);
		consume.setUserId(realGiftRecord.getCreateUser());
		consume.setConsumeDate(new Date());
		consume.setConsumeNumber(consumeNumber);	
		consume.setConsumeProjectId(realGiftRecord.getObjId());
		consumeDaoHibernate.save(consume);
		
	}
	
	/**
	 * 处理兑换
	 * @param realGiftRecord
	 */
	public void saveDealRecord(RealGiftRecord realGiftRecord){
		RealGiftRecord newGift = realGiftRecordManager.get(realGiftRecord.getObjId());
		newGift.setDealStatus(realGiftRecord.getDealStatus());
		if( null != realGiftRecord.getFpostType() &&  !realGiftRecord.getFpostType().equals("")){
			newGift.setFpostType(realGiftRecord.getFpostType());
		}
		if( null != realGiftRecord.getFpostNumber() &&  !realGiftRecord.getFpostNumber().equals("")){
			newGift.setFpostNumber(realGiftRecord.getFpostNumber());
		}		
		
		if(realGiftRecord.getDealStatus().equals(SmallscaleEnum.DEAL_STATUS_OUTSTOCK)){//缺货			
			Consume consume  = consumeDaoHibernate.getByProjectId(newGift.getObjId());
			if(consume != null){//缺货 消费记录中的消费积分设置为0
				consume.setConsumeNumber(0L);
				consumeDaoHibernate.save(consume);
			}
			//还原库存礼品数量
			Gift gift = newGift.getGift();
			gift.setExchangeCount(gift.getExchangeCount()+ newGift.getGiftCount() );
		}
		
		if(realGiftRecord.getDealStatus().equals(SmallscaleEnum.DEAL_STATUS_RECEIVE)){//收货
			newGift.setReceiveTime(new Date());
		}
		else{
			User user = AuthenticationHelper.getCurrentUser(true);
			newGift.setDealUser(user);
			newGift.setDealTime(new Date());
		}		
		realGiftRecordManager.save(newGift);
	}

	/** 
	 * Description :  获得记录列表
	 * Create Date: 2011-1-16下午11:42:53 by yucy  Modified Date: 2011-1-16下午11:42:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<RealGiftRecord> getRecordListByGiftId(String objId) throws Exception {
		return realGiftRecordDao.getRecordListByGiftId(objId);
	}

}
