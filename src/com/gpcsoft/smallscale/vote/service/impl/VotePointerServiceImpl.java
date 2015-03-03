package com.gpcsoft.smallscale.vote.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.smallscale.vote.manager.VotePointerManager;
import com.gpcsoft.smallscale.vote.service.VotePointerService;
import com.gpcsoft.smallscale.vote.dao.VotePointerDao;
import com.gpcsoft.smallscale.vote.domain.VotePointer;

@Service 
public class VotePointerServiceImpl extends BaseGenericServiceImpl<VotePointer> implements VotePointerService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("votePointerManagerImpl")
	private VotePointerManager votePointerManager;
	
	@Autowired(required=true)  @Qualifier("votePointerDaoHibernate")
	private VotePointerDao votePointerDaoHibernate;

	/**
	 * Description :  保存指标
	 * Create Date: 2011-2-18下午03:44:00 by zhaojf  Modified Date: 2011-2-18下午03:44:00 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveVotePointer(VotePointer votePointer) {
		//设置编号
		votePointerDaoHibernate.save(votePointer);
	}
}
