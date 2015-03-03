package com.gpcsoft.epp.contract.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.contract.dao.ContractDao;
import com.gpcsoft.epp.contract.domain.Contract;
import com.gpcsoft.epp.contract.domain.ContractAcquirer;
import com.gpcsoft.epp.contract.domain.ContractPaymentApplyInfo;
import com.gpcsoft.epp.contract.domain.ContractSupplier;
import com.gpcsoft.epp.contract.domain.WitnessParty;
import com.gpcsoft.epp.contract.manager.ContractManager;
import com.gpcsoft.epp.contract.service.ContractService;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskTypeEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;

@Service 
public class ContractServiceImpl extends BaseGenericServiceImpl<Contract> implements ContractService {

	@Autowired(required=true) @Qualifier("contractManagerImpl")
	private ContractManager contractManager;
	
	@Autowired(required=true) @Qualifier("contractDaoHibernate")
	private ContractDao contractDao;
	
	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;
	/**
	 * FuncName: saveContract
	 * Description :  创建合同对象
	 * @param 
	 * @return Contract
	 * @author: shenjz
	 * @Create Date:2011-3-16  下午07:00:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午07:00:23
	 */
	public Contract saveContract(Contract contract,ContractAcquirer contractAcquirer,ContractSupplier contractSupplier,WitnessParty witnessParty,List<ContractPaymentApplyInfo> applyInfoList){
		logger.debug("\n=ContractServiceImpl||saveContract\n");
		contractAcquirer.setBuyer(contract.getCuyerId());
		contractSupplier.setSupplier(contract.getCupplierId());
		witnessParty.setAgent(contract.getAgencyId());
		Contract contract2 = contract;
		contract2.setPartyA(contractAcquirer);
		contract2.setSecondParty(contractSupplier);
		contract2.setWitenessParty(witnessParty);
		contract2.setContractPaymentApplyInfo(applyInfoList);
		contractManager.save(contract2,Contract.class);
		if(contract.getProjectId()!=null){
			Project project = projectDaoHibernate.get(contract.getProjectId());
			project.setProjProcessStatus(ProjProcessStatusEnum.SUPPLIERS_DRAFT_CONTRACT);
			projectDaoHibernate.save(project);
		}
		return contract;
	}
	
	/**
	 * FuncName:generatorTaskPlanCodeByQO
	 * Description: 生成合同编号,Service层必须开启DB事物
	 * @param queryObject 为扩展参数，暂时不用
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44  
	 */
	public String createContractCodeByQO(QueryObject queryObject)throws EsException {
		return contractManager.generatorContractCodeByQO(queryObject);
	}
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
	public List<Contract> getContractList(String projectId){
		return contractDao.getContractList(projectId);
	}
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
	public Contract saveContractForAgency(Contract contract,ContractAcquirer contractAcquirer,ContractSupplier contractSupplier,WitnessParty witnessParty,List<ContractPaymentApplyInfo> applyInfoList){
		logger.debug("\n=ContractServiceImpl||saveContract\n");
		Contract contract2 = contract;
		contract2.setPartyA(contractAcquirer);
		contract2.setSecondParty(contractSupplier);
		contract2.setWitenessParty(witnessParty);
		contract2.setContractPaymentApplyInfo(applyInfoList);
		contractManager.save(contract2,Contract.class);
		return null;
	}

	
	
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
	public List<Contract> getContractListForBuyer(String projectId) {
		return contractDao.getContractListForBuyer(projectId);
	}

	
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
	public Contract saveConfirmContract(Contract contract) {
		contractManager.save(contract);
		if(contract.getProjectId()!=null){
			Project project = projectDaoHibernate.get(contract.getProjectId());
			project.setProjProcessStatus(ProjProcessStatusEnum.PURCHASER_CONFIRM_CONTRACT);
			projectDaoHibernate.save(project);
		}
		return contract;
	}

    /** 
     * FuncName : getContractCountByQuery
     * Description :  根据条件统计合同数量
     * Create Date: 2011-11-11上午11:37:41 by yangx  
     * Modified Date: 2011-11-11上午11:37:41 by yangx
     * @param   queryObject：查询条件
     * @return  BigDecimal
     */
    @SuppressWarnings("unchecked")
	public BigDecimal getContractCountByQuery(QueryObject queryObject){
    	return contractDao.getContractCountByQuery(queryObject);
    }

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
	public List<Contract> getContractListForBuyer(String cuyerId,String statsus){
		return contractDao.getContractListForBuyer(cuyerId,statsus);
	}

	/**
	 * FuncName: saveContract
	 * Description :  保存合同
	 * @param  void
	 * @author: liuke
	 * @Create Date:2011-8-12  上午11:28:24
	 * @Modifier: liuke
	 * @Modified Date:2011-8-12  上午11:28:24
	 */
	public void saveContract(Contract contract,String taskName) {
		contractManager.save(contract);
		String viewResultURL = "";
		String completedWorkTaskResult = "";
		 if("02".equals(contract.getContractStatus())){ //确认不通过
			completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
		}else{//确认通过
			completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
		}
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_CONTRACT,contract.getOpinion(),contract.getObjId(),contract.getProjectId(),viewResultURL,completedWorkTaskResult);//保存已办任务
	}
}	

