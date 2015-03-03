package com.gpcsoft.smallscale.pointmall.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.smallscale.point.dao.ConsumeDao;
import com.gpcsoft.smallscale.point.domain.Consume;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.smallscale.pointmall.dao.GiftDao;
import com.gpcsoft.smallscale.pointmall.dao.GiftExchangeRuleDao;
import com.gpcsoft.smallscale.pointmall.dao.VirtualGiftRecordDao;
import com.gpcsoft.smallscale.pointmall.domain.Gift;
import com.gpcsoft.smallscale.pointmall.domain.GiftExchangeRule;
import com.gpcsoft.smallscale.pointmall.domain.VirtualGiftRecord;
import com.gpcsoft.smallscale.pointmall.manager.VirtualGiftRecordManager;
import com.gpcsoft.smallscale.pointmall.service.VirtualGiftRecordService;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Service 
public class VirtualGiftRecordServiceImpl extends BaseGenericServiceImpl<VirtualGiftRecord> implements VirtualGiftRecordService {

	@Autowired(required=true) @Qualifier("virtualGiftRecordManagerImpl")
	private VirtualGiftRecordManager virtualGiftRecordManager;
	
	@Autowired(required=true)  @Qualifier("giftExchangeRuleDaoHibernate")
	private GiftExchangeRuleDao giftExchangeRuleDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("consumeDaoHibernate")
	private ConsumeDao consumeDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("giftDaoHibernate")
	private GiftDao giftDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("virtualGiftRecordDaoHibernate")
	private VirtualGiftRecordDao virtualGiftRecordDao;
	
	
	/**
	 * 根据礼品规则保存虚拟礼品
	 * @param eruleId
	 * @param giftCount
	 */
	public void save(String giftId,String eruleId,Integer giftCount,String email){
		GiftExchangeRule erule = giftExchangeRuleDaoHibernate.get(eruleId);
		Gift g = giftDaoHibernate.get(giftId);
		for(int i=0;i<giftCount;i++){
			VirtualGiftRecord gift = new VirtualGiftRecord();
			gift.setGift(g);
			gift.setErule(erule);
			gift.setDealStatus(SmallscaleEnum.DEAL_STATUS_NO);
			gift.setReceiveEmail(email);
			virtualGiftRecordManager.save(gift);
			
			//生成消费记录			
			Consume consume = new Consume();
			consume.setConsumeType(SmallscaleEnum.CURRENT_TYPE_GIFT);
			consume.setUserId(gift.getCreateUser());
			consume.setConsumeDate(new Date());
			consume.setConsumeNumber(erule.getScore().longValue());	
			consume.setConsumeProjectId(gift.getObjId());
			consumeDaoHibernate.save(consume);
		}
	}
	
	/**
	 * 礼品处理
	 * @param virtualGiftRecord
	 */
	public void saveDeal(VirtualGiftRecord virtualGiftRecord){
		VirtualGiftRecord  newGiftRecord = virtualGiftRecordManager.get(virtualGiftRecord.getObjId());
		
		Gift gift = giftDaoHibernate.get(newGiftRecord.getGift().getObjId());
		gift.setExchangeCount(gift.getExchangeCount() - 1);
		newGiftRecord.setGift(gift);//礼品库存
		
		newGiftRecord.setDealStatus(virtualGiftRecord.getDealStatus());
		
		User user = AuthenticationHelper.getCurrentUser(true);	
	    
		if(virtualGiftRecord.getDealStatus().equals(SmallscaleEnum.DEAL_STATUS_OUTSTOCK)){//缺货			
			Consume consume  = consumeDaoHibernate.getByProjectId(virtualGiftRecord.getObjId());
			if(consume != null){//缺货 消费记录中的消费积分设置为0
				consume.setConsumeNumber(0L);
				consumeDaoHibernate.save(consume);
			}
		}		
		else if(virtualGiftRecord.getDealStatus().equals(SmallscaleEnum.DEAL_STATUS_YES)){//收货
			newGiftRecord.setCardCode(virtualGiftRecord.getCardCode());
			newGiftRecord.setPassword(virtualGiftRecord.getPassword());
			newGiftRecord.setDealTime(new Date());
			newGiftRecord.setDealUser(user);
		}	
		
		
		super.save(newGiftRecord);	
		
		//发送邮件
		this.sendMail(newGiftRecord);
	}
	
	
	/**
	 * 发送礼品兑换处理邮件
	 * @param virtualGiftRecord
	 */
	private void sendMail(VirtualGiftRecord virtualGiftRecord){
		
		List<String> emailAddrs = new ArrayList<String>();		
		
		//邮箱地址
		if (null != virtualGiftRecord.getReceiveEmail()) {		
				emailAddrs.add(virtualGiftRecord.getReceiveEmail());			
		}		
		String templateName = null;
		if(virtualGiftRecord.getDealStatus().equals(SmallscaleEnum.DEAL_STATUS_OUTSTOCK)){//缺货				
			templateName = messageSource.getMessage("smallscale.pointmall.virtualGift.less.template");
		}		
		else if(virtualGiftRecord.getDealStatus().equals(SmallscaleEnum.DEAL_STATUS_YES)){//发货			
			templateName = messageSource.getMessage("smallscale.pointmall.virtualGift.gave.template");
		}
		
		Map<String, Object> model = new HashMap<String, Object>();		
		
		model.put("now", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		String siteUrl = SysInfo.getInstance().getSitename();
       
		model.put("virtualGiftRecord", virtualGiftRecord);
        model.put("siteUrl", siteUrl);		
        model.put("user",virtualGiftRecord.getCreateUser() );
		try{
			//发送邮件
			MailUtil.sendEmailAlways(
					(String[]) emailAddrs.toArray(new String[emailAddrs.size()]),
					messageSource.getMessage("smallscale.pointmall.virtualGift.title"),
					null, 
					templateName,
					model);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/** 
	 * Description :   获得记录列表
	 * Create Date: 2011-1-16下午11:43:43 by yucy  Modified Date: 2011-1-16下午11:43:43 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<VirtualGiftRecord> getVirtualListByGiftId(String objId) throws Exception {
		return virtualGiftRecordDao.getVirtualListByGiftId(objId);
	}

}
