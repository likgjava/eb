package com.gpcsoft.epp.resproject.service;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.resproject.domain.ResProject;
import com.gpcsoft.epp.resproject.domain.ResProjectItem;
import com.gpcsoft.srplatform.auth.domain.User;

public interface ResProjectService extends BaseGenericService<ResProject>{
	

	/**
	 * 查询项目列表(代理机构)
	 * @param queryParamMap 查询 参数集合
	 * @param page 分页对象
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findResProjectInfoListForAgenty(Map<String,Object> queryParamMap,Page<ResProject> page)throws Exception;
	
	/**
	 * 查询项目详细信息
	 * @param resProjectId 项目ID
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findResProjectInfo(String parentId,String resProjectId)throws Exception;
	
	/**
	 * 保存项目信息
	 * @param resProject
	 * @param moneySourceMap
	 * @throws Exception
	 */
	public void saveResProjectInfo(ResProject resProject,Map<String,String[]> moneySourceMap)throws Exception;
	
	/**
	 * 删除项目信息
	 * @param resProjectId 项目ID
	 * @throws Exception
	 */
	public void deleteResProjectInfo(String resProjectId)throws Exception;
	
	/**
	 * 结束项目
	 * @param resProjectId
	 * @throws Exception
	 */
	public void updateResProjectToEnd(String resProjectId)throws Exception;
	
	/**
	 * 查询项目的子项目信息
	 * @param resProjectId 项目ID
	 * @param page 分页对象
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findResProjectOfSubList(String resProjectId,Page<ResProject> page)throws Exception;
	
	/**
	 * 查询子项目详细信息
	 * @param parentId 父项目ID
	 * @param subResProjectId 子项目ID
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findSubResProjectInfo(String parentId,String subResProjectId)throws Exception;
	
	/**
	 * 保存招标项目
	 * @param project
	 * @param resProjectId
	 * @throws Exception
	 */
	public void saveTenderProjectInfo(Project project,String resProjectId,String projSubIds)throws Exception;
	
	
	/**
	 * FuncName : loadResProjectInfoListForBudget
	 * Description :  
	 * Create Date: 2011-12-21下午07:31:45 by liuke
	 * Modified Date: 2011-12-21下午07:31:45 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String,Object> findResProjectInfoListForBudget(Map<String,Object> queryParamMap,Page<ResProject> page)throws Exception;
	
	
	
	
	
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
	
	
	
	
	/**
	 * FuncName : saveResProjectItem
	 * Description :  
	 * Create Date: 2011-12-22下午03:17:17 by liuke
	 * Modified Date: 2011-12-22下午03:17:17 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveResProjectItem(ResProjectItem resProjectItem);
	
	
	/**
	 * FuncName : saveResProject
	 * Description :  
	 * Create Date: 2011-12-23上午11:37:01 by liuke
	 * Modified Date: 2011-12-23上午11:37:01 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveResProject(ResProject resProject);
	
	/** 
	 * FuncName : saveResProjectForAudit
	 * Description :  审核招标项目
	 * Create Date: 2011-12-24下午12:27:52 by yangx  
	 * Modified Date: 2011-12-24下午12:27:52 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ResProject saveResProjectForAudit(ResProject resProject,String opinion);
}
