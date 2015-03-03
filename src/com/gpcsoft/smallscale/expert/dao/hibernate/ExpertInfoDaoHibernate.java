package com.gpcsoft.smallscale.expert.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.expert.dao.ExpertInfoDao;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.smallscale.expert.enumeration.ExpertEnum;
import com.gpcsoft.srplatform.baseData.domain.District;

@Repository
public class ExpertInfoDaoHibernate extends BaseGenericDaoHibernate<ExpertInfo> implements ExpertInfoDao {
	
	/** 
	 * Description :  根据参数获得专家的展示信息列表，及时加载User和employee信息
	 * Create Date: 2010-12-1下午12:09:08 by liangxj  Modified Date: 2010-12-1下午12:09:08 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含行业信息、区域信息、排序信息
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<ExpertInfo> getExpertListForShow(final Page<ExpertInfo> page,final Map<String, Object> paramsMap) throws Exception {
		return (Page<ExpertInfo>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String categoryId = (String) paramsMap.get("categoryId"); //品目id
				String districtId = (String) paramsMap.get("districtId"); //区域id
				String validSort = (String) paramsMap.get("validSort"); //生效时间排序
				String modifySort = (String) paramsMap.get("modifySort"); //修改时间排序
				String specifySort = (String) paramsMap.get("specifySort"); //从事特长时间排序
				String keyWord= (String) paramsMap.get("keyWord"); //关键字
				
//				//查询专家信息
//				String preHql = "from ExpertInfo as s left join fetch s.user as o left join fetch o.emp e " +
//									"where s.auditStatus='"+ExpertEnum.PASS_EXAM+"' and s.useStatus = '"+ExpertEnum.USE_VALID+"' and s.isOff = '"+ExpertEnum.ENABLE+"' ";
//				
//				StringBuilder hql = new StringBuilder();
//				
//				//评审品目过滤
//				if(StringUtils.hasLength(categoryId)){
//					hql.append(" and s.appCategoryValue like '%").append(categoryId).append("%' ");
//				}
//				
//				//评审区域过滤
//				if(StringUtils.hasLength(districtId)){
//					hql.append(" and s.appDistrictValue like '%").append(districtId).append("%' ");
//				}
//				
//				//高级搜索条件
//				StringBuilder highparam = new StringBuilder();
//				if(StringUtils.hasLength((String)paramsMap.get("isRetiredC")) || StringUtils.hasLength((String)paramsMap.get("isRetiredJ"))){
//					highparam.append(" and ( 1=0 ");//是否退休
//					if(StringUtils.hasLength((String)paramsMap.get("isRetiredC"))) {
//						highparam.append(" or s.isRetired = '").append((String)paramsMap.get("isRetiredC")).append("'");
//					}
//					if(StringUtils.hasLength((String)paramsMap.get("isRetiredJ"))) {
//						highparam.append(" or s.isRetired = '").append((String)paramsMap.get("isRetiredJ")).append("'");
//					}
//					highparam.append(" )");
//				}
//				if(StringUtils.hasLength((String)paramsMap.get("isConsultant")) && !StringUtils.hasLength((String)paramsMap.get("isReviewers"))){ //是咨询专家
//					highparam.append(" and s.isConsultant = '").append((String)paramsMap.get("isConsultant")).append("'");
//				}else if(StringUtils.hasLength((String)paramsMap.get("isReviewers")) && !StringUtils.hasLength((String)paramsMap.get("isConsultant"))){ //是评审专家
//					highparam.append(" and s.isReviewers = '").append((String)paramsMap.get("isReviewers")).append("'");
//				}else if(StringUtils.hasLength((String)paramsMap.get("isConsultant")) && StringUtils.hasLength((String)paramsMap.get("isReviewers"))){ //是咨询专家或是评审专家
//					highparam.append(" and (s.isConsultant = '").append((String)paramsMap.get("isConsultant")).append("'");
//					highparam.append(" or s.isReviewers = '").append((String)paramsMap.get("isReviewers")).append("')");
//				}
//				if(StringUtils.hasLength((String)paramsMap.get("sspecifyYear"))){ //从事特长年限
//					highparam.append(" and s.specifyYear >= ").append((String)paramsMap.get("sspecifyYear"));
//				}
//				if(StringUtils.hasLength((String)paramsMap.get("especifyYear"))){
//					highparam.append(" and s.specifyYear <= ").append((String)paramsMap.get("especifyYear"));
//				}
//				if(StringUtils.hasLength((String)paramsMap.get("professionQualificationLevel"))){ //资质级别
//					highparam.append(" and s.professionQualificationLevel = '").append((String)paramsMap.get("professionQualificationLevel")).append("'");
//				}
//				if(StringUtils.hasLength((String)paramsMap.get("belongIndustry"))){ //行业
//					highparam.append(" and s.belongIndustry.objId = '").append((String)paramsMap.get("belongIndustry")).append("'");
//				}
//				
//				//关键字(姓名,特长描述,经验描述 )
//				if(StringUtils.hasLength(keyWord)){
//					highparam.append(" and (s.tenderExperience like '%").append(keyWord).append("%'");
//					highparam.append(" or s.technicalExcellence like '%").append(keyWord).append("%'");
//					highparam.append(" or o.usName like '%").append(keyWord).append("%'");
//					highparam.append(" or e.name like '%").append(keyWord).append("%')");
//				}
//				
//				hql.append(highparam.toString());
//				
//				//排序条件
//				StringBuilder orderHql = new StringBuilder();
//				if(StringUtils.hasLength(validSort) || StringUtils.hasLength(specifySort) || StringUtils.hasLength(modifySort)){
//					orderHql.append("order by ");
//					if(StringUtils.hasLength(specifySort)) {
//						orderHql.append("s.specifyYear ").append(specifySort).append(",");
//					}
//					if(StringUtils.hasLength(validSort)) {
//						orderHql.append("s.validTime ").append(validSort).append(",");
//					}
//					if(StringUtils.hasLength(modifySort)) {
//						orderHql.append("s.updateTime ").append(modifySort);
//					}
//					
//					if(orderHql.toString().endsWith(",")) {
//						orderHql.delete(orderHql.length()-1, orderHql.length());
//					}
//				}
//				
//				Query query = session.createQuery(preHql + hql + orderHql);
//				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
//		
//				List<Supplier> supplierList=query.list();
//				page.setData(supplierList);
//				
//				//查询总数
//				preHql = "select count(s.objId) from ExpertInfo as s left join s.user as o left join o.emp e where s.auditStatus='"+ExpertEnum.PASS_EXAM+"' and s.useStatus = '"+ExpertEnum.USE_VALID+"' and s.isOff = '"+ExpertEnum.ENABLE+"' ";
//				query = session.createQuery(preHql + hql);
//				page.setTotalRowNum((Long) query.list().get(0));
//				return page;
				
				String column = " select ex.expert_id ," +
						"ex.name ," +
						"ex.is_consultant," +
						"ex.is_reviewers," +
						"ex.photo_id ," +
						"ex.specify_year," +
						" substr(ex.political_landscape,0,2) ," +
						" substr(ex.profession_qualification_level,0,2)," +
						"ex.technical_excellence," +
						"d.district_name," +
						"ind.industry_name," +
						"ex.tender_experience";
		
				//连接表
				String table = " from expert_info ex " +
						" left join base_district d on ex.district_id = d.district_id" +
						" left join auth_user u on ex.user_id  = u.usr_id " +
						" left join auth_org_employee e on u.emp_id = e.emp_id " +
						" left join base_industry ind on ex.belong_industry = ind.industry_id " +
						" where ex.audit_status='"+ExpertEnum.PASS_EXAM+"' and ex.use_status = '"+ExpertEnum.USE_VALID+"' and ex.is_off = '"+ExpertEnum.ENABLE+"' ";

				//选择条件
				StringBuilder condition = new StringBuilder();
				
				//评审品目过滤
				if(StringUtils.hasLength(categoryId)){
					condition.append(" and instr(ex.app_category_value, '").append(categoryId).append("')>0 ");
				}
				
				//评审区域过滤
				if(StringUtils.hasLength(districtId)){
					condition.append(" and instr(ex.app_district_value, '").append(districtId).append("')>0 ");
				}
				
				//高级搜索条件
				if(StringUtils.hasLength((String)paramsMap.get("isRetiredC")) || StringUtils.hasLength((String)paramsMap.get("isRetiredJ"))){
					condition.append(" and ( 1=0 ");//是否退休
					if(StringUtils.hasLength((String)paramsMap.get("isRetiredC"))) {
						condition.append(" or ex.is_retired = '").append((String)paramsMap.get("isRetiredC")).append("'");
					}
					if(StringUtils.hasLength((String)paramsMap.get("isRetiredJ"))) {
						condition.append(" or ex.is_retired = '").append((String)paramsMap.get("isRetiredJ")).append("'");
					}
					condition.append(" )");
				}
				if(StringUtils.hasLength((String)paramsMap.get("isConsultant")) && !StringUtils.hasLength((String)paramsMap.get("isReviewers"))){ //是咨询专家
					condition.append(" and ex.is_consultant = '").append((String)paramsMap.get("isConsultant")).append("'");
				}else if(StringUtils.hasLength((String)paramsMap.get("isReviewers")) && !StringUtils.hasLength((String)paramsMap.get("isConsultant"))){ //是评审专家
					condition.append(" and ex.is_reviewers = '").append((String)paramsMap.get("isReviewers")).append("'");
				}else if(StringUtils.hasLength((String)paramsMap.get("isConsultant")) && StringUtils.hasLength((String)paramsMap.get("isReviewers"))){ //是咨询专家或是评审专家
					condition.append(" and (ex.is_consultant = '").append((String)paramsMap.get("isConsultant")).append("'");
					condition.append(" or ex.is_reviewers = '").append((String)paramsMap.get("isReviewers")).append("')");
				}
				if(StringUtils.hasLength((String)paramsMap.get("sspecifyYear"))){ //从事特长年限
					condition.append(" and ex.specify_year >= ").append((String)paramsMap.get("sspecifyYear"));
				}
				if(StringUtils.hasLength((String)paramsMap.get("especifyYear"))){
					condition.append(" and ex.specify_year <= ").append((String)paramsMap.get("especifyYear"));
				}
				if(StringUtils.hasLength((String)paramsMap.get("professionQualificationLevel"))){ //资质级别
					condition.append(" and ex.profession_qualification_level = '").append((String)paramsMap.get("professionQualificationLevel")).append("'");
				}
				if(StringUtils.hasLength((String)paramsMap.get("belongIndustry"))){ //行业
					condition.append(" and ex.belong_industry = '").append((String)paramsMap.get("belongIndustry")).append("'");
				}
				
				//关键字(姓名,特长描述,经验描述 )
				if(StringUtils.hasLength(keyWord)){
					condition.append(" and ( instr(ex.TENDER_EXPERIENCE ,'").append(keyWord).append("')>0");
					condition.append(" or instr(ex.TECHNICAL_EXCELLENCE ,'").append(keyWord).append("')>0");
					condition.append(" or instr(u.USR_NAME , '").append(keyWord).append("')>0");
					condition.append(" or instr(e.EMP_NAME , '").append(keyWord).append("')>0 )");
				}
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				if(StringUtils.hasLength(validSort) || StringUtils.hasLength(specifySort) || StringUtils.hasLength(modifySort)){
					orderHql.append("order by ");
					if(StringUtils.hasLength(specifySort)) {
						orderHql.append("ex.specify_year ").append(specifySort).append(",");
					}
					if(StringUtils.hasLength(validSort)) {
						orderHql.append("ex.valid_date ").append(validSort).append(",");
					}
					if(StringUtils.hasLength(modifySort)) {
						orderHql.append("ex.modify_time ").append(modifySort);
					}
					if(orderHql.toString().endsWith(",")) {
						orderHql.delete(orderHql.length()-1, orderHql.length());
					}
				}
				
				Query query = session.createSQLQuery(column + table + condition.toString() + orderHql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<ExpertInfo> dataList = new ArrayList<ExpertInfo>();
				
				//拼装对象
				for(Object[] objects : (List<Object[]>)query.list()){
					ExpertInfo expertInfo = new ExpertInfo();
					expertInfo.setObjId((String)objects[0]);
					expertInfo.setName((String)objects[1]);
					expertInfo.setIsConsultant(""+objects[2]);
					expertInfo.setIsReviewers(""+objects[3]);
					expertInfo.setPhoto((String)objects[4]);
					expertInfo.setSpecifyYear((BigDecimal)objects[5]);
					expertInfo.setPoliticalLandscape((String)objects[6]);
					expertInfo.setProfessionQualificationLevel((String)objects[7]);
					expertInfo.setTechnicalExcellence((String)objects[8]);
					
					Industry industry = new Industry();
					industry.setName((String)objects[10]);
					expertInfo.setBelongIndustry(industry);
					District district = new District();
					district.setName((String)objects[9]);
					expertInfo.setDistrict(district);
					expertInfo.setTenderExperience((String)objects[11]);
					dataList.add(expertInfo);
				}
				page.setData(dataList);
				
				//count
				query = session.createSQLQuery("select count(ex.expert_id) "+table + condition.toString());
				page.setTotalRowNum(new Long(query.list().get(0).toString()));
				return page;
		}});
	}

	/** 
	 * Description :  根据主键，获得专家的详细信息【基本信息，教育经历信息，培训经历信息，任职经历信息，职称管理信息】
	 * Create Date: 2010-11-26上午09:20:39 by likg  Modified Date: 2010-11-26上午09:20:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ExpertInfo getExpertAllInfo(final String objId) throws Exception {
		List<ExpertInfo> list = getExpertAllInfoList(objId);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/** 
	 * Description :  根据多个主键（已逗号分隔），获得专家的详细列表信息【基本信息，教育经历信息，培训经历信息，任职经历信息，职称管理信息】
	 * Create Date: 2010-11-26上午09:29:07 by likg  Modified Date: 2010-11-26上午09:29:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<ExpertInfo> getExpertAllInfoList(final String objIds) throws Exception {
		return (List<ExpertInfo>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				//查询专家信息
				StringBuilder hql = new StringBuilder();
				
				hql.append("from ExpertInfo as ei left join fetch ei.user as u left join fetch u.emp as e ");
				hql.append("left join fetch ei.district as d left join fetch d.parent as dp left join fetch dp.parent as dpp ");
				hql.append("left join fetch ei.educations as ed ");
				hql.append("left join fetch ei.trainings as tr ");
				hql.append("left join fetch ei.experiences ex ");
				hql.append("left join fetch ei.certificates ce ");
				hql.append("where ei.objId in (:objId) ");
				
				Query query = session.createQuery(hql.toString());
				query.setParameterList("objId", objIds.split(","));
				
				Set<ExpertInfo> expertInfoSet = new HashSet<ExpertInfo>(query.list());
				List<ExpertInfo> expertInfoList = new ArrayList<ExpertInfo>();
				expertInfoList.addAll(expertInfoSet);
				
				return expertInfoList;
			}
		});
	}
	
	/** 
	 * Description : 根据当前用户获得专家信息 
	 * Create Date: 2010-11-30下午02:26:41 by guoyr  Modified Date: 2010-11-30下午02:26:41 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public ExpertInfo getUserExpertInfo(final String userId) throws Exception {
		return (ExpertInfo) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				//查询专家信息
				StringBuilder hql = new StringBuilder();
				
				hql.append("from ExpertInfo as ei ");
				hql.append("left join fetch ei.educations as ed ");
				hql.append("left join fetch ei.trainings as tr ");
				hql.append("left join fetch ei.experiences ex ");
				hql.append("left join fetch ei.certificates ce ");
				hql.append("where ei.user.objId =:userId");
				
				Query query = session.createQuery(hql.toString());
				query.setString("userId", userId);
				List<ExpertInfo> expertInfoList = query.list();
				ExpertInfo expertInfo = null;
				
				if(null != expertInfoList && expertInfoList.size() > 0){
					expertInfo = expertInfoList.get(0);
				}else {
					expertInfo = new ExpertInfo();
				}
				return expertInfo;
			}
		});
	}
	
	/** 
	 * Description :  根据品目获得下级品目的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号,isLeaf 是否是叶子节点
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getCategoryListShowByCategory(final String categoryId,final String categoryCode,final Boolean isLeaf) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder("select a.id , a.category_code, a.category_name , b.expert_count from " +
											"purcatalog_category a, " +
											"(select t.id,count(d.EXPERT_ID) expert_count from " +
														"purcatalog_category t, " +
														"(select m.EXPERT_ID,m.APP_CATEGORY_VALUE from EXPERT_INFO m " +
																"where m.use_status =:useStatus and m.is_off =:isOff and m.audit_status =:auditStatus ) d " +
														"where d.APP_CATEGORY_VALUE like '%' || t.id || ',%' or d.APP_CATEGORY_VALUE like '%' || t.id || '#%' " +
														"group by t.id ) b " +
											"where a.id = b.id ");
				if(StringUtils.hasLength(categoryId)) {
					sql.append(" and c.id in (:categoryId) ");
				}
				if(StringUtils.hasLength(categoryCode)) {
					sql.append(" and a.category_code like :categoryCode ");
				}
				if(isLeaf) {
					sql.append(" and a.purcategory_is_leaf = :isLeaf ");
				}
				
				sql.append(" order by a.category_code");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("useStatus", OrganizationEnum.USE_VALID);
				query.setString("isOff", OrganizationEnum.ENABLE);
				query.setString("auditStatus", OrganizationEnum.PASS_EXAM);
				
				if(StringUtils.hasLength(categoryId)) {
					query.setParameterList("categoryId", categoryId.split(","));
				}
				if(StringUtils.hasLength(categoryCode)) {
					query.setString("categoryCode", categoryCode+"%");
				}
				if(isLeaf) {
					query.setBoolean("isLeaf", isLeaf);
				}
				
				List<String[]> gcList=query.list();
				
				return gcList;
			}});
	}
	
	/** 
	 * Description :  根据品目获得包含专家数量的区域信息
	 * Create Date: 2010-12-1下午05:06:35 by liangxj  Modified Date: 2010-12-1下午05:06:35 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getDistrictListForShow(final String districtId,final String categoryId,final Short districtLevel) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder("select a.district_id,a.district_name ,b.expert_count from " +
											"base_district a, " +
											"(select m.district_id,count(n.EXPERT_ID) expert_count " +
														"from base_district m, " +
														"(select s.EXPERT_ID,s.APP_DISTRICT_VALUE from EXPERT_INFO s " +
																"where s.APP_CATEGORY_VALUE like :appCategoryValue " +
																"and s.use_status =:useStatus and s.is_off =:isOff and s.AUDIT_STATUS =:auditStatus) n " +
														"where n.APP_DISTRICT_VALUE like '%' || m.district_id || ',%' or n.APP_DISTRICT_VALUE like '%' || m.district_id || '#%' " +
														"group by m.district_id ) b " +
											"where a.district_id = b.district_id ");
				
				if(StringUtils.hasLength(districtId)) {
					sql.append(" and a.district_parent_id in (:districtId) ");
				}
				if(districtLevel != 0) {
					sql.append(" and a.dis_level =:districtLeval ");
				}
				
				sql.append(" order by a.district_name");
				
				Query query = session.createSQLQuery(sql.toString());
				
				if(StringUtils.hasLength(categoryId)) {
					query.setString("appCategoryValue", "%"+categoryId+"%");
				}else {
					query.setString("appCategoryValue", "%");
				}
				
				query.setString("useStatus", OrganizationEnum.USE_VALID);
				query.setString("isOff", OrganizationEnum.ENABLE);
				query.setString("auditStatus", OrganizationEnum.PASS_EXAM);
				
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
}
