package com.gpcsoft.smallscale.vote.service.impl;

import java.util.List;
import java.util.Map;


import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goods.domain.GoodsBrand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.smallscale.vote.manager.VoteAssessedThingManager;
import com.gpcsoft.smallscale.vote.service.VoteAssessedThingService;
import com.gpcsoft.smallscale.vote.dao.VoteAssessedThingDao;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedThing;

@Service 
public class VoteAssessedThingServiceImpl extends BaseGenericServiceImpl<VoteAssessedThing> implements VoteAssessedThingService {

	@Autowired(required=true) @Qualifier("voteAssessedThingManagerImpl")
	private VoteAssessedThingManager voteAssessedThingManager;
	
	@Autowired(required=true)  @Qualifier("voteAssessedThingDaoHibernate")
	private VoteAssessedThingDao voteAssessedThingDaoHibernate;
	
	/**
	 * 获取主题下的评选单位
	 */
	public List<VoteAssessedThing> findAssessedThingOfTemplate(String templateId) {
		return voteAssessedThingManager.findAssessedThingOfTemplate(templateId);
	}

	/**
	 * 是否推荐(isRecommended)
	 */
	public Boolean updateIsRecommendedStatus(Map<String, Object> params) {
		return voteAssessedThingDaoHibernate.updateIsRecommendedStatus(params);
	}

	/**
	 * 判断是否存在参选对象
	 */
	public Boolean isExitAttender(Map<String, Object> params) {
		return voteAssessedThingDaoHibernate.isExitAttender(params);
	}

	/**
	 * 根据所属机构和品牌名称获取品牌对象
	 */
	public GoodsBrand getGoodsBrandByNameAndBelongs(String belongsId,String brandName) {
		return voteAssessedThingDaoHibernate.getGoodsBrandByNameAndBelongs(belongsId, brandName);
	}

	/**
	 * 设置排序号
	 */
	public Boolean setNumSort(Map<String, Object> params) {
		return voteAssessedThingDaoHibernate.setNumSort(params);
	}

}
