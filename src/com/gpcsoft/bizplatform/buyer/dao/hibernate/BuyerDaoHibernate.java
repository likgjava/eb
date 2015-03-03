package com.gpcsoft.bizplatform.buyer.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.bizplatform.base.industry.domain.Industry;
import com.gpcsoft.bizplatform.buyer.dao.BuyerDao;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class BuyerDaoHibernate extends BaseGenericDaoHibernate<Buyer> implements BuyerDao {
	
	/** 
	 * Description :  根据orgInfoId获取Buyer
	 * Create Date: 2011-10-24下午06:23:23 by sunl  Modified Date: 2011-10-24下午06:23:23 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Buyer getBuyerByOrgInfoId(final String orgInfoId) throws Exception {
		return (Buyer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "from Buyer b where b.orgInfo.objId=:orgInfoId ";
				Query query = session.createQuery(hql);
				query.setString("orgInfoId", orgInfoId);
				List list = query.list();
				if(list != null && list.size() > 0)
					return list.get(0);
				return null;
		}});
	}
	
	/** 
	 * Description :  更新采购人审核状态
	 * Create Date: 2010-7-28下午02:50:03 by sunl  Modified Date: 2010-7-28下午02:50:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer updateBuyerStatus(final String buyerId,final OrgInfo orgInfo) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append(" update Buyer bu set bu.auditStatus=:auditStatus,bu.verifyUser.objId=:verifyUserId,bu.verifyTime=:verifyTime,bu.opinion=:opinion where bu.objId=:objId");
				Query query = session.createQuery(hql.toString());
				query.setString("auditStatus", orgInfo.getAuditStatus());
				query.setString("objId", buyerId);
				
				query.setString("verifyUserId", AuthenticationHelper.getCurrentUser(true).getObjId());
				query.setDate("verifyTime", new Date());
				query.setString("opinion", orgInfo.getOpinion());
				
				return query.executeUpdate();
			}
		});
	}
	
	/** 
	 * Description :  根据参数获得采购人的展示信息列表，及时加载OrgInfor信息
	 * Create Date: 2010-10-19下午05:22:33 by liangxj  Modified Date: 2010-10-19下午05:22:33 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含所属区域、企业性质、所属行业、排序信息
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Buyer> getBuyerListForShow(final Page<Buyer> page,final Map<String, Object> paramsMap) throws Exception  {
		return (Page<Buyer>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String validSort = (String) paramsMap.get("validSort"); //生效时间排序
				String evalSort = (String) paramsMap.get("evalSort"); //评价排序
				String dealMoneySort = (String) paramsMap.get("dealMoneySort"); //交易金额排序
				String unitType = (String) paramsMap.get("unitType"); //企业性质
				String districtId = (String) paramsMap.get("districtId"); //所属区域
				String keyWord = (String) paramsMap.get("keyWord"); //关键字
				
//				//查询采购人信息
//				String preHql = "from Buyer as s  " +
//									"where s.orgInfo.useStatus = '"+OrganizationEnum.USE_VALID+"' and s.orgInfo.isOff = '"+OrganizationEnum.ENABLE+"' and s.auditStatus = '" + OrganizationEnum.PASS_EXAM + "' ";
//				
//				StringBuilder hql = new StringBuilder();
//				
//				//所属区域过滤
//				if(StringUtils.hasLength(districtId)){
//					hql.append(" and s.orgInfo.districtValue like '%").append(districtId).append("%' ");
//				}
//				
//				//采购人类型
//				if(StringUtils.hasLength(unitType)){
//					hql.append(" and s.orgInfo.entPrpt = '").append(unitType).append("' ");
//				}
//				
//				
//				//高级搜索条件
//				StringBuilder highparam = new StringBuilder();
//				if(StringUtils.hasLength((String)paramsMap.get("belongIndustry"))){
//					highparam.append(" and s.orgInfo.belongIndustry = '").append((String)paramsMap.get("belongIndustry")).append("' ");
//				}
//				if(StringUtils.hasLength((String)paramsMap.get("dealTime"))){
//					highparam.append(" and ( 1 = 0 ");
//					String[] dealTimes = ((String)paramsMap.get("dealTime")).split(",");
//					for (String string : dealTimes) {
//						int t = Integer.parseInt(string);
//						highparam.append(" or s.dealTime >= ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(DateUtil.addDay(new Date(), 0-t)))).append(" ");
//					}
//					highparam.append(" )");
//				}
//				if(StringUtils.hasLength((String)paramsMap.get("moneyTotleLeft"))){
//					highparam.append(" and s.dealTotal >= ").append((String)paramsMap.get("moneyTotleLeft"));
//				}
//				if(StringUtils.hasLength((String)paramsMap.get("moneyTotleRight"))){
//					highparam.append(" and s.dealTotal <= ").append((String)paramsMap.get("moneyTotleRight"));
//				}
//				
//				//关键字(主营产品,机构名称,简介 ,经营范围)
//				if(StringUtils.hasLength(keyWord)){
//					highparam.append(" and (s.orgInfo.bidForRange like '%").append(keyWord).append("%'");
//					highparam.append(" or s.orgInfo.mainProducts like '%").append(keyWord).append("%'");
//					highparam.append(" or s.orgInfo.descCn like '%").append(keyWord).append("%'");
//					highparam.append(" or s.orgInfo.orgName like '%").append(keyWord).append("%')");
//				}
//				
//				
//				hql.append(highparam.toString());
//				
//				//排序条件
//				StringBuilder orderHql = new StringBuilder();
//				if(StringUtils.hasLength(validSort) || StringUtils.hasLength(evalSort) || StringUtils.hasLength(dealMoneySort)){
//					orderHql.append(" order by ");
//					if(StringUtils.hasLength(evalSort)) {
//						orderHql.append("s.evalSum ").append(evalSort).append(",");
//					}
//					if(StringUtils.hasLength(dealMoneySort)) {
//						orderHql.append("s.dealTotal ").append(dealMoneySort).append(",");
//					}
//					if(StringUtils.hasLength(validSort)) {
//						orderHql.append("s.orgInfo.validTime ").append(validSort);
//					}
//					
//					if(orderHql.toString().endsWith(",")) {
//						orderHql.delete(orderHql.length()-1, orderHql.length());
//					}
//				}else{//默认排序
//					orderHql.append(" order by s.dealTotal desc, s.orgInfo.validTime desc ,s.objId ").append(",");
//					if(orderHql.toString().endsWith(",")) {
//						orderHql.delete(orderHql.length()-1, orderHql.length());
//					}
//				}
//				
//				Query query = session.createQuery(preHql + hql + orderHql);
//				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
//		
//				List<Buyer> buyerList=query.list();
//				page.setData(buyerList);
//				
//				//查询总数
//				//preHql = "select count(s.objId) from Buyer s left join s.orgInfo as o where s.orgInfo.useStatus = '"+OrganizationEnum.USE_VALID+"' and s.orgInfo.isOff = '"+OrganizationEnum.ENABLE+"' and s.auditStatus = '" + OrganizationEnum.PASS_EXAM + "' ";
//				query = session.createQuery("select count(s.objId) "+preHql + hql);
//				page.setTotalRowNum((Long) query.list().get(0));
//				return page;
				
				String column = " select b.buyer_id," +
				   " o.org_info_id,"+
			       " o.org_name,"+
			       " o.org_code," +
			       " o.org_logo," +
			       " (select nvl(avg(e.summary_score),0) from ecp_pub_evaluate e where e.orginfo_id = o.org_info_id)," +
			       " o.bid_for_range," +
			       " o.district_value," +
			       " o.create_time," +
			       " o.valid_date," +
			       " o.desc_cn," +
			       " b.deal_total," +
			       " bi.industry_name";

				//连接表
				String table = " from buy_buyer b ,org_info o " +
						" left join BASE_INDUSTRY bi on o.belongindustry = bi.industry_id" +
						" where b.org_info_id = o.org_info_id" +
						" and o.use_status = '"+OrganizationEnum.USE_VALID+"' and o.is_off = '"+OrganizationEnum.ENABLE+"' and b.audit_status = '" + OrganizationEnum.PASS_EXAM + "' ";
				
				//选择条件
				StringBuilder condition = new StringBuilder();
				
				//所属区域过滤
				if(StringUtils.hasLength(districtId)){
					condition.append(" and instr(o.district_value,'").append(districtId).append("')>0 ");
				}
				
				//采购人类型
				if(StringUtils.hasLength(unitType)){
					condition.append(" and o.ent_prpt = '").append(unitType).append("' ");
				}
				
				//高级搜索条件
				if(StringUtils.hasLength((String)paramsMap.get("belongIndustry"))){
					condition.append(" and o.belongindustry = '").append((String)paramsMap.get("belongIndustry")).append("' ");
				}
				if(StringUtils.hasLength((String)paramsMap.get("dealTime"))){
					condition.append(" and ( 1 = 0 ");
					String[] dealTimes = ((String)paramsMap.get("dealTime")).split(",");
					for (String string : dealTimes) {
						int t = Integer.parseInt(string);
						condition.append(" or b.deal_time >= ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(DateUtil.addDay(new Date(), 0-t)))).append(" ");
					}
					condition.append(" )");
				}
				if(StringUtils.hasLength((String)paramsMap.get("moneyTotleLeft"))){
					condition.append(" and b.deal_total >= ").append((String)paramsMap.get("moneyTotleLeft"));
				}
				if(StringUtils.hasLength((String)paramsMap.get("moneyTotleRight"))){
					condition.append(" and b.deal_total <= ").append((String)paramsMap.get("moneyTotleRight"));
				}
				
				//关键字(主营产品,机构名称,简介 ,经营范围)
				if(StringUtils.hasLength(keyWord)){
					condition.append(" and (instr(o.bid_for_range ,'").append(keyWord).append("')>0");
					condition.append(" or instr(o.main_products , '").append(keyWord).append("')>0");
					condition.append(" or instr(o.desc_cn  ,'").append(keyWord).append("')>0");
					condition.append(" or instr(o.org_name , '").append(keyWord).append("')>0)");
				}
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				if(StringUtils.hasLength(validSort) || StringUtils.hasLength(evalSort) || StringUtils.hasLength(dealMoneySort)){
					orderHql.append(" order by ");
					if(StringUtils.hasLength(evalSort)) {
						orderHql.append(" (select nvl(avg(e.summary_score),0) from ECP_PUB_EVALUATE e where e.ORGINFO_ID = o.ORG_INFO_ID) ").append(evalSort).append(",");
					}
					if(StringUtils.hasLength(dealMoneySort)) {
						orderHql.append("b.deal_total ").append(dealMoneySort).append(",");
					}
					if(StringUtils.hasLength(validSort)) {
						orderHql.append("o.valid_date ").append(validSort);
					}
					
					if(orderHql.toString().endsWith(",")) {
						orderHql.delete(orderHql.length()-1, orderHql.length());
					}
				}else{//默认排序
					orderHql.append(" order by b.deal_total desc, o.valid_date desc ,b.buyer_id ").append(",");
					if(orderHql.toString().endsWith(",")) {
						orderHql.delete(orderHql.length()-1, orderHql.length());
					}
				}
				
				Query query = session.createSQLQuery(column + table + condition.toString() + orderHql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());

				List<Buyer> dataList = new ArrayList<Buyer>();
				//拼装对象
				for(Object[] objects : (List<Object[]>)query.list()){
					Buyer buyer = new Buyer();
					buyer.setObjId((String)objects[0]);
					buyer.setEvalSum((BigDecimal)objects[5]);
					OrgInfo orgInfo = new OrgInfo();
					orgInfo.setObjId((String)objects[1]);
					orgInfo.setOrgName((String)objects[2]);
					orgInfo.setOrgCode((String)objects[3]);
					orgInfo.setLogo((String)objects[4]);
					orgInfo.setBidForRange((String)objects[6]);
					orgInfo.setDistrictValue((String)objects[7]);
					orgInfo.setCreateTime((Date)objects[8]);
					orgInfo.setValidTime((Date)objects[9]);
					orgInfo.setDescCn((String)objects[10]);
					buyer.setDealTotal((BigDecimal)objects[11]);
					buyer.setOrgInfo(orgInfo);
					
					Industry industry = new Industry();
					industry.setName((String)objects[12]);
					orgInfo.setBelongIndustry(industry);
					dataList.add(buyer);
				}
				page.setData(dataList);
				
				//count
				query = session.createSQLQuery("select count(b.buyer_id) "+table + condition.toString());
				page.setTotalRowNum(((BigDecimal)query.uniqueResult()).longValue());
				return page;
		}});
	}
	
	/** 
	 * Description :  根据主键，获得采购人的详细信息，包括基本信息、扩展信息、评价等
	 * Create Date: 2010-10-19下午05:22:33 by liangxj  Modified Date: 2010-10-19下午05:22:33 by liangxj
	 * @param 
	 * @return  
	 * @Exception   
	 */
	public Buyer getBuyerAllInfo(final String objId) throws Exception {
		List<Buyer> list = getBuyerAllInfoList(objId);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	
	/** 
	 * Description :  根据主键，获得采购人的详细信息，包括基本信息、扩展信息、评价等
	 * Create Date: 2010-10-19下午05:22:33 by liangxj  Modified Date: 2010-10-19下午05:22:33 by liangxj
	 * @param objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Buyer> getBuyerAllInfoList(final String objIds) throws Exception {
		return (List<Buyer>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				//查询采购人信息
				String hql = "from Buyer as s " +
								"left join fetch s.orgInfo as o left join fetch o.company " +
								"left join fetch o.evaluates as e " +
								"where s.objId in (:objId) ";
				
				Query query = session.createQuery(hql);
				query.setParameterList("objId", objIds.split(","));
				
				Set<Buyer> buyerSet = new HashSet<Buyer>(query.list());
				List<Buyer> buyerList = new ArrayList<Buyer>();
				buyerList.addAll(buyerSet);
				return buyerList;
		}});
	}
	
	/** 
	 * Description :  根据企业性质获得包含采购人数量的区域信息
	 * Create Date: 2010-10-19下午08:19:10 by liangxj  Modified Date: 2010-10-19下午08:19:10 by liangxj
	 * @param   districtId 区域id unitType 企业类型 districtLevel 区域级别
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getDistrictListForShow(final String districtId,final String unitType,final Short districtLevel,final String keyWord) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = 
//					new StringBuilder("select a.district_id,a.district_name ,b.buyer_count from " +
//											"base_district a, " +
//											"(select m.district_id,count(n.buyer_id) buyer_count " +
//														"from base_district m, " +
//														"(select t.buyer_id,s.district_value from buy_buyer t " +
//															"join org_info s on s.org_info_id = t.org_info_id " +
//																"where s.ENT_PRPT like :unitType " +
//																"and s.use_status =:useStatus and is_off =:isOff and t.AUDIT_STATUS =:auditStatus " +
//																"and (s.BID_FOR_RANGE like :keyWord or s.MAIN_PRODUCTS like :keyWord or s.DESC_CN like :keyWord or s.ORG_NAME like :keyWord ) " +
//																") n " +
//														"where n.district_value like '%' || m.district_id || ',%' or n.district_value like '%' || m.district_id || '#%' " +
//														"group by m.district_id ) b " +
//											"where a.district_id = b.district_id ");
//				
//				if(StringUtils.hasLength(districtId)) {
//					sql.append(" and a.district_parent_id in (:districtId) ");
//				}
//				if(districtLevel != 0) {
//					sql.append(" and a.dis_level =:districtLeval ");
//				}
//				
//				sql.append(" order by a.district_name");
					
				new StringBuilder("select m.district_id, m.district_name ,count(n.buyer_id) buyer_count")
				.append(" from base_district m,")
				.append(" (select t.buyer_id, s.district_value")
				.append("  from buy_buyer t")
				.append(" join org_info s on s.org_info_id = t.org_info_id")
				.append(" where "+(StringUtils.hasLength(unitType)?("s.ENT_PRPT like '%"+unitType+"%'"):"1=1"))
				.append("  and s.use_status = '"+OrganizationEnum.USE_VALID+"'")
				.append(" and is_off = '"+OrganizationEnum.ENABLE+"'")
				.append(" and t.AUDIT_STATUS = '"+OrganizationEnum.PASS_EXAM+"'")
				.append((StringUtils.hasLength(keyWord)?(" and (instr(s.BID_FOR_RANGE, '"+keyWord+"') > 0 or instr(s.MAIN_PRODUCTS, '"+keyWord+"') > 0 or instr(s.DESC_CN, '"+keyWord+"') > 0 or instr(s.ORG_NAME, '"+keyWord+"') > 0)"):"")+") n")
				.append(" where (instr(n.district_value, m.district_id || ',') > 0 or")
				.append(" instr(n.district_value, m.district_id || '#') > 0)")
				.append(districtLevel != 0?" and m.dis_level = :districtLeval ":"")
				.append(StringUtils.hasLength(districtId)?" and m.district_parent_id in (:districtId) ":"")
				
				.append(" group by m.district_id, m.district_name")
				.append(" order by m.district_name");
				
				Query query = session.createSQLQuery(sql.toString());
				
				if(StringUtils.hasLength(districtId)) {
					query.setParameterList("districtId", districtId.split(","));
				}
				if(districtLevel != 0) {
					query.setShort("districtLeval", districtLevel);
				}
				
				List<String[]> gcList=query.list();
				
				return gcList;
			}});
	}
	
	/** 
	 * Description :  获得采购人企业类型的展示数据 
	 * Create Date: 2010-10-19下午08:17:07 by liangxj  Modified Date: 2010-10-19下午08:17:07 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getUnitTypeListShow(final String keyWord) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
//				String hql = "select o.entPrpt ,0, count(a.objId) from Buyer a " +
//						"left join a.orgInfo o " +
//						"where o.useStatus =:useStatus and o.isOff =:isOff and a.auditStatus =:auditStatus " +
//						"and (o.bidForRange like :keyWord or o.mainProducts like :keyWord or o.descCn like :keyWord or o.orgName like :keyWord ) " +
//						"group by o.entPrpt ";
//				
//				Query query = session.createQuery(hql);
//				
//				query.setString("useStatus", OrganizationEnum.USE_VALID);
//				query.setString("isOff", OrganizationEnum.ENABLE);
//				query.setString("auditStatus", OrganizationEnum.PASS_EXAM);
//				query.setString("keyWord", "%"+(StringUtils.hasLength(keyWord) ? keyWord : "")+"%");
				
				String sql = "  select o.ent_prpt ,0,count(b.buyer_id) from buy_buyer b ,org_info o"+
				" where b.org_info_id = o.org_info_id"+
				" and o.use_status = '"+OrganizationEnum.USE_VALID+"' and o.is_off = '"+OrganizationEnum.ENABLE+"' and b.audit_status = '"+OrganizationEnum.PASS_EXAM+"'"+
				(StringUtils.hasLength(keyWord)?(" and (instr(o.bid_for_range ,'"+keyWord+"')>0 or  instr(o.main_products,'"+keyWord+"')>0 or instr(o.org_name,'"+keyWord+"')>0)"):"")+
				" group by o.ent_prpt";
				Query query = session.createSQLQuery(sql);
				
				return query.list();
		}});
	}
}
