package com.gpcsoft.agreement.bargin.project.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.project.dao.RecommendProjectDao;
import com.gpcsoft.agreement.bargin.project.domain.RecommendProject;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class RecommendProjectDaoHibernate extends BaseGenericDaoHibernate<RecommendProject> implements RecommendProjectDao {
	
	/** 
	 * Description :  保存推荐项目
	 * Create Date: 2010-10-11 下午 16:30:16 by likg  Modified Date: 2010-10-11 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void saveRecommendProject(final String[] projectIds, final RecommendProject recommendProjectPattern) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				//获得已推荐项目的最大排序序号
				Query queryMaxSort = session.createQuery("select max(rp.sort) from RecommendProject rp");
				Long maxSort = (Long) queryMaxSort.uniqueResult();
				if(maxSort == null) {
					maxSort = 0L;
				}
				
				StringBuilder sql = new StringBuilder();
				long mm = new Date().getTime();
				sql.append("insert into RECOMMEND_PROJECT (ID,RECOMMENDER,REASON,PICTURE,AUDIT_STATUS,USE_STATUS,CREATE_TIME,PROJECT_ID,SORT) ");
				sql.append("select p.TENDERID || :mm, :recommender, :reason, :picture, :audit_status, :use_status, :create_time, p.TENDERID, :sort + rownum ");
				sql.append("from ECP_TENDER_PROJECT p ");
				sql.append("where p.TENDERID in (:projectIds)");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setParameterList("projectIds", projectIds);
				query.setParameter("mm", mm);
				query.setParameter("recommender", AuthenticationHelper.getCurrentUser(true).getObjId());
				query.setParameter("reason", recommendProjectPattern.getReason());
				query.setParameter("picture", recommendProjectPattern.getPicture());
				query.setParameter("audit_status", recommendProjectPattern.getAuditStatus());
				query.setParameter("use_status", (recommendProjectPattern.getUseStatus()==true ? "1" : "0"));
				query.setParameter("create_time", recommendProjectPattern.getCreateTime());
				query.setParameter("sort", maxSort);
				
				query.executeUpdate();
				return true;
			}
		});
	}

	/** 
	 * Description :  获取所有的未推荐项目
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Project> listNoRecommendProject(final Map<String,Object> param, final Page<Project> page) throws Exception {
		return (Page<Project>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String projCode = (String) param.get("projCode"); //项目编号
				String projName = (String) param.get("projName"); //项目名称
				String ebuyMethod = (String) param.get("ebuyMethod"); //采购方式
				String amountRange = (String) param.get("amountRange"); //采购金额范围
				String order = (String) param.get("order"); //排序字段
				String order_flag = (String) param.get("order_flag"); //排序方式
				
				StringBuilder hql = new StringBuilder();
				hql.append(" from Project p, Bulletin b where p.objId = b.project.objId");
				hql.append(" and p.objId not in (select rp.project.objId from RecommendProject rp )");
				
				//过滤条件
				hql.append(" and p.useStatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("'");
				hql.append(" and b.useStatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("'");
				hql.append(" and b.auditStatus = '").append(AuditStatusEnum.AUDIT_PASS).append("'");
				if(EbuyMethodEnum.OPEN_BIDDING.equals(ebuyMethod)) {
					hql.append(" and b.bullType = '").append(BulletinTypeEnum.PURCHASE_BULLETIN).append("'");
				} else {
					hql.append(" and b.bullType = '").append(BulletinTypeEnum.BARGIN_BULLETIN).append("'");
				}
				
				//过滤掉已经结束的项目
				hql.append(" and (p.tenderRecord='00' or p.tenderRecord is null)");
				if(StringUtils.hasLength(projCode)) {
					hql.append(" and p.projCode like '%" + projCode + "%'");
				}
				if(StringUtils.hasLength(projName)) {
					hql.append(" and p.projName like '%" + projName + "%'");
				}
				if(StringUtils.hasLength(amountRange)) {
					String[] money = amountRange.split(",");
					hql.append(" and p.budgetTotalMoney between " + money[0] + " and " + money[1]) ;
				}
				if(StringUtils.hasLength(ebuyMethod)) {
					hql.append(" and p.ebuyMethod = '").append(ebuyMethod).append("' ");
				}
				
				//排序
				String orderHql = null;
				if(StringUtils.hasLength(order)){
					orderHql = " order by p." + order + ("true".equals(order_flag) ? " desc " : " asc ");
				}else{
					orderHql = " order by p.createTime desc ";
				}
				
				String queryColumn = "select p.objId,p.projCode,p.projName,p.budgetTotalMoney,p.evalStartTime,p.evalEndTime";
				Query query = session.createQuery(queryColumn + hql + orderHql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				//查询总记录数
				Query queryCount = session.createQuery("select count(*) " + hql);
				Integer totalCount = ((Long)queryCount.list().get(0)).intValue();
				
				return new Page<Project>(page.getStart(), totalCount, page.getPageSize(), query.list());
			}
		});
	}

	/** 
	 * Description :  删除过时的推荐项目
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void deleteOutdatedProject() throws Exception {
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append(" delete from RECOMMEND_PROJECT t where t.id in (");
				hql.append(" select rp.id from RECOMMEND_PROJECT rp");
				hql.append(" join ECP_TENDER_PROJECT p on rp.PROJECT_ID = p.tenderid ");
				hql.append(" and (p.eval_end_date < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				hql.append("     or p.TENDER_KEEP_ON_R = '01')");
				hql.append(" )");
				Query query = session.createSQLQuery(hql.toString());
				query.executeUpdate();
				
				return null;
			}
		});
	}

	/** 
	 * Description :  获得推荐项目列表
	 * Create Date: 2010-10-18下午05:11:35 by likg  Modified Date: 2010-10-18下午05:11:35 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<RecommendProject> getRecommendProject(final Page<RecommendProject> page, final Map<String, Object> paramsMap) throws Exception {
		return (Page<RecommendProject>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String categoryCode = (String) paramsMap.get("categoryCode"); //采购品目编号
				String ebuyMethod = (String) paramsMap.get("ebuyMethod"); //采购方式
				String amountRange = (String) paramsMap.get("amountRange"); //采购金额范围
//				
//				String queryhql = "from RecommendProject rp join fetch rp.project p where 1=1 ";
//				StringBuilder hql = new StringBuilder();
//				//过滤掉已经结束的项目
//				hql.append(" and p.projProcessStatus < ").append(ProjProcessStatusEnum.CALIBRATION_BID);
//				
//				//采购金额范围过滤
//				if(StringUtils.hasLength(amountRange)) {
//					String[] budgetMoney = amountRange.split(",");
//					hql.append(" and p.budgetTotalMoney between ").append(budgetMoney[0]).append(" and ").append(budgetMoney[1]);
//				}
//				//采购品目编号过滤
//				if(StringUtils.hasLength(categoryCode)) {
//					hql.append(" and p.purCategoryIds like '%").append(categoryCode).append("%' ");
//				}
//				//采购方式过滤
//				if(StringUtils.hasLength(ebuyMethod)) {
//					hql.append(" and p.ebuyMethod = '").append(ebuyMethod).append("' ");
//				}
//				
//				String sorthql = " order by rp.sort";
//				Query query = session.createQuery(queryhql + hql + sorthql);
//				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
//				Query queryCount = session.createQuery("select count(rp.objId) from RecommendProject rp join rp.project p where 1=1 " + hql);
//				Integer totalCount = ((Long)queryCount.list().get(0)).intValue();
//				
//				return new Page<RecommendProject>(page.getStart(), totalCount, page.getPageSize(), query.list());
				
				String column = " select rp.id, p.tenderid, p.tenderno, p.tendername, substr(p.tendermethod ,0,2), rp.picture, rp.reason ,p.creuser,p.eval_start_date,p.eval_end_date ,p.budget_total_money ,p.buyers_name,p.sign_up_start_date,p.sign_up_end_date ";

				//连接表
				String table = " from recommend_project rp, ecp_tender_project p where rp.project_id = p.tenderid ";
		
				//选择条件
				StringBuilder condition = new StringBuilder();
				
				//过滤掉已经结束的项目
				condition.append(" and (p.TENDER_KEEP_ON_R = '00' or p.TENDER_KEEP_ON_R is null) ");
				
				//采购金额范围过滤
				if(StringUtils.hasLength(amountRange)) {
					String[] budgetMoney = amountRange.split(",");
					condition.append(" and p.budget_total_money between ").append(budgetMoney[0]).append(" and ").append(budgetMoney[1]);
				}
				//采购品目编号过滤
				if(StringUtils.hasLength(categoryCode)) {
					condition.append(" and instr(p.purcategory_ids, '").append(categoryCode).append("')>0 ");
				}
				//采购方式过滤
				if(StringUtils.hasLength(ebuyMethod)) {
					condition.append(" and p.tendermethod = '").append(ebuyMethod).append("' ");
				}
				
				//排序
				String orderHql = " order by rp.sort";
				
				Query query = session.createSQLQuery(column + table + condition.toString() + orderHql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<RecommendProject> dataList = new ArrayList<RecommendProject>();
				//拼装对象
				for(Object[] objects : (List<Object[]>)query.list()){
					RecommendProject recommendProject = new RecommendProject();
					recommendProject.setObjId((String)objects[0]);
					Project project = new Project();
					project.setObjId((String)objects[1]);
					project.setProjCode((String)objects[2]);
					project.setProjName((String)objects[3]);
					project.setEbuyMethod((String)objects[4]);
					recommendProject.setPicture((String)objects[5]);
					recommendProject.setReason((String)objects[6]);
					User createUser = new User();createUser.setObjId((String)objects[7]);
					project.setCreateUser(createUser);
					project.setEvalStartTime((Date)objects[8]);
					project.setEvalEndTime((Date)objects[9]);
					project.setBudgetTotalMoney((BigDecimal)objects[10]);
					project.setBuyersName((String)objects[11]);
					project.setSignUpSTime((Date)objects[12]);
					project.setSignUpETime((Date)objects[13]);
					recommendProject.setProject(project);
					dataList.add(recommendProject);
				}
				
				page.setData(dataList);
				
				//count
				query = session.createSQLQuery("select count(rp.id) "+table + condition.toString());
				page.setTotalRowNum(((BigDecimal)query.uniqueResult()).longValue());
				return page;
			}
		});
	}
	
	/** 
	 * Description :  修改推荐项目的排序序号
	 * Create Date: 2010-12-22上午10:43:42 by likg  Modified Date: 2010-12-22上午10:43:42 by likg
	 * @param   
	 * @return 
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateSort(final Long sort, final boolean isToUp) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("from RecommendProject rp where 1=1 ");
				
				//过滤条件和排序条件
				if(isToUp){
					hql.append(" and rp.sort <= ").append(sort);
					hql.append(" order by rp.sort desc ");
				}else{
					hql.append(" and rp.sort >= ").append(sort);
					hql.append(" order by rp.sort asc ");
				}
				
				Query query = session.createQuery(hql.toString());
				query.setFirstResult(0).setMaxResults(2);
		
				List<RecommendProject> projectList = query.list();
				if(projectList!=null && projectList.size()==2) {
					RecommendProject sourceRecommendProject = projectList.get(0);
					RecommendProject targetRecommendProject = projectList.get(1);
					
					Long tempSort = sourceRecommendProject.getSort();
					sourceRecommendProject.setSort(targetRecommendProject.getSort());
					targetRecommendProject.setSort(tempSort);
				}
				
				return null;
			}	
		});
	}
	
}
