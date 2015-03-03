package com.gpcsoft.epp.noticemanage.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.noticemanage.dao.NoticeDao;
import com.gpcsoft.epp.noticemanage.domain.Notice;
import com.gpcsoft.epp.noticemanage.domain.NoticeStatusEnum;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class NoticeDaoHibernate extends BaseGenericDaoHibernate<Notice> implements NoticeDao {
	
	public List<SubProjectMTaskPlanSub> getSubProjectMTaskPlanSubBySubProjectId(String subProjectId) throws EsException {
		List<SubProjectMTaskPlanSub> subProjectMTaskPlanSubList = new ArrayList<SubProjectMTaskPlanSub>();
		if(null != subProjectId && !"".equals(subProjectId) && !"NULL".equals(subProjectId.toUpperCase())){
			StringBuffer hql = new StringBuffer();
			hql.append("select task.objId,task.quantity,task.buyMainBody.orgName,task.taskPlanSub.purchaseName,task.taskPlanSub.unit ");
			hql.append("from SubProjectMTaskPlanSub as task ");
			hql.append("where task.project.objId = ? ");
			Session session = getSession();
			Query query = session.createQuery(hql.toString());
			query.setString(0, subProjectId);
			List<Object[]> objects = query.list();
			if(null != objects && objects.size()>0){
				for(Object[] object:objects){
					
					SubProjectMTaskPlanSub subProjectMTaskPlanSub = new SubProjectMTaskPlanSub();
					subProjectMTaskPlanSub.setObjId(object[0]+"");
					subProjectMTaskPlanSub.setQuantity(BigDecimal.valueOf(Double.parseDouble(object[1]+"")));
					OrgInfo orgInfo = new OrgInfo();
					orgInfo.setOrgName(object[2]+"");
					subProjectMTaskPlanSub.setBuyMainBody(orgInfo);
					
					// 申报书条目
					TaskPlanSub taskPlanSub = new TaskPlanSub();
					taskPlanSub.setPurchaseName(object[3]+"");
					taskPlanSub.setUnit(object[4]+"");
					subProjectMTaskPlanSub.setTaskPlanSub(taskPlanSub);
					subProjectMTaskPlanSubList.add(subProjectMTaskPlanSub);
				}
			}
		}
		return subProjectMTaskPlanSubList;
	}
	/** 
	 * Description :  根据项目Id和采购人获取通知书
	 * Create Date: 2010-8-1上午11:59:22 by yangx  Modified Date: 2010-8-1上午11:59:22 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Notice> getListByBuyer(String projectId,String orgInfoId){
		StringBuffer hql = new StringBuffer();
		hql.append("From Notice tn where tn.project.objId in");
		hql.append("(  select t.objId from Project t");
		hql.append(" where  t.parentId='"+projectId+"' or t.objId='"+projectId+"')");
		hql.append(" and tn.useStatus='"+CommonEnum.USER_STATUS_FORMAL+"' and tn.receiveStatus='"+NoticeStatusEnum.RECEVICESTATUS_YES+"'");
		return this.findbyHql(hql.toString());
	}
	
	/** 
	 * Description :  根据项目Id、供应商Id查询待确认的通知书
	 * Create Date: 2010-8-30上午11:57:47 by yangx  Modified Date: 2010-8-30上午11:57:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Notice> getListBySupplier(String projectId,String orgInfoId){
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Notice t where ( t.project.parentId ='"+projectId+"' or t.project.objId= '"+projectId+"' ) and receiveOrg.objId='"+orgInfoId+"'");
		hql.append(" and t.useStatus='"+CommonEnum.USER_STATUS_FORMAL+"' and t.sendStatus='"+NoticeStatusEnum.SENDSTATUS_YES+"'");
		List<Notice> listNotice = this.findbyHql(hql.toString());
		return listNotice;
	}
	
	/**
	 * FuncName: getListByProjectId
	 * Description :  根据项目ID查询项目所有通知书
	 * @param 
	 * @param projectId
	 * @return List<Notice>
	 * @author: zhangsh
	 * @Create Date:2011-11-17  上午09:04:21
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-17  上午09:04:21
	 */
	public List<Notice> getListByProjectId(String projectId){
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Notice t where ( t.project.parentId ='"+projectId+"' or t.project.objId= '"+projectId+"' )");
		List<Notice> listNotice = this.findbyHql(hql.toString());
		return listNotice;
	}
	
	
	/**
	 * FuncName: getNoticeListByQueryObject
	 * Description : 根据QueryObject对象查询通知书列表
	 * @param 
	 * @param queryObject
	 * @return List
	 * @author: liuke
	 * @Create Date:2011-1-28  下午04:59:19
	 * @Modifier: liuke
	 * @Modified Date:2011-1-28  下午04:59:19
	 */
	@SuppressWarnings("unchecked") 
	public List getNoticeListByQueryObject(final QueryObject queryObject) {
	 return  (List)	this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				boolean isForNum = false;               //标识是否只查询数量
				StringBuffer hql = new StringBuffer();
				String preHql = "select count(n.objId)  "; //查询总数
				hql.append("from Notice n where 1=1 ");
				List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
				if(queryList!=null && !queryList.isEmpty()){
					for(int i = 0 ;i<queryList.size();i++){
						QueryParam queryParam  = (QueryParam)queryList.get(i);
						if("num".equals(queryParam.getName())&& null!=queryParam.getValue()&&!"".equals(queryParam.getValue())){
							isForNum = true;
						}
						if("noticeType".equals(queryParam.getName())&& null!=queryParam.getValue()&&!"".equals(queryParam.getValue())){
							hql.append("and n.noticeType in ('"+(String)queryParam.getValue() +"') ");
						}
						if("sendStatus".equals(queryParam.getName())&& null!=queryParam.getValue()&&!"".equals(queryParam.getValue())){
							hql.append("and n.sendStatus = '"+(String)queryParam.getValue()+"' ");
						}
						if("receiveStatus".equals(queryParam.getName())&& null!=queryParam.getValue()&&!"".equals(queryParam.getValue())){
							hql.append("and n.receiveStatus = '"+(String)queryParam.getValue()+"' ");
						}
						if("orgInfoId".equals(queryParam.getName())&& null!=queryParam.getValue()&&!"".equals(queryParam.getValue())){
							hql.append("and n.receiveOrg.objId = '"+(String)queryParam.getValue()+"' ");
						}
					}
				}
				Query query = null;
				if(isForNum){
					query = session.createQuery(preHql+ hql.toString());
				}else{
					query = session.createQuery(hql.toString());
				}
				return query.list();
			}
		});
	}
	
	
	
	/**
	 * FuncName: getNoticePageByQueryObject
	 * Description : 根据QueryObject对象查询通知书Page列表
	 * @param 
	 * @param queryObject
	 * @param page
	 * @return Page
	 * @author: liuke
	 * @Create Date:2011-1-30  上午09:36:00
	 * @Modifier: liuke
	 * @Modified Date:2011-1-30  上午09:36:00
	 */
	public Page getNoticePageByQueryObject(QueryObject queryObject, Page page) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Notice n where 1=1 ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList!=null && !queryList.isEmpty()){
			for(int i = 0 ;i<queryList.size();i++){
				QueryParam queryParam  = (QueryParam)queryList.get(i);
				if("num".equals(queryParam.getName())&& null!=queryParam.getValue()&&!"".equals(queryParam.getValue())){
				}
				if("noticeType".equals(queryParam.getName())&& null!=queryParam.getValue()&&!"".equals(queryParam.getValue())){
					hql.append("and n.noticeType in ('"+(String)queryParam.getValue() +"') ");
				}
				if("sendStatus".equals(queryParam.getName())&& null!=queryParam.getValue()&&!"".equals(queryParam.getValue())){
					hql.append("and n.sendStatus = '"+(String)queryParam.getValue()+"' ");
				}
				if("receiveStatus".equals(queryParam.getName())&& null!=queryParam.getValue()&&!"".equals(queryParam.getValue())){
					hql.append("and n.receiveStatus = '"+(String)queryParam.getValue()+"' ");
				}
				if("orgInfoId".equals(queryParam.getName())&& null!=queryParam.getValue()&&!"".equals(queryParam.getValue())){
					hql.append("and n.receiveOrg.objId = '"+(String)queryParam.getValue()+"' ");
				}
			}
		}
		hql.append("order by n.createTime desc");
		Page pageDate = this.findbyHql(hql.toString(), page.getStart(), page.getPageSize());
		return pageDate;
	}
	/**
	 * FuncName: getNotice
	 * Description :根据项目id和供应商id获取通知书
	 * @param 
	 * @return Notice
	 * @author: shenjz
	 * @Create Date:2011-3-31  下午08:18:31
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-31  下午08:18:31
	 */
	public Notice getNoticeBy(String projectId){
		StringBuffer sb = new StringBuffer();
		sb.append("from Notice n where n.project.objId = ? and n.receiveOrg.objId = ? and n.receiveStatus=?");
		List<Notice> list = this.findbyHql(sb.toString(),projectId,AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId(),CommonEnum.USER_STATUS_FORMAL);
		if(list!=null&&list.size()!=0){
			return  list.get(0);
		}
		return null;
	}
}
