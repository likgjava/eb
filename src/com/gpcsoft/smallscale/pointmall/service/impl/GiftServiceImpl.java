package com.gpcsoft.smallscale.pointmall.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.SequenceNumberUtil;
import com.gpcsoft.smallscale.pointmall.dao.GiftDao;
import com.gpcsoft.smallscale.pointmall.dao.GiftSeriesDao;
import com.gpcsoft.smallscale.pointmall.domain.Gift;
import com.gpcsoft.smallscale.pointmall.domain.GiftExchangeRule;
import com.gpcsoft.smallscale.pointmall.domain.GiftSeries;
import com.gpcsoft.smallscale.pointmall.manager.GiftManager;
import com.gpcsoft.smallscale.pointmall.service.GiftService;

@Service 
public class GiftServiceImpl extends BaseGenericServiceImpl<Gift> implements GiftService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("giftManagerImpl")
	private GiftManager giftManager;
	
	@Autowired(required=true) @Qualifier("giftDaoHibernate")
	private GiftDao giftDao;
	
	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("giftSeriesDaoHibernate")
	private GiftSeriesDao giftSeriesDao;
	
	/** 
	 * Description :  保存
	 * Create Date: 2011-1-7下午05:03:57 by yucy  Modified Date: 2011-1-7下午05:03:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Gift saveGift(Map<String, Object> param) throws Exception {
		Gift gift = (Gift)param.get("gift");
		
		//拼装对象
		String ruleStr = (String)param.get("ruleStr");
		
		if(StringUtils.hasLength(ruleStr)){
			Set<GiftExchangeRule> ruleSet = new HashSet<GiftExchangeRule>();
			for(String cellRule: ruleStr.split(",")){
				String [] cell = cellRule.split(":");
				GiftExchangeRule rule = new GiftExchangeRule();
				if(StringUtils.hasLength(cell[0])&&!"undefined".equals(cell[0])){
					rule.setObjId(cell[0]);
				}
				if(StringUtils.hasLength(cell[1])&&!"".equals(cell[1])){
					rule.setAmount(new BigDecimal(cell[1]));
				}
				if(StringUtils.hasLength(cell[2])&&!"".equals(cell[2])){
					rule.setScore(new Integer(cell[2]));
				}
				rule.setIsUsed(true);//设置为启用
				
				if(StringUtils.hasLength(rule.getObjId())){
					rule.setCreateUser(AuthenticationHelper.getCurrentUser(true));
					rule.setCreateTime(new Date());
				}
				
				rule.setGiftId(gift.getObjId());
				ruleSet.add(rule);
			}
			gift.setGiftExchangeRuleSet(ruleSet);
		}
		
		//生成编号
		if(!StringUtils.hasLength(gift.getGiftCode())){
			gift.setGiftCode(SequenceNumberUtil.getGiftSN());
		}
		
		//默认启用
		gift.setIsUsed(true);
		
		return giftDao.save(gift);
	}

	/** 
	 * Description :查询礼品
	 * Create Date: 2011-1-10上午09:09:49 by yucy  Modified Date: 2011-1-10上午09:09:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Gift> getGiftList(Map<String, Object> paramsMap) throws Exception {
		return giftDao.getGiftList(paramsMap);
	}

	/** 
	 * Description :  查询实际礼品和系列
	 * Create Date: 2011-1-10上午09:51:57 by yucy  Modified Date: 2011-1-10上午09:51:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Map<String, Object>> getSeriesAndReallyGift(Map<String, Object> paramsMap) throws Exception {
		
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		GiftSeries  giftSeries = (GiftSeries)paramsMap.get("giftSeries");
		
		List<Gift> giftList =  giftDao.getGiftList(paramsMap);
		
		if(giftSeries!=null){
			for(GiftSeries  children: giftSeries.getChildren()){
				Map<String, Object> cellMap = new HashMap<String, Object>();
				cellMap.put("giftSeries", children);
				List<Gift> giftCellList  =new ArrayList<Gift>();
				for(Gift gift:giftList){
					if(children.getObjId().equals(gift.getGiftSeries().getObjId())&&giftCellList.size()<4){//不超过五条
						giftCellList.add(gift);
					}
				}
				cellMap.put("giftList", giftCellList);
				
				result.add(cellMap);
			}
		}
		
		return result;
	}

	/** 
	 * Description :  查询礼品信息详情
	 * Create Date: 2011-1-10下午02:43:57 by yucy  Modified Date: 2011-1-10下午02:43:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Gift getGiftAllInfo(Map<String, Object> paramsMap) throws Exception {
		return giftDao.get((String)paramsMap.get("objId"));//待扩展
	}

	/** 
	 * Description : 更新是否推荐 
	 * Create Date: 2011-1-10下午11:20:01 by yucy  Modified Date: 2011-1-10下午11:20:01 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateGiftReCommond(Map<String, Object> param) throws Exception {
		return giftDao.updateGiftReCommond(param);
	}

	/** 
	 * Description :  取得礼品列表
	 * Create Date: 2011-1-11下午01:51:07 by yucy  Modified Date: 2011-1-11下午01:51:07 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Gift> getGiftListForShow(Page<Gift> page, Map<String, Object> paramsMap) throws Exception {
		return giftDao.getGiftListForShow(page,paramsMap);
	}
}
