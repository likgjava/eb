package com.gpcsoft.epp.worktask.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.worktask.dao.WaitprocWorkTaskDao;
import com.gpcsoft.epp.worktask.domain.WaitprocWorkTask;
import com.gpcsoft.epp.worktask.domain.WorkTaskReceiverTypeEnum;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.plugin.wflow.domain.TaskModel;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class WaitprocWorkTaskDaoHibernate extends BaseGenericDaoHibernate<WaitprocWorkTask> implements WaitprocWorkTaskDao {

	/**
	 * @Description:删除待办任务
	 * @param bizId
	 * @param userId
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-8-23 下午01:15:50 By wanghz
	 */
	public void  deleteWaitProcWorkTaskByBizIdAndUserId(final String bizId,String userId,final String paramtypeCode)throws Exception{
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String hql = " from WaitprocWorkTask as work where work.bizId = ? and work.worktaskType = ? ";
				List<WaitprocWorkTask> waitprocWorkTaskList = session.createQuery(hql).setString(0, bizId).setString(1, paramtypeCode).list();
				if (null != waitprocWorkTaskList && waitprocWorkTaskList.size()>0) {
					
					hql = "DELETE WorktaskReceive where workTask.objId=:objId";
					session.createQuery(hql).setString("objId", waitprocWorkTaskList.get(0).getObjId()).executeUpdate();
					
					hql = "DELETE WaitprocWorkTask where objId=:objId";
					session.createQuery(hql).setString("objId", waitprocWorkTaskList.get(0).getObjId()).executeUpdate();
				}
				return null;
			}});
	}
	
    /**
     * @Description: 扩展查询，根据query返回待办任务
     * @param 
     * @param 
     * @param 
     * @return List<WaitprocWorkTask>
     * @throws Exception
     * @Create Date 2010-8-23 下午02:17:02 By wanghz
     */
    public List<WaitprocWorkTask> getAllByQueryObject(QueryObject<WaitprocWorkTask> queryObject)throws Exception{
    	return this.findByQuery(queryObject);
    }

    /** 
	 * Description :  根据ID删除待办任务和待办任务的接收者信息
	 * Create Date: 2010-8-24下午03:51:21 by liuy  Modified Date: 2010-8-24下午03:51:21 by liuy
	 * @param workFlowTaskId
	 * @throws Exception
	 */
    public void removeById(final String workFlowTaskId) throws Exception {
    	getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String hql = " from WaitprocWorkTask as work where work.objId = ? ";
				List<WaitprocWorkTask> waitprocWorkTaskList = session.createQuery(hql).setString(0, workFlowTaskId).list();
				if (null != waitprocWorkTaskList && waitprocWorkTaskList.size()>0) {
					hql = "DELETE WorktaskReceive where workTask.objId=:objId";
					session.createQuery(hql).setString("objId", waitprocWorkTaskList.get(0).getObjId()).executeUpdate();
					
					hql = "DELETE WaitprocWorkTask where objId=:objId";
					session.createQuery(hql).setString("objId", waitprocWorkTaskList.get(0).getObjId()).executeUpdate();
				}
				return null;
			}});
	}

    /** 
	 * Description :  根据查询条件获得对应的待办任务数据
	 * Create Date: 2010-8-24下午05:04:27 by liuy  Modified Date: 2010-8-24下午05:04:27 by liuy
	 * @param queryObject
	 * @return
	 * @throws Exception
	 */
	public Page findByTaskModelByQueryObject(QueryObject queryObject, Page page)
			throws Exception {
		
		return null;
	}

	/** 
	 * Description :  拿到指定当前用户的待办任务
	 * Create Date: 2010-8-24下午05:40:37 by liuy  Modified Date: 2010-8-24下午05:40:37 by liuy
	 * @param queryObject
	 * @param page
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List getTaskModelByUser(final QueryObject queryObject,final Page<TaskModel> page, final User user) throws Exception {
		return (List)getHibernateTemplate().execute(new HibernateCallback(){
			public List doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append(" from WaitprocWorkTask as workTask ");
				hql.append(" where workTask.objId in ( ");
				hql.append(" select receive.workTask.objId ");
				hql.append(" from WorktaskReceive as receive ");
				hql.append(" where ( receive.receiveId=:receiveId ");
				hql.append(" or receive.receiveId=:empId ) ");
				hql.append(" and receive.worktaskReType=:worktaskReType ");
				List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
				Map<String, String> params = new HashMap<String, String>(1);
				if(queryList != null && !queryList.isEmpty()){
					for (int i = 0; i < queryList.size(); i++) {
						QueryParam queryParam = (QueryParam)queryList.get(i);
						if("worktaskType".equals(queryParam.getName())){
							hql.append(" and receive.workTask.worktaskType=:paramtypeCode ");
							params.put("paramtypeCode", (String)queryParam.getValue());
						}
					}
				}
				hql.append(" ) order by workTask.createTime desc ");
				Query query = session.createQuery(hql.toString());
				query.setString("receiveId", user.getObjId());
				query.setString("empId", user.getEmp().getObjId());
				query.setString("worktaskReType", WorkTaskReceiverTypeEnum.USER);
				for (Entry<String, String> entry:params.entrySet()) {
					query.setString(entry.getKey(), entry.getValue());
				}
				List list = query.list();
				if (null == list || list.isEmpty()) {
					list = new ArrayList();
				}
				return list;
		}});
	}

	/** 
	 * Description :  拿到指定给机构的待办任务
	 * Create Date: 2010-8-24下午05:57:41 by liuy  Modified Date: 2010-8-24下午05:57:41 by liuy
	 * @param queryObject
	 * @param page
	 * @param orgInfo
	 * @return
	 */
	public List getTaskModelByOrgInfo(final QueryObject queryObject,final Page<TaskModel> page, final OrgInfo orgInfo) {
		return (List)getHibernateTemplate().execute(new HibernateCallback(){
			public List doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append(" from WaitprocWorkTask as workTask ");
				hql.append(" where workTask.objId in ( ");
				hql.append(" select receive.workTask.objId ");
				hql.append(" from WorktaskReceive as receive ");
				hql.append(" where receive.receiveId=:receiveId ");
				hql.append(" and receive.worktaskReType=:worktaskReType ");
				List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
				Map<String, String> params = new HashMap<String, String>(1);
				if(queryList != null && !queryList.isEmpty()){
					for (int i = 0; i < queryList.size(); i++) {
						QueryParam queryParam = (QueryParam)queryList.get(i);
						if("worktaskType".equals(queryParam.getName())){
							hql.append(" and receive.workTask.worktaskType=:paramtypeCode ");
							params.put("paramtypeCode", (String)queryParam.getValue());
						}
					}
				}
				hql.append(" ) order by workTask.createTime desc ");
				Query query = session.createQuery(hql.toString());
				query.setString("receiveId", orgInfo.getObjId());
				query.setString("worktaskReType", WorkTaskReceiverTypeEnum.ORG);
				for (Entry<String, String> entry:params.entrySet()) {
					query.setString(entry.getKey(), entry.getValue());
				}
				List list = query.list();
				if (null == list || list.isEmpty()) {
					list = new ArrayList();
				}
				return list;
				
		}});
	}

	/** 
	 * Description :  拿到指定给所有用户的待办任务
	 * Create Date: 2010-8-24下午06:00:36 by liuy  Modified Date: 2010-8-24下午06:00:36 by liuy
	 * @param queryObject
	 * @param page
	 * @return
	 */
	public List getTaskModelByAll(final QueryObject queryObject, final Page<TaskModel> page) {
		return (List)getHibernateTemplate().execute(new HibernateCallback(){
			public List doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append(" from WaitprocWorkTask as workTask ");
				hql.append(" where workTask.objId in ( ");
				hql.append(" select receive.workTask.objId ");
				hql.append(" from WorktaskReceive as receive ");
				hql.append(" where receive.worktaskReType=:worktaskReType ");
				List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
				Map<String, String> params = new HashMap<String, String>(1);
				if(queryList != null && !queryList.isEmpty()){
					for (int i = 0; i < queryList.size(); i++) {
						QueryParam queryParam = (QueryParam)queryList.get(i);
						if("worktaskType".equals(queryParam.getName())){
							hql.append(" and receive.workTask.worktaskType=:paramtypeCode ");
							params.put("paramtypeCode", (String)queryParam.getValue());
						}
					}
				}
				hql.append(" ) order by workTask.createTime desc ");
				Query query = session.createQuery(hql.toString());
				query.setString("worktaskReType", WorkTaskReceiverTypeEnum.ALL);
				for (Entry<String, String> entry:params.entrySet()) {
					query.setString(entry.getKey(), entry.getValue());
				}
				List list = query.list();
				if (null == list || list.isEmpty()) {
					list = new ArrayList();
				}
				return list;
		}});
	}
	
	/**
	 * @Description: 获取待办任务
	 * @param paramtypeCode 任务类别
	 * @param bizId 业务ID
	 * @param parentBizId 顶级业务ID
	 * @return WaitprocWorkTask
	 * @throws Exception
	 * @Create Date 2010-8-26 上午09:52:30 By wanghz
	 */
	public WaitprocWorkTask getWaitprocWorkTask(final String paramtypeCode,final String bizId,final String parentBizId)throws EsException{
		return (WaitprocWorkTask)getHibernateTemplate().execute(new HibernateCallback(){
			public WaitprocWorkTask doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("from WaitprocWorkTask as task where 1=1 ");
				if (null != paramtypeCode && !"".equals(paramtypeCode)) {
					hql.append("and task.worktaskType = '"+paramtypeCode+"' ");
				}
				if (null != bizId && !"".equals(bizId)) {
					hql.append("and task.bizId = '"+bizId+"' ");
				}
				if (null != parentBizId && !"".equals(parentBizId)) {
					hql.append("and task.parentBizId = '"+parentBizId+"' ");
				}
				List<WaitprocWorkTask> waitprocWorkTaskList = session.createQuery(hql.toString()).list();
				if (null != waitprocWorkTaskList && waitprocWorkTaskList.size()>0) {
					if (waitprocWorkTaskList.size() == 1) {
						return waitprocWorkTaskList.get(0);
					}else{
						throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_WAITPROC_WORK_TASK_ERROR)+
						" paramtypeCode["+paramtypeCode+"] bizId["+bizId+"] parentBizId["+parentBizId+"]"
						+messageSource.getMessage(EsExceptionEnum.GET_WAITPROC_WORK_TASK_ROWS));
					}
				} else {
					return new WaitprocWorkTask();
				}
		}});
	}
	
	/**
	 * @Description: 根据任务类型CODE获取所有代办任务
	 * @param worktaskReType 任务接受者类型[接受者为所有、机构、组织、角色或用户]
	 * @param receiveId 接收人ID
	 * @return List<WaitprocWorkTask>
	 * @throws Exception
	 * @Create Date 2010-8-27 上午10:53:34 By wanghz
	 */
	public List<WaitprocWorkTask> getAllWaitprocWorkTask(final String paramtypeCode,final String worktaskReType,final String receiveId)throws EsException{
		return (List<WaitprocWorkTask>)getHibernateTemplate().execute(new HibernateCallback(){
			public List<WaitprocWorkTask> doInHibernate(Session session)throws HibernateException, SQLException {
				// 获取用户自己的待办任务
				StringBuffer hql = new StringBuffer();
				hql.append(" from WaitprocWorkTask as workTask ");
				hql.append(" where workTask.objId in ( ");
				hql.append(" select receive.workTask.objId ");
				hql.append(" from WorktaskReceive as receive ");
				hql.append(" where receive.workTask.worktaskType=:paramtypeCode ");
				hql.append(" and receive.receiveId=:receiveId ");
				hql.append(" and receive.worktaskReType=:worktaskReType ");
				hql.append(" ) ");
				
				// 获取机构下所有待办任务
				if (WorkTaskReceiverTypeEnum.ORG.equals(worktaskReType)) {
					hql.append(" or workTask.objId in ( ");
					hql.append(" select receive_1.workTask.objId");
					hql.append(" from WorktaskReceive as receive_1 ");
					hql.append(" where receive_1.workTask.worktaskType=:paramtypeCode ");
					hql.append(" and receive_1.receiveId in (   ");
					hql.append("  ");
					
					hql.append(" ) ) ");
				}
				
				// TODO By wanghz
				return null;
			}});
	}
	
	/**
	 * @Description: 根据待办任务分类CODE 获取所有OBJECT
	 * @return List<Object[]>
	 * @throws Exception
	 * @Create Date 2010-8-27 上午10:23:16 By wanghz
	 */
	public List<Object[]> getObjectsByParamtypeCodes(final String[] paramtypeCodes)throws EsException{
		return (List<Object[]>)getHibernateTemplate().execute(new HibernateCallback(){
			public List<Object[]> doInHibernate(Session session)throws HibernateException, SQLException {
				if (null != paramtypeCodes && paramtypeCodes.length>0) {
					User user=AuthenticationHelper.getCurrentUser();
					OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
					StringBuffer sql = new StringBuffer();
					sql.append(" SELECT config.TYPE_NAME as name ");
					sql.append(" ,itemValue.ITEM_VALUE_DETAIL as code ");
					
					// 获取记录条数-当前用户
					sql.append(" ,sum( ");
					sql.append(" (SELECT count(distinct task_count_1.WORKTASK_ID) FROM ECP_WORKTASK_RECEIVE receive_count_1 ");
					sql.append(" LEFT JOIN ECP_WAITPROCWORKTASK task_count_1 on task_count_1.WORKTASK_ID = receive_count_1.WORKTASK_ID ");
					sql.append(" WHERE (receive_count_1.RECEIVE_ID = '"+user.getObjId()+"' ");
					sql.append(" or receive_count_1.RECEIVE_ID = '"+user.getEmp().getObjId()+"' ) ");
					sql.append(" and receive_count_1.WORKTASK_RE_TYPE = '"+WorkTaskReceiverTypeEnum.USER+"' ");
					sql.append(" and task_count_1.WORKTASK_TYPE = itemValue.ITEM_VALUE_DETAIL ");
					sql.append(" ) + ");
					
					// 机构过滤
					sql.append(" (SELECT count(distinct task_count_2.WORKTASK_ID) FROM ECP_WORKTASK_RECEIVE receive_count_2 ");
					sql.append(" LEFT JOIN ECP_WAITPROCWORKTASK task_count_2 on task_count_2.WORKTASK_ID = receive_count_2.WORKTASK_ID ");
					sql.append(" WHERE receive_count_2.RECEIVE_ID = '"+orgInfo.getObjId()+"' ");
					sql.append(" and receive_count_2.WORKTASK_RE_TYPE = '"+WorkTaskReceiverTypeEnum.ORG+"' ");
					sql.append(" and task_count_2.WORKTASK_TYPE = itemValue.ITEM_VALUE_DETAIL ");
					sql.append(" ) + ");

					// 获取所有
					sql.append(" (SELECT count(distinct task_count_3.WORKTASK_ID) FROM ECP_WORKTASK_RECEIVE receive_count_3 ");
					sql.append(" LEFT JOIN ECP_WAITPROCWORKTASK task_count_3 on task_count_3.WORKTASK_ID = receive_count_3.WORKTASK_ID ");
					sql.append(" WHERE receive_count_3.WORKTASK_RE_TYPE = '"+WorkTaskReceiverTypeEnum.ALL+"' ");
					sql.append(" and receive_count_3.RECEIVE_ID = '"+WorkTaskReceiverTypeEnum.ALL_VALUE+"' ");
					sql.append(" and task_count_3.WORKTASK_TYPE = itemValue.ITEM_VALUE_DETAIL ");
					sql.append(" ) ");
					sql.append(" )count ");
					
					sql.append(" FROM BASE_SYSCONFIGITEM_VALUE itemValue ");
					sql.append(" LEFT JOIN BASE_SYSCONFIGITEM item on item.ITEM_ID = itemValue.ITEM_ID ");
					sql.append(" LEFT JOIN BASE_SYSCONFIG_TYPE config on config.TYPE_ID = item.TYPE_ID ");
					sql.append(" WHERE itemValue.ITEM_VALUE_DETAIL in ( ? ");
					for (int i=1;i<paramtypeCodes.length;i++) {
						sql.append(",?");
					}
					sql.append(" ) ");
//					sql.append(" order by config.CREATE_TIME,item.CREATE_TIME desc ");
					sql.append(" group BY config.TYPE_NAME, itemValue.ITEM_VALUE_DETAIL ");
					
					SQLQuery sqlQuery =  session.createSQLQuery(sql.toString());
					for (int i=0;i<paramtypeCodes.length;i++) {
						sqlQuery.setString(i, paramtypeCodes[i]);
					}
					List<Object[]> objects = sqlQuery.list();
					if (null != objects && objects.size()>0) {
						return objects;
					}
				}
				return new ArrayList<Object[]>(0);
			}});
	}
	
	/**
	 * @Description: 获取代办任务列表
	 * @param user 
	 * @param orgInfo
	 * @param worktaskType 分类编号
	 * @param name 待办任务名称
	 * @param start
	 * @param pageSize
	 * @param queryType(count:数据记录条数,data:数据)
	 * @return List<WaitprocWorkTask>
	 * @throws Exception
	 * @Create Date 2010-8-27 下午08:29:07 By wanghz
	 */
	@SuppressWarnings("unchecked")
	public Object getAllWaitprocWorkTask(final String worktaskType,final String name,final User user,final OrgInfo orgInfo ,final int start, final int pageSize,final String queryType)throws EsException{
		return (Object)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append(" from WaitprocWorkTask as task ");
				// 获取记录条数-当前用户
				hql.append(" where task.objId in ( ");
				hql.append(" select distinct receive_1.workTask.objId from WorktaskReceive as receive_1 ");
				hql.append(" where receive_1.workTask.worktaskType = '"+worktaskType+"' ");
				if (null != name && !"".equals(name) && !"NULL".equals(name.toUpperCase())) {
					hql.append(" and receive_1.workTask.worktaskName like '%"+name+"%' ");
				}
				hql.append(" and receive_1.worktaskReType = '"+WorkTaskReceiverTypeEnum.USER+"' ");
				hql.append(" and ( ");
				hql.append(" receive_1.receiveId = '"+user.getObjId()+"' ");
				hql.append(" or ");
				hql.append(" receive_1.receiveId = '"+user.getEmp().getObjId()+"' ");
				hql.append(" ) ");
				hql.append(" ) ");
				
				// 机构过滤
				hql.append(" or task.objId in ( ");
				hql.append(" select distinct receive_2.workTask.objId from WorktaskReceive as receive_2 ");
				hql.append(" where receive_2.workTask.worktaskType = '"+worktaskType+"' ");
				if (null != name && !"".equals(name) && !"NULL".equals(name.toUpperCase())) {
					hql.append(" and receive_2.workTask.worktaskName like '%"+name+"%' ");
				}
				hql.append(" and receive_2.worktaskReType = '"+WorkTaskReceiverTypeEnum.ORG+"' ");
				hql.append(" and receive_2.receiveId = '"+orgInfo.getObjId()+"' ");
				hql.append(" ) ");
				
				// 获取所有
				hql.append(" or task.objId in ( ");
				hql.append(" select distinct receive_3.workTask.objId from WorktaskReceive as receive_3 ");
				hql.append(" where receive_3.workTask.worktaskType = '"+worktaskType+"' ");
				if (null != name && !"".equals(name) && !"NULL".equals(name.toUpperCase())) {
					hql.append(" and receive_3.workTask.worktaskName like '%"+name+"%' ");
				}
				hql.append(" and receive_3.worktaskReType = '"+WorkTaskReceiverTypeEnum.ALL+"' ");
				hql.append(" and receive_3.receiveId = '"+WorkTaskReceiverTypeEnum.ALL_VALUE+"' ");
				hql.append(" ) ");
				hql.append(" order by task.createTime desc ");
				
				if ("count".equals(queryType)) {
					Query query = session.createQuery(" select count (task.objId) "+hql.toString());
					return query.list().get(0);
				}
				
				if ("data".equals(queryType)) {
					Query query = session.createQuery(hql.toString());
					query.setFirstResult(start);
					query.setMaxResults(pageSize);
					List<WaitprocWorkTask> list = query.list();
					if (null == list || list.isEmpty()) {
						list = new ArrayList<WaitprocWorkTask>(0);
					}
					return list;
				}
				return null;
			}});
	}
	
	/**
	 * @Description: 删除待办任务接收者
	 * @param workTaskId
	 * @param userId
	 * @return Boolean
	 * @throws Exception
	 * @Create Date 2010-8-29 下午06:38:05 By wanghz
	 */
	public void deleteWorktaskReceiveByWorkTaskIdAndReceiveId(final String workTaskId,final String[] receiveIds)throws EsException{
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				if (null != receiveIds && receiveIds.length>0) {
					StringBuffer hql = new StringBuffer();
					hql.append(" DELETE WorktaskReceive where workTask.objId= ? and receiveId in ( ?");
					for (int i=1;i<receiveIds.length;i++) {
						hql.append(",?");
					}
					hql.append(" ) ");
					Query query = session.createQuery(hql.toString());
					query.setString(0, workTaskId);
					for (int i=0;i<receiveIds.length;i++) {
						query.setString((i+1), receiveIds[i]);
					}
					query.executeUpdate();
				}
				return null;
			}});
	}
	
	/**
	 * @Description: 获取待办任务接受者记录数量
	 * @param workTaskId
	 * @param receiveIds
	 * @return Long
	 * @throws Exception
	 * @Create Date 2010-8-29 下午06:44:14 By wanghz
	 */
	public Long getWorktaskReceiveCountByWorkTaskIdAndReceiveId(final String workTaskId,final String[] receiveIds)throws EsException{
		return (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Long doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append(" select count(receive.objId) from WorktaskReceive as receive where receive.workTask.objId = ? and receive.receiveId in ( ? ");
				for (int i=1;i<receiveIds.length;i++) {
					hql.append(",?");
				}
				hql.append(" ) ");
				Query query = session.createQuery(hql.toString());
				query.setString(0, workTaskId);
				for (int i=0;i<receiveIds.length;i++) {
					query.setString((i+1), receiveIds[i]);
				}
				return (Long)query.list().get(0);
			}});
	}
	
	/**
	 * @Description: 获取待办任务记录条数
	 * @param code 待办任务CODE
	 * @param bizId 业务ID
	 * @param parentBizId 顶级业务ID
	 * @return Bolean
	 * @throws Exception
	 * @Create Date 2010-8-30 下午05:24:30 By wanghz
	 */
	public Long getProjectPlanWorkTaskIsCount(final String code,final String bizId,final String parentBizId)throws EsException{
		return (Long) getHibernateTemplate().execute(new HibernateCallback(){
			public Long doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append(" select count (workTask.objId) from WaitprocWorkTask as workTask where 1=1 ");
				if (null != code && !"".equals(code)) {
					hql.append(" and workTask.worktaskType=:code_1 ");
				}
				if (null != bizId && !"".equals(bizId)) {
					hql.append(" and workTask.bizId=:bizId_1 ");
				}
				if (null != parentBizId && !"".equals(parentBizId)) {
					hql.append(" and workTask.parentBizId=:parentBizId_1 ");
				}
				
				Query query = session.createQuery(hql.toString());
				if (hql.toString().contains("code_1")) {
					query.setString("code_1", code);
				}
				if (hql.toString().contains("bizId_1")) {
					query.setString("bizId_1", bizId);
				}
				if (hql.toString().contains("parentBizId_1")) {
					query.setString("parentBizId_1", parentBizId);
				}
				return (Long)query.list().get(0);
			}});
	}
}
