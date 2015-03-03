package com.gpcsoft.epp.resproject.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.tree.TreeVo;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.resproject.dao.ResProjectDao;
import com.gpcsoft.epp.resproject.domain.MoneySource;
import com.gpcsoft.epp.resproject.domain.ResProject;
import com.gpcsoft.epp.resproject.domain.ResProjectItem;
import com.gpcsoft.srplatform.auth.domain.Employee;

@Repository
public class ResProjectDaoHibernate  extends BaseGenericDaoHibernate<ResProject> implements ResProjectDao {


	/**
	 * 查询代理机构负责人所负责的项目
	 * @param agentyId 代理机构ID
	 * @param loginUser 登录人
	 * @param queryParam 查询参数集合
	 * @param page 分页对象
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Page<ResProject> findResProjectListOfAgentyLeader(String agentyId,Employee loginUser,Map<String,Object> queryParamMap,Page<ResProject> page)throws Exception{
		String projectName = (String)queryParamMap.get("projectName");
		String projectStatus = (String)queryParamMap.get("projectStatus");
		StringBuffer hql = new StringBuffer();
		
		hql.append(" from ResProject t where t.agenty.objId=:agentyId  and t.parent is null ");
		if(StringUtils.isNotEmpty(projectStatus)){
			hql.append(" and t.projectStatus = '"+projectStatus+"'");	
		}
		hql.append(" and (t.createUser.objId=:createUser or t.agentyLeader.objId=:agentyLeader) ");
		if(StringUtils.isNotEmpty(projectName)){
			hql.append(" and t.projectName like :projectName ");
		}
		Session session = this.getSession();
		Query query = session.createQuery(hql.toString());
		query.setString("agentyId", agentyId);
		query.setString("createUser", loginUser.getObjId());
		query.setString("agentyLeader", loginUser.getObjId());
		if(StringUtils.isNotEmpty(projectName)){
			query.setString("projectName", "%"+projectName+"%");
		}
		
		int totalCount =query.list().size();
		if(totalCount < 1) return new Page<ResProject>();
		
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getPageSize());
		List<ResProject> list = query.list();
		return new Page<ResProject>(page.getStart(), totalCount, page.getPageSize(), list);
	}
	
	/**
	 * 查询代理机构负责人所负责的项目
	 * @param agentyId 代理机构ID
	 * @param queryParam 查询参数集合
	 * @param page 分页对象
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Page<ResProject> findResProjectListOfAgenty(String agentyId,Map<String,Object> queryParamMap,Page<ResProject> page)throws Exception{
		String projectName = (String)queryParamMap.get("projectName");
		StringBuffer hql = new StringBuffer();
		
		hql.append(" from ResProject t where t.agenty.objId=:agentyId  and t.parent is null");
		if(StringUtils.isNotEmpty(projectName)){
			hql.append(" and t.projectName like :projectName ");
		}
		
		Query query = this.getSession().createQuery(hql.toString());
		query.setString("agentyId", agentyId);
		if(StringUtils.isNotEmpty(projectName)){
			query.setString("projectName", "%"+projectName+"%");
		}
		
		int totalCount =query.list().size();
		if(totalCount < 1) return new Page<ResProject>();
		
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getPageSize());
		List<ResProject> list = query.list();
		return new Page<ResProject>(page.getStart(), totalCount, page.getPageSize(), list);
	}
	
	/**
	 * 查询项目对应的资金来源信息集合
	 * @param rprojectId 项目ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MoneySource> findResProjectOfMoneySourceList(String resProjectId)throws Exception{
		return this.getHibernateTemplate().find("from MoneySource t where t.resProject.objId='"+resProjectId+"'");
	}
	
	/**
	 * 查询项目对应的资金来源信息集合
	 * @param rprojectId 项目ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findResProjectOfMoneySourceList(String resProjectId,String categoryType)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select c.id,c.zjly_name,nvl(t.money,0) from                                                    ");
		sql.append(" (select * from ecp_money_source s where s.project_id='"+resProjectId+"') t right join epm_01_zjly_category c  ");
		sql.append(" on t.zjly_category_id = c.id                                                                   ");
		sql.append(" where c.type='"+categoryType+"'                                                                ");
		sql.append(" order by c.sort                                                                                ");
		
		SQLQuery query = this.getSession().createSQLQuery(sql.toString());
		List<Object[]> list = query.list();
		
		return list;
	}
	
	/**
	 * 查询项目的子项目信息集合
	 * @param resProjectId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ResProject> findResProjectOfSubList(String resProjectId)throws Exception{
		StringBuffer hql = new StringBuffer();
		hql.append(" from ResProject t where t.parent.objId='"+resProjectId+"'");
		return this.getHibernateTemplate().find(hql.toString());
	}
	
	/**
	 * 查询项目的子项目信息集合
	 * @param resProjectId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page<ResProject> findResProjectOfSubList(String resProjectId,Page<ResProject> page)throws Exception{
		StringBuffer hql = new StringBuffer();
		hql.append(" from ResProject t where t.parent.objId='"+resProjectId+"'");
		return this.findbyHql(hql.toString(), page.getStart(), page.getPageSize());
	}
	
	/**
	 * 查询项目的子项目信息集合
	 * @param resProjectId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TreeVo> findResProjectTree(String id,String treeLeavl)throws Exception{
		StringBuffer hql = new StringBuffer();
		List<ResProject> list = null;
		if("3".equals(treeLeavl)){
			list = this.getHibernateTemplate().find(" from ResProject t where t.budget.company.objId='"+id+"' and t.parent.objId is null");
		}else if("4".equals(treeLeavl)){
			list = this.getHibernateTemplate().find(" from ResProject t where t.parent.objId='"+id+"'");
		}
		
		List<TreeVo> result=new ArrayList<TreeVo>();
		for(ResProject resProject:list){
			TreeVo treeVo=new TreeVo();
			treeVo.setTreeId(resProject.getObjId());
			treeVo.setTreeName(resProject.getProjectName());
			treeVo.setParentId(resProject.getParent()==null?null:resProject.getParent().getObjId());
			treeVo.setLeaf(resProject.getParent()==null);
			result.add(treeVo);
		}
		return result;
	}
	
	/**
	 * 查找通过通过resProjectId对应的源项目立项过的所有招标项目集合
	 * （如果resProjectId是顶级项目，则会得到所有子级项目对应的招标项目(如果有)或顶级项目对应的招标项目集合）
	 * （如果resProjectId是子级项目，则会得子级项目对应的招标项目集合）
	 * @param resProjectId 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Project> findTenderProjectListByResProjectId(String resProjectId)throws Exception{
		StringBuffer hql = new StringBuffer();
		ResProject resProject  = this.get(resProjectId);
		if(resProject.getParent()==null){//顶级项目
			List<ResProject> subResProjectList = findResProjectOfSubList(resProjectId);
			if(subResProjectList.size()>0){
				hql.append("from Project p where p.resProjectId in(select t.objId from ResProject t where t.parent.objId='"+resProjectId+"'");
			}else{
				hql.append("from Project p where p.resProjectId ='"+resProjectId+"'");
			}
		}else{//子级项目
			hql.append("from Project p where p.resProjectId ='"+resProjectId+"'");
		}
		return this.getHibernateTemplate().find(hql.toString());
	}

	
	/**
	 * FuncName : loadResProjectInfoListForBudget
	 * Description :  
	 * Create Date: 2011-12-21下午07:34:19 by liuke
	 * Modified Date: 2011-12-21下午07:34:19 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<ResProject> findResProjectInfoListForBudget(String budgetId,
			Map<String, Object> queryParamMap, Page<ResProject> page)
			throws Exception {
		String projectName = (String)queryParamMap.get("projectName");
		String budgetName = (String)queryParamMap.get("budgetName");
		StringBuffer hql = new StringBuffer();
		
		hql.append(" from ResProject t where t.createUser.objId=:budgetId  and t.parent is null and t.useStatus = '01' and t.recordForm.auditStatus='01'");
		if(StringUtils.isNotEmpty(projectName)){
			hql.append(" and t.projectName like :projectName ");
		}
		if(StringUtils.isNotEmpty(budgetName)){
			hql.append(" and t.budgetName like :budgetName ");
		}
		hql.append(" order by t.createTime desc ");
		Query query = this.getSession().createQuery(hql.toString());
		query.setString("budgetId", budgetId);
		if(StringUtils.isNotEmpty(projectName)){
			query.setString("projectName", "%"+projectName+"%");
		}
		if(StringUtils.isNotEmpty(budgetName)){
			query.setString("budgetName", "%"+budgetName+"%");
		}
		
		int totalCount =query.list().size();
		if(totalCount < 1) return new Page<ResProject>();
		
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getPageSize());
		List<ResProject> list = query.list();
		return new Page<ResProject>(page.getStart(), totalCount, page.getPageSize(), list);
	}

	
	/**
	 * FuncName : findResProjectItemListByResProjectId
	 * Description :  
	 * Create Date: 2011-12-22上午10:46:01 by liuke
	 * Modified Date: 2011-12-22上午10:46:01 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ResProjectItem> findResProjectItemListByResProjectId(
			String resProjectId) throws Exception {
		return this.getHibernateTemplate().find("from ResProjectItem t where t.resProject.objId = ?", resProjectId);
	}
}
