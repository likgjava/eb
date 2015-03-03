package com.gpcsoft.agreement.bargin.project.dao;

import java.util.List;

import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface BargainTurnDao extends BaseGenericDao<BargainTurn> {
	
	/** 
	 * Description :  根据项目ID获取轮次信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BargainTurn> getBargainTurnByProjId(String projId) throws Exception;
	
	/** 
	 * Description :  根据项目ID获取当前时间所属轮次
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public BargainTurn getCurrentBargainTurn(String projId) throws Exception;
	
	/** 
	 * Description :  根据项目ID获取下一个轮次
	 * Create Date: 2011-7-4下午06:26:27 by likg  Modified Date: 2011-7-4下午06:26:27 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public BargainTurn getNextBargainTurn(String projId) throws Exception;
	
	/** 
	 * Description :  根据项目ID删除轮次信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer removeBargainTurnByProjId(String projId) throws Exception;
	
	/** 
	 * Description :  根据轮次ID获取降幅
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Object[] getTotalCut(String turnId) throws Exception;
}
