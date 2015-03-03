package com.gpcsoft.agreement.bargin.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.project.dao.BargainTurnDao;
import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.agreement.bargin.project.service.BargainTurnService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class BargainTurnServiceImpl extends BaseGenericServiceImpl<BargainTurn> implements BargainTurnService {
	
	@Autowired(required=true) @Qualifier("bargainTurnDaoHibernate")
	private BargainTurnDao bargainTurnDaoHibernate;
	
	/** 
	 * Description :  根据项目ID获取轮次信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BargainTurn> getBargainTurnByProjId(String projId) throws Exception {
		return bargainTurnDaoHibernate.getBargainTurnByProjId(projId);
	}
	
	/** 
	 * Description :  根据项目ID获取当前时间所属轮次
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public BargainTurn getCurrentBargainTurn(final String projId) throws Exception {
		return bargainTurnDaoHibernate.getCurrentBargainTurn(projId);
	}
	
	/** 
	 * Description :  根据项目ID获取下一个轮次
	 * Create Date: 2011-7-4下午06:26:27 by likg  Modified Date: 2011-7-4下午06:26:27 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public BargainTurn getNextBargainTurn(String projId) throws Exception {
		return bargainTurnDaoHibernate.getNextBargainTurn(projId);
	}
	
	/** 
	 * Description :  根据项目ID删除轮次信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer removeBargainTurnByProjId(String projId) throws Exception {
		return bargainTurnDaoHibernate.removeBargainTurnByProjId(projId);
	}
	
	/** 
	 * Description :  根据轮次ID获取降幅
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Object[] getTotalCut(String turnId) throws Exception {
		return bargainTurnDaoHibernate.getTotalCut(turnId);
	}

}
