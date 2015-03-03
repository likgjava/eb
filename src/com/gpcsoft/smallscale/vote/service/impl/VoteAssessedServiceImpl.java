package com.gpcsoft.smallscale.vote.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.vote.manager.VoteAssessedGradeManager;
import com.gpcsoft.smallscale.vote.manager.VoteAssessedManager;
import com.gpcsoft.smallscale.vote.service.VoteAssessedService;
import com.gpcsoft.smallscale.vote.dao.VoteAssessedDao;
import com.gpcsoft.smallscale.vote.domain.VoteAssessed;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedGrade;
import com.gpcsoft.smallscale.vote.domain.VotePointer;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class VoteAssessedServiceImpl extends BaseGenericServiceImpl<VoteAssessed> implements VoteAssessedService {

	@Autowired(required=true) @Qualifier("voteAssessedManagerImpl")
	private VoteAssessedManager voteAssessedManager;
	
	@Autowired(required=true)  @Qualifier("voteAssessedDaoHibernate")
	private VoteAssessedDao voteAssessedDaoHibernate;
	
	@Autowired(required=true) @Qualifier("voteAssessedGradeManagerImpl")
	private VoteAssessedGradeManager voteAssessedGradeManager;

	/**
	 * 展示投票指标
	 */
	public Map<String, Object> findUnfurlPointerOfTemplate(String templateId,String voteAssessedThingId) {
		Map<String, Object> model = new HashMap<String, Object>();
		model = voteAssessedManager.findUnfurlPointerOfTemplate(templateId, voteAssessedThingId);
		return model;
	}

	/**
	 * 保存投票记录表
	 */
	public VoteAssessed saveVoteAssessed(VoteAssessed voteAssessed) {
		
//		//获取当前用户 
//		if(AuthenticationHelper.getCurrentUser() != null && voteAssessed.getUserName() == null){
//			Employee emp = AuthenticationHelper.getCurrentUser(true).getEmp();
//			voteAssessed.setUserName(emp.getName());
//		}
		
		//设置分值系数(针对普通投票和专家投票)
		//当voteAssessed.getPointerFactor()为null时,为普通投票
		if(voteAssessed.getPointerFactor() == null){
			voteAssessed.setPointerFactor(new BigDecimal("1.0"));//普通投票
		}
		
		
		//是否匿名投票
		if(voteAssessed.getIsAnonymity() == null ){
			voteAssessed.setIsAnonymity(false);
		}
		
		voteAssessed = voteAssessedManager.save(voteAssessed);
		return voteAssessed;
	}

	/**
	 * 保存 投票评分
	 */
	public void saveVoteAssessedGrade(String assessedId, String[] idsResult) {
		String[] pointerArray = null;
		
		//投票评选表Id
		VoteAssessed voteAssessed = new VoteAssessed();
		voteAssessed.setObjId(assessedId);
		
		//设置   指标Id和评分分值
		for(int i=0;i<idsResult.length;i++){
			pointerArray = idsResult[i].split("#");
			
			VoteAssessedGrade voteAssessedGrade = new VoteAssessedGrade();
			
			//主题指标Id
			VotePointer votePointer = new VotePointer();
			votePointer.setObjId(pointerArray[0]);
			
			voteAssessedGrade.setVoteAssessed(voteAssessed);//设置投票评选对象
			voteAssessedGrade.setVotePointer(votePointer);//设置主题指标对象
			voteAssessedGrade.setGrade(BigDecimal.valueOf(Double.parseDouble(pointerArray[1])));//设置 评分分值
			
			voteAssessedGradeManager.save(voteAssessedGrade);
		}
	}

	/**
	 * 查看投票评选
	 */
	public Map<String, Object> viewVoteAssessed(String objId) {
		Map<String, Object> model = new HashMap<String, Object>();
		VoteAssessed voteAssessed = voteAssessedManager.get(objId);
		model.put("voteAssessed", voteAssessed);
		model.put("voteTemplate", voteAssessed.getVoteTemplate());
		model.put("voteAssessedThing", voteAssessed.getVoteAssessedThing());
		model.put("voteAssessedGradeList", voteAssessedGradeManager.findVoteAssessedGradeList(objId));
		return model;
	}

	/**
	 * 删除投票评选,并删除评分值(objId为投票评选Id)
	 */
	public void removeVoteAssessed(String objId) {
		//删除评分值
		voteAssessedGradeManager.removeGradeByAssessedId(objId);
		
		//删除投票评选
		voteAssessedManager.remove(objId, VoteAssessed.class);
	}

	/**
	 * 验证当前登录用户是否对该主题下的此单位进行过评分
	 */
	public boolean isUnique(String templateId, String voteAssessedThingId,String isSingleVote) {
		Boolean isUnique = true;
		User user = AuthenticationHelper.getCurrentUser();
		if(user!=null){
			isUnique = voteAssessedManager.isUnique(templateId, voteAssessedThingId,user.getObjId(),isSingleVote);
		}		
		return isUnique;
	}

	/**
	 * 单一单位的票数数据统计展示
	 */
	public Map<String, Object> voteStatisticUnfurl(String templateId,String voteAssessedThingId) {
		Map<String, Object> model = new HashMap<String, Object>();
		model = voteAssessedManager.voteStatisticUnfurl(templateId, voteAssessedThingId);
		return model;
	}

	/**
	 * 统计参与单位的投票数
	 */
	public List<Object> findAssessedThingVoteCount(String templateId) {
		return voteAssessedManager.countVoteAssessedNum(templateId);
	}

	/**
	 * 评论展示列表(单个参选对象的评论列表)
	 */
	public Page<VoteAssessed> getVoteAssessedCommentShow(Page<VoteAssessed> page,Map<String, Object> paramsMap) {
		return voteAssessedDaoHibernate.getVoteAssessedCommentShow(page,paramsMap);
	}

	/**
	 * 投票结果列表
	 */
	public List<Object> getVoteResultShow(Map<String, Object> paramMap) {
		return voteAssessedDaoHibernate.getVoteResultShow(paramMap);
	}
}
