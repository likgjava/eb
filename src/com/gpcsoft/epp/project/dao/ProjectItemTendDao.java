package com.gpcsoft.epp.project.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.project.domain.ProjectItemTend;


@SuppressWarnings(value={"unchecked"})
public interface ProjectItemTendDao extends BaseGenericDao<ProjectItemTend> {
	
	/**
	 * FuncName: getProjectListByProjectItem
	 * Description :  获取已经根据条目创建的项目
	 * @param   List<ProjectItemTend>
	 * @author: shenjz
	 * @Create Date:2011-12-23  上午10:12:09
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-23  上午10:12:09
	 */
	public List<ProjectItemTend> getProjectListByProjectItem(String itemId);
	
}
