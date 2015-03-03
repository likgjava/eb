package com.gpcsoft.pubservice.application.concern.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.concern.dao.ConcernGroupDao;
import com.gpcsoft.pubservice.application.concern.domain.ConcernGroup;
import com.gpcsoft.pubservice.application.concern.manager.ConcernGroupManager;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class ConcernGroupManagerImpl extends BaseManagerImpl<ConcernGroup> implements ConcernGroupManager {

	@Autowired(required=true)  @Qualifier("concernGroupDaoHibernate")
	private ConcernGroupDao concernGroupDaoHibernate;

	/** 
	 * Description : 保存关注分组 
	 * Create Date: 2010-11-3下午05:56:54 by guoyr  Modified Date: 2010-11-3下午05:56:54 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ConcernGroup saveConcernGroup (ConcernGroup concernGroup){
		User currentUser = AuthenticationHelper.getCurrentUser(true);
		OrgInfo orgInfo = (OrgInfo) currentUser.getOrgInfo();
		if(concernGroup.getBelongsUser() == null){
			concernGroup.setBelongsUser(currentUser);
		}
		if(concernGroup.getBelongsOrg() == null){
			concernGroup.setBelongsOrg(orgInfo);// 冗余所属机构
		}		
		
		if(null == concernGroup.getSort() || "".equals(concernGroup.getSort())){
			
			// 保存分组序号
			concernGroup.setSort(concernGroupDaoHibernate.getMaxConcernGroupSort(concernGroup.getGroupType())+1);
		}
		concernGroupDaoHibernate.save(concernGroup);
		return concernGroup;
	}
	
	/** 
	 * Description :获得供应商或采购人的分组列表 
	 * Create Date: 2010-11-5下午02:39:31 by guoyr  Modified Date: 2010-11-5下午02:39:31 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ConcernGroup> getConcerngListByGroupType(String groupType){
		QueryObject<ConcernGroup> query = new QueryObjectBase<ConcernGroup>();
		query.setEntityClass(ConcernGroup.class);
		query.setQueryParam(new QueryParam());
		// 按分类获取
		//query.getQueryParam().and(new QueryParam("groupType",QueryParam.OPERATOR_EQ,groupType));
		User currentUser = AuthenticationHelper.getCurrentUser(true);
		// 过滤当前人
		query.getQueryParam().and(new QueryParam("belongsUser.objId",QueryParam.OPERATOR_EQ,currentUser.getObjId()));
		query.getQueryProjections().setOrderProperty("sort");
		List<ConcernGroup> concernGroupList = concernGroupDaoHibernate.findByQuery(query);
		return concernGroupList;
	}
	
	/** 
	 * Description :获得分组的最大排序号  
	 * Create Date: 2010-11-5下午08:23:38 by guoyr  Modified Date: 2010-11-5下午08:23:38 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer getMaxConcernGroupSort(String groupType){
		return concernGroupDaoHibernate.getMaxConcernGroupSort(groupType);
	}
}
