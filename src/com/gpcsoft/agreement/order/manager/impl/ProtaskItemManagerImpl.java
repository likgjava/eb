package com.gpcsoft.agreement.order.manager.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.exception.NotExistsTheProtaskException;
import com.gpcsoft.agreement.order.dao.ProtaskItemDao;
import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.agreement.order.manager.ProtaskItemManager;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class ProtaskItemManagerImpl extends BaseManagerImpl<ProtaskItem> implements ProtaskItemManager {

	@Autowired(required=true)  @Qualifier("protaskItemDaoHibernate")
	private ProtaskItemDao protaskItemDaoHibernate;

	/**
	 * 获得当前用户未完成的任务书明细
	 */
	public List<ProtaskItem> getNotFinishProtaskItems() {
		String companyId = AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId();
		
		return getNotFinishProtaskItems(companyId);
	}

	/**
	 * 获得指定采购人未完成的任务书明细
	 */
	public List<ProtaskItem> getNotFinishProtaskItems(String companyId) {
		QueryObject<ProtaskItem> protaskItemQuery = new QueryObjectBase<ProtaskItem>();
		protaskItemQuery.setEntityClass(ProtaskItem.class);
		protaskItemQuery.setQueryParam(new QueryParam("procurementtask.buyer.company.objId",QueryParam.OPERATOR_EQ,companyId));
		protaskItemQuery.setQueryParam(new QueryParam("finQty",QueryParam.OPERATOR_GT,new BigDecimal(0)));
		List<ProtaskItem> ProtaskItemlist = protaskItemDaoHibernate.findByQuery(protaskItemQuery);
		
		return ProtaskItemlist;
	}

	/**
	 * 获得当前用户未完成的品目id和名称
	 */
	public String[] getNotFinishProtaskCategory(boolean isException) {
		User user = AuthenticationHelper.getCurrentUser(true);
		String companyId = user.getEmp().getCompany().getObjId();
		
		
		return getNotFinishProtaskCategory(companyId , isException);
	}

	/**
	 * 获得指定采购人未完成的品目id和名称
	 */
	public String[] getNotFinishProtaskCategory(String companyId , boolean isException) {
		List<ProtaskItem> ProtaskItemlist = this.getNotFinishProtaskItems(companyId);
		
		if(isException && ProtaskItemlist.size() == 0)
			throw new NotExistsTheProtaskException("没有未完成的任务书信息！");
		else if(ProtaskItemlist.size() == 0)
			return new String[]{"",""};
		
		StringBuffer purCategorys = new StringBuffer("");
		StringBuffer purCategoryNames = new StringBuffer("");
		for (ProtaskItem protaskItem : ProtaskItemlist) {
			if(purCategorys.indexOf(protaskItem.getPurCategory().getObjId()) == -1){
				purCategorys.append(protaskItem.getPurCategory().getObjId() + ",");
				purCategoryNames.append(protaskItem.getPurCategory().getCategoryName() + ",");
			}
		}
		
		return new String[]{purCategorys.substring(0,purCategorys.length()-1),purCategoryNames.substring(0,purCategoryNames.length()-1)};
	}

	/**
	 * 获取任务单条目
	 */
	public List<ProtaskItem> getProtaskItemList(String objIds) throws Exception {
		return protaskItemDaoHibernate.getProtaskItemList(objIds);
	}
}
