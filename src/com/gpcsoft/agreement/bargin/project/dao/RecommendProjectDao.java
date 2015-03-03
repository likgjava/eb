package com.gpcsoft.agreement.bargin.project.dao;

import java.util.Map;

import com.gpcsoft.agreement.bargin.project.domain.RecommendProject;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.domain.Project;

public interface RecommendProjectDao extends BaseGenericDao<RecommendProject> {
	/** 
	 * Description :  保存推荐项目
	 * Create Date: 2010-10-11 下午 16:30:16 by likg  Modified Date: 2010-10-11 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveRecommendProject(String[] projectIds, RecommendProject recommendProjectPattern) throws Exception;
	
	/** 
	 * Description :  获取所有的未推荐项目
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Project> listNoRecommendProject(Map<String,Object> param, Page<Project> page) throws Exception;
	
	/** 
	 * Description :  删除过时的推荐项目
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void deleteOutdatedProject() throws Exception;

	/** 
	 * Description :  获得推荐项目
	 * Create Date: 2010-10-18下午05:11:35 by likg  Modified Date: 2010-10-18下午05:11:35 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<RecommendProject> getRecommendProject(Page<RecommendProject> page, Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  修改推荐项目的排序序号
	 * Create Date: 2010-12-22上午10:43:42 by likg  Modified Date: 2010-12-22上午10:43:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void updateSort(Long sort, boolean isToUp) throws Exception;
	
}
