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
import org.springframework.util.StringUtils;

import com.gpcsoft.agreement.bargin.project.dao.ProjectShowDao;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.buyresult.domain.ConfirmResultEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.baseData.domain.District;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class ProjectShowDaoHibernate extends BaseGenericDaoHibernate<Project> implements ProjectShowDao {
	
	/** 
	 * Description :  获取采购金额范围列表（供列表展示使用）
	 * Create Date: 2011-8-16上午08:51:14 by likg  Modified Date: 2011-8-16上午08:51:14 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getAmountRangeListForShow(final Map<String, Object> paramsMap) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String purCategoryId = (String) paramsMap.get("purCategoryId"); //采购品目id
				String ebuyMethod = (String) paramsMap.get("ebuyMethod"); //采购方式
				
				//查询
				StringBuffer sql = new StringBuffer("select num, count(idd)" +
				" from ("+
				" select p.tenderid idd, " + 
				" case when p.budget_total_money >= 0 and p.budget_total_money < 1000 then 1 " +
				" when p.budget_total_money >= 1000 and p.budget_total_money < 5000 then 2 " +
				" when p.budget_total_money >= 5000 and p.budget_total_money < 10000 then 3 " +
				" when p.budget_total_money >= 10000 and p.budget_total_money < 50000 then 4 " +
				" when p.budget_total_money >= 50000 and p.budget_total_money < 100000 then 5 " +
				" when p.budget_total_money >= 100000 and p.budget_total_money < 500000 then 6 " +
				" when p.budget_total_money >= 500000 and p.budget_total_money < 1000000 then 7 " +
				" when p.budget_total_money >= 1000000 and p.budget_total_money < 3000000 then 8 " +
				" when p.budget_total_money >= 3000000 and p.budget_total_money < 5000000 then 9 " +
				" when p.budget_total_money >= 5000000 then 10 " +
				" else 11 " + 
				" end num " +
				" from ECP_Tender_Project p " +
				" where p.useStatus = :useStatus");
				
				//采购品目过滤
				if(StringUtils.hasLength(purCategoryId)) {
					sql.append(" and p.PURCATEGORY_IDS like '%").append(purCategoryId).append("%' ");
				}
				//采购方式过滤
				if(StringUtils.hasLength(ebuyMethod)) {
					sql.append(" and p.tendermethod = '").append(ebuyMethod).append("' ");
				}
				sql.append(" )  group by num order by num" );
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("useStatus", CommonEnum.USER_STATUS_FORMAL);
				
				//组装数据
				List<String[]> result = new ArrayList<String[]>();
				List<String[]> queryResult = new ArrayList<String[]>();
				queryResult = query.list();
				for(int i=0; i<11; i++) {
					result.add(new String[]{"0"});
				}
				for(int i=0; i<queryResult.size(); i++) {
					Object[] strs = queryResult.get(i);
					result.set(Integer.parseInt(strs[0].toString())-1, new String[]{strs[1].toString()});
				}
				
				return result;
			}
		});
	}

	/** 
	 * Description :  获取采购区域列表（供列表展示使用）
	 * Create Date: 2011-8-15上午10:50:29 by likg  Modified Date: 2011-8-15上午10:50:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictListForShow(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String amountRange = (String) param.get("amountRange"); //采购金额范围
				String purCategoryId = (String) param.get("purCategoryId"); //采购品目Id
				String ebuyMethod = (String) param.get("ebuyMethod"); //采购方式
				
				StringBuilder sql = new StringBuilder();
				sql.append(" select d.district_id,d.district_name,pn.num");
				sql.append(" from BASE_DISTRICT d,");
				sql.append(" (select ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().subString("O.ORG_TOWN", 1, 2)).append(" || '0000' province, count(p.tenderid) num");
					sql.append(" from ecp_tender_project p ");
					sql.append(" join AUTH_USER U ON U.USR_ID = p.CREUSER");
					sql.append(" join AUTH_ORG_EMPLOYEE E ON E.EMP_ID = U.EMP_ID");
					sql.append(" join AUTH_ORGNIZATION O ON O.ORG_ID = E.EMP_COMPANY_ID");
					sql.append(" where p.useStatus = :useStatus");
					//采购品目过滤
					if(StringUtils.hasLength(purCategoryId)) {
						sql.append(" and p.PURCATEGORY_IDS like '%").append(purCategoryId).append("%' ");
					}
					//采购方式过滤
					if(StringUtils.hasLength(ebuyMethod)) {
						sql.append(" and p.tendermethod = '").append(ebuyMethod).append("' ");
					}
					//采购金额过滤
					if(StringUtils.hasLength(amountRange)) {
						String[] intervalMoney = amountRange.split(",");
						sql.append(" and p.BUDGET_TOTAL_MONEY >= ").append(intervalMoney[0]);
						sql.append(" and p.BUDGET_TOTAL_MONEY < ").append(intervalMoney[1]);
					}
					sql.append(" group by ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().subString("O.ORG_TOWN", 1, 2)).append(") pn");
				sql.append(" where d.district_id = pn.province");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("useStatus", CommonEnum.USER_STATUS_FORMAL);
				
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取采购品目列表（供列表展示使用）
	 * Create Date: 2011-8-15上午10:50:29 by likg  Modified Date: 2011-8-15上午10:50:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getPurCategoryListForShow(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String amountRange = (String) param.get("amountRange"); //采购金额范围
				String districtId = (String) param.get("districtId"); //区域Id
				String ebuyMethod = (String) param.get("ebuyMethod"); //采购方式
				
				StringBuilder sql = new StringBuilder();
				sql.append(" select p.PURCATEGORY_IDS, p.PURCATEGORY_NAMES");
				sql.append(" from ecp_tender_project p ");
				sql.append(" join AUTH_USER U ON U.USR_ID = p.CREUSER");
				sql.append(" join AUTH_ORG_EMPLOYEE E ON E.EMP_ID = U.EMP_ID");
				sql.append(" join AUTH_ORGNIZATION O ON O.ORG_ID = E.EMP_COMPANY_ID");
				sql.append(" where p.useStatus = :useStatus");
				//区域过滤
				if(StringUtils.hasLength(districtId)) {
					sql.append(" and O.ORG_TOWN like '").append(districtId.subSequence(0, 2)).append("%' ");
				}
				//采购方式过滤
				if(StringUtils.hasLength(ebuyMethod)) {
					sql.append(" and p.tendermethod = '").append(ebuyMethod).append("' ");
				}
				//采购金额过滤
				if(StringUtils.hasLength(amountRange)) {
					String[] intervalMoney = amountRange.split(",");
					sql.append(" and p.BUDGET_TOTAL_MONEY >= ").append(intervalMoney[0]);
					sql.append(" and p.BUDGET_TOTAL_MONEY < ").append(intervalMoney[1]);
				}
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("useStatus", CommonEnum.USER_STATUS_FORMAL);
				
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取项目列表（供列表展示使用）
	 * Create Date: 2011-8-15下午01:16:20 by likg  Modified Date: 2011-8-15下午01:16:20 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Project> getProjectListForShow(final Page<Project> page, final Map<String, Object> paramsMap) throws Exception {
		return (Page<Project>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String amountRange = (String) paramsMap.get("amountRange"); //采购金额范围
				String districtId = (String) paramsMap.get("districtId"); //区域id
				String purCategoryId = (String) paramsMap.get("purCategoryId"); //采购品目id
				String keyWord= (String) paramsMap.get("keyWord"); //关键字
				String ebuyMethod = (String) paramsMap.get("ebuyMethod"); //采购方式
				String projectProcess = (String) paramsMap.get("projectProcess"); //项目进度
				String isRecommendProject = (String) paramsMap.get("isRecommendProject"); //推荐项目
				String createTimeSort = (String) paramsMap.get("createTimeSort"); //项目创建时间排序
				
//				String tableHql = null;
//				String countHql = null;
//				//推荐采购项目
//				if(StringUtils.hasLength(isRecommendProject)) {
//					tableHql = " from RecommendProject rp" +
//						" left join rp.project p" +
//						" left join fetch p.createUser u " +
//						" left join fetch u.emp e " +
//						" left join fetch e.company c " +
//						" left join fetch c.town t " +
//						" left join t.parent tp" +
//						" left join tp.parent tpp" +
//						" where 1=1 ";
//					countHql = " from RecommendProject rp left join rp.project p left join p.createUser u left join u.emp e left join e.company c left join c.town t left join t.parent tp left join tp.parent tpp where 1=1 ";
//				}else{
//					tableHql = " from Project p" +
//						" left join fetch p.createUser u " +
//						" left join fetch u.emp e " +
//						" left join fetch e.company c " +
//						" left join fetch c.town t " +
//						" left join t.parent tp" +
//						" left join tp.parent tpp" +
//						" where 1=1 ";
//					countHql = " from Project p left join p.createUser u left join u.emp e left join e.company c left join c.town t left join t.parent tp left join tp.parent tpp where 1=1 ";
//				}
//
//				StringBuilder hql = new StringBuilder();
//				//状态过滤
//				hql.append(" and p.useStatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
//				
//				//采购金额过滤
//				if(StringUtils.hasLength(amountRange)) {
//					String[] intervalMoney = amountRange.split(",");
//					hql.append(" and p.budgetTotalMoney >= ").append(intervalMoney[0]);
//					hql.append(" and p.budgetTotalMoney < ").append(intervalMoney[1]);
//				}
//				
//				//采购品目过滤
//				if(StringUtils.hasLength(purCategoryId)) {
//					hql.append(" and p.purCategoryIds like '%").append(purCategoryId).append("%' ");
//				}
//				
//				//区域过滤
//				if(StringUtils.hasLength(districtId)) {
//					hql.append(" and (t.objId = '").append(districtId).append("' ")
//						.append(" or tp.objId = '").append(districtId).append("' ")
//						.append(" or tpp.objId = '").append(districtId).append("') ");
//				}
//				
//				//项目进度过滤
//				if("bargained".equals(projectProcess)) { //已结束
//					if(EbuyMethodEnum.OPEN_BIDDING.equals(ebuyMethod)) {
//						hql.append(" and p.projProcessStatus >= ").append(ProjProcessStatusEnum.CALIBRATION_BID);
//						hql.append(" and p.projProcessStatus != ").append(ProjProcessStatusEnum.BOOK_OPEN_BID_EVALUATION_ROOM);
//					} else {
//						hql.append(" and p.evalEndTime < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
//					}
//				} else if("bargaining".equals(projectProcess)) { //进行中
//					if(EbuyMethodEnum.OPEN_BIDDING.equals(ebuyMethod)) {
//						hql.append(" and p.projProcessStatus < ").append(ProjProcessStatusEnum.CALIBRATION_BID);
//					} else {
//						hql.append(" and p.evalEndTime > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
//					}
//				} else {
//					//获取已发布招标公告的项目
//					if(EbuyMethodEnum.OPEN_BIDDING.equals(ebuyMethod)) {
//						hql.append(" and p.projProcessStatus >= ").append(ProjProcessStatusEnum.SUBMIT_PURCHASE_BULLETIN);
//						hql.append(" and p.projProcessStatus != ").append(ProjProcessStatusEnum.BOOK_OPEN_BID_EVALUATION_ROOM);
//					}
//				}
//				
//				//过滤采购方式
//				if(StringUtils.hasLength(ebuyMethod)) {
//					hql.append(" and p.ebuyMethod = '").append(ebuyMethod).append("' ");
//				}
//				
//				//关键字(项目名称)
//				if(StringUtils.hasLength(keyWord)) {
//					hql.append(" and p.projName like '%").append(keyWord).append("%'");
//				}
//				
//				//排序条件
//				StringBuilder orderHql = new StringBuilder();
//				orderHql.append(" order by ");
//				if(StringUtils.hasLength(createTimeSort)) {
//					orderHql.append(" p.createTime ").append(createTimeSort);
//				} else {
//					orderHql.append(" p.createTime desc");
//				}
//				
//				Query query = session.createQuery("select p " + tableHql + hql + orderHql);
//				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
//				page.setData(query.list());
//				query = session.createQuery("select count(p.objId) " + countHql + hql);
//				page.setTotalRowNum((Long) query.list().get(0));
//				
//				return page;
				
				String column = " select p.tenderid, p.tenderno, p.tendername ," +
						" substr(p.tendermethod ,0,2) ," +
						" p.eval_start_date,"+
						" p.eval_end_date," +
						" prov.district_name prov," +
						" city.district_name city," +
						" town.district_name town," +
						" p.buyers_name" ;

				//连接表
				String table = " from ecp_tender_project p ," +
						"auth_user u ," +
						"auth_org_employee e ," +
						"auth_orgnization org , " +
						"base_district town ," +
						"base_district city ," +
						"base_district prov ";
				
				//推荐采购项目
				if(StringUtils.hasLength(isRecommendProject)) {
					table += " , recommend_project rp where rp.project_id = p.tenderid and";
				}else{
					table += " where ";
				}
				
				//选择条件
				StringBuilder condition = new StringBuilder();
				condition.append(" p.creuser = u.usr_id and u.emp_id = e.emp_id and e.emp_company_id = org.org_id ")
				.append("and org.org_town = town.district_id ")
				.append("and town.district_parent_id = city.district_id ")
				.append("and city.district_parent_id = prov.district_id ");
				
				//状态过滤
				condition.append(" and p.usestatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
				
				//采购金额过滤
				if(StringUtils.hasLength(amountRange)) {
					String[] intervalMoney = amountRange.split(",");
					condition.append(" and p.budget_total_money >= ").append(intervalMoney[0]);
					condition.append(" and p.budget_total_money < ").append(intervalMoney[1]);
				}
				
				//采购品目过滤
				if(StringUtils.hasLength(purCategoryId)) {
					condition.append(" and instr(p.purcategory_ids,'").append(purCategoryId).append("')>0 ");
				}
				
				//区域过滤
				if(StringUtils.hasLength(districtId)) {
					condition.append(" and ( org.org_town = '").append(districtId).append("' or city.district_id = '").append(districtId).append("' or prov.district_id = '").append(districtId).append("' ) ");
				}
				
				//项目进度过滤
				if("bargained".equals(projectProcess)) { //已结束
					if(EbuyMethodEnum.OPEN_BIDDING.equals(ebuyMethod)) {
						condition.append(" and p.processstatus >= ").append(ProjProcessStatusEnum.CALIBRATION_BID);
						condition.append(" and p.processstatus != ").append(ProjProcessStatusEnum.BOOK_OPEN_BID_EVALUATION_ROOM);
					} else {
						condition.append(" and p.eval_end_date < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
					}
				} else if("bargaining".equals(projectProcess)) { //进行中
					if(EbuyMethodEnum.OPEN_BIDDING.equals(ebuyMethod)) {
						condition.append(" and p.processstatus < ").append(ProjProcessStatusEnum.CALIBRATION_BID);
					} else {
						condition.append(" and p.eval_end_date > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
					}
				} else {
					//获取已发布招标公告的项目
					if(EbuyMethodEnum.OPEN_BIDDING.equals(ebuyMethod)) {
						condition.append(" and p.processstatus >= ").append(ProjProcessStatusEnum.SUBMIT_PURCHASE_BULLETIN);
						condition.append(" and p.processstatus != ").append(ProjProcessStatusEnum.BOOK_OPEN_BID_EVALUATION_ROOM);
					}
				}
				
				//过滤采购方式
				if(StringUtils.hasLength(ebuyMethod)) {
					condition.append(" and p.tendermethod = '").append(ebuyMethod).append("' ");
				}
				
				//关键字(项目名称)
				if(StringUtils.hasLength(keyWord)) {
					condition.append(" and instr(p.tendername,'").append(keyWord).append("')>0");
				}
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				orderHql.append(" order by ");
				if(StringUtils.hasLength(createTimeSort)) {
					orderHql.append(" p.credate ").append(createTimeSort);
				} else {
					orderHql.append(" p.credate desc");
				}
				
				Query query = session.createSQLQuery(column + table + condition.toString() + orderHql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<Project> dataList = new ArrayList<Project>();
				//拼装对象
				for(Object[] objects : (List<Object[]>)query.list()){
					Project project = new Project();
					project.setObjId((String)objects[0]);
					project.setProjCode((String)objects[1]);
					project.setProjName((String)objects[2]);
					project.setEbuyMethod((String)objects[3]);
					project.setEvalStartTime((Date)objects[4]);
					project.setEvalEndTime((Date)objects[5]);
					
					User createUser = new User();
					
					Company company = new Company();
					District prov = new District();prov.setName((String)objects[6]);
					District city = new District();city.setName((String)objects[7]);city.setParent(prov);
					District town = new District();town.setName((String)objects[8]);town.setParent(city);
					company.setTown(town);

					Employee emp = new Employee();
					emp.setCompany(company);
					createUser.setEmp(emp);
					project.setCreateUser(createUser);
					
					project.setBuyersName((String)objects[9]);
					dataList.add(project);
				}

				page.setData(dataList);
				
				//count
				query = session.createSQLQuery("select count(p.tenderid) "+table + condition.toString());
				page.setTotalRowNum(((BigDecimal)query.uniqueResult()).longValue());
				return page;
			}
		});
	}

	/** 
	 * Description :  获取电子招标项目列表数据
	 * Create Date: 2011-8-16下午07:56:23 by likg  Modified Date: 2011-8-16下午07:56:23 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Project> getBiddingProjectList(final Page<Project> page, final Map<String, Object> paramsMap) throws Exception {
		return (Page<Project>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String projectProcess = (String) paramsMap.get("projectProcess"); //项目进度状态
				String purCategoryId = (String) paramsMap.get("purCategoryId"); //采购品目
				
				StringBuilder hql = new StringBuilder();
				hql.append(" from Project p where 1=1");
				hql.append(" and p.useStatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
				hql.append(" and p.ebuyMethod = '").append(EbuyMethodEnum.OPEN_BIDDING).append("' ");
				
				//采购品目过滤
				if(StringUtils.hasLength(purCategoryId)) {
					hql.append(" and p.purCategoryIds like '%").append(purCategoryId).append("%'");
				}
				
				//已成交
				if("dealed".equals(projectProcess)) {
					hql.append(" and p.projProcessStatus >= ").append(ProjProcessStatusEnum.CALIBRATION_BID);
					hql.append(" and p.projProcessStatus != '").append(ProjProcessStatusEnum.BOOK_OPEN_BID_EVALUATION_ROOM).append("'");
				}
				//正在交易的项目公告
				else if("bargaining".equals(projectProcess)) {
					hql.append(" and p.projProcessStatus >= ").append(ProjProcessStatusEnum.SUBMIT_PURCHASE_BULLETIN);
					hql.append(" and p.projProcessStatus < ").append(ProjProcessStatusEnum.CALIBRATION_BID);
				}
				
				//排序条件
				String orderhql = " order by p.createTime desc";
				
				Query query = session.createQuery(hql + orderhql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<Bulletin> bulletinList=query.list();
				page.setData(bulletinList);
				query = session.createQuery("select count(p.objId)" + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
			}
		});
	}
	
	/** 
	 * Description :  获取电子采购项目列表数据
	 * Create Date: 2011-8-16下午07:56:23 by likg  Modified Date: 2011-8-16下午07:56:23 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Project> getBargainProjectList(final Page<Project> page, final Map<String, Object> paramsMap) throws Exception {
		return (Page<Project>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String projectProcess = (String) paramsMap.get("projectProcess"); //项目进度状态
				String categoryCode = (String) paramsMap.get("categoryCode"); //采购品目
				
				String queryHql = "from Project p where 1=1";
				String orderhql = " order by p.createTime desc";
				StringBuilder hql = new StringBuilder();
				hql.append(" and p.useStatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
				hql.append(" and p.ebuyMethod = '").append(EbuyMethodEnum.COMPETITION).append("' ");
				
				//采购品目过滤
				if(StringUtils.hasLength(categoryCode)) {
					hql.append(" and p.purCategoryIds like '%").append(categoryCode).append("%'");
				}
				
				//已成交
				if("dealed".equals(projectProcess)) {
					queryHql = "from Project p, BuyResult br where br.project.objId = p.objId and br.buyrResult = '" + ConfirmResultEnum.CONFIRM + "' ";
					orderhql = " order by br.createTime desc";
				}
				//进行中
				else if("bargaining".equals(projectProcess)) {
					hql.append(" and p.evalEndTime > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				}
				
				Query query = session.createQuery("select p " + queryHql + hql + orderhql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<Bulletin> bulletinList=query.list();
				page.setData(bulletinList);
				query = session.createQuery("select count(p.objId) " + queryHql + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
			}
		});
	}

}
