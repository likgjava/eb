package com.gpcsoft.epp.oppugnrequisition.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.oppugnrequisition.dao.OppugnRequisitionDao;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnRequisition;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

import org.springframework.stereotype.Repository;

@Repository
public class OppugnRequisitionDaoHibernate extends BaseGenericDaoHibernate<OppugnRequisition> implements OppugnRequisitionDao {

	/**
	 * 保存质疑答复
	 */
	public void saveOppugnReply(OppugnReply oppugnReply) {
		this.getHibernateTemplate().save(oppugnReply);
	}
	/** 
	 * Description :  采购人查询供应商质疑
	 * Create Date: 2010-8-1下午04:55:05 by yangx  Modified Date: 2010-8-1下午04:55:05 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<OppugnRequisition> getOppugnRequisitionByQO(QueryObject queryObject,Page page){
		StringBuffer hql = new StringBuffer();
		hql.append("from OppugnRequisition t where 1=1 ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				/** 质疑类型 */
				if("oppuType".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.oppuType like '%"+(String)queryParam.getValue()+"%'");
				}
				/** 质疑时间 */
				if("applyDate".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().date2String("t.createTime")+" ='"+(String)queryParam.getValue()+"' ");
				}
				/** 项目Id */
				if("projectId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.project.objId = '"+(String)queryParam.getValue()+"'");
				}
				/** 质疑对象 */
				if("consBuyerName".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.consBuyerName!='"+(String)queryParam.getValue()+"'");
				}
				/** 回复的质疑数目 */
//				if("replyCount".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
//					hql.append(" and t.replyCount ='"+(String)queryParam.getValue()+"'");
//				}
				/** 采购人 */
				if("orgInfoId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.project.objId in ");
					hql.append("(select tp.tproject.objId from ProjectMTaskPlan tp where tp.buyMainBody.objId='"+(String)queryParam.getValue()+"')");
				}
				/** 代理机构 */
				if("agentId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.project.agencies.objId ='"+(String)queryParam.getValue()+"'");
				}
				/** 状态 */
				if("useStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.useStatus ='"+(String)queryParam.getValue()+"'");
				}
			}
		}
		hql.append(" order by createTime desc");
		Page<OppugnRequisition> pages = this.findbyHql(hql.toString(),page.getStart(),page.getPageSize(),OppugnRequisition.class);
		return pages;
	}
}
