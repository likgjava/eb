package com.gpcsoft.smallscale.vote.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.smallscale.vote.manager.VoteTemplateManager;
import com.gpcsoft.smallscale.vote.service.VoteTemplateService;
import com.gpcsoft.smallscale.vote.dao.VoteAssessedDao;
import com.gpcsoft.smallscale.vote.dao.VoteAssessedThingDao;
import com.gpcsoft.smallscale.vote.dao.VotePointerDao;
import com.gpcsoft.smallscale.vote.dao.VoteTemplateDao;
import com.gpcsoft.smallscale.vote.dao.VoteUnitGroupDao;
import com.gpcsoft.smallscale.vote.domain.VoteAssesseExpert;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedThing;
import com.gpcsoft.smallscale.vote.domain.VotePointer;
import com.gpcsoft.smallscale.vote.domain.VoteTemplate;
import com.gpcsoft.smallscale.vote.domain.VoteUnitGroup;

@Service 
public class VoteTemplateServiceImpl extends BaseGenericServiceImpl<VoteTemplate> implements VoteTemplateService {

	@Autowired(required=true) @Qualifier("voteTemplateManagerImpl")
	private VoteTemplateManager voteTemplateManager;
	
	@Autowired(required=true)  @Qualifier("voteTemplateDaoHibernate")
	private VoteTemplateDao voteTemplateDaoHibernate;
	
	@Autowired(required=true) @Qualifier("votePointerDaoHibernate")
	private VotePointerDao votePointerDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("voteAssessedThingDaoHibernate")
	private VoteAssessedThingDao voteAssessedThingDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("voteAssessedDaoHibernate")
	private VoteAssessedDao voteAssessedDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("voteUnitGroupDaoHibernate")
	private VoteUnitGroupDao voteUnitGroupDaoHibernate;
	
	/**
	 * 生成主题编号
	 */
	public String generateVoteTemplateCode() {
		return voteTemplateManager.generateVoteTemplateCode();
	}

	/**
	 * 获取主题下的评选单位
	 */
	public List<VoteAssessedThing> findAssessedThingOfTemplate(String objId) {
		return voteAssessedThingDaoHibernate.findAssessedThingOfTemplate(objId);
	}

	/**
	 * 获取主题下的指标
	 */
	public List<VotePointer> findPointOfTemplate(String objId) {
		return votePointerDaoHibernate.findPointOfTemplate(objId,null);
	}

	/**
	 * 删除主题(确定删除时将同时删除主题下的指标、评选单位)
	 */
	public void removeVoteTemplate(String objId) {

		//删除指标分值记录
		voteAssessedDaoHibernate.removeAssessedAndGradeByTemplateId(objId);
		//删除投票评分记录
		voteAssessedDaoHibernate.removeAssessedByTemplateId(objId);
		//删除主题下的指标
		votePointerDaoHibernate.removeVotePointOfTemplate(objId);
		//删除主题下的评选单位
		voteAssessedThingDaoHibernate.removeAssessedThingOfTemplate(objId);
		//删除主题下的评选组
		voteUnitGroupDaoHibernate.removeVoteUnitGroupOfTemplate(objId);
		//删除主题信息
		voteTemplateManager.remove(objId, VoteTemplate.class);		
	}

	/**
	 * 根据主题编号获取主题信息
	 */
	public VoteTemplate getVoteTemplateByCode(String templateCode) {
		String hql = "select vt from VoteTemplate vt where vt.templateCode = '" + templateCode + "'";
		List<VoteTemplate> list = null;
		list = voteTemplateManager.findByHql(hql, null);
		return list.get(0);
	}

	/**
	 * 主题统计(主题下的评选单位的参评人数和最终成绩)
	 */
	public List<Object> toTemplateStatistic(String objId) {
		List<Object> templateStatistic = new ArrayList<Object>();
		List<Object> assessedThingList = voteTemplateManager.toTemplateStatistic(objId);
		for(int i=0;i<assessedThingList.size();i++){
			Object[] assessedThings = (Object[]) assessedThingList.get(i);
			List<Object> assessedAvgGradeList = voteAssessedDaoHibernate.countAvgGradeOfPointer(objId, assessedThings[2].toString());
			
			//最终成绩    Object[] avgGrade 数组5个元素
			BigDecimal assessedFinalGrade = new BigDecimal("0.0");
			for(int j=0;j<assessedAvgGradeList.size();j++){
				Object[] avgGrade = (Object[]) assessedAvgGradeList.get(j);
				assessedFinalGrade = assessedFinalGrade.add((BigDecimal)avgGrade[4]);
			}
			assessedThings[3] = assessedFinalGrade;
			templateStatistic.add(assessedThings);
		}
		return templateStatistic;
	}

	/**
	 * 获取主题下的评选组
	 */
	public Page<VoteUnitGroup> getVoteUnitGroupList(Page<VoteUnitGroup> page,Map<String, Object> paramsMap) {
		return voteTemplateDaoHibernate.getVoteUnitGroupList(page, paramsMap);
	}

	/**
	 * 主题下的参选对象列表
	 */
	public Page<VoteAssessedThing> getVoteAssessedObjectList(Page<VoteAssessedThing> page, Map<String, Object> paramsMap) {
		return voteTemplateDaoHibernate.getVoteAssessedObjectList(page, paramsMap);
	}

	/**
	 * 获取参选对象的所属机构
	 */
	public Page<OrgInfo> getVoteBelongsOrgInfoList(Page<OrgInfo> page,Map<String, Object> paramsMap) {
		return voteTemplateDaoHibernate.getVoteBelongsOrgInfoList(page,paramsMap);
	}

	/**
	 * 获取主题下评审专家列表
	 */
	public Page<VoteAssesseExpert> getVoteAssesseExpertList(Page<VoteAssesseExpert> page, Map<String, Object> paramsMap) {
		return voteTemplateDaoHibernate.getVoteAssesseExpertList(page, paramsMap);
	}
}
