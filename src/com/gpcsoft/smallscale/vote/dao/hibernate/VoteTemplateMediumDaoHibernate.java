package com.gpcsoft.smallscale.vote.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.vote.dao.VoteTemplateMediumDao;
import com.gpcsoft.smallscale.vote.domain.VoteTemplateMedium;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class VoteTemplateMediumDaoHibernate extends BaseGenericDaoHibernate<VoteTemplateMedium> implements VoteTemplateMediumDao {

	/**
	 * 从机构库里赛选媒体
	 */
	@SuppressWarnings("unchecked")
	public Page<OrgInfo> getMediumOrgList(final Page<OrgInfo> page,final Map<String, Object> paramsMap) {
		return (Page<OrgInfo>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String roleId = (String) paramsMap.get("roleId");//角色Id  此处是媒体角色
				String mediumName = (String) paramsMap.get("mediumName");
				StringBuilder hql = new StringBuilder();
				StringBuilder preHql = new StringBuilder();
				hql.append("from OrgInfo o where o.useStatus = '01' and o.isOff = '1' and o.company.objId in(");
				preHql.append("select r.orgId from OrgRole r where r.rolId = :roleId");
				hql.append(preHql).append(")");
				if(mediumName!=null && mediumName!=""){
					hql.append(" and o.orgName like '%"+mediumName+"%'");
				}
				
				Query query = session.createQuery(hql.toString());
				query.setString("roleId", roleId);
				
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<OrgInfo> orgInfoList = query.list();
				page.setData(orgInfoList);
				
				StringBuffer queryCountStr = new StringBuffer("select count(o.objId) from OrgInfo o where o.useStatus = '01'and o.company.objId in (");
				queryCountStr.append(preHql).append(")");
				if(mediumName!=null && mediumName!=""){
					hql.append(" and o.orgName like '%"+mediumName+"%'");
				}
				query = session.createQuery(queryCountStr.toString());
				query.setString("roleId", roleId);
				page.setTotalRowNum((Long)query.list().get(0));
				
				return page;
			}
		});
	}

	/**
	 * 媒体hql操作
	 */
	@SuppressWarnings("unchecked")
	public boolean operateMediumParams(final Map<String, Object> paramsMap) {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				boolean flag = false;
				String templateId = (String) paramsMap.get("templateId");//主题
				String mediumId = (String) paramsMap.get("mediumId");//媒体
				String templateMediumId = (String) paramsMap.get("templateMediumId");//主题媒体表
				Integer mediumSort = (Integer) paramsMap.get("mediumSort");//排序号
				String operation = (String) paramsMap.get("operationType");//操作类型
				
				StringBuffer hql = new StringBuffer();
				if("isHasMedium".equals(operation)){//判断媒体是否已是合作媒体
					hql.append("from VoteTemplateMedium vtm where vtm.voteTemplate.objId = :templateId ");
					hql.append("and vtm.orgInfo.objId = :mediumId");
					
					Query query = session.createQuery(hql.toString());
					query.setString("templateId", templateId);
					query.setString("mediumId", mediumId);
					List<Long>  list =  query.list();
					
					if(list.size() > 0){
						flag = true;
					}
				}
				if("setMediumSort".equals(operation)){//设置排序号
					hql.append("update VoteTemplateMedium vtm set vtm.mediumSort = :mediumSort where vtm.objId = :templateMediumId");
					Query query = session.createQuery(hql.toString());
					query.setLong("mediumSort", mediumSort);
					query.setString("templateMediumId", templateMediumId);
					query.executeUpdate();
					flag = true;
				}
				
				return flag;
			}
			
		});
	}

	/**
	 * 生成排序号
	 */
	@SuppressWarnings("unchecked")
	public Integer generateMediumSort(Map<String, Object> paramsMap) {
		String templateId = (String) paramsMap.get("templateId");
		List<Object> list = null;
		list = getHibernateTemplate().find("select Max(vtm.mediumSort) from VoteTemplateMedium vtm where vtm.voteTemplate.objId = '"+templateId+"'");
		return (Integer)list.get(0);
	}

	/**
	 * 合作媒体分页列表
	 */
	@SuppressWarnings("unchecked")
	public Page<VoteTemplateMedium> getVoteTemplateMediumPage(final Page<VoteTemplateMedium> page, final Map<String, Object> paramsMap) {
		return (Page<VoteTemplateMedium>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String templateId = (String) paramsMap.get("voteTemplateId"); //模板主题Id
				Integer rownum = (Integer) paramsMap.get("rownum");//首页显示数据数目
				
				StringBuffer hql = new StringBuffer();
				hql.append("from VoteTemplateMedium vtm where vtm.voteTemplate.objId = :templateId");
				
				if(rownum != null){
					hql.append(" and rownum <= :rownum");//前rownum条数据
				}
				hql.append(" order by vtm.mediumSort asc,vtm.createTime asc");
				
				Query query = session.createQuery(hql.toString());
				query.setString("templateId", templateId);
				if(rownum != null){
					query.setInteger("rownum", rownum);
				}
				
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<VoteTemplateMedium> voteTemplateMedium = query.list();
				page.setData(voteTemplateMedium);
				
				StringBuffer queryCountStr = new StringBuffer("select count(vtm.objId) from VoteTemplateMedium vtm where vtm.voteTemplate.objId = :templateId");
				if(rownum != null){
					queryCountStr.append(" and rownum <= :rownum");//前rownum条数据
				}
				query = session.createQuery(queryCountStr.toString());
				query.setString("templateId", templateId);
				if(rownum != null){
					query.setInteger("rownum", rownum);
				}
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
			}
		});
	}

}
