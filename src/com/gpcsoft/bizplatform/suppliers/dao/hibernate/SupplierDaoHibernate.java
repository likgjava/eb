package com.gpcsoft.bizplatform.suppliers.dao.hibernate;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.suppliers.dao.SupplierDao;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class SupplierDaoHibernate extends BaseGenericDaoHibernate<Supplier> implements SupplierDao {

	/** 
	 * Description :  更新供应商的审核状态
	 * Create Date: 2010-7-28下午02:29:47 by sunl  Modified Date: 2010-7-28下午02:29:47 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer updateSupplierStatus(final String supplierId,final OrgInfo orgInfo) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append(" update Supplier sup set sup.auditStatus=:auditStatus,sup.verifyUser.objId=:verifyUserId,sup.verifyTime=:verifyTime,sup.opinion=:opinion where sup.objId=:objId");
				Query query = session.createQuery(hql.toString());
				query.setString("auditStatus", orgInfo.getAuditStatus());
				query.setString("objId", supplierId);
				
				query.setString("verifyUserId", AuthenticationHelper.getCurrentUser(true).getObjId());
				query.setDate("verifyTime", new Date());
				query.setString("opinion", orgInfo.getOpinion());
				
				return query.executeUpdate();
			}
		});
	}
	
	/** 
	 * Description :  根据主键，获得供应商的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param 
	 * @return  
	 * @Exception   
	 */
	public Supplier getSupplierAllInfo(final String objId) throws Exception {
		List<Supplier> list = getSupplierAllInfoList(objId);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	
	/** 
	 * Description :  根据主键，获得供应商的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Supplier> getSupplierAllInfoList(final String objIds) throws Exception {
		return (List<Supplier>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				//查询供应商信息
				String hql = "from Supplier as s " +
								"left join fetch s.orgInfo as o left join fetch o.company " +
								"left join fetch o.qualitys as q " +
									"left join fetch q.qualificationClass as qc " +
									"left join fetch q.qualificationDefine as qd " +
									"left join fetch q.qualificationDetailSet as qds left join fetch qds.qualityParam " +
								"left join fetch o.successCases as c " +
								"left join fetch o.evaluates as e " +
								"where s.objId in (:objId) or s.orgInfo.objId in (:objId) "; // by yucy
				
				Query query = session.createQuery(hql);
				query.setParameterList("objId", objIds.split(","));
				
				Set<Supplier> supplierSet = new HashSet<Supplier>(query.list());
				List<Supplier> supplierList = new ArrayList<Supplier>();
				supplierList.addAll(supplierSet);
				return supplierList;
		}});
	}
	
	/** 
	 * Description :  根据参数获得供应商的展示信息列表，及时加载OrgInfor信息
	 * Create Date: 2010-8-9下午01:44:06 by liangxj  Modified Date: 2010-8-9下午01:44:06 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含品目信息、区域信息、排序信息
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Supplier> getSupplierListForShow(final Page<Supplier> page,final Map<String, Object> paramsMap) throws Exception  {
		return (Page<Supplier>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String categoryCode = (String) paramsMap.get("categoryCode"); //采购品目Code
				String districtId = (String) paramsMap.get("districtId"); //区域id
				String validSort = (String) paramsMap.get("validSort"); //生效时间排序
				String evalSort = (String) paramsMap.get("evalSort"); //评价排序
				String keyWord= (String) paramsMap.get("keyWord"); //关键字
				
//				//查询供应商信息
//				String preHql = "from Supplier s ,OrgInfo o " +
//									"where s.orgInfo.objId = o.objId and  s.auditStatus='"+OrganizationEnum.PASS_EXAM+"' and o.useStatus = '"+OrganizationEnum.USE_VALID+"' and o.isOff = '"+OrganizationEnum.ENABLE+"' ";
//				
//				StringBuilder hql = new StringBuilder();
//				
//				//品目过滤
//				if(StringUtils.hasLength(categoryCode)){
//					String categoryCodeHql = "";
//					for(String categoryCodeCell: categoryCode.split(",")){
//						categoryCodeHql += StringUtils.hasLength(categoryCodeHql)?" or ":"";
//						categoryCodeHql+= " o.bidForRange like '%"+categoryCodeCell+"%'";
//					}
//					hql.append(" and (").append(categoryCodeHql).append(")");
//				}
//				
//				//区域过滤
//				if(StringUtils.hasLength(districtId)){
//					hql.append(" and o.districtValue like '%").append(districtId).append("%' ");
//				}
//				
//				//高级搜索条件
//				StringBuilder highparam = new StringBuilder();
//				if(StringUtils.hasLength((String)paramsMap.get("isManufacturerC")) || StringUtils.hasLength((String)paramsMap.get("isManufacturerJ"))){
//					highparam.append(" and ( 1=0 ");
//					if(StringUtils.hasLength((String)paramsMap.get("isManufacturerC"))) {
//						highparam.append(" or o.isManufacturer like '%").append((String)paramsMap.get("isManufacturerC")).append("%'");
//					}
//					//供应商,isManufacturer为空，厂商为1，经销商为0
//					if(StringUtils.hasLength((String)paramsMap.get("isManufacturerJ"))) {
//						highparam.append(" or o.isManufacturer = '").append((String)paramsMap.get("isManufacturerJ")).append("'");
//					}
//					highparam.append(" )");
//				}
//				if(StringUtils.hasLength((String)paramsMap.get("belongIndustry"))){
//					highparam.append(" and o.belongIndustry = '").append((String)paramsMap.get("belongIndustry")).append("'");
//				}
//				if(StringUtils.hasLength((String)paramsMap.get("unitScape"))){
//					highparam.append(" and o.unitScape = '").append((String)paramsMap.get("unitScape")).append("'");
//				}
//				if(StringUtils.hasLength((String)paramsMap.get("entPrpt"))){
//					highparam.append(" and o.entPrpt = '").append((String)paramsMap.get("entPrpt")).append("'");
//				}
//				
//				//关键字(主营产品,机构名称,简介 ,经营范围)
//				if(StringUtils.hasLength(keyWord)){
//					highparam.append(" and (o.bidForRange like '%").append(keyWord).append("%'");
//					highparam.append(" or o.mainProducts like '%").append(keyWord).append("%'");
//					highparam.append(" or o.descCn like '%").append(keyWord).append("%'");
//					highparam.append(" or o.orgName like '%").append(keyWord).append("%')");
//				}
//				
//				hql.append(highparam.toString());
//				
//				//排序条件
//				StringBuilder orderHql = new StringBuilder();
//				if(StringUtils.hasLength(validSort) || StringUtils.hasLength(evalSort)){
//					orderHql.append("order by ");
//					if(StringUtils.hasLength(evalSort)) {
//						orderHql.append("s.evalSum ").append(evalSort).append(",");
//					}
//					if(StringUtils.hasLength(validSort)) {
//						orderHql.append("o.validTime ").append(validSort);
//					}
//					
//					if(orderHql.toString().endsWith(",")) {
//						orderHql.delete(orderHql.length()-1, orderHql.length());
//					}
//				} else {
//					orderHql.append("order by o.createTime desc "); //因Supplier中的createTime好多为空，改为OrgInfo中的createTime
//				}
//				
//				
//				Query query;
//				
//				//查询总数
//				String countHql = "select count(s.objId) from Supplier s ,OrgInfo o where s.orgInfo.objId = o.objId and s.auditStatus='"+OrganizationEnum.PASS_EXAM+"' and o.useStatus = '"+OrganizationEnum.USE_VALID+"' and o.isOff = '"+OrganizationEnum.ENABLE+"' ";
//				query = session.createQuery(countHql + hql);
//				Long totalRowNum = (Long) query.list().get(0);
//				page.setTotalRowNum(totalRowNum);
//				if(page.getStart()>totalRowNum){
//					page.setStart(0);//如果记录数小于当前页面参数时 设回第一页
//				}
//				
//				//查询记录数
//				query = session.createQuery("select s " +preHql + hql + orderHql);
//				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
//		
//				List<Supplier> supplierList=query.list();
//				page.setData(supplierList);
//				
//				return page;
				
				String column = " select o.supplier_id," +
								   " o.org_info_id,"+
							       " o.org_name,"+
							       " o.org_code," +
							       " o.org_logo," +
							       " (select nvl(avg(e.summary_score),0) from ecp_pub_evaluate e where e.orginfo_id = o.org_info_id)," +
							       " o.bid_for_range," +
							       " o.district_value," +
							       " o.create_time," +
							       " s.desc_cn";
				
				//连接表
				String table = " from spl_supplier s, org_info o where s.org_info_id = o.org_info_id and s.audit_status='"+OrganizationEnum.PASS_EXAM+"' and o.use_status = '"+OrganizationEnum.USE_VALID+"' and o.is_off = '"+OrganizationEnum.ENABLE+"'";
				
				//选择条件
				StringBuilder condition = new StringBuilder();
				
				//品目过滤
				if(StringUtils.hasLength(categoryCode)){
					String categoryCodeHql = "";
					for(String categoryCodeCell: categoryCode.split(",")){
						categoryCodeHql += StringUtils.hasLength(categoryCodeHql)?" or ":"";
						categoryCodeHql+= " instr(o.bid_for_range, '"+categoryCodeCell+"')>0";
					}
					condition.append(" and (").append(categoryCodeHql).append(")");
				}
				
				//区域过滤
				if(StringUtils.hasLength(districtId)){
					condition.append(" and instr(o.district_value, '").append(districtId).append("')>0 ");
				}
				
				//高级搜索条件
				if(StringUtils.hasLength((String)paramsMap.get("isManufacturerC")) || StringUtils.hasLength((String)paramsMap.get("isManufacturerJ"))){
					condition.append(" and ( 1=0 ");
					if(StringUtils.hasLength((String)paramsMap.get("isManufacturerC"))) {
						condition.append(" or o.ismanufacturer = '").append((String)paramsMap.get("isManufacturerC")).append("'");
					}
					//供应商,isManufacturer为空，厂商为1，经销商为0
					if(StringUtils.hasLength((String)paramsMap.get("isManufacturerJ"))) {
						condition.append(" or o.ismanufacturer = '").append((String)paramsMap.get("isManufacturerJ")).append("'");
					}
					condition.append(" )");
				}
				
				if(StringUtils.hasLength((String)paramsMap.get("belongIndustry"))){
					condition.append(" and o.belongindustry = '").append((String)paramsMap.get("belongIndustry")).append("'");
				}
				if(StringUtils.hasLength((String)paramsMap.get("unitScape"))){
					condition.append(" and o.unit_scape = '").append((String)paramsMap.get("unitScape")).append("'");
				}
				if(StringUtils.hasLength((String)paramsMap.get("entPrpt"))){
					condition.append(" and o.ent_prpt = '").append((String)paramsMap.get("entPrpt")).append("'");
				}
				
				//关键字(主营产品,机构名称,简介 ,经营范围)
				if(StringUtils.hasLength(keyWord)){
					condition.append(" and ( instr(o.bid_for_range ,'").append(keyWord).append("')>0");
					condition.append(" or instr(o.main_products ,'").append(keyWord).append("')>0");
					condition.append(" or instr(o.desc_cn ,'").append(keyWord).append("')>0");
					condition.append(" or instr(o.org_name,'").append(keyWord).append("')>0)");
				}
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				if(StringUtils.hasLength(validSort) || StringUtils.hasLength(evalSort)){
					orderHql.append(" order by ");
					if(StringUtils.hasLength(evalSort)) {
						orderHql.append(" (select nvl(avg(e.summary_score),0) from ECP_PUB_EVALUATE e where e.ORGINFO_ID = o.ORG_INFO_ID) ").append(evalSort).append(",");
					}
					if(StringUtils.hasLength(validSort)) {
						orderHql.append(" o.valid_date ").append(validSort);
					}
					if(orderHql.toString().endsWith(",")) {
						orderHql.delete(orderHql.length()-1, orderHql.length());
					}
				} else {
					orderHql.append(" order by o.create_time desc "); //因Supplier中的createTime好多为空，改为OrgInfo中的createTime
				}
				
				Query query = session.createSQLQuery(column + table + condition.toString() + orderHql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());

				List<Supplier> dataList = new ArrayList<Supplier>();
				//拼装对象
				for(Object[] objects : (List<Object[]>)query.list()){
					Supplier supplier = new Supplier();
					supplier.setObjId((String)objects[0]);
					supplier.setEvalSum((BigDecimal)objects[5]);
					OrgInfo orgInfo = new OrgInfo();
					orgInfo.setObjId((String)objects[1]);
					orgInfo.setOrgName((String)objects[2]);
					orgInfo.setOrgCode((String)objects[3]);
					orgInfo.setLogo((String)objects[4]);
					orgInfo.setBidForRange((String)objects[6]);
					orgInfo.setDistrictValue((String)objects[7]);
					orgInfo.setCreateTime((Date)objects[8]);
					orgInfo.setDescCn((String)objects[9]);
					supplier.setOrgInfo(orgInfo);
					dataList.add(supplier);
				}
				page.setData(dataList);
				
				//count
				query = session.createSQLQuery("select count(s.supplier_id) "+table + condition.toString());
				page.setTotalRowNum(((BigDecimal)query.uniqueResult()).longValue());
				
				return page;
		}});
	}
	
	/** 
	 * Description :  根据品目获得下级品目的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号,isLeaf 是否是叶子节点
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getCategoryListShowByCategory(final String categoryId,final String categoryCode,final Boolean isLeaf,final String keyWord) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				
				//获取品目信息和供应商数量
				StringBuilder sql = new StringBuilder();
				sql.append("select p.id,p.category_code,p.category_name,count(s.supplier_id) ");
				sql.append(" from org_info o ");
				sql.append(" join spl_supplier s on o.org_info_id = s.org_info_id ");
				sql.append(" and o.use_status = :useStatus and o.is_off = :isOff and s.audit_status = :auditStatus ");
				//关键字过滤
				if(StringUtils.hasLength(keyWord)) {
					//sql.append(" and (o.bid_for_range like :keyWord or o.ORG_NAME like :keyWord or o.MAIN_PRODUCTS like :keyWord) ");
					sql.append(" and ( ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().inStr("o.ORG_NAME", keyWord, 1)).append(" > 0 ");
					sql.append(" or ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().inStr("o.bid_for_range", keyWord, 1)).append(" > 0 ");
					sql.append(" or ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().inStr("o.MAIN_PRODUCTS", keyWord, 1)).append(" > 0 )");
				}
				sql.append(" join purcatalog_category p on instr(o.bid_for_range, p.id)>0 ");
				sql.append(" and length(p.id) = 5 where 1=1 ");
				//品目id过滤
				if(StringUtils.hasLength(categoryId)) {
					//sql.append(" and p.id like '%").append(categoryId).append("%' ");
					sql.append(" and ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().inStr("p.id", categoryId, 1)).append(" > 0 ");
				}
				//品目编号过滤
				if(StringUtils.hasLength(categoryCode)) {
					//sql.append(" and p.category_code like '%").append(categoryCode).append("%' ");
					sql.append(" and ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().inStr("p.category_code", categoryCode, 1)).append(" > 0 ");
				}
				sql.append(" group by p.id, p.category_code, p.category_name");
				sql.append(" order by p.id");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("useStatus", OrganizationEnum.USE_VALID);
				query.setString("isOff", OrganizationEnum.ENABLE);
				query.setString("auditStatus", OrganizationEnum.PASS_EXAM);
				return query.list();
			}
		});
	}
	
	/** 
	 * Description :  根据品目获得包含供应商数量的区域信息
	 * Create Date: 2010-8-9下午03:25:57 by liangxj  Modified Date: 2010-8-9下午03:25:57 by liangxj
	 * @param   districtLevel 0表示不过虑层级
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getDistrictListForShow(final String districtId,final String categoryCode,final Short districtLevel,final String keyWord) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql =
					
//					new StringBuilder("select a.district_id,a.district_name ,b.supplier_count from " +
//											"base_district a, " +
//											"(select m.district_id,count(n.supplier_id) supplier_count " +
//														"from base_district m, " +
//														"(select t.supplier_id,s.district_value from spl_supplier t " +
//															"join org_info s on s.org_info_id = t.org_info_id " +
//																"where s.bid_for_range like :categoryCode " +
//																"and s.use_status =:useStatus and is_off =:isOff and t.audit_status =:auditStatus " +
//																(StringUtils.hasLength(keyWord)?"and (s.BID_FOR_RANGE like :keyWord or s.MAIN_PRODUCTS like :keyWord or s.DESC_CN like :keyWord or s.ORG_NAME like :keyWord ) ":"") +
//																") n " +
//																//n.district_value like '%' || m.district_id || ',%' or n.district_value like '%' || m.district_id || '#%' "
//														"where instr(n.district_value,m.district_id||',')>0  or instr(n.district_value,m.district_id||'#')>0 " + //by yucy
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
					
				new StringBuilder("select m.district_id, m.district_name ,count(n.supplier_id) supplier_count ")
				.append(" from base_district m,")
				.append(" (select t.supplier_id, s.district_value")
				.append(" from spl_supplier t")
				.append(" join org_info s on s.org_info_id = t.org_info_id")
				.append(" where "+(StringUtils.hasLength(categoryCode)?("instr(s.bid_for_range, '"+categoryCode+"') > 0"):"1=1"))
				.append(" and s.use_status = '"+ OrganizationEnum.USE_VALID+"'")
				.append(" and is_off = '"+OrganizationEnum.ENABLE+"'")
				.append(" and t.audit_status = '"+ OrganizationEnum.PASS_EXAM+"'")
				.append(" "+(StringUtils.hasLength(keyWord)?("and (instr(s.BID_FOR_RANGE, '"+keyWord+"') > 0 or instr(s.MAIN_PRODUCTS, '"+keyWord+"') > 0 or instr(s.DESC_CN, '"+keyWord+"') > 0 or instr(s.ORG_NAME, '"+keyWord+"') > 0)" ):"")+") n")
				.append(" where (instr(n.district_value, m.district_id || ',') > 0")
				.append(" or instr(n.district_value, m.district_id || '#') > 0 )")
				.append(districtLevel != 0?(" and dis_level = "+districtLevel):"")
				.append(StringUtils.hasLength(districtId)?" and a.district_parent_id in (:districtId) ":"")
				.append(" group by m.district_id,district_name,dis_level order by  district_name");
					
				Query query = session.createSQLQuery(sql.toString());
				
				if(StringUtils.hasLength(districtId)) {
					query.setParameterList("districtId", districtId.split(","));
				}
				List<String[]> gcList=query.list();
				
				return gcList;
			}});
	}
	
	/** 
	 * Description :  根据orgInfoId获取供应商信息
	 * Create Date: 2010-8-17下午01:55:35 by sunl  Modified Date: 2010-8-17下午01:55:35 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Supplier getSupplierByOrgInfoId(final String orgInfoId) throws Exception {
		return (Supplier) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("from Supplier s where s.orgInfo.objId =:orgInfoId");
				Query query = session.createQuery(hql.toString());
				query.setString("orgInfoId", orgInfoId);
				
				return query.list().size()==0?new Supplier():(Supplier)query.list().get(0);
			}
		});
	}
}
