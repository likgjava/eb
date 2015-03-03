package com.gpcsoft.smallscale.vote.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.vote.dao.VoteTemplateDao;
import com.gpcsoft.smallscale.vote.domain.VoteAssesseExpert;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedThing;
import com.gpcsoft.smallscale.vote.domain.VoteTemplate;
import com.gpcsoft.smallscale.vote.domain.VoteUnitGroup;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class VoteTemplateDaoHibernate extends BaseGenericDaoHibernate<VoteTemplate> implements VoteTemplateDao {

	/**
	 * 获取当前主题最大的编号
	 */
	@SuppressWarnings("unchecked")
	public String getMaxVoteTemplateCode() {
		List<Object> list = null;
		list = getHibernateTemplate().find("select Max(templateCode) from VoteTemplate ");
		return (String)list.get(0);
	}
	
	/**
	 * 主题统计(主题下的评选单位的参评人数)
	 */
	@SuppressWarnings("unchecked")
	public List<Object> toTemplateStatistic(final String objId){
		return (ArrayList<Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer("select vt.attender_name, countThing.countNum, vt.assessed_thing_id,'' from vote_assessed_thing vt,");
				sql.append("(select v.ASSESSED_THING_ID, count(v.ASSESSED_THING_ID) as countNum from vote_assessed v where v.template_id = '"+objId);
				sql.append("' group by v.ASSESSED_THING_ID) countThing where vt.assessed_thing_id = countThing.ASSESSED_THING_ID(+) and vt.template_id = '"+objId+"'");
				Query query = session.createSQLQuery(sql.toString());
			
				return query.list();			
			}
		});
		
	}

	/**
	 * 获取主题下的评选组
	 */
	@SuppressWarnings("unchecked")
	public Page<VoteUnitGroup> getVoteUnitGroupList(final Page<VoteUnitGroup> page,final Map<String, Object> paramsMap) {
		return (Page<VoteUnitGroup>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String templateId = (String) paramsMap.get("templateId"); //模板主题Id
				String groupName = (String) paramsMap.get("groupName");
				
				StringBuffer hql = new StringBuffer();
				hql.append("from VoteUnitGroup ug where ug.voteTemplate.objId = :templateId");
				if(groupName != null && !"".equals(groupName)){
					hql.append(" and ug.groupName like '%"+groupName+"%'");
				}
				hql.append(" order by ug.createTime desc");
				
				Query query = session.createQuery(hql.toString());
				query.setString("templateId", templateId);
				
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<VoteUnitGroup> voteUnitGroupList = query.list();
				page.setData(voteUnitGroupList);
				
				String queryCountStr = "select count(f.objId) from VoteUnitGroup f where f.voteTemplate.objId = :templateId";
				query = session.createQuery(queryCountStr);
				query.setString("templateId", templateId);
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
			}
		});
	}

	/**
	 * 主题下的参选对象列表
	 */
	@SuppressWarnings("unchecked")
	public Page<VoteAssessedThing> getVoteAssessedObjectList(final Page<VoteAssessedThing> page, final Map<String, Object> paramsMap) {
		return (Page<VoteAssessedThing>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder preHql = new StringBuilder();
				StringBuilder hql = new StringBuilder();
				String templateId = (String) paramsMap.get("voteTemplateId");//主题Id
				String voteUnitGroupId = (String) paramsMap.get("voteUnitGroupId");//评选组Id
				Integer rownum = (Integer) paramsMap.get("rownum");//首页显示数据数目
				
				preHql.append("from VoteAssessedThing vat");
				hql.append(" where vat.voteTemplate.objId = :templateId");//模板Id
				hql.append(" and vat.useStatus = '01'");//有效状态
				if(voteUnitGroupId != null){
					hql.append(" and vat.voteUnitGroup.objId = :voteUnitGroupId");//评选组Id
				}
				if(rownum != null){
					hql.append(" and rownum <= :rownum");//前rownum条数据
				}
				hql.append(" order by vat.isRecommended desc,vat.voteFinalCountGrade desc,vat.voteCountNum desc,vat.voteCommentNum desc,vat.assessSort desc,vat.createTime asc");//排序
				
				Query query = session.createQuery(preHql.append(hql).toString());
				query.setString("templateId", templateId);
				
				if(voteUnitGroupId != null){
					query.setString("voteUnitGroupId", voteUnitGroupId);
				}
				if(rownum != null){
					query.setInteger("rownum", rownum);
				}
				
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<VoteAssessedThing> voteAssessedThingList = query.list();
				page.setData(voteAssessedThingList);
				
				String queryCountStr = "select count(vat.objId) from VoteAssessedThing vat";
				query = session.createQuery(queryCountStr+hql.toString());
				query.setString("templateId", templateId);
				if(voteUnitGroupId != null){
					query.setString("voteUnitGroupId", voteUnitGroupId);
				}
				if(rownum != null){
					query.setInteger("rownum", rownum);
				}
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
			}
		});
	}

	/**
	 * 获取参选对象的所属机构
	 */
	@SuppressWarnings("unchecked")
	public Page<OrgInfo> getVoteBelongsOrgInfoList(final Page<OrgInfo> page,final Map<String, Object> paramsMap) {
		return (Page<OrgInfo>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String voteTemplateId = (String) paramsMap.get("voteTemplateId");//主题id
				String groupType = (String) paramsMap.get("groupType");//评选组类型
				Integer rownum = (Integer) paramsMap.get("rownum");//数据设置数目
				
				StringBuffer orgHql = new StringBuffer();
				StringBuffer voteHql = new StringBuffer();
				StringBuffer brandHql = new StringBuffer();
				
				orgHql.append("select o.objId, o.orgName, o.logo, o.buyerId, o.supplierId from OrgInfo o where o.objId in (");
				voteHql.append("select distinct vat.attender from VoteAssessedThing vat where vat.voteTemplate.objId = :templateId and vat.attender is not null and vat.useStatus = '01'");
				brandHql.append("select distinct b.belongsId from GoodsBrand b where b.objId in("+voteHql.toString()+")");
				if("00".endsWith(groupType)){//企业
					orgHql.append(voteHql);
					orgHql.append(")");
				}
				if("01".endsWith(groupType)){//品牌
					orgHql.append(brandHql);
					orgHql.append(")");
				}
				
				if(rownum != null){
					orgHql.append(" and rownum <= :rownum");
				}
				
				Query query = session.createQuery(orgHql.toString());
				query.setString("templateId", voteTemplateId);
				if(rownum != null){
					query.setInteger("rownum", rownum);
				}
				
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<OrgInfo> orgInfoList = query.list();
				page.setData(orgInfoList);
				
				StringBuffer queryCountStr = new StringBuffer("select count(o.objId) from OrgInfo o where o.objId in (");
				if("00".endsWith(groupType)){//企业
					queryCountStr.append(voteHql);
					queryCountStr.append(")");
				}
				if("01".endsWith(groupType)){//品牌
					queryCountStr.append(brandHql);
					queryCountStr.append(")");
				}
				if(rownum != null){
					queryCountStr.append(" and rownum <= :rownum");
				}
				query = session.createQuery(queryCountStr.toString());
				query.setString("templateId", voteTemplateId);
				if(rownum != null){
					query.setInteger("rownum", rownum);
				}
				page.setTotalRowNum((Long)query.list().get(0));
				
				return page;
			}
		});
	}

	/**
	 * 获取主题下评审专家列表
	 */
	@SuppressWarnings("unchecked")
	public Page<VoteAssesseExpert> getVoteAssesseExpertList(final Page<VoteAssesseExpert> page, final Map<String, Object> paramsMap) {
		return (Page<VoteAssesseExpert>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String templateId = (String) paramsMap.get("voteTemplateId"); //模板主题Id
				String voteUnitGroupId = (String) paramsMap.get("voteUnitGroupId");//评选组Id
				Integer rownum = (Integer) paramsMap.get("rownum");//首页显示数据数目
				String expertName = (String) paramsMap.get("expertName");
				
				StringBuffer hql = new StringBuffer();
				hql.append("from VoteAssesseExpert ex where ex.voteTemplate.objId = :templateId");
				
				if(voteUnitGroupId != null && !"".endsWith(voteUnitGroupId)){
					hql.append(" and ex.voteUnitGroup.objId = :voteUnitGroupId");//评选组Id
				}
				if(rownum != null){
					hql.append(" and rownum <= :rownum");//前rownum条数据
				}
				if(expertName != null && !"".equals(expertName)){
					hql.append(" and expertName like '%"+expertName+"%'");
				}
				hql.append(" order by ex.expertSort desc, ex.createTime desc");
				
				Query query = session.createQuery(hql.toString());
				query.setString("templateId", templateId);
				if(voteUnitGroupId != null && !"".endsWith(voteUnitGroupId)){
					query.setString("voteUnitGroupId", voteUnitGroupId);
				}
				if(rownum != null){
					query.setInteger("rownum", rownum);
				}
				
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<VoteAssesseExpert> voteAssesseExpert = query.list();
				page.setData(voteAssesseExpert);
				
				StringBuffer queryCountStr = new StringBuffer("select count(ex.objId) from VoteAssesseExpert ex where ex.voteTemplate.objId = :templateId");
				if(voteUnitGroupId != null && !"".endsWith(voteUnitGroupId)){
					queryCountStr.append(" and ex.voteUnitGroup.objId = :voteUnitGroupId");//评选组Id
				}
				if(rownum != null){
					queryCountStr.append(" and rownum <= :rownum");//前rownum条数据
				}
				query = session.createQuery(queryCountStr.toString());
				query.setString("templateId", templateId);
				if(voteUnitGroupId != null && !"".endsWith(voteUnitGroupId)){
					query.setString("voteUnitGroupId", voteUnitGroupId);
				}
				if(rownum != null){
					query.setInteger("rownum", rownum);
				}
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
			}
		});
	}

}
