package com.gpcsoft.agreement.bargin.bulletin.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.agreement.bargin.bulletin.dao.BulletinShowDao;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.buyresult.domain.ConfirmResultEnum;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class BulletinShowDaoHibernate extends BaseGenericDaoHibernate<Bulletin> implements BulletinShowDao 
{

	/** 
	 * Description :  获取不同采购价区段的项目数量
	 * Create Date: 2010-10-20下午02:51:41 by likg  Modified Date: 2010-10-20下午02:51:41 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getBulletinListShowByBudgetMoney(final Map<String, Object> paramsMap) throws Exception
	{
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String purCategoryId = (String) paramsMap.get("purCategoryId"); //采购品目id
				
				String bulletinType = (String) paramsMap.get("bulletinType"); //公告类型
				
				//查询
				StringBuffer hql = new StringBuffer("select num, count(idd)" +
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
				" from ECP_Base_Bulletin t " +
				" inner join ECP_Tender_Project p on t.TenderID = p.TenderID " +
				" inner join AUTH_USER U ON U.USR_ID = p.CREUSER " +
				" inner JOIN AUTH_ORG_EMPLOYEE E ON E.EMP_ID = U.EMP_ID " +
				" inner JOIN AUTH_ORGNIZATION O ON O.ORG_ID = E.EMP_COMPANY_ID " +
				" inner JOIN BASE_DISTRICT D ON D.DISTRICT_ID = O.ORG_TOWN "+
				" inner JOIN BASE_DISTRICT DD ON DD.DISTRICT_ID = D.DISTRICT_PARENT_ID " +
				" where t.UseStatus = :useStatus AND t.AUDITSTATUS = :auditStatus and p.useStatus = :useStatus");
				
				//采购品目过滤
				if(StringUtils.hasLength(purCategoryId))
				{
					hql.append(" and p.PURCATEGORY_IDS like '%").append(purCategoryId).append("%' ");
				}
				
				//过滤公告类型
				if(StringUtils.hasLength(bulletinType)) {
					hql.append(" and t.BULLETIN_TYPE = '").append(bulletinType).append("' ");
				}

				hql.append(" )  group by num order by num" );
				
				List<String[]> result = new ArrayList<String[]>();
				List<String[]> queryResult = new ArrayList<String[]>();
				Query query = session.createSQLQuery(hql.toString());
				
				query.setString("useStatus", CommonEnum.USER_STATUS_FORMAL);
				query.setString("auditStatus", AuditStatusEnum.AUDIT_PASS);
				
				queryResult = query.list();
				for(int i=0; i<11; i++)
				{
					result.add(new String[]{"0"});
				}
				for(int i=0; i<queryResult.size(); i++)
				{
					Object[] strs = queryResult.get(i);
					result.set(Integer.parseInt(strs[0].toString())-1, new String[]{strs[1].toString()});
				}
				
				return result;
			}});
	}

	/** 
	 * Description :   获取采购项目公告
	 * Create Date: 2010-10-20下午04:52:51 by likg  Modified Date: 2010-10-20下午04:52:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Bulletin> getBulletinListForShow(final Page<Bulletin> page, final Map<String, Object> paramsMap) throws Exception
	{
		return (Page<Bulletin>) getHibernateTemplate().execute(new HibernateCallback()
		{
			public Page<Bulletin> doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String intervalBudgetMoney = (String) paramsMap.get("intervalBudgetMoney"); //采购金额区间
				String districtId = (String) paramsMap.get("districtId"); //区域id
				String purCategoryId = (String) paramsMap.get("purCategoryId"); //采购品目id
				String budgetMoneySort = (String) paramsMap.get("budgetMoneySort"); //采购金额排序
				String createTimeSort = (String) paramsMap.get("createTimeSort"); //公告创建时间排序
				String keyWord= (String) paramsMap.get("keyWord"); //关键字
				String bulletinType = (String) paramsMap.get("bulletinType"); //公告类型
				String ebuyMethod = (String) paramsMap.get("ebuyMethod"); //采购方式
				
				String queryStr = "select distinct b from Bulletin b " +
						"left join fetch b.project p " +
						"left join fetch p.createUser u " +
							"left join fetch u.emp e " +
								"left join fetch e.company c " +
									"left join fetch c.town t " +
										"left join fetch t.parent tp " +
											"left join fetch tp.parent tpp ";
				//查询总记录数
				String queryCountStr = "select count(distinct b.objId) from Bulletin b " +
						"left join b.project p " +
						"left join p.createUser u " +
							"left join u.emp e " +
								"left join e.company c " +
									"left join c.town t " +
										"left join t.parent tp " +
											"left join tp.parent tpp ";
				
				//采购品目过滤
				if(StringUtils.hasLength(purCategoryId)){
					queryStr += " where p.purCategoryIds like '%" + purCategoryId + "%' ";
					queryCountStr += " where p.purCategoryIds like '%" + purCategoryId + "%' ";
				}else{
					queryStr += " where 1=1 ";
					queryCountStr += " where 1=1 ";
				}

				StringBuilder hql = new StringBuilder();
				
				//状态过滤
				hql.append(" and b.auditStatus = '").append(AuditStatusEnum.AUDIT_PASS).append("' ");
				hql.append(" and b.useStatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
				hql.append(" and p.useStatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
				
				//采购金额区间过滤
				if(StringUtils.hasLength(intervalBudgetMoney))
				{
					String[] intervalMoney = intervalBudgetMoney.split(",");
					hql.append(" and p.budgetTotalMoney >= ").append(intervalMoney[0]);
					hql.append(" and p.budgetTotalMoney < ").append(intervalMoney[1]);
				}
				else
				{
					hql.append(" and p.budgetTotalMoney is not null ");
				}
				
				//区域过滤
				if(StringUtils.hasLength(districtId))
				{
					hql.append(" and (t.objId = '").append(districtId).append("' ")
					.append(" or tp.objId = '").append(districtId).append("' ")
					.append(" or tpp.objId = '").append(districtId).append("') ");
				}
				
				//高级搜索条件
				StringBuilder highparam = new StringBuilder();
				boolean evaltime_0 = StringUtils.hasLength((String)paramsMap.get("evaltime_0"));
				boolean evaltime_1 = StringUtils.hasLength((String)paramsMap.get("evaltime_1"));
				boolean evaltime_2 = StringUtils.hasLength((String)paramsMap.get("evaltime_2"));
				if(evaltime_0 && evaltime_1 && evaltime_2) //查询全部
				{
				}
				else if(evaltime_0 && evaltime_1) //未开始、正在进行报价
				{
					highparam.append(" and p.evalEndTime > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				}
				else if(evaltime_1 && evaltime_2) //正在进行、已结束报价
				{
					highparam.append(" and p.evalStartTime < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				}
				else if(evaltime_0 && evaltime_2) //未开始、已结束报价
				{
					highparam.append(" and (p.evalStartTime > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime())).append(" or p.evalEndTime < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime())).append(")");
				}
				else if(evaltime_0) //未开始报价
				{
					highparam.append(" and p.evalStartTime > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				}
				else if(evaltime_1) //正在进行报价
				{
					highparam.append(" and p.evalStartTime < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
					highparam.append(" and p.evalEndTime > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				}
				else if(evaltime_2) //已结束报价
				{
					highparam.append(" and p.evalEndTime < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				}
				
				if(StringUtils.hasLength((String)paramsMap.get("isRecommendProject"))) //推荐采购项目
				{
					queryStr = "select b from Bulletin b " +
							"left join fetch b.project p " +
								"left join fetch p.createUser u " +
									"left join fetch u.emp e " +
										"left join fetch e.company c " +
											"left join fetch c.town t " +
											"left join fetch t.parent tp " +
											"left join fetch tp.parent tpp" +
							", RecommendProject rp " +
							"where rp.bulletin.objId = b.objId ";
					queryCountStr = "select count(b.objId) from Bulletin b " +
							"left join b.project p " +
								"left join p.createUser u " +
									"left join u.emp e " +
										"left join e.company c " +
											"left join c.town t " +
												"left join t.parent tp " +
													"left join tp.parent tpp" +
							", RecommendProject rp " +
							"where rp.bulletin.objId = b.objId ";
				}
				
				//过滤公告类型
				if(StringUtils.hasLength(bulletinType)) {
					highparam.append(" and b.bullType = '").append(bulletinType).append("' ");
				}
				
				//过滤采购方式
				if(StringUtils.hasLength(ebuyMethod)) {
					highparam.append(" and p.ebuyMethod = '").append(ebuyMethod).append("' ");
				}
				
				//关键字(项目公告名称)
				if(StringUtils.hasLength(keyWord))
				{
					highparam.append(" and b.bullTitle like '%").append(keyWord).append("%'");
				}
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				orderHql.append(" order by ");
				if(StringUtils.hasLength(budgetMoneySort) || StringUtils.hasLength(createTimeSort))
				{
					if(StringUtils.hasLength(budgetMoneySort))
					{
						orderHql.append(" p.budgetTotalMoney ").append(budgetMoneySort).append(" ,");
					}
					if(StringUtils.hasLength(createTimeSort))
					{
						orderHql.append(" b.createTime ").append(createTimeSort);
					}
					
					if(orderHql.toString().endsWith(","))
					{
						orderHql.deleteCharAt(orderHql.length()-1);
					}
				}
				else
				{
					orderHql.append(" b.createTime desc");
				}
				
				Query query = session.createQuery(queryStr + hql.toString() + highparam + orderHql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<Bulletin> bulletinList=query.list();
				page.setData(bulletinList);
				
				query = session.createQuery(queryCountStr + hql.toString() + highparam);
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
			}
		});
	}
	
	
	/** 
	 * Description :   获取采购预告
	 * Create Date: 2010-12-30下午04:52:51 by dongcl  Modified Date: 2010-12-30下午04:52:51 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Object> getBulletinBuyPreList(final Page<Object> page, final Map<String, Object> paramsMap) throws Exception
	{
		return (Page<Object>) getHibernateTemplate().execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				
				String createTimeSort = (String) paramsMap.get("createTimeSort"); //公告创建时间排序
				String keyWord= (String) paramsMap.get("keyWord"); //关键字				
				
				String queryStr = "select b from Bulletin b  where  1=1 and b.project is null and b.relStatus = '02' ";
				//查询总记录数
				String queryCountStr = "select count(b.objId) from Bulletin b  where 1=1 and b.project is null and b.relStatus = '02' ";
				
				StringBuilder hql = new StringBuilder();					
				//搜索条件
				StringBuilder highparam = new StringBuilder();
				
				//关键字(公告名称)
				if(keyWord!=null && StringUtils.hasLength(keyWord))
				{
					highparam.append(" and (b.bullTitle like upper('%").append(keyWord).append("%') or b.bullTitle like lower('%").append(keyWord).append("%'))");
				}
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				orderHql.append(" order by ");
				if(StringUtils.hasLength(createTimeSort))
				{					
					orderHql.append(" b.createTime ").append(createTimeSort);					
				}
				else
				{
					orderHql.append(" b.createTime desc");
				}
				
				Query query = session.createQuery(queryStr + hql.toString() + highparam + orderHql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<Object> bulletinList=query.list();
				page.setData(bulletinList);
				
				query = session.createQuery(queryCountStr + hql.toString() + highparam);
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
			}
		});
	}

	/** 
	 * Description :  根据不同的采购价区间获得包含采购项目公告数量的区域信息
	 * Create Date: 2010-10-20下午06:17:43 by likg  Modified Date: 2010-10-20下午06:17:43 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getDistrictListForShow(final Map<String, Object> param) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String intervalBudgetMoney = (String) param.get("intervalBudgetMoney"); //采购价格区间
				Short districtLevel = (Short) param.get("districtLevel"); //区域级别
				String districtId = (String) param.get("districtId"); //区域Id
				String purCategoryId = (String) param.get("purCategoryId"); //采购品目Id
				String bulletinType = (String) param.get("bulletinType"); //公告类型
				
				StringBuilder sql = new StringBuilder("select a.district_id, a.district_name, b.bulletin_count " +
						" from base_district a, " +
						" (select m.district_id, count(n.Bulletin_ID) bulletin_count " +
								" from base_district m, " +
								" (select t.Bulletin_ID , O.ORG_TOWN , D.DISTRICT_PARENT_ID ORG_CITY,DD.DISTRICT_PARENT_ID ORG_PROVINCE" +
								" from ECP_Base_Bulletin t " +
								" join ECP_Tender_Project p on t.TenderID = p.TenderID " +
								" join AUTH_USER U ON U.USR_ID = p.CREUSER " +
								" join AUTH_ORG_EMPLOYEE E ON E.EMP_ID = U.EMP_ID " +
								" join AUTH_ORGNIZATION O ON O.ORG_ID = E.EMP_COMPANY_ID " +
								" join BASE_DISTRICT D ON D.DISTRICT_ID = O.ORG_TOWN "+
								" join BASE_DISTRICT DD ON DD.DISTRICT_ID = D.DISTRICT_PARENT_ID " +
								" where T.UseStatus = :useStatus AND T.AUDITSTATUS = :auditStatus and p.useStatus = :useStatus");
				
								//采购品目过滤
								if(StringUtils.hasLength(purCategoryId)) {
									sql.append(" and p.PURCATEGORY_IDS like '%").append(purCategoryId).append("%' ");
								}
								
								//过滤公告类型
								if(StringUtils.hasLength(bulletinType)) {
									sql.append(" and t.BULLETIN_TYPE = '").append(bulletinType).append("' ");
								}
								
								sql.append(" and p.BUDGET_TOTAL_MONEY >= :ltm and p.BUDGET_TOTAL_MONEY < :gtm " +
								" ) n " +
								" where n.ORG_TOWN = m.district_id OR M.DISTRICT_ID = N.ORG_CITY OR M.DISTRICT_ID = N.ORG_PROVINCE " +
								" group by m.district_id) b " +
								" where a.district_id = b.district_id " +
								" and a.dis_level = '1'") ;
								
				//过滤区域
				if(StringUtils.hasLength(districtId)) {
					sql.append(" and a.district_parent_id in (:districtId) ");
				}
				if(districtLevel != 0) {
					sql.append(" and a.dis_level =:districtLeval ");
				}
				sql.append(" order by a.district_name");
				
				Query query = session.createSQLQuery(sql.toString());
				
				//设置过滤状态
				query.setString("useStatus", CommonEnum.USER_STATUS_FORMAL);
				query.setString("auditStatus", AuditStatusEnum.AUDIT_PASS);
				
				//过滤采购价格
				if(StringUtils.hasLength(intervalBudgetMoney)) {
					String[] intervalMoney = intervalBudgetMoney.split(",");
					query.setString("ltm", intervalMoney[0]);
					query.setString("gtm", intervalMoney[1]);
				} else {
					query.setString("ltm", "0");
					query.setString("gtm", Long.MAX_VALUE+"");
				}
				
				if(StringUtils.hasLength(districtId)) {
					query.setParameterList("districtId", districtId.split(","));
				}
				if(districtLevel != 0) {
					query.setShort("districtLeval", districtLevel);
				}
				
				return query.list();
			}
		});
	}

	/** 
	 * Description :  根据条件获得不同状态的项目公告
	 * Create Date: 2010-10-23上午10:34:16 by likg  Modified Date: 2010-10-23上午10:34:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Bulletin> getBulletinForList(final Page<Bulletin> page, final Map<String, Object> paramsMap) throws Exception {
		return (Page<Bulletin>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String status = (String) paramsMap.get("status"); //项目状态（查询过滤条件）
				String categoryCode = (String) paramsMap.get("categoryCode"); //采购品目
				String orgInfoId = (String) paramsMap.get("orgInfoId"); //机构id
				String bulletinType = (String) paramsMap.get("bulletinType"); //公告类型
				
				String queryhql = "select b from Bulletin b join fetch b.project p where 1=1 ";
				//查询总记录数
				String queryCountStr = "select count(b.objId) from Bulletin b join b.project p where 1=1 ";
				String orderhql = " order by b.createTime desc";
				StringBuilder hql = new StringBuilder();
				hql.append(" and b.auditStatus = '").append(AuditStatusEnum.AUDIT_PASS).append("' ");
				hql.append(" and p.useStatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
				hql.append(" and b.useStatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
				
				//公告类型过滤
				if(StringUtils.hasLength(bulletinType)) {
					hql.append(" and b.bullType = '").append(bulletinType).append("' ");
				}
				//采购品目过滤
				if(StringUtils.hasLength(categoryCode)) {
					hql.append(" and p.purCategoryIds like '%").append(categoryCode).append("%'");
				}
				
				//交易完成的项目公告
				if("bargained".equals(status)) {
					queryhql = "select b from Bulletin b join fetch b.project p, BuyResult br where br.project.objId = p.objId and br.buyrResult = '" + ConfirmResultEnum.CONFIRM + "' ";
					queryCountStr = "select count(b.objId) from Bulletin b join b.project p, BuyResult br where br.project.objId = p.objId and br.buyrResult = '" + ConfirmResultEnum.CONFIRM + "' ";
					orderhql = " order by p.evalEndTime desc";
				}
				//正在交易的项目公告
				else if("bargaining".equals(status)) {
					//进行中的招标公告
					if(BulletinTypeEnum.PURCHASE_BULLETIN.equals(bulletinType)) {
						hql.append(" and p.projProcessStatus != '").append(ProjProcessStatusEnum.PUT_ON_RECORDS).append("' ");
					} else {
						hql.append(" and p.evalStartTime < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime())).append(" and p.evalEndTime > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
						orderhql = " order by p.evalStartTime desc";
					}
				}
				//竞价预告(未开始报价项目公告)
				else if("purchasePreview".equals(status)) {
					queryhql = "select b from Bulletin b left join b.project p left join p.createUser u left join u.emp e left join e.company c left join c.town t left join t.parent tp left join tp.parent tpp where 1=1 ";
					queryCountStr = "select count(b.objId) from Bulletin b left join b.project p left join p.createUser u left join u.emp e left join e.company c left join c.town t left join t.parent tp left join tp.parent tpp where 1=1 ";
					hql.append(" and p.evalStartTime > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
					hql.append(" and p.budgetTotalMoney is not null ");
				}
				//采购公告(项目ID为空)
				else if("buyPreview".equals(status)) {
					hql = new StringBuilder();
					queryhql = "select b from Bulletin b  where b.project is null and b.relStatus = '02' ";
					queryCountStr = "select count(b.objId) from Bulletin b  where b.project is null and b.relStatus = '02' ";					
				}
				//招标公告
				else if("biddingBulletin".equals(status)) {
					hql.append(" and b.bullType = '").append(BulletinTypeEnum.PURCHASE_BULLETIN).append("' ");
				}
				//中标公告
				else if("winbidBulletin".equals(status)) {
					hql.append(" and b.bullType = '").append(BulletinTypeEnum.RESULT_BULLETIN).append("' ");
				}
				//相关公告（品目相关或公告发布者）
				else if("relatedBulletin".equals(status)) {
					hql.append(" and ( ");
					String purCategoryIds = (String) paramsMap.get("purCategoryIds"); //项目相关采购品目
					//采购品目相关
					if(StringUtils.hasLength(purCategoryIds)) {
						for(String purCategoryId : purCategoryIds.split(",")) {
							hql.append(" p.purCategoryIds like '%").append(purCategoryId).append("%' or ");
						}
					}
					
					//发布者相关
					if(StringUtils.hasLength(orgInfoId)) {
						hql.append(" p.buyersId = '").append(orgInfoId).append("' ");
					} else if(StringUtils.hasLength(purCategoryIds)) {
						hql.append(" 1=2 ");
					} else {
						hql.append(" 1=1 ");
					}
					hql.append(" ) ");
				}
				
				Query query = session.createQuery(queryhql + hql.toString() + orderhql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<Bulletin> bulletinList=query.list();
				page.setData(bulletinList);
				
				query = session.createQuery(queryCountStr + hql.toString());
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
			}
		});
	}

	/** 
	 * Description :  获取商品分类信息
	 * Create Date: 2010-11-23上午10:08:27 by likg  Modified Date: 2010-11-23上午10:08:27 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getGoodsClassList(final Map<String, Object> params) throws Exception 
	{
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String purCategoryId = (String) params.get("purCategoryId"); //采购品目id
				String purCategoryCode = (String) params.get("purCategoryCode"); //采购品目编号
				Boolean isLeaf = (Boolean) params.get("isLeaf"); //是否叶子分类
				String intervalBudgetMoney = (String) params.get("intervalBudgetMoney"); //采购价格区间
				String districtId = (String) params.get("districtId"); //区域id
				String bulletinType = (String) params.get("bulletinType"); //公告类型

				StringBuilder sql = new StringBuilder("select gcid, gcname, count(gcidnum) from " +
						"( select b.id gcid, b.category_Name gcname, count(b.id) gcidnum, ri.project_id projid " +
						"from ECP_Base_Bulletin t " + 
						"join ECP_Tender_Project p on t.TenderID = p.TenderID " +
						"join AUTH_USER U on U.USR_ID = p.CREUSER " + 
						"join AUTH_ORG_EMPLOYEE E on E.EMP_ID = U.EMP_ID " + 
						"join AUTH_ORGNIZATION O on O.ORG_ID = E.EMP_COMPANY_ID " + 
						"join BASE_DISTRICT D on D.DISTRICT_ID = O.ORG_TOWN " + 
						"join BASE_DISTRICT DD on DD.DISTRICT_ID = D.DISTRICT_PARENT_ID " + 
						"join BASE_DISTRICT DDD on DDD.DISTRICT_ID = DD.DISTRICT_PARENT_ID " + 
						", EPS_AGREEMENT_REQUIRE_ITEM ri " + 
						"join PURCATALOG_CATEGORY b on ri.PUR_CATEGORY_ID = b.id " + 
						"where t.AuditStatus = :fAuditStatus " +
						"and t.UseStatus = :fUseStatus " +
						"and p.UseStatus = :fUseStatus " +
						"and ri.PROJECT_ID = p.TenderID ");
				
				//根据采购品目过滤
				if(StringUtils.hasLength(purCategoryId)) {
					String tempSql = "";
					for(String id : purCategoryId.split(",")) {
						if(!tempSql.equals("")) { tempSql += " or "; }
						tempSql += " b.ID like '" + id + "%' ";
					}
					sql.append(" and ( ").append(tempSql).append(" ) ");
				}
				if(StringUtils.hasLength(purCategoryCode)) 
				{
					sql.append(" and b.category_Code like :purCategoryCode ");
				}
				if(isLeaf) 
				{
					sql.append(" and b.PURCATEGORY_IS_LEAF = :isLeaf ");
				}
				if(StringUtils.hasLength(intervalBudgetMoney)) 
				{
					String[] intervalMoney = intervalBudgetMoney.split(",");
					sql.append(" and p.BUDGET_TOTAL_MONEY >= ").append(intervalMoney[0]);
					sql.append(" and p.BUDGET_TOTAL_MONEY < ").append(intervalMoney[1]);
				}
				if(StringUtils.hasLength(districtId)) 
				{
					sql.append(" and (D.DISTRICT_ID = '").append(districtId).append("' ");
					sql.append(" or DD.DISTRICT_ID = '").append(districtId).append("' ");
					sql.append(" or DDD.DISTRICT_ID = '").append(districtId).append("') ");
				}
				
				//过滤公告类型
				if(StringUtils.hasLength(bulletinType)) {
					sql.append(" and t.BULLETIN_TYPE = '").append(bulletinType).append("' ");
				}
				
				sql.append(" group by b.id, b.category_Name, ri.project_id ) ");
				sql.append(" group by gcid, gcname order by gcname");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("fAuditStatus", AuditStatusEnum.AUDIT_PASS);
				query.setString("fUseStatus", CommonEnum.USER_STATUS_FORMAL);
				
				if(StringUtils.hasLength(purCategoryCode)) 
				{
					query.setString("purCategoryCode", purCategoryCode+"%");
				}
				if(isLeaf) 
				{
					query.setBoolean("isLeaf", isLeaf);
				}
				
				List<String[]> gcList=query.list();
				
				return gcList;
			}
		});
	}

	/** 
	 * Description :  获取机构发布的竞价公告和招标公告的数量
	 * Create Date: 2011-2-16下午04:33:13 by likg  Modified Date: 2011-2-16下午04:33:13 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Long getBulletinNum(final Map<String, Object> param) throws Exception {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String orgInfoId = (String) param.get("orgInfoId"); //机构id
				StringBuilder hql = new StringBuilder("select count(b.objId) from Bulletin b where 1=1");
				hql.append(" and (b.bullType = '").append(BulletinTypeEnum.PURCHASE_BULLETIN).append("' ");
				hql.append(" or b.bullType = '").append(BulletinTypeEnum.BARGIN_BULLETIN).append("' )");
				
				if(StringUtils.hasLength(orgInfoId)) {
					hql.append(" and b.project.buyersId = '").append(orgInfoId.toString()).append("'");
				}
				
				Query query = session.createQuery(hql.toString());
				return query.uniqueResult();
			}
		});
	}

	/** 
	 * Description :  批量更新状态
	 * Create Date: 2011-3-11下午10:51:30 by yucy  Modified Date: 2011-3-11下午10:51:30 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateStatus(final String objIds,final Map<String, Object> param)throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String updateHql = " update Bulletin b ";
				String columHql = "";
				if(StringUtils.hasLength((String)param.get("auditStatus"))){
					columHql += " set b.auditStatus = '"+(String)param.get("auditStatus")+"'";
				}
				if(StringUtils.hasLength((String)param.get("relStatus"))){
					columHql += (StringUtils.hasLength(columHql)?",":"set")+ " b.relStatus = '"+(String)param.get("relStatus")+"'";
				}
				updateHql += columHql+ " where b.objId in (:objIds)";
				Query query = session.createQuery(updateHql);
				query.setParameterList("objIds", objIds.split(","));
				return query.executeUpdate()>0;
			}
		});
	}

}
