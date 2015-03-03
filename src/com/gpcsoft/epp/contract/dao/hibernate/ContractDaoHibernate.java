package com.gpcsoft.epp.contract.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.epp.contract.dao.ContractDao;
import com.gpcsoft.epp.contract.domain.Contract;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class ContractDaoHibernate extends BaseGenericDaoHibernate<Contract> implements ContractDao {
	
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
		return this.findbyHql("from Contract contract where contract.projectId = ? and contract.cupplierId= ?",projectId,AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId());
	}

	
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
	public List<Contract> getContractListForBuyer(String projectId) {
		return this.findbyHql("from Contract contract where contract.projectId = ? and contract.cuyerId = ?",projectId,AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId());
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
    	StringBuffer hql = new StringBuffer();
		hql.append("select count(t.objId) from Contract t where 1 = 1");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("supplierId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null){//供应商
					hql.append(" and t.cupplierId='"+(String)queryParam.getValue()+"' ");
				}
				if("cuyerId".equals(queryParam.getName())&&(String)queryParam.getValue()!=null){//采购人
					hql.append(" and t.cuyerId='"+(String)queryParam.getValue()+"' ");
				}
				if("useStatus".equals(queryParam.getName())&&(String)queryParam.getValue()!=null){//状态
					hql.append(" and t.contractStatus = '"+(String)queryParam.getValue()+"'");
				}
				
			}
		}
    	List contractlist= this.findbyHql(hql.toString());
    	Object object = contractlist.get(0);
    	return new BigDecimal(object.toString());
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
		return this.findbyHql("from Contract contract where contract.cuyerId = ? and contract.contractStatus = ?",cuyerId,statsus);
	}
}	
