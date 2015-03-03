package com.gpcsoft.epp.bulletin.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bulletin.dao.BulletinDao;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.common.domain.ApiLogStatusEnum;
import com.gpcsoft.epp.common.domain.ApiLogTypeEnum;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class BulletinDaoHibernate extends BaseGenericDaoHibernate<Bulletin> implements BulletinDao {

	/** 
	 * FuncName:getBulletinListByObject
	 * Description:根据对象获取公告列表
	 * @param   page:分页对象,queryObject:组装的查询对象数据
	 * @return  Page<Bulletin>
	 * @author yangx
	 * @Create Date: 2010-7-8下午01:56:21 
	 * @Modifier  yangx
	 * @Modified Date: 2010-7-8下午01:56:21 
	 */
	@SuppressWarnings("unchecked")
	public Page<Bulletin> getBulletinListByObject(Page<Bulletin> page,QueryObject queryObject){
		StringBuffer hql = new StringBuffer();
		hql.append("from Bulletin t where 1=1 ");
		hql.append(" and t.project is not null and t.project.ebuyMethod != " + EbuyMethodEnum.COMPETITION + " and t.project.ebuyMethod != " + EbuyMethodEnum.TALK);
		String bullType="";
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				/** 公告品目 */	
				if ("purcategorycode".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())) {//品目编号
					String[] purcategoryArray =  ((String)queryParam.getValue()).split(",");
					hql.append(" and (");
					for(int j=0;j<purcategoryArray.length;j++){
						 if(j>0){
							hql.append(" or t.purcategoryIds like '%"+purcategoryArray[j]+"%'");
						}else {
							hql.append(" t.purcategoryIds like '%"+purcategoryArray[j]+"%'");
						}
					}
					hql.append(" ) ");
				}
				
				/** 公告编号 */
				if("bulletinNo".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.bulletinNo like '%"+(String)queryParam.getValue()+"%'");
				}
				/** 公告标题 */
				if("bullTitle".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.bullTitle like '%"+(String)queryParam.getValue()+"%'");
				}
				/** 审核状态 */
				if("auditStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.auditStatus='"+(String)queryParam.getValue()+"'");
				}
				/** 公告类型 */
				if("bullType".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					bullType = (String)queryParam.getValue();
					hql.append(" and t.bullType in ("+(String)queryParam.getValue()+")");
				}
				/** 项目类型 */
				if("tenderType".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.project.tenderType = '"+ (String)queryParam.getValue()+"' ");
				}
				/**是否正式*/
				if("useStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append("and t.useStatus ='"+(String)queryParam.getValue()+"'");
				}
				/**是否发布*/
				if("relStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append("and t.relStatus ='"+(String)queryParam.getValue()+"'");
				}
				/** 项目监管人 */
				if("monitorId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and (t.project.objId in ");
					hql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.objId")+" from Project tm where tm.objId is not null and ( tm.monitor.objId='"+(String)queryParam.getValue()+"' or tm.manager.objId='"+(String)queryParam.getValue()+"') ) ");
					hql.append(" or t.project.objId in(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.objId")+" from Project ts where ts.parentId in");
					hql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ta.objId")+" from Project ta where ta.objId is not null and ( ta.monitor.objId='"+(String)queryParam.getValue()+"' or ta.manager.objId='"+(String)queryParam.getValue()+"') ))) ");
				}
				/** 采购人 */
				if("buyerId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.project.objId in ");
					hql.append(" (select distinct "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.tproject.objId")+" from SubProjectMTaskPlanSub tm where tm.tproject.objId is not null and tm.buyMainBody.objId='"+(String)queryParam.getValue()+"') ");
				}
				if ((!"".equals(bullType)&&BulletinTypeEnum.RESULT_BULLETIN.equals(bullType))||(!"".equals(bullType)&&BulletinTypeEnum.RESULT_PREVIEW.equals(bullType))) {
					if("managerID".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and (t.project.objId in ");
						hql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.objId")+" from Project tm where tm.objId is not null and tm.manager.objId='"+(String)queryParam.getValue()+"') ");
						hql.append(" or t.project.objId in(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.objId")+" from Project ts where ts.parentId in");
						hql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ta.objId")+" from Project ta where ta.objId is not null and ta.manager.objId='"+(String)queryParam.getValue()+"'))) ");
					}					
				}else{
					/** 项目负责人 */
					if("managerID".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and t.project.objId in ");
						hql.append(" (select distinct "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.objId")+" from Project tm where tm.manager.objId='"+(String)queryParam.getValue()+"') ");
					}
				}
				if("project.objId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.project.objId is null");
				}
			}
		}
		hql.append(" order by  relDate desc, createTime desc");
		Page<Bulletin> pages = this.findbyHql(hql.toString(),page.getStart(),page.getPageSize(),Bulletin.class);
		return pages;
	}
	
	/**
	 * FuncName:getBulletinTotalByQueryObject
	 * Description:根据查询条件统计对应的公告数据
	 * @param queryObject[buyerId:采购人ID;bullType:公告类型;auditStatus:审核状态;monitorId:监管人]
	 * @return BigDecimal
	 * @author Administrator
	 * @Create Date: 2010-7-7下午01:56:46 
	 * @Modifier Administrator
	 * @Modified Date: 2010-7-7下午01:56:46 
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getBulletinTotalByQueryObject(QueryObject queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(t) from Bulletin t where 1=1  ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		String bullType="";
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("bullType".equals(queryParam.getName())){
					bullType = (String)queryParam.getValue();
					hql.append(" and t.bullType='"+(String)queryParam.getValue()+"'");
				}
				if("buyerId".equals(queryParam.getName())){
					hql.append(" and t.project.objId in ");
					hql.append(" (select distinct "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.tproject.objId")+" from SubProjectMTaskPlanSub tm where tm.tproject.objId is not null and tm.buyMainBody.objId='"+(String)queryParam.getValue()+"') ");
				}
				if ((!"".equals(bullType)&&BulletinTypeEnum.RESULT_BULLETIN.equals(bullType))||(!"".equals(bullType)&&BulletinTypeEnum.RESULT_PREVIEW.equals(bullType))) {
					if("monitorId".equals(queryParam.getName())){
						hql.append(" and (t.project.objId in ");
						hql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.objId")+" from Project tm where tm.objId is not null and tm.monitor.objId='"+(String)queryParam.getValue()+"') ");
						hql.append(" or t.project.objId in(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.objId")+" from Project ts where ts.parentId in");
						hql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ta.objId")+" from Project ta where ta.objId is not null and ta.monitor.objId='"+(String)queryParam.getValue()+"'))) ");
					}
				}else {
					if("monitorId".equals(queryParam.getName())){
						hql.append(" and t.project.objId in ");
						hql.append(" (select distinct tm.objId from Project tm where tm.objId is not null and tm.monitor.objId='"+(String)queryParam.getValue()+"') ");
					}
				}
				if ((!"".equals(bullType)&&BulletinTypeEnum.RESULT_BULLETIN.equals(bullType))||(!"".equals(bullType)&&BulletinTypeEnum.RESULT_PREVIEW.equals(bullType))) {
					if("managerID".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and (t.project.objId in ");
						hql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.objId")+" from Project tm where tm.objId is not null and tm.manager.objId='"+(String)queryParam.getValue()+"') ");
						hql.append(" or t.project.objId in(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.objId")+" from Project ts where ts.parentId in");
						hql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ta.objId")+" from Project ta where ta.objId is not null and ta.manager.objId='"+(String)queryParam.getValue()+"'))) ");
					}
				}else{
					/** 项目负责人 */
					if("managerID".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and t.project.objId in ");
						hql.append(" (select distinct "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.objId")+" from Project tm where tm.manager.objId='"+(String)queryParam.getValue()+"') ");
					}
				}
				if("auditStatus".equals(queryParam.getName())){
					hql.append(" and t.auditStatus='"+(String)queryParam.getValue()+"'");
				}
				if("useStatus".equals(queryParam.getName())){
					hql.append(" and t.useStatus='"+(String)queryParam.getValue()+"'");
				}
				if("project.objId".equals(queryParam.getName())){
					hql.append(" and t.project.objId is null");
				}
			}
		}
		List list=this.findbyHql(hql.toString());
		Object object = list.get(0);
		return new BigDecimal(object.toString());
	}
	
	/** 
	 * FuncName:getBulletinByTypeAndAuditStatus
	 * Description:根据公告类型查询审核通过且没有发布过的公告
	 * @param   bullType:公告类型
	 * @return List<Bulletin>
	 * @author yangx
	 * @Create Date: 2010-8-24下午03:12:50 
	 * @Modifier   yangx
	 * @Modified Date: 2010-8-24下午03:12:50 by   
	 */
	public List<Bulletin> getBulletinByTypeAndAuditStatus(String bullType){
			StringBuffer hql = new StringBuffer();
			hql.append("From Bulletin t left join fetch t.project p left join fetch p.agencies a where 1=1  ");
			hql.append(" and t.bullType in('"+bullType+"') and t.auditStatus='"+AuditStatusEnum.AUDIT_PASS+"'");
			hql.append("and t.objId not in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("a.bizId")+" from ApiLog a where a.apiType='"+ApiLogTypeEnum.BULLETIN+"')  ");
			List<Bulletin> list = this.findbyHql(hql.toString());
			return list;
	}
	
	/** 
	 * FuncName:getBulletinListByObjIds
	 * Description :  根据公告Ids获取公告对象
	 * Create Date: 2011-1-15下午12:39:57 by yangx  Modified Date: 2011-1-15下午12:39:57 by yangx
	 * @param   bulletinIds:公告Ids
	 * @return  List<Bulletin>
	 * @Exception   
	 */
	public List<Bulletin> getBulletinListByObjIds(String bulletinIds){
		List<Bulletin> list = this.findbyHql("From Bulletin t where t.objId in('"+bulletinIds+"')");
		return list;
	}
	
	/** 
	 * FuncName:getBulletinListForRel
	 * Description :  获取待发送的公告列表
	 * Create Date: 2011-1-15下午12:10:05 by yangx  Modified Date: 2011-1-15下午12:10:05 by yangx
	 * @param   page:公告分页对象,queryObject:查询条件
	 * @return  Page<Bulletin>
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Bulletin> getBulletinListForRel(Page<Bulletin> page,QueryObject queryObject){
		StringBuffer hql = new StringBuffer();
		hql.append("from Bulletin t where 1=1 ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				/** 公告编号 */
				if("bulletinNo".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.bulletinNo like '%"+(String)queryParam.getValue()+"%'");
				}
				/** 公告标题 */
				if("bullTitle".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.bullTitle like '%"+(String)queryParam.getValue()+"%'");
				}
				/** 审核状态 */
				if("auditStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.auditStatus='"+(String)queryParam.getValue()+"'");
				}
				/** 公告类型 */
				if("bullType".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.bullType in ("+(String)queryParam.getValue()+")");
				}
				/**是否正式*/
				if("useStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append("and t.useStatus ='"+(String)queryParam.getValue()+"'");
				}
				/** 项目监管人 */
				if("monitorId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and (t.project.objId in ");
					hql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.objId")+" from Project tm where tm.objId is not null and tm.monitor.objId='"+(String)queryParam.getValue()+"') ");
					hql.append(" or t.project.objId in(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.objId")+" from Project ts where ts.parentId in");
					hql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ta.objId")+" from Project ta where ta.objId is not null and ta.monitor.objId='"+(String)queryParam.getValue()+"'))) ");
				}
				/**发送状态*/
				if ("status".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())) {
					if ("wait".equals(queryParam.getValue())) {//发送成功
						hql.append("and t.objId not in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("a.bizId")+" from ApiLog a where a.apiType='"+ApiLogTypeEnum.BULLETIN+"')  ");
					}else if ("true".equals(queryParam.getValue())) {//发送成功
						hql.append("and t.objId in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("a.bizId")+" from ApiLog a where a.apiType='"+ApiLogTypeEnum.BULLETIN+"' and a.status='"+ApiLogStatusEnum.APILOG_TRUE+"')  ");
					}else{//发送不成功
						hql.append("and t.objId in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("a.bizId")+" from ApiLog a where a.apiType='"+ApiLogTypeEnum.BULLETIN+"' and a.status='"+ApiLogStatusEnum.APILOG_FALSE+"')  ");
					}
				}
			}
		}
		hql.append(" order by createTime desc");
		Page<Bulletin> pages = this.findbyHql(hql.toString(),page.getStart(),page.getPageSize(),Bulletin.class);
		return pages;
	}
	
	/**
	 * FuncName: getProjectByBulletinId
	 * Description:根据公告Id查询项目
	 * @param   bulletinId:公告主键
	 * @return  Project
	 * @author yangx
	 * @Create Date: 2010-8-24下午05:30:16    
	 * @Modifier yangx
	 * @Modified Date: 2010-8-24下午05:30:16     
	 */
	@SuppressWarnings("unchecked")
	public Project getProjectByBulletinId(String bulletinId){
		StringBuffer hql = new StringBuffer();
		hql.append("From Project t left join fetch t.agencies a where");
		hql.append(" t.objId in(select tb.project.objId from Bulletin tb where tb.objId='"+bulletinId+"')");
		List list = this.findbyHql(hql.toString());
		return (list!=null&&list.size()>0)?(Project)list.get(0):null;
	}
	
	/** 
	 * FuncName:getBulletinByObjIds
	 * Description:根据日志Id查询公告
	 * @param   objIds:日志主键
	 * @return  List<Bulletin>
	 * @author yangx  
	 * @Create Date: 2010-9-15上午11:00:18 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-15上午11:00:18 
	 */
	public List<Bulletin> getBulletinByObjIds(String objIds){
		StringBuffer hql = new StringBuffer();
		hql.append("From Bulletin b left join fetch b.project p left join fetch p.agencies a where objId in");
		hql.append("(select t.bizId from ApiLog t where t.objId in("+objIds+"))");
		return this.findbyHql(hql.toString());
	}
	
	/**
	 * Funcname:getBulletinByBulletinNo
	 * Description:根据公告编号获取公告对象
	 * @param bulletinNo
	 * @return 公告对象
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 */
	public Bulletin getBulletinByBulletinNo(String bulletinNo) {
		List list = this.findbyHql("from Bulletin b where b.bulletinNo = ?", new Object[]{bulletinNo});
		return (list!=null&&list.size()>0)?(Bulletin)list.get(0):null;
	}

	
	
	/**
	 * FuncName: getBulletinListByObject
	 * Description :  根据queryObject对象查询公告列表
	 * @param 
	 * @param queryObject
	 * @return List<Bulletin>
	 * @author: liuke
	 * @Create Date:2011-10-11  上午11:27:35
	 * @Modifier: liuke
	 * @Modified Date:2011-10-11  上午11:27:35
	 */
	public List<Bulletin> getBulletinListByObject(QueryObject queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Bulletin t where 1=1 ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				/** 公告编号 */
				if("bulletinNo".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.bulletinNo like '%"+(String)queryParam.getValue()+"%'");
				}
				/** 公告标题 */
				if("bullTitle".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.bullTitle like '%"+(String)queryParam.getValue()+"%'");
				}
				/** 审核状态 */
				if("auditStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.auditStatus='"+(String)queryParam.getValue()+"'");
				}
				/** 公告类型 */
				if("bullType".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.bullType = '"+(String)queryParam.getValue()+"'");
				}
				/** 包组名称 */
				if("projectId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and ( t.project.objId = '"+(String)queryParam.getValue()+"' or t.project.objId in ( select p.objId from  Project p where p.parentId = '"+(String)queryParam.getValue()+"' ) )");
				}
				/**是否正式*/
				if("useStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append("and t.useStatus ='"+(String)queryParam.getValue()+"'");
				}
				/** 项目监管人 */
				if("monitorId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and (t.project.objId in ");
					hql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.objId")+" from Project tm where tm.objId is not null and tm.monitor.objId='"+(String)queryParam.getValue()+"') ");
					hql.append(" or t.project.objId in(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.objId")+" from Project ts where ts.parentId in");
					hql.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ta.objId")+" from Project ta where ta.objId is not null and ta.monitor.objId='"+(String)queryParam.getValue()+"'))) ");
				}
				/**发送状态*/
				if ("status".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())) {
					if ("wait".equals(queryParam.getValue())) {//发送成功
						hql.append("and t.objId not in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("a.bizId")+" from ApiLog a where a.apiType='"+ApiLogTypeEnum.BULLETIN+"')  ");
					}else if ("true".equals(queryParam.getValue())) {//发送成功
						hql.append("and t.objId in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("a.bizId")+" from ApiLog a where a.apiType='"+ApiLogTypeEnum.BULLETIN+"' and a.status='"+ApiLogStatusEnum.APILOG_TRUE+"')  ");
					}else{//发送不成功
						hql.append("and t.objId in (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("a.bizId")+" from ApiLog a where a.apiType='"+ApiLogTypeEnum.BULLETIN+"' and a.status='"+ApiLogStatusEnum.APILOG_FALSE+"')  ");
					}
				}
			}
		}
		hql.append(" order by createTime desc");
		List<Bulletin> list = this.findbyHql(hql.toString());
		return list;
	}
}
