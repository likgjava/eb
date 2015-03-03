package com.gpcsoft.agreement.bargin.project.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.project.dao.RecommendProjectDao;
import com.gpcsoft.agreement.bargin.project.domain.RecommendProject;
import com.gpcsoft.agreement.bargin.project.enumeration.RecommendProjectEnum;
import com.gpcsoft.agreement.bargin.project.manager.RecommendProjectManager;
import com.gpcsoft.agreement.bargin.project.service.RecommendProjectService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.domain.Project;

@Service 
public class RecommendProjectServiceImpl extends BaseGenericServiceImpl<RecommendProject> implements RecommendProjectService {

	@Autowired(required=true) @Qualifier("recommendProjectManagerImpl")
	private RecommendProjectManager recommendProjectManager;
	
	@Autowired(required=true)  @Qualifier("recommendProjectDaoHibernate")
	private RecommendProjectDao recommendProjectDaoHibernate;

	/** 
	 * Description :  保存推荐项目
	 * Create Date: 2010-10-11 下午 16:30:16 by likg  Modified Date: 2010-10-11 下午 16:30:16 by likg
	 * @param   recommendProjectPattern:推荐项目模板		projectIds:项目id（以逗号分割）
	 * @return  
	 * @Exception   
	 */
	public void saveRecommendProject(RecommendProject recommendProjectPattern, String projectIds) throws Exception {
		//删除已过时的推荐项目
		recommendProjectDaoHibernate.deleteOutdatedProject();
		
		//推荐指定的项目
		recommendProjectPattern.setAuditStatus(RecommendProjectEnum.AWAIT_AUDIT);
		recommendProjectPattern.setUseStatus(true);
		recommendProjectPattern.setCreateTime(new Date());
		recommendProjectPattern.setSort(0L);
		recommendProjectDaoHibernate.saveRecommendProject(projectIds.split(","), recommendProjectPattern);
	}

	/** 
	 * Description :  获取所有的未推荐项目
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Project> listNoRecommendProject(Map<String,Object> param, Page<Project> page) throws Exception {
		return recommendProjectDaoHibernate.listNoRecommendProject(param, page);
	}

	/** 
	 * Description :  修改推荐项目的排序序号
	 * Create Date: 2010-10-15上午10:43:42 by likg  Modified Date: 2010-10-15上午10:43:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateSort(String sourceObjId, boolean isToUp) throws Exception {
		RecommendProject sourceRecommendProject = recommendProjectManager.get(sourceObjId);
		recommendProjectDaoHibernate.updateSort(sourceRecommendProject.getSort(), isToUp);
	}

	/** 
	 * Description :  获得推荐项目
	 * Create Date: 2010-10-18下午05:08:32 by likg  Modified Date: 2010-10-18下午05:08:32 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<RecommendProject> getRecommendProject(Page<RecommendProject> page, Map<String, Object> paramsMap) throws Exception {
		return recommendProjectDaoHibernate.getRecommendProject(page, paramsMap);
	}

}
