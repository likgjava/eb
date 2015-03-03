package com.gpcsoft.epp.project.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.project.dao.ProjectItemTendDao;
import com.gpcsoft.epp.project.domain.ProjectItemTend;

@Repository
@SuppressWarnings(value={"unchecked"})
public class ProjectItemTendDaoHibernate extends BaseGenericDaoHibernate<ProjectItemTend> implements ProjectItemTendDao {
	
	
	
	/**
	 * FuncName: getProjectListByProjectItem
	 * Description :   获取已经根据条目创建的项目
	 * @param   List<ProjectItemTend>
	 * @author: shenjz
	 * @Create Date:2011-12-23  上午10:12:09
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-23  上午10:12:09
	 */
	public List<ProjectItemTend> getProjectListByProjectItem(String itemId){
		return this.findbyHql("from ProjectItemTend t where t.resProjectItem.objId = '"+itemId+"'");
	}

}
