package com.gpcsoft.smallscale.vote.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.vote.domain.VotePointer;

public interface VotePointerService extends BaseGenericService<VotePointer> {
	
	/**
	 * Description :  保存指标
	 * Create Date: 2011-2-18下午03:44:00 by zhaojf  Modified Date: 2011-2-18下午03:44:00 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveVotePointer(VotePointer votePointer);

}
