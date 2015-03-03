package com.gpcsoft.epp.resproject.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.tree.TreeVo;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.resproject.domain.MoneySource;
import com.gpcsoft.epp.resproject.domain.ResProject;
import com.gpcsoft.epp.resproject.domain.ResProjectItem;
import com.gpcsoft.srplatform.auth.domain.Employee;

public interface ResProjectDao extends BaseGenericDao<ResProject> {
	
	/**
	 * 查询代理机构负责人所负责的项目
	 * @param agentyId 代理机构ID
	 * @param loginUser 登录人
	 * @param queryParam 查询参数集合
	 * @param page 分页对象
	 * @return
	 * @throws Exception
	 */
	public Page<ResProject> findResProjectListOfAgentyLeader(String agentyId,Employee loginUser,Map<String,Object> queryParamMap,Page<ResProject> page)throws Exception;
	
	/**
	 * 查询代理机构负责人所负责的项目
	 * @param agentyId 代理机构ID
	 * @param queryParam 查询参数集合
	 * @param page 分页对象
	 * @return
	 * @throws Exception
	 */
	public Page<ResProject> findResProjectListOfAgenty(String agentyId,Map<String,Object> queryParamMap,Page<ResProject> page)throws Exception;
	
	/**
	 * 查询项目对应的资金来源信息集合
	 * @param resProjectId 项目ID
	 * @return
	 * @throws Exception
	 */
	public List<MoneySource> findResProjectOfMoneySourceList(String resProjectId)throws Exception;
	
	/**
	 * 查询项目对应的资金来源信息集合
	 * @param rprojectId 项目ID
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> findResProjectOfMoneySourceList(String resProjectId,String categoryType)throws Exception;
	
	/**
	 * 查询项目的子项目信息集合
	 * @param resProjectId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ResProject> findResProjectOfSubList(String resProjectId)throws Exception;
	
	/**
	 * 查询项目的子项目信息集合
	 * @param resProjectId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page<ResProject> findResProjectOfSubList(String resProjectId,Page<ResProject> page)throws Exception;
	
	/**
	 * 查询项目的树信息
	 * @return
	 * @throws Exception
	 */
	public List<TreeVo> findResProjectTree(String id,String treeLeavl)throws Exception;
	
	/**
	 * 查找通过通过resProjectId对应的源项目立项过的所有招标项目集合
	 * （如果resProjectId是顶级项目，则会得到所有子级项目对应的招标项目(如果有)或顶级项目对应的招标项目集合）
	 * （如果resProjectId是子级项目，则会得子级项目对应的招标项目集合）
	 * @param resProjectId 
	 * @return
	 * @throws Exception
	 */
	public List<Project> findTenderProjectListByResProjectId(String resProjectId)throws Exception;
	
	
	

	/**
	 * FuncName : loadResProjectInfoListForBudget
	 * Description :  
	 * Create Date: 2011-12-21下午07:34:19 by liuke
	 * Modified Date: 2011-12-21下午07:34:19 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<ResProject> findResProjectInfoListForBudget(String budgetId,Map<String,Object> queryParamMap,Page<ResProject> page)throws Exception;
	
	
	
	/**
	 * FuncName : findResProjectItemListByResProjectId
	 * Description :  
	 * Create Date: 2011-12-22上午10:46:01 by liuke
	 * Modified Date: 2011-12-22上午10:46:01 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<ResProjectItem> findResProjectItemListByResProjectId(String resProjectId)throws Exception;
}
