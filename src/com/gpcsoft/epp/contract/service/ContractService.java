package com.gpcsoft.epp.contract.service;
import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.contract.domain.Contract;
import com.gpcsoft.epp.contract.domain.ContractAcquirer;
import com.gpcsoft.epp.contract.domain.ContractPaymentApplyInfo;
import com.gpcsoft.epp.contract.domain.ContractSupplier;
import com.gpcsoft.epp.contract.domain.WitnessParty;

public interface ContractService extends BaseGenericService<Contract> {
	
	
	/**
	 * FuncName: saveContract
	 * Description :  创建合同
	 * @param 
	 * @return Contract
	 * @author: shenjz
	 * @Create Date:2011-3-16  下午07:00:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午07:00:23
	 */
	public Contract saveContract(Contract contract,ContractAcquirer contractAcquirer,ContractSupplier contractSupplier,WitnessParty witnessParty,List<ContractPaymentApplyInfo> applyInfoList);
	
	/**
	 * FuncName:generatorTaskPlanCodeByQO
	 * Description: 生成合同编号,Service层必须开启DB事物
	 * @param queryObject 为扩展参数，暂时不用
	 * @return String
	 * @author shenjz
	 * @Create Date 2011-3-22 上午09:59:44  
	 */
	public String createContractCodeByQO(QueryObject queryObject)throws EsException;
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
	 * 
	 * FuncName: saveContractForAgency
	 * Description :  代理机构保存合同功能
	 * @param 
	 * @return Contract
	 * @author: shenjz
	 * @Create Date:2011-3-29  下午01:37:17
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-29  下午01:37:17
	 */
	public Contract saveContractForAgency(Contract contract,ContractAcquirer contractAcquirer,ContractSupplier contractSupplier,WitnessParty witnessParty,List<ContractPaymentApplyInfo> applyInfoList);
	
	
	
	
	/**
	 * FuncName: getContractList
	 * Description :  采购人获取合同
	 * @param 
	 * @param projectId
	 * @return List<Contract>
	 * @author: liuke
	 * @Create Date:2011-9-15  下午02:20:00
	 * @Modifier: liuke
	 * @Modified Date:2011-9-15  下午02:20:00
	 */
	public List<Contract> getContractListForBuyer(String projectId);
	
	
	
	/**
	 * FuncName: saveConfirmContract
	 * Description :  保存确认合同
	 * @param 
	 * @param contract
	 * @return Contract
	 * @author: liuke
	 * @Create Date:2011-9-15  下午02:33:29
	 * @Modifier: liuke
	 * @Modified Date:2011-9-15  下午02:33:29
	 */
	public Contract saveConfirmContract(Contract contract);
	
    /** 
     * FuncName : getContractCountByQuery
     * Description :  根据条件统计合同数量
     * Create Date: 2011-11-11上午11:37:41 by yangx  
     * Modified Date: 2011-11-11上午11:37:41 by yangx
     * @param   queryObject：查询条件
     * @return  BigDecimal
     */
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

	/**
	 * FuncName: saveContract
	 * Description :  保存合同
	 * @param  void
	 * @author: liuke
	 * @Create Date:2011-8-12  上午11:28:24
	 * @Modifier: liuke
	 * @Modified Date:2011-8-12  上午11:28:24
	 */
	public void saveContract(Contract contract,String taskName);
}
