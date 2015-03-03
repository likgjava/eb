package com.gpcsoft.epp.project.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.dao.ProjectExceptionApplyDao;
import com.gpcsoft.epp.project.domain.ProjectExceptionApply;

import org.springframework.stereotype.Repository;

@Repository
public class ProjectExceptionApplyDaoHibernate extends BaseGenericDaoHibernate<ProjectExceptionApply> implements ProjectExceptionApplyDao {

	/** 
	 * FuncName:getProjectExceptionList
	 * Description:获取异常申请列表
	 * @param   page:分页对象,queryObject:查询条件组装对象
	 * @return  Page<ProjectExceptionApply>
	 * @author yangx
	 * @Create Date: 2010-10-12上午10:55:07 
	 * @Modifier yangx   
	 * @Modified Date: 2010-10-12上午10:55:07     
	 */
	@SuppressWarnings("unchecked")
	public Page<ProjectExceptionApply> getProjectExceptionList(Page page,QueryObject queryObject){
		StringBuffer hql = new StringBuffer();
		hql.append("select t from ProjectExceptionApply t where 1=1 ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("projCode".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){
					hql.append(" and t.project.projCode like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("projName".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){
					hql.append(" and t.project.projName like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("adviceProcway".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){
					hql.append(" and t.adviceProcway = '"+(String)queryParam.getValue()+"' ");
				}
				if("agencies".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){//若有代理机构ID，则需要把代理机构ID过滤加入到里面
					hql.append(" and t.project.agencies.objId = '"+(String)queryParam.getValue()+"'  ");
				}
				if("manager".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){//若有项目经办人ID，则需要把项目经办人ID过滤加入到里面
					hql.append(" and t.project.manager.objId = '"+(String)queryParam.getValue()+"'  ");
				}
			}
		}
		hql.append(" and t.useStatus = "+CommonEnum.USER_STATUS_FORMAL+" ");
		hql.append(" order by t.createTime desc");
		return this.findbyHql(hql.toString(), page.getStart(), page.getPageSize(),ProjectExceptionApply.class);
	}
}
