package com.gpcsoft.smallscale.vote.manager.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.vote.dao.VoteTemplateDao;
import com.gpcsoft.smallscale.vote.manager.VoteTemplateManager;
import com.gpcsoft.smallscale.vote.domain.VoteTemplate;

@Repository
public class VoteTemplateManagerImpl extends BaseManagerImpl<VoteTemplate> implements VoteTemplateManager {

	@Autowired(required=true)  @Qualifier("voteTemplateDaoHibernate")
	private VoteTemplateDao voteTemplateDaoHibernate;

	/**
	 * 生成主题编号(编号规则：'VT'+ 八位数字)
	 */
	public String generateVoteTemplateCode() {
		String voteTemplateCode = "VT";
		String maxCode = voteTemplateDaoHibernate.getMaxVoteTemplateCode();//获取当前主题的最大编号
		if(maxCode == null){
			voteTemplateCode = voteTemplateCode + "00000001";
		}else{
			int num = Integer.parseInt(maxCode.substring(voteTemplateCode.length())) + 1;
			voteTemplateCode = voteTemplateCode + this.supplyZeroBit(num);
		}		
		return voteTemplateCode;
	}
	
	/**
	 * Description :  编号补0(数字不足8位补0)
	 * Create Date: 2011-3-3上午11:00:31 by zhaojf  Modified Date: 2011-3-3上午11:00:31 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	protected String supplyZeroBit(Integer num){
		String number = num.toString();
		int leng = 8 - number.length();
		for(int i=0;i<leng;i++){
			number = "0" + number;
		}
		return number;
	}

	/**
	 * 主题统计(主题下的评选单位的参评人数)
	 */
	public List<Object> toTemplateStatistic(String objId) {
		return voteTemplateDaoHibernate.toTemplateStatistic(objId);
	}

	
}
