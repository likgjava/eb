package com.gpcsoft.epp.buyresult.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.buyresult.dao.BuyResultDao;
import com.gpcsoft.epp.buyresult.domain.BuyResult;

import org.hibernate.util.XMLHelper;
import org.springframework.stereotype.Repository;

@Repository
public class BuyResultDaoHibernate extends BaseGenericDaoHibernate<BuyResult> implements BuyResultDao {
    
	/**
	 * 
	 * Description :保存成交结果对象  
	 * Create Date: 2010-6-2下午04:33:30 by liuke  Modified Date: 2010-6-2下午04:33:30 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveBuyResult(BuyResult buyResult) {
		this.save(buyResult);
	}  
     
	/**
	 * FuncName: getListByProjectId
	 * Description : 根据项目主键查询成交结果集合
	 * @param  projectId
	 * @author: shenjz
	 * @Create Date:2011-7-12  上午11:56:52
	 * @Modifier: shenjz
	 * @Modified Date:2011-7-12  上午11:56:52
	 */
	public List<BuyResult> getListByProjectId(String projectId) {
		return this.findbyHql("select t from BuyResult t where t.project.objId = ? order by t.subProjCode, createTime desc", projectId);
	}

}
