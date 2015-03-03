package com.gpcsoft.epp.contract.dao;

import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.epp.contract.domain.Contract;

public interface ContractDao extends BaseGenericDao<Contract> {
	/**
	 * FuncName: getContractList
	 * Description :  查询项目已经创建的合同
	 * @param 
	 * @return List<Contract>
	 * @author: shenjz
	 * @Create Date:2011-3-24  下午05:04:49
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  下午05:04:49
	 */
	public List<Contract> getContractList(String projectId);
	
	
	/**
	 * FuncName: getContractListForBuyer
	 * Description : 采购人获取合同
	 * @param 
	 * @param projectId
	 * @return List<Contract>
	 * @author: liuke
	 * @Create Date:2011-9-15  下午02:21:39
	 * @Modifier: liuke
	 * @Modified Date:2011-9-15  下午02:21:39
	 */
	public List<Contract> getContractListForBuyer(String projectId);
	
	/** 
     * FuncName : getContractCountByQuery
     * Description :  根据条件统计合同数量
     * Create Date: 2011-11-11上午11:37:41 by yangx  
     * Modified Date: 2011-11-11上午11:37:41 by yangx
     * @param   queryObject：查询条件
     * @return  BigDecimal
     */	 
    @SuppressWarnings("unchecked")
	public BigDecimal getContractCountByQuery(QueryObject queryObject);


	/**
	 * FuncName: getContractListForBuyer
	 * Description :  查询待确认的合同
	 * @param 
	 * @param projectId
	 * @return List<Contract>
	 * @author: shenjz
	 * @Create Date:2011-8-4  下午04:18:40
	 * @Modifier: shenjz
	 * @Modified Date:2011-8-4  下午04:18:40
	 */
	public List<Contract> getContractListForBuyer(String cuyerId,String statsus);
    
}
