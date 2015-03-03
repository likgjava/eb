package com.gpcsoft.epp.consign.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.consign.dao.ConsignDao;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class ConsignDaoHibernate extends BaseGenericDaoHibernate<Consign> implements ConsignDao {
	
	/** 
	 * FuncName:submitConsign
	 * Description:提交委托书
	 * @param   objIds:以逗号分割的申报书主键,useStatus:用户使用状态,confirmStatus:确认状态
	 * @return  void
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:52:43 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-6下午05:52:43 
	 */
	@SuppressWarnings("unchecked")
	public void submitConsign(final String objIds,final String useStatus,final String confirmStatus) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				StringBuffer hql = new StringBuffer();
				hql.append("update Consign set");
				if(useStatus != null && useStatus.length() != 0){ //使用状态
					hql.append(" useStatus =:useStatus ,");
				} 
				if(confirmStatus != null && confirmStatus.length() != 0){ //审核状态
					hql.append(" confirmStatus =:confirmStatus ,");	
				} 
				if(hql.toString().endsWith(",")){
					hql.delete(hql.length()-1,hql.length());
				}
				hql.append(" where objId in (:objIds)");
				Query query = session.createQuery(hql.toString());
				query.setParameterList("objIds", objIds.split(","));
				if(useStatus != null && useStatus.length() != 0){
					query.setString("useStatus", useStatus);
				}
				if(confirmStatus != null && confirmStatus.length() != 0){
					query.setString("confirmStatus", confirmStatus);
				}
				return query.executeUpdate();
			}
		});
	}
	
	/** 
	 * FuncName:getConsignListByObject
	 * Description :  根据对象获取委托协议列表
	 * @param   page:分页对象,queryObject:数据组装对象 
	 * @return  Page<Consign>
	 * @author yangx
	 * @Create Date: 2010-7-13下午04:13:25 
	 * @Modifier  yangx
	 * @Modified Date: 2010-7-13下午04:13:25 
	 */
	@SuppressWarnings("unchecked")
	public Page<Consign> getConsignListByObject(Page<Consign> page,QueryObject queryObject){
		StringBuffer hql = new StringBuffer();
		hql.append("from Consign t where 1=1  ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				/** 编号 */
				if("consCode".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.consCode like '%"+(String)queryParam.getValue()+"%'");
				}
				/** 名称 */
				if("consName".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.consName like '%"+(String)queryParam.getValue()+"%'");
				}
				/** 代理机构 */
				if("consAgent.objId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.consAgent.objId='"+(String)queryParam.getValue()+"'");
				}
				/** 状态 */
				if("confirmStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.confirmStatus='"+(String)queryParam.getValue()+"'");
				}
				/** 采购人 */
				if ("consBuyer".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())) {
					hql.append(" and t.consBuyer.objId = '"+queryParam.getValue()+"' ");
				}
				/** 申报书类型 */
				if("taskType".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.objId in");
					hql.append("(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tct.consign.objId")+" from ConsignTaskPlan tct,TaskPlan tp where tct.taskPlan.objId=tp.objId");
					hql.append(" and tp.taskType='"+(String)queryParam.getValue()+"')");
				}
				
			}
		}
		Page<Consign> pageData = this.findbyHql(hql.toString(),page.getStart(),page.getPageSize(), Consign.class);
		return pageData;
	}
	
	/**
	 * FuncName:getConsignListOrCountByQuery
	 * Description: 获取委托协议List 或 记录条数
	 * @param queryObject{count:记录条数,type:查询类型{data:数据,count记录条数},createUser:创建人ID,confirmStatus:确认状态,consBuyer:采购人}
	 * @return Object
	 * @author wanghz
	 * @Create Date 2010-9-2 下午02:36:16 
	 */
	@SuppressWarnings("unchecked")
	public Object getConsignListOrCountByQuery(final QueryObject queryObject)throws EsException{
		return getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
				if(null == queryList){
					queryList = new ArrayList<QueryParam>(0);
				}
				Boolean isData = false;// 查询是否为列表数据
				for (QueryParam queryParam:queryList) {
					if ("type".equals(queryParam.getName())) {
						if ("data".equals(queryParam.getValue())) {
							isData = true;
						} else{
							hql.append(" select count(consign.objId) ");
						}
					}
				}
				hql.append(" from Consign as consign where 1=1 ");
				for (QueryParam queryParam:queryList) {
					if ("createUser".equals(queryParam.getName())) {
						hql.append(" and consign.createUser.objId = '"+queryParam.getValue()+"' ");
					}
					if ("confirmStatus".equals(queryParam.getName())) {
						hql.append(" and consign.confirmStatus = '"+queryParam.getValue()+"' ");
					}
					if ("consBuyer".equals(queryParam.getName())) {
						hql.append(" and consign.consBuyer.objId = '"+queryParam.getValue()+"' ");
					}
					if ("consAgentId".equals(queryParam.getName())) {
						hql.append(" and consign.consAgent.objId = '"+queryParam.getValue()+"' ");
					}
					if ("useStatus".equals(queryParam.getName())) {
						hql.append(" and consign.useStatus = '"+queryParam.getValue()+"' ");
					}
				    if("taskType".equals(queryParam.getName()) && queryParam.getValue() != null && !"".equals(queryParam.getValue()))
		            {
	                    hql.append(" and consign.objId in ");
	                    hql.append((new StringBuilder("(select ")).append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tct.consign.objId")).append(" from ConsignTaskPlan tct,TaskPlan tp where tct.taskPlan.objId=tp.objId").toString());
	                    hql.append((new StringBuilder(" and tp.taskType='")).append((String)queryParam.getValue()).append("')").toString());
		            }
					
				}
				if (isData) {
					for (QueryParam queryParam:queryList) {
						if ("count".equals(queryParam.getName())) {
							hql.append(" and rownum <= "+queryParam.getValue()+" ");
						}
					}
				}
				hql.append(" order by consign.consCode desc ,consign.createTime desc ");
				return session.createQuery(hql.toString()).list();
			}});
	}
}
