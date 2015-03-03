package com.gpcsoft.smallscale.vote.manager.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.vote.dao.VoteAssessedDao;
import com.gpcsoft.smallscale.vote.dao.VotePointerDao;
import com.gpcsoft.smallscale.vote.manager.VoteAssessedManager;
import com.gpcsoft.smallscale.vote.domain.VoteAssessed;
import com.gpcsoft.smallscale.vote.domain.VotePointer;

@Repository
public class VoteAssessedManagerImpl extends BaseManagerImpl<VoteAssessed> implements VoteAssessedManager {

	@Autowired(required=true)  @Qualifier("voteAssessedDaoHibernate")
	private VoteAssessedDao voteAssessedDaoHibernate;
	
	@Autowired(required=true) @Qualifier("votePointerDaoHibernate")
	private VotePointerDao votePointerDaoHibernate;

	/**
	 * 验证当前登录用户是否对该主题下的此单位进行过评分
	 */
	public boolean isUnique(String templateId, String voteAssessedThingId,
			String userId,String isSingleVote) {
		return voteAssessedDaoHibernate.isUnique(templateId, voteAssessedThingId, userId,isSingleVote);
	}
	
	/**
	 * 展示主题的投票指标
	 */
	public Map<String, Object> findUnfurlPointerOfTemplate(String templateId,
			String voteAssessedThingId) {
		Map<String, Object> model = new HashMap<String, Object>();
		//把id抛到页面
		model.put("templateId",templateId);
		model.put("voteAssessedThingId",voteAssessedThingId);
		
		//获取全部指标
		List<VotePointer> votePointerList = votePointerDaoHibernate.findPointOfTemplate(templateId,null);
		model.put("votePointerList", votePointerList);
		
		return model;
	}

	/**
	 * 单一单位的票数数据统计展示
	 */
	public Map<String, Object> voteStatisticUnfurl(String templateId,
			String voteAssessedThingId) {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取全部指标
		List<VotePointer> votePointerList = votePointerDaoHibernate.findPointOfTemplate(templateId,null);
		model.put("votePointerList", votePointerList);
		
		//单位投票数
		List<Object> statisticVoteCounList = voteAssessedDaoHibernate.countVoteAssessedNum(templateId,voteAssessedThingId);
		String statisticVoteCount = "0";
		if(statisticVoteCounList.size()>0){
			Object[] statisticCount = (Object[]) statisticVoteCounList.get(0);
			statisticVoteCount = statisticCount[1].toString();
		}
		model.put("statisticVoteCount", statisticVoteCount);
		
		//平均成绩  Object[5]
		List<Object> assessedAvgGradeList = voteAssessedDaoHibernate.countAvgGradeOfPointer(templateId, voteAssessedThingId);
		model.put("assessedAvgGradeList", assessedAvgGradeList);
		
		//最终成绩    Object[]数组5个元素
		BigDecimal assessedFinalGrade = new BigDecimal("0.0");
		for(int i=0;i<assessedAvgGradeList.size();i++){
			Object[] avgGrade = (Object[]) assessedAvgGradeList.get(i);
			assessedFinalGrade = assessedFinalGrade.add((BigDecimal)avgGrade[4]);
		}
		model.put("assessedFinalGrade", assessedFinalGrade);
		return model;
	}

	/**
	 *  统计参与单位的投票数
	 */
	public List<Object> countVoteAssessedNum(String templateId) {
		return voteAssessedDaoHibernate.countVoteAssessedNum(templateId,null);
	}

}
